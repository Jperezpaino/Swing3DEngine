package debugger;

// Java imports
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

// Project imports
import config.*;
import debugger.*;
import util.*;
import vue.*;

// Simple hex editing UI for manipulating emulation state memory
class CPUHexPane extends JScrollPane {

    // Instance fields
    private int      address;  // Address of top row of output
    private int      edit;     // The hex digit being edited
    private Debugger parent;   // Top-level UI manager
    private Integer  selStart; // First address of selection
    private int      selStop;  // Last address of selection
    private boolean  shown;    // Component has been displayed
    private ArrayList<CPUHexLine> lines; // Rows of output

    // UI components
    private JPanel client;   // Main content pane
    private JLabel editor;   // Displays the byte being edited



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    CPUHexPane(Debugger parent) {
        super();//VERTICAL_SCROLLBAR_NEVER, HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Configure instance fields
        address     = 0x05000000;
        edit        = -1;
        editor      = null;
        lines       = new ArrayList<CPUHexLine>();
        selStart    = null;
        selStop     = 0;
        shown       = false;
        this.parent = parent;

        // Configure content pane
        client = new JPanel(null) {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                if (!shown) preShow(size);
                return size;
            }
        };
        client.setBackground(parent.getConfig().getColor("window"));
        client.setFocusable(true);
        Util.onFocus  (client, e->onFocus(), e->onFocus());
        Util.onKey    (client, e->onKeyDown(e), null);
        Util.onMouse  (client, e->onMouseDown(e), null);
        Util.onResize (client, e->onResize());
        Util.onType   (client, e->onType(e));
        Util.onVisible(client, e->onShow(), null);
        client.addMouseWheelListener(e->onMouseWheel(e));

        // Configure component
        setViewportView(client);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // Focus changed
    private void onFocus() {
        boolean hasFocus   = client.isFocusOwner();
        int     numVisible = getVisible(false);

        // Commit any in-progress edit
        if (edit != -1)
            refresh();

        // Configure lines, recording the editor component
        editor = null;
        for (int x = 0; x < numVisible && x < lines.size(); x++) {
            JLabel editor = lines.get(x).setFocus(hasFocus, selStart, selStop);
            if (this.editor == null)
                this.editor = editor;
        }

    }

    // Key pressed
    private void onKeyDown(KeyEvent e) {
        boolean ctrl   = e.isControlDown();
        int     key    = e.getKeyCode();
        int     move   = 0;
        int     page   = getVisible(true) * 16;
        int     pc     = parent.getVUE().getProgramCounter();
        boolean scroll = selStart == null || Toolkit.getDefaultToolkit()
            .getLockingKeyState(KeyEvent.VK_SCROLL_LOCK);

        // CTRL key is pressed
        if (ctrl) switch (key) {
            case KeyEvent.VK_G: Goto();   break;
            case KeyEvent.VK_P: seek(pc); break;
        }

        // CTRL key is not pressed
        else switch (key) {

            // Navigation
            case KeyEvent.VK_DOWN:      move =              16; break;
            case KeyEvent.VK_LEFT:      move = scroll ? 0 : -1; break;
            case KeyEvent.VK_PAGE_DOWN: move =            page; break;
            case KeyEvent.VK_PAGE_UP:   move =           -page; break;
            case KeyEvent.VK_RIGHT:     move = scroll ? 0 :  1; break;
            case KeyEvent.VK_UP:        move =             -16; break;

            // Deselect selection
            case KeyEvent.VK_ESCAPE:
                if (selStart != null) {
                    commit();
                    selStart = null;
                    refresh();
                }
                break;

            // Commit partial edit
            case KeyEvent.VK_ENTER:
                if (selStart != null && edit != -1)
                    seek(selStart + 1);
                break;
        }

        // Manage navigation
        if (move != 0) {
            if (scroll) { // Move the viewport
                address += move;
                refresh();
            } else {      // Move the cursor
                e.consume();
                seek(selStart + move);
            }
        }

    }

