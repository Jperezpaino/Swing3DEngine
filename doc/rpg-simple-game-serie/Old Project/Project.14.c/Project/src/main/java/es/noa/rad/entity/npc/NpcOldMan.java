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
	super.speed = 2;
	    
    /* Set default solid Area. */ 
    this.solidArea = new Rectangle();
    this.solidArea.x = 8;
    this.solidArea.y = 4;
    this.solidArea.width = 32;
    this.solidArea.height = 40;
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

}
