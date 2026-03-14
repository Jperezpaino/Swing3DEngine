package es.noa.rad.swing.olddemo.tableMultiDemos;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.List;

public class TestTableMain {
    public static void main(String[] args) {
        new TestTableMain();
    }

    public TestTableMain() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                buildAndShowMainFrame();
            }
        });
    }

    private void buildAndShowMainFrame() {
        JFrame frame = new JFrame();
        JScrollPane scrollPane = new JScrollPane();

        TestTableModel model = new TestTableModel();
        JTable table = new JTable(model);

        TableRowSorter<TestTableModel> rowSorter = new TableRowSorter<>(model);
        rowSorter.setRowFilter(null);

        model.add("First added item.");
        /* The RowSorter doesn't observe the TableModel directly. Instead,
         * the JTable observes the TableModel and notifies the RowSorter
         * about changes. At this point, the RowSorter(s) internal variable
         * modelRowCount is incorrect.  There are two easy ways to fix this:
         *
         * 1. Don't add data to the model until the RowSorter has been
         * attached to the JTable.
         *
         * 2. Notify the RowSorter about model changes just prior to
         * attaching it to the JTable.
         */

        // Uncomment the next line to notify rowSorter that you've changed
        // the model it's using prior to attaching it to the table.
        //rowSorter.modelStructureChanged();
        table.setRowSorter(rowSorter);

        scrollPane.setViewportView(table);
        frame.setContentPane(scrollPane);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        table.setRowSorter(null);
        model.add("Second added item.");
        rowSorter.modelStructureChanged();
        table.setRowSorter(rowSorter);
    }

    private class TestTableModel extends AbstractTableModel {
        private List<String> items = new ArrayList<>();

        public TestTableModel() {
            for(int i=0;i<5;i++) {
                add("Item " + i);
            }
        }

        @Override
        public int getRowCount() {
            return items.size();
        }

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return items.get(rowIndex);
        }

        public void add(String item) {
            items.add(item);
            fireTableRowsInserted(items.size() - 1, items.size() - 1);
        }
    }
}