package es.noa.rad.demos.game3;

import java.awt.Image;
import java.awt.Rectangle;

public class Sprite {

    protected int x;
    protected int y;
    protected int i_width;
    protected int i_heigth;
    protected Image image;

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return i_width;
    }

    public int getHeight() {
        return i_heigth;
    }

    Image getImage() {
        return image;
    }

    Rectangle getRect() {
        return new Rectangle(x, y,
                image.getWidth(null), image.getHeight(null));
    }
}/*The Sprite class is a base class for all objects in the Board. We put here all methods and variables that are in Ball, Brick, and Paddle objects, like getImage() or getX() methods. */