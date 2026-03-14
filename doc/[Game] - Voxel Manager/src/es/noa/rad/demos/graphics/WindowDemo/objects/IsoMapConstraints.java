package es.noa.rad.demos.graphics.WindowDemo.objects;

 import
  java.awt.Color;

 public class IsoMapConstraints {
  private Color MapBackGroundColor;

  public IsoMapConstraints() {
   super();
   this.MapBackGroundColor = new Color(110, 110, 110);
  }

  public IsoMapConstraints(
         final Color MapBackGroundColorParam) {
   super();
   this.MapBackGroundColor = MapBackGroundColorParam;
  }

  public final Color getMapBackGroundColor() {
   return MapBackGroundColor;
  }

  public final void setMapBackGroundColor(
         final Color MapBackGroundColorParam) {
   this.MapBackGroundColor = MapBackGroundColorParam;
  }
 
 }
