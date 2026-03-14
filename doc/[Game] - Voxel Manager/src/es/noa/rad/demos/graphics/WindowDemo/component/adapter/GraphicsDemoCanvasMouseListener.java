package es.noa.rad.demos.graphics.WindowDemo.component.adapter;

 import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import es.noa.rad.demos.graphics.WindowDemo.component.GraphicsDemoCanvas;

 public class GraphicsDemoCanvasMouseListener extends MouseAdapter implements MouseWheelListener {
  private static boolean DEBUG = true;
  private GraphicsDemoCanvas parentComponent;
  
  public GraphicsDemoCanvasMouseListener(
         GraphicsDemoCanvas parentComponentParam) {
   super();
   this.parentComponent = parentComponentParam;
  }

  public void mouseClicked(MouseEvent eventParam) {
   if(DEBUG) {
    //System.out.println("Evento 'mouseClicked' lanzado.");
   }
  }
  public void mousePressed(MouseEvent eventParam) {
   if(DEBUG) {
    //System.out.println("Evento 'mousePressed' lanzado.");
   }
  }
  public void mouseReleased(MouseEvent eventParam) {
   if(DEBUG) {
    //System.out.println("Evento 'mouseReleased' lanzado.");
   }
  }
  public void mouseEntered(MouseEvent eventParam) {
   if(DEBUG) {
    //System.out.println("Evento 'mouseEntered' lanzado.");
   }
  }
  public void mouseExited(MouseEvent eventParam) {
   if(DEBUG) {
    //System.out.println("Evento 'mouseExited' lanzado.");
   }
  }
  public void mouseWheelMoved(MouseWheelEvent eventParam) {
   //System.out.println("Evento 'mouseWheelMoved' lanzado.");
   //System.out.println(eventParam.isControlDown());
   //if (eventParam.isControlDown()) // Control pulsado
   //{
   if(this.parentComponent.getMap().isSelectedTile()) {
    int actionMapHigh = this.parentComponent.getMap().getSelectedTileHigh();
    if (eventParam.getWheelRotation() < 0) {
     actionMapHigh++;    
    } else {
     actionMapHigh--;    
    }
    if(((this.parentComponent.getMap().getMapHigh() - 1) >= actionMapHigh) && (actionMapHigh >= 0)) {    
     this.parentComponent.getMap().setSelectedTileHigh(actionMapHigh);
     this.parentComponent.repaint();
    }
   }
   
   /*} else {
    // pass the event on to the scroll pane
    this.parentComponent.getParent().dispatchEvent(eventParam);
   }*/
  }
  public void mouseDragged(MouseEvent eventParam) {
   if(DEBUG) {
    //System.out.println("Evento 'mouseDragged' lanzado.");
   }
  }
  public void mouseMoved(MouseEvent eventParam) {
   if(DEBUG) {
    //System.out.println("Evento 'mouseMoved' lanzado.");
   }
  }
  
  
  
  
  
  
  
  
  /*public void mousePressed(MouseEvent eventParam) {
   if (ellipse.contains(e.getX(), e.getY())) {
     selectedShape = ellipse;
     if (handleRectangle != null)
       handleRectangle = ellipse.getBounds2D();
   } else {
     handleRectangle = null;
   }
   canvas.repaint();
   x1 = e.getX();
   y1 = e.getY();
 }

 public void mouseReleased(MouseEvent e) {
   if (ellipse.contains(e.getX(), e.getY())) {
     handleRectangle = ellipse.getBounds2D();
     selectedShape = ellipse;
   }
   canvas.repaint();
 }

 public void mouseClicked(MouseEvent e) {
   if (ellipse.contains(e.getX(), e.getY())) {
     selectedShape = ellipse;
     handleRectangle = ellipse.getBounds2D();
   } else {
     if (handleRectangle != null)
       handleRectangle = null;
   }
   canvas.repaint();
 }*/
 }

