package es.noa.rad.demos.graphics.WindowDemo.component.layer.definition;

 import
  java.awt.image.BufferedImage;
 import
  javax.swing.JComponent;
 
public interface GraphicsLayerDefinition {
 public BufferedImage getLayerGraphicsComponent();
 
 public void setLayerGraphicsComponent(
        final BufferedImage layerGraphicsComponentParam);
 
 public BufferedImage getLayer(
        final JComponent graphicsCanvasObjectParam);
}
