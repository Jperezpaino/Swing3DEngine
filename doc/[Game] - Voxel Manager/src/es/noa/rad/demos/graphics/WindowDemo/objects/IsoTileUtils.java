package es.noa.rad.demos.graphics.WindowDemo.objects;

 import
  java.awt.Graphics2D;
 import
  java.awt.Point;
import java.awt.Polygon;

 public class IsoTileUtils {
  // Dibujamos la maya de una tile completa.
  public final static void drawTileLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftLayer(
    graphics2D, tileParam, pointParam);
  }
  
  // Dibujamos la maya de una tile completa con el borde marcado.
  public final static void drawTileLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftLayerBorder(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos la maya de una tile inferior completa.
  public final static void drawBottomTileLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftLayer(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos la maya de una tile inferior completa con el borde marcado.
  public final static void drawBottomTileLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftLayerBorder(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos la maya de una tile superior completa.
  public final static void drawTopTileLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftLayer(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos la maya de una tile superior completa con el borde marcado.
  public final static void drawTopTileLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightLayerBorder(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftLayerBorder(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos la maya de cuadro inferior.
  public final static void drawBottomLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento de cuadro inferior.
  public final static void drawBottomSegment(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);
  }

  // Dibujamos la maya de cuadro inferior con el borde marcado.
  public final static void drawBottomLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior derecho.
  public final static void drawBottomRightLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento de cuadro inferior derecho.
  public final static void drawBottomRightSegment(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);
  }

  // Dibujamos la maya de cuadro inferior derecho con el borde marcado.
  public final static void drawBottomRightLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior izquierdo.
  public final static void drawBottomLeftLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento de cuadro inferior izquierdo.
  public final static void drawBottomLeftSegment(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameStroke());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);
  }

  // Dibujamos la maya de cuadro inferior izquierdo con el borde marcado.
  public final static void drawBottomLeftLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior.
  public final static void drawTopLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior con el borde marcado.
  public final static void drawTopLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior derecho.
  public final static void drawTopRightLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior derecho con el borde marcado.
  public final static void drawTopRightLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior izquierdo.
  public final static void drawTopLeftLayer(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameStroke());
   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior izquierdo con el borde marcado.
  public final static void drawTopLeftLayerBorder(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior con el border superior marcado.
  public final static void drawBottomLayerBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomBorderTop(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior del cuadro inferior.
  public final static void drawBottomBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior con el border inferior marcado.
  public final static void drawBottomLayerBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomBorderBottom(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior del cuadro inferior.
  public final static void drawBottomBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior con el border derecho marcado.
  public final static void drawBottomLayerBorderRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomBorderRight(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde derecho del cuadro inferior.
  public final static void drawBottomBorderRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior con el border izquierdo marcado.
  public final static void drawBottomLayerBorderLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomBorderLeft(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde izquierdo del cuadro inferior.
  public final static void drawBottomBorderLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior con el border superior derecho marcado.
  public final static void drawBottomLayerBorderTopRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomBorderTopRight(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior derecho del cuadro inferior.
  public final static void drawBottomBorderTopRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior con el border superior izquierdo marcado.
  public final static void drawBottomLayerBorderTopLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomBorderTopLeft(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior izquierdo del cuadro inferior.
  public final static void drawBottomBorderTopLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior con el border inferior derecho marcado.
  public final static void drawBottomLayerBorderBottomRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomBorderBottomRight(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior derecho del cuadro inferior.
  public final static void drawBottomBorderBottomRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior con el border inferior izquierdo marcado.
  public final static void drawBottomLayerBorderBottomLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomBorderBottomLeft(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior izquierdo del cuadro inferior.
  public final static void drawBottomBorderBottomLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior derecho con el border superior marcado.
  public final static void drawBottomRightLayerBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightBorderTop(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior del cuadro inferior derecho.
  public final static void drawBottomRightBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior derecho con el border inferior marcado.
  public final static void drawBottomRightLayerBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightBorderBottom(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior del cuadro inferior derecho.
  public final static void drawBottomRightBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior derecho con el border superior alto marcado.
  public final static void drawBottomRightLayerBorderTopHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightBorderTopHigh(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior alto del cuadro inferior derecho.
  public final static void drawBottomRightBorderTopHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior derecho con el border superior lateral marcado.
  public final static void drawBottomRightLayerBorderTopSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightBorderTopSide(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior lateral del cuadro inferior derecho.
  public final static void drawBottomRightBorderTopSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior derecho con el border inferior alto marcado.
  public final static void drawBottomRightLayerBorderBottomHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightBorderBottomHigh(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior alto del cuadro inferior derecho.
  public final static void drawBottomRightBorderBottomHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior derecho con el border inferior lateral marcado.
  public final static void drawBottomRightLayerBorderBottomSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomRightBorderBottomSide(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior lateral del cuadro inferior derecho.
  public final static void drawBottomRightBorderBottomSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior izquierdo con el border superior marcado.
  public final static void drawBottomLeftLayerBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftBorderTop(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior del cuadro inferior izquierdo.
  public final static void drawBottomLeftBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior izquierdo con el border inferior marcado.
  public final static void drawBottomLeftLayerBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftBorderBottom(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior del cuadro inferior izquierdo.
  public final static void drawBottomLeftBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior izquierdo con el border superior alto marcado.
  public final static void drawBottomLeftLayerBorderTopHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftBorderTopHigh(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior alto del cuadro inferior izquierdo.
  public final static void drawBottomLeftBorderTopHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior izquierdo con el border superior lateral marcado.
  public final static void drawBottomLeftLayerBorderTopSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftBorderTopSide(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior lateral del cuadro inferior izquierdo.
  public final static void drawBottomLeftBorderTopSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior izquierdo con el border inferior alto marcado.
  public final static void drawBottomLeftLayerBorderBottomHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftBorderBottomHigh(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior alto del cuadro inferior izquierdo.
  public final static void drawBottomLeftBorderBottomHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro inferior izquierdo con el border inferior lateral marcado.
  public final static void drawBottomLeftLayerBorderBottomSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawBottomLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawBottomLeftBorderBottomSide(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferiorlateral del cuadro inferior izquierdo.
  public final static void drawBottomLeftBorderBottomSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior con el border superior marcado.
  public final static void drawTopLayerBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopBorderTop(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior del cuadro superior.
  public final static void drawTopBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior con el border inferior marcado.
  public final static void drawTopLayerBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopBorderBottom(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior del cuadro superior.
  public final static void drawTopBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior con el border superior derecho marcado.
  public final static void drawTopLayerBorderTopRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopBorderTopRight(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior derecho del cuadro superior.
  public final static void drawTopBorderTopRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior con el border superior izquierdo marcado.
  public final static void drawTopLayerBorderTopLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopBorderTopLeft(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior izquierdo del cuadro superior.
  public final static void drawTopBorderTopLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior con el border inferior derecho marcado.
  public final static void drawTopLayerBorderBottomRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopBorderBottomRight(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior derecho del cuadro superior.
  public final static void drawTopBorderBottomRight(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior con el border inferior izquierdo marcado.
  public final static void drawTopLayerBorderBottomLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopBorderBottomLeft(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior izquierdo del cuadro superior.
  public final static void drawTopBorderBottomLeft(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior derecho con el border superior marcado.
  public final static void drawTopRightLayerBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightBorderTop(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior del cuadro superior derecho.
  public final static void drawTopRightBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior derecho con el border inferior marcado.
  public final static void drawTopRightLayerBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightBorderBottom(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior del cuadro superior derecho.
  public final static void drawTopRightBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior derecho con el border superior alto marcado.
  public final static void drawTopRightLayerBorderTopHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightBorderTopHigh(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior alto del cuadro superior derecho.
  public final static void drawTopRightBorderTopHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior derecho con el border superior lateral marcado.
  public final static void drawTopRightLayerBorderTopSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightBorderTopSide(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior lateral del cuadro superior derecho.
  public final static void drawTopRightBorderTopSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior derecho con el border inferior alto marcado.
  public final static void drawTopRightLayerBorderBottomHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightBorderBottomHigh(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior alto del cuadro superior derecho.
  public final static void drawTopRightBorderBottomHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior derecho con el border inferior lateral marcado.
  public final static void drawTopRightLayerBorderBottomSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopRightLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopRightBorderBottomSide(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferiorlateral del cuadro superior derecho.
  public final static void drawTopRightBorderBottomSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior izquierdo con el border superior marcado.
  public final static void drawTopLeftLayerBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftBorderTop(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior del cuadro superior izquierdo.
  public final static void drawTopLeftBorderTop(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior izquierdo con el border inferior marcado.
  public final static void drawTopLeftLayerBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftBorderBottom(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior del cuadro superior izquierdo.
  public final static void drawTopLeftBorderBottom(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior izquierdo con el border superior alto marcado.
  public final static void drawTopLeftLayerBorderTopHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftBorderTopHigh(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior alto del cuadro superior izquierdo.
  public final static void drawTopLeftBorderTopHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior izquierdo con el border superior lateral marcado.
  public final static void drawTopLeftLayerBorderTopSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftBorderTopSide(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde superior lateral del cuadro superior izquierdo.
  public final static void drawTopLeftBorderTopSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior izquierdo con el border inferior alto marcado.
  public final static void drawTopLeftLayerBorderBottomHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftBorderBottomHigh(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior alto del cuadro superior izquierdo.
  public final static void drawTopLeftBorderBottomHigh(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
  }

  // Dibujamos la maya de cuadro superior izquierdo con el border inferior lateral marcado.
  public final static void drawTopLeftLayerBorderBottomSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Operaciones previas.
   IsoTileUtils.drawTopLeftLayer(
    graphics2D, tileParam, pointParam);
   IsoTileUtils.drawTopLeftBorderBottomSide(
    graphics2D, tileParam, pointParam);
  }

  // Dibujamos el borde inferior lateral del cuadro superior izquierdo.
  public final static void drawTopLeftBorderBottomSide(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileWireFrameBorderColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile según su tipo.
  public final static void drawPhysicalTile(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam,
         final int typeTileParam) {
   if(typeTileParam == 0x01) {
    IsoTileUtils.drawPhysicalTileStyleBlock(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x02) {
    IsoTileUtils.drawPhysicalTileStyleInclinedNorthWest(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x03) {
    IsoTileUtils.drawPhysicalTileStyleInclinedNorthEast(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x04) {
    IsoTileUtils.drawPhysicalTileStyleInclinedSouthWest(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x05) {
    IsoTileUtils.drawPhysicalTileStyleInclinedSouthEast(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x06) {
    IsoTileUtils.drawPhysicalTileStyleInclinedNorth(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x07) {
    IsoTileUtils.drawPhysicalTileStyleInclinedSouth(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x08) {
    IsoTileUtils.drawPhysicalTileStyleInclinedWest(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x09) {
    IsoTileUtils.drawPhysicalTileStyleInclinedEast(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x0A) {
    IsoTileUtils.drawPhysicalTileStyleInclinedNorthWestEast(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x0B) {
    IsoTileUtils.drawPhysicalTileStyleInclinedSouthWestEast(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x0C) {
    IsoTileUtils.drawPhysicalTileStyleInclinedWestNorthSouth(
     graphics2DParam, tileParam, pointParam);
   } else if(typeTileParam == 0x0D) {
    IsoTileUtils.drawPhysicalTileStyleInclinedEastNorthSouth(
     graphics2DParam, tileParam, pointParam);
   }
  }

  // Dibujamos el segmento físico de una tile completa.
  public final static void drawPhysicalTileStyleBlock(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Norte - Sur.
  public final static void drawPhysicalTileStyleInclinedNorthWest(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Norte - Oeste.
  public final static void drawPhysicalTileStyleInclinedNorthEast(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Sur - Oeste.
  public final static void drawPhysicalTileStyleInclinedSouthWest(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Sur - Este.
  public final static void drawPhysicalTileStyleInclinedSouthEast(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Norte.
  public final static void drawPhysicalTileStyleInclinedNorth(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Sur.
  public final static void drawPhysicalTileStyleInclinedSouth(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Oeste.
  public final static void drawPhysicalTileStyleInclinedWest(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Este.
  public final static void drawPhysicalTileStyleInclinedEast(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Norte - Oeste - Este.
  public final static void drawPhysicalTileStyleInclinedNorthWestEast(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(11).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(11).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(10).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(10).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Sur - Oeste - Este.
  public final static void drawPhysicalTileStyleInclinedSouthWestEast(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(6).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(6).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(7).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(7).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Oeste - Norte - Sur.
  public final static void drawPhysicalTileStyleInclinedWestNorthSouth(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileLeftBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(9).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(9).getY() + pointParam.getY()))
   );
  }

  // Dibujamos el segmento físico de una tile con inclinación Este - Norte - Sur.
  public final static void drawPhysicalTileStyleInclinedEastNorthSouth(
         final Graphics2D graphics2DParam,
         final IsoTile tileParam,
         final Point pointParam) {
   Graphics2D graphics2D = (Graphics2D) graphics2DParam;
   Polygon polygon = null;

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileTopBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(0).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(0).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(1).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(1).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(3).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(3).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(5).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(5).getY() + pointParam.getY()))
   );

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBackGroundSelectedColor());

   // Dibujamos.
   polygon = new Polygon();
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   polygon.addPoint(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.fillPolygon(polygon);

   // Asignamos colores.
   graphics2D.setPaint(
    tileParam.getTileConstraints().getTileRightBorderSelectedColor());
   graphics2D.setStroke(
    tileParam.getTileConstraints().getTileWireFrameBorderStroke());

   // Dibujamos.
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(4).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(4).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY()))
   );
   graphics2D.drawLine(
    ((int) (tileParam.getTilePointsElement(2).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(2).getY() + pointParam.getY())),
    ((int) (tileParam.getTilePointsElement(8).getX() + pointParam.getX())),
    ((int) (tileParam.getTilePointsElement(8).getY() + pointParam.getY()))
   );
  }
 }
