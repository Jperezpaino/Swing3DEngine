package es.noa.rad.demos.drawshapes;

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
   graphics2D.drawRect(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */ 
    elementPositionX, elementPositionY, 
    /* Ancho y alto que marca las dimensiones del elemento. */ 
    elementDefaultWidth, elementDefaultHeight);

   /* Dibujamos unos rectangulos horizontales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace; 
   graphics2D.drawRect(
    elementPositionX, elementPositionY,
    elementDefaultWidth, ((elementDefaultHeight / 2) - 2));
   graphics2D.drawRect(
    elementPositionX, elementPositionY + ((elementDefaultHeight / 2) + 2),
    elementDefaultWidth, ((elementDefaultHeight / 2) - 2));
   
   /* Dibujamos unos rectangulos verticales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawRect(
    elementPositionX, elementPositionY,
    ((elementDefaultWidth / 2) - 2), elementDefaultHeight);
   graphics2D.drawRect(
    elementPositionX + ((elementDefaultWidth / 2) + 2), elementPositionY,
    ((elementDefaultWidth / 2) - 2), elementDefaultHeight);

   /* Dibujamos un cuadrado perfecto con bordes redondeados. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace; 
   graphics2D.drawRoundRect(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */ 
    elementPositionX, elementPositionY, 
    /* Ancho y alto que marca las dimensiones del elemento. */ 
    elementDefaultWidth, elementDefaultHeight,
    /* Establece el diámetro horizontal y el diámetro vertical del arco que forma cada una de las esquinas. */ 
    (elementDefaultWidth / 4), (elementDefaultHeight / 4));
   
   /* Dibujamos unos rectangulos horizontales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace; 
   graphics2D.drawRoundRect(
    elementPositionX, elementPositionY,
    elementDefaultWidth, ((elementDefaultHeight /2) - 2),
    (elementDefaultWidth / 3), (elementDefaultHeight / 3));
   graphics2D.drawRoundRect(
    elementPositionX, (elementPositionY + ((elementDefaultHeight / 2) + 2)),
    elementDefaultWidth, ((elementDefaultHeight / 2) - 2),
    (elementDefaultWidth / 3), (elementDefaultHeight / 3));
   
   /* Dibujamos unos rectangulos verticales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace; 
   graphics2D.drawRoundRect(
    (elementPositionX), (elementPositionY),
    ((elementDefaultWidth/2) - 2), (elementDefaultHeight),
    (elementDefaultWidth / 2), (elementDefaultHeight / 2));
   graphics2D.drawRoundRect(
    (elementPositionX + ((elementDefaultWidth / 2) + 2)), (elementPositionY), 
    ((elementDefaultWidth / 2) - 2), (elementDefaultHeight),
    (elementDefaultWidth / 2), (elementDefaultHeight / 2));
   
   /* Dibujamos un circulo perfecto. */
   elementPositionX = elementSpace;
   elementPositionY = elementPositionY + elementDefaultHeight + elementSpace;
   graphics2D.drawOval(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */      
    elementPositionX, elementPositionY,
    /* Ancho y alto que marca las dimensiones del elemento. */     
    elementDefaultWidth, elementDefaultHeight);
   
   /* Dibujamos unos ovalos o elipses horizontales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawOval(
    elementPositionX, elementPositionY,
    elementDefaultWidth, (elementDefaultHeight/2) - 2);
   graphics2D.drawOval(
    elementPositionX, elementPositionY + (elementDefaultHeight/2) + 2, 
    elementDefaultWidth, (elementDefaultHeight/2) - 2);
   
   /* Dibujamos unos ovalos o elipses verticales. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawOval(
    elementPositionX, elementPositionY,
    (elementDefaultWidth/2) - 2, elementDefaultHeight);
   graphics2D.drawOval(
    elementPositionX + (elementDefaultWidth/2) + 2, elementPositionY,
    (elementDefaultWidth/2) - 2, elementDefaultHeight);
   
   /* Dibujamos un arco de circunferencia entre 0º a 90º y otro entre 180º a 270º.*/   
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawArc(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */      
    elementPositionX, elementPositionY,
    /* Ancho y alto que marca las dimensiones del elemento. */        
    elementDefaultWidth, elementDefaultHeight,
    /* Angulo de inicio a partir del que se empezara a dibujar el arco. */
    0,
    /* Avance total en grados desde el angulo de inicio. */
    90);
   graphics2D.drawArc(
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
   graphics2D.drawArc(
    elementPositionX, elementPositionY,
    elementDefaultWidth, (elementDefaultHeight/2) - 2,
    135,
    270);
   graphics2D.drawArc(
    elementPositionX, elementPositionY + (elementDefaultHeight/2) + 2, 
    elementDefaultWidth, (elementDefaultHeight/2) - 2,
    315,
    270);
   
   /* Dibujamos unos arcos de circunferencia en vertical entre 45º a 315º y otro entre 315º a 225º. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawArc(
    elementPositionX, elementPositionY,
    (elementDefaultWidth/2) - 2, elementDefaultHeight,
    45,
    270);
   graphics2D.drawArc(
    elementPositionX + (elementDefaultWidth/2) + 2, elementPositionY,
    (elementDefaultWidth/2) - 2, elementDefaultHeight,
    225,
    270);
   
   /* Dibujamos lineas horizontales rectas. */   
   elementPositionX = elementSpace;
   elementPositionY = elementPositionY + elementDefaultHeight + elementSpace;
   graphics2D.drawLine(
    /* Coordenada que marca el inicio de la linea. */       
    elementPositionX, elementPositionY,
    /* Coordenada que marca el final de la linea. */     
    elementPositionX + elementDefaultWidth, elementPositionY);
   graphics2D.drawLine(
    elementPositionX, elementPositionY + (elementDefaultHeight / 2),
    elementPositionX + elementDefaultWidth, elementPositionY + (elementDefaultHeight / 2));   
   graphics2D.drawLine(
    elementPositionX, elementPositionY + elementDefaultHeight,
    elementPositionX + elementDefaultWidth, elementPositionY + elementDefaultHeight);
   
   /* Dibujamos Lineas verticales rectas. */
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawLine(
    elementPositionX, elementPositionY,
    elementPositionX, elementPositionY + elementDefaultHeight);
   graphics2D.drawLine(
    elementPositionX + ( elementDefaultWidth / 2), elementPositionY,
    elementPositionX + ( elementDefaultWidth / 2), elementPositionY + elementDefaultHeight);
   graphics2D.drawLine(
    elementPositionX + elementDefaultWidth, elementPositionY,
    elementPositionX + elementDefaultWidth, elementPositionY + elementDefaultHeight);
   
   /* Dibujamos lineas oblicuas descendentes. */ 
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawLine(
    elementPositionX, elementPositionY, elementPositionX + elementDefaultWidth,
    elementPositionY + elementDefaultHeight);
   graphics2D.drawLine(
    elementPositionX, elementPositionY + (elementDefaultHeight / 2),
    elementPositionX + ( elementDefaultWidth / 2), elementPositionY + elementDefaultHeight);
   graphics2D.drawLine(
    elementPositionX + ( elementDefaultWidth / 2), elementPositionY,
    elementPositionX + elementDefaultWidth, elementPositionY + (elementDefaultHeight / 2));   
   
   /* Dibujamos lineas oblicuas ascendentes. */ 
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawLine(
    elementPositionX, elementPositionY + elementDefaultHeight,
    elementPositionX + elementDefaultWidth, elementPositionY);
   graphics2D.drawLine(
    elementPositionX, elementPositionY + (elementDefaultHeight / 2),
    elementPositionX + ( elementDefaultWidth / 2), elementPositionY);
   graphics2D.drawLine(
    elementPositionX + ( elementDefaultWidth / 2), elementPositionY + elementDefaultHeight,
    elementPositionX + elementDefaultWidth, elementPositionY + (elementDefaultHeight / 2));
   
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.LIGHT_GRAY);
   graphics2D.draw3DRect(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */ 
    elementPositionX, elementPositionY, 
    /* Ancho y alto que marca las dimensiones del elemento. */ 
    elementDefaultWidth, elementDefaultHeight,
    /* Establece si el relieve es interior (falso) o exterior (true). */
    true);     
   
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.setColor(Color.LIGHT_GRAY);
   graphics2D.draw3DRect(
    /* Coordenada para el inicio del elemento, corresponde a la esquina superior izquierda. */ 
    elementPositionX, elementPositionY, 
    /* Ancho y alto que marca las dimensiones del elemento. */ 
    elementDefaultWidth, elementDefaultHeight, false);
   
   // .. Poligono cerrado de tres lados
   elementPositionX = elementSpace;
   elementPositionY = elementPositionY + elementDefaultHeight + elementSpace;
   graphics2D.setColor(Color.BLACK);   
   graphics2D.drawPolygon(
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
   graphics2D.drawPolygon(
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
   graphics2D.drawPolygon(polygon);
   
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;     
   polygon = new Polygon();
   polygon.addPoint(elementPositionX, elementPositionY + (elementDefaultHeight / 2));
   polygon.addPoint(elementPositionX + ( elementDefaultWidth / 2), elementPositionY + elementDefaultHeight);
   polygon.addPoint(elementPositionX + elementDefaultWidth, elementPositionY + (elementDefaultHeight / 2));
   polygon.addPoint(elementPositionX + ( elementDefaultWidth / 2), elementPositionY);
   graphics2D.drawPolygon(polygon);
     
   
   
   // .. Polilinea de tres puntos
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawPolyline(
    new int[] {
     elementPositionX + ( elementDefaultWidth / 2),
     elementPositionX,
     elementPositionX + elementDefaultWidth,
    },
    new int[] {
     elementPositionY,
     elementPositionY + elementDefaultHeight,
     elementPositionY + (elementDefaultHeight / 2)
    },
    3
   );
   
   // .. Polilinea multi puntos
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   graphics2D.drawPolyline(
    new int[] {
     elementPositionX + ( elementDefaultWidth / 2),
     elementPositionX,
     elementPositionX + elementDefaultWidth,
     elementPositionX,
     elementPositionX + ( elementDefaultWidth / 2),
     elementPositionX + elementDefaultWidth,
     elementPositionX,
     elementPositionX + elementDefaultWidth,
     elementPositionX + ( elementDefaultWidth / 2)
    },
    new int[] {
     elementPositionY,
     elementPositionY + elementDefaultHeight,
     elementPositionY + (elementDefaultHeight / 2),
     elementPositionY,
     elementPositionY + elementDefaultHeight,
     elementPositionY,
     elementPositionY + (elementDefaultHeight / 2),
     elementPositionY + elementDefaultHeight,
     elementPositionY
    },
    9
   );
   
   
   // Establecemos la sincronización en el contexto grafico.
   Toolkit.getDefaultToolkit().sync();
  }

 }
 