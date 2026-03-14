package org.eclipse.swt.snippets;

import org.apache.commons.lang.StringUtils;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Text;

/**
 * The Class VerifyIntegerListener.
 * 
 * @author <a href="-">-</a>
 */
public class VerifyIntegerListener extends VerifyNumberListener implements FocusListener {

    private final int CODE_SPACE = 32;

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean validParsing(final VerifyEvent event) {
        boolean valid = true;

        final Object source = event.getSource();

        if (source == null) {
            event.doit = false;
            return false;
        }

        if (event.keyCode == this.CODE_SPACE) {
            return false;
        }

        String oldS = "";

        // Casteo del widget origen
        if (source instanceof Text) {
            oldS = ((Text) source).getText();
        } else if (source instanceof StyledText) {
            oldS = ((StyledText) source).getText();
        }

        final String texto = (oldS != null) ? oldS.substring(0, event.start) + event.text + oldS.substring(event.end)
                : event.text;

        // Permite borrar el texto
        if ((texto == null) || texto.equals("")) {
            return true;
        }

        // Comprobamos si el texto final se puede parsear a integer
        try {

         // Comprobamos el String filtrando caracteres especiales
            Integer.parseInt(filtrarCaracteresEspeciales(texto));

        } catch (final NumberFormatException e) {
            valid = false;
        }

        return valid;
    }

    @Override
    public void focusGained(final FocusEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void focusLost(final FocusEvent event) {
        final Text text = (Text) event.getSource();
        final String texto = text.getText();
        if (StringUtils.isNotBlank(texto)) {
            final Integer intParseado = Integer.parseInt(texto);
            text.setText(intParseado.toString());
        }
    }
    
    private String filtrarCaracteresEspeciales(String text) {
     
     text = text.replaceAll("(\\r|\\n|\\t)", "");
     
     return text;
    }
       
}
