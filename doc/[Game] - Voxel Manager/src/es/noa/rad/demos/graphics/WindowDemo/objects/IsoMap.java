package es.noa.rad.demos.graphics.WindowDemo.objects;

 import
  java.awt.Dimension;
 import
  java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.HashMap;

 public class IsoMap {
  /** Objeto de definicion del tipo de tile que utilizara el mapa. */
  private IsoTile mapTile;
  
  private IsoTileMaskDTO mapTileMask;
  
  private HashMap<Point, Rectangle> mapActionMapper;
  
  
  /** Altura del mapa en número de tiles. */
  private int mapHigh;
  /** Ancho del mapa en número de tiles. */
  private int mapWide;
  /** largo del mapa en número de tiles. */
  private int mapLong;
  
  /** Ancho en pixeles generado con con valores del nuemro de tiles. */
  private int mapWidth;
  /** Alto en pixeles generado con con valores del nuemro de tiles. */
  private int mapHeight;
  /** Coordenada X del mapa para comenzar a dibujar. */
  private int mapXStartPoint;
  /** Coordenada Y del mapa para comenzar a dibujar. */
  private int mapYStartPoint;
  /** Objeto de control que define propiedades estaticas para el manejo del mapa */
  private IsoMapConstraints mapConstraints;
  
  /** variable que controla si existe alguna row seleccionada en el eje de altura.
    * en caso de no haber seleccion el valor sera -1. */
  private int selectedTileHigh;
  /** variable que controla si existe alguna row seleccionada en el eje izquierdo.
    * en caso de no haber seleccion el valor sera -1. */
  private int selectedTileWide;
  /** variable que controla si existe alguna row seleccionada en el eje derecho
    * en caso de no haber seleccion el valor sera -1. */  
  private int selectedTileLong;
  /** variable que establece si esta activada alguna tile, y no tener que comprobar
    * con ello las tras varibles de posición. */
  private boolean selectedTile;
  
  /** Array que contendra los valores del mapa referente a la capa fisica. */
  private int[] physicalTileDefinition; 
  
  
  public IsoMap() {
   this.mapConstraints = new IsoMapConstraints();   
   this.mapHigh = 0;
   this.mapWide = 0;
   this.mapLong = 0;
   this.physicalTileDefinition = null;
   this.mapTile = null;
   this.mapTileMask = null;  
   this.mapWidth = 0;
   this.mapHeight = 0;
   this.mapXStartPoint = 0;
   this.mapYStartPoint = 0;
   // No establecemos una tile seleccionada.
   this.selectedTileHigh = 0;
   this.selectedTileWide = 0;
   this.selectedTileLong = 0;
   this.selectedTile = false;   
  }

  public final IsoTile getMapTile() {
   return this.mapTile;
  }

  public final void setMapTile(
         final IsoTile mapTileParam) {
   this.mapTile = mapTileParam;
  }

  public final IsoTileMaskDTO getMapTileMask() {
   return this.mapTileMask;
  }

  public final void setMapTileMask(
         final IsoTileMaskDTO mapTileMaskParam) {
   this.mapTileMask = mapTileMaskParam;
  }

  public final int getMapHigh() {
   return this.mapHigh;
  }

  public final void setMapHigh(
         final int mapHighParam) {
   this.mapHigh = mapHighParam;
  }

  public final int getMapWide() {
   return this.mapWide;
  }

  public final void setMapWide(
         final int mapWideParam) {
   this.mapWide = mapWideParam;  
  }

  public final int getMapLong() {
   return this.mapLong;
  }

  public final void setMapLong(
         final int mapLongParam) {
   this.mapLong = mapLongParam; 
  }

  public final int getMapWidth() {
   return this.mapWidth;
  }

  public final void setMapWidth(
         final int mapWidthParam) {
   this.mapWidth = mapWidthParam;
  }

  public final int getMapHeight() {
   return this.mapHeight;
  }

  public final void setMapHeight(
         final int mapHeightParam) {
   this.mapHeight = mapHeightParam;
  }
  
  public final int getMapXStartPoint() {
   return this.mapXStartPoint;
  }

  public final void setMapXStartPoint(
         final int mapXStartPointParam) {
   this.mapXStartPoint = mapXStartPointParam;
  }

  public final int getMapYStartPoint() {
   return this.mapYStartPoint;
  }

  public final void setMapYStartPoint(
         final int mapYStartPointParam) {
   this.mapYStartPoint = mapYStartPointParam;
  }

  public final IsoMapConstraints getMapConstraints() {
   return this.mapConstraints;
  }

  public final void setMapConstraints(
         final IsoMapConstraints mapConstraintsParam) {
   this.mapConstraints = mapConstraintsParam;
  }
  
  public final int getSelectedTileHigh() {
   return this.selectedTileHigh;
  }

  public final void setSelectedTileHigh(
         final int selectedTileHighParam) {
   this.selectedTileHigh = selectedTileHighParam;
  }

  public final int getSelectedTileWide() {
   return this.selectedTileWide;
  }

  public final void setSelectedTileWide(
         final int selectedTileWideParam) {
   this.selectedTileWide = selectedTileWideParam;
  }

  public final int getSelectedTileLong() {
   return this.selectedTileLong;
  }

  public final void setSelectedTileLong(
         final int selectedTileLongParam) {
   this.selectedTileLong = selectedTileLongParam;
  }

  public final boolean isSelectedTile() {
   return this.selectedTile;
  }

  public final void setSelectedTile(
         final boolean selectedTileParam) {
   this.selectedTile = selectedTileParam;
  }

  public final void addPhysicalTileDefinitionElement(
         final int physicalTileDefinitionElementParam) {
   int arrayLength = this.getPhysicalTileDefinitionSize();
   this.physicalTileDefinition =
    Arrays.copyOf(this.physicalTileDefinition, arrayLength + 1);
   this.physicalTileDefinition[arrayLength]
    = physicalTileDefinitionElementParam;
  }

  public final void addPositionPhysicalTileDefinitionElement(
         final int physicalTileDefinitionElementParam,
         final int positionParam) {
   int arrayLength = this.getPhysicalTileDefinitionSize();
   if (positionParam < arrayLength) {
    int[] axiliarArray = new int[arrayLength + 1];
    System.arraycopy(
     this.physicalTileDefinition, 0, axiliarArray, 0, (arrayLength - 1));
    axiliarArray[positionParam] = physicalTileDefinitionElementParam;
    for (int i = positionParam + 1; i < arrayLength + 1; i++) {
     axiliarArray[i] = this.physicalTileDefinition[i - 1];
    }
    this.physicalTileDefinition = axiliarArray;
   } else {
    this.addPhysicalTileDefinitionElement(
     physicalTileDefinitionElementParam);
   }
  }

  public final void deletePhysicalTileDefinitionElement(
         final int positionParam) {
   int arrayLength = this.getPhysicalTileDefinitionSize();
   if (positionParam < arrayLength) {
    int[] axiliarArray = new int[arrayLength  - 1];
    System.arraycopy(
     this.physicalTileDefinition, 0, axiliarArray, 0, (arrayLength - 1));
    for (int i = positionParam + 1; i < arrayLength; i++) {
     axiliarArray[i - 1] = this.physicalTileDefinition[i];
    }
    this.physicalTileDefinition = axiliarArray;
   }
  }

  public final int[] getPhysicalTileDefinition() {
   return this.physicalTileDefinition;
  }

  public final int getPhysicalTileDefinitionElement(
         final int positionParam) {
   if (positionParam < this.getPhysicalTileDefinitionSize()) {
    return this.physicalTileDefinition[positionParam];
   }
   int elementPosition = this.getPhysicalTileDefinitionSize() - 1;
   return this.physicalTileDefinition[elementPosition];
  }

  public final int getPhysicalTileDefinitionSize() {
   return this.physicalTileDefinition.length;
  }

  public final void setPhysicalTileDefinition(
         final int[] physicalTileDefinitionParam) {
   this.physicalTileDefinition = physicalTileDefinitionParam;
  }

  public final void setPhysicalTileDefinitionElement(
         final int physicalTileDefinitionElementParam,
         final int positionParam) {
   if (positionParam < this.getPhysicalTileDefinitionSize()) {
    this.physicalTileDefinition[positionParam]
     = physicalTileDefinitionElementParam;
   } else {
    this.addPhysicalTileDefinitionElement(
     physicalTileDefinitionElementParam);
   }
  }

  public final boolean isPhysicalTileDefinitionEmpty() {
   return this.physicalTileDefinition.length == 0;
  }
  
  public HashMap<Point, Rectangle> getMapActionMapper() {
   return mapActionMapper;
  }

  public void setMapActionMapper(HashMap<Point, Rectangle> mapActionMapper) {
   this.mapActionMapper = mapActionMapper;
  }

  public static void main(String[] a) {
   IsoMap map;
   map = new IsoMap();
   map.setMapTile(new IsoTile(28, 21, 8, 6.5F));
   
   System.out.println("Iso Base 1 x 1 x 1 (30 x 23)");
   map.setMapLong(1);
   map.setMapWide(1);
   map.setMapHigh(1);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());
   
   System.out.println("Iso Base 1 x 1 x 2 (30 x 31)");
   map.setMapHigh(2);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());
   
   System.out.println("Iso Base 1 x 1 x 3 (30 x 39)");
   map.setMapHigh(3);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());

   System.out.println("Iso Base 1 x 1 x 4 (30 x 47)");
   map.setMapHigh(4);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());

   System.out.println("Iso Base 1 x 1 x 5 (30 x 55)");
   map.setMapHigh(5);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());

   System.out.println("Iso Base 1 x 1 x 6 (30 x 63)");
   map.setMapHigh(6);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());

   System.out.println("Iso Base 1 x 1 x 7 (30 x 71)");
   map.setMapHigh(7);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());

   System.out.println("Iso Base 1 x 1 x 8 (30 x 79)");
   map.setMapHigh(8);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());

   System.out.println("Iso Base 1 x 1 x 9 (30 x 87)");
   map.setMapHigh(9);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());

   System.out.println("Iso Base 1 x 1 x 10 (30 x 95)");
   map.setMapHigh(10);
   System.out.println(map.getMapWidth() + " x " + map.getMapHeight());


   System.out.println("Iso Base 1 x 2 x 2 (44 x 38) / (28 x 17)");
   map.setMapLong(1);
   map.setMapWide(2);
   map.setMapHigh(2);
   System.out.println("Iso Base "
    + map.getMapLong() + " x " + map.getMapWide() + " x " + map.getMapHigh()
    + " (" + map.getMapWidth() + " x " + map.getMapHeight() + ") / ("
    + map.getMapXStartPoint() + " x " + map.getMapYStartPoint() + ")");
   
   
   /*System.out.println("Iso Base 1 x 2 x 1 (72 x 50)");
   map.setMapWide(2);
   map.setMapHigh(1);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 1 x 2 x 2 (72 x 65)");
   map.setMapHigh(2);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 1 x 2 x 3 (72 x 80)");
   map.setMapHigh(3);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 1 x 3 x 1 (96 x 62)");
   map.setMapWide(3);
   map.setMapHigh(1);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 1 x 3 x 2 (96 x 77)");
   map.setMapHigh(2);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 1 x 3 x 3 (96 x 92)");
   map.setMapHigh(3);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 1 x 1 (72 x 50)");
   map.setMapLong(2);
   map.setMapWide(1);
   map.setMapHigh(1);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 1 x 2 (72 x 65)");
   map.setMapHigh(2);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 1 x 3 (72 x 80)");
   map.setMapHigh(3);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 2 x 1 (96 x 62)");
   map.setMapWide(2);
   map.setMapHigh(1);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 2 x 2 (96 x 77)");
   map.setMapHigh(2);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 2 x 3 (96 x 92)");
   map.setMapHigh(3);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 3 x 1 (120 x 74)");
   map.setMapWide(3);
   map.setMapHigh(1);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 3 x 2 (120 x 89)");
   map.setMapHigh(2);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 2 x 3 x 3 (120 x 104)");
   map.setMapHigh(3);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 1 x 1 (96 x 62)");
   map.setMapLong(3);
   map.setMapWide(1);
   map.setMapHigh(1);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 1 x 2 (96 x 77)");
   map.setMapHigh(2);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 1 x 3 (96 x 92)");
   map.setMapHigh(3);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 2 x 1 (120 x 74)");
   map.setMapWide(2);
   map.setMapHigh(1);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 2 x 2 (120 x 89)");
   map.setMapHigh(2);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 2 x 3 (120 x 104)");
   map.setMapHigh(3);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 3 x 1 (144 x 86)");
   map.setMapWide(3);
   map.setMapHigh(1);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 3 x 2 (144 x 101)");
   map.setMapHigh(2);
   map = IsoMapUtils.calculateMapSize(map);
   
   System.out.println("Iso Base 3 x 3 x 3 (144 x 116)");
   map.setMapHigh(3);
   map = IsoMapUtils.calculateMapSize(map);*/
  }

 }
