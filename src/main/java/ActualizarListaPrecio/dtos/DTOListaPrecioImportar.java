package ActualizarListaPrecio.dtos;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class DTOListaPrecioImportar {
    private int codListaPrecio;
    private Timestamp fechaHoraDesdeListaPrecio;
    private Timestamp fechaHoraHastaListaPrecio;
    
    public int getCodListaPrecio(){
        return codListaPrecio;
    }
    
    public void setCodListaPrecio (int codListaPrecio){
        this.codListaPrecio = codListaPrecio;
    }
    
    public Timestamp getFechaHoraDesdeListaPrecio(){
        return fechaHoraDesdeListaPrecio;
    }
    
    public void setFechaHoraDesdeListaPrecio (Timestamp fechaHoraDesdeListaPrecio){
        this.fechaHoraDesdeListaPrecio = fechaHoraDesdeListaPrecio;
    }
    
    public Timestamp getFechaHoraHastaListaPrecio(){
        return fechaHoraHastaListaPrecio;
    }
    
    public void setFechaHoraHastaListaPrecio(Timestamp fechaHoraHastaListaPrecio){
        this.fechaHoraHastaListaPrecio = fechaHoraHastaListaPrecio;
    }

}
