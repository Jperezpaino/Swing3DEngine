package es.noa.rad.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.text.DecimalFormat;
import es.noa.rad.GamePanel;
//import es.noa.rad.object.ObjectKey;
import es.noa.rad.GameState;
import es.noa.rad.util.GraphicsUtil;

public class UserInterface {
  private GamePanel gamePanel;
  private Graphics2D graphics2D;
  private Font arial32;
  private Font arial40;
  private Font arial80Bold;
  public boolean messageOn;
  public String message;
  private int messageCounter;

  public boolean gameFinished;
  public String currentDialogue;
  
  private GraphicsUtil graphicsUtil;
  
  public UserInterface(
         final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
    this.arial32 = new Font("Arial", Font.PLAIN, 32);
    this.arial40 = new Font("Arial", Font.PLAIN, 40);
    this.arial80Bold = new Font("Arial", Font.BOLD, 80);

    this.messageOn = false;
    this.message = "";
    this.messageCounter = 0;
    
    this.gameFinished = false;
    this.graphicsUtil = new GraphicsUtil();
  }    

  public final void showMessage(
         final String text) {
    this.message = text;
    this.messageOn = true;
  }

  public final void draw(
         final Graphics2D graphics2D) {
    this.graphics2D = graphics2D;
    this.graphics2D.setFont(this.arial80Bold);
    this.graphics2D.setColor(Color.white);

    if(this.gamePanel.gameState.equals(GameState.PLAY_STATE)) {
      /* No event. */
    } else if(this.gamePanel.gameState.equals(GameState.PAUSE_STATE)) {
      this.drawPauseScreen();    
    } else if(this.gamePanel.gameState.equals(GameState.DIALOGUE_STATE)) {
      this.drawDialogueScreen();    
    }

  }
  
  public final void drawPauseScreen() {
    final String text = "PAUSED";
    
    final int x = this.graphicsUtil.getXForCenteredText(this.graphics2D, this.gamePanel, text);
    final int y = (this.gamePanel.screenHeight / 2);
    
    this.graphics2D.drawString(text, x, y);
  }
  
  public final void drawDialogueScreen() {
    
    final int x = (this.gamePanel.tileSize * 2);
    final int y = (this.gamePanel.tileSize / 2);
    final int width = (this.gamePanel.screenWidth - (this.gamePanel.tileSize * 4));
    final int height= (this.gamePanel.tileSize * 4);
    
    this.graphicsUtil.drawSubWindow(this.graphics2D, this.gamePanel, x, y, width, height);
    
    int xMessage = (x + this.gamePanel.tileSize);
    int yMessage = (y + this.gamePanel.tileSize);
    
    this.graphics2D.setFont(this.arial32);
    for(final String line : this.currentDialogue.split("\n")) {
      this.graphics2D.drawString(line, xMessage, yMessage);	
      yMessage += 40;
    }
    
  }

}
