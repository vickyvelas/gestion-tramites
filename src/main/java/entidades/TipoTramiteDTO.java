package entidades;


import java.sql.Timestamp;

public class TipoTramiteDTO {
    private int codTipoTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private int maxDiaEntrega;
    private Timestamp fechaHoraBajaTipoTramite;
    private int categoriaTramiteId; // Se usa el ID de la categoría en lugar del objeto completo

    // Constructor
    public TipoTramiteDTO() {
    }

    // Getters y Setters
    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public String getDescripcionTipoTramite() {
        return descripcionTipoTramite;
    }

    public void setDescripcionTipoTramite(String descripcionTipoTramite) {
        this.descripcionTipoTramite = descripcionTipoTramite;
    }

    public String getDescripcionTipoTramiteWeb() {
        return descripcionTipoTramiteWeb;
    }

    public void setDescripcionTipoTramiteWeb(String descripcionTipoTramiteWeb) {
        this.descripcionTipoTramiteWeb = descripcionTipoTramiteWeb;
    }

    public int getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(int maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }

    public Timestamp getFechaHoraBajaTipoTramite() {
        return fechaHoraBajaTipoTramite;
    }

    public void setFechaHoraBajaTipoTramite(Timestamp fechaHoraBajaTipoTramite) {
        this.fechaHoraBajaTipoTramite = fechaHoraBajaTipoTramite;
    }

    public int getCategoriaTramiteId() {
        return categoriaTramiteId;
    }

    public void setCategoriaTramiteId(int categoriaTramiteId) {
        this.categoriaTramiteId = categoriaTramiteId;
    }
}
