package org.eclipse.swt.snippets;

 import org.eclipse.swt.SWT;

  /**
    * @author <a href="jppaino@indra.es">Jorge Perez Paino</a>
    *
    * Enumeración con la descripción y width de las columnas de la tabla
    * relacionesProrrogar de la PestanaFechasAlerta
    *
    */
 public enum CamposTablaRelacionesProrrogarEnum {
  CHECK(
   "",
   SWT.CENTER, 25, null),
  PRORROGA(
   Messages.ContratosAltaPestanaFechasAlertaDuracionTableProrroga,
   SWT.LEFT, 120, "prorroga") {
    public String getInString(final ProrrogarDto prorrogarDto) {
     return prorrogarDto.getProrroga() + "";
    }
   },
  FECHA_INICIO(
   Messages.ContratosAltaPestanaFechasAlertaDuracionTableFechaInicio,
   SWT.LEFT, 250, "fechaInicio") {
    public String getInString(final ProrrogarDto prorrogarDto) {
     return prorrogarDto.getFechaInicio().toString();
    }
   },
  FECHA_FIN(
   Messages.ContratosAltaPestanaFechasAlertaDuracionTableFechaFin,
   SWT.LEFT, 250, "fechaFin") {
    public String getInString(final ProrrogarDto prorrogarDto) {
     return prorrogarDto.getFechaFin().toString();
    }
   },
  FECHA_PREAVISO(
   Messages.ContratosAltaPestanaFechasAlertaDuracionTableFechaPreaviso,
   SWT.LEFT, 100, "fechaPreaviso") {
    public String getInString(final ProrrogarDto prorrogarDto) {
     return prorrogarDto.getFechaPreaviso().toString();
    }
   },
  DESCRIPCION(
   Messages.ContratosAltaPestanaFechasAlertaDuracionTableDescripcion,
   SWT.LEFT, 140, "descripcion") {
    public String getInString(final ProrrogarDto prorrogarDto) {
     return prorrogarDto.getDescripcion();
    }
   };

  private final String i18nTitle;
  private final int style;
  private final int width;
  private final String campoOrdenacionMemoria;

  private CamposTablaRelacionesProrrogarEnum(
          final String i18nTitle,
          final int style,
          final int width,
          final String campoOrdenacionMemoria) {
   this.i18nTitle = i18nTitle;
   this.style = style;
   this.width = width;
   this.campoOrdenacionMemoria = campoOrdenacionMemoria;            
  }

    public String getI18nTitle() {
        return this.i18nTitle;
    }

    public int getStyle() {
        return this.style;
    }

    public int getWidth() {
        return this.width;
    }

    public String getCampoOrdenacionMemoria() {
        return this.campoOrdenacionMemoria;
    }

    @SuppressWarnings({ "unused", "static-method" })
    public String getInString(final ProrrogarDto prorrogarDto) {
        return null;
    }    
    
}
