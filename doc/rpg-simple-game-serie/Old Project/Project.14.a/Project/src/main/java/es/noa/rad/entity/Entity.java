package es.noa.rad.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import es.noa.rad.GamePanel;

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
  }
 
}
