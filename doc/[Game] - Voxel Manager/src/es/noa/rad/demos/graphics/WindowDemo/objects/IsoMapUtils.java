package es.noa.rad.demos.graphics.WindowDemo.objects;

 import
  java.awt.Dimension;
 import
  java.awt.Point;
 import
  java.awt.Rectangle;
 import
  java.util.HashMap;

 public class IsoMapUtils {

  public static final Dimension calculateMapSize(
         final IsoMap mapParam) {
   Dimension mapBounds = new Dimension(0, 0);
   IsoMap map = mapParam;
   IsoTile tile = map.getMapTile();
   float mapWidth; // Ancho del mapa.
   float mapHeight; // Alto del mapa.
   // Calculamos el tamaño final del mapa generado.
   mapWidth = ((map.getMapLong() + map.getMapWide()) * (tile.getTileWidth() / 2));
   mapHeight =
      ((((map.getMapLong() + map.getMapWide()) * tile.getTileBaseHigh())
    + (((map.getMapLong() + map.getMapWide()) - 2) * 0.5F))
    + (map.getMapHigh() * tile.getTileHigh()));
   mapBounds.setSize(((Math.round(mapWidth)) + 20), ((Math.round(mapHeight)) + 20));
   return mapBounds;
  }

  public static final Point calculateMapPosition(
                      final IsoMap mapParam) {
   Point mapPosition = new Point(0, 0);
   IsoMap map = mapParam;
   IsoTile tile = map.getMapTile();
   // Calculamos la posicion para iniciar el dibujado de los elementos.
   int xPoint = ((int) (((map.getMapWidth() / 2) - ((map.getMapLong() - 1) * (tile.getTileWidth() / 4)) + ((map.getMapWide() - 1) * (tile.getTileWidth() / 4) )) - 1));
   int yPoint = ((int) (map.getMapHigh() * tile.getTileHigh()));
   mapPosition.setLocation(xPoint, (yPoint + 10));
   return mapPosition;
  }

  public static final HashMap<Point, Rectangle> calculeMouseActionMap(
                final IsoMap mapParam) {
   // Generamos un punto de referencia sobre el que empezar a dibujar.
   Point mapStarPoint = new Point(0, 0);
   // Generamos el Mapa final de coordenadas y areas de dibujo.
   HashMap<Point, Rectangle> mapActionMapper = new HashMap<Point, Rectangle>();
   // Recuperamos la información de la tile definida en el mapa.
   IsoTile tileInfo = mapParam.getMapTile();
   // Recuperamos el punto inicial sobre el que generar la regilla de control definido en el objeto 'Map'.
   mapStarPoint.setLocation(
    mapParam.getMapXStartPoint(),
    mapParam.getMapYStartPoint());
   //System.out.println(mapStarPoint.toString());
   // Desplazamos el punto al inicio de la tile.
   mapStarPoint.translate(
    ((int) (((tileInfo.getTileWidth() / 2) -1) * -1)), 0);
   
   // Calculamos los avances existentes en cada unos de los lados del punto central.    
   int pointPositionLeftAdvance = 0;
   int pointPositionRightAdvance = 0;   
   while(mapStarPoint.getX() > 0) {
    mapStarPoint.translate(((int)(tileInfo.getTileWidth() * -1)), 0);
    pointPositionLeftAdvance++;
   }   
   pointPositionRightAdvance = pointPositionLeftAdvance;
   pointPositionLeftAdvance = (pointPositionLeftAdvance * -1);
   
   // Calculamos los avances existentes hacia abajo del punto central.    
   int cellRowsCount;
   if(mapParam.getMapWide() < mapParam.getMapLong()) {
    cellRowsCount = mapParam.getMapLong();
   } else {
    cellRowsCount = mapParam.getMapWide();
   }  
   
   // Calculamos el mapa de control.
   for(int i = 0; i < cellRowsCount; i++) {
    Point middlePoint = mapStarPoint.getLocation();
    int pointPositionLeftAdvance_ = pointPositionLeftAdvance;
    int pointPositionRightAdvance_ = pointPositionRightAdvance;    
    for(int j = 0; j <= cellRowsCount; j++) {
     Rectangle actionArea = new Rectangle(
      (int)mapStarPoint.getX(),
      (int)mapStarPoint.getY(),
      tileInfo.getTileWidth() - 1,
      ((int) (tileInfo.getTileWidth() / 2)) - 1);     
     Point actionPoint = new Point(pointPositionLeftAdvance, pointPositionRightAdvance);
     // Elimina los mapas desbordados.
     boolean added = false;
       
     if((pointPositionLeftAdvance >= 0) && (pointPositionLeftAdvance < mapParam.getMapLong())) { 
      if((pointPositionRightAdvance >= 0) && (pointPositionRightAdvance < mapParam.getMapWide())) {
       mapActionMapper.put(actionPoint, actionArea);
       added = true;
      }
     } 
     if(!added) {
      if((pointPositionLeftAdvance + 1 >= 0) && (pointPositionLeftAdvance + 1 < mapParam.getMapLong())) { 
       if((pointPositionRightAdvance >= 0) && (pointPositionRightAdvance < mapParam.getMapWide())) {
        mapActionMapper.put(actionPoint, actionArea);
        added = true;        
       }
      }
     }
     if(!added) {
      if((pointPositionLeftAdvance - 1 >= 0) && (pointPositionLeftAdvance - 1 < mapParam.getMapLong())) { 
       if((pointPositionRightAdvance >= 0) && (pointPositionRightAdvance < mapParam.getMapWide())) {
        mapActionMapper.put(actionPoint, actionArea);
        added = true;        
       }
      }
     }
     if(!added) {
      if((pointPositionLeftAdvance >= 0) && (pointPositionLeftAdvance < mapParam.getMapLong())) { 
       if((pointPositionRightAdvance + 1 >= 0) && (pointPositionRightAdvance + 1 < mapParam.getMapWide())) {
        mapActionMapper.put(actionPoint, actionArea);
        added = true;        
       }
      }
     }     
     if(!added) {
      if((pointPositionLeftAdvance >= 0) && (pointPositionLeftAdvance < mapParam.getMapLong())) { 
       if((pointPositionRightAdvance - 1 >= 0) && (pointPositionRightAdvance - 1 < mapParam.getMapWide())) {
        mapActionMapper.put(actionPoint, actionArea);
        added = true;        
       }
      }
     }
     /*if(added) {
      System.out.println(actionArea.toString());
     }*/
     pointPositionRightAdvance = (pointPositionRightAdvance - 1);
     pointPositionLeftAdvance = (pointPositionLeftAdvance + 1);
     mapStarPoint.translate(tileInfo.getTileWidth(), 0);     
    }
    mapStarPoint = middlePoint.getLocation();
    mapStarPoint.translate(0, ((int) (tileInfo.getTileWidth() / 2)));
    pointPositionRightAdvance = (pointPositionRightAdvance_ + 1);
    pointPositionLeftAdvance = (pointPositionLeftAdvance_ + 1);
   }
   //System.out.println(mapActionMapper.toString());
   return mapActionMapper;   
  }
  
  public static final IsoMap regenerateMap(
         final IsoMap mapParam) throws Exception {
   IsoMap regenerateMap = mapParam;
   if(regenerateMap.getMapTile() != null) {
    if((regenerateMap.getMapHigh() > 0)
     && (regenerateMap.getMapWide() > 0)
     && (regenerateMap.getMapLong() > 0)) {
     // Generamos la Mascara del Mapa.
     regenerateMap.setMapTileMask(
      IsoTileMaskUtils.generateTileMask(regenerateMap.getMapTile()));    
     // Calculamos los tamaños fisicos del mapa, y la posicion de dibujado.
     Dimension mapBounds = IsoMapUtils.calculateMapSize(regenerateMap);
     regenerateMap.setMapWidth((int) mapBounds.getWidth());
     regenerateMap.setMapHeight((int) mapBounds.getHeight());
     Point mapPosition = IsoMapUtils.calculateMapPosition(regenerateMap);
     regenerateMap.setMapXStartPoint((int) mapPosition.getX());
     regenerateMap.setMapYStartPoint((int) mapPosition.getY());         
     // Recalculamos el tamaño del array fisico.
     regenerateMap.setPhysicalTileDefinition(
      new int[regenerateMap.getMapHigh() * regenerateMap.getMapWide() * regenerateMap.getMapLong()]);
     // Calculamos el mapa de acción.
     regenerateMap.setMapActionMapper(IsoMapUtils.calculeMouseActionMap(regenerateMap));
    } else {
     Exception exception = new Exception();
     throw exception;
    }     
   } else {
    Exception exception = new Exception();
    throw exception;
   }
   return regenerateMap;   
  }

 }