package es.noa.rad.swing.olddemo.tableMultiDemos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

public class JTableDrawIcon {

   public static void main(String[] args) {
       new JTableDrawIcon();
   }

   public JTableDrawIcon() {
       EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
               try {
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
               } catch (ClassNotFoundException ex) {
               } catch (InstantiationException ex) {
               } catch (IllegalAccessException ex) {
               } catch (UnsupportedLookAndFeelException ex) {
               }

               JTable table = new JTable();
               table.setGridColor(Color.LIGHT_GRAY);
               table.setShowGrid(true);
               table.setShowHorizontalLines(true);
               table.setShowVerticalLines(true);                
               table.setModel(new TestTableModel());
               table.getColumn("X").setCellRenderer(new DeleteCellRenderer());
               table.getColumn("X").setCellEditor(new DeleteCellEditor());

               JFrame frame = new JFrame("Test");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setLayout(new BorderLayout());
               frame.add(new JScrollPane(table));
               frame.pack();
               frame.setLocationRelativeTo(null);
               frame.setVisible(true);
           }
       });
   }

   protected class TestTableModel extends AbstractTableModel {

       private List<RowData> rowData;

       public TestTableModel() {
           rowData = new ArrayList<RowData>(25);
           for (int index = 0; index < 10; index++) {
               rowData.add(new RowData(index));
           }
       }

       @Override
       public String getColumnName(int column) {
           return column == 0 ? "Text" : "X";
       }

       @Override
       public boolean isCellEditable(int rowIndex, int columnIndex) {
           RowData rd = rowData.get(rowIndex);
           return rd.isDeletable();
       }

       @Override
       public int getRowCount() {
           return rowData.size();
       }

       @Override
       public int getColumnCount() {
           return 2;
       }

       @Override
       public Object getValueAt(int rowIndex, int columnIndex) {
           RowData rd = rowData.get(rowIndex);
           return columnIndex == 0 ? rd.getText() : rd.isDeletable();
       }

       @Override
       public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
           RowData rd = rowData.get(rowIndex);
           if (columnIndex == 1) {
               if (aValue instanceof Boolean && (Boolean)aValue) {
                   rowData.remove(rd);
                   fireTableRowsDeleted(rowIndex, rowIndex);
               }
           }
       }

   }

   public class RowData {

       private String text;
       private boolean deletable;

       public RowData(int row) {
           text = "Row " + row;
           deletable = Math.round(Math.random() * 1) == 0;
       }

       public String getText() {
           return text;
       }

       public boolean isDeletable() {
           return deletable;
       }        
   }

   public class DeleteCellRenderer extends DefaultTableCellRenderer {

       public DeleteCellRenderer() {
           try {
               BufferedImage img = ImageIO.read(getClass().getResource("./img/delete.png"));
               setIcon(new ImageIcon(img));
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       }

       @Override
       public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
           super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           setText(null);
           if (value instanceof Boolean && (Boolean)value) {
               setEnabled(true);
           } else {
               setEnabled(false);
           }
           return this;
       }        
   }

   public class DeleteCellEditor extends AbstractCellEditor implements TableCellEditor {

       private JLabel label;

       public DeleteCellEditor() {
           label = new JLabel("Delete");
       }

       @Override
       public Object getCellEditorValue() {
           return true;
       }

       @Override
       public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
           SwingUtilities.invokeLater(new Runnable() {
               @Override
               public void run() {
                   stopCellEditing();
               }
           });
           return label;
       }

       @Override
       public boolean isCellEditable(EventObject e) {
           return true;
       }        
   }    
}