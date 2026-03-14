package vue;
import vue.*;

// Bitwise instructions
class JavaCPU_Bitwise {

    ///////////////////////////////////////////////////////////////////////////
    //                            Common Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Update CPU state
    private static void update(JavaCPU cpu, int size, int reg2, int result) {
        cpu.registers[reg2] = result;
        cpu.psw_ov  = 0;
        cpu.psw_s   = result >>> 31;
        cpu.psw_z   = result == 0 ? 1 : 0;
        cpu.cycles += 1;
        cpu.pc     += size;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                             Instructions                              //
    ///////////////////////////////////////////////////////////////////////////

    // And
    static void AND(JavaCPU cpu, Instruction inst) {
        update(cpu, 2, inst.operands[0],
            cpu.registers[inst.operands[0]] & cpu.registers[inst.operands[1]]);
    }

    // And Immediate
    static void ANDI(JavaCPU cpu, Instruction inst) {
        update(cpu, 4, inst.operands[0],
            cpu.registers[inst.operands[1]] & inst.operands[2]);
    }

    // Not
    static void NOT(JavaCPU cpu, Instruction inst) {
        update(cpu, 2, inst.operands[0],
            ~cpu.registers[inst.operands[1]]);
    }

    // Or
    static void OR(JavaCPU cpu, Instruction inst) {
        update(cpu, 2, inst.operands[0],
            cpu.registers[inst.operands[0]] | cpu.registers[inst.operands[1]]);
    }

    // Or Immediate
    static void ORI(JavaCPU cpu, Instruction inst) {
        update(cpu, 4, inst.operands[0],
            cpu.registers[inst.operands[1]] | inst.operands[2]);
    }

    // Shift Arithmetic Right by Immediate
    static void SAR_IMM(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = inst.operands[1];
        cpu.psw_cy = right == 0 ? 0 : left >> right - 1 & 1;
        update(cpu, 2, inst.operands[0], left >> right);
    }

    // Shift Arithmetic Right by Register
    static void SAR_REG(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]] & 0x3F;
        cpu.psw_cy = right == 0 ? 0 : left >> right - 1 & 1;
        update(cpu, 2, inst.operands[0], left >> right);
    }

    // Shift Logical Left by Immediate
    static void SHL_IMM(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = inst.operands[1];
        cpu.psw_cy = right == 0 ? 0 : left >> 32 - right & 1;
        update(cpu, 2, inst.operands[0], left << right);
    }

    // Shift Logical Left by Register
    static void SHL_REG(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]] & 0x3F;
        cpu.psw_cy = right == 0 ? 0 : left >> 32 - right & 1;
        update(cpu, 2, inst.operands[0], left << right);
    }

    // Shift Logical Right by Immediate
    static void SHR_IMM(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = inst.operands[1];
        cpu.psw_cy = right == 0 ? 0 : left >> right - 1 & 1;
        update(cpu, 2, inst.operands[0], left >>> right);
    }

    // Shift Logical Right by Register
    static void SHR_REG(JavaCPU cpu, Instruction inst) {
        int left  = cpu.registers[inst.operands[0]];
        int right = cpu.registers[inst.operands[1]] & 0x3F;
        cpu.psw_cy = right == 0 ? 0 : left >> right - 1 & 1;
        update(cpu, 2, inst.operands[0], left >>> right);
    }

    // Exclusive Or
    static void XOR(JavaCPU cpu, Instruction inst) {
        update(cpu, 2, inst.operands[0],
            cpu.registers[inst.operands[0]] ^ cpu.registers[inst.operands[1]]);
    }

    // Exclusive Or Immediate
    static void XORI(JavaCPU cpu, Instruction inst) {
        update(cpu, 4, inst.operands[0],
            cpu.registers[inst.operands[1]] ^ inst.operands[2]);
    }

}
