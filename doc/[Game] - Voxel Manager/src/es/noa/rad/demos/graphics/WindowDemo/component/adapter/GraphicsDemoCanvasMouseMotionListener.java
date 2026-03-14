package es.noa.rad.demos.graphics.WindowDemo.component.adapter;

 import
  java.awt.Point;
 import
  java.awt.Rectangle;
 import
  java.awt.event.MouseEvent;
 import
  java.awt.event.MouseMotionAdapter;
 import
  java.util.Iterator;
 import
  java.util.Map;
 import
  java.util.Map.Entry;
 import
  es.noa.rad.demos.graphics.WindowDemo.component.GraphicsDemoCanvas;

 public final class GraphicsDemoCanvasMouseMotionListener extends MouseMotionAdapter {
  private static boolean DEBUG = true;
  private GraphicsDemoCanvas parentComponent;
    
  public GraphicsDemoCanvasMouseMotionListener(
         GraphicsDemoCanvas parentComponentParam) {
   super();
   this.parentComponent = parentComponentParam;
  }

  public void mouseDragged(MouseEvent eventParam) {
   if(DEBUG) {
    System.out.println("Evento 'mouseDragged' lanzado.");
   }
  }
  
  public void mouseMoved(MouseEvent eventParam) {
   int xMousePosition = eventParam.getX();
   int yMousePosition = eventParam.getY();
   Point mousePoint = new Point(xMousePosition, yMousePosition);
   this.parentComponent.setCanvasMouseLastPosition(mousePoint);
   //System.out.println("MOVE:" + mousePoint);
   this.parentComponent.mouseMovedCanvasActionMapper(mousePoint);
   this.parentComponent.mouseMovedMapActionMapper(mousePoint);
  }
  
  
  

 }