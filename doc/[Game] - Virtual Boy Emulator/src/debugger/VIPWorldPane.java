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

// Interface for VIP worlds
class VIPWorldPane extends VIPPanel {

    // Instance fields
    private int index; // Current world index
    private int param; // Current parameter index

    // Data element fields
    private int     address;        // Location in VIP memory
    private int     bgHeight;       // Height of background in maps
    private int     bgMapBase;      // First BG map in the world's background
    private int     bgWidth;        // Width of background in maps
    private int     destParallax;   // Destination stereoscopic disparity
    private int     destX;          // Horizontal destination coordinate
    private int     destY;          // Vertical destination coordinate
    private boolean end;            // Control world
    private int     height;         // Height in pixels
    private boolean left;           // Display in left frame buffer
    private int     mode;           // Rendering style
    private int     overCharacter;  // Overplane character index
    private boolean overplane;      // Use the overplane character
    private int     paramAddress;   // Location in VIP memory of current param
    private int     paramBase;      // Address base of parameters
    private boolean right;          // Display in right frame buffer
    private int     srcParallax;    // Source stereoscopic disparity
    private int     srcX;           // Horizontal source coordinate
    private int     srcY;           // Vertical source coordinate
    private int     width;          // Width in pixels

    // H-bias data element fields
    int leftBias;  // Left horizontal offset
    int rightBias; // Right horizontal offset

    // Affine data element fields
    int deltaX;       // Traversal vector horizontal change amount
    int deltaY;       // Traversal vector vertical change amount
    int srcParallaxA; // Source stereoscopic disparity
    int srcXA;        // Initial horizontal source coordinate
    int srcYA;        // Initial vertical source coordinate

    // Top-level components
    private JSpinner   spnIndex;   // Index spinner
    private JTextField txtAddress; // Address text box

    // Affine components
    private JSpinner   spnDeltaX;       // Delta X spinner
    private JSpinner   spnDeltaY;       // Delta Y spinner
    private JSpinner   spnIndexA;       // Affine index spinner
    private JSpinner   spnSrcParallaxA; // Affine src parallax spinner
    private JSpinner   spnSrcXA;        // Affine src X spinner
    private JSpinner   spnSrcYA;        // Affine src Y spinner
    private JTextField txtAddressA;     // Affine address text box

    // H-bias components
    private JSpinner   spnLeft;     // Left spinner
    private JSpinner   spnIndexH;   // H-bias index spinner
    private JSpinner   spnRight;    // Right spinner
    private JTextField txtAddressH; // H-bias address text box

    // Objects components
    private JLabel lblEnd;   // End label
    private JLabel lblGroup; // Group label
    private JLabel lblStart; // Start label

    // Properties components
    private JCheckBox  chkEnd;          // End check box
    private JCheckBox  chkLeft;         // Left check box
    private JCheckBox  chkOverplane;    // Overplane check box
    private JCheckBox  chkRight;        // Right check box
    private JComboBox  cmbBGHeight;     // BG height combo box
    private JComboBox  cmbBGWidth;      // BG width combo box
    private JComboBox  cmbMode;         // Mode combo box
    private JPanel     grpAffine;       // Affine group box
    private JPanel     grpHBias;        // H-bias group box
    private JPanel     grpObjects;      // Objects group box
    private JSpinner   spnDestParallax; // Dest parallax spinner
    private JSpinner   spnDestX;        // Dest X spinner
    private JSpinner   spnDestY;        // Dest Y spinner
    private JSpinner   spnHeight;       // Height spinner
    private JSpinner   spnMapBase;      // Map base spinner
    private JSpinner   spnOverplane;    // Overplane spinner
    private JSpinner   spnSrcParallax;  // Src parallax spinner
    private JSpinner   spnSrcX;         // Src X spinner
    private JSpinner   spnSrcY;         // Src Y spinner
    private JSpinner   spnWidth;        // Width spinner
    private JTextField txtParams;       // Params text box

    // Display components
    private JCheckBox chkGenericColors; // Generic colors check box



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // World modes
    private static final int AFFINE = 2;
    private static final int HBIAS  = 1;
    private static final int NORMAL = 0;
    private static final int OBJECT = 3;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    VIPWorldPane(Debugger parent) {
        super(parent);

        // Configure instance fields
        index = 31;
        param = 0;

        // Configure interface
        initTopLevel();
        initProperties();
        initAffine();
        initHBias();
        initObjects();
        initDisplay();

        // Initialize components
        updating = false;
        setIndex(index, false);
        setParam(param);
        onScale(sldScale.getValue());
    }

