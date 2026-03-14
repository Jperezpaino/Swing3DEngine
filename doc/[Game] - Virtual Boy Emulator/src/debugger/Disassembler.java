package debugger;

// Java imports
import java.util.*;

// Project imports
import debugger.*;
import vue.*;

// Converts instructions between assembly and machine code
class Disassembler {

    // Instance fields
    private HashMap<Integer, Integer> config; // Output configuration



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Confiuration keys
    public static final int ACCESS_FORMAT    =  0;
    public static final int ACCESS_NOTATION  =  1; // 12[reg1] or [reg1 + 12]
    public static final int ADDRESS_FORMAT   =  2;
    public static final int BCOND_NOTATION   =  3;
    public static final int CARRY_LOWER      =  4; // Condition mnemonics
    public static final int CONDITION_CASE   =  5;
    public static final int CONDITION_NAMES  =  6;
    public static final int EQUAL_ZERO       =  7; // Condition mnemonics
    public static final int HEX_CASE         =  8;
    public static final int HEX_NOTATION     =  9;
    public static final int HEX_USEWIDTH     = 10;
    public static final int IMMEDIATE_FORMAT = 11;
    public static final int JMP_BRACKETS     = 12;
    public static final int MNEMONIC_CASE    = 13;
    public static final int OPERAND_ORDER    = 14; // Dest first or dest last
    public static final int REGISTER_CASE    = 15;
    public static final int REGISTER_NAMES   = 16; // Use register names
    public static final int SETF_NOTATION    = 17;
    public static final int SYSREG_CASE      = 18;
    public static final int SYSREG_NAMES     = 19; // Use sysreg names

    // Configuration values
    public static final int ABSOLUTE      =  0; // Address format
    public static final int CARRY         =  1; // Condition mnemonic
    public static final int DEC_SIGNED    =  2; // Immediate format
    public static final int DEC_UNSIGNED  =  3; // Immediate format
    public static final int DEST_FIRST    =  4; // Operand order
    public static final int DEST_LAST     =  5; // Operand order
    public static final int DISP_INSIDE   =  6; // Access notation
    public static final int DISP_OUTSIDE  =  7; // Access notation
    public static final int EQUAL         =  8; // Condition mnemonic
    public static final int HEX_SIGNED    =  9; // Immediate format
    public static final int HEX_UNSIGNED  = 10; // Immediate format
    public static final int LOWER         = 11; // Condition mnemonic
    public static final int LOWERCASE     = 12;
    public static final int NO            = 13;
    public static final int ONE_OPERAND   = 14; // Bcond/SETF notation
    public static final int PREFIX_0X     = 15; // Hex notation
    public static final int PREFIX_DOLLAR = 16; // Hex notation
    public static final int SUFFIX_H      = 17; // Hex notation
    public static final int TWO_OPERAND   = 18; // Bcond/SETF notation
    public static final int UPPERCASE     = 19;
    public static final int YES           = 20;
    public static final int ZERO          = 21; // Condition mnemonic

    // Common configuration symbols
    public static final int DEFAULT = -1; // ID
    public static final int NONE    = -1; // Unmapped value
    public static final int REMOVE  = -1; // Control value

    // Instruction mnemonics
    private static final String[] MNEMONICS = {
        "ADD",     "ADD",     "ADDF.S",  "ADDI",   "AND",    "ANDI",
        "Bcond",   "CAXI",    "CLI",     "CMP",    "CMP",    "CMPF.S",
        "CVT.SW",  "CVT.WS",  "DIV",     "DIVF.S", "DIVU",   "HALT", 
        "IN.B",    "IN.H",    "IN.W",    "JAL",    "JMP",    "JR", 
        "LD.B",    "LD.H",    "LD.W",    "LDSR",   "MOV",    "MOV", 
        "MOVEA",   "MOVHI",   "MPYHW",   "MUL",    "MULF.S", "MULU", 
        "NOT",     "OR",      "ORI",     "OUT.B",  "OUT.H",  "OUT.W", 
        "RETI",    "REV",     "SAR",     "SAR",    "SEI",    "SETF", 
        "SHL",     "SHL",     "SHR",     "SHR",    "ST.B",   "ST.H", 
        "ST.W",    "STSR",    "SUB",     "SUBF.S", "TRAP",   "TRNC.SW", 
        "XB",      "XH",      "XOR",     "XORI",   "ANDBSU", "ANDNBSU", 
        "MOVBSU",  "NOTBSU",  "ORBSU",   "ORNBSU", "XORBSU", "XORNBSU",
        "SCH0BSD", "SCH0BSU", "SCH1BSD", "SCH1BSU",
    };

