package drawtile;

 import
  java.awt.Graphics2D;

 public class TileUtils {
  
  public final static void drawLeftTopLayer(
         final Graphics2D graphics2DParam,
         final TileDTO tileParam,
         final int xPointParam,
         final int yPointParam) {
   // Dibujamos la tapa superior izquierdo.
   graphics2DParam.setPaint(tileParam.getTileWireFrameColor());
   graphics2DParam.setStroke(tileParam.getTileWireFrameStroke());     
   graphics2DParam.drawLine(
    (xPointParam), (yPointParam),
    (xPointParam), (yPointParam - 15));
   graphics2DParam.drawLine(
    (xPointParam), (yPointParam),
    (xPointParam - 23), (yPointParam + 11));
   graphics2DParam.drawLine(
    (xPointParam - 23), (yPointParam + 11),
    (xPointParam - 23), (yPointParam - 4));
   graphics2DParam.drawLine(
    (xPointParam), (yPointParam - 15),
    (xPointParam - 23), (yPointParam - 4));
  }
  
  public final static void drawLeftTopLayerBorder(
   final Graphics2D graphics2DParam,
   final TileDTO tileParam,
   final int xPointParam,
   final int yPointParam) {
   // Dibujamos la tapa superior izquierdo.
   TileUtils.drawLeftTopLayer(
    graphics2DParam, tileParam, xPointParam, yPointParam);      
   // Dibujamos los bordes.
   graphics2DParam.setPaint(tileParam.getTileWireFrameBorderColor());
   graphics2DParam.setStroke(tileParam.getTileWireFrameBorderStroke());   
   graphics2DParam.drawLine(
    (xPointParam), (yPointParam),
    (xPointParam), (yPointParam - 15));
   graphics2DParam.drawLine(
    (xPointParam), (yPointParam),
    (xPointParam - 23), (yPointParam + 11));
   graphics2DParam.drawLine(
    (xPointParam - 23), (yPointParam + 11),
    (xPointParam - 23), (yPointParam - 4));
   graphics2DParam.drawLine(
    (xPointParam), (yPointParam - 15),
    (xPointParam - 23), (yPointParam - 4));
  }
  
  
  
  public final static void drawBottomLayer(
   final Graphics2D graphics2DParam,
   final TileDTO tileParam,
   final int xPointParam,
   final int yPointParam) {
   // Dibujamos la tapa inferior.
   graphics2DParam.setPaint(tileParam.getTileWireFrameColor());
   graphics2DParam.setStroke(tileParam.getTileWireFrameStroke());     
   graphics2DParam.drawLine(
    (xPointParam), (yPointParam),
    (xPointParam - 23), (yPointParam + 11));
   graphics2DParam.drawLine(
    (xPointParam + 1), (yPointParam),
    (xPointParam + 24), (yPointParam + 11));
   graphics2DParam.drawLine(
    (xPointParam - 23), (yPointParam + 11),
    (xPointParam), (yPointParam + 22));
   graphics2DParam.drawLine(
    (xPointParam + 24), (yPointParam + 11),
    (xPointParam + 1), (yPointParam + 22));
   }  
 }
