/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado;

import DTO.D.DestinosPosiblesDTO;
import RegistrarCambioEstado.dtos.TramitesConsultorDTO;
import RegistrarCambioEstado.dtos.TransicionDTO;
import RegistrarCambioEstado.dtos.HistoricoDTO;
import RegistrarCambioEstado.dtos.TramiteParticularDTO;
import RegistrarCambioEstado.exceptions.cambioEstadoException;
import entidades.EstadoTramite;
import java.util.List;

/**
 *
 * @author marti
 */
public class ControladorRegistrarCambioEstado {
    private ExpertoRegistrarCambioEstado expertoRegistrarCambioEstado = new ExpertoRegistrarCambioEstado();
    public List<TramitesConsultorDTO> buscarTramitesConsultor(int nroTramiteFiltro,String nombreTipoTramiteFiltro, String estadoFiltro){
        return expertoRegistrarCambioEstado.buscarTramitesConsultor(nroTramiteFiltro, nombreTipoTramiteFiltro, estadoFiltro);
    }
    
    public TramitesConsultorDTO buscarTramiteConsultor(int nroTramiteFiltro){
        return expertoRegistrarCambioEstado.buscarTramiteConsultor(nroTramiteFiltro);
    }
    
    public TramiteParticularDTO buscarTramiteParticular(int nroTramiteFiltro){
        return expertoRegistrarCambioEstado.buscarTramiteParticular(nroTramiteFiltro);
    }
    
    
    public void nuevoEstado(int nroLineaTramiteEstado, TransicionDTO  transicionDTO)throws cambioEstadoException{
        expertoRegistrarCambioEstado.nuevoEstado(nroLineaTramiteEstado, transicionDTO);
    }
    
     public List<HistoricoDTO> buscarTramitesEstado(int nroTramite){
        return expertoRegistrarCambioEstado.buscarTramitesEstado(nroTramite);
    }
     
     public void anularTramiteEstado(int nroLineaTramiteEstado) throws cambioEstadoException{
         expertoRegistrarCambioEstado.anularTramiteEstado(nroLineaTramiteEstado);
     }

    public EstadoTramite buscarEstadoTramitePorCodigo(int codEstadoTramite) {
        return expertoRegistrarCambioEstado.buscarEstadoTramitePorCodigo(codEstadoTramite);
    }
    
     public List<DestinosPosiblesDTO> buscarPosiblesDestinos(int nroTramite){
         return expertoRegistrarCambioEstado.buscarPosiblesDestinos(nroTramite);
     }
     
     
    
}
