package entidades;

import java.sql.Blob;
import java.sql.Timestamp;

public class TramiteDocumentacion extends Entidad {
    private int codTramiteDocumentacion;
    private Blob archivo;
    private Timestamp fechaHoraEntrega;
    private Documentacion documentacion;

    //Constructor
    public TramiteDocumentacion() {
    }
    
    // Getters y Setters
    public int getCodTramiteDocumentacion() {
        return codTramiteDocumentacion;
    }

    public void setCodTramiteDocumentacion(int codTramiteDocumentacion) {
        this.codTramiteDocumentacion = codTramiteDocumentacion;
    }

    public Blob getArchivo() {
        return archivo;
    }

    public void setArchivo(Blob archivo) {
        this.archivo = archivo;
    }

    public Timestamp getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Timestamp fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public Documentacion getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(Documentacion documentacion) {
        this.documentacion = documentacion;
    }
}
