package es.noa.rad.demo.swing.menubar;

 import
  java.awt.BorderLayout;
 import
  javax.swing.JFrame;
 import
  javax.swing.SwingUtilities;
 import
  javax.swing.UIManager;

 public class MenuBarViewerFrame extends JFrame {  
  private static final long serialVersionUID = 1L;

  private static void createAndShowGUI() {
   // Create and set up the window.
   JFrame frame = new JFrame("Menu Bar Demo");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
   // Add content to the window.
   frame.add(new MenuBarViewer(), BorderLayout.CENTER);
   frame.setResizable(false);

   // Display the window.
   frame.pack();
   frame.setVisible(true);
  }

  public static void main(String[] args) {
   // Schedule a job for the event dispatch thread:
   // Creating and showing this application's GUI.
   SwingUtilities.invokeLater(
    new Runnable() {
     public void run() {
      try { // Turn off metal's use of bold fonts
       UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } catch (Exception e) {
       e.printStackTrace();
      }
      createAndShowGUI();
     }
    }
   );
  }

 }
