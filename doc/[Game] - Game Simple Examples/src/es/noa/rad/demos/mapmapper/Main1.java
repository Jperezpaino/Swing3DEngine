package es.noa.rad.demos.mapmapper;

 import
  java.awt.Color;
 import
  java.awt.Graphics2D;
 import
  java.awt.image.BufferedImage;
 import
  java.io.File;
 import
  java.io.IOException;
 import
  java.util.ArrayList;
import java.util.TreeMap;

import
  javax.imageio.ImageIO;

 public class Main1 {
  private int pointXStart;
  private int pointYStart;
  private int tileWidth;
  private int tileHeight;
  private String fileLoad;
  private String dirLoad;
  
  public Main1(
   final int pointXStartParam,
   final int pointYStartParam,
   final int tileWidthParam,
   final int tileHeightParam,
   final String dirLoadParam,   
   final String fileLoadParam) {
   super();
   this.pointXStart = pointXStartParam;
   this.pointYStart = pointYStartParam;
   this.tileWidth = tileWidthParam;
   this.tileHeight = tileHeightParam;
   this.fileLoad = fileLoadParam;
   this.dirLoad = dirLoadParam;
  }

  public final int getPointXStart() {
   return this.pointXStart;
  }

  public final void setPointXStart(final int pointXStartParam) {
   this.pointXStart = pointXStartParam;
  }

  public final int getPointYStart() {
   return this.pointYStart;
  }

  public final void setPointYStart(final int pointYStartParam) {
   this.pointYStart = pointYStartParam;
  }

  public final int getTileWidth() {
   return this.tileWidth;
  }

  public final void setTileWidth(final int tileWidthParam) {
   this.tileWidth = tileWidthParam;
  }

  public final int getTileHeight() {
   return this.tileHeight;
  }

  public final void setTileHeight(final int tileHeightParam) {
   this.tileHeight = tileHeightParam;
  }

  public final String getFileLoad() {
   return this.fileLoad;
  }

  public final void setFileLoad(final String fileLoadParam) {
   this.fileLoad = fileLoadParam;
  }
  
  public final String getDirLoad() {
   return this.dirLoad;
  }

  public final void setDirLoad(final String dirLoadParam) {
   this.dirLoad = dirLoadParam;
  }
  
  public final void splitMap() {
   BufferedImage image;
   ArrayList<String> mapHashMapList = new ArrayList<String>();
   TreeMap<String,String> tileHashMapList = new TreeMap<String,String>();
   try {
    // Cargamos la imagen
    image = ImageIO.read(new File(this.dirLoad + this.fileLoad));
    // Comenzamos a procesarla
    for(int i = this.pointYStart; i < image.getHeight(); i = i + this.tileHeight) {
     for(int j = this.pointXStart; j < image.getWidth(); j = j + this.tileWidth) {
      BufferedImage imageTile = new BufferedImage(this.tileWidth, this.tileHeight, BufferedImage.TYPE_INT_ARGB);
      Graphics2D graphics2D = imageTile.createGraphics();
      graphics2D.drawImage(image, 0, 0, this.tileWidth, this.tileHeight, j, i, j + this.tileWidth, i + this.tileHeight, null);
      String hash = this.getImagehash(imageTile);
      mapHashMapList.add(hash);
     }
    }    
   } catch (IOException exception) {
    exception.printStackTrace();
   }
   // En este punto tenemos un HashMap con las tiles que contiene el mapa.
   // Cargamos la lista con las tiles que vamos a utilizar.
   File tileFileData = new File(this.dirLoad + "\\tiles\\");
   if(tileFileData.exists()) {
    // Buscamos una a una las tiles existentes.
    
   }
   
   
   
   int tilePosition = 0;
   for (int i = 0; i <tileHashMapList.size(); i++) {
    String[] tile = tileHashMapList.get(i).split("");
    int position = 0;
    BufferedImage imageTile = new BufferedImage(this.tileWidth, this.tileHeight, BufferedImage.TYPE_INT_ARGB);    
    for(int j = 0; j < this.tileHeight; j++) {
     for(int k = 0; k < this.tileWidth; k++) {
      if(tile[position].equals("0")) {
       imageTile.setRGB(k, j, Color.WHITE.getRGB());
      } else if(tile[position].equals("1")) {
       imageTile.setRGB(k, j, Color.LIGHT_GRAY.getRGB());       
      } else if(tile[position].equals("2")) {
       imageTile.setRGB(k, j, Color.GRAY.getRGB());
      } else if(tile[position].equals("3")) {
       imageTile.setRGB(k, j, Color.BLACK.getRGB());
      }
      position++;
     }
    }
    System.out.println(this.imageToStringMap(imageTile));
    
    // Salvamos la imagen que contiene la tile.      
    File saveFile = new File(this.dirLoad + "\\tiles\\");
    if(!saveFile.exists()) {
     saveFile.mkdirs();
    }
    saveFile = new File(this.dirLoad + "\\tiles\\tile_" + tilePosition + ".png");
    tilePosition++;
    try {
     ImageIO.write(imageTile, "PNG", saveFile);
    } catch (IOException exception) {
     exception.printStackTrace();
    }
   }      
  }
  
  
  
  private final String getImagehash(final BufferedImage imagenParam) {
   String result = "";
   for(int i = 0; i < imagenParam.getHeight(); i++) {
    for(int j = 0; j < imagenParam.getWidth(); j++) {
     Color color = new Color(imagenParam.getRGB(j, i));
     // Comprobamos el color Blanco
     Color colorWhite = new Color(255, 0, 0);
     if(color.equals(colorWhite)) {
      result = result + "0";
     }
     // Comprobamos el color Gris Claro
     Color colorGrey = new Color(0, 255, 0);
     if(color.equals(colorGrey)) {
      result = result + "1";
     }
     // Comprobamos el color Gris Oscuro
     Color colorDarkGrey = new Color(71, 71, 71);
     if(color.equals(colorDarkGrey)) {
      result = result + "2";
     }
     // Comprobamos el color negro
     Color colorBlack = new Color(9, 9, 9);
     if(color.equals(colorBlack)) {
      result = result + "3";
     }  
    }
   }
   return result;
  } 


  public String imageToStringMap(final BufferedImage imagenParam) {
   String result = " { ";
   int[] starByte = new int[8];
   int[] endByte = new int[8];   
   
   for(int j = 0; j < this.tileHeight; j++) {
    int bytePosition = 0;
    for(int k = 0; k < this.tileWidth; k++) {     
     int color = imagenParam.getRGB(k, j);
     if(color == Color.WHITE.getRGB()) {
      starByte[bytePosition] = 0;
      endByte[bytePosition] = 0;
     } else if(color == Color.LIGHT_GRAY.getRGB()) {
      starByte[bytePosition] = 1;
      endByte[bytePosition] = 0;
     } else if(color == Color.GRAY.getRGB()) {
      starByte[bytePosition] = 0;
      endByte[bytePosition] = 1;
     } else if(color == Color.BLACK.getRGB()) {
      starByte[bytePosition] = 1;
      endByte[bytePosition] = 1;
     }
     bytePosition++;
    }
    if(j > 0) {
     result += ", ";
    }
    result += (
     "0x"
     + getHexValue(starByte[0] + "" + starByte[1] + "" + starByte[2] + "" + starByte[3])
     + getHexValue(starByte[4] + "" + starByte[5] + "" + starByte[6] + "" + starByte[7])
    );
    result += ", ";
    result += (
     "0x"
     + getHexValue(endByte[0] + "" + endByte[1] + "" + endByte[2] + "" + endByte[3])
     + getHexValue(endByte[4] + "" + endByte[5] + "" + endByte[6] + "" + endByte[7])
    );    
   }
   result += "}";
   return result;
  }

  public BufferedImage stringMapToImagen(final String stringMapParam) {
   BufferedImage result = new BufferedImage(this.tileWidth, this.tileHeight, BufferedImage.TYPE_INT_ARGB);  
   
   return result;
  }  
  
  private String getHexValue(final String StringValue) {
   if(StringValue.equals("0000")) {
    return "0";
   } else if(StringValue.equals("0001")) {
    return "1";
   } else if(StringValue.equals("0010")) {
    return "2";
   } else if(StringValue.equals("0011")) {
    return "3";
   } else if(StringValue.equals("0100")) {
    return "4";
   } else if(StringValue.equals("0101")) {
    return "5";
   } else if(StringValue.equals("0110")) {
    return "6";
   } else if(StringValue.equals("0111")) {
    return "7";
   } else if(StringValue.equals("1000")) {
    return "8";
   } else if(StringValue.equals("1001")) {
    return "9";
   } else if(StringValue.equals("1010")) {
    return "A";
   } else if(StringValue.equals("1011")) {
    return "B";
   } else if(StringValue.equals("1100")) {
    return "C";
   } else if(StringValue.equals("1101")) {
    return "D";
   } else if(StringValue.equals("1110")) {
    return "E";
   } else if(StringValue.equals("1111")) {
    return "F";    
   }
   return null;
  }
  
  public static void main(String[] args) {
   // Cargamos la Imagen que contiene el mapa.
   Main1 app = new Main1(0, 0, 8, 8, "C:\\gbdk\\dev\\tilesplit\\", "tloz_la.bmp");
   app.splitMap();
  }
 }
 
