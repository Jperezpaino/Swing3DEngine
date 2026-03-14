package debugger;

// Java imports
import java.awt.*;
import javax.swing.*;

// Project imports
import config.*;
import debugger.*;

// One row of output in the disassembler pane
class CPUHexLine extends JPanel {

    // Instance fields
    private int          address;  // Address of row
    private final byte[] data;     // Memory data
    private boolean      dirty;    // Output needs to be regenerated
    private Debugger     parent;   // Top-level UI manager

    // UI components
    private JLabel[] columns;   // Memory text
    private JPanel[] highlight; // Highlight background



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Column indexes
    private static final int ADDRESS = 0;
    private static final int BYTES_A = 1;
    private static final int BYTES_B = 9;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    CPUHexLine(Debugger parent) {
        super(null);

        // Configure instance fields
        address     = 0;
        columns     = new JLabel[17];
        data        = new byte[16];
        dirty       = true;
        highlight   = new JPanel[2];
        this.parent = parent;

        // Initialize column labels
        Config config = parent.getConfig();
        Color  color         = config.getColor("windowText");
        for (int x = 0; x < columns.length; x++) {
            JLabel label = columns[x] = new JLabel();

            // Configure component
            label.setForeground(color);
            label.setHorizontalAlignment(
                x == ADDRESS ? SwingConstants.LEFT : SwingConstants.CENTER);
            label.setOpaque(false);

            // Component management
            config.addFontComponent(label, Config.FIXED);
            add(label);
        }

        // Initialize highlight backgrounds
        color  = config.getColor("window");
        for (int x = 0; x < highlight.length; x++) {
            JPanel panel = highlight[x] = new JPanel(null);

            // Configure component
            panel.setBackground(color);
            panel.setOpaque(true);
            panel.setVisible(x == 0);

            // Component management
            setComponentZOrder(panel, 16 + x);
            add(panel);
        }

        // Configure component
        setOpaque(false);
        revalidate();
    }


    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Configure component layout
    void configLayout(int hexWidth, int height, int spacing) {
        for (int x = 0, left = 0; x < columns.length; x++) {
            JLabel label = columns[x];

            // Update label
            int width = hexWidth * (x == ADDRESS ? 8 : 2);
            label.setBounds(left, 0, width, height);

            // Position for next label
            left += width;
            switch (x + 1) {
                case BYTES_A: case BYTES_B: left += spacing; break;
                default:                    left += hexWidth;
            }
        }
    }

    // Update state according to address and data
    void setData(int address, byte[] data, int offset) {

        // The address changed
        if (this.address != address) {
            dirty = true;
            this.address = address;
        }

        // Check each byte for changes
        for (int x = 0; x < 16; x++) {
            JLabel label = columns[x + 1];
            dirty = dirty || data[offset + x] != this.data[x] ||
                (Boolean) label.getClientProperty("dirty") != null;
            label.putClientProperty("dirty", false);
            this.data[x] = data[offset + x];
        }

        // No update is necessary
        if (!dirty) return;

        // Configure address column
        String hex = parent.getDasm().getHexCase() ? "X" : "x";
        columns[ADDRESS].setText(String.format("%08" + hex, address));

        // Configure byte columns
        hex = "%02" + hex;
        for (int x = 0; x < 16; x++)
            columns[x + 1].setText(String.format(hex, this.data[x]));

        // De-schedule output regeneration
        dirty = false;
    }

    // Update selection and focus state
    JLabel setFocus(boolean hasFocus, Integer selStart, int selStop) {
        JLabel ret = null;

        // The row intersects with the selection
        if (selStart != null && (
            Integer.compareUnsigned(selStart - address, 16) < 0 ||
            Integer.compareUnsigned(address-selStart, selStop-selStart) <= 0
        )) {
            selStart = Integer.compareUnsigned
                (selStart -= address, 16) < 0 ? selStart : -1;
            selStop  = Integer.compareUnsigned
                (selStop  -= address, 16) < 0 ? selStop  : 16;
        }

        // The row does not intersect with the selection
        else selStart = selStop = -1;

        // Configure highlight backgrounds
        Config config = parent.getConfig();
        Color s = config.getColor(hasFocus ? "selected" : "selectedBlur");
        highlight[0].setBackground(s);
        highlight[1].setBackground(s);
        highlight[0].setVisible(selStart + selStop != -2);
        highlight[1].setVisible(selStart > selStop);

        // The selection is contiguous
        if (selStart <= selStop)
            setHighlight(0, selStart, selStop);

        // The selection is not contiguous
        else {
            setHighlight(0, -1, selStop);
            setHighlight(1, selStart, 16);
        }

        // Update byte columns
        Color d = config.getColor("windowText");
        s       = config.getColor(hasFocus ? "selectedText" : "windowText");
        for (int x = 0; x < 16; x++)
            columns[x + 1].setForeground((
                selStart > selStop ?
                x >= selStart || x <= selStop :
                x >= selStart && x <= selStop
            ) ? s : d);
        
        // Return the column label at the start of the selection
        repaint();
        return selStart >= 0 && selStart <= 15 ? columns[selStart + 1] : null;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Configure a highlight background
    private void setHighlight(int index, int start, int stop) {
        int height   = getHeight();
        int hexWidth = parent.getConfig().getHexWidth() * 2;
        int x        = columns[Math.max(start, 0)+1].getX();
        int width    = columns[Math.min(stop, 15)+1].getX() + hexWidth - x;
        highlight[index].setBounds(x, 0, width, height);
    }

}