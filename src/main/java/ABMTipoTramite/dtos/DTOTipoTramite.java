
package ABMTipoTramite.dtos;

import java.sql.Timestamp;

public class DTOTipoTramite {
    
    private int codCategoriaTramite;
    private Timestamp fechaHoraBajaTipoTramite;
    private Timestamp maxDiaEntrega;
    private String nombreCategoriaTramite;
    private String nombreTipoTramite;
    

    public int getCodCategoriaTramite() {
        return codCategoriaTramite;
    }

    public void setCodCategoriaTramite(int codCategoriaTramite) {
        this.codCategoriaTramite = codCategoriaTramite;
    }

    public Timestamp getFechaHoraBajaTipoTramite() {
        return fechaHoraBajaTipoTramite;
    }

    public void setFechaHoraBajaTipoTramite(Timestamp fechaHoraBajaTipoTramite) {
        this.fechaHoraBajaTipoTramite = fechaHoraBajaTipoTramite;
    }

    public Timestamp getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(Timestamp maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }

    public String getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(String nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }
 
}
