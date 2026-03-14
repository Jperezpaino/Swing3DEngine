package vue;

// Emulation core context implementation descriptor
public class Implementation {

    // Instance fields
    public  boolean isNative;
    public  String  name;



    ///////////////////////////////////////////////////////////////////////////
    //                             Constructors                              //
    ///////////////////////////////////////////////////////////////////////////

    // Package constructor
    Implementation(String module) {
        this.isNative = isNative;
        this.name     = name;
    }

    // Java constructor
    private void initJava() {
        isNative = false;
        name     = "Java";
    }

}
