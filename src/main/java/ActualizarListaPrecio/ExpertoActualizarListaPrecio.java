//package ActualizarListaPrecio;
//
//import ActualizarListaPrecio.dtos.DTODetalleListaPrecioExportar;
//import ActualizarListaPrecio.dtos.DTODetalleListaPrecioImportar;
//import ActualizarListaPrecio.dtos.DTOListaPrecioExportar;
//import ActualizarListaPrecio.dtos.DTOListaPrecioImportar;
//import entidades.Cliente;
//import entidades.DetallePrecioTipoTramite;
//import entidades.ListaPrecio;
//import entidades.TipoTramite;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import utils.DTOCriterio;
//import utils.FachadaPersistencia;
//
///**
// *
// * @author arode
// */
//public class ExpertoActualizarListaPrecio {
//
//    public List<DTOListaPrecioExportar> obtenerListaPrecio(Timestamp fechaActual) {
//        //lista que devuelve el metodo
//        List<DTOListaPrecioExportar> listadelistas = new ArrayList<>();
//
//    
//        List<DTOCriterio> lCriterio = new ArrayList<DTOCriterio>();
//
//        if (fechaActual != null) {
//            DTOCriterio unCriterio = new DTOCriterio();
//            unCriterio.setAtributo("fechaHoraHastaListaPrecio");
//            unCriterio.setOperacion(">");
//            unCriterio.setValor(fechaActual);
//            lCriterio.add(unCriterio);
//        }
//        //Obtengo la lista de OBJETOS(listas de precios) desde la fachada de persistencia    
//        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);
//
//        for (Object x : objetoList) {
//            ListaPrecio listaPrecio = (ListaPrecio) x;
//            DTOListaPrecioExportar dtoListaPrecio = new DTOListaPrecioExportar();
//            dtoListaPrecio.setCodListaPrecio(listaPrecio.getCodListaPrecio());
//            dtoListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecio.getFechaHoraDesdeListaPrecio());
//            dtoListaPrecio.setFechaHoraHastaListaPrecio(listaPrecio.getFechaHoraHastaListaPrecio());
//            listadelistas.add(dtoListaPrecio);
//        }
//        return listadelistas;
//    }
//
//    public DTOListaPrecioImportar importarListaPrecio( DTOListaPrecioImportar listaPrecioImportarDTO, DTODetalleListaPrecioImportar detallesImportar) {
//       
//        List<DTOCriterio> lCriterio=new ArrayList<>();
//        
//        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);
//        
//       ListaPrecio listaPrecioMaxima = null;
//        Timestamp fechaMaxima = null;
//        
//        for (Object x : objetoList) {
//            ListaPrecio listaPrecio = (ListaPrecio) x;
//            Timestamp fechaDesde = listaPrecio.getFechaHoraDesdeListaPrecio();
//            
//            if (fechaMaxima == null || fechaDesde.after(fechaMaxima)) {
//                fechaMaxima = fechaDesde;
//                listaPrecioMaxima = listaPrecio;
//            }
//        }
//          Timestamp fechaDesdeMaxima = listaPrecioMaxima.getFechaHoraDesdeListaPrecio();
//
//        // Modificar instancia de ListaPrecio con fechaHoraHastaListaPrecio igual a fechaHoraDesdeListaPrecio (Paso 12)
//        listaPrecioMaxima.setFechaHoraHastaListaPrecio(fechaDesdeMaxima);
//
//        // Guardar cambios
//        FachadaPersistencia.getInstance().guardar(listaPrecioMaxima);
//        
//        
//        //CREAR NUEVA LISTA PRECIO
//        
//        ListaPrecio nuevaListaPrecio = new ListaPrecio();
//            nuevaListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecioMaxima.getFechaHoraHastaListaPrecio());
//            nuevaListaPrecio.setFechaHoraHastaListaPrecio(null); //ver como quedaría
//            
//            
//        //BUSCAR TIPOS TRAMITES
//      
//            DTOCriterio unCriterio = new DTOCriterio();
//            unCriterio.setAtributo("fechaHoraBajaTipoTramite");
//            unCriterio.setOperacion("!=");
//            unCriterio.setValor(null);
//            lCriterio.add(unCriterio);
//        List<Object> objetoListTT = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
//        
//        for(Object tt: objetoListTT){
//            TipoTramite tipoTramite = (TipoTramite)  tt;
//            
//            DetallePrecioTipoTramite  detallePrecioTipoTramite = new DetallePrecioTipoTramite ();
//                detallePrecioTipoTramite.setPrecioTipoTramite(0);
//                
//               List<DTODetalleListaPrecioImportar> listaDetalles=  listaPrecioImportarDTO.getDetalleListaPrecioImportar();
//                
//                for(DTODetalleListaPrecioImportar detImpdto : listaDetalles){
//                    boolean isEncontrado = false;
//                    tipoTramite.getCodTipoTramite();
//                    detImpdto.getCodTipotramite();
//                    //ver que los codigos sean iguales
//                    isEncontrado=true;
//                    
//                    if(isEncontrado=false){
//                        List<DetallePrecioTipoTramite> listaDetallesImp =listaPrecioMaxima.getDetallePrecioTipoTramiteList();
//                            for(DetallePrecioTipoTramite detImp : listaDetallesImp){
//                                detImp.getPrecioTipoTramite();
//                                
//                                //Comprobar que instancia de TipoTramite sea igual a instancia de TipoTramite 
//                                //precioTipoTramite (Paso 16.3.1)   igual a precioTipoTramite 
//                                //Añadir nueva instancia de DetallePrecioTipoTramite a nueva instancia de ListaPrecio
//
//                                
//                            }
//                    }
//                }
//                
//        }
//        
//        
//  
//        return null; //arreglar retorno
//    }
//
//    public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
//        
//        List<DTOCriterio> lCriterio = new ArrayList<DTOCriterio>();
//        DTOCriterio unCriterio = new DTOCriterio();
//            unCriterio.setAtributo("codListaPrecio");
//            unCriterio.setOperacion("=");
//            unCriterio.setValor(codListaPrecio);
//        ListaPrecio lp = (ListaPrecio) FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio).get(0);
//
//        DTOListaPrecioExportar dtoListaPrecioExportar = new DTOListaPrecioExportar();
//                dtoListaPrecioExportar.setCodListaPrecio(lp.getCodListaPrecio());
//                dtoListaPrecioExportar.setFechaHoraDesdeListaPrecio(lp.getFechaHoraDesdeListaPrecio());
//                dtoListaPrecioExportar.setFechaHoraHastaListaPrecio(lp.getFechaHoraHastaListaPrecio());
//        List<DetallePrecioTipoTramite> listDet = lp.getDetallePrecioTipoTramiteList();
//
//        for (DetallePrecioTipoTramite detlp : listDet) {
//            
//               TipoTramite tt=  detlp.getTipoTramite();
//
//            DTODetalleListaPrecioExportar dtoDetalleListaPrecioExportar = new DTODetalleListaPrecioExportar();
//            dtoDetalleListaPrecioExportar.setCodTipoTramite(tt.getCodTipoTramite());
//            dtoDetalleListaPrecioExportar.setNombreTipoTramite(tt.getNombreTipoTramite());
//            dtoDetalleListaPrecioExportar.setPrecioTipoTramite(detlp.getPrecioTipoTramite());
////            dtlp.add(dtoDetalleListaPrecioExportar);
////            lp.add(detlp);
//        }
//        return dtoListaPrecioExportar;
//    }
//    
//    
////no está en la secuencia
////    void darDeBajaListaPrecio(int codigo) {
////        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
////    }
//
//}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ActualizarListaPrecio;

