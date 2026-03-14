package es.noa.rad.test.aplication.util;

 import
  java.awt.Component;
 import
  java.awt.Dimension;
 import
  java.awt.Point;
 import
  java.awt.Toolkit;

 public class ComponentUtil {

  /**
    * 
    * M�todo que establece el componente en el centro de la pantalla.
    * 
    * @param componentParam
    */
  public static void centerComponentOnScreen(
         final Component componentParam) {
   Toolkit toolkit = Toolkit.getDefaultToolkit();
   Dimension dimension = toolkit.getScreenSize();
   
   // Definimos el punto central de la pantalla.
   Point point = new Point();
   point.x += ((dimension.width - componentParam.getWidth()) / 2);
   point.y += ((dimension.height - componentParam.getHeight()) / 2);
   
   // En caso de un posicionamiento X menor que cero, lo establecemos a 0.
   if (point.x < 0) {
    point.x = 0;
   }
   
   // En caso de un posicionamiento Y menor que cero, lo establecemos a 0.
   if (point.y < 0) {
    point.y = 0;
   }
   
   // Establecemos el componente en el centro de la pantalla.
   componentParam.setLocation(point);
  }

 }