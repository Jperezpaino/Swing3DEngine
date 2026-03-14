package vue;
import vue.*;

// Instruction implementations
import static vue.JavaCPU_Arithmetic.*;
import static vue.JavaCPU_BitString.*;
import static vue.JavaCPU_Bitwise.*;
import static vue.JavaCPU_Control.*;
import static vue.JavaCPU_FloatingPoint.*;
import static vue.JavaCPU_Memory.*;
import static vue.JavaCPU_Misc.*;
import static vue.JavaCPU_Nintendo.*;

// CPU state and behavior
class JavaCPU {

    // Instance fields
    Instruction inst;   // Current instruction data
    JavaVUE     parent; // Parent emulation context

    // Control state
    long      bsBuffer;   // Buffered source bit string bits
    boolean   bsBuffered; // Bit string input is currently buffered
    int       cycles;     // Cycles taken by current instruction
    int       exception;  // Current exception code
    int       halt;       // Current halt status
    boolean[] irq;        // Interrupt request status
    int       lastPC;     // Previous value of PC
    int       stage;      // Current virtual pipeline stage

    // Register set
    int[] registers; // Program registers
    int   pc;        // Program counter
    int   eipc;      // Exception/interrupt restore PC
    int   eipsw;     // Exception/interrupt restore PSW
    int   fepc;      // Duplexed exception restore PC
    int   fepsw;     // Duplexed exception restore PSW
    int   chcw;      // Cache control word
    int   adtre;     // Addredd trap register for execution
    int   sr29;      // System register 29
    int   sr31;      // System register 31

    // ECR
    int ecr_eicc; // Exception/interrupt cause code
    int ecr_fecc; // Duplexed exception cause code

    // PSW
    int psw_ae;  // Address trap enable
    int psw_cy;  // Carry
    int psw_ep;  // Exception/interrupt pending
    int psw_fiv; // Floating-point invalid operation
    int psw_fov; // Floating-point overflow
    int psw_fpr; // Floating-point precision degradation
    int psw_fro; // Floating-point reserved operand
    int psw_fud; // Floating-point underflow
    int psw_fzd; // Floating-point zero division
    int psw_i;   // Interrupt masking level
    int psw_id;  // Interrupt disable
    int psw_np;  // NMI/duplexed exception pending
    int psw_ov;  // Overflow
    int psw_s;   // Sign
    int psw_z;   // Zero



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Pipeline stages
    static final int FETCH16   = 0;
    static final int FETCH32   = 1;
    static final int EXECUTE   = 2;
    static final int INTERRUPT = 3;
    static final int EXCEPTION = 4;

    // Halt status
    static final int NONE  = 0;
    static final int FATAL = 1;
    static final int HALT  = 2;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    JavaCPU(JavaVUE parent) {
        inst        = new Instruction();
        irq         = new boolean[5];
        this.parent = parent;
        registers   = new int[32];
    }



    ///////////////////////////////////////////////////////////////////////////
    //                         Instruction Handlers                          //
    ///////////////////////////////////////////////////////////////////////////

