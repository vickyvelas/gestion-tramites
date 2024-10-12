package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Tramite extends Entidad{
    private int nroTramite;
    private float precioTramite;
    private Timestamp fechaHoraCargaTramite;
    private Timestamp fechaHoraVencimientoDocumentacion;
    private Timestamp fechaHoraFinEntregaDocumentacion;
    private Timestamp fechaHoraFinTramite;
    private Consultor consultor;
    private Cliente cliente;
    private TipoTramite tipoTramite;
    private Version version;
    private EstadoTramite estadoTramite;
    private List<TramiteEstado> tramiteEstadoList = new ArrayList<>();
    private List<TramiteDocumentacion> tramiteDocumentacionList = new ArrayList<>();
    
    // Constructor
    public Tramite() {
    }
    
    // Getters y Setters
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }

    public float getPrecioTramite() {
        return precioTramite;
    }

    public void setPrecioTramite(float precioTramite) {
        this.precioTramite = precioTramite;
    }

    public Timestamp getFechaHoraCargaTramite() {
        return fechaHoraCargaTramite;
    }

    public void setFechaHoraCargaTramite(Timestamp fechaHoraCargaTramite) {
        this.fechaHoraCargaTramite = fechaHoraCargaTramite;
    }

    public Timestamp getFechaHoraVencimientoDocumentacion() {
        return fechaHoraVencimientoDocumentacion;
    }

    public void setFechaHoraVencimientoDocumentacion(Timestamp fechaHoraVencimientoDocumentacion) {
        this.fechaHoraVencimientoDocumentacion = fechaHoraVencimientoDocumentacion;
    }

    public Timestamp getFechaHoraFinEntregaDocumentacion() {
        return fechaHoraFinEntregaDocumentacion;
    }

    public void setFechaHoraFinEntregaDocumentacion(Timestamp fechaHoraFinEntregaDocumentacion) {
        this.fechaHoraFinEntregaDocumentacion = fechaHoraFinEntregaDocumentacion;
    }

    public Timestamp getFechaHoraFinTramite() {
        return fechaHoraFinTramite;
    }

    public void setFechaHoraFinTramite(Timestamp fechaHoraFinTramite) {
        this.fechaHoraFinTramite = fechaHoraFinTramite;
    }

    public Consultor getConsultor() {
        return consultor;
    }

    public void setConsultor(Consultor consultor) {
        this.consultor = consultor;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }  
    
    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
    
    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    } 
    
    public EstadoTramite getEstadoTramite() {
        return estadoTramite;
    }

    public void setEstadoTramite(EstadoTramite estadoTramite) {
        this.estadoTramite = estadoTramite;
    }
    
    public List<TramiteEstado> getTramiteEstadoList() {
        return tramiteEstadoList;
    }

    public void setTramiteEstadoList(List<TramiteEstado> tramiteEstadoList) {
        this.tramiteEstadoList = tramiteEstadoList;
    }

    public void addTramiteEstadoList(TramiteEstado tramiteEstado) {
        tramiteEstadoList.add(tramiteEstado);
    }
      
    public List<TramiteDocumentacion> getTramiteDocumentacionList() {
        return tramiteDocumentacionList;
    }

    public void setTramiteDocumentacionList(List<TramiteDocumentacion> tramiteDocumentacionList) {
        this.tramiteDocumentacionList = tramiteDocumentacionList;
    }

    public void addTramiteDocumentacionList(TramiteDocumentacion tramiteDocumentacion) {
        tramiteDocumentacionList.add(tramiteDocumentacion);
    }
}
