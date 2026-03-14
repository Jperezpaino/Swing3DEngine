package es.noa.rad.test.swing.component;

 import java.awt.Container;
import
  java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import
  java.awt.GridLayout;
import
  java.awt.LayoutManager;
import
  java.awt.event.KeyEvent;

import
  javax.swing.ImageIcon;
import javax.swing.JButton;
import
  javax.swing.JComponent;
import
  javax.swing.JLabel;
import
  javax.swing.JPanel;
import
  javax.swing.JTabbedPane;
import
  javax.swing.border.Border;
import
  javax.swing.border.EmptyBorder;
import es.noa.rad.swing.olddemo.buttonpanel.UtilLayout;



 public class ButtonPanelWork extends JComponent{
  private static final long serialVersionUID = 1L;
  
  /**
    * Icono principal del componente, se utilizara tanto internamente, por el
    * propio componente, como externamente por los distintos elementos que
    * puedan contener este componente y hacer uso de él.
    */
  private ImageIcon componentIcon;
  /**
    * Titulo principal del componente, se utilizara tanto internamente, por el
    * propio componente, como externamente por los distintos elementos que
    * puedan contener este componente y hacer uso de él.
    */  
  private String componentTitle;

  /**
    * Margen exterior que se aplica globalmente al componente, dándole espacio
    * a su alrededor con el resto de componentes.
    */
  private Border componentMargin;  
  /**
    * Layout principal del componente, se utilizara internamente, para el
    * posicionamiento de los elementos dentro del componente.
    */
  private LayoutManager componentLayout;    

  
  
  
  
  
  
  
  
  
  public ButtonPanelWork() {
   super();
   this.componentIcon = null;
   this.componentTitle = null;
   
   this.componentLayout = null;
   this.componentMargin = null;
     
   // definimos el margen del componente
   this.componentMargin = new EmptyBorder(6, 6, 6, 6);
   this.setBorder(this.componentMargin);
   
   // definimos el layout
   this.componentLayout = new GridBagLayout();
   this.setLayout(this.componentLayout);
   
   GridBagConstraints layoutConstraints = new GridBagConstraints();
   // Marcamos el tamaño de cada celda.
   
  /* int rowHeights[] = {100, 100, 100, 100, 100};
   int columnWidths[] = {100, 100, 100, 100, 100};
   
   double rowWeights[] = {0.0, 0.0, 0.0, 0.0, 0.0};
   double columnWeights[] = {0.0, 0.0, 0.0, 0.0, 0.0};
   layoutPanel.rowHeights = rowHeights;
   layoutPanel.columnWidths = columnWidths;
   layoutPanel.rowWeights = rowWeights;
   layoutPanel.columnWeights = columnWeights;*/
   
   // Añadimos los componentes   
   JButton button = new JButton("Boton 1");   
   
   // Lo colocamos en la celda (0,0).
   layoutConstraints.gridx = 0;
   layoutConstraints.gridy = 0;
 //padding interios
   this.add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);
   
   
   button = new JButton("Boton 2");
   
   // Lo colocamos en la celda (1,0).
   layoutConstraints.gridx = 1;
   layoutConstraints.gridy = 0;   
   // Definimos su 'span' a 3 celdas horizontales.
   layoutConstraints.gridwidth = 3;
   // Definimos su crecimiento en horizontal.
   layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
   //layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
   layoutConstraints.weightx = 1.0;
   //padding interios
   this.add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);

   
   
   button = new JButton("Boton 3");  
   
   // Lo colocamos en la celda (0, 1).
   layoutConstraints.gridx = 4;
   layoutConstraints.gridy = 0;
   this.add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);

   button = new JButton("Boton 4");  
   
   // Lo colocamos en la celda (4,0).
   layoutConstraints.gridx = 0;
   layoutConstraints.gridy = 1;
   // Definimos su 'span' a 3 celdas horizontales.
   layoutConstraints.gridheight = 3;
   layoutConstraints.weighty = 1.0;
   // Definimos su crecimiento en horizontal.
   layoutConstraints.fill = GridBagConstraints.VERTICAL;
      
   add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);
   
   
   button = new JButton("Boton 5");  
   
   // Lo colocamos en la celda (0, 1).
   layoutConstraints.gridx = 1;
   layoutConstraints.gridy = 1;
   // Definimos su 'span' a 3 celdas horizontales.
   layoutConstraints.gridheight = 3;
   layoutConstraints.gridwidth = 3;
   layoutConstraints.weightx = 1.0;
   layoutConstraints.weighty = 1.0;
   // Definimos su crecimiento en horizontal.
   layoutConstraints.fill = GridBagConstraints.BOTH;
   
   
   add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);
   
   
   
   button = new JButton("Boton 6");  
   
   // Lo colocamos en la celda (4,0).
   layoutConstraints.gridx = 4;
   layoutConstraints.gridy = 1;
   // Definimos su 'span' a 3 celdas horizontales.
   layoutConstraints.gridheight = 3;
   layoutConstraints.weighty = 1.0;
   // Definimos su crecimiento en horizontal.
   layoutConstraints.fill = GridBagConstraints.VERTICAL;
      
   add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);
   
   
   
   button = new JButton("Boton 7");   
   
   // Lo colocamos en la celda (0,0).
   layoutConstraints.gridx = 0;
   layoutConstraints.gridy = 4;
   add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);
   
   
   button = new JButton("Boton 8");
   
   // Lo colocamos en la celda (1,0).
   layoutConstraints.gridx = 1;
   layoutConstraints.gridy = 4;   
   // Definimos su 'span' a 3 celdas horizontales.
   layoutConstraints.gridwidth = 3;
   layoutConstraints.weightx = 1.0;
   // Definimos su crecimiento en horizontal.
   layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
   add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);

   
   
   button = new JButton("Boton 9");  
   
   // Lo colocamos en la celda (0, 1).
   layoutConstraints.gridx = 4;
   layoutConstraints.gridy = 4;
   
   add(button, layoutConstraints);
   // Reiniciamos las 'Constraints' a lo valores basicos.
   UtilLayout.resetConstraints(layoutConstraints);
  }


  
  
  
  public final ImageIcon getComponentIcon() {
   return this.componentIcon;
  }

  public final void setComponentIcon(
         final ImageIcon componentIconParam) {
   this.componentIcon = componentIconParam;
  }

  public final String getComponentTitle() {
   return this.componentTitle;
  }

  public final void setComponentTitle(
         final String componentTitleParam) {
   this.componentTitle = componentTitleParam;
  }
  
  
  
  

 }
