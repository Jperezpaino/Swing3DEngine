package org.eclipse.swt.snippets;

import java.text.DecimalFormat;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;


/**
 * The Class VerifyNumberListener.
 * 
 * @author <a href="-">-</a>
 */
public abstract class VerifyNumberListener implements VerifyListener {
    private final DecimalFormat decimalFormat = MathUtil.getDecimalFormat(LocaleUtils.getLocale());

    private final boolean replaceGroupingSeparator;

    /**
     * Instancia un nuevo verify number listener.
     */
    public VerifyNumberListener() {
        this(true);
    }

    /**
     * Permite decidir si quieres que el 'verificador' elimine los separadores de miles o no.
     * 
     * @param replaceGroupingSeparator
     *            replace grouping separator
     */
    public VerifyNumberListener(final boolean replaceGroupingSeparator) {
        super();
        this.replaceGroupingSeparator = replaceGroupingSeparator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void verifyText(final VerifyEvent event) {

        if (this.replaceGroupingSeparator) {
            // eliesbc: No se por que se metio este zurullo indicente, lo dejo por compatibilidad hacia atras
            // TODO Se hace para evitar el problema de los enteros en formBean cuando se escriben a campos de la vista
            // (Lo hace el proceso de bindings... y no sabemos cómo cambiar este comportamiento)
            final String groupingSeparator = String.valueOf(this.decimalFormat.getDecimalFormatSymbols()
                    .getGroupingSeparator());
            event.text = event.text.replace(groupingSeparator, "");
        }

        if (!validParsing(event)) {
            event.doit = false;
            return;
        }
    }

    /**
     * Valid parsing.
     * 
     * @param event
     *            event
     * @return true, si termina correctamente
     */
    protected abstract boolean validParsing(VerifyEvent event);
}
