package org.eclipse.swt.snippets;

import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
//import static com.inditex.financiero.sfi.ui.utils.InterfaceUtils.getBlanco;
//import static com.inditex.financiero.sfi.ui.utils.InterfaceUtils.getCursorHand;
//import static com.inditex.financiero.sfi.ui.utils.InterfaceUtils.getNormalFont8;
//
//import org.apache.commons.collections.KeyValue;
//import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
//import org.eclipse.jface.viewers.LabelProvider;
//import org.eclipse.jface.viewers.ListViewer;
//import org.eclipse.jface.viewers.TableViewer;
//import org.eclipse.jface.viewers.TreeViewer;
//import org.eclipse.nebula.widgets.datechooser.DateChooserCombo;
import org.eclipse.nebula.widgets.formattedtext.DateFormatter;
//import org.eclipse.nebula.widgets.formattedtext.FormattedText;
//import org.eclipse.nebula.widgets.pgroup.MinMaxToggleRenderer;
//import org.eclipse.nebula.widgets.pgroup.PGroup;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.browser.ProgressAdapter;
//import org.eclipse.swt.browser.ProgressEvent;
//import org.eclipse.swt.custom.CCombo;
//import org.eclipse.swt.custom.CTabFolder;
//import org.eclipse.swt.custom.CTabItem;
//import org.eclipse.swt.custom.StyledText;
//import org.eclipse.swt.events.FocusAdapter;
//import org.eclipse.swt.events.FocusEvent;
//import org.eclipse.swt.events.KeyEvent;
//import org.eclipse.swt.events.KeyListener;
//import org.eclipse.swt.events.MouseEvent;
//import org.eclipse.swt.events.MouseListener;
//import org.eclipse.swt.events.SelectionAdapter;
//import org.eclipse.swt.events.SelectionEvent;
//import org.eclipse.swt.events.VerifyListener;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.graphics.Point;
//import org.eclipse.swt.layout.FormAttachment;
//import org.eclipse.swt.layout.FormData;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Link;
//import org.eclipse.swt.widgets.List;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.TableColumn;
//import org.eclipse.swt.widgets.TableItem;
//import org.eclipse.swt.widgets.Text;
//import org.eclipse.swt.widgets.ToolBar;
//import org.eclipse.swt.widgets.ToolItem;
//import org.eclipse.swt.widgets.Tree;
//import org.eclipse.swt.widgets.TreeColumn;
//
//import com.inditex.financiero.sfi.conf.UICommonsConstants;
//import com.inditex.financiero.sfi.ui.base.SFICompositeCharging;
//import com.inditex.financiero.sfi.ui.command.UICommand;
//import com.inditex.financiero.sfi.ui.listeners.DrawFocusControlListener;
//import com.inditex.financiero.sfi.ui.listeners.TableSortSelectionListener;
//import com.inditex.financiero.sfi.ui.utils.validacion.VerifyBigDecimalListener;
//import com.inditex.financiero.sfi.ui.utils.validacion.VerifyIntegerListener;
//import com.inditex.financiero.sfi.ui.widgets.cargando.CargandoComposite;
//import com.inditex.financiero.sfi.ui.widgets.group.ITXRectangleGroupStrategy;
//import com.inditex.financiero.sfi.ui.window.resources.SFIResourceFactory;
//import com.inditex.financiero.sfi.ui.window.resources.SWTSFIResourceFactory;
//import com.inditex.financiero.sfi.ui.window.resources.stock.ImagesCommons;
//
///**
// * Factoría de los componentes que usaremos en la aplicación. Crea dichos componentes con los estilos básicos
// * inicializados (Borde, fondo, letra, ... ) manteniendo así una interfaz homogénea por toda la aplicación
// * 
// * @author T08194
// * 
// */

public class ComponentsFactory {

    //private static final int NUMBER_VISIBLE_ITEMS = 10;

    /**
     * @wbp.parser.entryPoint
     */
    private ComponentsFactory() {
    }

    /**
     * New composite.
     * 
     * @param parent
     *            parent
     * @return the composite
     * @wbp.parser.entryPoint
     */
    public static Composite newComposite(final Composite parent) {
        return newComposite(parent, SWT.NONE);
    }

    /**
     * New composite.
     * 
     * @param parent
     *            parent
     * @param style
     *            style
     * @return the composite
     * @wbp.parser.entryPoint
     */
    public static Composite newComposite(final Composite parent, final int style) {
        final Composite cmp = new Composite(parent, style);
        //cmp.setFont(InterfaceUtils.getDefaultFont8Text());
        //cmp.setBackground(InterfaceUtils.getBlanco());
        return cmp;
    }

