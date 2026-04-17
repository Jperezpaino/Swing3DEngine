package es.noa.rad.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import es.noa.rad.GamePanel;
import es.noa.rad.KeyHandler;
import es.noa.rad.util.GraphicsUtil;

public class Player extends Entity {
  protected GamePanel gamePanel;
  protected KeyHandler keyHandler;
  
  public final int screenX;
  public final int screenY;
  
//  public int hasKey = 0;

  private int standCounter =  0;
  
  public Player(
         final GamePanel gamePanel,
         final KeyHandler keyHandler) {
    super();
    this.gamePanel = gamePanel;
    this.keyHandler = keyHandler;
    
    this.screenX = ((this.gamePanel.screenWidth / 2) - (this.gamePanel.tileSize / 2));
    this.screenY = ((this.gamePanel.screenHeight / 2) - (this.gamePanel.tileSize / 2));

    super.solidArea = new Rectangle();
    super.solidArea.x = 8;
    super.solidArea.y = 16;
    this.solidAreaDefaultX = this.solidArea.x;
    this.solidAreaDefaultY = this.solidArea.y;
    super.solidArea.width = 32;
    super.solidArea.height = 32;

    this.setDefaultValues();
    this.getPlayerImage();
  }
  
  public final void setDefaultValues() {
    super.worldX = this.gamePanel.tileSize * 23;
    super.worldY = this.gamePanel.tileSize * 21;
    super.speed = 4;
    super.direction = "down";
  }
  
  public void getPlayerImage() {
    super.up1 = setup("walking/boy_up_1");
    super.up2 = setup("walking/boy_up_2");
    super.down1 = setup("walking/boy_down_1");
    super.down2 = setup("walking/boy_down_2");
    super.left1 = setup("walking/boy_left_1");
    super.left2 = setup("walking/boy_left_2");
    super.right1 = setup("walking/boy_right_1");
    super.right2 = setup("walking/boy_right_2");
  }

  public final BufferedImage setup(
         final String imageName) {
    final GraphicsUtil graphicsUtil = new GraphicsUtil();
    BufferedImage image = null;
    try {
        image = graphicsUtil.scaleImage(
        ImageIO.read(getClass().getResourceAsStream("/assets/entity/player/" + imageName + ".png")),
        this.gamePanel.tileSize,
        this.gamePanel.tileSize);
    } catch (IOException iOException) {
      iOException.printStackTrace();
    } 
    return image;
  }
  
  public final void update() {
    if (this.keyHandler.upPressed
      || this.keyHandler.downPressed
      || this.keyHandler.leftPressed
      || this.keyHandler.rightPressed) {      
      if(this.keyHandler.upPressed == true) {
        super.direction = "up";
      } else if (this.keyHandler.downPressed == true) {
        super.direction = "down";
      } else if (this.keyHandler.leftPressed == true) {        
        super.direction = "left";
      } else if (this.keyHandler.rightPressed == true) {
        super.direction = "right";
      } 
    
      /* Check the collisions between the player and the destination tile. */
      super.collisionOn = false;
      /* disabled for this border example */
      //this.gamePanel.collisionChecker.checkTile(this);

      /* Check the collisions between the player and the objects. */
      int objectIndex = this.gamePanel.collisionChecker.checkObject(this, true);
      this.pickUpObject(objectIndex);
      
      /* if collision is false, player can move. */
      if (!this.collisionOn) {
        switch (direction) {
          case "up":
            this.worldY -= this.speed;
          break;             
          case "down":
            this.worldY += this.speed;
          break; 
          case "left":
            this.worldX -= this.speed;
          break; 
          case "right":
            this.worldX += this.speed;
          break; 
        }
      }
      
      super.spriteCounter++;
      if (this.spriteCounter > 12) {
        if (this.spriteNum == 1) {
          this.spriteNum = 2;
        } else if (this.spriteNum == 2) {
          this.spriteNum = 1;
        } 
        this.spriteCounter = 0;
      }
    } else {
      this.standCounter++;
      if(this.standCounter == 20) {
        this.spriteNum = 1;
        this.standCounter = 0;
      }
    }
  }
  
  public final void pickUpObject(
         final int i) {
    if (i != 999) {
     /* Nothing to do. */
    } 
  }
  
  public final void draw(
         final Graphics2D graphics2D) {
    
    BufferedImage image = null;

    switch (super.direction) {
      case "up":
        if (super.spriteNum == 1) {          
          image = this.up1;
        } else if (super.spriteNum == 2) {
          image = this.up2;
        }
      break;
      case "down":
        if (super.spriteNum == 1) {  
          image = this.down1;
        } else if (super.spriteNum == 2) {
          image = this.down2;
        }
      break;
      case "left":
        if (super.spriteNum == 1) {  
          image = this.left1;
        } else if (super.spriteNum == 2) {
          image = this.left2;
        }
      break;
      case "right":
        if (super.spriteNum == 1) {  
          image = this.right1;
        } else if (super.spriteNum == 2) {
          image = this.right2;
        }
      break;
    }

    int x = this.screenX;
    int y = this.screenY;

    if(this.screenX> this.worldX) {
      x = worldX;
    }
    if(this.screenY > this.worldY) {
      y = worldY;
    }
    
    int rightOffSet = (this.gamePanel.screenWidth - screenX);
    if(rightOffSet > (this.gamePanel.worldWidth - worldX)) {
      x = (this.gamePanel.screenWidth - (this.gamePanel.worldWidth - worldX));  
    }
    int bottomOffSet = (this.gamePanel.screenHeight - screenY);
    if(bottomOffSet > (this.gamePanel.worldHeight - worldY)) {
      y = (this.gamePanel.screenHeight - (this.gamePanel.worldHeight - worldY));  
    }
    
    graphics2D.drawImage(image, x, y, null);    
    
    /* Draw collision rectangle. */
    if(this.keyHandler.debugState) {
      graphics2D.setColor(Color.red);
      graphics2D.drawRect((this.screenX + this.solidArea.x), (this.screenY + this.solidArea.y), this.solidArea.width, this.solidArea.height);
    }

  }

}
