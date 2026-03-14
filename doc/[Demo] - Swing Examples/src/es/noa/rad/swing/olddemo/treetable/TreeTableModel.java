package es.noa.rad.swing.olddemo.treetable;

 import
  javax.swing.tree.TreeModel;

 /** 
   * TreeTableModel es el modelo utilizado por JTreeTable, xxtiende del objeto
   * basico 'TreeModel' para agregar métodos para obtener información sobre el
   * conjunto de columnas que puede tener cada nodo en TreeTableModel.
   * Cada columna, como una columna en un modelo de tabla, tiene un nombre y un
   * tipo asociado a ella. Cada nodo en TreeTableModel puede devolver un valor
   * para cada una de las columnas y establecer ese valor si isCellEditable() 
   * devuelve verdadero.
   */
 public interface TreeTableModel extends TreeModel {
  /**
    * Returns the number ofs availible column.
    */
  public int getColumnCount();

  /**
    * Returns the name for column number <code>column</code>.
    */
  public String getColumnName(int column);

  /**
    * Returns the type for column number <code>column</code>.
    */
  public Class<?> getColumnClass(int column);

  /**
    * Returns the value to be displayed for node <code>node</code>,
    * at column number <code>column</code>.
    */
  public Object getValueAt(Object node, int column);

  /**
    * Indicates whether the the value for node <code>node</code>,
    * at column number <code>column</code> is editable.
    */
  public boolean isCellEditable(Object node, int column);

  /**
    * Sets the value for node <code>node</code>,
    * at column number <code>column</code>.
    */
  public void setValueAt(Object aValue, Object node, int column);
 }