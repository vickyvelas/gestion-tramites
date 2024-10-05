package ActualizarListaPrecio.beans;
import utils.BeansUtils;
import ActualizarListaPrecio.ControladorActualizarListaPrecio;
import ActualizarListaPrecio.dtos.DTOListaPrecioExportar;
import ActualizarListaPrecio.dtos.DTOListaPrecioImportar;
import ActualizarListaPrecio.exceptions.ListaPrecioException;
import jakarta.annotation.ManagedBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

@Named("uiActualizarListaPrecioMenu")
@ViewScoped
public class UIActualizarListaPrecioMenu implements Serializable {

    private ControladorActualizarListaPrecio controladorActualizarListaPrecio = new ControladorActualizarListaPrecio();
    private Timestamp fechaFiltro;

    public UIActualizarListaPrecioMenu() {
        List<UIActualizarListaPrecioGrilla> listaPrecioGrilla = new ArrayList<>();
        List<DTOListaPrecioExportar> listapreciosDTO = controladorActualizarListaPrecio.obtenerListaPrecio(fechaFiltro);
        for (DTOListaPrecioExportar listaprecioDTO : listapreciosDTO) {
            UIActualizarListaPrecioGrilla listaprecioGrillaUI = new UIActualizarListaPrecioGrilla();
            listaprecioGrillaUI.setCodListaPrecio(listaprecioDTO.getCodListaPrecio());
            listaprecioGrillaUI.setFechaDesde(listaprecioDTO.getFechaHoraDesdeListaPrecio());
            listaprecioGrillaUI.setFechaHasta(listaprecioDTO.getFechaHoraHastaListaPrecio());
            //listaprecioGrillaUI.setFechaBaja(listaprecioDTO.);
                //no lo planteamos en la secuencia
            listaPrecioGrilla.add(listaprecioGrillaUI);
        }
    }
    
    
    

    public Timestamp getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(Timestamp fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }

   
    
   
    public void filtrar()
    {

    }

    public List<UIActualizarListaPrecioGrilla> buscarListaPrecio(){
        
        List<UIActualizarListaPrecioGrilla> listaPrecioGrilla = new ArrayList<>();
        List<DTOListaPrecioExportar> listapreciosDTO = controladorActualizarListaPrecio.obtenerListaPrecio(fechaFiltro);
        for (DTOListaPrecioExportar listaprecioDTO : listapreciosDTO) {
            UIActualizarListaPrecioGrilla listaprecioGrillaUI = new UIActualizarListaPrecioGrilla();
            listaprecioGrillaUI.setCodListaPrecio(listaprecioDTO.getCodListaPrecio());
            listaprecioGrillaUI.setFechaDesde(listaprecioDTO.getFechaHoraDesdeListaPrecio());
            listaprecioGrillaUI.setFechaHasta(listaprecioDTO.getFechaHoraHastaListaPrecio());
            //listaprecioGrillaUI.setFechaBaja(listaprecioDTO.);
                //no lo planteamos en la secuencia
            listaPrecioGrilla.add(listaprecioGrillaUI);
        }
        return listaPrecioGrilla;
    }

    public String irAgregarListaPrecio() {
        BeansUtils.guardarUrlAnterior();
        return "actualizarListaPrecio?faces-redirect=true&codigo=0"; // Usa '?faces-redirect=true' para hacer una redirección
    }

    
    public String irModificarListaPrecio(int codigo) {
        BeansUtils.guardarUrlAnterior();
        return "actualizarListaPrecio?faces-redirect=true&codigo=" + codigo; // Usa '?faces-redirect=true' para hacer una redirección
    }

//    public void darDeBajaListaPrecio(int codigo){
//        try {
//            controladorActualizarListaPrecio.darDeBajaListaPrecio(codigo);
//            Messages.create("Anulado").detail("Anulado").add();
//                    
//        } catch (ListaPrecioException e) {
//            Messages.create("Error!").error().detail(e.getMessage()).add();
//        }
//    }
//    
}