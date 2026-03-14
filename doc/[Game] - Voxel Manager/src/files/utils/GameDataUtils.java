package files.utils;

 import
  java.io.File;
 import
  java.util.List;
import es.noa.rad.util.file.FileUtils;
import
  files.GameDataDTO;
 import
  files.PaletteFileDTO;

public class GameDataUtils {
  
  private GameDataUtils() {}
  
  /**
    * Devuelve un objeto GameDataDTO obtenido a partir de la carga de cada uno de los elementos que forma el 'Game Data Resource', formado por:
    * <ul>
    *  <li> Ficheros de tipo *.palette.
    * </ul>
    * 
    * @param routeParam Corresponde a una ruta de directorio, la cual seguira la estructura marcada para la carga de los recursos. 
    * @return
    */
  public static GameDataDTO loadGameData(
         final String routeParam) {
   GameDataDTO gameData = new GameDataDTO();
   // Cargamos todos los ficheros de tipo '*.palete'.
   try {
    List<File> fileList = FileUtils.loadDirectory(routeParam + "/palettes", true, false);
    // Leemos uno a uno los ficheros recuperados.
    for(int i = 0; i < fileList.size(); i++) {
     PaletteFileDTO palette =
      PaletteFileUtils.loadPaletteFile(fileList.get(i));
     gameData.addPalettesGameDataElement(palette);
    }
   } catch(Exception e) {
    e.printStackTrace();
   }
   
   return gameData;
  }
 }
