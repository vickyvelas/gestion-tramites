package RC_2.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TramitesConsultorDTO {

    private int cuitCliente;
    private Timestamp fechaHoraCargaTramite;
    private String nombreEstadoTramite;
    private String nombreTipoTramite;
    private int nroTramite;
    private int legajoConsultor;
    private String nombreConsultor;

    public int getLegajoConsultor() {
        return legajoConsultor;
    }

    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }

    public String getNombreConsultor() {
        return nombreConsultor;
    }

    public void setNombreConsultor(String nombreConsultor) {
        this.nombreConsultor = nombreConsultor;
    }
    
    
    
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
