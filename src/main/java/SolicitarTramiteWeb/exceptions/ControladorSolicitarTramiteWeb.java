package SolicitarTramiteWeb.exceptions;

import SolicitarTramiteWeb.dtos.DTOCategoriaTramite;
import SolicitarTramiteWeb.dtos.DTOCliente;
import SolicitarTramiteWeb.dtos.DTOTipoTramiteWeb;
import SolicitarTramiteWeb.dtos.DTOTramiteWeb;
import java.util.List;

public class ControladorSolicitarTramiteWeb {
    private ExpertoSolicitarTramiteWeb expertoSolicitarTramiteWeb = new ExpertoSolicitarTramiteWeb();
    
    /*  public DTOCategoriaTramite elegirCategoriaTramite(String nombreCategoriaTramite){
       return expertoSolicitarTramiteWeb.elegirCategoriaTramite(nombreCategoriaTramite);
   }
    */
    public List<DTOCategoriaTramite> obtenerCategoriaTramite(){
       return expertoSolicitarTramiteWeb.obtenerCategoriaTramite();
   }
  
   public DTOTipoTramiteWeb elegirTipoTramite(String nombreTipoTramite) {
      return expertoSolicitarTramiteWeb.elegirTipoTramite(nombreTipoTramite);  
   }
   
    public DTOCliente obtenerCliente(int cuitCliente) {
      return expertoSolicitarTramiteWeb.obtenerCliente(cuitCliente);
   }
    /*
    public DTOCliente ingresarDatos(int cuitCliente) {
        return expertoSolicitarTramiteWeb.ingresarDatos (cuitCliente);
    }*/
    /*
    public DTOTramiteWeb solicitarTramite() {
      return expertoSolicitarTramiteWeb.solicitarTramite();
    }*/
    





}