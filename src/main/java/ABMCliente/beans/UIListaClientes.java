
package ABMCliente.beans;

import utils.BeansUtils;
import ABMCliente.ControladorABMCliente;
import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.ListaClienteDTO;
import ABMCliente.ExceptionABMCliente;

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


@Named("uiListaClientes") // Este nombre debe coincidir con el que usas en el archivo XHTML
@ViewScoped  // Asegúrate de tener el alcance correcto

public class UIListaClientes implements Serializable{
    private ControladorABMCliente controladorABMCliente = new ControladorABMCliente();
    private String nombreApellidoClienteFiltro = "";
    private int cuitClienteFiltro = 0;
    private Timestamp fechaHoraBajaCliente;
    
//Getter and Setter

    public ControladorABMCliente getControladorABMCliente() {
        return controladorABMCliente;
    }

    public void setControladorABMCliente(ControladorABMCliente controladorABMCliente) {
        this.controladorABMCliente = controladorABMCliente;
    }

    public String getNombreApellidoClienteFiltro() {
        return nombreApellidoClienteFiltro;
    }

    public void setNombreApellidoClienteFiltro(String nombreApellidoClienteFiltro) {
        this.nombreApellidoClienteFiltro = nombreApellidoClienteFiltro;
    }

    public int getCuitClienteFiltro() {
        return cuitClienteFiltro;
    }

    public void setCuitClienteFiltro(int cuitClienteFiltro) {
        this.cuitClienteFiltro = cuitClienteFiltro;
    }

    public Timestamp getFechaHoraBajaCliente() {
        return fechaHoraBajaCliente;
    }

    public void setFechaHoraBajaCliente(Timestamp fechaHoraBajaCliente) {
        this.fechaHoraBajaCliente = fechaHoraBajaCliente;
    }
    
    
    public void filtrar(){
        //Implementar el filtro si es necesario
       
    }

    //metodo obtener clientes
    public List<UIGrillaClientes> obtenerListaCliente(){
        List<UIGrillaClientes> uiGrillaCliente = new ArrayList<>();
        List<ListaClienteDTO> listaClienteDTOs = controladorABMCliente.obtenerListaCliente(cuitClienteFiltro, nombreApellidoClienteFiltro);
        
        for(ListaClienteDTO listaClienteDTO : listaClienteDTOs){
            UIGrillaClientes uiGrillaClientes = new UIGrillaClientes();
            uiGrillaClientes.setCuitCliente(listaClienteDTO.getCuitCliente());
            uiGrillaClientes.setNombreApellidoCliente(listaClienteDTO.getNombreApellidoCliente());
            uiGrillaClientes.setFechaHoraBajaCliente(listaClienteDTO.getFechaHoraBajaCliente());
            uiGrillaCliente.add(uiGrillaClientes);
            
        }
        return uiGrillaCliente;
        
    }
            
     // Redirigir a la página para modificar un consultor existente
    public String irModificarCliente(int cuitCliente) {
        BeansUtils.guardarUrlAnterior();
        return "detalleCliente?faces-redirect=true&cuit=" + cuitCliente;
    }

    // Método para dar de baja un cliente
    public void eliminarCliente(int cuitCliente) {
        try {
            controladorABMCliente.eliminarCliente(cuitCliente);
            Messages.create("Anulado").detail("Anulado").add();
          } catch (ExceptionABMCliente e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
// Redirigir a la página para agregar un nuevo cliente
    public String irAgregarCliente() {
        BeansUtils.guardarUrlAnterior();
        return "detalleCliente.xhtml?faces-redirect=true&cuit=0";
    }
    
}

    //metodo pasar de pagina a otra
    
//    public String irACliente(){
//         BeansUtils.guardarUrlAnterior();
//         return "detalleCliente.xhtml?faces-redirect=true&codigo=" + cuitCliente;
//    }
    
    
    

