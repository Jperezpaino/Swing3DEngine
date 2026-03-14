package es.noa.rad.demos.example3;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Board extends JPanel {

    private Image bardejov;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
        loadImage();
        
        int w = bardejov.getWidth(this);
        int h =  bardejov.getHeight(this);
        setPreferredSize(new Dimension(w, h));        
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon(".//res//fondo.jpg");
        bardejov = ii.getImage();        
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(bardejov, 0, 0, null);
    }
}

/*
We pain an image of a town on the board. The image is drawn inside the paintComponent() method.

ImageIcon ii = new ImageIcon("bardejov.png");

We create an ImageIcon.

bardejov = ii.getImage();

We get an Image out of the ImageIcon.

g.drawImage(bardejov, 0, 0, null);

We draw the image on the window.

int w = bardejov.getWidth(this);
int h =  bardejov.getHeight(this);
setPreferredSize(new Dimension(w, h));

We determine the width and height of the image. The preferred size of the board panel is set to the dimensions of the image. In cooperation with the JFrame's pack() method, the window is just big enough to show the image. 
*/