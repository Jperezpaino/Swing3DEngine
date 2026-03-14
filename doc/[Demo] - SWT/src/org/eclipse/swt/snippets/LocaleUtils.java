package org.eclipse.swt.snippets;



import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

/**
 * The Class LocaleUtils.
 * 
 * @author <a href="-">-</a>
 */
public class LocaleUtils {

    private static LocaleUtils instancia = new LocaleUtils();
    private static String formatoFecha = "";
    private static SimpleDateFormat formateadorFechaHoraUTC = null;
    private static SimpleDateFormat formateadorFechaHora = null;
    private static SimpleDateFormat formateadorFecha = null;
    private static NumberFormat numberFormat;
    private static SimpleDateFormat formateadorFechaDefault;
    private static Map<String, String> formatoFechaMap;

    // Las fechas se muestran siempre con este timezone
    // que es como se gurdan en BD
    private static final TimeZone TIME_ZONE_UTC = TimeZone.getTimeZone("UTC");
    private static final String FORMATOFECHADEFECTO = "dd/MM/yyyy";

    /**
     * Instancia un nuevo locale utils.
     */
    public LocaleUtils() {
        numberFormat = NumberFormat.getIntegerInstance(Locale.getDefault());
        formatoFechaMap = new HashMap<String, String>();
        formatoFechaMap.put("ES_ES", "dd/MM/yyyy");
        formatoFechaMap.put("EN_GB", "dd/MM/yyyy");
        formatoFechaMap.put("EN_US", "MM/dd/yyyy");
    }

    /**
     * Obtiene instancia.
     * 
     * @return instancia
     */
    public static LocaleUtils getInstancia() {
        return instancia;
    }

    /**
     * To fecha.
     * 
     * @param cal
     *            cal
     * @return the string
     */
    public static String toFecha(final Calendar cal) {
        return toFecha(cal.getTime());
    }

    /**
     * To fecha.
     * 
     * @param fecha
     *            fecha
     * @return the string
     */
    public static String toFecha(final Date fecha) {
        return formateadorFecha.format(fecha);
    }

    /**
     * To fecha default.
     * 
     * @param fecha
     *            fecha
     * @return the string
     */
    public static String toFechaDefault(final Date fecha) {
        return formateadorFechaDefault.format(fecha);
    }

    /**
     * To fecha hora.
     * 
     * @param cal
     *            cal
     * @return the string
     */
    public static String toFechaHora(final Calendar cal) {
        return toFechaHora(cal.getTime());
    }

    /**
     * To fecha hora.
     * 
     * @param fecha
     *            fecha
     * @return the string
     */
    public static String toFechaHora(final Date fecha) {
        final String result = formateadorFechaHoraUTC.format(fecha);
        return result;
    }

    /**
     * To fecha hora sin cambio autc.
     * 
     * @param fecha
     *            fecha
     * @return the string
     */
    public static String toFechaHoraSinCambioAUTC(final Date fecha) {
        final String result = formateadorFechaHora.format(fecha);
        return result;
    }

    /**
     * Obtiene locale.
     * 
     * @return locale
     */
    public static Locale getLocale() {
        return Locale.getDefault();
    }

    /**
     * Establece locale.
     * 
     * @param loc
     *            nuevo locale
     */
    public static void setLocale(final String loc) {
        Locale.setDefault(new Locale(loc.substring(0, 2), loc.substring(3)));
        numberFormat = NumberFormat.getIntegerInstance(Locale.getDefault());
        formatearNumberFormat();
        formatoFecha = getFinalFormatoFecha();
        formateadorFechaHora = new SimpleDateFormat(formatoFecha + " HH:mm:ss");
        formateadorFechaHoraUTC = new SimpleDateFormat(formatoFecha + " HH:mm:ss");
        formateadorFechaHoraUTC.setTimeZone(TIME_ZONE_UTC);
        formateadorFecha = new SimpleDateFormat(formatoFecha);
        formateadorFechaDefault = new SimpleDateFormat("yyyy/MM/dd");
    }

    /**
     * Obtiene locale as default.
     * 
     * @return locale as default
     */
    public static String getLocaleAsDefault() {
        return getLocale().toString();
    }

    /**
     * Obtiene locale as inditex.
     * 
     * @return locale as inditex
     */
    public static String getLocaleAsInditex() {
        final Locale locale = getLocale();
        return locale.getLanguage() + "-" + locale.getCountry();
    }

    /**
     * Obtiene number format.
     * 
     * @return number format
     */
    public static NumberFormat getNumberFormat() {
        return numberFormat;
    }

    private static void formatearNumberFormat() {
        numberFormat.setGroupingUsed(true);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
    }

    /**
     * Obtiene final formato fecha.
     * 
     * @return final formato fecha
     */
    public static String getFinalFormatoFecha() {
        final Iterator<Entry<String, String>> it = formatoFechaMap.entrySet().iterator();
        while (it.hasNext()) {
            final Map.Entry<String, String> entrada = it.next();
            if (entrada.getKey().equalsIgnoreCase(
                    LocaleUtils.getLocale().toString())) {
                return entrada.getValue();
            }
        }
        return FORMATOFECHADEFECTO;
    }

    /**
     * Obtiene formato fecha.
     * 
     * @return the formatoFecha
     */
    public static String getFormatoFecha() {
        return formatoFecha;
    }

    /**
     * Establece formato fecha.
     * 
     * @param formatoFecha
     *            the formatoFecha to set
     */
    public static void setFormatoFecha(final String formatoFecha) {
        LocaleUtils.formatoFecha = formatoFecha;
    }
    
    /**
     * Obtiene messages.
     * 
     * @return messages
     */
    public static ResourceBundle getMessages() {
        return ResourceBundle.getBundle("messages/messages");
    }

    /**
     * Obtiene message value.
     * 
     * @param key
     *            key
     * @return message value
     */
    public static String getMessageValue(final String key) {
//        try {
//            return getMessages().getString(key);
//        } catch (final MissingResourceException mre) {
//            return key + "*";
//        }
        
        return "Agrupacion Divisa";
    }
    
}
