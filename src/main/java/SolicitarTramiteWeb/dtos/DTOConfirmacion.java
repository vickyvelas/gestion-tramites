
package SolicitarTramiteWeb.dtos;



public class DTOConfirmacion  {
    
    private int cuitCliente;
    private String direccionCliente;
    private String nombreApellidoCliente;
    private String nombreTipoTramite;
    private int precioTipoTramite;

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

    public String getNombreApellidoCliente() {
        return nombreApellidoCliente;
    }

    public void setNombreApellidoCliente(String nombreApellidoCliente) {
        this.nombreApellidoCliente = nombreApellidoCliente;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public int getPrecioTipoTramite() {
        return precioTipoTramite;
    }

    public void setPrecioTipoTramite(int precioTipoTramite) {
        this.precioTipoTramite = precioTipoTramite;
    }
    
}
