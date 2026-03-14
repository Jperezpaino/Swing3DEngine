package es.noa.rad.demos.example2;

 import
  java.awt.EventQueue;
 import
  javax.swing.JFrame;

 public class Application extends JFrame {
  private static final long serialVersionUID = 1L;

  public Application() {
   this.initUI();
  }
 private void initUI() {

        add(new Board());

        setSize(330, 330);

        setTitle("Donut");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    
    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
             Application ex = new Application();
                ex.setVisible(true);
            }
        });
    }
}
 
 /*
 This is the entry point of the game. Here we have the main method.

add(new Board());

Here we put the Board to the center of the JFrame container.

setSize(250, 200);

This line sets the size of the window.

setDefaultCloseOperation(EXIT_ON_CLOSE);

This will close the application when we click on the close button. It is not the default behaviour.

setLocationRelativeTo(null);

Passing null to the setLocationRelativeTo() method centers the window on the screen.

public static void main(String[] args) {
    
    EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            Application ex = new Application();
            ex.setVisible(true);
        }
    });
}

We create an instance of our code example and make it visible on the screen. 
 */