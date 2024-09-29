/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author marti
 */
@Named("uiDestinosPosibles")
@ViewScoped

public class UIDestinosPosibles implements Serializable{
    private String nombreEstadoTramiteDestino;
    private int codEstadoTramiteDestino;

    public String getNombreEstadoTramiteDestino() {
        return nombreEstadoTramiteDestino;
    }

    public void setNombreEstadoTramiteDestino(String nombreEstadoTramiteDestino) {
        this.nombreEstadoTramiteDestino = nombreEstadoTramiteDestino;
    }

    public int getCodEstadoTramiteDestino() {
        return codEstadoTramiteDestino;
    }

    public void setCodEstadoTramiteDestino(int codEstadoTramiteDestino) {
        this.codEstadoTramiteDestino = codEstadoTramiteDestino;
    }
    
    
}
