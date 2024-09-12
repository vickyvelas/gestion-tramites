package DTO.D;

import java.util.Date;

public class dtoTramiteParticular {
    private int codTipoTramite;
    private Date fechaHoraFinEntregaDoc;
    private String nombreApellidoCliente;
    private float precioTipoTramite;

    //METODOS GET
    public int getCodTipoTramite() {
        return codTipoTramite;
    }
    public Date getFechaHoraFinEntregaDoc() {
        return fechaHoraFinEntregaDoc;
    }
    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }
    public float getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    //METODOS SET
    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }
    public void setFechaHoraFinEntregaDoc(Date fechaHoraFinEntregaDoc) {
        this.fechaHoraFinEntregaDoc = fechaHoraFinEntregaDoc;
    }
    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }
    public void setPrecioTipoTramite(float precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }


}
