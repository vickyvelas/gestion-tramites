package entidades;

import java.sql.Timestamp;

public class Cliente extends Entidad{
   
   private int cuitCliente;
    private String direccionCliente;
    private Timestamp fechaHoraAltaCliente;
    private Timestamp fechaHoraBajaCliente;
    private String mailCliente;
    private String nombreApellidoCliente;
    private double telefonoCliente;
    
    
    public Cliente(){}

    public int getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(int cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public Timestamp getFechaHoraAltaCliente() {
        return fechaHoraAltaCliente;
    }

    public void setFechaHoraAltaCliente(Timestamp fechaHoraAltaCliente) {
        this.fechaHoraAltaCliente = fechaHoraAltaCliente;
    }

    public Timestamp getFechaHoraBajaCliente() {
        return fechaHoraBajaCliente;
    }

    public void setFechaHoraBajaCliente(Timestamp fechaHoraBajaCliente) {
        this.fechaHoraBajaCliente = fechaHoraBajaCliente;
    }

    public String getMailCliente() {
        return mailCliente;
    }

    public void setMailCliente(String mailCliente) {
        this.mailCliente = mailCliente;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public double getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(double telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

  
    
    
}