    /**
     * New label.
     * 
     * @param parent
     *            parent
     * @param style
     *            style
     * @param text
     *            text
     * @return the label
     * @wbp.parser.entryPoint
     */
    public static Label newLabel(final Composite parent, final int style, final String text) {
        final Label lbl = new Label(parent, style);
        //lbl.setFont(InterfaceUtils.getDefaultFont8Text());
        //lbl.setBackground(getBlanco());
        if (text != null) {
            lbl.setText(text);
        }

        return lbl;
    }
//
//    /**
//     * New label.
//     * 
//     * @param parent
//     *            parent
//     * @param text
//     *            text
//     * @return the label
//     * @wbp.parser.entryPoint
//     */
//    public static Label newLabel(final Composite parent, final String text) {
//        final Label lbl = new Label(parent, SWT.NONE);
//        lbl.setFont(InterfaceUtils.getDefaultFont8Text());
//        lbl.setBackground(InterfaceUtils.getBlanco());
//        if (text != null) {
//            lbl.setText(text);
//        }
//
//        return lbl;
//    }
//
//    /**
//     * New label.
//     * 
//     * @param parent
//     *            parent
//     * @param style
//     *            style
//     * @return the label
//     * @wbp.parser.entryPoint
//     */
//    public static Label newLabel(final Composite parent, final int style) {
//        final Label lbl = new Label(parent, style);
//        lbl.setBackground(getBlanco());
//        lbl.setFont(InterfaceUtils.getDefaultFont8Text());
//        return lbl;
//    }
//
//    /**
//     * New label.
//     * 
//     * @param parent
//     *            parent
//     * @return the label
//     * @wbp.parser.entryPoint
//     */
//    public static Label newLabel(final Composite parent) {
//        return newLabel(parent, null);
//    }
//
//    /**
//     * New text read only.
//     * 
//     * @param parent
//     *            parent
//     * @return the text
//     */
//    public static Text newTextReadOnly(final Composite parent) {
//        return newText(parent, SWT.BORDER | SWT.READ_ONLY, null);
//    }
//
    /**
     * New text.
     * 
     * @param parent
     *            parent
     * @return the text
     */
    public static Text newText(final Composite parent) {
        return newText(parent, SWT.BORDER, null);
    }

    /**
     * New text.
     * 
     * @param parent
     *            parent
     * @param text
     *            text
     * @return the text
     */
    public static Text newText(final Composite parent, final String text) {
        return newText(parent, SWT.BORDER, text);
    }

    /**
     * New text.
     * 
     * @param parent
     *            parent
     * @param flags
     *            flags
     * @return the text
     */
    public static Text newText(final Composite parent, final int flags) {
        return newText(parent, flags, null);
    }

    /**
     * New text.
     * 
     * @param parent
     *            parent
     * @param flags
     *            flags
     * @param text
     *            text
     * @return the text
     */
    public static Text newText(final Composite parent, final int flags, final String text) {
        return newText(parent, flags, text, null);
    }