    // Set up the Affine interface components
    private void initAffine() {
        grpAffine = createGroup("vip.affine", false);

        // Index spinner
        spnIndexA = createSpinner(0, 0);
        spnIndexA.addChangeListener(e->onIndexA());
        add(grpAffine, spnIndexA, "common.index", false);

        // Address text box
        txtAddressA = createTextBox(8);
        txtAddressA.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtAddressA, null, e->onAddressA());
        add(grpAffine, txtAddressA, "common.address", false);

        // Src X spinner
        spnSrcXA = createSpinner(-4096.0, 4095.875);
        spnSrcXA.addChangeListener(e->onTweakA());
        add(grpAffine, spnSrcXA, "vip.src_x", false);

        // Src Y spinner
        spnSrcYA = createSpinner(-4096.0, 4095.875);
        spnSrcYA.addChangeListener(e->onTweakA());
        add(grpAffine, spnSrcYA, "vip.src_y", false);

        // Src parallax spinner
        spnSrcParallaxA = createSpinner(-32768, 32767);
        spnSrcParallaxA.addChangeListener(e->onTweakA());
        add(grpAffine, spnSrcParallaxA, "vip.src_parallax", false);

        // Delta X spinner
        spnDeltaX = createSpinner(-64.0, 63.998046875);
        spnDeltaX.addChangeListener(e->onTweakA());
        add(grpAffine, spnDeltaX, "vip.delta_x", false);

        // Delta Y spinner
        spnDeltaY = createSpinner(-64.0, 63.998046875);
        spnDeltaY.addChangeListener(e->onTweakA());
        add(grpAffine, spnDeltaY, "vip.delta_y", false);
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

    // Set up the H-bias interface components
    private void initHBias() {
        grpHBias = createGroup("vip.hbias", false);

        // Index spinner
        spnIndexH = createSpinner(0, 0);
        spnIndexH.addChangeListener(e->onIndexH());
        add(grpHBias, spnIndexH, "common.index", false);

        // Address text box
        txtAddressH = createTextBox(8);
        txtAddressH.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtAddressH, null, e->onAddressH());
        add(grpHBias, txtAddressH, "common.address", false);

        // Left spinner
        spnLeft = createSpinner(-4096, 4095);
        spnLeft.addChangeListener(e->onTweakH());
        add(grpHBias, spnLeft, "common.left", false);

