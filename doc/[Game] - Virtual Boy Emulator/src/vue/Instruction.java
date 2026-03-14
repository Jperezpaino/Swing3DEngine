package vue;
import vue.*;

// Helper class for encoding and decoding instructions
public final class Instruction {

    // Instance fields
    public final int[] bits;     // Binary machine code
    public int         format;   // Binary format
    public int         id;       // Emulation core internal identifier
    public int         opcode;   // Instruction opcode
    public final int[] operands; // Operands in machine code order
    public int         size;     // Size in bytes of instruction



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Temporary opcode identifiers
    private static final int BITSTRING = -2; // Bit string instructions
    private static final int FLOATENDO = -3; // Floating/Nintendo instructions

    // Lookup table: bit string instructions by subopcode
    private static int[] BITSTRINGS = {
        VUE.SCH0BSU,
        VUE.SCH0BSD,
        VUE.SCH1BSU,
        VUE.SCH1BSD,
        VUE.ILLEGAL,
        VUE.ILLEGAL,
        VUE.ILLEGAL,
        VUE.ILLEGAL,
        VUE.ORBSU,
        VUE.ANDBSU,
        VUE.XORBSU,
        VUE.MOVBSU,
        VUE.ORNBSU,
        VUE.ANDNBSU,
        VUE.XORNBSU,
        VUE.NOTBSU
    };

    // Lookup table: floating-point/Nintendo instructions by subopcode
    private static int[] FLOATENDOS = {
        VUE.CMPF_S,
        VUE.ILLEGAL,
        VUE.CVT_WS,
        VUE.CVT_SW,
        VUE.ADDF_S,
        VUE.SUBF_S,
        VUE.MULF_S,
        VUE.DIVF_S,
        VUE.XB,
        VUE.XH,
        VUE.REV,
        VUE.TRNC_SW,
        VUE.MPYHW
    };

    // Lookup table: instruction parameters by opcode
    private static class OPDEF {

        // Instance fields
        final boolean extend; // Immediate data is sign-extended
        final int     format; // Instruction format
        final int     id;     // Package identifier

        // Object constructor
        public OPDEF(int id, int format, boolean extend) {
            this.id     = id;
            this.format = format;
            this.extend = extend;
        }

    }
    private static final OPDEF[] OPDEFS = {
        new OPDEF(VUE.MOV_REG, 1, false),
        new OPDEF(VUE.ADD_REG, 1, false),
        new OPDEF(VUE.SUB,     1, false),
        new OPDEF(VUE.CMP_REG, 1, false),
        new OPDEF(VUE.SHL_REG, 1, false),
        new OPDEF(VUE.SHR_REG, 1, false),
        new OPDEF(VUE.JMP,     1, false),
        new OPDEF(VUE.SAR_REG, 1, false),
        new OPDEF(VUE.MUL,     1, false),
        new OPDEF(VUE.DIV,     1, false),
        new OPDEF(VUE.MULU,    1, false),
        new OPDEF(VUE.DIVU,    1, false),
        new OPDEF(VUE.OR,      1, false),
        new OPDEF(VUE.AND,     1, false),
        new OPDEF(VUE.XOR,     1, false),
        new OPDEF(VUE.NOT,     1, false),
        new OPDEF(VUE.MOV_IMM, 2,  true),
        new OPDEF(VUE.ADD_IMM, 2,  true),
        new OPDEF(VUE.SETF,    2, false),
        new OPDEF(VUE.CMP_IMM, 2,  true),
        new OPDEF(VUE.SHL_IMM, 2, false),
        new OPDEF(VUE.SHR_IMM, 2, false),
        new OPDEF(VUE.CLI,     2, false),
        new OPDEF(VUE.SAR_IMM, 2, false),
        new OPDEF(VUE.TRAP,    2, false),
        new OPDEF(VUE.RETI,    2, false),
        new OPDEF(VUE.HALT,    2, false),
        new OPDEF(VUE.ILLEGAL, 0, false),
        new OPDEF(VUE.LDSR,    2, false),
        new OPDEF(VUE.STSR,    2, false),
        new OPDEF(VUE.SEI,     2, false),
        new OPDEF(BITSTRING,   2, false),
        new OPDEF(VUE.BCOND,   3, false),
        new OPDEF(VUE.BCOND,   3, false),
        new OPDEF(VUE.BCOND,   3, false),
        new OPDEF(VUE.BCOND,   3, false),
        new OPDEF(VUE.BCOND,   3, false),
        new OPDEF(VUE.BCOND,   3, false),
        new OPDEF(VUE.BCOND,   3, false),
        new OPDEF(VUE.BCOND,   3, false),
        new OPDEF(VUE.MOVEA,   5,  true),
        new OPDEF(VUE.ADDI,    5,  true),
        new OPDEF(VUE.JR,      4, false),
        new OPDEF(VUE.JAL,     4, false),
        new OPDEF(VUE.ORI,     5, false),
        new OPDEF(VUE.ANDI,    5, false),
        new OPDEF(VUE.XORI,    5, false),
        new OPDEF(VUE.MOVHI,   5, false),
        new OPDEF(VUE.LD_B,    6, false),
        new OPDEF(VUE.LD_H,    6, false),
        new OPDEF(VUE.ILLEGAL, 0, false),
        new OPDEF(VUE.LD_W,    6, false),
        new OPDEF(VUE.ST_B,    6, false),
        new OPDEF(VUE.ST_H,    6, false),
        new OPDEF(VUE.ILLEGAL, 0, false),
        new OPDEF(VUE.ST_W,    6, false),
        new OPDEF(VUE.IN_B,    6, false),
        new OPDEF(VUE.IN_H,    6, false),
        new OPDEF(VUE.CAXI,    6, false),
        new OPDEF(VUE.IN_W,    6, false),
        new OPDEF(VUE.OUT_B,   6, false),
        new OPDEF(VUE.OUT_H,   6, false),
        new OPDEF(FLOATENDO,   7, false),
        new OPDEF(VUE.OUT_W,   6, false)
    };



