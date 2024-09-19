package ActualizarListaPrecio.dtos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DTOListaPrecioImportar {
    private int codListaPrecio;
    private Timestamp fechaHoraDesdeListaPrecio;
    private Timestamp fechaHoraHastaListaPrecio;
    private List <DTODetalleListaPrecioImportar> detalleLP=new ArrayList<>();

    public List<DTODetalleListaPrecioImportar> getDetalleLP() {
        return detalleLP;
    }

    public void setDetalleLP(List<DTODetalleListaPrecioImportar> detalleLP) {
        this.detalleLP = detalleLP;
    }
    public void addDetalleLP(DTODetalleListaPrecioImportar detalleLP) {
        this.detalleLP.add(detalleLP);
    }
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
