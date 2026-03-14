package es.noa.rad.demos.scaling;

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
  private final int PANEL_WIDTH = 221;
  private final int PANEL_HEIGHT = 590;
  private BufferedImage workImage;
  
  public Board() {
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   super.setDoubleBuffered(true);   
   this.workImage = this.loadImageDrawing(".//res//scaling//scaling.png");
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
   
   // Escalado utilizando coordenadas.
   // Imagen original.
   graphics2D.drawImage(this.workImage, 10, 10, this);
   // Imagen al doble de tamaño.
   graphics2D.drawImage(this.workImage,
    73, 10, // Coordenada con la posición.
    this.workImage.getWidth() * 2, // Ancho total de la imagen. 
    this.workImage.getHeight() * 2, // Alto total de la imagen.
    null);
   // Imagen a la mitad de tamaño.   
   graphics2D.drawImage(this.workImage,
    189, 10, // Coordenada con la posición.
    this.workImage.getWidth() / 2, // Ancho total de la imagen. 
    this.workImage.getHeight() / 2, // Alto total de la imagen.
    null);
   
   // Escalado utilizando transformaciones.
   AffineTransform scalingTransform = null;
   // Imagen original.
   graphics2D.drawImage(this.workImage, 10, 126, this);
   // Imagen al doble de tamaño.  
   scalingTransform = AffineTransform.getTranslateInstance(64, 126);
   scalingTransform.scale(2.0, 2.0);
   graphics2D.drawImage(this.workImage, scalingTransform, null);
   // Imagen a la mitad de tamaño.   
   scalingTransform = AffineTransform.getTranslateInstance(189, 126);
   scalingTransform.scale(0.5, 0.5);
   graphics2D.drawImage(this.workImage, scalingTransform, null);

   /* 
    * Escalado utilizando transformaciones, y el algoritmo de interpolación por
    * aproximación, se basa en obtener el promedio de valores de los 2 pixeles
    * más próximos, y con estos generar un nuevo pixel con un color que suaviza
    * la progresión.
    */
   AffineTransform scalingApproachTransform = null;
   AffineTransformOp scalingApproachTransformOperation = null;
   // Imagen original.
   graphics2D.drawImage(this.workImage, 10, 242, this);
   // Imagen al doble de tamaño.  
   scalingApproachTransform = AffineTransform.getScaleInstance(2.0, 2.0);
   scalingApproachTransformOperation =
    new AffineTransformOp(scalingApproachTransform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);   
   graphics2D.drawImage(this.workImage, scalingApproachTransformOperation, 64, 242);
   // Imagen a la mitad de tamaño.   
   scalingApproachTransform = AffineTransform.getScaleInstance(0.5, 0.5);
   scalingApproachTransformOperation =
    new AffineTransformOp(scalingApproachTransform, AffineTransformOp.TYPE_BILINEAR);   
   graphics2D.drawImage(this.workImage, scalingApproachTransformOperation, 189, 242);   
   
   /*
    * Escalado utilizando transformaciones, y el algoritmo de interpolación
    * bilineal, se basa en obtener el promedio de valores como el algoritmo por
    * aproximación pero en este caso se hace uso de los 4 pixeles adyacentes, y
    * con estos generar un nuevo pixel con un color que suaviza la progresión.
    */
   AffineTransform scalingBilinearTransform = null;
   AffineTransformOp scalingBilinearTransformOperation = null;
   // Imagen original.
   graphics2D.drawImage(this.workImage, 10, 358, this);
   // Imagen al doble de tamaño.  
   scalingBilinearTransform = AffineTransform.getScaleInstance(2.0, 2.0);
   scalingBilinearTransformOperation =
    new AffineTransformOp(scalingBilinearTransform, AffineTransformOp.TYPE_BILINEAR);   
   graphics2D.drawImage(this.workImage, scalingBilinearTransformOperation, 64, 358);
   // Imagen a la mitad de tamaño.   
   scalingBilinearTransform = AffineTransform.getScaleInstance(0.5, 0.5);
   scalingBilinearTransformOperation =
    new AffineTransformOp(scalingBilinearTransform, AffineTransformOp.TYPE_BILINEAR);   
   graphics2D.drawImage(this.workImage, scalingBilinearTransformOperation, 189, 358);
     
   /*
    * Escalado utilizando transformaciones, y el algoritmo de interpolación
    * bicubica, se basa en obtener el promedio de valores como el algoritmo por
    * aproximación pero en este caso se hace uso de los 16 pixeles adyacentes, y
    * con estos generar un nuevo pixel con un color que suaviza la progresión,
    * es el método de interpolación considerado estándar
    */
   AffineTransform scalingBicubicTransform = null;
   AffineTransformOp scalingBicubicTransformOperation = null;
   // Imagen original.
   graphics2D.drawImage(this.workImage, 10, 474, this);
   // Imagen al doble de tamaño.  
   scalingBicubicTransform = AffineTransform.getScaleInstance(2.0, 2.0);
   scalingBicubicTransformOperation =
    new AffineTransformOp(scalingBicubicTransform, AffineTransformOp.TYPE_BICUBIC);   
   graphics2D.drawImage(this.workImage, scalingBicubicTransformOperation, 64, 474);
   // Imagen a la mitad de tamaño.   
   scalingBicubicTransform = AffineTransform.getScaleInstance(0.5, 0.5);
   scalingBicubicTransformOperation =
    new AffineTransformOp(scalingBicubicTransform, AffineTransformOp.TYPE_BICUBIC);   
   graphics2D.drawImage(this.workImage, scalingBicubicTransformOperation, 189, 474);
   
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 