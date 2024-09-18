
package ABMTipoTramite.beans;

import ABMTipoTramite.ControladorABMTipoTramite;
import ABMTipoTramite.dtos.DTOTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;

@Named("UIABMTipoTramiteLista")
@ViewScoped

public class UIABMTipoTramiteLista implements Serializable{
    
    private ControladorABMTipoTramite controladorABMTipoTramite = new ControladorABMTipoTramite();
    private int codigoFiltro=0;
    private String nombreFiltro="";

    public ControladorABMTipoTramite ControladorABMTipoTramite() {
        return controladorABMTipoTramite;
    }

    public void setControladorABMTipoTramite(ControladorABMTipoTramite controladorABMTipoTramite) {
        this.controladorABMTipoTramite = controladorABMTipoTramite;
    }

    public int getCodigoFiltro() {
        return codigoFiltro;
    }

    public void setCodigoFiltro(int codigoFiltro) {
        this.codigoFiltro = codigoFiltro;
    }

    public String getNombreFiltro() {
        return nombreFiltro;
    }

    public void setNombreFiltro(String descripcionFiltro) {
        this.nombreFiltro = descripcionFiltro;
    }
    public void filtrar()
    {

    }

    public List<TipoTramiteGrillaUI> buscarTipoTramites(){
        System.out.println(codigoFiltro);
        System.out.println(nombreFiltro);
        List<TipoTramiteGrillaUI> tipotramitesGrilla = new ArrayList<>();
        List<DTOTipoTramite> DTOTipoTramites = controladorABMTipoTramite.buscarTipoTramites(codigoFiltro,nombreFiltro);
        for (DTOTipoTramite DTOTipoTramite : DTOTipoTramites) {
            TipoTramiteGrillaUI tipoTramiteGrillaUI = new TipoTramiteGrillaUI();
            tipoTramiteGrillaUI.setCodTipoTramite(DTOTipoTramite.getCodTipoTramite());
            tipoTramiteGrillaUI.setNombreTipoTramite(DTOTipoTramite.getNombreTipoTramite());
            tipoTramiteGrillaUI.setFechaHoraBajaTipoTramite(DTOTipoTramite.getFechaHoraBajaTipoTramite());
            tipoTramiteGrillaUI.add(tipoTramiteGrillaUI);
        }
        return tipotramitesGrilla;
    }

    public String irAgregarTipoTramite() {
        BeansUtils.guardarUrlAnterior();
        return "abmTipoTramite?faces-redirect=true&codigo=0"; // Usa '?faces-redirect=true' para hacer una redirección
    }

    
    public String irModificarTipoTramite(int codigo) {
        BeansUtils.guardarUrlAnterior();
        return "abmTipoTramite?faces-redirect=true&codigo=" + codigo; // Usa '?faces-redirect=true' para hacer una redirección
    }

    public void darDeBajaTipoTramite(int codigo){
        try {
            controladorABMTipoTramite.darDeBajaTipoTramite(codigo);
            Messages.create("Anulado").detail("Anulado").add();
                    
        } catch (ABMTipoTramiteException e) {
            Messages.create("Error!").error().detail(e.getMessage()).add();
        }
    }
    
    
}
