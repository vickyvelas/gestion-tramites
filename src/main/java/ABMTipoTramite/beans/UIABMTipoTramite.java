
package ABMTipoTramite.beans;

import ABMTipoTramite.ControladorABMTipoTramite;
import ABMTipoTramite.dtos.DTOModificarTipoTramite;
import ABMTipoTramite.dtos.DTOModificarTipoTramiteIn;
import ABMTipoTramite.dtos.DTONuevoTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import entidades.CategoriaTramite;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.sql.Timestamp;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

  @Named("UIABMTipoTramiteLista")
   @ViewScoped

public class UIABMTipoTramite implements Serializable{
    private ControladorABMTipoTramite controladorABMTipoTramite = new ControladorABMTipoTramite();
    private boolean insert;
    private int codTipoTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String descripcionTipoTramiteWeb;
    private int maxDiaEntrega;
    private Timestamp fechaHoraBajaTipoTramite;
    private CategoriaTramite categoriaTramite;
    
    @Named("UIABMTipoTramiteLista")
    @ViewScoped

    public ControladorABMTipoTramite getControladorABMTipoTramite() {
        return controladorABMTipoTramite;
    }

    public void setControladorABMTipoTramite(ControladorABMTipoTramite controladorABMTipoTramite) {
        this.controladorABMTipoTramite = controladorABMTipoTramite;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
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

    public int getMaxDiaEntrega() {
        return maxDiaEntrega;
    }

    public void setMaxDiaEntrega(int maxDiaEntrega) {
        this.maxDiaEntrega = maxDiaEntrega;
    }

    public Timestamp getFechaHoraBajaTipoTramite() {
        return fechaHoraBajaTipoTramite;
    }

    public void setFechaHoraBajaTipoTramite(Timestamp fechaHoraBajaTipoTramite) {
        this.fechaHoraBajaTipoTramite = fechaHoraBajaTipoTramite;
    }

    public CategoriaTramite getCategoriaTramite() {
        return categoriaTramite;
    }

    public void setCategoriaTramite(CategoriaTramite categoriaTramite) {
        this.categoriaTramite = categoriaTramite;
    }
    
    public UIABMTipoTramite() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        insert=true;
        if(codigo > 0)
        {
            insert=false;
            DTOModificarTipoTramite DTOModificarTipoTramite = ControladorABMTipoTramite.buscarABMTipoTramiteAModificar(codigo);
            setNombreTipoTramite(DTOModificarTipoTramite.getNombreTipoTramite());
            setCodTipoTramite(DTOModificarTipoTramite.getCodTipoTramite());
            setDescripcionTipoTramite(DTOModificarTipoTramite.getDescripcionTipoTramiteWeb());
        }
        
    }
    public String agregarTipoTramite(){
        try {

        
            if(!insert)
            {

                DTOModificarTipoTramiteIn DTOModificarTipoTramiteIn = new DTOModificarTipoTramiteIn();
                DTOModificarTipoTramiteIn.setNombreTipoTramite(getNombreTipoTramite());
                DTOModificarTipoTramiteIn.setCodTipoTramite(getCodTipoTramite());
                controladorABMTipoTramite.modificarTipoTramite(DTOModificarTipoTramiteIn);
                return BeansUtils.redirectToPreviousPage();
            }
            else
            {
                DTONuevoTipoTramite DTONuevoTipoTramite = new DTONuevoTipoTramite();
                DTONuevoTipoTramite.setNombreTipoTramite(getNombreTipoTramite());
                DTONuevoTipoTramite.setCodTipoTramite(getCodTipoTramite());
                controladorABMTipoTramite.agregarTipoTramite(DTONuevoTipoTramite);

            }
            return BeansUtils.redirectToPreviousPage();
        }
        
        catch (ABMTipoTramiteException e) {
                Messages.create(e.getMessage()).fatal().add();
                return "";
         }
    }

}