    /**
     * New text.
     * 
     * @param parent
     *            parent
     * @param flags
     *            flags
     * @param text
     *            text
     * @param verifyListener
     *            verify listener
     * @return the text
     */
    public static Text newText(final Composite parent, final int flags, final String text,
            final VerifyListener verifyListener) {
        final Text txt = new Text(parent, flags);
        //txt.setFont(InterfaceUtils.getDefaultFont8Text());
        //txt.setBackground(getBlanco());
        if (text != null) {
            txt.setText(text);
        }
        if (verifyListener != null) {
            txt.addVerifyListener(verifyListener);
        }
        return txt;
    }
//
//    /**
//     * New text read only.
//     * 
//     * @param parent
//     *            parent
//     * @return the text
//     */
//    public static StyledText newStyledTextReadOnly(final Composite parent) {
//        return newStyledText(parent, SWT.BORDER | SWT.READ_ONLY, null);
//    }
//
//    /**
//     * New text.
//     * 
//     * @param parent
//     *            parent
//     * @return the text
//     */
//    public static StyledText newStyledText(final Composite parent) {
//        return newStyledText(parent, SWT.BORDER, null);
//    }
//
//    /**
//     * New text.
//     * 
//     * @param parent
//     *            parent
//     * @param text
//     *            text
//     * @return the text
//     */
//    public static StyledText newStyledText(final Composite parent, final String text) {
//        return newStyledText(parent, SWT.BORDER, text);
//    }
//
//    /**
//     * New text.
//     * 
//     * @param parent
//     *            parent
//     * @param flags
//     *            flags
//     * @return the text
//     */
//    public static StyledText newStyledText(final Composite parent, final int flags) {
//        return newStyledText(parent, flags, null);
//    }
//
//    /**
//     * New text.
//     * 
//     * @param parent
//     *            parent
//     * @param flags
//     *            flags
//     * @param text
//     *            text
//     * @return the text
//     */
//    public static StyledText newStyledText(final Composite parent, final int flags, final String text) {
//        return newStyledText(parent, flags, text, null);
//    }
//
//    /**
//     * New text.
//     * 
//     * @param parent
//     *            parent
//     * @param flags
//     *            flags
//     * @param text
//     *            text
//     * @param verifyListener
//     *            verify listener
//     * @return the text
//     */
//    public static StyledText newStyledText(final Composite parent, final int flags, final String text,
//            final VerifyListener verifyListener) {
//        final StyledText txt = new StyledText(parent, flags);
//        txt.setFont(InterfaceUtils.getDefaultFont8Text());
//        if (text != null) {
//            txt.setText(text);
//        }
//        if (verifyListener != null) {
//            txt.addVerifyListener(verifyListener);
//        }
//        return txt;
//    }
//
//    /**
//     * New label image.
//     * 
//     * @param parent
//     *            parent
//     * @param image
//     *            image
//     * @return the label
//     */
//    public static Label newLabelImage(final Composite parent, final Image image) {
//        final Label lbl = new Label(parent, SWT.NONE);
//        lbl.setImage(image);
//        lbl.setBackground(getBlanco());
//        lbl.setCursor(getCursorHand());
//        lbl.addFocusListener(DrawFocusControlListener.getIntance());
//
//        return lbl;
//    }
//
//    /**
//     * New label image.
//     * 
//     * @param parent
//     *            parent
//     * @param image
//     *            image
//     * @param listener
//     *            listener
//     * @return the label
//     * @wbp.parser.entryPoint
//     */
//    public static Label newLabelImage(
//            final Composite parent, final Image image, final LabelFocusableListener listener) {
//        final Label lbl = newLabelImage(parent, image);
//
//        lbl.addMouseListener(new MouseListener() {
//
//            @Override
//            public void mouseUp(final MouseEvent arg0) {
//            }
//
//            @Override
//            public void mouseDown(final MouseEvent arg0) {
//                listener.execute();
//            }
//
//            @Override
//            public void mouseDoubleClick(final MouseEvent arg0) {
//            }
//        });
//
//        lbl.addKeyListener(new KeyListener() {
//
//            @Override
//            public void keyReleased(final KeyEvent arg0) {
//            }
//
//            @Override
//            public void keyPressed(final KeyEvent event) {
//                if ((event.keyCode == UICommonsConstants.KEY_CODE_ENTER)
//                        || (event.keyCode == UICommonsConstants.KEY_CODE_ENTER_TECLADO_NUMERICO)) {
//                    listener.execute();
//                }
//
//            }
//        });
//
//        return lbl;
//    }
//
//    /**
//     * New label image.
//     * 
//     * @param parent
//     *            parent
//     * @param image
//     *            image
//     * @param cursor
//     *            cursor
//     * @return the label
//     */
//    public static Label newLabelImage(final Composite parent, final Image image, final boolean cursor) {
//        final Label lbl = new Label(parent, SWT.NONE);
//        lbl.setImage(image);
//        lbl.setBackground(getBlanco());
//        if (cursor) {
//            lbl.setCursor(getCursorHand());
//        }
//
//        return lbl;
//    }
//
//    /**
//     * New table column.
//     * 
//     * @param tableViewer
//     *            table viewer
//     * @param message
//     *            message
//     * @param comparable
//     *            comparable
//     * @return the table column
//     */
//    public static TableColumn newTableColumn(final TableViewer tableViewer, final String message,
//            final boolean comparable) {
//        return newTableColumn(tableViewer, SWT.NONE, message, comparable);
//    }
//
//    /**
//     * New table column.
//     * 
//     * @param tableViewer
//     *            table viewer
//     * @param message
//     *            message
//     * @param width
//     *            width
//     * @param comparable
//     *            comparable
//     * @return the table column
//     */
//    public static TableColumn newTableColumn(final TableViewer tableViewer, final String message, final int width,
//            final boolean comparable) {
//        return newTableColumn(tableViewer, SWT.NONE, message, width, comparable);
//    }
//
//    /**
//     * New table column.
//     * 
//     * @param tableViewer
//     *            table viewer
//     * @param style
//     *            style
//     * @param message
//     *            message
//     * @param comparable
//     *            comparable
//     * @return the table column
//     */
//    public static TableColumn newTableColumn(final TableViewer tableViewer, final int style, final String message,
//            final boolean comparable) {
//        return newTableColumn(tableViewer, style, message, 100, comparable);
//    }
//
//    /**
//     * New table column.
//     * 
//     * @param tableViewer
//     *            table viewer
//     * @param style
//     *            style
//     * @param message
//     *            message
//     * @param width
//     *            width
//     * @param comparable
//     *            comparable
//     * @return the table column
//     */
//    public static TableColumn newTableColumn(final TableViewer tableViewer, final int style, final String message,
//            final Integer width, final boolean comparable) {
//        return newTableColumn(tableViewer, style, message, width, comparable, false, true);
//    }
//
//    /**
//     * New table column.
//     * 
//     * @param tableViewer
//     *            table viewer
//     * @param style
//     *            style
//     * @param message
//     *            message
//     * @param width
//     *            width
//     * @param comparable
//     *            comparable
//     * @param moveable
//     *            moveable
//     * @param resizable
//     *            resizable
//     * @return the table column
//     */
//    public static TableColumn newTableColumn(final TableViewer tableViewer, final int style, final String message,
//            final Integer width,
//            final boolean comparable, final boolean moveable, final boolean resizable) {
//        final TableColumn column = new TableColumn(tableViewer.getTable(), style);
//        if (message != null) {
//            column.setText(message);
//        }
//        column.setMoveable(moveable);
//        column.setResizable(resizable);
//        if (width != null) {
//            column.setWidth(width);
//        }
//
//        if (comparable) {
//            final int columnIndex = tableViewer.getTable().getColumnCount() - 1;
//            new TableSortSelectionListener(tableViewer, columnIndex);
//
//        }
//        return column;
//    }
//
//    /**
//     * New table column check.
//     * 
//     * @param table
//     *            table
//     * @return the table column
//     */
//    public static TableColumn newTableColumnCheck(final Table table) {
//        return newTableColumnCheck(table, true);
//    }
//
//    /**
//     * New table column check.
//     * 
//     * @param table
//     *            table
//     * @param checkeable
//     *            checkeable
//     * @return the table column
//     */
//    public static TableColumn newTableColumnCheck(final Table table, final boolean checkeable) {
//        final TableColumn tableColumnCheck = new TableColumn(table, SWT.CENTER);
//        tableColumnCheck.setWidth(29);
//
//        if (checkeable) {
//            tableColumnCheck.setImage(SWTSFIResourceFactory.getImage(ImagesCommons.ICO_CHECKBOX_FALSE));
//            tableColumnCheck.addSelectionListener(new SelectionAdapter() {
//
//                private boolean checked = false;
//
//                @Override
//                public void widgetSelected(final SelectionEvent arg0) {
//                    // setCursor(InterfaceUtils.getCursorWait());
//                    table.setRedraw(false);
//
//                    if (!this.checked) {
//                        tableColumnCheck.setImage(SWTSFIResourceFactory.getImage(ImagesCommons.ICO_CHECKBOX_TRUE));
//                        for (final TableItem tableItem : table.getItems()) {
//                            tableItem.setChecked(true);
//                        }
//                        this.checked = true;
//                    } else {
//                        tableColumnCheck.setImage(SWTSFIResourceFactory.getImage(ImagesCommons.ICO_CHECKBOX_FALSE));
//                        for (final TableItem tableItem : table.getItems()) {
//                            tableItem.setChecked(false);
//                        }
//                        this.checked = false;
//                    }
//
//                    table.setRedraw(true);
//                    // setCursor(InterfaceUtils.getCursordefecto());
//                }
//
//            });
//        }
//
//        return tableColumnCheck;
//    }
//
//    /**
//     * New table column check.
//     * 
//     * @param tableViewer
//     *            table viewer
//     * @return the table column
//     */
//    public static TableColumn newTableColumnCheck(final TableViewer tableViewer) {
//        return newTableColumnCheck(tableViewer, true);
//    }
//
//    /**
//     * New table column check.
//     * 
//     * @param tableViewer
//     *            table viewer
//     * @param checkeable
//     *            checkeable
//     * @return the table column
//     */
//    public static TableColumn newTableColumnCheck(final TableViewer tableViewer, final boolean checkeable) {
//        final TableColumn tableColumnCheck = newTableColumn(tableViewer, SWT.CENTER, null, 29, false, false, false);
//
//        if (checkeable) {
//            tableColumnCheck.setImage(SWTSFIResourceFactory.getImage(ImagesCommons.ICO_CHECKBOX_FALSE));
//            tableColumnCheck.addSelectionListener(new SelectionAdapter() {
//
//                private boolean checked = false;
//
//                @Override
//                public void widgetSelected(final SelectionEvent arg0) {
//                    // setCursor(InterfaceUtils.getCursorWait());
//                    tableViewer.getTable().setRedraw(false);
//
//                    if (!this.checked) {
//                        tableColumnCheck.setImage(SWTSFIResourceFactory.getImage(ImagesCommons.ICO_CHECKBOX_TRUE));
//                        for (final TableItem tableItem : tableViewer.getTable().getItems()) {
//                            tableItem.setChecked(true);
//                        }
//                        this.checked = true;
//                    } else {
//                        tableColumnCheck.setImage(SWTSFIResourceFactory.getImage(ImagesCommons.ICO_CHECKBOX_FALSE));
//                        for (final TableItem tableItem : tableViewer.getTable().getItems()) {
//                            tableItem.setChecked(false);
//                        }
//                        this.checked = false;
//                    }
//
//                    tableViewer.getTable().setRedraw(true);
//                    // setCursor(InterfaceUtils.getCursordefecto());
//                }
//
//            });
//        }
//
//        return tableColumnCheck;
//    }
//
//    /**
//     * New tree column.
//     * 
//     * @param treeViewer
//     *            tree viewer
//     * @param message
//     *            message
//     * @return the tree column
//     */
//    public static TreeColumn newTreeColumn(final TreeViewer treeViewer, final String message) {
//
//        return newTreeColumn(treeViewer.getTree(), SWT.NONE, message, 100);
//    }
//
//    /**
//     * New tree column.
//     * 
//     * @param tree
//     *            tree
//     * @param message
//     *            message
//     * @param width
//     *            width
//     * @return the tree column
//     */
//    public static TreeColumn newTreeColumn(final Tree tree, final String message, final Integer width) {
//        return newTreeColumn(tree, SWT.NONE, message, width);
//    }
//
//    /**
//     * New tree column.
//     * 
//     * @param tree
//     *            tree
//     * @param style
//     *            style
//     * @param message
//     *            message
//     * @param width
//     *            width
//     * @return the tree column
//     */
//    public static TreeColumn newTreeColumn(
//            final Tree tree, final int style, final String message, final Integer width) {
//        final TreeColumn column = new TreeColumn(tree, style);
//        if (message != null) {
//            column.setText(message);
//        }
//        if (width != null) {
//            column.setWidth(width);
//        }
//        return column;
//    }
//
//    /**
//     * New c combo.
//     * 
//     * @param parent
//     *            parent
//     * @return the c combo
//     */
//    public static CCombo newCCombo(final Composite parent) {
//        // Para que la seleccion aparezca alineada a la izquierda
//        final CCombo cmb = new CCombo(parent, SWT.READ_ONLY | SWT.BORDER) {
//            @Override
//            public void select(final int index) {
//                super.select(index);
//                this.setSelection(new Point(0, 0));
//            }
//
//            @Override
//            protected void checkSubclass() {
//            }
//        };
//        cmb.addSelectionListener(new SelectionAdapter() {
//            @Override
//            public void widgetSelected(final SelectionEvent e) {
//                final CCombo cmb = (CCombo) e.getSource();
//                cmb.setSelection(new Point(0, 0));
//            }
//        });
//        cmb.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(final FocusEvent e) {
//                final CCombo cmb = (CCombo) e.getSource();
//                cmb.setSelection(new Point(0, 0));
//            }
//        });
//        cmb.setFont(InterfaceUtils.getDefaultFont8Text());
//        cmb.setBackground(getBlanco());
//        cmb.setEditable(false);
//        cmb.setVisibleItemCount(NUMBER_VISIBLE_ITEMS);
//        return cmb;
//    }
//
//    /**
//     * New combo.
//     * 
//     * @param parent
//     *            parent
//     * @return the combo
//     */
//    public static Combo newCombo(final Composite parent) {
//        // Para que la seleccion aparezca alineada a la izquierda
//        final Combo cmb = new Combo(parent, SWT.READ_ONLY | SWT.BORDER);
//        cmb.setFont(InterfaceUtils.getDefaultFont8Text());
//        cmb.setBackground(getBlanco());
//        cmb.setVisibleItemCount(NUMBER_VISIBLE_ITEMS);
//        return cmb;
//    }
//
//    /**
//     * New check combo.
//     * 
//     * @param parent
//     *            parent
//     * @param items
//     *            items
//     * @return the iTX check combo
//     */
//    public static ITXCheckCombo newCheckCombo(final Composite parent, final java.util.List<KeyValue> items) {
//        final ITXCheckCombo cmb = new ITXCheckCombo(parent, items, SWT.BORDER);
//        cmb.setFont(InterfaceUtils.getDefaultFont8Text());
//        cmb.setBackground(getBlanco());
//        cmb.setVisibleItemCount(NUMBER_VISIBLE_ITEMS);
//        return cmb;
//    }
//
//    /**
//     * New c tab folder.
//     * 
//     * @param parent
//     *            parent
//     * @return the c tab folder
//     */
//    public static CTabFolder newCTabFolder(final Composite parent) {
//        return newCTabFolder(parent, SWT.NONE, false);
//    }
//
//    /**
//     * New c tab folder.
//     * 
//     * @param parent
//     *            parent
//     * @param style
//     *            style
//     * @return the c tab folder
//     */
//    public static CTabFolder newCTabFolder(final Composite parent, final int style) {
//        return newCTabFolder(parent, style, false);
//    }
//
//    /**
//     * New c tab folder.
//     * 
//     * @param parent
//     *            parent
//     * @param style
//     *            style
//     * @param simple
//     *            simple
//     * @return the c tab folder
//     */
//    public static CTabFolder newCTabFolder(final Composite parent, final int style, final boolean simple) {
//        final CTabFolder tabFolder = new CTabFolder(parent, style);
//        tabFolder.setUnselectedImageVisible(true);
//        tabFolder.setTabHeight(30);
//        tabFolder.setSimple(simple);
//        tabFolder.setSelectionBackground(getBlanco());
//        tabFolder.setBackground(InterfaceUtils.getGrisClaroInditex());
//        tabFolder.setFont(InterfaceUtils.getGenericFont());
//        return tabFolder;
//
//    }
//
//    /**
//     * New c tab item.
//     * 
//     * @param parent
//     *            parent
//     * @param index
//     *            index
//     * @param text
//     *            text
//     * @return the c tab item
//     */
//    public static CTabItem newCTabItem(final CTabFolder parent, final int index, final String text) {
//        return newCTabItem(parent, index, text, null);
//    }
//
//    /**
//     * New c tab item.
//     * 
//     * @param parent
//     *            parent
//     * @param index
//     *            index
//     * @param text
//     *            text
//     * @param control
//     *            control
//     * @return the c tab item
//     */
//    public static CTabItem newCTabItem(final CTabFolder parent, final Integer index, final String text,
//            final Control control) {
//
//        final CTabItem tbItem;
//        if (index != null) {
//            tbItem = new CTabItem(parent, SWT.NONE, index);
//        } else {
//            tbItem = new CTabItem(parent, SWT.NONE);
//        }
//        if (text != null) {
//            tbItem.setText(text);
//        }
//        if (control != null) {
//            tbItem.setControl(control);
//        }
//        return tbItem;
//    }
//
    /**
     * New button.
     * 
     * @param parent
     *            parent
     * @param image
     *            image
     * @return the button
     */
    public static Button newButton(final Composite parent, final Image image) {
        return newButton(parent, null, image);
    }

