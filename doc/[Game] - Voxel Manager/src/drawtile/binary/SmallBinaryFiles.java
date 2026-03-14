package drawtile.binary;

 import java.awt.Color;
import
  java.io.BufferedInputStream;
 import
  java.io.BufferedOutputStream;
 import
  java.io.ByteArrayOutputStream;
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


 public class SmallBinaryFiles {
  final static String FILE_NAME = "C:/Noa/Workspace/[Game] - Rol Card Game/res/graphics/palettes/Tabla.act";
  final static String OUTPUT_FILE_NAME = "C:\\Noa\\Workspace\\[Game] - Rol Card Game\\res\\Color palettes\\example_s.act";
  
  /** Run the example. */
  public static void main(String... aArgs) {
   SmallBinaryFiles test = new SmallBinaryFiles();
    //read in the bytes
    byte[] fileContents = test.readBinaryFile(FILE_NAME);
    // Procesamos la paleta
    int bytePosition = 0;
    for(int i = 0; i <= 255; i++) {
     int redComponent = test.getUnsignedByte(fileContents[bytePosition]);
     bytePosition++;
     int greenComponent = test.getUnsignedByte(fileContents[bytePosition]);
     bytePosition++;
     int blueComponent = test.getUnsignedByte(fileContents[bytePosition]);
     bytePosition++;
     System.out.println("Color " + (i + 1) + " " + new Color(redComponent, greenComponent, blueComponent));
    }
    int numColores = 0;
    if(test.getUnsignedByte(fileContents[bytePosition]) == 1) {
     numColores = 256;
     bytePosition = bytePosition + 2;
    } else {
     bytePosition++;
     numColores = test.getUnsignedByte(fileContents[bytePosition]);
     bytePosition++;
    }
    System.out.println("Numero de colores: " + numColores);
    // ignoramos 255, 255 es para la transparencia
    System.out.println(test.getUnsignedByte(fileContents[bytePosition]));
    bytePosition++;
    System.out.println(test.getUnsignedByte(fileContents[bytePosition]));
    
    
    //test.readAlternateImpl(INPUT_FILE_NAME);
    //write it back out to a different file name
    //test.write(fileContents, OUTPUT_FILE_NAME);
  }
   
  /**
    * Lee el fichero binario definido a traves del parametro y devuelve
    * un array de bytes con el contenido del mismo. 
    */   
  public final byte[] readBinaryFile(
   final String inputFileNameParam) {
   log("Reading in binary file named : " + inputFileNameParam);
   // TODO #si el fichero no existe.
   File binaryFile = new File(inputFileNameParam); 
   log("File size: " + binaryFile.length());
   byte[] fileContent = new byte[(int)binaryFile.length()];
   try {
    InputStream inputData = null;
    try {
     int totalBytesRead = 0;
     inputData = new BufferedInputStream(new FileInputStream(binaryFile));
     while(totalBytesRead < fileContent.length){
      int bytesRemaining = fileContent.length - totalBytesRead;
      int bytesRead = inputData.read(fileContent, totalBytesRead, bytesRemaining); 
      if (bytesRead > 0){
       totalBytesRead = totalBytesRead + bytesRead;
      }
     }
     log("Num bytes read: " + totalBytesRead);
    } finally {
     log("Closing input stream.");
     inputData.close();
    }
   } catch (FileNotFoundException ex) {
    log("File not found.");
   } catch (IOException ex) {
    log(ex);
   }
   return fileContent;
  }
  
  
  
  
  /**
   Write a byte array to the given file. 
   Writing binary data is significantly simpler than reading it. 
  */
  void write(byte[] aInput, String aOutputFileName){
    log("Writing binary file...");
    try {
      OutputStream output = null;
      try {
        output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
        output.write(aInput);
      }
      finally {
        output.close();
      }
    }
    catch(FileNotFoundException ex){
      log("File not found.");
    }
    catch(IOException ex){
      log(ex);
    }
  }
  
  /** Read the given binary file, and return its contents as a byte array.*/ 
  byte[] readAlternateImpl(String inputFileNameParam){
    log("Reading in binary file named : " + inputFileNameParam);
    File binaryFile = new File(inputFileNameParam);
    log("File size: " + binaryFile.length());
    byte[] fileContent = null;
    try {
      InputStream inputData =  new BufferedInputStream(new FileInputStream(binaryFile));
      fileContent = readAndClose(inputData);
    }
    catch (FileNotFoundException ex){
      log(ex);
    }
    return fileContent;
  }
  
  /**
   Read an input stream, and return it as a byte array.  
   Sometimes the source of bytes is an input stream instead of a file. 
   This implementation closes aInput after it's read.
  */
  byte[] readAndClose(InputStream aInput){
    //carries the data from input to output :    
    byte[] bucket = new byte[32*1024]; 
    ByteArrayOutputStream result = null; 
    try  {
      try {
        //Use buffering? No. Buffering avoids costly access to disk or network;
        //buffering to an in-memory stream makes no sense.
        result = new ByteArrayOutputStream(bucket.length);
        int bytesRead = 0;
        while(bytesRead != -1){
          //aInput.read() returns -1, 0, or more :
          bytesRead = aInput.read(bucket);
          if(bytesRead > 0){
            result.write(bucket, 0, bytesRead);
          }
        }
      }
      finally {
        aInput.close();
        //result.close(); this is a no-operation for ByteArrayOutputStream
      }
    }
    catch (IOException ex){
      log(ex);
    }
    return result.toByteArray();
  }
  
  private final void log(Object aThing){
   System.out.println(String.valueOf(aThing));
  } 
  
  public int getUnsignedByte(byte byteValue) {
   return (byteValue & 0xFF);
  }
  
 }  