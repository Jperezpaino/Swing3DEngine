package es.noa.rad.object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import es.noa.rad.GamePanel;
import es.noa.rad.util.GraphicsUtil;

public class SuperObject {
  public BufferedImage image;
  public String name;
  public boolean collision;
  public int worldX;
  public int worldY;
  public Rectangle solidArea;
  public int solidAreaDefaultX;
  public int solidAreaDefaultY;
  protected GraphicsUtil graphicsUtil;
  protected GamePanel gamePanel;
  
  public SuperObject(
         final GamePanel gamePanel) {
    super();
    this.gamePanel = gamePanel;
    this.collision = false;
    this.solidArea = new Rectangle(0, 0, 48, 48);
    this.solidAreaDefaultX = 0;
    this.solidAreaDefaultY = 0;
    this.graphicsUtil = new GraphicsUtil();    
  }

  public void draw(
         final Graphics2D graphics2D) {
      
    int screenX = (worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX);
    int screenY = (worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY);
    
    if ((worldX + this.gamePanel.tileSize) > (this.gamePanel.player.worldX - this.gamePanel.player.screenX)
     && (worldX - this.gamePanel.tileSize) < (this.gamePanel.player.worldX + this.gamePanel.player.screenX)
     && (worldY + this.gamePanel.tileSize) > (this.gamePanel.player.worldY - this.gamePanel.player.screenY)
     && (worldY - this.gamePanel.tileSize) < (this.gamePanel.player.worldY + this.gamePanel.player.screenY)) {
     graphics2D.drawImage(this.image, screenX, screenY, null);
    }
  }

}