    /**
     * New button.
     * 
     * @param parent
     *            parent
     * @param text
     *            text
     * @param image
     *            image
     * @return the button
     */
    public static Button newButton(final Composite parent, final String text, final Image image) {
        final Button btn = new Button(parent, SWT.NONE);
        //btn.setFont(InterfaceUtils.getDefaultFont8Text());
        if (text != null) {
            btn.setText(text);
        }
        if (image != null) {
            btn.setImage(image);
        }
        return btn;
    }
//
//    /**
//     * New button.
//     * 
//     * @param <T>
//     *            tipo generico
//     * @param parent
//     *            parent
//     * @param text
//     *            text
//     * @param image
//     *            image
//     * @param command
//     *            command
//     * @return the button
//     */
//    public static <T> Button newButton(final Composite parent, final String text, final Image image,
//            final UICommand command) {
//
//        final Button btn = newButton(parent, text, image);
//
//        if (command != null) {
//            btn.addSelectionListener(new SelectionAdapter() {
//                @Override
//                public void widgetSelected(final SelectionEvent paramSelectionEvent) {
//                    command.execute();
//                }
//            });
//        }
//
//        return btn;
//    }
//
//    /**
//     * New radio button.
//     * 
//     * @param parent
//     *            parent
//     * @param text
//     *            text
//     * @return the button
//     */
//    public static Button newRadioButton(final Composite parent, final String text) {
//        final Button btn = new Button(parent, SWT.RADIO);
//        btn.setBackground(getBlanco());
//        btn.setFont(getNormalFont8());
//        if (text != null) {
//            btn.setText(text);
//        }
//        return btn;
//    }

