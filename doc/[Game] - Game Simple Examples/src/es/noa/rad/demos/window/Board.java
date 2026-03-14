package es.noa.rad.demos.window;

 import
  java.awt.Dimension;
 import
  java.awt.Panel;

 /*
  * Clase principal del ejemplo, se representa como un tablero o lienzo donde
  * se aplicaran las técnicas de dibujado y este se mostrara en la pantalla, a
  * su vez implementara los métodos que nos permitirá interactuar con el mismo.
  */ 
 public class Board extends Panel {
  private static final long serialVersionUID = 1L;
  private final int PANEL_WIDTH = 800; /* Ancho que tendrá el lienzo. */
  private final int PANEL_HEIGHT = 600; /* Alto que tendrá el lienzo. */
  
  public Board() {
   /*
    * Inicializamos el lienzo con el tamaño que hemos definido en las
    * constantes.
    */
   super.setPreferredSize(
    new Dimension(PANEL_WIDTH, PANEL_HEIGHT));  
  }

 }
 
 /*The Board is a panel where the game takes place.*/ 