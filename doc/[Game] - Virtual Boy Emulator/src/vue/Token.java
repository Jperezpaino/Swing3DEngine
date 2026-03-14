package vue;
import vue.*;

// Breakpoint helper class--individual lexical element within an expression
class Token {

    // Instance fields
    private int     id;         // Identifier for token meaning
    private boolean isNode;     // Processed as tree node
    private Token   left;       // Left child node
    private Token   parent;     // Parent node
    private int     precedence; // For binary operators, precedence level
    private Token   right;      // Right child node
    private int     tag;        // Internal client variable
    private int     type;       // Lexical token type
    private int     value;      // Numeric value for value tokens



    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Types
    private static final int GROUPOPEN  = 0;
    private static final int GROUPCLOSE = 1;
    private static final int UNARY      = 2;
    private static final int BINARY     = 3;
    private static final int VALUE      = 4;
    private static final int ADDRESS    = 0;
    private static final int HYPHEN     = 1;
    private static final int COMMA      = 2;

    // Group open and group close IDs
    private static final int PRECEDENCE = 0;
    private static final int READ       = 1;

    // Symbol templates -- symbol, type, ID, precedence or value
    private static final Object[][] TEMPLATES = {
        { "(", GROUPOPEN , PRECEDENCE },
        { ")", GROUPCLOSE, PRECEDENCE },
        { "[", GROUPOPEN , READ       },
        { "]", GROUPCLOSE, READ       },

        { "~"     , UNARY, Breakpoint.BITWISENOT },
        { "!"     , UNARY, Breakpoint.LOGICALNOT },
        { "float" , UNARY, Breakpoint.CFLOAT     },
        { "int"   , UNARY, Breakpoint.CINT       },
        { "xfloat", UNARY, Breakpoint.XFLOAT     },
        { "xint"  , UNARY, Breakpoint.XINT       },
        { "fix"   , UNARY, Breakpoint.FIX        },
        { "floor" , UNARY, Breakpoint.FLOOR      },
        { "round" , UNARY, Breakpoint.ROUND      },
        { "ceil"  , UNARY, Breakpoint.CEIL       },

        { "/"  , BINARY, Breakpoint.DIVIDE       ,  0 },
        { "*"  , BINARY, Breakpoint.MULTIPLY     ,  0 },
        { "%"  , BINARY, Breakpoint.REMAINDER    ,  0 },
        { "+"  , BINARY, Breakpoint.ADD          ,  1 },
        { "<<" , BINARY, Breakpoint.SHIFTLEFT    ,  2 },
        { ">>" , BINARY, Breakpoint.SHIFTRIGHTA  ,  2 },
        { ">>>", BINARY, Breakpoint.SHIFTRIGHTL  ,  2 },
        { ">"  , BINARY, Breakpoint.GREATER      ,  3 },
        { ">=" , BINARY, Breakpoint.GREATEREQUAL ,  3 },
        { ">_" , BINARY, Breakpoint.GREATERU     ,  3 },
        { ">=_", BINARY, Breakpoint.GREATEREQUALU,  3 },
        { "<"  , BINARY, Breakpoint.LESS         ,  3 },
        { "<=" , BINARY, Breakpoint.LESSEQUAL    ,  3 },
        { "<_" , BINARY, Breakpoint.LESSU        ,  3 },
        { "<=_", BINARY, Breakpoint.LESSEQUALU   ,  3 },
        { "==" , BINARY, Breakpoint.EQUAL        ,  4 },
        { "!=" , BINARY, Breakpoint.NOTEQUAL     ,  4 },
        { "&"  , BINARY, Breakpoint.BITWISEAND   ,  5 },
        { "^"  , BINARY, Breakpoint.BITWISEXOR   ,  6 },
        { "|"  , BINARY, Breakpoint.BITWISEOR    ,  7 },
        { "&&" , BINARY, Breakpoint.LOGICALAND   ,  8 },
        { "^^" , BINARY, Breakpoint.LOGICALXOR   ,  9 },
        { "||" , BINARY, Breakpoint.LOGICALOR    , 10 },

        { "r0" , VALUE, Breakpoint.PROREG,  0     },
        { "r1" , VALUE, Breakpoint.PROREG,  1     },
        { "r2" , VALUE, Breakpoint.PROREG,  2     },
        { "r3" , VALUE, Breakpoint.PROREG,  3     },
        { "r4" , VALUE, Breakpoint.PROREG,  4     },
        { "r5" , VALUE, Breakpoint.PROREG,  5     },
        { "r6" , VALUE, Breakpoint.PROREG,  6     },
        { "r7" , VALUE, Breakpoint.PROREG,  7     },
        { "r8" , VALUE, Breakpoint.PROREG,  8     },
        { "r9" , VALUE, Breakpoint.PROREG,  9     },
        { "r10", VALUE, Breakpoint.PROREG, 10     },
        { "r11", VALUE, Breakpoint.PROREG, 11     },
        { "r12", VALUE, Breakpoint.PROREG, 12     },
        { "r13", VALUE, Breakpoint.PROREG, 13     },
        { "r14", VALUE, Breakpoint.PROREG, 14     },
        { "r15", VALUE, Breakpoint.PROREG, 15     },
        { "r16", VALUE, Breakpoint.PROREG, 16     },
        { "r17", VALUE, Breakpoint.PROREG, 17     },
        { "r18", VALUE, Breakpoint.PROREG, 18     },
        { "r19", VALUE, Breakpoint.PROREG, 19     },
        { "r20", VALUE, Breakpoint.PROREG, 20     },
        { "r21", VALUE, Breakpoint.PROREG, 21     },
        { "r22", VALUE, Breakpoint.PROREG, 22     },
        { "r23", VALUE, Breakpoint.PROREG, 23     },
        { "r24", VALUE, Breakpoint.PROREG, 24     },
        { "r25", VALUE, Breakpoint.PROREG, 25     },
        { "r26", VALUE, Breakpoint.PROREG, 26     },
        { "r27", VALUE, Breakpoint.PROREG, 27     },
        { "r28", VALUE, Breakpoint.PROREG, 28     },
        { "r29", VALUE, Breakpoint.PROREG, 29     },
        { "r30", VALUE, Breakpoint.PROREG, 30     },
        { "r31", VALUE, Breakpoint.PROREG, 31     },
        { "hp" , VALUE, Breakpoint.PROREG, VUE.HP },
        { "sp" , VALUE, Breakpoint.PROREG, VUE.SP },
        { "gp" , VALUE, Breakpoint.PROREG, VUE.GP },
        { "tp" , VALUE, Breakpoint.PROREG, VUE.TP },
        { "lp" , VALUE, Breakpoint.PROREG, VUE.LP },

        { "adtre", VALUE, Breakpoint.SYSREG, VUE.ADTRE     },
        { "chcw" , VALUE, Breakpoint.SYSREG, VUE.CHCW      },
        { "ecr"  , VALUE, Breakpoint.SYSREG, VUE.ECR       },
        { "eipc" , VALUE, Breakpoint.SYSREG, VUE.EIPC      },
        { "eipsw", VALUE, Breakpoint.SYSREG, VUE.EIPSW     },
        { "fepc" , VALUE, Breakpoint.SYSREG, VUE.FEPC      },
        { "fepsw", VALUE, Breakpoint.SYSREG, VUE.FEPSW     },
        { "pc"   , VALUE, Breakpoint.SYSREG, Breakpoint.PC },
        { "pir"  , VALUE, Breakpoint.SYSREG, VUE.PIR       },
        { "psw"  , VALUE, Breakpoint.SYSREG, VUE.PSW       },
        { "sr29" , VALUE, Breakpoint.SYSREG, 29            },
        { "sr30" , VALUE, Breakpoint.SYSREG, 30            },
        { "sr31" , VALUE, Breakpoint.SYSREG, 31            },
        { "tkcw" , VALUE, Breakpoint.SYSREG, VUE.TKCW      },

        { "address"  , VALUE, Breakpoint.OPERAND, Breakpoint.ADDRESS   },
        { "cond"     , VALUE, Breakpoint.OPERAND, Breakpoint.COND      },
        { "disp"     , VALUE, Breakpoint.OPERAND, Breakpoint.DISP      },
        { "format"   , VALUE, Breakpoint.OPERAND, Breakpoint.FORMAT    },
        { "imm"      , VALUE, Breakpoint.OPERAND, Breakpoint.IMM       },
        { "inst"     , VALUE, Breakpoint.OPERAND, Breakpoint.INST      },
        { "opcode"   , VALUE, Breakpoint.OPERAND, Breakpoint.OPCODE    },
        { "reg1"     , VALUE, Breakpoint.OPERAND, Breakpoint.REG1      },
        { "reg2"     , VALUE, Breakpoint.OPERAND, Breakpoint.REG2      },
        { "regid"    , VALUE, Breakpoint.OPERAND, Breakpoint.REGID     },
        { "size"     , VALUE, Breakpoint.OPERAND, Breakpoint.SIZE      },
        { "subopcode", VALUE, Breakpoint.OPERAND, Breakpoint.SUBOPCODE },
        { "vector"   , VALUE, Breakpoint.OPERAND, Breakpoint.VECTOR    },
        { "code"     , VALUE, Breakpoint.OPERAND, Breakpoint.EXCODE    },
        { "value"    , VALUE, Breakpoint.OPERAND, Breakpoint.VALUE     },

        { "add_imm", VALUE, Breakpoint.LITERALINT, VUE.ADD_IMM },
        { "add_reg", VALUE, Breakpoint.LITERALINT, VUE.ADD_REG },
        { "addf.s" , VALUE, Breakpoint.LITERALINT, VUE.ADDF_S  },
        { "addi"   , VALUE, Breakpoint.LITERALINT, VUE.ADDI    },
        { "and"    , VALUE, Breakpoint.LITERALINT, VUE.AND     },
        { "andbsu" , VALUE, Breakpoint.LITERALINT, VUE.ANDBSU  },
        { "andi"   , VALUE, Breakpoint.LITERALINT, VUE.ANDI    },
        { "andnbsu", VALUE, Breakpoint.LITERALINT, VUE.ANDNBSU },
        { "bcond"  , VALUE, Breakpoint.LITERALINT, VUE.BCOND   },
        { "caxi"   , VALUE, Breakpoint.LITERALINT, VUE.CAXI    },
        { "cli"    , VALUE, Breakpoint.LITERALINT, VUE.CLI     },
        { "cmp_imm", VALUE, Breakpoint.LITERALINT, VUE.CMP_IMM },
        { "cmp_reg", VALUE, Breakpoint.LITERALINT, VUE.CMP_REG },
        { "cmpf.s" , VALUE, Breakpoint.LITERALINT, VUE.CMPF_S  },
        { "cvt.sw" , VALUE, Breakpoint.LITERALINT, VUE.CVT_SW  },
        { "cvt.ws" , VALUE, Breakpoint.LITERALINT, VUE.CVT_WS  },
        { "div"    , VALUE, Breakpoint.LITERALINT, VUE.DIV     },
        { "divf.s" , VALUE, Breakpoint.LITERALINT, VUE.DIVF_S  },
        { "divu"   , VALUE, Breakpoint.LITERALINT, VUE.DIVU    },
        { "halt"   , VALUE, Breakpoint.LITERALINT, VUE.HALT    },
        { "illegal", VALUE, Breakpoint.LITERALINT, VUE.ILLEGAL },
        { "in.b"   , VALUE, Breakpoint.LITERALINT, VUE.IN_B    },
        { "in.h"   , VALUE, Breakpoint.LITERALINT, VUE.IN_H    },
        { "in.w"   , VALUE, Breakpoint.LITERALINT, VUE.IN_W    },
        { "jal"    , VALUE, Breakpoint.LITERALINT, VUE.JAL     },
        { "jmp"    , VALUE, Breakpoint.LITERALINT, VUE.JMP     },
        { "jr"     , VALUE, Breakpoint.LITERALINT, VUE.JR      },
        { "ld.b"   , VALUE, Breakpoint.LITERALINT, VUE.LD_B    },
        { "ld.h"   , VALUE, Breakpoint.LITERALINT, VUE.LD_H    },
        { "ld.w"   , VALUE, Breakpoint.LITERALINT, VUE.LD_W    },
        { "ldsr"   , VALUE, Breakpoint.LITERALINT, VUE.LDSR    },
        { "mov_imm", VALUE, Breakpoint.LITERALINT, VUE.MOV_IMM },
        { "mov_reg", VALUE, Breakpoint.LITERALINT, VUE.MOV_REG },
        { "movbsu" , VALUE, Breakpoint.LITERALINT, VUE.MOVBSU  },
        { "movea"  , VALUE, Breakpoint.LITERALINT, VUE.MOVEA   },
        { "movhi"  , VALUE, Breakpoint.LITERALINT, VUE.MOVHI   },
        { "mpyhw"  , VALUE, Breakpoint.LITERALINT, VUE.MPYHW   },
        { "mul"    , VALUE, Breakpoint.LITERALINT, VUE.MUL     },
        { "mulf.s" , VALUE, Breakpoint.LITERALINT, VUE.MULF_S  },
        { "mulu"   , VALUE, Breakpoint.LITERALINT, VUE.MULU    },
        { "not"    , VALUE, Breakpoint.LITERALINT, VUE.NOT     },
        { "or"     , VALUE, Breakpoint.LITERALINT, VUE.OR      },
        { "orbsu"  , VALUE, Breakpoint.LITERALINT, VUE.ORBSU   },
        { "ori"    , VALUE, Breakpoint.LITERALINT, VUE.ORI     },
        { "ornbsu" , VALUE, Breakpoint.LITERALINT, VUE.ORNBSU  },
        { "out.b"  , VALUE, Breakpoint.LITERALINT, VUE.OUT_B   },
        { "out.h"  , VALUE, Breakpoint.LITERALINT, VUE.OUT_H   },
        { "out.w"  , VALUE, Breakpoint.LITERALINT, VUE.OUT_W   },
        { "reti"   , VALUE, Breakpoint.LITERALINT, VUE.RETI    },
        { "rev"    , VALUE, Breakpoint.LITERALINT, VUE.REV     },
        { "sar_imm", VALUE, Breakpoint.LITERALINT, VUE.SAR_IMM },
        { "sar_reg", VALUE, Breakpoint.LITERALINT, VUE.SAR_REG },
        { "sch0bsd", VALUE, Breakpoint.LITERALINT, VUE.SCH0BSD },
        { "sch0bsu", VALUE, Breakpoint.LITERALINT, VUE.SCH0BSU },
        { "sch1bsd", VALUE, Breakpoint.LITERALINT, VUE.SCH1BSD },
        { "sch1bsu", VALUE, Breakpoint.LITERALINT, VUE.SCH1BSU },
        { "sei"    , VALUE, Breakpoint.LITERALINT, VUE.SEI     },
        { "setf"   , VALUE, Breakpoint.LITERALINT, VUE.SETF    },
        { "shl_imm", VALUE, Breakpoint.LITERALINT, VUE.SHL_IMM },
        { "shl_reg", VALUE, Breakpoint.LITERALINT, VUE.SHL_REG },
        { "shr_imm", VALUE, Breakpoint.LITERALINT, VUE.SHR_IMM },
        { "shr_reg", VALUE, Breakpoint.LITERALINT, VUE.SHR_REG },
        { "st.b"   , VALUE, Breakpoint.LITERALINT, VUE.ST_B    },
        { "st.h"   , VALUE, Breakpoint.LITERALINT, VUE.ST_H    },
        { "st.w"   , VALUE, Breakpoint.LITERALINT, VUE.ST_W    },
        { "stsr"   , VALUE, Breakpoint.LITERALINT, VUE.STSR    },
        { "sub"    , VALUE, Breakpoint.LITERALINT, VUE.SUB     },
        { "subf.s" , VALUE, Breakpoint.LITERALINT, VUE.SUBF_S  },
        { "trap"   , VALUE, Breakpoint.LITERALINT, VUE.TRAP    },
        { "trnc.sw", VALUE, Breakpoint.LITERALINT, VUE.TRNC_SW },
        { "xb"     , VALUE, Breakpoint.LITERALINT, VUE.XB      },
        { "xh"     , VALUE, Breakpoint.LITERALINT, VUE.XH      },
        { "xor"    , VALUE, Breakpoint.LITERALINT, VUE.XOR     },
        { "xorbsu" , VALUE, Breakpoint.LITERALINT, VUE.XORBSU  },
        { "xori"   , VALUE, Breakpoint.LITERALINT, VUE.XORI    },
        { "xornbsu", VALUE, Breakpoint.LITERALINT, VUE.XORNBSU }
    };

