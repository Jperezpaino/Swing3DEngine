package es.noa.rad.test.swingworker;


import java.awt.BorderLayout;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
 
@SuppressWarnings("serial")
public class EjemploFrame extends JFrame {
 
 public static int MAX_ITE = 100000;
 
 private JTextField jtfInformacion = new JTextField();
 private JButton jbIncrementarSinSwingWorker = new JButton();
 private JButton jbIncrementarConSwingWorker = new JButton();
 private JPanel jpMarco = new JPanel();
 private JPanel jpAccciones = new JPanel();
 
 public EjemploFrame(){
  initComponents();
 }
 
 private void initComponents(){
  jpMarco.setLayout(new BorderLayout());
  jpMarco.add(jtfInformacion, BorderLayout.NORTH);
  
 
  jbIncrementarSinSwingWorker.setAction(new IncrementarSinSwingWorkerAction(this));
  jbIncrementarSinSwingWorker.setText("Incrementar sin swing worker");
  jbIncrementarConSwingWorker.setAction(new IncrementarConSwingWorkerAction(this));
  jbIncrementarConSwingWorker.setText("Incrementar con swing worker");
  
  jpAccciones.add(jbIncrementarSinSwingWorker);
  jpAccciones.add(jbIncrementarConSwingWorker);
  
  jpMarco.add(jpAccciones, BorderLayout.CENTER);
  
  setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  
  getContentPane().add(jpMarco);
  
  this.setTitle("Ejemplo funcionamiento Swing Worker");
  
  pack();
 }
 
 public JTextField getTextField(){
  return this.jtfInformacion;
 }
 
 public static void main(String[] args) {
  SwingUtilities.invokeLater(new Runnable(){
   public void run(){
    new EjemploFrame().setVisible(true);
   }
  });
 }
}