package vue;

// Video processor
class JavaVIP {

    // Instance fields
    int[][]  bright;  // Brightness level snapshot for most recent render
    int      buffer;  // Frame buffer to display
    int[][]  columns; // Column table snapshot for most recent render
    int      cycles;  // Primary clock counter
    int      frame;   // Display frames to wait before next game frame
    JavaVUE  parent;  // Parent emulation context
    byte[][] pixels;  // Internal frame buffer memory
    byte[]   ram;     // VIP memory

    // I/O registers
    int[] brt;    // Brightness Control Registers and Rest Control Register
    int   bkcol;  // BG Color Palette Control Register
    int   cta;    // Column Table Read Start Address
    int   frmcyc; // Game Frame Control Register
    int[] gplt;   // BG Palette Control Register
    int   intenb; // Interrupt Enable
    int   intpnd; // Interrupt Pending
    int[] jplt;   // OBJ Palette Control Registers
    int[] spt;    // OBJ Control Registers

    // DPSTTS
    boolean dp_disp;  // Display is enabled
    boolean dp_lock;  // CTA is frozen
    boolean dp_re;    // Memory refresh is enabled
    int     dp_sbout; // Cycles remaining for SBOUT status
    boolean dp_synce; // Displays can be shown

    // XPCTRL/XPSTTS
    int     xp_sbcmp; // Group of 8 rows to latch while drawing
    boolean xp_xpen;  // Drawing is enabled



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Control values
    static final int FCLKHIGH  =  400000; // Cycles for FCLK
    static final int LEFTHIGH  =  340000; // +3ms
    static final int LEFTLOW   =  240000; // +8ms
    static final int FCLKLOW   =  200000; // +10ms
    static final int RIGHTHIGH =  140000; // +13ms
    static final int RIGHTLOW  =   40000; // +15ms
    static final int SBOUTLOW  =  398880; // +56us

    // Interrupt register flags
    static final int SCANERR    = 0x0001;
    static final int LFBEND     = 0x0002;
    static final int RFBEND     = 0x0004;
    static final int GAMESTART  = 0x0008;
    static final int FRAMESTART = 0x0010;
    static final int SBHIT      = 0x2000;
    static final int XPEND      = 0x4000;
    static final int TIMEERR    = 0x8000;

    // Display register flags
    static final int DPRST   = 0x0001;
    static final int DISP    = 0x0002;
    static final int L0BSY   = 0x0004;
    static final int R0BSY   = 0x0008;
    static final int L1BSY   = 0x0010;
    static final int R1BSY   = 0x0020;
    static final int SCANRDY = 0x0040;
    static final int FCLK    = 0x0080;
    static final int RE      = 0x0100;
    static final int SYNCE   = 0x0200;
    static final int LOCK    = 0x0400;

    // Drawing register flags
    static final int XPRST    = 0x0001;
    static final int XPEN     = 0x0002;
    static final int F0BSY    = 0x0004;
    static final int F1BSY    = 0x0008;
    static final int OVERTIME = 0x0010;
    static final int SBOUT    = 0x8000;

