package debugger;

// Java imports
import java.awt.*;
import javax.swing.*;

// Project imports
import config.*;
import vue.*;

// One row of the breakpoints list
class BreakLine extends JPanel {

    // Instance fields
    private Config config; // Global configuration options

    // Component 
    private JLabel lblName;   // Name label
    private JLabel lblStatus; // Status label

    // Package fields
    Breakpoint breakpoint; // Target breakpoint object



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    BreakLine(Config config) {
        super(null);

        // Configure instance fields
        breakpoint  = new Breakpoint();
        this.config = config;

        // Status label
        lblStatus = new JLabel();
        config.addFontComponent(lblStatus, Config.DIALOG);
        lblStatus.setLocation(0, 0);
        lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblStatus);

        // Name label
        lblName = new JLabel();
        config.addFontComponent(lblName, Config.DIALOG);
        add(lblName);

        // Configure component
        setOpaque(false);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Configure component layout
    int configureLayout(int lineHeight, FontMetrics metrics) {
        int width = metrics.stringWidth(breakpoint.getName());
        lblStatus.setSize(lineHeight, lineHeight);
        lblName.setBounds(lineHeight, 0, width, lineHeight);
        return lineHeight + width;
    }

    // Update the display with the current state
    void refresh(VUE vue) {
        if (!breakpoint.isValid())
            lblStatus.setText("!");
        else if (!breakpoint.isEnabled())
            lblStatus.setText("");
        else if (
            breakpoint.getEvents()      != 0 &&
            breakpoint.evaluate(vue)[1] != 0
        ) lblStatus.setText("\u2605");
        else lblStatus.setText("\u2713");
        lblName.setText(breakpoint.getName());
    }

    // Specify whether the disassembler pane has focus
    void setFocus(boolean selected, boolean hasFocus) {

        // Configure background
        setBackground(config.getColor(hasFocus ? "selected" : "selectedBlur"));
        setOpaque(selected);

        // Configure foreground
        Color color = config.getColor(selected && hasFocus ?
            "selectedText" : "windowText");
        lblStatus.setForeground(color);
        lblName.setForeground(color);
    }

}
