package vue;
import vue.*;

// Jump and control instructions
class JavaCPU_Control {

    ///////////////////////////////////////////////////////////////////////////
    //                           Jump Instructions                           //
    ///////////////////////////////////////////////////////////////////////////

    // Bcond
    static void BCOND(JavaCPU cpu, Instruction inst) {

        // The branch was taken
        if (cpu.getCondition(inst.operands[0])) {
            cpu.cycles += 3;
            cpu.pc     += inst.operands[1] & 0xFFFFFFFE;
        }

        // The branch was not taken
        else {
            cpu.cycles += 1;
            cpu.pc     += 2;
        }

    }

    // Jump and Link
    static void JAL(JavaCPU cpu, Instruction inst) {
        cpu.registers[31] = cpu.pc + 4;
        cpu.cycles += 3;
        cpu.pc     += inst.operands[0] & 0xFFFFFFFE;
    }

    // Jump Register
    static void JMP(JavaCPU cpu, Instruction inst) {
        cpu.cycles += 3;
        cpu.pc      = cpu.registers[inst.operands[1]] & 0xFFFFFFFE;
    }

    // Jump Relative
    static void JR(JavaCPU cpu, Instruction inst) {
        cpu.cycles += 3;
        cpu.pc     += inst.operands[0] & 0xFFFFFFFE;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                         Control Instructions                          //
    ///////////////////////////////////////////////////////////////////////////

    // Halt
    static void HALT(JavaCPU cpu) {
        cpu.halt = JavaCPU.HALT;
    }

    // Load to System Register
    static void LDSR(JavaCPU cpu, Instruction inst) {
        cpu.setSystemRegister(inst.operands[1],
            cpu.registers[inst.operands[0]], false);
        cpu.cycles += 8;
        cpu.pc     += 2;
    }

    // Return from Trap or Interrupt
    static void RETI(JavaCPU cpu) {

        // Returning from duplexed exception
        if (cpu.psw_np != 0) {
            cpu.pc = cpu.fepc;
            cpu.setSystemRegister(VUE.PSW, cpu.fepsw, false);
        }

        // Returning from regular exception
        else {
            cpu.pc = cpu.eipc;
            cpu.setSystemRegister(VUE.PSW, cpu.eipsw, false);
        }

        // Common processing
        cpu.cycles += 10;
    }

    // Store Contents of System Register
    static void STSR(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] =
            cpu.getSystemRegister(inst.operands[1]);
        cpu.cycles += 8;
        cpu.pc     += 2;
    }

    // Trap
    static void TRAP(JavaCPU cpu, Instruction inst) {
        cpu.exception  = 0xFFA0 + inst.operands[1];
        cpu.cycles    += 15;
        cpu.pc        += 2;
    }

    
}
