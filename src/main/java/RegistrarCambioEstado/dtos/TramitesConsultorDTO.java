package RegistrarCambioEstado.dtos;

import java.sql.Timestamp;
import java.util.Date;

public class TramitesConsultorDTO {

    private int cuitCliente;
    private Timestamp fechaHoraCargaTramite;
    private String nombreEstadoTramite;
    private String nombreTipoTramite;
    private int nroTramite;
    
    //METODOS
    public int getCuitCliente() {
        return cuitCliente;
    }
    public void setCuitCliente(int cuitCliente) {
        this.cuitCliente = cuitCliente;
    }
    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }
    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
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
