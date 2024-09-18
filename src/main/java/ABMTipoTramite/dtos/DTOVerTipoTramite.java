
package ABMTipoTramite.dtos;

import java.sql.Timestamp;


public class DTOVerTipoTramite {
    
    private int codTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private Timestamp maxDiaEntrega;
    private int codCategoriaTramite;
    private String nombreTipoTramite;
    private int nombreCategoriaTramite;

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    public String getDescripcionTipoTramite() {
        return descripcionTipoTramite;
    }

    public void setDescripcionTipoTramite(String descripcionTipoTramite) {
        this.descripcionTipoTramite = descripcionTipoTramite;
    }

    public String getDescripcionTipoTramiteWeb() {
        return descripcionTipoTramiteWeb;
    }

    public void setDescripcionTipoTramiteWeb(String descripcionTipoTramiteWeb) {
        this.descripcionTipoTramiteWeb = descripcionTipoTramiteWeb;
    }

    public Timestamp getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(Timestamp maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }

    public int getCodCategoriaTramite() {
        return codCategoriaTramite;
    }

    public void setCodCategoriaTramite(int codCategoriaTramite) {
        this.codCategoriaTramite = codCategoriaTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public int getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(int nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }
    
    
}
