package debugger;

// Java imports
import java.awt.*;
import javax.swing.*;

// Package imports
import debugger.*;
import util.*;

// Interface for VIP frame buffers
class VIPBufferPane extends VIPPanel {

    // Instance fields
    private int index; // Current buffer index

    // Top-level components
    private JSpinner   spnIndex; // Index spinner
    private JTextField txtLeft;  // Left text box
    private JTextField txtRight; // Right text box

    // Display components
    private JCheckBox chkLeft;          // Left check box
    private JCheckBox chkRight;         // Right check box
    private JCheckBox chkGenericColors; // Generic colors check box



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    VIPBufferPane(Debugger parent) {
        super(parent);

        // Configure instance fields
        index = 0;

        // Configure interface
        initTopLevel();
        initDisplay();

        // Initialize components
        updating = false;
        setIndex(index, false);
        onScale(sldScale.getValue());
    }

    // Set up the Display interface components
    private void initDisplay() {
        JPanel grpDisplay = createGroup("vip.display", true);

        // Scale slider
        sldScale.setValue(2);
        add(grpDisplay, sldScale, "vip.scale", false);

        // Left check box
        chkLeft = createCheckBox("common.left");
        chkLeft.setSelected(true);
        chkLeft.addActionListener(e->{ if (!updating) refresh(); });
        add(grpDisplay, chkLeft, false);

        // Right check box
        chkRight = createCheckBox("common.right");
        chkRight.setSelected(true);
        chkRight.addActionListener(e->{ if (!updating) refresh(); });
        add(grpDisplay, chkRight, true);

        // Generic colors check box
        chkGenericColors = createCheckBox("vip.generic_colors");
        chkGenericColors.addActionListener(e->{ if (!updating) refresh(); });
        add(grpDisplay, chkGenericColors, false, false);
    }

    // Set up the top-level interface components
    private void initTopLevel() {

        // Index spinner
        spnIndex = createSpinner(0, 1);
        spnIndex.addChangeListener(e->onIndex());
        add(ribbon, spnIndex, "common.index", false);

        // Left text box
        Util.FocusHandler onBlur = e->onAddress((JTextField) e.getSource());
        txtLeft = createTextBox(8);
        txtLeft.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtLeft, null, onBlur);
        add(ribbon, txtLeft, "common.left", false);

        // Right text box
        txtRight = createTextBox(8);
        txtRight.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtRight, null, onBlur);
        add(ribbon, txtRight, "common.right", false);
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
    private void onAddress(JTextField box) {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int address = 0;
        try { address = (int) Long.parseLong(box.getText(), 16); }
        catch (Exception e) { }

        // Calculate the index by address -- either text box works
        if (     address >= 0x00000000 && address <= 0x00005FFF ||
                 address >= 0x00008000 && address <= 0x0000DFFF)
            setIndex(0, true);
        else if (address >= 0x00010000 && address <= 0x00015FFF ||
                 address >= 0x00018000 && address <= 0x0001DFFF)
            setIndex(1, true);
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

        // Draw the world from VIP memory
        parent.clearBuffer();
        parent.drawBuffer(index, left, top, right, bottom);
        parent.paintBuffer(left, top, right, bottom,
            scale, chkLeft.isSelected(), chkRight.isSelected(), generic);
        g.drawImage(parent.image, 0, 0, 384 * scale, 224 * scale, null);
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



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Specify a new index for the current buffer
    private void setIndex(int index, boolean redraw) {

        // Update instance fields
        this.index = index;

        // Update components
        updating = true;
        spnIndex.setValue(index);
        txtLeft.setText (String.format("%08X", 0x00000000 | index << 15));
        txtRight.setText(String.format("%08X", 0x00010000 | index << 15));
        updating = false;
        
        // Redraw display
        if (redraw)
            canvas.repaint();
    }

}
