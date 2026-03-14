package es.noa.rad.swing.olddemo.tableMultiDemos;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class JTableSingleSelection {

  String[] headings = { "Name", "Customer ID","Order #", "Status" };

  Object[][] data = {
    { "A", new Integer(3), "0", new Date() },
    { "B", new Integer(6), "4", new Date() },
    { "C", new Integer(9), "9", new Date() },
    { "D", new Integer(7), "1", new Date() },
    { "E", new Integer(4), "1", new Date() },
    { "F", new Integer(8), "2", new Date() },
    { "G", new Integer(6), "1", new Date() }
  };

  JTable jtabOrders = new JTable(data, headings);

  JTableSingleSelection() {
    JFrame jfrm = new JFrame("JTable Demo");
    jfrm.setLayout(new FlowLayout());
    jfrm.setSize(460, 180);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane jscrlp = new JScrollPane(jtabOrders);
    jfrm.add(jscrlp);
    jtabOrders.setPreferredScrollableViewportSize(new Dimension(420, 60));

    jtabOrders.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    jfrm.setVisible(true);
  
    jtabOrders.setColumnSelectionAllowed(false);
    jtabOrders.setRowSelectionAllowed(true);
  }

  public static void main(String args[]) {
     new JTableSingleSelection();
  }
}