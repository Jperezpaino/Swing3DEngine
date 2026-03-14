package es.noa.rad.test.swing;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.Container;


public class CoreFrame extends JFrame
{
    //Variables existentes en el CoreFrame.
    private String CoreFrameName;
    
    //Constructor b�sico del objeto CoreFrame.
    public CoreFrame() 
    {
        //Establecemos que la ventana no tiene ni borde ni barra superior.
        this.setUndecorated(true);
        //Establecemos que cuando se cierre la ventana se cierre la aplicacion.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Establecemos el tama�o minimo que puede tener la ventana.
        this.setMinimumSize(new Dimension(200,200));
        //Establecemos el tama�o maximo que puede tener la ventana.
        this.setMaximumSize(new Dimension(200,200));
        //Establecemos que no se puede escalar la ventana.
        this.setResizable(true);
        //Establecemos el layout a Regilla "GridBagLayout""
        this.getContentPane().setLayout(new GridBagLayout());
        
        
       
        //this.setDefaultLookAndFeelDecorated(true);
        this.setName("CoreFrame");//Le damos nombre a la ventana internamente.
        this.setSize(new Dimension(400,200)); //Establecemos tama�o de la ventana.
        this.setTitle("Ventana Principal");//Le damos titulo a la ventana, se ve en la barra de titulo.
        this.setBackground(new Color(0,0,0));
        this.setLocation(100,100);
        
        this.SetLayoutBackground();
        
        //Comprimimos la ventana. 
        //this.pack();
    }
    
    //Constructor con parametros del objeto CoreFrame. 
    //|--(PARAMETRO_1::Nombre de la ventana).
    void CoreFrame(String CoreFrameName) 
    {
        //Establecemos el valor de la variable CoreFrameName. 
        this.SetCoreFrameName(CoreFrameName);
        //Establecemos el nombre de la ventana con el valor de la variable CoreFrameName.
        this.setName(CoreFrameName);
        //Llamamos al constructor basico.
        //this.CoreFrame(); 
    }
    
    //Creamos y situamos los elementos que forman la interface grafica de la ventana.
    void SetLayoutBackground()
    {
    
        //Estable coordenadas para el GridBagLoyaut 
        GridBagConstraints LayoutReg = new GridBagConstraints();
        
        //Esquina superior izquierda, 0,0-1,1
        JButton Area_1 = new JButton("1");
        LayoutReg.gridx = 0;
        LayoutReg.gridy = 0;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.NONE;
        this.getContentPane().add (Area_1, LayoutReg);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        //Marco superior, 1,0-1,1
        JButton Area_2 = new JButton("2");
        LayoutReg.gridx = 1;
        LayoutReg.gridy = 0;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 1.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add (Area_2, LayoutReg);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        //Esquina superior derecha, 2,0-1,1
        JButton Area_3 = new JButton("3");
        LayoutReg.gridx = 2;
        LayoutReg.gridy = 0;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.NONE;
        this.getContentPane().add (Area_3, LayoutReg);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        //Marco izquierdo, 0,1-1,1
        JButton Area_4 = new JButton("4");
        LayoutReg.gridx = 0;
        LayoutReg.gridy = 1;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 1.0;
        LayoutReg.weightx = 0.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.VERTICAL;
        this.getContentPane().add (Area_4, LayoutReg); 
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        //Marco central, 1,1-1,1
        JButton Area_5 = new JButton("5");
        LayoutReg.gridx = 1;
        LayoutReg.gridy = 1;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 1.0;
        LayoutReg.weightx = 1.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.BOTH;
        LayoutReg.insets = new Insets(5,5,5,5);
        this.getContentPane().add (Area_5, LayoutReg); 
        LayoutReg.insets = new Insets(0,0,0,0);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        //Marco Derecho, 2,1-1,1
        JButton Area_6 = new JButton("6");
        LayoutReg.gridx = 2;
        LayoutReg.gridy = 1;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 1.0;
        LayoutReg.weightx = 0.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.VERTICAL;
        this.getContentPane().add (Area_6, LayoutReg); 
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        //Esquina inferior izquierda, 0,2-1,1
        JButton Area_7 = new JButton("7");
        LayoutReg.gridx = 0;
        LayoutReg.gridy = 2;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.NONE;
        this.getContentPane().add (Area_7, LayoutReg);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        //Marco inferior, 1,2-1,1
        JButton Area_8 = new JButton("8");
        LayoutReg.gridx = 1;
        LayoutReg.gridy = 2;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 1.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add (Area_8, LayoutReg);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        
        //Esquina inferior derecha, 2,2-1,1
        JButton Area_9 = new JButton("9");
        LayoutReg.gridx = 2;
        LayoutReg.gridy = 2;
        LayoutReg.gridwidth = 1;
        LayoutReg.gridheight = 1;
        //Establecemos los anchos y altos
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
        //Establecemos los estiramientos
        LayoutReg.fill = GridBagConstraints.NONE;
        this.getContentPane().add (Area_9, LayoutReg);
        //Reestablecemos los anchos y altos.
        LayoutReg.weighty = 0.0;
        LayoutReg.weightx = 0.0;
    }
    
    //Establecemos el valor de la variable CoreFrameName, pasando el nombre mediante un parametro.
    //|--(PARAMETRO_1::Nombre de la ventana).
    void SetCoreFrameName(String CoreFrameName)
    {
        this.CoreFrameName = CoreFrameName;
    }
    
    //Devolvemos el valor de la variable CoreFrameName.
    String GetCoreFrameName()
    {
        return this.CoreFrameName;
    }
    
    public static void main(String[] args) 
    {
        CoreFrame Ventana_principal;
        Ventana_principal = new CoreFrame();
        Ventana_principal.show();
    }
}
