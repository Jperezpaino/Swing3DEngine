package vue;
import vue.*;

// Primary emulation context template
public interface VUE {

    ///////////////////////////////////////////////////////////////////////////
    //                               Constants                               //
    ///////////////////////////////////////////////////////////////////////////

    // Program register IDs
    public static final int GP =  4;
    public static final int HP =  2;
    public static final int LP = 31;
    public static final int SP =  3;
    public static final int TP =  5;

    // System register IDs
    public static final int ADTRE = 25;
    public static final int CHCW  = 24;
    public static final int ECR   =  4;
    public static final int EIPC  =  0;
    public static final int EIPSW =  1;
    public static final int FEPC  =  2;
    public static final int FEPSW =  3;
    public static final int PIR   =  6;
    public static final int PSW   =  5;
    public static final int TKCW  =  7;

    // Bus access data types
    public static final int S8  = 0x00;
    public static final int S16 = 0x01;
    public static final int S32 = 0x02;
    public static final int U8  = 0x03;
    public static final int U16 = 0x04;

    // Instruction IDs
    public static final int ILLEGAL = -1;
    public static final int ADD_IMM =  0;
    public static final int ADD_REG =  1;
    public static final int ADDF_S  =  2;
    public static final int ADDI    =  3;
    public static final int AND     =  4;
    public static final int ANDBSU  = 64;
    public static final int ANDI    =  5;
    public static final int ANDNBSU = 65;
    public static final int BCOND   =  6;
    public static final int CAXI    =  7;
    public static final int CLI     =  8;
    public static final int CMP_IMM =  9;
    public static final int CMP_REG = 10;
    public static final int CMPF_S  = 11;
    public static final int CVT_SW  = 12;
    public static final int CVT_WS  = 13;
    public static final int DIV     = 14;
    public static final int DIVF_S  = 15;
    public static final int DIVU    = 16;
    public static final int HALT    = 17;
    public static final int IN_B    = 18;
    public static final int IN_H    = 19;
    public static final int IN_W    = 20;
    public static final int JAL     = 21;
    public static final int JMP     = 22;
    public static final int JR      = 23;
    public static final int LD_B    = 24;
    public static final int LD_H    = 25;
    public static final int LD_W    = 26;
    public static final int LDSR    = 27;
    public static final int MOV_IMM = 28;
    public static final int MOV_REG = 29;
    public static final int MOVBSU  = 66;
    public static final int MOVEA   = 30;
    public static final int MOVHI   = 31;
    public static final int MPYHW   = 32;
    public static final int MUL     = 33;
    public static final int MULF_S  = 34;
    public static final int MULU    = 35;
    public static final int NOT     = 36;
    public static final int NOTBSU  = 67;
    public static final int OR      = 37;
    public static final int ORBSU   = 68;
    public static final int ORI     = 38;
    public static final int ORNBSU  = 69;
    public static final int OUT_B   = 39;
    public static final int OUT_H   = 40;
    public static final int OUT_W   = 41;
    public static final int RETI    = 42;
    public static final int REV     = 43;
    public static final int SAR_IMM = 44;
    public static final int SAR_REG = 45;
    public static final int SCH0BSD = 72;
    public static final int SCH0BSU = 73;
    public static final int SCH1BSD = 74;
    public static final int SCH1BSU = 75;
    public static final int SEI     = 46;
    public static final int SETF    = 47;
    public static final int SHL_IMM = 48;
    public static final int SHL_REG = 49;
    public static final int SHR_IMM = 50;
    public static final int SHR_REG = 51;
    public static final int ST_B    = 52;
    public static final int ST_H    = 53;
    public static final int ST_W    = 54;
    public static final int STSR    = 55;
    public static final int SUB     = 56;
    public static final int SUBF_S  = 57;
    public static final int TRAP    = 58;
    public static final int TRNC_SW = 59;
    public static final int XB      = 60;
    public static final int XH      = 61;
    public static final int XOR     = 62;
    public static final int XORBSU  = 70;
    public static final int XORI    = 63;
    public static final int XORNBSU = 71;

    // Game Pad keys
    public static final int A         = 0x0004;
    public static final int B         = 0x0008;
    public static final int L         = 0x0020;
    public static final int LDOWN     = 0x0400;
    public static final int LLEFT     = 0x0200;
    public static final int LRIGHT    = 0x0100;
    public static final int LUP       = 0x0800;
    public static final int POWER     = 0x0001;
    public static final int R         = 0x0010;
    public static final int RDOWN     = 0x8000;
    public static final int RLEFT     = 0x4000;
    public static final int RRIGHT    = 0x0080;
    public static final int RUP       = 0x0040;
    public static final int SELECT    = 0x2000;
    public static final int SIGNATURE = 0x0002;
    public static final int START     = 0x1000;

