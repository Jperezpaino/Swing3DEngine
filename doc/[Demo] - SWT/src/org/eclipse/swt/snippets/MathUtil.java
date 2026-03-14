package org.eclipse.swt.snippets;

/**
 * MathUtil.java 04-dic-2012
 *
 * Copyright 2012 INDITEX.
 * Departamento de Sistemas
 */
import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * Clase utilidad para realizar operaciones matematicas y logicas con.
 * 
 * {@link BigDecimal}. Los objetivos de estas operaciones son:
 * <ul>
 * <li>Son operaciones null-safe. El valor <code>null</code> su sustituye por la constante <code>ZERO</code> definida en
 * {@link BigDecimal}.</li>
 * <li>Son operaciones eficientes. Minimizan el nº de objetos {@link BigDecimal} que se instancian, en caso de que uno
 * de los operandos de una operacion sea el elemento neutro, se devuelve el otro operando. Por tanto, no se realizar la
 * operación y no se crea el objeto resultante de la misma.</li>
 * </ul>
 * 
 * @author <a href="mailto:elias.breijo@tecnocom.es">Elías Breijo Casás</a>
 */
public class MathUtil {

    //private static final Log log = LogFactory.getLog(MathUtil.class);

    private static final int DEFAULT_PRECISSION = 4;

    /** The Constant MINUS_ONE. */
    public static final BigDecimal MINUS_ONE = ZERO.subtract(ONE);

    /** The Constant MATH_CONTEXT. */
    public static final MathContext MATH_CONTEXT = new MathContext(15);

    private static final Map<Integer, DecimalFormat> DECIMAL_FORMATS = new HashMap<Integer, DecimalFormat>();

    private static final Locale DEFAULT_LOCALE = new Locale("es", "ES");

    private MathUtil() {
    }

    /**
     * Chequea si zero.
     * 
     * @param d
     *            d
     * @return true, si zero
     */
    public static boolean isZero(final BigDecimal d) {
        return (d == null) || (ZERO.compareTo(d) == 0);
    }

    /**
     * Anade el.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return the big decimal
     */
    public static BigDecimal add(final BigDecimal a, final BigDecimal b) {
        if (isZero(a)) {
            return b;
        }
        if (isZero(b)) {
            return a;
        }
        return a.add(b, MATH_CONTEXT);
    }

    /**
     * Subtract.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return the big decimal
     */
    public static BigDecimal subtract(BigDecimal a, final BigDecimal b) {
        if (isZero(b)) {
            return a;
        }
        if (a == null) {
            a = ZERO;
        }
        return a.subtract(b, MATH_CONTEXT);
    }

    /**
     * Multiply.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return the big decimal
     */
    public static BigDecimal multiply(final BigDecimal a, final BigDecimal b) {
        if (isZero(a) || isZero(b)) {
            return ZERO;
        }
        if (ONE.compareTo(a) == 0) {
            return b;
        }
        if (ONE.compareTo(b) == 0) {
            return a;
        }
        return a.multiply(b, MATH_CONTEXT);
    }

    /**
     * Divide.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return the big decimal
     */
    public static BigDecimal divide(final BigDecimal a, final BigDecimal b) {
        if (isZero(a)) {
            return ZERO;
        }
        if (isZero(b)) {
            throw new ArithmeticException("Division por cero");
        }
        if (ONE.compareTo(b) == 0) {
            return a;
        }
        return a.divide(b, MATH_CONTEXT);
    }

    /**
     * Format.
     * 
     * @param d
     *            d
     * @param locale
     *            locale
     * @return the string
     */
    public static String format(final BigDecimal d, final Locale locale) {
        return getDecimalFormat(locale).format(d == null ? ZERO : d);
    }

    /**
     * Format.
     * 
     * @param d
     *            d
     * @param locale
     *            locale
     * @param decimals
     *            decimals
     * @return the string
     */
    public static String format(final BigDecimal d, final Locale locale, final int decimals) {
        return getDecimalFormat(locale, decimals).format(d == null ? ZERO : d);
    }

    /**
     * Parsea a.
     * 
     * @param d
     *            d
     * @param locale
     *            locale
     * @return the big decimal
     * @throws ParseException
     *             de parse exception
     */
    public static BigDecimal parse(final String d, final Locale locale) throws ParseException {
        return parse(d, locale, DEFAULT_PRECISSION);
    }

    /**
     * Parsea a.
     * 
     * @param d
     *            d
     * @param locale
     *            locale
     * @param decimals
     *            decimals
     * @return the big decimal
     * @throws ParseException
     *             de parse exception
     */
    public static BigDecimal parse(final String d, final Locale locale, final int decimals) throws ParseException {
        BigDecimal retValue = null;
        if (StringUtils.isNotEmpty(d)) {
            final Object value = getDecimalFormat(locale, decimals).parseObject(d);
            if (value != null) {
                if (value instanceof BigDecimal) {
                    retValue = (BigDecimal) value;
                } else if (value instanceof Long) {
                    retValue = new BigDecimal(((Long) value).longValue());
                } else {
                    retValue = new BigDecimal(value.toString());
                }
            }
        }
        return retValue;
    }

    /**
     * Obtiene decimal format.
     * 
     * @param locale
     *            locale
     * @return decimal format
     */
    public static DecimalFormat getDecimalFormat(final Locale locale) {
        return getDecimalFormat(locale, 4);
    }

