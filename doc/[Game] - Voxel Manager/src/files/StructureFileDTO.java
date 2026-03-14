package files;

 import
  java.io.Serializable;
import files.utils.ObjectCompareUtils;

 /**
   * Definición del objeto de transferencia de datos 'StructureFileDTO', es una
   * definición de objeto simple sin lógica de negocio añadida, es decir no
   * presenta funcionalidades salvo la propia definición y acceso a los datos
   * que conforman el objeto.
   * La funcionalidad de la clase es la de manejar la definiciones de
   * 'Structure' del juego, básicamente una 'Structure' es un conjunto de
   * 'Tiles' con forma y valor determinada, su utilidad radica a la hora de
   * construir los mapas, ya que no es necesario ir 'Tile' por 'Tile', el uso
   * de 'Structures' facilita su construcción a la par que su mantenimiento, y
   * permite definir objetos genéricos de una forma clara.
   *
   * @since 0.1.a
   * @version $Id: StructureFileDTO.java 2017-03-21 09:54:00$
   *
   * @see
   *  java.io.Serializable
   *
   */
 public class StructureFileDTO
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
    * Variable privada de tipo {@code int} que especifica el identificador
    * interno que definirá la 'Structure'.
    */
  private int idStructure;

  /**
    * Variable privada de tipo {@code String} que presenta una descripción
    * simple de la 'Structure'.
    */
  private String descriptionStructure;

  /**
   * Variable privada de tipo {@code int} que especifica la altura en 'Tiles'
   * que presenta la 'Structure'.
   */
  private int highStructure;

  /**
   * Variable privada de tipo {@code int} que especifica la anchura en 'Tiles'
   * que presenta la 'Structure'.
   */
  private int wideStructure;

  /**
   * Variable privada de tipo {@code int} que especifica el largo en 'Tiles'
   * que presenta la 'Structure'.
   */
  private int longStructure;

  /**
    * Constructor genérico con parámetros de la clase 'StructureFileDTO',
    * inicializa las variables a través de los parámetros recibidos como
    * argumentos.
    *
    * @param idStructureParam {@code int}
    *  Valor de creación a asignar a la variable privada 'idStructureParam'.
    *
    * @param descriptionStructureParam {@code String}
    *  Valor de creación a asignar a la variable privada 'descriptionStructureParam'.
    *
    * @param highStructureParam {@code int}
    *  Valor de creación a asignar a la variable privada 'highStructureParam'.
    *
    * @param wideStructureParam {@code int}
    *  Valor de creación a asignar a la variable privada 'wideStructureParam'.
    *
    * @param longStructureParam {@code int}
    *  Valor de creación a asignar a la variable privada 'longStructureParam'.
    *
    */
  public StructureFileDTO(
         final int idStructureParam,
         final String descriptionStructureParam,
         final int highStructureParam,
         final int wideStructureParam,
         final int longStructureParam) {
   super();
   this.idStructure = idStructureParam;
   this.descriptionStructure = descriptionStructureParam;
   this.highStructure = highStructureParam;
   this.wideStructure = wideStructureParam;
   this.longStructure = longStructureParam;
  }

  /**
    * Constructor genérico sin parámetros de la clase 'StructureFileDTO',
    * inicializa las variables a un valor neutro según su tipo.
    */
  public StructureFileDTO() {
   super();
   this.idStructure = 0;
   this.descriptionStructure = "";
   this.highStructure = 0;
   this.wideStructure = 0;
   this.longStructure = 0;
  }

  /**
    * Método 'Getter' de la propiedad privada 'idStructure'.
    *
    * Este método realiza un {@code return} del tipo {@code int}, del
    * contenido de la variable privada 'idStructure'.
    *
    * @return {@code int}
    *  Devolvemos la variable privada 'idStructure'.
    *
    */
  public final int getIdStructure() {
   return this.idStructure;
  }

  /**
    * Método 'Setter' de la propiedad privada 'idStructure'.
    *
    * Este método establece un nuevo valor a la propiedad privada
    * 'idStructure' de tipo {@code int}, a través del parámetro recibido
    * 'idStructureParam' de tipo {@code int}.
    *
    * @param idStructureParam {@code int}
    *  Valor nuevo a asignar a la variable privada 'idStructure'.
    *
    */
  public final void setIdStructure(
         final int idStructureParam) {
   this.idStructure = idStructureParam;
  }

  /**
    * Método 'Getter' de la propiedad privada 'descriptionStructure'.
    *
    * Este método realiza un {@code return} del tipo {@code String}, del
    * contenido de la variable privada 'descriptionStructure'.
    *
    * @return {@code String}
    *  Devolvemos la variable privada 'descriptionStructure'.
    *
    */
  public final String getDescriptionStructure() {
   return this.descriptionStructure;
  }

  /**
    * Método 'Setter' de la propiedad privada 'descriptionStructure'.
    *
    * Este método establece un nuevo valor a la propiedad privada
    * 'descriptionStructure' de tipo {@code String}, a través del parámetro recibido
    * 'descriptionStructureParam' de tipo {@code String}.
    *
    * @param descriptionStructureParam {@code String}
    *  Valor nuevo a asignar a la variable privada 'descriptionStructure'.
    *
    */
  public final void setDescriptionStructure(
         final String descriptionStructureParam) {
   this.descriptionStructure = descriptionStructureParam;
  }

  /**
    * Método 'Getter' de la propiedad privada 'highStructure'.
    *
    * Este método realiza un {@code return} del tipo {@code int}, del
    * contenido de la variable privada 'highStructure'.
    *
    * @return {@code int}
    *  Devolvemos la variable privada 'highStructure'.
    *
    */
  public final int getHighStructure() {
   return this.highStructure;
  }

  /**
    * Método 'Setter' de la propiedad privada 'highStructure'.
    *
    * Este método establece un nuevo valor a la propiedad privada
    * 'highStructure' de tipo {@code int}, a través del parámetro recibido
    * 'highStructureParam' de tipo {@code int}.
    *
    * @param highStructureParam {@code int}
    *  Valor nuevo a asignar a la variable privada 'highStructure'.
    *
    */
  public final void setHighStructure(
         final int highStructureParam) {
   this.highStructure = highStructureParam;
  }

  /**
    * Método 'Getter' de la propiedad privada 'wideStructure'.
    *
    * Este método realiza un {@code return} del tipo {@code int}, del
    * contenido de la variable privada 'wideStructure'.
    *
    * @return {@code int}
    *  Devolvemos la variable privada 'wideStructure'.
    *
    */
  public final int getWideStructure() {
   return this.wideStructure;
  }

  /**
    * Método 'Setter' de la propiedad privada 'wideStructure'.
    *
    * Este método establece un nuevo valor a la propiedad privada
    * 'wideStructure' de tipo {@code int}, a través del parámetro recibido
    * 'wideStructureParam' de tipo {@code int}.
    *
    * @param wideStructureParam {@code int}
    *  Valor nuevo a asignar a la variable privada 'wideStructure'.
    *
    */
  public final void setWideStructure(
         final int wideStructureParam) {
   this.wideStructure = wideStructureParam;
  }

  /**
    * Método 'Getter' de la propiedad privada 'longStructure'.
    *
    * Este método realiza un {@code return} del tipo {@code int}, del
    * contenido de la variable privada 'longStructure'.
    *
    * @return {@code int}
    *  Devolvemos la variable privada 'longStructure'.
    *
    */
  public final int getLongStructure() {
   return this.longStructure;
  }

  /**
    * Método 'Setter' de la propiedad privada 'longStructure'.
    *
    * Este método establece un nuevo valor a la propiedad privada
    * 'longStructure' de tipo {@code int}, a través del parámetro recibido
    * 'longStructureParam' de tipo {@code int}.
    *
    * @param longStructureParam {@code int}
    *  Valor nuevo a asignar a la variable privada 'longStructure'.
    *
    */
  public final void setLongStructure(
         final int longStructureParam) {
   this.longStructure = longStructureParam;
  }

  /**
    * Método 'HashCode' de la clase 'StructureFileDTO'.
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
     hashCode = hashCode + this.getIdStructure();
     if (this.getDescriptionStructure() != null) {
      hashCode = hashCode + this.getDescriptionStructure().hashCode();
     }
     hashCode = hashCode + this.getHighStructure();
     hashCode = hashCode + this.getWideStructure();
     hashCode = hashCode + this.getLongStructure();
     this.hashCodeCalc = false;
    }
    return hashCode;
   }
  }

  /**
    * Método 'Equals' de la clase 'StructureFileDTO'.
    *
    * Este método comprueba la igualdad entre sí mismo y el objeto recibido a
    * través del parámetro 'objectParam' devolverá un tipo {@code boolean}
    * siendo 'true' en caso de ser iguales y 'false' en caso de no ser
    * iguales.
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
     if (objectParam instanceof StructureFileDTO) {
      if (this == objectParam) {
       result = true;
      } else {
       result =
        ObjectCompareUtils.equalsDataInt(
         this.getIdStructure(),
         ((StructureFileDTO) objectParam).getIdStructure())       
        && ObjectCompareUtils.equalsDataStringObject(
         this.getDescriptionStructure(),
         ((StructureFileDTO) objectParam).getDescriptionStructure())
        && ObjectCompareUtils.equalsDataInt(
         this.getHighStructure(),
         ((StructureFileDTO) objectParam).getHighStructure())    
        && ObjectCompareUtils.equalsDataInt(
         this.getWideStructure(),
         ((StructureFileDTO) objectParam).getWideStructure()) 
        && ObjectCompareUtils.equalsDataInt(
         this.getLongStructure(),
         ((StructureFileDTO) objectParam).getLongStructure());         
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
    * Método 'ToString' de la clase 'StructureFileDTO'.
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
   buffer.append("\"idStructure\":\"");
   buffer.append(this.getIdStructure());
   buffer.append("\",\"descriptionStructure\":\"");
   buffer.append(this.getDescriptionStructure());
   buffer.append("\",\"highStructure\":\"");
   buffer.append(this.getHighStructure());   
   buffer.append("\",\"wideStructure\":\"");
   buffer.append(this.getWideStructure());   
   buffer.append("\",\"longStructure\":\"");
   buffer.append(this.getLongStructure());      
   buffer.append("}");
   return buffer.toString();
  }

  /**
    * Destructor genérico del Objeto 'StructureFileDTO', libera el objeto de
    * memoria e invoca al recolector de basura para reutilizar la memoria
    * libre.
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
