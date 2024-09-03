
package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ListaPrecio extends Entidad{
    private int codListaPrecio;
    private Timestamp fechaHoraDesdeListaPrecio;
    private Timestamp fechaHoraHastaListaPrecio;
    private Timestamp fechaHoraBajaListaPrecio;
    private List<DetallePrecioTipoTramite> detallePrecioTipoTramiteList = new ArrayList<>();
    
    // Constructor
    public ListaPrecio(){
    }
    
    // Getters y Setters
    public int getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(int codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
    }

    public Timestamp getFechaHoraDesdeListaPrecio() {
        return fechaHoraDesdeListaPrecio;
    }

    public void setFechaHoraDesdeListaPrecio(Timestamp fechaHoraDesdeListaPrecio) {
        this.fechaHoraDesdeListaPrecio = fechaHoraDesdeListaPrecio;
    }

    public Timestamp getFechaHoraHastaListaPrecio() {
        return fechaHoraHastaListaPrecio;
    }

    public void setFechaHoraHastaListaPrecio(Timestamp fechaHoraHastaListaPrecio) {
        this.fechaHoraHastaListaPrecio = fechaHoraHastaListaPrecio;
    }

    public Timestamp getFechaHoraBajaListaPrecio() {
        return fechaHoraBajaListaPrecio;
    }

    public void setFechaHoraBajaListaPrecio(Timestamp fechaHoraBajaListaPrecio) {
        this.fechaHoraBajaListaPrecio = fechaHoraBajaListaPrecio;
    }

    public List<DetallePrecioTipoTramite> getDetallePrecioTipoTramiteList() {
        return detallePrecioTipoTramiteList;
    }

    public void setDetallePrecioTipoTramiteList(List<DetallePrecioTipoTramite> detallePrecioTipoTramiteList) {
        this.detallePrecioTipoTramiteList = detallePrecioTipoTramiteList;
    }
    
    public void addDetallePrecioTipoTramiteList(DetallePrecioTipoTramite detallePrecioTipoTramite) {
        detallePrecioTipoTramiteList.add(detallePrecioTipoTramite);
    }
}
