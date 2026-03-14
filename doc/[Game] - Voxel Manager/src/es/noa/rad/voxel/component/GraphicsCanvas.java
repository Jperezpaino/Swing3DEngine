package es.noa.rad.voxel.component;

 import
  java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
// import
//  java.awt.Font;
 import
  java.awt.Graphics;
// import
//  java.awt.Graphics2D;
//import java.awt.MouseInfo;
//import
//  java.awt.Point;
//import java.awt.Rectangle;
//import
//  java.awt.RenderingHints;
//import java.awt.event.ActionEvent;
 import
  java.awt.event.ActionListener;
 import
  java.awt.image.BufferedImage;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Map.Entry;
 import
  javax.swing.JComponent;
 import
  javax.swing.Timer;
import es.noa.rad.voxel.adapter.GraphicsComponentListener;
import es.noa.rad.voxel.adapter.GraphicsWindowListener;
import es.noa.rad.voxel.component.adapter.GraphicsCanvasMouseListener;
//import
//  es.noa.rad.demos.graphics.WindowDemo.component.adapter.GraphicsDemoCanvasMouseMotionListener;
// import
//  es.noa.rad.demos.graphics.WindowDemo.component.adapter.GraphicsDemoCanvasMouseListener;
// import
//  es.noa.rad.demos.graphics.WindowDemo.objects.IsoMap;
// import
//  es.noa.rad.demos.graphics.WindowDemo.objects.IsoTile;
// import
//  es.noa.rad.demos.graphics.WindowDemo.objects.IsoTileUtils;
import es.noa.rad.voxel.component.adapter.GraphicsCanvasMouseMotionListener;

 public class GraphicsCanvas extends JComponent implements ActionListener {
  private static final long serialVersionUID = 1L;
  
  /**
    *  Declaramos los elementos que forman el componente. 
    */
  
  /**
    * 'Imagen' que contendra el 'Buffer' grafico.
    */
  private BufferedImage backBufferGraphics;
  
  /**
    * 'Timer' que controlara los refrescos de la pantalla. 
    */
  private Timer timer;
  
  /**
    * Ancho total que presenta el canvas. 
    */
  private int canvasWidth;

  /**
    * Alto total que presenta el canvas. 
    */
  private int canvasHeight;
  
  /**
    *  Declaramos los distintos adaptadores de la ventana.
    */
  private GraphicsCanvasMouseListener mouseListener;
  private GraphicsCanvasMouseMotionListener mouseMotionListener;
  
  
  
  
  
  

 


  
// // Declaramos los objetos de la ventana
// private IsoMap map;
// private Point actionLayerPosition;
// private boolean mapMovementStatus;
// private int canvasXDisplacement;
// private int canvasXIncrement;
// private int canvasYDisplacement;
// private int canvasYIncrement;
// 

 
//  /**
//    * Variable privada de tipo {@code HashMap<Point, Rectangle>} que contiene
//    * una tabla 'Hash'con las diferentes zonas de acción que presenta e
//    * 'Canvas'.
//    */
//  private HashMap<Point, Rectangle> canvasActionMapper;
//  private Point canvasMouseLastPosition;
 
  /**
    * Constructor.
    */
  public GraphicsCanvas() {
   super.setBackground(Color.white);
   this.mouseListener = new GraphicsCanvasMouseListener(this);
   super.addMouseListener(this.mouseListener);   
   super.addMouseWheelListener(this.mouseListener);   
   this.mouseMotionListener = new GraphicsCanvasMouseMotionListener(this);
   super.addMouseMotionListener(this.mouseMotionListener);

   
//  this.canvasWidth = 0;
//  this.canvasHeight = 0;
//  this.actionLayerPosition = new Point(0, 0);
//  this.canvasMouseLastPosition = new Point(0, 0);
//  this.mapMovementStatus = false;
//  this.canvasXDisplacement = 0;
//  this.canvasXIncrement = 0;
//  this.canvasYDisplacement = 0;
//  this.canvasYIncrement = 0;
//  this.setMap(new IsoMap());
//  this.reloadActionMap();
  
   this.timer = new Timer(10, this);
   this.timer.start();   
  }
 
  /**
    * 
    * @return
    */
  public final Graphics2D createBackBufferGraphics() {
   Graphics2D graphics2D = null;

   if ((this.backBufferGraphics == null) 
    || (this.backBufferGraphics.getWidth() != this.canvasWidth) 
    || (this.backBufferGraphics.getHeight() != this.canvasHeight)) {
   this.backBufferGraphics = 
    (BufferedImage) super.createImage(this.canvasWidth, this.canvasHeight);
  }

  if (this.backBufferGraphics != null) {
   graphics2D = this.backBufferGraphics.createGraphics();
   graphics2D.setBackground(super.getBackground());
  }

  // Establecemos los atributos del contexto grafico.
  graphics2D.setRenderingHint(
   RenderingHints.KEY_ANTIALIASING,
   RenderingHints.VALUE_ANTIALIAS_ON);

  // Limpiamos el canvas.
  graphics2D.clearRect(0, 0, this.canvasWidth, this.canvasHeight);
  
  // Definimos la fuente por defecto.
  graphics2D.setFont(new Font("helvetica", Font.BOLD, 9));
  
  return graphics2D;
 }
 
  public final void paint(
         final Graphics graphicsParam) {
   Graphics graphics = graphicsParam;   
   if ((this.canvasWidth > 0 || this.canvasHeight > 0)) {
    Graphics2D graphics2D = this.createBackBufferGraphics();
    this.draw(graphics2D);
    graphics2D.dispose();
    if (this.backBufferGraphics != null && isShowing()) {
     graphics.drawImage(this.backBufferGraphics, 0, 0, this);
    }
   }
  }
 
 
 
 
 
 
 
  /**
    * 
    * @return
    */
  public final BufferedImage getBackBufferGraphics() {
   return this.backBufferGraphics;
  }
 
  /**
    * 
    * @param backBufferGraphicsParam
    */
  public final void setBackBufferGraphics(
         final BufferedImage backBufferGraphicsParam) {
   this.backBufferGraphics = backBufferGraphicsParam;
  }

  /**
    * 
    * @return
    */
  public final int getCanvasWidth() {
   return this.canvasWidth;
  }
 
  /**
    * 
    * @param canvasWidthParam
    */
  public final void setCanvasWidth(
         final int canvasWidthParam) {
   this.canvasWidth = canvasWidthParam;  
  }
 
  /**
    * 
    * @return
    */
  public final int getCanvasHeight() {
   return this.canvasHeight;
  }
 
  /**
    * 
    * @param canvasHeightParam
    */
  public final void setCanvasHeight(
         final int canvasHeightParam) {
   this.canvasHeight = canvasHeightParam;
  }


  
  
  
  public final void updateSizes() {
   this.setCanvasWidth(super.getSize().width);
   this.setCanvasHeight(super.getSize().height);
   //this.reloadActionMap();
  }
 
// public IsoMap getMap() {
//  return map;
// }
//
// public void setMap(IsoMap map) {
//  this.map = map;
// }
 
 
  public final void draw(
         final Graphics2D graphics2DParam) {
   Graphics2D graphics2D = graphics2DParam;
  
   /*
    * Dibujamos el fondo con el color establecido.
    */
   Color backGroundColor = Color.black;
   graphics2D.setPaint(backGroundColor);
   graphics2D.fillRect(0, 0, this.canvasWidth, this.canvasHeight);   
   /*
    *  Calculamos el centro del 'canvas'.
    */
   Point centerPoint = new Point(
    ((int) (this.canvasWidth / 2)),
    ((int) (this.canvasHeight / 2)));
   
//   // Definimos las capas de dibujo.
//   BufferedImage wireFrameGraphics = null;
//   BufferedImage physicalMapLayerGraphics = null;
//   BufferedImage selectedTileGraphics = null;
//   
//   // Generamos las capas.
//   wireFrameGraphics = this.drawWireFrame();
//   physicalMapLayerGraphics = this.drawPhysicalMapLayer();
//   if(this.map.isSelectedTile()) {
//    selectedTileGraphics = this.drawSelectedTile();
//   }
   
//   centerPoint.translate(
//    ((int) ((wireFrameGraphics.getWidth() / 2) * -1)),
//    ((int) ((wireFrameGraphics.getHeight() / 2) * -1)));
//   this.actionLayerPosition = centerPoint;
   
//   centerPoint.translate(this.canvasXDisplacement, this.canvasYDisplacement); 
   
//   boolean updatePosition = false;
// 
//   if(centerPoint.getY() > 0) {
//    this.setCanvasYDisplacement((int)(this.getCanvasYDisplacement() - centerPoint.getY()));
//    updatePosition = true;
//   } else if(Math.abs(centerPoint.getY()) > (wireFrameGraphics.getHeight() - this.canvasHeight)) {
//    this.setCanvasYDisplacement((int)(this.getCanvasYDisplacement() + (Math.abs(centerPoint.getY()) - (wireFrameGraphics.getHeight() - this.canvasHeight))));
//    updatePosition = true;
//   }
//   
//   if(centerPoint.getX() > 0) {
//    this.setCanvasXDisplacement((int)(this.getCanvasXDisplacement() - centerPoint.getX()));
//    updatePosition = true;
//   } else if(Math.abs(centerPoint.getX()) > (wireFrameGraphics.getWidth() - this.canvasWidth)) {
//    this.setCanvasXDisplacement((int)(this.getCanvasXDisplacement() + (Math.abs(centerPoint.getX()) - (wireFrameGraphics.getWidth() - this.canvasWidth))));
//    updatePosition = true;
//   }
   
//   if(updatePosition) {
//    centerPoint = new Point(
//     ((int) (this.canvasWidth / 2)),
//     ((int) (this.canvasHeight / 2)));    
//    centerPoint.translate(
//     ((int) ((wireFrameGraphics.getWidth() / 2) * -1)),
//     ((int) ((wireFrameGraphics.getHeight() / 2) * -1)));
//    this.actionLayerPosition = centerPoint;
//    centerPoint.translate(this.canvasXDisplacement, this.canvasYDisplacement); 
//   }
   
   
//   graphics2D.drawImage(
//    wireFrameGraphics, ((int) this.actionLayerPosition.getX()), ((int) this.actionLayerPosition.getY()), this);
//   graphics2D.drawImage(
//    physicalMapLayerGraphics, ((int) this.actionLayerPosition.getX()), ((int) this.actionLayerPosition.getY()), this);   
//   if(this.map.isSelectedTile()) {
//    graphics2D.drawImage(
//     selectedTileGraphics, ((int) this.actionLayerPosition.getX()), ((int) this.actionLayerPosition.getY()), this);
//   }
//   
//   graphics2D.drawImage(
//    this.drawLayerMouseActionCanvas(this.canvasActionMapper), 0, 0, this);
   
   /*HashMap<Point, Rectangle> mapActionMapper = this.map.getMapActionMapper();
   graphics2D.drawImage(
    this.drawLayerMouseActionMap(this.map, mapActionMapper),
    ((int) this.actionLayerPosition.getX() - this.map.getMapTile().getTileWidth()),
    ((int) this.actionLayerPosition.getY() - this.map.getMapTile().getTileHeight()), this);*/
   
   /*graphics2D.drawImage(
    drawMouseControlLayer(), ((int) this.actionLayerPosition.getX()), ((int) this.actionLayerPosition.getY()), this);*/
   
   /*graphics2D.drawImage(
    drawAllTileTest(), 0, 0, this);*/
   
 }

// 
//  public final void reloadActionMap() {
//   // Creamos las zonas de acción del 'Canvas'
//   this.canvasActionMapper = new HashMap<Point, Rectangle>();
//   Point actionCanvasPoint = null;
//   Rectangle actionCanvasArea = null;
//   // Esquina superior izquierda.
//   actionCanvasPoint = new Point(0, 0);
//   actionCanvasArea = new Rectangle(
//    (int) 0, (int) 0, (int) 9, (int) 9);
//   this.canvasActionMapper.put(actionCanvasPoint, actionCanvasArea);
//   // Cuadro superior
//   actionCanvasPoint = new Point(1, 0);
//   actionCanvasArea = new Rectangle(
//    (int) 10, (int) 0, (int) this.getCanvasWidth() - 21, (int) 9);
//   this.canvasActionMapper.put(actionCanvasPoint, actionCanvasArea);     
//   // Esquina superior derecha.
//   actionCanvasPoint = new Point(2, 0);
//   actionCanvasArea = new Rectangle(
//    (int) this.getCanvasWidth() - 10, (int) 0, (int) 9, (int) 9);
//   this.canvasActionMapper.put(actionCanvasPoint, actionCanvasArea);
//   // Cuadro izquierdo.
//   actionCanvasPoint = new Point(0, 1);
//   actionCanvasArea = new Rectangle(
//    (int) 0, (int) 10, (int) 9, (int) this.getCanvasHeight() - 21);
//   this.canvasActionMapper.put(actionCanvasPoint, actionCanvasArea);
//   // Cuadro derecho.
//   actionCanvasPoint = new Point(2, 1);
//   actionCanvasArea = new Rectangle(
//    (int) this.getCanvasWidth() - 10, (int) 10, (int) 9, (int) this.getCanvasHeight() - 21);
//   this.canvasActionMapper.put(actionCanvasPoint, actionCanvasArea);
//   // Esquina inferior izquierda.
//   actionCanvasPoint = new Point(0, 2);
//   actionCanvasArea = new Rectangle(
//    (int) 0, (int) this.getCanvasHeight() - 10, (int) 9, (int) 9);
//   this.canvasActionMapper.put(actionCanvasPoint, actionCanvasArea);
//   // Cuadro inferior
//   actionCanvasPoint = new Point(1, 2);
//   actionCanvasArea = new Rectangle(
//    (int) 10, (int) this.getCanvasHeight() - 10, (int) this.getCanvasWidth() - 21, (int) 9);
//   this.canvasActionMapper.put(actionCanvasPoint, actionCanvasArea);     
//   // Esquina inferior derecha. 
//   actionCanvasPoint = new Point(2, 2);
//   actionCanvasArea = new Rectangle(
//    (int) this.getCanvasWidth() - 10, (int) this.getCanvasHeight() - 10, (int) 9, (int) 9);
//   this.canvasActionMapper.put(actionCanvasPoint, actionCanvasArea);   
//  } 
//  
//  public final BufferedImage drawAllTileTest() {
//   // Generamos un imagen para facilitarnos el pintado.
//   BufferedImage mapBufferGraphics =
//    new BufferedImage(500, 750, BufferedImage.TYPE_INT_ARGB);
//   // Recuperamos el contexto grafico.
//   Graphics2D graphics2D = mapBufferGraphics.createGraphics();
//   // Establecemos los atributos del contexto grafico.
//   graphics2D.setRenderingHint(
//    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//   // Dibujamos el fondo con el color establecido.
//   Color backGroundColor = this.map.getMapConstraints().getMapBackGroundColor();
//   graphics2D.setPaint(backGroundColor);
//   graphics2D.fillRect(0, 0, 500, 750);
//
//   // Generamos un punto de referencia sobre el que empezar a dibujar.
//   Point starPoint = new Point(0, 0);
//   // Recuperamos la información de la tile que se va a dibujar.
//   IsoTile tileInfo = this.map.getMapTile();
//   
//   starPoint.setLocation(30, 20);
//   IsoTileUtils.drawTileLayer(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTileLayerBorder(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawBottomTileLayer(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawBottomTileLayerBorder(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTopTileLayer(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTopTileLayerBorder(graphics2D, tileInfo, starPoint);
//      
//   starPoint.setLocation(30, 60);
//   IsoTileUtils.drawBottomLayer(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawBottomLayerBorder(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawBottomRightLayer(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawBottomRightLayerBorder(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawBottomLeftLayer(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawBottomLeftLayerBorder(graphics2D, tileInfo, starPoint);
//   
//   starPoint.setLocation(30, 100);
//   IsoTileUtils.drawTopLayer(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTopLayerBorder(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTopRightLayer(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTopRightLayerBorder(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTopLeftLayer(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTopLeftLayerBorder(graphics2D, tileInfo, starPoint);
//   
//   starPoint.setLocation(30, 140);
//   IsoTileUtils.drawBottomBorderTop(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomBorderBottom(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomBorderTopRight(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomBorderTopLeft(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomBorderBottomRight(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomBorderBottomLeft(graphics2D, tileInfo, starPoint);   
//   
//   starPoint.setLocation(30, 180);
//   IsoTileUtils.drawBottomLayerBorderTop(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomLayerBorderBottom(graphics2D, tileInfo, starPoint);    
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomLayerBorderTopRight(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomLayerBorderTopLeft(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomLayerBorderBottomRight(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomLayerBorderBottomLeft(graphics2D, tileInfo, starPoint); 
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawBottomLayerBorderRight(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomLayerBorderLeft(graphics2D, tileInfo, starPoint);    
//    
//   
//   
//   starPoint.setLocation(30, 220);    
//   IsoTileUtils.drawBottomRightBorderTop(graphics2D, tileInfo, starPoint);    
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomRightBorderBottom(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);       
//   IsoTileUtils.drawBottomRightBorderTopHigh(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);      
//   IsoTileUtils.drawBottomRightBorderTopSide(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);     
//   IsoTileUtils.drawBottomRightBorderBottomHigh(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);      
//   IsoTileUtils.drawBottomRightBorderBottomSide(graphics2D, tileInfo, starPoint); 
//   
//   starPoint.setLocation(30, 260);
//   IsoTileUtils.drawBottomRightLayerBorderTop(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);      
//   IsoTileUtils.drawBottomRightLayerBorderBottom(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);      
//   IsoTileUtils.drawBottomRightLayerBorderTopHigh(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);     
//   IsoTileUtils.drawBottomRightLayerBorderTopSide(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);  
//   IsoTileUtils.drawBottomRightLayerBorderBottomHigh(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);     
//   IsoTileUtils.drawBottomRightLayerBorderBottomSide(graphics2D, tileInfo, starPoint); 
//   
//   starPoint.setLocation(30, 300);        
//   IsoTileUtils.drawBottomLeftBorderTop(graphics2D, tileInfo, starPoint);    
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawBottomLeftBorderBottom(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);             
//   IsoTileUtils.drawBottomLeftBorderTopHigh(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);           
//   IsoTileUtils.drawBottomLeftBorderTopSide(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);     
//   IsoTileUtils.drawBottomLeftBorderBottomHigh(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);          
//   IsoTileUtils.drawBottomLeftBorderBottomSide(graphics2D, tileInfo, starPoint); 
//   
//   starPoint.setLocation(30, 340);     
//   IsoTileUtils.drawBottomLeftLayerBorderTop(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);          
//   IsoTileUtils.drawBottomLeftLayerBorderBottom(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);           
//   IsoTileUtils.drawBottomLeftLayerBorderTopHigh(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);          
//   IsoTileUtils.drawBottomLeftLayerBorderTopSide(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);       
//   IsoTileUtils.drawBottomLeftLayerBorderBottomHigh(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawBottomLeftLayerBorderBottomSide(graphics2D, tileInfo, starPoint); 
//
//   starPoint.setLocation(30, 380);         
//   IsoTileUtils.drawTopBorderTop(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopBorderBottom(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);       
//   IsoTileUtils.drawTopBorderTopRight(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);         
//   IsoTileUtils.drawTopBorderTopLeft(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopBorderBottomRight(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);         
//   IsoTileUtils.drawTopBorderBottomLeft(graphics2D, tileInfo, starPoint);   
//   
//   starPoint.setLocation(30, 420);      
//   IsoTileUtils.drawTopLayerBorderTop(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);      
//   IsoTileUtils.drawTopLayerBorderBottom(graphics2D, tileInfo, starPoint);    
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopLayerBorderTopRight(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);       
//   IsoTileUtils.drawTopLayerBorderTopLeft(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);       
//   IsoTileUtils.drawTopLayerBorderBottomRight(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);     
//   IsoTileUtils.drawTopLayerBorderBottomLeft(graphics2D, tileInfo, starPoint);  
//
//   starPoint.setLocation(30, 460);   
//   IsoTileUtils.drawTopRightBorderTop(graphics2D, tileInfo, starPoint);    
//   starPoint.translate(60, 0);   
//   IsoTileUtils.drawTopRightBorderBottom(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);          
//   IsoTileUtils.drawTopRightBorderTopHigh(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);            
//   IsoTileUtils.drawTopRightBorderTopSide(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopRightBorderBottomHigh(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopRightBorderBottomSide(graphics2D, tileInfo, starPoint); 
//   
//   starPoint.setLocation(30, 500); 
//   IsoTileUtils.drawTopRightLayerBorderTop(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);          
//   IsoTileUtils.drawTopRightLayerBorderBottom(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);         
//   IsoTileUtils.drawTopRightLayerBorderTopHigh(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);         
//   IsoTileUtils.drawTopRightLayerBorderTopSide(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);     
//   IsoTileUtils.drawTopRightLayerBorderBottomHigh(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopRightLayerBorderBottomSide(graphics2D, tileInfo, starPoint); 
//
//   starPoint.setLocation(30, 540); 
//   IsoTileUtils.drawTopLeftBorderTop(graphics2D, tileInfo, starPoint);    
//   starPoint.translate(60, 0);      
//   IsoTileUtils.drawTopLeftBorderBottom(graphics2D, tileInfo, starPoint);   
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopLeftBorderTopHigh(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);           
//   IsoTileUtils.drawTopLeftBorderTopSide(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopLeftBorderBottomHigh(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopLeftBorderBottomSide(graphics2D, tileInfo, starPoint); 
//   
//   starPoint.setLocation(30, 580); 
//   IsoTileUtils.drawTopLeftLayerBorderTop(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopLeftLayerBorderBottom(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopLeftLayerBorderTopHigh(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);        
//   IsoTileUtils.drawTopLeftLayerBorderTopSide(graphics2D, tileInfo, starPoint);  
//   starPoint.translate(60, 0);     
//   IsoTileUtils.drawTopLeftLayerBorderBottomHigh(graphics2D, tileInfo, starPoint);
//   starPoint.translate(60, 0);          
//   IsoTileUtils.drawTopLeftLayerBorderBottomSide(graphics2D, tileInfo, starPoint); 
//    
//   starPoint.setLocation(30, 620); 
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x01);
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x02);
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x03);   
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x04);   
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x05);
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x06);
//   starPoint.setLocation(30, 660); 
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x07);
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x08);
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x09); 
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x0A); 
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x0B); 
//   starPoint.translate(60, 0);
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x0C); 
//   starPoint.setLocation(30, 700); 
//   IsoTileUtils.drawPhysicalTile(graphics2D, tileInfo, starPoint, 0x0D);
//   
//   // Devolvemos la imagen generada.
//   graphics2D.dispose();
//   return mapBufferGraphics;   
//  }
// 
//  public final BufferedImage drawWireFrame() {
//   // Generamos un imagen para facilitarnos el pintado.
//   BufferedImage mapBufferGraphics =
//    new BufferedImage(
//     this.map.getMapWidth(), this.map.getMapHeight(), BufferedImage.TYPE_INT_ARGB);
//   // Recuperamos el contexto grafico.
//   Graphics2D graphics2D = mapBufferGraphics.createGraphics();
//   // Establecemos los atributos del contexto grafico.
//   graphics2D.setRenderingHint(
//    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//
//   // Dibujamos el fondo con el color establecido.
//   Color backGroundColor = this.map.getMapConstraints().getMapBackGroundColor();      
//   graphics2D.setPaint(backGroundColor);
//   graphics2D.fillRect(0, 0, this.map.getMapWidth(), this.map.getMapHeight());
//   
//   graphics2D.setPaint(Color.ORANGE);
//   graphics2D.drawRect(0, 0, this.map.getMapWidth(), this.map.getMapHeight());
//   
//   // Generamos un punto de referencia sobre el que empezar a dibujar.
//   Point starPoint = new Point(0, 0);
//   // Recuperamos la información de la tile que se va a dibujar.
//   IsoTile tileInfo = this.map.getMapTile();
//
//   // Dibujamos la capa lateral derecha.
//   starPoint.setLocation(
//    this.map.getMapXStartPoint(),
//    this.map.getMapYStartPoint());
//   for(int i = 0; i < this.map.getMapHigh();i++) {
//    Point middlePoint = starPoint.getLocation();
//    for(int j = 0; j < this.map.getMapLong();j++) {
//     if(this.map.isSelectedTile()) {
//      if(j == this.map.getSelectedTileLong()) {
//       IsoTileUtils.drawBottomRightSegment(
//        graphics2D, tileInfo, starPoint);
//      }
//     }
//     if((this.map.getMapHigh() == 1) && (this.map.getMapLong() == 1)) {
//      // Alto y largo es uno, dibujamos el tile con todos los bordes exteriores.
//      IsoTileUtils.drawBottomRightLayerBorderTop(
//       graphics2D, tileInfo, starPoint);
//     } else if((this.map.getMapHigh() == 1) && (this.map.getMapLong() > 1)) {
//      // El alto es uno, pero el largo es multiple, se comprueba el tipo de tile.
//      if(j == this.map.getMapLong() - 1) {
//       IsoTileUtils.drawBottomRightLayerBorderTop(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomRightLayerBorderTopHigh(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else if((this.map.getMapHigh() >= 1) && (this.map.getMapLong() == 1)) {
//      // El largo es uno, pero el alto es multiple, se comprueba el tipo de tile.
//      if(i == this.map.getMapHigh() - 1) {
//       IsoTileUtils.drawBottomRightLayerBorderTop(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomRightLayerBorderTopSide(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else if((this.map.getMapHigh() >= 1) && (this.map.getMapLong() >= 1)) {
//      // El alto y el largo es multiple, se comprueba el tipo de tile.
//      if(i == this.map.getMapHigh() - 1) {
//       if(j == this.map.getMapLong() - 1) {
//        IsoTileUtils.drawBottomRightLayerBorderTop(
//         graphics2D, tileInfo, starPoint);
//       } else {
//        IsoTileUtils.drawBottomRightLayerBorderTopHigh(
//         graphics2D, tileInfo, starPoint);
//       }
//      } else if(j == this.map.getMapLong() - 1) {
//       IsoTileUtils.drawBottomRightLayerBorderTopSide(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomRightLayer(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else {
//      IsoTileUtils.drawBottomRightLayer(
//       graphics2D, tileInfo, starPoint);
//     }
//     // Comprobamos si es la tile seleccionada.
//     if(this.map.isSelectedTile()) {
//      if((i == this.map.getSelectedTileHigh()) && (j == this.map.getSelectedTileLong())) {
//       IsoTileUtils.drawBottomRightLayerBorder(
//        graphics2D, tileInfo, starPoint);
//      } else if((i == (this.map.getSelectedTileHigh() + 1)) && (j == this.map.getSelectedTileLong())) {
//       IsoTileUtils.drawBottomRightBorderBottomHigh(
//        graphics2D, tileInfo, starPoint);
//      } 
//     }     
//     starPoint.translate(
//      ((int) (tileInfo.getTileWidth() / 2)),
//      ((int) (tileInfo.getTileWidth() / 4)));
//    }
//    starPoint = middlePoint.getLocation();
//    starPoint.translate(0, (tileInfo.getTileHigh() * -1));
//   }
//
//   // Dibujamos la capa lateral izquierda.
//   starPoint.setLocation(
//    this.map.getMapXStartPoint(),
//    this.map.getMapYStartPoint());
//   for(int i = 0;i < this.map.getMapHigh();i++) {
//    Point middlePoint = starPoint.getLocation();
//    for(int j = 0;j < this.map.getMapWide();j++) {
//     if(this.map.isSelectedTile()) {
//      if(j == this.map.getSelectedTileWide()) {
//       IsoTileUtils.drawBottomLeftSegment(
//        graphics2D, tileInfo, starPoint);
//      }
//     }
//     if((this.map.getMapHigh() == 1) && (this.map.getMapWide() == 1)) {
//      // Alto y ancho es uno, dibujamos el tile con todos los bordes exteriores.
//      IsoTileUtils.drawBottomLeftLayerBorderTop(
//       graphics2D, tileInfo, starPoint);
//     } else if((this.map.getMapHigh() == 1) && (this.map.getMapWide() > 1)) {
//      // El alto es uno, pero el ancho es multiple, se comprueba el tipo de tile.
//      if(j == this.map.getMapWide() - 1) {
//       IsoTileUtils.drawBottomLeftLayerBorderTop(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomLeftLayerBorderTopHigh(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else if((this.map.getMapHigh() >= 1) && (this.map.getMapWide() == 1)) {
//      // El ancho es uno, pero el alto es multiple, se comprueba el tipo de tile.
//      if(i == this.map.getMapHigh() - 1) {
//       IsoTileUtils.drawBottomLeftLayerBorderTop(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomLeftLayerBorderTopSide(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else if((this.map.getMapHigh() >= 1) && (this.map.getMapWide() >= 1)) {
//      // El alto y el ancho es multiple, se comprueba el tipo de tile.
//      if(i == this.map.getMapHigh() - 1) {
//       if(j == this.map.getMapWide() - 1) {
//        IsoTileUtils.drawBottomLeftLayerBorderTop(
//         graphics2D, tileInfo, starPoint);
//       } else {
//        IsoTileUtils.drawBottomLeftLayerBorderTopHigh(
//         graphics2D, tileInfo, starPoint);
//       }
//      } else if(j == this.map.getMapWide() - 1) {
//       IsoTileUtils.drawBottomLeftLayerBorderTopSide(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomLeftLayer(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else {
//      IsoTileUtils.drawBottomLeftLayer(
//       graphics2D, tileInfo, starPoint);
//     }
//     // Comprobamos si es la tile seleccionada.
//     if(this.map.isSelectedTile()) {
//      if((i == this.map.getSelectedTileHigh()) && (j == this.map.getSelectedTileWide())) {
//       IsoTileUtils.drawBottomLeftLayerBorder(
//        graphics2D, tileInfo, starPoint);
//      } else if((i == (this.map.getSelectedTileHigh() + 1)) && (j == this.map.getSelectedTileWide())) {
//       IsoTileUtils.drawBottomLeftBorderBottomHigh(
//        graphics2D, tileInfo, starPoint);
//      }
//     }
//     starPoint.translate(
//      ((int) ((tileInfo.getTileWidth() / 2) * -1)),
//      ((int) (tileInfo.getTileWidth() / 4)));
//    }
//    starPoint = middlePoint.getLocation();
//    starPoint.translate(0, (tileInfo.getTileHigh() * -1));
//   }
//
//   // Dibujamos la capa inferior.
//   starPoint.setLocation(
//    this.map.getMapXStartPoint(),
//    this.map.getMapYStartPoint());
//   for(int i = 0; i < this.map.getMapWide(); i++) {
//    Point middlePoint = starPoint.getLocation();
//    for(int j = 0; j < this.map.getMapLong(); j++) {
//     if(this.map.isSelectedTile()) {
//      if((j == this.map.getSelectedTileLong()) && (i <= this.map.getSelectedTileWide())) {
//       IsoTileUtils.drawBottomSegment(
//        graphics2D, tileInfo, starPoint);
//      }
//      if((i == this.map.getSelectedTileWide()) && (j <= this.map.getSelectedTileLong())) {
//       IsoTileUtils.drawBottomSegment(
//        graphics2D, tileInfo, starPoint);
//      }
//     }
//     if((this.map.getMapWide() == 1) && (this.map.getMapLong() == 1)) {
//      // Ancho y largo es uno, dibujamos el tile con todos los bordes exteriores.
//      IsoTileUtils.drawBottomLayerBorderBottom(
//       graphics2D, tileInfo, starPoint);
//     } else if((this.map.getMapWide() == 1) && (this.map.getMapLong() > 1)) {
//      // El ancho es uno, pero el largo es multiple, se comprueba el tipo de tile.
//      if(j == this.map.getMapLong() - 1) {
//       IsoTileUtils.drawBottomLayerBorderBottom(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomLayerBorderBottomLeft(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else if((this.map.getMapWide() >= 1) && (this.map.getMapLong() == 1)) {
//      // El largo es uno, pero el ancho es multiple, se comprueba el tipo de tile.
//      if(i == this.map.getMapWide() - 1) {
//       IsoTileUtils.drawBottomLayerBorderBottom(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomLayerBorderBottomRight(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else if((this.map.getMapWide() >= 1) && (this.map.getMapLong() >= 1)) {
//      // El ancho y el largo es multiple, se comprueba el tipo de tile.
//      if(i == this.map.getMapWide() - 1) {
//       if(j == this.map.getMapLong() - 1) {
//        IsoTileUtils.drawBottomLayerBorderBottom(
//         graphics2D, tileInfo, starPoint);
//       } else {
//        IsoTileUtils.drawBottomLayerBorderBottomLeft(
//         graphics2D, tileInfo, starPoint);
//       }
//      } else if(j == this.map.getMapLong() - 1) {
//       IsoTileUtils.drawBottomLayerBorderBottomRight(
//        graphics2D, tileInfo, starPoint);
//      } else {
//       IsoTileUtils.drawBottomLayer(
//        graphics2D, tileInfo, starPoint);
//      }
//     } else {
//      IsoTileUtils.drawBottomLayer(
//       graphics2D, tileInfo, starPoint);
//     }
//     // Comprobamos si es la tile seleccionada.
//     if(this.map.isSelectedTile()) {
//      if((i == this.map.getSelectedTileWide()) && (j == this.map.getSelectedTileLong())) {
//       IsoTileUtils.drawBottomLayerBorder(
//        graphics2D, tileInfo, starPoint);
//      } else if((i == 0) && (j == this.map.getSelectedTileLong()) && (this.map.getSelectedTileHigh() == 0)) {
//       if(this.map.getSelectedTileLong() == this.map.getMapLong() - 1) {
//        IsoTileUtils.drawBottomLayerBorderRight(
//         graphics2D, tileInfo, starPoint);
//       } else {
//        IsoTileUtils.drawBottomLayerBorderTopRight(
//         graphics2D, tileInfo, starPoint);
//       }
//      } else if((i == this.map.getSelectedTileWide()) && (j == 0) && (this.map.getSelectedTileHigh() == 0)) {
//       if(this.map.getSelectedTileWide() == this.map.getMapWide() - 1) {
//        IsoTileUtils.drawBottomLayerBorderLeft(
//         graphics2D, tileInfo, starPoint);
//       } else {
//        IsoTileUtils.drawBottomLayerBorderTopLeft(
//         graphics2D, tileInfo, starPoint);
//       }       
//      }
//     }
//     starPoint.translate(
//      ((int) (tileInfo.getTileWidth() / 2)),
//      ((int) (tileInfo.getTileWidth() / 4)));
//    }
//    starPoint = middlePoint.getLocation();
//    starPoint.translate(
//     ((int) ((tileInfo.getTileWidth() / 2) * -1)),
//     ((int) (tileInfo.getTileWidth() / 4)));
//   }
//
//   // Devolvemos la imagen generada.
//   graphics2D.dispose();
//   return mapBufferGraphics;
//  }
// 
//  public final BufferedImage drawSelectedTile() {
//   // Generamos un imagen para facilitarnos el pintado.
//   BufferedImage mapBufferGraphics =
//    new BufferedImage(
//     this.map.getMapWidth(), this.map.getMapHeight(), BufferedImage.TYPE_INT_ARGB);
//   // Recuperamos el contexto grafico.
//   Graphics2D graphics2D = mapBufferGraphics.createGraphics();
//   // Establecemos los atributos del contexto grafico.
//   graphics2D.setRenderingHint(
//    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//
//   // Dibujamos el fondo con el color establecido.
//   Color clearColor = new Color(255,255,255,1);
//   graphics2D.setPaint(clearColor);
//   graphics2D.fillRect(0, 0, this.map.getMapWidth(), this.map.getMapHeight());
//   
//   // Generamos un punto de referencia sobre el que empezar a dibujar.
//   Point starPoint = new Point(0, 0);
//   // Recuperamos la información de la tile que se va a dibujar.
//   IsoTile tileInfo = this.map.getMapTile();
//   
//   // Calculamso el punto donde dibujar
//   starPoint.setLocation(
//    this.map.getMapXStartPoint(),
//    this.map.getMapYStartPoint());
//   
//   starPoint.translate(
//    ((int) ((tileInfo.getTileWidth() / 2) * this.map.getSelectedTileLong())),
//    ((int) ((tileInfo.getTileWidth() / 4) * this.map.getSelectedTileLong())));
//   starPoint.translate(
//    ((int) (((tileInfo.getTileWidth() / 2) * -1) * this.map.getSelectedTileWide())),
//    ((int) ((tileInfo.getTileWidth() / 4) * this.map.getSelectedTileWide())));   
//   starPoint.translate(0, 
//    ((int) ((tileInfo.getTileHigh() * -1) * this.map.getSelectedTileHigh())));
//   
//   IsoTileUtils.drawBottomTileLayerBorder(
//     graphics2D, tileInfo, starPoint);
//   IsoTileUtils. drawTopTileLayerBorder(
//     graphics2D, tileInfo, starPoint);
//   
//   // Devolvemos la imagen generada.
//   graphics2D.dispose();
//   return mapBufferGraphics;   
//  }
//  
//  public final BufferedImage drawPhysicalMapLayer() {
//   // Generamos un imagen para facilitarnos el pintado.
//   BufferedImage mapBufferGraphics =
//    new BufferedImage(
//     this.map.getMapWidth(), this.map.getMapHeight(), BufferedImage.TYPE_INT_ARGB);
//   // Recuperamos el contexto grafico.
//   Graphics2D graphics2D = mapBufferGraphics.createGraphics();
//   // Establecemos los atributos del contexto grafico.
//   graphics2D.setRenderingHint(
//    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//
//   // Dibujamos el fondo con el color establecido.
//   Color clearColor = new Color(255,255,255,0);
//   graphics2D.setPaint(clearColor);
//   graphics2D.fillRect(0, 0, this.map.getMapWidth(), this.map.getMapHeight());
//
//   // Generamos un punto de referencia sobre el que empezar a dibujar.
//   Point starPoint = new Point(0, 0);
//   // Recuperamos la información de la tile que se va a dibujar.
//   IsoTile tileInfo = this.map.getMapTile();
//
//   starPoint.setLocation(
//    this.map.getMapXStartPoint(),
//    this.map.getMapYStartPoint());
//   int mapPhysicalPosition = 0;
//   for(int i = 0; i < this.map.getMapLong(); i++) {
//    Point middleLongPoint = starPoint.getLocation();
//    for(int j = 0; j < this.map.getMapWide(); j++) {
//     Point middleWidePoint = starPoint.getLocation();
//     for(int k = 0; k < this.map.getMapHigh(); k++) {
//      IsoTileUtils.drawPhysicalTile(
//       graphics2D, tileInfo, starPoint,
//       this.map.getPhysicalTileDefinitionElement(mapPhysicalPosition));
//      mapPhysicalPosition++;
//      starPoint.translate(0, (tileInfo.getTileHigh() * -1));      
//     }
//     starPoint = middleWidePoint.getLocation();
//     starPoint.translate(
//      ((int) ((tileInfo.getTileWidth() / 2) * -1)),
//      ((int) (tileInfo.getTileWidth() / 4)));
//    }
//    starPoint = middleLongPoint.getLocation();
//    starPoint.translate(
//     ((int) (tileInfo.getTileWidth() / 2)),
//     ((int) (tileInfo.getTileWidth() / 4)));  
//   }
//
//   // Devolvemos la imagen generada.
//   graphics2D.dispose();
//   return mapBufferGraphics;   
//  }
//
//  public final BufferedImage drawMouseControlLayer() {
//   // Generamos un imagen para facilitarnos el pintado.
//   BufferedImage mapBufferGraphics =
//    new BufferedImage(
//     this.map.getMapWidth(), this.map.getMapHeight(), BufferedImage.TYPE_INT_ARGB);
//   // Recuperamos el contexto grafico.
//   Graphics2D graphics2D = mapBufferGraphics.createGraphics();
//   // Establecemos los atributos del contexto grafico.
//   graphics2D.setRenderingHint(
//    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//
//   // Dibujamos el fondo con el color establecido.
//   Color clearColor = new Color(255,255,255,1);
//   graphics2D.setPaint(clearColor);
//   graphics2D.fillRect(0, 0, this.map.getMapWidth(), this.map.getMapHeight());
//
//   // Generamos un punto de referencia sobre el que empezar a dibujar.
//   Point starPoint = new Point(0, 0);
//   // Recuperamos la información de la tile que se va a dibujar.
//   IsoTile tileInfo = this.map.getMapTile();
//   
//   // Recuperamos el punto inicial sobre el que generar la regilla de control.
//   starPoint.setLocation(
//    this.map.getMapXStartPoint(),
//    this.map.getMapYStartPoint());
//   
//   // Desplazamos el punto al inicio de la tile.
//   starPoint.translate(
//    ((int) (((tileInfo.getTileWidth() / 2) -1) * -1)), 0);
//   
//   // Calculamos los avances existentes en cada unos de los lados del punto central.    
//   int pointPositionLeftAdvance = 0;
//   int pointPositionRightAdvance = 0;   
//   while(starPoint.getX() > 0) {
//    starPoint.translate(((int)(tileInfo.getTileWidth() * -1)), 0);
//    pointPositionLeftAdvance++;
//   }   
//   pointPositionRightAdvance = pointPositionLeftAdvance;
//   pointPositionLeftAdvance = (pointPositionLeftAdvance * -1);
//   
//   // Calculamos los avances existentes hacia abajo del punto central.    
//   int cellRowsCount;
//   if(this.map.getMapWide() < this.map.getMapLong()) {
//    cellRowsCount = this.map.getMapLong();
//   } else {
//    cellRowsCount = this.map.getMapWide();
//   }
//    
//   graphics2D.setFont(new Font("Arial", Font.PLAIN, 10));
//   
//   // Dibujamos la regilla
//   for(int i = 0; i < cellRowsCount; i++) {
//    Point middlePoint = starPoint.getLocation();
//    int pointPositionLeftAdvance_ = pointPositionLeftAdvance;
//    int pointPositionRightAdvance_ = pointPositionRightAdvance;    
//    for(int j = 0; j <= cellRowsCount; j++) {
//     graphics2D.setPaint(new Color(255, 82, 82));     
//     graphics2D.drawRect((int)starPoint.getX(), (int)starPoint.getY(), tileInfo.getTileWidth(), ((int) (tileInfo.getTileWidth() / 2)));
//     graphics2D.setPaint(Color.BLACK);  
//     graphics2D.drawString(
//      "(" + pointPositionLeftAdvance + "," + pointPositionRightAdvance + ")",
//      (int) starPoint.getX() + 3,
//      (int) starPoint.getY() + 10);
//     pointPositionRightAdvance = (pointPositionRightAdvance - 1);
//     pointPositionLeftAdvance = (pointPositionLeftAdvance + 1);
//     starPoint.translate(tileInfo.getTileWidth(), 0);     
//    }
//    starPoint = middlePoint.getLocation();
//    starPoint.translate(0, ((int) (tileInfo.getTileWidth() / 2)));
//    pointPositionRightAdvance = (pointPositionRightAdvance_ + 1);
//    pointPositionLeftAdvance = (pointPositionLeftAdvance_ + 1);
//   }
//   
//   // Devolvemos la imagen generada.
//   graphics2D.dispose();
//   return mapBufferGraphics;   
//  }
////
//  public Point getActionLayerPosition() {
//   return actionLayerPosition;
//  }
//
//  public void setActionLayerPosition(Point actionLayerPosition) {
//   this.actionLayerPosition = actionLayerPosition;
//  }
//  
//  
//  
//  
//  
//  
//  
//  private final BufferedImage drawLayerMouseActionCanvas(
//          final HashMap<Point, Rectangle> canvasActionMapperParam) {   
//   // Generamos un imagen para facilitarnos el pintado.
//   BufferedImage canvasBufferGraphics =
//    new BufferedImage(
//     this.getWidth() , this.getHeight(), BufferedImage.TYPE_INT_ARGB);
//   // Recuperamos el contexto grafico.
//   Graphics2D graphics2D = canvasBufferGraphics.createGraphics();
//   // Establecemos los atributos del contexto grafico.
//   graphics2D.setRenderingHint(
//    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//   
//   // Dibujamos el de transparencia total.
//   Color clearColor = new Color(255, 255, 255, 0);
//   graphics2D.setPaint(clearColor);
//   graphics2D.fillRect(0, 0, canvasBufferGraphics.getWidth(), canvasBufferGraphics.getHeight());
//
//   Iterator<Entry<Point, Rectangle>> entriesActionMapper =
//   canvasActionMapperParam.entrySet().iterator();
//   while (entriesActionMapper.hasNext()) {
//   Map.Entry<Point, Rectangle> entry = 
//    (Map.Entry<Point, Rectangle>) entriesActionMapper.next();
//   Rectangle actionArea = (Rectangle) entry.getValue();
//
//   graphics2D.setPaint(new Color(94, 94, 196)); 
//   graphics2D.fillRect(
//    (int) actionArea.getX(),
//    (int) actionArea.getY(),
//    (int) actionArea.getWidth(),
//    (int) actionArea.getHeight());
//   graphics2D.setPaint(new Color(5, 5, 196));  
//   graphics2D.drawRect(
//    (int) actionArea.getX(),
//    (int) actionArea.getY(),
//    (int) actionArea.getWidth(),
//    (int) actionArea.getHeight());
//   }       
//   // Devolvemos la imagen generada.
//   graphics2D.dispose();
//   return canvasBufferGraphics;   
//  } 
//
//  private final BufferedImage drawLayerMouseActionMap(
//          final IsoMap mapParam,
//          final HashMap<Point, Rectangle> mapActionMapperParam) {
//   IsoTile tileInfo = mapParam.getMapTile();   
//   // Generamos un imagen para facilitarnos el pintado.
//   BufferedImage mapBufferGraphics =
//    new BufferedImage(
//     mapParam.getMapWidth() + (tileInfo.getTileWidth() * 2), mapParam.getMapHeight() + (tileInfo.getTileHeight() * 2), BufferedImage.TYPE_INT_ARGB);
//   // Recuperamos el contexto grafico.
//   Graphics2D graphics2D = mapBufferGraphics.createGraphics();
//   // Establecemos los atributos del contexto grafico.
//   graphics2D.setRenderingHint(
//    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//   // Establecemos el tipo de fuente por defecto.
//   graphics2D.setFont(new Font("helvetica", Font.BOLD, 9));
//   
//   // Dibujamos el de transparencia total.
//   Color clearColor = new Color(255, 255, 255, 0);
//   graphics2D.setPaint(clearColor);
//   graphics2D.fillRect(0, 0, mapBufferGraphics.getWidth(), mapBufferGraphics.getHeight());
//   
//   Iterator<Entry<Point, Rectangle>> entriesActionMapper =
//    mapActionMapperParam.entrySet().iterator();
//   while (entriesActionMapper.hasNext()) {
//    Map.Entry<Point, Rectangle> entry = 
//     (Map.Entry<Point, Rectangle>) entriesActionMapper.next();
//    Point actionPoint = (Point) entry.getKey();
//    Rectangle actionArea = (Rectangle) entry.getValue();
//    
//    graphics2D.setPaint(new Color(196, 94, 94));
//    graphics2D.drawRect(
//     (int) actionArea.getX() + (int) tileInfo.getTileWidth(),
//     (int) actionArea.getY() + (int) tileInfo.getTileHeight(),
//     (int) actionArea.getWidth(),
//     (int) actionArea.getHeight());
//    graphics2D.fillRect(
//     (int) actionArea.getX() + (int) tileInfo.getTileWidth(),
//     (int) actionArea.getY() + (int) tileInfo.getTileHeight(),
//     20, 11);
//    
//    String pointString = (int) actionPoint.getX() + "," + (int) actionPoint.getY();
//    int widthString = graphics2D.getFontMetrics().stringWidth(pointString);
//    
//    graphics2D.setPaint(new Color(232, 232, 232));
//    graphics2D.drawString(
//     pointString,
//     (int) actionArea.getX() + (int) tileInfo.getTileWidth() + (10 - (widthString / 2)),
//     (int) actionArea.getY() + (int) tileInfo.getTileHeight() + 9);   
//   }
//   // Devolvemos la imagen generada.
//   graphics2D.dispose();
//   return mapBufferGraphics;   
//  }
//
//  private final BufferedImage drawLayerMouseActionMapRelation(
//          final IsoMap mapParam,
//          final HashMap<Point, Rectangle> mapActionMapperParam) {
//   IsoTile tileInfo = mapParam.getMapTile();   
//   // Generamos un imagen para facilitarnos el pintado.
//   BufferedImage mapBufferGraphics =
//    new BufferedImage(
//     mapParam.getMapWidth() + (tileInfo.getTileWidth() * 2), mapParam.getMapHeight() + (tileInfo.getTileHeight() * 2), BufferedImage.TYPE_INT_ARGB);
//   // Recuperamos el contexto grafico.
//   Graphics2D graphics2D = mapBufferGraphics.createGraphics();
//   // Establecemos los atributos del contexto grafico.
//   graphics2D.setRenderingHint(
//    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
//   // Establecemos el tipo de fuente por defecto.
//   graphics2D.setFont(new Font("helvetica", Font.PLAIN, 8));
//   
//   // Dibujamos el de transparencia total.
//   Color clearColor = new Color(255, 255, 255, 0);
//   graphics2D.setPaint(clearColor);
//   graphics2D.fillRect(0, 0, mapBufferGraphics.getWidth(), mapBufferGraphics.getHeight());
//   
//   Iterator<Entry<Point, Rectangle>> entriesActionMapper =
//    mapActionMapperParam.entrySet().iterator();
//   while (entriesActionMapper.hasNext()) {
//    Map.Entry<Point, Rectangle> entry = 
//     (Map.Entry<Point, Rectangle>) entriesActionMapper.next();
//    Point actionPoint = (Point) entry.getKey();
//    Rectangle actionArea = (Rectangle) entry.getValue();
//    
//    graphics2D.setPaint(new Color(196, 94, 94, 100));
//    graphics2D.fillRect(
//     (int) actionArea.getX() + (int) tileInfo.getTileWidth(),
//     (int) actionArea.getY() + (int) tileInfo.getTileHeight(),
//     18, 10);    
//    graphics2D.fillRect(
//     ((int) (actionArea.getX() + tileInfo.getTileWidth() + actionArea.getWidth())) - 18,
//     (int) actionArea.getY() + (int) tileInfo.getTileHeight(),
//     18, 10);
//    graphics2D.fillRect(
//     (int) actionArea.getX() + (int) tileInfo.getTileWidth(),
//     ((int) (actionArea.getY() + tileInfo.getTileHeight() + actionArea.getHeight())) - 10,
//     18, 10);    
//    graphics2D.fillRect(
//     ((int) (actionArea.getX() + tileInfo.getTileWidth() + actionArea.getWidth())) - 18,
//     ((int) (actionArea.getY() + tileInfo.getTileHeight() + actionArea.getHeight())) - 10,
//     18, 10);
//    graphics2D.fillRect(
//     ((int) ((actionArea.getX() + tileInfo.getTileWidth() + actionArea.getWidth()) - (actionArea.getWidth() / 2) - 9)),
//     ((int) ((actionArea.getY() + tileInfo.getTileHeight() + actionArea.getHeight()) - (actionArea.getHeight() / 2) - 5)),
//     18, 10);      
//    
//    graphics2D.setPaint(new Color(196, 94, 94));
//    graphics2D.drawRect(
//     (int) actionArea.getX() + (int) tileInfo.getTileWidth(),
//     (int) actionArea.getY() + (int) tileInfo.getTileHeight(),
//     (int) actionArea.getWidth(),
//     (int) actionArea.getHeight());
//    
//    String pointString = "";
//    int widthString = 0;    
//    graphics2D.setPaint(new Color(232, 232, 232));
//    
//    pointString = ((int) actionPoint.getX() - 1) + "," + (int) actionPoint.getY();
//    widthString = graphics2D.getFontMetrics().stringWidth(pointString);    
//    graphics2D.drawString(
//     pointString,
//     (int) actionArea.getX() + (int) tileInfo.getTileWidth() + (9 - (widthString / 2)),
//     (int) actionArea.getY() + (int) tileInfo.getTileHeight() + 8);
//    
//    pointString = ((int) actionPoint.getX()) + "," + ((int) actionPoint.getY() - 1);
//    widthString = graphics2D.getFontMetrics().stringWidth(pointString); 
//    graphics2D.drawString(
//     pointString,
//     ((int) (((actionArea.getX() + tileInfo.getTileWidth()) + actionArea.getWidth()) - 18)) + (9 - (widthString / 2)),
//     ((int) (actionArea.getY() + tileInfo.getTileHeight())) + 8);
//    
//    pointString = ((int) actionPoint.getX()) + "," + ((int) actionPoint.getY() + 1);
//    widthString = graphics2D.getFontMetrics().stringWidth(pointString); 
//    graphics2D.drawString(
//     pointString,
//     (int) actionArea.getX() + (int) tileInfo.getTileWidth() + (9 - (widthString / 2)),
//     ((int) (((actionArea.getY() + tileInfo.getTileHeight()) + actionArea.getHeight())- 10)) + 8  
//     );
//
//    pointString = ((int) actionPoint.getX() + 1) + "," + ((int) actionPoint.getY());
//    widthString = graphics2D.getFontMetrics().stringWidth(pointString); 
//    graphics2D.drawString(
//     pointString,
//     ((int) (((actionArea.getX() + tileInfo.getTileWidth()) + actionArea.getWidth()) - 18)) + (9 - (widthString / 2)),
//     ((int) (((actionArea.getY() + tileInfo.getTileHeight()) + actionArea.getHeight())- 10)) + 8    
//     );
//    
//    pointString = ((int) actionPoint.getX()) + "," + (int) actionPoint.getY();
//    widthString = graphics2D.getFontMetrics().stringWidth(pointString);    
//    graphics2D.drawString(
//     pointString,
//     ((int) ((actionArea.getX() + tileInfo.getTileWidth() + actionArea.getWidth()) - (actionArea.getWidth() / 2) - 9)) + (9 - (widthString / 2)),
//     ((int) ((actionArea.getY() + tileInfo.getTileHeight() + actionArea.getHeight()) - (actionArea.getHeight() / 2) - 5)) + 8);    
//   }
//   // Devolvemos la imagen generada.
//   graphics2D.dispose();
//   return mapBufferGraphics;   
//  }
//
//  
//  
//  public HashMap<Point, Rectangle> getCanvasActionMapper() {
//   return canvasActionMapper;
//  }
//
//  public void setCanvasActionMapper(HashMap<Point, Rectangle> canvasActionMapper) {
//   this.canvasActionMapper = canvasActionMapper;
//  }
//
//  
//  public boolean isMapMovementStatus() {
//   return mapMovementStatus;
//  }
//
//  public void setMapMovementStatus(boolean mapMovementStatus) {
//   this.mapMovementStatus = mapMovementStatus;
//  }
//
//  public int getCanvasXDisplacement() {
//   return canvasXDisplacement;
//  }
//
//  public void setCanvasXDisplacement(int canvasXDisplacement) {
//   this.canvasXDisplacement = canvasXDisplacement;
//  }
//
//  public int getCanvasXIncrement() {
//   return canvasXIncrement;
//  }
//
//  public void setCanvasXIncrement(int canvasXIncrement) {
//   this.canvasXIncrement = canvasXIncrement;
//  }
//
//  public int getCanvasYDisplacement() {
//   return canvasYDisplacement;
//  }
//
//  public void setCanvasYDisplacement(int canvasYDisplacement) {
//   this.canvasYDisplacement = canvasYDisplacement;
//  }
//
//  public int getCanvasYIncrement() {
//   return canvasYIncrement;
//  }
//
//  public void setCanvasYIncrement(int canvasYIncrement) {
//   this.canvasYIncrement = canvasYIncrement;
//  }
//  
//  public Point getCanvasMouseLastPosition() {
//   return canvasMouseLastPosition;
//  }
//
//  public void setCanvasMouseLastPosition(Point canvasMouseLastPosition) {
//   this.canvasMouseLastPosition = canvasMouseLastPosition;
//  }
  
  @Override
  public final void actionPerformed(
         final ActionEvent actionEventParam) {
//   // TODO Auto-generated method stub
//   //System.out.println("[" + this.mapMovementStatus + "][X: " + this.canvasXDisplacement + " - "+ this.canvasXIncrement + "][Y: " + this.canvasYDisplacement + " - "+ this.canvasYIncrement + "]");
//   this.updateCanvasPosition();
//   //System.out.println("(" + MouseInfo.getPointerInfo().getLocation().x +  ", " + MouseInfo.getPointerInfo().getLocation().y + ")");
//   this.repaint();  
  }
  
