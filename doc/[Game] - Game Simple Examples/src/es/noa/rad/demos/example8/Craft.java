package es.noa.rad.demos.example8;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Craft extends Sprite {

    private int dx;
    private int dy;
    private ArrayList missiles;

    public Craft(int x, int y) {
        super(x, y);
        
        initCraft();
    }

    private void initCraft() {

        missiles = new ArrayList();
        loadImage(".//res//craft.png"); 
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public ArrayList getMissiles() {
        return missiles;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dy = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 1;
        }
    }

    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}
/* This is the Craft class.

if (key == KeyEvent.VK_SPACE) {
    fire();
}

If we press the Space key, we fire.

public void fire() {
    missiles.add(new Missile(x + width, y + height / 2));
}

The fire() method creates a new Missile object and adds it to the missiles ArrayList.

public ArrayList getMissiles() {
    return missiles;
}

The getMissiles() method returns the ArrayList of missiles. It is called from the Board class. */