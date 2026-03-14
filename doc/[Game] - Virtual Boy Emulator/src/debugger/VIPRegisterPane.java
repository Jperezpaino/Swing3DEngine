package debugger;

// Java imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

// Package imports
import config.*;
import debugger.*;
import util.*;
import vue.*;

// Interface for VIP registers
class VIPRegisterPane extends JScrollPane {

    // Instance fields
    private Config   config;   // Application configuration settings
    private boolean  header;   // Next control is in header column
    private Debugger parent;   // Parent debugger context
    private JPanel   ribbon;   // Controls container
    private boolean  updating; // Set when updating UI components
    private VUE      vue;      // Associated emulation state

    // Colors components
    private JLabel[]     lblBRT;      // Brightness labels
    private JSpinner     spnBKCOL;    // BKCOL spinner
    private JSpinner[][] spnPalettes; // Palette spinners
    private JTextField[] txtBRT;      // Brightness text boxes
    private JTextField[] txtPalettes; // Palette text boxes
    private JTextField   txtREST;     // REST text box

    // Display components
    private JButton     btnDPRST;   // DPRST button
    private JCheckBox[] chkDisplay; // Display flag check boxes
    private JTextField  txtDPSTTS;  // DPSTTS text box

    // Drawing components
    private JButton      btnXPRST;   // XPRST button
    private JCheckBox[]  chkDrawing; // Drawing flag check boxes
    private JComponent[] cmpDrawing; // Drawing components

    // Interrupt components
    private JCheckBox[][] chkIRQ;    // Interrupt flag check boxes
    private JTextField    txtINTENB; // INTENB text box
    private JTextField    txtINTPND; // INTPND text box

    // Misc components
    private JSpinner   spnBuffer; // Buffer spinner
    private JSpinner   spnFrame;  // Frame spinner
    private JSpinner   spnFRMCYC; // FRMCYC spinner
    private JTextField txtCTA;    // CTA text box
    private JTextField txtCTA_R;  // CTA_R text box
    private JTextField txtCTA_L;  // CTA_L text box

    // Objects components
    private JLabel[]   lblStart; // Start labels
    private JSpinner[] spnEnd;   // End spinners



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Display flag properties
    private static final Object[][] DPFLAGS = {
        // Name, mask, enabled
        { "LOCK"   , 0x0400, true  },
        { "R1BSY"  , 0x0020, false },
        { "SYNCE"  , 0x0200, true  },
        { "L1BSY"  , 0x0010, false },
        { "RE"     , 0x0100, true  },
        { "R0BSY"  , 0x0008, false },
        { "FCLK"   , 0x0080, false },
        { "L0BSY"  , 0x0004, false },
        { "SCANRDY", 0x0040, false },
        { "DISP"   , 0x0002, true  }
    };

    // Interrupt flag properties
    private static final Object[][] IRQFLAGS = {
        // Name, mask
        { "TIMEERR"   , 0x8000 },
        { "XPEND"     , 0x4000 },
        { "SBHIT"     , 0x2000 },
        { "FRAMESTART", 0x0010 },
        { "GAMESTART" , 0x0008 },
        { "RFBEND"    , 0x0004 },
        { "LFBEND"    , 0x0002 },
        { "SCANERR"   , 0x0001 }
    };

    // Object pointer register names
    private static final String[] OBJECTS =
        { "SPT0", "SPT1", "SPT2", "SPT3" };

    // Palette register names
    private static final String[] PALETTES = {
        "GPLT0", "GPLT1", "GPLT2", "GPLT3",
        "JPLT0", "JPLT1", "JPLT2", "JPLT3"
    };

    // Drawing text field properties
    private static final Object[][] XPFIELDS = {
        // Name, digits, register, position, size
        { "XPCTRL" , 4, 0, 0, 16 },
        { "XPSTTS" , 4, 1, 0, 16 },
        { "SBCMP"  , 2, 0, 8,  5 },
        { "SBCOUNT", 2, 1, 8,  5 }
    };

