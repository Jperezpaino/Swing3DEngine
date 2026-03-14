package vue;
import vue.*;

// Nintendo instructions
class JavaCPU_Nintendo {

    ///////////////////////////////////////////////////////////////////////////
    //                             Instructions                              //
    ///////////////////////////////////////////////////////////////////////////

    // Clear Interrupt Disable Flag
    static void CLI(JavaCPU cpu, Instruction inst) {
        cpu.psw_id  = 0;
        cpu.cycles += 12;
        cpu.pc     += 2;
    }

    // Multiply Halfword
    static void MPYHW(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] *=
            cpu.registers[inst.operands[1]] << 15 >> 15;
        cpu.cycles += 9;
        cpu.pc     += 4;
    }

    // Reverse Bits in Word
    static void REV(JavaCPU cpu, Instruction inst) {
        int src    = cpu.registers[inst.operands[1]];
        int result = 0;
        for (int x = 0; x < 32; x++)
            result = result << 1 | src >> x & 1;
        cpu.registers[inst.operands[0]] = result;
        cpu.cycles += 22;
        cpu.pc     += 4;
    }

    // Set Interrupt Disable Flag
    static void SEI(JavaCPU cpu, Instruction inst) {
        cpu.psw_id  = 1;
        cpu.cycles += 12;
        cpu.pc     += 2;
    }

    // Exchange Byte
    static void XB(JavaCPU cpu, Instruction inst) {
        int src = cpu.registers[inst.operands[0]];
        cpu.registers[inst.operands[0]] =
            src & 0xFFFF0000 | src >> 8 & 0x00FF | src << 8 & 0xFF00;
        cpu.cycles += 6;
        cpu.pc     += 4;
    }

    // Exchange Halfword
    static void XH(JavaCPU cpu, Instruction inst) {
        int src = cpu.registers[inst.operands[0]];
        cpu.registers[inst.operands[0]] = src << 16 | src >>> 16;
        cpu.cycles += 1;
        cpu.pc     += 4;
    }

}
