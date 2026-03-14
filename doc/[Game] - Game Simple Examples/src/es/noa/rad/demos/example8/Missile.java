package es.noa.rad.demos.example8;

public class Missile extends Sprite {

 private final int BOARD_WIDTH = 390;
 private final int MISSILE_SPEED = 2;

 public Missile(int x, int y) {
     super(x, y);
     
     initMissile();
 }
 
 private void initMissile() {
     
     loadImage(".//res//missile.png");  
     getImageDimensions();
 }


 public void move() {
     
     x += MISSILE_SPEED;
     
     if (x > BOARD_WIDTH) {
         vis = false;
     }
 }
}

/*
Here we have a new sprite called Missile.

public void move() {
   
   x += MISSILE_SPEED;
   
   if (x > BOARD_WIDTH) {
       vis = false;
   }
}

The missile moves at constant speed. When it hits the right border of the Board, it becomes invisible. It is then removed from the list of missiles. 
*/