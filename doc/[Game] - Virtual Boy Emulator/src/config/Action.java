package config;

// Java imports
import java.util.*;
import javax.swing.*;

// Project imports
import config.*;

// User-configurable action command
public class Action {

    // Instance fields
    private KeyStroke accelerator; // Menu item accelerator
    private String    key;         // Unique ID
    private Object    value;       // Action handler or mask
    private ArrayList<Input>     inputs; // User inputs
    private ArrayList<JMenuItem> menus;  // Menu items



    ///////////////////////////////////////////////////////////////////////////
    //                              Interfaces                               //
    ///////////////////////////////////////////////////////////////////////////

    // Functional interface for handling action commands
    public interface Command { void invoke(); }



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public Action(String key) {
        this(key, (Object) null);
    }

    // Object constructor
    public Action(String key, Object value) {
        inputs     = new ArrayList<Input>();
        this.key   = key;
        menus      = new ArrayList<JMenuItem>();
        this.value = value;
    }

    // Command constructor
    public Action(String key, Command command) {
        this(key, (Object) command);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Add an input to the list
    public void add(Input input) {
        if (inputs.contains(input))
            return;
        inputs.add(input);
        updateMenus();
    }

    // Add a menu item to the list
    public void add(JMenuItem item) {
        menus.add(item);
        updateMenus();
    }

    // Remove all inputs from the list
    public void clear() {
        inputs.clear();
        updateMenus();
    }

    // Retrieve the controller key mask for this action
    public int getKeyMask() {
        return value != null && value instanceof Integer ? (Integer) value : 0;
    }

    // Invoke the action's handler
    public boolean invoke() {
        if (!isCommand())
            return false;
        ((Command) value).invoke();
        return true;
    }

    // Determine whether the action represents a command
    public boolean isCommand() {
        return value != null && value instanceof Command;
    }

    // Retrieve the action's inputs as an array
    public Input[] list() {
        return inputs.toArray(new Input[inputs.size()]);
    }

    // Remove an input from the list
    public void remove(Input input) {
        if (!inputs.contains(input))
            return;
        inputs.remove(input);
        updateMenus();
    }

    // Specify a new action handler
    public void set(Object value) {
        this.value = value;
    }

    // Specify a new action handler
    public void set(Command command) {
        this.value = value;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update all attached menu items with the current accelerator
    private void updateMenus() {
        KeyStroke accel = null;

        // Find the first input with a valid accelerator configuration
        for (int x = 0; x < inputs.size(); x++) {
            Input input = inputs.get(x);
            if (input.source != Input.KEY)
                continue;
            accel = KeyStroke.getKeyStroke(input.value, input.mods);
            break;
        }

        // Update menu items
        for (int x = 0; x < menus.size(); x++)
            menus.get(x).setAccelerator(accel);
    }

}
