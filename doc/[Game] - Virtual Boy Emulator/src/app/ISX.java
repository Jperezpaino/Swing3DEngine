package app;

// Java imports
import java.util.*;

// ROM handler for Intelligent Systems debugger files
public class ISX {

    // Instance fields
    int      dataSize; // Number of significant bytes in ROM data
    boolean  extended; // ISX file is in extended format
    boolean  mixed;    // ISX file contains both 65815 and V810SF records
    Range[]  ranges;   // Data ranges
    int      romSize;  // Size in bytes of smallest valid ROM image
    Code[]   codes;    // ROM data sections
    Symbol[] symbols;  // Debugger symbols

    // Private fields
    private byte[] data; // Working file buffer
    private int    head; // Latest code ending address in first half
    private int    pos;  // Current position in file
    private int    tail; // Earliest code starting address in second half



    ///////////////////////////////////////////////////////////////////////////
    //                             Data Classes                              //
    ///////////////////////////////////////////////////////////////////////////

    // V810SF Code record
    static class Code implements Comparable<Code> {
        int    address;
        int    length;
        byte[] data;
        public int compareTo(Code o) {
            return Integer.compareUnsigned(address, o.address);
        }
    }

    // V810SF Symbol record
    static class Symbol implements Comparable<Symbol> {
        int    address;
        int    flags;
        String name;
        public int compareTo(Symbol o) {
            return name.compareTo(o.name);
        }
    }

    // V810SF Range record
    static class Range implements Comparable<Range> {
        int end;
        int start;
        int type;
        public int compareTo(Range o) {
            return Integer.compareUnsigned(start, o.start);
        }
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Factory Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Create an ISX object given file data
    static ISX decode(byte[] data) {
        try { return new ISX(data); }
        catch (Exception e) { }
        return null;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Factory constructor
    private ISX(byte[] data) throws Exception {

        // Configure instance fields
        this.data = data;
        extended  = readInt(3) == 0x585349;
        head      = 0x00000000;
        pos      += extended ? 29 : -3;
        tail      = 0x01000000;

        // Temporary collections for record management
        ArrayList<Code  > codes   = new ArrayList<Code  >();
        ArrayList<Range > ranges  = new ArrayList<Range >();
        ArrayList<Symbol> symbols = new ArrayList<Symbol>();

        // Process records until EOF
        while (pos < data.length)
        switch (readInt(1)) {
            case 0x01: doCode16  (       ); break;
            case 0x03: doRange16 (       ); break;
            case 0x04: doSymbol16(       ); break;
            case 0x11: doCode32  (codes  ); break;
            case 0x13: doRange32 (ranges ); break;
            case 0x14: doSymbol32(symbols); break;
            case 0x20: case 0x21: case 0x22:
                doSystem32(); break;
            default: throw new Exception("Invalid record type");
        }

        // Calculate the minimum required ROM size
        int length = head + 0x01000000 - tail;
        if (length == 0) throw new Exception("No V810SF code records");
        for (romSize = 1; romSize < length; romSize <<= 1);

        // Configure instance fields
        Arrays.sort(this.codes  =codes  .toArray(new Code  [codes  .size()]));
        Arrays.sort(this.ranges =ranges .toArray(new Range [ranges .size()]));
        Arrays.sort(this.symbols=symbols.toArray(new Symbol[symbols.size()]));
        this.data = null;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Package Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Retrieve the symbol name for a given address, if any
    String getAddressName(int address) {
        for (int x = 0; x < symbols.length; x++)
            if (symbols[x].address == address)
                return symbols[x].name;
        return null;
    }

    // Produce a ROM image from code record data
    byte[] getROM() {
        byte[] rom = new byte[romSize];
        for (int x = 0; x < codes.length; x++) {
            Code code = codes[x];
            System.arraycopy(code.data, 0,
                rom, code.address & romSize - 1, code.length);
        }
        return rom;
    }



    ///////////////////////////////////////////////////////////////////////////
    //                            Private Methods                            //
    ///////////////////////////////////////////////////////////////////////////

    // Processing for 65815 Code records
    private void doCode16() throws Exception {
        mixed = true;
        if (readInt(1) >= 0x80)
            pos++;
        pos += 2;
        pos = readInt(2) + pos;
    }

    // Processing for 65815 Range records
    private void doRange16() throws Exception {
        mixed = true;
        pos = readInt(2) * 6 + pos;
    }

    // Processing for 65815 Symbol records
    private void doSymbol16() throws Exception {
        mixed = true;
        for (int count = readInt(2), x = 0; x < count; x++)
            pos = readInt(1) + 4 + pos;
    }

    // Processing for V810SF Code records
    private void doCode32(ArrayList<Code> codes) throws Exception {
        Code code = new Code();
        codes.add(code);

        // Check the address field
        code.address = readInt(4);
        if ((code.address & 0x07000000) != 0x07000000)
            throw new Exception("Invalid code address");

        // Check the length field
        code.length = readInt(4);
        if (
            Integer.compareUnsigned(code.length , 0x01000000) >= 0 ||
            Integer.compareUnsigned(
                (code.address & 0x00FFFFFF) + code.length, 0x01000000) >= 0
        ) throw new Exception("Invalid code length");

        // Make a copy of the code data
        code.data = new byte[code.length];
        System.arraycopy(data, pos, code.data, 0, code.length);
        pos      += code.length;
        dataSize += code.length;

        // Track the data boundaries
        int start = code.address & 0x00FFFFFF;
        int end   = start + code.length - 1;
        if (start < 0x00800000) {
            if (end >= 0x00800000) {
                head = 0x007FFFFF;
                tail = 0x00800000;
            } else if (end > head) head = end;
        } else if (start < tail) tail = start;

    }

    // Processing for V810SF Range records
    private void doRange32(ArrayList<Range> ranges) throws Exception {
        for (int count = readInt(2), x = 0; x < count; x++) {
            Range range = new Range();
            range.start = readInt(4);
            range.end   = readInt(4);
            range.type  = readInt(1);
            ranges.add(range);
        }
    }

    // Processing for V810SF Symbol records
    private void doSymbol32(ArrayList<Symbol> symbols) throws Exception {
        for (int count = readInt(2), x = 0; x < count; x++) {
            Symbol symbol  = new Symbol();
            int length     = readInt(1);
            symbol.name    = new String(data, pos, length);
            pos           += length;
            symbol.flags   = readInt(2);
            symbol.address = readInt(4);
            symbols.add(symbol);
        }
    }

    // Processing for V810SF System records
    private void doSystem32() throws Exception {
        pos = readInt(4) + pos;
    }

    // Read an integer of arbitrary size from the file data
    private int readInt(int size) {
        int value = 0;
        for (int x = 0; x < size; x++)
            value = value >>> 8 | (data[pos++] & 0xFF) << 24;
        return value >>> (4 - size << 3);
    }

}
