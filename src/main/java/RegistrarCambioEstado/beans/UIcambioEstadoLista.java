/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado.beans;


import RegistrarCambioEstado.ControladorRegistrarCambioEstado;
import RegistrarCambioEstado.dtos.TramitesConsultorDTO;
import entidades.Tramite;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import utils.BeansUtils;

/**
 *
 * @author marti
 */
@Named("uicambioEstadoLista")
@ViewScoped
public class UIcambioEstadoLista implements Serializable{
    private ControladorRegistrarCambioEstado controladorRegistrarCambioEstado=new ControladorRegistrarCambioEstado();
    private int nroTramiteFiltro=0;
    private String nombreTipoTramiteFiltro=" ";
    private String estadoFiltro=" ";
      /*      
    public ControladorRegistrarCambioEstado getControladorRegistrarCambioEstado(){
        return controladorRegistrarCambioEstado;
    }
*/
   

       

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
    
    
    public List<UIcambioEstadoGrilla> buscarTramitesConsultor(){
        System.out.println(nroTramiteFiltro);
        System.out.println(nombreTipoTramiteFiltro);
        System.out.println(estadoFiltro);
        List<UIcambioEstadoGrilla> tramitesGrilla = new ArrayList<>();
        List<TramitesConsultorDTO> tramitesConsultorDTO = controladorRegistrarCambioEstado.buscarTramitesConsultor(nroTramiteFiltro,nombreTipoTramiteFiltro,estadoFiltro);
        for (TramitesConsultorDTO tramiteConsultorDTO : tramitesConsultorDTO) {
            UIcambioEstadoGrilla tramiteGrillaUI = new UIcambioEstadoGrilla();
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
        System.out.println("hola mundo" +nro);
        return "tramiteParticular.xhtml?faces-redirect=true&nroTramite=" + nro; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
    
   
    
}
