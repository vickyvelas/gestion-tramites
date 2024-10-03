/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCliente.beans;

import ABMCliente.ControladorABMCliente;
import ABMCliente.dtos.DetalleClienteDTO;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Named("uiDetalleCliente")
@ViewScoped
public class UIDetalleCliente implements Serializable{
    
    private int cuitCliente;
    private String nombreApellidoCliente;
    private String direccion;
    private String mailCliente;
    private double telefonoCliente;
    private ControladorABMCliente controladorABMCliente = new ControladorABMCliente();

    public UIDetalleCliente() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        cuitCliente = Integer.parseInt(request.getParameter("cuitCliente"));
        
        DetalleClienteDTO  detalleClienteDTO =controladorABMCliente.obtenerDetalleCliente(cuitCliente);
             nombreApellidoCliente=detalleClienteDTO.getNombreApellidoCliente();
    
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
    
    
    
}
