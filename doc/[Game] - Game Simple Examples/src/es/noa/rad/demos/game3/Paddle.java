package es.noa.rad.demos.game3;


import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends Sprite implements Commons {

    private int dx;

    public Paddle() {

        ImageIcon ii = new ImageIcon(".//res//breakout//paddle.png");
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_heigth = image.getHeight(null);

        resetState();
    }

    public void move() {

        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= WIDTH - i_width) {
            x = WIDTH - i_width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    private void resetState() {

        x = INIT_PADDLE_X;
        y = INIT_PADDLE_Y;
    }
}/* This is the Paddle class. It encapsulates the paddle object in the Breakout game. The paddle is controlled with left and right arrow keys. By pressing the arrow key, we set the direction variable. By releasing the arrow key, we set the dx variable to zero. This way the paddle stops moving.

public void move() {

    x += dx;

    if (x <= 0) {
        x = 0;
    }

    if (x >= WIDTH - i_width) {
        x = WIDTH - i_width;
    }
}

The paddle moves only in the horizontal direction, so we only update the x coordinate. The if conditions ensure that the paddle does not pass the window edges. */