package vue;
import vue.*;

// Memory and register transfer instructions
class JavaCPU_Memory {

    ///////////////////////////////////////////////////////////////////////////
    //                            Common Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Read a value from the bus
    private static void read(JavaCPU cpu, Instruction inst, int type) {
        int value = cpu.read(
            cpu.registers[inst.operands[1]] + inst.operands[2], type);
        if (cpu.parent.brkCode != 0)
            return;
        cpu.registers[inst.operands[0]] = value;
        cpu.cycles += 4;
        cpu.pc     += 4;
    }

    // Write a value to the bus
    private static void write(JavaCPU cpu, Instruction inst, int type) {
        cpu.write(cpu.registers[inst.operands[1]] + inst.operands[2],
            type, cpu.registers[inst.operands[0]]);
        if (cpu.parent.brkCode != 0)
            return;
        cpu.cycles += 1;
        cpu.pc     += 4;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                           Register Transfer                           //
    ///////////////////////////////////////////////////////////////////////////

    // Move Immediate
    static void MOV_IMM(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] = inst.operands[1];
        cpu.cycles += 1;
        cpu.pc     += 2;
    }

    // Move Register
    static void MOV_REG(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] = cpu.registers[inst.operands[1]];
        cpu.cycles += 1;
        cpu.pc     += 2;
    }

    // Add
    static void MOVEA(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] =
            cpu.registers[inst.operands[1]] + inst.operands[2];
        cpu.cycles += 1;
        cpu.pc     += 4;
    }

    // Add
    static void MOVHI(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] =
            cpu.registers[inst.operands[1]] + (inst.operands[2] << 16);
        cpu.cycles += 1;
        cpu.pc     += 4;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Load and Input                             //
    ///////////////////////////////////////////////////////////////////////////

    // Input Byte from Port
    static void IN_B(JavaCPU cpu, Instruction inst) {
        read(cpu, inst, VUE.U8);
    }

    // Input Halfword from Port
    static void IN_H(JavaCPU cpu, Instruction inst) {
        read(cpu, inst, VUE.U16);
    }

    // Input Word from Port
    static void IN_W(JavaCPU cpu, Instruction inst) {
        read(cpu, inst, VUE.S32);
    }

    // Load Byte
    static void LD_B(JavaCPU cpu, Instruction inst) {
        read(cpu, inst, VUE.S8);
    }

    // Load Halfword
    static void LD_H(JavaCPU cpu, Instruction inst) {
        read(cpu, inst, VUE.S16);
    }

    // Load Word
    static void LD_W(JavaCPU cpu, Instruction inst) {
        read(cpu, inst, VUE.S32);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                           Store and Output                            //
    ///////////////////////////////////////////////////////////////////////////

    // Output Byte to Port
    static void OUT_B(JavaCPU cpu, Instruction inst) {
        write(cpu, inst, VUE.U8);
    }

    // Output Halfword to Port
    static void OUT_H(JavaCPU cpu, Instruction inst) {
        write(cpu, inst, VUE.U16);
    }

    // Output Word to Port
    static void OUT_W(JavaCPU cpu, Instruction inst) {
        write(cpu, inst, VUE.S32);
    }

    // Store Byte
    static void ST_B(JavaCPU cpu, Instruction inst) {
        write(cpu, inst, VUE.U8);
    }

    // Store Halfword
    static void ST_H(JavaCPU cpu, Instruction inst) {
        write(cpu, inst, VUE.U16);
    }

    // Store Word
    static void ST_W(JavaCPU cpu, Instruction inst) {
        write(cpu, inst, VUE.S32);
    }

}