/**
 * This class demonstrates how to load an Image from an external file
 */
/*public class SplitImage extends Component {
           
    BufferedImage img;
 
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
 
  public SplitImage() {
   try {
    img = ImageIO.read(new File("C:\\Users\\JPEREZ\\Desktop\\Iconos\\Data Icons.png"));
    int pointXStart = 5; //5;
    int pointYStart = 5; //5;
    int splitWidth = 25;
    int[] displacementWidth = {88,88,89,88,88,89,88,88,88,88,88,88,88};    
    int splitHeight = 25;
    int[] displacementHeight = {12,12,13,12,12,13,12,12,13,12,
                                12,13,11,13,12,12,13,12,12,13,
                                12,12,13,12,12,13,7,12,13,12,
                                12,13,12,12,13,12,12,13,12,12,
                                13,12,14,12,13,12,12,12,13,12,
                                13,12,12,13,12,12,12,12,13,12,
                                9,12,1,12,13,10,12,13,12,12,
                                13,12,12,13,12,10,12,13,12,12,
                                13,12,12,13,12,12,12};
    int position = 1123;    
    int yPostion = 0;
    for(int i = pointYStart; i <img.getHeight(); i = i + splitHeight + displacementHeight[yPostion]) {
     int xPostion = 0;
     for(int j = pointXStart; j < (img.getWidth() - (displacementWidth[0]/2)); j = j + splitWidth + displacementWidth[xPostion]) {
      BufferedImage off_Image = new BufferedImage(splitWidth, splitHeight, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = off_Image.createGraphics();
      g2.drawImage(img,
       0, 0, splitWidth, splitHeight,     /* dst rectangle */
       //*j, i, j + splitWidth, i + splitHeight, /* src area of image */
       /*null);
      File saveFile = new File("C:\\Users\\JPEREZ\\Desktop\\Iconos\\Icons\\savedimage_" + position + ".png");
      position++;
      try {
       ImageIO.write(off_Image, "PNG", saveFile);
      } catch (IOException ex) {
       ex.printStackTrace();
      }
      xPostion++;
     }
     yPostion++;
    }
    
    int cellWidth = 8;
    int cellHeight = 57;
    int margin = 5;
    int width = (margin * 2) + (cellWidth * (splitWidth + displacementWidth[0])) + pointXStart;
    int Height = (margin * 2) + (cellHeight * (splitHeight + displacementHeight[0])) + pointYStart;
    BufferedImage final_Image = new BufferedImage(width, Height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = final_Image.createGraphics();
    g2.setColor(Color.WHITE);
    g2.fillRect(0, 0, final_Image.getWidth(), final_Image.getHeight());    
    position = 1123;
    yPostion = 0;
    for(int i = pointYStart; i <img.getHeight(); i = i + splitHeight + displacementHeight[yPostion]) {
    int xPostion = 0;     
     for(int j = pointXStart; j <(img.getWidth() - (displacementWidth[0]/2)); j = j + splitWidth + displacementWidth[xPostion]) {
      BufferedImage tile = ImageIO.read(new File("C:\\Users\\JPEREZ\\Desktop\\Iconos\\Icons\\savedimage_" + position + ".png"));
      position++;
      g2.drawImage(tile, j, i, null);     
      xPostion++;
     }
     yPostion++;
    }
    File saveFile = new File("C:\\Users\\JPEREZ\\Desktop\\Iconos\\Icons\\ClearIcons.png");
    try {
     ImageIO.write(final_Image, "PNG", saveFile);
    } catch (IOException ex) {
     ex.printStackTrace();     
    }
    img = final_Image;
   } catch (IOException e) {
    e.printStackTrace();
   }
   
   
   
  }
 
    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
 
    public static void main(String[] args) {
 
        JFrame f = new JFrame("Load Image Sample");
             
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });
 
        f.add(new SplitImage());
        f.pack();
        f.setVisible(true);
    }
}*/