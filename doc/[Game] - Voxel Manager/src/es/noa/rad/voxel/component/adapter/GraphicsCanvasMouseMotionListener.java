package es.noa.rad.voxel.component.adapter;

 import
  java.awt.event.MouseEvent;
 import
  java.awt.event.MouseMotionAdapter;
 import
  es.noa.rad.voxel.component.GraphicsCanvas;

 /**
   * 
   * @author Jorge Pérez Paino
   *
   */
 public final class GraphicsCanvasMouseMotionListener
        extends MouseMotionAdapter {

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
  public GraphicsCanvasMouseMotionListener(
         final GraphicsCanvas parentComponentParam) {
   super();
   this.parentComponent = parentComponentParam;
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
    // System.out.println("Evento 'MouseEvent' lanzado.");
   }
  }

 }