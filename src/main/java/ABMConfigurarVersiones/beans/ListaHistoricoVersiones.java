
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
     private int codigo;
     private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
     
    public ControladorABMVersion getControladorABMVersion() {
        return controlador;
    }

    public void setControladorABMVersion(ControladorABMVersion controladorABMVersion) {
        this.controlador = controladorABMVersion;
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
        //String nombre= request.getParameter("nombre");
        //System.out.println("El tipo de trámite es:" + codigo);
        //System.out.println(nombre);
        setCodigo(codigo);
    }

    public List<DTOVersionHistorial> listarHistorico() {
        List<DTOVersionHistorial> listResultado = new ArrayList();
        List<DTOVersionHistorial> listaDto = new ArrayList();
        listaDto = controlador.historialVersiones(getCodigo());
        for (DTOVersionHistorial x : listaDto){
            DTOVersionHistorial y = new DTOVersionHistorial();
            y.setCodTipoTramite(x.getCodTipoTramite());
            y.setCodVersion(x.getCodVersion());
            y.setFechaBajaVersion(x.getFechaBajaVersion());
            y.setFechaDesdeVersion(x.getFechaDesdeVersion());
            y.setFechaHastaVersion(x.getFechaHastaVersion());
            y.setNombreTipoTramite(x.getNombreTipoTramite());
            listResultado.add(y);
        }
        return listResultado;
    }
    
    
        public DTOVersionOutput irVerVersion(int codigo, int codigov) throws VersionException {
        DTOVersionOutput verVersion = new DTOVersionOutput(); //controlador.verVersion(codigo, codigov);
        //BeansUtils.guardarUrlAnterior();
        return verVersion;
    }
}
    
    

