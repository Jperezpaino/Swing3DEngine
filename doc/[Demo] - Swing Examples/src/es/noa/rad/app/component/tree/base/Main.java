package es.noa.rad.app.component.tree.base;

//ww w. j  a v  a2  s .  co  m

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class Main extends JPanel {

 DefaultMutableTreeNode root = new DefaultMutableTreeNode("java2s.com", true),
     node1 = new DefaultMutableTreeNode("node 1", true), node2 = new DefaultMutableTreeNode(
         "node 2", true), node3 = new DefaultMutableTreeNode("node 3", true);

 MyJTree tree = new MyJTree(root);

 public Main() {
   root.add(node1);
   node1.add(node2);
   root.add(node3);
   setLayout(new BorderLayout());
   add(new JScrollPane((JTree) tree), "Center");
 }
 public static void main(String s[]) {
   JFrame frame = new JFrame("Tree With Popup");
   Main panel = new Main();

   frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
   frame.setBackground(Color.lightGray);
   frame.getContentPane().add(panel, "Center");

   frame.setSize(panel.getPreferredSize());
   frame.setVisible(true);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}
class MyJTree extends JTree implements ActionListener {
 JPopupMenu popup = new JPopupMenu();
 JMenuItem mi = new JMenuItem("Insert a children");

 MyJTree(DefaultMutableTreeNode dmtn) {
   super(dmtn);    
   mi.addActionListener(this);
   mi.setActionCommand("insert");
   popup.add(mi);
   mi = new JMenuItem("Remove this node");
   mi.addActionListener(this);
   mi.setActionCommand("remove");
   popup.add(mi);
   addMouseListener(new MouseAdapter() {
     public void mouseReleased(MouseEvent e) {
       if (e.isPopupTrigger()) {
         popup.show((JComponent) e.getSource(), e.getX(), e.getY());
       }
     }
   });

 }

 public void actionPerformed(ActionEvent ae) {
   DefaultMutableTreeNode dmtn, node;

   TreePath path = this.getSelectionPath();
   dmtn = (DefaultMutableTreeNode) path.getLastPathComponent();
   if (ae.getActionCommand().equals("insert")) {
     node = new DefaultMutableTreeNode("children");
     dmtn.add(node);
     ((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) dmtn);
   }
   if (ae.getActionCommand().equals("remove")) {
     node = (DefaultMutableTreeNode) dmtn.getParent();
     int nodeIndex = node.getIndex(dmtn);
     dmtn.removeAllChildren();
     node.remove(nodeIndex);
     ((DefaultTreeModel) this.getModel()).nodeStructureChanged((TreeNode) dmtn);
   }
 }
}