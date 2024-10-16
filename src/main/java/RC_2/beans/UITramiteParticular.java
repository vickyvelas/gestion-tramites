/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;

import RC_2.ControladorRC;
import RC_2.dto.HistoricoDTO;
import RC_2.dto.TramiteParticularDTO;
import RC_2.dto.TramitesConsultorDTO;
import RC_2.exceptionRC;
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

@Named("uiTramiteParticular")
@ViewScoped
public class UITramiteParticular implements Serializable {
   //PARA MOSTRAR DATOS
    //det dto tramite particular
    private int codTipoTramite;
    private Timestamp fechaHoraFinEntregaDoc;
    private String nombreApellidoCliente;
    private float precioTipoTramite;
    //del dto consultor
    private int cuitCliente;
    private Timestamp fechaHoraCargaTramite;
    private String nombreEstadoTramite;
    private String nombreTipoTramite;
    private int nroTramite;
    private String observacionTramiteEstado;  
    List<UIHistorico>uiHistoricoList=new ArrayList<>();
    //PARA EL HISTORICO DE ESTADOS
    
    
    private ControladorRC controladorRegistrarCambioEstado = new ControladorRC();

    
    public UITramiteParticular() throws exceptionRC {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        nroTramite = Integer.parseInt(request.getParameter("nroTramite"));
        System.out.println(nroTramite);
        TramiteParticularDTO tramiteParticularDTO = controladorRegistrarCambioEstado.obtenerTramiteParticular(nroTramite);
        nombreApellidoCliente=tramiteParticularDTO.getNombreApellidoCliente();
        codTipoTramite=tramiteParticularDTO.getCodTipoTramite();
        fechaHoraFinEntregaDoc=tramiteParticularDTO.getFechaHoraFinEntregaDoc();
        precioTipoTramite=tramiteParticularDTO.getPrecioTipoTramite();
        observacionTramiteEstado=tramiteParticularDTO.getObservaciones();
       
       TramitesConsultorDTO tramitesConsultorDTO = controladorRegistrarCambioEstado.buscarTramiteConsultor(nroTramite);
       cuitCliente = tramitesConsultorDTO.getCuitCliente();
       fechaHoraCargaTramite = tramitesConsultorDTO.getFechaHoraCargaTramite();
       nombreEstadoTramite = tramitesConsultorDTO.getNombreEstadoTramite();
       nombreTipoTramite = tramitesConsultorDTO.getNombreTipoTramite();
         
      
        
        List<HistoricoDTO> historicosDTO = tramiteParticularDTO.getHistoricoDTOList();
        for (HistoricoDTO HistoricoDTO : historicosDTO) {
            UIHistorico uiHistoricoDTO = new UIHistorico();
            uiHistoricoDTO.setCodEstadoTramite(HistoricoDTO.getCodEstadoTramite());
            uiHistoricoDTO.setFechaCambio(HistoricoDTO.getFechaHastaTramiteEstado());
            uiHistoricoDTO.setFechaBajaTramiteEstado(HistoricoDTO.getFechaHoraBajaTramiteEstado());
            uiHistoricoDTO.setNombreEstadoTramite(HistoricoDTO.getNombreEstadoTramite());
            uiHistoricoDTO.setNroLineaTramiteEstado(HistoricoDTO.getNroLineaTramiteEstado());
            uiHistoricoDTO.setObservacionTramiteEstado(HistoricoDTO.getObservaciones());
            uiHistoricoList.add(uiHistoricoDTO);
        }
       
       

    }

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    public Timestamp getFechaHoraFinEntregaDoc() {
        return fechaHoraFinEntregaDoc;
    }

    public void setFechaHoraFinEntregaDoc(Timestamp fechaHoraFinEntregaDoc) {
        this.fechaHoraFinEntregaDoc = fechaHoraFinEntregaDoc;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public float getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(float precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }

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

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
    }

  
    
    public void anularUltimo(int nroTramite) {
        try{
            controladorRegistrarCambioEstado.anularUltimo(nroTramite);
            Messages.create("Anulado").detail("Anulado").add();
        }catch(exceptionRC e){
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
    
    public String irNuevoEstado() {
        BeansUtils.guardarUrlAnterior();
        return "transicion.xhtml?faces-redirect=true&nroTramite=" + nroTramite; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
    
//    public String irEtapas(int nro){
//         BeansUtils.guardarUrlAnterior();
//         return "tramiteParticular.xhtml?faces-redirect=true&codigo=" + nro;
//    }
    
    public List<UIHistorico> etapas(int nroTramite){
                return uiHistoricoList;
     }
    
}