    // Bcond mnemonics
    private static final String[] BCONDS = {
        "BV",  "BC",  "BE",  "BNH", "BN", "BR",  "BLT", "BLE",
        "BNV", "BNC", "BNE", "BH",  "BP", "NOP", "BGE", "BGT",
        "BL",  "BZ",  "BNL", "BNZ"
    };

    // Condition mnemonics
    private static final String[] CONDITIONS = {
        "V",  "C",  "E",  "NH", "N", "T", "LT", "LE",
        "NV", "NC", "NE", "H",  "P", "F", "GE", "GT",
        "L",  "Z",  "NL", "NZ"
    };

    // Program register names
    private static final String[] REGNAMES = {
        "r0",  "r1",  "hp",  "sp",  "gp",  "tp",  "r6",  "r7",
        "r8",  "r9",  "r10", "r11", "r12", "r13", "r14", "r15",
        "r16", "r17", "r18", "r19", "r20", "r21", "r22", "r23",
        "r24", "r25", "r26", "r27", "r28", "r29", "r30", "lp"
    };

    // System register names
    private static final String[] SYSREGNAMES = {
        "EIPC", "EIPSW", "FEPC", "FEPSW", "ECR", "PSW", "PIR", "TKCW",
        null,   null,    null,   null,    null,   null, null,  null,
        null,   null,    null,   null,    null,   null, null,  null,
        "CHCW", "ADTRE", null,   null,    null,   null, null,  null
    };



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public Disassembler() {
        config = new HashMap<Integer, Integer>();

        // Configure default settings
        setValue(DEFAULT, ACCESS_FORMAT,    HEX_SIGNED);
        setValue(DEFAULT, ACCESS_NOTATION,  DISP_OUTSIDE);
        setValue(DEFAULT, ADDRESS_FORMAT,   ABSOLUTE);
        setValue(DEFAULT, BCOND_NOTATION,   ONE_OPERAND);
        setValue(DEFAULT, CARRY_LOWER,      LOWER);
        setValue(DEFAULT, CONDITION_CASE,   LOWERCASE);
        setValue(DEFAULT, CONDITION_NAMES,  YES);
        setValue(DEFAULT, EQUAL_ZERO,       EQUAL);
        setValue(DEFAULT, HEX_CASE,         UPPERCASE);
        setValue(DEFAULT, HEX_NOTATION,     PREFIX_0X);
        setValue(DEFAULT, HEX_USEWIDTH,     NO);
        setValue(DEFAULT, IMMEDIATE_FORMAT, DEC_UNSIGNED);
        setValue(DEFAULT, JMP_BRACKETS,     YES);
        setValue(DEFAULT, MNEMONIC_CASE,    UPPERCASE);
        setValue(DEFAULT, OPERAND_ORDER,    DEST_LAST);
        setValue(DEFAULT, REGISTER_CASE,    LOWERCASE);
        setValue(DEFAULT, REGISTER_NAMES,   YES);
        setValue(DEFAULT, SETF_NOTATION,    TWO_OPERAND);
        setValue(DEFAULT, SYSREG_CASE,      LOWERCASE);
        setValue(DEFAULT, SYSREG_NAMES,     YES);

        // Configure per-instruction settings
        setValue(VUE.ADD_IMM, IMMEDIATE_FORMAT, DEC_SIGNED);
        setValue(VUE.ADDI,    IMMEDIATE_FORMAT, DEC_SIGNED);
        setValue(VUE.ANDI,    IMMEDIATE_FORMAT, HEX_UNSIGNED);
        setValue(VUE.ANDI,    HEX_USEWIDTH,     YES);
        setValue(VUE.CMP_IMM, IMMEDIATE_FORMAT, DEC_SIGNED);
        setValue(VUE.MOV_IMM, IMMEDIATE_FORMAT, DEC_SIGNED);
        setValue(VUE.MOVEA,   IMMEDIATE_FORMAT, HEX_UNSIGNED);
        setValue(VUE.MOVEA,   HEX_USEWIDTH,     YES);
        setValue(VUE.MOVHI,   IMMEDIATE_FORMAT, HEX_UNSIGNED);
        setValue(VUE.MOVHI,   HEX_USEWIDTH,     YES);
        setValue(VUE.ORI,     IMMEDIATE_FORMAT, HEX_UNSIGNED);
        setValue(VUE.ORI,     HEX_USEWIDTH,     YES);
        setValue(VUE.XORI,    IMMEDIATE_FORMAT, HEX_UNSIGNED);
        setValue(VUE.XORI,    HEX_USEWIDTH,     YES);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Convert a machine code instruction into assembly
    public String[] disassemble(int address,Instruction inst,String[] columns){

        // Initialize columns
        columns[0] = String.format(getHexCase() ? "%08X" : "%08x", address);
        columns[1] = fmtBytes(inst);
        columns[2] = null;
        columns[3] = null;

        // Invalid instruction
        if (inst.id == VUE.ILLEGAL)
            return columns;

        // Process special-case instructions
        switch (inst.id) {

            // Unique instructions
            case VUE.BCOND: fmtBcond(inst, columns, address); break;
            case VUE.JMP:   fmtJMP  (inst, columns);          break;
            case VUE.SETF:  fmtSETF (inst, columns);          break;

            // System register instructions
            case VUE.LDSR: case VUE.STSR:
                fmtSysReg(inst, columns);
                break;

            // TRAP
            case VUE.TRAP:
                columns[2] = MNEMONICS[inst.id];
                columns[3] = fmtNumber(inst.id, IMMEDIATE_FORMAT,
                    inst.operands[1], 2);
                break;

            // Single-register instructions
            case VUE.XB: case VUE.XH:
                columns[2] = MNEMONICS[inst.id];
                columns[3] = fmtRegister(inst.operands[0]);
                break;

            // Zero-operand instructions
            case VUE.CLI: case VUE.HALT: case VUE.RETI: case VUE.SEI:
                columns[2] = MNEMONICS[inst.id];
                break;
        }

        // Process all other instructions by format
        if (columns[2] == null) {
            columns[2] = MNEMONICS[inst.id];
            if (inst.opcode != 0x1F) switch(inst.format) {

                // Format I and VII
                case 1: case 7:
                    columns[3] = fmtOperands(
                        fmtRegister(inst.operands[1]),
                        fmtRegister(inst.operands[0]),
                        null
                    );
                    break;

                // Format II
                case 2:
                    columns[3] = fmtOperands(
                        fmtNumber(inst.id, IMMEDIATE_FORMAT,
                            inst.operands[1], 8),
                        fmtRegister(inst.operands[0]),
                        null
                    );
                    break;

                // Format IV
                case 4:
                    columns[3] = fmtAddress(address, inst.operands[0]);
                    break;

                // Format V
                case 5:
                    columns[3] = fmtOperands(
                        fmtNumber(inst.id, IMMEDIATE_FORMAT,
                            inst.operands[2], 4),
                        fmtRegister(inst.operands[1]),
                        fmtRegister(inst.operands[0])
                    );
                    break;

                // Format VI
                case 6: fmtFormatVI(inst, columns);
            }
        }

        // Finalize mnemonic text
        if (getValue(DEFAULT, MNEMONIC_CASE) == LOWERCASE)
            columns[2] = columns[2].toLowerCase();

        return columns;
    }

    // Determine the case of hexadecimal figures
    // Returns true if uppercase
    public boolean getHexCase() {
        return getValue(DEFAULT, HEX_CASE) == UPPERCASE;
    }

    // Determine the case of program register names
    // Returns true if uppercase
    public boolean getProgramCase() {
        return getValue(DEFAULT, REGISTER_CASE) == UPPERCASE;
    }

    // Determine whether program register names are in use
    public boolean getProgramNames() {
        return getValue(DEFAULT, REGISTER_NAMES) == YES;
    }

    // Determine the case of system register names
    // Returns true if uppercase
    public boolean getSystemCase() {
        return getValue(DEFAULT, SYSREG_CASE) == UPPERCASE;
    }

    // Determine whether system register names are in use
    public boolean getSystemNames() {
        return getValue(DEFAULT, SYSREG_NAMES) == YES;
    }

    // Retrieve a behavior associated with an instruction
    public int getValue(int id, int key) {
        Integer value = config.get(id << 8 | key);
        if (value == null) // Fall back to default setting
            value = config.get (DEFAULT << 8 | key);
        return value == null ? NONE : value;
    }

    // Specify a new behavior for a given instruction
    public void setValue(int id, int key, int value) {
        if (value == REMOVE && id != DEFAULT)
            config.remove(id << 8 | key);
        else config.put(id << 8 | key, value);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                          Disassembly Methods                          //
    ///////////////////////////////////////////////////////////////////////////

    // Format Bcond
    private void fmtBcond(Instruction inst, String[] columns, int address) {
        String addr = fmtAddress(address, inst.operands[1]);

        // One-operand notation
        if (getValue(DEFAULT, BCOND_NOTATION) == ONE_OPERAND) {
            columns[2] = BCONDS[getCondition(inst.operands[0])];
            if (inst.operands[0] != 0xD) // Zero-operand for NOP
                columns[3] = addr;
            return;
        }

        // Two-operand notation
        columns[2] = MNEMONICS[inst.id];
        columns[3] = fmtCondition(inst.id, inst.operands[0]) + ", " + addr;
    }

    // Format instructions in Format VI
    private void fmtFormatVI(Instruction inst, String[] columns) {
        String  reg2    = fmtRegister(inst.operands[0]);
        String  reg1    = fmtRegister(inst.operands[1]);
        String  disp    = "";
        boolean outside = getValue(DEFAULT, ACCESS_NOTATION) == DISP_OUTSIDE;

        // Format displacement
        if (inst.operands[2] != 0) {
            disp = fmtNumber(inst.id, ACCESS_FORMAT,      // Outside
                inst.operands[2], 8); 
            if (!outside) disp = disp.charAt(0) == '-' ?  // Inside
                " - " + disp.substring(1) : " + " + disp;
        }

        // Format source
        String addr = getValue(DEFAULT, ACCESS_NOTATION) == DISP_OUTSIDE ?
            disp + "[" + reg1 + "]" : "[" + reg1 + disp + "]";

        // Format output
        int base = 0;
        switch (inst.id) {
            case VUE.OUT_B: case VUE.OUT_H: case VUE.OUT_W:
            case VUE.ST_B:  case VUE.ST_H:  case VUE.ST_W:
                base = 1;
        }
        String[] operands = { addr, reg2 };
        columns[3] = fmtOperands(operands[base], operands[base ^ 1], null);
    }

    // Format JMP
    private void fmtJMP(Instruction inst, String[] columns) {
        String reg1 = fmtRegister(inst.operands[1]);
        columns[2]  = MNEMONICS[inst.id];
        columns[3]  = getValue(DEFAULT, JMP_BRACKETS) == YES ?
            "[" + reg1 + "]" : reg1;
    }

    // Format SETF
    private void fmtSETF(Instruction inst, String[] columns) {
        String reg = fmtRegister(inst.operands[0]);
        columns[2] = MNEMONICS[inst.id];

        // One-operand notation
        if (getValue(DEFAULT, SETF_NOTATION) == ONE_OPERAND) {
            String cond  = fmtCondition(DEFAULT, inst.operands[1]);
            columns[2]  += cond.toUpperCase();
            columns[3]   = reg;
            return;
        }

        // Two-operand notation
        String cond = fmtCondition(inst.id, inst.operands[1]);
        columns[3]  = fmtOperands(cond, reg, null);
    }

    // Format a system register instruction
    private void fmtSysReg(Instruction inst, String[] columns) {
        int base = inst.id == VUE.LDSR ? 0 : 1;
        String[] operands = {
            fmtRegister(         inst.operands[0]),
            fmtSysReg  (inst.id, inst.operands[1])
        };
        columns[2] = MNEMONICS[inst.id];
        columns[3] = fmtOperands(operands[base], operands[base ^ 1], null);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                      General Formatting Methods                       //
    ///////////////////////////////////////////////////////////////////////////

    // Format an address
    private String fmtAddress(int address, int disp) {
        return getValue(DEFAULT, ADDRESS_FORMAT) == ABSOLUTE ?
            String.format(getValue(DEFAULT, HEX_CASE) == UPPERCASE ?
                "%08X" : "%08x", address + disp) :
            fmtNumber(DEFAULT, ADDRESS_FORMAT, disp, 8);
    }

    // Format machine code bits
    private String fmtBytes(Instruction inst) {
        String format = getHexCase() ? "%02X" : "%02x";
        String ret    = String.format(format + " " + format,
            inst.bits[0] & 0xFF, inst.bits[0] >> 8 & 0xFF);
        if (inst.size == 4) ret += String.format(" " + format + " " + format,
            inst.bits[1] & 0xFF, inst.bits[1] >> 8 & 0xFF);
        return ret;
    }

    // Format a condition
    private String fmtCondition(int id, int cond) {

        // Condition names are not used
        if (getValue(DEFAULT, CONDITION_NAMES) == NO)
            return fmtNumber(id, IMMEDIATE_FORMAT, cond, 2);

        // Process condition name
        String ret = CONDITIONS[getCondition(cond)];
        if (getValue(DEFAULT, CONDITION_CASE) == LOWERCASE)
            ret = ret.toLowerCase();
        return ret;
    }

    // Format an immediate value
    private String fmtNumber(int id, int key, int value, int digits) {

        // Select parameters by format
        boolean hex  = false; // Format is hexadecimal
        boolean sign = false; // Number is signed
        switch(getValue(id, key)) {
            case DEC_SIGNED:   hex = false; sign = true;  break;
            case DEC_UNSIGNED: hex = false; sign = false; break;
            case HEX_SIGNED:   hex = true;  sign = true;  break;
            case HEX_UNSIGNED: hex = true;  sign = false; break;
        }

        // Process sign
        if (sign) {
            sign = value < 0;
            if (sign) value = -value;
        }

        // Generate the initial formatting
        String ret = null;
        if (hex) {
            ret = String.format(getValue(DEFAULT, HEX_CASE) == UPPERCASE ?
                "%X" : "%x", value);
            if (getValue(id, HEX_USEWIDTH) == YES) {
                while (ret.length() < digits)
                    ret = "0" + ret;
                ret = ret.substring(ret.length() - digits);
            }
        } else ret = Integer.toString(value);

        // Process hexadecimal notation
        if (hex) switch (getValue(DEFAULT, HEX_NOTATION)) {
            case PREFIX_0X:     ret =  "0x" + ret; break;
            case PREFIX_DOLLAR: ret =  "$"  + ret; break;
            case SUFFIX_H:      ret += "h";
                if (ret.charAt(0) > '9')
                    ret = "0" + ret;
                break;
        }

        // Process sign
        if (sign) ret = "-" + ret;
        return ret;
    }

    // Format operands
    private String fmtOperands(String op1, String op2, String op3) {
        return getValue(DEFAULT, OPERAND_ORDER) == DEST_LAST ?
            op3 == null ? op1 + ", " + op2 : op1 + ", " + op2 + ", " + op3 :
            op3 == null ? op2 + ", " + op1 : op3 + ", " + op2 + ", " + op1
        ;
    }

    // Format a program register
    private String fmtRegister(int index) {

        // Retrieve register name
        String ret = getValue(DEFAULT, REGISTER_NAMES) == YES ?
            REGNAMES[index] : "r" + index;

        // Convert the name to uppercase
        if (getValue(DEFAULT, REGISTER_CASE) == UPPERCASE)
            ret = ret.toUpperCase();
        return ret;
    }

    // Format a system register
    private String fmtSysReg(int id, int index) {

        // Retrieve system register name if available
        String ret = getValue(DEFAULT, SYSREG_NAMES) == YES ?
            SYSREGNAMES[index] : null;

        // No system register name is available -- use the literal number
        if (ret == null)
            return fmtNumber(id, IMMEDIATE_FORMAT, index, 2);

        // Convert the name to lowercase
        if (getValue(DEFAULT, SYSREG_CASE) == LOWERCASE)
            ret = ret.toLowerCase();
        return ret;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Determine the mnemonic index for a condition
    private int getCondition(int cond) {
        int cl = getValue(DEFAULT, CARRY_LOWER);
        int ez = getValue(DEFAULT, EQUAL_ZERO);
        switch (cond) {
            case  1: if (cl == LOWER) return 16; break;
            case  2: if (ez == ZERO ) return 17; break;
            case  9: if (cl == LOWER) return 18; break;
            case 10: if (ez == ZERO ) return 19; break;
        }
        return cond;
    }

}
