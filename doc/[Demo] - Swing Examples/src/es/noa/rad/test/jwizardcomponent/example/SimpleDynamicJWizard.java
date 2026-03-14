package es.noa.rad.test.jwizardcomponent.example;

 import
  javax.swing.JFrame;
 import
  javax.swing.JLabel;
 import
  javax.swing.SwingUtilities;
 import
  javax.swing.UIManager;
 import
  es.noa.rad.test.aplication.util.ComponentUtil;
 import
  es.noa.rad.test.jwizardcomponent.frame.SimpleJWizardFrame;

 public class SimpleDynamicJWizard extends SimpleJWizardFrame {

  public static void main(String [] args) {
    try {
      SimpleJWizardFrame wizardFrame = new SimpleJWizardFrame();
      wizardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      SwingUtilities.updateComponentTreeUI(wizardFrame);

      wizardFrame.setTitle("Simple Dynamic JWizardComponent");

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