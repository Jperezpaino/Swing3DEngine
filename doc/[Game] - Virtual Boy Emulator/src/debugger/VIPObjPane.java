package debugger;

// Java imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Project imports
import config.*;
import debugger.*;
import util.*;
import vue.*;

// Interface for VIP objects
class VIPObjPane extends VIPPanel {

    // Instance fields
    private int index; // Current object index

    // Data element fields
    private int     address;   // Location in VIP memory
    private int     character; // Pixel pattern index
    private boolean hFlip;     // Flipped horizontally
    private boolean left;      // Displayed in left image
    private int     palette;   // Palette index
    private int     parallax;  // Horizontal disparity
    private boolean right;     // Displayed in right image
    private boolean vFlip;     // Flipped vertically
    private int     x;         // Horizontal position
    private int     y;         // Vertical position

    // Top-level components
    private JSpinner   spnIndex;   // Index spinner
    private JTextField txtAddress; // Address text box
    private JPanel     panPattern; // Pixel pattern display

    // Display components
    private JCheckBox chkGenericColors; // Generic colors check box

    // Properties components
    private JCheckBox chkHFlip;     // H-flip check box
    private JCheckBox chkLeft;      // Left check box
    private JCheckBox chkRight;     // Right check box
    private JCheckBox chkVFlip;     // V-flip check box
    private ComboBox  cmbPalette;   // Palette combo box
    private JSpinner  spnCharacter; // Character spinner
    private JSpinner  spnParallax;  // Parallax spinner
    private JSpinner  spnX;         // X spinner
    private JSpinner  spnY;         // Y spinner



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    VIPObjPane(Debugger parent) {
        super(parent);

        // Configure instance fields
        index = 0;

        // Configure UI
        initTopLevel();
        initProperties();
        initDisplay();

        // Initialize components
        updating = false;
        onScale(sldScale.getValue());
        setIndex(index, false);
    }

    // Set up the Display interface components
    private void initDisplay() {
        JPanel grpDisplay = createGroup("vip.display", true);

        // Scale slider
        sldScale.setValue(2);
        add(grpDisplay, sldScale, "vip.scale", false);

        // Generic colors check box
        chkGenericColors = createCheckBox("vip.generic_colors");
        chkGenericColors.addActionListener(e->{ if (!updating) refresh(); });
        add(grpDisplay, chkGenericColors, false, false);
    }

    // Set up the Properties interface components
    private void initProperties() {
        JPanel grpProperties = createGroup("common.properties", false);

        // Character spinner
        spnCharacter = createSpinner(0, 2047);
        spnCharacter.addChangeListener(e->onTweak());
        add(grpProperties, spnCharacter, "vip.character", false);

        // Palette combo box
        cmbPalette = createComboBox(new String[] {
            "!OBJ 0",  "!OBJ 1",  "!OBJ 2",  "!OBJ 3" });
        cmbPalette.addActionListener(e->onTweak());
        add(grpProperties, cmbPalette, "vip.palette", false);

        // X spinner
        spnX = createSpinner(-512, 511);
        spnX.addChangeListener(e->onTweak());
        add(grpProperties, spnX, "common.x", false);

        // Y spinner
        spnY = createSpinner(-8, 247);
        spnY.addChangeListener(e->onTweak());
        add(grpProperties, spnY, "common.y", false);

        // Parallax spinner
        spnParallax = createSpinner(-512, 511);
        spnParallax.addChangeListener(e->onTweak());
        add(grpProperties, spnParallax, "vip.parallax", false);

        // H-flip check box
        chkHFlip = createCheckBox("vip.hflip");
        chkHFlip.addActionListener(e->onTweak());
        add(grpProperties, chkHFlip, false);

        // V-flip check box
        chkVFlip = createCheckBox("vip.vflip");
        chkVFlip.addActionListener(e->onTweak());
        add(grpProperties, chkVFlip, true);

        // Left check box
        chkLeft = createCheckBox("common.left");
        chkLeft.addActionListener(e->onTweak());
        add(grpProperties, chkLeft, false);

        // Right check box
        chkRight = createCheckBox("common.right");
        chkRight.addActionListener(e->onTweak());
        add(grpProperties, chkRight, true);
    }

    // Set up the top-level interface components
    private void initTopLevel() {

        // Index spinner
        spnIndex = createSpinner(0, 1023);
        spnIndex.addChangeListener(e->onIndex());
        add(ribbon, spnIndex, "common.index", false);

        // Address text box
        txtAddress = createTextBox(8);
        txtAddress.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtAddress, null, e->onAddress());
        add(ribbon, txtAddress, "common.address", false);

        // Pattern panel
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBorder(Util.getInsetBorder());
        outer.setOpaque(false);

