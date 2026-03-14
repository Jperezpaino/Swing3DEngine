package es.noa.rad.test.aplication.component.base;

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

 public class BaseSimpleFrameDemo extends JFrame {  
  private static final long serialVersionUID = 1L;
  private static final int MINIMUN_WIDTH_SIZE = 410;
  private static final int MINIMUN_HEIGHT_SIZE = 520;
  private static final boolean RESIZABLE_FRAME = false;
  
  private static void createAndShowGUI() {
   final JFrame frame = new JFrame("Tabbed Pane Simple Frame Demo");
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
   frame.add(new BaseSimpleDemo(), BorderLayout.CENTER);
   frame.setMinimumSize(
    new Dimension(MINIMUN_WIDTH_SIZE, MINIMUN_HEIGHT_SIZE));
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
   frame.setSize(
    new Dimension(MINIMUN_WIDTH_SIZE, MINIMUN_HEIGHT_SIZE));   
   frame.setResizable(RESIZABLE_FRAME);
   frame.pack();
   frame.setVisible(true);
  }

  public static void main(String[] args) {
   SwingUtilities.invokeLater(
    new Runnable() {
     public void run() {
      try {
       /* Propiedad de la interface de usuario que define la apariencia basica
        * que vamos a utilizar, en este caso la apariencia basica de windows.*/    	  
       UIManager.setLookAndFeel(
        "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");      
      } catch (Exception e) {
       e.printStackTrace();
      }
      createAndShowGUI();
     }
    }
   );
  }

 }
