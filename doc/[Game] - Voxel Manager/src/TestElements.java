 import
  files.GameDataDTO;
 import
  files.utils.GameDataUtils;

public class TestElements {
  public static void main(String[] args) {
   /*PaletteFileDTO palette =
    PaletteFileUtils.loadPaletteFile("C:/Noa/Workspace/[Game] - Rol Card Game/res/graphics/palettes/00.palette");
   System.out.println(palette.toString());
   PaletteFileUtils.savePaletteFile("C:/Noa/Workspace/[Game] - Rol Card Game/res/graphics/palettes/01.palette", palette);
   
   byte[] idxPalette = PaletteFileUtils.transformPaletteFileToPaletteIdx(palette);
   PaletteFileDTO paletteGen = PaletteFileUtils.transformPaletteIdxToPaletteFile(idxPalette);
   System.out.println(paletteGen.toString());*/
   
   GameDataDTO gameResources = GameDataUtils.loadGameData("C:/Noa/Workspace/[Game] - Rol Card Game/res/graphics/");
   System.out.println(gameResources.toString());   
  }
 }
