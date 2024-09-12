/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado;

import DTO.D.TramitesConsultorDTO;
import entidades.EstadoTramite;
import java.util.List;

/**
 *
 * @author marti
 */
public class ControladorRegistrarCambioEstado {
    private ExpertoRegistrarCambioEstado expertoRegistrarCambioEstado = new ExpertoRegistrarCambioEstado();
    public List<TramitesConsultorDTO> buscarTramitesConsultor(int nroTramite,String nombreTipoTramite, EstadoTramite estado){
        return expertoRegistrarCambioEstado.buscarTramitesConsultor(nroTramite, nombreTipoTramite, estado);
    }
    
    
}
