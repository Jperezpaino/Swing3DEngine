package es.noa.rad.demo.swt;

 import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

 public class AntialiasExample {
  /** Variable estatica con el nombre de la ventana por defecto. */
  private static final String SHELLNAME = "Ejemplo SWT Antialias"; //$NON-NLS-1$
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
    * Clase 'Main' que lanzara la aplicación Demo.
    * @param args
    */
  public static void main(String[] args) {
   try {
    AntialiasExample window = new AntialiasExample();
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
   final Canvas canvasNoAlias = new Canvas(this.shell, SWT.NULL);
   final Canvas canvasAlias = new Canvas(this.shell, SWT.NULL);
   canvasNoAlias.setBackground(this.display.getSystemColor(SWT.COLOR_WHITE));
   canvasNoAlias.addPaintListener(new PaintListener() {
    @Override
    public void paintControl(PaintEvent e) {
     paint(e.gc, canvasNoAlias.getBounds().width,  canvasNoAlias.getBounds().height, false);
    }
   });
   canvasAlias.setBackground(this.display.getSystemColor(SWT.COLOR_WHITE));
   canvasAlias.addPaintListener(new PaintListener() {
    @Override
    public void paintControl(PaintEvent e) {
     paint(e.gc, canvasAlias.getBounds().width,  canvasAlias.getBounds().height, true);
    }
   });   
  }

  public void paint(GC gc, int width, int height, boolean isAntialias) {
   if(isAntialias) {
    gc.setAntialias(1);
   } else {
    gc.setAntialias(0);
   }
   
   Path quadCurve = new Path(this.display);
   for(int i = 10; i < 900; i = i + 50) {
    quadCurve.moveTo(10, 10);
    quadCurve.quadTo(150, i, 300, 10);
    gc.drawPath(quadCurve);
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