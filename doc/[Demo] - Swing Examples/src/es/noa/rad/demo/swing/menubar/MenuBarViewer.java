package es.noa.rad.demo.swing.menubar;

 import
  java.awt.GridLayout;
 import
  java.awt.LayoutManager;
 import
  javax.swing.ImageIcon;
 import
  javax.swing.JComponent;
 import
  javax.swing.border.Border;
 import
  javax.swing.border.EmptyBorder;

 public class MenuBarViewer extends JComponent{
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

  public MenuBarViewer() {
   super();
   this.componentIcon = null;
   this.componentTitle = null;   
   this.componentLayout = null;
   this.componentMargin = null;
     
   // Definimos el margen del componente.
   this.componentMargin = new EmptyBorder(6, 6, 6, 6);
   this.setBorder(this.componentMargin);
   
   // Definimos el layout.
   componentLayout = new GridLayout(1, 1);
   this.setLayout(componentLayout);
      
   // Definimos los componentes.
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
