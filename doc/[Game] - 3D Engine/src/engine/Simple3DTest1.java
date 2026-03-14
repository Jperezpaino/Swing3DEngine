package engine; 

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.DisplayMode;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A 3D Test to demonstrate drawing polygons.
 */
public class Simple3DTest1 extends GameCore {

  public static void main(String[] args) {
    new Simple3DTest1().run();
  }

  // create solid-colored polygons
  private SolidPolygon3D treeLeaves = new SolidPolygon3D(new Vector3D(-50,
      -35, 0), new Vector3D(50, -35, 0), new Vector3D(0, 150, 0));

  private SolidPolygon3D treeTrunk = new SolidPolygon3D(new Vector3D(-5, -50,
      0), new Vector3D(5, -50, 0), new Vector3D(5, -35, 0), new Vector3D(
      -5, -35, 0));

  private Transform3D treeTransform = new Transform3D(0, 0, -500);

  private Polygon3D transformedPolygon = new Polygon3D();

  private ViewWindow viewWindow;

  private GameAction exit = new GameAction("exit");

  private GameAction zoomIn = new GameAction("zoomIn");

  private GameAction zoomOut = new GameAction("zoomOut");

  public void init() {
    super.init();

    InputManager inputManager = new InputManager(screen
        .getFullScreenWindow());
    inputManager.setCursor(InputManager.INVISIBLE_CURSOR);
    inputManager.mapToKey(exit, KeyEvent.VK_ESCAPE);
    inputManager.mapToKey(zoomIn, KeyEvent.VK_UP);
    inputManager.mapToKey(zoomOut, KeyEvent.VK_DOWN);

    // make the view window the entire screen
    viewWindow = new ViewWindow(0, 0, screen.getWidth(),
        screen.getHeight(), (float) Math.toRadians(75));

    // give the polygons color
    treeLeaves.setColor(new Color(0x008000));
    treeTrunk.setColor(new Color(0x714311));
  }

  public void update(long elapsedTime) {
    if (exit.isPressed()) {
      stop();
      return;
    }

    // cap elapsedTime
    elapsedTime = Math.min(elapsedTime, 100);

    // rotate around the y axis
    treeTransform.rotateAngleY(0.002f * elapsedTime);

    // allow user to zoom in/out
    if (zoomIn.isPressed()) {
      treeTransform.getLocation().z += 0.5f * elapsedTime;
    }
    if (zoomOut.isPressed()) {
      treeTransform.getLocation().z -= 0.5f * elapsedTime;
    }
  }

  public void draw(Graphics2D g) {
    // erase background
    g.setColor(Color.black);
    g.fillRect(0, 0, screen.getWidth(), screen.getHeight());

    // draw message
    g.setColor(Color.white);
    g.drawString("Press up/down to zoom. Press Esc to exit.", 5, fontSize);

    // draw the tree polygons
    trandformAndDraw(g, treeTrunk);
    trandformAndDraw(g, treeLeaves);
  }

  /**
   * Projects and draws a polygon onto the view window.
   */
  private void trandformAndDraw(Graphics2D g, SolidPolygon3D poly) {
    transformedPolygon.setTo(poly);

    // translate and rotate the polygon
    transformedPolygon.add(treeTransform);

    // project the polygon to the screen
    transformedPolygon.project(viewWindow);

    // convert the polygon to a Java2D GeneralPath and draw it
    GeneralPath path = new GeneralPath();
    Vector3D v = transformedPolygon.getVertex(0);
    path.moveTo(v.x, v.y);
    for (int i = 1; i < transformedPolygon.getNumVertices(); i++) {
      v = transformedPolygon.getVertex(i);
      path.lineTo(v.x, v.y);
    }
    g.setColor(poly.getColor());
    g.fill(path);
  }
}