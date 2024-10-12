/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.beans;

import ListaPrecio.ControladorLP;
import ListaPrecio.dtos.DTOListaPrecioExportar;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.BeansUtils;

/**
 *
 * @author marti
 */
@Named("uiActualizarLP")
@ViewScoped

public class UIActualizarLP implements Serializable {

    private Date fechaDesde;
    private ControladorLP controladorLP = new ControladorLP();

    public UIActualizarLP() {
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public List<UIActualizarLPGrilla> obtenerListaPrecio() {
        // Convertir Date a Timestamp antes de usarlo
        Timestamp fechaDesdeTS = (fechaDesde != null) ? new Timestamp(fechaDesde.getTime()) : null;
        
        List<UIActualizarLPGrilla> listaPreciogrilla = new ArrayList<>();
        List<DTOListaPrecioExportar> dtoListasPreciosExportar = controladorLP.obtenerListaPrecio(fechaDesdeTS);
        for (DTOListaPrecioExportar dtoListaPrecioExportar : dtoListasPreciosExportar) {
            UIActualizarLPGrilla uiActualizarLPGrilla = new UIActualizarLPGrilla();
            uiActualizarLPGrilla.setCodListaPrecio(dtoListaPrecioExportar.getCodListaPrecio());
            uiActualizarLPGrilla.setFechaHoraDesdeListaPrecio(dtoListaPrecioExportar.getFechaHoraDesdeListaPrecio());
            uiActualizarLPGrilla.setFechaHoraHastaListaPrecio(dtoListaPrecioExportar.getFechaHoraHastaListaPrecio());
            listaPreciogrilla.add(uiActualizarLPGrilla);
        }

        return listaPreciogrilla;
    }

    public void filtrar() {

    }

    private List<UIActualizarLPGrilla> listaPreciogrillaFiltrada;

    public List<UIActualizarLPGrilla> getListaPreciogrillaFiltrada() {
        return listaPreciogrillaFiltrada;
    }
    
    public String irAImportar() {
        BeansUtils.guardarUrlAnterior();
        return "importarLP.xhtml?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
    }

}
