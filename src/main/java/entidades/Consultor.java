
package entidades;

import java.sql.Timestamp;

public class Consultor extends Entidad {
    private int legajoConsultor;
    private String nombreApellidoConsultor;
    private int consultorNroMaxTramite;
    private Timestamp fechaHoraBajaConsultor;
    
    // Constructor
    public Consultor() {
    }
    
    // Getters y Setters
    public int getLegajoConsultor() {
        return legajoConsultor;
    }
    
    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor;
    }
    
    public String getNombreApellidoConsultor() {
        return nombreApellidoConsultor;
    }
    
    public void setNombreApellidoConsultor(String nombreApellidoConsultor) {
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }
    
    public int getConsultorNroMaxTramite() {
        return consultorNroMaxTramite;
    }
    
    public void setConsultorNroMaxTramite(int consultorNroMaxTramite) {
        this.consultorNroMaxTramite = consultorNroMaxTramite;
    }
    
    public Timestamp getFechaHoraBajaConsultor() {
        return fechaHoraBajaConsultor;
    }
    
    public void setFechaHoraBajaConsultor(Timestamp fechaHoraBajaConsultor) {
        this.fechaHoraBajaConsultor = fechaHoraBajaConsultor;
    }

    public int getId() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}    

