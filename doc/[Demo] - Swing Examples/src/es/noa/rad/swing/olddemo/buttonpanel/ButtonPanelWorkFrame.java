package es.noa.rad.swing.olddemo.buttonpanel;

 import java.awt.BorderLayout;
import javax.swing.JDialog;
import
  javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

 public class ButtonPanelWorkFrame extends JFrame {  
  private static final long serialVersionUID = 1L;

  private static void createAndShowGUI() {
   //Create and set up the window.
   JFrame frame = new JFrame("TabbedPaneDemo");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
   //Add content to the window.
   frame.add(new ButtonPanelWork(), BorderLayout.CENTER);
   frame.setResizable(false);

   //Display the window.
   frame.pack();
   frame.setVisible(true);
}

public static void main(String[] args) {
   //Schedule a job for the event dispatch thread:
   //creating and showing this application's GUI.
   SwingUtilities.invokeLater(new Runnable() {
       public void run() {
           //Turn off metal's use of bold fonts
        try {
         UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
       } catch (Exception e) {
         e.printStackTrace();
       }
   createAndShowGUI();
       }
   });
}
 }
