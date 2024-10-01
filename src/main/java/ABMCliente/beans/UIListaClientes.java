
package ABMCliente.beans;

import utils.BeansUtils;
import ABMCliente.ControladorABMCliente;
import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.ListaClienteDTO;
//import ABMCliente.exceptions.nombreclaseExceptions;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;


@Named("uiListaClientes")
@ViewScoped

public class UIListaClientes implements Serializable{

    private ControladorABMCliente controladorABMCliente = new ControladorABMCliente();
   
    private String nombreApellidoCliente;
    private int cuitCliente;
    private Timestamp fechaHoraBajaCliente;
    
    
    
    //metodo obtener clientes
    public List<UIGrillaClientes> obtenerListaCliente(){
        List<UIGrillaClientes> uiGrillaCliente = new ArrayList<>();
        List<ListaClienteDTO> listaClienteDTOs = controladorABMCliente.obtenerListaCliente();
        
        for(ListaClienteDTO listaClienteDTO : listaClienteDTOs){
            UIGrillaClientes uiGrillaClientes = new UIGrillaClientes();
            uiGrillaClientes.setCuitCliente(listaClienteDTO.getCuitCliente());
            uiGrillaClientes.setNombreApellidoCliente(listaClienteDTO.getNombreApellidoCliente());
            uiGrillaClientes.setFechaHoraBajaCliente(listaClienteDTO.getFechaHoraBajaCliente());
            uiGrillaCliente.add(uiGrillaClientes);
            
        }
        return uiGrillaCliente;
        
    }
            
    
    //metodo pasar de pagina a otra
    
    public String irACliente(){
         BeansUtils.guardarUrlAnterior();
         return "detalleCliente.xhtml?faces-redirect=true";
    }
    
    
    
}
