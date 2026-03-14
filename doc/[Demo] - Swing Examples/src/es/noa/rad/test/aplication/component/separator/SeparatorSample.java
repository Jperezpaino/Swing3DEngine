package es.noa.rad.test.aplication.component.separator;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class SeparatorSample {
  public static void main(String args[]) {
    JFrame f = new JFrame("JSeparator Sample");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container content = f.getContentPane();
    content.setLayout(new GridLayout(0, 1));
    JLabel above = new JLabel("Above Separator");
    content.add(above);
    JSeparator separator = new JSeparator();
    content.add(separator);
    JLabel below = new JLabel("Below Separator");
    content.add(below);
    f.setSize(300, 100);
    f.setVisible(true);
  }
}