package mino.ermal.engine3d;

import javax.swing.*;
import java.awt.*;

/**
 * User: mino.ermal(AT)gmail.com
 * Date: Jun 15, 2008
 * Time: 2:45:23 PM
 *
 * This is the application entry point to be used from the web
 * as an applet.
 *
 */
public class Engine3DApplet extends JApplet {
    private final Engine3DControlPanel panel=new Engine3DControlPanel();
    public void init(){
		setBackground(Color.gray);
		setSize(800,600);
		getContentPane().add(panel);
	}
}
