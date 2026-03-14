package drawtile;

 import
  java.awt.BasicStroke;
 import
  java.awt.Color;
 import
  java.awt.Point;
 import
  java.awt.Stroke;

 public class TileDTO { 
  private int tileWidth; // Ancho total de la tile del mapa.
  private int tileHeight; // Alto total de la tile del mapa.
  private int tileHigh; // Alto del lateral de la tile del mapa.
  private float tileBaseHigh; // Alto de la base de la tile del mapa.  
  // Puntos de control para facilitar el dibujado del wireframe  
  private Point topMainStartPoint = new Point(13, 0);
  private Point topMainEndPoint = new Point(14, 0); 
  private Point middleMainStartPoint = new Point(0, 6);
  private Point middleMainEndPoint = new Point(27, 6);
  private Point bottomMainStartPoint = new Point(13, 12);
  private Point bottomMainEndPoint = new Point(14, 12);
  private Point topSideStartPoint = new Point(0, 14);
  private Point topSideEndPoint = new Point(27, 14);
  private Point bottomSideStartPoint = new Point(13, 20);
  private Point bottomSideEndPoint = new Point(14, 20);
  // Elementos para el dibujado
  private Color tileWireFrameColor = new Color(212, 229, 246);
  private Stroke tileWireFrameStroke = new BasicStroke(1.0F); //0.9F //0.2F
  private Color tileWireFrameBorderColor = new Color(210, 44, 44);
  private Stroke tileWireFrameBorderStroke = new BasicStroke(1.0F); //0.6F //0.1F  
  
  public TileDTO(
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
  this.topMainStartPoint = new Point(
   ((this.tileWidth / 2) - 1), 0);
  this.topMainEndPoint = new Point(
   (this.tileWidth / 2), 0);  
  this.middleMainStartPoint = new Point(
   0, ((int)(((this.tileBaseHigh * 2) - 1) / 2)));
  this.middleMainEndPoint = new Point(
   (this.tileWidth - 1), ((int)(((this.tileBaseHigh * 2) - 1) / 2)));
  this.bottomMainStartPoint = new Point(
   ((this.tileWidth / 2) - 1), ((int)((this.tileBaseHigh * 2) - 1)));
  this.bottomMainEndPoint = new Point(
   (this.tileWidth / 2), ((int)((this.tileBaseHigh * 2) - 1)));    
  this.topSideStartPoint = new Point(
   0, ((int)((this.tileBaseHigh * 2) + 1)));   
  this.topSideEndPoint = new Point(
   (this.tileWidth - 1), ((int)((this.tileBaseHigh * 2) + 1)));   
  this.bottomSideStartPoint = new Point(
    ((this.tileWidth / 2) - 1), (this.tileHeight - 1));
  this.bottomSideEndPoint = new Point(  
   (this.tileWidth / 2), (this.tileHeight - 1));
  }
  
  public final int getTileWidth() {
   return this.tileWidth;
  }

  public final void setTileWidth(final int tileWidthParam) {
   this.tileWidth = tileWidthParam;
  }

  public final int getTileHeight() {
   return this.tileHeight;
  }

  public final void setTileHeight(final int tileHeightParam) {
   this.tileHeight = tileHeightParam;
  }
  
  public final int getTileHigh() {
   return this.tileHigh;
  }

  public final void setTileHigh(final int tileHighParam) {
   this.tileHigh = tileHighParam;
  }
  
  public final float getTileBaseHigh() {
   return this.tileBaseHigh;
  }

  public final void setTileBaseHigh(final float tileBaseHighParam) {
   this.tileBaseHigh = tileBaseHighParam;
  }
  
  public final Point getTopMainStartPoint() {
   return this.topMainStartPoint;
  }

  public final void setTopMainStartPoint(final Point topMainStartPointParam) {
   this.topMainStartPoint = topMainStartPointParam;
  }

  public final Point getTopMainEndPoint() {
   return this.topMainEndPoint;
  }

  public final void setTopMainEndPoint(final Point topMainEndPointParam) {
   this.topMainEndPoint = topMainEndPointParam;
  }

  public final Point getMiddleMainStartPoint() {
   return this.middleMainStartPoint;
  }

  public final void setMiddleMainStartPoint(final Point middleMainStartPointParam) {
   this.middleMainStartPoint = middleMainStartPointParam;
  }

  public final Point getMiddleMainEndPoint() {
   return this.middleMainEndPoint;
  }

  public final void setMiddleMainEndPoint(final Point middleMainEndPointParam) {
   this.middleMainEndPoint = middleMainEndPointParam;
  }

  public final Point getBottomMainStartPoint() {
   return this.bottomMainStartPoint;
  }

  public final void setBottomMainStartPoint(final Point bottomMainStartPointParam) {
   this.bottomMainStartPoint = bottomMainStartPointParam;
  }

  public final Point getBottomMainEndPoint() {
   return this.bottomMainEndPoint;
  }

  public final void setBottomMainEndPoint(final Point bottomMainEndPointParam) {
   this.bottomMainEndPoint = bottomMainEndPointParam;
  }

  public final Point getTopSideStartPoint() {
   return this.topSideStartPoint;
  }

  public final void setTopSideStartPoint(final Point topSideStartPointParam) {
   this.topSideStartPoint = topSideStartPointParam;
  }

  public final Point getTopSideEndPoint() {
   return this.topSideEndPoint;
  }

  public final void setTopSideEndPoint(final Point topSideEndPointParam) {
   this.topSideEndPoint = topSideEndPointParam;
  }

  public final Point getBottomSideStartPoint() {
   return this.bottomSideStartPoint;
  }

  public final void setBottomSideStartPoint(final Point bottomSideStartPointParam) {
   this.bottomSideStartPoint = bottomSideStartPointParam;
  }

  public final Point getBottomSideEndPoint() {
   return this.bottomSideEndPoint;
  }

  public final void setBottomSideEndPoint(final Point bottomSideEndPointParam) {
   this.bottomSideEndPoint = bottomSideEndPointParam;
  }

  public final Color getTileWireFrameColor() {
   return this.tileWireFrameColor;
  }
  
  public final void setTileWireFrameColor(final Color tileWireFrameColorParam) {
   this.tileWireFrameColor = tileWireFrameColorParam;
  }
  
  public final Stroke getTileWireFrameStroke() {
   return this.tileWireFrameStroke;
  }
  
  public final void setTileWireFrameStroke(final Stroke tileWireFrameStrokeParam) {
   this.tileWireFrameStroke = tileWireFrameStrokeParam;
  }
  
  public final Color getTileWireFrameBorderColor() {
   return this.tileWireFrameBorderColor;
  }
  
  public final void setTileWireFrameBorderColor(final Color tileWireFrameBorderColorParam) {
   this.tileWireFrameBorderColor = tileWireFrameBorderColorParam;
  }

  public final Stroke getTileWireFrameBorderStroke() {
   return this.tileWireFrameBorderStroke;
  }

  public final void setTileWireFrameBorderStroke(final Stroke tileWireFrameBorderStrokeParam) {
   this.tileWireFrameBorderStroke = tileWireFrameBorderStrokeParam;
  }





//  private Point startDrawPoint;
// 
//  /*public TileDTO() {
//   super();
//   this.startDrawPoint = new Point();
//   this.startDrawPoint.setLocation(100, 100);   
//  }
//   
//  public TileDTO(
//    final Color backGroundColorParam,
//    final Color borderColorParam,
//    final Point startDrawPointParam) {
//   super();
//   this.backGroundColor = backGroundColorParam;
//   this.borderColor = borderColorParam;
//   this.startDrawPoint = startDrawPointParam;
//  }
//  
//  public TileDTO(
//    final Point startDrawPointParam) {
//   super();
//   this.startDrawPoint = startDrawPointParam;
//  }*/
//  
//
//  public final Point getStartDrawPoint() {
//   return this.startDrawPoint;
//  }
//
//  public final void setStartDrawPoint(final Point startDrawPointParam) {
//   this.startDrawPoint = startDrawPointParam;
//  }
//
//  public final void drawTile(final Graphics2D graphics2DParam) {
//   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
//   
//   // Dibujamos los fondos.
//   Polygon polygon = null; 
//   graphics2D.setColor(this.backGroundColor); 
//   
//   polygon = new Polygon();
//   polygon.addPoint(
//    ((int) (topMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (topMainStartPoint.getY() + startDrawPoint.getY()))
//   );
//   polygon.addPoint(
//    ((int) (topMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (topMainEndPoint.getY() + startDrawPoint.getY()))  
//   );
//   polygon.addPoint(
//    ((int) (middleMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainEndPoint.getY() + startDrawPoint.getY()))
//   );
//   polygon.addPoint(
//    ((int) (bottomMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomMainEndPoint.getY() + startDrawPoint.getY()))
//   );
//   polygon.addPoint(
//    ((int) (bottomMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomMainStartPoint.getY() + startDrawPoint.getY()))
//   );
//   polygon.addPoint(
//    ((int) (middleMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainStartPoint.getY() + startDrawPoint.getY()))
//   );
//   graphics2D.fillPolygon(polygon);
//   
//   polygon = new Polygon();
//   polygon.addPoint(
//    ((int) (middleMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainStartPoint.getY() + startDrawPoint.getY()))
//   );
//   polygon.addPoint(
//    ((int) (bottomMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomMainStartPoint.getY() + startDrawPoint.getY()))
//   );   
//   polygon.addPoint(
//    ((int) (bottomSideStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomSideStartPoint.getY() + startDrawPoint.getY()))   
//   );
//   polygon.addPoint(
//    ((int) (topSideStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (topSideStartPoint.getY() + startDrawPoint.getY()))
//   );
//   graphics2D.fillPolygon(polygon);
//   
//   polygon = new Polygon();
//   polygon.addPoint(
//    ((int) (middleMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainEndPoint.getY() + startDrawPoint.getY()))
//   );
//   polygon.addPoint(
//    ((int) (bottomMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomMainEndPoint.getY() + startDrawPoint.getY()))
//   );   
//   polygon.addPoint(
//    ((int) (bottomSideEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomSideEndPoint.getY() + startDrawPoint.getY()))   
//   );
//   polygon.addPoint(
//    ((int) (topSideEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (topSideEndPoint.getY() + startDrawPoint.getY()))
//   );
//   graphics2D.fillPolygon(polygon);
//   
//   this.drawBorderTile(graphics2D);
//  }
//  
//  public final void drawBorderTile(final Graphics2D graphics2DParam) {
//   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
//   
//   // Dibujamos las lineas.
//   graphics2D.setColor(this.borderColor);   
//   graphics2D.drawLine(
//    ((int) (topMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (topMainStartPoint.getY() + startDrawPoint.getY())),
//    ((int) (middleMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainStartPoint.getY() + startDrawPoint.getY()))
//   );
//   graphics2D.drawLine(
//    ((int) (topMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (topMainEndPoint.getY() + startDrawPoint.getY())),
//    ((int) (middleMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainEndPoint.getY() + startDrawPoint.getY()))
//   );
//   graphics2D.drawLine(
//    ((int) (middleMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainStartPoint.getY() + startDrawPoint.getY())),
//    ((int) (bottomMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomMainStartPoint.getY() + startDrawPoint.getY()))
//   );
//   graphics2D.drawLine(
//    ((int) (middleMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainEndPoint.getY() + startDrawPoint.getY())),
//    ((int) (bottomMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomMainEndPoint.getY() + startDrawPoint.getY())) 
//   );
//   graphics2D.drawLine(
//    ((int) (middleMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainStartPoint.getY() + startDrawPoint.getY())),
//    ((int) (topSideStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (topSideStartPoint.getY() + startDrawPoint.getY()))
//   );
//   graphics2D.drawLine(
//    ((int) (middleMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (middleMainEndPoint.getY() + startDrawPoint.getY())),
//    ((int) (topSideEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (topSideEndPoint.getY() + startDrawPoint.getY())) 
//   );
//   graphics2D.drawLine(
//    ((int) (bottomMainStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomMainStartPoint.getY() + startDrawPoint.getY())),
//    ((int) (bottomSideStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomSideStartPoint.getY() + startDrawPoint.getY()))    
//   );
//   graphics2D.drawLine(
//    ((int) (bottomMainEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomMainEndPoint.getY() + startDrawPoint.getY())),
//    ((int) (bottomSideEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomSideEndPoint.getY() + startDrawPoint.getY()))     
//   );   
//   graphics2D.drawLine(
//    ((int) (topSideStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (topSideStartPoint.getY() + startDrawPoint.getY())),
//    ((int) (bottomSideStartPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomSideStartPoint.getY() + startDrawPoint.getY()))      
//   );
//   graphics2D.drawLine(
//    ((int) (topSideEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (topSideEndPoint.getY() + startDrawPoint.getY())),
//    ((int) (bottomSideEndPoint.getX() + startDrawPoint.getX())),
//    ((int) (bottomSideEndPoint.getY() + startDrawPoint.getY()))    
//   );
//  }
//  
  
 }