    // Audio filters
    public static final int HARDWARE = 0;
    public static final int LINEAR   = 2;
    public static final int NEAREST  = 1;



    ///////////////////////////////////////////////////////////////////////////
    //                            Static Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Create a new emulation context
    public static VUE create() {
        return new JavaVUE();
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Public Methods                             //
    ///////////////////////////////////////////////////////////////////////////

    // Used by the implementation for managing communications
    void communicate(int ccsr, int cdtr);

    // Process the simulation
    int emulate(int cycles);

    // Process the simulation with a communication peer
    int[] emulate(int cycles, VUE peer);

    // Retrieve the audio sample buffer
    byte[] getAudioBuffer();

    // Retrieve the current audio filter setting
    int getAudioFilter();

    // Retrieve the buffer position of the next audio sample
    int getAudioPosition();

    // Retrieve the audio sampling rate
    int getAudioRate();

    // Retrieve the application-supplied break code
    int getBreakCode();

    // Retrieve the mask for triggered breakpoints
    int getBreakMask();

    // Retrieve the current set breakpoints
    Breakpoint[] getBreakpoints();

    // Determine how many CPU cycles can be processed without communication
    int getComCycles();

    // Retrieve the exception code being processed
    int getExceptionCode();

    // Retrieve the most recently rendered image into a pixel buffer
    int[] getFrame(int[] pixels, int leftColor, int rightColor);

    // Retrieve the current frame buffer index
    int getFrameBufferIndex();

    // Retrieve the current frame index
    int getFrameIndex();

    // Retrieve the current game pad state
    int getKeys();

    // Retrieve the previous program counter address
    int getPreviousProgramCounter();

    // Retrieve the current program counter address
    int getProgramCounter();

    // Retrieve the value of a program register
    int getProgramRegister(int index);

    // Retrieve the current ROM into a byte buffer
    // Returns true if successful
    boolean getROM(byte[] buffer, int offset);

    // Retrieve the current ROM size in bytes
    int getROMSize();

    // Retrieve the current SRAM into a byte buffer
    // Returns true if successful
    boolean getSRAM(byte[] buffer, int offset);

    // Retrieve the current SRAM size in bytes
    int getSRAMSize();

    // Retrieve the value of a system register
    int getSystemRegister(int regID);

    // Retrieve the analog audio output filter setting
    boolean isAudioAnalog();

    // Determine whether audio output is enabled
    boolean isAudioEnabled();

    // Determine whether the simulated CPU is in halt status
    boolean isHalted();

    // Read a value from the bus
    int read(int address, int type, boolean debug);

    // Read values from the bus into a byte buffer
    boolean read(int address, int type, byte[] data, int offset, int length,
        boolean debug);

    // Perform system initialization
    void reset();

    // Specify whether to simulate analog audio output
    void setAudioAnalog(boolean use);

    // Specify a new audio sample  buffer
    // Returns true if successful
    boolean setAudioBuffer(byte[] buffer);

    // Enable or disable audio output
    void setAudioEnabled(boolean enabled);

    // Specify a new audio filter setting
    // Returns true if successful
    boolean setAudioFilter(int filter);

    // Specify a new audio sampling rate
    // Returns the actual sampling rate to be used
    int setAudioRate(int hertz);

    // Specify which breakpoints are checked
    void setBreakMask(int mask);

    // Specify a new set of breakpoints
    void setBreakpoints(Breakpoint[] breakpoints);

    // Specify the current frame buffer index
    int setFrameBufferIndex(int buffer);

    // Specify the current frame index
    int setFrameIndex(int frame);

    // Specify a game pad configuration
    int setKeys(int keys);

    // Specify a new address for the program counter
    int setProgramCounter(int address);

    // Specify a new value for a program register
    // Returns the actual new value of the program register
    int setProgramRegister(int index, int value);

    // Specify a new ROM buffer
    // Returns true if successful
    boolean setROM(byte[] buffer, int offset, int length);

    // Specify a new SRAM buffer
    // Returns true if successful
    boolean setSRAM(byte[] buffer, int offset, int length);

    // Store a value into a system register
    // Returns the actual new value of the system register
    int setSystemRegister(int regID, int value);

    // Used by the implementation for scheduling interrupts
    int untilIRQ(int cycles);

    // Write a value to the bus
    void write(int address, int type, int value, boolean debug);

    // Write values to the bus from a byte buffer
    boolean write(int address, int type, byte[] data, int offset, int length,
        boolean debug);

}