    /**
     * New check button.
     * 
     * @param parent
     *            parent
     * @param text
     *            text
     * @return the button
     */
    public static Button newCheckButton(final Composite parent, final String text) {
        final Button btn = new Button(parent, SWT.CHECK);
        //btn.setBackground(getBlanco());
        //btn.setFont(getNormalFont8());
        if (text != null) {
            btn.setText(text);
        }
        return btn;
    }


  /**
    * New group.
    * 
    * @param parent parent
    * @param text text
    * @return the group
    */
  public static Group newGroup(
         final Composite parent,
         final String text) {
   final Group group = new Group(parent, SWT.NONE);
   //group.setFont(getNormalFont8());
   //group.setBackground(getBlanco());
   if (text != null) {
    group.setText(text);
   }
   return group;
  }
 
//
//    /**
//     * New p group.
//     * 
//     * @param parent
//     *            parent
//     * @param text
//     *            text
//     * @return the p group
//     */
//    public static PGroup newPGroup(final Composite parent, final String text) {
//        final PGroup group = new PGroup(parent, SWT.NONE);
//        group.setToggleRenderer(new MinMaxToggleRenderer());
//        group.setStrategy(new ITXRectangleGroupStrategy());
//        group.setBackground(InterfaceUtils.getBlanco());
//        group.setFont(InterfaceUtils.getTitleFont());
//        if (text != null) {
//            group.setText(text);
//        }
//        return group;
//    }
//
//    /**
//     * New link.
//     * 
//     * @param parent
//     *            parent
//     * @param text
//     *            text
//     * @return the link
//     */
//    public static Link newLink(final Composite parent, final String text) {
//        final Link link = new Link(parent, SWT.NONE);
//        link.setFont(getNormalFont8());
//        link.setBackground(getBlanco());
//        link.setCursor(getCursorHand());
//        if (text != null) {
//            link.setText("<a>" + text + "</a>");
//        }
//        return link;
//    }
//
//    /**
//     * New link.
//     * 
//     * @param parent
//     *            parent
//     * @param text
//     *            text
//     * @param command
//     *            command
//     * @return the link
//     */
//    public static Link newLink(final Composite parent, final String text, final UICommand command) {
//        final Link link = newLink(parent, text);
//
//        if (command != null) {
//
//            link.addSelectionListener(new SelectionAdapter() {
//                @Override
//                public void widgetSelected(final SelectionEvent paramSelectionEvent) {
//                    command.execute();
//                }
//            });
//
//        }
//
//        return link;
//    }
//
//    /**
//     * New list.
//     * 
//     * @param parent
//     *            parent
//     * @return the list
//     */
//    public static List newList(final Composite parent) {
//        return newList(parent, SWT.BORDER);
//    }
//
//    /**
//     * New list.
//     * 
//     * @param parent
//     *            parent
//     * @param flags
//     *            flags
//     * @return the list
//     */
//    public static List newList(final Composite parent, final int flags) {
//        final List link = new List(parent, flags);
//        link.setFont(getNormalFont8());
//        link.setBackground(getBlanco());
//        return link;
//    }
//
//    /**
//     * New tool item.
//     * 
//     * @param toolbar
//     *            toolbar
//     * @param message
//     *            message
//     * @param image
//     *            image
//     * @return the tool item
//     */
//    public static ToolItem newToolItem(final ToolBar toolbar, final String message, final Image image) {
//        return newToolItem(toolbar, SWT.NONE, message, image, null);
//    }
//
//    /**
//     * New tool item.
//     * 
//     * @param <T>
//     *            tipo generico
//     * @param toolbar
//     *            toolbar
//     * @param style
//     *            style
//     * @param message
//     *            message
//     * @param image
//     *            image
//     * @param command
//     *            command
//     * @return the tool item
//     */
//    public static <T> ToolItem newToolItem(final ToolBar toolbar, final int style, final String message,
//            final Image image, final UICommand command) {
//        final ToolItem toolItem = new ToolItem(toolbar, style);
//        toolItem.setText(message);
//        toolItem.setImage(image);
//
//        if (command != null) {
//
//            toolItem.addSelectionListener(new SelectionAdapter() {
//                @Override
//                public void widgetSelected(final SelectionEvent paramSelectionEvent) {
//                    command.execute();
//                }
//            });
//        }
//
//        return toolItem;
//    }
//
    /**
     * New text big decimal.
     * 
     * @param parent
     *            parent
     * @return the text
     */
    public static Text newTextBigDecimal(final Composite parent) {
        return newText(parent, SWT.BORDER | SWT.RIGHT, null, new VerifyBigDecimalListener());
    }
//
    /**
     * New text big decimal.
     * 
     * @param parent
     *            parent
     * @param listener
     *            listener
     * @return the text
     */
    public static Text newTextBigDecimal(final Composite parent, final VerifyBigDecimalListener listener) {
        final Text tx = newText(parent, SWT.BORDER | SWT.RIGHT, null, listener);
        tx.addFocusListener(listener);
        return tx;
    }

