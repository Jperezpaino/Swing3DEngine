package es.noa.rad.demos.fillshapes;

 import java.awt.Color;
import
  java.awt.Dimension;
 import
  java.awt.Graphics;
 import
  java.awt.Graphics2D;
 import
  java.awt.Panel;
import java.awt.Polygon;
import
  java.awt.Toolkit;

 /*
  * Clase principal del ejemplo se representa como un tablero o lienzo donde
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
  
  @Override
  public void paint(final Graphics graphicsParam) {
   /*
    * Sobrescribimos el método encargado de repintar el componente, y le
    * asignamos el contexto gráfico del componente como contexto propio al
    * lienzo.
    */
   super.paint(graphicsParam);
   /*
    * Invocamos al método general de repintado, contiene la ejecución y el
    * código objeto del ejemplo.
    */ 
   this.drawComponent(graphicsParam);
  }

  private void drawComponent(final Graphics graphics) {
   /* 
    * Establecemos el contexto gráfico, de gráficos avanzados o 'Graphics2D',
    * al heredar de la clase 'Graphics' todos sus métodos siguen siendo
    * validos, y a su vez nos permite obtener las mejoras correspondientes del
    * objeto 'Graphics2D'.
    */
   Graphics2D graphics2D = (Graphics2D) graphics;
   
   /*
    * Definimos variables para centralizar los tamaños y las posiciones de los
    * distintos elementos. 
    */
   int elementPositionX = 0;
   int elementPositionY = 0;
   int elementDefaultWidth = 96;
   int elementDefaultHeight = 96;
   int elementSpace = 8;
   
   /* Dibujamos un cuadrado perfecto. */
   elementPositionX = elementSpace;
   elementPositionY = elementSpace;
   graphics2D.setColor(Color.RED);
   graphics2D.fillRect(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */ 
    elementPositionX, elementPositionY, 
    /* Ancho y alto que marca las dimensiones del elemento. */ 
    elementDefaultWidth, elementDefaultHeight);

   /* Dibujamos unos rectangulos horizontales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.GREEN);
   graphics2D.fillRect(
    elementPositionX, elementPositionY,
    elementDefaultWidth, ((elementDefaultHeight / 2) - 2));
   graphics2D.setColor(Color.BLUE);
   graphics2D.fillRect(
    elementPositionX, elementPositionY + ((elementDefaultHeight / 2) + 2),
    elementDefaultWidth, ((elementDefaultHeight / 2) - 2));
   
   /* Dibujamos unos rectangulos verticales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.BLUE);   
   graphics2D.fillRect(
    elementPositionX, elementPositionY,
    ((elementDefaultWidth / 2) - 2), elementDefaultHeight);
   graphics2D.setColor(Color.GREEN);   
   graphics2D.fillRect(
    elementPositionX + ((elementDefaultWidth / 2) + 2), elementPositionY,
    ((elementDefaultWidth / 2) - 2), elementDefaultHeight);

   /* Dibujamos un cuadrado perfecto con bordes redondeados. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.RED);
   graphics2D.fillRoundRect(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */ 
    elementPositionX, elementPositionY, 
    /* Ancho y alto que marca las dimensiones del elemento. */ 
    elementDefaultWidth, elementDefaultHeight,
    /* Establece el diámetro horizontal y el diámetro vertical del arco que forma cada una de las esquinas. */ 
    (elementDefaultWidth / 4), (elementDefaultHeight / 4));
   
   /* Dibujamos unos rectangulos horizontales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.GREEN);   
   graphics2D.fillRoundRect(
    elementPositionX, elementPositionY,
    elementDefaultWidth, ((elementDefaultHeight /2) - 2),
    (elementDefaultWidth / 3), (elementDefaultHeight / 3));
   graphics2D.setColor(Color.BLUE);   
   graphics2D.fillRoundRect(
    elementPositionX, (elementPositionY + ((elementDefaultHeight / 2) + 2)),
    elementDefaultWidth, ((elementDefaultHeight / 2) - 2),
    (elementDefaultWidth / 3), (elementDefaultHeight / 3));
   
   /* Dibujamos unos rectangulos verticales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.BLUE);   
   graphics2D.fillRoundRect(
    (elementPositionX), (elementPositionY),
    ((elementDefaultWidth/2) - 2), (elementDefaultHeight),
    (elementDefaultWidth / 2), (elementDefaultHeight / 2));
   graphics2D.setColor(Color.GREEN);   
   graphics2D.fillRoundRect(
    (elementPositionX + ((elementDefaultWidth / 2) + 2)), (elementPositionY), 
    ((elementDefaultWidth / 2) - 2), (elementDefaultHeight),
    (elementDefaultWidth / 2), (elementDefaultHeight / 2));
   
   /* Dibujamos un circulo perfecto. */
   elementPositionX = elementSpace;
   elementPositionY = elementPositionY + elementDefaultHeight + elementSpace;
   graphics2D.setColor(Color.RED);
   graphics2D.fillOval(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */      
    elementPositionX, elementPositionY,
    /* Ancho y alto que marca las dimensiones del elemento. */     
    elementDefaultWidth, elementDefaultHeight);
   
   /* Dibujamos unos ovalos o elipses horizontales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.GREEN);   
   graphics2D.fillOval(
    elementPositionX, elementPositionY,
    elementDefaultWidth, (elementDefaultHeight/2) - 2);
   graphics2D.setColor(Color.BLUE);   
   graphics2D.fillOval(
    elementPositionX, elementPositionY + (elementDefaultHeight/2) + 2, 
    elementDefaultWidth, (elementDefaultHeight/2) - 2);
   
   /* Dibujamos unos ovalos o elipses verticales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.BLUE);   
   graphics2D.fillOval(
    elementPositionX, elementPositionY,
    (elementDefaultWidth/2) - 2, elementDefaultHeight);
   graphics2D.setColor(Color.GREEN);   
   graphics2D.fillOval(
    elementPositionX + (elementDefaultWidth/2) + 2, elementPositionY,
    (elementDefaultWidth/2) - 2, elementDefaultHeight);
   
   /* Dibujamos un arco de circunferencia entre 0º a 90º y otro entre 180º a 270º.*/   
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.ORANGE);
   graphics2D.fillArc(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */      
    elementPositionX, elementPositionY,
    /* Ancho y alto que marca las dimensiones del elemento. */        
    elementDefaultWidth, elementDefaultHeight,
    /* Angulo de inicio a partir del que se empezara a dibujar el arco. */
    0,
    /* Avance total en grados desde el angulo de inicio. */
    90);
   graphics2D.setColor(Color.YELLOW);
   graphics2D.fillArc(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */      
    elementPositionX, elementPositionY,
    /* Ancho y alto que marca las dimensiones del elemento. */        
    elementDefaultWidth, elementDefaultHeight,
    /* Angulo de inicio a partir del que se empezara a dibujar el arco. */    
    180,
    /* Avance total en grados desde el angulo de inicio. */
    90);
   
   /* Dibujamos unos arcos de circunferencia en horizontal entre 135º a 45º y otro entre 315º a 225º. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.ORANGE);
   graphics2D.fillArc(
    elementPositionX, elementPositionY,
    elementDefaultWidth, (elementDefaultHeight/2) - 2,
    135,
    270);
   graphics2D.setColor(Color.YELLOW);   
   graphics2D.fillArc(
    elementPositionX, elementPositionY + (elementDefaultHeight/2) + 2, 
    elementDefaultWidth, (elementDefaultHeight/2) - 2,
    315,
    270);
   
   /* Dibujamos unos arcos de circunferencia en vertical entre 45º a 315º y otro entre 315º a 225º. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.ORANGE);
   graphics2D.fillArc(
    elementPositionX, elementPositionY,
    (elementDefaultWidth/2) - 2, elementDefaultHeight,
    45,
    270);
   graphics2D.setColor(Color.YELLOW);
   graphics2D.fillArc(
    elementPositionX + (elementDefaultWidth/2) + 2, elementPositionY,
    (elementDefaultWidth/2) - 2, elementDefaultHeight,
    225,
    270);
   
   /* Dibujamos lineas horizontales rectas. */   
   elementPositionX = elementSpace;
   elementPositionY = elementPositionY + elementDefaultHeight + elementSpace;
   
   
   /* Dibujamos Lineas verticales rectas. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
  
   
   /* Dibujamos lineas oblicuas descendentes. */ 
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   
  
   /* Dibujamos lineas oblicuas ascendentes. */ 
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
     
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.LIGHT_GRAY);
   graphics2D.fill3DRect(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */ 
    elementPositionX, elementPositionY, 
    /* Ancho y alto que marca las dimensiones del elemento. */ 
    elementDefaultWidth, elementDefaultHeight,
    /* Establece si el relieve es interior (falso) o exterior (true). */
    true);     
   
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.LIGHT_GRAY);
   graphics2D.fill3DRect(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */ 
    elementPositionX, elementPositionY, 
    /* Ancho y alto que marca las dimensiones del elemento. */ 
    elementDefaultWidth, elementDefaultHeight, false);
   
   // .. Poligono cerrado de tres lados
   elementPositionX = elementSpace;
   elementPositionY = elementPositionY + elementDefaultHeight + elementSpace;
   graphics2D.setColor(Color.CYAN);   
   graphics2D.fillPolygon(
     /* Establece una lista con todas las coordenadas x de los puntos que forman el poligono. */ 
     new int[] {
      elementPositionX,
      elementPositionX,
      elementPositionX + elementDefaultWidth
     },
     /* Establece una lista con todas las coordenadas y de los puntos que forman el poligono. */     
     new int[] {
      elementPositionY,
      elementPositionY + elementDefaultHeight,
      elementPositionY + elementDefaultHeight
      },
     /* Cada par de coordenadas x,y definen un punto */
     /* Cantidad total de puntos que definen el poligono. */
     3
    );
   
   // .. Poligono de cuatro lados
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.MAGENTA);   
   graphics2D.fillPolygon(
    new int[] {
     elementPositionX,
     elementPositionX + ( elementDefaultWidth / 2),
     elementPositionX + elementDefaultWidth,
     elementPositionX + ( elementDefaultWidth / 2)
    },
    new int[] {
     elementPositionY + (elementDefaultHeight / 2),
     elementPositionY + elementDefaultHeight,
     elementPositionY + (elementDefaultHeight / 2),
     elementPositionY
    },
    4
   );
   
   Polygon polygon = null; 
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   polygon = new Polygon();
   polygon.addPoint(elementPositionX, elementPositionY);
   polygon.addPoint(elementPositionX, elementPositionY + elementDefaultHeight);
   polygon.addPoint(elementPositionX + elementDefaultWidth, elementPositionY + elementDefaultHeight);
   graphics2D.setColor(Color.CYAN);
   graphics2D.fillPolygon(polygon);
   
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;     
   polygon = new Polygon();
   polygon.addPoint(elementPositionX, elementPositionY + (elementDefaultHeight / 2));
   polygon.addPoint(elementPositionX + ( elementDefaultWidth / 2), elementPositionY + elementDefaultHeight);
   polygon.addPoint(elementPositionX + elementDefaultWidth, elementPositionY + (elementDefaultHeight / 2));
   polygon.addPoint(elementPositionX + ( elementDefaultWidth / 2), elementPositionY);
   graphics2D.setColor(Color.MAGENTA);   
   graphics2D.fillPolygon(polygon);
     
   
   
   // .. Polilinea de tres puntos
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;

   
   // .. Polilinea multi puntos
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   
   
   
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 