    // Background modes
    static final int NORMAL = 0;
    static final int HBIAS  = 1;
    static final int AFFINE = 2;
    static final int OBJECT = 3;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    JavaVIP(JavaVUE parent) {
        bright      = new int[2][4];
        brt         = new int[4];
        columns     = new int[2][192];
        gplt        = new int[4];
        jplt        = new int[4];
        this.parent = parent;
        pixels      = new byte[2][0x15000];
        spt         = new int[4];
        ram         = new byte[0x40000];
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Process the simulation
    void emulate(int cycles) {
        this.cycles -= cycles;

        // Check for breakpoints
        if (this.cycles <= 0)
            parent.checkBreakpoints(Breakpoint.FRAME, null);

        // Process all ellapsed frames
        for (int frames = 0; this.cycles <= 0; frames++) {

            // Process the next game frame
            if (xp_xpen && frame++ == frmcyc) {

                // Render the image, but only for the first two frames
                if (frames < 2)
                    render();

                // Update state
                buffer   ^= 1; // Display the other frame buffer
                frame     = 0;
                intpnd   |= GAMESTART | XPEND | (xp_sbcmp < 28 ? SBHIT : 0);
            }

            // Process the next display frame
            if (dp_disp)
                intpnd |= FRAMESTART | RFBEND | LFBEND;

            // Update state
            this.cycles += FCLKHIGH;
            irq();
        }

    }

    // Read a value from the VIP bus
    int read(int address, int type, boolean debug) {
        address &= 0x0007FFFF;

        // RAM
        if (address < 0x00040000)
            return parent.readMemory(ram, address, type);

        // Unmapped
        if (address < 0x0005E000)
            return 0;

        // I/O registers
        if (address < 0x00060000) {
            int value = readRegister(address, type, debug);
            if (type == VUE.S32) value = value & 0xFFFF |
                readRegister(address + 2, type, debug);
            return value;
        }

        // Unmapped
        if (address < 0x00078000)
            return 0;

        // Character memory mirrors
             if (address < 0x0007A000) address -= 0x00072000;
        else if (address < 0x0007C000) address -= 0x0006C000;
        else if (address < 0x0007E000) address -= 0x00066000;
        else                           address -= 0x00060000;
        return parent.readMemory(ram, address, type);
    }

    // Initialize VIP for system reset
    void reset() {

        // Initialize module
        buffer  = 0;
        cycles  = FCLK;
        frame   = 0;
        for (int x = 0; x < bright [0].length; x++)
            bright [0][x] = bright [1][x] = 0;
        for (int x = 0; x < columns[0].length; x++)
            columns[0][x] = columns[1][x] = 0;
        for (int x = 0; x < pixels [0].length; x++)
            pixels [0][x] = pixels [1][x] = 0;

        // Reset memory to zeroes to aid with debugging
        // The hardware does not do this
        for (int x = 0; x < ram.length; x++)
            ram[x] = 0;

        // Initialize VIP state
        dp_disp  = false;
        dp_re    = false;
        dp_synce = false;
        intenb   = 0;
        xp_xpen  = false;

        // Reset all other state as well
        // The hardware does not do this
        bkcol    = 0;
        cta      = 0;
        frmcyc   = 0;
        intpnd   = 0;
        brt [0]  = brt [1] = brt [2] = brt [3] = 0;
        gplt[0]  = gplt[1] = gplt[2] = gplt[3] = 0;
        jplt[0]  = jplt[1] = jplt[2] = jplt[3] = 0;
        spt [0]  = spt [1] = spt [2] = spt [3] = 0;
        dp_lock  = false;
        xp_sbcmp = 0;
    }

    // Determine how many CPU cycles can elapse before an interrupt request
    int untilIRQ(int cycles) {

        // This implementation stops at every video frame because audio
        // output may depend on it even while video interrupts are disabled
        return Math.min(cycles, this.cycles);

        /*
        // Check whether a display frame will occur
        if (this.cycles >= cycles)
            return cycles; // No display frame will occur

        // Determine which interrupts might be raised
        int mask = 0;
        if (dp_disp)
            mask |= FRAMESTART | RFBEND | LFBEND;
        if (xp_xpen) {
            mask |= GAMESTART | XPEND;
            if (xp_sbcmp < 28)
                mask |= SBHIT;
        }
        return (intenb & mask) != 0 ? this.cycles : cycles;
        */
    }

    // Write a value to the VIP bus
    void write(int address, int type, int value, boolean debug) {
        address &= 0x0007FFFF;

        // RAM
        if (address < 0x00040000) {
            parent.writeMemory(ram, address, type, value);
            writePixels(address, type, value);
            return;
        }

        // Unmapped
        if (address < 0x0005E000)
            return;

        // I/O registers
        if (address < 0x00060000) {
            writeRegister(address, type, value, debug);
            if (type == VUE.S32)
                writeRegister(address + 2, type, value >> 16, debug);
            return;
        }

        // Unmapped
        if (address < 0x00078000)
            return;

        // Character memory mirrors
             if (address < 0x0007A000) address -= 0x00072000;
        else if (address < 0x0007C000) address -= 0x0006C000;
        else if (address < 0x0007E000) address -= 0x00066000;
        else                           address -= 0x00060000;
        parent.writeMemory(ram, address, type, value);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update the state of the CPU interrupt request line for this subsystem
    private void irq() {
        parent.cpu.irq[4] = (intenb & intpnd) != 0;
    }

    // Simulate a read of DPSTTS
    private int readDPSTTS() {
        return
            (dp_lock          ? LOCK  : 0) |
            (dp_synce         ? SYNCE : 0) |
            (dp_re            ? RE    : 0) |
            (cycles > FCLKLOW ? FCLK  : 0) |
            SCANRDY                        |
            (dp_disp          ? DISP  : 0) |
            (cycles<=LEFTHIGH  && cycles>LEFTLOW  ? L0BSY<<(buffer<<1) : 0) |
            (cycles<=RIGHTHIGH && cycles>RIGHTLOW ? R0BSY<<(buffer<<1) : 0)
        ;
    }

    // Read an I/O register as a halfword or byte
    private int readRegister(int address, int type, boolean debug) {
        int value = 0;

        // Read register by address
        switch (address >> 1) {
            case 0x05F800 >> 1: value = intpnd;            break; // INTPND
            case 0x05F802 >> 1: value = intenb;            break; // INTENB
            case 0x05F804 >> 1: value = 0;                 break; // INTCLR
            case 0x05F820 >> 1: value = readDPSTTS();      break; // DPSTTS
            case 0x05F822 >> 1: value = 0;                 break; // DPCTRL
            case 0x05F824 >> 1: value = brt[0];            break; // BRTA
            case 0x05F826 >> 1: value = brt[1];            break; // BRTB
            case 0x05F828 >> 1: value = brt[2];            break; // BRTC
            case 0x05F82A >> 1: value = brt[3];            break; // REST
            case 0x05F82E >> 1: value = frmcyc;            break; // FRMCYC
            case 0x05F830 >> 1: value = cta;               break; // CTA
            case 0x05F840 >> 1: value = readXPSTTS();      break; // XPSTTS
            case 0x05F842 >> 1: value = readXPCTRL(debug); break; // XPCTRL
            case 0x05F844 >> 1: value = 2;                 break; // VER
            case 0x05F848 >> 1: value = spt[0];            break; // SPT0
            case 0x05F84A >> 1: value = spt[1];            break; // SPT1
            case 0x05F84C >> 1: value = spt[2];            break; // SPT2
            case 0x05F84E >> 1: value = spt[3];            break; // SPT3
            case 0x05F860 >> 1: value = gplt[0];           break; // GPLT0
            case 0x05F862 >> 1: value = gplt[1];           break; // GPLT1
            case 0x05F864 >> 1: value = gplt[2];           break; // GPLT2
            case 0x05F866 >> 1: value = gplt[3];           break; // GPLT3
            case 0x05F868 >> 1: value = jplt[0];           break; // JPLT0
            case 0x05F86A >> 1: value = jplt[1];           break; // JPLT1
            case 0x05F86C >> 1: value = jplt[2];           break; // JPLT2
            case 0x05F86E >> 1: value = jplt[3];           break; // JPLT3
            case 0x05F870 >> 1: value = bkcol;             break; // BKCOL

            // Unmapped -- Explicit case labels to encourage tableswitch
            case 0x05F806 >> 1: case 0x05F808 >> 1: case 0x05F80A >> 1:
            case 0x05F80C >> 1: case 0x05F80E >> 1: case 0x05F810 >> 1:
            case 0x05F812 >> 1: case 0x05F814 >> 1: case 0x05F816 >> 1:
            case 0x05F818 >> 1: case 0x05F81A >> 1: case 0x05F81C >> 1:
            case 0x05F81E >> 1: case 0x05F82C >> 1: case 0x05F832 >> 1:
            case 0x05F834 >> 1: case 0x05F836 >> 1: case 0x05F838 >> 1:
            case 0x05F83A >> 1: case 0x05F83C >> 1: case 0x05F83E >> 1:
            case 0x05F846 >> 1: case 0x05F850 >> 1: case 0x05F852 >> 1:
            case 0x05F854 >> 1: case 0x05F856 >> 1: case 0x05F858 >> 1:
            case 0x05F85A >> 1: case 0x05F85C >> 1: case 0x05F85E >> 1:
                value = 0;
        }

        // Processing for byte reads
        if (type == VUE.S8 || type == VUE.U8) {
            if ((address & 1) == 1)
                value >>= 8;
            value &= 0xFF;
            if (type == VUE.S8 && (value & 0x80) != 0)
                value |= 0xFFFFFF00;
        }

        return value;
    }

    // Simulate a read of XPCTRL
    private int readXPCTRL(boolean debug) {
        return !debug ? 0 :
            xp_sbcmp << 8 |
            (xp_xpen ? XPEN : 0)
        ;
    }

    // Simulate a read of XPSTTS
    private int readXPSTTS() {
        return
            (cycles > SBOUTLOW ? SBOUT : 0) |
            28 << 8                         | // SBCOUNT
            (xp_xpen           ? XPEN  : 0) |
            (cycles > SBOUTLOW ? F1BSY >> buffer : 0)
        ;
    }

    // Simulate a write to CTA
    private void writeCTA(int value, boolean high, boolean low, boolean debug){
        if (!debug) return;
        cta = (high ? value : cta) & 0xFF00 |
              (low  ? value : cta) & 0x00FF;
    }

    // Simulate a write to DPCTRL
    private void writeDPCTRL(int value, boolean high, boolean low){

        // Parse flags
        if (high) {
            dp_lock  = (value & LOCK ) != 0;
            dp_synce = (value & SYNCE) != 0;
            dp_re    = (value & RE   ) != 0;
        }
        if (low) {
            boolean disp = (value & DISP) != 0;
            if (disp != dp_disp) {
                dp_disp = disp;
            }
        }

        // Clear interrupts
        if (!low || (value & DPRST) == 0) return;
        int mask = ~(TIMEERR|FRAMESTART|GAMESTART|RFBEND|LFBEND|SCANERR);
        intenb  &= mask;
        intpnd  &= mask;
        irq();
    }

    // Simulate a write to FRMCYC
    private void writeFRMCYC(int value, boolean high, boolean low) {
        if (!low)
            return;
        frmcyc = value & 15;
        frame  = Math.min(frame, frmcyc);
    }

    // Simulate a write to INTCLR
    private void writeINTCLR(int value, boolean high, boolean low) {
        if (high) intpnd &= ~(value & 0xFF00);
        if (low ) intpnd &= ~(value & 0x00FF);
        irq();
    }

    // Simulate a write to INTENB
    private void writeINTENB(int value, boolean high, boolean low) {
        intenb = (high ? value : intenb) & 0xE000 |
                 (low  ? value : intenb) & 0x001F;
        irq();
    }

    // Simulate a write to INTPND
    private void writeINTPND(int value,boolean high,boolean low,boolean debug){
        if (!debug) return;
        intpnd = (high ? value : intpnd) & 0xE000 |
                 (low  ? value : intpnd) & 0x001F;
        irq();
    }

    // Write an I/O register as a halfword or byte
    private void writeRegister(int address,int type,int value,boolean debug) {
        boolean isByte = type == VUE.S8 || type == VUE.U8;
        boolean isOdd  =  isByte && (address & 1) != 0;
        boolean high   = !isByte ||  isOdd;
        boolean low    = !isByte || !isOdd;

        // Special handling for odd-aligned byte writes
        if (isOdd) value <<= 8;

        // Write register by address
        switch (address >> 1) {
            case 0x05F800 >> 1: writeINTPND(value, high, low, debug); return;
            case 0x05F802 >> 1: writeINTENB(value, high, low);        return;
            case 0x05F804 >> 1: writeINTCLR(value, high, low);        return;
            case 0x05F820 >> 1: value = 0; /* DPSTTS */               return;
            case 0x05F822 >> 1: writeDPCTRL(value, high, low);        return;
            case 0x05F824 >> 1: if (low) brt[0] = value & 0x00FF;     return;
            case 0x05F826 >> 1: if (low) brt[1] = value & 0x00FF;     return;
            case 0x05F828 >> 1: if (low) brt[2] = value & 0x00FF;     return;
            case 0x05F82A >> 1: if (low) brt[3] = value & 0x00FF;     return;
            case 0x05F82E >> 1: writeFRMCYC(value, high, low);        return;
            case 0x05F830 >> 1: writeCTA   (value, high, low, debug); return;
            case 0x05F840 >> 1: value = 0; /* XPSTTS */               return;
            case 0x05F842 >> 1: writeXPCTRL(value, high, low);        return;
            case 0x05F844 >> 1: value = 0; /* VER */                  return;
            case 0x05F848 >> 1: writeSPT(0, value, high, low);        return;
            case 0x05F84A >> 1: writeSPT(1, value, high, low);        return;
            case 0x05F84C >> 1: writeSPT(2, value, high, low);        return;
            case 0x05F84E >> 1: writeSPT(3, value, high, low);        return;
            case 0x05F860 >> 1: if (low) gplt[0] = value & 0x00FC;    return;
            case 0x05F862 >> 1: if (low) gplt[1] = value & 0x00FC;    return;
            case 0x05F864 >> 1: if (low) gplt[2] = value & 0x00FC;    return;
            case 0x05F866 >> 1: if (low) gplt[3] = value & 0x00FC;    return;
            case 0x05F868 >> 1: if (low) jplt[0] = value & 0x00FC;    return;
            case 0x05F86A >> 1: if (low) jplt[1] = value & 0x00FC;    return;
            case 0x05F86C >> 1: if (low) jplt[2] = value & 0x00FC;    return;
            case 0x05F86E >> 1: if (low) jplt[3] = value & 0x00FC;    return;
            case 0x05F870 >> 1: if (low) bkcol   = value & 0x0003;    return;

            // Unmapped -- Explicit case labels to encourage tableswitch
            case 0x05F806 >> 1: case 0x05F808 >> 1: case 0x05F80A >> 1:
            case 0x05F80C >> 1: case 0x05F80E >> 1: case 0x05F810 >> 1:
            case 0x05F812 >> 1: case 0x05F814 >> 1: case 0x05F816 >> 1:
            case 0x05F818 >> 1: case 0x05F81A >> 1: case 0x05F81C >> 1:
            case 0x05F81E >> 1: case 0x05F82C >> 1: case 0x05F832 >> 1:
            case 0x05F834 >> 1: case 0x05F836 >> 1: case 0x05F838 >> 1:
            case 0x05F83A >> 1: case 0x05F83C >> 1: case 0x05F83E >> 1:
            case 0x05F846 >> 1: case 0x05F850 >> 1: case 0x05F852 >> 1:
            case 0x05F854 >> 1: case 0x05F856 >> 1: case 0x05F858 >> 1:
            case 0x05F85A >> 1: case 0x05F85C >> 1: case 0x05F85E >> 1:
                value = 0;
        }

    }

    // Write a SPT0-SPT3 register
    private void writeSPT(int index, int value, boolean high, boolean low) {
        spt[index] = (high ? value : spt[index]) & 0x0300 |
                     (low  ? value : spt[index]) & 0x00FF;
    }

    // Simulate a write to XPCTRL
    private void writeXPCTRL(int value, boolean high, boolean low) {

        // Parse flags
        if (high) {
            xp_sbcmp = value >> 8 & 31;
        }
        if (low) {
            boolean xpen = (value & XPEN) != 0;
            if (xpen != xp_xpen) {
                frame   = 0;
                xp_xpen = xpen;
            }
        }

        // Clear interrupts
        if (!low || (value & XPRST) == 0) return;
        int mask = ~(TIMEERR | XPEND | SBHIT);
        intenb  &= mask;
        intpnd  &= mask;
        irq();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                           Rendering Methods                           //
    ///////////////////////////////////////////////////////////////////////////

    // Draw a background world
    private void drawBackground(int address) {

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
        if (gy > 223 || gy + height < 0)
            return; // World is not in-frame
        int top    = Math.max(  0, gy);
        int bottom = Math.min(223, gy + height - 1);

        // Retrieve horizontal properties
        int   gx    = halfword(address | 2) << 22 >> 22;
        int   gp    = halfword(address | 4) << 22 >> 22;
        int[] left  = { Math.max(  0, gx - gp            ),
                        Math.max(  0, gx + gp            ) };
        int[] right = { Math.min(383, gx - gp + width - 1),
                        Math.min(383, gx + gp + width - 1) };

        // Check horizontal positions
        if (!lon || left[0] > 383 || right[0] < 0)
            left[0] = -1; // Not in-frame
        if (!ron || left[1] > 383 || right[1] < 0)
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
                    if ((pixels[buffer][dest + x] >> bits & 4) == 0) {

                        // Sample the pixel in the background
                        int sample = sampleBackground(
                            MX[eye] >> 9, MY[eye] >> 9,
                            bgMapBase, scx, scy, overplane
                        );

                        // Store the pixel if it isn't invisible
                        if (sample != -1)
                            pixels[buffer][dest + x] = (byte) (
                                (4 | sample) << bits |
                                pixels[buffer][dest + x] & 0xF0 >> bits
                            );
                    }

                    // Update background source position
                    MX[eye] += DX;
                    MY[eye] += DY;
                } // x

            } // eye

        } // y

    }