    // Drawing flag properties
    private static final Object[][] XPFLAGS = {
        // Name, mask, enabled
        { "SBOUT"   , 0x8000, false },
        { "F1BSY"   , 0x0008, false },
        { "F0BSY"   , 0x0004, false },
        { "OVERTIME", 0x0010, false },
        { "XPEN"    , 0x0002, true  }
    };



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    VIPRegisterPane(Debugger parent) {
        super();

        // Configure instance fields
        this.parent = parent;
        config      = parent.getConfig();
        header      = true;
        updating    = false;
        vue         = parent.getVUE();

        // Configure interface
        ribbon = new JPanel(new GridBagLayout());
        ribbon.setFocusable(true);
        Util.onMouse(ribbon, e->ribbon.requestFocus(), null);
        initInterrupt();
        initDisplay();
        initColors();
        initObjects();
        initDrawing();
        initMisc();

        // Manage layout
        JPanel client = new JPanel();
        ((FlowLayout) client.getLayout()).setAlignment(FlowLayout.LEFT);
        Util.onMouse(client, e->ribbon.requestFocus(), null);
        client.add(ribbon);

        // Configure component
        setBorder(null);
        setViewportView(client);
    }

    // Set up the Colors interface components
    private void initColors() {
        ActionListener onAction  = e->ribbon.requestFocus();
        JPanel         grpColors = createGroup("vip.colors", 2, 0, true);

        // Header row
        createLabel(grpColors, null, 2, true);
        createLabel(grpColors, "!1", 1, true);
        createLabel(grpColors, "!2", 1, true);
        createLabel(grpColors, "!3", -1, true);

        // Brightness text boxes
        Util.FocusHandler onBlur = e->onBRT((JTextField) e.getSource());
        createLabel(grpColors, "!BRT", 2, false);
        txtBRT = new JTextField[3];
        for (int x = 0; x < txtBRT.length; x++) {
            JTextField ctl = txtBRT[x] =
                createTextBox(grpColors, 2, x == 2 ? -1 : 1);
            ctl.putClientProperty("index", x);
            ctl.addActionListener(onAction);
            Util.onFocus(ctl, null, onBlur);
        }

        // Brightness labels
        createLabel(grpColors, null, 1, false);
        lblBRT = new JLabel[4];
        for (int x = 0; x < lblBRT.length; x++) {
            JLabel ctl = lblBRT[x] =
                createLabel(grpColors, "! ", x == 3 ? -1 : 1, false);
            ctl.setOpaque(true);
        }

        // Palette text boxes and spinners
        onBlur                  = e->onPalette((JComponent) e.getSource());
        ChangeListener onChange = e->onPalette((JComponent) e.getSource());
        spnPalettes = new JSpinner[PALETTES.length][3];
        txtPalettes = new JTextField[PALETTES.length];
        for (int y = 0; y < PALETTES.length; y++) {
            createLabel(grpColors, "!" + PALETTES[y], 1, false);

            // Register text box
            JTextField txt = txtPalettes[y] =
                createTextBox(grpColors, 2, 1);
            txt.putClientProperty("palette", y);
            txt.addActionListener(onAction);
            Util.onFocus(txt, null, onBlur);

            // Color value spinners
            JSpinner[] ctls = spnPalettes[y];
            for (int x = 1; x < 4; x++) {
                JSpinner ctl = ctls[x - 1] =
                    createSpinner(grpColors, 0, 3, x == 3 ? -1 : 1);
                ctl.putClientProperty("palette", y);
                ctl.putClientProperty("index", x);
                ctl.addChangeListener(onChange);
            }
        }

        // BKCOL spinner
        createLabel(grpColors, "!BKCOL", 1, false);
        spnBKCOL = createSpinner(grpColors, 0, 3, 1);
        spnBKCOL.addChangeListener(e->onBKCOL());

        // REST text box
        JLabel label = createLabel(grpColors, "!REST", 2, false);
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        txtREST = createTextBox(grpColors, 2, -1);
        txtREST.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtREST, null, e->onREST());

