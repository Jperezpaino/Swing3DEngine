package es.noa.rad.entity;

import java.awt.Rectangle;
import java.util.Random;

import es.noa.rad.GamePanel;

public class Npc extends Entity{

  public String dialogues[];
  
  public Npc(
         final GamePanel gamePanel) {
    super(gamePanel);

  }

  @Override
  public void setDefaultValues() {
    super.direction = "down";
	super.speed = 1;
    
    /* Set default solid Area. */ 
    this.solidArea = new Rectangle();
    this.solidArea.x = 0;
    this.solidArea.y = 0;
    this.solidArea.width = 48;
    this.solidArea.height = 48;
    
    this.dialogues = new String[10];
    this.setDialogue();
  }
  
  @Override
  public void setAction() {
	
	super.actionLookCounter++;

	if(super.actionLookCounter == 120) {
      final Random random = new Random();
    
      /* Pick up a random number from 1 to 100. */    
      int randonInt = (random.nextInt(100) + 1); 
    
      if(randonInt <= 25) {
        super.direction = "up";	
      } else if((randonInt > 25) && (randonInt <= 50)) {
        super.direction = "down";
      } else if((randonInt > 50) && (randonInt <= 75)) {
        super.direction = "left";
      } else if((randonInt > 75) && (randonInt <= 100)) { 
        super.direction = "right";
      }
      super.actionLookCounter = 0;
    }

  }

  public void setDialogue() {

  }

  public void speak() {
	  
  }

}
