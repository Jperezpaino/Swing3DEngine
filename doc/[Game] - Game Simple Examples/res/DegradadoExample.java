package es.noa.rad.demo.swt;

 import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

 public class DegradadoExample {
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
    DegradadoExample window = new DegradadoExample();
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

  public void paint(GC gc, int width, int height) {
   int xElement = 0;
   int yElement = 0;
   int defWidth = 150;
   int defHeight = 140;
   int space = 7;
   Pattern gradient;
   gc.setAntialias(0);
   
   // .. Cuadrado
   xElement = space;
   yElement = space;
   //gc.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
   //gc.setForeground(display.getSystemColor(SWT.COLOR_CYAN));
   gradient = new Pattern(this.display, xElement, yElement, xElement + defWidth, yElement + defHeight, this.display.getSystemColor(SWT.COLOR_BLACK), this.display.getSystemColor(SWT.COLOR_WHITE));
   gc.setBackgroundPattern(gradient);
   gc.fillRectangle(xElement, yElement, defWidth, defHeight);
   gradient.dispose();
   
   xElement = xElement + defWidth + space;
   gradient = new Pattern(this.display, xElement + defWidth, yElement, xElement, yElement + defHeight, this.display.getSystemColor(SWT.COLOR_BLACK), this.display.getSystemColor(SWT.COLOR_WHITE));
   gc.setBackgroundPattern(gradient);   
   gc.fillRectangle(xElement, yElement, defWidth, defHeight);
   gradient.dispose();
   
   xElement = xElement + defWidth + space;
   gradient = new Pattern(this.display, xElement, yElement + defHeight,  xElement + defWidth, yElement, this.display.getSystemColor(SWT.COLOR_BLACK), this.display.getSystemColor(SWT.COLOR_WHITE));
   gc.setBackgroundPattern(gradient);   
   gc.fillRectangle(xElement, yElement, defWidth, defHeight);
   gradient.dispose();
   
   xElement = xElement + defWidth + space;
   gradient = new Pattern(this.display, xElement + defWidth, yElement + defHeight, xElement, yElement, this.display.getSystemColor(SWT.COLOR_BLACK), this.display.getSystemColor(SWT.COLOR_WHITE));
   gc.setBackgroundPattern(gradient);   
   gc.fillRectangle(xElement, yElement, defWidth, defHeight);
   gradient.dispose();
   
   xElement = space;
   yElement = yElement + defHeight + space;
   gradient = new Pattern(this.display, xElement, yElement, xElement, yElement + defHeight, this.display.getSystemColor(SWT.COLOR_BLACK), this.display.getSystemColor(SWT.COLOR_WHITE));
   gc.setBackgroundPattern(gradient);   
   gc.fillRectangle(xElement, yElement, defWidth, defHeight);
   gradient.dispose();
   
   xElement = xElement + defWidth + space;
   gradient = new Pattern(this.display, xElement, yElement + defHeight, xElement, yElement, this.display.getSystemColor(SWT.COLOR_BLACK), this.display.getSystemColor(SWT.COLOR_WHITE));
   gc.setBackgroundPattern(gradient);   
   gc.fillRectangle(xElement, yElement, defWidth, defHeight);
   gradient.dispose();
   
   xElement = xElement + defWidth + space;
   gradient = new Pattern(this.display, xElement, yElement, xElement + defWidth, yElement, this.display.getSystemColor(SWT.COLOR_BLACK), this.display.getSystemColor(SWT.COLOR_WHITE));
   gc.setBackgroundPattern(gradient);   
   gc.fillRectangle(xElement, yElement, defWidth, defHeight);
   gradient.dispose();
   
   xElement = xElement + defWidth + space;
   gradient = new Pattern(this.display, xElement + defWidth, yElement, xElement, yElement, this.display.getSystemColor(SWT.COLOR_BLACK), this.display.getSystemColor(SWT.COLOR_WHITE));
   gc.setBackgroundPattern(gradient);   
   gc.fillRectangle(xElement, yElement, defWidth, defHeight);
   gradient.dispose();
   
   xElement = space;
   yElement = yElement + defHeight + space;
   gc.drawRectangle(xElement, yElement, defWidth, defHeight);
   
   xElement = xElement + defWidth + space;
   gc.drawRectangle(xElement, yElement, defWidth, defHeight);
   
   xElement = xElement + defWidth + space;
   gc.drawRectangle(xElement, yElement, defWidth, defHeight);
   
   xElement = xElement + defWidth + space;
   gc.drawRectangle(xElement, yElement, defWidth, defHeight);
   
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