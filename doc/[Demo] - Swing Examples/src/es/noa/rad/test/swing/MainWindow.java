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

public class MainWindow extends JFrame
{
    private int ancho;
    private int alto;
    private CoreWindowAdaptator CAW = new CoreWindowAdaptator();
    private JSplitPane innerPane;
    PanelSecundario Panel_2 = new PanelSecundario();
    PanelPrincipal Panel_1 = new PanelPrincipal(Panel_2);
    
    public MainWindow() 
    {
        super("Manager Text");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();  
        ancho = pantalla.width;
        alto = pantalla.height - 35;
        this.setBounds(0,0,ancho,alto);
        this.addWindowListener(CAW);
        this.setExtendedState(MAXIMIZED_BOTH);
        innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        innerPane.setLeftComponent(this.Panel_1);
        innerPane.setRightComponent(this.Panel_2);
        innerPane.setContinuousLayout(true);
        innerPane.setOneTouchExpandable(false);
        this.getContentPane().setLayout(new GridBagLayout());
        this.SetLayoutBackground();
        this.setVisible(true);  
    } 
    
    //Creamos y situamos los elementos que forman la interface grafica de la ventana.
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
        this.getContentPane().add (this.innerPane, LayoutReg); 
        LayoutReg.insets = new Insets(0,0,0,0);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        innerPane.setLeftComponent(this.Panel_1);
        innerPane.setRightComponent(this.Panel_2);
    }

}
