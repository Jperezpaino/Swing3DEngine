package debugger;

// Java imports
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Project import
import config.*;
import debugger.*;
import util.*;
import vue.*;

// Configurable interface for CPU registers
class CPURegister {

    // Instance fields
    private int      format;
    private int      index;
    private boolean  isSystem;
    private Debugger parent;
            boolean  refreshing;
    private int      value;

    // UI components
    private JLabel       btnExpand; // Expand/collapse button
    private JPanel       client;    // Containing element
    private JComponent[] controls; // Expansion controls
    private JPanel       expansion; // Expansion control area
    private JLabel       lblName;   // Register name
    private JTextField   txtValue;  // Register value



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Expansion button text
    private static final String COLLAPSE = "-";
    private static final String EXPAND   = "+";

    // Value text box formats
    private static final int HEX      = 0;
    private static final int SIGNED   = 1;
    private static final int UNSIGNED = 2;
    private static final int FLOAT    = 3;

    // System register indexes
    static final int PC = -1;

    // Program register names
    private static final String[] PROREGS = {
        null, null, "HP", "SP", "GP", "TP", null, null,
        null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, null,
        null, null, null, null, null, null, null, "LP"
    };

    // System register names
    private static final String[] SYSREGS = {
        "PC",
        "EIPC", "EIPSW", "FEPC", "FEPSW", "ECR", "PSW", "PIR", "TKCW",
        null,   null,    null,   null,    null,  null,  null,  null,
        null,   null,    null,   null,    null,  null,  null,  null,
        "CHCW", "ADTRE", null,   null,    null,  null,  null,  null
    };

    // Expansion control types
    private static final int CHECK = 0;
    private static final int TEXT  = 1;
    private static final int RADIO = 2;

    // Expansion control flags
    private static final int BREAK    = 0x10;
    private static final int DISABLED = 0x20;
    private static final int HEXFLAG  = 0x40;
    private static final int SPACE    = 0x80;

    // Program register expansion controls
    private static final Object[] EXP_PROGRAM = {
        RADIO | BREAK, "Hex",
        RADIO | BREAK, "Signed",
        RADIO | BREAK, "Unsigned",
        RADIO | BREAK, "Float"
    };

    // CHCW expansion controls
    private static final Object[] EXP_CHCW = {
        CHECK | BREAK, "ICE", 0x00000002
    };

    // ECR expansion controls
    private static final Object[] EXP_ECR = {
        TEXT | HEXFLAG | BREAK, "EICC", 4, 16, 0,
        TEXT | HEXFLAG | BREAK, "FECC", 4, 16, 16
    };

    // PIR expansion controls
    private static final Object[] EXP_PIR = {
        TEXT | HEXFLAG | DISABLED | BREAK, "PT", 4, 16, 0
    };

    // PSW expansion controls
    private static final Object[] EXP_PSW = {
        CHECK | SPACE, "Z",   0x00000001,
        CHECK | BREAK, "FRO", 0x00000200,
        CHECK | SPACE, "S",   0x00000002,
        CHECK | BREAK, "FIV", 0x00000100,
        CHECK | SPACE, "OV",  0x00000004,
        CHECK | BREAK, "FZD", 0x00000080,
        CHECK | SPACE, "CY",  0x00000008,
        CHECK | BREAK, "FOV", 0x00000040,
        CHECK | SPACE, "EP",  0x00004000,
        CHECK | BREAK, "FUD", 0x00000020,
        CHECK | SPACE, "NP",  0x00008000,
        CHECK | BREAK, "FPR", 0x00000010,
        CHECK | BREAK, "AE",  0x00002000,
        CHECK | SPACE, "ID",  0x00001000,
        TEXT  | BREAK, "I",   1, 4, 16
    };

