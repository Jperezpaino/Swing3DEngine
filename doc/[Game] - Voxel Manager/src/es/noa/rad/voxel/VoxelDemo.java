package es.noa.rad.voxel;

 import
  java.awt.Dimension;
 import
  java.awt.GridLayout;
 import
  javax.swing.JFrame;
 import
  javax.swing.SwingUtilities;
 import
  javax.swing.UIManager;
 import
  es.noa.rad.voxel.adapter.GraphicsComponentListener;
 import
  es.noa.rad.voxel.adapter.GraphicsWindowListener;
 import
  es.noa.rad.voxel.component.GraphicsCanvas;

 public class VoxelDemo extends JFrame {
  private static final long serialVersionUID = 1L;
  
  /**
    *  Declaramos lo componentes que forman la ventana. 
    */
  private GraphicsCanvas graphicsCanvas;
  
  /**
    *  Declaramos los distintos adaptadores de la ventana.
    */
  private GraphicsWindowListener windowListener;
  private GraphicsComponentListener componentListener;
  
  /**
    * 
    */
  public VoxelDemo() {
   super();
   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   this.setMinimumSize(new Dimension(1024, 768));   

   this.graphicsCanvas = new GraphicsCanvas();

   GridLayout windowLayout = new GridLayout(1,1);
   this.setLayout(windowLayout);
   this.add(this.graphicsCanvas);

   this.windowListener = new GraphicsWindowListener(this);
   this.addWindowListener(this.windowListener);
   this.addWindowStateListener(this.windowListener);
   this.addWindowFocusListener(this.windowListener);
   this.componentListener = new GraphicsComponentListener(this);
   this.addComponentListener(this.componentListener);
    
   this.setResizable(true);
   this.pack();
   this.setVisible(true);
  } 

  /**
    * 
    * @return
    */
  public final GraphicsCanvas getGraphicsCanvas() {
   return this.graphicsCanvas;
  }

  /**
    * 
    * @param graphicsCanvas
    */
  public final void setGraphicsCanvas(
         final GraphicsCanvas graphicsCanvasParam) {
   this.graphicsCanvas = graphicsCanvasParam;
  }

  /**
    * 
    * @param args
    */
  public static void main(String[] args) {
   SwingUtilities.invokeLater(
    new Runnable() {
     public void run() {
      try {
       UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      } catch (Exception e) {
       e.printStackTrace();
      }
      new VoxelDemo();
     }
    }
   );
  }

 }