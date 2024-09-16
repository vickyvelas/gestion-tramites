
// Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 //Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template


package ABMConfigurarVersiones.beans;
import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.exceptions.ControladorABMVersion;
import ABMConfigurarVersiones.exceptions.VersionException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
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
        this.controlador = controladorABMVersion;
    }
   
    public void irAnularVersion(int codigo, int codigov){
        try{
        controlador.anularVersion(codigov, codigo);
        Messages.create("Anulado").detail("Anulado").add();
        }catch (VersionException e) {
          Messages.create("Error!").error().detail(e.getMessage()).add();
        }
        
    }
    public  List<DTOVersionHistorial> irHistorialVersion(int codigo) {
        List<DTOVersionHistorial> listResultado = controlador.historialVersiones(codigo);
        BeansUtils.guardarUrlAnterior();
        return listResultado; // Usa '?faces-redirect=true' para hacer una redirección
    }
    
        public DTOVersionOutput irVerVersion(int codigo, int codigov) throws VersionException {
        DTOVersionOutput verVersion = controlador.verVersion(codigo, codigov);
        BeansUtils.guardarUrlAnterior();
        return verVersion;
    }
}
    
    

