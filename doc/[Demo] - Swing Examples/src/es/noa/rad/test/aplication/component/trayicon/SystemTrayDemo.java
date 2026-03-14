package es.noa.rad.test.aplication.component.trayicon;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SystemTrayDemo {
  public static void main(String[] args)throws Exception {
    if (!SystemTray.isSupported()) {
      System.out.println("SystemTray is not supported");
      return;
    }

    SystemTray tray = SystemTray.getSystemTray();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Image image = toolkit.getImage("trayIcon.jpg");

    PopupMenu menu = new PopupMenu();

    MenuItem messageItem = new MenuItem("Show Message");
    messageItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "www.java2s.com");
      }
    });
    menu.add(messageItem);

    MenuItem closeItem = new MenuItem("Close");
    closeItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    menu.add(closeItem);
    TrayIcon icon = new TrayIcon(image, "SystemTray Demo", menu);
    icon.setImageAutoSize(true);

    tray.add(icon);
  }
}