    ///////////////////////////////////////////////////////////////////////////
    //                            Static Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Determine the size of an instruction given its first halfword
    public static int getSize(int bits0) {
        bits0 = bits0 >> 10 & 0x3F; // Resolve opcode
        return bits0 < 0x28 || bits0 == 0x32 || bits0 == 0x36 ? 2 : 4;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public Instruction() {
        bits     = new int[2];
        operands = new int[3];
    }

    // Decoding constructor
    public Instruction(int bits0, int bits1) {
        this();
        decode(bits0, bits1);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Decode an instruction given its machine code bits
    public void decode(int bits0, int bits1) {
        bits[0] = bits0 & 0xFFFF;
        bits[1] = bits1 & 0xFFFF;
        decode();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Decode the instruction
    void decode() {

        // Configure instance fields
        opcode = bits[0] >> 10 & 0x3F;
        size   = opcode < 0x28 || opcode == 0x32 || opcode == 0x36 ? 2 : 4;

        // Decode the operands from the given bits
        OPDEF def = OPDEFS[opcode];
        switch (def.format) {
            case 1: decodeFormatII (false);      break;
            case 2: decodeFormatII (def.extend); break;
            case 3: decodeFormatIII();           break;
            case 4: decodeFormatIV ();           break;
            case 5: decodeFormatV  (def.extend); break;
            case 6: decodeFormatV  (true);       break;
            case 7: decodeFormatVII();           break;
        }

        // Process internal ID
        id = def.id;
        switch (id) {
            case BITSTRING: id = operands[1] >= 0x10 ? VUE.ILLEGAL :
                BITSTRINGS[operands[1]]; break;
            case FLOATENDO: id = operands[2] >= 0x0D ? VUE.ILLEGAL :
                FLOATENDOS[operands[2]]; break;
        }

        // Configure other fields
        format = def.format;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                      Instruction Format Decoders                      //
    ///////////////////////////////////////////////////////////////////////////

    private void decodeFormatII(boolean extend) {
        operands[0] = bits[0] >> 5 & 0x001F;
        operands[1] = bits[0]      & 0x001F;
        if (extend && (operands[1] & 0x10) != 0)
            operands[1] |= 0xFFFFFFE0;
    }

    private void decodeFormatIII() {
        opcode      = 0x20; // Special case for opcode
        operands[0] = bits[0] >> 9 & 0x000F;
        operands[1] = bits[0]      & 0x01FF;
        if ((bits[0] & 0x0100) != 0)
            operands[1] |= 0xFFFFFE00;
    }

    private void decodeFormatIV() {
        operands[0] = (bits[0] << 16 | bits[1]) & 0x03FFFFFF;
        if ((operands[0] & 0x02000000) != 0)
            operands[0] |= 0xFC000000;
    }

    private void decodeFormatV(boolean extend) {
        decodeFormatII(false);
        operands[2] = bits[1];
        if (extend && (operands[2] & 0x8000) != 0)
            operands[2] |= 0xFFFF0000;
    }

    private void decodeFormatVII() {
        decodeFormatII(false);
        operands[2] = bits[1] >> 10 & 0x3F;
    }

}
