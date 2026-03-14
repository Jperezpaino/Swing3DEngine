package app;

// Java imports
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

// Project imports
import app.*;
import debugger.*;
import config.*;
import util.*;
import vue.*;

// Top-level user interface window
public class MainWindow extends JComponent {

    // Instance fields
    private Debugger   debugger;   // Debugger UI
    private boolean    firstDebug; // Debug Mode has been shown
    private int        mode;       // Game Mode or Debug Mode
    private int        number;     // Window number
    private App        parent;     // Application environment
    private String     romName;    // ROM filename
    private Dimension  sizeDebug;  // Size for Debug Mode
    private Dimension  sizeGame;   // Size for Game Mode
    private VUE        vue;        // Emulation state context

    // Component fields
    private Console           console;       // Console pane
    private JDesktopPane      desktop;       // MDI container
    private JFrame            window;        // Top-level window
    private JCheckBoxMenuItem mnuDebugMode;  // File -> Debug Mode
    private JMenu             mnuLink;       // Emulation -> Link
    private JSeparator        mnuLinkSep;    // Link menu separator
    private JMenuItem         mnuDisconnect; // Link -> Disconnect
    private ArrayList<JComponent> uiGame;    // Elements visible in Game Mode
    private ArrayList<JComponent> uiDebug;   // Elements visible in Debug Mode
    private ArrayList<JMenuItem>  mnuPeers;  // Link peer menu items

    // Window fields
    private JInternalFrame wndBreakpoints; // Breakpoints window
    private JInternalFrame wndConsole;     // Console window
    private JInternalFrame wndCPU;         // CPU window
    private JInternalFrame wndVIP;         // VIP window

    // Breakpoint fields
    private Breakpoint brkFrame;    // Frame processed
    private Breakpoint brkStepOver; // Step Over execute target



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Application modes
    private static final int GAME  = 0;
    private static final int DEBUG = 1;

    // Application break codes
    static final int BRK_FRAME    = -1;
    static final int BRK_STEPOVER = -2;

    // ROM file types
    private static final String[] ROMTYPES_GAME =
        { "dialog.vb_roms", "vb" };
    private static final String[] ROMTYPES_DEBUG =
        { "dialog.vb_roms", "vb", "dialog.isx_roms", "isx" };



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    MainWindow(App parent) {
        super();

        // Configure instance fields
        Config config = parent.getConfig();
        this.parent = parent;
        vue         = VUE.create();
        mnuPeers    = new ArrayList<JMenuItem>();
        mode        = -1;
        sizeDebug   = new Dimension(800, 600);
        uiGame      = new ArrayList<JComponent>();
        uiDebug     = new ArrayList<JComponent>();

        // Configure debugger
        debugger = new Debugger(config, vue) {
            public void onBreakpoint() {
                configBreakpoints();
            }
        };

        // Configure component
        window = new JFrame();
        putClientProperty("{NUMBER}" , "");
        putClientProperty("{ROMNAME}", "");
        putClientProperty("{HYPHEN}" , "");
        config.addLocaleComponent(this, "app.window_title");
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowListener() {
            public void windowActivated  (WindowEvent e) { }
            public void windowClosed     (WindowEvent e) { }
            public void windowClosing    (WindowEvent e) { onExit(); }
            public void windowDeactivated(WindowEvent e) { }
            public void windowDeiconified(WindowEvent e) { }
            public void windowIconified  (WindowEvent e) { }
            public void windowOpened     (WindowEvent e) { }
        });

        // Configure component
        console = new Console(this);
        window.setContentPane(console);

        // Configure windows
        desktop = new JDesktopPane();
        desktop.setBackground(config.getColor("shadow"));
        desktop.setFocusable(true);
        initConsole();
        initCPU();
        initVIP();

        // Configure breakpoints
        initBreakpoints();
        vue.setKeys(VUE.SIGNATURE);

        // Initialize interface
        initMenuBar();
        setMode(GAME);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // Initialize breakpoints and Breakpoints window
    private void initBreakpoints() {
        wndBreakpoints = createWindow("breakpoints");
        wndBreakpoints.setSize(400, 300);
        wndBreakpoints.setContentPane(debugger.getBreakpoints());

        // Frame processed
        brkFrame = new Breakpoint();
        brkFrame.setBreakCode(BRK_FRAME);
        brkFrame.setEnabled(true);
        brkFrame.setEvents(Breakpoint.FRAME);

        // Step over execute target
        brkStepOver = new Breakpoint();
        brkStepOver.setBreakCode(BRK_STEPOVER);
        brkStepOver.setEnabled(false);
        brkStepOver.setEvents(Breakpoint.EXECUTE);

        // Initialize simulation breakpoints
        configBreakpoints();
    }

