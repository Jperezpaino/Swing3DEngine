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
//  private Font arial40;
  private Font arial80Bold;
//  private BufferedImage keyImage;
  public boolean messageOn;
  public String message;
//  private int messageCounter;

  public boolean gameFinished;
//  private double playTime;
//  private DecimalFormat decimalFormat;
  private GraphicsUtil graphicsUtil;
  
  public UserInterface(
         final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
//    this.arial40 = new Font("Arial", Font.PLAIN, 40);
    this.arial80Bold = new Font("Arial", Font.BOLD, 80);

    //final ObjectKey key = new ObjectKey(this.gamePanel);
    //this.keyImage = key.image;
    this.messageOn = false;
    this.message = "";
//    this.messageCounter = 0;
    
    this.gameFinished = false;
//    this.decimalFormat = new DecimalFormat("#0.00");
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

    //if(this.gamePanel.gameState != null) {
      if(this.gamePanel.gameState.equals(GameState.PLAY_STATE)) {
        /* No event. */    
      } else if(this.gamePanel.gameState.equals(GameState.PAUSE_STATE)) {
        this.drawPauseScreen();    
      }
   // }

  }
  
  public final void drawPauseScreen() {
    final String text = "PAUSED";
    
    final int x = this.graphicsUtil.getXForCenteredText(this.graphics2D, this.gamePanel, text);
    final int y = (this.gamePanel.screenHeight / 2);
    
    this.graphics2D.drawString(text, x, y);
  }

}
