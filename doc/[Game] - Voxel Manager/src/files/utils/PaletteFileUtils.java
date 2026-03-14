package files.utils;

 import
  java.awt.Color;
import java.io.File;
import
  java.nio.ByteBuffer;
 import
  java.nio.charset.Charset;
import es.noa.rad.util.file.FileUtils;
import es.noa.rad.util.language.binary.IdxFileUtils;
import files.PaletteFileDTO;

 public final class PaletteFileUtils {
  
  private PaletteFileUtils() {
   
  }
  
  /**
    * Devuelve un objeto PaletteFileDTO obtenido a partir de la carga de un
    * fichero de tipo *.palette, a traves de la ruta del fichero.
    * 
    * @param routeParam
    * @return
    */
  public static PaletteFileDTO loadPaletteFile(
         final String routeParam) throws Exception {
   File file = new File(routeParam);
   PaletteFileDTO palette = new PaletteFileDTO();
   try {
    palette = PaletteFileUtils.loadPaletteFile(file);
   } catch (Exception exception) {
    exception.printStackTrace();
    throw new Exception();
   }  
   return palette;
  }
 
  /**
    * Devuelve un objeto PaletteFileDTO obtenido a partir de la carga de un
    * fichero de tipo *.palette a traves de un parametro de tipo 'File'.
    * 
    * @param fileParam
    * @return
    */
  public static PaletteFileDTO loadPaletteFile(
         final File fileParam) throws Exception {
   PaletteFileDTO palette = new PaletteFileDTO();
   // Leemos el fichero de disco.
   try {
    byte[] contentFile = FileUtils.loadFile(fileParam);
    // Parseamos el contenido.
    String clearContent = new String(contentFile, Charset.forName("UTF-8"));
    // Eliminamos las almohadillas iniciales y finales.
    clearContent = clearContent.substring(1, clearContent.length() - 1);
    String[] elements = clearContent.split("#");
    int positionElement = 0;
    // Comprobamos si el fichero cargado corresponde a un fichero de 'palette'.
    String fileHead = elements[positionElement];
    if(!fileHead.equals("PAL")) {
     // el fichero cargado no es un fichero de 'palette'.
     throw new Exception();
    }
    positionElement++;
    // Obtenemos el 'IdPalette'.
    palette.setIdPalette(Integer.parseInt(elements[positionElement]));
    positionElement++;
    // Obtenemos el 'DescriptionPalette'.
    palette.setDescriptionPalette(elements[positionElement]);
    positionElement++;
    // Obtenemos el 'NumberColorsPalette'.
    palette.setNumberColorsPalette(Integer.parseInt(elements[positionElement]));
    positionElement++;
    // Obtenemos el 'TransparentColorPalette'.
    palette.setTransparentColorPalette(Integer.parseInt(elements[positionElement]));
    positionElement++;    
    // Procesamos la lista de colores.
    for(int i = 0; i < palette.getNumberColorsPalette(); i++) {
     int redComponent = Integer.parseInt(elements[positionElement]);
     positionElement++;
     int greenComponent = Integer.parseInt(elements[positionElement]);
     positionElement++;
     int blueComponent = Integer.parseInt(elements[positionElement]);
     positionElement++;
     palette.addColorsPaletteElement(new Color(redComponent, greenComponent, blueComponent));
    }
   } catch (Exception exception) {
    exception.printStackTrace();
    return null;
   }
   return palette; 
  }
  
  
  
  /**
    * Genera o reemplaza el contenido de un fichero de tipo *.palette
    * recibido a traves de un parametro por los valores definidos a traves
    * de un objeto de tipo PaletteFileDTO recibido como parametro.
    * 
    * @param routeParam
    * @param paletteFileParam
    * @return
    */
  public static void savePaletteFile(
         final String routeParam,
         final PaletteFileDTO paletteFileParam) {
   /* El continido del fichero es texto plano con formato, por lo que
      utilizamos un StringBuffer. */
   final StringBuilder contentFileBuffer = new StringBuilder();
   // Añadimos la almohadilla inicial.
   contentFileBuffer.append("#");
   // Añadimos La cabecera del fichero de tipo 'palette'.
   contentFileBuffer.append("PAL");
   // Añadimos el 'IdPalette'.
   contentFileBuffer.append(paletteFileParam.getIdPalette());
   // Añadimos la almohadilla separadora.   
   contentFileBuffer.append("#");   
   // Añadimos el 'DescriptionPalette'.
   contentFileBuffer.append(paletteFileParam.getDescriptionPalette());
   // Añadimos la almohadilla separadora.   
   contentFileBuffer.append("#");   
   // Añadimos el 'NumberColorsPalette'.
   contentFileBuffer.append(paletteFileParam.getNumberColorsPalette());
   // Añadimos la almohadilla separadora.   
   contentFileBuffer.append("#");
   // Añadimos el 'TransparentColorPalette'.
   contentFileBuffer.append(paletteFileParam.getTransparentColorPalette());
   // Añadimos la almohadilla separadora.   
   contentFileBuffer.append("#");
   // Procesamos la lista de colores.
   for(int i = 0; i < paletteFileParam.getNumberColorsPalette(); i++) {
    // Añadimos el componente Rojo.  
    contentFileBuffer.append(paletteFileParam.getColorsPaletteElement(i).getRed());
    // Añadimos la almohadilla separadora.   
    contentFileBuffer.append("#");
    // Añadimos el componente Verde.    
    contentFileBuffer.append(paletteFileParam.getColorsPaletteElement(i).getGreen());
    // Añadimos la almohadilla separadora.   
    contentFileBuffer.append("#");
    // Añadimos el componente Azul.
    contentFileBuffer.append(paletteFileParam.getColorsPaletteElement(i).getBlue());
    // Añadimos la almohadilla separadora.   
    contentFileBuffer.append("#");   
   }
   // Salvamos el fichero en la ruta especificada.   
   try {
    FileUtils.saveFile(routeParam, contentFileBuffer.toString().getBytes());
   } catch(Exception exception) {
    exception.printStackTrace();
   }
  }

  /**
    * Transforma un objeto de tipo PaletteFileDTO, en un buffer binario de tipo
    * 'idx'.
    *
    * @param paletteParam
    * @return
    */
  public static byte[] transformPaletteFileToPaletteIdx(
         final PaletteFileDTO paletteParam) {
   // Calculamos el tamaño del fichero idx, segun los valores del objeto.
   int idxFileSize = 0;
   // 'IdPalette' -> 2 bytes 0x0000 a 0xFFFF.
   idxFileSize = idxFileSize + 2;
   // 'DescriptionPalette' -> Automatico segun longitud de la cadena mas 1 byte para el control de longitud,
   //   1º byte 0x00 a 0xFF marca la longitud de la cadena.
   //   2º byte en adelante, contenido de la cadena.
   idxFileSize = idxFileSize + paletteParam.getDescriptionPalette().getBytes().length + 1;
   // 'NumberColorsPalette' -> 1 byte 0x00 a 0xFF.
   idxFileSize = idxFileSize + 1;
   // 'TransparentColorPalette' -> 2 bytes,   
   //   1º byte 0x00 a 0xFF marca si hay transparencia.
   //   2º byte 0x00 a 0xFF marca la posición del color.
   idxFileSize = idxFileSize + 2;
   // 'ColorsPalette' -> Automatico segun el número de colores.
   idxFileSize = idxFileSize + (paletteParam.getNumberColorsPalette() * 3);
   // Con el tamaño generamos el array que contendra los datos idx.
   byte[] idxFileContent = new byte[idxFileSize];
   ByteBuffer buffer = ByteBuffer.wrap(idxFileContent);
   int idxFlagPosition = 0;
   // Añadimos el 'IdPalette'.
   byte[] idPaletteMask = IdxFileUtils.intToByteArray(paletteParam.getIdPalette());
   buffer.position(idxFlagPosition);
   buffer.put(idPaletteMask, 2, 2);
   idxFlagPosition = idxFlagPosition + 2;     
   // Añadimos el 'DescriptionPalette'.
   buffer.position(idxFlagPosition);
   buffer.put((byte) paletteParam.getDescriptionPalette().getBytes().length);
   idxFlagPosition = idxFlagPosition + 1;
   buffer.position(idxFlagPosition);
   buffer.put(paletteParam.getDescriptionPalette().getBytes());
   idxFlagPosition = idxFlagPosition + paletteParam.getDescriptionPalette().getBytes().length;
   // Añadimos el 'NumberColorsPalette'.
   buffer.position(idxFlagPosition);
   buffer.put((byte) paletteParam.getNumberColorsPalette());
   idxFlagPosition = idxFlagPosition + 1;
   // Añadimos el 'TransparentColorPalette'.
   buffer.position(idxFlagPosition);
   if(paletteParam.getTransparentColorPalette() == 0) {
    buffer.put(new byte[] {(byte) 0x00, (byte) 0x00});
    idxFlagPosition = idxFlagPosition + 2;
   } else {
    buffer.put((byte) 0xFF);
    idxFlagPosition = idxFlagPosition + 1;
    buffer.position(idxFlagPosition); 
    buffer.put((byte) paletteParam.getTransparentColorPalette());
    idxFlagPosition = idxFlagPosition + 1;
   }
   // Añadimos el 'ColorsPalette'.
   for(int i = 0; i < paletteParam.getColorsPaletteSize(); i++) {
    buffer.position(idxFlagPosition);
    byte[] colorsPaletteMask = new byte[3];
    colorsPaletteMask[0] = (byte) paletteParam.getColorsPaletteElement(i).getRed();
    colorsPaletteMask[1] = (byte) paletteParam.getColorsPaletteElement(i).getGreen();
    colorsPaletteMask[2] = (byte) paletteParam.getColorsPaletteElement(i).getBlue();
    buffer.put(colorsPaletteMask);
    idxFlagPosition = idxFlagPosition + 3;
   }
   return idxFileContent;
  }

  /**
    * Transforma un buffer binario de tipo `idx` en un objeto de tipo
    * PaletteFileDTO.
    *
    * @param idxParam
    * @return
    */  
  public static PaletteFileDTO transformPaletteIdxToPaletteFile(
         final byte[] idxParam) {
   PaletteFileDTO palette = new PaletteFileDTO();
   int idxFlagPosition = 0;
   // Obtenemos el 'IdPalette'.
   palette.setIdPalette(
    IdxFileUtils.getUnsignedByte(
     new byte[] {idxParam[idxFlagPosition], idxParam[idxFlagPosition + 1]}
    )
   );   
   idxFlagPosition = idxFlagPosition + 2;   
   // Obtenemos el 'DescriptionPalette'.
   int idxDescriptionPaletteLength = IdxFileUtils.getUnsignedByte(idxParam[idxFlagPosition]);
   idxFlagPosition = idxFlagPosition + 1;
   byte[] idxDescriptionPalette = new byte[idxDescriptionPaletteLength];
   for(int i = 0; i < idxDescriptionPaletteLength; i++) {
    idxDescriptionPalette[i] = idxParam[i + idxFlagPosition];
   }   
   palette.setDescriptionPalette(new String(idxDescriptionPalette, Charset.forName("UTF-8")));
   idxFlagPosition = idxFlagPosition + idxDescriptionPaletteLength;   
   // Obtenemos el 'NumberColorsPalette'.
   int idxColorsPalette = IdxFileUtils.getUnsignedByte(idxParam[idxFlagPosition]);
   palette.setNumberColorsPalette(idxColorsPalette);
   idxFlagPosition = idxFlagPosition + 1;
   // Obtenemos el 'TransparentColorPalette'.
   int idxTransparentColorPaletteFlag = IdxFileUtils.getUnsignedByte(idxParam[idxFlagPosition]);
   idxFlagPosition = idxFlagPosition + 1;
   if(idxTransparentColorPaletteFlag == 0) {
    palette.setTransparentColorPalette(0);
   } else {
    int idxTransparentColorPalette = IdxFileUtils.getUnsignedByte(idxParam[idxFlagPosition]);
    palette.setTransparentColorPalette(idxTransparentColorPalette);     
   }
   idxFlagPosition = idxFlagPosition + 1;   
   // Procesamos la lista de colores.
   for(int i = 0; i < palette.getNumberColorsPalette(); i++) {
    int idxRedComponent = IdxFileUtils.getUnsignedByte(idxParam[idxFlagPosition]);
    idxFlagPosition = idxFlagPosition + 1;
    int idxGreenComponent = IdxFileUtils.getUnsignedByte(idxParam[idxFlagPosition]);
    idxFlagPosition = idxFlagPosition + 1;
    int idxBlueComponent = IdxFileUtils.getUnsignedByte(idxParam[idxFlagPosition]);
    idxFlagPosition = idxFlagPosition + 1;
    palette.addColorsPaletteElement(new Color(idxRedComponent, idxGreenComponent, idxBlueComponent));
   }
   return palette;
  }  
 }
