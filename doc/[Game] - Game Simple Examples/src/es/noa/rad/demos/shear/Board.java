package es.noa.rad.demos.shear;

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
  java.awt.image.AffineTransformOp;
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
  private final int PANEL_WIDTH = 480;
  private final int PANEL_HEIGHT = 480;
  private BufferedImage workImage;
  
  public Board() {
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   super.setDoubleBuffered(true);   
   this.workImage = this.loadImageDrawing(".//res//shear//shear.png");
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
   graphics2D.drawImage(this.workImage, 60, 60, this);
  
   AffineTransform shearTransform = null;
   
   // Imagen sesgada verticalmente un 25%.
   shearTransform = AffineTransform.getTranslateInstance(120, 60);
   shearTransform.shear(0.0, -0.25);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada verticalmente un 25%.
   shearTransform = AffineTransform.getTranslateInstance(180, 60);
   shearTransform.shear(0.0, 0.25);
   graphics2D.drawImage(this.workImage, shearTransform, null);
   
   // Imagen sesgada verticalmente un 75%.
   shearTransform = AffineTransform.getTranslateInstance(240, 60);
   shearTransform.shear(0.0, -0.75);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada verticalmente un 75%.
   shearTransform = AffineTransform.getTranslateInstance(300, 60);
   shearTransform.shear(0.0, 0.75);
   graphics2D.drawImage(this.workImage, shearTransform, null);
  
   // Imagen sesgada verticalmente un 100%.
   shearTransform = AffineTransform.getTranslateInstance(360, 60);
   shearTransform.shear(0.0, -1.0);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada verticalmente un 100%.
   shearTransform = AffineTransform.getTranslateInstance(420, 60);
   shearTransform.shear(0.0, 1.0);
   graphics2D.drawImage(this.workImage, shearTransform, null);
      
   // Imagen sesgada horizontalmente un 25%.
   shearTransform = AffineTransform.getTranslateInstance(60, 120);
   shearTransform.shear(-0.25, 0.0);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada horizontalmente un 25%.
   shearTransform = AffineTransform.getTranslateInstance(60, 180);
   shearTransform.shear(0.25, 0.0);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada horizontalmente un 75%.
   shearTransform = AffineTransform.getTranslateInstance(60, 240);
   shearTransform.shear(-0.75, 0.0);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada horizontalmente un 75%.
   shearTransform = AffineTransform.getTranslateInstance(60, 300);
   shearTransform.shear(0.75, 0.0);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada horizontalmente un 100%.
   shearTransform = AffineTransform.getTranslateInstance(60, 360);
   shearTransform.shear(-1.0, 0.0);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada horizontalmente un 100%.
   shearTransform = AffineTransform.getTranslateInstance(60, 420);
   shearTransform.shear(1.0, 0.0);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada  verticalmente un 25% y horizontalmente un 25%.
   shearTransform = AffineTransform.getTranslateInstance(120, 120);
   shearTransform.shear(-0.25, -0.25);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada  verticalmente un 25% y horizontalmente un 25%.
   shearTransform = AffineTransform.getTranslateInstance(180, 180);
   shearTransform.shear(0.25, 0.25);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada  verticalmente un 75% y horizontalmente un 75%.
   shearTransform = AffineTransform.getTranslateInstance(280, 280);
   shearTransform.shear(-0.75, -0.75);
   graphics2D.drawImage(this.workImage, shearTransform, null);

   // Imagen sesgada  verticalmente un 75% y horizontalmente un 75%.
   shearTransform = AffineTransform.getTranslateInstance(340, 340);
   shearTransform.shear(0.75, 0.75);
   graphics2D.drawImage(this.workImage, shearTransform, null);
   
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 