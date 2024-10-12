package CargarTramite.beans;
import CargarTramite.ControladorCargarTramite;
import CargarTramite.dtos.DTOClienteV;
import CargarTramite.dtos.DTOTipoTramiteV; 
import CargarTramite.exceptions.TramiteException;
import entidades.Tramite;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;


@Named("uicargarTramite")
@ViewScoped
public class UICargarTramite implements Serializable {

    private int cuit;
    private int codigo;
    private CargarTramiteGrillaUI cargarTramiteGrilla = new CargarTramiteGrillaUI();
    private ControladorCargarTramite controladorCargarTramite = new ControladorCargarTramite();
    private boolean noEncontrado;
    private String mensajeError;
    
    //asi es 


    public UICargarTramite() {
    }

    
    public CargarTramiteGrillaUI obtenerCliente(int cuit) throws Exception {
        try {
             DTOClienteV dtoCliente = controladorCargarTramite.obtenerCliente(cuit);
             cargarTramiteGrilla.setCuit(dtoCliente.getCUIT());
             cargarTramiteGrilla.setNombreCliente(dtoCliente.getNombreCliente());
             cargarTramiteGrilla.setDireccion(dtoCliente.getDireccion());
             System.out.println(cargarTramiteGrilla.getCuit());
             System.out.println(cargarTramiteGrilla.getNombreCliente());
             System.out.println(cargarTramiteGrilla.getDireccion());
             return cargarTramiteGrilla;
        } catch (Exception e) {
             mensajeError="Cliente no registrado.";
             cargarTramiteGrilla.setNombreCliente(mensajeError); 
             return cargarTramiteGrilla;
        }
    } 
    
    
        public CargarTramiteGrillaUI obtenerTipoTramite(int codigo) {
        try {
            System.out.println("enttramos al tipo tramite");
             DTOTipoTramiteV dtoTT = controladorCargarTramite.obtenerTipoTramite(codigo);
             cargarTramiteGrilla.setCodigo(dtoTT.getCodigoTT());
             cargarTramiteGrilla.setNombreTipoTramite(dtoTT.getNombreTipoTramite());
             System.out.println("RETORNAMOS EL TIPOTRAMITE");
             return cargarTramiteGrilla;
        } catch (Exception e) {
             mensajeError="Tipo Tramite no registrado.";
             cargarTramiteGrilla.setNombreTipoTramite(mensajeError); 
             return cargarTramiteGrilla;
            }
        }
        
        public String cancelar(){
            return "mostrarTramitesLista?redirect=true";
        }
        
        public String botonCargar(int cuit, int codigoTT) throws TramiteException, Exception{
            return controladorCargarTramite.botonCargar(cuit, codigoTT);
        }
    
    // Getters y setters
    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    
    public CargarTramiteGrillaUI getCargarTramiteGrilla() {
        return cargarTramiteGrilla;
    }

    public void setCargarTramiteGrilla(CargarTramiteGrillaUI cargarTramiteGrilla) {
        this.cargarTramiteGrilla = cargarTramiteGrilla;
    }

    public ControladorCargarTramite getControladorCargarTramite() {
        return controladorCargarTramite;
    }

    public void setControladorCargarTramite(ControladorCargarTramite controladorCargarTramite) {
        this.controladorCargarTramite = controladorCargarTramite;
    }
    
    
    public boolean isNoEncontrado() {
        return noEncontrado;
    }

    public void setNoEncontrado(boolean noEncontrado) {
        this.noEncontrado = noEncontrado;
    }
    
    
    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    
   
}

