package es.noa.rad.demos.game4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import es.noa.rad.demos.game4.Shape.Tetrominoes;

public class Board extends JPanel implements ActionListener {


    final int BoardWidth = 10;
    final int BoardHeight = 22;

    Timer timer;
    boolean isFallingFinished = false;
    boolean isStarted = false;
    boolean isPaused = false;
    int numLinesRemoved = 0;
    int curX = 0;
    int curY = 0;
    JLabel statusbar;
    Shape curPiece;
    Tetrominoes[] board;



    public Board(Tetris parent) {

       setFocusable(true);
       curPiece = new Shape();
       timer = new Timer(400, this);
       timer.start(); 

       statusbar =  parent.getStatusBar();
       board = new Tetrominoes[BoardWidth * BoardHeight];
       addKeyListener(new TAdapter());
       clearBoard();  
    }

    public void actionPerformed(ActionEvent e) {
        if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
        }
    }


    int squareWidth() { return (int) getSize().getWidth() / BoardWidth; }
    int squareHeight() { return (int) getSize().getHeight() / BoardHeight; }
    Tetrominoes shapeAt(int x, int y) { return board[(y * BoardWidth) + x]; }


    public void start()
    {
        if (isPaused)
            return;

        isStarted = true;
        isFallingFinished = false;
        numLinesRemoved = 0;
        clearBoard();

        newPiece();
        timer.start();
    }

    private void pause()
    {
        if (!isStarted)
            return;

        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
            statusbar.setText("paused");
        } else {
            timer.start();
            statusbar.setText(String.valueOf(numLinesRemoved));
        }
        repaint();
    }

    public void paint(Graphics g)
    { 
        super.paint(g);

        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - BoardHeight * squareHeight();


        for (int i = 0; i < BoardHeight; ++i) {
            for (int j = 0; j < BoardWidth; ++j) {
                Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
                if (shape != Tetrominoes.NoShape)
                    drawSquare(g, 0 + j * squareWidth(),
                               boardTop + i * squareHeight(), shape);
            }
        }

        if (curPiece.getShape() != Tetrominoes.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = curX + curPiece.x(i);
                int y = curY - curPiece.y(i);
                drawSquare(g, 0 + x * squareWidth(),
                           boardTop + (BoardHeight - y - 1) * squareHeight(),
                           curPiece.getShape());
            }
        }
    }

    private void dropDown()
    {
        int newY = curY;
        while (newY > 0) {
            if (!tryMove(curPiece, curX, newY - 1))
                break;
            --newY;
        }
        pieceDropped();
    }

    private void oneLineDown()
    {
        if (!tryMove(curPiece, curX, curY - 1))
            pieceDropped();
    }


    private void clearBoard()
    {
        for (int i = 0; i < BoardHeight * BoardWidth; ++i)
            board[i] = Tetrominoes.NoShape;
    }

    private void pieceDropped()
    {
        for (int i = 0; i < 4; ++i) {
            int x = curX + curPiece.x(i);
            int y = curY - curPiece.y(i);
            board[(y * BoardWidth) + x] = curPiece.getShape();
        }

        removeFullLines();

        if (!isFallingFinished)
            newPiece();
    }

    private void newPiece()
    {
        curPiece.setRandomShape();
        curX = BoardWidth / 2 + 1;
        curY = BoardHeight - 1 + curPiece.minY();

        if (!tryMove(curPiece, curX, curY)) {
            curPiece.setShape(Tetrominoes.NoShape);
            timer.stop();
            isStarted = false;
            statusbar.setText("game over");
        }
    }

    private boolean tryMove(Shape newPiece, int newX, int newY)
    {
        for (int i = 0; i < 4; ++i) {
            int x = newX + newPiece.x(i);
            int y = newY - newPiece.y(i);
            if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
                return false;
            if (shapeAt(x, y) != Tetrominoes.NoShape)
                return false;
        }

        curPiece = newPiece;
        curX = newX;
        curY = newY;
        repaint();
        return true;
    }

    private void removeFullLines()
    {
        int numFullLines = 0;

        for (int i = BoardHeight - 1; i >= 0; --i) {
            boolean lineIsFull = true;

            for (int j = 0; j < BoardWidth; ++j) {
                if (shapeAt(j, i) == Tetrominoes.NoShape) {
                    lineIsFull = false;
                    break;
                }
            }

            if (lineIsFull) {
                ++numFullLines;
                for (int k = i; k < BoardHeight - 1; ++k) {
                    for (int j = 0; j < BoardWidth; ++j)
                         board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
                }
            }
        }

        if (numFullLines > 0) {
            numLinesRemoved += numFullLines;
            statusbar.setText(String.valueOf(numLinesRemoved));
            isFallingFinished = true;
            curPiece.setShape(Tetrominoes.NoShape);
            repaint();
        }
     }

    private void drawSquare(Graphics g, int x, int y, Tetrominoes shape)
    {
        Color colors[] = { new Color(0, 0, 0), new Color(204, 102, 102), 
            new Color(102, 204, 102), new Color(102, 102, 204), 
            new Color(204, 204, 102), new Color(204, 102, 204), 
            new Color(102, 204, 204), new Color(218, 170, 0)
        };


        Color color = colors[shape.ordinal()];

        g.setColor(color);
        g.fillRect(x + 1, y + 1, squareWidth() - 2, squareHeight() - 2);

        g.setColor(color.brighter());
        g.drawLine(x, y + squareHeight() - 1, x, y);
        g.drawLine(x, y, x + squareWidth() - 1, y);

        g.setColor(color.darker());
        g.drawLine(x + 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + squareHeight() - 1);
        g.drawLine(x + squareWidth() - 1, y + squareHeight() - 1,
                         x + squareWidth() - 1, y + 1);
    }

    class TAdapter extends KeyAdapter {
         public void keyPressed(KeyEvent e) {

             if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {  
                 return;
             }

             int keycode = e.getKeyCode();

             if (keycode == 'p' || keycode == 'P') {
                 pause();
                 return;
             }

             if (isPaused)
                 return;

             switch (keycode) {
             case KeyEvent.VK_LEFT:
                 tryMove(curPiece, curX - 1, curY);
                 break;
             case KeyEvent.VK_RIGHT:
                 tryMove(curPiece, curX + 1, curY);
                 break;
             case KeyEvent.VK_DOWN:
                 tryMove(curPiece.rotateRight(), curX, curY);
                 break;
             case KeyEvent.VK_UP:
                 tryMove(curPiece.rotateLeft(), curX, curY);
                 break;
             case KeyEvent.VK_SPACE:
                 dropDown();
                 break;
             case 'd':
                 oneLineDown();
                 break;
             case 'D':
                 oneLineDown();
                 break;
             }

         }
     }
}

