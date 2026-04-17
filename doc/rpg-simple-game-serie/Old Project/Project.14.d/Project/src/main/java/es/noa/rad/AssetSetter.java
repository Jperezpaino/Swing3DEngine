package es.noa.rad;

import es.noa.rad.entity.npc.NpcOldMan;

public class AssetSetter {

  private GamePanel gamePanel;

  public AssetSetter(
         final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }

  public final void setObject() {

  }

  public final void setNpc() {
    this.gamePanel.npc[0] = new NpcOldMan(this.gamePanel);
    this.gamePanel.npc[0].worldX = this.gamePanel.tileSize * 21;
    this.gamePanel.npc[0].worldY = this.gamePanel.tileSize * 21;
  }
  
  public final GamePanel getGamePanel() {
    return this.gamePanel;
  }

  public final void setGamePanel(
         final GamePanel gamePanel) {
    this.gamePanel = gamePanel;
  }
   
}
