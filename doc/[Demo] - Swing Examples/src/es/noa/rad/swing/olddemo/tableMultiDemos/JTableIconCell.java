package es.noa.rad.swing.olddemo.tableMultiDemos;

import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class JTableIconCell {
  public static void main(String[] argv) throws Exception {
    DefaultTableModel model = new DefaultTableModel();
    JTable table = new JTable(model);

    model.addColumn("Col1");
    model.addColumn("Col2");
    
    String[] row = { "row1", "row1"};
    model.addRow(row);
    
    table.getTableHeader().getColumnModel().getColumn(1).setHeaderRenderer(new IconHeaderRenderer());

    table.getColumnModel().getColumn(1).setHeaderValue(new TextAndIcon("Col2", new ImageIcon("./img/delete.png")));
    

    JFrame jfrm = new JFrame("JTable Demo");
    jfrm.setLayout(new FlowLayout());
    jfrm.setSize(460, 180);
    jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JScrollPane jscrlp = new JScrollPane(table);
    jfrm.add(jscrlp);
    jfrm.setVisible(true);
  }
}

class TextAndIcon {
  TextAndIcon(String text, Icon icon) {
    this.text = text;
    this.icon = icon;
  }

  String text;

  Icon icon;
}

class IconHeaderRenderer extends DefaultTableCellRenderer {
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
      boolean hasFocus, int row, int column) {
    if (table != null) {
      JTableHeader header = table.getTableHeader();
      if (header != null) {
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
      }
    }
    if (value instanceof TextAndIcon) {
      setIcon(((TextAndIcon) value).icon);
      setText(((TextAndIcon) value).text);
    } else {
      setText((value == null) ? "" : value.toString());
      setIcon(null);
    }
    setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    setHorizontalAlignment(JLabel.CENTER);
    return this;
  }
}