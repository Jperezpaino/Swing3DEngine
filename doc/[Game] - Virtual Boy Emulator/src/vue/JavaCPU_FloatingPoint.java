package vue;
import vue.*;

// Floating-point instructions
class JavaCPU_FloatingPoint {

    ///////////////////////////////////////////////////////////////////////////
    //                            Common Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Perform a float-to-word conversion, optionally rounding
    private static void convert(JavaCPU cpu, Instruction inst, boolean round) {
        int bits = cpu.registers[inst.operands[1]];

        // Check for a reserved operand
        if (reserved(bits)) {
            cpu.exception = 0xFF60;
            cpu.psw_fro   = 1;
            return;
        }

        // Produce the whole number version of the float
        float value = Float.intBitsToFloat(bits);
        if (round)
            value += value >= 0 ? 0.5f : -0.5f;
        value -= value % 1;

        // Check for invalid operation
        if (value > 2147483647.0f || value < -2147483648.0f) {
            cpu.exception = 0xFF70;
            cpu.psw_fiv   = 1;
            return;
        }

        // Perform operation and update CPU state
        int result = (int) value;
        cpu.registers[inst.operands[0]] = result;
        if ((float) result != value)
            cpu.psw_fpr = 1;
        cpu.psw_ov  = 0;
        cpu.psw_s   = result >>> 31;
        cpu.psw_z   = result == 0 ? 1 : 0;
        cpu.pc     += 4;
        cpu.cycles += 14;
    }

    // Assign the appropriate status flags for the result of an operation
    private static int flags(JavaCPU cpu, boolean fud, float result) {
        int bits = Float.floatToIntBits(result);

        // Check for and correct underflow
        if ((bits & 0x7F800000) == 0 && (bits & 0x007FFFFF) != 0) {
            bits = 0;
            if (fud)
                cpu.psw_fud = 1;
        }

        // Prevent negative zero
        if (bits == 0x80000000)
            bits = 0;

        // Common state management
        cpu.psw_cy  = bits >>> 31;
        cpu.psw_ov  = 0;
        cpu.psw_s   = cpu.psw_cy;
        cpu.psw_z   = bits == 0 ? 1 : 0;
        cpu.pc     += 4;
        return bits;
    }

    // Check whether a value is a reserved operand
    private static boolean reserved(int bits) {
        int exp = bits >> 23 & 0xFF;
        return exp == 0xFF || exp == 0 && (bits & 0x007FFFFF) != 0;
    }

    // Check whether either of two operands is reserved
    private static boolean reserved(JavaCPU cpu, int left, int right) {

        // Neither operand is reserved
        if (!(reserved(left) || reserved(right)))
            return false;

        // Raise a reserved operand exception
        cpu.exception = 0xFF60;
        cpu.psw_fro   = 1;
        return true;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                             Instructions                              //
    ///////////////////////////////////////////////////////////////////////////

    // Add Floating Short
    static void ADDF_S(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]];

        // Check for reserved operands
        if (reserved(cpu, left, right))
            return;

        // Perform operation and update CPU state
        cpu.registers[inst.operands[0]] = flags(cpu, true,
            Float.intBitsToFloat(left) + Float.intBitsToFloat(right));
        cpu.cycles += 28;
    }

    // Compare Floating Short
    static void CMPF_S(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]];

        // Check for reserved operands
        if (reserved(cpu, left, right))
            return;

        // Perform operation and update CPU state
        flags(cpu, false,
            Float.intBitsToFloat(left) - Float.intBitsToFloat(right));
        cpu.cycles += 10;
    }

    // Convert Short Floating to Word Integer
    static void CVT_SW(JavaCPU cpu, Instruction inst) {
        convert(cpu, inst, true);
    }

    // Convert Word Integer to Short Floating
    static void CVT_WS(JavaCPU cpu, Instruction inst) {
        int   bits   = cpu.registers[inst.operands[1]];
        float result = (float) bits;

        // Update CPU state
        cpu.registers[inst.operands[0]] = flags(cpu, false, result);
        if ((int) result != bits)
            cpu.psw_fpr = 1;
        cpu.cycles += 16;
    }

    // Divide Floating Short
    static void DIVF_S(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]];

        // Check for reserved operands
        if (reserved(left) || reserved(right)) {
            cpu.exception = 0xFF60;
            cpu.psw_fro   = 1;
            return;
        }

        // Check for zero division
        if (right == 0) {
            if (left == 0) {
                cpu.exception = 0xFF70;
                cpu.psw_fiv   = 1;
            } else {
                cpu.exception = 0xFF68;
                cpu.psw_fzd   = 1;
            }
            return;
        }

        // Perform operation and update CPU state
        cpu.registers[inst.operands[0]] = flags(cpu, true,
            Float.intBitsToFloat(left) / Float.intBitsToFloat(right));
        cpu.cycles += 44;
    }

    // Multiply Floating Short
    static void MULF_S(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]];

        // Check for reserved operands
        if (reserved(left) || reserved(right)) {
            cpu.exception = 0xFF60;
            cpu.psw_fro   = 1;
            return;
        }

        // Perform operation and update CPU state
        cpu.registers[inst.operands[0]] = flags(cpu, true,
            Float.intBitsToFloat(left) * Float.intBitsToFloat(right));
        cpu.cycles += 30;
    }

    // Subtract Floating Short
    static void SUBF_S(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]];

        // Check for reserved operands
        if (reserved(left) || reserved(right)) {
            cpu.exception = 0xFF60;
            cpu.psw_fro   = 1;
            return;
        }

        // Perform operation and update CPU state
        cpu.registers[inst.operands[0]] = flags(cpu, true,
            Float.intBitsToFloat(left) - Float.intBitsToFloat(right));
        cpu.cycles += 28;
    }

    // Truncate Short Floating to Word Integer
    static void TRNC_SW(JavaCPU cpu, Instruction inst) {
        convert(cpu, inst, false);
    }

}
