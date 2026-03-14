package org.eclipse.swt.snippets;

 import java.io.Serializable;
 import java.util.Date;
 import org.apache.commons.collections.KeyValue;

 public class ProrrogarDto implements Serializable, KeyValue {
  private static final long serialVersionUID = 1L;
   private Integer prorroga;
   private Date fechaInicio;
   private Date fechaFin; 
   private Date fechaPreaviso; 
   private String descripcion;
   
   public ProrrogarDto() {}
    
   public final Integer getProrroga() {
    return this.prorroga;
   }

   public final void setProrroga(final Integer prorroga) {
    this.prorroga = prorroga;
   }

   public final Date getFechaInicio() {
    return this.fechaInicio;
   }

   public final void setFechaInicio(final Date fechaInicio) {
    this.fechaInicio = fechaInicio;
   }

   public final Date getFechaFin() {
    return this.fechaFin;
   }

   public final void setFechaFin(final Date fechaFin) {
    this.fechaFin = fechaFin;
   }

   public final Date getFechaPreaviso() {
    return this.fechaPreaviso;
   }

   public final void setFechaPreaviso(final Date fechaPreaviso) {
    this.fechaPreaviso = fechaPreaviso;
   }

   public final String getDescripcion() {
    return this.descripcion;
   }

   public final void setDescripcion(final String descripcion) {
    this.descripcion = descripcion;
   }
   
   @Override
   public Object getKey() {
    return this.prorroga;
   }

   @Override
   public Object getValue() {
    return this.descripcion;
   }

   @Override
   public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((prorroga == null) ? 0 : prorroga.hashCode());
    return result;
   }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProrrogarDto other = (ProrrogarDto) obj;
        if (prorroga == null) {
            if (other.prorroga != null)
                return false;
        } else if (!prorroga.equals(other.prorroga))
            return false;
        return true;
    }

}
