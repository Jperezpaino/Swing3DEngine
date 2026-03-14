package es.noa.rad.demos.example7;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Craft craft;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        craft = new Craft();

        timer = new Timer(DELAY, this);
        timer.start();        
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        craft.move();
        repaint();  
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}
/* This is the Board class.

private void doDrawing(Graphics g) {
    
    Graphics2D g2d = (Graphics2D) g;
    g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);        
}

In the doDrawing() method, we draw the spacecraft with the drawImage() method. We get the image and the coordinates from the sprite class.

@Override
public void actionPerformed(ActionEvent e) {
    
    craft.move();
    repaint();  
}

The actionPerformed() method is called every DELAY ms. We move the sprite and repaint the board.

private class TAdapter extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent e) {
        craft.keyReleased(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        craft.keyPressed(e);
    }
}

In the Board class we listen for key events. The overridden methods of the KeyAdapter class delegate the processing to the methods of the Craft class. */