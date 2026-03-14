package es.noa.rad.test.aplication.component.base;

 import
  java.awt.GridLayout;
 import
  java.awt.LayoutManager;
 import
  java.awt.event.ActionEvent;
 import
  java.awt.event.ActionListener;
 import
  javax.swing.ImageIcon;
 import
  javax.swing.JPanel;
 import
  javax.swing.border.Border;
 import
  javax.swing.border.EmptyBorder;

 public class BaseSimpleDemo extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  
  /**
   * Icono principal del componente, se utilizara tanto internamente, por el
   * propio componente, como externamente por los distintos elementos que
   * puedan contener este componente y hacer uso de �l.
   */
  private ImageIcon componentIcon; 

  /**
    * Titulo principal del componente, se utilizara tanto internamente, por el
    * propio componente, como externamente por los distintos elementos que
    * puedan contener este componente y hacer uso de �l.
    */  
  private String componentTitle;

  /**
    * Layout principal del componente, se utilizara internamente, para el
    * posicionamiento de los elementos dentro del componente.
    */
  private LayoutManager componentLayout;    

  /**
    * Margen exterior que se aplica globalmente al componente, d�ndole espacio
    * a su alrededor con el resto de componentes.
    */
  private Border componentMargin;  

  public BaseSimpleDemo(
         final ImageIcon componentIconParam,
         final String componentTitleParam) {
   super();
   this.componentIcon = componentIconParam;
   this.componentTitle = componentTitleParam;
   this.componentLayout = null;
   this.componentMargin = null;
   
   // Definimos el layout
   this.componentLayout = new GridLayout(1, 1);
   this.setLayout(this.componentLayout);
   
   // Definimos el margen del componente.
   this.componentMargin = new EmptyBorder(6, 6, 6, 6);
   this.setBorder(this.componentMargin);

  }
  
  public BaseSimpleDemo() {
   this(null, null);   
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
  
  public final void actionPerformed(
         final ActionEvent actionEventParam) {
  }

 }
