package es.noa.rad.demos.loadImage;

 import java.awt.Color;
import
  java.awt.Dimension;
 import
  java.awt.Graphics;
 import
  java.awt.Graphics2D;
import java.awt.Image;
import
  java.awt.Toolkit;
 import
  java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import
  java.io.File;
 import
  java.io.IOException;
import java.nio.ByteBuffer;
import
  javax.imageio.ImageIO;
 import
  javax.swing.JPanel;

 public class Board extends JPanel {
  private static final long serialVersionUID = 1L;

  /**
    * Definimos el ancho del 'Board' o tablero de juego. 
    */
  private static final Integer BOARD_WIDTH = new Integer(692);

  /**
    * Definimos el alto del 'Board' o tablero de juego. 
    */  
  private static final Integer BOARD_HEIGHT = new Integer(944);

  /**
    * Definimos la 'image' que dibujaremos.
    */
//  private BufferedImage image;
//  private BufferedImage imageCopy;
//  private ByteBuffer buffer;
//  
  
  private BufferedImage cardBase;
  private final String cardBaseSource = ".//res//images//card.png";
  private BufferedImage cardAttack;
  private final String cardAttackSource = ".//res//images//attack-texture.png";  
  private BufferedImage cardHealth;
  private final String cardHealthSource = ".//res//images//health-texture.png";  
  
  /**
   * Definimos la ruta de la 'image' que dibujaremos.
   */
  private final String imageSource = ".//res//images//card.png";
  
  /**
    *  
    */
  public Board() {
   /* Establecemos el tamaño del 'Board' o tablero de juego. */
   super.setPreferredSize(
    new Dimension(Board.BOARD_WIDTH.intValue(), Board.BOARD_HEIGHT.intValue()));
   /* Establecemos el uso del doble buffer. */
   super.setDoubleBuffered(true);   
   /* Cargamos la imagen desde el directorio de recursos. */
//   this.image
//    = this.loadImageDrawing(imageSource);

   this.cardBase
   = this.loadImageDrawing(this.cardBaseSource);
   this.cardAttack
   = this.loadImageDrawing(this.cardAttackSource);
   this.cardHealth
   = this.loadImageDrawing(this.cardHealthSource);     
   
   
//   this.imageCopy = new BufferedImage(this.image.getWidth(), this.image.getHeight(), BufferedImage.TYPE_INT_RGB);
//   Graphics2D g = this.imageCopy.createGraphics();
//   try {
//       g.drawImage(this.image, 0, 0, null);
//   }
//   finally {
//       g.dispose();
//   }
//  
//   final Image imageWithTransparency = makeColorTransparent(this.image, new Color(255,255,255));
//
//   this.imageCopy = imageToBufferedImage(imageWithTransparency);
//
//   try {
//    buffer = decodePng(this.image);
//   } catch (IOException e) {
//    // TODO Auto-generated catch block
//    e.printStackTrace();
//   }
   

  }

  /**
    *
    */
  @Override
  public final void paintComponent(
         final Graphics graphics) {
   super.paintComponent(graphics);
   this.drawComponent(graphics);
  }

  /**
    * 
    * @param graphics
    */
  private final void drawComponent(
          final Graphics graphics) {
   /* Establecemos el contexto grafico. */
   Graphics2D graphics2D = (Graphics2D) graphics;
   
   /* Dibujamos la imagen. */
   graphics2D.drawImage(this.cardBase, 0, 0, this);
   graphics2D.drawImage(this.cardAttack, 100, 0, this);
   graphics2D.drawImage(this.cardHealth, 347, 620,
     (int)(this.cardHealth.getWidth() / 1.4), // Ancho total de la imagen. 
     (int)(this.cardHealth.getHeight() / 1.4), // Alto total de la imagen.
     this);
//   graphics2D.drawImage(this.imageCopy, 522, 10, this);

   /* Establecemos la sincronización en el contexto grafico. */
   Toolkit.getDefaultToolkit().sync();
  }

  /**
    * 
    * @param imageSourceParam
    * @return
    */
  private final BufferedImage loadImageDrawing(
          final String imageSourceParam) {
   BufferedImage bufferedImage = null;
   try {
    bufferedImage = ImageIO.read(new File(imageSourceParam));
   } catch (IOException exception) {
    System.out.println("Imposible cargar la imagen: " + imageSourceParam.toString());
    System.exit(1);
   }
   return bufferedImage;
  }

  public static ByteBuffer decodePng( BufferedImage image )
    throws IOException
{

int width = image.getWidth();
int height = image.getHeight();

// Load texture contents into a byte buffer
ByteBuffer buf = ByteBuffer.allocateDirect( 4 * width * height );

// decode image
// ARGB format to -> RGBA
for( int h = 0; h < height; h++ )
  for( int w = 0; w < width; w++ ) {
     int argb = image.getRGB( w, h );
     buf.put( (byte) ( 0xFF & ( argb >> 16 ) ) );
     buf.put( (byte) ( 0xFF & ( argb >> 8 ) ) );
     buf.put( (byte) ( 0xFF & ( argb ) ) );
     buf.put( (byte) ( 0xFF & ( argb >> 24 ) ) );
  }
buf.flip();
return buf;
}
  
  
  /**
   * Make provided image transparent wherever color matches the provided color.
   *
   * @param im BufferedImage whose color will be made transparent.
   * @param color Color in provided image which will be made transparent.
   * @return Image with transparency applied.
   */
  public static Image makeColorTransparent(final BufferedImage im, final Color color)
  {
     final ImageFilter filter = new RGBImageFilter()
     {
        // the color we are looking for (white)... Alpha bits are set to opaque
        public int markerRGB = color.getRGB() | 0xFFFFFFFF;

        public final int filterRGB(final int x, final int y, final int rgb)
        {
           //System.out.println((rgb | 0xFF000000));

//           if ((rgb | 0xFF000000) == markerRGB)
//           {
//              // Mark the alpha bits as zero - transparent
//              return 0x00FFFFFF & rgb;
//           }
//           else
//           {
//              // nothing to do
//              return rgb;
//           }
         
         

         int alpha = ((rgb >> 24) & 0xff);
         // System.out.println("Alpha: " + ((rgb >> 32) & 0xff)); 
         //System.out.println("red: " + ((rgb >> 16) & 0xff));
         //System.out.println("green: " + ((rgb >> 8) & 0xff));
         //System.out.println("blue: " + ((rgb >> 0) & 0xff));
         return (rgb & 0xFF000000) | (alpha << 16) | (alpha << 8) | (alpha << 0);         
        }
     };

     final ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
     return Toolkit.getDefaultToolkit().createImage(ip);
  }
  /**
   * Convert Image to BufferedImage.
   *
   * @param image Image to be converted to BufferedImage.
   * @return BufferedImage corresponding to provided Image.
   */
  private static BufferedImage imageToBufferedImage(final Image image)
  {
     final BufferedImage bufferedImage =
        new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
     final Graphics2D g2 = bufferedImage.createGraphics();
     g2.drawImage(image, 0, 0, null);
     g2.dispose();
     return bufferedImage;
   }
 }
 