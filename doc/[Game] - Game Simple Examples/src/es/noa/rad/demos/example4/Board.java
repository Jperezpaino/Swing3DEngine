package es.noa.rad.demos.example4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel
        implements ActionListener {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int INITIAL_X = -40;
    private final int INITIAL_Y = -40;
    private final int DELAY = 25;

    private Image star;
    private Timer timer;
    private int x, y;

    public Board() {

        initBoard(); 
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon(".//res//star.png");
        star = ii.getImage();
    }

    private void initBoard() {

        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);

        loadImage();
        
        x = INITIAL_X;
        y = INITIAL_Y;
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawStar(g);
    }

    private void drawStar(Graphics g) {

        g.drawImage(star, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        x += 1;
        y += 1;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }

        repaint();
    }
}




/*
private final BufferedImage loadImageDrawing(final String imageSourceParam) {
 BufferedImage bufferedImage = null;
 try {
  bufferedImage = ImageIO.read(new File(imageSourceParam));
  int imageWidth = bufferedImage.getWidth(null);
  int imageHeight = bufferedImage.getHeight(null);
  /*if (bufferedImage.getType() != BufferedImage.TYPE_INT_RGB) {
   // En caso de que la imagen no sea RGB, la transformamos. 
   BufferedImage backBufferedImage =
    new BufferedImage(
     imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
   Graphics backBufferedGraphics = backBufferedImage.getGraphics();
   backBufferedGraphics.drawImage(bufferedImage, 0, 0, null);
   bufferedImage = backBufferedImage;
  }*/
 /*} catch (IOException exception) {
  System.out.println("Imposible cargar la imagen: " + imageSourceParam.toString());
  System.exit(1);
 }
 return bufferedImage;
}*/

/*
 In the Board class we move a star that from the upper-left corner to the right-bottom corner.

private final int B_WIDTH = 350;
private final int B_HEIGHT = 350;
private final int INITIAL_X = -40;
private final int INITIAL_Y = -40;
private final int DELAY = 25;

Five constants are defined. The first two constants are the board width and height. The third and fourth are the initial coordinates of the star. The last one determines the speed of the animation.

private void loadImage() {
    
    ImageIcon ii = new ImageIcon("star.png");
    star = ii.getImage();        
}

In the loadImage() method we create an instance of the ImageIcon class. The image is located in the project directory. The getImage() method will return the the Image object from this class. This object will be drawn on the board.

setDoubleBuffered(true);

The JPanel component will use a buffer to paint. This means that all drawing will be done in memory first. Later the off-screen buffer will be copied to the screen. In this simple example, we might not notice any differences.

timer = new Timer(DELAY, this);
timer.start();

Here we create a Swing Timer class and call its start() method. Every DELAY ms the timer will call the actionPerformed() method. In order to use the actionPerformed() method, we must implement the ActionListener interface.

@Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);

    drawStar(g);
}

Custom painting is done in the paintComponent() method. Note that we also call the paintComponent() method of its parent. The actual painting is delegated to the drawStar() method.

private void drawStar(Graphics g) {

    g.drawImage(star, x, y, this);
    Toolkit.getDefaultToolkit().sync();
}

In the drawStar() method, we draw the image on the window with the usage of the drawImage() method. The Toolkit.getDefaultToolkit().sync() synchronises the painting on systems that buffer graphics events. Without this line, the animation might not be smooth on Linux.

@Override
public void actionPerformed(ActionEvent e) {

    x += 1;
    y += 1;

    if (y > B_HEIGHT) {

        y = INITIAL_Y;
        x = INITIAL_X;
    }

    repaint();
}

The actionPerformed() method is repeatedly called by the timer. Inside the method, we increase the x and y values of the star object. Then we call the repaint() method which will cause the paintComponent() to be called. This way we regularly repaint the Board thus making the animation. 
 */
