package config;

// Java imports
import java.awt.*;
import java.awt.event.*;
import java.nio.charset.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;

// Project imports
import app.*;
import config.*;
import util.*;
import vue.*;

// Global configuration settings manager
public class Config {

    // Instance fields
    private Font[]        fonts;          // Display fonts
    private FontMetrics[] fontMetrics;    // Font attributes
    private int           hexWidth;       // Maximum pixel width of hex digit
    private int           invisibleColor; // Color of clear pixels
    private int           leftColor;      // Color of left pixels
    private int           lineHeight;     // Maximum pixel height of both fonts
    private Localization  locale;         // Localization messages
    private int           rightColor;     // Color of right pixels
    private HashMap<String, Color>      colors;   // Colors cache
    private HashMap<JComponent, Object> uiFont;   // Font-dependent components
    private HashMap<JComponent, Object> uiLocale; // Localized components
    private HashMap<Input     , Action> byInput;  // Actions by Input
    private HashMap<String    , Action> byKey;    // Actions by key

    // Audio fields
    private boolean audioAnalog;  // Simulate analog audio output
    private boolean audioEnabled; // Audio output is enabled
    private int     audioFilter;  // Audio filtering type
    private int     audioRate;    // Audio sampling rate
    private float   audioVolume;  // Audio amplitude



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Font IDs
    public static final int ALL    = -1;
    public static final int DIALOG =  0;
    public static final int FIXED  =  1;

