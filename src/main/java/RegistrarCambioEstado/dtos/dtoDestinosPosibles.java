package DTO.D;

public class dtoDestinosPosibles {

    private String nombreEstadoTramiteDestino;
    private int codEstadoTramite;

     //METODOS GET
    public String getNombreEstadoTramiteDestino() {
        return nombreEstadoTramiteDestino;
    }
    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    //METODOS SET
    public void setNombreEstadoTramiteDestino(String nombreEstadoTramiteDestino) {
        this.nombreEstadoTramiteDestino = nombreEstadoTramiteDestino;
    }
    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    

    
    
}
