package entidades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ListaPrecio")
public class ListaPrecio extends Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codListaPrecio;
    private Timestamp fechaHoraBajaListaPrecio;
    
    @Column(name = "fechaHoraDesdeListaPrecio")
    private Timestamp fechaHoraDesdeListaPrecio;
    
    @Column(name = "fechaHoraHastaListaPrecio")
    private Timestamp fechaHoraHastaListaPrecio;
    
    @OneToMany(mappedBy = "listaPrecio", cascade = CascadeType.ALL)
    private List<DetallePrecioTipoTramite> detallePrecioTipoTramiteList = new ArrayList<>();

    // Constructor
    public ListaPrecio() {
    }

    public int getCodListaPrecio() {
        return codListaPrecio;
    }

    public void setCodListaPrecio(int codListaPrecio) {
        this.codListaPrecio = codListaPrecio;
    }

    public Timestamp getFechaHoraBajaListaPrecio() {
        return fechaHoraBajaListaPrecio;
    }

    public void setFechaHoraBajaListaPrecio(Timestamp fechaHoraBajaListaPrecio) {
        this.fechaHoraBajaListaPrecio = fechaHoraBajaListaPrecio;
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
