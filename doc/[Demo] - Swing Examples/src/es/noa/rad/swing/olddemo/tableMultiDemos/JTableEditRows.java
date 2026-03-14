package es.noa.rad.swing.olddemo.tableMultiDemos;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class JTableEditRows {

    public static void main(String[] args) {
        new JTableEditRows();
    }

    public JTableEditRows() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                DefaultTableModel model = new DefaultTableModel();
                for (int index = 0; index < 13; index++) {
                    model.addColumn(Character.toString((char) (index + 65)));
                }
                for (int row = 0; row < 100; row++) {
                    Vector<String> rowData = new Vector<>(13);
                    for (int col = 0; col < 13; col++) {
                        rowData.add(row + "x" + col);
                    }
                    model.addRow(rowData);
                }

                JTable table = new JTable(model);
                DeleteRowFromTableAction deleteAction = new DeleteRowFromTableAction(table, model);

                JToolBar tb = new JToolBar();
                tb.add(deleteAction);

                InputMap im = table.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
                ActionMap am = table.getActionMap();
                im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteRow");
                am.put("deleteRow", deleteAction);

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(tb, BorderLayout.NORTH);
                frame.add(new JScrollPane(table));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public abstract class AbstractTableAction<T extends JTable, M extends TableModel> extends AbstractAction {

        private T table;
        private M model;

        public AbstractTableAction(T table, M model) {
            this.table = table;
            this.model = model;
        }

        public T getTable() {
            return table;
        }

        public M getModel() {
            return model;
        }

    }

    public class DeleteRowFromTableAction extends AbstractTableAction<JTable, DefaultTableModel> {

        public DeleteRowFromTableAction(JTable table, DefaultTableModel model) {
            super(table, model);
            putValue(NAME, "Delete selected rows");
            putValue(SHORT_DESCRIPTION, "Delete selected rows");
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    setEnabled(getTable().getSelectedRowCount() > 0);
                }
            });
            setEnabled(getTable().getSelectedRowCount() > 0);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("...");
            JTable table = getTable();
            if (table.getSelectedRowCount() > 0) {
                List<Vector> selectedRows = new ArrayList<>(25);
                DefaultTableModel model = getModel();
                Vector rowData = model.getDataVector();
                for (int row : table.getSelectedRows()) {
                    int modelRow = table.convertRowIndexToModel(row);
                    Vector rowValue = (Vector) rowData.get(modelRow);
                    selectedRows.add(rowValue);
                }

                for (Vector rowValue : selectedRows) {
                    int rowIndex = rowData.indexOf(rowValue);
                    model.removeRow(rowIndex);
                }
            }
        }

    }

}