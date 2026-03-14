package vue;

// Project imports
import vue.*;

// Audio source channel
class JavaVSU_Channel {

    // Instance fields
    boolean bit;       // Pseudorandom output bit
    int     envelope;  // Current envelope level
    int     freq_new;  // Pre-calculated frequency modification value
    int     frequency; // Current frequency value
    int     modpos;    // Modulation RAM position
    JavaVSU parent;    // Parent audio mixer
    int     position;  // Sampling position
    int     lfsr;      // Pseudorandom register
    int     type;      // Channel capabilities

    // Timing fields
    int cycEnvelope; // CPU cycles remaining until envelope changes
    int cycFreqMod;  // CPU cycles remaining until frequency changes
    int cycLength;   // CPU cycles remaining until channel deactivates
    int cycSample;   // CPU cycles remaining until sample change
    int maxEnvelope; // CPU cycles between envelope changes
    int maxFreqMod;  // CPU cycles between frequency changes
    int maxSample;   // CPU cycles between sample changes

    // SxINT
    boolean int_auto;     // Automatic shutoff is enabled
    int     int_interval; // Time until automatic shutoff
    boolean int_enable;   // Channel is enabled

    // SxLRV
    int lrv_left;  // Left stereo level
    int lrv_right; // Right stereo level

    // SxFQL, SxFQH
    int freq_write; // Most recently written frequency value

    // SxEV0, SxEV1
    int     ev0_value;     // Initial/reload envelope value
    int     ev0_interval;  // Envelope modification interval
    boolean ev0_direction; // Envelope will grow
    boolean ev1_enable;    // Envelope modifications are enabled
    boolean ev1_repeat;    // Envelope modifications repeat

    // S5EV1, S5SWP
    int     swp_clock;     // Frequency modification base clock
    boolean swp_direction; // Sweep will grow
    boolean swp_enable;    // Frequency modifications are enabled
    int     swp_function;  // Frequency modification function
    int     swp_interval;  // Frequency modification interval
    boolean swp_repeat;    // Modulation repeats
    int     swp_shift;     // Sweep shift amount

    // S6EV1
    int rng_tap; // Tap location

    // SxRAM
    int ram_wave; // PCM wave index



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Channel types
    static final int NORMAL  = 0;
    static final int FREQMOD = 1;
    static final int NOISE   = 2;

