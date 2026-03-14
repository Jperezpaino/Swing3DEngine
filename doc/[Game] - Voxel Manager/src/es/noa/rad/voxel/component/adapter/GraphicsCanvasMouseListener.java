package es.noa.rad.voxel.component.adapter;

 import
  java.awt.event.MouseAdapter;
 import
  java.awt.event.MouseEvent;
 import
  java.awt.event.MouseWheelEvent;
 import
  java.awt.event.MouseWheelListener;
 import
  es.noa.rad.voxel.component.GraphicsCanvas;

 /**
   * 
   * @author Jorge Pérez Paino
   *
   */
 public final class GraphicsCanvasMouseListener
        extends MouseAdapter
        implements MouseWheelListener {

  /**
    * Establecemos el uso del 'debug' dentro de la clase. 
    */  
  private static boolean DEBUG = true;
  
  /**
    * Componente padre que contien la funcionalidad que desencadenan las
    * acciones. 
    */
  private GraphicsCanvas parentComponent;
  
  /**
    * Constructor
    *
    * @param parentComponentParam
    *
    */
  public GraphicsCanvasMouseListener(
         final GraphicsCanvas parentComponentParam) {
   super();
   this.parentComponent = parentComponentParam;
  }

  /**
    * 
    */
  public final void mouseClicked(
         final MouseEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'mouseClicked' lanzado.");
   }
  }
  
  /**
    * 
    */
  public final void mousePressed(
         final MouseEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'mousePressed' lanzado.");
   }
  }

  /**
    * 
    */
  public final void mouseReleased(
         final MouseEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'mouseReleased' lanzado.");
   }
  }
  
  /**
    * 
    */
  public final void mouseEntered(
         final MouseEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'mouseEntered' lanzado.");
   }
  }
  
  /**
    * 
    */
  public final void mouseExited(
         final MouseEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'mouseExited' lanzado.");
   }
  }
  
  /**
    * 
    */
  public final void mouseWheelMoved(
         final MouseWheelEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'mouseWheelMoved' lanzado.");
   }
  }

  /**
    * 
    */
  public final void mouseDragged(
         final MouseEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'mouseDragged' lanzado.");
   }
  }
  
  /**
    * 
    */
  public final void mouseMoved(
         final MouseEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'mouseMoved' lanzado.");
   }
  }

 }

