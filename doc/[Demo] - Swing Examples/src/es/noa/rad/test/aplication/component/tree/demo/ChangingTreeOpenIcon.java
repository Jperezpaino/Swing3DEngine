package es.noa.rad.test.aplication.component.tree.demo;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;

 class TreeNodeVectorIcon<E> extends Vector<E> {
  String name;

  TreeNodeVectorIcon(String name) {
    this.name = name;
  }

  TreeNodeVectorIcon(String name, E elements[]) {
    this.name = name;
    for (int i = 0, n = elements.length; i < n; i++) {
      add(elements[i]);
    }
  }

  public String toString() {
    return "[" + name + "]";
  }
}

public class ChangingTreeOpenIcon {
  public static void main(final String args[]) {
    UIManager.put("Tree.openIcon", new ImageIcon(ChangingTreeOpenIcon.class.getResource("library_ico.gif")));
    
    JFrame frame = new JFrame("JTreeSample");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Vector<String> v1 = new TreeNodeVectorIcon<String>("Two", new String[] { "Mercury", "Venus",
        "Mars" });
    Vector<Object> v2 = new TreeNodeVectorIcon<Object>("Three");
    v2.add(System.getProperties());
    v2.add(v1);
    Object rootNodes[] = {v1, v2 };
    Vector<Object> rootVector = new TreeNodeVectorIcon<Object>("Root", rootNodes);
    JTree tree = new JTree(rootVector);
    frame.add(new JScrollPane(tree), BorderLayout.CENTER);

    frame.setSize(300, 300);
    frame.setVisible(true);
    
  }
}