package es.noa.rad;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
  protected GamePanel gamePanel;
  public boolean upPressed;
  public boolean downPressed;
  public boolean leftPressed;
  public boolean rightPressed;
	
  public KeyHandler(
		 final GamePanel gamePanel) {
    super();
	this.gamePanel = gamePanel;
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
      upPressed = true;	
    }
    if(code == KeyEvent.VK_S) {
      downPressed = true;	
    }
    if(code == KeyEvent.VK_A) {
      leftPressed = true;	
    }
    if(code == KeyEvent.VK_D) {
      rightPressed = true;	
    }
    if(code == KeyEvent.VK_UP) {
      this.gamePanel.zoomInOut(1);
    }   
    if(code == KeyEvent.VK_DOWN) {
      this.gamePanel.zoomInOut(-1);
    }   
  }

  @Override
  public final void keyReleased(
		 final KeyEvent keyEvent) {
    int code = keyEvent.getKeyCode();

    if(code == KeyEvent.VK_W) {
      upPressed = false;
    }
    if(code == KeyEvent.VK_S) {
      downPressed = false;
    }
    if(code == KeyEvent.VK_A) {
      leftPressed = false;
    }
    if(code == KeyEvent.VK_D) {
      rightPressed = false;
    } 
  }

}
