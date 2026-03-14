 import
  java.io.UnsupportedEncodingException;
 import
  java.nio.charset.Charset;

 public class SqlSelectDemo {

  public static void main(String[] args) {
  
   /** 
     * Method: public String();
     */
   String stringOne = new String();
   System.out.println(stringOne); // ""
  
   /**
     * Method: public String(String original);
     */
   String stringTwo = new String("stringTwo");
   System.out.println(stringTwo); // "stringTwo"
  
   /**
     * Method: public String(char value[]); 
     */
   char[] stringThreeChar = new char[]
    {'s', 't', 'r', 'i', 'n', 'g', 'T', 'h', 'r', 'e', 'e'};
   
   String stringThree = new String(stringThreeChar); 
   System.out.println(stringThree); // "stringThree"
  
   /**
     * Method: public String(char value[], int offset, int count);
     */
   char[] stringFourChar = new char[]
    {'s', 't', 'r', 'i', 'n', 'g', 'F', 'o', 'u', 'r'}; 
   
   String stringFour = new String(stringFourChar, 2, 7);
   System.out.println(stringFour); // "ringFou"
  
   /**
     * Method: public String(int[] codePoints, int offset, int count); 
     */
   int[] stringFiveChar = new int[] // stringFive
    {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
     103 /* g */,  70 /* F */, 105 /* i */, 118 /* v */, 101 /* e */};
   
   String stringFive = new String(stringFiveChar, 2, 7);
   System.out.println(stringFive); // "ringFiv"
  
   /**
     * @Deprecated 
     * Method: public String(byte ascii[], int hibyte); 
     */
   byte[] stringSixBytes = new byte[] // stringSix
    {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
     103 /* g */,  83 /* S */, 105 /* i */, 120 /* x */};

   @SuppressWarnings("deprecation")
   String stringSix = new String(stringSixBytes, 0);
   System.out.println(stringSix); // "stringSix"
  
   /**
     * @Deprecated 
     * Method: public String(byte ascii[], int hibyte, int offset, int count);
     */
   byte[] stringSevenBytes = new byte[] // stringSeven
    {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
     103 /* g */,  83 /* S */, 101 /* e */, 118 /* v */, 101 /* e */,
     110 /* n */};

   @SuppressWarnings("deprecation")
   String stringSeven = new String(stringSevenBytes, 0, 2, 8);
   System.out.println(stringSeven); // "ringSeve"

   /**
     * Method: public String(byte bytes[]);
     */
   byte[] stringEightBytes = new byte[] // stringEight 
    {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
     103 /* g */,  69 /* E */, 105 /* i */, 103 /* g */, 104 /* h */, 
     116 /* t */};
   
   String stringEight = new String(stringEightBytes);
   System.out.println(stringEight); // "stringEight"    

   /**
     * Method: public String(byte bytes[], int offset, int length)
     */
   byte[] stringNineBytes = new byte[] // stringNine
     {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
      103 /* g */,  78 /* N */, 105 /* i */, 110 /* n */, 101 /* e */};
   
   String stringNine = new String(stringNineBytes, 2, 7);
   System.out.println(stringNine); // "ringNi"   
   
   /**
     * Method: public String(byte bytes[], String charsetName)
     *          throws UnsupportedEncodingException;
     */
   byte[] stringTenBytes = new byte[] // stringTen 
    {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
     103 /* g */,  84 /* T */, 101 /* e */, 110 /* n */};
 
   String stringTen = null;
   try {
    stringTen = new String(stringTenBytes, "ASCII");
    System.out.println(stringTen); // "sringTen"    
   } catch (UnsupportedEncodingException unsupportedEncodingException) {
    unsupportedEncodingException.printStackTrace();
   }   

   /**
     * Method: public String(byte bytes[], Charset charset); 
     */
   byte[] stringElevenBytes = new byte[] // stringEleven 
    {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
     103 /* g */,  69 /* E */, 108 /* l */, 101 /* e */, 118 /* v */,
     101 /* e */, 110 /* n */};

   String stringEleven = new String(stringElevenBytes, Charset.forName("ASCII"));
   System.out.println(stringEleven); // "stringEleven"  

   /**
     * Method: public String(byte bytes[], int offset, int length, String charsetName) 
     *          throws UnsupportedEncodingException;
     */
   byte[] stringTwelveBytes = new byte[] // stringTwelve
    {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
     103 /* g */,  84 /* T */, 119 /* w */, 101 /* e */, 108 /* l */,
     118 /* v */, 101 /* e */};
   
   String stringTwelve = null;
   try {
    stringTwelve = new String(stringTwelveBytes, 2, 8, "ASCII");
    System.out.println(stringTwelve); // "ringTwel"    
   } catch (UnsupportedEncodingException unsupportedEncodingException) {
    unsupportedEncodingException.printStackTrace();
   }  

   /**
     * Method: public String(byte bytes[], int offset, int length, Charset charset);
     */
   byte[] stringThirteenBytes = new byte[] // stringThirteen
    {115 /* s */, 116 /* t */, 114 /* r */, 105 /* i */, 110 /* n */,
     103 /* g */,  84 /* T */, 104 /* h */, 105 /* i */, 114 /* r */,
     116 /* t */, 101 /* e */, 101 /* e */, 110 /* n */};     

   String stringThirteen = new String(stringThirteenBytes, 2, 10, Charset.forName("ASCII"));
   System.out.println(stringThirteen); // "ringThirte"  

   /**
     * Method: public String(StringBuffer buffer);
     */
   StringBuffer stringFourteenBuffer = new StringBuffer();
   stringFourteenBuffer.append("stringFourteen");
   
   String stringFourteen = new String(stringFourteenBuffer);
   System.out.println(stringFourteen); // "stringFourteen"  
   
   /**
     * Method: public String(StringBuilder builder);
     */
   StringBuilder stringFifteenBuilder = new StringBuilder();
   stringFifteenBuilder.append("stringFifteen");
   
   String stringFifteen = new String(stringFifteenBuilder);
   System.out.println(stringFifteen); // "stringFifteen" 
   

   /**
     * Method: public int length(); 
     */
   System.out.println( // 0
    "stringOne:\n --> Longitud: ["
   + stringOne.length()
   + "]\n --> Valor: ["
   + stringOne
   + "]");
   System.out.println( // 9
     "stringTwo:\n --> Longitud: ["
    + stringTwo.length()
    + "]\n --> Valor: ["
    + stringTwo
    + "]");
   System.out.println( // 11
     "stringThree:\n --> Longitud: ["
    + stringThree.length()
    + "]\n --> Valor: ["
    + stringThree
    + "]");
   System.out.println( // 7
     "stringFour:\n --> Longitud: ["
    + stringFour.length()
    + "]\n --> Valor: ["
    + stringFour
    + "]");
   System.out.println( // 7
     "stringFive:\n --> Longitud: ["
    + stringFive.length()
    + "]\n --> Valor: ["
    + stringFive
    + "]");
   System.out.println( // 9
     "stringSix:\n --> Longitud: ["
    + stringSix.length()
    + "]\n --> Valor: ["
    + stringSix
    + "]");
   System.out.println( // 8
     "stringSeven:\n --> Longitud: ["
    + stringSeven.length()
    + "]\n --> Valor: ["
    + stringSeven
    + "]");
   System.out.println( // 11
     "stringEight:\n --> Longitud: ["
    + stringEight.length()
    + "]\n --> Valor: ["
    + stringEight
    + "]");
   System.out.println( // 7
     "stringNine:\n --> Longitud: ["
    + stringNine.length()
    + "]\n --> Valor: ["
    + stringNine
    + "]");
   System.out.println( // 9
     "stringTen:\n --> Longitud: ["
    + stringTen.length()
    + "]\n --> Valor: ["
    + stringTen
    + "]");
   System.out.println( // 12
     "stringEleven:\n --> Longitud: ["
    + stringEleven.length()
    + "]\n --> Valor: ["
    + stringEleven
    + "]");
   System.out.println( // 8
     "stringTwelve:\n --> Longitud: ["
    + stringTwelve.length()
    + "]\n --> Valor: ["
    + stringTwelve
    + "]");
   System.out.println( // 10
     "stringThirteen:\n --> Longitud: ["
    + stringThirteen.length()
    + "]\n --> Valor: ["
    + stringThirteen
    + "]");
   System.out.println( // 14
     "stringFourteen:\n --> Longitud: ["
    + stringFourteen.length()
    + "]\n --> Valor: ["
    + stringFourteen
    + "]");   
   System.out.println( // 13
    "stringFifteen:\n --> Longitud: ["
   + stringFifteen.length()
   + "]\n --> Valor: ["
   + stringFifteen
   + "]"); 
  }

  
 private static void printChar() {
  for(int i = 0; i < 255; i++) {
  int[] intChar = {i};
  String string = new String(intChar, 0, 1);
  System.out.println(i + ": " + string);
  }
 }
 
 private static void printByte() {
  for(int i = 0; i < 255; i++) {
  byte[] byteChar = {(byte) i};
  String string = new String(byteChar, 0, 0, 1);
  System.out.println(i + ": " + string);
  }
 } 
 }
