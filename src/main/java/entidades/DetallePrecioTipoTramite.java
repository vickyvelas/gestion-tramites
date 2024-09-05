package entidades;

public class DetallePrecioTipoTramite extends Entidad{
    private float precioTipoTramite;
    private TipoTramite tipoTramite;
    
    public DetallePrecioTipoTramite() {}

    public float getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(float precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }
    
    

   
    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

   
    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }
    
}
