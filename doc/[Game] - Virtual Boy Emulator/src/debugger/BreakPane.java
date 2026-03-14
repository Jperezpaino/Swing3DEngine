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

// Interface for breakpoints
class BreakPane extends JPanel {

    // Instance fields
    private int      index;  // Currently selected line item
    private Debugger parent; // Parent application
    private ArrayList<BreakLine> lines; // Individual lines of the list box

    // Component fields
    private JButton     btnDelete;    // Delete button
    private JCheckBox   chkEnabled;   // Enabled check box
    private JCheckBox   chkException; // Exception check box
    private JCheckBox   chkExecute;   // Execute check box
    private JCheckBox   chkRead;      // Read check box
    private JCheckBox   chkWrite;     // Write check box
    private JLabel      lblError;     // Error label
    private JPanel      panList;      // Client of list box
    private JPanel      ribbon;       // Controls panel
    private JScrollPane scrList;      // List box
    private JTextField  txtAddress;   // Address text box
    private JTextField  txtCondition; // Condition text box
    private JTextField  txtName;      // Name text box



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Error keys
    private static final String[] ERRORS = {
        "breakpoints.err_badeof",
        "breakpoints.err_literal",
        "breakpoints.err_syntax",
        "breakpoints.err_unknown"
    };



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    BreakPane(Debugger parent) {
        super(new BorderLayout());
        Config config = parent.getConfig();

        // Configure instance fields
        lines       = new ArrayList<BreakLine>();
        this.parent = parent;

        // List box
        scrList = new JScrollPane(
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        add(scrList, BorderLayout.CENTER);

        // List contents
        panList = new JPanel(null);
        panList.setBackground(config.getColor("window"));
        panList.setFocusable(true);
        Util.onMouse(panList, e->onList(e), null);
        Util.onFocus(panList, e->onFocus(true), e->onFocus(false));
        scrList.setViewportView(panList);

        // Controls ribbon
        ribbon = new JPanel(new GridBagLayout());
        ribbon.setBackground(config.getColor("control"));
        ribbon.setFocusable(true);
        Util.onMouse(ribbon, e->ribbon.requestFocus(), null);
        add(ribbon, BorderLayout.PAGE_END);

        // Name text box
        add(ribbon, txtName = createTextBox(Config.DIALOG, e->onName()),
            "common.name", true);

        // Check boxes
        ActionListener onCheck = e->onType();
        add(ribbon, chkEnabled = createCheckBox(null, e->onEnabled()),
            "common.enabled", true);
        add(ribbon, chkRead = createCheckBox("breakpoints.read", onCheck),
            "common.type", false);
        add(ribbon, chkWrite = createCheckBox("breakpoints.write", onCheck),
            null, false);
        add(ribbon, chkExecute = createCheckBox("breakpoints.execute",onCheck),
            null, false);
        add(ribbon, chkException = createCheckBox("cpu.exception", onCheck),
            null, true);

        // Text boxes
        add(ribbon, txtAddress = createTextBox(Config.FIXED, e->onAddress()),
            "common.address", true);
        add(ribbon, txtCondition = createTextBox(
            Config.FIXED, e->onCondition()), "breakpoints.condition", true);

        // Bottom panel
        JPanel panel = new JPanel(new GridBagLayout());
        add(ribbon, panel, null, true);

        // Error label
        lblError = new JLabel();
        config.addFontComponent(lblError, Config.DIALOG);
        lblError.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill    = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        panel.add(lblError, gbc);

        // Buttons
        JButton btnNew;
        add(panel, btnNew    = createButton("common.new"   , e->onNew()),
            null, false);
        add(panel, btnDelete = createButton("common.delete", e->onDelete()),
            null, true);

        // Configure component
        setBackground(config.getColor("control"));
        addComponentListener(new ComponentListener() {
            public void componentHidden (ComponentEvent e) { }
            public void componentMoved  (ComponentEvent e) { }
            public void componentResized(ComponentEvent e) { onResize(); }
            public void componentShown  (ComponentEvent e) { }
        });

        // State management
        enableControls(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Retrieve a list of all current breakpoints
    Breakpoint[] listBreakpoints() {
        Breakpoint[] ret = new Breakpoint[lines.size()];
        for (int x = 0; x < ret.length; x++) {
            Breakpoint brk = lines.get(x).breakpoint;
            brk.setBreakCode(x + 1);
            ret[x] = brk;
        }
        return ret;
    }

    // Update the display with the current state
    void refresh() {
        Config      config     = parent.getConfig();
        VUE         vue        = parent.getVUE();
        boolean     focus      = panList.isFocusOwner();
        int         innerWidth = 0;
        int         lineHeight = config.getLineHeight(Config.DIALOG);
        FontMetrics metrics    = config.getFontMetrics(Config.DIALOG);
        int         width      = panList.getWidth();

        // Configure scroll pane
        scrList.getVerticalScrollBar().setUnitIncrement(lineHeight);

        // Configure list items
        for (int x = 0; x < lines.size(); x++) {
            BreakLine line = lines.get(x);
            innerWidth = Math.max(innerWidth,
                line.configureLayout(lineHeight, metrics));
            line.setFocus(x == index, focus);
            line.refresh(vue);
        }

        // Configure list content pane
        int top = 0;
        for (int x = 0; x < lines.size(); x++, top += lineHeight)
            lines.get(x).setBounds(0, top, width, lineHeight);
        panList.setPreferredSize(new Dimension(innerWidth, top));
        scrList.repaint();

        // Configure entry fields
        Breakpoint brk = lines.size() == 0 || index == -1 ? null :
            lines.get(index).breakpoint;
        enableControls(brk != null);
        int events = brk == null ? 0 : brk.getEvents();
        txtName     .setText    (brk == null ? "" : brk.getName());
        chkEnabled  .setSelected(brk == null ? false : brk.isEnabled());
        chkRead     .setSelected((events & Breakpoint.READ     ) != 0);
        chkWrite    .setSelected((events & Breakpoint.WRITE    ) != 0);
        chkExecute  .setSelected((events & Breakpoint.EXECUTE  ) != 0);
        chkException.setSelected((events & Breakpoint.EXCEPTION) != 0);
        txtAddress  .setText    (brk == null ? "" : brk.getAddress());
        txtCondition.setText    (brk == null ? "" : brk.getCondition());

        // Configure error text
        if (brk != null) for (int type = 0; type < 2; type++) {
            int code = brk.getError(type, Breakpoint.CODE);
            if (code == Breakpoint.NOERROR)
                continue;

            // Format the error message
            int    col  = brk.getError(type, Breakpoint.COLUMN);
            String text = brk.getErrorText(type);
            lblError.setText("<html>" +
                config.getMessage("breakpoints.err_header") + " " +
                config.getMessage(type == 0 ? "common.address" :
                    "breakpoints.condition") + ":" + col + ":<br>" +
                config.getMessage(ERRORS[code - 1]).replace("{TEXT}", text)
            + "</html>");
            return;
        }
        lblError.setText("");
    }

    // Specify the index of the selected line
    void setIndex(int index) {
        if (index < 0 || index >= lines.size())
            index = -1;
        this.index = index;
        refresh();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // Address text box
    private void onAddress() {
        Breakpoint brk = lines.get(index).breakpoint;
        brk.setAddress(txtAddress.getText());
        refresh();
        parent.onBreakpoint();
    }

    // Condition text box
    private void onCondition() {
        Breakpoint brk = lines.get(index).breakpoint;
        brk.setCondition(txtCondition.getText());
        refresh();
        parent.onBreakpoint();
    }

    // Delete button
    private void onDelete() {
        BreakLine line = lines.get(index);
        panList.remove(line);
        lines.remove(index);
        index = index >= lines.size() ? lines.size() - 1 : index;
        refresh();
        parent.onBreakpoint();
    }

    // List box focus
    private void onFocus(boolean focus) {
        if (index != -1 && index < lines.size())
            lines.get(index).setFocus(true, focus);
    }

    // Enabled check box
    private void onEnabled() {
        Breakpoint brk = lines.get(index).breakpoint;
        brk.setEnabled(chkEnabled.isSelected());
        refresh();
        parent.onBreakpoint();
    }

    // List box mouse down
    private void onList(MouseEvent e) {
        Config  config = parent.getConfig();
        int     button = e.getButton();
        int     clicks = e.getClickCount();
        int     x      = e.getX();
        int     y      = e.getY();
        boolean commit = false;

        // Grab focus regardless
        panList.requestFocus();

        // Commit any active edits
        if (commit = index != -1 && txtName.isFocusOwner() ||
            txtAddress.isFocusOwner() || txtCondition.isFocusOwner()) {
            Breakpoint brk = lines.get(index).breakpoint;
            brk.setName     (txtName     .getText());
            brk.setAddress  (txtAddress  .getText());
            brk.setCondition(txtCondition.getText());
        }

        // Only respond to left clicks
        if (button != 1) {
            if (commit) {
                parent.onBreakpoint();
                refresh();
            }
            return;
        }

        // Determine which line was clicked
        int index  = y / config.getLineHeight(Config.DIALOG);
        int bottom = Math.min(index, lines.size() - 1);
        if (bottom < 0)
            return;

        // Change the index to the clicked line
        if (bottom != this.index) {
            setIndex(bottom);
            return;
        }

        // Enable or disable the clicked line
        if (index >= lines.size() || (clicks & 1) != 0)
            return;
        Breakpoint brk = lines.get(index).breakpoint;
        brk.setEnabled(!brk.isEnabled());
        refresh();
        parent.onBreakpoint();
    }

    // Name text box
    private void onName() {
        Breakpoint brk = lines.get(index).breakpoint;
        brk.setName(txtName.getText());
        refresh();
        parent.onBreakpoint();
    }

    // New button
    private void onNew() {
        Config    config = parent.getConfig();
        BreakLine line   = new BreakLine(config);
        lines.add(line);
        panList.add(line);
        Breakpoint brk  = line.breakpoint;
        brk.setEnabled(true);
        brk.setName(config.getMessage("breakpoints.new"));
        setIndex(lines.size() - 1);
        parent.onBreakpoint();
    }

    // Window resize
    private void onResize() {
        Config config = parent.getConfig();
        int    width  = panList.getWidth();
        int    height = config.getLineHeight(Config.DIALOG);
        for (int x = 0; x < lines.size(); x++)
            lines.get(x).setSize(width, height);
    }

    // Type check boxes
    private void onType() {
        Breakpoint brk = lines.get(index).breakpoint;
        brk.setEvents(
            (chkRead     .isSelected() ? Breakpoint.READ      : 0) |
            (chkWrite    .isSelected() ? Breakpoint.WRITE     : 0) |
            (chkExecute  .isSelected() ? Breakpoint.EXECUTE   : 0) |
            (chkException.isSelected() ? Breakpoint.EXCEPTION : 0)
        );
        refresh();
        parent.onBreakpoint();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Add a component to a container, optionally producing a label
    private void add(JComponent container, JComponent ctl, String key,
        boolean last) {
        Config config = parent.getConfig();
        GridBagConstraints gbc;
        int top = container.getComponentCount() == 0 ? 4 : 0;

        // Produce a label for the component
        if (key != null) {
            JLabel label = new JLabel();
            config.addFontComponent(label, Config.DIALOG);
            config.addLocaleComponent(label, key);
            label.setOpaque(false);
            gbc = new GridBagConstraints();
            gbc.anchor       = GridBagConstraints.LINE_START;
            gbc.insets.left  = 4;
            gbc.insets.right = 4;
            container.add(label, gbc);
        }

        // Add the component to the container
        gbc = new GridBagConstraints();
        gbc.anchor        = ctl instanceof JButton ?
            GridBagConstraints.SOUTH : GridBagConstraints.LINE_START;
        gbc.gridwidth     = last ? GridBagConstraints.REMAINDER : 1;
        gbc.insets.left   = 4;
        gbc.insets.right  = !(ctl instanceof JButton) && last ? 4 : 0;
        gbc.insets.top    = top;
        gbc.insets.bottom =   ctl instanceof JButton ? 0 : 4;
        gbc.weightx       = !(ctl instanceof JButton) && last ? 1 : 0;
        gbc.fill          =
            ctl instanceof JTextField || ctl instanceof JPanel ?
            GridBagConstraints.BOTH : GridBagConstraints.NONE;
        container.add(ctl, gbc);
    }

    // Create a button
    private JButton createButton(String key, ActionListener evt) {
        Config  config = parent.getConfig();
        JButton ctl    = new JButton();
        config.addFontComponent(ctl, Config.DIALOG);
        config.addLocaleComponent(ctl, key);
        ctl.addActionListener(evt);
        return ctl;
    }

    // Create a check box
    private JCheckBox createCheckBox(String key, ActionListener evt) {
        Config    config = parent.getConfig();
        JCheckBox ctl    = new JCheckBox();
        ctl.setBorder(null);
        ctl.setOpaque(false);
        config.addFontComponent(ctl, Config.DIALOG);
        if (key != null)
            config.addLocaleComponent(ctl, key);
        ctl.addActionListener(evt);
        return ctl;
    }

    // Create a text box
    private JTextField createTextBox(int font, Util.FocusHandler evt) {
        Config     config = parent.getConfig();
        JTextField ctl    = new JTextField();
        config.addFontComponent(ctl, font);
        ctl.addActionListener(e->requestFocus());
        Util.onFocus(ctl, null, evt);
        return ctl;
    }

    // Enable or disable all controls in the form
    private void enableControls(boolean enabled) {
        txtName     .setEnabled(enabled);
        chkEnabled  .setEnabled(enabled);
        chkRead     .setEnabled(enabled);
        chkWrite    .setEnabled(enabled);
        chkExecute  .setEnabled(enabled);
        chkException.setEnabled(enabled);
        txtAddress  .setEnabled(enabled);
        txtCondition.setEnabled(enabled);
        btnDelete   .setEnabled(enabled);
    }

}
