package files;

 import
  java.io.Serializable;
 import
  java.util.ArrayList;
 import
  java.util.List;
 import
  files.utils.ObjectCompareUtils;

 /**
   * Definición del objeto de transferencia de datos 'GameDataDTO', es una
   * definición de objeto simple sin lógica de negocio añadida, es decir no
   * presenta funcionalidades salvo la propia definición y acceso a los datos
   * que conforman el objeto.
   * La funcionalidad de la clase es la de manejar los conjuntos de recursos
   * que forman parte del juego, siendo la clase global de acceso y carga de
   * estos recursos, presenta a su vez la configuración global del juego y los
   * valores estándar que presenta el mismo.
   *
   * @since 0.1.a
   * @version $Id: GameDataDTO.java 2017-03-20 12:22:00$
   *
   * @see
   *  java.io.Serializable
   *
   */
 public class GameDataDTO
        implements Serializable {

  /**
    * Variable privada e inmutable de tipo {@code long} que contiene el número
    * de serie de la clase.
    */
  private static final long serialVersionUID = 1L;

  /**
    * Variable privada de transición de tipo {@code boolean} para establecer si
    * el 'HashCode' de la clase a sido calculado.
    */
  private transient boolean hashCodeCalc;

  /**
    * Variable privada de tipo {@code int} que especifica el ancho que presenta
    * las 'Tiles' que se utilizaran en el juego, es un valor global por lo que
    * todas las 'Tiles' del juego utilizaran este valor.
    */
  private int tileWidthGameData;

  /**
    * Variable privada de tipo {@code int} que especifica el alto que presenta
    * las 'Tiles' que se utilizaran en el juego, es un valor global por lo que
    * todas las 'Tiles' del juego utilizaran este valor.
    */
  private int tileHeightGameData;

  /**
    * Variable privada de tipo {@code List<PaletteFileDTO>} con cada una de las
    * 'Palettes' que contiene el juego en formato de objeto {@code
    * TileFileDTO}, estas 'Palettes' contienen la información sobre el color
    * necesaria para la generación de los contextos gráficos del juego.
    */
  private List<PaletteFileDTO> palettesGameData;

  /**
    * Constructor genérico con parámetros de la clase 'GameDataDTO', inicializa
    * las variables a través de los parámetros recibidos como argumentos.
    *
    * @param tileWidthGameDataParam {@code int}
    *  Valor de creación a asignar a la variable privada 'tileWidthGameData'.
    *
    * @param tileHeightGameDataParam {@code int}
    *  Valor de creación a asignar a la variable privada 'tileHeightGameData'.
    *
    * @param palettesGameDataParam {@code List<PaletteFileDTO>}
    *  Valor de creación a asignar a la variable privada 'palettesGameData'.
    *
    */
  public GameDataDTO(
         final int tileWidthGameDataParam,
         final int tileHeightGameDataParam,
         final List<PaletteFileDTO> palettesGameDataParam) {
   super();
   this.tileWidthGameData = 0;
   this.tileHeightGameData = 0;
   this.palettesGameData = palettesGameDataParam;
  }

  /**
    * Constructor genérico sin parámetros de la clase 'GameDataDTO', inicializa
    * las variables a un valor neutro según su tipo.
    */
  public GameDataDTO() {
   super();
   this.tileWidthGameData = 0;
   this.tileHeightGameData = 0;
   this.palettesGameData = new ArrayList<>();
  }

  /**
    * Método 'Getter' de la propiedad privada 'tileWidthGameData'.
    *
    * Este método realiza un {@code return} del tipo {@code int}, del
    * contenido de la variable privada 'tileWidthGameData'.
    *
    * @return {@code int}
    *  Devolvemos la variable privada 'tileWidthGameData'.
    *
    */
  public final int getTileWidthGameData() {
   return this.tileWidthGameData;
  }

  /**
    * Método 'Setter' de la propiedad privada 'tileWidthGameData'.
    *
    * Este método establece un nuevo valor a la propiedad privada
    * 'tileWidthGameData' de tipo {@code int}, a través del parámetro recibido
    * 'tileWidthGameDataParam' de tipo {@code int}.
    *
    * @param tileWidthGameDataParam {@code int}
    *  Valor nuevo a asignar a la variable privada 'tileWidthGameData'.
    *
    */
  public final void setTileWidthGameData(
         final int tileWidthGameDataParam) {
   this.tileWidthGameData = tileWidthGameDataParam;
  }

  /**
    * Método 'Getter' de la propiedad privada 'tileHeightGameData'.
    *
    * Este método realiza un {@code return} del tipo {@code int}, del
    * contenido de la variable privada 'tileHeightGameData'.
    *
    * @return {@code int}
    *  Devolvemos la variable privada 'tileHeightGameData'.
    *
    */
  public final int getTileHeightGameData() {
   return this.tileHeightGameData;
  }

  /**
    * Método 'Setter' de la propiedad privada 'tileHeightGameData'.
    *
    * Este método establece un nuevo valor a la propiedad privada
    * 'tileHeightGameData' de tipo {@code int}, a través del parámetro recibido
    * 'tileHeightGameDataParam' de tipo {@code int}.
    *
    * @param tileHeightGameDataParam {@code int}
    *  Valor nuevo a asignar a la variable privada 'tileHeightGameData'.
    *
    */
  public final void setTileHeightGameData(
         final int tileHeightGameDataParam) {
   this.tileHeightGameData = tileHeightGameDataParam;
  }

  /**
    * Método 'Add' de la propiedad privada 'palettesGameData'.
    *
    * Este método añade un nuevo valor a la lista de la propiedad privada
    * 'palettesGameData', de tipo {@code List<PaletteFileDTO>}, a través del
    * parámetro recibido 'palettesGameDataElementParam' de tipo {@code
    * PaletteFileDTO}.
    *
    * @param palettesGameDataElementParam {@code PaletteFileDTO}
    *  Valor nuevo a añadir a la lista de la variable privada
    *  'palettesGameData'.
    *
    */
  public final void addPalettesGameDataElement(
         final PaletteFileDTO palettesGameDataElementParam) {
   this.palettesGameData.add(
    palettesGameDataElementParam);
  }

  /**
    * Método 'Add by Position' de la propiedad privada 'palettesGameData'.
    *
    * Este método añade un nuevo valor en una determinada posición de la lista
    * de la propiedad privada 'palettesGameData', de tipo {@code
    * List<PaletteFileDTO>}, a través del parámetro recibido
    * 'palettesGameDataElementParam' de tipo {@code PaletteFileDTO}, y el
    * parámetro recibido 'positionParam' de tipo {@code int}, que marca la
    * posición en la que realizar la inserción, en caso de ser una posición
    * mayor a la longitud de la lista se insertara al final de la misma.
    *
    * @param palettesGameDataElementParam {@code PaletteFileDTO}
    *  Valor nuevo a añadir a la lista de la variable privada
    *  'palettesGameData'.
    *
    * @param positionParam {@code int}
    *  Valor con la posición de la lista de la variable privada
    *  'palettesGameData' en la que se quiere insertar.
    *
    */
  public final void addPositionPalettesGameDataElement(
         final PaletteFileDTO palettesGameDataElementParam,
         final int positionParam) {
   int listLength = this.getPalettesGameDataSize();
   if (positionParam < listLength) {
    this.palettesGameData.add(
     positionParam, palettesGameDataElementParam);
   } else {
    this.palettesGameData.add(
     palettesGameDataElementParam);
   }
  }

  /**
    * Método 'Delete' de la propiedad privada 'palettesGameData'.
    *
    * Este método elimina un valor de la lista de la propiedad privada
    * 'palettesGameData', de tipo {@code List<PaletteFileDTO>}, a través del
    * parámetro recibido 'positionParam' de tipo {@code int}, que marca la
    * posición de la lista a eliminar.
    *
    * @param positionParam {@code int}
    *  Valor con la posición de la lista de la variable privada
    *  'palettesGameData' que se quiere eliminar.
    *
    */
  public final void deletePalettesGameDataElement(
         final int positionParam) {
   int listLength = this.getPalettesGameDataSize();
   if (positionParam < listLength) {
    this.palettesGameData.remove(positionParam);
   }
  }

  /**
    * Método 'Getter' de la propiedad privada 'palettesGameData'.
    *
    * Este método realiza un {@code return} del tipo {@code
    * List<PaletteFileDTO>}, del contenido de la variable privada
    * 'palettesGameData'.
    *
    * @return {@code List<PaletteFileDTO>}
    *  Devolvemos la variable privada 'palettesGameData'.
    *
    */
  public final List<PaletteFileDTO> getPalettesGameData() {
   return this.palettesGameData;
  }

  /**
    * Método 'Getter Element' de la propiedad privada 'palettesGameData'.
    *
    * Este método realiza un {@code return} del tipo {@code PaletteFileDTO} del
    * contenido de la posición de la lista de la variable privada
    * 'palettesGameData', de tipo {@code List<PaletteFileDTO>}, a través del
    * parámetro recibido 'positionParam' de tipo {@code int}, que marca la
    * posición de la lista a recuperar, en caso de ser una posición mayor a la
    * longitud de la lista se recuperara el ultimo valor de la lista.
    *
    * @param positionParam {@code int}
    *  Valor con la posición de la lista de la variable privada
    *  'palettesGameData' que se quiere recuperar.
    *
    * @return {@code PaletteFileDTO}
    *  Devolvemos la posición de la lista de la variable privada
    *  'palettesGameData'.
    *
    */
  public final PaletteFileDTO getPalettesGameDataElement(
         final int positionParam) {
   if (positionParam < this.getPalettesGameDataSize()) {
    return this.palettesGameData.get(positionParam);
   }
   return this.palettesGameData.get(this.getPalettesGameDataSize());
  }

  /**
    * Método 'Getter Size' de la propiedad privada 'palettesGameData'.
    *
    * Este método realiza un {@code return}, del tipo {@code int}, del tamaño
    * de la lista de la variable privada 'palettesGameData'.
    *
    * @return {@code int}
    *  Devolvemos el tamaño de la lista de la variable privada
    *  'palettesGameData'.
    *
    */
  public final int getPalettesGameDataSize() {
   return this.palettesGameData.size();
  }

  /**
    * Método 'Setter' de la propiedad privada 'palettesGameData'.
    *
    * Este método establece un nuevo valor a la propiedad privada
    * 'palettesGameData' de tipo {@code List<PaletteFileDTO>}, a través del
    * parámetro recibido 'palettesGameDataParam' de tipo {@code
    * List<PaletteFileDTO>}.
    *
    * @param palettesGameDataParam {@code List<PaletteFileDTO>}
    *  Valor nuevo a asignar a la variable privada 'palettesGameData'.
    *
    */
  public final void setPalettesGameData(
         final List<PaletteFileDTO> palettesGameDataParam) {
   this.palettesGameData = palettesGameDataParam;
  }

  /**
    * Método 'Setter Element' de la propiedad privada 'palettesGameData'.
    *
    * Este método establece un nuevo valor en una determinada posición de la
    * lista de la propiedad privada 'palettesGameData' de tipo {@code
    * List<PaletteFileDTO>}, a través del parámetro recibido
    * 'palettesGameDataElementParam' de tipo {@code PaletteFileDTO}, y el
    * parámetro recibido 'positionParam' de tipo {@code int}, que marca la
    * posición en la que realizar la inserción, en caso de ser una posición
    * mayor a la longitud de la lista se insertara al final de la misma.
    *
    * @param palettesGameDataElementParam {@code PaletteFileDTO}
    *  Valor nuevo a asignar a la posición de la lista de la variable privada
    *  'palettesGameData'.
    *
    * @param positionParam {@code int}
    *  Valor con la posición de la lista de la variable privada
    *  'palettesGameData' en la que se quiere actualizar.
    *
    */
  public final void setPalettesGameDataElement(
         final PaletteFileDTO palettesGameDataElementParam,
         final int positionParam) {
   if (positionParam < this.getPalettesGameDataSize()) {
    this.palettesGameData.remove(positionParam);
    this.palettesGameData.add(
     positionParam, palettesGameDataElementParam);
   } else {
    this.palettesGameData.add(
     palettesGameDataElementParam);
   }
  }

  /**
    * Método 'Is Info' de la propiedad privada 'palettesGameData'.
    *
    * Este método realiza un {@code return}, del tipo {@code boolean}, con el
    * estado que presenta la lista de la variable privada 'palettesGameData',
    * con respecto a si está vacía de elementos o no.
    *
    * @return {@code boolean}
    *  Devolvemos 'true' o 'false' con respecto a si está vacía de elementos o
    *  no la variable privada 'palettesGameData', devuelve 'true' en caso de
    *  estar vacía y 'false' en caso de presentar elementos.
    *
    */
  public final boolean isPalettesGameDataEmpty() {
   return this.palettesGameData.isEmpty();
  }

  /**
    * Método 'HashCode' de la clase 'GameDataDTO'.
    *
    * Este método genera el 'HashCode' correspondiente al objeto de la clase, y
    * lo devuelve con el tipo {@code int}.
    *
    * @return {@code int}
    *  Devolvemos el 'HashCode' generado de la clase.
    *  
    * @see
    *  java.lang.Object#hashCode()
    *  
    * @see
    *  java.util.Hashtable &lt; K , V &gt;
    *  
    */
  @Override
  public final int hashCode() {
   synchronized (this) {
    int hashCode;
    if (this.hashCodeCalc) {
     hashCode = 0;
    } else {
     hashCode = 1;
     this.hashCodeCalc = true;
     hashCode = hashCode + this.getTileWidthGameData();
     hashCode = hashCode + this.getTileHeightGameData();
     if (this.getPalettesGameData() != null) {
      hashCode = hashCode + this.getPalettesGameData().hashCode();
     }
     this.hashCodeCalc = false;
    }
    return hashCode;
   }
  }

  /**
    * Método 'Equals' de la clase 'GameDataDTO'.
    *
    * Este método comprueba la igualdad entre sí mismo y el objeto recibido a través del parámetro 'objectParam' devolverá un tipo {@code boolean} siendo 'true' en caso de ser iguales y 'false' en caso de no ser iguales.
    *
    * @param objectParam {@code Object}
    *  Objeto sobre el que se comparara el actual.
    *
    * @return {@code boolean}
    *  Devolvemos 'true' o 'false' según la equidad o no de los objetos.
    *
    * @see
    *  java.lang.Object#equals(java.lang.Object)
    *
    */
  @Override
  public final boolean equals(
         final Object objectParam) {
   synchronized (this) {
    boolean result;
    if (objectParam == null) {
     result = false;
    } else {
     if (objectParam instanceof GameDataDTO) {
      if (this == objectParam) {
       result = true;
      } else {
       result =
        ObjectCompareUtils.equalsDataInt(
         this.getTileWidthGameData(),
         ((GameDataDTO) objectParam).getTileWidthGameData())
        && ObjectCompareUtils.equalsDataInt(
         this.getTileHeightGameData(),
         ((GameDataDTO) objectParam).getTileHeightGameData())
        && ObjectCompareUtils.equalsDataList(
         this.getPalettesGameData(),
         ((GameDataDTO) objectParam).getPalettesGameData());
       result = false;
      }
     } else {
      result = false;
     }
    }
    return result;
   }
  }

  /**
    * Método 'ToString' de la clase 'GameDataDTO'.
    *
    * Este método genera una representación escrita del objeto y devolverá un
    * tipo {@code String} con su contenido.
    *
    * @return {@code String}
    *  Devolvemos la representación escrita del objeto.
    *
    * @see
    *  java.lang.Object#toString()
    *
    */
  @Override
  public final String toString() {
   final StringBuilder buffer = new StringBuilder();
   buffer.append("{");
   buffer.append("\"tileWidth\":\"");
   buffer.append(this.getTileWidthGameData());
   buffer.append("\",\"tileHeight\":\"");
   buffer.append(this.getTileHeightGameData());   
   buffer.append("\",\"paletteFiles\":[");
   for(int i = 0; i < this.getPalettesGameDataSize(); i++) {
    buffer.append(this.getPalettesGameDataElement(i).toString());
    if(i < this.getPalettesGameDataSize() - 1) {
     buffer.append(",");
    }
   }
   buffer.append("]}");
   return buffer.toString();
  }

  /**
    * Destructor genérico del Objeto 'GameDataDTO', libera el objeto de memoria
    * e invoca al recolector de basura para reutilizar la memoria libre.
    */
  @Override
  public final void finalize() {
   try {
    super.finalize();
    Runtime garbage = Runtime.getRuntime();
    garbage.gc();
   } catch (Throwable throwable) {
    this.finalize();
   }
  }

 }