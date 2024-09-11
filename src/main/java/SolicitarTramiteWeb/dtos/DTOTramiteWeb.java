package SolicitarTramiteWeb.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

public class DTOTramiteWeb implements Serializable {
    
    private int nroTramite;
    private float precioTramite;
    private Timestamp fechaHoraCargaTramite;
    private Timestamp fechaHoraVencimientoDocumentacion;
    private Timestamp fechaHoraFinEntregaDocumentacion;
    private Timestamp fechaHoraFinTramite;

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

