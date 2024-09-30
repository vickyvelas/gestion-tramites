/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;

import RC_2.ControladorRC;
import RC_2.dto.TramitesConsultorDTO;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.BeansUtils;

@Named("uiTramitesConsultor")
@ViewScoped
public class UItramitesConsultor implements Serializable{
    private int nroTramite;
    private String nombreTipoTramite;
    private String estado;
    private Timestamp fechaInicio;
    private int cuitCliente;
    //filtros
    private int nroTramiteFiltro=0;
    private String nombreTipoTramiteFiltro=" ";
    private String estadoFiltro=" ";
    private Timestamp fechaDesde;
    
    private ControladorRC controladorRegistrarCambioEstado = new ControladorRC();

    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(int cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public int getNroTramiteFiltro() {
        return nroTramiteFiltro;
    }

    public void setNroTramiteFiltro(int nroTramiteFiltro) {
        this.nroTramiteFiltro = nroTramiteFiltro;
    }

    public String getNombreTipoTramiteFiltro() {
        return nombreTipoTramiteFiltro;
    }

    public void setNombreTipoTramiteFiltro(String nombreTipoTramiteFiltro) {
        this.nombreTipoTramiteFiltro = nombreTipoTramiteFiltro;
    }

    public String getEstadoFiltro() {
        return estadoFiltro;
    }

    public void setEstadoFiltro(String estadoFiltro) {
        this.estadoFiltro = estadoFiltro;
    }

    public Timestamp getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public UItramitesConsultor() {
    }
    
    
    public List<UItramitesConsultor> obtenerTramitesConsultor( ){
   
        List<UItramitesConsultor> tramitesGrilla = new ArrayList<>();
        List<TramitesConsultorDTO> tramitesConsultorDTO = controladorRegistrarCambioEstado.obtenerTramitesConsultor(nroTramiteFiltro, nombreTipoTramiteFiltro, estadoFiltro);
        for (TramitesConsultorDTO tramiteConsultorDTO : tramitesConsultorDTO) {
            UItramitesConsultor tramiteGrillaUI = new UItramitesConsultor();
            tramiteGrillaUI.setNroTramite(tramiteConsultorDTO.getNroTramite());
            tramiteGrillaUI.setNombreTipoTramite(tramiteConsultorDTO.getNombreTipoTramite());
            tramiteGrillaUI.setFechaInicio(tramiteConsultorDTO.getFechaHoraCargaTramite());
            tramiteGrillaUI.setCuitCliente(tramiteConsultorDTO.getCuitCliente());
            tramiteGrillaUI.setEstado(tramiteConsultorDTO.getNombreEstadoTramite());
            tramitesGrilla.add(tramiteGrillaUI);
        }
        return tramitesGrilla;
    }
    
    
    
  
    
    public String irATramite(int nro) {
        BeansUtils.guardarUrlAnterior();
        //System.out.println("hola mundo" +nro);
        return "tramiteParticular.xhtml?faces-redirect=true&nroTramite=" + nro; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
            
    
}
