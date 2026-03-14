package org.eclipse.swt.snippets;

 import org.eclipse.jface.viewers.ILabelProvider;
 import org.eclipse.jface.viewers.ILabelProviderListener;
 import org.eclipse.swt.graphics.Image;
 import com.inditex.financiero.sfi.ui.widgets.table.vo.TableLabelColorProvider;

 /**
   * Implementacion de ILabelProvider para proporcionar los nombres de las columnas de la tabla
   * 
   * @author <a href="jppaino@indra.es">Jorge Perez Paino</a>
   * 
   * LabelProvider para la tabla Prorrogar de la PestanaFechasAlerta
   *
   */
 public class TableRelacionesProrrogarLabelProvider extends TableLabelColorProvider implements ILabelProvider {
  @Override
  public Image getImage(final Object arg0) {
   return null;
  }

  @Override
  public Comparable<?> getElement(final Object element, final int columnIndex, final boolean inString) {
   String columnLabel = null;
   if (element != null) {
    final ProrrogarDto prorrogarDto = (ProrrogarDto) element;
    final CamposTablaRelacionesProrrogarEnum camposTablaRelacionesProrrogarEnum
     = CamposTablaRelacionesProrrogarEnum.values()[columnIndex];
    columnLabel = camposTablaRelacionesProrrogarEnum.getInString(prorrogarDto);
   }
   return columnLabel;
  }

 }
