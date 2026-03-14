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

// Interface for VIP BG maps
class VIPBGMapPane extends VIPPanel {

    // Instance fields
    private int index; // Current global cell index

    // Data element fields
    private int     address;   // Location in VIP memory
    private int     character; // Pixel pattern index
    private boolean hFlip;     // Flipped horizontally
    private int     palette;   // Palette index
    private boolean vFlip;     // Flipped vertically

    // Top-level components
    private JSpinner   spnCell;    // Cell spinner
    private JSpinner   spnMap;     // Map spinner
    private JTextField txtAddress; // Address text box
    private JPanel     panPattern; // Pixel pattern display

    // Cell components
    private JCheckBox chkHFlip;     // H-flip check box
    private JCheckBox chkVFlip;     // V-flip check box
    private ComboBox  cmbPalette;   // Palette combo box
    private JSpinner  spnCharacter; // Character spinner

    // Display components
    private JCheckBox chkGenericColors; // Generic colors check box
    private JCheckBox chkShowGrid;      // Show grid check box



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    VIPBGMapPane(Debugger parent) {
        super(parent);

        // Configure instance fields
        index = 0;

        // Configure UI
        initTopLevel();
        initCell();
        initDisplay();

        // Initialize components
        updating = false;
        onScale(sldScale.getValue());
        setIndex(index, false);
    }

    // Set up the Cell interface components
    private void initCell() {
        JPanel grpCell = createGroup("vip.cell", false);

        // Character spinner
        spnCharacter = createSpinner(0, 2047);
        spnCharacter.addChangeListener(e->onTweak());
        add(grpCell, spnCharacter, "vip.character", false);

        // Palette combo box
        cmbPalette = createComboBox(new String[] {
            "!BG 0",  "!BG 1",  "!BG 2",  "!BG 3" });
        cmbPalette.addActionListener(e->onTweak());
        add(grpCell, cmbPalette, "vip.palette", false);

        // H-flip check box
        chkHFlip = createCheckBox("vip.hflip");
        chkHFlip.addActionListener(e->onTweak());
        add(grpCell, chkHFlip, false);

        // V-flip check box
        chkVFlip = createCheckBox("vip.vflip");
        chkVFlip.addActionListener(e->onTweak());
        add(grpCell, chkVFlip, false);
    }

    // Set up the Display interface components
    private void initDisplay() {
        JPanel grpDisplay = createGroup("vip.display", true);

        // Scale slider
        sldScale.setValue(2);
        add(grpDisplay, sldScale, "vip.scale", false);

        // Show grid check box
        chkShowGrid = createCheckBox("vip.show_grid");
        chkShowGrid.addActionListener(e->{ if (!updating) refresh(); });
        add(grpDisplay, chkShowGrid, false, false);

        // Generic colors check box
        chkGenericColors = createCheckBox("vip.generic_colors");
        chkGenericColors.addActionListener(e->{ if (!updating) refresh(); });
        add(grpDisplay, chkGenericColors, false, false);
    }

    // Set up the top-level interface components
    private void initTopLevel() {

        // Canvas
        Util.onMouse(canvas, e->onCanvas(e), null);
        Util.onMouseMove(canvas, null, e->onCanvas(e));

        // Map spinner
        spnMap = createSpinner(0, 15);
        spnMap.addChangeListener(e->onMap());
        add(ribbon, spnMap, "vip.map", false);

        // Cell spinner
        spnCell = createSpinner(0, 65535);
        spnCell.addChangeListener(e->onCell());
        add(ribbon, spnCell, "vip.cell", false);

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

        // Calculate the cell by address
        if (address >= 0x00020000 && address <= 0x0003FFFF)
            setIndex(address - 0x00020000 >> 1, true);
        else
            setIndex(index, true);
    }

