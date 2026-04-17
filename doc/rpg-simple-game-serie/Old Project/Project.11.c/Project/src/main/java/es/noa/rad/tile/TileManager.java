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
  
    this.tile = new Tile[10];
    this.mapTileNum = new int[this.gamePanel.maxWorldCol][this.gamePanel.maxWorldRow];
  
    this.getTileImage();
    this.loadMap("/assets/map/world01.txt");
  }
  
  public final void getTileImage() {
    this.setup(0, "grass", false);
    this.setup(1, "wall", true);
    this.setup(2, "water", true);
    this.setup(3, "earth", false);
    this.setup(4, "tree", true);
    this.setup(5, "sand", false);
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
      
      if ((worldX + this.gamePanel.tileSize) > (this.gamePanel.player.worldX - this.gamePanel.player.screenX)
       && (worldX - this.gamePanel.tileSize) < (this.gamePanel.player.worldX + this.gamePanel.player.screenX)
       && (worldY + this.gamePanel.tileSize) > (this.gamePanel.player.worldY - this.gamePanel.player.screenY)
       && (worldY - this.gamePanel.tileSize) < (this.gamePanel.player.worldY + this.gamePanel.player.screenY)) {
      
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