        // Right spinner
        spnRight = createSpinner(-4096, 4095);
        spnRight.addChangeListener(e->onTweakH());
        add(grpHBias, spnRight, "common.right", false);
    }

    // Set up the Objects interface components
    private void initObjects() {
        grpObjects = createGroup("vip.objects", false);

        // Group label
        lblGroup = new JLabel("0");
        add(grpObjects, lblGroup, "vip.group", false);

        // Start label
        lblStart = new JLabel("0");
        add(grpObjects, lblStart, "common.start", false);

        // End label
        lblEnd = new JLabel("0");
        add(grpObjects, lblEnd, "common.end", false);
    }

    // Set up the Properties interface components
    private void initProperties() {
        JPanel grpProperties = createGroup("common.properties", false);

        // Map base spinner
        spnMapBase = createSpinner(0, 15);
        spnMapBase.addChangeListener(e->onTweak());
        add(grpProperties, spnMapBase, "vip.map_base", false);

        // BG width combo box
        cmbBGWidth = createComboBox(new String[] {
            "!1",  "!2",  "!4",  "!8" });
        cmbBGWidth.addActionListener(e->onTweak());
        add(grpProperties, cmbBGWidth, "vip.bg_width", false);

        // BG height combo box
        cmbBGHeight = createComboBox(new String[] {
            "!1",  "!2",  "!4",  "!8" });
        cmbBGHeight.addActionListener(e->onTweak());
        add(grpProperties, cmbBGHeight, "vip.bg_height", false);

        // Dest X spinner
        spnDestX = createSpinner(-512, 511);
        spnDestX.addChangeListener(e->onTweak());
        add(grpProperties, spnDestX, "vip.dest_x", false);

        // Dest Y spinner
        spnDestY = createSpinner(-32768, 32767);
        spnDestY.addChangeListener(e->onTweak());
        add(grpProperties, spnDestY, "vip.dest_y", false);

        // Dest parallax spinner
        spnDestParallax = createSpinner(-512, 511);
        spnDestParallax.addChangeListener(e->onTweak());
        add(grpProperties, spnDestParallax, "vip.dest_parallax", false);

        // Src X spinner
        spnSrcX = createSpinner(-4096, 4095);
        spnSrcX.addChangeListener(e->onTweak());
        add(grpProperties, spnSrcX, "vip.src_x", false);

        // Src Y spinner
        spnSrcY = createSpinner(-32768, 32767);
        spnSrcY.addChangeListener(e->onTweak());
        add(grpProperties, spnSrcY, "vip.src_y", false);

        // Src parallax spinner
        spnSrcParallax = createSpinner(-4096, 4095);
        spnSrcParallax.addChangeListener(e->onTweak());
        add(grpProperties, spnSrcParallax, "vip.src_parallax", false);

        // Width spinner
        spnWidth = createSpinner(-4095, 4096);
        spnWidth.addChangeListener(e->onTweak());
        add(grpProperties, spnWidth, "vip.width", false);

        // Height spinner
        spnHeight = createSpinner(-32767, 32768);
        spnHeight.addChangeListener(e->onTweak());
        add(grpProperties, spnHeight, "vip.height", false);

        // Mode combo box
        cmbMode = createComboBox(new String[] {
            "vip.normal",  "vip.hbias",  "vip.affine",  "vip.object" });
        cmbMode.addActionListener(e->onTweak());
        add(grpProperties, cmbMode, "vip.mode", false);

        // Params text box
        txtParams = createTextBox(8);
        txtParams.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtParams, null, e->onParams());
        add(grpProperties, txtParams, "vip.params", false);

        // Overplane spinner
        spnOverplane = createSpinner(0, 65535);
        spnOverplane.addChangeListener(e->onTweak());
        add(grpProperties, spnOverplane, "vip.overplane", false);

        // Left check box
        chkLeft = createCheckBox("common.left");
        chkLeft.addActionListener(e->onTweak());
        add(grpProperties, chkLeft, false);

        // Right check box
        chkRight = createCheckBox("common.right");
        chkRight.addActionListener(e->onTweak());
        add(grpProperties, chkRight, true);

        // End check box
        chkEnd = createCheckBox("vip.end");
        chkEnd.addActionListener(e->onTweak());
        add(grpProperties, chkEnd, false);

        // Overplane check box
        chkOverplane = createCheckBox("vip.overplane");
        chkOverplane.addActionListener(e->onTweak());
        add(grpProperties, chkOverplane, true);
    }

    // Set up the top-level interface components
    private void initTopLevel() {

        // Index spinner
        spnIndex = createSpinner(0, 31);
        spnIndex.addChangeListener(e->onIndex());
        add(ribbon, spnIndex, "common.index", false);

        // Address text box
        txtAddress = createTextBox(8);
        txtAddress.addActionListener(e->ribbon.requestFocus());
        Util.onFocus(txtAddress, null, e->onAddress());
        add(ribbon, txtAddress, "common.address", false);
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
        if (address >= 0x0003D800 && address <= 0x0003DBFF)
            setIndex(address - 0x0003D800 >> 5, true);
        else
            setIndex(index, true);
    }

    // Affine address text box
    private void onAddressA() {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int address = 0;
        try { address = (int) Long.parseLong(txtAddressA.getText(), 16); }
        catch (Exception e) { }

        // Calculate the index by address
        if (address >= 0x00020000 && address < 0x00020000 + (height << 4))
            setParam(address - 0x00020000 >> 4);
        else
            setParam(param);
    }

    // H-bias address text box
    private void onAddressH() {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int address = 0;
        try { address = (int) Long.parseLong(txtAddressH.getText(), 16); }
        catch (Exception e) { }

        // Calculate the index by address
        if (address >= 0x00020000 && address < 0x00020000 + (height << 2))
            setParam(address - 0x00020000 >> 2);
        else
            setParam(param);
    }

    // Index spinner
    private void onIndex() {
        if (!updating)
            setIndex((Integer) spnIndex.getValue(), true);
    }

    // Affine index spinner
    private void onIndexA() {
        if (!updating)
            setParam((Integer) spnIndexA.getValue());
    }

    // H-bias index spinner
    private void onIndexH() {
        if (!updating)
            setParam((Integer) spnIndexH.getValue());
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
        parent.drawWorld(index, left, top, right, bottom, generic);
        parent.paintBuffer(left, top, right, bottom,
            scale, true, true, generic);
        g.drawImage(parent.image, 0, 0, 384 * scale, 224 * scale, null);
    }

    // Params text box
    private void onParams() {
        if (updating)
            return;

        // Attempt to process the text entered by the user
        int address = 0;
        try {
            address = (int) Long.parseLong(txtParams.getText(), 16);
        } catch (Exception e) {
            address = 0;
        }

        // Check if the address is in range
        if (address < 0x00020000 || address > 0x0003FFFF) {
            refresh();
            return;
        }

        // Update the value in VIP memory and commit the edit
        parent.getVUE().write(this.address | 18, VUE.U16,
            address >> 1 & 0xFFFF, false);
        parent.refresh(false);
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

    // World property changed
    private void onTweak() {
        if (updating)
            return;

        // Update the data element in VIP memory
        write(address,
            (Integer) spnMapBase.getValue()          |
            (chkEnd      .isSelected() ? 0x0040 : 0) |
            (chkOverplane.isSelected() ? 0x0080 : 0) |
            cmbBGHeight.getSelectedIndex() <<  8     |
            cmbBGWidth .getSelectedIndex() << 10     |
            cmbMode    .getSelectedIndex() << 12     |
            (chkRight    .isSelected() ? 0x4000 : 0) |
            (chkLeft     .isSelected() ? 0x8000 : 0)
        );
        write(address |  2, (Integer) spnDestX       .getValue());
        write(address |  4, (Integer) spnDestParallax.getValue());
        write(address |  6, (Integer) spnDestY       .getValue());
        write(address |  8, (Integer) spnSrcX        .getValue());
        write(address | 10, (Integer) spnSrcParallax .getValue());
        write(address | 12, (Integer) spnSrcY        .getValue());
        write(address | 14, (Integer) spnWidth       .getValue() - 1);
        write(address | 16, (Integer) spnHeight      .getValue() - 1);
        write(address | 20, (Integer) spnOverplane   .getValue());

        // Commit the edit to the emulation state and update debugger
        parent.getVUE().write(address, VUE.U16,
            parent.vipMemory, address, 32, false);
        parent.refresh(false);
    }

    // Affine property changed
    private void onTweakA() {
        if (updating)
            return;

        // Update the data element in VIP memory
        write(paramAddress    , (int) Math.round(
            (Double) spnSrcXA .getValue() *   8));
        write(paramAddress | 2, (Integer) spnSrcParallaxA.getValue());
        write(paramAddress | 4, (int) Math.round(
            (Double) spnSrcYA .getValue() *   8));
        write(paramAddress | 6, (int) Math.round(
            (Double) spnDeltaX.getValue() * 512));
        write(paramAddress | 8, (int) Math.round(
            (Double) spnDeltaY.getValue() * 512));

        // Commit the edit to the emulation state and update debugger
        parent.getVUE().write(paramAddress, VUE.U16,
            parent.vipMemory, paramAddress, 16, false);
        parent.refresh(false);
    }

    // H-bias property changed
    private void onTweakH() {
        if (updating)
            return;

        // Update the data element in VIP memory
        write(paramAddress    , (Integer) spnLeft .getValue());
        write(paramAddress | 2, (Integer) spnRight.getValue());

        // Commit the edit to the emulation state and update debugger
        parent.getVUE().write(paramAddress, VUE.U16,
            parent.vipMemory, paramAddress, 4, false);
        parent.refresh(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Decode one data element from VIP memory
    private void decode() {
        address       = 0x0003D800 + (index << 5);
             int bits = read(address);
        bgMapBase     =  bits & 0x000F;
        end           = (bits & 0x0040) != 0;
        overplane     = (bits & 0x0080) != 0;
        bgWidth       =  bits >>  8 & 3;
        bgHeight      =  bits >> 10 & 3;
        mode          =  bits >> 12 & 3;
        right         = (bits & 0x4000) != 0;
        left          = (bits & 0x8000) != 0;
        destX         = read(address |  2) << 22 >> 22;
        destParallax  = read(address |  4) << 22 >> 22;
        destY         = read(address |  6);
        srcX          = read(address |  8) << 19 >> 19;
        srcParallax   = read(address | 10) << 17 >> 17;
        srcY          = read(address | 12) << 19 >> 19;
            bits      = read(address | 14);
        width         = (mode != AFFINE ? bits<<19>>19 : bits<<22>>22) + 1;
        height        = read(address | 16) + 1;
        paramBase     = read(address | 18) & 0xFFFF;
        overCharacter = read(address | 20) & 0xFFFF;
    }

    // Decode one affine data element from VIP memory
    private void decodeA() {
        paramAddress = 0x00020000 + ((paramBase + (param << 3) & 0xFFFF) << 1);
        srcXA        = read(paramAddress    );
        srcParallaxA = read(paramAddress | 2);
        srcYA        = read(paramAddress | 4);
        deltaX       = read(paramAddress | 6);
        deltaY       = read(paramAddress | 8);
    }

    // Decode one H-bias data element from VIP memory
    private void decodeH() {
        paramAddress = 0x00020000 + ((paramBase + (param << 1) & 0xFFFF) << 1);
        leftBias     = read(paramAddress    ) << 19 >> 19;
        rightBias    = read(paramAddress | 2) << 19 >> 19;
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
        spnMapBase.setValue(bgMapBase);
        cmbBGWidth.setSelectedIndex(bgWidth);
        cmbBGHeight.setSelectedIndex(bgHeight);
        spnDestX.setValue(destX);
        spnDestY.setValue(destY);
        spnDestParallax.setValue(destParallax);
        spnSrcX.setValue(srcX);
        spnSrcY.setValue(srcY);
        spnSrcParallax.setValue(srcParallax);
        spnWidth.setValue(width);
        spnHeight.setValue(height);
        cmbMode.setSelectedIndex(mode);
        txtParams.setText(String.format("%08X", 0x00020000+(paramBase << 1)));
        spnOverplane.setValue(overCharacter);
        chkLeft.setSelected(left);
        chkRight.setSelected(right);
        chkEnd.setSelected(end);
        chkOverplane.setSelected(overplane);
        updating = false;
        setParam(param);

        // Redraw display
        if (redraw)
            canvas.repaint();
    }

    // Specify a new index for the current parameter
    private void setParam(int param) {

        // Update group box visibility
        grpAffine .setVisible(height > 0 && mode == AFFINE);
        grpHBias  .setVisible(height > 0 && mode == HBIAS );
        grpObjects.setVisible(              mode == OBJECT);
        onResize();

        // Update instance fields
        if (height > 0) {
            this.param = Math.min(param, height - 1);
            switch (mode) {
                case AFFINE: decodeA(); break;
                case HBIAS:  decodeH(); break;
            }
        }

        // Update components
        updating = true;

        // Update H-bias components
        if (height > 0 && mode == HBIAS) {
            ((SpinnerNumberModel) spnIndexH.getModel())
                .setMaximum(height - 1);
            spnIndexH.setValue(param);
            txtAddressH.setText(String.format("%08X", paramAddress));
            spnLeft .setValue(leftBias );
            spnRight.setValue(rightBias);
        }

        // Update affine components
        else if (height > 0 && mode == AFFINE) {
            ((SpinnerNumberModel) spnIndexA.getModel())
                .setMaximum(height - 1);
            spnIndexA.setValue(param);
            txtAddressA.setText(String.format("%08X", paramAddress));
            spnSrcXA.setValue(srcXA / 8.0);
            spnSrcYA.setValue(srcYA / 8.0);
            spnSrcParallaxA.setValue(srcParallaxA);
            spnDeltaX.setValue(deltaX / 512.0);
            spnDeltaY.setValue(deltaY / 512.0);
        }

        // Update Object components
        else if (mode == OBJECT) {

            // Retrieve object group information
            int group = parent.getObjGroup(index);
            int start = parent.getObjStart(group);
            int end   = parent.objPointers[group];

            // Update components
            lblGroup.setText("" + group);
            lblStart.setText("" + start);
            lblEnd  .setText("" + end  );
        }

        updating = false;
    }

}
