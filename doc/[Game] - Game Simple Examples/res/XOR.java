package es.noa.rad.demo.swt;

 import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

 public class XOR {
  /** Variable estatica con el nombre de la ventana por defecto. */
  private static final String SHELLNAME = "";
  /** Variable estatica con el ancho de la ventana por defecto. */
  private static final int SHELLWIDTH = 640;
  /** Variable estatica con el alto de la ventana por defecto. */
  private static final int SHELLHEIGHT = 480;
  /** Variable estatica con la posición x de la ventana por defecto. */
  private static final int WINDOWPOSITIONX = 10;
  /** Variable estatica con la posición y de la ventana por defecto. */
  private static final int WINDOWPOSITIONY = 10;
  /** Variable final que contendra el display de la aplicación. */
  protected Display display;
  /** Variable final que contendra el shell o frame principal. */
  protected Shell shell; 
  
  /**
    * Clase 'Main' que lanzara la aplicaci�n Demo.
    * @param args
    */
  public static void main(String[] args) {
   try {
    XOR window = new XOR();
    window.open();
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
  
  /**
    * Metodo generico que se encarga de la inicialización y construcción de la
    * ventana de la aplicación.
    */
  public void open() {
   this.display = Display.getDefault();
   // Método que genera los componentes que forman parte de la ventana.  
   this.createContents();
   this.shell.open();
   this.shell.setVisible(true);
   this.shell.layout();
   while (!this.shell.isDisposed()) {
    if (!this.display.readAndDispatch()) {
     this.display.sleep();
    }
   }
   //Limpiamos y liberamos el Display de la memoria
   this.display.dispose();   
  }

  /**
    * Genera los componentes que forman parte de la ventana.
    */
  private void createContents() {
   this.shell = new Shell(this.display, SWT.TITLE | SWT.CLOSE | SWT.BORDER);   
   // Establecemos el tamaño de la ventana.
   this.shell.setSize(SHELLWIDTH, SHELLHEIGHT);
   // Establecemos el titulo de la ventana.
   this.shell.setText(SHELLNAME);
   //Establecemos la posición de la ventana dentro de la pantalla.
   this.shell.setLocation(WINDOWPOSITIONX, WINDOWPOSITIONY);
   // Centramos la ventana.
   centerWindow(this.display, this.shell);
   // Construimos los componentes.
   this.shell.setLayout(new FillLayout());
   final Canvas canvas = new Canvas(this.shell, SWT.NULL);
   canvas.setBackground(this.display.getSystemColor(SWT.COLOR_WHITE));
   canvas.addPaintListener(new PaintListener() {
    @Override
    public void paintControl(PaintEvent e) {
     paint(e.gc, canvas.getBounds().width,  canvas.getBounds().height);
    }
   });
  }
/*
  public void paint(GC gc, int width, int height) {
   //gc.setXORMode(true);
   int positionX = (width / 2) - 150;
   int positionY = (height / 2) - 150;
   
   gc.setBackground(this.display.getSystemColor(SWT.COLOR_GREEN));
   gc.fillOval(positionX, positionY, 300, 300); // Centro
   
   gc.setBackground(this.display.getSystemColor(SWT.COLOR_RED));
   gc.fillOval(positionX - 150, positionY, 300, 300); //Izquierda
   
   gc.setBackground(this.display.getSystemColor(SWT.COLOR_RED));
   gc.fillOval(positionX + 150, positionY, 300, 300); //Derecha
   
   gc.setBackground(this.display.getSystemColor(SWT.COLOR_BLUE));
   gc.fillOval(positionX, positionY - 150, 300, 300); //Arriba
   
   gc.setBackground(this.display.getSystemColor(SWT.COLOR_BLUE));
   gc.fillOval(positionX, positionY + 150, 300, 300); //Abajo 
   
   gc.dispose();
  }*/
  
  public void paint(GC gc, int width, int height) {

   
   gc.setAntialias(1);
   
   
   


   



   
   
   
   // .. Linea curva bézier
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   Path bezierCurve = new Path(this.display);
   int[] bqX = { elementPositionX, elementPositionX + elementDefaultWidth, elementPositionX, elementPositionX + elementDefaultWidth};
   int[] bqY = { elementPositionY, elementPositionY, elementPositionY + elementDefaultHeight, elementPositionY + elementDefaultHeight};
   bezierCurve.moveTo(bqX[0], bqY[0]);
   bezierCurve.cubicTo(bqX[1], bqY[1], bqX[2], bqY[2], bqX[3], bqY[3]);
   gc.drawPath(bezierCurve);
   
   // .. Linea curva cuadratica
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   Path quadCurve = new Path(this.display);
   int[] qcX = { elementPositionX, elementPositionX + elementDefaultWidth, elementPositionX + elementDefaultWidth};
   int[] qcY = { elementPositionY, elementPositionY, elementPositionY + elementDefaultHeight};
   quadCurve.moveTo(qcX[0], qcY[0]);
   quadCurve.quadTo(qcX[1], qcY[1], qcX[2], qcY[2]);
   gc.drawPath(quadCurve);
   
   // .. Path a partir de una String
   elementPositionX = elementSpace;
   elementPositionY = elementPositionY + elementDefaultHeight + elementSpace;
   Path textpath = new Path(this.display);
   FontData data = this.display.getSystemFont().getFontData()[0];
   Font font = new Font(this.display, data.getName(), 30, SWT.BOLD | SWT.ITALIC);
   textpath.addString("SWT", elementPositionX, elementPositionY, font); //$NON-NLS-1$
   gc.drawPath(textpath);
   
   // .. Puntos
   elementPositionX = elementPositionX + elementDefaultWidth + elementSpace;
   for(int i = 0; i < elementDefaultWidth; i = i + 2) {
    gc.drawPoint(elementPositionX + i, elementPositionY + i);
   }



  
   gc.dispose();
  }
  
  /**
    * Metodo generico que se encarga de centrar la ventana de la aplicación en
    * el centro de la pantalla.
    */
  private static void centerWindow(final Display displayParam, final Shell shellParam) {
   //Recuperamos el objeto del monitor principal
   Monitor primary = displayParam.getPrimaryMonitor();
   //Obtenemos las dimensiones del monitor principal
   Rectangle bounds = primary.getBounds();
   //Calculamos la posici�n segun el centro y el tama�o de la ventana
   int positionX = bounds.x + (bounds.width - shellParam.getBounds().width) / 2;
   int positionY = bounds.y + (bounds.height - shellParam.getBounds().height) / 2;
   //Establecemos la posición de la ventana dentro de la pantalla
   shellParam.setLocation(positionX, positionY);
  }
  
 }