package es.noa.rad.test.aplication.component.trayicon;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SystemTrayDemo1 {
  public static void main(String[] args) throws Exception {
    if (!SystemTray.isSupported()) {
      return;
    }
    SystemTray tray = SystemTray.getSystemTray();

    PropertyChangeListener pcl;
    pcl = new PropertyChangeListener() {
      public void propertyChange(PropertyChangeEvent pce) {
        System.out.println("Property changed = " + pce.getPropertyName());
        TrayIcon[] tia = (TrayIcon[]) pce.getOldValue();
        if (tia != null) {
          System.out.println("Old tray icon array contents: ");
          for (int i = 0; i < tia.length; i++)
            System.out.println(tia[i]);
          System.out.println();
        }

        tia = (TrayIcon[]) pce.getNewValue();
        if (tia != null) {
          System.out.println("New tray icon array contents: ");
          for (int i = 0; i < tia.length; i++)
            System.out.println(tia[i]);
          System.out.println();
        }
      }
    };
    tray.addPropertyChangeListener("trayIcons", pcl);

    Dimension size = tray.getTrayIconSize();
    BufferedImage bi = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
    Graphics g = bi.getGraphics();

    g.setColor(Color.blue);
    g.fillRect(0, 0, size.width, size.height);
    g.setColor(Color.yellow);
    int ovalSize = (size.width < size.height) ? size.width : size.height;
    ovalSize /= 2;
    g.fillOval(size.width / 4, size.height / 4, ovalSize, ovalSize);

    TrayIcon icon = null;
    tray.add(icon = new TrayIcon(bi));

    Thread.sleep(300000);
    tray.remove(icon);

    Thread.sleep(300000);
    System.exit(0);
  }

}