    // TKCW expansion controls
    private static final Object[] EXP_TKCW = {
        CHECK | DISABLED | SPACE, "OTM", 0x00000100,
        CHECK | DISABLED | BREAK, "FVT", 0x00000020,
        CHECK | DISABLED | SPACE, "FIT", 0x00000080,
        CHECK | DISABLED | BREAK, "FUT", 0x00000010,
        CHECK | DISABLED | SPACE, "FZT", 0x00000040,
        CHECK | DISABLED | BREAK, "FPT", 0x00000008,
        CHECK | DISABLED | SPACE, "RDI", 0x00000004,
        TEXT  | DISABLED | BREAK, "RD" , 1, 2, 0
    };



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    CPURegister(Debugger parent, JPanel client, int index, boolean isSystem) {

        // Configure instance fields
        this.client   = client;
        controls      = null;
        expansion     = null;
        format        = HEX;
        this.index    = index;
        this.isSystem = isSystem;
        this.parent   = parent;
        refreshing    = false;
        value         = 0;

        // Configure register controls
        if (isSystem) switch (index) {
            case VUE.PSW: case VUE.EIPSW: case VUE.FEPSW:
                           initExpansion(EXP_PSW ); break;
            case VUE.ECR:  initExpansion(EXP_ECR ); break;
            case VUE.CHCW: initExpansion(EXP_CHCW); break;
            case VUE.PIR:  initExpansion(EXP_PIR ); break;
            case VUE.TKCW: initExpansion(EXP_TKCW); break;
        } else initExpansion(EXP_PROGRAM);
        initCommon();
    }

    // Common control constructor
    private void initCommon() {
        Config config = parent.getConfig();
        Color  color  = config.getColor("windowText");
        GridBagConstraints gbc;

        // Expand/collapse button
        if (expansion != null) {
            btnExpand = new JLabel(EXPAND);
            btnExpand.setForeground(color);
            btnExpand.setHorizontalAlignment(SwingConstants.CENTER);
            btnExpand.setOpaque(false);
            config.addFontComponent(btnExpand, Config.DIALOG);
            Util.onMouse(btnExpand, e->onExpand(e), null);
            gbc              = new GridBagConstraints();
            gbc.fill         = GridBagConstraints.BOTH;
            gbc.insets.right = 4;
            client.add(btnExpand, gbc);
        }

        // Name label
        lblName = new JLabel(getName());
        lblName.setForeground(color);
        lblName.setOpaque(false);
        config.addFontComponent(lblName, Config.DIALOG);
        Util.onMouse(lblName, e->onExpand(e), null);
        gbc              = new GridBagConstraints();
        gbc.fill         = GridBagConstraints.BOTH;
        gbc.gridx        = 1;
        gbc.insets.right = 4;
        gbc.weightx      = 1.0;
        client.add(lblName, gbc);

        // Value text box
        txtValue = new JTextField("00000000");
        txtValue.setBorder(null);
        txtValue.setForeground(color);
        txtValue.setOpaque(false);
        txtValue.putClientProperty("digits", 8);
        txtValue.putClientProperty("forceHex", true);
        config.addFontComponent(txtValue, Config.FIXED);
        Util.onFocus(txtValue, null, e->onValue());
        txtValue.addActionListener(e->{ client.requestFocus(); });
        gbc           = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        client.add(txtValue, gbc);

        // Expansion controls
        if (expansion != null) {
            expansion.setVisible(false);
            gbc = new GridBagConstraints();
            gbc.anchor      = GridBagConstraints.LINE_START;
            gbc.gridwidth   = GridBagConstraints.REMAINDER;
            gbc.gridx       = 1;
            gbc.insets.left = 8;
            client.add(expansion, gbc);
        }

    }

