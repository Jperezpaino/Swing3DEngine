package es.noa.rad.test.aplication.util;

 import
  java.awt.GridBagConstraints;
 import
  java.awt.Insets;

 public class UtilLayout {
  
  public static void resetConstraints(
         final GridBagConstraints gridBagConstraintsParam) {
   // default constraints
   gridBagConstraintsParam.gridx = GridBagConstraints.RELATIVE;
   gridBagConstraintsParam.gridy = GridBagConstraints.RELATIVE;
   gridBagConstraintsParam.gridwidth = 1;
   gridBagConstraintsParam.gridheight = 1;
   gridBagConstraintsParam.weightx = 0;
   gridBagConstraintsParam.weighty = 0;
   gridBagConstraintsParam.anchor = GridBagConstraints.CENTER;
   gridBagConstraintsParam.fill = GridBagConstraints.NONE;
   gridBagConstraintsParam.insets = new Insets(0, 0, 0, 0);
   gridBagConstraintsParam.ipadx = 0;
   gridBagConstraintsParam.ipady = 0;
  }

 }
