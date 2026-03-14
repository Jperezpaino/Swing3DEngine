package hqs;

import es.noa.rad.util.file.FileUtils;

public class Compressdemo {
 public static void main(String[] args) {
  Zip zipuncompressdata;
  Zip zipcompressdata;
  LZSS lzuncompressdata;
  LZSS lzcompressdata;
  try {
   zipuncompressdata = new Zip(FileUtils.loadFileInInputStream("C:/mapesp.jpg"));
   FileUtils.saveFile("C:/mapesp.zip", zipuncompressdata.compress());
   
   zipcompressdata = new Zip(FileUtils.loadFileInInputStream("C:/mapesp.zip"));
   FileUtils.saveFile("C:/mapesp2.jpg", zipcompressdata.uncompress());
   
   /*lzuncompressdata = new LZSS(FileUtils.loadFileInInputStream("C:/mapesp.jpg"));
   FileUtils.saveFile("C:/mapesp.lz", lzuncompressdata.compress());
   
   lzcompressdata = new LZSS(FileUtils.loadFileInInputStream("C:/mapesp.lz"));
   FileUtils.saveFile("C:/mapesp3.jpg", lzcompressdata.uncompress());*/
   
   LZ77 lz = new LZ77();
   lz.compress("C:/mapesp.jpg");
   lz.unCompress("C:/mapesp.jpg");
   
  } catch(Exception e) {
   e.printStackTrace();
  }
  
  
 }
}
