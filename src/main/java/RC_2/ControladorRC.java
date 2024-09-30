/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2;

import RC_2.dto.EstadoActualDTO;
import RC_2.dto.HistoricoDTO;
import RC_2.dto.TramiteParticularDTO;
import RC_2.dto.TramitesConsultorDTO;
import RC_2.dto.TransicionDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marti
 */
public class ControladorRC {
    
    private ExpertoRC expertoRC = new ExpertoRC();
    
    
    
     public void anularUltimo(int nroTramite) throws exceptionRC{
        expertoRC.anularUltimo(nroTramite);
    }
            
     
     
     
    public void cambiarTramite (TransicionDTO tdto, int nroTramite){
        expertoRC.cambiarTramite(tdto, nroTramite);
    }
    
   
    
    
    public List<HistoricoDTO> etapas(int nroTramite){
      
        return expertoRC.etapas(nroTramite);
    }
    
    
            
    public EstadoActualDTO nuevoEstado(int nroTramite){
        return expertoRC.nuevoEstado(nroTramite);
    }
            
    
    
    public TramiteParticularDTO obtenerTramiteParticular(int nroTramite){
        return expertoRC.obtenerTramiteParticular(nroTramite);
    }
    
    
            
    public List<TramitesConsultorDTO> obtenerTramitesConsultor(int nroTramiteFiltro){
       
        return expertoRC.obtenerTramitesConsultor(nroTramiteFiltro);
    }
    
}
