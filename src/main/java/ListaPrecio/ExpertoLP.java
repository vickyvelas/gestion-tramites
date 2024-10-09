/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio;

import ListaPrecio.dtos.DTODetalleListaPrecioExportar;
import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
import ListaPrecio.dtos.DTOListaPrecioExportar;
import ListaPrecio.dtos.DTOListaPrecioImportar;
import entidades.DetallePrecioTipoTramite;
import entidades.ListaPrecio;
import entidades.TipoTramite;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author marti
 */
public class ExpertoLP {
    
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

    public void importarListaPrecio( DTOListaPrecioImportar listaPrecioImportarDTO ) {
       
        
          
        List<DTOCriterio> lCriterio=new ArrayList<>();
        
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);
//        ListaPrecio listaPrecioAnterior = new ListaPrecio();
//        for(Object l : objetoList){
//        for(int i=0;objetoList.size();i++)
//            ListaPrecio lp = (ListaPrecio) l;
//            if(lp.getFechaHoraDesdeListaPrecio().compareTo(objetoList).get(0).getFechaHoraDesdeListaPrecio()<0){
//                
//            }
//            
//        }
//   [4,8,3]
//        isPrimero=True
//        valor=0
//        bucle
//                   if primero
//                           valor=valorActual
//                            isPrimero=False
//                   
//                    else
//                            if valor<valorActual
//                                    valor=ValorActual
//         fin bucle
//       
//
//           
//        ListaPrecio listaPrecioAnterior = objetoList.stream()
//                
//                .map(x -> (ListaPrecio) x)
//                .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio))
//                .orElse(null);
//        
//        
//        // Modificar instancia de ListaPrecio con fechaHoraHastaListaPrecio igual a fechaHoraDesdeListaPrecio del dto
//        listaPrecioAnterior.setFechaHoraHastaListaPrecio(listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio());
//
//        // Guardar cambios
//        FachadaPersistencia.getInstance().iniciarTransaccion();
//        FachadaPersistencia.getInstance().guardar(listaPrecioAnterior);
//        FachadaPersistencia.getInstance().finalizarTransaccion();
//        
//        //CREAR NUEVA LISTA PRECIO
//        
//        ListaPrecio nuevaListaPrecio = new ListaPrecio();
//            nuevaListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecioAnterior.getFechaHoraHastaListaPrecio());
//            nuevaListaPrecio.setFechaHoraHastaListaPrecio(listaPrecioImportarDTO.getFechaHoraHastaListaPrecio()); 
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
//            DetallePrecioTipoTramite  nuevodetallePrecioTipoTramite = new DetallePrecioTipoTramite ();
//                nuevodetallePrecioTipoTramite.setTipoTramite(tipoTramite);
//                nuevodetallePrecioTipoTramite.setPrecioTipoTramite(0);
//                
//                //obtengo detalles
//               List<DTODetalleListaPrecioImportar> listaDetalles=  listaPrecioImportarDTO.getDetalleListaPrecioImportar();
//                boolean isEncontrado = false;
//                for(DTODetalleListaPrecioImportar detImpdto : listaDetalles){
//                    
//                    int codTT = tipoTramite.getCodTipoTramite();
//                    int codTTdto= detImpdto.getCodTipotramite();
//                    
//                    //ver que los codigos sean iguales
//                    if(codTT == codTTdto){
//                        
//                        nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImpdto.getPrecioTipoTramite());
//                        isEncontrado=true;
//                    }
//                    
//                    
//                    if(isEncontrado=false){
//                        //lista de detalles anterior
//                        List<DetallePrecioTipoTramite> listaDetallesAnt =listaPrecioAnterior.getDetallePrecioTipoTramiteList();
//                        
//                            for(DetallePrecioTipoTramite detImp : listaDetallesAnt){
//                                TipoTramite ttAnterior = detImp.getTipoTramite();
//                               int codTTA = tipoTramite.getCodTipoTramite();
//                               if (codTTA==codTTdto){
//                                   
//                                   nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImp.getPrecioTipoTramite());
//                                   
//                               }
//                               //añadir el nuevo detalle a la lista de precios nueva
//                               nuevaListaPrecio.addDetallePrecioTipoTramiteList(nuevodetallePrecioTipoTramite);
//                               
//                               //guardo el detalle? o queda automatico con la lista?
//                               //FachadaPersistencia.getInstance().guardar(nuevodetallePrecioTipoTramite);
//                                
//                            }
//                    }
//                }
//                 FachadaPersistencia.getInstance().iniciarTransaccion();
//                 FachadaPersistencia.getInstance().guardar(nuevaListaPrecio);
//                 FachadaPersistencia.getInstance().finalizarTransaccion();
//                
//        }
//        
//      
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
