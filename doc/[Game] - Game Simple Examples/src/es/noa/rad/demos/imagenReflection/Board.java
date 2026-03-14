package es.noa.rad.demos.imagenReflection;

 import java.awt.AlphaComposite;
import
  java.awt.Color;
 import
  java.awt.Dimension;
import java.awt.GradientPaint;
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
   this.workImage = this.loadImageDrawing(".//res//imagereflection//imagereflection.png");
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
  
   // Recuperamos las dimensiones para distribuir la imagen.
   int width = super.getWidth();
   int height = super.getHeight();   
   int imageWidth = this.workImage.getWidth();
   int imageHeight = this.workImage.getHeight();
   
   int gap = 20;
   float opacity = 0.4f;
   float fadeHeight = 0.3f;

   graphics2D.setPaint(new GradientPaint( 0, 0, Color.black, 0, height, Color.darkGray ) );
   graphics2D.fillRect(0, 0, width, height );
   graphics2D.translate( (width-imageWidth)/2, height/2-imageHeight );
   graphics2D.drawRenderedImage(this.workImage, null );
   graphics2D.translate(0, 2*imageHeight+gap );
   graphics2D.scale( 1, -1 ); // Invierte la imagen.

   BufferedImage reflection = new BufferedImage( imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB );
   Graphics2D rg = reflection.createGraphics();
   rg.drawRenderedImage(this.workImage, null );
   rg.setComposite( AlphaComposite.getInstance( AlphaComposite.DST_IN ) );
   rg.setPaint( 
       new GradientPaint( 
           0, imageHeight*fadeHeight, new Color( 0.0f, 0.0f, 0.0f, 0.0f ),
           0, imageHeight, new Color( 0.0f, 0.0f, 0.0f, opacity )
       )
   );
   rg.fillRect( 0, 0, imageWidth, imageHeight );
   rg.dispose();
   graphics2D.drawRenderedImage( reflection, null );
   

   
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 