    // Draw an object world
    private void drawObject(int group) {
        int start = group == 0 ? 0 : spt[group - 1] + 1 & 1023;
        int end   = spt[group];

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
            if (jy > 223 || jy + 8 < 0)
                continue; // Object is not in-frame
            int top    = Math.max(  0, jy);
            int bottom = Math.min(223, jy + 7);

            // Retrieve horizontal properties
            int   jx    = halfword(address) << 22 >> 22;
            int[] jp    = { bits << 22 >> 22, -(bits << 22 >> 22) };
            int[] left  = { Math.max(  0, jx - jp[0]    ),
                            Math.max(  0, jx + jp[0]    ) };
            int[] right = { Math.min(383, jx - jp[0] + 7),
                            Math.min(383, jx + jp[0] + 7) };

            // Check horizontal positions
            if (!lon || left[0] > 383 || right[0] < 0)
                left[0] = -1; // Not in-frame
            if (!ron || left[1] > 383 || right[1] < 0)
                left[1] = -1; // Not in-frame

            // Retrieve common object parameters
            bits = halfword(address | 6);
            int     jca  = bits & 2047;
            boolean hflp = (bits & 0x2000) != 0;
            boolean vflp = (bits & 0x1000) != 0;
            int     plts = jplt[bits >> 14 & 3];

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
                    if ((pixels[buffer][dest + x] >> bits & 4) != 0)
                        continue; // The pixel has already been written

                    // Sample the pixel in the character
                    int sample = sampleCharacter(jca,
                        x - jx + jp[eye], y - jy, hflp, vflp);
                    if (sample == 0)
                        continue; // Pixel is invisible

                    // Store the pixel
                    pixels[buffer][dest + x] = (byte) (
                        (4 | plts >> (sample << 1) & 3) << bits |
                        pixels[buffer][dest + x] & 0xF0 >> bits
                    );

                } // x

            } // y

        } while (o != end && (o = o + 1 & 1023) > -1); // o

    }

    // Read a halfword from memory
    private int halfword(int address) {
        return ram[address] & 0xFF | ram[address | 1] << 8;
    }

    // Draw an image into a frame buffer
    private void render() {

        // Initialize pixel buffer
        byte clear = (byte) (bkcol << 4 | bkcol);
        for (int x = 0; x < 0x15000; x++)
            pixels[buffer][x] = clear;

        // Locate the foremost world and object group
        int address = 0x0003DBE0;
        int world   = 31;
        int group   =  0;
        for (; world >= 0; world--, address -= 32) {
            if ((ram[address    ] & 0x40) !=    0) // Control world
                break;
            if ((ram[address | 1] & 0xC0) ==    0) // Dummy world
                continue;
            if ((ram[address | 1] & 0x30) == 0x30) // Object world
                group = group - 1 & 3; 
        }

        // Draw worlds from front to back
        for (world++, address += 32; world < 32; world++, address += 32) {
            if ((ram[address    ] & 0x40) !=    0)   // Control world
                break;
            if ((ram[address | 1] & 0xC0) ==    0)   // Dummy world
                continue;
            if ((ram[address | 1] & 0x30) == 0x30) { // Object world
                drawObject(group); 
                group = group + 1 & 3;
            } else drawBackground(address);          // Background world
        }

        // Encode the pixel buffer into the appropriate frame buffer
        int[] buffers = {
            0x00000000 | buffer << 15,
            0x00010000 | buffer << 15
        };

        // Process all pixels in the pixel buffer
        for (int y=0, src=0, dest=0; y < 224; y+=4, src+=1152, dest-=24575)
        for (int x = 0; x < 384; x++, src -= 1535, dest += 64) {
            int[] bits = { 0, 0 };

            // Process all pixels for the current byte
            for (int p = 0; p < 4; p++, src += 384) {
                int pix = pixels[buffer][src];
                for (int eye = 0; eye < 2; eye++)
                    bits[eye] = bits[eye] >> 2 | (pix >> (eye << 2) & 3) << 6;
            }

            // Update the bytes in VIP memory
            for (int eye = 0; eye < 2; eye++)
                ram[buffers[eye] | dest] = (byte) bits[eye];
        }

        // Record a snapshot of the column table state
        for (int x = 0; x < 192; x += 2) {
            columns[buffer][x    ] = (ram[0x0003DCA5 + x] & 0xFF) + 1;
            columns[buffer][x + 1] = (ram[0x0003DEA5 + x] & 0xFF) + 1;
        }

        // Record a snapshot of the brightness levels
        if (dp_disp && dp_synce) {
            bright[buffer][1] = brt[0];
            bright[buffer][2] = brt[1];
            bright[buffer][3] = brt[0] + brt[1] + brt[2];
        } else bright[buffer][1] = bright[buffer][2] = bright[buffer][3] = 0;
    }

    // Sample the brightness level of a pixel from 
    private int sampleBackground(int mx, int my, int bgMapBase,
        int scx, int scy, int overplane) {
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
        return sample == 0 ? -1 : gplt[bits >> 14 & 3] >> (sample << 1) & 3;
    }

    // Sample a pixel from a character
    private int sampleCharacter(int index, int x, int y,
        boolean hFlip, boolean vFlip) {
        if (hFlip) x = 7 - x;
        if (vFlip) y = 7 - y;
        return ram[0x00006000 + (index >> 9 << 15) | // Table
            (index & 511) << 4 | y << 1 | x >> 2]    // Byte
            >> ((x & 3) << 1) & 3;                   // Pixel
    }

    // CPU is writing into frame buffer
    private void writePixels(int address, int type, int value) {

        // Calculate image coordinates
        int x = (address & 0x007FC0) >> 6;
        int y = (address & 0x00003F) << 2;

        // Not drawing into a frame buffer
        if (address >= 0x020000 || x > 383 || y > 223)
            return;

        // Precalculate working variables
        byte[] buffer = pixels[address >> 15 & 1];
        int    count  = JavaVUE.typeSize(type) << 2;
        int    shift  = address >> 14 & 4;
        int    mask   = ~(0xF << shift);
        int    offset = y * 384 + x;

        // Draw the pixels into the frame buffer
        for (int p = 0; p < count && y < 224; p++, y++) {
            buffer[offset] = (byte) (
                buffer[offset] & mask |
                (value & 3) << shift
            );
            value >>=   2;
            offset += 384;
        }
            
    }

}
