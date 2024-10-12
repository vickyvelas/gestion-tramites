/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCliente.beans;

import ABMCliente.ControladorABMCliente;
import ABMCliente.ExceptionABMCliente;
import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.DetalleClienteDTOIn;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("uiDetalleCliente")
@ViewScoped
public class UIDetalleCliente implements Serializable{
    private boolean insert;
    private int cuitCliente;
    private String nombreApellidoCliente;
    private String direccion;
    private String mailCliente;
    private double telefonoCliente;
    private ControladorABMCliente controladorABMCliente = new ControladorABMCliente();
    
    public boolean isInsert() {
        return insert;
    }

    //getter and setter
    public void setInsert(boolean insert) {    
        this.insert = insert;
    }

    public int getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(int cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMailCliente() {
        return mailCliente;
    }

    public void setMailCliente(String mailCliente) {
        this.mailCliente = mailCliente;
    }

    public double getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(double telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public ControladorABMCliente getControladorABMCliente() {
        return controladorABMCliente;
    }

    public void setControladorABMCliente(ControladorABMCliente controladorABMCliente) {
        this.controladorABMCliente = controladorABMCliente;
    }
    
    
        public UIDetalleCliente() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int cuitCliente = Integer.parseInt(request.getParameter("cuit"));
        insert = true;
        if(cuitCliente>0)
        {
            insert=false;
            DetalleClienteDTO  detalleClienteDTO =controladorABMCliente.obtenerDetalleCliente(cuitCliente);
            setNombreApellidoCliente(detalleClienteDTO.getNombreApellidoCliente());
            setCuitCliente(detalleClienteDTO.getCuitCliente());
            setDireccion(detalleClienteDTO.getDireccion());
            setMailCliente(detalleClienteDTO.getMailCliente());
            setTelefonoCliente(detalleClienteDTO.getTelefonoCliente());
                   
        }
    }
    
    public String agregarCliente(){
        try{
            if(!insert)
            {
                DetalleClienteDTOIn detalleClienteDTOIn = new DetalleClienteDTOIn();
                detalleClienteDTOIn.setCuitCliente(getCuitCliente());
                detalleClienteDTOIn.setNombreApellidoCliente(getNombreApellidoCliente());
                controladorABMCliente.modificarCliente(cuitCliente, detalleClienteDTOIn);
                return BeansUtils.redirectToPreviousPage();
            }
            else
            {
            DetalleClienteDTO detalleClienteDTO = new DetalleClienteDTO();
            detalleClienteDTO.setCuitCliente(getCuitCliente());
            detalleClienteDTO.setNombreApellidoCliente(getNombreApellidoCliente());
            detalleClienteDTO.setDireccion(getDireccion());
            detalleClienteDTO.setMailCliente(getMailCliente());
            detalleClienteDTO.setTelefonoCliente(getTelefonoCliente());
            controladorABMCliente.agregarCliente(detalleClienteDTO);
            
            }
            return BeansUtils.redirectToPreviousPage();
            
        }
        catch (ExceptionABMCliente e){
            Messages.create(e.getMessage()).fatal().add();
            return "";
        }
    }
        

}
    
    

