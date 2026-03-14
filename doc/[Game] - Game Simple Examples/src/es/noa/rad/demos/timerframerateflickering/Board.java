package es.noa.rad.demos.timerframerateflickering;

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
import java.awt.RenderingHints;
import
  java.awt.Toolkit;
 import
  java.awt.event.ActionEvent;
 import
  java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
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
  private final int MAX_BALLS = 100; /* Numero maximo de bolas */
  private Timer boardTimer;
  private int boardTimerFrameRate;
  private Ball[] ballArray;
  
  private BufferedImage buffer;
  
  public final BufferedImage getBuffer() {
   return this.buffer;
  }

  public final void setBackBuffer(final BufferedImage bufferParam) {
   this.buffer = bufferParam;
  }
  
  /** metodo que creara el contexto grafico. */ 
  public Graphics2D createDemoGraphics2D(Graphics g) {
   Graphics2D graphics2D = null;

   if (this.buffer == null) { // Comprobamos si el buffer esta a null
     this.buffer = (BufferedImage) createImage(super.getSize().width, super.getSize().height);
   } else {
    if (this.buffer.getWidth() != super.getSize().width
     || this.buffer.getHeight() != super.getSize().height) {
     this.buffer = (BufferedImage) createImage(super.getSize().width, super.getSize().height);
    }
   }

   // Comprobamos si se ha generado correstamente
   if (this.buffer != null) {
    graphics2D = this.buffer.createGraphics();
    //graphics2D.setBackground(super.getBackground());    
    // Establecemos los atributos del contexto grafico
    //graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Limpiamos el canvas
    graphics2D.clearRect(0, 0, super.getSize().width, super.getSize().height);
   }
   
   return graphics2D;
  }

  
  
  @Override
  public void repaint() {
   this.paint(this.getGraphics());
  }

  @Override
  public void paint(final Graphics graphicsParam) {
   if (super.getSize().width > 0 || super.getSize().height > 0) {
    Graphics2D graphics2D = this.createDemoGraphics2D(graphicsParam);
    this.draw(graphics2D);
    graphics2D.dispose();
    if (this.buffer != null && super.isShowing()) {
     graphicsParam.drawImage(this.buffer, 0, 0, this);
    }
   }
  }
  
  /** metodo que pintara */
  public void draw(final Graphics graphicsParam) {
   // Generamos un contexto grafico 2D. 
   Graphics2D graphics2D = (Graphics2D) graphicsParam;
   // Limpiamos la imagen con el color de fondo establecido.
   //graphics2D.setColor(super.getBackground());
   //graphics2D.fillRect(0, 0, super.getSize().width, super.getSize().height);
   // Dibujamos.
   ////////////////////////////////////////
   this.drawComponent(graphics2D);
   // Fin del dibujado
  }
  
  
  public Board() {
   this.initComponent();
  }
  
  /*@Override
  public void paint(final Graphics graphicsParam) {
   /*
    * Sobrescribimos el método encargado de repintar el componente, y le
    * asignamos el contexto gráfico del componente como contexto propio al
    * lienzo.
    */
   /*super.paint(graphicsParam);
   /*
    * Invocamos al método general de repintado, contiene la ejecución y el
    * código objeto del ejemplo.
    */ 
   /*this.drawComponent(graphicsParam);
 /* }*/

  private void initComponent() {
   /*
    * Inicializamos el lienzo con el tamaño que hemos definido en las
    * constantes.
    */   
   super.setPreferredSize(
     new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   /* Inicializamos las coordenadas donde comenzara a aparecer el cuadrado. */
   Random ramdon = new Random();
   this.ballArray = new Ball[MAX_BALLS];
   for(int i = 0; i < this.ballArray.length; i++) {
    int radio = ramdon.nextInt(49) + 1;
    int positionX = ramdon.nextInt(PANEL_WIDTH - (radio * 2)) + radio;
    int positionY = ramdon.nextInt(PANEL_HEIGHT - (radio * 2)) + radio;
    int avanceX = ramdon.nextInt(50) + 1;
    int avanceY = ramdon.nextInt(50) + 1;
    Color color = new Color(
     ramdon.nextInt(255),ramdon.nextInt(255), ramdon.nextInt(255));
    this.ballArray[i] = new Ball(positionX, avanceX, positionY, avanceY, radio, color);
   }
   
   this.boardTimerFrameRate = 50;
   this.boardTimer = new Timer(this.boardTimerFrameRate, this);
   this.boardTimer.start();
  }
  
  private void drawComponent(final Graphics graphics) {
   Graphics2D graphics2D = (Graphics2D) graphics;
   graphics2D.setColor(Color.BLACK);
   graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
   for(int i = 0; i < this.ballArray.length; i++) {
    Ball ballObject = this.ballArray[i];    
    graphics2D.setColor(ballObject.getBallColor());
    graphics2D.fillOval(
     (ballObject.getBallPositionX() - ballObject.getBallRadious()),
     (ballObject.getBallPositionY() - ballObject.getBallRadious()),
     (ballObject.getBallRadious() * 2),
     (ballObject.getBallRadious() * 2));
   }
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
   for(int i = 0; i < this.ballArray.length; i++) {
    Ball ballObject = this.ballArray[i]; 
    
    ballObject.setBallPositionX(
     ballObject.getBallPositionX() + ballObject.getBallAdvanceX());
    ballObject.setBallPositionY(
     ballObject.getBallPositionY() + ballObject.getBallAdvanceY());
   
    if ((ballObject.getBallPositionX() + ballObject.getBallRadious()) > this.getWidth()) {
     ballObject.setBallAdvanceX(ballObject.getBallAdvanceX() * -1);
     ballObject.setBallPositionX(
      (this.getWidth() - ballObject.getBallRadious()));
    }
  
    if ((ballObject.getBallPositionY() + ballObject.getBallRadious()) > this.getHeight()) {
     ballObject.setBallAdvanceY(ballObject.getBallAdvanceY() * -1);
     ballObject.setBallPositionY(
      (this.getHeight() - ballObject.getBallRadious()));    
    }
   
    if ((ballObject.getBallPositionX() - ballObject.getBallRadious()) < 0) {
     ballObject.setBallAdvanceX(ballObject.getBallAdvanceX() * -1);
     ballObject.setBallPositionX(
      ballObject.getBallRadious());    
    }
  
    if ((ballObject.getBallPositionY() - ballObject.getBallRadious()) < 0) {
     ballObject.setBallAdvanceY(ballObject.getBallAdvanceY() * -1);
     ballObject.setBallPositionY(
      ballObject.getBallRadious());       
    }
   }
   
   repaint();
  }

 }
 