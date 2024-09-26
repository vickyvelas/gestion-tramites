/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado.beans;

import RegistrarCambioEstado.ControladorRegistrarCambioEstado;
import RegistrarCambioEstado.dtos.HistoricoDTO;
import RegistrarCambioEstado.dtos.TramiteParticularDTO;
import RegistrarCambioEstado.dtos.TramitesConsultorDTO;
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

/**
 *
 * @author marti
 */

@Named("uitramiteParticular")
@ViewScoped
public class UItramiteParticular implements Serializable {
    
    private ControladorRegistrarCambioEstado controladorRegistrarCambioEstado = new ControladorRegistrarCambioEstado();
    private boolean insert;
    private String nombre;
    private int codigo;
    private int codTipoTramite;
    private Timestamp fechaHoraFinEntregaDoc;
    private String nombreApellidoCliente;
    private float precioTipoTramite;
    private int cuitCliente;

    public int getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(int cuitCliente) {
        this.cuitCliente = cuitCliente;
    }
    
    

    public ControladorRegistrarCambioEstado getControladorRegistrarCambioEstado() {
        return controladorRegistrarCambioEstado;
    }

    public void setControladorRegistrarCambioEstado(ControladorRegistrarCambioEstado controladorRegistrarCambioEstado) {
        this.controladorRegistrarCambioEstado = controladorRegistrarCambioEstado;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
    
    public UItramiteParticular(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int nroTramite = Integer.parseInt(request.getParameter("nroTramite"));
        System.out.println(nroTramite);
        TramiteParticularDTO tramiteParticularDTO = controladorRegistrarCambioEstado.buscarTramiteParticular(nroTramite);
        nombreApellidoCliente=tramiteParticularDTO.getNombreApellidoCliente();
        codTipoTramite=tramiteParticularDTO.getCodTipoTramite();
        fechaHoraFinEntregaDoc=tramiteParticularDTO.getFechaHoraFinEntregaDoc();
        precioTipoTramite=tramiteParticularDTO.getPrecioTipoTramite();
       
        
       //NUEVO y si lo hago con la ui?
//        List<TramitesConsultorDTO>  tramitesConsultorDTO = controladorRegistrarCambioEstado.buscarTramitesConsultor(nroTramite, nombreApellidoCliente, nombre);
//        cuitCliente=tramitesConsultorDTO.getCuitCliente();
        
        
    }

    public TramiteParticularDTO getTramiteParticularDTO() {
        return new TramiteParticularDTO();
    }

    public void setTramiteParticularDTO(TramiteParticularDTO tramiteParticularDTO) {
       // this.tramiteParticularDTO = tramiteParticularDTO;
    }
    public void setTramiteParticular(TramiteParticularDTO tramiteParticularDTO){
       // this.tramiteParticularDTO=tramiteParticularDTO;
    }
            
    
    public void buscarTramiteParticular(int nroTramiteFiltro){
        
        //tramiteParticularDTO = controladorRegistrarCambioEstado.buscarTramiteParticular(nroTramiteFiltro);
        
        }
    
    
     public List<UIHistorico> buscarTramitesEstado(int nroTramite){
        List<UIHistorico> tamiteEstadoGrilla = new ArrayList<>();
        List<HistoricoDTO> historicosDTO = controladorRegistrarCambioEstado.buscarTramitesEstado(nroTramite);
        for (HistoricoDTO HistoricoDTO : historicosDTO) {
            UIHistorico tramEstadoGrillaUI = new UIHistorico();
            tramEstadoGrillaUI.setCodEstadoTramite(HistoricoDTO.getCodEstadoTramite());
            tramEstadoGrillaUI.setFechaCambio(HistoricoDTO.getFechaHastaTramiteEstado());
            tramEstadoGrillaUI.setFechaBajaTramiteEstado(HistoricoDTO.getFechaHoraBajaTramiteEstado());
            tramEstadoGrillaUI.setNombreEstadoTramite(HistoricoDTO.getNombreEstadoTramite());
            tramEstadoGrillaUI.setNroLineaTramiteEstado(HistoricoDTO.getNroLineaTramiteEstado());
            tramEstadoGrillaUI.setObservacionTramiteEstado(HistoricoDTO.getObservaciones());
            tamiteEstadoGrilla.add(tramEstadoGrillaUI);
        }
        return tamiteEstadoGrilla;
     }
     
     
     
     
    }