    // Initialize main menu
    private void initMenuBar() {

        // Configure menu bar
        JMenuBar bar = new JMenuBar();
        bar.setBorder(null);
        window.setJMenuBar(bar);

        // Configure top-level menus
        initFileMenu     (bar);
        initEmulationMenu(bar);
        initSettingsMenu (bar);
        initDebugMenu    (bar);
    }

    // Initialize Debug menu
    private void initDebugMenu(JMenuBar bar) {
        JMenu menu = new JMenu();
        configMenu(menu, false, true, "menu.debug", null, null);
        bar.add(menu);

        menu.add(configMenu(new JMenuItem(), false, true,
            "console", "showConsole", null));
        menu.add(configMenu(new JMenuItem(), false, true,
            "cpu", "showCPU", null));
        menu.add(configMenu(new JMenuItem(), false, true,
            "vip", "showVIP", null));
JMenuItem hax;
        menu.add(configMenu(hax = new JMenuItem(), false, true,
            "vsu", null, null));
hax.setEnabled(false);
        menu.add(configMenu(hax = new JMenuItem(), false, true,
            "components", null, null));
hax.setEnabled(false);
        menu.add(configMenu(new JMenuItem(), false, true,
            "breakpoints", "showBreakpoints", null));
        menu.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.debug.rom_info", null, null));
hax.setEnabled(false);
    }

    // Initialize Emulation menu
    private void initEmulationMenu(JMenuBar bar) {
        JMenu menu = new JMenu();
        configMenu(menu, true, true, "menu.emulation", null, null);
        bar.add(menu);

        menu.add(configMenu(new JMenuItem(), true, true,
            "menu.emulation.run_pause", "runPause", null));
        menu.add(configMenu(new JMenuItem(), true, true,
            "menu.emulation.reset", "reset", null));
        menu.add(configMenu(mnuLink = new JMenu(), true, true,
            "menu.emulation.link", null, null));

        menu.add(configMenu(new JSeparator(), true, true, null, null, null));

        menu.add(configMenu(new JMenuItem(), false, true,
            "menu.emulation.step", "step", null));
        menu.add(configMenu(new JMenuItem(), false, true,
            "menu.emulation.step_over", "stepOver", null));
        menu.add(configMenu(new JMenuItem(), true, true,
            "menu.emulation.frame_advance", "frameAdvance", null));

        // Context-sensitive Link menu elements
        configMenu(mnuLinkSep = new JSeparator(), true, true,
            null, null, null);
        configMenu(mnuDisconnect = new JMenuItem(), true, true,
            "menu.emulation.disconnect", "disconnect", null);
    }

