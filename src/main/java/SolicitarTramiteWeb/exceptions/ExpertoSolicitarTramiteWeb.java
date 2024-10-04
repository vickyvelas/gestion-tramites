package SolicitarTramiteWeb.exceptions;

import SolicitarTramiteWeb.dtos.DTOCategoriaTramite;
import SolicitarTramiteWeb.dtos.DTOCliente;
import SolicitarTramiteWeb.dtos.DTOTipoTramiteWeb;
import SolicitarTramiteWeb.dtos.DTOTramiteWeb;
import entidades.CategoriaTramite;
import entidades.Cliente;
import entidades.EstadoTramite;
import entidades.TipoTramite;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

public class ExpertoSolicitarTramiteWeb {
    
    // Método para obtener un cliente por su CUIT
    public DTOCliente obtenerCliente(int cuitCliente) {
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
        DTOCliente dtoc=new DTOCliente();
        //buscar('Estado','codigo='+nuevoEstadoDTO.getCodigo() and fechaBaja=null' )
        
        List<DTOCriterio> criterioList = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("cuitCliente");
        dto.setOperacion("=");
        dto.setValor(cuitCliente);
        criterioList.add(dto);
        
        DTOCriterio dto2  = new DTOCriterio();

        dto2.setAtributo("fechaHoraBajaCliente");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        
        List lCliente=FachadaPersistencia.getInstance().buscar("Cliente", criterioList);
        System.out.println("Tamaño:"+lCliente.size());  
        if(lCliente.size()> 0)
        {
            
            Cliente c=(Cliente) lCliente.get(0);
            dtoc.setCuitCliente(cuitCliente);
            dtoc.setNombreApellidoCliente(c.getNombreApellidoCliente());
            dtoc.setDireccionCliente(c.getDireccionCliente());
            dtoc.setMailCliente(c.getMailCliente());
        }
        /*
        FachadaPersistencia.getInstance().guardar(cliente);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        }*/
        return dtoc;
       
    }
    
        public List<DTOCategoriaTramite> obtenerCategoriaTramite() {
            
                   
        List<DTOCriterio> criterio = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("fechaHoraBajaCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(null);
        criterio.add(dto);
            
            
        
        
        List objetoList = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterio);
        List<DTOCategoriaTramite> categoria = new ArrayList<>();
        for (Object x : objetoList) {
            CategoriaTramite categ = (CategoriaTramite) x;
            DTOCategoriaTramite DTOcategoriatramite = new DTOCategoriaTramite();
            DTOcategoriatramite.setCodCategoriaTramite(categ.getCodCategoriaTramite());
            DTOcategoriatramite.setNombreCategoriaTramite(categ.getNombreCategoriaTramite());
            categoria.add(DTOcategoriatramite);
        
        }
        return categoria;
    }
        
        public DTOTipoTramiteWeb elegirTipoTramite(String nombreTipoTramite) {
            DTOTipoTramiteWeb dtotp=new DTOTipoTramiteWeb();
            
            List<DTOCriterio> rcriterio = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("nombreCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor("Renovacion");
        rcriterio.add(dto);
        
       
           // TipoTramite t=(TipoTramite) nomb;
            dtotp.setNombreTipoTramite(nombreTipoTramite);
        
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", rcriterio);
        List<DTOCategoriaTramite> categoria = new ArrayList<>();
        for (Object x : objetoList) {
            CategoriaTramite categ = (CategoriaTramite) x;
            DTOCategoriaTramite DTOcategoriatramite = new DTOCategoriaTramite();
            DTOcategoriatramite.setCodCategoriaTramite(categ.getCodCategoriaTramite());
            DTOcategoriatramite.setNombreCategoriaTramite(categ.getNombreCategoriaTramite());
            categoria.add(DTOcategoriatramite);
        
        }
        return dtotp;
        }
        
        
     
    

    /*

   
    
    // Método para elegir un tipo de trámite por nombre
    public DTOTipoTramiteWeb elegirTipoTramite(String nombreTipoTramite) {
        // Lógica para elegir el tipo de trámite
        DTOTipoTramiteWeb tipoTramite = new DTOTipoTramiteWeb();
        // Ejemplo: setear valores o consultar el tipo de trámite
        tipoTramite.setNombreTipoTramite(nombreTipoTramite);
        return tipoTramite;
    }
    
    
    /*
    // Método para solicitar un trámite
    public DTOTramiteWeb solicitarTramite() {
        // Lógica para solicitar el trámite
        DTOTramiteWeb tramite = new DTOTramiteWeb();
        // Ejemplo: creación del trámite
        tramite.setEstadoTramite();
        return tramite;
    }


    DTOCliente ingresarDatos(int cuitCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     */
}





