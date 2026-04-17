package es.noa.rad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
  
  public GamePanel gamePanel;
  public boolean upPressed;
  public boolean downPressed;
  public boolean leftPressed;
  public boolean rightPressed;
  public boolean debugState;
  	
  public KeyHandler(
         final GamePanel gamePanel) {
    super();
	this.gamePanel = gamePanel;
	this.upPressed = false;
	this.downPressed = false;
	this.leftPressed = false;
    this.rightPressed = false;
    this.debugState = false;
  }


  @Override
  public final void keyTyped(
         final KeyEvent keyEvent) {
  }

  @Override
  public final void keyPressed(
         final KeyEvent keyEvent) {
    int code = keyEvent.getKeyCode();

    if(code == KeyEvent.VK_W) {
      this.upPressed = true;	
    }
    if(code == KeyEvent.VK_S) {
      this.downPressed = true;	
    }
    if(code == KeyEvent.VK_A) {
      this.leftPressed = true;	
    }
    if(code == KeyEvent.VK_D) {
      this.rightPressed = true;	
    }
    if(code == KeyEvent.VK_P) {
      if(this.gamePanel.gameState.equals(GameState.PLAY_STATE)) {
        this.gamePanel.gameState = GameState.PAUSE_STATE;
      } else if(this.gamePanel.gameState.equals(GameState.PAUSE_STATE)) {
    	this.gamePanel.gameState = GameState.PLAY_STATE;    	  
      }
    }
    
    /* Active debug key. */
    if(code == KeyEvent.VK_T) {
      this.debugState = !this.debugState;	
    }  
  }

  @Override
  public final void keyReleased(
		 final KeyEvent keyEvent) {
    int code = keyEvent.getKeyCode();

    if(code == KeyEvent.VK_W) {
      this.upPressed = false;
    }
    if(code == KeyEvent.VK_S) {
      this.downPressed = false;
    }
    if(code == KeyEvent.VK_A) {
      this.leftPressed = false;
    }
    if(code == KeyEvent.VK_D) {
      this.rightPressed = false;
    } 
  }

}
