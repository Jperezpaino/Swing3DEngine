package drawtile;

 import java.awt.Color;
import
  java.awt.Graphics2D;
 import
  java.awt.Point;
import java.awt.Polygon;
import
  java.util.ArrayList;

 public class TileDimensionAux {
  /** Lista que contendra las coordenadas de los puntos de control para dibujar la tile que conforma el mapa. */
  private ArrayList<Point> tilePoints;
  /** Ancho total de la tile que conforma el mapa. */
  private int tileWidth;
  /** Alto total de la tile que conforma el mapa. */  
  private int tileHeight;
  /** Alto del lateral de la tile que conforma el mapa. */    
  private int tileHigh;
  /** Alto de la base de la tile que conforma el mapa. */    
  private float tileBaseHigh;  
  private Point startDrawPoint;
 
  
  
  public ArrayList<Point> getTilePoints() {
   return tilePoints;
  }

  public void setTilePoints(ArrayList<Point> tilePoints) {
   this.tilePoints = tilePoints;
  }

  public int getTileWidth() {
   return tileWidth;
  }

  public void setTileWidth(int tileWidth) {
   this.tileWidth = tileWidth;
  }

  public int getTileHeight() {
   return tileHeight;
  }

  public void setTileHeight(int tileHeight) {
   this.tileHeight = tileHeight;
  }

  public int getTileHigh() {
   return tileHigh;
  }

  public void setTileHigh(int tileHigh) {
   this.tileHigh = tileHigh;
  }

  public float getTileBaseHigh() {
   return tileBaseHigh;
  }

  public void setTileBaseHigh(float tileBaseHigh) {
   this.tileBaseHigh = tileBaseHigh;
  }

  public TileDimensionAux(
         final int tileWidthParam,
         final int tileHeightParam,
         final int tileHighParam,
         final float tileBaseHighParam) {
   super();
   this.tileWidth = tileWidthParam;
   this.tileHeight = tileHeightParam;
   this.tileHigh = tileHighParam;
   this.tileBaseHigh = tileBaseHighParam;
   
   // Recalculamos los puntos de control.
   this.tilePoints = new ArrayList<>();
   //28, 21, 8, 6.5F
   Point controlPoint;   
   
   // Cuadro inferior, punto superior inicial. (0)
   controlPoint = new Point(0, 0);
   this.tilePoints.add(controlPoint);
   
   // Cuadro inferior, punto superior final. (1)
   controlPoint = new Point(1, 0);
   this.tilePoints.add(controlPoint);
   
   // Cuadro inferior, punto medio inicial. (2)
   controlPoint = new Point(
    ((int)(((this.tileWidth / 2) - 1) * -1)),
    ((int)(((tileBaseHigh * 2) - 1) / 2))
   );
   this.tilePoints.add(controlPoint);
   
   // Cuadro inferior, punto medio final. (3)
   controlPoint = new Point(
    ((int)(this.tileWidth / 2)),
    ((int)(((tileBaseHigh * 2) - 1) / 2))
   );
   this.tilePoints.add(controlPoint);
   
   // Cuadro inferior, punto inferior inicial. (4)
   controlPoint = new Point(
    0,
    ((int)((tileBaseHigh * 2) - 1))
   );
   this.tilePoints.add(controlPoint);
   
   // Cuadro inferior, punto inferior final. (5)
   controlPoint = new Point(
    1,
    ((int)((tileBaseHigh * 2) - 1))
   );
   this.tilePoints.add(controlPoint); 
   
   // Cuadro superior, punto superior inicial. (6)
   controlPoint = new Point(
    0, 
    ((int)(this.tileHigh * -1))
   );
   this.tilePoints.add(controlPoint);
   
   // Cuadro superior, punto superior final. (7)
   controlPoint = new Point(
    1,
    ((int)(this.tileHigh * -1))
   );
   this.tilePoints.add(controlPoint);
   
   // Cuadro superior, punto medio inicial. (8)
   controlPoint = new Point(
    ((int)(((this.tileWidth / 2) - 1) * -1)),
    ((int) ((((tileBaseHigh * 2) - 1) / 2) - this.tileHigh))
   );
   this.tilePoints.add(controlPoint);
   
   // Cuadro superior, punto medio final. (9)
   controlPoint = new Point(
    (this.tileWidth / 2),
    ((int) ((((tileBaseHigh * 2) - 1) / 2) - this.tileHigh))
   );
   this.tilePoints.add(controlPoint);
   
   // Cuadro superior, punto inferior inicial. (10)
   controlPoint = new Point(
    0,
    ((int) (((tileBaseHigh * 2) - 1) - this.tileHigh))
   );
   this.tilePoints.add(controlPoint);
   
   // Cuadro superior, punto inferior final. (11)
   controlPoint = new Point(
    1,
    ((int) (((tileBaseHigh * 2) - 1) - this.tileHigh))
   );
   this.tilePoints.add(controlPoint);
  }
  
  public final Point getStartDrawPoint() {
   return this.startDrawPoint;
  }
  
  public final void setStartDrawPoint(final Point startDrawPointParam) {
   this.startDrawPoint = startDrawPointParam;
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



