package es.noa.rad.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import es.noa.rad.GamePanel;
import es.noa.rad.GameState;
import es.noa.rad.KeyHandler;

public class Player extends Entity {
  protected KeyHandler keyHandler;
  
  public final int screenX;
  public final int screenY;
  
//  public int hasKey = 0;

  private int standCounter =  0;
  
  public Player(
         final GamePanel gamePanel,
         final KeyHandler keyHandler) {
    super(gamePanel);
    this.keyHandler = keyHandler;
    
    this.screenX = ((super.gamePanel.screenWidth / 2) - (super.gamePanel.tileSize / 2));
    this.screenY = ((super.gamePanel.screenHeight / 2) - (super.gamePanel.tileSize / 2));
  }
  
  @Override
  public final void setDefaultValues() {
    super.direction = "down";
	super.speed = 4;
    
    /* Set player solid Area. */
    super.solidArea = new Rectangle();
    super.solidArea.x = 8;
    super.solidArea.y = 16;
    super.solidArea.width = 32;
    super.solidArea.height = 32;
    
    super.solidAreaDefaultX = super.solidArea.x;
    super.solidAreaDefaultY = super.solidArea.y;
    
    super.worldX = super.gamePanel.tileSize * 23;
    super.worldY = super.gamePanel.tileSize * 21;
  }
  
  @Override
  public final void getImage() {
    super.up1 = super.setup("/assets/entity/player/", "walking/up_1");
    super.up2 = super.setup("/assets/entity/player/", "walking/up_2");
    super.down1 = super.setup("/assets/entity/player/", "walking/down_1");
    super.down2 = super.setup("/assets/entity/player/", "walking/down_2");
    super.left1 = super.setup("/assets/entity/player/", "walking/left_1");
    super.left2 = super.setup("/assets/entity/player/", "walking/left_2");
    super.right1 = super.setup("/assets/entity/player/", "walking/right_1");
    super.right2 = super.setup("/assets/entity/player/", "walking/right_2");
  }

  @Override
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
      super.gamePanel.collisionChecker.checkTile(this);

      /* Check the collisions between the player and the objects. */
      int objectIndex = super.gamePanel.collisionChecker.checkObject(this, true);
      this.pickUpObject(objectIndex);

      /* Check the collisions between the player and the NPC. */
      int npcIndex = super.gamePanel.collisionChecker.checkEntity(this, super.gamePanel.npc);
      this.interactiveNpc(npcIndex);
      
      
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
  
  public final void interactiveNpc(
         final int i) {
    if (i != 999) {
      super.gamePanel.gameState = GameState.DIALOGUE_STATE;      
      super.gamePanel.stopMusic();
      super.gamePanel.npc[i].speak();
    } 
  }

  @Override
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

    graphics2D.drawImage(image, this.screenX, this.screenY, null);    
    
    /* Draw collision rectangle. */
    if(this.keyHandler.debugState) {
      graphics2D.setColor(Color.red);
      graphics2D.drawRect(
        (this.screenX + this.solidArea.x),
        (this.screenY + this.solidArea.y),
        this.solidArea.width,
        this.solidArea.height
      );
    }

  }

}
