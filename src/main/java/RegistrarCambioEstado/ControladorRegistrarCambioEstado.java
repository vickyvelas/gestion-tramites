/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado;

import RegistrarCambioEstado.dtos.TramitesConsultorDTO;
import DTO.D.TransicionDTO;
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
    public void cambioEstado(TransicionDTO  transicionDTO)throws cambioEstadoException{
        expertoRegistrarCambioEstado.cambioEstado(transicionDTO);
    }
    
}
