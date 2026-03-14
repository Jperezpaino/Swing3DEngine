package es.noa.rad.swing.olddemo.treetable;

 import
  java.io.File;
 import
  java.util.Date;

 /**
   * FileSystemModel is a TreeTableModel representing a hierarchical file system.
   * Nodes in the FileSystemModel are FileNodes which, when they are directory
   * nodes, cache their children to avoid repeatedly querying the real file
   * system.
   */
 public class FileSystemModel
        extends AbstractTreeTableModel
        implements TreeTableModel {

 /** Lista con el nombre de cada una de las columnas. */
 static protected String[] columnasNames = {
  "Name", "Size", "Type", "Modified"
 };

 /** Lista con el tipo de clase de cada una las columnas. */
 static protected Class<?>[] columnasTypes = {
  TreeTableModel.class, Integer.class, String.class, Date.class
 };

 

 public final int getChildCount(
        final Object treeTableNodeParam) {
  Object[] treeTableNodeChildrens = this.getChildren(treeTableNodeParam);
  if (treeTableNodeChildrens == null) {
   return 0;
  } else {
   return treeTableNodeChildrens.length;
  }
 }

 public final Object getChild(
        final Object treeTableNodeParam,
        final int positionParam) {
  return this.getChildren(treeTableNodeParam)[positionParam];
 }
 
 protected Object[] getChildren(Object treeTableNodeParam) {
  FileNode fileNode = ((FileNode) treeTableNodeParam);
  return fileNode.getChildren();
 }
 
 
 // The the returned file length for directories.
 public static final Integer ZERO = new Integer(0);

 public FileSystemModel() {
  super(new FileNode(new File(File.separator)));
 }

 //
 // Some convenience methods.
 //

 protected File getFile(Object node) {
  FileNode fileNode = ((FileNode) node);
  return fileNode.getFile();
 }



 //
 // The TreeModel interface
 //


 // The superclass's implementation would work, but this is more efficient.
 public boolean isLeaf(Object node) {
  return getFile(node).isFile();
 }

 //
 // The TreeTableNode interface.
 //

 public int getColumnCount() {
  return columnasNames.length;
 }

 public String getColumnName(int column) {
  return columnasNames[column];
 }

 public Class<?> getColumnClass(int column) {
  return columnasTypes[column];
 }

 public Object getValueAt(Object node, int column) {
  File file = getFile(node);
  try {
   switch (column) {
   case 0:
    return file.getName();
   case 1:
    return file.isFile() ? new Integer((int) file.length()) : ZERO;
   case 2:
    return file.isFile() ? "File" : "Directory";
   case 3:
    return new Date(file.lastModified());
   }
  } catch (SecurityException se) {
  }

  return null;
 }

 public String getColumnName(Object node, int column) {
  return columnasNames[column];
 }
}

/*
 * A FileNode is a derivative of the File class - though we delegate to the File
 * object rather than subclassing it. It is used to maintain a cache of a
 * directory's children and therefore avoid repeated access to the underlying
 * file system during rendering.
 */
class FileNode {
 File file;
 Object[] children;

 public FileNode(File file) {
  this.file = file;
 }

 // Used to sort the file names.
 static private MergeSort fileMS = new MergeSort() {
  public int compareElementsAt(int a, int b) {
   return ((String) toSort[a]).compareTo((String) toSort[b]);
  }
 };

 /**
  * Returns the the string to be used to display this leaf in the JTree.
  */
 public String toString() {
  return file.getName();
 }

 public File getFile() {
  return file;
 }

 /**
  * Loads the children, caching the results in the children ivar.
  */
 protected Object[] getChildren() {
  if (children != null) {
   return children;
  }
  try {
   String[] files = file.list();
   if (files != null) {
    fileMS.sort(files);
    children = new FileNode[files.length];
    String path = file.getPath();
    for (int i = 0; i < files.length; i++) {
     File childFile = new File(path, files[i]);
     children[i] = new FileNode(childFile);
    }
   }
  } catch (SecurityException se) {
  }
  return children;
 }
}