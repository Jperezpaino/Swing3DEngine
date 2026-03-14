package es.noa.rad.test.aplication.component.tree.demo;

import java.awt.BorderLayout;
/* ww w .j  av a 2 s.  c  o m*/
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class MainAdd extends JPanel {
  JTree tree = new JTree();

  public MainAdd() {
    super(new BorderLayout());
    JScrollPane jsp = new JScrollPane(tree);
    add(jsp, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    MainAdd temp = new MainAdd();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(temp);
    frame.pack();
    frame.setVisible(true);
    Thread updater = new Thread(temp.new CustomThread());
    updater.start();
  }

  public class CustomThread implements Runnable {
    public void run() {
      for (int i = 0; i < 1000; i++) {
        updateTree("New Item " + i);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    public void updateTree(final String nodeToAdd) {
      DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
      DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel()
          .getRoot();
      DefaultMutableTreeNode child = new DefaultMutableTreeNode(nodeToAdd);
      model.insertNodeInto(child, root, root.getChildCount());
      tree.scrollPathToVisible(new TreePath(child.getPath()));
    }
  }
}