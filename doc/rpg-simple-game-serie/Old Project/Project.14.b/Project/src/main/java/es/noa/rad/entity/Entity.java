package es.noa.rad.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import es.noa.rad.GamePanel;
import es.noa.rad.util.GraphicsUtil;

public class Entity {
  protected GamePanel gamePanel;
  
  public int worldX;
  public int worldY;
  public int speed;
  
  public BufferedImage up1;
  public BufferedImage up2;
  public BufferedImage down1;
  public BufferedImage down2;
  public BufferedImage left1;
  public BufferedImage left2;
  public BufferedImage right1;
  public BufferedImage right2;

  public String direction;
  
  public int spriteCounter = 0;
  public int spriteNum = 1;
  
  public Rectangle solidArea;
  public int solidAreaDefaultX;
  public int solidAreaDefaultY;
  public boolean collisionOn = false;

  public Entity(
         final GamePanel gamePanel) {
    super();
    this.gamePanel = gamePanel;
    
    this.setDefaultValues();
    this.getImage();
  }

  public void setDefaultValues() {
    this.direction = "down";
    this.speed = 0;
    
    /* Set default solid Area. */ 
    this.solidArea = new Rectangle();
    this.solidArea.x = 0;
    this.solidArea.y = 0;
    this.solidArea.width = 48;
    this.solidArea.height = 48;
  }
  
  public void getImage() {
	  
  }

  public void draw(
	     final Graphics2D graphics2D) {
	    
    BufferedImage image = null;
    final int screenX = (this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX);
    final int screenY = (this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY);
    
    if ((this.worldX + this.gamePanel.tileSize) > (this.gamePanel.player.worldX - this.gamePanel.player.screenX)
     && (this.worldX - this.gamePanel.tileSize) < (this.gamePanel.player.worldX + this.gamePanel.player.screenX)
     && (this.worldY + this.gamePanel.tileSize) > (this.gamePanel.player.worldY - this.gamePanel.player.screenY)
     && (this.worldY - this.gamePanel.tileSize) < (this.gamePanel.player.worldY + this.gamePanel.player.screenY)) {

      switch (this.direction) {
	    case "up":
          if (this.spriteNum == 1) {          
            image = this.up1;
          } else if (this.spriteNum == 2) {
            image = this.up2;
          }
        break;
        case "down":
          if (this.spriteNum == 1) {  
            image = this.down1;
          } else if (this.spriteNum == 2) {
            image = this.down2;
          }
        break;
        case "left":
          if (this.spriteNum == 1) {  
            image = this.left1;
          } else if (this.spriteNum == 2) {
            image = this.left2;
          }
        break;
        case "right":
          if (this.spriteNum == 1) {  
            image = this.right1;
          } else if (this.spriteNum == 2) {
            image = this.right2;
          }
        break;
      }
      graphics2D.drawImage(image, screenX, screenY, null);

      /* Draw collision rectangle. */
      if(this.gamePanel.keyHandler.debugState) {
        graphics2D.setColor(Color.red);
        graphics2D.drawRect(
          (screenX + this.solidArea.x),
          (screenY + this.solidArea.y),
          this.solidArea.width,
          this.solidArea.height
        );
      }
      
    }
  }
  
  public final BufferedImage setup(
         final String imagePackage,
         final String imageName) {
    final GraphicsUtil graphicsUtil = new GraphicsUtil();
    BufferedImage image = null;
    try {
      image = graphicsUtil.scaleImage(
        ImageIO.read(getClass().getResourceAsStream(imagePackage + imageName + ".png")),
        this.gamePanel.tileSize,
        this.gamePanel.tileSize
      );
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
    return image;
  }

}
