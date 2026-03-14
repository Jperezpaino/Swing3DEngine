package es.noa.rad.demos.rotate;

 import
  java.awt.Color;
 import
  java.awt.Dimension;
 import
  java.awt.Graphics;
 import
  java.awt.Graphics2D;
 import
  java.awt.Toolkit;
 import
  java.awt.geom.AffineTransform;
 import
  java.awt.geom.Point2D;
 import
  java.awt.image.BufferedImage;
 import
  java.io.File;
 import
  java.io.IOException;
 import
  javax.imageio.ImageIO;
 import
  javax.swing.JPanel;

 public class Board extends JPanel {
  private static final long serialVersionUID = 1L;
  private final int PANEL_WIDTH = 370;
  private final int PANEL_HEIGHT = 520;
  private BufferedImage workImage;
  
  public Board() {
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   super.setDoubleBuffered(true);   
   this.workImage = this.loadImageDrawing(".//res//rotate//rotate.png");
  }

  private final BufferedImage loadImageDrawing(final String imageSourceParam) {
   BufferedImage bufferedImage = null;
   try {
    bufferedImage = ImageIO.read(new File(imageSourceParam));
   } catch (IOException exception) {
    System.out.println("Imposible cargar la imagen: " + imageSourceParam.toString());
    System.exit(1);
   }
   return bufferedImage;
  }
  
  @Override
  public void paintComponent(final Graphics graphics) {
   super.paintComponent(graphics);
   this.drawComponent(graphics);
  }

  private void drawComponent(final Graphics graphics) {
   // Establecemos el contexto grafico.
   Graphics2D graphics2D = (Graphics2D) graphics;
   

   // Imagen original en el centro.
   graphics2D.drawImage(this.workImage, 60, 10, this);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect(60, 10, (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1)); 
   
   graphics2D.drawImage(this.workImage, 160, 10, this);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect(160, 10, (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   graphics2D.drawImage(this.workImage, 260, 10, this);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect(260, 10, (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   AffineTransform shearTransform = null;
   Point2D positionPoint = null;
   
   // Imagen rotada 15º ((1⁄12)pi radianes), con el centro de giro en la esquina superior izquierda.   
   shearTransform = AffineTransform.getTranslateInstance(60, 85);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(15));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));   

   // Imagen rotada 15º ((1⁄12)pi radianes), con el centro de giro en el centro de la imagen.
   shearTransform = AffineTransform.getTranslateInstance(160, 85);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(15), (this.workImage.getWidth() / 2), (this.workImage.getHeight() / 2));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
  
   // Imagen rotada 15º ((1⁄12)pi radianes), con el centro de giro en la esquina inferior derecha.
   shearTransform = AffineTransform.getTranslateInstance(260, 85);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(15), this.workImage.getWidth(), this.workImage.getHeight());
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 30º ((1⁄6)pi radianes), con el centro de giro en la esquina superior izquierda.   
   shearTransform = AffineTransform.getTranslateInstance(60, 160);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(30));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));   

   // Imagen rotada 30º ((1⁄6)pi radianes), con el centro de giro en el centro de la imagen.
   shearTransform = AffineTransform.getTranslateInstance(160, 160);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(30), (this.workImage.getWidth() / 2), (this.workImage.getHeight() / 2));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 30º ((1⁄6)pi radianes), con el centro de giro en la esquina inferior derecha.
   shearTransform = AffineTransform.getTranslateInstance(260, 160);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(30), (this.workImage.getWidth()), (this.workImage.getHeight()));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 45º ((1⁄4)pi radianes), con el centro de giro en la esquina superior izquierda.   
   shearTransform = AffineTransform.getTranslateInstance(60, 235);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(45));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));   

   // Imagen rotada 45º ((1⁄4)pi radianes), con el centro de giro en el centro de la imagen.
   shearTransform = AffineTransform.getTranslateInstance(160, 235);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(45), (this.workImage.getWidth() / 2), (this.workImage.getHeight() / 2));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 45º ((1⁄4)pi radianes), con el centro de giro en la esquina inferior derecha.
   shearTransform = AffineTransform.getTranslateInstance(260, 235);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(45), (this.workImage.getWidth()), (this.workImage.getHeight()));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 60º ((1⁄3)pi radianes), con el centro de giro en la esquina superior izquierda.   
   shearTransform = AffineTransform.getTranslateInstance(60, 310);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(60));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));   

   // Imagen rotada 60º ((1⁄3)pi radianes), con el centro de giro en el centro de la imagen.
   shearTransform = AffineTransform.getTranslateInstance(160, 310);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(60), (this.workImage.getWidth() / 2), (this.workImage.getHeight() / 2));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 60º ((1⁄3)pi radianes), con el centro de giro en la esquina inferior derecha.
   shearTransform = AffineTransform.getTranslateInstance(260, 310);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(60), (this.workImage.getWidth()), (this.workImage.getHeight()));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 75º ((5⁄12)pi radianes), con el centro de giro en la esquina superior izquierda.   
   shearTransform = AffineTransform.getTranslateInstance(60, 385);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(75));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));   

   // Imagen rotada 75º ((5⁄12)pi radianes), con el centro de giro en el centro de la imagen.
   shearTransform = AffineTransform.getTranslateInstance(160, 385);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(75), (this.workImage.getWidth() / 2), (this.workImage.getHeight() / 2));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 75º ((5⁄12)pi radianes), con el centro de giro en la esquina inferior derecha.
   shearTransform = AffineTransform.getTranslateInstance(260, 385);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(75), (this.workImage.getWidth()), (this.workImage.getHeight()));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 90º ((1⁄2)pi radianes), con el centro de giro en la esquina superior izquierda.   
   shearTransform = AffineTransform.getTranslateInstance(60, 460);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(90));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));   

   // Imagen rotada 90º ((1⁄2)pi radianes), con el centro de giro en el centro de la imagen.
   shearTransform = AffineTransform.getTranslateInstance(160, 460);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(90), (this.workImage.getWidth() / 2), (this.workImage.getHeight() / 2));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));  
   
   // Imagen rotada 90º ((1⁄2)pi radianes), con el centro de giro en la esquina inferior derecha.
   shearTransform = AffineTransform.getTranslateInstance(260, 460);
   positionPoint = new Point2D.Double(shearTransform.getTranslateX(), shearTransform.getTranslateY());
   shearTransform.rotate(Math.toRadians(90), (this.workImage.getWidth()), (this.workImage.getHeight()));
   graphics2D.drawImage(this.workImage, shearTransform, null);
   // Cuadro de control.
   graphics2D.setColor(Color.black);
   graphics2D.drawRect((int) positionPoint.getX(), (int) positionPoint.getY(), (this.workImage.getWidth() - 1), (this.workImage.getHeight() - 1));   
  
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 