    // Expansion control constructor
    private void initExpansion(Object[] def) {
        Config                config   = parent.getConfig();
        ArrayList<JComponent> controls = new ArrayList<JComponent>();
        GridBagConstraints gbc;
        ButtonGroup        group = null;

        // Configure container component
        expansion = new JPanel(new GridBagLayout());
        expansion.setOpaque(false);

        // Process all controls
        for (int x = 0; x < def.length;) {

            // Process top-level parameters
            int        flags   = (Integer) def[x++];
            boolean    enabled = (flags & DISABLED) == 0;
            boolean    hex     = (flags & HEXFLAG)  != 0;
            boolean    isBreak = (flags & BREAK)    != 0;
            boolean    isSpace = (flags & SPACE)    != 0;
            int        type    = flags  & 0x0F;

            // Prepare control for creation
            JComponent control    = null;
                       gbc        = new GridBagConstraints();
                       gbc.anchor = GridBagConstraints.LINE_START;

            // Check box
            if (type == CHECK) {
                JCheckBox check = new JCheckBox((String) def[x++]);
                check.setFocusable(false);
                check.putClientProperty("mask", def[x++]);
                control = check;
                config.addFontComponent(control, Config.DIALOG);

                // Configure event response
                check.addActionListener(e->onExpansion());
            }

            // Radio button
            if (type == RADIO) {
                JRadioButton radio = new JRadioButton((String) def[x++]);
                radio.setFocusable(false);
                control = radio;
                config.addFontComponent(control, Config.DIALOG);

                // Add the control to the local group
                if (group == null) {
                    group = new ButtonGroup();
                    radio.setSelected(true);
                }
                group.add(radio);

                // Configure event response
                int format = controls.size();
                radio.addActionListener(e->onFormat(format));
            }

            // Text box
            if (type == TEXT) {

                // Insert a label for the control
                JLabel label = new JLabel((String) def[x++]);
                label.setEnabled(enabled);
                label.setOpaque(false);
                gbc.insets.right = 4;
                expansion.add(label, gbc);
                gbc = new GridBagConstraints();
                gbc.anchor = GridBagConstraints.LINE_START;

                // Configure the control's display properties
                if (!enabled) control = new JLabel(" ");
                else          control = new JTextField(" ");
                control.putClientProperty("isHex",    hex);
                control.putClientProperty("digits",   def[x++]);
                control.putClientProperty("size",     def[x++]);
                control.putClientProperty("position", def[x++]);
                config.addFontComponent(control,
                    hex ? Config.FIXED : Config.DIALOG);

                // Configure event response
                if (enabled) {
                    Util.onFocus(control, null, e->onExpansion());
                    ((JTextField) control).addActionListener
                        (e->client.requestFocus());
                }

            }

            // Configure layout
            gbc.gridwidth = isBreak ? GridBagConstraints.REMAINDER : 1;
            if (isSpace) gbc.insets.right = 8;

            // Configure control
            control.setBorder(null);
            control.setEnabled(enabled);
            control.setOpaque(false);
            controls.add(control);
            expansion.add(control, gbc);
        }

        // Produce the final array of controls
        this.controls = controls.toArray(new JComponent[controls.size()]);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // Collapse/expand
    private void onExpand(MouseEvent e) {
        int button = e.getButton();

        // Transfer focus to the containing component
        client.requestFocus();

        // No operation to perform
        if (
            expansion == null            || // No expansion controls
            button != MouseEvent.BUTTON1    // Not left mouse button
        ) return;

        setExpanded(!expansion.isVisible());
    }

    // Expansion control
    private void onExpansion() {

        // Take no action if refreshing
        if (refreshing)
            return;

        // Produce register value from controls
        int value = 0;
        for (int x = 0; x < controls.length; x++) {
            JComponent control = controls[x];

            // Check box
            if (control instanceof JCheckBox) {
                JCheckBox check = (JCheckBox) control;
                if (check.isSelected())
                    value |= (Integer) check.getClientProperty("mask");
            }

            // Text box
            if (control instanceof JTextField) try {
                JTextField text  = (JTextField) control;
                boolean    isHex = (Boolean) text.getClientProperty("isHex");
                int     position = (Integer)text.getClientProperty("position");
                int        size  = (Integer) text.getClientProperty("size");
                int       number =Integer.parseInt(text.getText(),isHex?16:10);
                value |= (number & (1 << size) - 1) << position;
            } catch (Exception e) { }

        }

        // Update emulation state
        setValue(value);
    }

    // Program register format
    private void onFormat(int format) {
        this.format = format;
        parent.getConfig().addFontComponent(txtValue,
            format == HEX ? Config.FIXED : Config.DIALOG);
        refresh();
    }

    // Value changed
    private void onValue() {
        String text  = txtValue.getText();
        int    value = this.value;

        // Take no action if refreshing
        if (refreshing)
            return;

        // Parse the entered value
        try { switch (format) {
            case HEX:
                value = (int) Long.parseLong(text, 16);
                break;
            case SIGNED: case UNSIGNED:
                value = (int) Long.parseLong(text);
                break;
            case FLOAT:
                value = Float.floatToIntBits(Float.parseFloat(text));
                break;
        } } catch (Exception e) { }

        // Update the register's value into the emulation state
        setValue(value);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Determine the top coordinate of this register's controls in the layout
    int getY() {
        return lblName.getY();
    }

    // Update display with the current emulation state
    void refresh() {
        String hex = parent.getDasm().getHexCase() ? "X" : "x";
        VUE vue    = parent.getVUE();
        value      = index == PC ? vue.getProgramCounter() : isSystem ?
            vue.getSystemRegister(index) : vue.getProgramRegister(index);

        refreshing = true;

        // Update top-level controls
        lblName.setText(getName());
        txtValue.setText(getText());
        txtValue.setCaretPosition(0);

        // Update expansion controls
        if (controls != null) for (int x = 0; x < controls.length; x++) {
            JComponent control = controls[x];

            // Check box
            if (control instanceof JCheckBox) {
                JCheckBox check = (JCheckBox) control;
                int mask = (Integer) check.getClientProperty("mask");
                check.setSelected((value & mask) != 0);
            }

            // Text box/label
            else if (control instanceof JLabel||control instanceof JTextField){
                int     digits = (Integer) control.getClientProperty("digits");
                boolean isHex  = (Boolean) control.getClientProperty("isHex");
                int    position=(Integer)control.getClientProperty("position");
                int     size   = (Integer) control.getClientProperty("size");

                // Determine display text
                String text = String.format(
                    isHex ? "%0" + digits + hex : "%d",
                    value >> position & (1 << size) - 1
                );

                // Update control
                if (control instanceof JTextField)
                    ((JTextField) control).setText(text);
                else ((JLabel) control).setText(text);
            }

        }

        refreshing = false;
    }

    // Expand or collapse the expansion area
    void setExpanded(boolean expanded) {

        // No expansion controls
        if (expansion == null)
            return;

        // Update exapnsion controls
        btnExpand.setText(expanded ? COLLAPSE : EXPAND);
        expansion.setVisible(expanded);
    }

    // Store a new value into the emulation state
    void setValue(int value) {

        // Take no action
        if (value == this.value) {
            refresh();
            return;
        }

        // Update emulation state
        VUE vue = parent.getVUE();
        if (index == PC)
            vue.setProgramCounter(value);
        else if (isSystem)
            vue.setSystemRegister(index, value);
        else vue.setProgramRegister(index, value);
        parent.refresh(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Determine the current display name of the register
    private String getName() {
        Disassembler dasm = parent.getDasm();

        // Determine the uppercase version of the register's name
        String ret = isSystem ?
            true                   ? SYSREGS[index + 1] : null :
            dasm.getProgramNames() ? PROREGS[index]     : null;
        if (ret == null)
            ret = (!isSystem ? "R" : "") + index;

        // Convert to lowercase if appropriate
        if (!(isSystem ? true : dasm.getProgramCase()))
            ret = ret.toLowerCase();
        return ret;
    }

    // Determine the current display value of the register
    private String getText() {
        switch (format) {
            case HEX:      return String.format("%08X", value);
            case SIGNED:   return "" + value;
            case UNSIGNED: return "" + (value & 0xFFFFFFFFL);
            case FLOAT:
                float  valuef = Float.intBitsToFloat(value);
                String ret    = "" + valuef;
                if (ret.toLowerCase().indexOf("e") != -1)
                    ret = String.format("%f", valuef);
                return ret;
        }
        return null;
    }

}
