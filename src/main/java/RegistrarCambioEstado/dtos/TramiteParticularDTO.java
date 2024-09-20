package RegistrarCambioEstado.dtos;

import java.sql.Timestamp;
import java.util.Date;

public class TramiteParticularDTO {
    private int codTipoTramite;
    private Timestamp fechaHoraFinEntregaDoc;
    private String nombreApellidoCliente;
    private float precioTipoTramite;

    //METODOS GET
    public int getCodTipoTramite() {
        return codTipoTramite;
    }
    public Timestamp getFechaHoraFinEntregaDoc() {
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
    public void setFechaHoraFinEntregaDoc(Timestamp fechaHoraFinEntregaDoc) {
        this.fechaHoraFinEntregaDoc = fechaHoraFinEntregaDoc;
    }
    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }
    public void setPrecioTipoTramite(float precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }


}
