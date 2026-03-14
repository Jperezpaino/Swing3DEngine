package es.noa.rad.demos.graphics.WindowDemo.objects;


 public final class IsoTileMaskUtils {
  static final int TILE_CENTER_VALUE = 0;
  static final int TILE_LEFT_TOP_VALUE = 1;
  static final int TILE_RIGHT_TOP_VALUE = 2;
  static final int TILE_LEFT_BOTTOM_VALUE = 3;
  static final int TILE_RIGHT_BOTTOM_VALUE = 4;
  
  
  public final static IsoTileMaskDTO generateTileMask(
         final IsoTile tileParam) {
   int maskWidth = tileParam.getTileWidth(); 
   int maskHeight = (int) ((tileParam.getTileBaseHigh() * 2) + 1);
   int[] tileMaskMap = new int[maskWidth * maskHeight];
   IsoTileMaskDTO tileMask = new IsoTileMaskDTO(maskWidth, maskHeight, tileMaskMap);
   int maxNumberPositioncenter = 2;
   int maxNumberPositionSide = ((maskWidth / 2) - 2);
   int arrayPosition = 0;
   // Generamos la parte superior de la mascara.
   for(int i = 0; i < (maskHeight / 2) - 1; i++) {
    for(int j = 0; j < maxNumberPositionSide; j++) {
     tileMask.setTileMaskElement(TILE_LEFT_TOP_VALUE, arrayPosition);
     arrayPosition++;
    }
    for(int j = 0; j < maxNumberPositioncenter * 2; j++) {
     tileMask.setTileMaskElement(TILE_CENTER_VALUE, arrayPosition);
     arrayPosition++;
    }
    for(int j = 0; j < maxNumberPositionSide; j++) {
     tileMask.setTileMaskElement(TILE_RIGHT_TOP_VALUE, arrayPosition);
     arrayPosition++;
    }
    // Nos desplazamos a la siguiente linea.
    maxNumberPositioncenter = maxNumberPositioncenter + 2;
    maxNumberPositionSide = maxNumberPositionSide - 2; 
   }
   // Generamos la parte inferior de la mascara.
   for(int i = 0; i < (maskHeight / 2) + 1; i++) {
    for(int j = 0; j < maxNumberPositionSide; j++) {
     tileMask.setTileMaskElement(TILE_LEFT_BOTTOM_VALUE, arrayPosition);
     arrayPosition++;
    }
    for(int j = 0; j < maxNumberPositioncenter * 2; j++) {
     tileMask.setTileMaskElement(TILE_CENTER_VALUE, arrayPosition);
     arrayPosition++;
    }
    for(int j = 0; j < maxNumberPositionSide; j++) {
     tileMask.setTileMaskElement(TILE_RIGHT_BOTTOM_VALUE, arrayPosition);
     arrayPosition++;
    }
    // Nos desplazamos a la siguiente linea.    
    maxNumberPositioncenter = maxNumberPositioncenter - 2;
    maxNumberPositionSide = maxNumberPositionSide + 2; 
   }
   //System.out.println(tileMask.getMaskWidth() + ", " + tileMask.getMaskHeight());
   //System.out.println(tileMask.toString());
   return tileMask;
  }  
 }
