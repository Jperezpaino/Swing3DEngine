package es.noa.rad.object;

import java.io.IOException;
import javax.imageio.ImageIO;
import es.noa.rad.GamePanel;

public class ObjectKey extends SuperObject {

  public ObjectKey(
         final GamePanel gamePanel) {
    super(gamePanel);      
    super.name = "Key";
    try {
      this.image = super.graphicsUtil.scaleImage(
        ImageIO.read(getClass().getResourceAsStream("/assets/entity/object/key.png")),
        super.gamePanel.tileSize,
        super.gamePanel.tileSize);
    } catch (IOException e) {
     e.printStackTrace();
    } 
  }

}
