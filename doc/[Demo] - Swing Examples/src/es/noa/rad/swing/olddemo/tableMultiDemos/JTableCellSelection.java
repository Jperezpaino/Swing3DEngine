package es.noa.rad.swing.olddemo.tableMultiDemos;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class JTableCellSelection {
  public static void main(String[] argv) throws Exception {
    int rows = 10;
    int cols = 5;
    JTable table = new JTable(rows, cols);

    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    table.setColumnSelectionAllowed(true);
    table.setRowSelectionAllowed(true);

    int row = 2;
    int col = 1;
    boolean toggle = false;
    boolean extend = false;
    table.changeSelection(row, col, toggle, extend);
    
    
    JFrame jfrm = new JFrame("JTable Demo");
    jfrm.setLayout(new FlowLayout());
    jfrm.setSize(460, 180);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane jscrlp = new JScrollPane(table);
    jfrm.add(jscrlp);
    jfrm.setVisible(true);   
  }
}