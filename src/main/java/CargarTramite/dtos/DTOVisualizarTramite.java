
package CargarTramite.dtos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class DTOVisualizarTramite {

    
    private int nroTramite;
    private Timestamp fechaHoraCargaTramite;
    private String nombreEstadoTramite;
    private String nombreCliente;
    private int cuitCliente;
    private String direccion;
    private String nombreApellidoConsultor;
    private String nombreDocumentacion;
    private Timestamp fechaHoraEntrega;
    private float precioTramite;
    private String nombreTipoTramite;
    private List<DTODocumentacion> dtoDocumentaciones = new ArrayList<>();
    
    
    
    
    
    //getters and setters
    public List<DTODocumentacion> getDtoDocumentaciones() {
        return dtoDocumentaciones;
    }

    public void setDtoDocumentaciones(List<DTODocumentacion> dtoDocumentaciones) {
        this.dtoDocumentaciones = dtoDocumentaciones;
    }
    
    public void addDTODocumentacion(DTODocumentacion dto){
        this.dtoDocumentaciones.add(dto);
    }
    
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }

    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }

    public String getNombreEstadoTramite() {
        return nombreEstadoTramite;
    }

    public void setNombreEstadoTramite(String nombreEstadoTramite) {
        this.nombreEstadoTramite = nombreEstadoTramite;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(int cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }

    public void setNombreApellidoConsultor(String nombreApellidoConsultor) {
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }

    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }

    public Timestamp getFechaHoraEntrega() {
        return fechaHoraEntrega;
    }

    public void setFechaHoraEntrega(Timestamp fechaHoraEntrega) {
        this.fechaHoraEntrega = fechaHoraEntrega;
    }

    public float getPrecioTramite() {
        return precioTramite;
    }

    public void setPrecioTramite(float precioTramite) {
        this.precioTramite = precioTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }
    
    //getters and setters

    
}
