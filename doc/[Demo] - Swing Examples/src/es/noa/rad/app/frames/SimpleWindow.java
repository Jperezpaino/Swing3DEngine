package es.noa.rad.app.frames;

 import
  java.awt.Dimension;
 import
  java.awt.GridLayout;
 import
  java.awt.event.ComponentAdapter;
 import
  java.awt.event.ComponentEvent;
 import
  javax.swing.JFrame;
 import
  javax.swing.JPanel;
 import
  javax.swing.UIManager;

 public class SimpleWindow extends JPanel {
  private static final long serialVersionUID = 1L;
  private static final String WINDOW_NAME = "Simple Windows Example";
  private static final int WINDOW_MIN_WIDTH = 800;
  private static final int WINDOW_MIN_HEIGHT = 600;
  
  /**
    * Establecemos si utilizamos 'look and feel' del sistema o el generico de
    * 'swing'.
    */
  private static boolean useSystemLookAndFeel = false;
    
  public SimpleWindow() {
   /* Establecemos el 'Layout' que utilizara la ventana. */	  
   super(new GridLayout(1,0));
  }
  
  /**
    * Creamos la GUI y la mostramos. Para seguridad del hilo, este método debe
    * invocarse desde el hilo de gestion de eventos.
    */
  private static void createAndShowGUI() {
   if (useSystemLookAndFeel) {
    try {
     UIManager.setLookAndFeel(
      UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
     System.err.println("Couldn't use system look and feel.");
    }
   }
   /* Creamos la ventana y la establecemos. */
   
   /* 
    * Establecemos el nombre de la ventana a traves de la constante generica
    * de la clase.
    */
   JFrame mainFrame = new JFrame(WINDOW_NAME);
   mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   /*
    * Añadimos a la ventana en contenedor principal.
    */
   mainFrame.add(new SimpleWindow());

   /*
    * Compactamos y mostramos la ventana.
    */
   mainFrame.pack();
   mainFrame.setVisible(true);
   
   /*
    * Establecemos el control del tamaño para la ventana.
    */
   mainFrame.setMinimumSize(
    new Dimension(WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT));
   mainFrame.addComponentListener(
    new ComponentAdapter(){
     public void componentResized(
            final ComponentEvent componentEventParam){
      Dimension dimension = mainFrame.getSize();
      Dimension minDimension = mainFrame.getMinimumSize();
      if(dimension.getWidth() < minDimension.getWidth()) {
       dimension.width = (int) minDimension.getWidth();
      }
      if(dimension.getHeight() < minDimension.getHeight()) {
       dimension.height = (int) minDimension.getHeight();
      }
      mainFrame.setSize(dimension);
     }
    }
   );

  }
  
  public static void main(String[] args) {
   javax.swing.SwingUtilities.invokeLater(
    new Runnable() {
     public void run() {
      createAndShowGUI();
     }
    }
   );
  }

 }
