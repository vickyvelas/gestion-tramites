///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package ListaPrecio.beans;
//
//import ListaPrecio.ControladorLP;
//import ListaPrecio.dtos.DTODetalleListaPrecioExportar;
//import ListaPrecio.dtos.DTOListaPrecioExportar;
//import jakarta.faces.view.ViewScoped;
//import jakarta.inject.Named;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.omnifaces.util.Messages;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//import utils.BeansUtils;
//
///**
// *
// * @author marti
// */
//@Named("uiActualizarLP")
//@ViewScoped
//
//public class UIActualizarLP implements Serializable {
//
//    private Date fechaDesde;
//    private ControladorLP controladorLP = new ControladorLP();
//    private StreamedContent fileD;
//    private List<DTODetalleListaPrecioExportar> detallesExportarData = new ArrayList<>();
//    private int codListaPrecioFiltro;
//
//    public UIActualizarLP() {
//    }
//
//    public Date getFechaDesde() {
//        return fechaDesde;
//    }
//
//    public void setFechaDesde(Date fechaDesde) {
//        this.fechaDesde = fechaDesde;
//    }
//
//    public List<UIActualizarLPGrilla> obtenerListaPrecio() {
//
//        List<UIActualizarLPGrilla> listaPreciogrilla = new ArrayList<>();
//        List<DTOListaPrecioExportar> dtoListasPreciosExportar = controladorLP.obtenerListaPrecio(fechaDesde);
//        for (DTOListaPrecioExportar dtoListaPrecioExportar : dtoListasPreciosExportar) {
//            UIActualizarLPGrilla uiActualizarLPGrilla = new UIActualizarLPGrilla();
//            uiActualizarLPGrilla.setCodListaPrecio(dtoListaPrecioExportar.getCodListaPrecio());
//            uiActualizarLPGrilla.setFechaHoraDesdeListaPrecio(dtoListaPrecioExportar.getFechaHoraDesdeListaPrecio());
//            uiActualizarLPGrilla.setFechaHoraHastaListaPrecio(dtoListaPrecioExportar.getFechaHoraHastaListaPrecio());
//            listaPreciogrilla.add(uiActualizarLPGrilla);
//        }
//
//        return listaPreciogrilla;
//    }
//
//    public void filtrar() {
//
//    }
//
//    public String irAImportar() {
//        BeansUtils.guardarUrlAnterior();
//        return "importarLP.xhtml?faces-redirect=true"; // Usa '?faces-redirect=true' para hacer una redirección
//    }
//
//    public StreamedContent getFileD() {
//        populateDetallesExportarData(); // Populate the detallesListaPrecio before creating the Excel file
//
//        try {
//            Workbook workbook = new XSSFWorkbook();
//            Sheet sheet = workbook.createSheet("Hoja 1");
//
//            // Create header row
//            Row headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("Codigo");
//            headerRow.createCell(1).setCellValue("Nombre");
//            headerRow.createCell(2).setCellValue("Precio");
//
//            // Fill data rows
//            int rowIndex = 1;
//            for (DTODetalleListaPrecioExportar dtoDLPE : detallesExportarData) {
//                Row row = sheet.createRow(rowIndex++);
//                row.createCell(0).setCellValue(dtoDLPE.getCodTipoTramite());
//                row.createCell(1).setCellValue(dtoDLPE.getNombreTipoTramite());
//                row.createCell(2).setCellValue(dtoDLPE.getPrecioTipoTramite());
//            }
//
//            // Create a temporary file and write to it
//            File tempFile = File.createTempFile("listaprecio_", ".xlsx");
//            try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
//                workbook.write(outputStream);
//            }
//            workbook.close();
//
//            // Prepare the StreamedContent for download
//            InputStream stream = new FileInputStream(tempFile);
//            fileD = DefaultStreamedContent.builder()
//                    .name("Lista de Precios.xlsx")
//                    .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//                    .stream(() -> stream)
//                    .build();
//
//        } catch (IOException ex) {
//            Logger.getLogger(UIActualizarLP.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create("Error generating the file: " + ex.getMessage()).error().add();
//        }
//        return fileD;
//    }
//
//    private void populateDetallesExportarData() {
//       detallesExportarData = controladorLP.buscarDetallesExportar(codListaPrecioFiltro);
//    }
//
//}


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
        // Convertir Date a Timestamp antes de usarlo
        Timestamp fechaDesdeTS = (fechaDesde != null) ? new Timestamp(fechaDesde.getTime()) : null;

        // Obtener la lista de precios filtrada por la fecha seleccionada
        List<DTOListaPrecioExportar> dtoListasPreciosExportar = controladorLP.obtenerListaPrecio(fechaDesdeTS);

        // Crear una nueva lista para la grilla de UI
        listaPreciogrillaFiltrada = new ArrayList<>();
        for (DTOListaPrecioExportar dtoListaPrecioExportar : dtoListasPreciosExportar) {
            UIActualizarLPGrilla uiActualizarLPGrilla = new UIActualizarLPGrilla();
            uiActualizarLPGrilla.setCodListaPrecio(dtoListaPrecioExportar.getCodListaPrecio());
            uiActualizarLPGrilla.setFechaHoraDesdeListaPrecio(dtoListaPrecioExportar.getFechaHoraDesdeListaPrecio());
            uiActualizarLPGrilla.setFechaHoraHastaListaPrecio(dtoListaPrecioExportar.getFechaHoraHastaListaPrecio());
            listaPreciogrillaFiltrada.add(uiActualizarLPGrilla);
        }
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
