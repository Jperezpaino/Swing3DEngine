package util;

// Java imports
import java.nio.charset.*;
import java.util.*;
import javax.swing.*;

// Processes localization message files
public class Localization {

    // Instance fields
    private HashMap<String, String> map;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public Localization() {
        map = new HashMap<String, String>();
    }

    // Data constructor
    public Localization(String data) {
        this();
        set(data);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Retrieve a localized string for a component
    public String get(JComponent ctl) {
        return get(ctl, "locale");
    }

    // Retrieve a localized string for a component given its property
    public String get(JComponent ctl, String property) {
        Object val = ctl.getClientProperty(property);
        return val == null || !(val instanceof String) ? null :
            substitute(get((String) val), ctl);
    }

    // Retrieve a localized message for a given key, if any
    public String get(String key) {

        // Error checking
        if (key == null)
            return null;

        // Do not translate the key
        if (key.startsWith("!"))
            return key.substring(1);

        // Retrieve the message
        String ret =  map.get(key.toUpperCase());
        return ret == null ? key : ret;
    }

    // Retrieve a list of localized strings for a component
    public String[] getList(JComponent ctl) {
        Object val = ctl.getClientProperty("locale");

        // Error checking
        if (val instanceof String)
            return new String[] { (String) val };
        if (!(val instanceof String[]))
            return null;

        // Convert the keys to their respective values
        String[] keys = (String[]) val;
        for (int x = 0; x < keys.length; x++)
            keys[x] = substitute(get(keys[x]), ctl);
        return keys;
    }

    // Supply new localization messages
    public boolean set(String text) {

        // Error checking
        if (text == null)
            return false;

        // Process all lines in the input
        map.clear();
        String[] lines = text.replaceAll("\\r", "").split("\\n");
        for (int x = 0; x < lines.length; x++) {

            // Locate the first whitespace
            int length = lines[x].length();
            if (length == 0)
                continue; // No text
            int split  = Math.min(
                (lines[x].indexOf(" " ) + length) % length,
                (lines[x].indexOf("\t") + length) % length
            );
            if (split == length - 1)
                continue; // No room for a value

            // Add the entry to the map
            String key   = lines[x].substring(0, split).toUpperCase();
            String value = lines[x].substring(split).trim();
            if (!value.isEmpty())
                map.put(key, value);
        }
        return true;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Construct a dynamic message via substitution
    private String substitute(String text, JComponent ctl) {

        // Find all substitution tokens in the message
        while (text.length() < 150) {
            int close = -1;
            int open  = -1;

            // Find the start and end of the first substitution
            outer: for (int x = 0; x < text.length(); x++)
            switch (text.charAt(x)) {
                case '{': open  = x; continue;
                case '}': close = x; break outer;
            }
            if (close == -1 || open == -1)
                break;

            // Retrieve the substitution text from the control
            String key = text.substring(open, close + 1);
            Object val = ctl.getClientProperty(key);
            String msg = val == null || !(val instanceof String) ?
                get(text.substring(open + 1, close)) : get((String) val);

            text = text.substring(0, open) +
                (msg == null ? "(null)" : msg) + text.substring(close + 1);
        }

        return text;
    }

}
