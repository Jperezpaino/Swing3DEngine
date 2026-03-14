package vue;
import vue.*;

// Represents a stop condition in the emulator
public class Breakpoint {

    // Instance fields
    private boolean activated;    // The breakpoint is active right now
    private String  address;      // User-entered address text
    private int[][] addresses;    // Applicable address ranges
    private int[]   condition;    // Compiled RPN-ordered command list
    private int     breakCode;    // Break code when activated
    private boolean enabled;      // The breakpoint is active
    private int[]   errStatus;    // Error properties
    private String  expression;   // User-entered condition text
    private int     events;       // Bit mask of emulator event types
    private String  name;         // Display text



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Event types
    public static final int ALWAYS    = ~0;
    public static final int READ      = 0x01;
    public static final int WRITE     = 0x02;
    public static final int EXECUTE   = 0x04;
    public static final int EXCEPTION = 0x08;
    public static final int FRAME     = 0x10;

    // Error parameters
    public static final int ADDRESS   = 0;
    public static final int CONDITION = 1;
    public static final int CODE      = 0;
    public static final int LINE      = 1;
    public static final int COLUMN    = 2;
           static final int POSITION  = 3;
           static final int LENGTH    = 4;

    // Error codes
           static final int ERROR   = -1;
    public static final int NOERROR =  0;
    public static final int BADEOF  =  1;
    public static final int LITERAL =  2;
    public static final int SYNTAX  =  3;
    public static final int UNKNOWN =  4;

    // Evaluation types
    public static final int INT   = 0;
    public static final int FLOAT = 1;
    public static final int BOOL  = 2;

    // Command type indexes
    private static final int UNARY  =  5;
    private static final int BINARY = 17;

    // Command IDs
    static final int LITERALINT    =  0;
    static final int LITERALFLOAT  =  1;
    static final int PROREG        =  2;
    static final int SYSREG        =  3;
    static final int OPERAND       =  4;
    static final int BITWISENOT    =  5;
    static final int LOGICALNOT    =  6;
    static final int NEGATE        =  7;
    static final int BUSREAD       =  8;
    static final int CFLOAT        =  9;
    static final int CINT          = 10;
    static final int XFLOAT        = 11;
    static final int XINT          = 12;
    static final int FIX           = 13;
    static final int FLOOR         = 14;
    static final int ROUND         = 15;
    static final int CEIL          = 16;
    static final int DIVIDE        = 17;
    static final int MULTIPLY      = 18;
    static final int REMAINDER     = 19;
    static final int ADD           = 20;
    static final int SUBTRACT      = 21;
    static final int SHIFTLEFT     = 22;
    static final int SHIFTRIGHTA   = 23;
    static final int SHIFTRIGHTL   = 24;
    static final int GREATER       = 25;
    static final int GREATEREQUAL  = 26;
    static final int GREATERU      = 27;
    static final int GREATEREQUALU = 28;
    static final int LESS          = 29;
    static final int LESSEQUAL     = 30;
    static final int LESSU         = 31;
    static final int LESSEQUALU    = 32;
    static final int EQUAL         = 33;
    static final int NOTEQUAL      = 34;
    static final int BITWISEAND    = 35;
    static final int BITWISEXOR    = 36;
    static final int BITWISEOR     = 37;
    static final int LOGICALAND    = 38;
    static final int LOGICALXOR    = 39;
    static final int LOGICALOR     = 40;

    // System register IDs
    static final int PC = -1;

    // Instruction operand IDs
    static final int EXCODE    = -1;
    static final int COND      =  1;
    static final int DISP      =  2;
    static final int FORMAT    =  3;
    static final int IMM       =  4;
    static final int INST      =  5;
    static final int OPCODE    =  6;
    static final int REG1      =  7;
    static final int REG2      =  8;
    static final int REGID     =  9;
    static final int SIZE      = 10;
    static final int SUBOPCODE = 11;
    static final int VECTOR    = 12;
    static final int VALUE     = 13;

