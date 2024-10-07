/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio.beans;

import java.sql.Timestamp;

/**
 *
 * @author marti
 */
public class UIActualizarLPGrilla {
    private int codListaPrecio;
    private Timestamp fechaHoraDesdeListaPrecio;
    private Timestamp fechaHoraHastaListaPrecio;

    public UIActualizarLPGrilla() {
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
