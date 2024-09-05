package entidades;

import java.sql.Timestamp;


public class TramiteEstado extends Entidad{    
    private Timestamp fechaHoraBajaTramiteEstado;
    private Timestamp fechaHoraDesdeTramiteEstado;
    private Timestamp fechaHoraHastaTramiteEstado;
    private int nroLineaTramiteEstado;
    private String observacionTramiteEstado;
    private String estadoTramite;
    
    // Constructor
    public TramiteEstado() {
    }

    public Timestamp getFechaHoraBajaTramiteEstado() {
        return fechaHoraBajaTramiteEstado;
    }

    public void setFechaHoraBajaTramiteEstado(Timestamp fechaHoraBajaTramiteEstado) {
        this.fechaHoraBajaTramiteEstado = fechaHoraBajaTramiteEstado;
    }

    public Timestamp getFechaHoraDesdeTramiteEstado() {
        return fechaHoraDesdeTramiteEstado;
    }

    public void setFechaHoraDesdeTramiteEstado(Timestamp fechaHoraDesdeTramiteEstado) {
        this.fechaHoraDesdeTramiteEstado = fechaHoraDesdeTramiteEstado;
    }

    public Timestamp getFechaHoraHastaTramiteEstado() {
        return fechaHoraHastaTramiteEstado;
    }

    public void setFechaHoraHastaTramiteEstado(Timestamp fechaHoraHastaTramiteEstado) {
        this.fechaHoraHastaTramiteEstado = fechaHoraHastaTramiteEstado;
    }

    public int getNroLineaTramiteEstado() {
        return nroLineaTramiteEstado;
    }

    public void setNroLineaTramiteEstado(int nroLineaTramiteEstado) {
        this.nroLineaTramiteEstado = nroLineaTramiteEstado;
    }

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
    }
    
    public String getEstadoTramite() {
        return estadoTramite;
    }

    // Setter
    public void setEstadoTramite(String estadoTramite) {
        this.estadoTramite = estadoTramite;
    }
    
}
