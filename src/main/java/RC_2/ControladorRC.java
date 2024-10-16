/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2;

import RC_2.dto.EstadoActualDTO;
import RC_2.dto.HistoricoDTO;
import RC_2.dto.NuevoEstadoDTO;
import RC_2.dto.TramiteParticularDTO;
import RC_2.dto.TramitesConsultorDTO;
import RC_2.dto.TransicionDTO;
import entidades.TramiteEstado;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */
public class ControladorRC {
    
    private ExpertoRC expertoRC = new ExpertoRC();
    
    
    
     public void anularUltimo(int nroTramite) throws ExceptionRC{
        expertoRC.anularUltimo(nroTramite);
    }
            
     
     
     
    public void cambiarEstadoTramite (TransicionDTO tdto, int nroTramite){
         expertoRC.cambiarEstadoTramite(tdto, nroTramite);
    }
    
   
    
    
    public List<HistoricoDTO> etapas(int nroTramite){
      
        return new ArrayList<HistoricoDTO>();
    }
    
    
            
    public NuevoEstadoDTO nuevoEstado(int nroTramite) throws ExceptionRC{
        return expertoRC.nuevoEstado(nroTramite);
    }
            
    
    
    public TramiteParticularDTO obtenerTramiteParticular(int nroTramite){
        return expertoRC.obtenerTramiteParticular(nroTramite);
    }
    
    
            
    public List<TramitesConsultorDTO> obtenerTramitesConsultor( Timestamp fechaFiltro,String nombreTipoTramiteFiltro, String estadoFiltro){
       
        return expertoRC.obtenerTramitesConsultor(fechaFiltro, nombreTipoTramiteFiltro, estadoFiltro);
    }
    
     public TramitesConsultorDTO buscarTramiteConsultor(int nroTramiteFiltro){
         return expertoRC.buscarTramiteConsultor(nroTramiteFiltro);
     }
    
}
