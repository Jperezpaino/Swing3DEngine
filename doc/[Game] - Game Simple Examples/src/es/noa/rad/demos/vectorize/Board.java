package es.noa.rad.demos.vectorize;

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
  private final int PANEL_WIDTH = 800;
  private final int PANEL_HEIGHT = 600;
  private BufferedImage workImage;
  
  public Board() {
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   super.setDoubleBuffered(true);   
   this.workImage = this.loadImageDrawing(".//res//vectorize//basicShape.png");
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
      
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 