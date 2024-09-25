package SolicitarTramiteWeb.dtos;



import entidades.EstadoTramite;

import java.sql.Timestamp;

public class DTOTramiteWeb  {
    
    private int nroTramite;
    private float precioTramite;
    private Timestamp fechaHoraCargaTramite;
    private Timestamp fechaHoraVencimientoDocumentacion;
    private Timestamp fechaHoraFinEntregaDocumentacion;
    private Timestamp fechaHoraFinTramite;
    private EstadoTramite estadoTramite;

    public EstadoTramite getEstadoTramite() {
        return estadoTramite;
    }

    public void setEstadoTramite(EstadoTramite estadoTramite) {
        this.estadoTramite = estadoTramite;
    }
   

    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public float getPrecioTramite() {
        return precioTramite;
    }

    public void setPrecioTramite(float precioTramite) {
        this.precioTramite = precioTramite;
    }

    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }

    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }

    public Timestamp getFechaHoraVencimientoDocumentacion() {
        return fechaHoraVencimientoDocumentacion;
    }

    public void setFechaHoraVencimientoDocumentacion(Timestamp fechaHoraVencimientoDocumentacion) {
        this.fechaHoraVencimientoDocumentacion = fechaHoraVencimientoDocumentacion;
    }

    public Timestamp getFechaHoraFinEntregaDocumentacion() {
        return fechaHoraFinEntregaDocumentacion;
    }

    public void setFechaHoraFinEntregaDocumentacion(Timestamp fechaHoraFinEntregaDocumentacion) {
        this.fechaHoraFinEntregaDocumentacion = fechaHoraFinEntregaDocumentacion;
    }

    public Timestamp getFechaHoraFinTramite() {
        return fechaHoraFinTramite;
    }

    public void setFechaHoraFinTramite(Timestamp fechaHoraFinTramite) {
        this.fechaHoraFinTramite = fechaHoraFinTramite;
    }
    
    
}

