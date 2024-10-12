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
        
        /*
        public List<DTOCategoriaTramite> elegirCategoriaTramite(String nombreCategoriaTramite) {
           
            CategoriaTramite categ;
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("nombreCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(nombreCategoriaTramite);

        criterioList.add(dto);

        try {

            categ = (CategoriaTramite) FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList).get(0);
            DTOCategoriaTramite DTOcategoriatramite = new DTOCategoriaTramite();
            DTOcategoriatramite.setNombreCategoriaTramite(categ.getNombreCategoriaTramite());
           } catch (Exception e) {
            System.out.println("No se pudo recuperar la categoria --- " + e.toString());
        }
        
        List<DTOCriterio> acriterio = new ArrayList<>();
        
        DTOCriterio dto4 = new DTOCriterio();
        

        dto4.setAtributo("CategoriaTramite");
        dto4.setOperacion("=");
        dto4.setValor(null);
        criterioList.add(dto);
        
        DTOCriterio dto2  = new DTOCriterio();

        dto2.setAtributo("fechaHoraBajaCliente");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", acriterio);
        
        List<DTOCategoriaTramite> categoria = new ArrayList<>();
        for (Object x : objetoList) {
            CategoriaTramite categ1 = (CategoriaTramite) x;
            DTOCategoriaTramite DTOcategoriatramite = new DTOCategoriaTramite();
            DTOcategoriatramite.setCodCategoriaTramite(categ1.getCodCategoriaTramite());
            DTOcategoriatramite.setNombreCategoriaTramite(categ1.getNombreCategoriaTramite());
            categoria.add(DTOcategoriatramite);
            
            }
        return categoria;
    }
     */
        /*
            List<DTOCriterio> ocriterio = new ArrayList<>();
            
            DTOCriterio dto3 = new DTOCriterio();
            // Primero busco nombre Categ
            dto3.setAtributo("nombreCategoriaTramite");
            dto3.setOperacion("=");
            dto3.setValor(nombreCategoriaTramite);
            ocriterio.add(dto3);
            
            CategoriaTramite nmbcateg = (CategoriaTramite) FachadaPersistencia.getInstance().buscar("CategoriaTramite", ocriterio).get(0);
            
            ocriterio.clear();

            dto3.setAtributo("estado");
            dto3.setOperacion("=");
            dto3.setValor(nmbcateg);

            criterioList.add(dto);
       //List objetoList = FachadaPersistencia.getInstance().buscar("CategoriaTramite", ocriterio);
   //List lCliente=FachadaPersistencia.getInstance().buscar("Cliente", criterioList);
        System.out.println("Tamaño:"+objetoList.size());  
        if(objetoList.size()> 0)
        {
            
            TipoTramite t=(TipoTramite) objetoList.get(0);
            dtoc.setCodTipoTramite(codTipoTramite);
            dtoc.setNombreTipoTramite(nombreTipoTramite);
            dtoc.setDescripcion(c.getDireccionCliente());
            
        }
       
            
            List<DTOCriterio> lcriterio = new ArrayList<>();
            DTOCriterio dtotip = new DTOCriterio();
            
            dtotip.setAtributo("nombreCategoriaTramite");
            dtotip.setOperacion("=");
            dtotip.setValor("Renovacion");
            ocriterio.add(dto3);
            
            
  
        }
   /*
        List<DTOCategoriaTramite> categoria = new ArrayList<>();
        for (Object x : objetoList) {
            CategoriaTramite categ = (CategoriaTramite) x;
            DTOCategoriaTramite DTOcategoriatramite = new DTOCategoriaTramite();
            DTOcategoriatramite.setNombreCategoriaTramite(categ.getNombreCategoriaTramite());
            categoria.add(DTOcategoriatramite);
            
            
            TipoTramite tipotra = (TipoTramite) x;
            DTOTipoTramiteWeb dtw=new DTOTipoTramiteWeb();
            dtw.setCodTipoTramite(0);
            dtw.setDescripcionTipoTramiteWeb(tipotra.getDescripcionTipoTramiteWeb());
            dtw.setNombreTipoTramite(tipotra.getNombreTipoTramite());
        }
            return categoria;
            
          
          
        }
        */
      /*
     // public DTOTipoTramiteWeb elegirCategoriaTramite(String nombreCategoriaTramite) {
            DTOTipoTramiteWeb dtotp=new DTOTipoTramiteWeb();
            
            List<DTOCriterio> rcriterio = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("nombreCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor("Renovacion");
        rcriterio.add(dto);
        
       if(rcriterio.size()> 0)
               {
          // TipoTramite t=(TipoTramite)rcriterio.get(0);
            dtotp.setNombreTipoTramite(t.getNombreTipoTramite());
        }
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
        
        */
    
}
   
    




