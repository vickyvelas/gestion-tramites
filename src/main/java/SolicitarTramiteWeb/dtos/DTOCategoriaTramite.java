package SolicitarTramiteWeb.dtos;

import java.io.Serializable;

public class DTOCategoriaTramite implements Serializable {
    
    private int codCategoriaTramite;
    private int nombreCategoriaTramite;

    public int getCodCategoriaTramite() {
        return codCategoriaTramite;
    }

    public void setCodCategoriaTramite(int codCategoriaTramite) {
        this.codCategoriaTramite = codCategoriaTramite;
    }

    public int getNombreCategoriaTramite() {
        return nombreCategoriaTramite;
    }

    public void setNombreCategoriaTramite(int nombreCategoriaTramite) {
        this.nombreCategoriaTramite = nombreCategoriaTramite;
    }
           
}