    // Noise generator tap locations
    private static final int[] TAPS = { 14, 10, 13, 4, 8, 6, 9, 11 };



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    JavaVSU_Channel(JavaVSU parent, int type) {
        this.parent = parent;
        this.type   = type;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Process the simulation
    void emulate(int cycles) {

        // Channel is not active
        if (!int_enable)
            return;

        // Process all cycles
        while (cycles > 0) {

            // Begin a frequency modification frame
            if (type == FREQMOD && cycFreqMod == maxFreqMod) {

                // Processing for sweep
                if (swp_function == 0) {
                    if (swp_direction)
                         freq_new = frequency + (frequency >> swp_shift);
                    else freq_new = frequency - (frequency >> swp_shift);

                    // Frequency overflow
                    if (freq_new > 2047) {
                        int_enable = false;
                        return;
                    }

                }

                // Processing for modulation
                else freq_new = freq_write + parent.modulation[modpos] & 2047;
            }

            // Cycles until next event
            int next = Math.min(cycles, cycSample  );
            if (ev1_enable)
                next = Math.min(next  , cycEnvelope);
            if (swp_enable && swp_interval != 0)
                next = Math.min(next  , cycFreqMod );
            if (int_auto)
                next = Math.min(next  , cycLength  );

            // Update timing counters
            cycles    -= next;
            cycSample -= next;
            if (ev1_enable)
                cycEnvelope -= next;
            if (int_auto)
                cycLength   -= next;
            if (swp_enable && swp_interval != 0)
                cycFreqMod  -= next;

            // Process length counter
            if (int_auto && cycLength == 0) {
                int_enable = false;
                return;
            }

            // Process source sample
            if (cycSample == 0) {
                cycSample = maxSample;

                // Noise processing
                if (type == NOISE) {
                    bit  = ((lfsr >> 7 ^ lfsr >> TAPS[rng_tap]) & 1) == 0;
                    lfsr = lfsr << 1 | (bit ? 0 : 1);
                }

                // PCM processing
                else position = position + 1 & 31;
            }

            // Process envelope
            if (ev1_enable && cycEnvelope == 0) {
                cycEnvelope = maxEnvelope;
                     if (!ev0_direction && envelope !=  0) envelope--;
                else if ( ev0_direction && envelope != 15) envelope++;
                else if ( ev1_repeat) envelope = ev0_value;
            }

            // Process frequency modification
            if (swp_enable && swp_interval != 0 && cycFreqMod == 0) {
                cycFreqMod = maxFreqMod;
                setFrequency(freq_new);
                if (swp_function == 1 && (modpos < 31 || swp_repeat))
                    modpos = modpos + 1 & 31;
            }

        }

    }

    // Read a value
    int read(int address) {
        switch (address) {
            case 0: return readSxINT();
            case 1: return readSxLRV();
            case 2: return readSxFQL();
            case 3: return readSxFQH();
            case 4: return readSxEV0();
            case 5: return readSxEV1();
            case 6: return readSxRAM();
            case 7: return readS5SWP();
        }
        return 0;
    }

    // Produce a pair of stereo output samples for the current state
    void render(int[] samples) {
        int sample;

        // Channel is not active or will not produce output
        if (!int_enable || envelope == 0 || lrv_left + lrv_right == 0)
            return;

        // Sample from noise generator
        if (type == NOISE)
            sample = bit ? parent.filter == VUE.HARDWARE ? 63 : 32767 : 0;

        // Sample from invalid PCM wave
        else if (ram_wave > 4)
            sample = 0;

        // Sample from PCM wave memory
        else {

            // Hardware sample
            sample = parent.waves[ram_wave][position];

            // High-precision sample
            if (parent.filter != VUE.HARDWARE)
                sample = (sample * 65534 + 63) / 126;

            // Linear interpolated sample
            if (parent.filter == VUE.LINEAR) {
                int next =
                    (parent.waves[ram_wave][position+1&31] * 65534 + 63) / 126;
                sample = ((next * maxSample + cycSample *
                    (sample - next) << 1) + maxSample) / (maxSample << 1);
            }

        }

        // No processing for sample value zero
        if (sample == 0)
            return;

        // Process both stereo streams
        for (int x = 0; x < 2; x++) {
            int level = x == 0 ? lrv_left : lrv_right;

            // Processing for hardware mixing
            if (parent.filter == VUE.HARDWARE) {
                int amp = level * envelope >> 3;
                if (level != 0 && envelope != 0)
                    amp++;
                samples[x] += sample * amp;
            }

            // Processing for high-precision mixing
            else samples[x] += ((sample * level * envelope << 1) + 225) / 450;

        }

    }

    // Initialize channel for system reset
    void reset() {

        // Configure instance fields
        bit       = false;
        envelope  = 0;
        frequency = 0;
        lfsr      = 0x7FFF;
        modpos    = 0;
        position  = 0;

        // Configure I/O registers
        ev0_direction = false;
        ev0_interval  = 0;
        ev0_value     = 0;
        ev1_enable    = false;
        ev1_repeat    = false;
        freq_write    = 0;
        int_auto      = false;
        int_interval  = 0;
        int_enable    = false;
        lrv_left      = 0;
        lrv_right     = 0;
        rng_tap       = 0;
        ram_wave      = 0;
        swp_clock     = 0;
        swp_direction = false;
        swp_enable    = false;
        swp_function  = 0;
        swp_interval  = 0;
        swp_repeat    = false;
        swp_shift     = 0;

        // Configure timing fields
        cycEnvelope = 0;
        cycFreqMod  = 0;
        cycLength   = 0;
        cycSample   = 0;
        maxEnvelope = 307220;
        maxFreqMod  = 0;
        maxSample   = 8188;
    }

    // Write a value
    void write(int address, int value) {
        switch (address) {
            case 0: writeSxINT(value); break;
            case 1: writeSxLRV(value); break;
            case 2: writeSxFQL(value); break;
            case 3: writeSxFQH(value); break;
            case 4: writeSxEV0(value); break;
            case 5: writeSxEV1(value); break;
            case 6: writeSxRAM(value); break;
            case 7: writeS5SWP(value); break;
        }
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Simulate a read of S5SWP
    private int readS5SWP() {
        return type != FREQMOD ? 0 :
            swp_clock    << 7          |
            swp_interval << 4          |
            (swp_direction ? 0x08 : 0) |
            swp_shift
        ;
    }

    // Simulate a read of SxEV0
    private int readSxEV0() {
        return
            ev0_value << 4             |
            (ev0_direction ? 0x08 : 0) |
            ev0_interval
        ;
    }

    // Simulate a read of SxEV1
    private int readSxEV1() {
        return
            (ev1_enable ? 0x02 : 0) |
            (ev1_repeat ? 0x01 : 0) |

            (type == FREQMOD ?
                (swp_enable ? 0x40 : 0) |
                (swp_repeat ? 0x20 : 0) |
                swp_function << 4
            : 0) |

            (type == NOISE ? rng_tap << 4 : 0)
        ;
    }

    // Simulate a read of SxFQH
    private int readSxFQH() {
        return frequency >> 8;
    }

    // Simulate a read of SxFQL
    private int readSxFQL() {
        return frequency & 0xFF;
    }

    // Simulate a read of SxINT
    private int readSxINT() {
        return
            (int_enable ? 0x80 : 0) |
            (int_auto   ? 0x20 : 0) |
            int_interval
        ;
    }

    // Simulate a read of SxLRV
    private int readSxLRV() {
        return lrv_left << 4 | lrv_right;
    }

    // Simulate a read of SxRAM
    private int readSxRAM() {
        return type == NOISE ? 0 : ram_wave;
    }

    // Specify a new current frequency value
    private void setFrequency(int frequency) {
        this.frequency = frequency;
        maxSample      = (type == NOISE ? 40 : 4) * (2048 - frequency);
    }

    // Simulate a write to S5SWP
    private void writeS5SWP(int value) {
        if (type != FREQMOD)
            return;
        swp_clock     = value >> 7 & 1;
        swp_interval  = value >> 4 & 7;
        swp_direction = (value & 0x08) != 0;
        swp_shift     = value      & 7;
        maxFreqMod    = (swp_clock == 0 ? 19201 : 153610) * swp_interval;
        cycFreqMod    = maxFreqMod;
    }

    // Simulate a write to SxEV0
    private void writeSxEV0(int value) {
        ev0_value     = value >> 4 & 15;
        ev0_direction = (value & 0x08) != 0;
        ev0_interval  = value      &  7;
        envelope      = ev0_value;
        maxEnvelope   = (ev0_interval + 1) * 307220;
        cycEnvelope   = maxEnvelope;
    }

    // Simulate a write to SxEV1
    private void writeSxEV1(int value) {
        ev1_repeat = (value & 0x02) != 0;
        ev1_enable = (value & 0x01) != 0;

        if (type == FREQMOD) {
            swp_enable   = (value & 0x40) != 0;
            swp_repeat   = (value & 0x20) != 0;
            swp_function = value >> 4 & 1;
        }

        if (type != NOISE)
            return;
        rng_tap = value >> 4 & 7;
    }

    // Simulate a write to SxFQH
    private void writeSxFQH(int value) {
        value      = (value & 0x07) << 8;
        frequency  = frequency  & 0x00FF | value;
        freq_write = freq_write & 0x00FF | value;
        setFrequency(frequency);
    }

    // Simulate a write to SxFQL
    private void writeSxFQL(int value) {
        frequency  = frequency  & 0x0700 | value;
        freq_write = freq_write & 0x0700 | value;
        setFrequency(frequency);
    }

    // Simulate a write to SxINT
    private void writeSxINT(int value) {
        int_enable   = (value & 0x80) != 0;
        int_auto     = (value & 0x20) != 0;
        int_interval = value & 0x1F;
        cycEnvelope  = maxEnvelope;
        cycFreqMod   = maxFreqMod;
        cycLength    = (int_interval + 1) * 76805;
        cycSample    = maxSample;
        lfsr         = 0x7FFF;
        modpos       = 0;
        position     = 0;
    }

    // Simulate a write to SxLRV
    private void writeSxLRV(int value) {
        lrv_left  = value >> 4 & 15;
        lrv_right = value      & 15;
    }

    // Simulate a write to SxRAM
    private void writeSxRAM(int value) {
        if (type != NOISE)
            ram_wave = value & 7;
    }

}
