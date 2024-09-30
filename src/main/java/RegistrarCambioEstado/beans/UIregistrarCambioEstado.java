/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado.beans;

import DTO.D.DestinosPosiblesDTO;
import RegistrarCambioEstado.ControladorRegistrarCambioEstado;
import entidades.TramiteEstado;
import entidades.TransicionEstado;
import entidades.Version;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */
@Named("uiregistrarCambioEstado")
@ViewScoped
public class UIregistrarCambioEstado implements Serializable {
    private String nombreEstadoTramiteDestino;
    
    private String nombreEstadoTramiteActual;
    private int codEstadoTramite;
    private String observacionTramiteEstado;
    
    private ControladorRegistrarCambioEstado controladorRegistrarCambioEstado = new ControladorRegistrarCambioEstado();

    public UIregistrarCambioEstado() {
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int nroTramite = Integer.parseInt(request.getParameter("codigo"));
       
    }
    
   
    private UIDestinosPosibles destinoSeleccionado;

    public UIDestinosPosibles getDestinoSeleccionado() {
        return destinoSeleccionado;
    }

    public void setDestinoSeleccionado(UIDestinosPosibles destinoSeleccionado) {
        this.destinoSeleccionado = destinoSeleccionado;
    }

    //SEGUN IA

    
    
    public String getNombreEstadoTramiteActual() {
        return nombreEstadoTramiteActual;
    }

    public void setNombreEstadoTramiteActual(String nombreEstadoTramiteActual) {
        this.nombreEstadoTramiteActual = nombreEstadoTramiteActual;
    }
    
    
    public String getNombreEstadoTramiteDestino() {
        return nombreEstadoTramiteDestino;
    }

    public void setNombreEstadoTramiteDestino(String nombreEstadoTramiteDestino) {
        this.nombreEstadoTramiteDestino = nombreEstadoTramiteDestino;
    }

    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
    }
    
    

    public ControladorRegistrarCambioEstado getControladorRegistrarCambioEstado() {
        return controladorRegistrarCambioEstado;
    }

    public void setControladorRegistrarCambioEstado(ControladorRegistrarCambioEstado controladorRegistrarCambioEstado) {
        this.controladorRegistrarCambioEstado = controladorRegistrarCambioEstado;
    }
    
   
    
    public List<UIDestinosPosibles> buscarPosiblesDestinos(int nroTramite){
        
        System.out.println(nroTramite);
        List<UIDestinosPosibles> dp = new ArrayList<>();/*
        List<DestinosPosiblesDTO> destinosPosibles =controladorRegistrarCambioEstado.buscarPosiblesDestinos(nroTramite);
        
        for(DestinosPosiblesDTO dtoDP : destinosPosibles){
            UIDestinosPosibles destPosibleUI = new UIDestinosPosibles();
            destPosibleUI.setCodEstadoTramiteDestino(dtoDP.getCodEstadoTramite());
            destPosibleUI.setNombreEstadoTramiteDestino(dtoDP.getNombreEstadoTramiteDestino());
            dp.add(destPosibleUI);
        }*/
      return dp;  
    }
    
    
    
    
    
     

//    public String nuevoEstado(){
//        try {
//
//                HistoricoDTO historicoDTO = new HistoricoDTO();
//                historicoDTO.setNombreEstadoTramite(getNombreEstadoTramite());
//                historicoDTO.setCodEstadoTramite(getCodEstadoTramite());
//                historicoDTO.setObservaciones(getObservacionTramiteEstado());
//                controladorRegistrarCambioEstado.nuevoEstado(historicoDTO);
//
//            
//            return BeansUtils.redirectToPreviousPage();
//        }catch (cambioEstadoException e) {
//                Messages.create(e.getMessage()).fatal().add();
//                return "";
//         }
    
    
    
//    } 
}
