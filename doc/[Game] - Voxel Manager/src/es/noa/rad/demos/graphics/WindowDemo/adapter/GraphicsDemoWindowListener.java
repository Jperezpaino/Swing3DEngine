package es.noa.rad.demos.graphics.WindowDemo.adapter;

 import java.awt.Component;
 import java.awt.Frame;
 import java.awt.event.WindowAdapter;
 import java.awt.event.WindowEvent;
 import es.noa.rad.demos.graphics.WindowDemo.GraphicsDemo;

 public class GraphicsDemoWindowListener extends WindowAdapter {
  private static boolean DEBUG = false;
  // Ventana padre que controla el adaptador. 
  private GraphicsDemo windowComponent;
 
  public GraphicsDemoWindowListener(Component windowComponentParam) {
   this.windowComponent = (GraphicsDemo) windowComponentParam;
  }
  
  /** 
    * WINDOW_OPENED 
    * Se produce la primera vez que una ventana se hace visible.
    */
  public void windowOpened(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowOpened' lanzado.");
   }
   this.windowComponent.windowActionOpen();
  }
 
  /**
    * WINDOW_CLOSING
    * Se produce como resultado al pulsar es icono de cierre o cerrar la ventana
    * seleccionándolo en el menú del sistema.
    */
  public void windowClosing(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowClosing' lanzado.");
   }
   this.windowComponent.dispose();
   System.exit(0);  
  }
  
  /** 
    * WINDOW_CLOSED
    * Se produce cuando la ventana ha sido cerrada.
    */
  public void windowClosed(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowClosing' lanzado.");
   }
  }
  
  /** 
    * WINDOW_ICONIFIED
    * Se produce cuando la ventana está minimizada y se reduce a un icono en la
    * barra de tareas.
    */
  public void windowIconified(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowIconified' lanzado.");
   }
  }
  
  /** 
    * WINDOW_DEICONIFIED
    * Se produce cuando la ventana se restaura desde un icono.
    */ 
  public void windowDeiconified(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowDeiconified' lanzado.");
   }
  }
  
  /** 
    * WINDOW_ACTIVATED
    * Se produce cuando se activa la ventana y obtiene el foco, es decir cuando
    * otro componente de interfaz gráfica de usuario tiene el foco, se puede
    * hacer que la ventana obtenga el foco haciendo clic sobre un componente.
    */  
  public void windowActivated(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowActivated' lanzado.");
   }
  }

  /** 
    * WINDOW_DEACTIVATED
    * Se produce cuando se desactiva la ventana ypierde el foco, es decir al
    * hacer clic en otra ventana causaría este evento.
    */ 
  public void windowDeactivated(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowDeactivated' lanzado.");
   }
  }
  
  /** 
    * WINDOW_STATE_CHANGED
    * Se produce cuando cambia el estado de la ventana por ejemplo cuando se
    * maximiza o minimiza.
    */
  public void windowStateChanged(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowStateChanged' lanzado.");
   }
   if ((eventParam.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
    if(DEBUG) {
     System.out.println("Evento 'windowStateChanged' tipo 'Minimizar' lanzado.");
    }
    this.windowComponent.windowActionResized();
   } else if ((eventParam.getNewState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
    if(DEBUG) {
     System.out.println("Evento 'windowStateChanged' tipo 'Maximizar' lanzado.");
    }
    this.windowComponent.windowActionResized();
   }
  }
  
  /** 
   * WINDOW_GAINED_FOCUS
   * Se produce cuando la ventana gana el foque, esto implica que la ventana o
   * uno de sus componentes recibirán eventos de teclado.
   */  
  public void windowGainedFocus(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowGainedFocus' lanzado.");
   }
  }

  /** 
   * WINDOW_LOST_FOCUS
   * Se produce cuando la ventana pierde el foco. Esto implica que los eventos
   * de teclado no serán entregados a la ventana o cualquiera de sus
   * componentes.
   */    
  public void windowLostFocus(WindowEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'windowLostFocus' lanzado.");
   }
  }

 }