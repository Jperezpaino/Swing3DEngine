package vue;

// Audio processor
class JavaVSU {

    // Instance fields
    boolean analog;     // Simulate analog filtering
    byte[]  buffer;     // Sample buffer
    int     cycles;     // Current CPU cycle within second
    boolean enabled;    // Audio output is enabled
    int     filter;     // Filtering mode
    int[]   modulation; // Modulation RAM
    JavaVUE parent;     // Parent emulation context
    int     position;   // Sample buffer position
    int     rate;       // Sampling rate in hertz
    int     sample;     // Current audio sample within second
    int[]   samples;    // Output audio samples
    int[][] waves;      // PCM waves
    JavaVSU_Channel[] channels; // Sound sources

    // Analog fields
    float     coef;    // RC filter coefficient
    float[][] input;   // Input sample history
    float[]   output1; // Output sample history



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    JavaVSU(JavaVUE parent) {

        // Configure instance fields
        analog      = true;
        enabled     = true;
        filter      = VUE.HARDWARE;
        input       = new float[2][2];
        modulation  = new int[32];
        output1     = new float[2];
        this.parent = parent;
        samples     = new int[2];
        waves       = new int[5][32];
        setRate(41700);

        // Configure channels
        channels = new JavaVSU_Channel[] {
            new JavaVSU_Channel(this, JavaVSU_Channel.NORMAL),
            new JavaVSU_Channel(this, JavaVSU_Channel.NORMAL),
            new JavaVSU_Channel(this, JavaVSU_Channel.NORMAL),
            new JavaVSU_Channel(this, JavaVSU_Channel.NORMAL),
            new JavaVSU_Channel(this, JavaVSU_Channel.FREQMOD),
            new JavaVSU_Channel(this, JavaVSU_Channel.NOISE)
        };

    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Process the simulation
    void emulate(int cycles) {

        // Audio output is disabled
        if (!enabled) {
            for (int x = 0; x < 6; x++)
                channels[x].emulate(cycles);
            this.cycles = (this.cycles + cycles) % 20000000;
            return;
        }

        // Process all elapsed samples
        while (cycles > 0) {

            // Calculate the number of CPU cycles until the next audio sample
            int next = -this.cycles + (int)
                ( ((sample + 1) * 20000000L + rate - 1) / rate );

            // Process for up to the next sample's worth of CPU cycles
            int count = Math.min(next, cycles);
            for (int x = 0; x < 6; x++)
                channels[x].emulate(count);
            this.cycles = (this.cycles + count) % 20000000;

            // No sample has been generated
            if (count < next)
                return;

            // Output the next pair of stereo samples
            render();
            if (buffer != null) {
                buffer[position++] = (byte)  samples[0];
                buffer[position++] = (byte) (samples[0] >> 8);
                buffer[position++] = (byte)  samples[1];
                buffer[position++] = (byte) (samples[1] >> 8);
                if (position == buffer.length)
                    position = 0;
            }

            // State management
            cycles -= count;
            if (++sample == rate)
                sample = 0;
        }

    }

    // Initialize VSU for system reset
    void reset() {

        // Configure instance fields
        cycles = 0;
        sample = 0;
        setRate(rate);

        // Configure channels
        for (int x = 0; x < 6; x++)
            channels[x].reset();

        // Configure RAM
        for (int x = 0; x < 32; x++) {
            for (int y = 0; y < 5; y++)
                waves[y][x] = 0;
            modulation[x]   = 0;
        }

    }

    // Read a value from the VSU bus
    // This is not allowed on the hardware
    int read(int address, int type, boolean debug) {

        // Error checking
        if (!debug || (address & 3) != 0)
            return 0;

        // Adjust address
        address = (address & 0x000007FF) >> 2;

        // RAM
        if (address < 0x000280 >> 2)
            return waves[address >> 5][address & 31];
        else if (address < 0x000300 >> 2)
            return modulation         [address & 31];

        // I/O register -- Read by channel
        int channel = address >> 4 & 15;
        if (channel < 6)
            return channels[channel].read(address & 15);

        // Unmapped
        return 0;
    }

    // Enable or disable audio output
    void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled)
            sample = (int) ((long) cycles * rate / 20000000);
    }

    // Specify a new audio sampling rate
    // Returns the actual sampling rate to be used
    int setRate(int hertz) {
        rate = Math.max(1000, Math.min(100000, hertz));

        // Prepare analog parameters
        coef = 0.022f / (1.0f / rate + 0.022f);
        input[0][0] = input[1][0] = output1[0] = 
        input[0][1] = input[1][1] = output1[1] = 0;

        // Configure instance fields
        sample = (int) ((long) cycles * rate / 20000000);
        return rate;
    }

    // Write a value to the VSU bus
    void write(int address, int type, int value, boolean debug) {

        // Error checking
        if ((address & 3) != 0 || !debug && type != VUE.S8 && type != VUE.U8)
            return;

        // Adjust address
        address = (address & 0x000007FF) >> 2;

        // Wave RAM
        if (address < 0x000280 >> 2) {
            boolean playing = false;
            for (int x = 0; !(debug || playing) && x < 6; x++)
                playing = channels[x].int_enable;
            if (!playing)
                waves[address >> 5][address & 31] = value & 0x3F;
            return;
        }

        // Modulation RAM
        if (address < 0x000300 >> 2) {
            if (debug || !channels[4].int_enable)
                modulation[address & 31] = value << 24 >> 24;
            return;
        }

        // SSTOP
        if (address == 0x000580 >> 2) {
            if ((value & 1) != 0)
                for (int x = 0; x < 6; x++)
                    channels[x].int_enable = false;
            return;
        }

        // I/O register -- Write by channel
        int channel = address >> 4 & 15;
        if (channel < 6)
            channels[channel].write(address & 15, value);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Produce a pair of stereo output samples for the current state
    private void render() {
        samples[0] = samples[1] = 0;

        // Sample from each source channel
        for (int x = 0; x < 6; x++)
            channels[x].render(samples);

        // Process both stereo streams
        for (int x = 0; x < 2; x++) {

            // Processing for hardware mixing
            if (filter == VUE.HARDWARE)
                samples[x] = ((samples[x] >> 4) * 65534 + 685) / 1370;

            // Processing for high-precision mixing
            else samples[x] = (samples[x] + 3) / 6;

            // Apply analog output filter
            if (!analog)
                continue;
            input[0][x] = samples[x];
            output1 [x] = coef * (output1[x] + input[0][x] - input[1][x]);
            input[1][x] = input[0][x];
            samples [x] = Math.min(32767, Math.max(-32767, (int) output1[x]));
        }

    }

}
