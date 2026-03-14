package es.noa.rad.test.jwizardcomponent.example;

 import
  javax.swing.ImageIcon;
 import
  javax.swing.JFrame;
 import
  javax.swing.JLabel;
 import
  javax.swing.SwingUtilities;
 import
  javax.swing.UIManager;
 import
  es.noa.rad.test.jwizardcomponent.DefaultJWizardComponents;
 import
  es.noa.rad.test.aplication.util.ComponentUtil;
 import
  es.noa.rad.test.jwizardcomponent.frame.SimpleLogoJWizardFrame;

public class SimpleDynamicLogoJWizard {

  static ImageIcon LOGO;

  public static void main(String [] args) {
    try {
    	
    	LOGO =
			new ImageIcon(DefaultJWizardComponents.class.getResource("images/logo.jpeg"));

      SimpleLogoJWizardFrame wizardFrame = new SimpleLogoJWizardFrame(
          LOGO);
      wizardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      SwingUtilities.updateComponentTreeUI(wizardFrame);
      
      wizardFrame.setTitle("Simple Logo Dynamic JWizardComponent");

      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("Dynamic Test")));

      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleDynamicWizardPanel(wizardFrame.getWizardComponents()));

      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("Done!")));
      wizardFrame.setSize(500, 300);
      
      // Centramos el componente dentro de la pantalla.
      ComponentUtil.centerComponentOnScreen(wizardFrame);
      
      wizardFrame.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}