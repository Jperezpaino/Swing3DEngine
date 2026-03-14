package es.noa.rad.demo.swing.menupopup.res.java2s;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PopupMenu {
  public static void main(String[] args) {
    JFrame frame = new JFrame("JPopupMenu");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final Toolkit toolkit = frame.getToolkit();

    final JPopupMenu menu = new JPopupMenu();
    JMenuItem menuItemBeep = new JMenuItem("Beep");

    menuItemBeep.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        toolkit.beep();
      }
    });

    menu.add(menuItemBeep);

    JMenuItem menuItemClose = new JMenuItem("Close");
    menuItemClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });

    menu.add(menuItemClose);
    frame.addMouseListener(new MouseAdapter() {
      public void mouseReleased(MouseEvent e) {
        if (e.getButton() == e.BUTTON3) {
          menu.show(e.getComponent(), e.getX(), e.getY());
        }
      }
    });

    frame.setSize(250, 200);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}