    // Mouse down
    private void onMouseDown(MouseEvent e) {
        int    button     = e.getButton();
        Config config     = parent.getConfig();
        int    hexWidth   = config.getHexWidth();
        int    lineHeight = config.getLineHeight(Config.FIXED);
        int    spacing    = lineHeight;
        int    left       = hexWidth * 15 / 2 + spacing;
        int    right      = left + hexWidth * 23 + spacing;
        int    width      = hexWidth * 24;
        int    x          = e.getX();
        int    y          = e.getY();

        // Transfer focus
        client.requestFocus();

        // Only respond to left clicks
        if (button != MouseEvent.BUTTON1)
            return;

        // Clicked on a left-side byte
        if (x >= left && x < left + width)
            x = (x - left) / (hexWidth * 3);

        // Clicked on a right-side byte
        else if (x >= right && x < right + width)
            x = 8 + (x - right) / (hexWidth * 3);

        // Did not click on a byte
        else return;

        // Navigate to the clicked byte
        seek(address + y / lineHeight * 16 + x);
    }

    // Mouse wheel
    private void onMouseWheel(MouseWheelEvent e) {
        int amount = e.getWheelRotation() * e.getScrollAmount();
        if (amount == 0) return;
        address += amount * 16;
        refresh();
    }

    // Resize
    private void onResize() {
        refresh();
    }

    // Component shown
    private void onShow() {
        shown = true;
        client.revalidate();
    }

    // Key typed
    private void onType(KeyEvent e) {
        boolean ctrl = e.isControlDown();
        char    key  = e.getKeyChar();

        // Ignore CTRL commands
        if (ctrl) return;

        // Process typing depending on the digit
        if (key >= '0' && key <= '9')
            type(key - '0');
        if (key >= 'A' && key <= 'F')
            type(key - 'A' + 10);
        if (key >= 'a' && key <= 'f')
            type(key - 'a' + 10);
    }

    // Component not yet shown
    private void preShow(Dimension size) {
        size.height = parent.getConfig().getLineHeight() * 8;
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
    void refresh() {
        Config config     = parent.getConfig();
        int    height     = config.getLineHeight(Config.FIXED);
        int    hexWidth   = config.getHexWidth();
        int    numVisible = getVisible(false);
        int    spacing    = height;
        int    width      = hexWidth * 54 + spacing * 2;

        // Commit any in-progress edit
        commit();

        // Fetch all of the visible data from the emulation state
        byte[] data = new byte[numVisible * 16];
        parent.getVUE().read(address, VUE.U8, data, 0, data.length, false);

        // Process all lines of visible output
        for (int x = 0, offset = 0; x < numVisible; x++, offset += 16) {
            CPUHexLine line;

            // A line object already exists
            if (x < lines.size()) {
                line = lines.get(x);
                line.setVisible(true);
            }

            // Create a new line object
            else {
                line = new CPUHexLine(parent);
                lines.add(line);
                client.add(line);
            }

            // Configure the line
            line.setData(address + offset, data, offset);
            line.configLayout(hexWidth, height, spacing);
            line.setBounds(0, x * height, width, height);
        }

        // Hide all unused lines
        for (int x = numVisible; x < lines.size(); x++)
            lines.get(x).setVisible(false);

        // Configure UI component
        client.setPreferredSize(new Dimension(width, 0));
        repaint();
        onFocus();
    }

    // Relocate the display to a new address
    void seek(int address) {
        int numFull = getVisible(true);

        // Commit any in-progress edit
        commit();

        // The new address is not in the fully visible output
        if (address >= this.address + numFull * 16 ||
            address < this.address)
            this.address = address -
                (address - this.address < 0 ? 0 : numFull * 16 - 16) & -16;
            
        // Update the selection range
        selStart = address;
        selStop  = address;
        refresh();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Write the current edit back to the emulation state
    private void commit() {

        // No edit is in progress
        if (selStart == null || edit == -1)
            return;

        // Write the value to the emulation state
        editor.putClientProperty("dirty", true);
        parent.getVUE().write(selStart, VUE.U8, edit, true);
        edit = -1;

        // Update debugger
        parent.refresh(false);
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

    // Edit by typing one keystroke
    private void type(int digit) {

        // Take no action if there is no selection
        if (selStart == null)
            return;

        // Establish a new edit
        if (edit == -1) {
            selStop = selStart;
            seek(selStart);
            edit = digit;
            editor.setText(String.format(" %" +
                (parent.getDasm().getHexCase() ? "X" : "x"), digit));
        }

        // Complete an edit
        else {
            edit = edit << 4 | digit;
            commit();
            seek(selStart + 1);
        }

    }

}
