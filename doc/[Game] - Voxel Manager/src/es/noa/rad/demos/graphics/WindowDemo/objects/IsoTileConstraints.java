package es.noa.rad.demos.graphics.WindowDemo.objects;

 import
  java.awt.BasicStroke;
 import
  java.awt.Color;
 import
  java.awt.Stroke;

 public class IsoTileConstraints {
  private Color tileWireFrameColor;
  private Stroke tileWireFrameStroke;
  private Color tileWireFrameBorderColor;
  private Stroke tileWireFrameBorderStroke;
  private Color tileBackGroundColor;
  private Color tileTopBackGroundSelectedColor;
  private Color tileTopBorderSelectedColor;
  private Color tileRightBackGroundSelectedColor;
  private Color tileRightBorderSelectedColor;
  private Color tileLeftBackGroundSelectedColor;
  private Color tileLeftBorderSelectedColor;

  public IsoTileConstraints() {
   super();
   this.tileWireFrameColor = new Color(130, 130, 130);
   this.tileWireFrameStroke =  new BasicStroke(1.0F);
   this.tileWireFrameBorderColor = new Color(251, 184, 28);
   this.tileWireFrameBorderStroke = new BasicStroke(1.0F);
   this.tileBackGroundColor = new Color(110, 110, 110);
   this.tileTopBackGroundSelectedColor = new Color(191, 184, 159);
   this.tileTopBorderSelectedColor = new Color(148, 143, 124);
   this.tileRightBackGroundSelectedColor = new Color(68, 65, 56);
   this.tileRightBorderSelectedColor = new Color(56, 53, 47);
   this.tileLeftBackGroundSelectedColor = new Color(123, 118, 102);
   this.tileLeftBorderSelectedColor = new Color(97, 93, 81);
  }

  public IsoTileConstraints(
         final Color tileWireFrameColorParam,
         final Stroke tileWireFrameStrokeParam,
         final Color tileWireFrameBorderColorParam,
         final Stroke tileWireFrameBorderStrokeParam,
         final Color tileBackGroundColorParam,
         final Color tileTopBackGroundSelectedColorParam,
         final Color tileTopBorderSelectedColorParam,
         final Color tileRightBackGroundSelectedColorParam,
         final Color tileRightBorderSelectedColorParam,
         final Color tileLeftBackGroundSelectedColorParam,
         final Color tileLeftBorderSelectedColorParam) {
   super();
   this.tileWireFrameColor = tileWireFrameColorParam;
   this.tileWireFrameStroke = tileWireFrameStrokeParam;
   this.tileWireFrameBorderColor = tileWireFrameBorderColorParam;
   this.tileWireFrameBorderStroke = tileWireFrameBorderStrokeParam;
   this.tileBackGroundColor = tileBackGroundColorParam;
   this.tileTopBackGroundSelectedColor = tileTopBackGroundSelectedColorParam;
   this.tileTopBorderSelectedColor = tileTopBorderSelectedColorParam;
   this.tileRightBackGroundSelectedColor = tileRightBackGroundSelectedColorParam;
   this.tileRightBorderSelectedColor = tileRightBorderSelectedColorParam;
   this.tileLeftBackGroundSelectedColor = tileLeftBackGroundSelectedColorParam;
   this.tileLeftBorderSelectedColor = tileLeftBorderSelectedColorParam;
  }

  public final Color getTileWireFrameColor() {
   return this.tileWireFrameColor;
  }
  
  public final void setTileWireFrameColor(
         final Color tileWireFrameColorParam) {
   this.tileWireFrameColor = tileWireFrameColorParam;
  }

  public final Stroke getTileWireFrameStroke() {
   return this.tileWireFrameStroke;
  }

  public final void setTileWireFrameStroke(
         final Stroke tileWireFrameStrokeParam) {
   this.tileWireFrameStroke = tileWireFrameStrokeParam;
  }

  public final Color getTileWireFrameBorderColor() {
   return this.tileWireFrameBorderColor;
  }

  public final void setTileWireFrameBorderColor(
         final Color tileWireFrameBorderColorParam) {
   this.tileWireFrameBorderColor = tileWireFrameBorderColorParam;
  }

  public final Stroke getTileWireFrameBorderStroke() {
   return this.tileWireFrameBorderStroke;
  }

  public final void setTileWireFrameBorderStroke(
         final Stroke tileWireFrameBorderStrokeParam) {
   this.tileWireFrameBorderStroke = tileWireFrameBorderStrokeParam;
  }

  public final Color getTileBackGroundColor() {
   return tileBackGroundColor;
  }

  public final void setTileBackGroundColor(
         final Color tileBackGroundColorParam) {
   this.tileBackGroundColor = tileBackGroundColorParam;
  }

  
  public final Color getTileTopBackGroundSelectedColor() {
   return tileTopBackGroundSelectedColor;
  }
  

  public final void setTileTopBackGroundSelectedColor(
         final Color tileTopBackGroundSelectedColorParam) {
   this.tileTopBackGroundSelectedColor = tileTopBackGroundSelectedColorParam;
  }

  public final Color getTileTopBorderSelectedColor() {
   return this.tileTopBorderSelectedColor;
  }

  public final void setTileTopBorderSelectedColor(
         final Color tileTopBorderSelectedColorParam) {
   this.tileTopBorderSelectedColor = tileTopBorderSelectedColorParam;
  }

  public final Color getTileRightBackGroundSelectedColor() {
   return this.tileRightBackGroundSelectedColor;
  }

  public final void setTileRightBackGroundSelectedColor(
         final Color tileRightBackGroundSelectedColorParam) {
   this.tileRightBackGroundSelectedColor = tileRightBackGroundSelectedColorParam;
  }

  public final Color getTileRightBorderSelectedColor() {
   return this.tileRightBorderSelectedColor;
  }

  public final void setTileRightBorderSelectedColor(
         final Color tileRightBorderSelectedColorParam) {
   this.tileRightBorderSelectedColor = tileRightBorderSelectedColorParam;
  }

  public final Color getTileLeftBackGroundSelectedColor() {
   return this.tileLeftBackGroundSelectedColor;
  }

  public final void setTileLeftBackGroundSelectedColor(
         final Color tileLeftBackGroundSelectedColorParam) {
   this.tileLeftBackGroundSelectedColor = tileLeftBackGroundSelectedColorParam;
  }

  public final Color getTileLeftBorderSelectedColor() {
   return this.tileLeftBorderSelectedColor;
  }

  public final void setTileLeftBorderSelectedColor(
         final Color tileLeftBorderSelectedColorParam) {
   this.tileLeftBorderSelectedColor = tileLeftBorderSelectedColorParam;
  }

 }
