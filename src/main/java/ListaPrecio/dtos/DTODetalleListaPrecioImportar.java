/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.dtos;

/**
 *
 * @author marti
 */
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
