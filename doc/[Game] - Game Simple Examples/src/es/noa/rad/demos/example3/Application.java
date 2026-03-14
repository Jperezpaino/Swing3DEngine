package es.noa.rad.demos.example3;

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

        pack();

        setTitle("Bardejov");
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