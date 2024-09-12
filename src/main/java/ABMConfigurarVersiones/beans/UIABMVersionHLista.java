
// Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 //Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

/*
package ABMConfigurarVersiones.beans;

import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.exceptions.ControladorABMVersion;
import entidades.TipoTramite;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;
/*
/**
 *
 * @author julie
 */
/*
public class UIABMVersionHLista implements Serializable{
     private ControladorABMVersion controladorABMVersion = new ControladorABMVersion();
    private int codigoFiltro=0;
    private String nombreFiltro="";

    public ControladorABMVersion getControladorABMVersion() {
        return controladorABMVersion;
    }

    public void setControladorABMVersion(ControladorABMVersion controladorABMVersion) {
        this.controladorABMVersion = controladorABMVersion;
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

    public void setNombreFiltro(String nombreFiltro) {
        this.nombreFiltro = nombreFiltro;
    }

    
    public void filtrar()
    {

    }

    public List<DTOVersionHistorial> buscarVersiones(){
        System.out.println(codigoFiltro);
        System.out.println(nombreFiltro);
        List<DTOVersionHistorial> versionGrilla = new ArrayList<>();
        List<DTOVersionHistorial> LversionHistorial = controladorABMVersion.buscarVersionHistorial(codigoFiltro,nombreFiltro);
        for (DTOVersionHistorial versionHistorial : LversionHistorial) {
            DTOVersionHistorial versionGrillaUI = new DTOVersionHistorial();
            versionGrillaUI.setCodVersion(versionHistorial.getCodVersion());
            versionGrillaUI.setFechaBajaVersion(versionHistorial.getFechaBajaVersion());
            versionGrillaUI.setFechaDesdeVersion(versionHistorial.getFechaDesdeVersion());
            versionGrillaUI.setFechaHastaVersion(versionHistorial.getFechaHastaVersion());
            versionGrillaUI.setCodTipoTramite(versionHistorial.getCodTipoTramite());
            versionGrillaUI.setNombreTipoTramite(versionHistorial.getNombreTipoTramite());
            versionGrilla.add(versionGrillaUI);
        }
        return versionGrilla;
    }
*/
    //public String irAgregarVersion() {
    //    BeansUtils.guardarUrlAnterior();
    //    return "abmEstado?faces-redirect=true&codigo=0"; // Usa '?faces-redirect=true' para hacer una redirección
    //}

    
    //public String irModificarEstado(int codigo) {
    //    BeansUtils.guardarUrlAnterior();
    //    return "abmEstado?faces-redirect=true&codigo=" + codigo; // Usa '?faces-redirect=true' para hacer una redirección
    //}

    //public void darDeBajaEstado(int codigo){
    //    try {
    //        controladorABMEstado.darDeBajaEstado(codigo);
    //        Messages.create("Anulado").detail("Anulado").add();
                    
    //    } catch (EstadoException e) {
    //        Messages.create("Error!").error().detail(e.getMessage()).add();
    //    }
    //}
    
    

