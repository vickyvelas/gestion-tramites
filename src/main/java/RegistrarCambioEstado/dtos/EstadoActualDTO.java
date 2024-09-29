 package RegistrarCambioEstado.dtos;

import DTO.D.DestinosPosiblesDTO;
import java.util.List;


public class EstadoActualDTO {

    private String nombreEstadoTramiteActual;
    private List<DestinosPosiblesDTO> destinosPosiblesList; 

    // getters y setters
    public List<DestinosPosiblesDTO> getDestinosPosiblesList() {
        return destinosPosiblesList;
    }

    public void setDestinosPosiblesList(List<DestinosPosiblesDTO> destinosPosibles) {
        this.destinosPosiblesList = destinosPosibles;
    }

    public String getNombreEstadoTramiteActual() {
        return nombreEstadoTramiteActual;
    }

    public void setNombreEstadoTramiteActual(String nombreEstadoTramiteActual) {
        this.nombreEstadoTramiteActual = nombreEstadoTramiteActual;
    }  

    
}