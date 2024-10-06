package ActualizarListaPrecio.beans;

import ActualizarListaPrecio.ControladorActualizarListaPrecio;
import ActualizarListaPrecio.dtos.DTOListaPrecioExportar;
import ActualizarListaPrecio.exceptions.ListaPrecioException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("uiActualizarListaPrecioMenu")
@ViewScoped
public class UIActualizarListaPrecioMenu implements Serializable {

    private ControladorActualizarListaPrecio controladorActualizarListaPrecio = new ControladorActualizarListaPrecio();
    private Timestamp fechaFiltro;
    private List<UIImportarListaPrecio> listaPrecioGrilla;
    private UIImportarListaPrecio listaPrecioSeleccionada;

    public UIActualizarListaPrecioMenu() {
        listaPrecioGrilla = new ArrayList<>();
        listaPrecioGrilla = buscarListaPrecio();
    }

    public Timestamp getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(Timestamp fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }

    public List<UIImportarListaPrecio> getListaPrecioGrilla() {
        return listaPrecioGrilla;
    }

    public void setListaPrecioGrilla(List<UIImportarListaPrecio> listaPrecioGrilla) {
        this.listaPrecioGrilla = listaPrecioGrilla;
    }

    public UIImportarListaPrecio getListaPrecioSeleccionada() {
        return listaPrecioSeleccionada;
    }

    public void setListaPrecioSeleccionada(UIImportarListaPrecio listaPrecioSeleccionada) {
        this.listaPrecioSeleccionada = listaPrecioSeleccionada;
    }

    public void filtrar() {
        listaPrecioGrilla = buscarListaPrecio();
    }

    public List<UIImportarListaPrecio> buscarListaPrecio() {
        List<UIImportarListaPrecio> listaPrecioGrilla = new ArrayList<>();
        List<DTOListaPrecioExportar> listapreciosDTO = controladorActualizarListaPrecio.obtenerListaPrecio(fechaFiltro);
        for (DTOListaPrecioExportar listaprecioDTO : listapreciosDTO) {
            UIImportarListaPrecio listaprecioGrillaUI = new UIImportarListaPrecio();
            listaprecioGrillaUI.setCodListaPrecio(listaprecioDTO.getCodListaPrecio());
            listaprecioGrillaUI.setFechaHoraDesdeListaPrecio(listaprecioDTO.getFechaHoraDesdeListaPrecio());
            listaprecioGrillaUI.setFechaHoraHastaListaPrecio(listaprecioDTO.getFechaHoraHastaListaPrecio());
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

    public void darDeBajaListaPrecio(int codListaPrecio) {
        try {
            controladorActualizarListaPrecio.darDeBajaListaPrecio(codListaPrecio);
            Messages.create("Anulado").detail("Anulado").add();
        } catch (ListaPrecioException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
    
    
    
    
//no se si estos dos van, pero servirian para los botones
    
    
//    public void eliminarListaPrecio() {
//        if (listaPrecioSeleccionada != null) {
//            try {
//                controladorActualizarListaPrecio.eliminarListaPrecio(listaPrecioSeleccionada.getCodListaPrecio());
//                listaPrecioGrilla.remove(listaPrecioSeleccionada);
//                Messages.create("Eliminado").detail("Eliminado").add();
//            } catch (ListaPrecioException e) {
//                Messages.create("Error!").error().detail(e.getMessage()).add();
//            }
//        } else {
//            Messages.create("Seleccione una lista de precio").detail("Seleccione una lista de precio").add();
//        }
//    }
//
//    public void editarListaPrecio() {
//        if (listaPrecioSeleccionada != null) {
//            try {
//                controladorActualizarListaPrecio.editarListaPrecio(listaPrecioSeleccionada);
//                Messages.create("Editado").detail("Editado").add();
//            } catch (ListaPrecioException e) {
//                Messages.create("Error!").error().detail(e.getMessage()).add();
//            }
//        }
//    }
}
