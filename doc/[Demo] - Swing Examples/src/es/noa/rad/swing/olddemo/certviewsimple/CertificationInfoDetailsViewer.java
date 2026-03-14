package es.noa.rad.swing.olddemo.certviewsimple;

 import java.awt.BorderLayout;
import java.awt.CardLayout;
import
  java.awt.Dimension;
 import
  java.awt.GridLayout;
 import
  java.awt.LayoutManager;
 import
  java.awt.event.KeyEvent;
 import
  javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import
  javax.swing.JComponent;
 import
  javax.swing.JLabel;
 import
  javax.swing.JPanel;
 import
  javax.swing.JTabbedPane;
import javax.swing.JTextField;
import
  javax.swing.border.Border;
 import
  javax.swing.border.EmptyBorder;

 public class CertificationInfoDetailsViewer extends JComponent{
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

  
  
  
  
  
  
  
  
  
  public CertificationInfoDetailsViewer() {
   super();
   this.componentIcon = null;
   this.componentTitle = null;
   
   this.componentLayout = null;
   this.componentMargin = null;
     
   // definimos el margen del componente
   this.componentMargin = new EmptyBorder(6, 6, 6, 6);
   this.setBorder(this.componentMargin);
   
   // definimos el layout
   componentLayout = new GridLayout(3,2);
   this.setLayout(componentLayout);
   
   
   // definimos los componentes
  
  }

  protected JComponent makeTextPanel(String text) {
   JPanel panel = new JPanel(false);
   JLabel filler = new JLabel(text);
   filler.setHorizontalAlignment(JLabel.CENTER);
   panel.setLayout(new GridLayout(1, 1));
   panel.add(filler);
   return panel;
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
