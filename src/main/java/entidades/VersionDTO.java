package entidades;


import java.sql.Timestamp;

public class VersionDTO {
    private int codVersion;
    private Timestamp fechaHoraDesdeVersion;
    private Timestamp fechaHoraHastaVersion;
    private Timestamp fechaHoraBajaVersion;
    private int tipoTramiteId; // Se usa el ID del tipo de trámite en lugar del objeto completo

    // Constructor
    public VersionDTO() {
    }

    // Getters y Setters
    public int getCodVersion() {
        return codVersion;
    }

    public void setCodVersion(int codVersion) {
        this.codVersion = codVersion;
    }

    public Timestamp getFechaHoraDesdeVersion() {
        return fechaHoraDesdeVersion;
    }

    public void setFechaHoraDesdeVersion(Timestamp fechaHoraDesdeVersion) {
        this.fechaHoraDesdeVersion = fechaHoraDesdeVersion;
    }

    public Timestamp getFechaHoraHastaVersion() {
        return fechaHoraHastaVersion;
    }

    public void setFechaHoraHastaVersion(Timestamp fechaHoraHastaVersion) {
        this.fechaHoraHastaVersion = fechaHoraHastaVersion;
    }

    public Timestamp getFechaHoraBajaVersion() {
        return fechaHoraBajaVersion;
    }

    public void setFechaHoraBajaVersion(Timestamp fechaHoraBajaVersion) {
        this.fechaHoraBajaVersion = fechaHoraBajaVersion;
    }

    public int getTipoTramiteId() {
        return tipoTramiteId;
    }

    public void setTipoTramiteId(int tipoTramiteId) {
        this.tipoTramiteId = tipoTramiteId;
    }
}
