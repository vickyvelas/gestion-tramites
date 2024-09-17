
package ABMConfigurarVersiones.beans;

import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.dtos.DTOVersionesDeTipo;
import ABMConfigurarVersiones.dtos.DTOVersionyEstados;
import ABMConfigurarVersiones.exceptions.ControladorABMVersion;
import ABMConfigurarVersiones.exceptions.VersionException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import utils.BeansUtils;

@Named("uiabmVersionTTLista")
@ViewScoped
public class VersionGrilla implements Serializable {
    private ControladorABMVersion controlador = new ControladorABMVersion();
    private int codigoFiltro=0;
    private String nombreFiltro="";
    
    public ControladorABMVersion getControlador() {
        return controlador;
    }

    public void setControlador(ControladorABMVersion controlador) {
        this.controlador = controlador;
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
    public List<DTOVersionesDeTipo> listarTipoTramiteVersion(){

        List<DTOVersionesDeTipo> lisResultado = new ArrayList();
        List<DTOVersionesDeTipo> listaDto = new ArrayList();
        listaDto = controlador.listarTablaVersionesDeTipos();
        for (DTOVersionesDeTipo x: listaDto){
            DTOVersionesDeTipo y = new DTOVersionesDeTipo();
            y.setCodTipoTramite(x.getCodTipoTramite());
            y.setCodVersion(x.getCodVersion());
            y.setFechaHoraBajaTipoTramite(x.getFechaHoraBajaTipoTramite());
            y.setFechaHoraDesdeVersion(x.getFechaHoraDesdeVersion());
            y.setNombreTipoTramite(x.getNombreTipoTramite());
            lisResultado.add(y);
        }
        return lisResultado;
    }
    public List<DTOVersionesDeTipo> buscarTipoTramiteVersion(){
        System.out.println(codigoFiltro);
        System.out.println(nombreFiltro);
        List<DTOVersionesDeTipo> lisResultado = new ArrayList();
        List<DTOVersionesDeTipo> listaDto = new ArrayList();
        listaDto = controlador.filtroTablaVersiones(codigoFiltro, nombreFiltro);
        for (DTOVersionesDeTipo x: listaDto){
            DTOVersionesDeTipo y = new DTOVersionesDeTipo();
            y.setCodTipoTramite(x.getCodTipoTramite());
            y.setCodVersion(x.getCodVersion());
            y.setFechaHoraBajaTipoTramite(x.getFechaHoraBajaTipoTramite());
            y.setFechaHoraDesdeVersion(x.getFechaHoraDesdeVersion());
            y.setNombreTipoTramite(x.getNombreTipoTramite());
            lisResultado.add(y);
        }
        return lisResultado;
    }
    public DTOVersionyEstados irEditarVersion(int codigo) throws VersionException {
        BeansUtils.guardarUrlAnterior();
        DTOVersionyEstados dtoeditar = controlador.buscarVersionEditar(codigo);
        return dtoeditar; 
    }
    
    public  String irHistorialVersion(int codigo) {
        List<DTOVersionHistorial> listResultado = controlador.historialVersiones(codigo);
        BeansUtils.guardarUrlAnterior();
        return "/ABMConfigurarVersiones/HistorialVersiones.xhtml?faces-redirect=true"+ listResultado; // Usa '?faces-redirect=true' para hacer una redirección
    }
    public DTOVersionOutput irVerVersion(int codigo, int codigov) throws VersionException {
        DTOVersionOutput verVersion = controlador.verVersion(codigo, codigov);
        BeansUtils.guardarUrlAnterior();
        return verVersion;
    }
}
   