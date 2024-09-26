/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado.beans;

import RegistrarCambioEstado.ControladorRegistrarCambioEstado;
import RegistrarCambioEstado.dtos.HistoricoDTO;
import RegistrarCambioEstado.exceptions.cambioEstadoException;
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

@Named("uiHistorico")
@ViewScoped
public class UIHistorico implements Serializable{
    private int nroLineaTramiteEstado;
    private int codEstadoTramite;
    private String observacionTramiteEstado;
    private Timestamp fechaBajaTramiteEstado;
    private String nombreEstadoTramite;
    private Timestamp fechaCambio;
    private int nroTramite;
    
     private ControladorRegistrarCambioEstado controladorRegistrarCambioEstado = new ControladorRegistrarCambioEstado();
    
    public ControladorRegistrarCambioEstado getControladorRegistrarCambioEstado() {
        return controladorRegistrarCambioEstado;
    }

    public void setControladorRegistrarCambioEstado(ControladorRegistrarCambioEstado controladorRegistrarCambioEstado) {
        this.controladorRegistrarCambioEstado = controladorRegistrarCambioEstado;
    }

    public UIHistorico() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        

    }
    
    
    

    public int getNroLineaTramiteEstado() {
        return nroLineaTramiteEstado;
    }

    public void setNroLineaTramiteEstado(int nroLineaTramiteEstado) {
        this.nroLineaTramiteEstado = nroLineaTramiteEstado;
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

    public Timestamp getFechaBajaTramiteEstado() {
        return fechaBajaTramiteEstado;
    }

    public void setFechaBajaTramiteEstado(Timestamp fechaBajaTramiteEstado) {
        this.fechaBajaTramiteEstado = fechaBajaTramiteEstado;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public Timestamp getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Timestamp fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
    
    public void anularTramiteEstado(int nroLineaTramiteEstado) {
        try{
            controladorRegistrarCambioEstado.anularTramiteEstado(nroLineaTramiteEstado);
            Messages.create("Anulado").detail("Anulado").add();
        }catch(cambioEstadoException e){
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
    
    public String irNuevoEstado() {
        BeansUtils.guardarUrlAnterior();
        
        return "registrarCambioEstado.xhtml?faces-redirect=true" ; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
    //no funciona, seguramente porque necesita el nro de tramite
    public String irEtapas(){
         BeansUtils.guardarUrlAnterior();
         return "tramiteParticular.xhtml?faces-redirect=true";
    }
    
    
    
    
    
}
