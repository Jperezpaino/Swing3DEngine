package es.noa.rad.test.jwizardcomponent.example;

 import
  java.awt.BorderLayout;
 import
  javax.swing.ImageIcon;
 import
  javax.swing.JButton;
 import
  javax.swing.JFrame;
 import
  javax.swing.JLabel;
 import
  javax.swing.SwingUtilities;
 import
  javax.swing.UIManager;
 import
  javax.swing.WindowConstants;
 import
  es.noa.rad.test.aplication.util.ComponentUtil;
 import
  es.noa.rad.test.jwizardcomponent.dialog.*;

public class SimpleModalLogoJWizard {
    
    static ImageIcon LOGO;
    
    public static void main(String [] args) {
        try {
            
            // optional: set a look and feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // create a new frame or use an existing one of your application
            final JFrame mainWindow=new JFrame("Simple demo of a modal wizard with a logo icon.");
            mainWindow.getContentPane().setLayout(new BorderLayout());
            mainWindow.getContentPane().add("North", new JLabel("Click the button to get a modal wizard dialog for this JFrame.", JLabel.CENTER));
            
            // in this example, we use a button to open a new wizard
            JButton dialogButton=new JButton("open modal wizard");
            dialogButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    
                    // create the modal wizard: the constructor takes the parent frame
                    SimpleLogoJWizardDialog wizardDialog = new SimpleLogoJWizardDialog(mainWindow, LOGO, true);
                    
                    SwingUtilities.updateComponentTreeUI(wizardDialog);
                    wizardDialog.setTitle("Simple Logo JWizardComponent");
                    
                    // add panels to the wizard
                    wizardDialog.getWizardComponents().addWizardPanel(
                    new SimpleLabelWizardPanel(wizardDialog.getWizardComponents(),
                    new JLabel("This")));
                    wizardDialog.getWizardComponents().addWizardPanel(
                    new SimpleLabelWizardPanel(wizardDialog.getWizardComponents(),
                    new JLabel("is")));
                    wizardDialog.getWizardComponents().addWizardPanel(
                    new SimpleLabelWizardPanel(wizardDialog.getWizardComponents(),
                    new JLabel("a")));
                    wizardDialog.getWizardComponents().addWizardPanel(
                    new SimpleLabelWizardPanel(wizardDialog.getWizardComponents(),
                    new JLabel("modal")));
                    wizardDialog.getWizardComponents().addWizardPanel(
                    new SimpleLabelWizardPanel(wizardDialog.getWizardComponents(),
                    new JLabel("wizard!")));
                    wizardDialog.setSize(500, 300);
                    wizardDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    // Centramos el componente dentro de la pantalla.
                    ComponentUtil.centerComponentOnScreen(wizardDialog);                    
                    
                    // show the wizard
                    wizardDialog.show();
                }
            });
            mainWindow.getContentPane().add("South", dialogButton);
            mainWindow.setSize(400,  100);
            mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            LOGO =
            new ImageIcon("images/logo.jpeg");
            
            // show the frame
            mainWindow.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
