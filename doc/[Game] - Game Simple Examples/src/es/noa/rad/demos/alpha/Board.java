package es.noa.rad.demos.alpha;

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
  private final int PANEL_WIDTH = 715;
  private final int PANEL_HEIGHT = 715;
  private BufferedImage workImage;
  private BufferedImage blackAndWhiteMaskImage;
  private BufferedImage darkGreyScaleMaskImage;  
  private BufferedImage lightGreyScaleMaskImage;
  
  public Board() {
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   super.setDoubleBuffered(true);   
   this.workImage = this.loadImageDrawing(".//res//alpha//alphaImage.png");
   this.blackAndWhiteMaskImage = this.loadImageDrawing(".//res//alpha//blackAndWhiteMask.png");
   this.darkGreyScaleMaskImage = this.loadImageDrawing(".//res//alpha//darkGreyScaleMask.png");
   this.lightGreyScaleMaskImage = this.loadImageDrawing(".//res//alpha//lightGreyScaleMask.png");
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
   
   // Imagenes original.
   graphics2D.drawImage(this.workImage, 10, 10, this);
   graphics2D.drawImage(this.workImage, 245, 10, this);
   graphics2D.drawImage(this.workImage, 480, 10, this);
   
   // Mascaras originales.
   graphics2D.drawImage(this.blackAndWhiteMaskImage, 10, 245, this);
   graphics2D.drawImage(this.darkGreyScaleMaskImage, 245, 245, this);
   graphics2D.drawImage(this.lightGreyScaleMaskImage, 480, 245, this);
   
   // Aplicamos las mascaras
   BufferedImage blackAndWhiteMaskImageAlpha = applyGrayscaleMaskToAlpha(this.workImage, this.blackAndWhiteMaskImage);
   graphics2D.drawImage(blackAndWhiteMaskImageAlpha, 10, 480, this);
   BufferedImage darkGreyScaleMaskImageAlpha = applyGrayscaleMaskToAlpha(this.workImage, this.darkGreyScaleMaskImage);
   graphics2D.drawImage(darkGreyScaleMaskImageAlpha, 245, 480, this);
   BufferedImage lightGreyScaleMaskImageAlpha = applyGrayscaleMaskToAlpha(this.workImage, this.lightGreyScaleMaskImage);
   graphics2D.drawImage(lightGreyScaleMaskImageAlpha, 480, 480, this);
   
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

  public BufferedImage applyGrayscaleMaskToAlpha(BufferedImage image, BufferedImage mask) {
   int width = image.getWidth();
   int height = image.getHeight();
   int[] imagePixels = image.getRGB(0, 0, width, height, null, 0, width);
   int[] maskPixels = mask.getRGB(0, 0, width, height, null, 0, width);
   for (int i = 0; i < imagePixels.length; i++) {
    int color = imagePixels[i] & 0x00ffffff; // Mask preexisting alpha
    int alpha = maskPixels[i] << 24; // Shift blue to alpha
    imagePixels[i] = color | alpha;
   }
   BufferedImage imageResult = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
   imageResult.setRGB(0, 0, width, height, imagePixels, 0, width);
   return imageResult;
  }
  
 }
 