package util;

// Java imports
import javax.swing.*;

// String-focused extension of JComboBox -- workaround for unchecked casting
public class ComboBox extends JComboBox<String> {

    // Instance fields
    private DefaultComboBoxModel<String> model;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public ComboBox() {
        super();
        model = new DefaultComboBoxModel<String>();
        setModel(model);
    }

    // Array constructor
    public ComboBox(String[] items) {
        this();
        setItems(items);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Add an item to the end of the list
    public void add(String item) {
        add(item, model.getSize());
    }

    // Add an item at a particular index in the list
    public void add(String item, int index) {
        int num = model.getSize();
        model.insertElementAt(item,
            index <    0 ?   0 :
            index >= num ? num :
            index
        );
    }

    // Retrieve the item at a particular index in the list
    public String get(int index) {
        return index >= 0 && index < model.getSize() ?
            model.getElementAt(index) : null;
    }

    // Retrieve the selected index, or zero if not available
    public int getSelectedIndex() {
        return Math.max(0, super.getSelectedIndex());
    }

    // Retrieve the number of items in the control
    public int length() {
        return model.getSize();
    }

    // Remove the item at a particular index in the list
    public String removeAt(int index) {

        // Error checking
        if (index < 0 || index >= model.getSize())
            return null;

        // Retrieve and remove the item
        String ret = model.getElementAt(index);
        model.removeElementAt(index);
        return ret;
    }

    // Replace the list of items in the control
    public void setItems(String[] items) {
        int size = model.getSize();

        // Clear the existing list of items
        model.removeAllElements();
        if (items == null) return;

        // Insert the new list of items
        for (int x = 0; x < items.length; x++)
            model.addElement(items[x]);
        setSelectedIndex(Math.max(
            size == items.length ? getSelectedIndex() : 0, 0));
    }

    // Attempt to set the selected item
    public void setSelectedIndex(int index) {
        try { super.setSelectedIndex(index); }
        catch (Exception e) { }
    }

}
