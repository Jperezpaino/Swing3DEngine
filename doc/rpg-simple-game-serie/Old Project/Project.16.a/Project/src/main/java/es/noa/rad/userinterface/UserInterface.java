package es.noa.rad.userinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import es.noa.rad.GamePanel;
import es.noa.rad.GameState;
import es.noa.rad.util.GraphicsUtil;

public class UserInterface {
  private GamePanel gamePanel;
  private Graphics2D graphics2D;
  private Font maruMonicaPlain;
  private Font purisaBold;
  public boolean messageOn;
  public String message;
  private int messageCounter;

  public boolean gameFinished;
  public String currentDialogue;
  
  private GraphicsUtil graphicsUtil;
  
  public UserInterface(
         final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
    
    try {
      final InputStream inputStream
        = this.getClass().getResourceAsStream(
          "/assets/interface/fonts/MaruMonica/Plain/MaruMonica.ttf");
      this.maruMonicaPlain
        = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	} catch (FontFormatException fontFormatException) {
      fontFormatException.printStackTrace();
	} catch (IOException iOException) {
      iOException.printStackTrace();
	}
    
    try {
      final InputStream inputStream
        = this.getClass().getResourceAsStream(
          "/assets/interface/fonts/Purisa/Bold/Purisa.ttf");
      this.purisaBold
        = Font.createFont(Font.TRUETYPE_FONT, inputStream);
	} catch (FontFormatException fontFormatException) {
      fontFormatException.printStackTrace();
	} catch (IOException iOException) {
      iOException.printStackTrace();
	}

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
    this.graphics2D.setRenderingHint(
      RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    this.graphics2D.setFont(this.maruMonicaPlain.deriveFont(Font.PLAIN, 32F));
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
    this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.PLAIN, 124F));
    
    final int x = this.graphicsUtil.getXForCenteredText(this.graphics2D, this.gamePanel, text);
    final int y = (this.gamePanel.screenHeight / 2);
    
    this.graphics2D.drawString(text, x, y);
  }
  
  public final void drawDialogueScreen() {
	this.graphics2D.setFont(this.purisaBold.deriveFont(Font.PLAIN, 24F));
	  
    final int x = (this.gamePanel.tileSize * 2);
    final int y = (this.gamePanel.tileSize / 2);
    final int width = (this.gamePanel.screenWidth - (this.gamePanel.tileSize * 4));
    final int height= (this.gamePanel.tileSize * 4);
    
    this.graphicsUtil.drawSubWindow(this.graphics2D, this.gamePanel, x, y, width, height);
    
    int xMessage = (x + this.gamePanel.tileSize);
    int yMessage = (y + this.gamePanel.tileSize);
    
    for(final String line : this.currentDialogue.split("\n")) {
      this.graphics2D.drawString(line, xMessage, yMessage);	
      yMessage += 40;
    }
    
  }

}
