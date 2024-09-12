package DTO.D;

import java.util.Date;

public class dtoHistorico {
    private int codEstadoTramite;
    private Date fechaHastaTramiteEstado;
    private Date fechaHoraBajaEstadoTramite;
    private String nombreEstadoTramite;
    private int nroLineaTramiteEstado;

    //METODOS GET

    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }
    public Date getFechaHastaTramiteEstado() {
        return fechaHastaTramiteEstado;
    }
    public Date getFechaHoraBajaEstadoTramite() {
        return fechaHoraBajaEstadoTramite;
    }
    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }
    public int getNroLineaTramiteEstado() {
        return nroLineaTramiteEstado;
    }
    
    //METODOS SET

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public void setFechaHastaTramiteEstado(Date fechaHastaTramiteEstado) {
        this.fechaHastaTramiteEstado = fechaHastaTramiteEstado;
    }

    public void setFechaHoraBajaEstadoTramite(Date fechaHoraBajaEstadoTramite) {
        this.fechaHoraBajaEstadoTramite = fechaHoraBajaEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public void setNroLineaTramiteEstado(int nroLineaTramiteEstado) {
        this.nroLineaTramiteEstado = nroLineaTramiteEstado;
    }
}
