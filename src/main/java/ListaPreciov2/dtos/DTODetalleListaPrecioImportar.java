package ListaPreciov2.dtos;

public class DTODetalleListaPrecioImportar {
    private int codTipoTramite;
    private float precioTipoTramite;
    
    public int getCodTipotramite(){
        return codTipoTramite;
    }
    
    public void setCodTipoTramite (int codTipoTramite){
        this.codTipoTramite = codTipoTramite;
    }
    
    public float getPrecioTipoTramite(){
        return precioTipoTramite;
    }
    
    public void setPrecioTipoTramite (float precioTipoTramite){
        this.precioTipoTramite = precioTipoTramite;
    }
}
