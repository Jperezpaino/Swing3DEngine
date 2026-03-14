package vue;

// Communication port interface
class JavaLink {

    // Instance fields
    int     cycles; // Primary clock counter
    boolean irqA;   // CCR interrupt is being raised
    boolean irqB;   // CCSR interrupt is being raised
    JavaVUE parent; // Parent emulation context

    // CCR
    boolean c_int_inh; // Interrupts are disabled
    boolean c_clk_sel; // True if external clock
    boolean c_stat;    // Communication is underway

    // CCSR
    boolean cc_int_inh; // Interrupts are disabled
    boolean cc_int_lev; // CC-Smp matches this for interrupt
    boolean cc_sig;     // Signal state written after communication
    boolean cc_smp;     // Signal state sampled after communication
    boolean cc_wr;      // Signal state written by software
    boolean cc_rd;      // Signal state sampled by software

    // Data registers
    int cdtr; // Transmitted data
    int cdrr; // Received data



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Intervals expressed as number of CPU cycles
    static final int CLOCK = 3200; // 50.0 KHz * 8 for communication



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    JavaLink(JavaVUE parent) {
        this.parent = parent;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update state between peers
    void communicate(boolean cc_wr, boolean cc_sig, int cdtr) {

        // Common processing
        cc_rd = this.cc_wr && cc_wr;
        if (cdtr == -1)
            return; // A communication has not been completed

        // End-of-communication processing
        c_stat = false;
        cycles = 0;
        cdrr   = cdtr;
        cc_smp = this.cc_sig && cc_sig && cc_rd;

        // Interrupt management
        irqA = irqA || !c_int_inh;
        irqB = irqB || !cc_int_inh && cc_int_lev == cc_smp;
        irq();
    }

    // Process the simulation
    void emulate(int cycles) {

        // No action to perform
        if (!c_stat || c_clk_sel)
            return;

        // Calculate cycles until communication completes
        this.cycles -= cycles;

        // Process communication
        if (this.cycles <= 0)
            communicate(cc_wr, cc_sig, 0x00);
    }

    // Initialize timer for system reset
    void reset() {

        // Configure instance fields
        irqA = false;
        irqB = false;

        // Configure CCR fields
        c_int_inh = false;
        c_clk_sel = false;
        c_stat    = false;

        // Configure CCSR fields
        cc_int_inh = true;
        cc_int_lev = true;
        cc_sig     = true;
        cc_smp     = true;
        cc_wr      = true;
        cc_rd      = true;

        // Configure data registers
        cdtr = 0;
        cdrr = 0;
    }

    // Simulate a read of CCR
    int readCCR() {
        return 0x6D |
            (c_int_inh ? 0x80 : 0) |
            (c_clk_sel ? 0x10 : 0) |
            (c_stat    ? 0x02 : 0)
        ;
    }

    // Simulate a read of CCSR
    int readCCSR() {
        return 0x60 |
            (cc_int_inh ? 0x80 : 0) |
            (cc_int_lev ? 0x10 : 0) |
            (cc_sig     ? 0x08 : 0) |
            (cc_smp     ? 0x04 : 0) |
            (cc_wr      ? 0x02 : 0) |
            (cc_rd      ? 0x01 : 0)
        ;
    }

    // Determine how many CPU cycles can elapse before an interrupt request
    // Passes on master even if interrupts disabled, in case enabled on remote
    int untilIRQ(int cycles) {

        // An interrupt is currently active
        if (irqA || irqB)
            return 0;

        // No interrupt will occur
        if (!c_stat || c_clk_sel) // Not communicating, remote clock
            return cycles;

        // Return the total number of cycles until the next potential interrupt
        return Math.min(cycles, this.cycles);
    }

    // Simulate a write to CCR
    void writeCCR(int value) {

        // Parse flags
        c_int_inh       = (value & 0x80) != 0;
        c_clk_sel       = (value & 0x10) != 0;
        boolean c_start = (value & 0x04) != 0;

        // Initiate a communication
        if (c_start && !c_stat)
            cycles = CLOCK;

        // Update communication state
        c_stat = c_stat || c_start;

        // Acknowledge an interrupt
        if (!(irqA && c_int_inh))
            return;
        irqA = false;
        irq();
    }

    // Simulate a write to CCSR
    void writeCCSR(int value) {

        // Parse flags
        cc_int_inh = (value & 0x80) != 0;
        cc_int_lev = (value & 0x10) != 0;
        cc_sig     = (value & 0x08) != 0;
        cc_wr      = (value & 0x02) != 0;
        cc_rd      = cc_wr;

        // Acknowledge an interrupt
        if (!(irqB && cc_int_inh))
            return;
        irqB = false;
        irq();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update the state of the CPU interrupt request line for this subsystem
    private void irq() {
        parent.cpu.irq[3] = irqA || irqB;
    }

}
