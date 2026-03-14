package es.noa.rad.voxel.adapter;

 import
  java.awt.Frame;
 import
  java.awt.event.WindowAdapter;
 import
  java.awt.event.WindowEvent;
 import
  es.noa.rad.voxel.VoxelDemo;

 /**
   * 
   * @author Jorge Pérez Paino
   *
   */
 public class GraphicsWindowListener
        extends WindowAdapter {

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
  public GraphicsWindowListener(
         final VoxelDemo windowComponentParam) {
   super();
   this.windowComponent = windowComponentParam;
  }
  
  /**
    * 
    */
  public final void windowOpened(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowOpened' lanzado.");
   }
   this.windowComponent.getGraphicsCanvas().repaint();
  }
  
  /**
    * 
    */
  public final void windowClosing(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowClosing' lanzado.");
   }
   this.windowComponent.dispose();
   System.exit(0);  
  }
  
  /** 
    *
    */
  public final void windowClosed(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowClosing' lanzado.");
   }
  }
  
  /** 
    *
    */
  public final void windowIconified(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowIconified' lanzado.");
   }
  }
  
  /** 
    *
    */ 
  public final void windowDeiconified(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowDeiconified' lanzado.");
   }
  }
  
  /** 
    *
    */  
  public final void windowActivated(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowActivated' lanzado.");
   }
  }

  /** 
    *
    */ 
  public final void windowDeactivated(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowDeactivated' lanzado.");
   }
  }
  
  /** 
    *
    */
  public final void windowStateChanged(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowStateChanged' lanzado.");
   }
   if ((eventParam.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
    if(DEBUG) {
     // System.out.println("Evento 'windowStateChanged' tipo 'Minimizar' lanzado.");
    }
    this.windowComponent.getGraphicsCanvas().updateSizes();
   } else if ((eventParam.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
    if(DEBUG) {
     // System.out.println("Evento 'windowStateChanged' tipo 'Maximizar' lanzado.");
    }
    this.windowComponent.getGraphicsCanvas().updateSizes();
   }
  }
  
  /** 
    *
    */  
  public final void windowGainedFocus(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowGainedFocus' lanzado.");
   }
  }

  /** 
    *
    */    
  public final void windowLostFocus(
         final WindowEvent eventParam) {
   if(DEBUG) {
    // System.out.println("Evento 'windowLostFocus' lanzado.");
   }
  }

 }