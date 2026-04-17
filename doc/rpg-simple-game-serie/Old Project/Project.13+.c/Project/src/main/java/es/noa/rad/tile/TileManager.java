package es.noa.rad.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import es.noa.rad.GamePanel;
import es.noa.rad.util.GraphicsUtil;

public class TileManager {
  GamePanel gamePanel;
  public Tile[] tile;
  public int[][] mapTileNum;

  public TileManager(
         final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  
    this.tile = new Tile[50];
    this.mapTileNum = new int[this.gamePanel.maxWorldCol][this.gamePanel.maxWorldRow];
  
    this.getTileImage();
    this.loadMap("/assets/map/worldV2.txt");
  }
  
  public final void getTileImage() {
    /* Placeholder Start */
    this.setup(0, "grass00", false);
    this.setup(1, "grass00", false);
    this.setup(2, "grass00", false);
    this.setup(3, "grass00", false);
    this.setup(4, "grass00", false);
    this.setup(5, "grass00", false);
    this.setup(6, "grass00", false);
    this.setup(7, "grass00", false);
    this.setup(8, "grass00", false);
    this.setup(9, "grass00", false);
    /* Placeholder End */
    this.setup(10, "grass00", false);
    this.setup(11, "grass01", false);
    this.setup(12, "water00", false);
    this.setup(13, "water01", true);
    this.setup(14, "water02", true);
    this.setup(15, "water03", true);
    this.setup(16, "water04", true);
    this.setup(17, "water05", true);
    this.setup(18, "water06", true);
    this.setup(19, "water07", true);
    this.setup(20, "water08", true);
    this.setup(21, "water09", true);
    this.setup(22, "water10", true);
    this.setup(23, "water11", true);
    this.setup(24, "water12", true);
    this.setup(25, "water13", true);
    this.setup(26, "road00", false);
    this.setup(27, "road01", false);
    this.setup(28, "road02", false);
    this.setup(29, "road03", false);
    this.setup(30, "road04", false);
    this.setup(31, "road05", false);
    this.setup(32, "road06", false);
    this.setup(33, "road07", false);
    this.setup(34, "road08", false);
    this.setup(35, "road09", false);
    this.setup(36, "road10", false);
    this.setup(37, "road11", false);
    this.setup(38, "road12", false);
    this.setup(39, "earth", false);
    this.setup(40, "wall", true);
    this.setup(41, "tree", true);
  }

  public final void setup(
         final int index,
         final String imageName,
         final boolean collision) {
    final GraphicsUtil graphicsUtil = new GraphicsUtil();

     try {
       this.tile[index] = new Tile();
       (this.tile[index]).image
         = graphicsUtil.scaleImage(
            ImageIO.read(getClass().getResourceAsStream("/assets/map/tiles/" + imageName + ".png")),
            this.gamePanel.tileSize,
            this.gamePanel.tileSize);
       (this.tile[index]).collision = collision;
    } catch (IOException iOException) {
      iOException.printStackTrace();
    }
  }

  public final void loadMap(
         final String filePath) {
    try {
      final InputStream inputStream
        = this.getClass().getResourceAsStream(filePath);
      final BufferedReader bufferedReader
        = new BufferedReader(new InputStreamReader(inputStream));
    
      int col = 0;
      int row = 0;

      while (col < this.gamePanel.maxWorldCol && row < this.gamePanel.maxWorldRow) {
        String line = bufferedReader.readLine();
        while (col < this.gamePanel.maxWorldCol) {
          String[] numbers = line.split(" ");
          int num = Integer.parseInt(numbers[col]);
          this.mapTileNum[col][row] = num;
          col++;
        } 
        if (col == this.gamePanel.maxWorldCol) {
          col = 0;
          row++;
        } 
      }
      bufferedReader.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }
  
  public final void draw(
         final Graphics2D graphics2D) {      
    int worldCol = 0;
    int worldRow = 0;

    while(worldCol < this.gamePanel.maxWorldCol && worldRow < this.gamePanel.maxWorldRow) {
      
      int tileNum = this.mapTileNum[worldCol][worldRow];
      
      int worldX = (worldCol * this.gamePanel.tileSize);
      int worldY = (worldRow * this.gamePanel.tileSize);
      int screenX = (worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX);
      int screenY = (worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY);
      
      /* Stop moving the camera at the edge. */
      if(this.gamePanel.player.screenX > this.gamePanel.player.worldX) {
        screenX = worldX; 
      }
      if(this.gamePanel.player.screenY > this.gamePanel.player.worldY) {
        screenY = worldY; 
      }
      int rightOffSet = (this.gamePanel.screenWidth - this.gamePanel.player.screenX);
      if(rightOffSet > (this.gamePanel.worldWidth - this.gamePanel.player.worldX)) {
        screenX = (this.gamePanel.screenWidth - (this.gamePanel.worldWidth - worldX));  
      }
      int bottomOffSet = (this.gamePanel.screenHeight - this.gamePanel.player.screenY);
      if(bottomOffSet > (this.gamePanel.worldHeight - this.gamePanel.player.worldY)) {
        screenY = (this.gamePanel.screenHeight - (this.gamePanel.worldHeight - worldY));  
      }

      if ((worldX + this.gamePanel.tileSize) > (this.gamePanel.player.worldX - this.gamePanel.player.screenX)
       && (worldX - this.gamePanel.tileSize) < (this.gamePanel.player.worldX + this.gamePanel.player.screenX)
       && (worldY + this.gamePanel.tileSize) > (this.gamePanel.player.worldY - this.gamePanel.player.screenY)
       && (worldY - this.gamePanel.tileSize) < (this.gamePanel.player.worldY + this.gamePanel.player.screenY)) {      
        graphics2D.drawImage((this.tile[tileNum]).image, screenX, screenY, null);
      } else if((this.gamePanel.player.screenX > this.gamePanel.player.worldX)
             || (this.gamePanel.player.screenY > this.gamePanel.player.worldY)
             || (rightOffSet > (this.gamePanel.worldWidth - this.gamePanel.player.worldX))
             || (bottomOffSet > (this.gamePanel.worldHeight - this.gamePanel.player.worldY))) {
        graphics2D.drawImage((this.tile[tileNum]).image, screenX, screenY, null);
      }

      worldCol++;

      if(worldCol == this.gamePanel.maxWorldCol) {
        worldCol = 0;
        worldRow++;
      }
    }

  }

}