    // Initialize File menu
    private void initFileMenu(JMenuBar bar) {
        JMenu menu = new JMenu();
        configMenu(menu, true, true, "menu.file", null, null);
        bar.add(menu);

        menu.add(configMenu(new JMenuItem(), true, false,
            "menu.file.load_rom", "loadROM", null));

        JMenu sub;
        menu.add(configMenu(sub = new JMenu(), false, true,
            "menu.file.rom", null, null));
JMenuItem hax;
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.new", null, null));
hax.setEnabled(false);
        sub.add(configMenu(new JMenuItem(), false, true,
            "menu.file.load", "loadROM", null));
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.save", null, null));
hax.setEnabled(false);
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.save_as", null, null));
hax.setEnabled(false);
        sub.add(configMenu(new JSeparator(), false, true, null, null, null));
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.size", null, null));
hax.setEnabled(false);

        menu.add(configMenu(sub = new JMenu(), false, true,
            "menu.file.sram", null, null));
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.new", null, null));
hax.setEnabled(false);
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.load", null, null));
hax.setEnabled(false);
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.save", null, null));
hax.setEnabled(false);
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.save_as", null, null));
hax.setEnabled(false);
        sub.add(configMenu(new JSeparator(), false, true, null, null, null));
        sub.add(configMenu(hax = new JMenuItem(), false, true,
            "menu.file.size", null, null));
hax.setEnabled(false);

        menu.add(configMenu(sub = new JMenu(), true, true,
            "menu.file.state", null, null));
        sub.add(configMenu(hax = new JMenuItem(), true, true,
            "menu.file.load", null, null));
hax.setEnabled(false);
        sub.add(configMenu(hax = new JMenuItem(), true, true,
            "menu.file.save_as", null, null));
hax.setEnabled(false);
        sub.add(configMenu(new JSeparator(), true, true, null, null, null));
        sub.add(configMenu(hax = new JMenuItem(), true, true,
            "menu.file.reload", null, null));
hax.setEnabled(false);
        sub.add(configMenu(hax = new JMenuItem(), true, true,
            "menu.file.save", null, null));
hax.setEnabled(false);

        menu.add(configMenu(new JSeparator(), true, true, null, null, null));

        menu.add(configMenu(new JMenuItem(), true, true,
            "menu.file.new_window", "newWindow", null));
        menu.add(configMenu(mnuDebugMode = new JCheckBoxMenuItem(), true, true,
            "menu.file.debug_mode", "debugMode", null));

        menu.add(configMenu(new JSeparator(), true, true, null, null, null));

        menu.add(configMenu(new JMenuItem(), true, true,
            "menu.file.exit", "exit", null));
    }

    // Initialize Settings menu
    private void initSettingsMenu(JMenuBar bar) {
        JMenu menu = new JMenu();
        configMenu(menu, true, true, "menu.settings", null, null);
        bar.add(menu);

JMenuItem hax;
        menu.add(configMenu(hax = new JMenuItem(), true, true,
            "menu.settings.input", null, null));
hax.setEnabled(false);
        menu.add(configMenu(hax = new JMenuItem(), true, true,
            "menu.settings.video", null, null));
hax.setEnabled(false);
        menu.add(configMenu(hax = new JMenuItem(), true, true,
            "menu.settings.audio", null, null));
hax.setEnabled(false);

        menu.add(configMenu(new JSeparator(), true, true, null, null, null));

        menu.add(configMenu(hax = new JMenuItem(), true, true,
            "menu.settings.settings", null, null));
hax.setEnabled(false);
    }

    // Configure Console window
    private void initConsole() {
        wndConsole = createWindow("console");
        wndConsole.getContentPane().setPreferredSize(new Dimension(384, 224));
        wndConsole.pack();
    }

    // Configure CPU window
    private void initCPU() {
        wndCPU = createWindow("cpu");
        wndCPU.setSize(640, 480);

        JSplitPane left = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            debugger.getDisassembler(), debugger.getHexEditor());
        Util.adjustSplitPane(left);
        left.setResizeWeight(1);