/*inally, we have the Board.java file. This is where the game logic is located.

...
isFallingFinished = false;
isStarted = false;
isPaused = false;
numLinesRemoved = 0;
curX = 0;
curY = 0;
...

We initialize some important variables. The isFallingFinished variable determines if the tetris shape has finished falling and we then need to create a new shape. The numLinesRemoved counts the number of lines we have removed so far. The curX and curY variables determine the actual position of the falling tetris shape.

setFocusable(true);

We must explicitly call the setFocusable() method. From now, the board has the keyboard input.

timer = new Timer(400, this);
timer.start(); 

Timer object fires one or more action events after a specified delay. In our case, the timer calls the actionPerformed() method each 400 ms.

public void actionPerformed(ActionEvent e) {
    if (isFallingFinished) {
        isFallingFinished = false;
        newPiece();
    } else {
        oneLineDown();
    }
}

The actionPerformed() method checks if the falling has finished. If so, a new piece is created. If not, the falling tetris piece goes one line down.

Inside the paint() method, we draw the all objects on the board. The painting has two steps.

for (int i = 0; i < BoardHeight; ++i) {
    for (int j = 0; j < BoardWidth; ++j) {
        Tetrominoes shape = shapeAt(j, BoardHeight - i - 1);
        if (shape != Tetrominoes.NoShape)
            drawSquare(g, 0 + j * squareWidth(),
                    boardTop + i * squareHeight(), shape);
    }
}

In the first step we paint all the shapes, or remains of the shapes that have been dropped to the bottom of the board. All the squares are rememberd in the board array. We access it using the shapeAt() method.

if (curPiece.getShape() != Tetrominoes.NoShape) {
    for (int i = 0; i < 4; ++i) {
        int x = curX + curPiece.x(i);
        int y = curY - curPiece.y(i);
        drawSquare(g, 0 + x * squareWidth(),
                boardTop + (BoardHeight - y - 1) * squareHeight(),
                curPiece.getShape());
    }
}

In the second step, we paint the actual falling piece.

private void dropDown()
{
    int newY = curY;
    while (newY > 0) {
        if (!tryMove(curPiece, curX, newY - 1))
            break;
        --newY;
    }
    pieceDropped();
}

If we press the space key, the piece is dropped to the bottom. We simply try to drop the piece one line down until it reaches the bottom or the top of another fallen tetris piece.

private void clearBoard()
{
    for (int i = 0; i < BoardHeight * BoardWidth; ++i)
        board[i] = Tetrominoes.NoShape;
}

The clearBoard() method fills the board with empty NoSpapes. This is later used at collision detection.

private void pieceDropped()
{
    for (int i = 0; i < 4; ++i) {
        int x = curX + curPiece.x(i);
        int y = curY - curPiece.y(i);
        board[(y * BoardWidth) + x] = curPiece.getShape();
    }

    removeFullLines();

    if (!isFallingFinished)
        newPiece();
}

The pieceDropped() method puts the falling piece into the board array. Once again, the board holds all the squares of the pieces and remains of the pieces that has finished falling. When the piece has finished falling, it is time to check if we can remove some lines off the board. This is the job of the removeFullLines() method. Then we create a new piece. More precisely, we try to create a new piece.

private void newPiece()
{
    curPiece.setRandomShape();
    curX = BoardWidth / 2 + 1;
    curY = BoardHeight - 1 + curPiece.minY();

    if (!tryMove(curPiece, curX, curY)) {
        curPiece.setShape(Tetrominoes.NoShape);
        timer.stop();
        isStarted = false;
        statusbar.setText("game over");
    }
}

The newPiece() method creates a new tetris piece. The piece gets a new random shape. Then we compute the initial curX and curY values. If we cannot move to the initial positions, the game is over. We top out. The timer is stopped. We put game over string on the statusbar.

private boolean tryMove(Shape newPiece, int newX, int newY)
{
    for (int i = 0; i < 4; ++i) {
        int x = newX + newPiece.x(i);
        int y = newY - newPiece.y(i);
        if (x < 0 || x >= BoardWidth || y < 0 || y >= BoardHeight)
            return false;
        if (shapeAt(x, y) != Tetrominoes.NoShape)
            return false;
    }

    curPiece = newPiece;
    curX = newX;
    curY = newY;
    repaint();
    return true;
}

The tryMove() method tries to move the tetris piece. The method returns false if it has reached the board boundaries or it is adjacent to the already fallen tetris pieces.

for (int i = BoardHeight - 1; i >= 0; --i) {
    boolean lineIsFull = true;

    for (int j = 0; j < BoardWidth; ++j) {
        if (shapeAt(j, i) == Tetrominoes.NoShape) {
            lineIsFull = false;
            break;
        }
    }

    if (lineIsFull) {
        ++numFullLines;
        for (int k = i; k < BoardHeight - 1; ++k) {
            for (int j = 0; j < BoardWidth; ++j)
                board[(k * BoardWidth) + j] = shapeAt(j, k + 1);
        }
    }
}

Inside the removeFullLines() method, we check if there is any full row among all rows in the board. If there is at least one full line, it is removed. After finding a full line we increase the counter. We move all the lines above the full row one line down. This way we destroy the full line. Notice that in our Tetris game, we use so called naive gravity. This means that the squares may be left floating above empty gaps.

Every tetris piece has four squares. Each of the squares is drawn with the drawSquare() method. Tetris pieces have different colours.

g.setColor(color.brighter());
g.drawLine(x, y + squareHeight() - 1, x, y);
g.drawLine(x, y, x + squareWidth() - 1, y);

The left and top sides of a square are drawn with a brighter colour. Similarly, the bottom and right sides are drawn with darker colours. This is to simulate a 3D edge.

We control the game with a keyboard. The control mechanism is implemented with a KeyAdapter. This is an inner class that overrides the keyPressed() method.

case KeyEvent.VK_RIGHT:
    tryMove(curPiece, curX + 1, curY);
    break;

If we pressed the left arrow key, we try to move the falling piece one square to the left. */