package es.noa.rad.game.rpgs2d;

import es.noa.rad.game.rpgs2d.entity.Entity;
import es.noa.rad.game.rpgs2d.tile.Tile;

  /**
   *
   */
  public class CollisionChecker {

    /**
     *
     */
    private GamePanel gamePanel;

    /**
     * @param _gamePanel {@code GamePanel}
     */
    public CollisionChecker(
        final GamePanel _gamePanel) {
      this.gamePanel = _gamePanel;
    }

    /**
     * @param _entity {@code _entity}
     */
    public final void checkEntityTileCollision(
        final Entity _entity) {

      final int tileSize = this.gamePanel.getTileSize();

      final int entityLeftWorldX
        = (_entity.getWorldX()
         + ((int) (_entity.getSolidArea().getX()))
          );
      final int entityRightWorldX
        = (_entity.getWorldX()
         + ((int) (_entity.getSolidArea().getX()))
         + ((int) (_entity.getSolidArea().getWidth()))
          );
      final int entityTopWorldY
        = (_entity.getWorldY()
         + ((int) (_entity.getSolidArea().getY()))
          );
      final int entityBottomWorldY
        = (_entity.getWorldY()
         + ((int) (_entity.getSolidArea().getY()))
         + ((int) (_entity.getSolidArea().getHeight()))
          );

      int entityLeftCol = (entityLeftWorldX / tileSize);
      int entityRightCol = (entityRightWorldX / tileSize);
      int entityTopRow = (entityTopWorldY / tileSize);
      int entityBottomRow = (entityBottomWorldY / tileSize);

      final int[][] mapTileData
        = this.gamePanel.getTileManager().getMapTileData();

      final Tile[] tiles
        = this.gamePanel.getTileManager().getTiles();

      switch (_entity.getDirection()) {
        case "up":
          entityTopRow
            = ((entityTopWorldY - _entity.getSpeed()) / tileSize);
          final int tileNum1 = mapTileData[entityLeftCol][entityTopRow];
          final int tileNum2 = mapTileData[entityRightCol][entityTopRow];
          if ((tiles[tileNum1]).isCollisionable()
           || (tiles[tileNum2]).isCollisionable()) {
            _entity.setCollision(true);
          }
        break;
        case "down":
        break;
        case "left":
        break;
        case "right":
        break;
        default:
        break;
      }
    }

  }