    /**
     * New text integer.
     * 
     * @param parent
     *            parent
     * @param listener
     *            listener
     * @return the text
     */
    public static Text newTextInteger(final Composite parent, final VerifyBigDecimalListener listener) {
        final Text tx = newText(parent, SWT.BORDER | SWT.RIGHT, null, listener);
        tx.addFocusListener(listener);
        return tx;
    }

    /**
     * New text integer.
     * 
     * @param parent
     *            parent
     * @return the text
     */
    public static Text newTextInteger(final Composite parent) {
        final VerifyIntegerListener listener = new VerifyIntegerListener();
        final Text tx = newText(parent, SWT.BORDER | SWT.LEFT, null, listener);
        //tx.setFont(InterfaceUtils.getDefaultFont8Text());
        tx.addFocusListener(listener);
        return tx;
    }
//
//    /**
//     * Obtiene imagen buscar by style.
//     * 
//     * @param style
//     *            style
//     * @return imagen buscar by style
//     */
//    public static Image getImagenBuscarByStyle(final int style) {
//        Image image = SWTSFIResourceFactory.getImage(ImagesCommons.LABEL_BUTTON_BUSCAR);
//        if (UICommonsConstants.STYLE_NEW.equals(style)) {
//            image = SWTSFIResourceFactory.getImage(ImagesCommons.LABEL_BUTTON_BUSCAR);
//        }
//        if (UICommonsConstants.STYLE_NEW_TRANSPARENTE.equals(style)) {
//            image = SWTSFIResourceFactory.getImage(ImagesCommons.BUTTON_ICON_BUSCAR);
//        }
//        if (UICommonsConstants.STYLE_CURRENT.equals(style)) {
//            image = SWTSFIResourceFactory.getImage(ImagesCommons.LABEL_BUTTON_BUSCAR);
//        }
//        if (UICommonsConstants.STYLE_OLD.equals(style)) {
//            image = SWTSFIResourceFactory.getImage(ImagesCommons.LABEL_BUTTON_BUSCAR);
//        }
//        return image;
//    }
//
//    /**
//     * New cargando composite.
//     * 
//     * @param parent
//     *            parent
//     * @return the cargando composite
//     */
//    public static CargandoComposite newCargandoComposite(final Composite parent) {
//        return newCargandoComposite(parent, false);
//    }
//
//    /**
//     * New cargando composite.
//     * 
//     * @param parent
//     *            parent
//     * @return the cargando composite
//     */
//    public static CargandoComposite newCargandoComposite(final Composite parent, boolean redimensionar) {
//        // Cargando
//        final CargandoComposite cargandoComposite;
//
//        if (redimensionar) {
//            cargandoComposite = new CargandoComposite(parent);
//        } else {
//            cargandoComposite = new CargandoComposite(parent, 0, false, 0, false, false, redimensionar);
//        }
//
//        final FormData fd_cargandoComposite = new FormData();
//        fd_cargandoComposite.top = new FormAttachment(50, -100);
//        fd_cargandoComposite.left = new FormAttachment(50, -300);
//        fd_cargandoComposite.width = 600;
//        fd_cargandoComposite.height = 200;
//        cargandoComposite.setLayoutData(fd_cargandoComposite);
//        // Ponerlo por encima del resto de hermanos
//        for (final Control sibling : parent.getChildren()) {
//            if (!cargandoComposite.equals(sibling)) {
//                cargandoComposite.moveAbove(sibling);
//            }
//        }
//        cargandoComposite.setVisible(false);
//        return cargandoComposite;
//    }
//
    /**
     * New date chooser combo.
     * 
     * @param parent
     *            parent
     * @return the date chooser combo
     */
    public static DateChooserCombo newDateChooserCombo(final Composite parent) {
        final DateChooserCombo fecha = new DateChooserCombo(parent, SWT.BORDER);
        //fecha.setFont(getNormalFont8());
        //fecha.setBackground(getBlanco());
        fecha.setFormatter(new DateFormatter("dd/MM/yyyy"));
        return fecha;
    }
//
//    /**
//     * New form data.
//     * 
//     * @param top
//     *            top
//     * @param right
//     *            right
//     * @param left
//     *            left
//     * @param bottom
//     *            bottom
//     * @param width
//     *            width
//     * @param height
//     *            height
//     * @return the form data
//     */
//    public static FormData newFormData(final FormAttachment top, final FormAttachment right, final FormAttachment left,
//            final FormAttachment bottom, final Integer width, final Integer height) {
//        return new FormDataBuilder().withTop(top).withRight(right).withBottom(bottom).withLeft(left).withWidth(width)
//                .withHeight(height).getFormData();
//    }
//
//    /**
//     * New list viewer.
//     * 
//     * @param list
//     *            list
//     * @param labelProvider
//     *            label provider
//     * @return the list viewer
//     */
//    public static ListViewer newListViewer(final List list, final LabelProvider labelProvider) {
//        final ListViewer listViewer = new ListViewer(list);
//        listViewer.setContentProvider(new ObservableListContentProvider());
//        listViewer.setLabelProvider(labelProvider);
//        return listViewer;
//    }
//
//    /**
//     * New formatted text.
//     * 
//     * @param parent
//     *            parent
//     * @return the formatted text
//     */
//    public static FormattedText newFormattedText(final Composite parent) {
//        final FormattedText formattedText = new FormattedText(parent, SWT.BORDER);
//        formattedText.getControl().setFont(getNormalFont8());
//        formattedText.getControl().setBackground(getBlanco());
//        return formattedText;
//    }
//
//    /**
//     * New progress adapter charging.
//     * 
//     * @param composite
//     *            composite
//     * @return the progress adapter
//     */
//    public static ProgressAdapter newProgressAdapterCharging(final SFICompositeCharging composite) {
//        return newProgressAdapterCharging(composite, SFIResourceFactory.getMessage("Cargando_Aviso_Busqueda"));
//    }
//
//    /**
//     * Crea el progress adapter.
//     * 
//     * @param composite
//     *            composite
//     * @param texto
//     *            texto
//     * @return the progress adapter
//     */
//    public static ProgressAdapter newProgressAdapterCharging(final SFICompositeCharging composite, final String texto) {
//        return new ProgressAdapter() {
//
//            private boolean cargando = false;
//
//            @Override
//            public void changed(final ProgressEvent e) {
//
//                if ((this.cargando == false) && (e.current != e.total) && (e.total != 0)) {
//
//                    composite.openCharging(texto);
//
//                    this.cargando = true;
//                }
//
//                if (((e.current == e.total) || (e.total == 0)) && (this.cargando == true)) {
//
//                    composite.closeCharging();
//                    this.cargando = false;
//                }
//            }
//        };
//    }
//
//    public static Text newTextInteger(final Composite parent, final int style) {
//        final VerifyIntegerListener listener = new VerifyIntegerListener();
//        final Text tx = newText(parent, SWT.BORDER | style, null, listener);
//        tx.setFont(InterfaceUtils.getDefaultFont8Text());
//        tx.addFocusListener(listener);
//        return tx;
//    }

}
