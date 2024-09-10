
import java.sql.Timestamp;

public class TramiteDTO {
    private int nroTramite;
    private float precioTramite;
    private Timestamp fechaHoraCargaTramite;
    private Timestamp fechaHoraVencimientoDocumentacion;
    private Timestamp fechaHoraFinEntregaDocumentacion;
    private Timestamp fechaHoraFinTramite;
    private int consultorLegajo;
    private int clienteCuit;
    private int tipoTramiteCod;
    private int versionCod;
    private int estadoTramiteId;

    // Constructor
    public TramiteDTO() {
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

    public int getConsultorLegajo() {
        return consultorLegajo;
    }

    public void setConsultorLegajo(int consultorLegajo) {
        this.consultorLegajo = consultorLegajo;
    }

    public int getClienteCuit() {
        return clienteCuit;
    }

    public void setClienteCuit(int clienteCuit) {
        this.clienteCuit = clienteCuit;
    }

    public int getTipoTramiteCod() {
        return tipoTramiteCod;
    }

    public void setTipoTramiteCod(int tipoTramiteCod) {
        this.tipoTramiteCod = tipoTramiteCod;
    }

    public int getVersionCod() {
        return versionCod;
    }

    public void setVersionCod(int versionCod) {
        this.versionCod = versionCod;
    }

    public int getEstadoTramiteId() {
        return estadoTramiteId;
    }

    public void setEstadoTramiteId(int estadoTramiteId) {
        this.estadoTramiteId = estadoTramiteId;
    }
}
