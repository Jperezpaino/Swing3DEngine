package app;

// Java imports
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

// Project imports
import app.*;
import config.*;
import util.*;
import vue.*;

// Application window manager
public class App {

    // Instance fields
    private Config config; // Global settings
private int anaglyph; // TEMP: Anaglyph color setting

    // Component fields
    private JFileChooser dlgFile; // File selection dialog
    private ArrayList<MainWindow> windows; // Main window environment



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

// TEMP: Anaglyph colors
private static final int[][] COLORS = {
    { 0xFF0000, 0x00C6F0 }, // Red/cyan
    { 0x00B400, 0xC800FF }, // Green/magenta
    { 0xFF0000, 0x0000FF }  // Red/blue
};

    // Global command indexes
    private static final int DEBUGMODE       =  0;
    private static final int DISCONNECT      =  1;
    private static final int EXIT            =  2;
    private static final int FRAMEADVANCE    =  3;
    private static final int LOADROM         =  4;
    private static final int RESET           =  5;
    private static final int RUNPAUSE        =  6;
    private static final int SHOWBREAKPOINTS =  7;
    private static final int SHOWCONSOLE     =  8;
    private static final int SHOWCPU         =  9;
    private static final int SHOWVIP         = 10;
    private static final int STEP            = 11;
    private static final int STEPOVER        = 12;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public App() {

        // Configure global keyboard handler
        KeyboardFocusManager.getCurrentKeyboardFocusManager()
            .addKeyEventDispatcher(e->globalKey(e));

        // Configure global settings
        initConfig();

        // Configure Load ROM dialog box
        dlgFile = new JFileChooser(".");
        config.addLocaleComponent(dlgFile, (String) null);

        // Configure windows
        windows = new ArrayList<MainWindow>();
        createWindow();
    }

