package es.noa.rad.entity.npc;

import java.awt.Rectangle;

import es.noa.rad.GamePanel;
import es.noa.rad.entity.Npc;

public class NpcOldMan extends Npc {

  public NpcOldMan(
         final GamePanel gamePanel) {
    super(gamePanel);
  }

  @Override
  public final void setDefaultValues() {
    super.direction = "down";
	super.speed = 1;
	    
    /* Set default solid Area. */ 
    this.solidArea = new Rectangle();
    this.solidArea.x = 8;
    this.solidArea.y = 4;
    this.solidArea.width = 32;
    this.solidArea.height = 40;
    
    this.dialogues = new String[10];
    this.setDialogue();
  }

  public final void getImage() {
    super.up1 = super.setup("/assets/entity/npc/oldman/", "walking/up_1");
    super.up2 = super.setup("/assets/entity/npc/oldman/", "walking/up_2");
    super.down1 = super.setup("/assets/entity/npc/oldman/", "walking/down_1");
    super.down2 = super.setup("/assets/entity/npc/oldman/", "walking/down_2");
    super.left1 = super.setup("/assets/entity/npc/oldman/", "walking/left_1");
    super.left2 = super.setup("/assets/entity/npc/oldman/", "walking/left_2");
    super.right1 = super.setup("/assets/entity/npc/oldman/", "walking/right_1");
    super.right2 = super.setup("/assets/entity/npc/oldman/", "walking/right_2");
  }

  @Override
  public void setDialogue() {
    super.dialogues[0] = "Hello, lad.";
    super.dialogues[1] = "So you've come to this island to find the treasure?";
    super.dialogues[2] = "I used to be great wizard but now... I'm a bit too old for taking an adventure.";
    super.dialogues[3] = "Well, good luck on you.";    
  }
  
  @Override
  public final void speak() {
	super.gamePanel.userInterface.currentDialogue = super.dialogues[super.dialoguesIndex];
	super.dialoguesIndex++;
  }

}
