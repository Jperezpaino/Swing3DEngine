package es.noa.rad.path;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

 public class GeneralPathDemo extends JPanel {
  int x, y, x1, y1, x2, y2;
  GeneralPath oddShape;
  BufferedImage bi;
  Graphics2D big;
  boolean firstTime;
  Rectangle area;

  public GeneralPathDemo() {
   oddShape = new GeneralPath();
   firstTime = true;
   area = new Rectangle();
  }

  public GeneralPath createPath() {
    oddShape.moveTo(20, 30);
    oddShape.lineTo(30, 40);
    oddShape.lineTo(50, 10);
    oddShape.lineTo(70, 20);
    oddShape.curveTo(10, 90, 100, 50, 34, 99);
    return oddShape;
  }

  public void paintComponent(Graphics g) {
    // super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    if (firstTime) {
      Dimension dim = getSize();
      int w = dim.width;
      int h = dim.height;
      oddShape = createPath();
      area = new Rectangle(w, h);
      bi = (BufferedImage) createImage(w, h);
      big = bi.createGraphics();
      big.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
      firstTime = false;
    }

    // Clears the shape that was previously drawn.
    big.setColor(Color.white);
    big.fillRect(0, 0, area.width, area.height);

    big.setColor(Color.magenta);
    big.setStroke(new BasicStroke(3.0f));
    big.draw(oddShape);
    // Draws the buffered image to the screen.
    g2.drawImage(bi, 0, 0, this);

  }

  public static void main(String s[]) {

    JFrame f = new JFrame("Odd Shape");
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });

    f.getContentPane().add(new GeneralPathDemo());
    f.setSize(new Dimension(350, 200));
    f.setVisible(true);
  }

}