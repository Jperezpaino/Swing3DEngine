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
    
    /* Stop Moving camera. */
    if(this.gamePanel.player.worldX < this.gamePanel.player.screenX) {
     screenX = this.worldX;
    }
    if(this.gamePanel.player.worldY < this.gamePanel.player.screenY) {
     screenY = this.worldY;
    }   
    int rightOffSet = this.gamePanel.screenWidth - this.gamePanel.player.screenX;      
    if(rightOffSet > this.gamePanel.worldWidth - this.gamePanel.player.worldX) {
     screenX = this.gamePanel.screenWidth - (this.gamePanel.worldWidth - this.worldX);
    } 
    int bottomOffSet = this.gamePanel.screenHeight - this.gamePanel.player.screenY;
    if(bottomOffSet > this.gamePanel.worldHeight - this.gamePanel.player.worldY) {
     screenY = this.gamePanel.screenHeight - (this.gamePanel.worldHeight - this.worldY);
    }
    
    if ((worldX + this.gamePanel.tileSize) > (this.gamePanel.player.worldX - this.gamePanel.player.screenX)
     && (worldX - this.gamePanel.tileSize) < (this.gamePanel.player.worldX + this.gamePanel.player.screenX)
     && (worldY + this.gamePanel.tileSize) > (this.gamePanel.player.worldY - this.gamePanel.player.screenY)
     && (worldY - this.gamePanel.tileSize) < (this.gamePanel.player.worldY + this.gamePanel.player.screenY)) {
     graphics2D.drawImage(this.image, screenX, screenY, null);
    } else if((this.gamePanel.player.worldX < this.gamePanel.player.screenX)
    		|| (this.gamePanel.player.worldY < this.gamePanel.player.screenY)
    		|| (rightOffSet > this.gamePanel.worldWidth - this.gamePanel.player.worldX)
    		|| (bottomOffSet > this.gamePanel.worldHeight - this.gamePanel.player.worldY)) {
      graphics2D.drawImage(image, screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null); 
    }  
  }

}
