package es.noa.rad.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import es.noa.rad.GamePanel;

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

  public final int getXForCenteredText(
         final Graphics2D graphics2D,
         final GamePanel gamePanel,
         final String text) {
      
    final Rectangle2D textLengh
      = graphics2D.getFontMetrics().getStringBounds(text, graphics2D);
        
    return ((gamePanel.screenWidth / 2) - (((int) textLengh.getWidth()) / 2));
  }

  public final int getYForCenteredText(
         final Graphics2D graphics2D,
         final GamePanel gamePanel,
         final String text) {
      
    final Rectangle2D textLengh
      = graphics2D.getFontMetrics().getStringBounds(text, graphics2D);
        
    return ((gamePanel.screenHeight / 2) + (((int) textLengh.getHeight()) / 2));
  }

  public final void drawSubWindow(
	     final Graphics2D graphics2D,
	     final GamePanel gamePanel,
	     final int x,
	     final int y,
	     final int width,
	     final int height) {
    Color color = new Color(0, 0, 0);
    graphics2D.setColor(color);
    graphics2D.fillRoundRect(x, y, width, height, 35, 35);
  }

}
