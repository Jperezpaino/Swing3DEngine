package es.noa.rad.swing.olddemo.tableMultiDemos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class ImageIconTable {

    private final int imageHeight = 200;//todo change to desire height
    private JTable table;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImageIconTable().initGUI();
            }
        });
    }

    public void initGUI() {
        JFrame frame = new JFrame();

        ImageIcon icon1 = null;// new ImageIcon( "c://test1.gif"); //its not worked with gif
        ImageIcon icon2 = null;// new ImageIcon( "c://test2.jpg"); //it work with jpg

        //try {
            //BufferedImage bufferedImage1 = ImageIO.read(new File("c://test1.gif"));
            //icon1 = new ImageIcon(bufferedImage1); //it work with all type images
            //todo scale imageHeight
            //BufferedImage bufferedImage2 = ImageIO.read(new File("c://test2.jpg"));
            //icon2 = new ImageIcon(bufferedImage2); //it work with all type images
         icon1 = (ImageIcon) UIManager.getIcon("OptionPane.errorIcon");
         icon2 = (ImageIcon) UIManager.getIcon("OptionPane.informationIcon");         
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/

       /*
       approach 1
       Object[] columnNames = {"name","picture"};
        Object[][] rowData = {{"me", icon1}
                        ,{"boss", icon2}};*/

        //approach 2
        Vector columnNames = new Vector();
        columnNames.add("name");
        columnNames.add("picture");

        Vector rowData = new Vector();
        Vector row1 = new Vector();
        Vector row2 = new Vector();
        row1.add("me");
        row1.add(icon1);
        row2.add("boss");
        row2.add(icon2);
        rowData.add(row1);
        rowData.add(row2);

        DefaultTableModel tableModel = new DefaultTableModel(rowData,columnNames) {
            @Override
            public Class getColumnClass(int column) {
                if (column == 1) return ImageIcon.class;
                return Object.class;
            }
        };

        table = new JTable(tableModel);
        table.setRowHeight(imageHeight);

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}