package debugger;

// Java imports
import java.awt.*;
import java.util.*;
import javax.swing.*;

// Project imports
import config.*;
import debugger.*;
import vue.*;

// One row of output in the disassembler pane
class CPUDasmLine extends JPanel {

    // Instance fields
    public int         address; // Address of instruction
    public final int[] bits;    // Instruction machine code
    public int         size;    // Size in bytes of instruction

    // Private fields
    private JLabel[]    columns; // Instruction text
    private boolean     dirty;   // Output needs to be regenerated
    private Instruction inst;    // Disassembled instruction
    private Debugger    parent;  // Top-level UI manager



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Column indexes
    static final int ADDRESS  = 0;
    static final int BYTES    = 1;
    static final int MNEMONIC = 2;
    static final int OPERANDS = 3;
    static final int COMMENT  = 4;

    // I/O register names
    private static HashMap<Integer, String> REGISTERS;

    // Static constructor
    static {
        REGISTERS = new HashMap<Integer, String>();

        // Hardware component registers
        REGISTERS.put(0x02000000, "CCR" );
        REGISTERS.put(0x02000004, "CCSR");
        REGISTERS.put(0x02000008, "CDTR");
        REGISTERS.put(0x0200000C, "CDRR");
        REGISTERS.put(0x02000010, "SDLR");
        REGISTERS.put(0x02000014, "SDHR");
        REGISTERS.put(0x02000018, "TLR" );
        REGISTERS.put(0x0200001C, "THR" );
        REGISTERS.put(0x02000020, "TCR" );
        REGISTERS.put(0x02000024, "WCR" );
        REGISTERS.put(0x02000028, "SCR" );

        // VIP registers
        REGISTERS.put(0x0005F800, "INTPND");
        REGISTERS.put(0x0005F802, "INTENB");
        REGISTERS.put(0x0005F804, "INTCLR");
        REGISTERS.put(0x0005F820, "DPSTTS");
        REGISTERS.put(0x0005F822, "DPCTRL");
        REGISTERS.put(0x0005F824, "BRTA"  );
        REGISTERS.put(0x0005F826, "BRTB"  );
        REGISTERS.put(0x0005F828, "BRTC"  );
        REGISTERS.put(0x0005F82A, "REST"  );
        REGISTERS.put(0x0005F82E, "FRMCYC");
        REGISTERS.put(0x0005F830, "CTA"   );
        REGISTERS.put(0x0005F840, "XPSTTS");
        REGISTERS.put(0x0005F842, "XPCTRL");
        REGISTERS.put(0x0005F844, "VER"   );
        REGISTERS.put(0x0005F848, "SPT0"  );
        REGISTERS.put(0x0005F84A, "SPT1"  );
        REGISTERS.put(0x0005F84C, "SPT2"  );
        REGISTERS.put(0x0005F84E, "SPT3"  );
        REGISTERS.put(0x0005F860, "GPLT0" );
        REGISTERS.put(0x0005F862, "GPLT1" );
        REGISTERS.put(0x0005F864, "GPLT2" );
        REGISTERS.put(0x0005F866, "GPLT3" );
        REGISTERS.put(0x0005F868, "JPLT0" );
        REGISTERS.put(0x0005F86A, "JPLT1" );
        REGISTERS.put(0x0005F86C, "JPLT2" );
        REGISTERS.put(0x0005F86E, "JPLT3" );
        REGISTERS.put(0x0005F870, "BKCOL" );

        // VSU registers
        for (int x = 0; ; x++) {
            int    address = 0x01000400 + (x << 6);
            String prefix  = "S" + (x + 1);
            REGISTERS.put(address + 0x00, prefix + "INT");
            REGISTERS.put(address + 0x04, prefix + "LRV");
            REGISTERS.put(address + 0x08, prefix + "FQL");
            REGISTERS.put(address + 0x0C, prefix + "FQH");
            REGISTERS.put(address + 0x10, prefix + "EV0");
            REGISTERS.put(address + 0x14, prefix + "EV1");
            if (x == 5)
                break;
            REGISTERS.put(address + 0x18, prefix + "RAM");
        }
        REGISTERS.put(0x0100051C, "S5SWP");
        REGISTERS.put(0x01000580, "SSTOP");
    }



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    CPUDasmLine(Debugger parent) {
        super(null);

        // Configure instance fields
        address     = 0;
        bits        = new int[2];
        columns     = new JLabel[5];
        dirty       = true;
        inst        = new Instruction();
        size        = 0;
        this.parent = parent;

        // Initialize column labels
        Config config = parent.getConfig();
        for (int x = 0; x < columns.length; x++) {
            JLabel label = columns[x] = new JLabel();

            // Configure component
            label.setForeground(config.getColor("windowText"));
            label.setOpaque(false);
            label.setVisible(x != COMMENT);

            // Component management
            config.addFontComponent(label, x == COMMENT ?
                Config.DIALOG : Config.FIXED);
            add(label);
        }

        // Configure component
        setOpaque(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Configure component layout
    void configLayout(int[] widths, int height, int spacing) {
        for (int x = 0, left = 0; x < columns.length; x++) {
            JLabel  label   = columns[x];
            boolean isEmpty = widths[x] == 0;

            // Processing for empty column
            label.setVisible(!isEmpty);
            if (isEmpty) continue;

            // Processing for populated column
            if (x > 0) left += spacing;
            label.setBounds(left, 0, widths[x], height);
            left += widths[x];
        }
    }

    // Update UI components
    void refresh(int[] widths) {
        Config      config  = parent.getConfig();
        FontMetrics dialog  = config.getFontMetrics(Config.DIALOG);
        FontMetrics fixed   = config.getFontMetrics(Config.FIXED);
        boolean     isPC    = parent.getVUE().getProgramCounter() == address;

        // Disassemble the instruction and update column label text
        if (dirty) {
            inst.decode(bits[0], bits[1]);
            String[] text = parent.getDasm().disassemble(
                address, inst, new String[5]);
            for (int x = 0; x < columns.length; x++)
                columns[x].setText(x == MNEMONIC && text[x] == null ?
                    "---" : text[x]);
            dirty = false;
        }

        // Update comment column text
        columns[COMMENT].setText(getComment(isPC));

        // Measure column widths
        for (int x = 0; x < columns.length; x++) {
            String text = columns[x].getText();
            if (text == null) continue;
            widths[x] = Math.max(widths[x],
                (x == COMMENT ? dialog : fixed).stringWidth(text));
        }

    }

    // Update state according to address
    void setAddress(int address) {
        VUE vue = parent.getVUE();

        // The address changed
        if (this.address != address) {
            dirty = true;
            this.address = address;
        }

        // The lower halfword of machine code changed
        int bits = vue.read(address, VUE.U16, false);
        int size = Instruction.getSize(bits);
        dirty = dirty || this.bits[0] != bits || this.size != size;
        this.bits[0] = bits;
        this.size    = size;

        // The upper halfword of machine code changed
        if (size == 2) return;
        bits = vue.read(address + 2, VUE.U16, false);
        dirty = dirty || this.bits[1] != bits;
        this.bits[1] = bits;
    }

    // Specify whether the disassembler pane has focus
    void setFocus(boolean hasFocus) {
        Config  config = parent.getConfig();
        boolean isPC   = parent.getVUE().getProgramCounter() == address;

        // Configure component colors
        setBackground(config.getColor(hasFocus ? "selected" : "selectedBlur"));
        setOpaque(isPC);

        // Configure column labels and measure column widths
        Color color = config.getColor(isPC && hasFocus ?
            "selectedText" : "windowText");
        for (int x = 0; x < columns.length; x++)
            columns[x].setForeground(color);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Determine the comment text for the current line
    private String getComment(boolean isPC) {
        Config config = parent.getConfig();
        VUE    vue    = parent.getVUE();

        // Return instruction
        if (inst.id == VUE.JMP && inst.operands[1] == 31)
            return "; " + parent.getConfig().getMessage("dasm.return");

        // There is no comment
        if (!isPC)
            return null;

        // An exception is pending
        int code = vue.getExceptionCode();
        if (code != 0)
            return String.format("; %s 0x%04X",
                config.getMessage("cpu.exception"), code);

        // There is no comment
        if (inst.format != 6)
            return null;

        // Check for hardware register address
        int address = 0x07FFFFFF & parent.getVUE()
            .getProgramRegister(inst.operands[1]) + inst.operands[2];
        switch (address >> 24 & 7) {
            case 0: address &= 0x0007FFFF; break; // VIP
            case 1: address &= 0x010007FF; break; // VSU
            case 2: address &= 0x0200003F; break; // Hardware components
        }

        // Determine the register name, if any
        String ret = REGISTERS.get(address);
        return ret == null ? null : "; " + ret;
    }

}
