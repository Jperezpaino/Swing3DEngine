package es.noa.rad.test.aplication.component.trayicon;

 import
  java.awt.BorderLayout;
 import
  java.awt.Dimension;
 import
  java.awt.event.ComponentAdapter;
 import
  java.awt.event.ComponentEvent;
 import
  javax.swing.JFrame;
 import
  javax.swing.SwingUtilities;
 import
  javax.swing.UIManager;

 public class TrayIconSimpleFrameDemo extends JFrame {  
  private static final long serialVersionUID = 1L;

  private static void createAndShowGUI() {
   final JFrame frame = new JFrame("Combo Box Simple Frame Demo");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
   frame.add(new TrayIconSimpleDemo(), BorderLayout.CENTER);
   frame.setMinimumSize(new Dimension(620, 280));
   frame.addComponentListener(
    new ComponentAdapter() {
     public void componentResized(ComponentEvent evt) {
      Dimension size = frame.getSize();
      Dimension min = frame.getMinimumSize();
      if (size.getWidth() < min.getWidth()) {
       frame.setSize((int)min.getWidth() ,(int) size.getHeight());
      }
      if (size.getHeight() < min.getHeight()) {
       frame.setSize((int)size.getWidth() ,(int) min.getHeight());
      }
     }
    }
   );
   frame.setSize(300,300);   
   frame.setResizable(true);
   frame.pack();
   frame.setVisible(true);
  }

  public static void main(String[] args) {
   SwingUtilities.invokeLater(
    new Runnable() {
     public void run() {
      try {
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
