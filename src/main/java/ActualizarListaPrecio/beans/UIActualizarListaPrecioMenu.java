package ActualizarListaPrecio.beans;
import utils.BeansUtils;
import ActualizarListaPrecio.ControladorActualizarListaPrecio;
import ActualizarListaPrecio.dtos.DTOListaPrecioImportar;
import ActualizarListaPrecio.exceptions.ListaPrecioException;
import jakarta.annotation.ManagedBean;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import org.primefaces.PrimeFaces;

@Named("uiActualizarListaPrecioMenu")
@ViewScoped
public class UIActualizarListaPrecioMenu implements Serializable {

    private ControladorActualizarListaPrecio controladorActualizarListaPrecio = new ControladorActualizarListaPrecio();
    private int codigoFiltro=0;
    private String nombreFiltro="";

    public ControladorActualizarListaPrecio getControladorActualizarListaPrecio() {
        return controladorActualizarListaPrecio;
    }

    public void setControladorActualizarListaPrecio(ControladorActualizarListaPrecio controladorActualizarListaPrecio) {
        this.controladorActualizarListaPrecio = controladorActualizarListaPrecio;
    }

    public int getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(int codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public String getNombreFiltro() {
        return nombreFiltro;
    }

    public void setNombreFiltro(String descripcionFiltro) {
        this.nombreFiltro = descripcionFiltro;
    }
    public void filtrar()
    {

    }

    public List<UIActualizarListaPrecioGrilla> buscarListaPrecio(){
        System.out.println(codigoFiltro);
        System.out.println(nombreFiltro);
        List<UIActualizarListaPrecioGrilla> listaPrecioGrilla = new ArrayList<>();
        //List<DTOListaPrecioImportar> listapreciosDTO = controladorActualizarListaPrecio.buscarListaPrecio(codigoFiltro,nombreFiltro);
//        for (DTOListaPrecioImportar listaprecioDTO : listapreciosDTO) {
//            UIActualizarListaPrecioGrilla listaprecioGrillaUI = new UIActualizarListaPrecioGrilla();
 /*           listaprecioGrillaUI.setCodigo(listaprecioDTO.getCodigo());
            listaprecioGrillaUI.setNombre(listaprecioDTO.getNombre());
            listaprecioGrillaUI.setFechaAlta(listaprecioDTO.getFechaAlta());
            listaprecioGrillaUI.setFechaBaja(listaprecioDTO.getFechaBaja());
            listaPrecioGrilla.add(listaprecioGrillaUI);*/
        //}
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

    public void darDeBajaListaPrecio(int codigo){
        try {
            controladorActualizarListaPrecio.darDeBajaListaPrecio(codigo);
            Messages.create("Anulado").detail("Anulado").add();
                    
        } catch (ListaPrecioException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
    
}