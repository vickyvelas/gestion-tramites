
// Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 //Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template


package ABMConfigurarVersiones.beans;
import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.exceptions.ControladorABMVersion;
import ABMConfigurarVersiones.exceptions.VersionException;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("uiabmVersionHistorialLista")
@ViewScoped
public class ListaHistoricoVersiones implements Serializable{
     private ControladorABMVersion controlador = new ControladorABMVersion();

    public ControladorABMVersion getControladorABMVersion() {
        return controlador;
    }

    public void setControladorABMVersion(ControladorABMVersion controladorABMVersion) {
    //    this.controlador = controladorABMVersion;
    }
   
    public void irAnularVersion(int codigo, int codigov){
        /*
        try{
        controlador.anularVersion(codigov, codigo);
        Messages.create("Anulado").detail("Anulado").add();
        }catch (VersionException e) {
          Messages.create("Error!").error().detail(e.getMessage()).add();
        }
        */
    }

    public ListaHistoricoVersiones() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int codigo = Integer.parseInt(request.getParameter("tipoTramite"));
        String nombre= request.getParameter("nombre");
        System.out.println("El tipo de trámite es:" + codigo);
        System.out.println(nombre);
        
    }
    
    public  List<DTOVersionHistorial> irHistorialVersion(int codigo) {
        
        List<DTOVersionHistorial> listResultado = new ArrayList<DTOVersionHistorial>();// controlador.historialVersiones(codigo);
       // BeansUtils.guardarUrlAnterior();
        return listResultado; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
        public DTOVersionOutput irVerVersion(int codigo, int codigov) throws VersionException {
        DTOVersionOutput verVersion = new DTOVersionOutput(); //controlador.verVersion(codigo, codigov);
        //BeansUtils.guardarUrlAnterior();
        return verVersion;
    }
}
    
    

