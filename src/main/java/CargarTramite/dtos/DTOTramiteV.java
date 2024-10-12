package CargarTramite.dtos;

import java.sql.Timestamp;

public class DTOTramiteV {

    
    private int CUITCliente;
    private String estadoDocumentacion;
    private Timestamp fechaHoraCargaTramite;
    private String nombreCliente;
    private String nombreConsultor;
    private String nombreEstadoTramite;
    private String nombreTipoTramite;
    private int nroTramite;
    private Timestamp fechaHoraFinEntregaDoc;
    //atributos agregados por mi en la implementacion
    //necesarios para metodo anular
    private Timestamp fechaHoraBajaTramite;
     
     
     
    
    //getters and setters
    
    public Timestamp getFechaHoraBajaTramite() {
        return fechaHoraBajaTramite;
    }


    public void setFechaHoraBajaTramite(Timestamp fechaHoraBajaTramite) {
        this.fechaHoraBajaTramite = fechaHoraBajaTramite;
    }

    public int getCUITCliente() {
        return CUITCliente;
    }

    public void setCUITCliente(int CUITCliente) {
        this.CUITCliente = CUITCliente;
    }

    public String getEstadoDocumentacion() {
        return estadoDocumentacion;
    }

    public void setEstadoDocumentacion(String estadoDocumentacion) {
        this.estadoDocumentacion = estadoDocumentacion;
    }

    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }

    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreConsultor() {
        return nombreConsultor;
    }

    public void setNombreConsultor(String nombreConsultor) {
        this.nombreConsultor = nombreConsultor;
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

    public Timestamp getFechaHoraFinEntregaDoc() {
        return fechaHoraFinEntregaDoc;
    }

    public void setFechaHoraFinEntregaDoc(Timestamp fechaHoraFinEntregaDoc) {
        this.fechaHoraFinEntregaDoc = fechaHoraFinEntregaDoc;
    }

}
