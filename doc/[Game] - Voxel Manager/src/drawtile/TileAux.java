package drawtile;

 import
  java.awt.Color;
 import
  java.awt.Graphics2D;
 import
  java.awt.Point;
 import
  java.awt.Polygon;
 import
  java.util.ArrayList;

 public class TileAux {
  /** Lista que contendra las coordenadas de los puntos de control para dibujar la tile que conforma el mapa. */
  private ArrayList<Point> tilePoints;
  private Point startDrawPoint;
  
  public TileAux(
   final Point startDrawPointParam) {
   this.startDrawPoint = startDrawPointParam;   
   this.tilePoints = new ArrayList<>();
   Point controlPoint;   
   // Cuadro inferior, punto superior inicial. (0)
   controlPoint = new Point(0, 0);
   this.tilePoints.add(controlPoint);
   // Cuadro inferior, punto superior final. (1)
   controlPoint = new Point(1, 0);
   this.tilePoints.add(controlPoint);
   // Cuadro inferior, punto medio inicial. (2)
   controlPoint = new Point(-13, 6);
   this.tilePoints.add(controlPoint);
   // Cuadro inferior, punto medio final. (3)
   controlPoint = new Point(14, 6);
   this.tilePoints.add(controlPoint);
   // Cuadro inferior, punto inferior inicial. (4)
   controlPoint = new Point(0, 12);
   this.tilePoints.add(controlPoint);
   // Cuadro inferior, punto inferior final. (5)
   controlPoint = new Point(1, 12);
   this.tilePoints.add(controlPoint);   
   // Cuadro superior, punto superior inicial. (6)
   controlPoint = new Point(0, -8);
   this.tilePoints.add(controlPoint);
   // Cuadro superior, punto superior final. (7)
   controlPoint = new Point(1, -8);
   this.tilePoints.add(controlPoint);
   // Cuadro superior, punto medio inicial. (8)
   controlPoint = new Point(-13, -2);
   this.tilePoints.add(controlPoint);
   // Cuadro superior, punto medio final. (9)
   controlPoint = new Point(14, -2);
   this.tilePoints.add(controlPoint);
   // Cuadro superior, punto inferior inicial. (10)
   controlPoint = new Point(0, 4);
   this.tilePoints.add(controlPoint);
   // Cuadro superior, punto inferior final. (11)
   controlPoint = new Point(1, 4);
   this.tilePoints.add(controlPoint);
  }

  public final void drawBorderTile(final Graphics2D graphics2DParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   // Dibujamos las lineas.
   graphics2D.setColor(Color.DARK_GRAY);  
   
   // Dibujamos el cuadro inferior 
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(0).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(0).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(1).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(1).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(4).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(4).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(5).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(5).getY() + startDrawPoint.getY()))
   );
   
   // Dibujamos el cuadro lateral izquierdo trasero.
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(6).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(6).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(0).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(0).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(0).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(0).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(6).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(6).getY() + startDrawPoint.getY()))
   );
   
   // Dibujamos el cuadro lateral derecho trasero.
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(7).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(7).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(1).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(1).getY() + startDrawPoint.getY()))
   );   
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(1).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(1).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(7).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(7).getY() + startDrawPoint.getY()))
   );

   // Dibujamos el cuadro superior
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(6).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(6).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(7).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(7).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(10).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(10).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(11).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(11).getY() + startDrawPoint.getY()))
   );
   
   // Dibujamos el cuadro lateral izquierdo delantero.
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(10).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(10).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(10).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(10).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(4).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(4).getY() + startDrawPoint.getY()))
   );   
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(4).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(4).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY()))
   );
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY()))
   );   
   
   // Dibujamos el cuadro lateral derecho delantero.
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(11).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(11).getY() + startDrawPoint.getY()))
   ); 
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(11).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(11).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(5).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(5).getY() + startDrawPoint.getY()))
   );   
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(5).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(5).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY()))
   );   
   graphics2D.drawLine(
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY())),
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY()))
   );      
   
  }
  
  public final void drawBackgroundTile(final Graphics2D graphics2DParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;
   
   // Dibujamos las lineas.
   graphics2D.setColor(Color.GRAY);
   
   // Dibujamos el cuadro inferior 
   polygon = new Polygon();
   polygon.addPoint(   
    ((int) (this.tilePoints.get(0).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(0).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(   
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(       
    ((int) (this.tilePoints.get(4).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(4).getY() + startDrawPoint.getY()))
   );   
   polygon.addPoint(       
    ((int) (this.tilePoints.get(5).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(5).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(  
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(  
    ((int) (this.tilePoints.get(1).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(1).getY() + startDrawPoint.getY()))
   );  
   graphics2D.fillPolygon(polygon);
   
   // Dibujamos el cuadro lateral izquierdo trasero.
   polygon = new Polygon();   
   polygon.addPoint(
    ((int) (this.tilePoints.get(6).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(6).getY() + startDrawPoint.getY()))
   ); 
   polygon.addPoint(    
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(
    ((int) (this.tilePoints.get(0).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(0).getY() + startDrawPoint.getY()))
   );
   graphics2D.fillPolygon(polygon);
   
   // Dibujamos el cuadro lateral derecho trasero.
   polygon = new Polygon();   
   polygon.addPoint(
    ((int) (this.tilePoints.get(7).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(7).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY()))
   );   
   polygon.addPoint(
    ((int) (this.tilePoints.get(1).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(1).getY() + startDrawPoint.getY()))
   );
   graphics2D.fillPolygon(polygon);
   
   // Dibujamos el cuadro superior
   polygon = new Polygon();   
   polygon.addPoint(
    ((int) (this.tilePoints.get(6).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(6).getY() + startDrawPoint.getY()))
   ); 
   polygon.addPoint(    
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(
    ((int) (this.tilePoints.get(10).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(10).getY() + startDrawPoint.getY()))
   ); 
   polygon.addPoint(
    ((int) (this.tilePoints.get(11).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(11).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY()))
   ); 
   polygon.addPoint(    
    ((int) (this.tilePoints.get(7).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(7).getY() + startDrawPoint.getY()))
   );
   graphics2D.fillPolygon(polygon);
   
   // Dibujamos el cuadro lateral izquierdo delantero.
   polygon = new Polygon();   
   polygon.addPoint(
    ((int) (this.tilePoints.get(8).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(8).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(
    ((int) (this.tilePoints.get(10).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(10).getY() + startDrawPoint.getY()))
   );   
   polygon.addPoint(
    ((int) (this.tilePoints.get(4).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(4).getY() + startDrawPoint.getY()))
   );
   polygon.addPoint(
    ((int) (this.tilePoints.get(2).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(2).getY() + startDrawPoint.getY()))
   );   
   graphics2D.fillPolygon(polygon);
   
   // Dibujamos el cuadro lateral derecho delantero.
   polygon = new Polygon();   
   polygon.addPoint(
    ((int) (this.tilePoints.get(9).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(9).getY() + startDrawPoint.getY()))
   ); 
   polygon.addPoint(
    ((int) (this.tilePoints.get(11).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(11).getY() + startDrawPoint.getY()))
   );   
   polygon.addPoint(
    ((int) (this.tilePoints.get(5).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(5).getY() + startDrawPoint.getY()))
   );   
   polygon.addPoint(
    ((int) (this.tilePoints.get(3).getX() + startDrawPoint.getX())),
    ((int) (this.tilePoints.get(3).getY() + startDrawPoint.getY()))
   );      
  }
 }



