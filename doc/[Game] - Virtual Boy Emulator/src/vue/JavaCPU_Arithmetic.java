package vue;
import vue.*;

// Arithmetic instructions
class JavaCPU_Arithmetic {

    ///////////////////////////////////////////////////////////////////////////
    //                            Common Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Addition
    private static int add(JavaCPU cpu, int left, int right) {
        int result = left + right;
        cpu.psw_cy = Integer.compareUnsigned(result, left) < 0 ? 1 : 0;
        cpu.psw_ov = (~(left ^ right) & (left ^ result)) >>> 31;
        cpu.psw_s  = result >>> 31;
        cpu.psw_z  = result == 0 ? 1 : 0;
        return result;
    }

    // Subtraction
    static int subtract(JavaCPU cpu, int left, int right) {
        int result = left - right;
        cpu.psw_cy = Integer.compareUnsigned(left, right) < 0 ? 1 : 0;
        cpu.psw_ov = ((left ^ right) & (left ^ result)) >>> 31;
        cpu.psw_s  = result >>> 31;
        cpu.psw_z  = result == 0 ? 1 : 0;
        return result;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                             Instructions                              //
    ///////////////////////////////////////////////////////////////////////////

    // Add Immediate
    static void ADD_IMM(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] = add(cpu,
            cpu.registers[inst.operands[0]],
            inst.operands[1]);
        cpu.cycles += 1;
        cpu.pc     += 2;
    }

    // Add Register
    static void ADD_REG(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] = add(cpu,
            cpu.registers[inst.operands[0]],
            cpu.registers[inst.operands[1]]);
        cpu.cycles += 1;
        cpu.pc     += 2;
    }

    // Add Immediate
    static void ADDI(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] = add(cpu,
            cpu.registers[inst.operands[1]],
            inst.operands[2]);
        cpu.cycles += 1;
        cpu.pc     += 4;
    }

    // Compare Immediate
    static void CMP_IMM(JavaCPU cpu, Instruction inst) {
        subtract(cpu, cpu.registers[inst.operands[0]], inst.operands[1]);
        cpu.cycles += 1;
        cpu.pc     += 2;
    }

    // Compare Register
    static void CMP_REG(JavaCPU cpu, Instruction inst) {
        subtract(cpu, cpu.registers[inst.operands[0]],
                 cpu.registers[inst.operands[1]]);
        cpu.cycles += 1;
        cpu.pc     += 2;
    }

    // Divide
    static void DIV(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]];
        int remainder;
        int result;

        // Division by zero
        if (right == 0) {
            cpu.exception = 0xFF80;
            return;
        }

        // Special-case processing
        if (left == 0x80000000 && right == 0xFFFFFFFF) {
            remainder  = 0;
            result     = left;
            cpu.psw_ov = 1;
        }

        // Regular processing
        else {
            remainder  = left % right;
            result     = left / right;
            cpu.psw_ov = 0;
        }

        // Update CPU state
        cpu.registers[30]               = remainder;
        cpu.registers[inst.operands[0]] = result;
        cpu.psw_s   = result >>> 31;
        cpu.psw_z   = result == 0 ? 1 : 0;
        cpu.cycles += 38;
        cpu.pc     += 2;
    }

    // Divide Unsigned
    static void DIVU(JavaCPU cpu, Instruction inst) {
        long left  = cpu.registers[inst.operands[0]] & 0xFFFFFFFFL;
        long right = cpu.registers[inst.operands[1]] & 0xFFFFFFFFL;

        // Division by zero
        if (right == 0) {
            cpu.exception = 0xFF80;
            return;
        }

        // Perform operation
        int result                      = (int) (left / right);
        cpu.registers[30]               = (int) (left % right);
        cpu.registers[inst.operands[0]] = result;

        // Update CPU state
        cpu.psw_ov  = 0;
        cpu.psw_s   = result >>> 31;
        cpu.psw_z   = result == 0 ? 1 : 0;
        cpu.cycles += 36;
        cpu.pc     += 2;
    }

    // Multiply
    static void MUL(JavaCPU cpu, Instruction inst) {
        long result   = (long) cpu.registers[inst.operands[0]] *
                        (long) cpu.registers[inst.operands[1]];
        int  result32 = (int) result;
        cpu.registers[30]               = (int) (result >> 32);
        cpu.registers[inst.operands[0]] = result32;

        // Update CPU state
        cpu.psw_ov  = result != result32 ? 1 : 0;
        cpu.psw_s   = result32 >>> 31;
        cpu.psw_z   = result32 == 0 ? 1 : 0;
        cpu.cycles += 13;
        cpu.pc     += 2;
    }

    // Multiply Unsigned
    static void MULU(JavaCPU cpu, Instruction inst) {
        long result   = (cpu.registers[inst.operands[0]] & 0xFFFFFFFFL) *
                        (cpu.registers[inst.operands[1]] & 0xFFFFFFFFL);
        int  result32 = (int) result;
        cpu.registers[30]               = (int) (result >> 32);
        cpu.registers[inst.operands[0]] = result32;

        // Update CPU state
        cpu.psw_ov  = result != (result32 & 0xFFFFFFFFL) ? 1 : 0;
        cpu.psw_s   = result32 >>> 31;
        cpu.psw_z   = result32 == 0 ? 1 : 0;
        cpu.cycles += 13;
        cpu.pc     += 2;
    }

    // Subtract
    static void SUB(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] = subtract(cpu,
            cpu.registers[inst.operands[0]],
            cpu.registers[inst.operands[1]]);
        cpu.cycles += 1;
        cpu.pc     += 2;
    }

}
