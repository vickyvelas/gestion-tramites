package ABMTipoTramite;
//fsfdfsvsvdfdw
import ABMTipoTramite.dtos.DTOModificarTipoTramite;
import ABMTipoTramite.dtos.DTOModificarTipoTramiteIn;
import ABMTipoTramite.dtos.DTONuevoTipoTramite;
import ABMTipoTramite.dtos.DTOTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import java.util.List;

public class ControladorABMTipoTramite {

    public static DTOModificarTipoTramite buscarABMTipoTramiteAModificar(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     private ExpertoABMTipoTramite expertoABMTipoTramite = new ExpertoABMTipoTramite();
    public List<DTOTipoTramite> buscarTipoTramites(int codigo, String nombre){
        return expertoABMTipoTramite.buscarTipoTramites(codigo,nombre);
    }

    public void agregarTipoTramite(DTONuevoTipoTramite DTONuevoTipoTramite) throws ABMTipoTramiteException{
        expertoABMTipoTramite.agregarTipoTramite(DTONuevoTipoTramite);
    }

    public void modificarTipoTramite(DTOModificarTipoTramiteIn DTOModificarTipoTramiteIn){
        expertoABMTipoTramite.modificarTipoTramite(DTOModificarTipoTramiteIn);
    }

    public DTOModificarTipoTramite buscarTipoTramiteAModificar(int codigo){
        return expertoABMTipoTramite.buscarTipoTramiteAModificar(codigo);
    }

    public void darDeBajaTipoTramite(int codigo) throws ABMTipoTramiteException {
        expertoABMTipoTramite.darDeBajaTipoTramite(codigo);
    }
    
}

