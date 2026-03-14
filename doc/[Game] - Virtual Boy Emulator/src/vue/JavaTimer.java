package vue;

// High-performance timer
class JavaTimer {

    // Instance fields
    int     counter;   // Ticks remaining before reload
    int     cycles;    // Primary clock counter
    boolean interrupt; // Interrupt is being raised
    JavaVUE parent;    // Parent emulation context
    int     reload;    // Refresh value

    // TCR
    int     t_clk_sel; // Tick interval in CPU cycles
    boolean t_enb;     // Timer function is active
    boolean tim_z_int; // Timer zero interrupt is enabled
    boolean z_stat;    // Zero status



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Intervals expressed as number of CPU cycles
    static final int US20  =  400; //  20 microseconds
    static final int US100 = 2000; // 100 microseconds



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    JavaTimer(JavaVUE parent) {
        this.parent = parent;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Process the simulation
    void emulate(int cycles) {

        // Timer is not enabled
        if (!t_enb)
            return;

        // Calculate cycles until next tick
        this.cycles -= cycles;

        // Process all ellapsed ticks
        while (this.cycles <= 0) {

            // Update the counter according to zero status
            if (counter-- != 0)
                interrupt = interrupt || counter == 0 && tim_z_int;
            else counter = reload;

            // Update state
            z_stat       = z_stat || counter == 0;
            this.cycles += t_clk_sel;
            irq();
        }

    }

    // Initialize timer for system reset
    void reset() {

        // Configure instance fields
        counter   = 0xFFFF;
        cycles    = 0;
        interrupt = false;
        reload    = 0x0000;

        // Configure TCR fields
        t_clk_sel = US100;
        t_enb     = false;
        tim_z_int = false;
        z_stat    = false;
    }

    // Simulate a read of TCR
    int readTCR() {
        return 0xE4 |
            (t_enb             ? 0x01 : 0) |
            (z_stat            ? 0x02 : 0) |
            (tim_z_int         ? 0x08 : 0) |
            (t_clk_sel == US20 ? 0x10 : 0)
        ;
    }

    // Simulate a read of THR
    int readTHR() {
        return counter >> 8 & 0xFF;
    }

    // Simulate a read of TLR
    int readTLR() {
        return counter & 0xFF;
    }

    // Determine how many CPU cycles can elapse before an interrupt request
    int untilIRQ(int cycles) {

        // An interrupt is currently active
        if (interrupt)
            return 0;

        // No interrupt will occur
        if (
            !(t_enb && tim_z_int) ||    // Interrupt disabled
            counter == 0 && reload == 0 // Interrupt will not be raised
        ) return cycles;

        // Calculate the total number of cycles until the next interrupt
        return Math.min(cycles, this.cycles +
            (counter == 0 ? reload : counter - 1) * t_clk_sel);
    }

    // Simulate a write to TCR
    void writeTCR(int value) {

        // Parse flags
        t_clk_sel          = (value & 0x10) == 0 ? US100 : US20;
        tim_z_int          = (value & 0x08) != 0;
        boolean z_stat_clr = (value & 0x04) != 0;
        boolean t_enb      = (value & 0x01) != 0;

        // Acknowledge interrupt
        interrupt = interrupt && tim_z_int;

        // Clear zero status
        if (z_stat_clr &&
            !(this.t_enb && !t_enb) &&    // Timer disable exclusivity
            (counter != 0 || !this.t_enb) // Zero clear condition
        ) interrupt = z_stat = false;

        // Update state
        cycles     = t_clk_sel;
        this.t_enb = t_enb;
        irq();
    }

    // Simulate a write to THR
    void writeTHR(int value) {
        writeReload(reload & 0x00FF | (value & 0xFF) << 8);
    }

    // Simulate a write to TLR
    void writeTLR(int value) {
        writeReload(reload & 0xFF00 | value & 0xFF);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update the state of the CPU interrupt request line for this subsystem
    private void irq() {
        parent.cpu.irq[1] = interrupt;
    }

    // Store a new value into the reload register
    private void writeReload(int value) {

        // Raise interrupt
        interrupt = interrupt || value == 0 && counter != 0 && tim_z_int;

        // Update state
        counter = reload = value;
        cycles  = t_clk_sel;
        z_stat  = z_stat || t_enb && reload == 0;
        irq();
    }

}
