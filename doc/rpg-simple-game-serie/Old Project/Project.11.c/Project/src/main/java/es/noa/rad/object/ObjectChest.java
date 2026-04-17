package es.noa.rad.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import es.noa.rad.GamePanel;

public class ObjectChest extends SuperObject {

  public ObjectChest(
         final GamePanel gamePanel) {
    super(gamePanel);	  
    super.name = "Chest";	  
    try {
      this.image = super.graphicsUtil.scaleImage(
        ImageIO.read(getClass().getResourceAsStream("/assets/entity/object/chest.png")),
        super.gamePanel.tileSize,
        super.gamePanel.tileSize);
    } catch (IOException e) {
     e.printStackTrace();
    } 
  }

}
