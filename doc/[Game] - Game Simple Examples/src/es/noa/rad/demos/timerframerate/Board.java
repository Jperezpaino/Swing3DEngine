package es.noa.rad.demos.timerframerate;

 import
  java.awt.Color;
 import
  java.awt.Dimension;
 import
  java.awt.Graphics;
 import
  java.awt.Graphics2D;
 import
  java.awt.Panel;
 import
  java.awt.Polygon;
 import
  java.awt.Toolkit;
 import
  java.awt.event.ActionEvent;
 import
  java.awt.event.ActionListener;
 import
  javax.swing.Timer;

 /*
  * Clase principal del ejemplo se representa como un tablero o lienzo donde
  * se aplicaran las técnicas de dibujado y este se mostrara en la pantalla, a
  * su vez implementara los métodos que nos permitirá interactuar con el mismo.
  */
 public class Board extends Panel implements ActionListener {
  private static final long serialVersionUID = 1L;
  private final int PANEL_WIDTH = 800; /* Ancho que tendrá el lienzo. */
  private final int PANEL_HEIGHT = 600; /* Alto que tendrá el lienzo. */
  private Timer boardTimer;
  private int boardTimerFrameRate;
  private int boxPositionX;
  private boolean boxAdvanceX;
  private int boxPositionY;
  private boolean boxAdvanceY;
  
  public Board() {
   this.initComponent();
  }
  
  @Override
  public void paint(final Graphics graphicsParam) {
   /*
    * Sobrescribimos el método encargado de repintar el componente, y le
    * asignamos el contexto gráfico del componente como contexto propio al
    * lienzo.
    */
   super.paint(graphicsParam);
   /*
    * Invocamos al método general de repintado, contiene la ejecución y el
    * código objeto del ejemplo.
    */ 
   this.drawComponent(graphicsParam);
  }

  private void initComponent() {
   /*
    * Inicializamos el lienzo con el tamaño que hemos definido en las
    * constantes.
    */   
   super.setPreferredSize(
     new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   /* Inicializamos las coordenadas donde comenzara a aparecer el cuadrado. */
   this.boxPositionX = 0;
   this.boxAdvanceX = true;
   this.boxPositionY = 0;
   this.boxAdvanceY = true;   
   this.boardTimerFrameRate = 60;
   this.boardTimer = new Timer(this.boardTimerFrameRate, this);
   this.boardTimer.start();
  }
  
  private void drawComponent(final Graphics graphics) {
   Graphics2D graphics2D = (Graphics2D) graphics;
   graphics2D.setColor(Color.BLACK);
   graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
   graphics2D.setColor(Color.BLUE);  
   graphics2D.fillOval(this.boxPositionX, this.boxPositionY, 50, 50);
   
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
   // TODO Auto-generated method stub
   if(this.boxAdvanceX) {
    this.boxPositionX += 10;
   } else {
    this.boxPositionX -= 10;
   }
   
   if(this.boxAdvanceY) {
    this.boxPositionY += 10;
   } else {
    this.boxPositionY -= 10;
   }
   
   if (this.boxPositionX > this.getWidth()) {
    this.boxAdvanceX = false;
   }
  
   if (this.boxPositionY > this.getHeight()) {
    this.boxAdvanceY = false;
   }
   
   if (this.boxPositionX < 0) {
    this.boxAdvanceX = true;
   }
  
   if (this.boxPositionY < 0) {
    this.boxAdvanceY = true;
   }
   
   super.repaint();
  }

 }
 