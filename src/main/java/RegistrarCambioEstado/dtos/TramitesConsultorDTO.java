package DTO.D;

import java.util.Date;

public class TramitesConsultorDTO {

    private int cuit;
    private Date fechaHoraCargaTramite;
    private String nombreEstadoTramite;
    private String nombreTipoTramite;
    private int nroTramite;

    //METODOS
    public int getCuit() {
        return cuit;
    }
    public void setCuit(int cuit) {
        this.cuit = cuit;
    }
    public Date getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }
    public void setFechaHoraCargaTramite(Date fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }
    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }
    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }
    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }
    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }
    public int getNroTramite() {
        return nroTramite;
    }
    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    
    
}
