package org.eclipse.swt.snippets;

/**
 * VerifyBigDecimalListener.java 22-ene-2013
 *
 * Copyright 2013 INDITEX.
 * Departamento de Sistemas
 */

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;


/**
 * Clase para verificar un número. Utilizar mediante la factoría
 * <code>com.inditex.financiero.sfi.ui.utils.ComponentsFactory.newTextBigDecimal(
 * Composite, VerifyBigDecimalListener)</code> o añadiendo al campo de texto un verify y focus listener
 * 
 * @author <a href="josel.lopez@tecnocom.es">T08064</a>
 */
public class VerifyBigDecimalListener implements VerifyListener, FocusListener {

    private static final int DEFAULT_DECIMALS = 2;
    private static final int DEFAULT_MAX_SIZE = 12;
    private int decimals = DEFAULT_DECIMALS;
    private int maxSize = DEFAULT_MAX_SIZE;
    private final int CODE_DELETE = 8;
    private final int CODE_SPACE = 32;
    private final static int KEY_CODE_SEPARADOR_DECIMALES_TECLADO_NUMERICO = 16777262;
    private final DecimalFormat decimalFormat;
    private boolean conSigno = false;

    /**
     * Instancia un nuevo verify big decimal listener.
     */
    public VerifyBigDecimalListener() {
        this(DEFAULT_MAX_SIZE, DEFAULT_DECIMALS, false);
    }

    /**
     * Instancia un nuevo verify big decimal listener.
     * 
     * @param decimals
     *            decimals
     */
    public VerifyBigDecimalListener(final int decimals) {
        this(DEFAULT_MAX_SIZE, decimals, false);
    }

    /**
     * Instancia un nuevo verify big decimal listener.
     * 
     * @param maxSize
     *            max size
     * @param decimals
     *            decimals
     */
    public VerifyBigDecimalListener(final int maxSize, final int decimals) {
        this(maxSize, decimals, false);
    }

    /**
     * Instancia un nuevo verify big decimal listener.
     * 
     * @param maxSize
     *            max size
     * @param decimals
     *            decimals
     * @param conSigno
     *            con signo
     */
    public VerifyBigDecimalListener(final int maxSize, final int decimals, final boolean conSigno) {
        this.decimals = decimals;
        this.maxSize = maxSize;
        this.conSigno = conSigno;

        this.decimalFormat = MathUtil.getDecimalFormat(LocaleUtils.getLocale(), decimals);
        this.decimalFormat.setGroupingUsed(false);
    }

    private boolean inicializacion = false;

    /**
     * Chequea si inicializacion.
     * 
     * @return true, si inicializacion
     */
    public boolean isInicializacion() {
        return this.inicializacion;
    }

    /**
     * Establece inicializacion.
     * 
     * @param inicializacion
     *            nuevo inicializacion
     */
    public void setInicializacion(final boolean inicializacion) {
        this.inicializacion = inicializacion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void verifyText(final VerifyEvent event) {
        final String decimalSeparator = String.valueOf(this.decimalFormat.getDecimalFormatSymbols()
                .getDecimalSeparator());

        if (this.inicializacion) {
            return;
        }

        final String stringOriginal = ((Text) event.getSource()).getText();

        if (event.keyCode == KEY_CODE_SEPARADOR_DECIMALES_TECLADO_NUMERICO) {
            event.text = decimalSeparator;
        }

        final String text = (stringOriginal != null) ? stringOriginal.substring(0, event.start) + event.text
                + stringOriginal.substring(event.end) : event.text;

        if (StringUtils.isBlank(text)) {
            event.doit = true;
            return;
        }

        if (this.conSigno && text.equals("-")) {
            event.doit = true;
            return;
        }

        try {
            final BigDecimal bigDecimal = parseNumber(text);

            if (superaLongitudMaxima(bigDecimal)) {
                event.doit = false;
                return;
            }

            event.doit = true;
            return;
        } catch (final ParseException e) {
            event.doit = false;
            return;
        }

    }

    private BigDecimal parseNumber(final String text) throws ParseException {
        final String decimalSeparator = String.valueOf(this.decimalFormat.getDecimalFormatSymbols()
                .getDecimalSeparator());
        final String groupingSeparator = String.valueOf(this.decimalFormat.getDecimalFormatSymbols()
                .getGroupingSeparator());

        if (!text.matches("(\\-)?[\\d\\" + groupingSeparator + "]+(\\" + decimalSeparator + "\\d*)?")) {
            throw new ParseException("Formato de número incorrecto", 0);
        }

        return new BigDecimal(this.decimalFormat.parse(text.replace(groupingSeparator, "")).toString());
    }

    private String formatNumber(final Number number) {

        this.decimalFormat.setMaximumFractionDigits(this.decimals);
        this.decimalFormat.setMinimumFractionDigits(this.decimals);
        final String decimalSeparator = String.valueOf(this.decimalFormat.getDecimalFormatSymbols()
                .getDecimalSeparator());
        final String groupingSeparator = String.valueOf(this.decimalFormat.getDecimalFormatSymbols()
                .getGroupingSeparator());

        String parsed = this.decimalFormat.format(number);

        if (parsed.matches("[\\d\\" + groupingSeparator + "]+")) {
            parsed += decimalSeparator;
            for (int i = 0; i < this.decimals; i++) {
                parsed += "0";
            }
        }

        return parsed;
    }

    private boolean superaLongitudMaxima(final Number number) {
        if (number == null) {
            return false;
        }
        final String numberS = number.toString();
        final int indexSeparador = numberS.indexOf(".") == -1 ? numberS.length() : numberS.indexOf(".");
        final String parteEntera = numberS.substring(0, indexSeparador);
        String parteDecimal;
        if (indexSeparador < numberS.length()) {
            parteDecimal = numberS.substring(indexSeparador + 1, numberS.length());
        } else {
            parteDecimal = "";
        }

        return (parteDecimal.length() > this.decimals) || ((parteEntera.length() + this.decimals) > this.maxSize);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusGained(final FocusEvent arg0) {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void focusLost(final FocusEvent arg0) {
        final Text text = (Text) arg0.getSource();

        if (this.conSigno) {
            if ("-".equals(text.getText())) {
                text.setText("");
            }
        }

        try {
            text.setText(formatNumber(parseNumber(text.getText())));
        } catch (final ParseException e) {
            text.setText("");
        }

    }
}
