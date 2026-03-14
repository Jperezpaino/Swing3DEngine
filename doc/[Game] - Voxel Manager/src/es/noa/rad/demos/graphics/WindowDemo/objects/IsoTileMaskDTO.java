package es.noa.rad.demos.graphics.WindowDemo.objects;

 import
  java.util.Arrays;

 /**
   * Clase que se encargara de contener el objeto mascara de la 'Tile' definida
   * en el mapa, la máscara es básicamente un mapa de coordenadas que permite
   * mapear la posición del ratón dentro del mapa isométrico. Al ser un mapa
   * generado dinámicamente la máscara de control debe generarse acorde a las a
   * las dimensiones del mapa.
   */
 public final class IsoTileMaskDTO {
  /**
    * Ancho total que ocupa la mascara en posiciones de control.
    */
  private int maskWidth;
  /**
    * Alto total que ocupa la mascara en posiciones de control.
    */
  private int maskHeight;  
  /**
    *  Array que contendrá los valores de la máscara de la 'Tile' definida en el
    *  mapa.
    */
  private int[] tileMask;

  public IsoTileMaskDTO() {
   super();  
   this.maskWidth = 0;
   this.maskHeight = 0;
   this.tileMask = new int[this.maskWidth * this.maskHeight];    
  }
  
  public IsoTileMaskDTO(
         final int maskWidthParam,
         final int maskHeightParam,
         final int[] tileMaskParam) {
   super();
   this.maskWidth = maskWidthParam;
   this.maskHeight = maskHeightParam;  
   this.tileMask = tileMaskParam;
  }

  public final int getMaskWidth() {
   return this.maskWidth;
  }

  public final void setMaskWidth(
         final int maskWidthParam) {
   this.maskWidth = maskWidthParam;
  }

  public final int getMaskHeight() {
   return this.maskHeight;
  }

  public final void setMaskHeight(
         final int maskHeightParam) {
   this.maskHeight = maskHeightParam;
  }

  public final void addTileMaskElement(
         final int tileMaskElementParam) {
   int arrayLength = this.getTileMaskSize();
   this.tileMask =
    Arrays.copyOf(this.tileMask, arrayLength + 1);
   this.tileMask[arrayLength]
    = tileMaskElementParam;
  }

  public final void addPositionTileMaskElement(
         final int tileMaskElementParam,
         final int positionParam) {
   int arrayLength = this.getTileMaskSize();
   if (positionParam < arrayLength) {
    int[] axiliarArray = new int[arrayLength + 1];
    System.arraycopy(
     this.tileMask, 0, axiliarArray, 0, (arrayLength - 1));
    axiliarArray[positionParam] = tileMaskElementParam;
    for (int i = positionParam + 1; i < arrayLength + 1; i++) {
     axiliarArray[i] = this.tileMask[i - 1];
    }
    this.tileMask = axiliarArray;
   } else {
    this.addTileMaskElement(
     tileMaskElementParam);
   }
  }

  public final void deleteTileMaskElement(
         final int positionParam) {
   int arrayLength = this.getTileMaskSize();
   if (positionParam < arrayLength) {
    int[] axiliarArray = new int[arrayLength  - 1];
    System.arraycopy(
     this.tileMask, 0, axiliarArray, 0, (arrayLength - 1));
    for (int i = positionParam + 1; i < arrayLength; i++) {
     axiliarArray[i - 1] = this.tileMask[i];
    }
    this.tileMask = axiliarArray;
   }
  }

  public final int[] getTileMask() {
   return this.tileMask;
  }

  public final int getTileMaskElement(
         final int positionParam) {
   if (positionParam < this.getTileMaskSize()) {
    return this.tileMask[positionParam];
   }
   int elementPosition = this.getTileMaskSize() - 1;
   return this.tileMask[elementPosition];
  }

  public final int getTileMaskSize() {
   return this.tileMask.length;
  }

  public final void setTileMask(
         final int[] tileMaskParam) {
   this.tileMask = tileMaskParam;
  }

  public final void setTileMaskElement(
         final int tileMaskElementParam,
         final int positionParam) {
   if (positionParam < this.getTileMaskSize()) {
    this.tileMask[positionParam]
     = tileMaskElementParam;
   } else {
    this.addTileMaskElement(
     tileMaskElementParam);
   }
  }

  public final boolean isTileMaskEmpty() {
   return this.tileMask.length == 0;
  }

   
  @Override
  public String toString() {
   String result = "";
   int position = 0;
   for(int i = 0; i < this.maskHeight; i++) {
    for(int j = 0; j < this.maskWidth; j++) {
     result = result + this.getTileMaskElement(position);
     position++;
    }
    result = result + "\n";
   }
   return result;
  }

 }
