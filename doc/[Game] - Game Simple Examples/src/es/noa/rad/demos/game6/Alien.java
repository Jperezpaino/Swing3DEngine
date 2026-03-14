package es.noa.rad.demos.game6;

import javax.swing.ImageIcon;


public class Alien extends Sprite {

    private Bomb bomb;
    private final String shot = ".//res//spaceinvaders//alien.png";

    public Alien(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(shot);
        //ImageIcon ii = new ImageIcon(this.getClass().getResource(shot));
        setImage(ii.getImage());

    }

    public void act(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public class Bomb extends Sprite {

        private final String bomb = ".//res//spaceinvaders//bomb.png";
        private boolean destroyed;

        public Bomb(int x, int y) {
            setDestroyed(true);
            this.x = x;
            this.y = y;
            //ImageIcon ii = new ImageIcon(this.getClass().getResource(bomb));
            ImageIcon ii = new ImageIcon(bomb);
            setImage(ii.getImage());
        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }
}/* This is the Alien sprite. Each alien has an inner Bomb class.

public void act(int direction) {
    this.x += direction;
}

The act() method is called from the Board class. It is used to position an alien in horizontal direction.

public Bomb getBomb() {
    return bomb;
}

The getBomb() method is called, when the alien is about to drop a bomb. */