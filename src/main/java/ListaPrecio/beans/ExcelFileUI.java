package ListaPrecio.beans;

import static com.sun.tools.javac.jvm.PoolConstant.LoadableConstant.Double;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omnifaces.util.Messages;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named("uiExcelFile")
@ViewScoped
@Model
public class ExcelFileUI implements Serializable{

    private UploadedFile file;

    private StreamedContent fileD;

 

    public ExcelFileUI() {
      
        

        
    }

    public StreamedContent getFileD() {
        try {
            Workbook libro = new XSSFWorkbook();
            final String nombreArchivo = "./tmp.xlsx";
            Sheet hoja = libro.createSheet("Hoja 1");
            for(int j=0;j<2;j++)
            {
                Row primeraFila = hoja.createRow(j);
                for (int i=0;i<10;i++)
                {
                    Cell primeraCelda = primeraFila.createCell(i);
                    primeraCelda.setCellValue("Yo voy en la celda"+i+" y fila"+j);
                }
            }
            FileOutputStream outputStream;
         
                outputStream = new FileOutputStream(nombreArchivo);
                libro.write(outputStream);
                libro.close();
                InputStream ie=new FileInputStream(nombreArchivo) ;   
                fileD = DefaultStreamedContent.builder()
                    .name("archivo.xlsx")
                    .contentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                    .stream(() ->ie)
                    .build();
        } catch (IOException ex) {
            Logger.getLogger(ExcelFileUI.class.getName()).log(Level.SEVERE, null, ex);
                   Messages.create(ex.getMessage()).error().add();
        }
        return fileD;
    }

    public void setFileD(StreamedContent fileD) {
        this.fileD = fileD;
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded. Size (KB): "+event.getFile().getSize()/1024f);
        FacesContext.getCurrentInstance().addMessage(null, message);
        
         try {
             
             
          InputStream inp=event.getFile().getInputStream();
                
       

 
         Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
        int iRow=0;
        Row row = sheet.getRow(iRow); //En qué fila empezar ya dependerá también de si tenemos, por ejemplo, el título de cada columna en la primera fila
      while(row!=null) 
      {
          Cell cell = row.getCell(0);  
          if (cell!=null && cell.getCellType()==CellType.STRING){
            String value = cell.getStringCellValue();
            Messages.create(iRow +"String").detail(value).add();
            System.out.println(iRow + "Valor de la celda es " + value);
          }
          if (cell!=null && cell.getCellType()==CellType.NUMERIC ){
            double value = cell.getNumericCellValue();
            Messages.create(iRow +"Numero").detail(Double(value).toString()).add();
          }

          iRow++;  
          row = sheet.getRow(iRow);
         } 
        } catch (Exception ex) {
            Logger.getLogger(ExcelFileUI.class.getName()).log(Level.SEVERE, null, ex);
               Messages.create(ex.getMessage()).error().add();
        }
    }
}
