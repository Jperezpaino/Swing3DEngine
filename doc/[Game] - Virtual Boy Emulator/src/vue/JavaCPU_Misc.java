package vue;
import vue.*;

// Miscellaneous instructions
class JavaCPU_Misc {

    ///////////////////////////////////////////////////////////////////////////
    //                             Instructions                              //
    ///////////////////////////////////////////////////////////////////////////

    // Compare and Exchange Interlocked
    static void CAXI(JavaCPU cpu, Instruction inst) {

        // Read the lock word from the bus
        int value = cpu.read(cpu.registers[inst.operands[1]] + 
            inst.operands[2], VUE.S32);
        if (cpu.parent.brkCode != 0)
            return;

        // Perform the comparison and determine the exchange value
        JavaCPU_Arithmetic.subtract(cpu,
            cpu.registers[inst.operands[0]], value);
        if (cpu.psw_z != 0)
            value = cpu.registers[30];

        // Write the exchange value to the bus
        cpu.write(cpu.registers[inst.operands[1]] + 
            inst.operands[2], VUE.S32, value);
        if (cpu.parent.brkCode != 0)
            return;

        // Update CPU state
        cpu.cycles += 26;
        cpu.pc     += 4;
    }

    // Set Flag Condition
    static void SETF(JavaCPU cpu, Instruction inst) {
        cpu.registers[inst.operands[0]] =
            cpu.getCondition(inst.operands[1]) ? 1 : 0;
        cpu.cycles += 1;
        cpu.pc     += 2;
    }

}
