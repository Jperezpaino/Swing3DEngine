
package es.noa.rad.test.swing;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.Window;
import java.awt.Container;
import javax.swing.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import java.util.Hashtable;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;

public class PanelPrincipal extends JPanel
{
    protected DefaultTreeModel m_model = null;
    private Color BackGroundColor = new Color(255,12,000);
    private JSplitPane innerPane;
    JLabel Image_1 = new JLabel();
    JLabel Image_2 = new JLabel();
    PanelSecundario Panel;
    
    public PanelPrincipal(PanelSecundario Panel_2) 
    {
        //this.setBackground(BackGroundColor);
        //this.SetLayoutBackground();
        this.Panel = Panel_2;
        this.add(this.CTree(), BorderLayout.CENTER);
    }
    
    JScrollPane CTree() 
    {


        
           Object[] nodes = new Object[5];
    DefaultMutableTreeNode top = new DefaultMutableTreeNode(
      new OidNode(1, "ISO"));
    DefaultMutableTreeNode parent = top;
    nodes[0] = top;

    DefaultMutableTreeNode node = new DefaultMutableTreeNode(
      new OidNode(0, "standard"));
    parent.add(node);
    node = new DefaultMutableTreeNode(new OidNode(2, 
      "member-body"));
    parent.add(node);
    node = new DefaultMutableTreeNode(new OidNode(3, "org"));
    parent.add(node);
    parent = node;
    nodes[1] = parent;

    node = new DefaultMutableTreeNode(new OidNode(6, "dod"));
    parent.add(node);
    parent = node;
    nodes[2] = parent;

    node = new DefaultMutableTreeNode(new OidNode(1, 
      "internet"));
    parent.add(node);
    parent = node;
    nodes[3] = parent;

    node = new DefaultMutableTreeNode(new OidNode(1, 
      "directory"));
    parent.add(node);
    node = new DefaultMutableTreeNode(new OidNode(2, 
      "mgmt"));
    parent.add(node);
    nodes[4] = node;
    node.add(new DefaultMutableTreeNode(new OidNode(1, 
      "mib-2")));
    node = new DefaultMutableTreeNode(new OidNode(3, 
      "experimental"));
    parent.add(node);
    node = new DefaultMutableTreeNode(new OidNode(4, 
      "private"));
    node.add(new DefaultMutableTreeNode(new OidNode(1, 
      "enterprises")));
    parent.add(node);
    node = new DefaultMutableTreeNode(new OidNode(5, 
      "security"));
    parent.add(node);
    node = new DefaultMutableTreeNode(new OidNode(6, 
      "snmpV2"));
    parent.add(node);
    node = new DefaultMutableTreeNode(new OidNode(7, 
      "mail"));
    parent.add(node);
    
    
       m_model = new DefaultTreeModel(top);

        JTree tree = new JTree(m_model);
        tree.setShowsRootHandles(true); 
        tree.setEditable(false);
        TreePath path = new TreePath(nodes);
        tree.setSelectionPath(path);
        
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();  
        int alto = pantalla.height - 68;
        tree.setMinimumSize(new Dimension(180, alto-20));
        tree.setPreferredSize(new Dimension(180, alto-20));
        tree.addTreeSelectionListener(new OidSelectionListener(this.Panel));
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setMinimumSize(new Dimension(200, alto));
        scrollPane.setPreferredSize(new Dimension(200, alto));
        return scrollPane;
    }
    
    void SetLayoutBackground()
    {
        //Estable coordenadas para el GridBagLoyaut 
        GridBagConstraints LayoutReg = new GridBagConstraints();
               
        //Marco central, 1,1-1,1
        LayoutReg.gridx = 1;
        LayoutReg.gridy = 1;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 1.0;
        LayoutReg.weightx = 1.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.BOTH;
        //LayoutReg.insets = new Insets(5,5,5,5);
        this.add (this.CTree(), LayoutReg); 
        LayoutReg.insets = new Insets(0,0,0,0);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
    }
    
      class OidSelectionListener 
    implements TreeSelectionListener 
  {
          PanelSecundario Panel;
       OidSelectionListener(PanelSecundario Panel_2)
       {
        this.Panel = Panel_2;
       }
    public void valueChanged(TreeSelectionEvent e)
    {
      TreePath path = e.getPath();
      Object[] nodes = path.getPath();
      String oid = "";
      for (int k=0; k<nodes.length; k++)
      {
        DefaultMutableTreeNode node = 
          (DefaultMutableTreeNode)nodes[k];
        OidNode nd = (OidNode)node.getUserObject();
        oid += "."+nd.getId();
      }
      this.Panel.EtiquetaValue(oid);
    }
  }
}



class OidNode
{
  protected int    m_id;
  protected String m_name;

  public OidNode(int id, String name)
  {
    m_id = id;
    m_name = name;
  }

  public int getId() 
  { 
    return m_id;
  }

  public String getName() 
  { 
    return m_name;
  }

  public String toString() 
  { 
    return m_name;
  }
}