package debugger;

// Java imports
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

// Project imports
import config.*;
import debugger.*;
import util.*;
import vue.*;

// Interface for VIP characters
class VIPChrPane extends VIPPanel {

    // Instance fields
    private int color;   // Selected color
    private int index;   // Current character
    private int palette; // Selected palette

    // Top-level components
    private JPanel     panPattern; // Pixel pattern display
    private JSpinner   spnIndex;   // Index spinner
    private JTextField txtAddress; // Address text box
    private JTextField txtMirror;  // Mirror text box

    // Colors components
    private JPanel[] panColors; // Color picker selections

    // Display components
    private JCheckBox chkShowGrid; // Show Grid check box
    private ComboBox  cmbPalette;  // Palette combo box



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Border decorations for palette color picker
    private static Border bdrEmpty       = new EmptyBorder(3, 3, 3, 3);
    private static Border bdrEmptyCenter = new EmptyBorder(3, 1, 3, 1);
    private static Border bdrEmptyLeft   = new EmptyBorder(3, 3, 3, 1);
    private static Border bdrEmptyRight  = new EmptyBorder(3, 1, 3, 3);
    private static Border bdrSelected    = null; // Initialized in constructor

    // Palette indexes
    private static final int GENERIC = -1;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    VIPChrPane(Debugger parent) {
        super(parent);

        // Configure instance fields
        color   = 0;
        index   = 0;
        palette = GENERIC;

        // Configure UI
        initTopLevel();
        initColors();
        initDisplay();

        // Initialize components
        updating = false;
        onScale(sldScale.getValue());
        setPalette(palette, false);
        setIndex(index, false);
    }

    // Set up the Colors interface components
    private void initColors() {
        JPanel grpColors = createGroup("vip.colors", false);

        // Palette combo box
        cmbPalette = createComboBox(new String[] {
            "vip.generic",
            "!BG 0",  "!BG 1",  "!BG 2",  "!BG 3",
            "!OBJ 0", "!OBJ 1", "!OBJ 2", "!OBJ 3"
        });
        cmbPalette.addActionListener(e->{ if (!updating) onPalette(); });
        add(grpColors, cmbPalette, "vip.palette", false);
        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        // Configure border decorations
        if (bdrSelected == null)
        bdrSelected = new CompoundBorder(
            new LineBorder (config.getColor("selected"), 2),
            new EmptyBorder(1, 1, 1, 1)
        );

        // Configure all color boxes
        panColors = new JPanel[4];
        for (int x = 0; x < panColors.length; x++) {
            JPanel col = panColors[x] = new JPanel(new BorderLayout());

            // A child component is required for insets to work properly
            JPanel inner = new JPanel(null);
            inner.setBackground(Color.RED);
            inner.setOpaque(true);
            inner.setPreferredSize(new Dimension(24, 24));
            inner.putClientProperty("index", x);
            col.putClientProperty("inner", inner);
            Util.onMouse(inner, e->setColor(e.getSource()), null);

            // Configure outer element
            col.setBorder(x == 0 ? bdrEmpty : bdrEmptyRight);
            col.add(inner);

            // Add the color to the selector
            container.add(col, BorderLayout.CENTER);
        }

        // Add the selector to the ribbion
        add(grpColors, container, true, false);
        setColor(color);
    }

    // Set up the Display interface components
    private void initDisplay() {
        JPanel grpDisplay = createGroup("vip.display", true);

        // Scale slider
        sldScale.setValue(3);
        add(grpDisplay, sldScale, "vip.scale", false);

        // Show grid check box
        chkShowGrid = createCheckBox("vip.show_grid");
        chkShowGrid.setSelected(true);
        chkShowGrid.addActionListener(e->
            { if (!updating) onScale(sldScale.getValue()); });
        add(grpDisplay, chkShowGrid, false, false);
    }

    // Set up the top-level interface components
    private void initTopLevel() {

        // Canvas
        Util.onMouse(canvas, e->onCanvas(e), null);
        Util.onMouseMove(canvas, null, e->onCanvas(e));

        // Index spinner
        spnIndex = createSpinner(0, 2047);
        spnIndex.addChangeListener(e->onIndex());
        add(ribbon, spnIndex, "common.index", false);

        // Address text box
        txtAddress = createTextBox(8);
        txtAddress.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtAddress, null, e->onAddress(txtAddress));
        add(ribbon, txtAddress, "common.address", false);

