package debugger;

// Java imports
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

// Project imports
import config.*;
import debugger.*;
import util.*;
import vue.*;

// Top-level class for mananging the debugger UI
public class Debugger {

    // Instance fields
    private Config       config; // Configuration manager
    private Disassembler dasm;   // Disassembly manager
    private VUE          vue;    // Associated emulation state

    // UI components
    private VIPBGMapPane    uiBGMaps;  // BG Maps tab
    private VIPBufferPane   uiBuffers; // Frame Buffers tab
    private BreakPane       uiBreak;   // Breakpoints pane
    private VIPChrPane      uiChrs;    // Characters tab
    private CPUDasmPane     uiDasm;    // Disassembler pane
    private CPUHexPane      uiHex;     // Hex editor pane
    private VIPObjPane      uiObjs;    // Objects tab
    private CPURegisterList uiProRegs; // Program registers
    private CPURegisterList uiSysRegs; // System registers
    private VIPRegisterPane uiVIPRegs; // VIP registers
    private VIPWorldPane    uiWorlds;  // Worlds tab

    // VIP fields
    int[]         brightness;  // Brightness levels
    int[]         buffer;      // Image pixel buffer
    int[][][]     colors;      // Scaled colors for red, left and right
    BufferedImage image;       // Rendering target
    int[]         objPointers; // Object group pointers
    int[][]       palettes;    // Palette memory
    byte[]        pixels;      // Internal pixel buffer
    byte[]        vipMemory;   // VIP memory cache



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Background modes
    static final int NORMAL = 0;
    static final int HBIAS  = 1;
    static final int AFFINE = 2;
    static final int OBJECT = 3;

    // Palette indexes
    static final int BG0  = 0;
    static final int BG1  = 1;
    static final int BG2  = 2;
    static final int BG3  = 3;
    static final int OBJ0 = 4;
    static final int OBJ1 = 5;
    static final int OBJ2 = 6;
    static final int OBJ3 = 7;

    // Color type indexes
    static final int GENERIC = 0;
    static final int SCALED  = 1;

    // Color indexes
    static final int RED   = 0;
    static final int LEFT  = 1;
    static final int RIGHT = 2;

    // Generic palette
    static final int[] GENERIC_PALETTE = { -1, 1, 2, 3 };