//  
//  public void updateCanvasPosition() {
//   if(this.mapMovementStatus) {
//    this.canvasXDisplacement = this.canvasXDisplacement + this.canvasXIncrement;
//    this.canvasYDisplacement = this.canvasYDisplacement + this.canvasYIncrement;
//    //System.out.println("UPDATE:" + this.getCanvasMouseLastPosition());
//    this.mouseMovedMapActionMapper(this.getCanvasMouseLastPosition());
//   }
//  }
//  
//  
//  public void mouseMovedCanvasActionMapper(
//            final Point mousePointParam) {
//   int xMousePosition = (int) mousePointParam.getX();
//   int yMousePosition = (int) mousePointParam.getY();
//   Point mousePoint = new Point(xMousePosition, yMousePosition);   
//   // Comprobamos si esta en una zona de acción el 'Canvas'.
//   Iterator<Entry<Point, Rectangle>> entriesCanvasActionMapper =
//    this.getCanvasActionMapper().entrySet().iterator();
//   boolean disbledAction = true;
//   while (entriesCanvasActionMapper.hasNext()) {
//    Map.Entry<Point, Rectangle> entry = 
//     (Map.Entry<Point, Rectangle>) entriesCanvasActionMapper.next();
//    Point actionPoint = (Point) entry.getKey();
//    Rectangle actionArea = (Rectangle) entry.getValue();
//    //System.out.println(actionPoint + " - " + positionActionArea);
//    if(pointIntersects(mousePoint, actionArea)) {
//     disbledAction = false;
//     this.setMapMovementStatus(true);
//     if(actionPoint.getX() == 0) {
//      this.setCanvasXIncrement(2);
//     }
//     if(actionPoint.getX() == 1) {
//      this.setCanvasXIncrement(0);
//     }
//     if(actionPoint.getX() == 2) {
//      this.setCanvasXIncrement(-2);
//     }
//     if(actionPoint.getY() == 0) {
//      this.setCanvasYIncrement(2);
//     }
//     if(actionPoint.getY() == 1) {
//      this.setCanvasYIncrement(0);
//     }
//     if(actionPoint.getY() == 2) {
//      this.setCanvasYIncrement(-2);
//     }     
//    }
//   }
//   if(disbledAction) {
//    this.setMapMovementStatus(false);
//   }   
//  }
//  
//  public void mouseMovedMapActionMapper(
//            final Point mousePointParam) {
//   int xMousePosition = (int) mousePointParam.getX();
//   int yMousePosition = (int) mousePointParam.getY();
//   // Desactivamos el 'flag' de 'Tile' seleccionada. 
//   this.getMap().setSelectedTile(false);
//   
//   // Establecemos el area de acción.
//   Rectangle workArea = new Rectangle(
//    (int) this.getActionLayerPosition().getX(),
//    (int) this.getActionLayerPosition().getY(),
//    this.getMap().getMapWidth(),
//    this.getMap().getMapHeight());
//   
//   Point mousePoint = new Point(xMousePosition, yMousePosition); 
//   
//   // Comprobamos si la posicion esta dentro del cuadro de dibujo.
//   if(pointIntersects(mousePoint, workArea)) {    
//    // Comprobamos si la posicion esta dentro del cuadro de accion.
//    Iterator<Entry<Point, Rectangle>> entriesActionMapper =
//      this.getMap().getMapActionMapper().entrySet().iterator();
//     while (entriesActionMapper.hasNext()) {
//      Map.Entry<Point, Rectangle> entry = 
//       (Map.Entry<Point, Rectangle>) entriesActionMapper.next();
//      Point actionPoint = (Point) entry.getKey();
//      Rectangle actionArea = (Rectangle) entry.getValue();
//      /*
//       * Clonamos el area para para desplazarla segun la posicion dentro del
//       * frame, y no afectar al mapa de acciones.
//       */
//      Rectangle positionActionArea = (Rectangle) actionArea.clone();
//      positionActionArea.translate(
//       ((int) this.getActionLayerPosition().getX()),
//       ((int) this.getActionLayerPosition().getY())
//      ); 
//      //System.out.println(actionPoint + " - " + positionActionArea);
//      if(pointIntersects(mousePoint, positionActionArea)) {
//       //System.out.println("X: " + mousePoint.getX() + " Y: " + mousePoint.getY());        
//       //System.out.println("X: " + positionActionArea.getX() + " Y: " + positionActionArea.getY());
//       Point actionLocalPosition = new Point(
//        ((int)(mousePoint.getX() - positionActionArea.getX())),
//        ((int)(mousePoint.getY() - positionActionArea.getY()))        
//       );
//       //System.out.println("[X: " + actionLocalPosition.getX() + " Y: " + actionLocalPosition.getY() + "]");
//       int actionWidth = this.getMap().getMapTileMask().getMaskWidth();
//       int actionPosition = ((actionWidth * ((int) actionLocalPosition.getY())) + ((int) actionLocalPosition.getX()));
//       int actionValue =  this.getMap().getMapTileMask().getTileMaskElement(actionPosition);       
//       int actionMapLong = ((int) actionPoint.getX());
//       int actionMapWide = ((int) actionPoint.getY());       
//       //System.out.println("[" + actionValue + "][" + actionPosition + "][" + actionMapLong + ", " + actionMapWide + "]");       
//       if(actionValue == 1) {
//        actionMapLong--;
//       } else if(actionValue == 2) {
//        actionMapWide--;
//       } else if(actionValue == 3) {
//        actionMapWide++;
//       } else if(actionValue == 4) {
//        actionMapLong++;
//       }
//       //System.out.println(actionMapLong + " - " + actionMapWide);
//       if(((this.getMap().getMapLong() - 1) >= actionMapLong) && (actionMapLong >= 0)) {
//        if(((this.getMap().getMapWide() - 1) >= actionMapWide) && (actionMapWide >= 0)) {
//         //System.out.println(actionMapLong + " - " + actionMapWide);
//         this.getMap().setSelectedTileLong(actionMapLong);
//         this.getMap().setSelectedTileWide(actionMapWide);
//         this.getMap().setSelectedTile(true);
//        } else {
//         this.getMap().setSelectedTile(false);
//        }
//       } else {
//        this.getMap().setSelectedTile(false);
//       }
//       //System.out.println("------------------");
//       break;       
//      }
//     }    
//   }
//   this.repaint(); 
//  }
//  
//  
  
//  public static final boolean pointIntersects(
//    final Point pointParam,
//    final Rectangle rectangleParam){
//boolean intersects = false;
//
////Left point
////System.out.println("Condicion 1: " + pointParam.getX() + " >= " + rectangleParam.getMinX() + " - " + (pointParam.getX() >= rectangleParam.getMinX()));
////System.out.println("Condicion 2: " + pointParam.getX() + " <= " + rectangleParam.getMaxX() + " - " + (pointParam.getX() <= rectangleParam.getMaxX()));
////System.out.println("Condicion 3: " + pointParam.getY() + " >= " + rectangleParam.getMinY() + " - " + (pointParam.getY() >= rectangleParam.getMinY()));
////System.out.println("Condicion 4: " + pointParam.getY() + " <= " + rectangleParam.getMaxY() + " - " + (pointParam.getY() <= rectangleParam.getMaxY()));   
//if ((pointParam.getX() >= rectangleParam.getMinX()) &&
//  (pointParam.getX() <= rectangleParam.getMaxX()) &&
//  (pointParam.getY() >= rectangleParam.getMinY()) &&
//  (pointParam.getY() <= rectangleParam.getMaxY())) {
//intersects = true;
//}
//return intersects;
//}

  
 }