    // Instruction data template
    private static final Instruction BLANKINST = new Instruction();



    ///////////////////////////////////////////////////////////////////////////
    //                              Stack Class                              //
    ///////////////////////////////////////////////////////////////////////////

    // Stack implementation for pairing types with values
    static class Stack {

        // Instance fields
        private int[][] items;  // Collection items
        private int     length; // Number of items in collection

        // Default constructor
        Stack() {
            length = 0; 
            items  = new int[16][2];
        }

        // Reset the contents of the stack
        void clear() {
            length = 0;
        }

        // Determine whether the bottom stack value is truthy
        boolean isTrue() {
            return
                length == 0 ? true  :
                length != 1 ? false :
                Breakpoint.isTrue(items[0])
            ;
        }

        // Remove an item from the stack
        int[] pop() {
            return length == 0 ? null : items[--length];
        }

        // Add a new item to the stack
        int[] push(int type, int value) {

            // Reallocate list of items to hold more elements
            if (length == items.length)
                setCapacity(length << 1);

            // Add the item to the list
            int[] ret = items[length++];
            ret[0]    = type;
            ret[1]    = value;
            return ret;
        }

        // Add a new float item to the stack
        int[] push(int type, float value) {
            return push(type, asInteger(value));
        }

        // Add a new double item to the stack
        int[] push(int type, double value) {
            return push(type, asInteger((float) value));
        }

        // Ensure enough capacity for a set of breakpoints
        void setCapacity(Breakpoint[] breakpoints) {
            setCapacity(getHeight(breakpoints));
        }

        // Specify a new collection capacity
        private void setCapacity(int capacity) {
            if (capacity <= items.length)
                return;
            int[][] items = new int[capacity][2];
            System.arraycopy(this.items, 0, items, 0, length);
            this.items = items;
        }

    }
    


    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    public Breakpoint() {
        address    = "";
        addresses  = new int[0][];
        breakCode  = -1;
        condition  = new int[0];
        errStatus  = new int[10];
        expression = "";
        name       = "";
    }

