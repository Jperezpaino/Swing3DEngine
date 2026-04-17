package es.noa.rad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

import es.noa.rad.entity.Npc;
import es.noa.rad.entity.Player;
import es.noa.rad.object.SuperObject;
import es.noa.rad.sound.Sound;
import es.noa.rad.tile.TileManager;
import es.noa.rad.userinterface.UserInterface;

public class GamePanel extends JPanel implements Runnable {

  /**
    * 
    */
  private static final long serialVersionUID = 1L;
  
  /* Screen settings. */
  private final int originalTileSize = 16;
  private final int scale = 3;
  
  public final int tileSize = this.originalTileSize * this.scale;
  public final int maxScreenCol = 16;
  public final int maxScreenRow = 12;
  public final int screenWidth = this.tileSize * this.maxScreenCol;
  public final int screenHeight = this.tileSize * this.maxScreenRow;
  
  /* World Settings. */
  public final int maxWorldCol = 50;
  public final int maxWorldRow = 50;

  /* Maximum Frames Per Second. */
  final int fps = 60;
  
  /* System variables. */
  protected TileManager tileManager;
  public KeyHandler keyHandler;
  protected Sound music;
  protected Sound soundEffect;
  public CollisionChecker collisionChecker;
  public AssetSetter assetSetter;
  public UserInterface userInterface;
  protected Thread gameThread;
  
  /* Entity and objects variables. */
  public Player player;
  public SuperObject[] object;
  public Npc[] npc;  
  
  /* Game State. */
  public GameState gameState;
  
  public GamePanel() {
    super();
    this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
        
    this.keyHandler = new KeyHandler(this);
    this.addKeyListener(this.keyHandler);    
    this.setFocusable(true);
    
    this.music = new Sound();
    this.soundEffect = new Sound();
    
    this.collisionChecker = new CollisionChecker(this);
    this.assetSetter = new AssetSetter(this);
    this.userInterface = new UserInterface(this);
    this.tileManager = new TileManager(this);
    this.player = new Player(this, this.keyHandler);
    this.object = new SuperObject[10];
    this.npc = new Npc[10];    
  }
  
  public final void setupGame() {
    /* Set the state of the game. */
    this.gameState = GameState.PLAY_STATE;

    this.assetSetter.setObject();
    this.assetSetter.setNpc();
    
    /* Start music. */
    this.startMusic(0);
    this.playMusic();
  }
  
  public final void startGameThread() {
    this.gameThread = new Thread(this);  
    this.gameThread.start();
  }
  
  public final void stopGameThread() {
    this.gameThread = null;
  }
  
  @Override
  public final void run() {

    double drawInterval = (1000000000/this.fps); /* 0.01666 Seconds. */
    double deltaTime = 0.0D;
    long lastTime = System.nanoTime();
    long timer = 0;
    //long drawCount = 0;

    while(this.gameThread != null) {
      final long currentTime = System.nanoTime();
      deltaTime += ((currentTime - lastTime) / drawInterval);
      timer += (currentTime - lastTime);
      lastTime = currentTime;
    
      if(deltaTime >= 1.0D) {
        /* 1 UPDATE: Update information such as character positions. */
        this.update();

        /* 2 DRAW: Draw the screen with the update information. */
        this.repaint();
        
        deltaTime--;
        //drawCount++;
      }
      
      if(timer >= 1000000000L) {
        /* Frame Per Seconds debug. */
        // System.out.println("FPS: " + drawCount);
        //drawCount = 0;
        timer = 0L;
      }
    }

  }
  
  public final void update() {
    if(this.gameState.equals(GameState.PLAY_STATE)) {
      /* Player update */
      this.player.update();    
    	
      /* Update NPC */
      for (int i = 0; i < this.npc.length; i++) {
        if (this.npc[i] != null) {
          this.npc[i].update();
        }
      } 
    } else if(this.gameState.equals(GameState.PAUSE_STATE)) {
      /* Nothing. */
    }    
  }

  @Override
  public final void paintComponent(
         final Graphics graphics) {
    super.paintComponent(graphics);
    
    Graphics2D graphics2D = (Graphics2D) graphics;
    
    /* Debug drawing time start. */
    long drawStartTime = 0;
    if(this.keyHandler.debugState) {
      drawStartTime = System.nanoTime();
    }
    
    /* Draw Tiles. */    
    this.tileManager.draw(graphics2D);
    
    /* Draw Objects. */
    for (int i = 0; i < this.object.length; i++) {
      if (this.object[i] != null) {
        this.object[i].draw(graphics2D);
      }
    } 
    
    /* Draw NPC */
    for (int i = 0; i < this.npc.length; i++) {
      if (this.npc[i] != null) {
        this.npc[i].draw(graphics2D);
      }
    } 
    
    /* Draw Player. */
    this.player.draw(graphics2D);    
    
    /* Draw User Interface. */
    this.userInterface.draw(graphics2D);
    
    /* Debug drawing time end. */
    if(this.keyHandler.debugState) {
      final long drawEndTime = System.nanoTime();

      /* NANOSECONDS -> milmillonésima parte de un segundo, (10^-9 segundos)*/
      /* MICROSECONDS -> millonésima parte de un segundo, (10^-6 segundos)*/
      /* MILISECONDS -> milésima parte de un segundo, (10^-3 segundos)*/
    
      final long passed
        = TimeUnit.MICROSECONDS.convert(drawEndTime - drawStartTime, TimeUnit.NANOSECONDS);

      graphics2D.setFont(new Font("Arial", Font.PLAIN, 40));
      graphics2D.setColor(Color.white);
      graphics2D.drawString("Draw Time: " + passed, 10, 550);
      System.out.println("Draw Time: " + passed);
    }

    graphics2D.dispose();
  }


  public final void startMusic(
         final int i) {
    this.music.setFile(i);
    this.music.loop();
  }
  
  public final void playMusic() {
    this.music.play();
  }

  public final void stopMusic() {
    this.music.stop();
  }
    
  public final void playSoundEffect(
         final int i) {
    this.soundEffect.setFile(i);
    this.soundEffect.play();
  }
  
}
