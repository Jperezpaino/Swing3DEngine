package es.noa.rad.demos.game3;
public interface Commons {
 
 public static final int WIDTH = 300;
 public static final int HEIGTH = 400;
 public static final int BOTTOM_EDGE = 390;
 public static final int N_OF_BRICKS = 30;
 public static final int INIT_PADDLE_X = 200;
 public static final int INIT_PADDLE_Y = 360;
 public static final int INIT_BALL_X = 230;
 public static final int INIT_BALL_Y = 355;    
 public static final int DELAY = 1000;
 public static final int PERIOD = 10;
}/*The Commons.java file has some common constants. The WIDTH and HEIGHT constants store the dimensions of the board. When the ball passes the BOTTOM_EDGE, the game is over. The N_OF_BRICKS is the number of bricks in the game. The INIT_PADDLE_X and INIT_PADDLE_Y are initial coordinates of the paddle object. The INIT_BALL_X and INIT_BALL_Y are initial coordinates of the ball object. The DELAY is the initial delay in milliseconds before task is to be executed and the PERIOD is the time in milliseconds between successive task executions that form game cycles. */