    // Scaled color indexes
    public static final int LEFT  = 1;
    public static final int RED   = 0;
    public static final int RIGHT = 2;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public Config() {

        // Configure instance fields
        byInput        = new HashMap<Input, Action>();
        byKey          = new HashMap<String, Action>();
        colors         = new HashMap<String, Color>();
        fonts          = new Font[2];
        fontMetrics    = new FontMetrics[2];
        hexWidth       = 1;
        invisibleColor = 0xD8000000;
        leftColor      = 0xFFFF0000;
        lineHeight     = 1;
        locale         = new Localization();
        rightColor     = 0xFF00C6F0;
        uiFont         = new HashMap<JComponent, Object>();
        uiLocale       = new HashMap<JComponent, Object>();

        // Configure audio fields
        //audioAnalog  = true;
        //audioEnabled = true;
        audioFilter  = VUE.HARDWARE;
        audioRate    = 41700;
        audioVolume  = 1.0f;

        // Initialize with the default font
        Font font = Util.getDefaultFont();
        setFonts(font, font);

        // Initialize colors
        colors.put("control",      SystemColor.control);
        colors.put("controlText",  SystemColor.controlText);
        colors.put("highlight",    SystemColor.controlHighlight);
        colors.put("selected",     SystemColor.textHighlight);
        colors.put("selectedBlur", Util.mixColors(
            SystemColor.window, SystemColor.textHighlight, 0.4f));
        colors.put("selectedText", SystemColor.textHighlightText);
        colors.put("shadow",       SystemColor.controlShadow);
        colors.put("window",       SystemColor.window);
        colors.put("windowText",   SystemColor.windowText);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Create a new action
    public Action addAction(String key, Object value) {
        Action action = new Action(key, value);
        byKey.put(key, action);
        return action;
    }

    // Create a new action with an input
    public Action addAction(String key, Object value, Input input) {
        Action action = addAction(key, value);
        addInput(key, input);
        return action;
    }

    // Create a new action given an command
    public Action addAction(String key, Action.Command command) {
        return addAction(key, (Object) command);
    }

    // Create a new action given a command and input
    public Action addAction(String key, Action.Command command, Input input) {
        return addAction(key, (Object) command, input);
    }

    // Create a new action given a key mask and input
    public Action addAction(String key, int mask, Input input) {
        return addAction(key, (Object) Integer.valueOf(mask), input);
    }

    // Register a font-dependent component
    public void addFontComponent(JComponent ctl, int font) {
        ctl.putClientProperty("font", font);
        uiFont.remove(ctl);
        uiFont.put(ctl, ctl);
        updateFont(ctl);
    }

    // Associate an input with an action
    public Input addInput(String key, Input input) {

        // Disassociate the input from the previous action
        Action action = byInput.get(input);
        if (action != null)
            action.remove(input);

        // Retrieve the new action
        action = byKey.get(key);
        if (action == null)
            return null;

        // Associate the input with the action
        action.add(input);
        byInput.put(input, action);
        return input;
    }

    // Register a locale-dependent component
    public void addLocaleComponent(JComponent ctl, String key) {
        ctl.putClientProperty("locale", key);
        uiLocale.put(ctl, ctl);
        updateText(ctl);
    }

    // Register a locale-dependent component with multiple keys
    public void addLocaleComponent(JComponent ctl, String[] keys) {
        ctl.putClientProperty("locale", keys);
        uiLocale.put(ctl, ctl);
        updateText(ctl);
    }

    // Register a menu item to receive accelerator settings from an action
    public void addMenuComponent(JMenuItem ctl, String key) {
        Action action = byKey.get(key);
        if (action != null)
            action.add(ctl);
    }

    // Remove all inputs associated with an action
    public void clearAction(String key) {

        // Retrieve the action
        Action action = byKey.get(key);
        if (action == null)
            return;

        // Disassociate all bound inputs
        Input[] inputs = action.list();
        for (int x = 0; x < inputs.length; x++)
            byInput.remove(inputs[x]);
        action.clear();
    }

    // Retrieve the action associated with an input
    public Action getAction(Input input) {
        return byInput.get(input);
    }

    // Retrieve an action by key
    public Action getAction(String key) {
        return byKey.get(key);
    }

    // Retrieve the audio filter type
    public int getAudioFilter() {
        return audioFilter;
    }

    // Retrieve the audio sampling rate
    public int getAudioRate() {
        return audioRate;
    }

    // Retrieve the audio volume
    public float getAudioVolume() {
        return audioVolume;
    }

    // Retrieve a color with alpha from the colors cache
    public Color getColor(String name, float alpha) {

        // Retrieve the cached color
        String key = name + (alpha == 1.0f ? "" : alpha);
        Color  ret = colors.get(key);
        if (ret != null) return ret;

        // Retrieve the base color by name
        ret = colors.get(name);
        if (ret == null) return null;

        // Store the new modified color
        ret = Util.getColor(ret, alpha);
        colors.put(key, ret);
        return ret;
    }

    // Retrieve a color with full opacity from the colors cache
    public Color getColor(String name) {
        return getColor(name, 1.0f);
    }

    // Retrieve a font
    public Font getFont(int font) {
        return fonts[font];
    }

    // Retrieve font attributes
    public FontMetrics getFontMetrics(int font) {
        return fontMetrics[font];
    }

    // Retrieve the maximum width in pixels of a hexadecimal digit
    public int getHexWidth() {
        return hexWidth;
    }

    // Retrieve the invisible color in ARGB format
    public int getInvisibleColor() {
        return invisibleColor;
    }

    // Retrieve the controller key mask for a given input event
    public int getKeyMask(Input input) {
        Action action = byInput.get(input);
        return action == null ? 0 : action.getKeyMask();
    }

    // Retrieve the left color in ARGB format
    public int getLeftColor() {
        return leftColor;
    }

    // Retrieve the height of one line of text
    public int getLineHeight() {
        return getLineHeight(ALL);
    }

    // Retrieve the height of one line of text for a given font
    public int getLineHeight(int font) {
        return font == ALL ? lineHeight : fontMetrics[font].getHeight();
    }

    // Retrieve a localization message by key
    public String getMessage(String key) {
        return locale.get(key);
    }

    // Retrieve the right color in ARGB format
    public int getRightColor() {
        return rightColor;
    }

    // Determine whether audio output is enabled
    public boolean isAudioEnabled() {
        return audioEnabled;
    }

    // Determine whether analog audio output is simulated
    public boolean isAudioAnalog() {
        return audioAnalog;
    }

    // Produce scaled colors for a set of brightness levels
    public int[][] scaleColors(int[] brightness) {
        int[] brt;

        // Determine the final brightness intensities, scaled to 511
        if (brightness != null) {
            brt = new int[] { 0, brightness[1], brightness[2],
                brightness[1] + brightness[2] + brightness[3] };
            for (int x = 1; x < 4; x++)
                brt[x] = (Math.min(brt[x], 127) * 1022 + 1) / 254;
        }

        // Use generic default brightness levels
        else brt = new int[] { 0, 170, 340, 511 };

        // Parse the left and right stereo colors to RGB
        int[] left  = { leftColor  >> 16 & 0xFF,
            leftColor  >> 8 & 0xFF, leftColor  & 0xFF };
        int[] right = { rightColor >> 16 & 0xFF,
            rightColor >> 8 & 0xFF, rightColor & 0xFF };

        // Process all brightness levels
        int[][] ret = new int[3][4];
        for (int x = 0; x < 4; x++) {

            // Initialize color with full opacity
            for (int y = 0; y < ret.length; y++)
                ret[y][x] = 0xFF000000;

            // Special handling for brightness level 0
            if (x == 0)
                continue;

            // Process red level
            ret[RED][x] |= (brt[x] * 510 + 1) / 1022 << 16;

            // Process stereo color channels
            for (int y = 0, z = 16; y < 3; y++, z -= 8) {
                ret[LEFT ][x] |= (left [y] * brt[x] + 1) / 510 << z;
                ret[RIGHT][x] |= (right[y] * brt[x] + 1) / 510 << z;
            }

        } // x

        return ret;
    }

    // Update fonts, updating all dependent components
    public void setFonts(Font dialog, Font fixed) {
        fonts      [DIALOG] = dialog;
        fonts      [FIXED ] = fixed;
        fontMetrics[DIALOG] = Util.getFontMetrics(dialog);
        fontMetrics[FIXED ] = Util.getFontMetrics(fixed);

        // Determine the maximum height in pixels of both fonts
        lineHeight = Math.max(
            Math.max(1, fontMetrics[DIALOG].getHeight()),
            Math.max(1, fontMetrics[FIXED ].getHeight())
        );

        // Determine the maximum width in pixels of a hex digit
        String digitstr = "0123456789ABCDEFabcdef";
        hexWidth = 0;
        for (int x = 0; x < digitstr.length(); x++)
            hexWidth = Math.max(hexWidth,
                fontMetrics[FIXED].stringWidth(digitstr.substring(x, x + 1)));

        // Process all font-dependent components
        JComponent[] ctls = uiFont.keySet().toArray(
            new JComponent[uiFont.size()]);
        for (int x = 0; x < ctls.length; x++)
            updateFont(ctls[x]);
    }

    // Specify whether to simulate analog audio output
    public void setAudioAnalog(boolean analog) {
        audioAnalog = analog;
    }

    // Specify whether audio output is enabled
    public void setAudioEnabled(boolean enabled) {
        audioEnabled = enabled;
    }

    // Specify the audio filtering type
    public boolean setAudioFilter(int filter) {
        if (filter < VUE.HARDWARE || filter > VUE.LINEAR)
            return false;
        audioFilter = filter;
        return true;
    }

    // Specify the audio sampling rate
    public int setAudioRate(int rate) {
        return audioRate = Math.max(1000, Math.min(100000, rate));
    }

    // Specify the audio volume
    public void setAudioVolume(float volume) {
        audioVolume = volume;
    }

    // Specify a new invisible color in ARGB format
    public void setInvisibleColor(int color) {
        invisibleColor = color;
    }

    // Specify a new left color in ARGB format
    public void setLeftColor(int color) {
        leftColor = 0xFF000000 | color;
    }

    // Update localization messges, updating all dependent components
    public void setLocale(Localization locale) {
        this.locale = locale;

        // Process all locale-dependent components
        JComponent[] ctls = uiLocale.keySet().toArray(
            new JComponent[uiLocale.size()]);
        for (int x = 0; x < ctls.length; x++)
            updateText(ctls[x]);
    }

    // Update localization messages by filename
    public void setLocale(String filename) {
        setLocale(new Localization(Util.textFile(filename)));
    }

    // Specify a new right color in ARGB format
    public void setRightColor(int color) {
        rightColor = 0xFF000000 | color;
    }

    // Configure a component's text
    public void updateText(JComponent ctl) {
        String   text  = locale.get(ctl);
        String[] texts = locale.getList(ctl);

        // Call the appropriate method based on component type
        if (ctl instanceof AbstractButton)
            ((AbstractButton) ctl).setText(text);
        else if (ctl instanceof JInternalFrame)
            ((JInternalFrame) ctl).setTitle(text);
        else if (ctl instanceof JLabel)
            ((JLabel) ctl).setText(text);
        else if (ctl instanceof JPanel)
            ((TitledBorder) ctl.getBorder()).setTitle(text);
        else if (ctl instanceof ComboBox)
            ((ComboBox) ctl).setItems(texts);
        else if (ctl instanceof MainWindow)
            ((MainWindow) ctl).setTitle(text);

        // Special handling for file choosers
        else if (ctl instanceof JFileChooser) {
            JFileChooser fc = (JFileChooser) ctl;

            FileFilter[] filters = fc.getChoosableFileFilters();
            for (int y = 0; y < filters.length; y++)
                fc.removeChoosableFileFilter(filters[y]);

            String[] ft = (String[]) fc.getClientProperty("fileTypes");
            for (int y = 0; ft != null && y < ft.length; y += 2) {
                FileNameExtensionFilter filter =
                    new FileNameExtensionFilter(
                        locale.get(ft[y]) + " (*."+ft[y+1]+")", ft[y + 1]);
                fc.addChoosableFileFilter(filter);
                if (y == 0)
                    fc.setFileFilter(filter);
            }

            fc.setApproveButtonText(locale.get(ctl, "accept"));
            fc.setDialogTitle      (locale.get(ctl, "title" ));
        }

        // Special handling for tab strips
        else if (ctl instanceof JTabbedPane) {
            JTabbedPane tabs = (JTabbedPane) ctl;
            for (int y = 0; y < texts.length; y++)
                tabs.setTitleAt(y, texts[y]);
        }
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Configure a component's font
    private void updateFont(JComponent ctl) {
        int  index      = (Integer) ctl.getClientProperty("font");
        Font font       = fonts[index];
        int  lineHeight = fontMetrics[index].getHeight();

        // Call the appropriate method based on component type
        if (ctl instanceof JPanel)
            ((TitledBorder) ctl.getBorder()).setTitleFont(font);
        else if (ctl instanceof JScrollPane)
            ((JScrollPane) ctl).getVerticalScrollBar()
                .setUnitIncrement(lineHeight);
        else ctl.setFont(font);

        // Size a fixed component with configured number of digits
        Integer digits = (Integer) ctl.getClientProperty("digits");
        if (digits == null)
            return;

        // Determine the width of the content area
        Boolean forceHex = (Boolean) ctl.getClientProperty("forceHex");
        if (forceHex == null)
            forceHex = false;
        boolean isHex    = forceHex || font == fonts[FIXED];
        Dimension size = ctl.getPreferredSize();
        size.width = 2 + digits * (isHex ? hexWidth : lineHeight);

        // Determine the width of the border
        Border border = ctl.getBorder();
        if (border != null) {
            Insets insets = border.getBorderInsets(ctl);
            size.width += insets.left + insets.right;
        }

        // Update the component
        ctl.setPreferredSize(size);
        ctl.revalidate();
    }

}
