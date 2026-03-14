package es.noa.rad.app.component.formattedtextfield;

 import
  java.beans.PropertyChangeListener;
 import
  java.beans.PropertyChangeEvent;
 import
  java.text.NumberFormat;
 import
  javax.swing.BorderFactory;
 import
  javax.swing.JFormattedTextField;
 import
  javax.swing.JFrame;
 import
  javax.swing.JLabel;
 import
  javax.swing.JPanel;
 import
  javax.swing.SwingUtilities;
 import
  javax.swing.UIManager;
 import
  java.awt.BorderLayout;
 import
  java.awt.GridLayout;

 public class FormattedTextFieldDemo extends JPanel
                                     implements PropertyChangeListener {
  private static final long serialVersionUID = 1L;
  // Definimos los componentes que presenta el panel {nombre}{Componente}.
  private JPanel labelPanel;
  private JPanel formattedTextFieldPanel;
  
  private JLabel integerNumberLabel;
  private String integerNumberLabelString = "Número entero: ";
  private JFormattedTextField integerNumberFormattedTextField;
  private NumberFormat integerNumberFormat;
  
  private JLabel decimalNumberLabel;
  private String decimalNumberLabelString = "Número decimal: ";
  private JFormattedTextField decimalNumberFormattedTextField;
  private NumberFormat decimalNumberFormat;
  
  private JLabel percentageLabel;
  private String percentageLabelString = "Porcentaje: ";
  private JFormattedTextField percentageFormattedTextField;  
  private NumberFormat percentageFormat;
    
  private double integerNumberValue = 1234.56;
  private double decimalNumberValue = 1234.56;
  private double percentageValue = 12.3456;
 
  public FormattedTextFieldDemo() {
   super(new BorderLayout());
   // Establecemos el formato numerico a utilizar.
   this.integerNumberFormat = NumberFormat.getIntegerInstance();
   this.decimalNumberFormat = NumberFormat.getNumberInstance();
   this.decimalNumberFormat.setMinimumFractionDigits(2);
   this.percentageFormat = NumberFormat.getPercentInstance();
   // Inicializamos los componentes.
   this.integerNumberLabel = new JLabel(this.integerNumberLabelString);
   this.integerNumberFormattedTextField
    = new JFormattedTextField(this.integerNumberFormat);
   this.integerNumberFormattedTextField.setValue(
    new Double(this.integerNumberValue));
   this.integerNumberFormattedTextField.setColumns(10);
   this.integerNumberFormattedTextField.addPropertyChangeListener("value", this);
   this.integerNumberLabel.setLabelFor(this.integerNumberFormattedTextField);
   
   this.decimalNumberLabel = new JLabel(this.decimalNumberLabelString);
   this.decimalNumberFormattedTextField
    = new JFormattedTextField(this.decimalNumberFormat);
   this.decimalNumberFormattedTextField.setValue(
    new Double(this.decimalNumberValue));
   this.decimalNumberFormattedTextField.setColumns(10);
   this.decimalNumberFormattedTextField.addPropertyChangeListener("value", this);
   this.decimalNumberLabel.setLabelFor(this.decimalNumberFormattedTextField);
   
   this.percentageLabel = new JLabel(this.percentageLabelString);   
   this.percentageFormattedTextField
    = new JFormattedTextField(this.percentageFormat);
   this.percentageFormattedTextField.setValue(
    new Double(this.percentageValue));
   this.percentageFormattedTextField.setColumns(10);
   this.percentageFormattedTextField.addPropertyChangeListener("value", this);
   this.percentageLabel.setLabelFor(this.percentageFormattedTextField);

   // Generamos el 'Layout' y distribuimos los componentes. 
   this.labelPanel = new JPanel(new GridLayout(0,1));
   this.labelPanel.add(this.integerNumberLabel);
   this.labelPanel.add(this.decimalNumberLabel);
   this.labelPanel.add(this.percentageLabel);
   
   this.formattedTextFieldPanel = new JPanel(new GridLayout(0,1));
   this.formattedTextFieldPanel.add(this.integerNumberFormattedTextField);
   this.formattedTextFieldPanel.add(this.decimalNumberFormattedTextField);
   this.formattedTextFieldPanel.add(this.percentageFormattedTextField);
   
   super.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
   super.add(this.labelPanel, BorderLayout.CENTER);
   super.add(this.formattedTextFieldPanel, BorderLayout.LINE_END);   
  }
 
  public void propertyChange(PropertyChangeEvent e) {
   Object source = e.getSource();
   if (source == this.integerNumberFormattedTextField) {
    this.integerNumberValue = ((Number) this.integerNumberFormattedTextField.getValue()).doubleValue();
   } else if (source == this.decimalNumberFormattedTextField) {
    this.decimalNumberValue = ((Number) this.decimalNumberFormattedTextField.getValue()).doubleValue();
   } else if (source == this.percentageFormattedTextField) {
    this.percentageValue = ((Number) this.percentageFormattedTextField.getValue()).doubleValue();
   }
   //double payment = computePayment(amount, porcentaje, numPeriods);
   //paymentField.setValue(new Double(payment));
  }
 
  private static void createAndShowGUI() {
   JFrame frame = new JFrame("FormattedTextFieldDemo");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.add(new FormattedTextFieldDemo());
   frame.pack();
   frame.setVisible(true);
  }
 
  public static void main(String[] args) {
   SwingUtilities.invokeLater(
    new Runnable() {
     public void run() {
      UIManager.put("swing.boldMetal", Boolean.FALSE);
      createAndShowGUI();
     }
    }
   );
  }
 
    //Compute the monthly payment based on the loan amount,
    //APR, and length of loan.
    /*double computePayment(double loanAmt, double porcentaje, int numPeriods) {
        double I, partial1, denominator, answer;
 
        numPeriods *= 12;        //get number of months
        if (porcentaje > 0.01) {
            I = porcentaje / 100.0 / 12.0;         //get monthly porcentaje from annual
            partial1 = Math.pow((1 + I), (0.0 - numPeriods));
            denominator = (1 - partial1) / I;
        } else { //porcentaje ~= 0
            denominator = numPeriods;
        }
 
        answer = (-1 * loanAmt) / denominator;
        return answer;
    }*/
 
    //Create and set up number formats. These objects also
    //parse numbers input by user.
    //private void setUpFormats() {
        //amountFormat = NumberFormat.getNumberInstance();
 
        //percentFormat = NumberFormat.getNumberInstance();
       // percentFormat.setMinimumFractionDigits(3);
 
        //paymentFormat = NumberFormat.getCurrencyInstance();
    //}
}