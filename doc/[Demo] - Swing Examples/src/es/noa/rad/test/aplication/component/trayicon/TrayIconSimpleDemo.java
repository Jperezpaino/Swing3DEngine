package es.noa.rad.test.aplication.component.trayicon;

 import
  java.awt.AWTException;
 import
  java.awt.GridBagLayout;
 import
  java.awt.Image;
 import
  java.awt.LayoutManager;
 import
  java.awt.SystemTray;
 import
  java.awt.TrayIcon;
 import
  java.awt.event.ActionEvent;
 import
  java.awt.event.ActionListener;
 import
  java.net.URL;
 import
  javax.swing.ImageIcon;
 import
  javax.swing.JPanel;
 import
  javax.swing.border.Border;
 import
  javax.swing.border.EmptyBorder;

 public class TrayIconSimpleDemo extends JPanel implements ActionListener {
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
    * Margen exterior que se aplica globalmente al componente, d�ndole espacio
    * a su alrededor con el resto de componentes.
    */
  private Border componentMargin;  
  /**
    * Layout principal del componente, se utilizara internamente, para el
    * posicionamiento de los elementos dentro del componente.
    */
  private LayoutManager componentLayout;    

  /* 
   * Definimos los Componentes funcionales, es decir, aquellos que tienen
   * funcionalidad y operatibilidad, el resto de componentes se generaran en
   * durante el ciclo de ejecuci�n.
   */
  private TrayIcon simpleTrayIcon;
  
  public TrayIconSimpleDemo() {
   super();
   this.componentIcon = null;
   this.componentTitle = null;   
   this.componentLayout = null;
   this.componentMargin = null;
   
   // Definimos el margen del componente
   this.componentMargin = new EmptyBorder(6, 6, 6, 6);
   this.setBorder(this.componentMargin);
   
   // Definimos el layout
   this.componentLayout = new GridBagLayout();
   this.setLayout(this.componentLayout);
   
   Image trayIconImage = createImageIcon("images/Unknown.png").getImage();
   this.simpleTrayIcon = new TrayIcon(trayIconImage, "Tray Icon Demo");
   
   if (SystemTray.isSupported()) {
    SystemTray systemTray = SystemTray.getSystemTray();
    this.simpleTrayIcon.setImageAutoSize(true);
    this.simpleTrayIcon.addActionListener(
     new ActionListener() {
      public void actionPerformed(ActionEvent e) {
       System.out.println("In here");
       simpleTrayIcon.displayMessage("Tester!", "Some action performed", TrayIcon.MessageType.INFO);
      }
     }
    );
    try {
     systemTray.add(this.simpleTrayIcon);
    } catch (AWTException e) {
     System.err.println("TrayIcon could not be added.");
    }
   }
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

  protected final ImageIcon createImageIcon(
            final String imagePathParam) {
   URL imgURL = getClass().getResource(imagePathParam);
   if (imgURL != null) {
    return new ImageIcon(imgURL, "");
   } else {
    System.err.println("Couldn't find file: " + imagePathParam);
    return null;
   }
  }
 }

