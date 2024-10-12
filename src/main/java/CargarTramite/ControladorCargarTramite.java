package CargarTramite;

import CargarTramite.dtos.DTOClienteV;
import CargarTramite.dtos.DTOTipoTramiteV;
import CargarTramite.dtos.DTOTramiteV;
import CargarTramite.dtos.DTOVisualizarTramite;
import CargarTramite.exceptions.TramiteException;
import java.util.Date;

import java.util.List;

public class ControladorCargarTramite {
    
    
    private ExpertoCargarTramite expertoCargarTramite = new ExpertoCargarTramite();
    

    public List<DTOTramiteV> mostrarTramites(Date fechaDesdeFiltro, int cuitFiltro,
                int nroTramiteFiltro, String estadoFiltro, 
                int legajoConsultorFiltro, int codigoTTFiltro){
        
        return expertoCargarTramite.mostrarTramites(fechaDesdeFiltro, cuitFiltro,
                nroTramiteFiltro,estadoFiltro,legajoConsultorFiltro,codigoTTFiltro);
    }
    
    
    public String botonCargar(int cuit, int codigoTT) throws TramiteException, Exception{
        return  expertoCargarTramite.botonCargar(cuit, codigoTT);
    }

    
    
    public DTOVisualizarTramite visualizarTramite(int nroTramite) throws Exception{
        return expertoCargarTramite.visualizarTramite(nroTramite);
    }
//    public void agregarEstado(NuevoEstadoDTO nuevoEstadoDTO) throws TramiteException{
//        expertoCargarTramite.agregarEstado(nuevoEstadoDTO);
//    }
//
//    public void modificarEstado(ModificarEstadoDTOIn modificarEstadoDTOIn){
//        expertoCargarTramite.modificarEstado(modificarEstadoDTOIn);
//    }
//
//    public ModificarEstadoDTO buscarEstadoAModificar(int codigo){
//        return expertoCargarTramite.buscarEstadoAModificar(codigo);
//    }
//
    
    public DTOClienteV obtenerCliente(int cuit) throws TramiteException, Exception {
        return expertoCargarTramite.obtenerCliente(cuit);
    }
    
    public DTOTipoTramiteV obtenerTipoTramite(int codigoTT) throws TramiteException, Exception {
        return expertoCargarTramite.obtenerTipoTramite(codigoTT);
    }
    
    public void anularTramite(int nroTramite) throws TramiteException {
        expertoCargarTramite.anularTramite(nroTramite);
    }
    


}
