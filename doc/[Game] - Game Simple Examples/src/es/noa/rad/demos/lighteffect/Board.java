package es.noa.rad.demos.lighteffect;

 import
  java.awt.AlphaComposite;
 import
  java.awt.Color;
 import
  java.awt.Dimension;
 import
  java.awt.Graphics;
 import
  java.awt.Graphics2D;
 import
  java.awt.MultipleGradientPaint.CycleMethod;
 import
  java.awt.Point;
 import
  java.awt.RadialGradientPaint;
 import
  java.awt.Toolkit;
 import
  java.awt.event.MouseEvent;
 import
  java.awt.event.MouseMotionListener;
 import
  java.awt.geom.Point2D;
 import
  java.awt.image.BufferedImage;
 import
  java.util.Random;
 import
  javax.swing.JPanel;

 public class Board extends JPanel implements MouseMotionListener {
  private static final long serialVersionUID = 1L;
  private final int PANEL_WIDTH = 800;
  private final int PANEL_HEIGHT = 600;
  private Point point = new Point(0,0);
  private BufferedImage backGroundImage;
  private BufferedImage shadowImage;
  
  public Board() {
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
   super.setDoubleBuffered(true);
   this.backGroundImage = this.createBackGroundImage();
   this.shadowImage = new BufferedImage(
    this.backGroundImage.getWidth(),
    this.backGroundImage.getHeight(),
    BufferedImage.TYPE_INT_ARGB);
   super.addMouseMotionListener(this);
  }
 
  @Override
  public void paintComponent(final Graphics graphics) {
   super.paintComponent(graphics);
   this.drawComponent(graphics);
  }

  private void drawComponent(final Graphics graphics) {
   // Establecemos el contexto grafico.
   Graphics2D graphics2D = (Graphics2D) graphics;
   graphics2D.drawImage(this.backGroundImage, 0, 0, this);
   this.drawLightsAndShadows();
   graphics2D.drawImage(this.shadowImage, 0,0, null);
      
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
  
  private BufferedImage createBackGroundImage() {
   BufferedImage backGroundImage = new BufferedImage(
    PANEL_WIDTH, PANEL_HEIGHT, BufferedImage.TYPE_INT_ARGB);
   Graphics graphics = backGroundImage.getGraphics();
   Random random = new Random(0);
   for (int i = 0; i < 200; i++) {
    int positionX = random.nextInt(PANEL_WIDTH);
    int positionY = random.nextInt(PANEL_HEIGHT);
    Color color = new Color(
     random.nextInt(255),
     random.nextInt(255),
     random.nextInt(255));
    graphics.setColor(color);
    graphics.fillOval(positionX - 20, positionY - 20, 40, 40);
   }
   graphics.dispose();
   return backGroundImage;
  }
  
  private void drawLightsAndShadows() {
   Graphics2D graphics2D = this.shadowImage.createGraphics();
   graphics2D.setComposite(AlphaComposite.Src);
   graphics2D.setColor(new Color(0,0,16,240));
   graphics2D.fillRect(0, 0, this.shadowImage.getWidth(), this.shadowImage.getHeight());
   this.drawLight(graphics2D, new Point(100, 100));
   this.drawLight(graphics2D, this.point);
   this.drawShadow(graphics2D, new Point(250, 250));
   graphics2D.dispose();
  }

  private void drawLight(final Graphics2D graphics2DParam, final Point pointParam) {
   float radius = 100;
   graphics2DParam.setComposite(AlphaComposite.DstOut);
   Point2D center = new Point2D.Double(pointParam.getX(), pointParam.getY());
   float[] distance = {0.0f, 1.0f};
   Color[] colors = {
    new Color(255, 255, 255, 255),
    new Color(0, 0, 0, 0)};
   RadialGradientPaint radialGradientPaint = new RadialGradientPaint(
    center, radius, distance, colors, CycleMethod.NO_CYCLE);
   graphics2DParam.setPaint(radialGradientPaint);
   graphics2DParam.fillOval(
    (int) (pointParam.getX() - (double) radius),
    (int) (pointParam.getY() - (double) radius),
    (int) (radius * 2),
    (int) (radius * 2));
  }
  
  private void drawShadow(final Graphics2D graphics2DParam, final Point pointParam) {
   float radius = 75;
   graphics2DParam.setComposite(AlphaComposite.DstOver);
   Point2D center = new Point2D.Double(pointParam.getX(), pointParam.getY());
   float[] distance = {0.0f, 0.7f, 1.0f};
   Color[] colors = { 
    new Color(0,0,0,200),
    new Color(0,0,0,150),
    new Color(255,255,255,0)};
   RadialGradientPaint radialGradientPaint = new RadialGradientPaint(
    center, radius, distance, colors, CycleMethod.NO_CYCLE);
   graphics2DParam.setPaint(radialGradientPaint);
   graphics2DParam.fillOval(
    (int) (pointParam.getX() - (double) radius),
    (int) (pointParam.getY() - (double) radius),
    (int) (radius * 2),
    (int) (radius * 2));
  }
  
  @Override
  public void mouseDragged(final MouseEvent mouseEventParam) {
  }

  @Override
  public void mouseMoved(final MouseEvent mouseEventParam) {
   this.point = mouseEventParam.getPoint();
   super.repaint();
  }

 }
