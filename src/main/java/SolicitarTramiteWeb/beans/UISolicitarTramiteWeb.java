package SolicitarTramiteWeb.beans;

import SolicitarTramiteWeb.dtos.DTOCategoriaTramite;
import SolicitarTramiteWeb.dtos.DTOCliente;
import SolicitarTramiteWeb.dtos.DTOTipoTramiteWeb;
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
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FlowEvent;

@Named("uiSolicitarTramiteWeb")
@ViewScoped

public class UISolicitarTramiteWeb implements Serializable {
    
    private ControladorSolicitarTramiteWeb controladorSolicitarTramiteWeb = new ControladorSolicitarTramiteWeb();
    
    private String cuitCliente;
    private String direccionCliente;
    private String nombreApellidoCliente;
    private String mailCliente;
    private Timestamp fechaHoraBajaCliente;
    private int codCategoriaTramite;
    private String nombreCategoriaTramite;

    public int getCodCategoriaTramite() {
        return codCategoriaTramite;
    }

    public Timestamp getFechaHoraBajaCliente() {
        return fechaHoraBajaCliente;
    }

    public void setFechaHoraBajaCliente(Timestamp fechaHoraBajaCliente) {
        this.fechaHoraBajaCliente = fechaHoraBajaCliente;
    }

    public void setCodCategoriaTramite(int codCategoriaTramite) {
        this.codCategoriaTramite = codCategoriaTramite;
    }

    public String getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(String nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }
    
    
    

    public String getMailCliente() {
        return mailCliente;
    }

    public void setMailCliente(String mailCliente) {
        this.mailCliente = mailCliente;
    }

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
    
    
    public List<UIDatosCategoria> obtenerCategorias()
    {
         List<UIDatosCategoria> lc=new ArrayList<>();
         UIDatosCategoria dc;
         List<DTOCategoriaTramite> a=controladorSolicitarTramiteWeb.obtenerCategoriaTramite();
         for (DTOCategoriaTramite unC: a)
         {
         dc=new UIDatosCategoria();
         //dc.setNombre(Integer.toString(unC.getCodCategoriaTramite()));
         dc.setCodigo((unC.getCodCategoriaTramite()));
         dc.setNombre(unC.getNombreCategoriaTramite());
         lc.add(dc);
         }
         return lc;
                
    }
 
    /*
    public List<UITipoTramite> elegirCategorias()
    {
         List<UITipoTramite> lc=new ArrayList<>();
         UITipoTramite dc;
         List<DTOCategoriaTramite> a=controladorSolicitarTramiteWeb.elegirCategoriaTramite(nombreCategoriaTramite);
         for (DTOCategoriaTramite unT: a)
         {
         dc=new UITipoTramite();
         dc.setCodigo((unT.getCodTipoTramite()));
         dc.setNombre(unT.getNombreTipoTramite());
         dc.setDescripcion(unT.getDescripcionTipoTramiteWeb());
         lc.add(dc);
         }
         return lc;
                
    }
    
    */
    
    
    
    
    public String onFlowProcess(FlowEvent event) {
        DTOCliente dtoC;
        PrimeFaces.current().executeScript("a=$(\".ui-dataview-header\");a.css('visibility', 'hidden');a.css('height', '0');");
        if(event.getOldStep().compareTo("validaciondeid")==0)
        {
            int cuitInt=Integer.parseInt(cuitCliente);
            dtoC=controladorSolicitarTramiteWeb.obtenerCliente(cuitInt);
           if(dtoC.getCuitCliente()>0)
           {
            this.nombreApellidoCliente=dtoC.getNombreApellidoCliente();
            this.direccionCliente=dtoC.getDireccionCliente();
            this.mailCliente=dtoC.getMailCliente();
            
            return event.getNewStep();
            
           }
           else
           {
               this.nombreApellidoCliente="";
               Messages.create("No se encontró el cliente ingresado").fatal().add();
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
