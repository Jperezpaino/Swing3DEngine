package util;

// Java imports
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.nio.charset.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.plaf.basic.*;

// Miscellaneous utility methods
public final class Util {

    // Static fields
    private static Font       defaultFont; // Default UI dialog font
    private static String[]   fontNames;   // Full list of local font families
    private static Graphics2D G;           // Graphics context for font metrics
    private static Border     insetBorder; // Inset-style control border



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Byte-order marks
    private static final int[] UTF_8    = { 0xEF, 0xBB, 0xBF };
    private static final int[] UTF_16BE = { 0xFE, 0xFF };
    private static final int[] UTF_16LE = { 0xFF, 0xFE };
    private static final int[] UTF_32LE = { 0xFF, 0xFE, 0x00, 0x00 };



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    public interface AncestorHandler { public void onEvent(AncestorEvent  e); }
    public interface ChangeHandler   { public void onEvent(ChangeEvent    e); }
    public interface FocusHandler    { public void onEvent(FocusEvent     e); }
    public interface KeyHandler      { public void onEvent(KeyEvent       e); }
    public interface MouseHandler    { public void onEvent(MouseEvent     e); }
    public interface ResizeHandler   { public void onEvent(ComponentEvent e); }



    ///////////////////////////////////////////////////////////////////////////
    //                            Static Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Static constructor
    static {

        // Use the system look and feel if available
        try { UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName() ); }
        catch (Exception e) { }

        // Graphics context for font metrics
        G = (Graphics2D) (new BufferedImage(
            1, 1, BufferedImage.TYPE_INT_ARGB).getGraphics());

        // Inset-style control border
        insetBorder = new JScrollPane().getBorder();

