package es.noa.rad.entity;

import java.awt.Rectangle;

import es.noa.rad.GamePanel;

public class Npc extends Entity{

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
  }

}
