package es.noa.rad.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import es.noa.rad.GamePanel;

public class ObjectBoots extends SuperObject {

  public ObjectBoots(
         final GamePanel gamePanel) {
    super(gamePanel);      
    super.name = "Boots";      
    try {
      this.image = super.graphicsUtil.scaleImage(
        ImageIO.read(getClass().getResourceAsStream("/assets/entity/object/boots.png")),
        super.gamePanel.tileSize,
        super.gamePanel.tileSize);
    } catch (IOException e) {
     e.printStackTrace();
    } 
  }

}
