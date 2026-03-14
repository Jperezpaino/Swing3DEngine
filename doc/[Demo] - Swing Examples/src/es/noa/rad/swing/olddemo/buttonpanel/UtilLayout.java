package es.noa.rad.swing.olddemo.buttonpanel;

 import java.awt.GridBagConstraints;
 import java.awt.Insets;

 public class UtilLayout {
  
  public static void resetConstraints(final GridBagConstraints con) {
   //  default constraints
		    con.gridx = GridBagConstraints.RELATIVE;
		    con.gridy = GridBagConstraints.RELATIVE;
		    con.gridwidth = 1;
		    con.gridheight = 1;
		    con.weightx = 0;
		    con.weighty = 0;
		    con.anchor = GridBagConstraints.CENTER;
		    con.fill = GridBagConstraints.NONE;
		    con.insets = new Insets(0, 0, 0, 0);
		    con.ipadx = 0;
		    con.ipady = 0;
		  } 
 }
