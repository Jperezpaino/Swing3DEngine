package es.noa.rad.demos.game3;


import javax.swing.ImageIcon;

public class Ball extends Sprite implements Commons {

    private int xdir;
    private int ydir;

    public Ball() {

        xdir = 1;
        ydir = -1;

        ImageIcon ii = new ImageIcon(".//res//breakout//ball.png");
        image = ii.getImage();

        i_width = image.getWidth(null);
        i_heigth = image.getHeight(null);

        resetState();
    }

    public void move() {
        
        x += xdir;
        y += ydir;

        if (x == 0) {
            setXDir(1);
        }

        if (x == WIDTH - i_width) {
            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }
    }

    private void resetState() {
        
        x = INIT_BALL_X;
        y = INIT_BALL_Y;
    }

    public void setXDir(int x) {
        xdir = x;
    }

    public void setYDir(int y) {
        ydir = y;
    }

    public int getYDir() {
        return ydir;
    }
}/* This is the Ball class.

public void move() {
    
    x += xdir;
    y += ydir;

    if (x == 0) {
        setXDir(1);
    }

    if (x == WIDTH - i_width) {
        setXDir(-1);
    }

    if (y == 0) {
        setYDir(1);
    }
}

The move() method moves the ball on the Board. If the ball hits the borders, the directions are changed accordingly.

public void setXDir(int x) {
    xdir = x;
}

public void setYDir(int y) {
    ydir = y;
}

These two methods are called when the ball hits the paddle or a brick. */