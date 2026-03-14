/*
 * PanelSecundario.java
 *
 * Created on 9 de diciembre de 2006, 5:49
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

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

public class PanelSecundario extends JPanel
{
    private JLabel Etiqueta = new JLabel("sin valor");
   
    public PanelSecundario() 
    {
        this.add(Etiqueta);
    }
    
    void EtiquetaValue(String Name)
    {
        this.Etiqueta.setText(Name);
    }
    
}
