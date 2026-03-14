package config;

// Java imports
import java.awt.event.*;
import java.util.zip.*;

// Discrete user input
public class Input {

    // Instance fields
    public final int source; // Input device
    public final int mods;   // Modifier keys
    public final int value;  // Key or button code



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Source IDs
    public static final int UNDEFINED = -1; // Invalid source
    public static final int KEY       =  0; // Keyboard
    public static final int MOUSE     =  1; // Mouse button
    public static final int WHEEL     =  2; // Mouse wheel

    // Mouse wheel directions
    public static final int UP   = -1;
    public static final int DOWN =  1;

    // Alt+F4 keyboard combination
    public static final Input ALT_F4 =
        new Input(KEY, InputEvent.ALT_DOWN_MASK, KeyEvent.VK_F4);



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public Input(int source, int mods, int value) {
        this.source = source;
        this.mods   = mods;
        this.value  = value;
    }

    // String constructor
    public Input(String text) {
        String[] args = text == null ? new String[0] : text.split(" ");
        int[]    vals = new int[args.length];

        try {
            vals[0] = Integer.parseInt(args[0]);
            vals[1] = Integer.parseInt(args[1]);
            vals[2] = Integer.parseInt(args[2]);
        } catch (Exception e) { }

        source = vals.length >= 1 ? vals[0] : UNDEFINED;
        mods   = vals.length >= 2 ? vals[1] : 0;
        value  = vals.length >= 3 ? vals[2] : 0;
    }

    // Keyboard constructor
    public Input(KeyEvent e) {
        switch (e.getID()) {
            case KeyEvent.KEY_PRESSED:
            case KeyEvent.KEY_RELEASED:
                source = KEY; break;
            default: source = UNDEFINED;
        }
        mods  = e.getModifiersEx();
        value = e.getKeyCode();
    }

    // Mouse constructor
    public Input(MouseEvent e) {
        switch (e.getID()) {
            case MouseEvent.MOUSE_PRESSED:
            case MouseEvent.MOUSE_RELEASED:
                source = MOUSE; break;
            default: source = UNDEFINED;
        }
        mods  = e.getModifiersEx();
        value = e.getButton();
    }

    // Mouse wheel constructor
    public Input(MouseWheelEvent e) {
        int rot = e.getWheelRotation();
        source = WHEEL;
        mods   = e.getModifiersEx();
        value  = rot < 0 ? UP : rot > 0 ? DOWN : 0;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Determine whether this object is equivalent to another
    public boolean equals(Object o) {
        if (!(o instanceof Input))
            return false;
        Input i = (Input) o;
        return source == i.source && mods == i.mods && value == i.value;
    }

    // Get the display text for this input
    public String getText() {

        // Formatting for modifiers
        String mods = InputEvent.getModifiersExText(this.mods);
        if (mods.length() != 0)
            mods += "+";

        // Formatting by source
        switch (source) {
            case KEY:
                return mods + KeyEvent.getKeyText(value);
            case MOUSE:
                switch (value) {
                    case MouseEvent.BUTTON1: return mods + "{mouse.left}";
                    case MouseEvent.BUTTON2: return mods + "{mouse.middle}";
                    case MouseEvent.BUTTON3: return mods + "{mouse.right}";
                }
                break;
            case WHEEL:
                switch (value) {
                    case DOWN: return mods + "{mouse.down}";
                    case UP:   return mods + "{mouse.up}";
                }
                break;
        }

        // Unrecognized configuration
        return "{common.unknown}";
    }

    // Generate a hash code for this object
    public int hashCode() {
        CRC32 crc = new CRC32();
        int[] data = { source, mods, value };
        for (int x = 0; x < 3; x++)
            for (int y = 0; y < 32; y += 8)
                crc.update(data[x] >> y);
        return (int) crc.getValue();
    }

    // Represent this object as a string
    public String toString() {
        return source + " " + mods + " " + value;
    }

}