    // Invalid opcode
    static void ILLEGAL(JavaCPU cpu) {
        cpu.exception = 0xFF90;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                           Pipeline Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Exception processing
    int exception() {
        boolean isIRQ = (exception & 0xFF00) == 0xFE00;
        int     level = exception >> 4 & 0xF;

        // There is no exception to process
        if (exception == 0) {
            stage = FETCH16;
            return 0;
        }

        // Interrupt masking conditions
        if (
            halt == FATAL || // No processing on fatal halt
            isIRQ && (irqMasked() || psw_i > level) // Regular masking
        ) return 0;

        // Check for breakpoints
        if (parent.checkBreakpoints(Breakpoint.EXCEPTION,
            isIRQ ? null : inst))
            return 0;

        // Fatal exception
        if (psw_np != 0) {
            // 0xFFFFcode -> 0x00000000
            // PSW        -> 0x00000004
            // PC         -> 0x00000008
            halt = FATAL;
            return 1; // Need actual cycle count
        }

        // Duplexed exception
        if (psw_ep != 0) {
            ecr_fecc = exception;
            fepsw    = getSystemRegister(VUE.PSW);
            fepc     = pc;
            psw_np   = 1;
            pc       = 0xFFFFFFD0;
        }

        // Regular exception, interrupt or trap
        else {
            ecr_eicc = exception;
            eipsw    = getSystemRegister(VUE.PSW);
            eipc     = pc;
            psw_ep   = 1;
            pc       = 0xFFFF0000 | 0xFFF0 &
                (exception == 0xFF70 ? 0xFF60 : exception);
        }

        // Processing exclusive to interrupts
        if (isIRQ) {
            psw_i   = level + 1;
            eipc   += halt;
            halt    = NONE;
        }

        // Common processing
        psw_id = 1;
        psw_ae = 0;

        // Update control state
        bsBuffered = false;
        exception  = 0;
        stage      = FETCH16;
        return 1; // Need actual cycle count
    }

    // Execute the current instruction
    void execute() {

        // Check for breakpoints
        if (parent.checkBreakpoints(Breakpoint.EXECUTE, inst))
            return;

        // Record the previous PC value
        lastPC = pc;

        // Process by instruction ID
        switch (inst.id) {
            case VUE.ILLEGAL: ILLEGAL(this      ); break;
            case VUE.ADDF_S : ADDF_S (this, inst); break;
            case VUE.ADD_IMM: ADD_IMM(this, inst); break;
            case VUE.ADD_REG: ADD_REG(this, inst); break;
            case VUE.ADDI   : ADDI   (this, inst); break;
            case VUE.AND    : AND    (this, inst); break;
            case VUE.ANDBSU : ANDBSU (this      ); break;
            case VUE.ANDI   : ANDI   (this, inst); break;
            case VUE.ANDNBSU: ANDNBSU(this      ); break;
            case VUE.BCOND  : BCOND  (this, inst); break;
            case VUE.CAXI   : CAXI   (this, inst); break;
            case VUE.CLI    : CLI    (this, inst); break;
            case VUE.CMPF_S : CMPF_S (this, inst); break;
            case VUE.CMP_IMM: CMP_IMM(this, inst); break;
            case VUE.CMP_REG: CMP_REG(this, inst); break;
            case VUE.CVT_SW : CVT_SW (this, inst); break;
            case VUE.CVT_WS : CVT_WS (this, inst); break;
            case VUE.DIV    : DIV    (this, inst); break;
            case VUE.DIVF_S : DIVF_S (this, inst); break;
            case VUE.DIVU   : DIVU   (this, inst); break;
            case VUE.HALT   : HALT   (this      ); break;
            case VUE.IN_B   : IN_B   (this, inst); break;
            case VUE.IN_H   : IN_H   (this, inst); break;
            case VUE.IN_W   : IN_W   (this, inst); break;
            case VUE.JAL    : JAL    (this, inst); break;
            case VUE.JMP    : JMP    (this, inst); break;
            case VUE.JR     : JR     (this, inst); break;
            case VUE.LD_B   : LD_B   (this, inst); break;
            case VUE.LD_H   : LD_H   (this, inst); break;
            case VUE.LD_W   : LD_W   (this, inst); break;
            case VUE.LDSR   : LDSR   (this, inst); break;
            case VUE.MOV_IMM: MOV_IMM(this, inst); break;
            case VUE.MOV_REG: MOV_REG(this, inst); break;
            case VUE.MOVBSU : MOVBSU (this      ); break;
            case VUE.MOVEA  : MOVEA  (this, inst); break;
            case VUE.MOVHI  : MOVHI  (this, inst); break;
            case VUE.MPYHW  : MPYHW  (this, inst); break;
            case VUE.MUL    : MUL    (this, inst); break;
            case VUE.MULF_S : MULF_S (this, inst); break;
            case VUE.MULU   : MULU   (this, inst); break;
            case VUE.NOT    : NOT    (this, inst); break;
            case VUE.NOTBSU : NOTBSU (this      ); break;
            case VUE.OR     : OR     (this, inst); break;
            case VUE.ORBSU  : ORBSU  (this      ); break;
            case VUE.ORI    : ORI    (this, inst); break;
            case VUE.ORNBSU : ORNBSU (this      ); break;
            case VUE.OUT_B  : OUT_B  (this, inst); break;
            case VUE.OUT_H  : OUT_H  (this, inst); break;
            case VUE.OUT_W  : OUT_W  (this, inst); break;
            case VUE.RETI   : RETI   (this      ); break;
            case VUE.REV    : REV    (this, inst); break;
            case VUE.SAR_IMM: SAR_IMM(this, inst); break;
            case VUE.SAR_REG: SAR_REG(this, inst); break;
            case VUE.SCH0BSD: SCH0BSD(this      ); break;
            case VUE.SCH0BSU: SCH0BSU(this      ); break;
            case VUE.SCH1BSD: SCH1BSD(this      ); break;
            case VUE.SCH1BSU: SCH1BSU(this      ); break;
            case VUE.SEI    : SEI    (this, inst); break;
            case VUE.SETF   : SETF   (this, inst); break;
            case VUE.SHL_IMM: SHL_IMM(this, inst); break;
            case VUE.SHL_REG: SHL_REG(this, inst); break;
            case VUE.SHR_IMM: SHR_IMM(this, inst); break;
            case VUE.SHR_REG: SHR_REG(this, inst); break;
            case VUE.ST_B   : ST_B   (this, inst); break;
            case VUE.ST_H   : ST_H   (this, inst); break;
            case VUE.ST_W   : ST_W   (this, inst); break;
            case VUE.STSR   : STSR   (this, inst); break;
            case VUE.SUB    : SUB    (this, inst); break;
            case VUE.SUBF_S : SUBF_S (this, inst); break;
            case VUE.TRAP   : TRAP   (this, inst); break;
            case VUE.TRNC_SW: TRNC_SW(this, inst); break;
            case VUE.XB     : XB     (this, inst); break;
            case VUE.XH     : XH     (this, inst); break;
            case VUE.XOR    : XOR    (this, inst); break;
            case VUE.XORBSU : XORBSU (this      ); break;
            case VUE.XORI   : XORI   (this, inst); break;
            case VUE.XORNBSU: XORNBSU(this      ); break;
            default: throw new RuntimeException(
                "Unsupported instruction: " + inst.id);
        }

        // Prevent instructions from changing r0
        registers[0] = 0;

        // An application break occurred
        if (parent.brkCode != 0)
            return;

        // Update control state
        stage = exception == 0 ? INTERRUPT : EXCEPTION;
    }

    // Fetch the first 16 bits of an instruction
    void fetch16() {

        // Check for address trap
        if (psw_ae != 0 && pc == adtre) {
            // cycles += ???
            exception = 0xFFC0;
            stage     = EXCEPTION;
            return;
        }

        // Retrieve the bits from the bus
        int value = read(pc, VUE.U16);
        if (parent.brkCode != 0)
            return;

        // The instruction is 32 bits
        inst.bits[0] = value;
        if (Instruction.getSize(value) == 4) {
            stage = FETCH32;
            return;
        }

        // Decode the instruction
        inst.decode();
        stage = EXECUTE;
    }

    // Fetch the second 16 bits of an instruction
    void fetch32() {

        // Retrieve the bits from the bus
        int value = read(pc + 2, VUE.U16);
        if (parent.brkCode != 0)
            return;

        // Decode the instruction
        inst.bits[1] = value;
        inst.decode();
        stage = EXECUTE;
    }

    // Check for interrupts
    void interrupt() {
        stage = EXCEPTION;

        // Interrupts are masked
        if (irqMasked()) return;

        // Check interrupt request lines
        for (int level = 4; level >= psw_i; level--) {
            if (!irq[level]) continue;
            exception = 0xFE00 | level << 4;
            return;
        }

    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Check for a condition state
    boolean getCondition(int id) {
        switch (id) {
            case  0: return  psw_ov                  == 1; // V
            case  1: return  psw_cy                  == 1; // C, L
            case  2: return  psw_z                   == 1; // E, Z
            case  3: return (psw_cy | psw_z)         == 1; // NH
            case  4: return  psw_s                   == 1; // N
            case  5: return true;                            // T
            case  6: return (psw_ov ^ psw_s)         == 1; // LT
            case  7: return (psw_ov ^ psw_s | psw_z) == 1; // LE
            case  8: return  psw_ov                  == 0; // NV
            case  9: return  psw_cy                  == 0; // NC, NL
            case 10: return  psw_z                   == 0; // NE, NZ
            case 11: return (psw_cy | psw_z)         == 0; // H
            case 12: return  psw_s                   == 0; // P
            case 13: return false;                           // F
            case 14: return (psw_ov ^ psw_s)         == 0; // GE
            case 15: return (psw_ov ^ psw_s | psw_z) == 0; // GT
        }
        return false; // Invalid id
    }

    // Retrieve the binary value of a system register
    int getSystemRegister(int id) {

        // Process value by ID
        switch (id) {
            case  0: return eipc;
            case  1: return eipsw;
            case  2: return fepc;
            case  3: return fepsw;
            case  4: return ecr_fecc << 16 | ecr_eicc;
            case  6: return 0x00005346; // PIR
            case  7: return 0x000000E0; // TKCW
            case 24: return chcw;
            case 25: return adtre;
            case 29: return sr29;
            case 30: return 0x00000004; // System register 30
            case 31: return sr31;
            case  5: return             // PSW
                psw_z         |
                psw_s   <<  1 |
                psw_ov  <<  2 |
                psw_cy  <<  3 |
                psw_fpr <<  4 |
                psw_fud <<  5 |
                psw_fov <<  6 |
                psw_fzd <<  7 |
                psw_fiv <<  8 |
                psw_fro <<  9 |
                psw_id  << 12 |
                psw_ae  << 13 |
                psw_ep  << 14 |
                psw_np  << 15 |
                psw_i   << 16
            ;

            // Explicit case labels to encourage tableswitch
            case  8: case  9: case 10: case 11: case 12:
            case 13: case 14: case 15: case 16: case 17:
            case 18: case 19: case 20: case 21: case 22:
            case 23: case 26: case 27: case 28:
                return 0;
        }

        return 0; // Invalid ID
    }

    // Determine whether all interrupts are masked
    boolean irqMasked() {
        return (psw_np | psw_ep | psw_id) != 0;
    }

    // Read a value from the bus
    int read(int address, int type) {
        if (stage == EXECUTE && parent.checkBreakpoints(Breakpoint.READ, inst))
            return 0;
        int value = parent.read(address, type, false);
        //cycles += readCycles(address);
        return value;
    }

    // Initialize CPU for system reset
    void reset() {

        // Reset all registers to zero to aid with debugging
        // The hardware does not do this
        for (int x = 0; x < 32; x++) {
            registers[x] = 0;
            setSystemRegister(x, 0, true);
        }

        // Initialize CPU state
        bsBuffered = false;
        exception  = 0;
        halt       = NONE;
        pc         = 0xFFFFFFF0;
        stage      = FETCH16;
        setSystemRegister(VUE.PSW, 0x00008000, true);
        setSystemRegister(VUE.ECR, 0x0000FFF0, true);
        irq[0] = irq[1] = irq[2] = irq[3] = irq[4] = false;
    }

    // Specify a new address for PC
    int setProgramCounter(int address) {
        pc        = address & 0xFFFFFFFE;
        exception = 0;
        halt      = NONE;
        stage     = INTERRUPT;
        return pc;
    }

    // Specify a new value for a system register
    int setSystemRegister(int id, int value, boolean debug) {

        // Process value by ID
        switch (id) {
            case  0: return eipc  = value & 0xFFFFFFFE;
            case  1: return eipsw = value & 0x000FF3FF;
            case  2: return fepc  = value & 0xFFFFFFFE;
            case  3: return fepsw = value & 0x000FF3FF;
            case 24: return cacheControl(value);
            case 25: return adtre = value & 0xFFFFFFFE;
            case 29: return sr29  = value;
            case 31: return sr31  = debug || value >= 0 ? value : -value;
            case 4: // ECR
                if (debug) {
                    ecr_eicc = value & 0xFFFF;
                    ecr_fecc = value >>> 16;
                }
                return value;
            case 5: // PSW
                psw_z   = value >>  0 & 1;
                psw_s   = value >>  1 & 1; 
                psw_ov  = value >>  2 & 1;
                psw_cy  = value >>  3 & 1;
                psw_fpr = value >>  4 & 1;
                psw_fud = value >>  5 & 1;
                psw_fov = value >>  6 & 1;
                psw_fzd = value >>  7 & 1;
                psw_fiv = value >>  8 & 1;
                psw_fro = value >>  9 & 1;
                psw_id  = value >> 12 & 1;
                psw_ae  = value >> 13 & 1;
                psw_ep  = value >> 14 & 1;
                psw_np  = value >> 15 & 1;
                psw_i   = value >> 16 & 0xF;
                return value & 0x000FF3FF;

            // Explicit case labels to encourage tableswitch
            case  6: case  7: case  8: case  9: case 10: case 11:
            case 12: case 13: case 14: case 15: case 16: case 17:
            case 18: case 19: case 20: case 21: case 22: case 23:
            case 26: case 27: case 28: case 30:
                return getSystemRegister(id);
        }

        return 0; // Invalid ID
    }

    // Write a value to the bus
    void write(int address, int type, int value) {
        if (stage==EXECUTE && parent.checkBreakpoints(Breakpoint.WRITE, inst))
            return;
        parent.write(address, type, value, false);
        //cycles += writeCycles(address);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Configure instruction cache
    private int cacheControl(int value) {
        //boolean icc = (value & 0x00000001) != 0;
        boolean ice = (value & 0x00000002) != 0;
        //boolean icd = (value & 0x00000010) != 0;
        //boolean icr = (value & 0x00000020) != 0;
        //int     cec = value >>   8 & 0xFFF;
        //int     cen = value >>> 20;
        //int     sa  = value >>>  8;
        //if (cec > 128) cec = 128;

        // Instruction cache is not currently implemented

        return chcw = ice ? 2 : 0;
    }

}