    // Miscellaneous templates
    private static final Object[] BUSREAD  =
        { "[]", UNARY , Breakpoint.BUSREAD     };
    private static final Object[] NEGATE   =
        { "-" , UNARY , Breakpoint.NEGATE      };
    private static final Object[] SUBTRACT =
        { "-" , BINARY, Breakpoint.SUBTRACT, 1 };



    ///////////////////////////////////////////////////////////////////////////
    //                            TokenList Class                            //
    ///////////////////////////////////////////////////////////////////////////

    // Used in place of java.util.ArrayList so that java.util is not needed

    // ArrayList-like class for managing collections of Token objects
    private static class TokenList {

        // Instance fields
        private Token[] items;  // Collection items
        private int     length; // Number of items in collection

        // Object constructor
        TokenList() {
            items  = new Token[16];
            length =  0;
        }

        // Add an item
        void add(Token token) {

            // Reallocate list of items to hold more elements
            if (length == items.length) {
                Token[] items = new Token[this.items.length << 1];
                System.arraycopy(this.items, 0, items, 0, length);
                this.items    = items;
            }

            // Add the item to the list
            this.items[length++] = token;
        }

        // Retrieve an item from the specified index
        Token get(int index) {
            return index >= 0 && index < length ? items[index] : null;
        }

