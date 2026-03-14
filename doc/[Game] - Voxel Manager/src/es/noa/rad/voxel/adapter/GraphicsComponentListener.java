package es.noa.rad.voxel.adapter;

 import java.awt.Dimension;
import
  java.awt.event.ComponentAdapter;
 import
  java.awt.event.ComponentEvent;
 import
  es.noa.rad.voxel.VoxelDemo;

 /**
   * 
   * @author Jorge Pérez Paino
   *
   */
 public class GraphicsComponentListener
        extends ComponentAdapter {

  /**
    * Establecemos el uso del 'debug' dentro de la clase. 
    */  
  private static boolean DEBUG = true;
  
  /**
    * Componente padre que contien la funcionalidad que desencadenan las
    * acciones. 
    */
  private VoxelDemo windowComponent;
  
  /**
    * Constructor
    *
    * @param windowComponentParam
    *
    */
  public GraphicsComponentListener(
         final VoxelDemo windowComponentParam) {
   super();
   this.windowComponent = windowComponentParam;
  }
 
  /**
    * 
    */
  public final void componentResized(
         final ComponentEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'componentResized' lanzado.");
   }
   Dimension size = this.windowComponent.getSize();
   Dimension min = this.windowComponent.getMinimumSize();
   if (size.getWidth() < min.getWidth()) {
    this.windowComponent.setSize((int)min.getWidth() ,(int) size.getHeight());
   }
   if (size.getHeight() < min.getHeight()) {
    this.windowComponent.setSize((int)size.getWidth() ,(int) min.getHeight());
   }   
   this.windowComponent.getGraphicsCanvas().updateSizes();
  }
  
  /**
    * 
    */
  public final void componentMoved(
         final ComponentEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'componentMoved' lanzado.");
   }
  }
  
  /**
    * 
    */
  public final void componentShown(
         final ComponentEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'componentShown' lanzado.");
   }
  }
  
  /**
    * 
    */
  public final void componentHidden(
         final ComponentEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'componentHidden' lanzado.");
   }
  }
 }