        JSplitPane right = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            debugger.getSystemRegisters(), debugger.getProgramRegisters());
        Util.adjustSplitPane(right);
        right.setResizeWeight(0);

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
            left, right);
        Util.adjustSplitPane(split);
        split.setResizeWeight(1);

        wndCPU.setContentPane(split);
    }

    // Configure VIP window
    private void initVIP() {
        wndVIP = createWindow("vip");
        wndVIP.setSize(640, 480);

        JTabbedPane tabs = new JTabbedPane();
        tabs.add(debugger.getCharacters());
        tabs.add(debugger.getBGMaps());
        tabs.add(debugger.getObjects());
        tabs.add(debugger.getWorlds());
        tabs.add(debugger.getFrameBuffers());
        tabs.add(debugger.getVIPRegisters());
        tabs.setFocusable(false);
        tabs.setOpaque(true);
        parent.getConfig().addLocaleComponent(tabs, new String[] {
            "vip.characters",
            "vip.bg_maps",
            "vip.objects",
            "vip.worlds",
            "vip.frame_buffers",
            "vip.registers"
        });

        wndVIP.setContentPane(tabs);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Specify a new window title
    public void setTitle(String text) {
        window.setTitle(text);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Retrieve the global settings object
    Config getConfig() {
        return parent.getConfig();
    }

    // Retrieve the current window number
    int getNumber() {
        return number;
    }

    // Retrieve the emulation state context
    VUE getVUE() {
        return vue;
    }

    // Determine whether the window is in debug mode
    boolean isDebugging() {
        return mode == DEBUG;
    }

    // Determine whether the window has focus
    boolean isFocused() {
        return window.getFocusOwner() != null;
    }

    // Specify a new list of potential link peers
    void setLinkPeers(ArrayList<MainWindow> peers) {

        // Clear the link menu
        mnuLink.removeAll();

        // Add potential link peers to the menu
        boolean enabled = false;
        for (int x = 0; x < peers.size(); x++) {
            MainWindow window = peers.get(x);

            // The window is not a potential link peer
            if (window == this)
                continue;

            // Add the link peer to the menu
            Console           peer = window.console.getPeer();
            JCheckBoxMenuItem item = new JCheckBoxMenuItem();
            window.mnuPeers.add(item);
            item.setEnabled(peer == null || peer == console);
            item.setState(peer == console);
            mnuLink.add(configMenu(item, true, true,
                "app.link_peer", null, e->{
                    onLink(window);
                    item.setState(true);
                }
            ));
            enabled = true;
        }

        // Add the Disconnect option if linked
        if (console.getPeer() != null) {
            if (enabled)
                mnuLink.add(mnuLinkSep);
            enabled = true;
            mnuLink.add(mnuDisconnect);
        }

        // Specify whether the Link menu is active
        mnuLink.setEnabled(enabled);
    }

    // Specify a new window number
    void setNumber(int number) {
        this.number = number;
        mnuPeers.clear();
    }

    // Redraw any element depending on anaglyph colors
    void updateColors() {

    }

    // Update the window title according to configuration
    void updateTitle() {
        Config config  = parent.getConfig();
        String number  = this.number  ==    0 ? "" : this.number  + " ";
        String romName = this.romName == null ? "" : this.romName + " ";

        // Update window title
        putClientProperty("{NUMBER}" , number);
        putClientProperty("{ROMNAME}", romName);
        putClientProperty("{HYPHEN}" ,
            this.number == 0 && this.romName == null ? "" : "- ");
        config.updateText(this);

        // Update link peer menu items
        for (int x = 0; x < mnuPeers.size(); x++) {
            JMenuItem item = mnuPeers.get(x);
            item.putClientProperty("{NUMBER}" , number);
            item.putClientProperty("{ROMNAME}", romName);
            config.updateText(item);
        }

    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // Emulation breakpoint
    // Returns true if the timer needs to automatically stop
    boolean onBreak(int breakCode) {

        // Check for application breaks
        switch (breakCode) {
            case 0:            return false;
            case BRK_FRAME:    return false;
            case BRK_STEPOVER: return true;
        }

        // A user breakpoint was triggered
        return true;
    }

    // Debug -> Breakpoints
    void onBreakpoints() {
        showWindow(wndBreakpoints);
    }

    // Debug -> Console
    void onConsole() {
        showWindow(wndConsole);
    }

    // Frame processing has been stopped
    void onConsolePause() {
        int breakCode;
        synchronized (vue)
            { breakCode = vue.getBreakCode(); }

        refresh(false, true);
        if (breakCode <= 0)
            return;

        debugger.selectBreakpoint(breakCode - 1);
        if (!wndCPU.isVisible())
            showWindow(wndCPU);
        showWindow(wndBreakpoints);
    }

    // Frame processing has begun
    void onConsoleRun() {

    }

    // Debug -> CPU
    void onCPU() {
        showWindow(wndCPU);
    }

    // File -> Debug Mode
    void onDebugMode() {
        setMode(mode == GAME ? DEBUG : GAME);
        mnuDebugMode.setState(mode == DEBUG);
    }

    // Emulation -> Link -> Disconnect
    void onDisconnect() {
        console.setPeer(null);
        parent.configWindows();
    }

    // File -> Exit, window close
    void onExit() {
        console.dispose();
        parent.removeWindow(this);
        window.dispose();
    }

    // Emulation -> Frame Advance
    void onFrameAdvance() {
        console.pause();
        console.process(400000, true, false);
        refresh(true, true);
    }

    // Console key
    private void onKey(KeyEvent e) {

        // Check the controller key mask
        int mask = parent.getConfig().getKeyMask(new Input(e));
        if (mask == 0)
            return;

        // Update the simulation's keys
        synchronized (vue) {
            int keys = vue.getKeys();
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                keys |= mask;
                switch (mask) {
                    case VUE.LDOWN : keys &= ~VUE.LUP   ; break;
                    case VUE.LLEFT : keys &= ~VUE.LRIGHT; break;
                    case VUE.LRIGHT: keys &= ~VUE.LLEFT ; break;
                    case VUE.LUP   : keys &= ~VUE.LDOWN ; break;
                    case VUE.RDOWN : keys &= ~VUE.RUP   ; break;
                    case VUE.RLEFT : keys &= ~VUE.RRIGHT; break;
                    case VUE.RRIGHT: keys &= ~VUE.RLEFT ; break;
                    case VUE.RUP   : keys &= ~VUE.RDOWN ; break;
                }
            } else keys &= ~mask;
            vue.setKeys(keys);
        }

    }

    // Emulation -> Link -> (window)
    private void onLink(MainWindow peer) {

        // Cannot link with the same peer
        if (peer == console.getPeerWindow())
            return;

        // Associate communication peers
        boolean restart = console.isRunning() || peer.console.isRunning();
        console.pause(false);
        peer.console.pause(false);
        console.setPeer(peer.console);
        if (restart)
            peer.run();

        // Configure windows
        parent.configWindows();
    }

    // File -> (Load ROM)(ROM -> Load)
    void onLoadROM() {
        Config config = parent.getConfig();

        // Prompt the user to select a file
        File file = parent.promptFile(window, true, "rom",
            mode == GAME ? ROMTYPES_GAME : ROMTYPES_DEBUG);
        if (file == null)
            return;

        // Load the file into memory
        byte[] rom = Util.fileLoad(file);
        if (rom == null) {
            JOptionPane.showMessageDialog(window,
                config.getMessage("error.file_read"),
                config.getMessage("dialog.load_rom"),
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Process as an ISX file
        ISX isx = ISX.decode(rom);
        if (isx != null)
            rom = isx.getROM();

        // Disable frame processing
        boolean restart = console.isRunning();
        console.pause();

        // Set the file data as the emulation context's ROM
        boolean loaded;
        synchronized (vue)
            { loaded = vue.setROM(rom, 0, rom.length); }

        // ROM loaded successfully, initialization tasks
        if (loaded) {
            romName = file.getName();
            updateTitle();
            console.reset();
        }

        // ROM loading failed
        else JOptionPane.showMessageDialog(window,
            config.getMessage("error.not_rom"),
            config.getMessage("dialog.load_rom"),
            JOptionPane.ERROR_MESSAGE
        );

        // Restart frame processing
        if (mode == DEBUG)
            debugger.refresh(true);
        else restart = true;
        if (restart)
            console.run();
    }

    // Emulation -> Reset
    void onReset() {
        console.reset();
        refresh(true, true);
    }

    // Emulation -> Run/Pause
    void onRunPause() {
        if (console.isRunning())
            pause();
        else run();
    }

    // Emulation -> Step
    void onStep() {
        if (mode != DEBUG)
            return;
        console.pause();
        console.process(1, true, false);
        if (!wndCPU.isVisible())
            showWindow(wndCPU);
        refresh(true, true);
    }

    // Emulation -> Step Over
    void onStepOver() {
        if (mode != DEBUG)
            return;

        // Stop frame processing
        console.pause();

        // Configure Step Over breakpoint
        synchronized (vue) {
            int pc     = vue.getProgramCounter();
            int target = pc + Instruction.getSize(vue.read(pc, VUE.U16, true));
            brkStepOver.setAddress(new int[][] { { target } });
        }
        brkStepOver.setEnabled(true);
        configBreakpoints();

        // Process the simulation
        console.process(100000000, true, true);
        if (!wndCPU.isVisible())
            showWindow(wndCPU);
        refresh(true, true);

        // De-configure Step Over breakpoint
        brkStepOver.setEnabled(false);
        configBreakpoints();
    }

    // Debug -> VIP
    void onVIP() {
        showWindow(wndVIP);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Configure breakpoints for the next emulation
    private void configBreakpoints() {
        Breakpoint[] user = debugger.listBreakpoints();
        Breakpoint[] brks = new Breakpoint[2 + user.length];

        // Configure application breakpoints
        brks[0] = brkFrame;
        brks[1] = brkStepOver;

        // Configure user breakpoints
        for (int x = 0; x < user.length; x++)
            brks[2 + x] = user[x];

        // Update emulation breakpoints
        synchronized (vue)
            { vue.setBreakpoints(brks); }

    }

    // Configure a new menu item
    private JComponent configMenu(JComponent item, boolean game, boolean debug,
        String localeKey, String commandKey, ActionListener click) {

        // Configure localization and event properties
        Config config = parent.getConfig();
        config.addFontComponent(item, Config.DIALOG);
        if (localeKey != null)
            config.addLocaleComponent(item, localeKey);
        if (commandKey != null) {
            config.addMenuComponent((JMenuItem) item, commandKey);
            if (click == null) {
                config.Action action = config.getAction(commandKey);
                ((AbstractButton) item).addActionListener(e->action.invoke());
            }
        }
        if (click != null)
            ((AbstractButton) item).addActionListener(click);

        // Configure application mode properties
        item.putClientProperty(GAME , game);
        item.putClientProperty(DEBUG, debug);
        if (game ) uiGame.add(item);
        if (debug) uiDebug.add(item);

        return item;
    }

    // Create a new MDI window
    private JInternalFrame createWindow(String key) {
        JInternalFrame wnd = new JInternalFrame();
        wnd.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        wnd.setOpaque(true);
        wnd.setClosable(true);
        wnd.setIconifiable(true);
        wnd.setMaximizable(true);
        wnd.setResizable(true);
        wnd.setLocation(0, 0);
        parent.getConfig().addLocaleComponent(wnd, key);
        desktop.add(wnd);
        return wnd;
    }

    // Pause emulation
    private void pause() {
        console.pause();
    }

    // Update UI
    private void refresh(boolean doConsole, boolean doDebugger) {
        MainWindow peer = console.getPeerWindow();

        // Refresh self
        if (doConsole)
            console.refresh();
        if (doDebugger && mode == DEBUG)
            debugger.refresh(true);

        // Refresh peer
        if (peer != null) {
            if (doConsole)
                peer.console.refresh();
            if (doDebugger && peer.mode == DEBUG)
                peer.debugger.refresh(true);
        }

    }

    // Resume emulation
    private void run() {
        console.run();
        if (mode == DEBUG)
            showWindow(wndConsole);
    }

    // Specify the current application mode
    private void setMode(int mode) {

        // Mode is not changing
        if (mode == this.mode)
            return;

        // Update instance fields
        this.mode = mode;

        // Configure menu visibility
        for (int x = 0; x < 2; x++) {
            ArrayList<JComponent> list = x == 0 ? uiGame : uiDebug;
            for (int y = 0; y < list.size(); y++) {
                JComponent ctl = list.get(y);
                Object val = ctl.getClientProperty(mode);
                ctl.setVisible(mode == -1 || val == null || (Boolean) val);
            }
        }

        // Configure Game Mode
        if (mode == GAME) {
            if (sizeGame == null)
                sizeGame = window.getSize();
            else sizeDebug = window.getSize();
            window.setSize(sizeGame);
            window.setContentPane(console);
        }

        // Configure Debug Mode
        else {
            sizeGame = window.getSize();
            window.setSize(sizeDebug);
            window.setContentPane(desktop);
            wndConsole.setContentPane(console);
            if (!firstDebug) {
                firstDebug = true;
                showWindow(wndConsole);
            }
            debugger.refresh(true);
        }

    }

    // Ensure a given window is visible and selected
    private void showWindow(JInternalFrame window) {
        int     height = desktop.getHeight();
        int     width  = desktop.getWidth();
        int     left   = window.getX();
        int     top    = window.getY();
        int     wide   = window.getWidth();
        int     tall   = window.getHeight();
        boolean first  = window.getClientProperty("shown") == null;

        // Position the window
        if (first || left + wide < 32 || left > width - 32)
            left = Math.max(0, width  - wide >> 1);
        if (first || top + tall < 32 || top > height - 32)
            top  = Math.max(0, height - tall >> 1);
        window.setLocation(left, top);

        // Show the window
        if (mode != DEBUG)
            return;
        window.putClientProperty("shown", true);
        window.setVisible(true);
        window.moveToFront();
        if (isFocused()) try {
            window.setSelected(true);
        } catch (Exception e) { }
        desktop.repaint();

        // First time displaying CPU window
        if (first && window == wndCPU)
            debugger.refresh(true);
    }

}
