package drawtile;

 import
  java.awt.Color;
 import
  java.awt.Dimension;
 import
  java.awt.Graphics;
 import
  java.awt.Graphics2D;
 import
  java.awt.Panel;
 import
  java.awt.Point;
 import
  java.awt.Toolkit;

 /*
  * Clase principal del ejemplo se representa como un tablero o lienzo donde
  * se aplicaran las técnicas de dibujado y este se mostrara en la pantalla, a
  * su vez implementara los métodos que nos permitirá interactuar con el mismo.
  */
 public class Board extends Panel {
  private static final long serialVersionUID = 1L;
  private final int PANEL_WIDTH = 800; /* Ancho que tendrá el lienzo. */
  private final int PANEL_HEIGHT = 600; /* Alto que tendrá el lienzo. */
  
  public Board() {
   /*
    * Inicializamos el lienzo con el tamaño que hemos definido en las
    * constantes.
    */
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT)); 
  }
  
  @Override
  public void paint(final Graphics graphicsParam) {
   /*
    * Sobrescribimos el método encargado de repintar el componente, y le
    * asignamos el contexto gráfico del componente como contexto propio al
    * lienzo.
    */
   super.paint(graphicsParam);
   /*
    * Invocamos al método general de repintado, contiene la ejecución y el
    * código objeto del ejemplo.
    */ 
   this.drawComponent(graphicsParam);
  }

  private void drawComponent(final Graphics graphics) {
   /* 
    * Establecemos el contexto gráfico, de gráficos avanzados o 'Graphics2D',
    * al heredar de la clase 'Graphics' todos sus métodos siguen siendo
    * validos, y a su vez nos permite obtener las mejoras correspondientes del
    * objeto 'Graphics2D'.
    */
   Graphics2D graphics2D = (Graphics2D) graphics;

   graphics2D.setColor(Color.BLACK);
   graphics2D.fillRect(0,0, this.getWidth(), this.getHeight());
   TileAux tile = new TileAux(new Point(20, 15));
   tile.drawBackgroundTile(graphics2D);
   tile.drawBorderTile(graphics2D);

   TileDimensionAux tileD = new TileDimensionAux(28, 21, 8, 6.5F);
   tileD.setStartDrawPoint(new Point(60, 15));
   tileD.drawBackgroundTile(graphics2D);
   tileD.drawBorderTile(graphics2D);
   
   TileDimensionAux tileD2 = new TileDimensionAux(48, 38, 15, 11.5F);
   tileD2.setStartDrawPoint(new Point(100, 15));
   tileD2.drawBackgroundTile(graphics2D);
   tileD2.drawBorderTile(graphics2D);
   
   
   /*TileDTO tile = new TileDTO(28, 21, 8, 6.5F);
   tile.setTileWireFrameColor(new Color(212, 229, 246));
   tile.setTileWireFrameStroke(new BasicStroke(1.0F));
   tile.setTileWireFrameBorderColor(new Color(210, 44, 44));
   tile.setTileWireFrameBorderStroke(new BasicStroke(1.0F));    
   
   TileUtils.drawLeftTopLayer(graphics2D, tile, 50, 50); 
   TileUtils.drawBottomLayer(graphics2D, tile, 75, 75);*/
   
   //TileDTO tile = new TileDTO(new Point(10, 10));
   //tile.drawTile(graphics2D);

   
   
   

   
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 