        // Remove an item at a given index
        void remove(int index) {
            if (index >= 0 && index < length)
                System.arraycopy(items, index+1, items, index, --length-index);
        }

        // Specify a new item at a given index
        void set(int index, Token token) {
            if (index >= 0 && index < length)
                items[index] = token;
        }

        // Retrieve the number of items in the collection
        int size() {
            return length;
        }

    }



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Default constructor
    private Token(int type, int id, int third) {
        this.id    = id;
        precedence = third;
        this.type  = type;
        value      = third;
    }

    // Template constructor
    private Token(Object[] template) {
        this((Integer) template[1], (Integer) template[2],
            template.length > 3 ? (Integer) template[3] : 0);
    }

    // Integer literal constructor
    private Token(int value) {
        this(VALUE, Breakpoint.LITERALINT, value);
    }

    // Long literal constructor
    private Token(long value) {
        this(VALUE, Breakpoint.LITERALINT, (int) value);
        if (value < -2147483648L || value > 4294967295L)
            throw new RuntimeException();
    }

    // Float literal constructor
    private Token(float value) {
        this(VALUE, Breakpoint.LITERALFLOAT, Float.floatToIntBits(value));
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Process a user-supplied address string into a list of address ranges
    static int[][] parseAddress(String input, int[] errStatus) {
        TokenList tokens = new TokenList();

        // Process the entire input string
        int line = 1;
        int col  = 1;
        int pos  = 0;
        while (pos < input.length()) {
            Token token = null;

            // Processing by next character
            switch (input.charAt(pos)) {

                // Whitespace
                case '\n':            line++; col = 0; // Fallthrough
                case ' ' : case '\t': col++;           // Fallthrough
                case '\r':            pos++; continue;

                // Minus and comma
                case '-': token = doDelimit(tokens, false); break;
                case ',': token = doDelimit(tokens, true ); break;

                // Numeral
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':
                case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
                case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
                    token = doAddress(tokens, input, pos);
                    break;

                // Other characters
                default:
                    token = new Token(Breakpoint.ERROR, Breakpoint.UNKNOWN, 1);
            }

            // Error checking
            if (token.type == Breakpoint.ERROR) {
                doError(errStatus, Breakpoint.ADDRESS,
                    token.id, line, col, pos, token.value);
                return new int[0][];
            }

            // Advance to the next token
            tokens.add(token);
            col += token.tag;
            pos += token.tag;
        } // pos

        // Input is empty
        if (tokens.size() == 0) {
            doError(errStatus, Breakpoint.ADDRESS,
                Breakpoint.NOERROR, line, col, pos, 0);
            return new int[0][];
        }

        // Error checking for last token
        if (followsAddress(tokens, ADDRESS)) {
            doError(errStatus, Breakpoint.ADDRESS,
                Breakpoint.BADEOF, line, col, pos, 0);
            return new int[0][];
        }

        // Token list is correct, so produce address range list
        doError(errStatus, Breakpoint.ADDRESS,
            Breakpoint.NOERROR, line, col, pos, 0);
        return doRanges(tokens);
    }

    // Process a user-supplied condition string into a list of commands
    static int[] parseCondition(String input, int[] errStatus) {
        TokenList tokens = new TokenList();

        // Process the entire input string
        int line = 1;
        int col  = 1;
        int pos  = 0;
        while (pos < input.length()) {
            Token token = null;

            // Processing by next character
            switch (input.charAt(pos)) {

                // Whitespace
                case '\n':            line++; col = 0; // Fallthrough
                case ' ' : case '\t': col++;           // Fallthrough
                case '\r':            pos++; continue;

                // Minus
                case '-':
                    token = doMinus(tokens);
                    break;

                // Numeral
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':
                    token = doLiteral(tokens, input, pos);
                    break;

                // Operator or named symbol
                default: token = doOther(tokens, input, pos);
            }

            // Error checking
            if (token.type == Breakpoint.ERROR) {
                doError(errStatus, Breakpoint.CONDITION,
                    token.id, line, col, pos, token.value);
                return new int[0];
            }

            // Advance to the next token
            tokens.add(token);
            col += token.tag;
            pos += token.tag;
        } // pos

        // Input is empty
        if (tokens.size() == 0) {
            doError(errStatus, Breakpoint.CONDITION,
                Breakpoint.NOERROR, line, col, pos, 0);
            return new int[0];
        }

        // Error checking for last token
        if (followsCondition(tokens, GROUPOPEN)) {
            doError(errStatus, Breakpoint.CONDITION,
                Breakpoint.BADEOF, line, col, pos, 0);
            return new int[0];
        }

        // Error checking for group nesting
        int depth = 0;
        for (int x = 0; x < tokens.length; x++)
        switch (tokens.get(x).type) {
            case GROUPCLOSE: depth--; break;
            case GROUPOPEN : depth++; break;
        }
        if (depth != 0) { // Not enough group close operators
            doError(errStatus, Breakpoint.CONDITION,
                Breakpoint.BADEOF, line, col, pos, 0);
            return new int[0];
        }

        // Token list is correct, so produce command list
        doError(errStatus, Breakpoint.CONDITION,
            Breakpoint.NOERROR, line, col, pos, 0);
        return doCommands(tokens);
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Processing for address
    private static Token doAddress(TokenList tokens, String input, int pos) {
        int length = 1;

        // Process input until an invalid character is encountered
        for (; pos + length < input.length(); length++) {
            char c = input.charAt(pos + length);
            if (!(
                c >= '0' && c <= '9' ||
                c >= 'A' && c <= 'F' ||
                c >= 'a' && c <= 'f'
            )) break;
        }

        // Error checking
        if (!followsAddress(tokens, ADDRESS))
            return new Token(Breakpoint.ERROR, Breakpoint.SYNTAX, length);

        // Parse the value and produce a new token
        String text = input.substring(pos, pos + length);
        try {
            Token token = new Token(Long.parseLong(text, 16));
            token.tag   = length;
            token.type  = ADDRESS;
            return token;
        } catch (Exception e) { }

        // Produce an error token
        return new Token(Breakpoint.ERROR, Breakpoint.LITERAL, length);
    }

    // Process a list of tokens into an RPN-ordered list of commands
    private static int[] doCommands(TokenList tokens) {
        int[] cmds = new int[0];
        Token root = doGroup(tokens, 0);

        // Traverse the tree twice, once to count commands, once for output
        for (int pass = 0; pass < 2; pass++) {
            int pos = 0;

            // Traverse the tree
            outer: for (Token token = root;;) {

                // Find the first leaf node from the current token
                if (token.left != null || token.right != null) {
                    token = token.left != null ? token.left : token.right;
                    continue;
                }

                // Output in postorder
                for (;;) {

                    // Output token
                    if (pass == 1) {
                        cmds[pos] = token.id;
                        if (token.type == VALUE)
                            cmds[pos + 1] = token.value;
                    }
                    pos += token.type == VALUE ? 2 : 1;

                    // The entire tree has been processed
                    if (token == root)
                        break outer;

                    // Traverse backwards
                    if (token == token.parent.left) {
                        token = token.parent.right;
                        continue outer;
                    }
                    token = token.parent;
                }
            }

            // Allocate output buffer
            if (pass == 0)
                cmds = new int[pos];
        }

        return cmds;
    }

    // Processing for delimiters
    private static Token doDelimit(TokenList tokens, boolean comma) {
        int type = comma ? COMMA : HYPHEN;
        if (!followsAddress(tokens, type))
            return new Token(Breakpoint.ERROR, Breakpoint.SYNTAX, 1);
        Token ret = new Token(type, 0, 0);
        ret.tag = 1;
        return ret;
    }

    // Error processing
    private static void doError(int[] errStatus, int type, int code,
        int line, int col, int pos, int length) {
        type *= 5;
        errStatus[type + Breakpoint.CODE    ] = code;
        errStatus[type + Breakpoint.LINE    ] = line;
        errStatus[type + Breakpoint.COLUMN  ] = col;
        errStatus[type + Breakpoint.POSITION] = pos;
        errStatus[type + Breakpoint.LENGTH  ] = length;
    }

    // Processing for token group
    private static Token doGroup(TokenList tokens, int index) {
        int     start     = index;
        int     end       = index;
        boolean up        = index==0 || tokens.get(index - 1).type==GROUPOPEN;
        int     stepStart = up ? 0 : -1;
        int     stepEnd   = up ? 1 :  0;
        int     stepIndex = up ? 1 : -1;

        // Process until only a node remains
        for (;;) {
            int binIndex = -1; // Index of binary operator

            // Locate the other side of the group and possible binary operator
            for (int depth = 0; index >= 0 && index < tokens.size();
                start += stepStart, end += stepEnd, index += stepIndex) {
                Token token = tokens.get(index);

                // Do not consider node tokens
                if (token.isNode)
                    continue;

                // Processing by token type
                switch (token.type) {
                    case GROUPCLOSE: depth -= stepIndex; break;
                    case GROUPOPEN : depth += stepIndex; break;
                    case BINARY:
                        int p = binIndex == -1 ? 0 :
                            token.precedence - tokens.get(binIndex).precedence;
                        if (depth == 0 && (binIndex == -1 || p < 0 ||
                            p == 0 && index < binIndex))
                            binIndex = index;
                        // Fallthrough
                    default: continue;
                }

                // The other side of the group has been reached
                if (depth < 0)
                    break;
            }
            start -= stepStart;
            end   -= stepEnd;

            // Group contains only one node
            if (start == end && tokens.get(start).isNode) {
                Token token = tokens.get(start);

                // Token is the root node
                if (start == 0)
                    return token;

                // Processing for read groups
                if (tokens.get(start - 1).id == READ) {
                    Token read    = new Token(BUSREAD);
                    read.isNode   = true;
                    read.right    = token;
                    tokens.set(start, token = token.parent = read);
                }

                // Remove group tokens
                tokens.remove(start + 1);
                tokens.remove(start - 1);
                token.tag = start - 1;
                return token;
            }

            // Processing for binary operator
            if (binIndex != -1) {
                Token token        = tokens.get(binIndex);
                token.isNode       = true;
                token.right        = doOperand(tokens, binIndex + 1, true);
                token.left         = doOperand(tokens, binIndex - 1, true);
                token.left .parent = token;
                token.right.parent = token;
            }

            // Processing for value
            else doOperand(tokens, end, false);

            // Scan the group again
            end = index = start;
            stepStart   = 0;
            stepIndex   = stepEnd = 1;
        } // Unconditional

    }

    // Processing for numeric literal
    private static Token doLiteral(TokenList tokens, String input, int pos) {
        boolean isFloat = false;
        boolean isHex   = false;
        int     length  = 1;

        // Process input until an invalid character is encountered
        outer: for (; pos + length < input.length(); length++)
        switch (input.charAt(pos + length)) {

            // Hexadecimal notation
            case 'x': case 'X':
                if (length != 1 || input.charAt(pos) != '0')
                    break outer;
                isHex = true;
                continue;

            // Floating-point notation
            case '.':
                if (isHex || isFloat)
                    break outer;
                isFloat = true;
                continue;

            // Decimal digit
            case '0': case '1': case '2': case '3': case '4':
            case '5': case '6': case '7': case '8': case '9':
                continue;

            // Hex digit
            case 'a': case 'b': case 'c': case 'd': case 'e': case 'f':
            case 'A': case 'B': case 'C': case 'D': case 'E': case 'F':
                if (!isHex)
                    break outer;
                continue;

            // Invalid character
            default: break outer;
        }

        // Error checking
        if (!followsCondition(tokens, VALUE))
            return new Token(Breakpoint.ERROR, Breakpoint.SYNTAX, length);

        // Parse the value and produce a new token
        String text = input.substring(pos + (isHex ? 2 : 0), pos + length);
        try {
            Token token = isFloat ? new Token(Float.parseFloat(text)) :
                new Token(Long.parseLong(text, isHex ? 16 : 10));
            token.tag   = length;
            return token;
        } catch (Exception e) { }

        // Produce an error token
        return new Token(Breakpoint.ERROR, Breakpoint.LITERAL, length);
    }

    // Processing for minus sign
    private static Token doMinus(TokenList tokens) {
        Token token = new Token(followsCondition(tokens, UNARY) ?
            NEGATE : SUBTRACT);
        token.tag   = 1;
        return token;
    }

    // Processing for operand token
    private static Token doOperand(TokenList tokens,int index,boolean remove) {

        // Scan past any unary operators
        for (; tokens.get(index).type == UNARY; index++);

        // Processing by token type
        Token token = tokens.get(index);
        switch (token.type) {
            case GROUPCLOSE: token = doGroup(tokens, index - 1); break;
            case GROUPOPEN : token = doGroup(tokens, index + 1); break;
            default: // VALUE or node
                token.isNode = true;
                token.tag    = index;
        }

        // Processing for unary operands
        for (index = token.tag; index > 0; index--) {
            Token unary = tokens.get(index - 1);

            // No further unary operators exist
            if (unary.type != UNARY)
                break;

            // Attach the node to the unary operator
            unary.isNode = true;
            unary.right  = token;
            token        = token.parent = unary;
            tokens.remove(index);
        }

        // Remove the token from the list
        if (remove)
            tokens.remove(index);
        return token;
    }

    // Processing for symbol name
    private static Token doOther(TokenList tokens, String input, int pos) {
        int length = 1;

        // Determine the longest matching symbol from the current position
        outer: for (; pos + length <= input.length(); length++) {
            String text = input.substring(pos, pos + length).toLowerCase();
            for (int x = 0; x < TEMPLATES.length; x++) {
                if (((String) TEMPLATES[x][0]).startsWith(text))
                    continue outer;
            }
            break;
        };
        length--;

        // No matching symbol found
        if (length == 0)
            return new Token(Breakpoint.ERROR, Breakpoint.UNKNOWN, 1);

        // Find the template that exactly matches the symbol
        Object[] template = null;
        String   text     = input.substring(pos, pos + length).toLowerCase();
        for (int x = 0; template == null && x < TEMPLATES.length; x++)
            if (((String) TEMPLATES[x][0]).equals(text))
                template = TEMPLATES[x];

        // No matching template exists
        if (template == null)
            return new Token(Breakpoint.ERROR, Breakpoint.UNKNOWN, 1);

        // Error checking for syntax
        if (!followsCondition(tokens, (Integer) template[1]))
            return new Token(Breakpoint.ERROR, Breakpoint.SYNTAX, length);

        // Error checking for group close operator
        if ((Integer) template[1] == GROUPCLOSE) {
            Token err = new Token(Breakpoint.ERROR, Breakpoint.SYNTAX, 1);
            int x;

            // Find the corresponding group open operator
            int depth = 0;
            for (x = tokens.size() - 1; x >= 0; x--) {
                Token token = tokens.get(x);
                switch (token.type) {
                    case GROUPCLOSE: depth++; break;
                    case GROUPOPEN:  depth--; break;
                    default: continue;
                }
                if (depth >= 0)
                    continue;

                // The group open operator is mismatched
                if (token.id != (Integer) template[2])
                    return err;
                break;
            }

            // There is no corresponding group open operator
            if (x < 0)
                return err;
        }

        // Produce a new token with the template
        Token token = new Token(template);
        token.tag   = length;
        return token;
    }

    // Process a list of address ranges
    private static int[][] doRanges(TokenList tokens) {

        // Collapse hyphens into ranges and remove commas
        for (int x = 0; x < tokens.size(); x++) {
            Token token = tokens.get(x);

            // Processing for hyphen
            if (token.type == HYPHEN) {
                token.left  = tokens.get(x - 1);
                token.right = tokens.get(x + 1);
                tokens.remove(x + 1);
                tokens.remove(x-- - 1);
                continue;
            }

            // Processing for comma
            if (token.type == COMMA)
                tokens.remove(x--);

        }

        // Construct output list
        int[][] ranges = new int[tokens.size()][];
        for (int x = 0; x < tokens.size(); x++) {
            Token token = tokens.get(x);
            ranges[x] = token.type == ADDRESS ? new int[] { token.value } :
                new int[] { token.left.value, token.right.value };
        }
        return ranges;
    }

    // Determine whether a new toekn can follow the last token in a list
    private static boolean followsAddress(TokenList tokens, int type) {
        int last = tokens.size() == 0 ? COMMA :
            tokens.get(tokens.size() - 1).type;
        switch (type) {
            case ADDRESS: return last != ADDRESS;
            case HYPHEN:  return last == ADDRESS && (tokens.size() == 1 ||
                tokens.get(tokens.size() - 2).type != HYPHEN);
        }
        return last == ADDRESS; // Comma
    }

    // Determine whether a new token can follow the last token in a list
    private static boolean followsCondition(TokenList tokens, int type) {
        return (0xAAD555 >> type + (tokens.size() == 0 ? GROUPOPEN :
            tokens.get(tokens.size() - 1).type * 5) & 1) != 0;
    }

}
