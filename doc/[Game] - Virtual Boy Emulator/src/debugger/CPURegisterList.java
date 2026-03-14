package debugger;

// Java imports
import java.awt.*;
import javax.swing.*;

// Project imports
import config.*;
import debugger.*;
import util.*;
import vue.*;

// Scrollable list of CPU registers
class CPURegisterList extends JScrollPane {

    // Instance fields
    private int           initHeight; // Initial height of system registers
    private boolean       isSystem;   // List of system registers
    private Debugger      parent;     // Top-level UI manager
    private CPURegister[] registers;  // CPU registers
    private boolean       shown;      // Component has been displayed

    // UI components
    private JPanel client; // Main content pane



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // System register indexes
    private static final int[] SYSREGS = {
        CPURegister.PC, VUE.PSW,   VUE.EIPC, VUE.EIPSW, VUE.FEPC, VUE.FEPSW,
        VUE.ECR,        VUE.ADTRE, VUE.CHCW, VUE.PIR,   VUE.TKCW, 29, 30, 31
    };



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    CPURegisterList(Debugger parent, boolean isSystem) {
        super(VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Configure instance fields
        this.isSystem = isSystem;
        this.parent   = parent;
        registers     = new CPURegister[isSystem ? SYSREGS.length : 32];
        shown         = false;

        // Configure content pane
        Config config = parent.getConfig();
        client = new JPanel(new GridBagLayout()) {
            public Dimension getPreferredSize() {
                Dimension size = super.getPreferredSize();
                if (!shown) preShow(size);
                return size;
            }
        };
        client.setBackground(config.getColor("window"));
        client.setFocusable(true);
        Util.onMouse  (client, e->client.requestFocus(), null);
        Util.onVisible(client, e->onShow(), null);

        // Configure registers
        for (int x = 0; x < registers.length; x++) {
            registers[x] = new CPURegister(parent, client,
                isSystem ? SYSREGS[x] : x, isSystem);
            if (isSystem && x == 1) {
                registers[x].setExpanded(true);
                initHeight =
                    client.getLayout().preferredLayoutSize(client).height;
            }
        }

        // Add a dynamic spacer to the bottom of the list
        JPanel spacer          = new JPanel(null);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth          = GridBagConstraints.REMAINDER;
        gbc.weighty            = 1.0;
        spacer.setPreferredSize(new Dimension(0, 0));
        client.add(spacer, gbc);

        // Configure component
        getVerticalScrollBar().setUnitIncrement(config.getLineHeight());
        setViewportView(client);
    }

    private void onShow() {
        shown = true;
        client.revalidate();
    }

    private void preShow(Dimension size) {
        size.height = isSystem ? initHeight : 0;
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
        for (int x = 0; x < registers.length; x++)
            registers[x].refresh();
    }

}
