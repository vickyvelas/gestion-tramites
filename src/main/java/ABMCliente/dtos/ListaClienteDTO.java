package ABMCliente.dtos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ListaClienteDTO {
    private int cuitCliente;
    private String nombreApellidoCliente;
    private Timestamp fechaHoraBajaCliente;


    //Getter and Setter (METODOS)
    public int getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(int cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public Timestamp getFechaHoraBajaCliente() {
        return fechaHoraBajaCliente;
    }

    public void setFechaHoraBajaCliente(Timestamp fechaHoraBajaCliente) {
        this.fechaHoraBajaCliente = fechaHoraBajaCliente;
    }


 }    
 
