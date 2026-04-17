package es.noa.rad.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
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

//  public void draw(
//         final Graphics2D graphics2D) {
//    
//    final BufferedImage image = null;
//    int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
//    int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;
//    
//    // STOP MOVING CAMERA
//    if(this.gamePanel.player.worldX < this.gamePanel.player.screenX) {
//     screenX = this.worldX;
//    }
//    if(this.gamePanel.player.worldY < this.gamePanel.player.screenY) {
//     screenY =this.worldY;
//    }   
//    int rightOffset = this.gamePanel.screenWidth - this.gamePanel.player.screenX;      
//    if(rightOffset > this.gamePanel.worldWidth - this.gamePanel.player.worldX) {
//     screenX = this.gamePanel.screenWidth - (this.gamePanel.worldWidth - this.worldX);
//    } 
//    int bottomOffset = this.gamePanel.screenHeight - this.gamePanel.player.screenY;
//    if(bottomOffset > this.gamePanel.worldHeight - this.gamePanel.player.worldY) {
//     screenY = this.gamePanel.screenHeight - (this.gamePanel.worldHeight - this.worldY);
//    }
//    ///////////////////
//    
//    switch(direction) {
//    case "up":
//     if(spriteNum == 1) {
//      image = up1;
//     }
//     if(spriteNum == 2) {
//      image = up2;
//     }
//     break;
//    case "down":
//     if(spriteNum == 1) {
//      image = down1;
//     }
//     if(spriteNum == 2) {
//      image = down2;
//     }
//     break;
//    case "left":
//     if(spriteNum == 1) {
//      image = left1;    
//     }   
//     if(spriteNum == 2) {
//      image = left2;
//     }
//     break;
//    case "right":
//     if(spriteNum == 1) {
//      image = right1;
//     }   
//     if(spriteNum == 2) {
//      image = right2;
//     }
//     break;
//    }
//    
//    if(worldX + this.gamePanel.tileSize > this.gamePanel.player.worldX - this.gamePanel.player.screenX &&
//       worldX - this.gamePanel.tileSize < this.gamePanel.player.worldX + this.gamePanel.player.screenX &&
//       worldY + this.gamePanel.tileSize > this.gamePanel.player.worldY - this.gamePanel.player.screenY &&
//       worldY - this.gamePanel.tileSize < this.gamePanel.player.worldY + this.gamePanel.player.screenY) {      
//     g2.drawImage(image, screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
//    }
//    // If player is around the edge, draw everything
//    else if(this.gamePanel.player.worldX < this.gamePanel.player.screenX ||
//      this.gamePanel.player.worldY < this.gamePanel.player.screenY ||
//      rightOffset > this.gamePanel.worldWidth - this.gamePanel.player.worldX ||
//      bottomOffset > this.gamePanel.worldHeight - this.gamePanel.player.worldY) {
//     g2.drawImage(image, screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null); 
//    }
//   }  
}