    /**
     * Obtiene decimal format.
     * 
     * @param locale
     *            locale
     * @param decimals
     *            decimals
     * @return decimal format
     */
    public static DecimalFormat getDecimalFormat(final Locale locale, final int decimals) {

        final int hash = hashCode(locale, decimals);
        DecimalFormat decimalFormat = DECIMAL_FORMATS.get(Integer.valueOf(hash));

        if (decimalFormat == null) {
            synchronized (DECIMAL_FORMATS) {
                decimalFormat = DECIMAL_FORMATS.get(Integer.valueOf(hash));
                if (decimalFormat == null) {
                    decimalFormat = (DecimalFormat) NumberFormat.getInstance(locale == null ? DEFAULT_LOCALE : locale);
                    decimalFormat.setMaximumFractionDigits(decimals);
                    decimalFormat.setMinimumFractionDigits(decimals);
                    decimalFormat.setParseBigDecimal(true);
                    /*if (log.isDebugEnabled()) {
                        log.debug(String.format("Creando DecimalFormat[%s, %d]", locale, Integer.valueOf(decimals)));
                    }*/
                    DECIMAL_FORMATS.put(Integer.valueOf(hash), decimalFormat);
                }
            }
        }

        return decimalFormat;
    }

    private static int hashCode(final Locale locale, final int decimals) {
        return 31 * decimals * locale.hashCode();
    }

    /**
     * Compare.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return the int
     */
    public static int compare(BigDecimal a, BigDecimal b) {
        if (a == null) {
            a = ZERO;
        }
        if (b == null) {
            b = ZERO;
        }
        if (a == b) {
            return 0;
        }
        return a.compareTo(b);
    }

    /**
     * Gt.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return true, si termina correctamente
     */
    public static boolean gt(final BigDecimal a, final BigDecimal b) {
        return compare(a, b) > 0;
    }

    /**
     * Ge.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return true, si termina correctamente
     */
    public static boolean ge(final BigDecimal a, final BigDecimal b) {
        return compare(a, b) >= 0;
    }

    /**
     * Lt.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return true, si termina correctamente
     */
    public static boolean lt(final BigDecimal a, final BigDecimal b) {
        return compare(a, b) < 0;
    }

    /**
     * Le.
     * 
     * @param a
     *            a
     * @param b
     *            b
     * @return true, si termina correctamente
     */
    public static boolean le(final BigDecimal a, final BigDecimal b) {
        return compare(a, b) <= 0;
    }

    /**
     * Abs.
     * 
     * @param a
     *            a
     * @return the big decimal
     */
    public static BigDecimal abs(final BigDecimal a) {
        if (ge(a, ZERO)) {
            return a;
        }
        return ZERO.subtract(a);
    }

    /**
     * Metodo Main.
     * 
     * @param args
     *            argumentos de entrada
     * @throws ParseException
     *             de parse exception
     */
    public static void main(final String[] args) throws ParseException {

        /*
         * long t; int count = 100000;
         * 
         * BigDecimal[] nos = new BigDecimal[count]; double d = -1; double signo = 1; for (int i = 0; i < count; i++) {
         * signo *= d; nos[i] = new BigDecimal(signo * Math.random(), MATH_CONTEXT); }
         * 
         * BigDecimal[] nos1 = new BigDecimal[count]; t = System.currentTimeMillis(); for (int i = 0; i < count; i++) {
         * nos1[i] = abs(nos[i]); } System.out.println("t1 = " + (System.currentTimeMillis() - t));
         * 
         * BigDecimal[] nos2 = new BigDecimal[count]; t = System.currentTimeMillis(); for (int i = 0; i < count; i++) {
         * nos2[i] = nos[i].abs(MATH_CONTEXT); } System.out.println("t2 = " + (System.currentTimeMillis() - t));
         * 
         * for (int i = 0; i < count; i++) { if (!nos1[i].s(nos2[i])) throw new IllegalStateException(nos1[i] + " != " +
         * nos2[i]); } System.out.println(parse("-0,3", es)); System.out.println(parse("-3", es));
         */

        final Locale es = new Locale("es", "ES");
        final Locale enGb = new Locale("en", "GB");
        final Locale enUs = new Locale("en", "US");

        final BigDecimal a = parse("0,1234", es);
        final BigDecimal b = parse("0,2345", es);
        final BigDecimal c = parse("0,3456", es);
        final BigDecimal d = add(b, c);
        final BigDecimal e = divide(b, parse("2", es));
        final BigDecimal uno = parse("1", es);

        System.out.println("Uno + a : " + (format(add(a, uno), es)));

        System.out.println("a: " + a);
        System.out.println("b: " + b);
        System.out.println("c: " + c);
        System.out.println("(b+c): " + d);
        System.out.println("(b/2): " + e);

        System.out.println();

        System.out.println("format a " + format(a, es));
        System.out.println("format b " + format(b, es));
        System.out.println("format c " + format(c, es));
        System.out.println("format (b+c) " + format(d, es));
        System.out.println("format (b/2) " + format(e, es));

        final BigDecimal currency = parse("726,2463", es);
        final BigDecimal exchange = parse("1,3857", es);

        System.out.println(format(divide(currency, exchange), es));

        System.out.println(getDecimalFormat(es).getDecimalFormatSymbols().getDecimalSeparator());
        System.out.println(getDecimalFormat(enGb).getDecimalFormatSymbols().getDecimalSeparator());
        System.out.println(getDecimalFormat(enUs).getDecimalFormatSymbols().getDecimalSeparator());
    }

}
