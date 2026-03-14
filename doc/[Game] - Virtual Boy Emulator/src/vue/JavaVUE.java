package vue;
import vue.*;

// Java implementation of emulation core
class JavaVUE implements VUE {

    // Emulation fields
    JavaCPU     cpu;     // CPU state
    JavaGamePad gamePad; // Game pad serial interface
    JavaLink    link;    // Communication port
    JavaTimer   timer;   // High-frequency timer
    JavaVIP     vip;     // Video processor
    JavaVSU     vsu;     // Audio processor

    // Memory
    byte[] rom;  // ROM buffer
    byte[] sram; // SRAM buffer
    byte[] wram; // WRAM buffer

    // Instance fields
    int              brkCode;   // Application-supplied break code
    int              brkMask;   // Bit mask for checking breakpoints
    Breakpoint[]     brkPoints; // Active breakpoints
    Breakpoint.Stack brkStack;  // Value stack for breakpoint evaluation
    int              wcr;       // Wait Control Register



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    JavaVUE() {

        // Configure emulation fields
        cpu     = new JavaCPU    (this);
        gamePad = new JavaGamePad(this);
        link    = new JavaLink   (this);
        timer   = new JavaTimer  (this);
        vip     = new JavaVIP    (this);
        vsu     = new JavaVSU    (this);

        // Configure memory
        wram = new byte[0x10000];

        // Configure instance fields
        brkMask   = Breakpoint.ALWAYS;
        brkPoints = new Breakpoint[0];
        brkStack  = new Breakpoint.Stack();

        // Startup tasks
        reset();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Static Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Process two simulations as linked communication peers
    // This generic implementation is for use with mismatched VUE instances
    static int[] emulate(VUE a, VUE b, int cycles) {
        int     aLeft  = cycles    , bLeft = cycles;
        boolean aDone  = aLeft <= 0, bDone = bLeft <= 0;
        int     before = a.read(0x02000000, VUE.U8, true) &
                         b.read(0x02000000, VUE.U8, true) | 0x10;

        // Keep processing until both simulations have completed
        while (!(aDone && bDone)) {
            cycles =
                aDone ? Math.max(1, bLeft - aLeft) :
                bDone ? Math.max(1, aLeft - bLeft) :
                Math.min(a.getComCycles(), b.getComCycles())
            ;

            // Process both simulations
            if (!aDone && (bDone ? aLeft > bLeft : aLeft >= bLeft))
                aLeft -= cycles - a.emulate(cycles);
            if (!bDone && (aDone ? bLeft > aLeft : bLeft >= aLeft))
                bLeft -= cycles - b.emulate(cycles);

            // Read communication registers
            int aCCR  = a.read(0x02000000, VUE.U8, true);
            int bCCR  = b.read(0x02000000, VUE.U8, true);
            int aCCSR = a.read(0x02000004, VUE.U8, true);
            int bCCSR = b.read(0x02000004, VUE.U8, true);

            // Check for a data transfer
            int after  = before & (aCCR ^ bCCR);
                before = aCCR & bCCR | 0x10;
            int aCDTR  = -1, bCDTR = -1;
            if ((after & 0x12) == 0x12) {
                aCDTR = a.read(0x02000008, VUE.U8, true);
                bCDTR = b.read(0x02000008, VUE.U8, true);
            }

            // Perform communications
            a.communicate(bCCSR, bCDTR);
            b.communicate(aCCSR, aCDTR);

            // Check for finished status
            aDone = bDone&&aLeft<=bLeft || aLeft<=0 || a.getBreakCode()!=0;
            bDone = aDone&&bLeft<=aLeft || bLeft<=0 || b.getBreakCode()!=0;
        }

        return new int[] { aLeft, bLeft };
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Used by the implementation for managing communications
    public void communicate(int ccsr, int cdtr) {

        // Configure communication port state
        link.communicate((ccsr & 0x02) != 0, (ccsr & 0x08) != 0, cdtr);

        // Configure CPU state
        if (cdtr == -1 || (cpu.exception & 0x0100) != 0)
            return;
        cpu.exception = 0;
        cpu.interrupt();
    }

    // Process the simulation
    public int emulate(int cycles) {

        // Initialize state prior to emulation
        brkCode = 0;

        // Process for at least as many CPU cycles as specified
        while (cycles > 0) {

            // Exception processing
            if (cpu.stage == JavaCPU.EXCEPTION)
                cpu.cycles = cpu.exception();

            // Get CPU cycles until next interrupt/auto break
            else if (cpu.halt != JavaCPU.NONE) {
                cpu.stage  = JavaCPU.INTERRUPT;
                cpu.cycles = cycles;

                // Process maskable interrupts
                if (cpu.psw_i == 0) {
                    cpu.cycles = gamePad.untilIRQ(cpu.cycles);
                    cpu.cycles = link   .untilIRQ(cpu.cycles);
                    cpu.cycles = timer  .untilIRQ(cpu.cycles);
                    cpu.cycles = vip    .untilIRQ(cpu.cycles);
                }

            }

            // Process by CPU pipeline stage
            else outer: for(;;) {
                switch (cpu.stage) {
                    case JavaCPU.FETCH16: cpu.fetch16(); break;
                    case JavaCPU.FETCH32: cpu.fetch32(); break;
                    case JavaCPU.EXECUTE: cpu.execute(); break;
                    default: break outer;
                }
                if (brkCode != 0)
                    return cycles;
            }

            // Process subsystems
            if (cpu.cycles > 0) {
                gamePad.emulate(cpu.cycles);
                link   .emulate(cpu.cycles);
                timer  .emulate(cpu.cycles);
                vip    .emulate(cpu.cycles);
                vsu    .emulate(cpu.cycles);
                cycles    -= cpu.cycles;
                cpu.cycles = 0;
            }

            // Check for interrupts
            if (cpu.stage == JavaCPU.INTERRUPT)
                cpu.interrupt();
            if (brkCode != 0)
                return cycles;
        }

        // Automatic break
        return cycles;
    }

    // Process the simulation with a communication peer
    public int[] emulate(int cycles, VUE peer) {
        return peer == null ?
            new int[] { emulate(cycles), 0 } : emulate(this, peer, cycles);
    }

    // Retrieve the audio sample buffer
    public byte[] getAudioBuffer() {
        return vsu.buffer;
    }

    // Retrieve the current audio filter setting
    public int getAudioFilter() {
        return vsu.filter;
    }

    // Retrieve the buffer position of the next audio sample
    public int getAudioPosition() {
        return vsu.position;
    }

    // Retrieve the audio sampling rate
    public int getAudioRate() {
        return vsu.rate;
    }

    // Retrieve the mask for triggered breakpoints
    public int getBreakMask() {
        return brkMask;
    }

    // Retrieve the application-supplied break code
    public int getBreakCode() {
        return brkCode;
    }

    // Retrieve the current set breakpoints
    public Breakpoint[] getBreakpoints() {
        return Breakpoint.cloneAll(brkPoints);
    }

    // Determine how many CPU cycles can be processed without communication
    public int getComCycles() {
        return cpu.halt == 0 || cpu.exception != 0 ? 1 :
            untilIRQ(!link.c_clk_sel&&link.c_stat ? link.cycles : vip.cycles);
    }

    // Retrieve the exception code being processed
    public int getExceptionCode() {
        return cpu.exception;
    }

    // Retrieve the most recently rendered image into a pixel buffer
    public int[] getFrame(int[] pixels, int leftColor, int rightColor) {
        int[]   brt    = vip.bright [vip.buffer];
        int[]   cols   = vip.columns[vip.buffer];
        byte[]  pix    = vip.pixels [vip.buffer];
        int[][] colors = { { 0xFF000000, 0, 0, 0 }, { 0xFF000000, 0, 0, 0 } };

        // Parse the RGB components from the output colors
        int[][] rgb = {
            { leftColor >>16 & 0xFF, leftColor >>8 & 0xFF, leftColor  & 0xFF },
            { rightColor>>16 & 0xFF, rightColor>>8 & 0xFF, rightColor & 0xFF }
        };

        // Error checking
        if (pixels == null || pixels.length < 0x15000)
            pixels = new int[0x15000];

        // Process all columns
        for (int x = 0, col = 0, pos = 0; x < 384; x++, pos -= 0x14FFF) {

            // Scale output colors every 4 columns
            if ((x & 3) == 0) {
                for (int eye = 0; eye < 2; eye++, col++)
                for (int y = 1; y < 4; y++) {
                    colors[eye][y] = 0xFF000000;
                    for (int z = 0, bits = 16; z < 3; z++, bits -= 8)
                        colors[eye][y] |= (
                            rgb[eye][z] *
                            Math.min(127, brt[y] * cols[col]) /
                            127
                        ) << bits;
                }
            }

            // Process all rows
            for (int y = 0; y < 224; y++, pos += 384) {
                int bits    = pix[pos];
                pixels[pos] = 0xFF000000;
                for (int eye = 0; eye < 2; eye++, bits >>= 4)
                    pixels[pos] |= colors[eye][bits & 3];
            }

        } // c

        return pixels;
    }

    // Retrieve the current frame buffer index
    public int getFrameBufferIndex() {
        return vip.buffer;
    }

    // Retrieve the current frame index
    public int getFrameIndex() {
        return vip.frame;
    }

    // Retrieve the current game pad state
    public int getKeys() {
        return gamePad.keys;
    }

    // Retrieve the previous program counter address
    public int getPreviousProgramCounter() {
        return cpu.lastPC;
    }

    // Retrieve the current program counter address
    public int getProgramCounter() {
        return cpu.pc;
    }

    // Retrieve the value of a program register
    public int getProgramRegister(int index) {
        return index < 0 || index > 31 ? 0 : cpu.registers[index];
    }

    // Retrieve the current ROM into a byte buffer
    // Returns true if successful
    public boolean getROM(byte[] buffer, int offset) {
        return getMemory(rom, buffer, offset);
    }

    // Retrieve the current ROM size in bytes
    public int getROMSize() {
        return rom == null ? 0 : rom.length;
    }

    // Retrieve the current SRAM into a byte buffer
    // Returns true if successful
    public boolean getSRAM(byte[] buffer, int offset) {
        return getMemory(sram, buffer, offset);
    }

    // Retrieve the current SRAM size in bytes
    public int getSRAMSize() {
        return sram == null ? 0 : sram.length;
    }

    // Retrieve the value of a system register
    public int getSystemRegister(int regID) {
        return cpu.getSystemRegister(regID);
    }

    // Retrieve the analog audio output filter setting
    public boolean isAudioAnalog() {
        return vsu.analog;
    }

    // Determine whether audio output is enabled
    public boolean isAudioEnabled() {
        return vsu.enabled;
    }

    // Determine whether the simulated CPU is in halt status
    public boolean isHalted() {
        return cpu.halt != JavaCPU.NONE;
    }

    // Read a value from the bus
    public int read(int address, int type, boolean debug) {

        // Validate type parameter
        int size = typeSize(type);
        if (size == 0) return 0;

        // Select memory region and address
        int region  = address >> 24 & 7;
        address    &= 0x00FFFFFF & -size;

        // Process access by memory region
        switch (region) {
            case 0: return vip.read    (address, type, debug); // VIP
            case 1: return vsu.read    (address, type, debug); // VSU
            case 2: return readHardware(address, type, debug); // Other
            case 3: return 0; // Unmapped
            case 4: return 0; // Cartridge expansion
            case 5: return readMemory(                         // WRAM
                wram, address & wram.length - 1, type);
            case 6: return sram == null ? 0 : readMemory(      // SRAM
                sram, address & sram.length - 1, type);
            case 7: return rom  == null ? 0 : readMemory(      // ROM
                rom,  address & rom.length  - 1, type);
        }

        return 0; // Required by compiler
    }

    // Read values from the bus into a byte buffer
    public boolean read(int address, int type, byte[] data, int offset,
        int length, boolean debug) {

        // Select parameters by size of data type
        int size  = typeSize(type);
        length   &= -size;

        // Error checking
        if (
            data   == null ||
            size   == 0    ||
            offset <  0    ||
            length <  size ||
            offset + length > data.length
        ) return false;

        // Process elements
        for (int x = 0; x < length; x += size) {

            // Read the value from the bus
            int value = read(address, type, debug);

            // Encode the value into the byte buffer
            data[offset] = (byte) value;
            if (size != 1)
                data[offset + 1] = (byte) (value >> 8);
            if (size == 4) {
                data[offset + 2] = (byte) (value >> 16);
                data[offset + 3] = (byte) (value >> 24);
            }

            // Update positions
            offset  += size;
            address += size;
        }

        return true;
    }

    // Perform system initialization
    public void reset() {

        // Configure instance fields
        brkCode = 0;
        wcr     = 0;

        // Reset subsystems
        cpu    .reset();
        gamePad.reset();
        link   .reset();
        timer  .reset();
        vip    .reset();
        vsu    .reset();

        // Reset WRAM to zeroes to aid with debugging
        // The hardware does not do this
        for (int x = 0; x < wram.length; x++)
            wram[x] = 0;
    }

    // Specify whether to simulate analog audio output
    public void setAudioAnalog(boolean use) {
        vsu.analog = use;
    }

    // Specify a new audio sample  buffer
    // Returns true if successful
    public boolean setAudioBuffer(byte[] buffer) {

        // Error checking
        if (buffer != null && (buffer.length == 0 || (buffer.length & 3) != 0))
            return false;

        // Configure VSU
        vsu.buffer   = buffer;
        vsu.position = 0;
        return true;
    }

    // Enable or disable audio output
    public void setAudioEnabled(boolean enabled) {
        vsu.setEnabled(enabled);
    }

    // Specify a new audio filter setting
    // Returns true if successful
    public boolean setAudioFilter(int filter) {
        if (filter < HARDWARE || filter > LINEAR)
            return false;
        vsu.filter = filter;
        return true;
    }

    // Specify a new audio sampling rate
    // Returns the actual sampling rate to be used
    public int setAudioRate(int hertz) {
        return vsu.setRate(hertz);
    }

    // Specify which breakpoints are checked
    public void setBreakMask(int mask) {
        brkMask = mask;
    }

    // Specify a new set of breakpoints
    public void setBreakpoints(Breakpoint[] breakpoints) {

        // All breakpoints are being removed
        if (breakpoints == null || breakpoints.length == 0) {
            brkPoints = new Breakpoint[0];
            return;
        }

        // Configure instance fields
        brkPoints = Breakpoint.cloneAll(breakpoints);
        brkStack.setCapacity(brkPoints);
    }

    // Specify the current frame buffer index
    public int setFrameBufferIndex(int buffer) {
        if ((buffer & 1) == buffer)
            vip.buffer = buffer;
        return vip.buffer;
    }

    // Specify the current frame index
    public int setFrameIndex(int frame) {
        if ((frame & 15) == frame && frame <= vip.frmcyc)
            vip.frame = frame;
        return vip.frame;
    }

    // Specify a game pad configuration
    public int setKeys(int keys) {
        return gamePad.keys = keys & 0xFFFF;
    }

    // Specify a new address for the program counter
    public int setProgramCounter(int address) {
        return cpu.setProgramCounter(address);
    }

    // Specify a new value for a program register
    // Returns the actual new value of the program register
    public int setProgramRegister(int index, int value) {
        return index < 1 || index > 31 ? 0 : (cpu.registers[index] = value);
    }

    // Specify a new ROM buffer
    // Returns true if successful
    public boolean setROM(byte[] buffer, int offset, int length) {

        // Error checking
        if (buffer == null || length == 0) {
            rom = null;
            return true;
        }

        // Process the buffer
        buffer = setMemory(buffer, offset, length, 1);
        if (buffer == null) return false;

        // Update ROM
        rom = buffer;
        return true;
    }

    // Specify a new SRAM buffer
    // Returns true if successful
    public boolean setSRAM(byte[] buffer, int offset, int length) {

        // Error checking
        if (buffer == null || length == 0) {
            sram = null;
            return true;
        }

        // Process the buffer
        buffer = setMemory(buffer, offset, length, 1);
        if (buffer == null) return false;

        // Update SRAM
        sram = buffer;
        return true;
    }

    // Store a value into a system register
    // Returns the actual new value of the system register
    public int setSystemRegister(int regID, int value) {
        return cpu.setSystemRegister(regID, value, true);
    }

    // Used by the implementation for scheduling interrupts
    public int untilIRQ(int cycles) {
        if (cpu.psw_id == 1)
            return cycles;
        cycles = gamePad.untilIRQ(cycles);
        cycles = link   .untilIRQ(cycles);
        cycles = timer  .untilIRQ(cycles);
        cycles = vip    .untilIRQ(cycles);
        return cycles;
    }

    // Write a value to the bus
    public void write(int address, int type, int value, boolean debug) {

        // Validate type parameter
        int size = typeSize(type);
        if (size == 0) return;

        // Select memory region and address
        int region  = address >> 24 & 7;
        address    &= 0x00FFFFFF & -size;

        // Process access by memory region
        switch (region) {
            case 0: vip.write    (address, type, value, debug); return; // VIP
            case 1: vsu.write    (address, type, value, debug); return; // VSU
            case 2: writeHardware(address, type, value, debug); return; //Other
            case 3: return; // Unmapped
            case 4: return; // Cartridge expansion
            case 5:                           writeMemory(              // WRAM
                wram, address & wram.length - 1, type, value);
                return;
            case 6: if (sram != null)         writeMemory(              // SRAM
                sram, address & sram.length - 1, type, value);
                return;
            case 7: if (debug && rom != null) writeMemory(              // ROM
                rom,  address & rom.length  - 1, type, value);
                return;
        }

    }

    // Write values to the bus from a byte buffer
    public boolean write(int address, int type, byte[] data, int offset,
        int length, boolean debug) {

        // Select parameters by size of data type
        int size  = typeSize(type);
        length   &= -size;

        // Error checking
        if (
            data   == null ||
            size   == 0    ||
            offset <  0    ||
            length <  size ||
            offset + length > data.length
        ) return false;

        // Process elements
        for (int x = 0; x < length; x += size) {

            // Parse the value from the byte buffer
            int value = (int) data[offset] & 0xFF;
            if (size != 1) value |=
                ((int) data[offset + 1] & 0xFF) << 8;
            if (size == 4) value |=
                ((int) data[offset + 2] & 0xFF) << 16 |
                 (int) data[offset + 3]         << 24;

            // Write the value to the bus
            write(address, type, value, debug);

            // Update positions
            offset  += size;
            address += size;
        }

        return true;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Determine if any break point has occurred here
    boolean checkBreakpoints(int event, Instruction inst) {

        // Check all breakpoints
        for (int x = 0; x < brkPoints.length; x++) {
            Breakpoint brk = brkPoints[x];
            if (
                (brk.getEvents() & brkMask) == 0           || // Masked
                !brk.evaluate(this, event, inst, brkStack) || // Not evaluated
                !brkStack.isTrue()                            // Was false
            ) continue;

            // A break was triggered
            brkCode = brk.getBreakCode();
            return true;
        }

        // No break was triggered
        return false;
    }

    // Read a value from a memory buffer
    int readMemory(byte[] mem, int offset, int type) {
        int size = typeSize(type);

        // Process the first byte of the value
        int value = (int) mem[offset] & 0xFF;
        if (size == 1) {
            if (type == VUE.S8 && (value & 0x80) != 0)
                value |= 0xFFFFFF00; // Sign-extend
            return value;
        }

        // Process the second byte of the value
        value |= ((int) mem[offset + 1] & 0xFF) << 8;
        if (size == 2) {
            if (type == VUE.S16 && (value & 0x8000) != 0)
                value |= 0xFFFF0000; // Sign-extend
            return value;
        }

        // Process the third and fourth bytes of the value
        return value |
            ((int) mem[offset + 2] & 0xFF) << 16 |
             (int) mem[offset + 3]         << 24
        ;
    }

    // Adjust a value to conform to a given data type
    static int tweakValue(int value, int type) {
        switch (type) {
            case VUE.S8:  return value & 0x000000FF |
                ((value & 0x00000080) != 0 ? 0xFFFFFF00 : 0);
            case VUE.S16: return value & 0x0000FFFF |
                ((value & 0x00008000) != 0 ? 0xFFFF0000 : 0);
            case VUE.U8:  return value & 0x000000FF;
            case VUE.U16: return value & 0x0000FFFF;
        }
        return value;
    }

    // Determine the size in bytes of a data type
    static int typeSize(int type) {
        switch (type) {
            case VUE.S8:  case VUE.U8:  return 1;
            case VUE.S16: case VUE.U16: return 2;
            case VUE.S32:               return 4;
        }
        return 0;
    }

    // Write a value into a memory buffer
    void writeMemory(byte[] mem, int offset, int type, int value) {
        int size = typeSize(type);

        // Process the first byte of the value
        mem[offset] = (byte) value;
        if (size == 1) return;

        // Process the second byte of the value
        mem[offset + 1] = (byte) (value >> 8);
        if (size == 2) return;

        // Process the third and fourth bytes of the value
        mem[offset + 2] = (byte) (value >> 16);
        mem[offset + 3] = (byte) (value >> 24);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Retrieve the current ROM or SRAM into a byte buffer
    private boolean getMemory(byte[] mem, byte[] data, int offset) {

        // Implicit success if memory is unmapped
        if (mem == null) return true;

        // Error checking
        if (
            data   == null ||
            offset <  0    ||
            offset + mem.length > data.length
        ) return false;

        // Copy the memory into the byte buffer
        System.arraycopy(mem, 0, data, offset, mem.length);
        return true;
    }

    // Read a value from a hardware component port
    private int readHardware(int address, int type, boolean debug) {
        int value = 0;

        // Read port by address
        if ((address & 3) == 0) switch (address >> 2) {
            case 0x000000 >> 2: value = link   .readCCR (); break;
            case 0x000004 >> 2: value = link   .readCCSR(); break;
            case 0x000008 >> 2: value = link.cdtr;          break;
            case 0x00000C >> 2: value = link.cdrr;          break;
            case 0x000010 >> 2: value = gamePad.readSDLR(); break;
            case 0x000014 >> 2: value = gamePad.readSDHR(); break;
            case 0x000018 >> 2: value = timer  .readTLR (); break;
            case 0x00001C >> 2: value = timer  .readTHR (); break;
            case 0x000020 >> 2: value = timer  .readTCR (); break;
            case 0x000024 >> 2: value = wcr;                break;
            case 0x000028 >> 2: value = gamePad.readSCR (); break;
        }

        return tweakValue(value, type);
    }

    // Specify a new memory buffer with a minimum length
    private byte[] setMemory(byte[] data, int offset, int length, int min) {

        // Error checking
        if (
            offset < 0 ||
            length < 0 ||
            offset + length > data.length
        ) return null;

        // Ensure the length is a power of two in the appropriate range
        for (int x = min; ; x <<= 1) {
            if (x == length)     break;
            if (x == 0x01000000) return null;
        }

        // Copy the memory from the byte b uffer
        byte[] mem = new byte[length];
        System.arraycopy(data, offset, mem, 0, length);
        return mem;
    }

    // Write a value to a hardware component port
    private void writeHardware(int address,int type,int value,boolean debug) {
        value &= 0xFF;

        // Write port by address
        switch (address >> 2) {
            case 0x000000 >> 2: link   .writeCCR (value);     break;
            case 0x000004 >> 2: link   .writeCCSR(value);     break;
            case 0x000008 >> 2:            link.cdtr = value; break;
            case 0x00000C >> 2: if (debug) link.cdrr = value; break;
            case 0x000010 >> 2: // Fallthrough, SDLR
            case 0x000014 >> 2: value = 0; /* SDHR */         break;
            case 0x000018 >> 2: timer  .writeTLR (value);     break;
            case 0x00001C >> 2: timer  .writeTHR (value);     break;
            case 0x000020 >> 2: timer  .writeTCR (value);     break;
            case 0x000024 >> 2: wcr = value & 3;              break;
            case 0x000028 >> 2: gamePad.writeSCR (value);     break;
        }

    }
    
}
