package es.noa.rad.demos.traslation;

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
  private final int PANEL_WIDTH = 800;
  private final int PANEL_HEIGHT = 600;
  private BufferedImage workImage;
  
  public Board() {
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   super.setDoubleBuffered(true);   
   this.workImage = this.loadImageDrawing(".//res//traslation//traslation.png");
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
   
   // Dibujamos un fondo de mosaico utilizando posiciones absolutas.
   for(int i = 0; i < PANEL_HEIGHT; i = i + this.workImage.getHeight()) {
    for(int j = 0; j < PANEL_WIDTH; j = j + this.workImage.getWidth()) {
     graphics2D.drawImage(this.workImage, j, i, null);
    }
   }
   
   // Dibujamos un fondo de mosaico utilizando traslaciones.
   AffineTransform shearTransform = null;

   for(int i = 0; (i * this.workImage.getHeight()) < PANEL_HEIGHT; i++) {
    for(int j = 0; (j * this.workImage.getWidth()) < PANEL_WIDTH; j++) {
     if(shearTransform == null) {
      // Creamos el objeto de trasformación, y lo situamos en la posición (0, 0). 
      shearTransform = AffineTransform.getTranslateInstance(j, i);
     } else {
      shearTransform.translate(
       ((j * this.workImage.getWidth()) - shearTransform.getTranslateX()),
       ((i * this.workImage.getHeight()) - shearTransform.getTranslateY())); 
     }
     graphics2D.drawImage(this.workImage, shearTransform, null);
    }
   }

   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 