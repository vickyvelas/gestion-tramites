/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado.beans;

import RegistrarCambioEstado.ControladorRegistrarCambioEstado;
import RegistrarCambioEstado.dtos.DestinosPosiblesDTO;
import entidades.TramiteEstado;
import entidades.TransicionEstado;
import entidades.Version;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
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
    private int codEstadoTramite;

    private ControladorRegistrarCambioEstado controladorRegistrarCambioEstado = new ControladorRegistrarCambioEstado();

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

    public ControladorRegistrarCambioEstado getControladorRegistrarCambioEstado() {
        return controladorRegistrarCambioEstado;
    }

    public void setControladorRegistrarCambioEstado(ControladorRegistrarCambioEstado controladorRegistrarCambioEstado) {
        this.controladorRegistrarCambioEstado = controladorRegistrarCambioEstado;
    }
    
    
     public List<DestinosPosiblesDTO> buscarEstadosDestinos(int nroTramite, TramiteEstado tramiteEstado) {
        List <DestinosPosiblesDTO>  destinosPosiblesDTO = new ArrayList<>();
        List<UIregistrarCambioEstado> estadosDestinos = new ArrayList<>();
        List<DestinosPosiblesDTO> estadosDestDTO = controladorRegistrarCambioEstado.buscarEstadosDestinos(nroTramite, tramiteEstado);
       
        for (DestinosPosiblesDTO estadoDestDTO : estadosDestDTO) {
            UIregistrarCambioEstado estadoGrillaUI = new UIregistrarCambioEstado();
            estadosDestinos.setCodEstadoTramite(estadoDestDTO.getCodEstadoTramite());
            estadosDestinos.setNombreEstadoTramite(estadoDestDTO.getNombreEstadoTramite());
            estadosDestinos.setFechaAlta(estadoDestDTO.getFechaAlta());
            estadosDestinos.setFechaBaja(estadoDestDTO.getFechaBaja());
            estadosDestinos.add(estadosDestinos);
        }
        return destinosPosiblesDTO ;
         
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
