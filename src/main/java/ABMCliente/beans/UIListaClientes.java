
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
import org.omnifaces.util.Messages;


@Named("uiListaClientes")
@ViewScoped

public class UIListaClientes implements Serializable{

    private ControladorABMCliente ControladorABMCliente = new ControladorABMCliente();
    private boolean insert;
    private String nombreApellidoCliente;
    private int cuitCliente;
    
}
