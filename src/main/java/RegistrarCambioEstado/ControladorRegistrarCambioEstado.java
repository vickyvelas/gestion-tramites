/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado;

import RegistrarCambioEstado.dtos.TramitesConsultorDTO;
import DTO.D.TransicionDTO;
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
    
    public TramiteParticularDTO buscarTramiteParticular(int nroTramiteFiltro){
        return expertoRegistrarCambioEstado.buscarTramiteParticular(nroTramiteFiltro);
    }
    
    public void cambioEstado(TransicionDTO  transicionDTO)throws cambioEstadoException{
        expertoRegistrarCambioEstado.cambioEstado(transicionDTO);
    }
    
     public List<HistoricoDTO> buscarTramitesEstado(int nroTramite){
        return expertoRegistrarCambioEstado.buscarTramitesEstado(nroTramite);
    }
     
     public void anularTramiteEstado(int nroLineaTramiteEstado) throws cambioEstadoException{
         expertoRegistrarCambioEstado.anularTramiteEstado(nroLineaTramiteEstado);
     }
}
