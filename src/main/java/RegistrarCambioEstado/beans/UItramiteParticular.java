/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado.beans;

import RegistrarCambioEstado.ControladorRegistrarCambioEstado;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;

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
    
    public UItramiteParticular(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//        
//        int codigo = Integer.parseInt(request.getParameter("codigo"));
//        insert=true;
//        if(codigo > 0)
//        {
//            insert=false;
//            ModificarEstadoDTO modificarEstadoDTO = controladorABMEstado.buscarEstadoAModificar(codigo);
//            setNombre(modificarEstadoDTO.getNombre());
//            setCodigo(modificarEstadoDTO.getCodigo());
//        }
        
    
    }
}
