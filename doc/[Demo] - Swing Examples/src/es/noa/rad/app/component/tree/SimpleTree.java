package es.noa.rad.app.component.tree;

 import
  java.awt.Dimension;
 import
  java.awt.GridLayout;
 import
  java.awt.event.ComponentAdapter;
 import
  java.awt.event.ComponentEvent;
 import
  javax.swing.JFrame;
 import
  javax.swing.JPanel;
 import
  javax.swing.JScrollPane;
 import
  javax.swing.JTree;
 import
  javax.swing.UIManager;
 import
  javax.swing.tree.DefaultMutableTreeNode;
 import
  javax.swing.tree.TreeSelectionModel;

 public class SimpleTree extends JPanel {
  private static final long serialVersionUID = 1L;
  private static final String WINDOW_NAME = "Simple Tree Example";
  private static final int WINDOW_MIN_WIDTH = 400;
  private static final int WINDOW_MIN_HEIGHT = 600;
  private static String lineStyle = "Angled"; //"Angled" (default) / "Horizontal" / "None"
  private JTree tree;

  /**
    * Establecemos si utilizamos 'look and feel' del sistema o el generico de
    * 'swing'.
    */
  private static boolean useSystemLookAndFeel = false;
    
  public SimpleTree() {
   /* Establecemos el 'Layout' que utilizara la ventana. */	  
   super(new GridLayout(1,0));
   
   // Creamos los nodos.
   DefaultMutableTreeNode top = new DefaultMutableTreeNode("The Java Series");   
   createNodes(top);
 
   // Creamos el arbol, y establecemos el modo de selección simple.
   tree = new JTree(top);
   tree.getSelectionModel()
    .setSelectionMode(
     TreeSelectionModel.SINGLE_TREE_SELECTION);
   
   // Establecemos propiedades de visualización.
   tree.putClientProperty("JTree.lineStyle", lineStyle);
   
   // creamos un scroll y lo asignamos al arbol
   JScrollPane treeView = new JScrollPane(tree);
 
   add(treeView);
  }

  /**
    * Método generico y privado que establece los valores iniciales del arbol.
    * 
    * @param top
    */
  private final void createNodes(
          final DefaultMutableTreeNode top) {
   DefaultMutableTreeNode category = null;
   DefaultMutableTreeNode book = null;
 
   category = new DefaultMutableTreeNode("Books for Java Programmers");
   top.add(category);
 
   // Original Tutorial
   book = new DefaultMutableTreeNode(
    new BookInfo(
     "The Java Tutorial: A Short Course on the Basics",
     "tutorial.html"));
   category.add(book);
 
   // Tutorial Continued
   book = new DefaultMutableTreeNode(
    new BookInfo(
     "The Java Tutorial Continued: The Rest of the JDK",
     "tutorialcont.html"));
   category.add(book);
 
   // JFC Swing Tutorial
   book = new DefaultMutableTreeNode(
    new BookInfo(
     "The JFC Swing Tutorial: A Guide to Constructing GUIs",
     "swingtutorial.html"));
   category.add(book);
 
   // Bloch
   book = new DefaultMutableTreeNode(
    new BookInfo(
     "Effective Java Programming Language Guide",
     "bloch.html"));
   category.add(book);
 
   // Arnold/Gosling
   book = new DefaultMutableTreeNode(
    new BookInfo(
     "The Java Programming Language",
     "arnold.html"));
   category.add(book);
 
   // Chan
   book = new DefaultMutableTreeNode(
    new BookInfo(
     "The Java Developers Almanac",
     "chan.html"));
   category.add(book);
 
   category = new DefaultMutableTreeNode("Books for Java Implementers");
   top.add(category);
 
   // VM
   book = new DefaultMutableTreeNode(
    new BookInfo(
     "The Java Virtual Machine Specification",
     "vm.html"));
   category.add(book);
 
   // Language Spec
   book = new DefaultMutableTreeNode(
    new BookInfo(
     "The Java Language Specification",
     "jls.html"));
   category.add(book);
  }

  /**
    * Creamos la GUI y la mostramos. Para seguridad del hilo, este método debe
    * invocarse desde el hilo de gestion de eventos.
    */
  private static void createAndShowGUI() {
   if (useSystemLookAndFeel) {
    try {
     UIManager.setLookAndFeel(
      UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
     System.err.println("Couldn't use system look and feel.");
    }
   }
   /* Creamos la ventana y la establecemos. */
   
   /* 
    * Establecemos el nombre de la ventana a traves de la constante generica
    * de la clase.
    */
   JFrame mainFrame = new JFrame(WINDOW_NAME);
   mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   /*
    * Añadimos a la ventana en contenedor principal.
    */
   mainFrame.add(new SimpleTree());

   /*
    * Compactamos y mostramos la ventana.
    */
   mainFrame.pack();
   mainFrame.setVisible(true);
   
   /*
    * Establecemos el control del tamaño para la ventana.
    */
   mainFrame.setMinimumSize(
    new Dimension(WINDOW_MIN_WIDTH, WINDOW_MIN_HEIGHT));
   mainFrame.addComponentListener(
    new ComponentAdapter(){
     public void componentResized(
            final ComponentEvent componentEventParam){
      Dimension dimension = mainFrame.getSize();
      Dimension minDimension = mainFrame.getMinimumSize();
      if(dimension.getWidth() < minDimension.getWidth()) {
       dimension.width = (int) minDimension.getWidth();
      }
      if(dimension.getHeight() < minDimension.getHeight()) {
       dimension.height = (int) minDimension.getHeight();
      }
      mainFrame.setSize(dimension);
     }
    }
   );

  }
  
  public static void main(String[] args) {
   javax.swing.SwingUtilities.invokeLater(
    new Runnable() {
     public void run() {
      createAndShowGUI();
     }
    }
   );
  }

 }