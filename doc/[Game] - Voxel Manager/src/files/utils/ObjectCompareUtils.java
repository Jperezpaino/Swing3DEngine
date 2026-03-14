package files.utils;

 import
  java.util.Iterator;
 import
  java.util.List;
 import
  java.util.Map;
 import
  java.util.Set;
 import
  java.util.SortedMap;
 import
  java.util.SortedSet;
 import
  java.util.Map.Entry;

 /**
   * Clase estática de utilidades globales, que presenta métodos y utilidades
   * para la comparación de contenidos y valores de distintos tipos y clases.
   */
 public final class ObjectCompareUtils {

  /**
    * Constructor privado sin parámetros de la clase 'ObjectCompareUtils',
    * evitamos así la posibilidad de instanciar esta clase, y haciéndola
    * únicamente accesible a través de métodos estáticos.
    */
  private ObjectCompareUtils() {
   super();
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'boolean' contra otra variable de tipo primitivo 'boolean'.
    *
    * @param elementFirstParam boolean
    * @param elementSecondParam boolean
    * @return boolean
    */
  public static boolean equalsDataBoolean(
         final boolean elementFirstParam,
         final boolean elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam != elementSecondParam)) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Boolean'
    * contra otra variable de tipo objeto 'Boolean'.
    *
    * @param elementFirstParam Boolean
    * @param elementSecondParam Boolean
    * @return boolean
    */
  public static boolean equalsDataBooleanObject(
         final Boolean elementFirstParam,
         final Boolean elementSecondParam) {
   return equalsDataBoolean(elementFirstParam.booleanValue(), elementSecondParam.booleanValue());
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'boolean' contra otra variable de tipo objeto 'Boolean'.
    *
    * @param elementFirstParam boolean
    * @param elementSecondParam Boolean
    * @return boolean
    */
  public static boolean equalsDataBooleanToBooleanObject(
         final boolean elementFirstParam,
         final Boolean elementSecondParam) {
   return equalsDataBoolean(elementFirstParam, elementSecondParam.booleanValue());
  }

  /**
    * Método estático para la comparación de variables de tipo objeto  'Boolean'
    * contra otra variable de tipo primitivo 'boolean'.
    *
    * @param elementFirstParam Boolean
    * @param elementSecondParam boolean
    * @return boolean
    */
  public static boolean equalsDataBooleanObjectToBoolean(
         final Boolean elementFirstParam,
         final boolean elementSecondParam) {
   return equalsDataBoolean(elementFirstParam.booleanValue(), elementSecondParam);
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'boolean' contra otra variable 'Array' de tipo primitivo
    * 'boolean'.
    *
    * @param elementFirstParam boolean[]
    * @param elementSecondParam boolean[]
    * @return boolean
    */
  public static boolean equalsDataBooleanArray(
         final boolean[] elementFirstParam,
         final boolean[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      boolean firstArrayElement = elementFirstParam[i];
      boolean secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataBoolean(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'Boolean' contra otra variable 'Array' de tipo objeto 'Boolean'.
    *
    * @param elementFirstParam Boolean[]
    * @param elementSecondParam Boolean[]
    * @return boolean
    */
  public static boolean equalsDataBooleanObjectArray(
         final Boolean[] elementFirstParam,
         final Boolean[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Boolean firstArrayElement = elementFirstParam[i];
      Boolean secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataBooleanObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'boolean' contra otra variable 'Array' de tipo objeto
    * 'Boolean'.
    *
    * @param elementFirstParam boolean[]
    * @param elementSecondParam Boolean[]
    * @return boolean
    */
  public static boolean equalsDataBooleanArrayToBooleanObjectArray(
         final boolean[] elementFirstParam,
         final Boolean[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      boolean firstArrayElement = elementFirstParam[i];
      boolean secondArrayElement = elementSecondParam[i].booleanValue();
     equalsResult = equalsDataBoolean(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * objeto 'Boolean' contra otra variable 'Array' de tipo primitivo
    * 'boolean'.
    *
    * @param elementFirstParam Boolean[]
    * @param elementSecondParam boolean[]
    * @return boolean
    */
  public static boolean equalsDataBooleanObjectArrayToBooleanArray(
         final Boolean[] elementFirstParam,
         final boolean[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      boolean firstArrayElement = elementFirstParam[i].booleanValue();
      boolean secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataBoolean(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'byte' contra otra variable de tipo primitivo 'byte'.
    *
    * @param elementFirstParam byte
    * @param elementSecondParam byte
    * @return boolean
    */
  public static boolean equalsDataByte(
         final byte elementFirstParam,
         final byte elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam != elementSecondParam)) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Byte'
    * contra otra variable de tipo objeto 'Byte'.
    *
    * @param elementFirstParam Byte
    * @param elementSecondParam Byte
    * @return boolean
    */
  public static boolean equalsDataByteObject(
         final Byte elementFirstParam,
         final Byte elementSecondParam) {
   return equalsDataByte(elementFirstParam.byteValue(), elementSecondParam.byteValue());
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'byte' contra otra variable de tipo onjeto 'Byte'.
    *
    * @param elementFirstParam byte
    * @param elementSecondParam Byte
    * @return boolean
    */
  public static boolean equalsDataByteToByteObject(
         final byte elementFirstParam,
         final Byte elementSecondParam) {
   return equalsDataByte(elementFirstParam, elementSecondParam.byteValue());
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Byte'
    * contra otra variable de tipo primitivo 'byte'.
    *
    * @param elementFirstParam Byte
    * @param elementSecondParam byte
    * @return boolean
    */
  public static boolean equalsDataByteObjectToByte(
         final Byte elementFirstParam,
         final byte elementSecondParam) {
   return equalsDataByte(elementFirstParam.byteValue(), elementSecondParam);
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'byte' contra otra variable 'Array' de tipo primitivo
    * 'byte'.
    *
    * @param elementFirstParam byte[]
    * @param elementSecondParam byte[]
    * @return boolean
    */
  public static boolean equalsDataByteArray(
         final byte[] elementFirstParam,
         final byte[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      byte firstArrayElement = elementFirstParam[i];
      byte secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataByte(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'Byte' contra otra variable 'Array' de tipo objeto 'Byte'.
    *
    * @param elementFirstParam Byte[]
    * @param elementSecondParam Byte[]
    * @return boolean
    */
  public static boolean equalsDataByteObjectArray(
         final Byte[] elementFirstParam,
         final Byte[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Byte firstArrayElement = elementFirstParam[i];
      Byte secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataByteObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'byte' contra otra variable 'Array' de tipo objeto 'Byte'.
    *
    * @param elementFirstParam byte[]
    * @param elementSecondParam Byte[]
    * @return boolean
    */
  public static boolean equalsDataByteArrayToByteObjectArray(
         final byte[] elementFirstParam,
         final Byte[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      byte firstArrayElement = elementFirstParam[i];
      byte secondArrayElement = elementSecondParam[i].byteValue();
     equalsResult = equalsDataByte(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * objeto 'Byte' contra otra variable 'Array' de tipo primitivo 'byte'.
    *
    * @param elementFirstParam Byte[]
    * @param elementSecondParam byte[]
    * @return boolean
    */
  public static boolean equalsDataByteObjectArrayToByteArray(
         final Byte[] elementFirstParam,
         final byte[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      byte firstArrayElement = elementFirstParam[i].byteValue();
      byte secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataByte(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'short' contra otra variable de tipo primitivo 'short'.
    *
    * @param elementFirstParam short
    * @param elementSecondParam short
    * @return boolean
    */
  public static boolean equalsDataShort(
         final short elementFirstParam,
         final short elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam != elementSecondParam)) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Short'
    * contra otra variable de tipo objeto 'Short'.
    *
    * @param elementFirstParam Short
    * @param elementSecondParam Short
    * @return boolean
    */
  public static boolean equalsDataShortObject(
         final Short elementFirstParam,
         final Short elementSecondParam) {
   return equalsDataShort(elementFirstParam.shortValue(), elementSecondParam.shortValue());
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'short' contra otra variable de tipo objeto 'Short'.
    *
    * @param elementFirstParam short
    * @param elementSecondParam Short
    * @return boolean
    */
  public static boolean equalsDataShortToShortObject(
         final short elementFirstParam,
         final Short elementSecondParam) {
   return equalsDataShort(elementFirstParam, elementSecondParam.shortValue());
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Short'
    * contra otra variable de tipo primitivo 'short'.
    *
    * @param elementFirstParam Short
    * @param elementSecondParam short
    * @return boolean
    */
  public static boolean equalsDataShortObjectToShort(
         final Short elementFirstParam,
         final short elementSecondParam) {
   return equalsDataShort(elementFirstParam.shortValue(), elementSecondParam);
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'short' contra otra variable 'Array' de tipo primitivo
    * 'short'.
    *
    * @param elementFirstParam short[]
    * @param elementSecondParam short[]
    * @return boolean
    */
  public static boolean equalsDataShortArray(
         final short[] elementFirstParam,
         final short[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      short firstArrayElement = elementFirstParam[i];
      short secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataShort(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'Short' contra otra variable 'Array' de tipo objeto 'Short'.
    *
    * @param elementFirstParam Short[]
    * @param elementSecondParam Short[]
    * @return boolean
    */
  public static boolean equalsDataShortObjectArray(
         final Short[] elementFirstParam,
         final Short[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Short firstArrayElement = elementFirstParam[i];
      Short secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataShortObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'short' contra otra variable 'Array' de tipo objeto 'Short'.
    *
    * @param elementFirstParam short[]
    * @param elementSecondParam Short[]
    * @return boolean
    */
  public static boolean equalsDataShortArrayToShortObjectArray(
         final short[] elementFirstParam,
         final Short[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      short firstArrayElement = elementFirstParam[i];
      short secondArrayElement = elementSecondParam[i].shortValue();
     equalsResult = equalsDataShort(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * objeto 'Short' contra otra variable 'Array' de tipo primitivo 'short'.
    *
    * @param elementFirstParam Short[]
    * @param elementSecondParam short[]
    * @return boolean
    */
  public static boolean equalsDataShortObjectArrayToShortArray(
         final Short[] elementFirstParam,
         final short[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      short firstArrayElement = elementFirstParam[i].shortValue();
      short secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataShort(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'int' contra otra variable de tipo primitivo 'int'.
    *
    * @param elementFirstParam int
    * @param elementSecondParam int
    * @return boolean
    */
  public static boolean equalsDataInt(
         final int elementFirstParam,
         final int elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam != elementSecondParam)) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Integer'
    * contra otra variable de tipo objeto 'Integer'.
    *
    * @param elementFirstParam Integer
    * @param elementSecondParam Integer
    * @return boolean
    */
  public static boolean equalsDataIntegerObject(
         final Integer elementFirstParam,
         final Integer elementSecondParam) {
   return equalsDataInt(elementFirstParam.intValue(), elementSecondParam.intValue());
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'int' contra otra variable de tipo objeto 'Integer'.
    *
    * @param elementFirstParam int
    * @param elementSecondParam Integer
    * @return boolean
    */
  public static boolean equalsDataIntToIntegerObject(
         final int elementFirstParam,
         final Integer elementSecondParam) {
   return equalsDataInt(elementFirstParam, elementSecondParam.intValue());
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Integer'
    * contra otra variable de tipo primitivo 'int'.
    *
    * @param elementFirstParam Integer
    * @param elementSecondParam int
    * @return boolean
    */
  public static boolean equalsDataIntegerObjectToInt(
         final Integer elementFirstParam,
         final int elementSecondParam) {
   return equalsDataInt(elementFirstParam.intValue(), elementSecondParam);
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'int' contra otra variable 'Array' de tipo primitivo
    * 'int'.
    *
    * @param elementFirstParam int[]
    * @param elementSecondParam int[]
    * @return boolean
    */
  public static boolean equalsDataIntArray(
         final int[] elementFirstParam,
         final int[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      int firstArrayElement = elementFirstParam[i];
      int secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataInt(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'Integer' contra otra variable 'Array' de tipo objeto 'Integer'.
    *
    * @param elementFirstParam Integer[]
    * @param elementSecondParam Integer[]
    * @return boolean
    */
  public static boolean equalsDataIntegerObjectArray(
         final Integer[] elementFirstParam,
         final Integer[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Integer firstArrayElement = elementFirstParam[i];
      Integer secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataIntegerObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'int' contra otra variable 'Array' de tipo objeto 'Integer'.
    *
    * @param elementFirstParam int[]
    * @param elementSecondParam Integer[]
    * @return boolean
    */
  public static boolean equalsDataIntArrayToIntegerObjectArray(
         final int[] elementFirstParam,
         final Integer[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      int firstArrayElement = elementFirstParam[i];
      int secondArrayElement = elementSecondParam[i].intValue();
     equalsResult = equalsDataInt(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * objeto 'Integer' contra otra variable 'Array' de tipo primitivo 'int'.
    *
    * @param elementFirstParam Integer[]
    * @param elementSecondParam int[]
    * @return boolean
    */
  public static boolean equalsDataIntegerObjectArrayToIntArray(
         final Integer[] elementFirstParam,
         final int[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      int firstArrayElement = elementFirstParam[i].intValue();
      int secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataInt(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'long' contra otra variable de tipo primitivo 'long'.
    *
    * @param elementFirstParam long
    * @param elementSecondParam long
    * @return boolean
    */
  public static boolean equalsDataLong(
         final long elementFirstParam,
         final long elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam != elementSecondParam)) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Long'
    * contra otra variable de tipo objeto 'Long'.
    *
    * @param elementFirstParam Long
    * @param elementSecondParam Long
    * @return boolean
    */
  public static boolean equalsDataLongObject(
         final Long elementFirstParam,
         final Long elementSecondParam) {
   return equalsDataLong(elementFirstParam.longValue(), elementSecondParam.longValue());
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'long' contra otra variable de tipo objeto 'Long'.
    *
    * @param elementFirstParam long
    * @param elementSecondParam Long
    * @return boolean
    */
  public static boolean equalsDataLongToLongObject(
         final long elementFirstParam,
         final Long elementSecondParam) {
   return equalsDataLong(elementFirstParam, elementSecondParam.longValue());
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Long'
    * contra otra variable de tipo primitivo 'long'.
    *
    * @param elementFirstParam Long
    * @param elementSecondParam long
    * @return boolean
    */
  public static boolean equalsDataLongObjectToLong(
         final Long elementFirstParam,
         final long elementSecondParam) {
   return equalsDataLong(elementFirstParam.longValue(), elementSecondParam);
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'long' contra otra variable 'Array' de tipo primitivo
    * 'long'.
    *
    * @param elementFirstParam long[]
    * @param elementSecondParam long[]
    * @return boolean
    */
  public static boolean equalsDataLongArray(
         final long[] elementFirstParam,
         final long[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      long firstArrayElement = elementFirstParam[i];
      long secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataLong(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'Long' contra otra variable 'Array' de tipo objeto 'Long'.
    *
    * @param elementFirstParam Long[]
    * @param elementSecondParam Long[]
    * @return boolean
    */
  public static boolean equalsDataLongObjectArray(
         final Long[] elementFirstParam,
         final Long[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Long firstArrayElement = elementFirstParam[i];
      Long secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataLongObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'long' contra otra variable 'Array' de tipo objeto 'Long'.
    *
    * @param elementFirstParam long[]
    * @param elementSecondParam Long[]
    * @return boolean
    */
  public static boolean equalsDataLongArrayToLongObjectArray(
         final long[] elementFirstParam,
         final Long[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      long firstArrayElement = elementFirstParam[i];
      long secondArrayElement = elementSecondParam[i].longValue();
     equalsResult = equalsDataLong(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * objeto 'Long' contra otra variable 'Array' de tipo primitivo 'long'.
    *
    * @param elementFirstParam Long[]
    * @param elementSecondParam long[]
    * @return boolean
    */
  public static boolean equalsDataLongObjectArrayToLongArray(
         final Long[] elementFirstParam,
         final long[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      long firstArrayElement = elementFirstParam[i].longValue();
      long secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataLong(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'float' contra otra variable de tipo primitivo 'float'.
    *
    * @param elementFirstParam float
    * @param elementSecondParam float
    * @return boolean
    */
  public static boolean equalsDataFloat(
         final float elementFirstParam,
         final float elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam != elementSecondParam)) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Float'
    * contra otra variable de tipo objeto 'Float'.
    *
    * @param elementFirstParam Float
    * @param elementSecondParam Float
    * @return boolean
    */
  public static boolean equalsDataFloatObject(
         final Float elementFirstParam,
         final Float elementSecondParam) {
   return equalsDataFloat(elementFirstParam.floatValue(), elementSecondParam.floatValue());
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'float' contra otra variable de tipo objeto 'Float'.
    *
    * @param elementFirstParam float
    * @param elementSecondParam Float
    * @return boolean
    */
  public static boolean equalsDataFloatToFloatObject(
         final float elementFirstParam,
         final Float elementSecondParam) {
   return equalsDataFloat(elementFirstParam, elementSecondParam.floatValue());
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Float'
    * contra otra variable de tipo primitivo 'float'.
    *
    * @param elementFirstParam Float
    * @param elementSecondParam float
    * @return boolean
    */
  public static boolean equalsDataFloatObjectToFloat(
         final Float elementFirstParam,
         final float elementSecondParam) {
   return equalsDataFloat(elementFirstParam.floatValue(), elementSecondParam);
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'float' contra otra variable 'Array' de tipo primitivo
    * 'float'.
    *
    * @param elementFirstParam float[]
    * @param elementSecondParam float[]
    * @return boolean
    */
  public static boolean equalsDataFloatArray(
         final float[] elementFirstParam,
         final float[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      float firstArrayElement = elementFirstParam[i];
      float secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataFloat(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'Float' contra otra variable 'Array' de tipo objeto 'Float'.
    *
    * @param elementFirstParam Float[]
    * @param elementSecondParam Float[]
    * @return boolean
    */
  public static boolean equalsDataFloatObjectArray(
         final Float[] elementFirstParam,
         final Float[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Float firstArrayElement = elementFirstParam[i];
      Float secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataFloatObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'float' contra otra variable 'Array' de tipo objeto 'Float'.
    *
    * @param elementFirstParam float[]
    * @param elementSecondParam Float[]
    * @return boolean
    */
  public static boolean equalsDataFloatArrayToFloatObjectArray(
         final float[] elementFirstParam,
         final Float[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      float firstArrayElement = elementFirstParam[i];
      float secondArrayElement = elementSecondParam[i].floatValue();
     equalsResult = equalsDataFloat(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * objeto 'Float' contra otra variable 'Array' de tipo primitivo 'float'.
    *
    * @param elementFirstParam Float[]
    * @param elementSecondParam float[]
    * @return boolean
    */
  public static boolean equalsDataFloatObjectArrayToFloatArray(
         final Float[] elementFirstParam,
         final float[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      float firstArrayElement = elementFirstParam[i].floatValue();
      float secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataFloat(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'double' contra otra variable de tipo primitivo 'double'.
    *
    * @param elementFirstParam double
    * @param elementSecondParam double
    * @return boolean
    */
  public static boolean equalsDataDouble(
         final double elementFirstParam,
         final double elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam != elementSecondParam)) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Double'
    * contra otra variable de tipo objeto 'Double'.
    *
    * @param elementFirstParam Double
    * @param elementSecondParam Double
    * @return boolean
    */
  public static boolean equalsDataDoubleObject(
         final Double elementFirstParam,
         final Double elementSecondParam) {
   return equalsDataDouble(elementFirstParam.doubleValue(), elementSecondParam.doubleValue());
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'double' contra otra variable de tipo objeto 'Double'.
    *
    * @param elementFirstParam double
    * @param elementSecondParam Double
    * @return boolean
    */
  public static boolean equalsDataDoubleToDoubleObject(
         final double elementFirstParam,
         final Double elementSecondParam) {
   return equalsDataDouble(elementFirstParam, elementSecondParam.doubleValue());
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Double'
    * contra otra variable de tipo primitivo 'double'.
    *
    * @param elementFirstParam Double
    * @param elementSecondParam double
    * @return boolean
    */
  public static boolean equalsDataDoubleObjectToDouble(
         final Double elementFirstParam,
         final double elementSecondParam) {
   return equalsDataDouble(elementFirstParam.doubleValue(), elementSecondParam);
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'double' contra otra variable 'Array' de tipo primitivo
    * 'double'.
    *
    * @param elementFirstParam double[]
    * @param elementSecondParam double[]
    * @return boolean
    */
  public static boolean equalsDataDoubleArray(
         final double[] elementFirstParam,
         final double[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      double firstArrayElement = elementFirstParam[i];
      double secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataDouble(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'Double' contra otra variable 'Array' de tipo objeto 'Double'.
    *
    * @param elementFirstParam Double[]
    * @param elementSecondParam Double[]
    * @return boolean
    */
  public static boolean equalsDataDoubleObjectArray(
         final Double[] elementFirstParam,
         final Double[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Double firstArrayElement = elementFirstParam[i];
      Double secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataDoubleObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'double' contra otra variable 'Array' de tipo objeto 'Double'.
    *
    * @param elementFirstParam double[]
    * @param elementSecondParam Double[]
    * @return boolean
    */
  public static boolean equalsDataDoubleArrayToDoubleObjectArray(
         final double[] elementFirstParam,
         final Double[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      double firstArrayElement = elementFirstParam[i];
      double secondArrayElement = elementSecondParam[i].doubleValue();
     equalsResult = equalsDataDouble(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * objeto 'Double' contra otra variable 'Array' de tipo primitivo 'double'.
    *
    * @param elementFirstParam Double[]
    * @param elementSecondParam double[]
    * @return boolean
    */
  public static boolean equalsDataDoubleObjectArrayToDoubleArray(
         final Double[] elementFirstParam,
         final double[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      double firstArrayElement = elementFirstParam[i].doubleValue();
      double secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataDouble(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'char' contra otra variable de tipo primitivo 'char'.
    *
    * @param elementFirstParam char
    * @param elementSecondParam char
    * @return boolean
    */
  public static boolean equalsDataChar(
         final char elementFirstParam,
         final char elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam != elementSecondParam)) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Character'
    * contra otra variable de tipo objeto 'Character'.
    *
    * @param elementFirstParam Character
    * @param elementSecondParam Character
    * @return boolean
    */
  public static boolean equalsDataCharacterObject(
         final Character elementFirstParam,
         final Character elementSecondParam) {
   return equalsDataChar(elementFirstParam.charValue(), elementSecondParam.charValue());
  }

  /**
    * Método estático para la comparación de variables de tipo primitivo
    * 'char' contra otra variable de tipo objeto 'Character'.
    *
    * @param elementFirstParam char
    * @param elementSecondParam Character
    * @return boolean
    */
  public static boolean equalsDataCharToCharacterObject(
         final char elementFirstParam,
         final Character elementSecondParam) {
   return equalsDataChar(elementFirstParam, elementSecondParam.charValue());
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'Character'
    * contra otra variable de tipo primitivo 'char'.
    *
    * @param elementFirstParam Character
    * @param elementSecondParam char
    * @return boolean
    */
  public static boolean equalsDataCharacterObjectToChar(
         final Character elementFirstParam,
         final char elementSecondParam) {
   return equalsDataChar(elementFirstParam.charValue(), elementSecondParam);
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'char' contra otra variable 'Array' de tipo primitivo
    * 'char'.
    *
    * @param elementFirstParam char[]
    * @param elementSecondParam char[]
    * @return boolean
    */
  public static boolean equalsDataCharArray(
         final char[] elementFirstParam,
         final char[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      char firstArrayElement = elementFirstParam[i];
      char secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataChar(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'Character' contra otra variable 'Array' de tipo objeto 'Character'.
    *
    * @param elementFirstParam Character[]
    * @param elementSecondParam Character[]
    * @return boolean
    */
  public static boolean equalsDataCharacterObjectArray(
         final Character[] elementFirstParam,
         final Character[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Character firstArrayElement = elementFirstParam[i];
      Character secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataCharacterObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * primitivo 'char' contra otra variable 'Array' de tipo objeto 'Character'.
    *
    * @param elementFirstParam char[]
    * @param elementSecondParam Character[]
    * @return boolean
    */
  public static boolean equalsDataCharArrayToCharacterObjectArray(
         final char[] elementFirstParam,
         final Character[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      char firstArrayElement = elementFirstParam[i];
      char secondArrayElement = elementSecondParam[i].charValue();
     equalsResult = equalsDataChar(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo
    * objeto 'Character' contra otra variable 'Array' de tipo primitivo 'char'.
    *
    * @param elementFirstParam Character[]
    * @param elementSecondParam char[]
    * @return boolean
    */
  public static boolean equalsDataCharacterObjectArrayToCharArray(
         final Character[] elementFirstParam,
         final char[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      char firstArrayElement = elementFirstParam[i].charValue();
      char secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataChar(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables de tipo objeto 'String'
    * contra otra variable de tipo objeto 'String'.
    *
    * @param elementFirstParam String
    * @param elementSecondParam String
    * @return boolean
    */
  public static boolean equalsDataStringObject(
         final String elementFirstParam,
         final String elementSecondParam) {
   boolean equalsResult;
   if ((elementFirstParam == null) || (elementSecondParam == null)) {
    equalsResult = false;
   } else {
    if ((elementFirstParam == null) || !(elementFirstParam.equals(elementSecondParam))) {
    equalsResult = false;
    } else {
    equalsResult = true;
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo objeto
    * 'String' contra otra variable 'Array' de tipo objeto 'String'.
    *
    * @param elementFirstParam String[]
    * @param elementSecondParam String[]
    * @return boolean
    */
  public static boolean equalsDataStringArray(
         final String[] elementFirstParam,
         final String[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      String firstArrayElement = elementFirstParam[i];
      String secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataStringObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Object' de cualquier
    * tipo o clase contra otra variable 'Object' de cualquier tipo o clase.
    *
    * @param elementFirstParam Object
    * @param elementSecondParam Object
    * @return boolean
    */
  public static boolean equalsDataObject(
         final Object elementFirstParam,
         final Object elementSecondParam) {
   boolean equalsResult;
   equalsResult = true;
   if (elementFirstParam.getClass().equals(elementSecondParam.getClass())) {
    if (elementFirstParam.equals(elementSecondParam)) {
     equalsResult = true;
    } else {
     equalsResult = false;
    }
   } else {
    equalsResult = false;
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Array' de tipo 'Object'
    * de cualquier tipo o clase contra otra variable 'Array' de tipo
    * 'Object' de cualquier tipo o clase.
    *
    * @param elementFirstParam Object[]
    * @param elementSecondParam Object[]
    * @return boolean
    */
  public static boolean equalsDataObjectArray(
         final Object[] elementFirstParam,
         final Object[] elementSecondParam) {
   boolean equalsResult;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.length != elementSecondParam.length) {
    equalsResult = false;
    } else {
    equalsResult = true;
     for (int i = 0; i < elementFirstParam.length; i++) {
      Object firstArrayElement = elementFirstParam[i];
      Object secondArrayElement = elementSecondParam[i];
     equalsResult = equalsDataObject(firstArrayElement, secondArrayElement);
      if (!equalsResult) {
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'List' de cualquiera de
    * los tipos existentes contra otra variable 'list' de cualquiera de los
    * tipos existentes.
    *
    * @param elementFirstParam List<?>
    * @param elementSecondParam List<?>
    * @return boolean
    */
  public static boolean equalsDataList(
         final List<?> elementFirstParam,
         final List<?> elementSecondParam) {
   boolean equalsResult;
   equalsResult = true;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    if (elementFirstParam.size() != elementSecondParam.size()) {
     equalsResult = false;
    } else {
     for (int i = 0; i < elementFirstParam.size(); i++) {
      if (!equalsDataObject(elementFirstParam.get(i), elementSecondParam.get(i))) {
       equalsResult = false;
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Map' de tipo 'HashMap'
    * contra otra variable 'Map' de tipo objeto 'HashMap'.
    *
    * @param elementFirstParam Map<?, ?>
    * @param elementSecondParam Map<?, ?>
    * @return boolean
    */
  public static boolean equalsDataHashMap(
         final Map<?, ?> elementFirstParam,
         final Map<?, ?> elementSecondParam) {
   boolean equalsResult;
   equalsResult = true;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    // comprobamos si son instancias virtuales.
    String elementFirstClearName =
     elementFirstParam.getClass().getTypeName().split("\\$", 0)[0]; //$NON-NLS-1$
    String elementSecondClearName =
     elementSecondParam.getClass().getTypeName().split("\\$", 0)[0]; //$NON-NLS-1$
    if(!(elementFirstClearName.equals(elementSecondClearName))) {
     equalsResult = false;
    } else {
     equalsResult = true;
    }
   } else {
    equalsResult = true;
   }
   if(equalsResult) {
    if (elementFirstParam.size() != elementSecondParam.size()) {
     equalsResult = false;
    } else {
     Iterator<?> iteratorFirst = elementFirstParam.entrySet().iterator();
     Iterator<?> iteratorSecond = elementSecondParam.entrySet().iterator();
     while (iteratorFirst.hasNext() && iteratorSecond.hasNext()) {
      Entry<?, ?> entryFirst = (Entry<?, ?>) iteratorFirst.next();
      Entry<?, ?> entrySecond = (Entry<?, ?>) iteratorSecond.next();
      if (!(entryFirst.getClass().equals(entrySecond.getClass()))) {
       equalsResult = false;
       break;
      }
      if (!(entryFirst.getKey().equals(entrySecond.getKey()))) {
       equalsResult = false;
       break;
      }
      if (!(entryFirst.getValue().equals(entrySecond.getValue()))) {
       equalsResult = false;
       break;
      }
      equalsResult = true;
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'SortedMap' de tipo 'TreeMap'
    * contra otra variable 'SortedMap' de tipo objeto 'TreeMap'.
    *
    * @param elementFirstParam SortedMap<?, ?>
    * @param elementSecondParam SortedMap<?, ?>
    * @return boolean
    */
  public static boolean equalsDataTreeMap(
         final SortedMap<?, ?> elementFirstParam,
         final SortedMap<?, ?> elementSecondParam) {
   boolean equalsResult;
   equalsResult = true;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    // comprobamos si son instancias virtuales.
    String elementFirstClearName =
     elementFirstParam.getClass().getTypeName().split("\\$", 0)[0]; //$NON-NLS-1$
    String elementSecondClearName =
     elementSecondParam.getClass().getTypeName().split("\\$", 0)[0]; //$NON-NLS-1$
    if(!(elementFirstClearName.equals(elementSecondClearName))) {
     equalsResult = false;
    } else {
     equalsResult = true;
    }
   } else {
    equalsResult = true;
   }
   if(equalsResult) {
    if (elementFirstParam.size() != elementSecondParam.size()) {
     equalsResult = false;
    } else {
     Iterator<?> iteratorFirst = elementFirstParam.entrySet().iterator();
     Iterator<?> iteratorSecond = elementSecondParam.entrySet().iterator();
     while (iteratorFirst.hasNext() && iteratorSecond.hasNext()) {
      Entry<?, ?> entryFirst = (Entry<?, ?>) iteratorFirst.next();
      Entry<?, ?> entrySecond = (Entry<?, ?>) iteratorSecond.next();
      if (!(entryFirst.getClass().equals(entrySecond.getClass()))) {
       equalsResult = false;
       break;
      }
      if (!(entryFirst.getKey().equals(entrySecond.getKey()))) {
       equalsResult = false;
       break;
      }
      if (!(entryFirst.getValue().equals(entrySecond.getValue()))) {
       equalsResult = false;
       break;
      }
      equalsResult = true;
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'Set' de tipo 'HashSet'
    * contra otra variable 'Set' de tipo objeto 'HashSet'.
    *
    * @param elementFirstParam Set<?>
    * @param elementSecondParam Set<?>
    * @return boolean
    */
  public static boolean equalsDataHashSet(
         final Set<?> elementFirstParam,
         final Set<?> elementSecondParam) {
   boolean equalsResult;
   equalsResult = true;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   if(equalsResult) {
    if (elementFirstParam.size() != elementSecondParam.size()) {
     equalsResult = false;
    } else {
     Iterator<?> iteratorFirst = elementFirstParam.iterator();
     Iterator<?> iteratorSecond = elementSecondParam.iterator();
     while (iteratorFirst.hasNext() && iteratorSecond.hasNext()) {
      Object entryFirst = iteratorFirst.next();
      Object entrySecond = iteratorSecond.next();
      if (!(entryFirst.getClass().equals(entrySecond.getClass()))) {
       equalsResult = false;
       break;
      }
      if (!(equalsDataObject(entryFirst, entrySecond))) {
       equalsResult = false;
       break;
      }
     }
    }
   }
   return equalsResult;
  }

  /**
    * Método estático para la comparación de variables 'SortedSet' de tipo 'TreeSet'
    * contra otra variable 'SortedSet' de tipo objeto 'TreeSet'.
    *
    * @param elementFirstParam Set<?>
    * @param elementSecondParam Set<?>
    * @return boolean
    */
  public static boolean equalsDataTreeSet(
         final SortedSet<?> elementFirstParam,
         final SortedSet<?> elementSecondParam) {
   boolean equalsResult;
   equalsResult = true;
   if (!(elementFirstParam.getClass().equals(elementSecondParam.getClass()))) {
    equalsResult = false;
   } else {
    equalsResult = true;
   }
   if(equalsResult) {
    if (elementFirstParam.size() != elementSecondParam.size()) {
     equalsResult = false;
    } else {
     Iterator<?> iteratorFirst = elementFirstParam.iterator();
     Iterator<?> iteratorSecond = elementSecondParam.iterator();
     while (iteratorFirst.hasNext() && iteratorSecond.hasNext()) {
      Object entryFirst = iteratorFirst.next();
      Object entrySecond = iteratorSecond.next();
      if (!(entryFirst.getClass().equals(entrySecond.getClass()))) {
       equalsResult = false;
       break;
      }
      if (!(equalsDataObject(entryFirst, entrySecond))) {
       equalsResult = false;
       break;
      }
     }
    }
   }
   return equalsResult;
  }

 }
