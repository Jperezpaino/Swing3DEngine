package es.noa.rad.demos.example2;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class Board extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawDonut(g);
    }

    private void drawDonut(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        RenderingHints rh
                = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setRenderingHints(rh);

        Dimension size = getSize();
        double w = size.getWidth();
        double h = size.getHeight();

        Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.gray);

        for (double deg = 0; deg < 360; deg += 5) {
            AffineTransform at
                    = AffineTransform.getTranslateInstance(w/2, h/2);
            at.rotate(Math.toRadians(deg));
            g2d.draw(at.createTransformedShape(e));
        }
    }
}


/*

The painting is done inside the paintComponent() method.

Graphics2D g2d = (Graphics2D) g;

The Graphics2D class extends the Graphics class. It provides more sophisticated control over geometry, coordinate transformations, colour management, and text layout.

private void drawDonut(Graphics g) {
...
}

It is a good programming practice to delegate the actual painting to a specific method.

RenderingHints rh
       = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
               RenderingHints.VALUE_ANTIALIAS_ON);

rh.put(RenderingHints.KEY_RENDERING,
       RenderingHints.VALUE_RENDER_QUALITY);

g2d.setRenderingHints(rh);

The rendering hints are used to make the drawing smooth.

Dimension size = getSize();
double w = size.getWidth();
double h = size.getHeight();

We get the height and the width of the window. We need them to center the donut shape on the window.

Ellipse2D e = new Ellipse2D.Double(0, 0, 80, 130);
g2d.setStroke(new BasicStroke(1));
g2d.setColor(Color.gray);

Here we create the ellipse.

for (double deg = 0; deg < 360; deg += 5) {
   AffineTransform at
           = AffineTransform.getTranslateInstance(w/2, h/2);
   at.rotate(Math.toRadians(deg));
   g2d.draw(at.createTransformedShape(e));
}

Here the ellipse is rotated 72 times to create a donut shape. 
*/