    // Error messages for Goto
    private static final String[] GOTOS = {
        "breakpoints.err_badeof",
        "breakpoints.err_literal",
        "breakpoints.err_syntax",
        "breakpoints.err_unknown"
    };



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public Debugger(Config config, VUE vue) {

        // Configure instance fields
        this.config = config;
        dasm        = new Disassembler();
        this.vue    = vue;

        // Configure VIP fields
        brightness  = new int[4];
        buffer      = new int[0x15000];
        image       = new BufferedImage(384, 224, BufferedImage.TYPE_INT_ARGB);
        objPointers = new int[4];
        palettes    = new int[8][4];
        pixels      = new byte[0x15000];
        vipMemory   = new byte[0x40000];

        // Configure color fields
        colors    = new int[2][3][4];

        // Configure UI components
        uiBGMaps  = new VIPBGMapPane(this);
        uiBreak   = new BreakPane(this);
        uiBuffers = new VIPBufferPane(this);
        uiChrs    = new VIPChrPane(this);
        uiDasm    = new CPUDasmPane(this);
        uiHex     = new CPUHexPane(this);
        uiObjs    = new VIPObjPane(this);
        uiProRegs = new CPURegisterList(this, false);
        uiSysRegs = new CPURegisterList(this, true);
        uiVIPRegs = new VIPRegisterPane(this);
        uiWorlds  = new VIPWorldPane(this);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Retrieve the VIP BG maps pane
    public JComponent getBGMaps() {
        return uiBGMaps;
    }

    // Retrieve the Breakpoints pane
    public JComponent getBreakpoints() {
        return uiBreak;
    }

    // Retrieve the VIP characters pane
    public JComponent getCharacters() {
        return uiChrs;
    }

    // Retrieve the disassembler pane
    public JComponent getDisassembler() {
        return uiDasm;
    }

    // Retrieve the Frame Buffers pane
    public JComponent getFrameBuffers() {
        return uiBuffers;
    }

    // Retrieve the hex editor pane
    public JComponent getHexEditor() {
        return uiHex;
    }

    // Retrieve the VIP objects pane
    public JComponent getObjects() {
        return uiObjs;
    }

    // Retrieve the list of program registers
    public JComponent getProgramRegisters() {
        return uiProRegs;
    }

    // Retrieve the list of system registers
    public JComponent getSystemRegisters() {
        return uiSysRegs;
    }

    // Retrieve the VIP worlds pane
    public JComponent getWorlds() {
        return uiWorlds;
    }

    // Retrieve the VIP registers pane
    public JComponent getVIPRegisters() {
        return uiVIPRegs;
    }

    // Retrieve the current user-defined breakpoints as an array
    public Breakpoint[] listBreakpoints() {
        return uiBreak.listBreakpoints();
    }

    // Perform a full update of UI components
    public void refresh(boolean seekToPC) {

        // Color fields
        refreshColors();

        // VIP fields
        vue.read(0x00000000, VUE.U16, vipMemory, 0, 0x40000, false);
        for (int x = 0; x < objPointers.length; x++)
            objPointers[x] = vue.read(0x0005F848+x*2, VUE.U16, false) & 0x03FF;

        // CPU window
        uiDasm   .refresh(seekToPC);
        uiHex    .refresh();
        uiProRegs.refresh();
        uiSysRegs.refresh();

        // VIP window
        uiBGMaps .refresh();
        uiBuffers.refresh();
        uiChrs   .refresh();
        uiObjs   .refresh();
        uiVIPRegs.refresh();
        uiWorlds .refresh();

        // Notify listeners
        onRefresh();
    }

    // Refresh only the breakpoints pane
    public void refreshBreakpoints() {
        uiBreak.refresh();
    }

    // Display a specific address in the disassembler
    public void seekDisassembler(int address) {
        uiDasm.seek(address);
    }

    // Select a specific breakpoint in the list
    public void selectBreakpoint(int index) {
        uiBreak.setIndex(index);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Event Handlers                             //
    ///////////////////////////////////////////////////////////////////////////

    // These methods need to be overridden to be useful.

    // Refresh
    public void onRefresh() { }

    // Breakpoints modified
    public void onBreakpoint() { }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Retrieve the configuration manager
    Config getConfig() {
        return config;
    }

    // Retrieve the data disassembler
    Disassembler getDasm() {
        return dasm;
    }

    // Determine the object group contained by a given world
    int getObjGroup(int index) {
        int ret = 3;
        for (int x = 31; x > index; x--)
            if ((vipMemory[0x0003D801 + (x << 5)] & 0x30) == 0x30)
                ret = ret - 1 & 3;
        return ret;
    }

    // Determine the first object to draw in a group
    int getObjStart(int group) {
        return group == 0 ? 0 : objPointers[group - 1] + 1 & 1023;
    }

    // Retrieve the backing emulation context object
    VUE getVUE() {
        return vue;
    }

    // Generic Goto prompt
    Integer Goto(JComponent parent) {
        String title = config.getMessage("dialog.goto");

        // Prompt the user to enter an address
        String input = JOptionPane.showInputDialog(parent,
            config.getMessage("dialog.enter_address"),
            title, JOptionPane.PLAIN_MESSAGE);
        if (input == null) return null;

        // A hex value was entered
        boolean isHex = input.length() <= 8;
        for (int x = 0; isHex && x < input.length(); x++) {
            char c = input.charAt(x);
            isHex =
                (c >= '0' && c <= '9') ||
                (c >= 'A' && c <= 'F') ||
                (c >= 'a' && c <= 'f')
            ;
        }
        try { if (isHex) return (int) Long.parseLong(input, 16); }
        catch (Exception e) { }

        // A condition was entered
        Breakpoint brk = new Breakpoint();
        if (!brk.setCondition(input)) {
            String text = brk.getErrorText(Breakpoint.CONDITION);
            JOptionPane.showMessageDialog(parent,
                config.getMessage("dialog.bad_address") + " " +
                config.getMessage(GOTOS[brk.getError(
                    Breakpoint.CONDITION, Breakpoint.CODE) - 1])
                .replaceAll("{TEXT}", text),
            title, JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // The result is not an integer
        brk.setEnabled(true);
        brk.setEvents (Breakpoint.ALWAYS);
        int[] eval = brk.evaluate(vue);
        if (eval[0] != Breakpoint.INT) {
            JOptionPane.showMessageDialog(parent,
                config.getMessage("dialog.bad_address") + " " +
                config.getMessage("dialog.bad_type"),
            title, JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Return the value entered
        return eval[1];
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Regenerate rendering colors
    private void refreshColors() {

        // Retrieve brightness levels
        for (int x = 1; x < 4; x++)
            brightness[x] = 0x00FF &
                vue.read(0x0005F822 + x * 2, VUE.U16, false);

        // Retrieve palettes
        for (int x = 0; x < 8; x++) {
            int value = vue.read(0x0005F860 + x * 2, VUE.U16, false);
            palettes[x][0] = -1;
            for (int y = 1, z = 2; y < 4; y++, z += 2)
                palettes[x][y] = value >> z & 3;
        }

        // Produce scaled colors
        colors[GENERIC] = config.scaleColors(null);
        colors[SCALED]  = config.scaleColors(brightness);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                           Rendering Methods                           //
    ///////////////////////////////////////////////////////////////////////////

    // These methods are adapted from vue.JavaVIP

    // Reset the pixel buffer
    void clearBuffer() {
        for (int x = 0; x < pixels.length; x++)
            pixels[x] = 0;
    }

    // Draw a background world
    private void drawBackground(int address, int L, int T, int R, int B,
        boolean generic) {
        byte[] ram = vipMemory;

        // Retrieve mode and eye information
        int     bits = ram[address | 1];
        int     mode = bits >> 4 & 3;
        boolean lon  = (bits & 0x80) != 0;
        boolean ron  = (bits & 0x40) != 0;

        // Check dimensions
            bits   = mode == AFFINE ? 22 : 19;
        int width  = (halfword(address | 14) << bits >> bits) + 1;
        int height = (halfword(address | 16)                ) + 1;
        if (width <= 0 || height <= 0)
            return; // Invalid dimensions
        if (mode != AFFINE)
            height = Math.max(8, height);

        // Check vertical position
        int gy = halfword(address | 6);
        if (gy > B || gy + height < T)
            return; // World is not in-frame
        int top    = Math.max(T, gy);
        int bottom = Math.min(B, gy + height - 1);

        // Retrieve horizontal properties
        int   gx    = halfword(address | 2) << 22 >> 22;
        int   gp    = halfword(address | 4) << 22 >> 22;
        int[] left  = { Math.max(L, gx - gp            ),
                        Math.max(L, gx + gp            ) };
        int[] right = { Math.min(R, gx - gp + width - 1),
                        Math.min(R, gx + gp + width - 1) };

        // Check horizontal positions
        if (!lon || left[0] > R || right[0] < L)
            left[0] = -1; // Not in-frame
        if (!ron || left[1] > R || right[1] < L)
            left[1] = -1; // Not in-frame

        // Retrieve common world parameters
        int mp        = halfword(address | 10) << 17 >> 17;
        int mx        = halfword(address |  8) << 19 >> 19;
        int my        = halfword(address | 12) << 19 >> 19;
        int paramBase = halfword(address | 18) & 0xFFFF;

        // Retrieve background parameters
        bits = halfword(address);
        int bgMapBase = bits       & 15;
        int scx       = bits >> 10 & 3;
        int scy       = bits >>  8 & 3;
        int overplane = (bits & 0x0080) == 0 ? -1 :
            halfword(address | 20) & 0xFFFF;

        // Prepare for rendering
        int   DX = 0;        // Adjustment per column
        int   DY = 0;
        int[] MX = { 0, 0 }; // Current source position
        int[] MY = { 0, 0 };

        // Process all rows
        for (int y = top, dest = top * 384; y <= bottom; y++, dest += 384) {

            // Initialize non-affine parameters
            if (mode != AFFINE) {

                // Determine state from world attributes
                MX[0] = left[0]   - gx + gp + mx - mp << 9;
                MX[1] = left[1]   - gx - gp + mx + mp << 9;
                MY[0] = MY[1] = y - gy + my      << 9;
                DX    = 1 << 9;
                DY    = 0;

                // Apply H-bias parameters
                if (mode == HBIAS) {
                    int param = 0x00020000 +
                        ((paramBase + (y - gy << 1) & 0xFFFF) << 1);
                    MX[0] += halfword(param    ) << 19 >> 10;
                    MX[1] += halfword(param | 2) << 19 >> 10;
                }

            }

            // Initialize affine parameters
            else {

                // Determine state from parameter data
                int param = 0x00020000 +
                    ((paramBase + (y - gy << 3) & 0xFFFF) << 1);
                int MP = halfword(param | 2);
                DX     = halfword(param | 6);
                DY     = halfword(param | 8);
                MX[0]  = MX[1] = halfword(param    ) << 6;
                MY[0]  = MY[1] = halfword(param | 4) << 6;

                // Adjust for starting position and parallax
                int l = left[0] - gx + gp - (MP <  0 ? MP : 0);
                int r = left[1] - gx - gp + (MP >= 0 ? MP : 0);
                MX[0] += DX * l;
                MX[1] += DX * r;
                MY[0] += DY * l;
                MY[1] += DY * r;
            }

            // Process both eyes
            for (int eye = 0; eye < 2; eye++) {

                // Check visibility
                if (left[eye] == -1)
                    continue; // Not in-frame

                // Process all columns
                bits = eye << 2;
                for (int x = left[eye]; x <= right[eye]; x++) {

                    // Update the current pixel if it hasn't been written
                    if ((pixels[dest + x] >> bits & 4) == 0) {

                        // Sample the pixel in the background
                        int sample = sampleBackground(
                            MX[eye] >> 9, MY[eye] >> 9,
                            bgMapBase, scx, scy, overplane, generic
                        );

                        // Store the pixel if it isn't invisible
                        if (sample != -1)
                            pixels[dest + x] = (byte) (
                                (4 | sample) << bits |
                                pixels[dest + x] & 0xF0 >> bits
                            );
                    }

                    // Update background source position
                    MX[eye] += DX;
                    MY[eye] += DY;
                } // x

            } // eye

        } // y

    }

    // Draw a frame buffer
    void drawBuffer(int index, int L, int T, int R, int B) {
        byte[] ram = vipMemory;
        int[] address = {
            0x00000000 | index << 15,
            0x00010000 | index << 15
        };

        for (int y = 0, dest = 0; y < 224; y++)
        for (int x = 0; x < 384; x++, dest++) {
            int offset = x << 6 | y >> 2;
            int bits   = (y & 3) << 1;
            pixels[dest] = (byte) (0x44 |
                (ram[address[1] | offset] >> bits & 3) << 4 |
                 ram[address[0] | offset] >> bits & 3
            );
        }

    }

    // Draw an object world
    void drawObject(int group, int L, int T, int R, int B,
        boolean generic) {
        byte[] ram = vipMemory;
        int start = group == 0 ? 0 : objPointers[group - 1] + 1 & 1023;
        int end   = objPointers[group];

        // Draw objects from front to back
        int o = start;
        do {
            int address = 0x0003E000 + (o << 3);

            // Check visibility
            int     bits = halfword(address | 2);
            boolean lon  = (bits & 0x8000) != 0;
            boolean ron  = (bits & 0x4000) != 0;
            if (!lon && !ron)
                continue; // Object is not visible

            // Check vertical position
            int jy = ram[address | 4];
            if (jy < -8) jy += 256;
            if (jy > B || jy + 8 < T)
                continue; // Object is not in-frame
            int top    = Math.max(T, jy);
            int bottom = Math.min(B, jy + 7);

            // Retrieve horizontal properties
            int   jx    = halfword(address) << 22 >> 22;
            int[] jp    = { bits << 22 >> 22, -(bits << 22 >> 22) };
            int[] left  = { Math.max(L, jx - jp[0]    ),
                            Math.max(L, jx + jp[0]    ) };
            int[] right = { Math.min(R, jx - jp[0] + 7),
                            Math.min(R, jx + jp[0] + 7) };

            // Check horizontal positions
            if (!lon || left[0] > R || right[0] < L)
                left[0] = -1; // Not in-frame
            if (!ron || left[1] > R || right[1] < L)
                left[1] = -1; // Not in-frame

            // Retrieve common object parameters
            bits = halfword(address | 6);
            int     jca  = bits & 2047;
            boolean hflp = (bits & 0x2000) != 0;
            boolean vflp = (bits & 0x1000) != 0;
            int[]   plts = generic ? GENERIC_PALETTE :
                palettes[OBJ0 + (bits >> 14 & 3)];

            // Process all rows
            for (int y = top, dest = top * 384; y <= bottom; y++, dest += 384)
            for (int eye = 0; eye < 2; eye++) {

                // Check visibility
                if (left[eye] == -1)
                    continue; // Not in-frame

                // Process all columns
                bits = eye << 2;
                for (int x = left[eye]; x <= right[eye]; x++) {

                    // Check whether the pixel can be written
                    if ((pixels[dest + x] >> bits & 4) != 0)
                        continue; // The pixel has already been written

                    // Sample the pixel in the character
                    int sample = sampleCharacter(jca,
                        x - jx + jp[eye], y - jy, hflp, vflp);
                    if (sample == 0)
                        continue; // Pixel is invisible

                    // Store the pixel
                    pixels[dest + x] = (byte) (
                        (4 | plts[sample]) << bits |
                        pixels[dest + x] & 0xF0 >> bits
                    );

                } // x

            } // y

        } while (o != end && (o = o + 1 & 1023) > -1); // o

    }

    // Draw a world
    void drawWorld(int index, int L, int T, int R, int B, boolean generic) {
        int address = 0x0003D800 | (index << 5);
        if ((vipMemory[address | 1] & 0x30) == 0x30)
            drawObject(getObjGroup(index), L, T, R, B, generic);
        else drawBackground(address, L, T, R, B, generic);
    }

    // Read a halfword from memory
    private int halfword(int address) {
        byte[] ram = vipMemory;
        return ram[address] & 0xFF | ram[address | 1] << 8;
    }

    // Draw a region of the pixel buffer into the image
    void paintBuffer(int L, int T, int R, int B, int scale,
        boolean left, boolean right, boolean generic) {
        int[][] colors = this.colors[generic ? GENERIC : SCALED];

        // Process all rows and columns
        for (int y = T, src = T * 384; y <= B; y++, src += 384)
        for (int x = L; x <= R; x++) {
            int bits  = pixels[src + x];
            int color = 0;

            // Process both eyes
            for (int eye = 0; eye < 2; eye++) {

                // Don't draw for this eye
                if (
                    (eye == 0 && (!left  || (bits & 0x04) == 0)) ||
                    (eye == 1 && (!right || (bits & 0x40) == 0))
                ) continue;

                // Apply the pixel to the current color
                color |= colors[eye == 0 ? LEFT : RIGHT]
                    [bits >> (eye == 0 ? 0 : 4) & 3];
            }

            // Store the color in the image buffer
            buffer[src + x] = color;
        } // x, y

        // Update the image
        image.setRGB(L, T, R - L + 1, B - T + 1, buffer, T * 384 + L, 384);
    }

    // Sample the brightness level of a pixel from 
    private int sampleBackground(int mx, int my, int bgMapBase,
        int scx, int scy, int overplane, boolean generic) {
        byte[] ram = vipMemory;
        int wide = (512 << scx) - 1;
        int tall = (512 << scy) - 1;
        int cell = overplane;

        // Determine the index of the visible cell if not the overplane cell
        if (overplane == -1 || (mx & wide) == mx && (my & tall) == my) {

            // Correct malformed background dimensions
            if (scx + scy > 3) {
                scx  = 3 - scy;
                wide = (512 << scx) - 1;
            }

            // Calculate the index of the visible cell
            cell = (bgMapBase & (~0 << scx + scy)                 | // Base
                (my & tall) >> 9 << scx | (mx & wide) >> 9) << 12 | // Map
                (my &  511) >> 3 <<   6 | (mx &  511) >> 3;         // Cell
        }

        // Sample the pixel from the visible cell
        int bits   = halfword(0x00020000 + (cell << 1));
        int sample = sampleCharacter(bits & 2047, mx & 7, my & 7,
            (bits & 0x2000) != 0, (bits & 0x1000) != 0);
        return sample == 0 ? -1 : (generic ?
            GENERIC_PALETTE : palettes[BG0 + (bits >> 14 & 3)])[sample];
    }

    // Sample a pixel from a character
    int sampleCharacter(int index, int x, int y,
        boolean hFlip, boolean vFlip) {
        byte[] ram = vipMemory;
        if (hFlip) x = 7 - x;
        if (vFlip) y = 7 - y;
        return ram[0x00006000 + (index >> 9 << 15) | // Table
            (index & 511) << 4 | y << 1 | x >> 2]    // Byte
            >> ((x & 3) << 1) & 3;                   // Pixel
    }


}
