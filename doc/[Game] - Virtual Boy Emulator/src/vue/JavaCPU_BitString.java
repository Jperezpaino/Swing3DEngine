package vue;
import vue.*;

// Bit string instructions
class JavaCPU_BitString {


    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Arithmetic operations
    private static final int AND =  0;
    private static final int MOV = -1;
    private static final int OR  =  1;
    private static final int XOR =  2;



    ///////////////////////////////////////////////////////////////////////////
    //                            Common Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Arithmetic operation
    private static void arithmetic(JavaCPU cpu, int operation, boolean not) {
        long buffer; // Buffered source bit string bits
        int  dest;   // Destination bit string bits
        int  length; // Bits to process this invocation
        int  mask;   // Bits to write into destination
        int  src;    // Source bit string bits

        // Initialize CPU state
        cpu.registers[30] &= 0xFFFFFFFC;
        cpu.registers[29] &= 0xFFFFFFFC;
        cpu.registers[27] &= 0x0000001F;
        cpu.registers[26] &= 0x0000001F;

        // Determine the number of destination bits to process
        length = 32 - cpu.registers[26];
        if (Integer.compareUnsigned(length, cpu.registers[28]) > 0)
            length = cpu.registers[28];
        mask = length == 32 ? 0xFFFFFFFF :
            (1 << length) - 1 << cpu.registers[26];

        // Nothing to process
        if (length == 0) {
            cpu.pc     +=  2;
            cpu.cycles += 20;
            return;
        }

        // Retrieve the source bits, optionally buffered on the CPU
        if (!cpu.bsBuffered) {
            buffer = fillBuffer(cpu, 0, 0, 0);
            if (cpu.parent.brkCode != 0)
                return;
        } else buffer = cpu.bsBuffer;

        // Read the destination bits
        dest = cpu.read(cpu.registers[29], VUE.S32);
        if (cpu.parent.brkCode != 0)
            return;

        // Perform the operation
        src = (int) buffer << cpu.registers[26];
        if (not)
            src = ~src;
        switch (operation) {
            case AND: src &= dest; break;
            case OR : src |= dest; break;
            case XOR: src ^= dest; break;
        }
        dest = src & mask | dest & ~mask;

        // Read the next source bits before storing the destination bits
        buffer = fillBuffer(cpu, buffer, length, dest);
        if (cpu.parent.brkCode != 0)
            return;

        // Store the result into the destination
        cpu.write(cpu.registers[29], VUE.S32, dest);
        if (cpu.parent.brkCode != 0)
            return;

        // Update CPU state
        cpu.registers[30] += (cpu.registers[27] += length) >= 32 ? 4 : 0;
        cpu.registers[29] += (cpu.registers[26] += length) >= 32 ? 4 : 0;
        cpu.registers[28] -= length;
        cpu.registers[27] &= 0x0000001F;
        cpu.registers[26] &= 0x0000001F;
        cpu.cycles        += 45;
        if (cpu.bsBuffered = cpu.registers[28] != 0)
            cpu.bsBuffer   = buffer;
        else cpu.pc       += 2;
    }

    // Fill a 64-bit buffer with source bit string bits
    private static long fillBuffer(JavaCPU cpu, long buffer, int advance,
        int feedback) {
        int address = cpu.registers[30] + (advance == 0 ? 0 : 8);
        int offset  = cpu.registers[27];
        int remain  = advance == 0 ? 64 : advance;

        // Read bits into the buffer until it is full
        buffer >>>= advance;
        while (remain > 0) {

            // Read the next word from the bus
            int bits = cpu.read(address, VUE.S32);
            if (cpu.parent.brkCode != 0)
                return 0;

            // Apply feedback bits if reading and writing the same word
            if (
                address == cpu.registers[30] + 8       &&
                address == cpu.registers[29]           &&
                cpu.registers[26] >= cpu.registers[27] &&
                advance != 0
            ) {
                int mask = cpu.registers[26] == 0 ? 0 :
                    (1 << cpu.registers[26]) - 1;
                bits = bits & mask | feedback & ~mask;
            }

            // Buffer management
            buffer  |= (bits & 0xFFFFFFFFL) >>> offset << 64 - remain;
            remain  -= 32 - offset;
            address += 4;
            offset   = 0;
        }

        return buffer;
    }

    // Search operation
    private static void search(JavaCPU cpu, int bit, int direction) {

        // Initialize CPU state
        cpu.registers[30] &= 0xFFFFFFFC;
        cpu.registers[27] &= 0x0000001F;
        cpu.psw_z          = 1;

        // Nothing to process
        if (cpu.registers[28] == 0) {
            cpu.pc     +=  2;
            cpu.cycles += 13;
            return;
        }

        // Retrieve the bits for the current word
        int bits = cpu.read(cpu.registers[30], VUE.S32);
        if (cpu.parent.brkCode != 0)
            return;

        // Search for the specified bit in the current word
        bits >>= cpu.registers[27];
        while (cpu.registers[28] != 0 && cpu.psw_z == 1) {
            cpu.psw_z          = bits & 1 ^ bit;
            cpu.registers[29] += cpu.psw_z;
            cpu.registers[28] -= 1;
            cpu.registers[27] += direction;
            bits             >>= 1;

            // End of current word
            if ((cpu.registers[27] & 32) != 0) {
                cpu.registers[27] &= 0x0000001F;
                cpu.registers[30] += direction << 2;
                break;
            }

        }

        // Update CPU state
        cpu.cycles += 40;
        if (cpu.psw_z == 0)
            cpu.pc +=  2;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                             Instructions                              //
    ///////////////////////////////////////////////////////////////////////////

    // And Bit String Upward
    static void ANDBSU(JavaCPU cpu) {
        arithmetic(cpu, AND, false);
    }

    // And Not Bit String Upward
    static void ANDNBSU(JavaCPU cpu) {
        arithmetic(cpu, AND, true);
    }

    // Move Bit String Upward
    static void MOVBSU(JavaCPU cpu) {
        arithmetic(cpu, MOV, false);
    }

    // Not Bit String Upward
    static void NOTBSU(JavaCPU cpu) {
        arithmetic(cpu, MOV, true);
    }

    // Or Bit String Upward
    static void ORBSU(JavaCPU cpu) {
        arithmetic(cpu, OR, false);
    }

    // Or Not Bit String Upward
    static void ORNBSU(JavaCPU cpu) {
        arithmetic(cpu, OR, true);
    }

    // Search Bit 0 Downward
    static void SCH0BSD(JavaCPU cpu) {
        search(cpu, 0, -1);
    }

    // Search Bit 0 Upward
    static void SCH0BSU(JavaCPU cpu) {
        search(cpu, 0, 1);
    }

    // Search Bit 1 Downward
    static void SCH1BSD(JavaCPU cpu) {
        search(cpu, 1, -1);
    }

    // Search Bit 1 Upward
    static void SCH1BSU(JavaCPU cpu) {
        search(cpu, 1, 1);
    }

    // Exclusive Or Bit String Upward
    static void XORBSU(JavaCPU cpu) {
        arithmetic(cpu, XOR, false);
    }

    // Exclusive Or Not Bit String Upward
    static void XORNBSU(JavaCPU cpu) {
        arithmetic(cpu, XOR, true);
    }

}
