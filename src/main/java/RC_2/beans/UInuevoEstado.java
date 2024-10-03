/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;


import RC_2.ControladorRC;
import RC_2.dto.DestinosPosiblesDTO;
import RC_2.dto.NuevoEstadoDTO;
import RC_2.dto.TramiteParticularDTO;
import RC_2.dto.TransicionDTO;
import entidades.TramiteEstado;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("uiNuevoEstado")
@ViewScoped
public class UInuevoEstado  implements Serializable{
    private List<String> nombreEstadoTramiteDestino;
    private List<EstadosPosiblesUI> estadosPosiblesUI=new ArrayList<>();
    private String nombreEstadoTramiteActual;
    private int codEstadoTramite;
    private String observacionTramiteEstado;
    private ControladorRC controladorRC = new ControladorRC();
    private int nroTramite;
    private String nombreTipoTramite;
    private String nombreApellidoCliente;
    private int codEstadoSeleccionado;
    private String nombreEstadoTramiteSeleccionado;

    public String getNombreEstadoTramiteSeleccionado() {
        return nombreEstadoTramiteSeleccionado;
    }

    public void setNombreEstadoTramiteSeleccionado(String nombreEstadoTramiteSeleccionado) {
        this.nombreEstadoTramiteSeleccionado = nombreEstadoTramiteSeleccionado;
    }

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

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }
    
    

    public List<EstadosPosiblesUI> getEstadosPosiblesUI() {
        return estadosPosiblesUI;
    }

    public void setEstadosPosiblesUI(List<EstadosPosiblesUI> estadosPosiblesUI) {
        this.estadosPosiblesUI = estadosPosiblesUI;
    }

    public int getCodEstadoSeleccionado() {
        return codEstadoSeleccionado;
    }

    public void setCodEstadoSeleccionado(int codEstadoSeleccionado) {
        this.codEstadoSeleccionado = codEstadoSeleccionado;
    }
    
    
    public UInuevoEstado() {
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        ExternalContext externalContext = facesContext.getExternalContext();
//        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//        
//         nroTramite = Integer.parseInt(request.getParameter("nroTramite"));
         NuevoEstadoDTO nuevoEstadoDTO=controladorRC.nuevoEstado(nroTramite);
         nombreEstadoTramiteActual=nuevoEstadoDTO.getNombreEstadoTramiteActual();
         observacionTramiteEstado=nuevoEstadoDTO.getObservacionTramiteEstado();
         nombreApellidoCliente=nuevoEstadoDTO.getNombreApellidoCliente();
         nombreTipoTramite=nuevoEstadoDTO.getNombreTipoTramite();
         nroTramite=nuevoEstadoDTO.getNroTramite();
         for (DestinosPosiblesDTO d : nuevoEstadoDTO.getDestinosPosiblesList())
         {
         EstadosPosiblesUI e = new EstadosPosiblesUI();
         e.setCodEstadoTramite(d.getCodEstadoTramite());
         e.setNombreEstadoTramiteDestino(d.getNombreEstadoTramiteDestino());
         estadosPosiblesUI.add(e);
        }
                  EstadosPosiblesUI e = new EstadosPosiblesUI();
         e.setCodEstadoTramite(1);
         e.setNombreEstadoTramiteDestino("Cristian");
         estadosPosiblesUI.add(e);
                           e = new EstadosPosiblesUI();
         e.setCodEstadoTramite(2);
         e.setNombreEstadoTramiteDestino("Martina");
         estadosPosiblesUI.add(e);
                            e = new EstadosPosiblesUI();
        e.setCodEstadoTramite(3);
        e.setNombreEstadoTramiteDestino("Etapa 1");
        estadosPosiblesUI.add(e);
        
    }

   
    public String getNombreEstadoTramiteActual() {
        return nombreEstadoTramiteActual;
    }

    public void setNombreEstadoTramiteActual(String nombreEstadoTramiteActual) {
        this.nombreEstadoTramiteActual = nombreEstadoTramiteActual;
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
    
    //provisorio
//    public String guardar(){
//  
//              System.out.println(codEstadoSeleccionado); 
//              System.out.println(observacionTramiteEstado);
//            //return BeansUtils.redirectToPreviousPage();
//           return "";
//    }
    
    
    public String cambiarEstadoTramite (){
        TransicionDTO transicionDTO = new TransicionDTO();
            transicionDTO.setCodEstadoTramite(getCodEstadoSeleccionado());
            transicionDTO.setNombreEstadoTramite(getNombreEstadoTramiteSeleccionado());
            transicionDTO.setObservacion(getObservacionTramiteEstado());
            controladorRC.cambiarEstadoTramite(transicionDTO, nroTramite);

        return BeansUtils.redirectToPreviousPage();
    }

    
    
    
}
