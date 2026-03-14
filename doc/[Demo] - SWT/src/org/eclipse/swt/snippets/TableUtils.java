package org.eclipse.swt.snippets;

import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;

import com.inditex.financiero.sfi.ui.widgets.table.vo.TableLabelColorProvider;

/**
 * Clase de utileria para tablas.
 * 
 * @author <a href="victor.alvarez.ext@tecnocom.es">Victor Alvarez Alvarez</a>
 * 
 */
public class TableUtils {

    /**
     * Alta para filas con buscadores.
     */
    public static final int ROW_HEIGHT_FOR_BUSCADORES = 23;

    /**
     * Se indica el alto para las filas de una tabla.
     * 
     * 
     * @param table
     * @param height
     */
    public static void setRowHeight(final Table table, final int height) {

        table.addListener(SWT.MeasureItem, new Listener() {
            @Override
            public void handleEvent(final Event event) {
                event.height = height;
            }
        });
    }
    
    public static Table initTable(final Composite parent) {
        final Table table = new Table(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        return table;
    }

    public static CheckboxTableViewer initTableViewer(final Table table, final TableLabelColorProvider tableLabelColorProvider) {
        final CheckboxTableViewer tableViewer = new CheckboxTableViewer(table);
        tableViewer.setUseHashlookup(true);
        tableViewer.setContentProvider(new ObservableListContentProvider());
        tableViewer.setLabelProvider(tableLabelColorProvider);
        return tableViewer;
    }

}
