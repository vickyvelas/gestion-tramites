/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.beans;

import ListaPrecio.ControladorLP;
import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
import ListaPrecio.dtos.DTOListaPrecioImportar;
import ListaPrecio.exceptions.ListaPrecioException;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;
import org.omnifaces.util.Messages;
import org.primefaces.event.FileUploadEvent;
import utils.BeansUtils;

@Named("uiImportatLP")
@ViewScoped
/**
 *
 * @author marti
 */
public class UIImportarLP implements Serializable {

    private int codListaPrecio;
    private Date fechaHoraDesdeListaPrecio;
    private Date fechaHoraHastaListaPrecio;
    private List<DTODetalleListaPrecioImportar> detallesImportar = new ArrayList<>();
    private ControladorLP controladorLP = new ControladorLP();

    public UIImportarLP() {
    }

    public int getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(int codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
    }

    public Date getFechaHoraDesdeListaPrecio() {
        return fechaHoraDesdeListaPrecio;
    }

    public void setFechaHoraDesdeListaPrecio(Date fechaHoraDesdeListaPrecio) {
        this.fechaHoraDesdeListaPrecio = fechaHoraDesdeListaPrecio;
    }

    public Date getFechaHoraHastaListaPrecio() {
        return fechaHoraHastaListaPrecio;
    }

    public void setFechaHoraHastaListaPrecio(Date fechaHoraHastaListaPrecio) {
        this.fechaHoraHastaListaPrecio = fechaHoraHastaListaPrecio;
    }

    public String irAMenuPrincipal() {
        BeansUtils.guardarUrlAnterior();
        return "actualizarLP.xhtml?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
    }

    // METODO IMPORTAR EXCEL
    public void handleFileUpload(FileUploadEvent event) {
        try (InputStream inp = event.getFile().getInputStream()) {
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            int iRow = 1; // Cambia a 0 si quieres leer desde la primera fila

            // Limpiar la lista antes de importar nuevos datos
            detallesImportar.clear();

            // Leer las filas del archivo Excel
            while (true) {
                Row row = sheet.getRow(iRow);
                if (row == null) {
                    break; // Salir si no hay más filas
                }
                DTODetalleListaPrecioImportar dtoDLPI = new DTODetalleListaPrecioImportar();

                // Leer el código del trámite
                Cell codTTCell = row.getCell(0);
                if (codTTCell != null && codTTCell.getCellType() == CellType.NUMERIC) {
                    dtoDLPI.setCodTipoTramite((int) codTTCell.getNumericCellValue());
                } else {
                    System.out.println("Error: Código no válido en la fila " + (iRow + 1));
                }

                // Leer el importe
                Cell precioTT = row.getCell(2);
                if (precioTT != null && precioTT.getCellType() == CellType.NUMERIC) {
                    dtoDLPI.setPrecioTipoTramite((int) precioTT.getNumericCellValue());
                } else {
                    System.out.println("Error: Precio no válido en la fila " + (iRow + 1));
                }
                detallesImportar.add(dtoDLPI); // Agregar el dto a la lista
                iRow++;
            }

            // Comprobar si se han cargado trámites
            if (detallesImportar.isEmpty()) {
                throw new ListaPrecioException("No se han cargado detalles desde el archivo Excel.");
            }

        } catch (Exception ex) {
            Logger.getLogger(UIImportarLP.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
        }
    }

    public void importar() {
        try {
            // Primero, verificar qué se ha subido al archivo
            if (detallesImportar.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "No se han cargado detalles desde el archivo Excel.", null));
                return;
            }

            // Crear la lista de precios
            DTOListaPrecioImportar listaPrecioImportar = new DTOListaPrecioImportar();
            listaPrecioImportar.setCodListaPrecio(getCodListaPrecio());
            listaPrecioImportar.setFechaHoraDesdeListaPrecio(getFechaHoraDesdeListaPrecio());
            listaPrecioImportar.setFechaHoraHastaListaPrecio(getFechaHoraHastaListaPrecio());

            // Llamar al método del controlador con ambos parámetros
            controladorLP.importarListaPrecio(listaPrecioImportar, detallesImportar);

            // Mensaje de éxito
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Lista y Detalles guardados exitosamente.", null));

        }catch (ListaPrecioException e){
            Messages.create("Error al guardar la Lista de Precios: " + e.getMessage()).error().add();
        } catch (Exception e) {
            Messages.create("Error inesperado: " + e.getMessage()).error().add();

        }
    }
}