        // Configure fonts
        defaultFont = new JLabel().getFont();
        refreshFonts();
    }

    // Adjust JSplitPane borders and divider size
    public static void adjustSplitPane(JSplitPane split) {
        split.setDividerSize(2);
        split.setContinuousLayout(true);
        split.setUI(new BasicSplitPaneUI() {
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    public void setBorder(Border b) { }
                };
            }
        });
        split.setBorder(null);
    }

    // Load a file from the local filesystem
    public static byte[] fileLoad(File file) {
        try { return load(new FileInputStream(file)); }
        catch (Exception e) { }
        return null;
    }

    // Load a file from the JAR or local filesystem
    public static byte[] fileLoad(String filename) {
        byte[] ret = load(Util.class.getResourceAsStream("/" + filename));
        return ret != null ? ret : fileLoad(new File(filename));
    }

    // Determine whether a font is available
    public static boolean fontExists(String name) {
        return Arrays.binarySearch(fontNames, name,
            String.CASE_INSENSITIVE_ORDER) >= 0;
    }

    // Modify a color to have transparency
    public static Color getColor(Color ref, float alpha) {
        return new Color(
            (int) Math.round(alpha * 255) << 24 |
            ref.getRGB() & 0x00FFFFFF,
        true);
    }

    // Retrieve the default font used by window controls
    public static Font getDefaultFont() {
        return defaultFont;
    }

    // Retrieve a font for a particular size in pixels
    public static Font getFont(String name, int style, int height) {

        // Perform a binary search to find the best font size
        int min =   1;
        int max = 100;
        for (;;) {

            // Determine the value to compare
            int         mid     = min + (max - min) / 2;
            Font        ret     = new Font(name, style, mid);
            FontMetrics metrics = getFontMetrics(ret);
            int         check   = metrics.getHeight();

            // Compare the value with the specified height
            if (check < height) min = mid;
            if (check > height) max = mid;
            if (check == height || min >= max - 1)
                return ret;
        }

    }

    // Determine the metrics for a font
    public static FontMetrics getFontMetrics(Font font) {
        return G.getFontMetrics(font);
    }

    // Return a list of all supported font names (sorted case-insensitive)
    public static String[] getFonts() {
        return Arrays.copyOf(fontNames, fontNames.length);
    }

    // Load an image from a file
    public static BufferedImage imageLoad(File file) {
        try { return ImageIO.read(file); }
        catch (Exception e) { }
        return null;
    }

    // Placebo method for invoking the static constructor
    public static void initialize() { }

    // Retrieve the inset-style control border
    public static Border getInsetBorder() {
        return insetBorder;
    }

    // Mix two colors together with alpha
    public static Color mixColors(Color back, Color fore, float alpha) {
        int Back = back.getRGB();
        int Fore = fore.getRGB();
        int ret  = 0;

        // Process all color channels
        for (int x = 0; x < 3; x++) {
            int shift  = x << 3;
            int b      = Back >> shift & 0xFF;
            int f      = Fore >> shift & 0xFF;
            ret       |= (int) Math.round(b + (f - b) * alpha) << shift;
        }

        return new Color(ret);
    }

    // Produce a FocusListener using functional interfaces
    public static void onFocus(Component component, FocusHandler focus,
        FocusHandler blur) {
        component.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e)
                { if (focus != null) focus.onEvent(e); }
            public void focusLost  (FocusEvent e)
                { if (blur  != null) blur.onEvent(e); }
        });
    }

    // Produce a KeyListener (down, up) using functional interfaces
    public static void onKey(Component component, KeyHandler down,
        KeyHandler up) {
        component.addKeyListener(new KeyListener() {
            public void keyTyped   (KeyEvent e) { }
            public void keyPressed (KeyEvent e)
                { if (down != null) down.onEvent(e); }
            public void keyReleased(KeyEvent e)
                { if (up   != null) up.onEvent(e); }
        });
    }

    // Produce a MouseListener using functional interfaces
    public static void onMouse(Component component, MouseHandler down,
        MouseHandler up) {
        component.addMouseListener(new MouseListener() {
            public void mouseClicked (MouseEvent e) { }
            public void mouseEntered (MouseEvent e) { }
            public void mouseExited  (MouseEvent e) { }
            public void mousePressed (MouseEvent e)
                { if (down != null) down.onEvent(e); }
            public void mouseReleased(MouseEvent e)
                { if (up   != null) up.onEvent(e); }
        });
    }

    // Produce a MouseMotionListener using functional interfaces
    public static void onMouseMove(Component component, MouseHandler move,
        MouseHandler drag) {
        component.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e)
                { if (drag != null) drag.onEvent(e); }
            public void mouseMoved  (MouseEvent e)
                { if (move != null) move.onEvent(e); }
        });
    }

    // Produce a ComponentListener for resize using a functional interface
    public static void onResize(Component component, ResizeHandler resize) {
        component.addComponentListener(new ComponentListener() {
            public void componentHidden (ComponentEvent e) { }
            public void componentMoved  (ComponentEvent e) { } 
            public void componentShown  (ComponentEvent e) { }
            public void componentResized(ComponentEvent e)
                { if (resize != null) resize.onEvent(e); }
        });
    }

    // Produce a KeyListener (type) using functional interfaces
    public static void onType(Component component, KeyHandler type) {
        component.addKeyListener(new KeyListener() {
            public void keyPressed (KeyEvent e) { }
            public void keyReleased(KeyEvent e) { }
            public void keyTyped   (KeyEvent e)
                { if (type != null) type.onEvent(e); }
        });
    }

    // Produce an AncestorListener using functional interfaces
    public static void onVisible(JComponent component, AncestorHandler show,
        AncestorHandler hide) {
        component.addAncestorListener(new AncestorListener() {
            public void ancestorMoved  (AncestorEvent e) { }
            public void ancestorAdded  (AncestorEvent e)
                { if (show != null) show.onEvent(e); }
            public void ancestorRemoved(AncestorEvent e)
                { if (hide != null) hide.onEvent(e); }
        });
    }

    // Regenerate the list of font names
    public static void refreshFonts() {
        fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment(
            ).getAvailableFontFamilyNames();
        Arrays.sort(fontNames, String.CASE_INSENSITIVE_ORDER);
    }

    // Detect the encoding of a text file and produce a matching String
    public static String textFile(byte[] data) {

        // Error checking
        if (data == null)
            return null;

        // Detect whether a byte-order mark is present
        int[][] boms = { UTF_8, UTF_32LE, UTF_16BE, UTF_16LE };
        int x, y;
        for (x = 0; x < boms.length; x++) {       // Process all BOMs
            if (data.length < boms[x].length)     // Input is too short
                continue;
            for (y = 0; y < boms[x].length; y++)
                if ((data[y] & 0xFF) != boms[x][y])
                    break;                        // BOM does not match
            if (y == boms[x].length)
                break;                            // BOM fully matched
        }

        // Produce a String object using the input
        int     offset  = new int[] { 3, 0, 2, 2, 0 }[x];
        Charset charset = new Charset[] {
            StandardCharsets.UTF_8,
            null,
            StandardCharsets.UTF_16BE,
            StandardCharsets.UTF_16LE,
            StandardCharsets.UTF_8
        }[x];
        try { return new String(data, offset, data.length - offset, charset); }
        catch (Exception e) { }
        return null;
    }

    // Load a text file, detecting its encoding automatically
    public static String textFile(String filename) {
        return textFile(fileLoad(filename));
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Cannot be instantiated
    private Util() { }

    // Load all data from a stream into a byte buffer
    private static byte[] load(InputStream stream) {
        ByteArrayOutputStream out    = new ByteArrayOutputStream();
        byte[]                buffer = new byte[64 * 1024];

        try {

            // Read from the stream until EOF is reached
            for (;;) {
                int x = stream.read(buffer, 0, buffer.length);
                if (x == -1) break;
                out.write(buffer, 0, x);
            }

            // Return the loaded contents
            stream.close();
            return out.toByteArray();
        } catch (Exception e) { }

        // An error occurred
        return null;
    }

}