import ActualizarListaPrecio.dtos.DTODetalleListaPrecioExportar;
import ActualizarListaPrecio.dtos.DTODetalleListaPrecioImportar;
import ActualizarListaPrecio.dtos.DTOListaPrecioExportar;
import ActualizarListaPrecio.dtos.DTOListaPrecioImportar;
import entidades.DetallePrecioTipoTramite;
import entidades.ListaPrecio;
import entidades.TipoTramite;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author marti
 */
public class ExpertoActualizarListaPrecio {
    
    
    
    public List<DTOListaPrecioExportar> obtenerListaPrecio(Timestamp fechaActual) {
        //lista que devuelve el metodo
        List<DTOListaPrecioExportar> listadelistas = new ArrayList<>();

    
        List<DTOCriterio> lCriterio = new ArrayList<>();

        if (fechaActual != null) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("fechaHoraHastaListaPrecio");
            unCriterio.setOperacion(">");
            unCriterio.setValor(fechaActual);
            lCriterio.add(unCriterio);
        }
        //Obtengo la lista de OBJETOS(listas de precios) desde la fachada de persistencia    
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);

        for (Object x : objetoList) {
            ListaPrecio listaPrecio = (ListaPrecio) x;
            DTOListaPrecioExportar dtoListaPrecio = new DTOListaPrecioExportar();
            dtoListaPrecio.setCodListaPrecio(listaPrecio.getCodListaPrecio());
            dtoListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecio.getFechaHoraDesdeListaPrecio());
            dtoListaPrecio.setFechaHoraHastaListaPrecio(listaPrecio.getFechaHoraHastaListaPrecio());
            listadelistas.add(dtoListaPrecio);
        }
        return listadelistas;
    }

    public DTOListaPrecioImportar importarListaPrecio( DTOListaPrecioImportar listaPrecioImportarDTO, DTODetalleListaPrecioImportar detallesImportar) {
       
        List<DTOCriterio> lCriterio=new ArrayList<>();
        
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);
        
        ListaPrecio listaPrecioAnterior = objetoList.stream()
                .map(x -> (ListaPrecio) x)
                .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio))
                .orElse(null);
       
        // Modificar instancia de ListaPrecio con fechaHoraHastaListaPrecio igual a fechaHoraDesdeListaPrecio del dto
        listaPrecioAnterior.setFechaHoraHastaListaPrecio(listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio());

        // Guardar cambios
        FachadaPersistencia.getInstance().guardar(listaPrecioAnterior);
        
        
        //CREAR NUEVA LISTA PRECIO
        
        ListaPrecio nuevaListaPrecio = new ListaPrecio();
            nuevaListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecioAnterior.getFechaHoraHastaListaPrecio());
            nuevaListaPrecio.setFechaHoraHastaListaPrecio(listaPrecioImportarDTO.getFechaHoraHastaListaPrecio()); 
            
            
        //BUSCAR TIPOS TRAMITES
      
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("fechaHoraBajaTipoTramite");
            unCriterio.setOperacion("!=");
            unCriterio.setValor(null);
            lCriterio.add(unCriterio);
        List<Object> objetoListTT = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        
        for(Object tt: objetoListTT){
            TipoTramite tipoTramite = (TipoTramite)  tt;
            
            DetallePrecioTipoTramite  nuevodetallePrecioTipoTramite = new DetallePrecioTipoTramite ();
                nuevodetallePrecioTipoTramite.setTipoTramite(tipoTramite);
                nuevodetallePrecioTipoTramite.setPrecioTipoTramite(0);
                
                //obtengo detalles
               List<DTODetalleListaPrecioImportar> listaDetalles=  listaPrecioImportarDTO.getDetalleListaPrecioImportar();
                boolean isEncontrado = false;
                for(DTODetalleListaPrecioImportar detImpdto : listaDetalles){
                    
                    int codTT = tipoTramite.getCodTipoTramite();
                    int codTTdto= detImpdto.getCodTipotramite();
                    
                    //ver que los codigos sean iguales
                    if(codTT == codTTdto){
                        
                        nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImpdto.getPrecioTipoTramite());
                        isEncontrado=true;
                    }
                    
                    
                    if(isEncontrado=false){
                        //lista de detalles anterior
                        List<DetallePrecioTipoTramite> listaDetallesAnt =listaPrecioAnterior.getDetallePrecioTipoTramiteList();
                        
                            for(DetallePrecioTipoTramite detImp : listaDetallesAnt){
                                TipoTramite ttAnterior = detImp.getTipoTramite();
                               int codTTA = tipoTramite.getCodTipoTramite();
                               if (codTTA==codTTdto){
                                   
                                   nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImp.getPrecioTipoTramite());
                                   
                               }
                               //añadir el nuevo detalle a la lista de precios nueva
                               nuevaListaPrecio.addDetallePrecioTipoTramiteList(nuevodetallePrecioTipoTramite);
                               
                               //guardo el detalle? o queda automatico con la lista?
                               //FachadaPersistencia.getInstance().guardar(nuevodetallePrecioTipoTramite);
                                
                            }
                    }
                }
                
                 FachadaPersistencia.getInstance().guardar(nuevaListaPrecio);
                 
                
        }
        
        
  
        return null; //arreglar retorno
    }

    public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
        
        List<DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("codListaPrecio");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codListaPrecio);
        ListaPrecio lp = (ListaPrecio) FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio).get(0);

        DTOListaPrecioExportar dtoListaPrecioExportar = new DTOListaPrecioExportar();
                dtoListaPrecioExportar.setCodListaPrecio(lp.getCodListaPrecio());
                dtoListaPrecioExportar.setFechaHoraDesdeListaPrecio(lp.getFechaHoraDesdeListaPrecio());
                dtoListaPrecioExportar.setFechaHoraHastaListaPrecio(lp.getFechaHoraHastaListaPrecio());
                //obtiene detalles
                List<DetallePrecioTipoTramite> listDet = lp.getDetallePrecioTipoTramiteList();

        for (DetallePrecioTipoTramite detlp : listDet) {
            
               TipoTramite tt =  detlp.getTipoTramite();

            DTODetalleListaPrecioExportar dtoDetalleListaPrecioExportar = new DTODetalleListaPrecioExportar();
            dtoDetalleListaPrecioExportar.setCodTipoTramite(tt.getCodTipoTramite());
            dtoDetalleListaPrecioExportar.setNombreTipoTramite(tt.getNombreTipoTramite());
            dtoDetalleListaPrecioExportar.setPrecioTipoTramite(detlp.getPrecioTipoTramite());
            dtoListaPrecioExportar.addDetalleListaPrecioExportar(dtoDetalleListaPrecioExportar);
        }
        return dtoListaPrecioExportar;
    }
    
    
}