    // Canvas click
    private void onCanvas(MouseEvent e) {
        if (e.getButton() > 1)
            return;

        // Retrieve UI properties
        boolean grid  = chkShowGrid.isSelected();
        int     map   = index >> 12;
        int     scale = sldScale.getValue();
        int     size  = scale << 3 | (grid ? 1 : 0);
        int     x     = e.getX();
        int     y     = e.getY();

        // Select the index of the clicked cell
        if (
            x >= 0 && x < 64 * size &&
            y >= 0 && y < 64 * size
        ) setIndex((index & ~4095) | y / size * 64 + x / size, true);
    }

    // Cell spinner
    private void onCell() {
        if (!updating)
            setIndex((Integer) spnCell.getValue(), true);
    }

    // Map spinner
    private void onMap() {
        if (!updating)
            setIndex((Integer) spnMap.getValue() << 12 | index & 4095, true);
    }

    // Canvas paint
    void onPaint(Graphics g) {
        Rectangle bounds  = g.getClipBounds();
        boolean   generic = chkGenericColors.isSelected();
        boolean   grid    = chkShowGrid.isSelected();
        int       scale   = sldScale.getValue();
        int       size    = scale << 3 | (grid ? 1 : 0);

        // Draw all visible cells
        for (int z = 0; z < 4096; z++) {
            int x = (z & 63) * size;
            int y = (z >> 6) * size;

            // The cell is not visible
            if (
                x <= bounds.x - size         ||
                x >= bounds.x + bounds.width ||
                y <= bounds.y - size         ||
                y >= bounds.y + bounds.height
            ) continue;

            // Draw the character
            decode(index & ~4095 | z);
            paintChr(g, character, x, y, hFlip, vFlip, scale,
                generic ? -1 : Debugger.BG0 + palette);

            // Draw the selection indicator
            if (z != (index & 4095))
                continue;
            g.setColor(config.getColor("selected", 0.5f));
            g.fillRect(x, y, scale << 3, scale << 3);
        }

    }

    // Pattern box paint
    private void onPaintPattern(Graphics g) {
        decode(index);
        paintChr(g, character, 0, 0, hFlip, vFlip, 16,
            chkGenericColors.isSelected() ? -1 : Debugger.BG0 + palette);
    }

    // Scale slider
    void onScale(int scale) {
        if (updating)
            return;

        // UI management for child-less canvas component
        boolean grid = chkShowGrid.isSelected();
        int     size = scale << 3 | (grid ? 1 : 0);
        canvas.setPreferredSize(new Dimension(64 * size, 64 * size));
        canvas.revalidate();
        canvas.repaint();

        // Scroll bar increment amounts
        setCanvasIncrement(size, size);
    }

    // Cell property changed
    private void onTweak() {
        if (updating)
            return;

        // Update the data element in VIP memory
        write(address,
            (Integer) spnCharacter.getValue()    |
            (chkVFlip.isSelected() ? 0x1000 : 0) |
            (chkHFlip.isSelected() ? 0x2000 : 0) |
            Math.max(0, cmbPalette.getSelectedIndex()) << 14
        );

        // Commit the edit to the emulation state and update debugger
        parent.getVUE().write(address, VUE.U16,
            parent.vipMemory, address, 2, false);
        parent.refresh(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Decode one data element from VIP memory
    private void decode(int index) {
        address   = 0x00020000 + (index << 1);
        int bits  = read(address);
        character = bits & 0x07FF;
        vFlip     = (bits & 0x1000) != 0;
        hFlip     = (bits & 0x2000) != 0;
        palette   = bits >> 14 & 3;
    }

    // Specify a new index for the current cell
    private void setIndex(int index, boolean redraw) {

        // Update instance fields
        this.index = index;
        decode(index);

        // Update components
        updating = true;
        spnMap.setValue(index >> 12);
        spnCell.setValue(index);
        txtAddress.setText(String.format("%08X", address));
        spnCharacter.setValue(character);
        cmbPalette.setSelectedIndex(palette);
        chkHFlip.setSelected(hFlip);
        chkVFlip.setSelected(vFlip);
        updating = false;

        // Update display
        if (!redraw)
            return;
        canvas.repaint();
        panPattern.repaint();
    }

}
