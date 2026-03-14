package es.noa.rad.test.jwizardcomponent.frame;

 import
  java.awt.GridBagConstraints;
 import
  java.awt.GridBagLayout;
 import
  java.awt.GridLayout;
 import
  java.awt.FlowLayout;
 import
  java.awt.Insets;
 import
  java.awt.event.WindowAdapter;
 import
  java.awt.event.WindowEvent;
 import
  java.beans.PropertyChangeEvent;
 import
  java.beans.PropertyChangeListener;
 import
  javax.swing.JFrame;
 import
  javax.swing.JLabel;
 import
  javax.swing.JPanel;
 import
  javax.swing.JSeparator;
 import 
  javax.swing.SwingConstants;
 import
  javax.swing.WindowConstants;
 import
  es.noa.rad.test.jwizardcomponent.CancelAction;
 import
  es.noa.rad.test.jwizardcomponent.FinishAction;
 import
  es.noa.rad.test.jwizardcomponent.JWizardComponents;
 import
  es.noa.rad.test.jwizardcomponent.DefaultJWizardComponents;
 import
  es.noa.rad.test.jwizardcomponent.JWizardPanel;

 public class JWizardFrame extends JFrame {
    
    private JWizardComponents wizardComponents;
    
    private JPanel buttonPanel;
    private JLabel panelTitleLabel;
    
    public JWizardFrame() {
        init();
    }
    
    private void init() {
        wizardComponents = new DefaultJWizardComponents();
        wizardComponents.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                setPanelTitle(((JWizardPanel)event.getNewValue()).getPanelTitle());
            }
        });
        
        this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(createTitlePanel()
        , new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH
        , new Insets(5, 5, 5, 5), 0, 0));
        
        this.getContentPane().add(new JSeparator()
        , new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH,
        new Insets(1, 1, 1, 1), 0, 0));
        
        this.getContentPane().add(wizardComponents.getWizardPanelsContainer()
        , new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0
        , GridBagConstraints.CENTER, GridBagConstraints.BOTH
        , new Insets(0, 0, 0, 0), 0, 0));
        
        this.getContentPane().add(new JSeparator()
        , new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0
        , GridBagConstraints.WEST, GridBagConstraints.BOTH
        , new Insets(1, 1, 1, 1), 0, 0));
        
        this.getContentPane().add(createButtonPanel(),
        new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0
        ,GridBagConstraints.EAST, GridBagConstraints.NONE,
        new Insets(5, 5, 5, 5), 0, 0));
        
        wizardComponents.setFinishAction(createFinishAction());
        wizardComponents.setCancelAction(createCancelAction());
        handleWindowClosing();
    }
    
    public JWizardComponents getWizardComponents(){
        return wizardComponents;
    }
    
    public void setWizardComponents(JWizardComponents aWizardComponents){
        wizardComponents = aWizardComponents;
    }
    
    public void show() {
        wizardComponents.updateComponents();
        super.show();
    }
    
    
    protected void setPanelTitle(String title) {
        panelTitleLabel.setText(title);
    }
    
    protected JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitleLabel = new JLabel();
        panelTitleLabel.setHorizontalAlignment(SwingConstants.LEADING);
        panel.add(panelTitleLabel);
        return panel;
    }
    
    protected JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout());
        panel.add(wizardComponents.getBackButton());
        panel.add(wizardComponents.getNextButton());
        panel.add(wizardComponents.getFinishButton());
        panel.add(wizardComponents.getCancelButton());
        return panel;
    }
    
    protected FinishAction createFinishAction() {
        return new FinishAction(wizardComponents) {
            public void performAction() {
                System.out.println("FinishAction performed");
                dispose();
            }
        };
    }
    
    protected CancelAction createCancelAction() {
        return new CancelAction(wizardComponents) {
            public void performAction() {
                System.out.println("CancelAction performed");
                dispose();
            }
        };
    }
    
    protected void handleWindowClosing() {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                wizardComponents.getCancelAction().performAction();
            }
        });
    }
}
