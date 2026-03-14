package debugger;

// Java imports
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

// Project imports
import config.*;
import debugger.*;
import util.*;

// Common UI container for all VIP interfaces
class VIPPanel extends JPanel {

    // Instance fields
    Config   config;    // Application configuration settings
    boolean  displayed; // Component has been displayed at least once
    Debugger parent;    // Parent debugger context
    boolean  updating;  // Set when updating UI components

    // UI components
    JPanel      canvas;    // Graphical output
    JPanel      ribbon;    // Controls container
    JSlider     sldScale;  // Scale slider
    JScrollPane scrCanvas; // Container for canvas
    JScrollPane scrRibbon; // Container for controls container



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    VIPPanel(Debugger parent) {
        super(new BorderLayout());

        // Configure instance fields
        config      = parent.getConfig();
        displayed   = false;
        this.parent = parent;
        updating    = true;

        // Controls ribbon
        ribbon = new JPanel(new GridBagLayout());
        ribbon.setBorder(new EmptyBorder(0, 2, 0, 2));
        ribbon.setFocusable(true);
        Util.onMouse(ribbon, e->ribbon.requestFocus(), null);

        // Ribbon container
        scrRibbon = new JScrollPane(ribbon,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
            public void paintComponent(Graphics g) {
                if (displayed)
                    return;
                displayed = true;
                onResize();
            }
        };
        scrRibbon.setBorder(null);

        // Output canvas
        canvas = new JPanel(null) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                onPaint(g);
            }
        };
        canvas.setFocusable(true);
        Util.onMouse(canvas, e->canvas.requestFocus(), null);
        scrCanvas = new JScrollPane(canvas);

        // Configure component
        add(scrRibbon, BorderLayout.LINE_START);
        add(scrCanvas, BorderLayout.CENTER);
        Util.onResize(this, e->onResize());
        config.addFontComponent(scrRibbon, Config.DIALOG);

        // Configure scale slider
        sldScale = new JSlider(1, 10, 1);
        sldScale.setFocusable(false);
        sldScale.setLabelTable(null);
        sldScale.setPreferredSize(new Dimension(0,
            sldScale.getPreferredSize().height));
        sldScale.setSnapToTicks(true);
        sldScale.addChangeListener(e->onScale(sldScale.getValue()));
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // Canvas paint
    // Must be overridden to produce meaningful output
    void onPaint(Graphics g) { }

    // Resize
    void onResize() {

        // Determine the size of the inner element and the visible area
        Dimension extent = scrRibbon.getViewport().getExtentSize();
        Dimension prefer = ribbon.getPreferredSize();
        if (extent.width == prefer.width)
            return;

        // Resize the container to exactly contain the inner element
        prefer.width  = scrRibbon.getWidth() + prefer.width - extent.width;
        prefer.height = 0;
        scrRibbon.setPreferredSize(prefer);
        ribbon.revalidate();
        scrRibbon.revalidate();
        scrRibbon.repaint();
    }

    // Scale slider
    // Must be overridden to produce meaningful output
    void onScale(int scale) { }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update display with the current emulation state
    // Must be overridden to produce meaningful output
    void refresh() { }



    ///////////////////////////////////////////////////////////////////////////
    //                           Internal Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Add a component to a container, optionally not at the end of the row
    void add(JComponent container, JComponent component, boolean end) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = end ? GridBagConstraints.REMAINDER : 1;
        if (!end)
            gbc.insets.right = 2;
        gbc.anchor  = GridBagConstraints.LINE_START;
        gbc.weightx = 1;
        container.add(component, gbc);
    }

    // Add a component to a container
    void add(JComponent container, JComponent component, boolean center,
        boolean last) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        if (!last)
            gbc.insets.bottom = 2;
        if (!center)
            gbc.anchor = GridBagConstraints.LINE_START;
        container.add(component, gbc);

        // Finalize the layout
        if (last)
            finish(container);
    }

    // Add a component with a localized label to a container
    void add(JComponent container, JComponent component, String key,
        boolean last) {

        // Configure the localized label
        JLabel label = new JLabel();
        config.addFontComponent  (label, Config.DIALOG);
        config.addLocaleComponent(label, key);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.LINE_START;
        if (!last)
            gbc.insets.bottom = 2;
        container.add(label, gbc);

        // Configure the component
        gbc = new GridBagConstraints();
        gbc.anchor        = GridBagConstraints.LINE_START;
        gbc.fill          = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth     = GridBagConstraints.REMAINDER;
        gbc.insets.left   = 4;
        gbc.weightx       = 1;
        if (!last)
            gbc.insets.bottom = 2;
        container.add(component, gbc);

        // Finalize the layout
        if (last)
            finish(container);
    }

    // Factory method for check boxes
    JCheckBox createCheckBox(String key) {
        JCheckBox ctl = new JCheckBox();
        ctl.setBorder(null);
        config.addLocaleComponent(ctl, key);
        config.addFontComponent(ctl, Config.DIALOG);
        return ctl;
    }

    // Factory method for combo boxes
    ComboBox createComboBox(String[] keys) {
        ComboBox ctl = new ComboBox(keys);
        config.addLocaleComponent(ctl, keys);
        config.addFontComponent(ctl, Config.DIALOG);
        return ctl;
    }

    // Factory method for group boxes
    JPanel createGroup(String key, boolean last) {

        // Configure the border
        JPanel box = new JPanel(new GridBagLayout());
        box.setBorder(new TitledBorder(""));
        config.addFontComponent(box, Config.DIALOG);
        config.addLocaleComponent(box, key);

        // Configure the container and add it to the ribbon
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill      = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        if (!last)
            gbc.insets.bottom = 2;
        ribbon.add(box, gbc);

        // Finalize the ribbon's layout
        if (last)
            finish(ribbon);

        return box;
    }

    // Factory method for int spinners
    JSpinner createSpinner(int min, int max) {
        JSpinner ctl = new JSpinner(new SpinnerNumberModel(min, min, max, 1));
        ctl.setEditor(new JSpinner.NumberEditor(ctl, "#"));
        config.addFontComponent(ctl, Config.DIALOG);
        return ctl;
    }

    // Factory method for double spinners
    JSpinner createSpinner(double min, double max) {
        JSpinner ctl = new JSpinner(new SpinnerNumberModel(min, min, max, 1));
        ctl.setEditor(new JSpinner.NumberEditor(ctl, "0.000"));
        config.addFontComponent(ctl, Config.DIALOG);
        return ctl;
    }

    // Factory method for text boxes
    JTextField createTextBox(Integer digits) {
        JTextField ctl = new JTextField();
        ctl.setHorizontalAlignment(JTextField.RIGHT);
        ctl.putClientProperty("digits", digits);
        config.addFontComponent(ctl, Config.FIXED);
        return ctl;
    }

    // Finalize the layout
    void finish(JComponent container) {
        JPanel             spacer = new JPanel(null);
        GridBagConstraints gbc    = new GridBagConstraints();
        gbc.gridwidth             = GridBagConstraints.REMAINDER;
        gbc.weighty               = 1;
        spacer.setOpaque(false);
        spacer.setPreferredSize(new Dimension(0, 0));
        container.add(spacer, gbc);
    }

    // Specify scroll bar increments for the canvas scroll pane
    void setCanvasIncrement(int horizontal, int vertical) {
        scrCanvas.getHorizontalScrollBar().setUnitIncrement(horizontal);
        scrCanvas.getVerticalScrollBar().setUnitIncrement(vertical);
    }

    // Convert left and right brightness levels into a color value
    int toColor(int left, int right, boolean generic) {

        // The pixel is fully invisible
        if (left + right == -2)
            return parent.getConfig().getInvisibleColor();

        // Construct the color from the visible samples
        int[][] colors =
            parent.colors[generic ? Debugger.GENERIC : Debugger.SCALED];
        int     ret    = 0xFF000000; // Default to opaque black
        if (left  != -1) ret |= colors[Config.LEFT ][left ];
        if (right != -1) ret |= colors[Config.RIGHT][right];
        return ret;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                              VIP Methods                              //
    ///////////////////////////////////////////////////////////////////////////

    // Determine the address of a given character
    static int chrAddress(int index) {
        return 0x00006000 + (index >> 9 << 15) | (index & 511) << 4;
    }

    // Specify a new value for one pixel within a character
    void drawChr(int index, int x, int y, int value) {
        int address = chrAddress(index) | y << 1 | x >> 2;
        int shift   = (x & 3) << 1;
        parent.vipMemory[address] = (byte) (value << shift |
            parent.vipMemory[address] & ~(3 << shift));
    }

    // Draw a character pattern into a component
    void paintChr(Graphics g, int index, int x, int y,
        boolean hFlip, boolean vFlip, int scale, int palette) {
        boolean generic = palette == -1;
        int[]   pal     = generic ?
            parent.GENERIC_PALETTE : parent.palettes[palette];
        int[]   reds    = parent.colors[generic ?
            Debugger.GENERIC : Debugger.SCALED][Config.RED];

        // Draw all pixels in the character
        int invisible = parent.getConfig().getInvisibleColor();
        for (int Y = 0; Y < 8; Y++)
        for (int X = 0; X < 8; X++) {
            int brt = pal[parent.sampleCharacter(index, X, Y, hFlip, vFlip)];
            g.setColor(new Color(brt == -1 ? invisible : reds[brt], true));
            g.fillRect(x + X * scale, y + Y * scale, scale, scale);
        }

    }

    // Read a halfword from VIP memory
    int read(int address) {
        return parent.vipMemory[address    ] & 0xFF |
               parent.vipMemory[address | 1] << 8;
    }

    // Retrieve the value of one pixel within an object in both frame buffers
    boolean sampleObj(int[] samples, int index, int x, int y, boolean generic){
        int address = 0x0003E000 + (index << 3);

        // Check whether the object is to be drawn
        int eyes = parent.vipMemory[address | 3];
        if ((eyes & 0xC0) == 0)
            return true; // Object is not drawn

        // Check vertical position
        int bits = parent.vipMemory[address | 4];
        y -= bits < -8 ? bits + 256 : bits;
        if (y < 0 || y > 7)
            return true; // Y coordinate out of range

        // Retrieve object information
        bits              = parent.vipMemory[address | 7] << 8;
        boolean hFlip     = (bits & 0x2000) != 0;
        boolean vFlip     = (bits & 0x1000) != 0;
        int     character = read(address | 6) & 0x07FF;
        int     parallax  = read(address | 2) << 22 >> 22;
        int[]   palette   = generic ? Debugger.GENERIC_PALETTE :
            parent.palettes[Debugger.OBJ0 + (bits >> 14 & 3)];

        // Sample both eyes
        x -= read(address) << 22 >> 22;
        for (int eye = 0; eye < 2; eye++) {

            // Check whether the object is drawn for this eye
            if (samples[eye] != -1 || (eyes & 0x80 >> eye) == 0)
                continue; // Object is not drawn

            // Check horizontal position
            int X = x + (eye == 0 ? parallax : -parallax);
            if (X < 0 || X > 7)
                continue; // X coordinate out of range

            // Sample from the object
            samples[eye] = palette[parent.sampleCharacter(
                character, X, y, hFlip, vFlip)];
        }

        // Determine whether anything was drawn
        return samples[0] + samples[1] == -2;
    }

    // Write a halfword to VIP memory
    void write(int address, int value) {
        parent.vipMemory[address    ] = (byte) value;
        parent.vipMemory[address | 1] = (byte) (value >> 8);
    }

}
