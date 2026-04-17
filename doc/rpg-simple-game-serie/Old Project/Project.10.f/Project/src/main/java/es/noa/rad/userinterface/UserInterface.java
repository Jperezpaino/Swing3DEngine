package es.noa.rad.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import es.noa.rad.GamePanel;
import es.noa.rad.object.ObjectKey;

public class UserInterface {
  private GamePanel gamePanel;
  private Font arial40;
  private Font arial80Bold;
  private BufferedImage keyImage;
  public boolean messageOn;
  public String message;
  private int messageCounter;

  public boolean gameFinished;
  private double playTime;

  public UserInterface(
         final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
    this.arial40 = new Font("Arial", Font.PLAIN, 40);
    this.arial80Bold = new Font("Arial", Font.BOLD, 80);

    final ObjectKey key = new ObjectKey();
    this.keyImage = key.image;
    this.messageOn = false;
    this.message = "";
    this.messageCounter = 0;
    
    this.gameFinished = false;
  }    

  public final void showMessage(
         final String text) {
    this.message = text;
    this.messageOn = true;
  }

  public final void draw(
         final Graphics2D graphics2D) {
	  
    if (this.gameFinished) {
      graphics2D.setFont(this.arial40);
      graphics2D.setColor(Color.white);

      String text = "";
      int textLength = 0;
      int x = 0;
      int y = 0;
      
      text = "You found the treasure!";
      textLength = ((int) (graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth()));
      x = ((this.gamePanel.screenWidth / 2) - (textLength / 2));
      y = ((this.gamePanel.screenHeight / 2) - (this.gamePanel.tileSize * 3));
      graphics2D.drawString(text, x, y);

      graphics2D.setFont(this.arial80Bold);
      graphics2D.setColor(Color.yellow);

      text = "Congratulations!";
      textLength = ((int) (graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth()));
      x = ((this.gamePanel.screenWidth / 2) - (textLength / 2));
      y = ((this.gamePanel.screenHeight / 2) + (this.gamePanel.tileSize * 2));
      graphics2D.drawString(text, x, y);
      
      this.gamePanel.stopGameThread();

    } else {
      graphics2D.setFont(this.arial40);
      graphics2D.setColor(Color.white);
      graphics2D.drawImage(this.keyImage, (this.gamePanel.tileSize / 2), (this.gamePanel.tileSize / 2), this.gamePanel.tileSize, this.gamePanel.tileSize, null);
      graphics2D.drawString("x " + this.gamePanel.player.hasKey, 75, 65);    
    
      /* Time Control. */
      this.playTime += ((double) 1/60);
      graphics2D.drawString("Time:" + this.playTime, this.gamePanel.tileSize * 11, 65);

      /* Draw the messages. */
      if (this.messageOn) {
        graphics2D.setFont(graphics2D.getFont().deriveFont(30.0F));
        graphics2D.drawString(this.message, (this.gamePanel.tileSize / 2), (this.gamePanel.tileSize * 5));
        this.messageCounter++;
        if (this.messageCounter > 120) {
          this.messageCounter = 0;
          this.messageOn = false;
        } 
      }
    }
  }

}
