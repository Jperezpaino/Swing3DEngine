package es.noa.rad.demos.example9;

public class Alien extends Sprite {

 private final int INITIAL_X = 400;

 public Alien(int x, int y) {
     super(x, y);

     initAlien();
 }

 private void initAlien() {

     loadImage(".//res//alien.png");
     getImageDimensions();
 }

 public void move() {

     if (x < 0) {
         x = INITIAL_X;
     }

     x -= 1;
 }
}
/* This is the Alien class.

public void move() {

    if (x < 0) {
        x = INITIAL_X;
    }

    x -= 1;
}

Aliens return to the screen on the right side after they have disappeared on the left*/