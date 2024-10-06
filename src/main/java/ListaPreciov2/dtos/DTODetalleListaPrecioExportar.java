package ListaPreciov2.dtos;

public class DTODetalleListaPrecioExportar {
    private int codTipoTramite;
    private String nombreTipoTramite;
    private float precioTipoTramite;

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

    public float getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(float precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }
}