        // A child component is required for insets to work properly
        panPattern = new JPanel(null) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                onPaintPattern(g);
            }
        };
        panPattern.setOpaque(false);
        panPattern.setPreferredSize(new Dimension(128, 128));
        outer.add(panPattern, BorderLayout.CENTER);

        // Add the pattern box
        add(ribbon, outer, true, false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update display with the current emulation state
    void refresh() {
        setIndex(index, true);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // Address text box
    private void onAddress() {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int address = 0;
        try { address = (int) Long.parseLong(txtAddress.getText(), 16); }
        catch (Exception e) { }

        // Calculate the index by address
        if (address >= 0x0003E000 && address <= 0x0003FFFF)
            setIndex(address - 0x0003E000 >> 3, true);
        else
            setIndex(index, true);
    }

    // Index spinner
    private void onIndex() {
        if (!updating)
            setIndex((Integer) spnIndex.getValue(), true);
    }

    // Canvas paint
    void onPaint(Graphics g) {
        Rectangle bounds  = g.getClipBounds();
        boolean   generic = chkGenericColors.isSelected();
        int       scale   = sldScale.getValue();

        // Calculate the source bounds of the visible region
        int left   = Math.min(Math.max(bounds.x / scale, 0), 383);
        int top    = Math.min(Math.max(bounds.y / scale, 0), 223);
        int right  =Math.min(Math.max((bounds.x+bounds.width -1)/scale,0),383);
        int bottom =Math.min(Math.max((bounds.y+bounds.height-1)/scale,0),223);

        // Draw the invisible color across the entire canvas background
        g.setColor(new Color(config.getInvisibleColor(), true));
        g.fillRect(0, 0, 384 * scale, 224 * scale);

        // Draw all visible output pixels
        int[] samples = new int[2];
        for (int y = top;  y <= bottom; y++)
        for (int x = left; x <= right;  x++) {
            samples[0] = samples[1] = -1;
            if (sampleObj(samples, index, x, y, generic))
                continue;
            int color = toColor(samples[0], samples[1], generic);
            g.setColor(new Color(color, true));
            g.fillRect(x * scale, y * scale, scale, scale);
        }

    }

    // Pattern box paint
    private void onPaintPattern(Graphics g) {
        decode();
        paintChr(g, character, 0, 0, hFlip, vFlip, 16,
            chkGenericColors.isSelected() ? -1 : palette);
    }

    // Scale slider
    void onScale(int scale) {
        if (updating)
            return;

        // UI management for child-less canvas component
        canvas.setPreferredSize(
            new Dimension(384 * scale, 224 * scale));
        canvas.revalidate();
        canvas.repaint();

        // Scroll bar increment amounts
        setCanvasIncrement(scale * 8, scale * 8);
    }

    // Object property changed
    private void onTweak() {
        if (updating)
            return;

        // Update the data element in VIP memory
        write(address, (Integer) spnX.getValue());
        write(address | 2,
            (Integer) spnParallax.getValue() & 0x03FF |
            (chkRight.isSelected() ? 0x4000 : 0)      |
            (chkLeft .isSelected() ? 0x8000 : 0)
        );
        write(address | 4, (Integer) spnY.getValue());
        write(address | 6,
            (Integer) spnCharacter.getValue()    |
            (chkVFlip.isSelected() ? 0x1000 : 0) |
            (chkHFlip.isSelected() ? 0x2000 : 0) |
            cmbPalette.getSelectedIndex() << 14
        );

        // Commit the edit to the emulation state and update debugger
        parent.getVUE().write(address, VUE.U16,
            parent.vipMemory, address, 8, false);
        parent.refresh(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Decode one data element from VIP memory
    private void decode() {
        address   = 0x0003E000 + (index << 3);
        int bits  = read(address);
        x         = bits << 22 >> 22;
            bits  = read(address | 2);
        parallax  = bits << 22 >> 22;
        right     = (bits & 0x4000) != 0;
        left      = (bits & 0x8000) != 0;
            bits  = read(address | 4);
        y         = bits << 24 >> 24; if (y < -8) y += 256;
            bits  = read(address | 6);
        character = bits & 0x07FF;
        vFlip     = (bits & 0x1000) != 0;
        hFlip     = (bits & 0x2000) != 0;
        palette   = bits >> 14 & 3;
    }

    // Specify a new index for the current object
    private void setIndex(int index, boolean redraw) {

        // Update instance fields
        this.index = index;
        decode();

        // Update components
        updating = true;
        spnIndex.setValue(index);
        txtAddress.setText(String.format("%08X", address));
        spnCharacter.setValue(character);
        spnX.setValue(x);
        spnY.setValue(y);
        spnParallax.setValue(parallax);
        cmbPalette.setSelectedIndex(palette);
        chkHFlip.setSelected(hFlip);
        chkVFlip.setSelected(vFlip);
        chkLeft.setSelected(left);
        chkRight.setSelected(right);
        updating = false;

        // Redraw display
        if (!redraw)
            return;
        panPattern.repaint();
        canvas.repaint();
    }

}
