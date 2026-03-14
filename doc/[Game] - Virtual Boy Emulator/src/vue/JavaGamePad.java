package vue;

// Game pad serial port interface
class JavaGamePad {

    // Instance fields
    int     clocks;    // Number of 0,1 clocks to the controller
    int     cycles;    // Primary clock counter
    int     keys;      // Application-defined controller state
    boolean interrupt; // Interrupt is being raised
    JavaVUE parent;    // Parent emulation context

    // SCR
    boolean k_int_inh; // Interrupts are disabled
    boolean para_si;   // Resetting controller read state
    boolean s_abt_dis; // Aborting hardware read operation
    boolean si_stat;   // A hardware read is underway
    boolean soft_ck;   // Current software clock state



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Intervals expressed as number of CPU cycles
    static final int CLOCK = 320; // 31.25 (times 2) KHz for hardware reads



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    JavaGamePad(JavaVUE parent) {
        this.parent = parent;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Process the simulation
    void emulate(int cycles) {

        // No action to perform
        if (!si_stat)
            return;

        // Calculate cycles until next tick
        this.cycles -= cycles;

        // Process all ellapsed ticks
        while (this.cycles <= 0) {
            signal(clocks & 1 ^ 1);
            if (!si_stat)
                break;
            this.cycles += CLOCK;
        }

    }

    // Initialize timer for system reset
    void reset() {

        // Configure instance fields
        clocks    = -1;
        cycles    = 0;
        interrupt = false;

        // Configure SCR fields
        k_int_inh = false;
        para_si   = false;
        s_abt_dis = false;
        soft_ck   = false;
    }

    // Simulate a read of SCR
    int readSCR() {
        return 0x4C |
            (k_int_inh ? 0x80 : 0) |
            (para_si   ? 0x20 : 0) |
            (soft_ck   ? 0x10 : 0) |
            (si_stat   ? 0x02 : 0) |
            (s_abt_dis ? 0x01 : 0)
        ;
    }

    // Simulate a read of SDHR
    int readSDHR() {
        return clocks != 31 ? 0 : keys >> 8;
    }

    // Simulate a read of SDLR
    int readSDLR() {
        return clocks != 31 ? 0 : keys & 0xFF;
    }

    // Determine how many CPU cycles can elapse before an interrupt request
    int untilIRQ(int cycles) {

        // An interrupt is currently active
        if (interrupt)
            return 0;

        // No interrupt will occur
        if (
            k_int_inh            || // Interrupt disabled
            !si_stat             || // No hardware read underway
            (keys & 0xFFF0) == 0 || // Invalid key state
            (keys & 0x000E) != 0    // Invalid key state
        ) return cycles;

        // Calculate the total number of cycles until the next interrupt
        return Math.min(cycles, this.cycles + (30 - clocks) * CLOCK);
    }

    // Simulate a write to SCR
    void writeSCR(int value) {

        // Parse flags
        k_int_inh     = (value & 0x80) != 0;
        para_si       = (value & 0x20) != 0;
        soft_ck       = (value & 0x10) != 0;
        boolean hw_si = (value & 0x04) != 0;
        s_abt_dis     = (value & 0x01) != 0;

        // Initiate a hardware read
        if (!(s_abt_dis || si_stat) && hw_si) {
            si_stat = true;
            clocks  = -1;
            cycles  = CLOCK;
        }

        // Cancel a hardware read
        if (si_stat && s_abt_dis) {
            si_stat = false;
            clocks  = 32;
        }

        // Software read settings
        if (!si_stat) {

            // Reset the controller for a software read
            if (para_si)
                clocks = -1;

            // Send a signal for a software read
            signal(soft_ck ? 0 : 1);
        }

        // Acknowledge an interrupt
        interrupt = interrupt && !k_int_inh;
        irq();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Update the state of the CPU interrupt request line for this subsystem
    private void irq() {
        parent.cpu.irq[0] = interrupt;
    }

    // Send a 0 or 1 signal to the controller
    private void signal(int state) {

        // The state did not change
        if (((clocks ^ state) & 1) == 0)
            return;

        // Increase the internal counter
        if (clocks < 31)
            clocks++;
        if (clocks != 31 || !si_stat)
            return;

        // Complete a hardware read operation
        si_stat = false;
        if (k_int_inh)
            return;

        // Configure interrupt status
        interrupt = interrupt ||
            (keys & 0xFFF0) != 0 && (keys & 0x000E) == 0;
        irq();
    }

}