        // Mirror text box
        txtMirror = createTextBox(8);
        txtMirror.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtMirror, null, e->onAddress(txtMirror));
        add(ribbon, txtMirror, "vip.mirror", false);

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
        Util.onMouse(panPattern, e->onPattern(e), null);
        Util.onMouseMove(panPattern, null, e->onPattern(e));
        outer.add(panPattern, BorderLayout.CENTER);

        // Add the pattern box
        add(ribbon, outer, true, false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update display with the current emulation state
    void refresh() {
        panPattern.repaint();
        canvas.repaint();
        updateColors();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // Address and Mirror text boxes
    private void onAddress(JTextField box) {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int address = 0;
        try { address = (int) Long.parseLong(box.getText(), 16); }
        catch (Exception e) { }

        // Calculate the index by address -- either text box works
        if (address >= 0x00006000 && address <= 0x00007FFF)
            setIndex(address - 0x06000 >> 4, true);
        else if (address >= 0x0000E000 && address <= 0x0000FFFF)
            setIndex(address - 0x0C000 >> 4, true);
        else if (address >= 0x00016000 && address <= 0x00017FFF)
            setIndex(address - 0x12000 >> 4, true);
        else if (address >= 0x0001E000 && address <= 0x0001FFFF)
            setIndex(address - 0x18000 >> 4, true);
        else if (address >= 0x00078000 && address <= 0x0007FFFF)
            setIndex(address - 0x78000 >> 4, true);
        else
            setIndex(index, true);
    }

    // Canvas click
    private void onCanvas(MouseEvent e) {
        if (e.getButton() > 1)
            return;

        // Retrieve UI properties
        boolean grid  = chkShowGrid.isSelected();
        int     scale = sldScale.getValue();
        int     size  = scale << 3 | (grid ? 1 : 0);
        int     x     = e.getX();
        int     y     = e.getY();

        // Select the index of the clicked character
        if (
            x >= 0 && x <  16 * size &&
            y >= 0 && y < 128 * size
        ) setIndex(y / size * 16 + x / size, true);
    }

    // Index spinner
    private void onIndex() {
        if (!updating)
            setIndex((Integer) spnIndex.getValue(), true);
    }

    // Palette combo box
    private void onPalette() {
        setPalette(cmbPalette.getSelectedIndex() - 1, true);
    }

    // Canvas paint
    void onPaint(Graphics g) {
        Rectangle bounds = g.getClipBounds();
        boolean   grid   = chkShowGrid.isSelected();
        int       scale  = sldScale.getValue();
        int       size   = scale << 3 | (grid ? 1 : 0);

        // Draw all visible characters
        for (int z = 0; z < 2048; z++) {
            int x = (z & 15) * size;
            int y = (z >> 4) * size;

            // The character is not visible
            if (
                x <= bounds.x - size         ||
                x >= bounds.x + bounds.width ||
                y <= bounds.y - size         ||
                y >= bounds.y + bounds.height
            ) continue;

            // Draw the character
            paintChr(g, z, x, y, false, false, scale, palette);

            // Draw the selection indicator
            if (z != index)
                continue;
            g.setColor(config.getColor("selected", 0.5f));
            g.fillRect(x, y, scale << 3, scale << 3);
        }

    }

    // Pattern box paint
    private void onPaintPattern(Graphics g) {
        paintChr(g, index, 0, 0, false, false, 16, palette);
    }

    // Pattern box click or drag
    private void onPattern(MouseEvent e) {
        ribbon.requestFocus();
        if (e.getButton() > 1)
            return;

        // The mouse coordinates are not within the pattern image
        int x = e.getX() >> 4;
        int y = e.getY() >> 4;
        if (x < 0 || x >= 8 || y < 0 || y >= 8)
            return;

        // Specify a new pixel value in the character data object
        if (parent.sampleCharacter(index, x, y, false, false) == color)
            return;
        drawChr(index, x, y, color);

        // Commit the edit to the emulation state and update debugger
        int address = chrAddress(index);
        parent.getVUE().write(address, VUE.U16,
            parent.vipMemory, address, 16, false);
        parent.refresh(false);
    }

    // Scale slider
    void onScale(int scale) {
        if (updating)
            return;

        // UI management for child-less canvas component
        boolean grid = chkShowGrid.isSelected();
        int     size = scale << 3 | (grid ? 1 : 0);
        canvas.setPreferredSize(new Dimension(16 * size, 128 * size));
        canvas.revalidate();
        canvas.repaint();

        // Scroll bar increment amounts
        setCanvasIncrement(size, size);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Specify a new drawing color by index
    private void setColor(int color) {

        // Update color picker display
        for (int x = 0; x < panColors.length; x++) {
            JPanel col = panColors[x];

            // Select the appropriate border for the current color
            col.setBorder(
                x == color ? bdrSelected :
                x == 0 ? color == 1 ? bdrEmptyLeft : bdrEmpty :
                x == color - 1 ? bdrEmptyCenter : bdrEmptyRight
            );

            col.revalidate();
            col.repaint();
        }

        // Update the current drawing color
        this.color = color;
    }

    // Specify a new drawing color by color picker component
    private void setColor(Object source) {
        ribbon.requestFocus();
        setColor((int) (Integer) ((JPanel) source).getClientProperty("index"));
    }

    // Specify a new index for the current character
    private void setIndex(int index, boolean redraw) {

        // Update instance fields
        this.index = index;

        // Update components
        updating = true;
        spnIndex.setValue(index);
        txtAddress.setText(String.format("%08X", chrAddress(index)));
        txtMirror.setText(String.format("%08X", 0x00078000 + (index << 4)));
        updating = false;

        // Update display
        if (!redraw)
            return;
        panPattern.repaint();
        canvas.repaint();
    }

    // Specify a new palette for the display
    private void setPalette(int palette, boolean redraw) {

        // Error checking
        if (palette < GENERIC || palette > Debugger.OBJ3)
            palette = GENERIC;

        // Update instance fields
        this.palette = palette;

        // Redraw display
        updateColors();
        if (!redraw)
            return;
        panPattern.repaint();
        canvas.repaint();
    }

    // Update the color picker colors with the current palette
    private void updateColors() {
        boolean generic = palette == GENERIC;
        int[]   pal     = generic ?
            parent.GENERIC_PALETTE : parent.palettes[palette];
        int[]   reds    = parent.colors[generic ?
            Debugger.GENERIC : Debugger.SCALED][Config.RED];

        // Update color picker
        int invisible = config.getInvisibleColor();
        for (int x = 0; x < 4; x++)
            ((JPanel) panColors[x].getClientProperty("inner")).setBackground(
                new Color(pal[x] == -1 ? invisible : reds[pal[x]], true));
    }

}