    // Cloning constructor
    Breakpoint(Breakpoint template) {
        address    = template.address;
        addresses  = template.getAddresses();
        condition  = copy(template.condition);
        breakCode  = template.breakCode;
        enabled    = template.enabled;
        errStatus  = copy(template.errStatus);
        expression = template.expression;
        events     = template.events;
        name       = template.name;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Evaluate the condition given an emulation state context and event
    public int[] evaluate(VUE vue) {

        // Determine state information
        int         events = this.events;
        int         pc     = vue.getProgramCounter();
        Stack       stack  = new Stack();
        Instruction inst   = new Instruction(
            vue.read(pc    , VUE.U16, true),
            vue.read(pc + 2, VUE.U16, true)
        );

        // Restrict event mask
        switch (inst.id) {
            case VUE.IN_B: case VUE.IN_H: case VUE.IN_W:
            case VUE.LD_B: case VUE.LD_H: case VUE.LD_W:
            case VUE.CAXI:
                events &= ~Breakpoint.WRITE;
                break;
            case VUE.OUT_B: case VUE.OUT_H: case VUE.OUT_W:
            case VUE.ST_B : case VUE.ST_H : case VUE.ST_W:
                events &= ~Breakpoint.READ;
        }
        if (vue.getExceptionCode() == 0)
            events &= ~Breakpoint.EXCEPTION;

        // Evaluate the breakpoint
        if (!evaluate(vue, events, inst, stack))
            return new int[] { BOOL, 0 };

        // Return the stack contents
        if (condition.length == 0)
            return new int[] { BOOL, 1 };
        int[] ret = new int[2];
        System.arraycopy(stack.pop(), 0, ret, 0, 2);
        return ret;
    }

    // Retrieve the address expression
    public String getAddress() {
        return address;
    }

    // Produce a deep copy of the breakpoint's address ranges
    public int[][] getAddresses() {
        int[][] ret = new int[addresses.length][];
        for (int x = 0; x < addresses.length; x++)
            ret[x] = copy(addresses[x]);
        return ret;
    }

    // Retrieve the break code
    public int getBreakCode() {
        return breakCode;
    }

    // Retrieve the condition expression
    public String getCondition() {
        return expression;
    }

    // Retrieve an error property
    public int getError(int type, int prop) {
        return (
            type < ADDRESS || type > CONDITION ||
            prop < CODE    || prop > LENGTH
        ) ? 0 : errStatus[type * 5 + prop];
    }

    // Retrieve the text that caused the last parse error
    public String getErrorText(int type) {

        // No text to retrieve
        if (
            type < ADDRESS || type > CONDITION || // Invalid type
            getError(type, CODE) == NOERROR       // No error
        ) return null;

        // Retrieve the offending text
        String text = type == ADDRESS ? address : expression;
        int    pos  = getError(type, POSITION);
        int    len  = getError(type, LENGTH);
        return text.substring(pos, pos + len);
    }

    // Retrieve the source event bit mask
    public int getEvents() {
        return events;
    }

    // Retrieve the display text
    public String getName() {
        return name;
    }

    // Retrieve the enabled status
    public boolean isEnabled() {
        return enabled;
    }

    // Determine whether the breakpoint will always succeed
    public boolean isUnconditional() {
        return addresses.length == 0 && condition.length == 0;
    }

    // Determine whether the breakpoint is ready to use
    public boolean isValid() {
        return
            getError(ADDRESS  , CODE) == NOERROR &&
            getError(CONDITION, CODE) == NOERROR
        ;
    }

    // Specify a new user-supplied address expression
    // Returns true if the expression is successfully parsed
    public boolean setAddress(String expression) {

        // Error checking
        if (expression == null)
            expression = "";

        // Parse the expression
        address   = expression;
        addresses = Token.parseAddress(expression, errStatus);
        return getError(ADDRESS, CODE) == NOERROR;
    }

    // Specify  new user-supplied address ranges as integers
    public void setAddress(int[][] ranges) {

        // Error checking
        if (ranges == null)
            ranges = new int[0][];

        // Count the number of valid address ranges
        int count = 0;
        for (int y = 0; y < ranges.length; y++) {
            int[] range = ranges[y];
            if (range != null && (range.length == 1 || range.length == 2))
                count++;
        }

        // Copy the valid ranges into breakpoint state
        addresses = new int[count][];
        count     = 0;
        address   = "";
        for (int y = 0; y < ranges.length; y++) {
            int[] range = ranges[y];
            if (range == null || range.length != 1 && range.length != 2)
                continue;

            // Output delimiter
            if (count > 0)
                address += ", ";

            // Output range
            int[] rng = addresses[count++] = new int[range.length];
            for (int x = 0; x < range.length; x++) {
                if (x > 0)
                    address += "-";
                rng[x] = range[x];
                address += String.format("%08X", range[x]);
            } // x

        } // y

    }

    // Specify a new break code
    public boolean setBreakCode(int breakCode) {
        if (breakCode == 0)
            return false;
        this.breakCode = breakCode;
        return true;
    }

    // Specify a new user-supplied condition expression
    // Returns true if the expression is successfully parsed
    public boolean setCondition(String expression) {

        // Error checking
        if (expression == null)
            expression = "";

        // Parse the expression
        this.expression = expression;
        condition       = Token.parseCondition(expression, errStatus);
        return getError(CONDITION, CODE) == NOERROR;
    }

    // Enable or disable the breakpoint
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    // Specify a new source event bit mask
    public void setEvents(int flags) {
        events = flags & 0x1F;
    }

    // Specify a new display name
    public void setName(String name) {
        this.name = name == null ? "" : name;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Make a deep copy of an array of breakpoints
    static Breakpoint[] cloneAll(Breakpoint[] breakpoints) {
        Breakpoint[] ret = new Breakpoint[breakpoints.length];
        for (int x = 0; x < breakpoints.length; x++)
            ret[x] = new Breakpoint(breakpoints[x]);
        return ret;
    }

    // Evaluate the condition given an emulation state context and event
    boolean evaluate(VUE vue, int event, Instruction inst, Stack stack) {

        // Check if the breakpoint should be considered
        if (
            !isValid() ||          // Breakpoint has an error
            !enabled   ||          // Breakpoint disabled
            (event &= events) == 0 // Event not captured
        ) return false;

        // Determine access size in bytes
        int size = 4;
        if (inst != null) switch (inst.id) {
            case VUE.IN_B: case VUE.LD_B: case VUE.OUT_B: case VUE.ST_B:
                size = 1; break;
            case VUE.IN_H: case VUE.LD_H: case VUE.OUT_H: case VUE.ST_H:
                size = 2;
        }

        // Check the breakpoint's addresses
        if (!((event & FRAME) != 0 || // Display clock

            // Load/store effective address
            inst != null                  && // Instruction is required
            (event & (READ | WRITE)) != 0 && // Read or write breakpoint
            inst.format == 6              && // Load/store instruction
            atAddress(vue.getProgramRegister(
                inst.operands[1]) + inst.operands[2], size)
        ||
            // Program counter
            (event & (EXECUTE | EXCEPTION)) != 0 && // Execution address
            atAddress(vue.getProgramCounter(), 1)

        )) return false; // Evaluation did not occur

        // Evaluate the breakpoint
        evaluate(vue, inst, stack);
        return true;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Interpret integer bits as a float
    private static float asFloat(int value) {
        return isReserved(value) || value == 0x80000000 ?
            0 : Float.intBitsToFloat(value);
    }

    // Represent a float as integer bits
    private static int asInteger(float value) {
        int ret = Float.floatToIntBits(value);
        return isReserved(ret) ? 0 : ret;
    }

    // Determine whether an address is captured by this breakpoint
    private boolean atAddress(int address, int size) {

        // Captures all addresses
        if (addresses.length == 0)
            return true;

        // Determine the range of addresses being accessed
        int start1 = address & ~(size - 1);
        int end1   = start1 + size - 1;

        // Compare with all address ranges
        for (int x = 0; x < addresses.length; x++) {
            int[] range  = addresses[x];

            // Determine the bounds of the range
            int start2 = range[0];
            int end2   = range.length == 1 ? start2 : range[1];

            // Check whether the ranges overlap
            if (
                Integer.compareUnsigned(start2 - start1, end1 - start1) <= 0 ||
                Integer.compareUnsigned(start1 - start2, end2 - start2) <= 0
            ) return true;
        }

        // Does not capture the given address
        return false;
    }

    // Create a clone of an int array
    private static int[] copy(int[] src) {
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    // Retrieve an error property for the last condition parse operation
    private int getErrorProperty(int prop) {
        return condition.length == 0 || condition[0] != ERROR ?
            NOERROR : condition[prop];
    }

    // Determine the maximum stack height for the current condition
    private int getHeight() {
        int ret = 0;

        // No condition to evaluate
        if (condition.length == 0)
            return 0;

        // Measure the highest point in the stack
        for (int x = 0, height = 0; x < condition.length; x++)
            ret = Math.max(ret, height +=
                condition[x] <  5 ? 1 : // Value
                condition[x] < 17 ? 0 : // Unary operation
            -1);                        // Binary operation

        return ret;
    }

    // Determine the maximum stack height for an array of breakpoints
    private static int getHeight(Breakpoint[] breakpoints) {
        int ret = 0;
        for (int x = 0; x < breakpoints.length; x++) 
            ret = Math.max(ret, breakpoints[x].getHeight());
        return ret;
    }

    // Determine whether float bits represent a reserved value
    private static boolean isReserved(int bits) {
        int exp = bits >> 23 & 0xFF;
        return exp == 0xFF || exp == 0x00 && (bits & 0x007FFFFF) != 0;
    }

    // Determine whether a value is truthy
    private static boolean isTrue(int[] item) {
        return item[0] == FLOAT && isReserved(item[1]) ? false : item[1] != 0;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                          Evaluation Methods                           //
    ///////////////////////////////////////////////////////////////////////////

    // Process a binary operation
    private void doBinary(VUE vue, Stack stack, int cmd) {
        int[] right = stack.pop();
        int[] left  = stack.pop();

        // Type restrictions
        switch (cmd) {
            case SHIFTLEFT : case SHIFTRIGHTA: case SHIFTRIGHTL:
            case BITWISEAND: case BITWISEXOR : case BITWISEOR  :
                left[0] = right[0] = INT;
        }

        // Integer operations
        if (left[0] != FLOAT && right[0] != FLOAT) {
            int b = left[0] == BOOL && right[0] == BOOL ? BOOL : INT;
            int l = left [1];
            int r = right[1];
            switch (cmd) {
                case DIVIDE      : stack.push(INT , r == 0 ? 0 : l / r); break;
                case MULTIPLY    : stack.push(INT , l * r             ); break;
                case REMAINDER   : stack.push(INT , r == 0 ? 0 : l % r); break;
                case ADD         : stack.push(INT , l + r             ); break;
                case SUBTRACT    : stack.push(INT , l - r             ); break;
                case SHIFTLEFT   : stack.push(INT , l <<  (r & 31)    ); break;
                case SHIFTRIGHTA : stack.push(INT , l >>  (r & 31)    ); break;
                case SHIFTRIGHTL : stack.push(INT , l >>> (r & 31)    ); break;
                case GREATER     : stack.push(BOOL, l >  r ? 1 : 0    ); break;
                case GREATEREQUAL: stack.push(BOOL, l >= r ? 1 : 0    ); break;
                case LESS        : stack.push(BOOL, l <  r ? 1 : 0    ); break;
                case LESSEQUAL   : stack.push(BOOL, r <= r ? 1 : 0    ); break;
                case EQUAL       : stack.push(BOOL, l == r ? 1 : 0    ); break;
                case NOTEQUAL    : stack.push(BOOL, l != r ? 1 : 0    ); break;
                case BITWISEAND  : stack.push(INT , l & r             ); break;
                case BITWISEXOR  : stack.push(INT , l ^ r             ); break;
                case BITWISEOR   : stack.push(INT , l | r             ); break;
                case LOGICALAND  : stack.push(b   , l == 0 ? l : r    ); break;
                case LOGICALOR   : stack.push(b   , l != 0 ? l : r    ); break;
                case GREATERU:
                    stack.push(BOOL, Integer.compareUnsigned(l, r) >  0 ?1:0);
                    break;
                case GREATEREQUALU:
                    stack.push(BOOL, Integer.compareUnsigned(l, r) >= 0 ?1:0);
                    break;
                case LESSU:
                    stack.push(BOOL, Integer.compareUnsigned(l, r) <  0 ?1:0);
                    break;
                case LESSEQUALU:
                    stack.push(BOOL, Integer.compareUnsigned(l, r) <= 0 ?1:0);
                    break;
                case LOGICALXOR:
                    boolean L = l != 0, R = r != 0;
                    stack.push(b, L == R ? 0 : L ? l : r);
            }
        }

        // Float operations
        else {
            float l = left [0] == FLOAT ? asFloat(left [1]) : (float) left [1];
            float r = right[0] == FLOAT ? asFloat(right[1]) : (float) right[1];
            switch (cmd) {
                case DIVIDE      : stack.push(FLOAT, r==0 ? 0.0f : l/r); break;
                case MULTIPLY    : stack.push(FLOAT, l * r            ); break;
                case REMAINDER   : stack.push(FLOAT, r==0 ? 0.0f : l%r); break;
                case ADD         : stack.push(FLOAT, l + r            ); break;
                case SUBTRACT    : stack.push(FLOAT, l - r            ); break;
                case EQUAL       : stack.push(BOOL , l == r ? 1 : 0   ); break;
                case NOTEQUAL    : stack.push(BOOL , l != r ? 1 : 0   ); break;
                case LOGICALAND  : stack.push(FLOAT, l == 0 ? l : r   ); break;
                case LOGICALOR   : stack.push(FLOAT, l != 0 ? l : r   ); break;
                case GREATER: case GREATERU:
                    stack.push(BOOL, l > r ? 1 : 0);
                    break;
                case GREATEREQUAL: case GREATEREQUALU:
                    stack.push(BOOL, l >= r ? 1 : 0);
                    break;
                case LESS: case LESSU:
                    stack.push(BOOL, l <  r ? 1 : 0);
                    break;
                case LESSEQUAL: case LESSEQUALU:
                    stack.push(BOOL, r <= r ? 1 : 0);
                    break;
                case LOGICALXOR:
                    boolean L = l != 0, R = r != 0;
                    stack.push(FLOAT, L == R ? 0.0f : L ? l : r);
            }
        }

    }

    // Process a unary operation
    private void doUnary(VUE vue, Stack stack, int cmd) {
        int[] right = stack.pop();

        // Type restrictions
        if (right[0] == BOOL || cmd == BITWISENOT || cmd == BUSREAD)
            right[0] = INT;

        // Integer operations
        if (right[0] == INT) {
            int r = right[1];
            switch (cmd) {
                case BITWISENOT: stack.push(INT  , ~r            ); break;
                case NEGATE    : stack.push(INT  , -r            ); break;
                case LOGICALNOT: stack.push(BOOL , r == 0 ? 1 : 0); break;
                case CFLOAT    : stack.push(FLOAT, (float) r     ); break;
                case XFLOAT    : stack.push(FLOAT, asFloat(r)    ); break;
                case BUSREAD   : stack.push(INT, vue.read(r,
                    (r & 3) == 0 ? VUE.S32 :
                    (r & 1) == 0 ? VUE.U16 : VUE.U8, true));
            }
        }

        // Float operations
        else if (right[0] == FLOAT) {
            float r = asFloat(right[1]);
            switch (cmd) {
                case NEGATE:     stack.push(FLOAT, -r            ); break;
                case LOGICALNOT: stack.push(BOOL , r == 0 ? 1 : 0); break;
                case CINT:       stack.push(INT,(int)Math.round(r));break;
                case XINT:       stack.push(INT  , r             ); break;
                case FIX:        stack.push(FLOAT, r - r % 1     ); break;
                case FLOOR:      stack.push(FLOAT, Math.floor(r) ); break;
                case ROUND:      stack.push(FLOAT, Math.round(r) ); break;
                case CEIL:       stack.push(FLOAT, Math.ceil (r) );
            }
        }

    }

    // Retrieve an operand by ID from an instruction
    private int getOperand(VUE vue, Instruction inst, int id) {

        // Processing by operand
        outer: switch (id) {
            case FORMAT: return inst.format;
            case INST  : return inst.id;
            case OPCODE: return inst.opcode;
            case SIZE  : return inst.size;

            // Target address for Bcond, JAL, JMP, JR, RETI, TRAP and format VI
            case ADDRESS:
                switch (inst.id) {
                    case VUE.BCOND:
                        return vue.getProgramCounter() + inst.operands[1];
                    case VUE.JAL: case VUE.JR:
                        return vue.getProgramCounter() + inst.operands[0];
                    case VUE.JMP:
                        return vue.getProgramRegister(inst.operands[1]);
                    case VUE.RETI:
                        return vue.getSystemRegister(0 != (0x00008000 &
                            vue.getSystemRegister(VUE.PSW))?VUE.FEPC:VUE.EIPC);
                    case VUE.TRAP:
                        return 0xFFFFFFA0 + inst.operands[1] & 15;
                }
                if (inst.format == 6)
                    return vue.getProgramRegister(inst.operands[1]) +
                        inst.operands[2];
                break;

            // Condition for Bcond and SETF
            case COND:
                switch (inst.id) {
                    case VUE.BCOND: return inst.operands[0];
                    case VUE.SETF : return inst.operands[1];
                }
                break;

            // Displacement for formats III, IV and VI
            case DISP:
                switch (inst.format) {
                    case 3: return inst.operands[1];
                    case 4: return inst.operands[0];
                    case 6: return inst.operands[2];
                }
                break;

            // Immediate for all format II except bit string, LDSR, SETF, STSR
            // and TRAP, and for all format V
            case IMM:
                if (inst.opcode == 0x1F)
                    break outer;
                switch (inst.id) {
                    case VUE.LDSR: case VUE.SETF: case VUE.STSR: case VUE.TRAP:
                        break outer;
                }
                switch (inst.format) {
                    case 2: return inst.operands[1];
                    case 5: return inst.operands[2];
                }
                break;

            // Reg1 for formats I, V, VI and VII
            case REG1:
                switch (inst.format) {
                    case 1: case 5: case 6: case 7:
                        return inst.operands[1];
                }
                break;

            // Reg2 for formats I, II, V, VI and VII
            case REG2:
                switch (inst.format) {
                    case 1: case 2: case 5: case 6: case 7:
                        return inst.operands[0];
                }
                break;

            // RegID for LDSR and STSR
            case REGID:
                switch (inst.id) {
                    case VUE.LDSR: case VUE.STSR:
                        return inst.operands[1];
                }
                break;

            // Sub-opcode for bit string and floating-point
            case SUBOPCODE:
                switch (inst.opcode) {
                    case 0x1F: return inst.operands[1];
                    case 0x3E: return inst.operands[2];
                }
                break;

            // Vector for TRAP
            case VECTOR:
                if (inst.id == VUE.TRAP)
                    return inst.operands[1];
                break;

            // Code for exceptions
            case EXCODE:
                return vue.getExceptionCode();

            // Value for load/store operations
            case VALUE:
                switch (inst.id) {

                    // Input and load
                    case VUE.IN_B: case VUE.IN_H: case VUE.IN_W:
                    case VUE.LD_B: case VUE.LD_H: case VUE.LD_W:
                    case VUE.CAXI:
                        return vue.read(
                            inst.operands[2] +
                                vue.getProgramRegister(inst.operands[1]),
                            inst.id == VUE.IN_B ? VUE.U8  :
                            inst.id == VUE.IN_H ? VUE.U16 :
                            inst.id == VUE.LD_B ? VUE.S8  :
                            inst.id == VUE.LD_H ? VUE.S16 :
                            VUE.S32,
                            true
                        );

                    // Output and store
                    case VUE.OUT_B: case VUE.OUT_H: case VUE.OUT_W:
                    case VUE.ST_B : case VUE.ST_H : case VUE.ST_W :
                        return vue.getProgramRegister(inst.operands[0]);
                }
                break;
        }

        // Default value
        return 0;
    }

    // Evaluate the condition given an emulation state context
    private void evaluate(VUE vue, Instruction inst, Stack stack) {

        // Unconditional capture
        stack.clear();
        if (condition.length == 0)
            return;

        // Ensure an instruction object exists
        if (inst == null)
            inst = BLANKINST;

        // Check the condition
        for (int x = 0; x < condition.length; x++) {
            int cmd = condition[x];

            // Values
            if (cmd < UNARY) {
                int value = condition[++x];
                switch (cmd) {
                    case LITERALFLOAT: stack.push(FLOAT, value); break;
                    case LITERALINT  : stack.push(INT  , value); break;
                    case PROREG:
                        stack.push(INT, vue.getProgramRegister(value));
                        break;
                    case SYSREG:
                        stack.push(INT, value == PC ? vue.getProgramCounter() :
                            vue.getSystemRegister(value));
                        break;
                    case OPERAND:
                        stack.push(INT, getOperand(vue, inst, value));
                        break;
                }
            }

            // Unary operations
            else if (cmd < BINARY)
                doUnary(vue, stack, cmd);

            // Binary operations
            else doBinary(vue, stack, cmd);
        }

    }

}
