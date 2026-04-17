package es.noa.rad.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GraphicsUtil {
  
  public final BufferedImage scaleImage(
		 final BufferedImage originalImage,
		 final int width,
		 final int height) {
    final BufferedImage scaledImage
      = new BufferedImage(width, height, originalImage.getType());
    final Graphics2D graphics2D
      = scaledImage.createGraphics();
    
    graphics2D.drawImage(originalImage, 0, 0, width, height, null);
    graphics2D.dispose();
    
    return scaledImage; 
   }

}
