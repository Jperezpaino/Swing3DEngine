package es.noa.rad.demos.game6;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Player extends Sprite implements Commons{

    private final int START_Y = 280; 
    private final int START_X = 270;

    private final String player = ".//res//spaceinvaders//player.png";
    private int width;

    public Player() {

        ImageIcon ii = new ImageIcon(player);

        width = ii.getImage().getWidth(null); 

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    public void act() {
        x += dx;
        if (x <= 2) 
            x = 2;
        if (x >= BOARD_WIDTH - 2*width) 
            x = BOARD_WIDTH - 2*width;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = -2;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT)
        {
            dx = 0;
        }
    }
}/* This is the Player sprite. We control the cannon with the cursor keys.

private final int START_Y = 280; 
private final int START_X = 270;

These are the initial coordinates of the player sprite.

if (key == KeyEvent.VK_LEFT)
{
    dx = -2;
}

If we press the left cursor key, the dx variable is set to -2. Next time the act() method is called, the player moves to the left.

if (key == KeyEvent.VK_LEFT)
{
    dx = 0;
}

if (key == KeyEvent.VK_RIGHT)
{
    dx = 0;
}

If we release the left or the right cursor, the dx variable is set to zero. The player sprite stops moving. */