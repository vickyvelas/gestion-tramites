package entidades;

import java.sql.Timestamp;
import java.util.Objects;



public class Consultor extends Entidad {
    private int legajoConsultor;
    private String nombreApellidoConsultor;
    private int consultorNroMaxTramite;
    private Timestamp fechaHoraBajaConsultor;
    
    // Constructor
    public Consultor() {
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Consultor consultor = (Consultor) obj;
        return legajoConsultor == consultor.legajoConsultor; // Compara usando ==
    }

    @Override
    public int hashCode() {
        return Objects.hash(legajoConsultor); // Asegúrate de usar un atributo único
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
}    

