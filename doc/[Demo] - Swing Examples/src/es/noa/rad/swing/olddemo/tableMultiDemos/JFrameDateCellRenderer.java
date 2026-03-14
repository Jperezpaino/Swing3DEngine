package es.noa.rad.swing.olddemo.tableMultiDemos;

import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class JFrameDateCellRenderer {
public static void main(String[] args) {
    Object[][] data = {
                       {"Amar", "2013-01-25"},
                       {"Sammy", "2013-01-25"} 
                      };
    Object[] columnNames = {"Name", "Date"};
    JTable table = new JTable(data, columnNames);
    table.getColumnModel().getColumn(1).setCellRenderer(new DateRenderer());
    JFrame frame = new JFrame();
    frame.add(new JScrollPane(table));
    frame.setSize(new Dimension(400, 100));
    frame.setVisible(true);
}
}


class DateRenderer extends DefaultTableCellRenderer {

private static final long serialVersionUID = 1L;
private Date dateValue;
private SimpleDateFormat sdfNewValue = new SimpleDateFormat("EE MMM dd hh:mm:ss z yyyy");
private String valueToString = "";

@Override
public void setValue(Object value) {
    if ((value != null)) {
        String stringFormat = value.toString();
        try {
            dateValue = new SimpleDateFormat("yyyy-mm-dd", Locale.ENGLISH).parse(stringFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        valueToString = sdfNewValue.format(dateValue);
        value = valueToString;
    }
    super.setValue(value);
}
}