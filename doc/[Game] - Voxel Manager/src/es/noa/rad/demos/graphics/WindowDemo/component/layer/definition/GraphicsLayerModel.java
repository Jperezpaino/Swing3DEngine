package es.noa.rad.demos.graphics.WindowDemo.component.layer.definition;

 import
  java.awt.image.BufferedImage;
 import
  javax.swing.JComponent;

 public abstract class GraphicsLayerModel implements GraphicsLayerDefinition {
  private BufferedImage layerGraphicsComponent;
  
  @Override
  public BufferedImage getLayerGraphicsComponent() {
   return layerGraphicsComponent;
  }
  
  @Override
  public void setLayerGraphicsComponent(
         final BufferedImage layerGraphicsComponent) {
   this.layerGraphicsComponent = layerGraphicsComponent;
  }

  @Override
  public BufferedImage getLayer(
         final JComponent graphicsCanvasObjectParam) {
   return this.getLayerGraphicsComponent();
  }
  
 }
