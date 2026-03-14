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
  es.noa.rad.test.aplication.util.ComponentUtil;
 import
  es.noa.rad.test.jwizardcomponent.frame.SimpleJWizardFrame;
 import
  es.noa.rad.test.jwizardcomponent.frame.SimpleLogoJWizardFrame;

 public class SimpleLogoJWizard extends SimpleJWizardFrame {
  private static final long serialVersionUID = 1L;
  static ImageIcon LOGO;

  public static void main(String [] args) {
    try {

        LOGO =
            new ImageIcon("images/logo.jpeg");

      SimpleLogoJWizardFrame wizardFrame = new SimpleLogoJWizardFrame(
          LOGO);
      wizardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      SwingUtilities.updateComponentTreeUI(wizardFrame);

      wizardFrame.setTitle("Simple Logo JWizardComponent");

      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("Yo")));
      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("This")));
      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("Is")));
      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("A")));
      wizardFrame.getWizardComponents().addWizardPanel(
          new SimpleLabelWizardPanel(wizardFrame.getWizardComponents(),
          new JLabel("Test!")));
      wizardFrame.setSize(500, 300);
      
      // Centramos el componente dentro de la pantalla.
      ComponentUtil.centerComponentOnScreen(wizardFrame);
      
      wizardFrame.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
