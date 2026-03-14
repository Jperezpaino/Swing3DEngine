package debugger;

// Java imports
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Project imports
import config.*;
import debugger.*;
import util.*;
import vue.*;

// Disassembler UI for navigating around program instructions
class CPUDasmPane extends JScrollPane {

    // Instance fields
    private int      address;   // Address of first output instruction
    private Debugger parent;    // Top-level UI manager
    private boolean  showBytes; // Display the bytes column
    private int[]    widths;    // Column widths in pixels
    private ArrayList<CPUDasmLine> lines; // Rows of output

    // UI components
    private JPanel client; // Main content pane



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    CPUDasmPane(Debugger parent) {
        super(VERTICAL_SCROLLBAR_NEVER, HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Configure instance fields
        address     = 0;
        lines       = new ArrayList<CPUDasmLine>();
        this.parent = parent;
        showBytes   = true;
        widths      = new int[5];

        // Configure content pane
        client = new JPanel(null);
        client.setBackground(parent.getConfig().getColor("window"));
        client.setFocusable(true);
        Util.onFocus (client, e->onFocus(), e->onFocus());
        Util.onKey   (client, e->onKeyDown(e), null);
        Util.onMouse (client, e->requestFocus(), null);
        Util.onResize(client, e->onResize());
        client.addMouseWheelListener(e->onMouseWheel(e));

        // Configure component
        setViewportView(client);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                               Overrides                               //
    ///////////////////////////////////////////////////////////////////////////

    // Determine focus on the contents panel instead of the scroll pane
    public boolean isFocusOwner() {
        return client != null && client.isFocusOwner();
    }

    // Requsest focus on the contents panel instead of the scroll pane
    public void requestFocus() {
        if (client != null)
            client.requestFocus();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update display with the current emulation state
    void refresh(boolean seekToPC) {
        refresh(seekToPC, parent.getVUE().getProgramCounter(),
            getVisible(true) / 3);
    }

    // Relocate the display to a new address
    void seek(int address) {
        refresh(true, address, getVisible(true) / 3);
    }

    // Specify whether the bytes column is shown
    void showBytesColumn(boolean show) {
        showBytes = show;
        refresh(false, 0, 0);
    }

    // Toggle whether the bytes column is shown
    void toggleBytesColumn() {
        showBytesColumn(!showBytes);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // Focus changed
    private void onFocus() {
        boolean hasFocus   = client.isFocusOwner();
        int     numVisible = getVisible(false);
        for (int x = 0; x < numVisible; x++)
            lines.get(x).setFocus(hasFocus);
    }

    // Key pressed
    private void onKeyDown(KeyEvent e) {
        boolean ctrl = e.isControlDown();
        int     key  = e.getKeyCode();
        int     step = 1;
        int     page = getVisible(true);
        int     move = address;

        // CTRL key is pressed
        if (ctrl) switch (key) {
            case KeyEvent.VK_B: toggleBytesColumn(); break;
            case KeyEvent.VK_G: Goto();              break;
            case KeyEvent.VK_P: refresh(true);       break;
        }

        // CTRL key is not pressed
        else switch (key) {
            case KeyEvent.VK_DOWN:      move  = findAddress(move, -step);break;
            case KeyEvent.VK_LEFT:      move -= 2;                       break;
            case KeyEvent.VK_PAGE_DOWN: move  = findAddress(move, -page);break;
            case KeyEvent.VK_PAGE_UP:   move  = findAddress(move,  page);break;
            case KeyEvent.VK_RIGHT:     move += 2;                       break;
            case KeyEvent.VK_UP:        move  = findAddress(move,  step);break;
        }

        // Reposition the display
        if (!ctrl && move != address) {
            address = move;
            refresh(false, 0, 0);
        }

    }

    // Mouse wheel
    private void onMouseWheel(MouseWheelEvent e) {
        int amount = e.getWheelRotation() * e.getScrollAmount();
        if (amount == 0) return;
        address = findAddress(address, -amount);
        refresh(false, 0, 0);
    }

    // Resize
    private void onResize() {
        refresh(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Determine the address of the first row of output relative to the
    // position of the instruction containing a given address
    private int findAddress(int address, int position) {
        int[] buffer = new int[Math.max(0, position) + 1];
        VUE   vue    = parent.getVUE();

        // Find the address of the instruction containing the given address
        for(int check = address - Math.max(0, position) * 4 - 32, x = 0;;) {

            // Record the current instruction address in the circular buffer
            buffer[x] = check;
            x = x == buffer.length - 1 ? 0 : x + 1;

            // The given address is part of the current instruction
            int size = Instruction.getSize(vue.read(check, VUE.U16, false));
            if (check == address || size == 4 && check + 2 == address) {
                address = buffer[x];
                break;
            }

            // Advance to the next instruction
            check += size;
        }

        // Find the address of the first row of output
        while (position++ < 0)
            address += Instruction.getSize(vue.read(address, VUE.U16, false));

        return address;
    }

    // Determine the number of visible rows of output
    private int getVisible(boolean fully) {
        int lineHeight = parent.getConfig().getLineHeight(Config.FIXED);
        return (getHeight() + (fully ? 0 : lineHeight - 1)) / lineHeight;
    }

    // Prompt the user to seek to a new address
    private void Goto() {
        Integer address = parent.Goto(this);
        if (address != null)
            seek(address);
    }

    // Configure UI for disassembly
    // Returns true if the refrence address is fully visible
    private boolean prepare(int reference) {
        int     numFull    = getVisible(true);
        int     numVisible = getVisible(false);
        boolean refVisible = false;
        VUE     vue        = parent.getVUE();
        int     pc         = vue.getProgramCounter();

        // Process all visible rows of output
        int address = this.address;
        for (int x = 0; x < numVisible; x++) {
            CPUDasmLine line;

            // The current line is at the program counter
            if (!refVisible && x < numFull && address == reference)
                refVisible = true;

            // A line object already exists
            if (x < lines.size())
                line = lines.get(x);

            // Create a new line object
            else {
                line = new CPUDasmLine(parent);
                lines.add(line);
                client.add(line);
            }

            // Configure the line object with the current address
            line.setVisible(true);
            line.setAddress(address);
            address += address == pc - 2 ? 2 : line.size;
        }

        // Hide all invisible rows of output
        for (int x = numVisible; x < lines.size(); x++)
            lines.get(x).setVisible(false);

        return refVisible;
    }

    // Update display with the current emulation state
    private void refresh(boolean seekToReference, int reference, int position){
        int numVisible = getVisible(false);
        int pc         = parent.getVUE().getProgramCounter();

        // Prepare the rows of output for updating
        if (!prepare(reference) && seekToReference) {
            address = findAddress(reference, position);
            prepare(reference);
        }

        // Update output and record column widths
        boolean hasFocus = client.isFocusOwner();
        widths[CPUDasmLine.COMMENT] = 0;
        for (int x = 0; x < numVisible; x++) {
            CPUDasmLine line = lines.get(x);
            line.refresh (widths);
            line.setFocus(hasFocus);
        }
        if (!showBytes)
            widths[CPUDasmLine.BYTES] = 0;

        // Determine dimensions of content pane
        int height  = parent.getConfig().getLineHeight(Config.FIXED);
        int spacing = height;
        int width   = 0;
        for (int x = 0; x < widths.length; x++) {
            if (widths[x] == 0) continue;
            if (x > 0) width += spacing;
            width += widths[x];
        }
        client.setPreferredSize(new Dimension(width, 0));
        width = Math.max(width, client.getWidth());

        // Configure component layout
        for (int x = 0; x < numVisible; x++) {
            CPUDasmLine line = lines.get(x);
            line.configLayout(widths, height, spacing);
            line.setBounds(0, x * height, width, height);
        }

        repaint();
    }

}
