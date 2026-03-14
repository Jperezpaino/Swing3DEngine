package es.noa.rad.demos.window;

 import
  java.awt.EventQueue;
 import
  java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

 public class Window extends Frame {
  private static final long serialVersionUID = 1L;

  public Window() {
   /*
    * Ejecutamos el método que engloba la configuración para el inicio de la
    * pantalla de juego y establece como capa o tablero de juego el código
    * correspondiente a este ejemplo.
    */
   this.initUI();
  }

  private void initUI() {
   /*
    * Añadimos la capa o tablero que contiene el código del ejemplo.
    */
   this.add(new Board());
   /*
    * Establecemos que el contenedor de la aplicación no pueda ser dimensionado
    * manualmente.
    */
   this.setResizable(false);
   /*
    * Este método adapta el tamaño de la pantalla al tamaño establecido por su
    * contenido, en este caso al tamaño de la capa o tablero del juego.
    */
   this.pack();   
   /*
    * Establecemos el nombre de la aplicación, que sera visible en el nombre de
    * la ventana.
    */
   this.setTitle("Window Demo");
   /*
    * Establecemos la función para responder a la acción de cerrar la ventana,
    * fuerza a finalizar la aplicación cuando se pulsa en botón de cerrar, no
    * suele ser el comportamiento habitual pero para los ejemplos es suficiente
    * esta acción.
    */
   this.addWindowListener(
    new WindowListener() {    
     @Override
     public void windowOpened(final WindowEvent windowEventParam) {}   
     @Override
     public void windowIconified(final WindowEvent windowEventParam) {}    
     @Override
     public void windowDeiconified(final WindowEvent windowEventParam) {}    
     @Override
     public void windowDeactivated(final WindowEvent windowEventParam) {}    
     @Override
     public void windowClosing(final WindowEvent windowEventParam) {
      System.exit(0);
     }    
     @Override
     public void windowClosed(final WindowEvent windowEventParam) {}    
     @Override
     public void windowActivated(final WindowEvent windowEventParam) {}
    }
   );
   /*
    * No establecemos una posición relativa por lo que la ventana se centrara
    * en la pantalla.
    */
   this.setLocationRelativeTo(null);
  }

  public static void main(String[] args) {
   /*
    * Creamos una nueva instancia del código del objeto y lo ejecutamos
    * haciéndolo visible.
    */
   EventQueue.invokeLater(
    new Runnable() {
     public void run() {
      Window windowDemo = new Window();
      windowDemo.setVisible(true);
     }
    }
   );
  }

 }