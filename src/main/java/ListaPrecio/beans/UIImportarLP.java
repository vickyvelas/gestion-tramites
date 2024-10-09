/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.beans;

import ListaPrecio.ControladorLP;
import ListaPrecio.dtos.DTOListaPrecioImportar;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import utils.BeansUtils;

@Named("uiImportatLP")
@ViewScoped
/**
 *
 * @author marti
 */
public class UIImportarLP implements Serializable{
    private int codListaPrecio;
    private Timestamp fechaHoraDesdeListaPrecio;
    private Timestamp fechaHoraHastaListaPrecio;
    
    private ControladorLP  controladorLP = new ControladorLP();

    public UIImportarLP() {
        
    }
    
    

    public int getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(int codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
    }

    public Timestamp getFechaHoraDesdeListaPrecio() {
        return fechaHoraDesdeListaPrecio;
    }

    public void setFechaHoraDesdeListaPrecio(Timestamp fechaHoraDesdeListaPrecio) {
        this.fechaHoraDesdeListaPrecio = fechaHoraDesdeListaPrecio;
    }

    public Timestamp getFechaHoraHastaListaPrecio() {
        return fechaHoraHastaListaPrecio;
    }

    public void setFechaHoraHastaListaPrecio(Timestamp fechaHoraHastaListaPrecio) {
        this.fechaHoraHastaListaPrecio = fechaHoraHastaListaPrecio;
    }
    
    public String importar(){
        DTOListaPrecioImportar listaPrecioImportar = new DTOListaPrecioImportar();
            listaPrecioImportar.setCodListaPrecio(getCodListaPrecio());
            listaPrecioImportar.setFechaHoraDesdeListaPrecio(getFechaHoraDesdeListaPrecio());
            listaPrecioImportar.setFechaHoraHastaListaPrecio(getFechaHoraHastaListaPrecio());
//            ExcelFileUI excel = new ExcelFileUI();
//            listaPrecioImportar.setDetalleListaPrecioImportar(excel.handleFileUpload(event).));
            
            controladorLP.importarListaPrecio(listaPrecioImportar);
            return BeansUtils.redirectToPreviousPage();
    }
    
}
