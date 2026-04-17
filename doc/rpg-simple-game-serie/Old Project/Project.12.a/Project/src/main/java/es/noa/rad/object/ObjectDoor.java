package es.noa.rad.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import es.noa.rad.GamePanel;

public class ObjectDoor extends SuperObject {

  public ObjectDoor(
         final GamePanel gamePanel) {
    super(gamePanel);	  
    super.name = "Door";
    this.collision = true;
    try {
      this.image = super.graphicsUtil.scaleImage(
        ImageIO.read(getClass().getResourceAsStream("/assets/entity/object/door.png")),
        super.gamePanel.tileSize,
        super.gamePanel.tileSize);
    } catch (IOException e) {
     e.printStackTrace();
    } 
  }

}
