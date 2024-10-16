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

    //filtros
    private int nroTramiteFiltro=0; //no se usa, no sirve
    private String nombreTipoTramiteFiltro=" ";
    private String estadoFiltro=" ";
    private Timestamp fechaFiltro;
    private int legajoConsultor;
    private String nombreConsultor;
    private ControladorRC controladorRegistrarCambioEstado = new ControladorRC();

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

    public Timestamp getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(Timestamp fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }

    public UItramitesConsultor() {
        
        
    }
    
    
    public List<UITramitesConsultorGrilla> obtenerTramitesConsultor( ){
   
        List<UITramitesConsultorGrilla> tramitesGrilla = new ArrayList<>();
        List<TramitesConsultorDTO> tramitesConsultorDTO = controladorRegistrarCambioEstado.obtenerTramitesConsultor(fechaFiltro, nombreTipoTramiteFiltro, estadoFiltro);
        for (TramitesConsultorDTO tramiteConsultorDTO : tramitesConsultorDTO) {
            UITramitesConsultorGrilla tramiteGrillaUI = new UITramitesConsultorGrilla();
            tramiteGrillaUI.setNroTramite(tramiteConsultorDTO.getNroTramite());
            tramiteGrillaUI.setNombreTipoTramite(tramiteConsultorDTO.getNombreTipoTramite());
            tramiteGrillaUI.setFechaInicio(tramiteConsultorDTO.getFechaHoraCargaTramite());
            tramiteGrillaUI.setCuitCliente(tramiteConsultorDTO.getCuitCliente());
            tramiteGrillaUI.setEstado(tramiteConsultorDTO.getNombreEstadoTramite());
            
            tramitesGrilla.add(tramiteGrillaUI);
        }
        
        return tramitesGrilla;
    }
    
    public void filtrar(){
    }
    
  
    
    public String irATramite(int nro) {
        BeansUtils.guardarUrlAnterior();
        //System.out.println("hola mundo" +nro);
        return "tramiteParticular.xhtml?faces-redirect=true&nroTramite=" + nro; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
            
    
}
