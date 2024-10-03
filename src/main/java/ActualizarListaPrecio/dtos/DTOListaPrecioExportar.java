package ActualizarListaPrecio.dtos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DTOListaPrecioExportar {

    private int codListaPrecio;
    private Timestamp fechaHoraDesdeListaPrecio;
    private Timestamp fechaHoraHastaListaPrecio;
    private List<DTODetalleListaPrecioExportar> detalleListaPrecioExportar = new ArrayList<>();

    public List<DTODetalleListaPrecioExportar> getDetalleListaPrecioExportar() {
        return detalleListaPrecioExportar;
    }

    public void setDetalleListaPrecioExportar(List<DTODetalleListaPrecioExportar> detalleListaPrecioExportar) {
        this.detalleListaPrecioExportar = detalleListaPrecioExportar;
    }

    public void addDetalleListaPrecioExportar(DTODetalleListaPrecioExportar detalleListaPrecioExportar) {
        this.detalleListaPrecioExportar.add(detalleListaPrecioExportar);
    }

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

}
