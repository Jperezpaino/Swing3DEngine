package files.utils;

 import
  java.io.ByteArrayInputStream;
 import
  java.io.File;
 import
  java.io.FileInputStream;
 import
  java.io.FileNotFoundException;
 import
  java.io.FileOutputStream;
 import
  java.io.IOException;
 import
  java.io.InputStream;
 import
  java.io.OutputStream;
 import
  java.util.ArrayList;
 import
  java.util.List;

 public final class FileUtils {
   
  private FileUtils() {   
  }

  /**
    * Metodo que se encarga de eliminar un fichero del disco segun la ruta
    * recibida.
    *
    * @param routeParam String
    * @return boolean
    */
  public static boolean deleteFile(
         final String routeParam) {
   File file = new File(routeParam);
   return FileUtils.deleteFile(file);
  }

  /**
    * Metodo que se encarga de eliminar un fichero del disco segun un objeto
    * File recibido.
    *
    * @param fileParam File
    * @return boolean
    */
  public static boolean deleteFile(
         final File fileParam) {
   if (fileParam.delete()) {
    return fileParam.exists();
   } else {
    return false;
   }
  }

  public static List<File> loadDirectory(
         final String directoryParam,
         final boolean onlyFilesParam,
         final boolean useRecursivelyParam) throws Exception {
   File file = new File(directoryParam);
   return FileUtils.loadDirectory(file, onlyFilesParam, useRecursivelyParam);
  }

  public static List<File> loadDirectory(
         final File directoryParam,
         final boolean onlyFilesParam,
         final boolean useRecursivelyParam) throws Exception {
   ArrayList<File> resultListFiles = new ArrayList<File>();
   if(directoryParam.isDirectory()) {
    if(!directoryParam.exists()) {
     // El directorio no existe.
     throw new Exception();
    } else {
     File[] directoryListFiles = directoryParam.listFiles();
     for (int i = 0; i < directoryListFiles.length; i++) {
      if (directoryListFiles[i].isDirectory()){
       if(!onlyFilesParam) {
        resultListFiles.add(directoryListFiles[i]);
       }
       if(useRecursivelyParam) {
        resultListFiles.addAll(
         loadDirectory(directoryListFiles[i], onlyFilesParam, useRecursivelyParam));
       }
      } else {
       resultListFiles.add(directoryListFiles[i]);
      }
     }
    }
   } else {
    // La ruta recibida en un fichero.
    throw new Exception();
   }
   return resultListFiles;
  }   
  

  /**
    * Metodo que se encarga de cargar el contenido de un fichero del disco
    * segun al ruta recibida.
    *
    * @param routeParam String
    * @return byte[]
    * @throws Exception as error
    */
  public static byte[] loadFile(
         final String routeParam) throws Exception {
   File file = new File(routeParam);
   byte[] fileContent = null;
   try {
    fileContent = FileUtils.loadFile(file);
   } catch (Exception exception) {
    exception.printStackTrace();
    throw new Exception();
   }  
   return fileContent;
  }
 
  /**
    * Metodo que se encarga de cargar el contenido de un fichero del disco
    * segun un objeto File recibido.
    *
    * @param fileParam File
    * @return byte[]
    * @throws Exception as error
    */
  public static byte[] loadFile(
         final File fileParam) throws Exception {
   InputStream is;
   try {
    is = new FileInputStream(fileParam);
   } catch (FileNotFoundException fileNotFoundException) {
    fileNotFoundException.printStackTrace();
    throw new Exception();
    // Fichero no encontrado.
   }
   // Obtenemos el tamaño del fichero.
   long length = fileParam.length();
   if (length > Integer.MAX_VALUE) {
    try {
     is.close();
    } catch (IOException inputStreamException) {
     inputStreamException.printStackTrace();
     throw new Exception();
     // No se ha podido cerrar el stream de lectura.
    }    
    throw new Exception();
    // Tamaño maximo permitido.
   }
   // Creamos el Array de Bytes para almacenar el contenido.
   byte[] fileContent = new byte[(int) length];
   // Leemos los Bytes.
   int offset = 0;
   int numRead = 0;
   try {
    numRead = is.read(fileContent, offset, fileContent.length - offset);
   } catch (IOException iOException) {
    iOException.printStackTrace();
    try {
     is.close();
    } catch (IOException inputStreamException) {
     inputStreamException.printStackTrace();     
     throw new Exception();
     // No se ha podido cerrar el stream de lectura.
    }       
    throw new Exception();
    // Error de lectura del fichero.    
   }
   while ((offset < fileContent.length) && (numRead >= 0)) {
    offset += numRead;
   }
   if (offset < fileContent.length) {
    try {
     is.close();
    } catch (IOException inputStreamException) {
     inputStreamException.printStackTrace();     
     throw new Exception();
     // No se ha podido cerrar el stream de lectura.
    }    
    throw new Exception();
    // No se han podido leer todo el contenido del fichero.
   }
   // Cerramos el stream de lectura.
   try {
    is.close();
   } catch (IOException inputStreamException) {
    inputStreamException.printStackTrace();    
    throw new Exception();
    // No se ha podido cerrar el stream de lectura.
   }  
   return fileContent;
  }


  
  
  
  

  /**
    * Metodo que se encarga de crear toda la estructura de directorios en disco a
    * partir de la ruta recibida.
    *
    * @param routeParam String
    * @return boolean
    * @throws Exception as error
    */
  public static boolean saveDirectory(
         final String routeParam) throws Exception {
   File file = new File(routeParam); 
   return FileUtils.saveDirectory(file);
  }
  
  /**
    * Metodo que se encarga de crear toda la estructura de directorios en disco a
    * partir del objeto File recibido.
    *
    * @param fileParam File
    * @return boolean
    * @throws Exception as error
    */
  public static boolean saveDirectory(
         final File fileParam) throws Exception {
   String directoryRoute = fileParam.getAbsolutePath();
   String[] directoryElements = directoryRoute.split(File.separator);
   String axiliarRoute = "";
    for (int i = 0; i < directoryElements.length; i++) {
     axiliarRoute = axiliarRoute + directoryElements[i] + File.separator;
     File file = new File(axiliarRoute); 
     if(!file.exists()) {
      file.mkdir();
      if(!file.exists()) {
       throw new Exception();
       // No se ha podido crear el directorio destino.
      }
     }
    }
    return true;   
   }

  /**
    * Metodo que se encarga de almacenar el contenido de un byte[] en un fichero
    * de disco segun la ruta recibida, en caso de existir sustituira el antiguo
    * y generara uno nuevo.
    *
    * @param routeParam String
    * @param fileContentParam byte[]
    * @return boolean
    * @throws Exception as error
    */
  public static boolean saveFile(
         final String routeParam,
         final byte[] fileContentParam) throws Exception {
   File file = new File(routeParam);
   try {
    return FileUtils.saveFile(file, fileContentParam);
   } catch (Exception exception) {
    exception.printStackTrace();
    throw new Exception();
   }
  }

  /**
    * Metodo que se encarga de almacenar el contenido de un byte[] en un fichero
    * de disco segun el objeto File recibido, en caso de existir sustituira el
    * antiguo y generara uno nuevo.
    *
    * @param fileParam File
    * @param fileContentParam byte[]
    * @return boolean
    * @throws Exception as error
    */
  public static boolean saveFile(
         final File fileParam,
         final byte[] fileContentParam) throws Exception {
   try {
    fileParam.createNewFile();
    FileInputStream fileInputStream = new FileInputStream(fileParam);
    ByteArrayInputStream byteArrayInputStream = 
     new ByteArrayInputStream(fileContentParam);
    OutputStream outputStream = new FileOutputStream(fileParam);
    int numWrite;
    while ((numWrite = byteArrayInputStream.read()) != -1) {
     outputStream.write(numWrite);
    }
    fileInputStream.close();
    outputStream.close();
   } catch (FileNotFoundException fileNotFoundException) {
    fileNotFoundException.printStackTrace();
    throw new Exception();
    // Fichero no encontrado.    
   } catch (IOException iOException) {
    iOException.printStackTrace();
    throw new Exception();
    // Error de lectura del fichero.    
   }     
   return true;
  }
    
 }
