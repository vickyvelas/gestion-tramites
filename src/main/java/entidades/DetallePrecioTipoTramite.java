package entidades;

public class DetallePrecioTipoTramite extends Entidad{
    private double precioTipoTramite;
    private TipoTramite tipoTramite;
    
    public DetallePrecioTipoTramite() {}

    public double getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(double precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }
    
    

   
    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

   
    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
    
}
