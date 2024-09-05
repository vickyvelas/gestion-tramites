package entidades;

import java.sql.Timestamp;

public class ListaPrecio extends Entidad {

    private int codListaPrecio;
    private Timestamp fechaHoraDesdeListaPrecio;
    private Timestamp fechaHoraHastaListaPrecio;
    private Timestamp fechaHoraBajaListaPrecio;

    public ListaPrecio() {
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
    
    public Timestamp getFechaHoraHastaListaPrecio(){
        return fechaHoraHastaListaPrecio;
    }
    
    public void setFechaHoraHastaListaPrecio(Timestamp fechaHoraBajaListaPrecio){
        this.fechaHoraBajaListaPrecio = fechaHoraBajaListaPrecio;
    }
    
    public Timestamp getFechaHoraBajaListaPrecio() {
        return fechaHoraBajaListaPrecio;
    }

    public void setFechaHoraBajaListaPrecio(Timestamp fechaHoraBajaListaPrecio) {
        this.fechaHoraBajaListaPrecio = fechaHoraBajaListaPrecio;
    }
}
