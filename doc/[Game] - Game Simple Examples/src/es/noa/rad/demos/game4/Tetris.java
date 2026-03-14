package es.noa.rad.demos.game4;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Tetris extends JFrame {

    JLabel statusbar;


    public Tetris() {

        statusbar = new JLabel(" 0");
        add(statusbar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board);
        board.start();

        setSize(200, 400);
        setTitle("Tetris");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   public JLabel getStatusBar() {
       return statusbar;
   }

    public static void main(String[] args) {

        Tetris game = new Tetris();
        game.setLocationRelativeTo(null);
        game.setVisible(true);

    } 
}/* In the Tetris.java file, we set up the game. We create a board on which we play the game. We create a statusbar.

board.start();

The start() method starts the Tetris game. Immediately, after the window appears on the screen. */