        // Finalize the layout
        finish(grpColors);
    }

    // Set up the Display interface components
    private void initDisplay() {
        JPanel grpDisplay = createGroup("vip.display", 1, 0, false);

        // DPSTTS text box
        createLabel(grpDisplay, "!DPSTTS", 1, false);
        txtDPSTTS = createTextBox(grpDisplay, 4, -1);
        txtDPSTTS.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtDPSTTS, null, e->onDPSTTS());

        // Flags
        ActionListener onAction = e->onDisplayFlag((JCheckBox) e.getSource());
        chkDisplay = new JCheckBox[DPFLAGS.length];
        for (int x = 0; x < chkDisplay.length; x++) {
            Object[] flag = DPFLAGS[x];
            JCheckBox chk = chkDisplay[x] =
                createCheckBox(grpDisplay, "!" + flag[0], 1 - (x & 1));
            chk.setEnabled((Boolean) flag[2]);
            chk.putClientProperty("mask", flag[1]);
            chk.addActionListener(onAction);
        }

        // DPRST button
        createLabel(grpDisplay, null, 1, true);
        btnDPRST = createButton(grpDisplay, "!DPRST", -1);
        btnDPRST.addActionListener(e->onDPRST());

        // Finalize the layout
        finish(grpDisplay);
    }

    // Set up the Drawing interface components
    private void initDrawing() {
        JPanel grpDrawing = createGroup("vip.drawing", 1, 1, false);

        // Text fields and spinners
        ActionListener    onAction = e->ribbon.requestFocus();
        Util.FocusHandler onBlur   = e->onDrawing((JComponent) e.getSource());
        ChangeListener    onChange = e->onDrawing((JComponent) e.getSource());
        cmpDrawing = new JComponent[XPFIELDS.length];
        for (int x = 0; x < cmpDrawing.length; x++) {
            Object[] field = XPFIELDS[x];
            createLabel(grpDrawing, "!" + field[0], 1, false);
            JComponent ctl = null;

            // Configuration for text fields
            if (x < 2) {
                JTextField txt = (JTextField) (ctl = cmpDrawing[x] =
                    createTextBox(grpDrawing, (Integer) field[1], -1));
                txt.addActionListener(onAction);
                Util.onFocus(txt, null, onBlur);
            }

            // Configuration for spinners
            else {
                JSpinner spn = (JSpinner) (ctl = cmpDrawing[x] =
                    createSpinner(grpDrawing, 0, 31, -1));
                spn.addChangeListener(onChange);
            }

            // Common configuration
            ctl.putClientProperty("field", field);
        }

        // Flags
        onAction = e->onXPFlag((JCheckBox) e.getSource());
        chkDrawing = new JCheckBox[XPFLAGS.length];
        for (int x = 0; x < chkDrawing.length; x++) {
            Object[] flag = XPFLAGS[x];
            JCheckBox ctl = chkDrawing[x] =
                createCheckBox(grpDrawing, "!" + flag[0], 1 - (x & 1));
            ctl.setEnabled((Boolean) flag[2]);
            ctl.putClientProperty("mask", flag[1]);
            ctl.addActionListener(onAction);
        }

        // XPRST button
        btnXPRST = createButton(grpDrawing, "!XPRST", -1);
        btnXPRST.addActionListener(e->onXPRST());

        // Finalize the layout
        finish(grpDrawing);
    }

    // Set up the Interrupt interface components
    private void initInterrupt() {
        JPanel grpInterrupt = createGroup("vip.interrupt", 0, 0, true);

        // INTENB text box
        createLabel(grpInterrupt, "!INTENB", 1, false);
        txtINTENB = createTextBox(grpInterrupt, 4, -1);
        txtINTENB.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtINTENB, null, e->onIRQ(0));

        // INTPND text box
        createLabel(grpInterrupt, "!INTPND", 1, false);
        txtINTPND = createTextBox(grpInterrupt, 4, -1);
        txtINTPND.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtINTPND, null, e->onIRQ(1));

        // Header row for flags
        createLabel(grpInterrupt, "! ", -1, false);
        createLabel(grpInterrupt, null, 1, false);
        createLabel(grpInterrupt, "!ENB", 1, true);
        createLabel(grpInterrupt, "!PND", -1, true);

        // Flags
        ActionListener onAction = e->onIRQFlag((JCheckBox) e.getSource());
        chkIRQ = new JCheckBox[IRQFLAGS.length][2];
        for (int y = 0; y < chkIRQ.length; y++) {

            // Set up header label
            Object[] flag = IRQFLAGS[y];
            createLabel(grpInterrupt, "!" + flag[0], 1, false);

            // Set up both check boxes
            for (int x = 0; x < 2; x++) {
                JCheckBox chk = chkIRQ[y][x] =
                    createCheckBox(grpInterrupt, null, 1 - x);
                chk.putClientProperty("reg", x);
                chk.putClientProperty("mask", flag[1]);
                chk.addActionListener(onAction);
            }

        }

        // Finalize the layout
        finish(grpInterrupt);
    }

    // Set up the Misc. interface components
    private void initMisc() {
        JPanel         grpMisc  = createGroup("common.misc", 3, 1, false);
        ActionListener onAction = e->ribbon.requestFocus();

        // FRMCYC spinner
        createLabel(grpMisc, "!FRMCYC", 1, false);
        spnFRMCYC = createSpinner(grpMisc, 0, 15, -1);
        spnFRMCYC.addChangeListener(e->onFRMCYC());

        // CTA text box
        Util.FocusHandler onBlur = e->refresh();
        createLabel(grpMisc, "!CTA", 1, false);
        txtCTA = createTextBox(grpMisc, 4, -1);
        txtCTA.addActionListener(onAction);
        Util.onFocus(txtCTA, null, onBlur);

        // CTA_L text box
        createLabel(grpMisc, "!CTA_L", 1, false);
        txtCTA_L = createTextBox(grpMisc, 4, -1);
        txtCTA_L.addActionListener(onAction);
        Util.onFocus(txtCTA_L, null, onBlur);

        // CTA_R text box
        createLabel(grpMisc, "!CTA_R", 1, false);
        txtCTA_R = createTextBox(grpMisc, 4, -1);
        txtCTA_R.addActionListener(onAction);
        Util.onFocus(txtCTA_R, null, onBlur);

        // Buffer spinner
        createLabel(grpMisc, "vip.buffer", 1, false);
        spnBuffer = createSpinner(grpMisc, 0, 1, -1);
        spnBuffer.addChangeListener(e->onBuffer());

        // Frame spinner
        createLabel(grpMisc, "vip.frame", 1, false);
        spnFrame = createSpinner(grpMisc, 0, 15, -1);
        spnFrame.addChangeListener(e->onFrame());

        // Finalize the layout
        finish(grpMisc);
    }

    // Set up the Objects interface components
    private void initObjects() {
        JPanel grpObjects = createGroup("vip.objects", 3, 0, false);

        // Header row
        createLabel(grpObjects, null, 1, true);
        createLabel(grpObjects, "common.start", 1, true);
        createLabel(grpObjects, "common.end", -1, true);

        // Object pointers
        ChangeListener onChange = e->onObject((JSpinner) e.getSource());
        lblStart = new JLabel[OBJECTS.length];
        spnEnd   = new JSpinner[OBJECTS.length];
        for (int x = 0; x < OBJECTS.length; x++) {
            createLabel(grpObjects, "!" + OBJECTS[x], 1, false);
            JLabel lbl = lblStart[x] =
                createLabel(grpObjects, null, 1, false);
            lbl.setHorizontalAlignment(SwingConstants.RIGHT);
            JSpinner spn = spnEnd[x] =
                createSpinner(grpObjects, 0, 1023, -1);
            spn.putClientProperty("index", x);
            spn.addChangeListener(onChange);
        }

        // Finalize the layout
        finish(grpObjects);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update display with the current emulation state
    void refresh() {
        updating = true;
        refreshColors();
        refreshDisplay();
        refreshDrawing();
        refreshInterrupt();
        refreshMisc();
        refreshObjects();
        updating = false;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // BKCOL text box
    private void onBKCOL() {
        if (updating)
            return;
        vue.write(0x0005F870, VUE.U16, (Integer) spnBKCOL.getValue() & 3, true);
        parent.refresh(false);
    }

    // Brightness text box
    private void onBRT(JTextField source) {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int value = 0;
        try { value = (int) Long.parseLong(source.getText(), 16); }
        catch (Exception e) {
            refresh();
            return;
        }

        // Update emulation state
        int address = 0x0005F824 +
            ((Integer) source.getClientProperty("index") << 1);
        vue.write(address, VUE.U16, value & 0xFF, true);
        parent.refresh(false);
    }

    // Buffer spinner
    private void onBuffer() {
        if (updating)
            return;
        vue.setFrameBufferIndex((Integer) spnBuffer.getValue());
        parent.refresh(false);
    }

    // Display flag check box
    private void onDisplayFlag(JCheckBox source) {
        if (updating)
            return;

        // Retrieve state information for the source component
        int     mask  = (Integer) source.getClientProperty("mask");
        boolean state = source.isSelected();

        // Update emulation state
        int value = vue.read(0x0005F820, VUE.U16, true);
        vue.write(0x0005F820, VUE.U16, state? value|mask : value&~mask, true);
        parent.refresh(false);
    }

    // DPRST button
    private void onDPRST() {
        if (updating)
            return;
        int value = vue.read(0x0005F820, VUE.U16, true) | 1;
        vue.write(0x0005F822, VUE.U16, value, true);
        parent.refresh(false);
    }

    // DPSTTS text box
    private void onDPSTTS() {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int value = 0;
        try { value = (int) Long.parseLong(txtDPSTTS.getText(), 16); }
        catch (Exception e) {
            refresh();
            return;
        }

        // Update emulation state
        vue.write(0x0005F820, VUE.U16, value, true);
        parent.refresh(false);
    }

    // Drawing field text boxes and spinners
    private void onDrawing(JComponent source) {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int value = -1;

        // Processing for text boxes
        if (source instanceof JTextField) {
            try { value = (int) Long.parseLong(
                ((JTextField) source).getText(), 16); }
            catch (Exception e) {
                refresh();
                return;
            }
        }

        // Processing for spinners
        else value = (Integer) ((JSpinner) source).getValue();

        // Retrieve field properties
        Object[] field = (Object[]) source.getClientProperty("field");
        int      shift = (Integer) field[3];
        int      size  = (Integer) field[4];
        int      mask  = (1 << size) - 1;

        // Update emulation state
        int address = 0x0005F842 - ((Integer) field[2] << 1);
        int reg     = vue.read(address, VUE.U16, true);
        value       = reg & ~(mask << shift) | (value & mask) << shift;
        vue.write(address, VUE.U16, value, true);
        parent.refresh(false);
    }

    // Frame spinner
    private void onFrame() {
        if (updating)
            return;
        vue.setFrameIndex((Integer) spnFrame.getValue());
        parent.refresh(false);
    }

    // FRMCYC spinner
    private void onFRMCYC() {
        if (updating)
            return;
        vue.write(0x0005F82E, VUE.U16,
            (Integer) spnFRMCYC.getValue() & 15, true);
        parent.refresh(false);
    }

    // Interrupt text box
    private void onIRQ(int reg) {
        if (updating)
            return;

        // Retrieve state information
        int    address = 0x0005F802 - (reg << 1);
        String text    = (reg == 0 ? txtINTENB : txtINTPND).getText();

        // Attempt to process the text entered by the user
        int value = 0;
        try { value = (int) Long.parseLong(text, 16); }
        catch (Exception e) {
            refresh();
            return;
        }

        // Update emulation state
        vue.write(address, VUE.U16, value, true);
        parent.refresh(false);
    }

    // Interrupt flag check box
    private void onIRQFlag(JCheckBox source) {
        if (updating)
            return;

        // Retrieve state information for the source component
        int     reg   = (Integer) source.getClientProperty("reg");
        int     mask  = (Integer) source.getClientProperty("mask");
        boolean state = source.isSelected();

        // Update emulation state
        int address = 0x0005F800 + (1 - reg) * 2;
        int value = vue.read(address, VUE.U16, true);
        vue.write(address, VUE.U16, state? value | mask : value & ~mask, true);
        parent.refresh(false);
    }

    // Object spinner
    private void onObject(JSpinner source) {
        if (updating)
            return;
        vue.write(((Integer) source.getClientProperty("index") << 1) + 
            0x0005F848, VUE.U16, (Integer) source.getValue(), true);
        parent.refresh(false);
    }

    // Palette text box
    private void onPalette(JComponent source) {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int value = 0;

        // Processing for text boxes
        if (source instanceof JTextField) {
            try { value = (int) Long.parseLong(
                ((JTextField) source).getText(), 16); }
            catch (Exception e) {
                refresh();
                return;
            }
        }

        // Processing for spinners
        else value = (Integer) ((JSpinner) source).getValue();

        // Retrieve palette properties
        int palette = (Integer) source.getClientProperty("palette");
        int address = 0x0005F860 + (palette << 1);
        int reg     = vue.read(address, VUE.U16, true);

        // Compose a register value for a color entry
        Integer index = (Integer) source.getClientProperty("index");
        if (index != null) {
            int shift = index << 1;
            int mask  = 3 << shift;
            value = reg & ~mask | (value & 3) << shift;
        }

        // Update emulation state
        vue.write(address, VUE.U16, value, true);
        parent.refresh(false);
    }

    // REST text box
    private void onREST() {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int value = 0;
        try { value = (int) Long.parseLong(txtREST.getText(), 16); }
        catch (Exception e) {
            refresh();
            return;
        }

        // Update emulation state
        vue.write(0x0005F82A, VUE.U16, value & 0xFF, true);
        parent.refresh(false);
    }

    // Drawing flag check box
    private void onXPFlag(JCheckBox source) {
        if (updating)
            return;

        // Retrieve state information for the source component
        int     mask  = (Integer) source.getClientProperty("mask");
        boolean state = source.isSelected();

        // Update emulation state
        int value = vue.read(0x0005F842, VUE.U16, true);
        vue.write(0x0005F842, VUE.U16, state ? value|mask : value&~mask, true);
        parent.refresh(false);
    }

    // XPRST button
    private void onXPRST() {
        if (updating)
            return;
        int value = vue.read(0x0005F840, VUE.U16, true) | 1;
        vue.write(0x0005F842, VUE.U16, value, true);
        parent.refresh(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Add a component to a container
    private void add(JComponent container, JComponent ctl,
        int width, boolean center) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor        = header ?
            GridBagConstraints.LINE_START : GridBagConstraints.CENTER;
        gbc.fill          = center ?
            GridBagConstraints.NONE : GridBagConstraints.BOTH;
        gbc.gridwidth     = width > 0 ? width : GridBagConstraints.REMAINDER;
        gbc.insets.bottom = 1;
        gbc.insets.left   = header ? 0 : 4;
        gbc.weightx       = header ? 1 : 0;
        container.add(ctl, gbc);
        header = width <= 0;
    }

    // Factory method for buttons
    private JButton createButton(JComponent container,
        String key, int width) {
        JButton ctl = new JButton();
        config.addFontComponent(ctl, Config.DIALOG);
        config.addLocaleComponent(ctl, key);
        add(container, ctl, width, true);
        return ctl;
    }

    // Factory method for check boxes
    private JCheckBox createCheckBox(JComponent container,
        String key, int width) {
        JCheckBox ctl = new JCheckBox();
        ctl.setBorder(null);
        ctl.setFocusable(false);
        if (key != null) {
            config.addFontComponent(ctl, Config.DIALOG);
            config.addLocaleComponent(ctl, key);
        }
        add(container, ctl, width, key == null);
        return ctl;
    }

    // Factory method for group boxes
    private JPanel createGroup(String key, int x, int y, boolean tall) {

        // Configure component
        JPanel box = new JPanel(new GridBagLayout());
        box.setBorder(new TitledBorder(""));
        config.addFontComponent(box, Config.DIALOG);
        config.addLocaleComponent(box, key);

        // Configure layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill          = GridBagConstraints.BOTH;
        gbc.gridheight    = tall ? 2 : 1;
        gbc.gridwidth     = x != 3 ? 1 : GridBagConstraints.REMAINDER;
        gbc.gridx         = x;
        gbc.gridy         = y;
        gbc.insets.bottom = y == 0 && !tall ? 0 : 0;
        gbc.insets.right  = x < 3 ? 0 : 0;
        ribbon.add(box, gbc);

        return box;
    }

    // Factory method for labels
    private JLabel createLabel(JComponent container, String key,
        int width, boolean center) {
        JLabel ctl = new JLabel();
        if (key != null) {
            config.addFontComponent(ctl, Config.DIALOG);
            config.addLocaleComponent(ctl, key);
        }
        add(container, ctl, width, center);
        return ctl;
    }

    // Factory method for spinners
    private JSpinner createSpinner(JComponent container,
        int min, int max, int width) {
        JSpinner ctl = new JSpinner(new SpinnerNumberModel(min, min, max, 1));
        ctl.setEditor(new JSpinner.NumberEditor(ctl, "#"));
        config.addFontComponent(ctl, Config.DIALOG);
        add(container, ctl, width, false);
        return ctl;
    }

    // Factory method for text boxes
    private JTextField createTextBox(JComponent container,
        int digits, int width) {
        JTextField ctl = new JTextField();
        ctl.setHorizontalAlignment(JTextField.RIGHT);
        ctl.putClientProperty("digits", digits);
        config.addFontComponent(ctl, Config.FIXED);
        add(container, ctl, width, false);
        return ctl;
    }

    // Finalize the layout
    private void finish(JComponent container) {
        JPanel             spacer = new JPanel(null);
        GridBagConstraints gbc    = new GridBagConstraints();
        gbc.gridwidth             = GridBagConstraints.REMAINDER;
        gbc.weighty               = 1;
        spacer.setOpaque(false);
        spacer.setPreferredSize(new Dimension(0, 0));
        container.add(spacer, gbc);
    }

    // Update Colors components
    private void refreshColors() {

        // Update brightness text boxes and labels
        int[] brt = { 0, 0, 0, 0 };
        for (int x = 0; x < 4; x++) {

            // Update text box
            if (x > 0) {
                brt[x] = vue.read(0x0005F822 + (x << 1), VUE.U16, true) & 0xFF;
                txtBRT[x - 1].setText(String.format("%02X", brt[x]));
            }

            // Update label
            brt[3] += brt[1] + brt[2];
            lblBRT[x].setBackground(new Color(
                (Math.min(brt[x], 127) * 510 + 1) / 254 << 16));
        }

        // Update palette text boxes and spinners
        for (int y = 0; y < txtPalettes.length; y++) {
            int          reg  = vue.read(0x0005F860 + (y << 1), VUE.U16, true);
            txtPalettes[y].setText(String.format("%02X", reg & 0xFC));
            for (int x = 1; x < 4; x++)
                spnPalettes[y][x - 1].setValue(reg >> (x << 1) & 3);
        }

        // Update REST text box and BKCOL spinner
        txtREST.setText(String.format("%02X",
            vue.read(0x0005F82A, VUE.U16, true) & 0xFF));
        spnBKCOL.setValue(vue.read(0x0005F870, VUE.U16, true) & 3);
    }

    // Update Display components
    private void refreshDisplay() {
        int reg = vue.read(0x0005F820, VUE.U16, true);

        // Update DPSTTS text box
        txtDPSTTS.setText(String.format("%04X", reg));

        // Update flag check boxes
        for (int x = 0; x < chkDisplay.length; x++)
            chkDisplay[x].setSelected(
                (reg & (Integer) DPFLAGS[x][1]) != 0);
    }

    // Update Drawing components
    private void refreshDrawing() {
        int[] regs = {
            vue.read(0x0005F842, VUE.U16, true),
            vue.read(0x0005F840, VUE.U16, true)
        };

        // Update field text boxes and spinners
        for (int x = 0; x < cmpDrawing.length; x++) {
            JComponent ctl = cmpDrawing[x];

            // Retrieve field properties
            Object[] field = (Object[]) ctl.getClientProperty("field");
            int      reg   = (Integer) field[2];
            int      shift = (Integer) field[3];
            int      size  = (Integer) field[4];
            int      mask  = (1 << size) - 1;
            int      value = regs[reg] >> shift & mask;

            // Update control as a text box
            if (x < 2) ((JTextField) ctl).setText(String.format(
                size <= 8 ? "%02X" : "%04X", value));

            // Update control as a spinner
            else ((JSpinner) ctl).setValue(value);
        }

        // Update flag check boxes
        for (int x = 0; x < chkDrawing.length; x++) {
            JCheckBox ctl  = chkDrawing[x];
            int       mask = (Integer) ctl.getClientProperty("mask");
            ctl.setSelected((regs[1] & mask) != 0);
        }

    }

    // Update Interrupt components
    private void refreshInterrupt() {
        int[] regs = {
            vue.read(0x0005F802, VUE.U16, true),
            vue.read(0x0005F800, VUE.U16, true)
        };

        // Update value text boxes
        txtINTENB.setText(String.format("%04X", regs[0]));
        txtINTPND.setText(String.format("%04X", regs[1]));

        // Update flag check boxes
        for (int y = 0; y < chkIRQ.length; y++)
            for (int x = 0; x < 2; x++)
                chkIRQ[y][x].setSelected(
                    (regs[x] & (Integer) IRQFLAGS[y][1]) != 0);
    }

    // Update Misc. components
    private void refreshMisc() {

        // FRMCYC spinner
        spnFRMCYC.setValue(vue.read(0x0005F82E, VUE.U16, true) & 15);

        // CTA, CTA_L and CTA_R text boxes
        int cta = vue.read(0x0005F830, VUE.U16, true);
        txtCTA  .setText(String.format("%04X", cta            ));
        txtCTA_L.setText(String.format("%02X", cta      & 0xFF));
        txtCTA_R.setText(String.format("%02X", cta >> 8 & 0xFF));

        // Buffer and Frame spinner
        spnBuffer.setValue(vue.getFrameBufferIndex());
        spnFrame .setValue(vue.getFrameIndex      ());
    }

    // Update Objects components
    private void refreshObjects() {
        int[] regs = new int[4];

        // Retrieve register values
        for (int x = 0; x < 4; x++)
            regs[x] = vue.read(0x0005F848 + (x << 1), VUE.U16, true);

        // Update components
        for (int x = 0; x < 4; x++) {
            lblStart[x].setText("" + (x == 0 ? 0 : regs[x - 1] + 1 & 1023));
            spnEnd  [x].setValue(regs[x]);
        }

    }

}
