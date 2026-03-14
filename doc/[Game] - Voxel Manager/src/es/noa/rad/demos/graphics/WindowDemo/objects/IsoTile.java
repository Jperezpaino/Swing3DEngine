package es.noa.rad.demos.graphics.WindowDemo.objects;

 import
  java.awt.Point;
 import
  java.io.Serializable;
 import
  java.util.ArrayList;
 import
  java.util.List;

 public final class IsoTile
        implements Serializable {

  /**
    * Variable privada e inmutable de tipo {@code long} que contiene el número
    * de serie de la clase.
    */
  private static final long serialVersionUID = 1L;

  /**
    * Variable de transición de tipo {@code boolean} para establecer si el
    * 'HashCode' de la clase a sido calculado.
    */
  private transient boolean hashCodeCalc;
  
  /**
    * Variable privada de tipo {@code int} que contiene el ancho total de la
    * tile que va a conforma el mapa.
    */
  private int tileWidth;
    
  /**
    * Variable privada de tipo {@code int} que contiene el alto total de la
    * tile que va a conforma el mapa.
    */
  private int tileHeight;

  /**
    * Variable privada de tipo {@code int} que contiene el alto del lateral de
    * la tile que va a conforma el mapa.
    */  
  private int tileHigh;
  
  /**
    * Variable privada de tipo {@code int} que contiene el alto de la base de
    * la tile que va a conforma el mapa.
    */
  private float tileBaseHigh;
  
  
  
  /** Lista que contendra las coordenadas de los puntos de control para dibujar la tile que conforma el mapa. */
  private List<Point> tilePoints;
  /** Objeto de control que define propiedades estaticas para el manejo de la tile que conforma el mapa. */
  private IsoTileConstraints tileConstraints;

  public IsoTile(
         final int tileWidthParam,
         final int tileHeightParam,
         final int tileHighParam,
         final float tileBaseHighParam) {
   super();
   this.tileWidth = tileWidthParam;
   this.tileHeight = tileHeightParam;
   this.tileHigh = tileHighParam;
   this.tileBaseHigh = tileBaseHighParam;
   // Recalculamos los puntos de control.
   this.calculeTilePoints();   
   this.tileConstraints = new IsoTileConstraints();
  }

  public IsoTile() {
   this(0, 0, 0, 0);
  }

  public final int getTileWidth() {
   return this.tileWidth;
  }

  public final void setTileWidth(
         final int tileWidthParam) {
   this.tileWidth = tileWidthParam;
  }

  public final int getTileHeight() {
   return this.tileHeight;
  }

  public final void setTileHeight(
         final int tileHeightParam) {
   this.tileHeight = tileHeightParam;
  }

  public final int getTileHigh() {
   return this.tileHigh;
  }

  public final void setTileHigh(
         final int tileHighParam) {
   this.tileHigh = tileHighParam;
  }
  
  public final float getTileBaseHigh() {
   return this.tileBaseHigh;
  }

  public final void setTileBaseHigh(
         final float tileBaseHighParam) {
   this.tileBaseHigh = tileBaseHighParam;
  }

  public final void addTilePointsElement(
         final Point tilePointsElementParam) {
   this.tilePoints.add(tilePointsElementParam);
  }

  public final void addPositionTilePointsElement(
         final Point tilePointsElementParam,
         final int positionParam) {
   int listLength = this.getTilePointsSize();
   if(positionParam < listLength) {
    this.tilePoints.add(positionParam, tilePointsElementParam);
   } else {
    this.tilePoints.add(tilePointsElementParam);
   }
  }

  public final void deleteTilePointsElement(
         final int positionParam) {
   int listLength = this.getTilePointsSize();
   if(positionParam < listLength) {
    this.tilePoints.remove(positionParam);
   }
  }

  public final List<Point> getTilePoints() {
   return this.tilePoints;
  }

  public final Point getTilePointsElement(
         final int positionParam) {
   if (positionParam < this.getTilePointsSize()) {
    return this.tilePoints.get(positionParam);
   }
   return this.tilePoints.get(this.getTilePointsSize());
  }

  public final int getTilePointsSize() {
   return this.tilePoints.size();
  }

  public final void setTilePoints(
         final List<Point> tilePointsParam) {
   this.tilePoints = tilePointsParam;
  }

  public final void setTilePointsElement(
         final Point tilePointsElementParam,
         final int positionParam) {
   if (positionParam < this.getTilePointsSize()) {
    this.tilePoints.add(
     positionParam, tilePointsElementParam);
   } else {
    this.tilePoints.add(
     tilePointsElementParam);
   }
  }

  public final boolean isTilePointsEmpty() {
   return this.tilePoints.isEmpty();
  }

  public final IsoTileConstraints getTileConstraints() {
   return this.tileConstraints;
  }

  public final void setTileConstraints(
         final IsoTileConstraints tileConstraintsParam) {
   this.tileConstraints = tileConstraintsParam;
  }
  
  private void calculeTilePoints() {
   this.tilePoints = new ArrayList<>();
   Point controlPoint;

   // Cuadro inferior, punto superior inicial. (0)
   controlPoint = new Point(0, 0);
   this.tilePoints.add(controlPoint);

   // Cuadro inferior, punto superior final. (1)
   controlPoint = new Point(1, 0);
   this.tilePoints.add(controlPoint);

   // Cuadro inferior, punto medio inicial. (2)
   controlPoint = new Point(((int) (((this.tileWidth / 2) - 1) * -1)), ((int) (((tileBaseHigh * 2) - 1) / 2)));
   this.tilePoints.add(controlPoint);

   // Cuadro inferior, punto medio final. (3)
   controlPoint = new Point(((int) (this.tileWidth / 2)), ((int) (((tileBaseHigh * 2) - 1) / 2)));
   this.tilePoints.add(controlPoint);

   // Cuadro inferior, punto inferior inicial. (4)
   controlPoint = new Point(0, ((int) ((tileBaseHigh * 2) - 1)));
   this.tilePoints.add(controlPoint);

   // Cuadro inferior, punto inferior final. (5)
   controlPoint = new Point(1, ((int) ((tileBaseHigh * 2) - 1)));
   this.tilePoints.add(controlPoint);

   // Cuadro superior, punto superior inicial. (6)
   controlPoint = new Point(0, ((int) (this.tileHigh * -1)));
   this.tilePoints.add(controlPoint);

   // Cuadro superior, punto superior final. (7)
   controlPoint = new Point(1, ((int) (this.tileHigh * -1)));
   this.tilePoints.add(controlPoint);

   // Cuadro superior, punto medio inicial. (8)
   controlPoint = new Point(((int) (((this.tileWidth / 2) - 1) * -1)), ((int) ((((tileBaseHigh * 2) - 1) / 2) - this.tileHigh)));
   this.tilePoints.add(controlPoint);

   // Cuadro superior, punto medio final. (9)
   controlPoint = new Point((this.tileWidth / 2), ((int) ((((tileBaseHigh * 2) - 1) / 2) - this.tileHigh)));
   this.tilePoints.add(controlPoint);

   // Cuadro superior, punto inferior inicial. (10)
   controlPoint = new Point(0, ((int) (((tileBaseHigh * 2) - 1) - this.tileHigh)));
   this.tilePoints.add(controlPoint);

   // Cuadro superior, punto inferior final. (11)
   controlPoint = new Point(1, ((int) (((tileBaseHigh * 2) - 1) - this.tileHigh)));
   this.tilePoints.add(controlPoint);
  }
  
 }