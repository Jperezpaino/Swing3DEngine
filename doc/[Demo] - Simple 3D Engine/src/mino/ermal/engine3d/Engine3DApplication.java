package mino.ermal.engine3d;

import javax.swing.JFrame;

/**

 * User: mino.ermal(AT)gmail.com
 * Date: 9-dic-2006
 * Time: 17.31.41
 *
 * This JFrame subclass is used as the application entry point.
 * As we can see all implementation and user interface creation
 * is in the Engine3DControlPanel and it's usage from an applet or
 * an application is very straight forward, just use it as content pane.
 *
 */
public class Engine3DApplication extends JFrame {
    private final Engine3DControlPanel controlPanel = new Engine3DControlPanel();

    private Engine3DApplication() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(100, 60, 1024, 768);
        this.setContentPane(controlPanel);
        this.setTitle("Pure Java Simple 3D Engine");
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Engine3DApplication();
    }


}
