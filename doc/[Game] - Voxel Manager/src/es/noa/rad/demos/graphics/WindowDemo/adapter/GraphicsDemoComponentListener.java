package es.noa.rad.demos.graphics.WindowDemo.adapter;

 import java.awt.Component;
 import java.awt.event.ComponentAdapter;
 import java.awt.event.ComponentEvent;
 import es.noa.rad.demos.graphics.WindowDemo.GraphicsDemo;

 public class GraphicsDemoComponentListener extends ComponentAdapter {
  private static boolean DEBUG = true;
  // Ventana padre que controla el adaptador.  
  private GraphicsDemo windowComponent;
 
  public GraphicsDemoComponentListener(Component windowComponentParam) {
   this.windowComponent = (GraphicsDemo) windowComponentParam;
  }
 
  public void componentResized(ComponentEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'componentResized' lanzado.");
   }
   this.windowComponent.windowActionResized();
  }

  public void componentMoved(ComponentEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'componentMoved' lanzado.");
   }
  }

  public void componentShown(ComponentEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'componentShown' lanzado.");
   }
  }

  public void componentHidden(ComponentEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'componentHidden' lanzado.");
   }
  }
 }