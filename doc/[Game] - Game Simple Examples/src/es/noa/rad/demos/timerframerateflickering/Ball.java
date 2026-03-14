package es.noa.rad.demos.timerframerateflickering;

import java.awt.Color;

public class Ball {
  private int ballPositionX; /* Posicion X de la bola, respecto a su centro. */ 
  private int ballAdvanceX; /* Avance o velocidad en el eje X */
  private int ballPositionY; /* Posicion Y de la bola, respecto a su centro. */
  private int ballAdvanceY; /* Avance o velocidad en el eje Y */
  private int ballRadious; /* Radio de la bola. */
  private Color ballColor;
  
  public Ball(
         final int ballPositionXParam,
         final int ballAdvanceXParam,
         final int ballPositionYParam,
         final int ballAdvanceYParam,
         final int ballRadiousParam,
         final Color ballColorParam) {
   super();
   this.ballPositionX = ballPositionXParam;
   this.ballAdvanceX = ballAdvanceXParam;
   this.ballPositionY = ballPositionYParam;
   this.ballAdvanceY = ballAdvanceYParam;
   this.ballRadious = ballRadiousParam;
   this.ballColor = ballColorParam;
  }

  public Ball() {
   super();
   this.ballPositionX = 0;
   this.ballAdvanceX = 0;
   this.ballPositionY = 0;
   this.ballAdvanceY = 0;
   this.ballRadious = 0;
   this.ballColor = Color.BLACK;
  }

  public final int getBallPositionX() {
   return this.ballPositionX;
  }

  public final void setBallPositionX(
         final int ballPositionXParam) {
   this.ballPositionX = ballPositionXParam;
  }

  public final int getBallAdvanceX() {
   return this.ballAdvanceX;
  }

  public final void setBallAdvanceX(
         final int ballAdvanceXParam) {
  this.ballAdvanceX = ballAdvanceXParam;}

  public final int getBallPositionY() {
   return this.ballPositionY;
  }

  public final void setBallPositionY(
         final int ballPositionYParam) {
  this.ballPositionY = ballPositionYParam;}

  public final int getBallAdvanceY() {
   return this.ballAdvanceY;
  }

  public final void setBallAdvanceY(
         final int ballAdvanceYParam) {
  this.ballAdvanceY = ballAdvanceYParam;}

  public final int getBallRadious() {
   return this.ballRadious;
  }

  public final void setBallRadious(
         final int ballRadiousParam) {
   this.ballRadious = ballRadiousParam;
  }

  
  public final Color getBallColor() {
   return this.ballColor;
  }
  

  public final void setBallColor(
         final Color ballColorParam) {
   this.ballColor = ballColorParam;
  }
  
 }
