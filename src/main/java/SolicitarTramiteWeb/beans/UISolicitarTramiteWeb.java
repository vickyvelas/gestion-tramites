package SolicitarTramiteWeb.beans;

import SolicitarTramiteWeb.dtos.DTOCliente;
import SolicitarTramiteWeb.exceptions.ControladorSolicitarTramiteWeb;
import com.google.gson.Gson;
import entidades.EstadoTramite;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import org.omnifaces.util.Messages;
import org.primefaces.event.FlowEvent;

@Named("uiSolicitarTramiteWeb")
@ViewScoped

public class UISolicitarTramiteWeb implements Serializable {
    
    private ControladorSolicitarTramiteWeb controladorSolicitarTramiteWeb = new ControladorSolicitarTramiteWeb();
    
    private String cuitCliente;
    private String direccionCliente;
    private String nombreApellidoCliente;
    private Timestamp fechaHoraBajaCliente;

    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }
   
 
    public String onFlowProcess(FlowEvent event) {
        DTOCliente dtoC;
        if(event.getOldStep().compareTo("nuevoTramite")==0)
        {
            int cuitInt=Integer.parseInt(cuitCliente);
            dtoC=controladorSolicitarTramiteWeb.obtenerCliente(cuitInt);
           if(dtoC.getCuitCliente()>0)
           {
            this.nombreApellidoCliente=dtoC.getNombreApellidoCliente();
            return event.getNewStep();
           }
           else
           {
               this.nombreApellidoCliente="";
               Messages.create("El cliente no existe").fatal().add();
               return event.getOldStep();        
           }
        }
        else
        {
        System.out.println(event.getOldStep());
    
            return event.getNewStep();
        }
    }
/*
    public Timestamp getFechaHoraBajaCliente() {
        return fechaHoraBajaCliente;
    }

    public void setFechaHoraBajaCliente(Timestamp fechaHoraBajaCliente) {
        this.fechaHoraBajaCliente = fechaHoraBajaCliente;
    }
    
    

    public ControladorSolicitarTramiteWeb getControladorSolicitarTramiteWeb() {
        return controladorSolicitarTramiteWeb;
    }

    public void setControladorSolicitarTramiteWeb(ControladorSolicitarTramiteWeb controladorSolicitarTramiteWeb) {
        this.controladorSolicitarTramiteWeb = controladorSolicitarTramiteWeb;
    }
*/    
    
    
    
   
}
