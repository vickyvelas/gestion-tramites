//package ListaPrecio.beans;
//
//import ListaPrecio.ControladorLP;
//import ListaPrecio.dtos.DTODetalleListaPrecioExportar;
//import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
//import ListaPrecio.dtos.DTOListaPrecioExportar;
//import ListaPrecio.dtos.DTOListaPrecioImportar;
//
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.model.file.UploadedFile;
//
//import jakarta.enterprise.inject.Model;
//import jakarta.faces.application.FacesMessage;
//import jakarta.faces.context.FacesContext;
//import jakarta.faces.view.ViewScoped;
//import jakarta.inject.Named;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.omnifaces.util.Messages;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//
//@Named("uiExcelFile")
//@ViewScoped
//@Model
//public class ExcelFileUI implements Serializable {
//
//    private UploadedFile file;
//
//    private StreamedContent fileD;
//
//    private int codListaPrecio;
//
//    private ControladorLP controladorLP = new ControladorLP();
//
//    public ExcelFileUI() {
//
//        codListaPrecio = 2;
//
//    }
//
//    public StreamedContent getFileD() {
//        DTOListaPrecioExportar dtoListaPrecioExportar = controladorLP.exportarListaPrecio(codListaPrecio);
//        List<DTODetalleListaPrecioExportar> detallesListaPrecio = dtoListaPrecioExportar.getDetalleListaPrecioExportar();
//
//        try {
//            Workbook libro = new XSSFWorkbook();
//
//            final String nombreArchivo = "./tmp.xlsx";
//            Sheet hoja = libro.createSheet("Hoja 1");
//
//            int nFila = 0;
//            int nColumna = 0;
//            Row fila;
//            Cell celda;
//            fila = hoja.createRow(nFila);
//            celda = fila.createCell(nColumna);
//            celda.setCellValue("Cod");
//            nColumna++;
//            celda = fila.createCell(nColumna);
//            celda.setCellValue("nombre");
//            nColumna++;
//            celda = fila.createCell(nColumna);
//            celda.setCellValue("precio");
//
//            int j = 0;
//            for (DTODetalleListaPrecioExportar detalleLPE : detallesListaPrecio) {
//                nColumna = 0;
//                nFila++;
//                fila = hoja.createRow(nFila);
//                celda = fila.createCell(nColumna);
//                celda.setCellValue(detalleLPE.getCodTipoTramite());
//                nColumna++;
//                celda = fila.createCell(nColumna);
//                celda.setCellValue(detalleLPE.getNombreTipoTramite());
//                nColumna++;
//                celda = fila.createCell(nColumna);
//                celda.setCellValue(detalleLPE.getPrecioTipoTramite());
//
//            }
//
//            FileOutputStream outputStream;
//
//            outputStream = new FileOutputStream(nombreArchivo);
//            libro.write(outputStream);
//            libro.close();
//            InputStream ie = new FileInputStream(nombreArchivo);
//            fileD = DefaultStreamedContent.builder()
//                    .name("ListaPrecio.xlsx")
//                    .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
//                    .stream(() -> ie)
//                    .build();
//        } catch (IOException ex) {
//            Logger.getLogger(ExcelFileUI.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create(ex.getMessage()).error().add();
//        }
//        return fileD;
//    }
//
//    public void setFileD(StreamedContent fileD) {
//        this.fileD = fileD;
//    }
//
//    public UploadedFile getFile() {
//        return file;
//    }
//
//    public void setFile(UploadedFile file) {
//        this.file = file;
//    }
//
//    public void handleFileUpload(FileUploadEvent event) {
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded. Size (KB): " + event.getFile().getSize() / 1024f);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//        //creo dto que lleno con los excel
//        DTOListaPrecioImportar listaPrecioImportarDTO = new DTOListaPrecioImportar();
//
//        try {
//
//            InputStream inp = event.getFile().getInputStream();
//
//            Workbook wb = WorkbookFactory.create(inp);
//            Sheet sheet = wb.getSheetAt(0);//obtiene la primer hoja
//            int iRow = 1; //antes era 0 
//            Row row = sheet.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
//            while (row != null) {
//                //crea 1 detalle
//                DTODetalleListaPrecioImportar detalle = new DTODetalleListaPrecioImportar();
//                Cell cellCodTramite = row.getCell(0);
//                Cell cellNombreTT = row.getCell(1);
//                Cell cellPrecio = row.getCell(3);
//
//                if (cellCodTramite != null && cellCodTramite.getCellType() == CellType.NUMERIC && cellCodTramite.getNumericCellValue() > 0) {
//                    if (cellCodTramite.getNumericCellValue() > 0) {
//                        detalle.setCodTipoTramite((int) cellCodTramite.getNumericCellValue());
//                        System.out.println(detalle.getCodTipotramite());
//                    } else {
//                        break;
//                    }
//                }
//
//                if (cellNombreTT != null && cellNombreTT.getCellType() == CellType.STRING) {
//                    String value = cellNombreTT.getStringCellValue();
//                    Messages.create(iRow + "String").detail(value).add();
//                    System.out.println(detalle
//                }
//
//                if (cellPrecio != null && (cellPrecio.getCellType() == CellType.NUMERIC || cellPrecio.getCellType() == CellType.FORMULA)) {
//                    detalle.setPrecioTipoTramite((float) cellPrecio.getNumericCellValue());
//                    System.out.println(detalle.getPrecioTipoTramite());
//                }
//
//                listaPrecioImportarDTO.addDetalleListaPrecioImportar(detalle);
//                iRow++;
//                row = sheet.getRow(iRow);
//            }
//            controladorLP.importarListaPrecio(listaPrecioImportarDTO);
//
//        } catch (Exception ex) {
//            Logger.getLogger(ExcelFileUI.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create(ex.getMessage()).error().add();
//        }
//    }
//}








//    public void handleFileUpload(FileUploadEvent event) {
//        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded. Size (KB): " + event.getFile().getSize() / 1024f);
//        FacesContext.getCurrentInstance().addMessage(null, message);
//
//        try {
//
//            InputStream inp = event.getFile().getInputStream();
//
//            Workbook wb = WorkbookFactory.create(inp);
//            Sheet sheet = wb.getSheetAt(0);
//            int iRow = 0;
//            Row row = sheet.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
//            while (row != null) {
//                Cell cell = row.getCell(0);
//                if (cell != null && cell.getCellType() == CellType.STRING) {
//                    String value = cell.getStringCellValue();
//                    Messages.create(iRow + "String").detail(value).add();
//                    System.out.println(iRow + "Valor de la celda es " + value);
//                }
//                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
//                    double value = cell.getNumericCellValue();
//                    //Messages.create(iRow + "Numero").detail(Double(value).toString()).add();
//                }
//
//                iRow++;
//                row = sheet.getRow(iRow);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(ExcelFileUI.class.getName()).log(Level.SEVERE, null, ex);
//            Messages.create(ex.getMessage()).error().add();
//        }
//    }
//}