    // Initialize global settings
    private void initConfig() {
        config = new Config();

        // Configure locale
        config.setLocale("locale/en-US.txt");

        // Configure fonts
        Font dialog = Util.getDefaultFont();
        Font fixed  = Util.getFont(Util.fontExists("Consolas") ? "Consolas" :
            Font.MONOSPACED, Font.PLAIN, Math.max(16,
                Util.getFontMetrics(dialog).getHeight()));
        config.setFonts(dialog, fixed);

        // Configure global commands
        config.addAction("debugMode"   , ()->cmdActive(DEBUGMODE   ),new Input(
            Input.KEY, InputEvent.CTRL_DOWN_MASK , KeyEvent.VK_D  ));
        config.addAction("disconnect"  , ()->cmdActive(DISCONNECT  ),new Input(
            Input.KEY, InputEvent.CTRL_DOWN_MASK , KeyEvent.VK_X  ));
        config.addAction("exit"        , ()->cmdActive(EXIT        ),new Input(
            Input.KEY, InputEvent.ALT_DOWN_MASK  , KeyEvent.VK_F4 ));
        config.addAction("frameAdvance", ()->cmdActive(FRAMEADVANCE),new Input(
            Input.KEY,                          0, KeyEvent.VK_F6 ));
        config.addAction("loadROM"     , ()->cmdActive(LOADROM     ),new Input(
            Input.KEY, InputEvent.CTRL_DOWN_MASK , KeyEvent.VK_O  ));
        config.addAction("newWindow"   , ()->createWindow   (), new Input(
            Input.KEY, InputEvent.CTRL_DOWN_MASK , KeyEvent.VK_N  ));
        config.addAction("reset"       , ()->cmdActive(RESET       ),new Input(
            Input.KEY, InputEvent.SHIFT_DOWN_MASK, KeyEvent.VK_F5 ));
        config.addAction("runPause"    , ()->cmdActive(RUNPAUSE    ),new Input(
            Input.KEY,                          0, KeyEvent.VK_F5 ));
        config.addAction("showBreakpoints", ()->cmdActive(SHOWBREAKPOINTS));
        config.addAction("showConsole"    , ()->cmdActive(SHOWCONSOLE));
        config.addAction("showCPU"        , ()->cmdActive(SHOWCPU    ));
        config.addAction("showVIP"        , ()->cmdActive(SHOWVIP    ));
        config.addAction("step"        , ()->cmdActive(STEP        ),new Input(
            Input.KEY,                          0, KeyEvent.VK_F11));
        config.addAction("stepOver"    , ()->cmdActive(STEPOVER    ),new Input(
            Input.KEY,                          0, KeyEvent.VK_F10));

// TEMP: Toggle audio
config.addAction("", ()->config.setAudioEnabled(!config.isAudioEnabled()),
new Input(Input.KEY, InputEvent.CTRL_DOWN_MASK, KeyEvent.VK_A));

// TEMP: Anaglyph color cycler
config.addAction("cycleColors", ()->{
    anaglyph = (anaglyph + 1) % COLORS.length;
    int[] set = COLORS[anaglyph];
    config.setLeftColor(set[0]);
    config.setRightColor(set[1]);
    for (int x = 0; x < windows.size(); x++)
        windows.get(x).updateColors();
}, new Input(
Input.KEY, InputEvent.CTRL_DOWN_MASK, KeyEvent.VK_3));

        // Configure game controls
        config.addAction("keyA"     , VUE.A,
            new Input(Input.KEY, 0, KeyEvent.VK_F));
        config.addAction("keyB"     , VUE.B,
            new Input(Input.KEY, 0, KeyEvent.VK_D));
        config.addAction("keyL"     , VUE.L,
            new Input(Input.KEY, 0, KeyEvent.VK_E));
        config.addAction("keyR"     , VUE.R,
            new Input(Input.KEY, 0, KeyEvent.VK_R));
        config.addAction("keyStart" , VUE.START,
            new Input(Input.KEY, 0, KeyEvent.VK_S));
        config.addAction("keySelect", VUE.SELECT,
            new Input(Input.KEY, 0, KeyEvent.VK_A));
        config.addAction("keyRRight", VUE.RRIGHT,
            new Input(Input.KEY, 0, KeyEvent.VK_L));
        config.addAction("keyRUp"   , VUE.RUP,
            new Input(Input.KEY, 0, KeyEvent.VK_I));
        config.addAction("keyRLeft" , VUE.RLEFT,
            new Input(Input.KEY, 0, KeyEvent.VK_J));
        config.addAction("keyRDown" , VUE.RDOWN,
            new Input(Input.KEY, 0, KeyEvent.VK_K));
        config.addAction("keyLRight", VUE.LRIGHT,
            new Input(Input.KEY, 0, KeyEvent.VK_RIGHT));
        config.addAction("keyLUp"   , VUE.LUP,
            new Input(Input.KEY, 0, KeyEvent.VK_UP));
        config.addAction("keyLLeft" , VUE.LLEFT,
            new Input(Input.KEY, 0, KeyEvent.VK_LEFT));
        config.addAction("keyLDown" , VUE.LDOWN,
            new Input(Input.KEY, 0, KeyEvent.VK_DOWN));
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update window properties
    void configWindows() {

        // Update window numbers
        if (windows.size() == 1)
            windows.get(0).setNumber(0);
        else for (int x = 0; x < windows.size(); x++)
            windows.get(x).setNumber(x + 1);

        // Update link menus
        for (int x = 0; x < windows.size(); x++)
            windows.get(x).setLinkPeers(windows);

        // Update window titles
        for (int x = 0; x < windows.size(); x++)
            windows.get(x).updateTitle();
    }

    // Create a new main window and associate it with the environment
    MainWindow createWindow() {
        MainWindow window = new MainWindow(this);
        windows.add(window);
        configWindows();
        return window;
    }

    // Retrieve the global Config object
    Config getConfig() {
        return config;
    }

    // Prompt the user to select a file
    File promptFile(Component parent, boolean load, String suffix,
        String[] fileTypes) {

        // Configure the dialog component
        dlgFile.setDialogType(load ?
            JFileChooser.OPEN_DIALOG : JFileChooser.SAVE_DIALOG);
        dlgFile.putClientProperty("accept",
            "dialog." + (load ? "load" : "save"));
        dlgFile.putClientProperty("fileTypes", fileTypes);
        dlgFile.putClientProperty("title",
            "dialog." + (load ? "select_" : "save_") + suffix);
        config.updateText(dlgFile);
        dlgFile.setAcceptAllFileFilterUsed(true);

        // Prompt the user to select a file
        if (dlgFile.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION)
            return null;
        return dlgFile.getSelectedFile();
    }

    // Disassociate a window from the environment
    void removeWindow(MainWindow window) {
        windows.remove(window);
        if (windows.size() == 0)
            exit(window);
        else configWindows();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // All windows have closed, perform final cleanup
    private void exit(MainWindow last) {
    }

    // Determine which main window, if any, currently has focus
    private MainWindow getActiveWindow() {
        for (int x = 0; x < windows.size(); x++) {
            MainWindow window = windows.get(x);
            if (window.isFocused())
                return window;
        }
        return null;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Global Commands                            //
    ///////////////////////////////////////////////////////////////////////////

    // Global key handler
    private boolean globalKey(KeyEvent e) {
        int code = e.getKeyCode();

        // Ignore key events that are not presses or are for modifier keys
        if (
            e.getID() != KeyEvent.KEY_PRESSED ||
            code == KeyEvent.VK_ALT           ||
            code == KeyEvent.VK_CONTROL       ||
            code == KeyEvent.VK_SHIFT  
        ) return false;

        // Check whether a global command was selected
        Input         input  = new Input(e);
        config.Action action = config.getAction(input);
        if (action != null && action.invoke())
            return true;

        // Delegate all other inputs except Alt+F4
        return input.equals(Input.ALT_F4);
    }

    // Forward commands to the active window
    private void cmdActive(int command) {
        MainWindow window = getActiveWindow();
        if (window == null)
            return;

        // Carry out the command by index
        switch (command) {
            case DEBUGMODE:       window.onDebugMode   (); break;
            case DISCONNECT:      window.onDisconnect  (); break;
            case EXIT:            window.onExit        (); break;
            case FRAMEADVANCE:    window.onFrameAdvance(); break;
            case LOADROM:         window.onLoadROM     (); break;
            case RESET:           window.onReset       (); break;
            case RUNPAUSE:        window.onRunPause    (); break;
            case SHOWBREAKPOINTS: window.onBreakpoints (); break;
            case SHOWCONSOLE:     window.onConsole     (); break;
            case SHOWCPU:         window.onCPU         (); break;
            case SHOWVIP:         window.onVIP         (); break;
            case STEP:            window.onStep        (); break;
            case STEPOVER:        window.onStepOver    (); break;
        }

    }

}
