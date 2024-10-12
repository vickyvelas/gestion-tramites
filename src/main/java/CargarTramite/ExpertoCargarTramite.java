package CargarTramite;


import CargarTramite.dtos.DTOClienteV;
import CargarTramite.dtos.DTODocumentacion;
import CargarTramite.dtos.DTOTipoTramiteV;
import CargarTramite.dtos.DTOTramiteV;
import CargarTramite.dtos.DTOVisualizarTramite;
import CargarTramite.exceptions.TramiteException;
import ObtenerConsultor.ExpertoObtenerConsultor;
import entidades.Cliente;
import entidades.Consultor;
import entidades.DetallePrecioTipoTramite;
import entidades.Documentacion;
import entidades.EstadoTramite;
import entidades.ListaPrecio;
import entidades.TipoTramite;
import entidades.Tramite;
import entidades.TramiteDocumentacion;
import entidades.TramiteEstado;
import entidades.Version;
import java.util.Date;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpertoCargarTramite {
    
    
    
public List<DTOTramiteV> mostrarTramites(Date fechaDesde, int cuitFiltro,
                int nroTramiteFiltro, String nombreEstadoTramiteFiltro, 
                int legajoConsultorFiltro, int codTipoTramiteFiltro){
        
    List<DTOCriterio> lCriterio = new ArrayList<DTOCriterio>(); 
    
    try {
        
        if (fechaDesde != null) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("fechaHoraCargaTramite");
            unCriterio.setOperacion(">=");
            unCriterio.setValor(fechaDesde);
            lCriterio.add(unCriterio);
        }
        
        
        if ( cuitFiltro > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("cliente.cuitCliente");
            unCriterio.setOperacion("=");
            unCriterio.setValor(cuitFiltro);
            lCriterio.add(unCriterio);
        }

        if(nombreEstadoTramiteFiltro.trim().length() > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("estadoTramite.nombreEstadoTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreEstadoTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        
        if(legajoConsultorFiltro > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("consultor.legajoConsultor");
            unCriterio.setOperacion("=");
            unCriterio.setValor(legajoConsultorFiltro);
            lCriterio.add(unCriterio);
        }
        if(codTipoTramiteFiltro > 0) {
            
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("tipoTramite.codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codTipoTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        if(nroTramiteFiltro > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        
        List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        lCriterio.clear();
        List<DTOTramiteV> tramitesResultado = new ArrayList<>();

        for (Object x : objetoList) {
            Tramite tramite = (Tramite) x;
            DTOTramiteV tramiteDTO = new DTOTramiteV();
            tramiteDTO.setNroTramite(tramite.getNroTramite());
            tramiteDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            tramiteDTO.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            tramiteDTO.setNombreConsultor(tramite.getConsultor().getNombreApellidoConsultor());
            tramiteDTO.setNombreCliente(tramite.getCliente().getNombreApellidoCliente());
            tramiteDTO.setCUITCliente(tramite.getCliente().getCuitCliente());
            tramiteDTO.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            
            System.out.println("fecha desde" +fechaDesde);
            System.out.println("fechaCarga" +tramite.getFechaHoraCargaTramite());
            tramiteDTO.setFechaHoraFinEntregaDoc(tramite.getFechaHoraFinEntregaDocumentacion());
            tramiteDTO.setFechaHoraBajaTramite(tramite.getFechaHoraBajaTramite());
            
            tramitesResultado.add(tramiteDTO);
        }
        
        return tramitesResultado;

    } catch (Exception e) {
        // Capturamos cualquier excepción y mostramos un mensaje de error
        System.err.println("Ocurrió un error: " + e.getMessage());
        e.printStackTrace();
        return new ArrayList<>();  // Devolvemos una lista vacía si ocurre un error
    }
}



    
     public DTOClienteV obtenerCliente(int cuit) throws TramiteException, Exception{
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();

        try{
            if(cuit>0)
            {
                DTOCriterio unCriterio=new DTOCriterio();
                unCriterio.setAtributo("cuitCliente");
                unCriterio.setOperacion("=");
                unCriterio.setValor(cuit);
                lCriterio.add(unCriterio);
            }
            List objetoList = FachadaPersistencia.getInstance().buscar("Cliente", lCriterio);
            Cliente clienteRecuperado = null;
            if (!objetoList.isEmpty()) {
                clienteRecuperado = (Cliente)objetoList.get(0); // Obtener el primer cliente
                DTOClienteV clienteDTO = new DTOClienteV();
                clienteDTO.setCUIT(clienteRecuperado.getCuitCliente());
                clienteDTO.setNombreCliente(clienteRecuperado.getNombreApellidoCliente());
                clienteDTO.setDireccion(clienteRecuperado.getDireccionCliente());
                System.out.println(clienteDTO.getCUIT());
                System.out.println(clienteDTO.getDireccion());
                System.out.println(clienteDTO.getNombreCliente());
                return clienteDTO;

            } else {
                DTOClienteV dto = new DTOClienteV();
                throw new TramiteException("El cliente solicitado no está registrado como tal");
            }
        }catch(Exception e){
             throw new Exception(e.getMessage());
            }
        }
    
     
     
     
     
     public DTOTipoTramiteV obtenerTipoTramite(int codigoTT) throws TramiteException, Exception{
         
         List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        try{


            if(codigoTT>0){
                DTOCriterio dto = new DTOCriterio();
                dto.setAtributo("codTipoTramite");
                dto.setOperacion("=");
                dto.setValor(codigoTT);
                lCriterio.add(dto);
            }

            List<Object> objectList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);

            if(!objectList.isEmpty()){
                TipoTramite tt = (TipoTramite)objectList.get(0);
                DTOTipoTramiteV ttDTO = new DTOTipoTramiteV();
                ttDTO.setCodigoTT(tt.getCodTipoTramite());
                ttDTO.setNombreTipoTramite(tt.getNombreTipoTramite());
                System.out.println(ttDTO.getCodigoTT());
                System.out.println(ttDTO.getNombreTipoTramite());
                return ttDTO;
            }else{
                throw new TramiteException("No se encontró el Tipo de Trámite solicitado.");
               }
            
         }catch(Exception e){
             throw new Exception("Error al cargar el trámite", e);
            }
     }
     
     
     
     
     public String botonCargar(int cuit, int codigoTT) throws TramiteException, Exception{
         
        try{
            
         FachadaPersistencia.getInstance().iniciarTransaccion();
     
         System.out.println("Enramos al botonCargar");
         //Variables auxiliares
         List<DTOCriterio> lCriterio = new ArrayList<DTOCriterio>();
         FachadaPersistencia fachada = FachadaPersistencia.getInstance();
         Timestamp fechaHoraActual = new Timestamp(System.currentTimeMillis());
         DTOCriterio criterio = new DTOCriterio();
         DTOCriterio criterio2 = new DTOCriterio();
         DTOCriterio criterio3 = new DTOCriterio();
         List<Object> objectList = new ArrayList<>();
         Date fechaDesde = new Date(0);
         Date mayorFechaDesde = new Date(0);
         Date fechaHasta = new Date(0);
         int contador=0;
     
         //Creamos el Tramite
         Tramite tramite = new Tramite();
         System.out.println("creamos tramite");
         
         //Asigamos la fechaCarga
         tramite.setFechaHoraCargaTramite(fechaHoraActual);
         System.out.println("fechaHoraCarga ok");
         
         //Asignamos TipoTramite
         if(codigoTT > 0){
             criterio.setAtributo("codTipoTramite");
             criterio.setOperacion("=");
             criterio.setValor(codigoTT);
             lCriterio.add(criterio);
         }
         objectList = fachada.buscar("TipoTramite", lCriterio);
         if(!objectList.isEmpty()){
             TipoTramite tt = (TipoTramite)objectList.get(0);
             tramite.setTipoTramite(tt);
             System.out.println("codigott=" +tramite.getTipoTramite().getCodTipoTramite()+ "=" +codigoTT);
             
             
             
             //Asignamos la fecha vencimiento documentacion
             int maxDiaEntrega = tt.getMaxDiaEntrega();
             System.out.println("max dia de entrega: " +tt.getMaxDiaEntrega());
             Calendar calendar = Calendar.getInstance();
             calendar.setTime(fechaHoraActual);
             calendar.add(Calendar.DAY_OF_MONTH, maxDiaEntrega);
             tramite.setFechaHoraVencimientoDocumentacion(new Timestamp(calendar.getTimeInMillis()));
             System.out.println("fechaHoraVencimientoDocumentacion= " +tramite.getFechaHoraVencimientoDocumentacion());
             System.out.println("fecha vencimiento documentacion ok");
             
              //Creamos instancia de TramiteDocumentacion y seteamos la Documentacion correspondiete al tipoTramite relacionado
                try{
                List<Documentacion> documentaciones = new ArrayList<>();
                documentaciones = tt.getDocumentaciones();
                
                    System.out.println("RECUPERE LA LISTA DE DOCUMENTACIONES");
                if(!documentaciones.isEmpty()){
                     contador=0;
                    System.out.println("cantidad de elementos en documnetaciones: "+documentaciones.size());
                    for(Documentacion doc : documentaciones){
                         TramiteDocumentacion td = new TramiteDocumentacion();
                         contador++;
                         td.setCodTramiteDocumentacion(contador);
                         System.out.println("contador= "+contador +" y codDocu= "+td.getCodTramiteDocumentacion());
                         td.setDocumentacion(doc);
                         tramite.addTramiteDocumentacionList(td);
                         System.out.println("tramitedocumentacion agregada ");
                         }
                     }else{throw new TramiteException("El tipo de tramite no tiene documentos asignados");}
                
                
                }catch(Exception e){
                     System.out.println("se rompe en tramiteDocumentacion");
                     throw new Exception(e.getMessage());
                }
                
         //Asignamos version
         try{
         lCriterio.clear();
         criterio.setAtributo("fechaHoraDesdeVersion");
         criterio.setOperacion("<=");
         criterio.setValor(fechaHoraActual);
             System.out.println(criterio.getAtributo() +criterio.getOperacion() +criterio.getValor());
         lCriterio.add(criterio);
         
         
         criterio2.setAtributo("fechaHoraHastaVersion");
         criterio2.setOperacion(">=");
         criterio2.setValor(fechaHoraActual);
             System.out.println(criterio2.getAtributo() +criterio2.getOperacion() +criterio2.getValor());
         lCriterio.add(criterio2);
         
         
         criterio3.setAtributo("fechaHoraBajaVersion");
         criterio3.setOperacion("==");
         criterio3.setValor(null);
             System.out.println(criterio3.getAtributo() +criterio3.getOperacion() +criterio3.getValor());
         lCriterio.add(criterio3);
         
         objectList.clear();
         objectList = fachada.buscar("Version", lCriterio);
         Version mayorVersion = null;
         if(!objectList.isEmpty()){
             for(Object v : objectList){
                 if(codigoTT == tt.getCodTipoTramite()){
                 Version version = (Version)v;
                 fechaDesde = version.getFechaHoraDesdeVersion();
                 if(fechaDesde.after(mayorFechaDesde)){
                     mayorFechaDesde = fechaDesde;
                     mayorVersion = version;
                     System.out.println("version= " +version.getOID());
                     System.out.println("mayorVersion= " +mayorVersion.getOID());
                    }
                }else{throw new TramiteException("No hay ninguna version que se relacione con el tipo de tramite nro "+codigoTT );}
            }
                tramite.setVersion(mayorVersion);
                System.out.println("codigoTT =" +codigoTT);
                System.out.println("codigo mayor version =" +mayorVersion.getTipoTramite().getCodTipoTramite());
             
                System.out.println("version lista");

        }else{throw new TramiteException("No se encontro una lista de versiones con esos criterios");}
              
         
         
         }catch(Exception e){
             System.out.println("version se rompio");
             throw new Exception(e.getMessage());
         }
                
       }else{throw new TramiteException("No se encontro ningun Tipo de Tramite con codigo = " +codigoTT);}
         
         
       //Asignamos Cliente
       lCriterio.clear();
       objectList.clear();
       
       if(cuit>0){
       criterio.setAtributo("cuitCliente");
       criterio.setOperacion("=");
       criterio.setValor(cuit);
       lCriterio.add(criterio);
       objectList = fachada.buscar("Cliente", lCriterio);
       Cliente cliente = (Cliente)objectList.get(0);
           System.out.println("cliente recuperado tiene cuit = " +cliente.getCuitCliente()+"= cuit = "+cuit);
       tramite.setCliente(cliente);
           System.out.println("cliente ok");
       }
         
         
       //Asignamos el nroTramite
       try{
       objectList.clear();
       objectList = fachada.buscar("Tramite", null);
       contador=0;
       if(!objectList.isEmpty()){
            for(int i=1; i<=objectList.size(); i++){
                contador=i;
           }

           tramite.setNroTramite(contador+1);
           System.out.println("tramite nro" +tramite.getNroTramite());
           System.out.println("tramite ok");
           }else{
           tramite.setNroTramite(1);
                System.out.println("nroTramite = " +tramite.getNroTramite());
            }
       }catch(Exception e){
           System.out.println("se rompio todo cuando queremos asignar el nroTramite");
           throw new Exception(e.getMessage());
       }
         
     
       //Asignamos el Estado Tramite
       lCriterio.clear();
       objectList.clear();
       criterio.setAtributo("nombreEstadoTramite");
       criterio.setOperacion("=");
       criterio.setValor("Iniciado");
        System.out.println(criterio.getAtributo() +criterio.getOperacion() +criterio.getValor());
       lCriterio.add(criterio);
    try{
       objectList = fachada.buscar("EstadoTramite", lCriterio);
       EstadoTramite estado = (EstadoTramite)objectList.get(0);
         System.out.println("Estado del tramite = " +estado.getNombreEstadoTramite());
       tramite.setEstadoTramite(estado);
         System.out.println("EstadoTramite ok");

         
       //Creamos instancia de TramiteEstado y asignamos el EstadoTramite
       TramiteEstado tramiteE = new TramiteEstado();
       tramiteE.setNroLineaTramiteEstado(1);
       tramiteE.setFechaHoraDesdeTramiteEstado(fechaHoraActual);
       tramiteE.setEstadoTramite(estado);
        System.out.println("tramiteEstadonro= "+tramiteE.getNroLineaTramiteEstado()
                +"con estado= " +tramiteE.getEstadoTramite().getNombreEstadoTramite());
       
       tramite.addTramiteEstadoList(tramiteE);
        }catch(Exception e){throw new Exception (e.getMessage());}




     
     
        //Buscamos lista precio, recuperamos la mayor, obtenemos detalle y asignamos precio al tramite
         try{
         lCriterio.clear();
         criterio.setAtributo("fechaHoraDesdeListaPrecio");
         criterio.setOperacion("<=");
         criterio.setValor(fechaHoraActual);
             System.out.println(criterio.getAtributo() +criterio.getOperacion() +criterio.getValor());
         lCriterio.add(criterio);
         
         
         criterio2.setAtributo("fechaHoraHastaListaPrecio");
         criterio2.setOperacion(">=");
         criterio2.setValor(fechaHoraActual);
             System.out.println(criterio2.getAtributo() +criterio2.getOperacion() +criterio2.getValor());
         lCriterio.add(criterio2);
         
         
         criterio3.setAtributo("fechaHoraBajaListaPrecio");
         criterio3.setOperacion("==");
         criterio3.setValor(null);
             System.out.println(criterio3.getAtributo() +criterio3.getOperacion() +criterio3.getValor());
         lCriterio.add(criterio3);
         
         objectList.clear();
         mayorFechaDesde = new Date(0);
             System.out.println("se reinicio mayorFecha?? mayorFecha= " +mayorFechaDesde);
         objectList = fachada.buscar("ListaPrecio", lCriterio);
         ListaPrecio mayorListaPrecio = null;
         if(!objectList.isEmpty()){
             for(Object l : objectList){
                 ListaPrecio lista = (ListaPrecio)l;
                 fechaDesde = lista.getFechaHoraDesdeListaPrecio();
                 if(fechaDesde.after(mayorFechaDesde)){
                     mayorFechaDesde = fechaDesde;
                     mayorListaPrecio = lista;
                     System.out.println("lista= " +lista.getOID());
                     System.out.println("mayorListaPrecio= " +mayorListaPrecio.getOID());
                    }
                }
            }
         
            List<DetallePrecioTipoTramite> detalles = mayorListaPrecio.getDetallePrecioTipoTramiteList();
             System.out.println("hola");

            for(DetallePrecioTipoTramite d : detalles){
                int codigoRecuperado = d.getTipoTramite().getCodTipoTramite();
                    if(codigoTT == codigoRecuperado){
                        tramite.setPrecioTramite(d.getPrecioTipoTramite());
                        System.out.println("tramite con precio = " +tramite.getPrecioTramite());
                       
                    }

                 }
            }catch(Exception e){
             System.out.println("lista precio se rompio");
             throw new Exception(e.getMessage());
            }

              
         //Include CU - ObtenerConsultor
        ExpertoObtenerConsultor experto = new ExpertoObtenerConsultor();
        Consultor consultor = experto.obtenerConsultor();
            System.out.println("consultor oid = " +consultor.getOID());
        tramite.setConsultor(consultor);
        
        FachadaPersistencia.getInstance().guardar(tramite);
             System.out.println("TRAMITE GUARDADO ");
             System.out.println("tramite con oid " +tramite.getOID());
        FachadaPersistencia.getInstance().finalizarTransaccion();
     
        
        }catch (Exception e) {
            throw new Exception(e.getCause());  
        }finally{
            String salida = "mostrarTramitesLista?faces-redirect=true";
            return salida;
        }
     }
     
     
     
     
     
     public DTOVisualizarTramite visualizarTramite(int nroTramite) throws Exception{
         
         DTOVisualizarTramite dtoV = new DTOVisualizarTramite();
         DTOCriterio criterio = new DTOCriterio();  
         List<DTOCriterio> lCriterio = new ArrayList<>();
         
         if(nroTramite>0){
             criterio.setAtributo("nroTramite");
             criterio.setOperacion("=");
             criterio.setValor(nroTramite);
             lCriterio.add(criterio);
         }
         
         List<Object> objectList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        
         try{
             
         
         if(!objectList.isEmpty()){
            Tramite tramite = (Tramite)objectList.get(0);
            dtoV.setNroTramite(tramite.getNroTramite());
            System.out.println("Número de trámite: " + dtoV.getNroTramite());
            
            dtoV.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            System.out.println("Fecha de carga: " + dtoV.getFechaHoraCargaTramite());

            dtoV.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            System.out.println("Nombre de tipo de trámite: " + dtoV.getNombreTipoTramite());

            dtoV.setNombreCliente(tramite.getCliente().getNombreApellidoCliente());
            System.out.println("Nombre del cliente: " + dtoV.getNombreCliente());

            dtoV.setCuitCliente(tramite.getCliente().getCuitCliente());
            System.out.println("CUIT del cliente: " + dtoV.getCuitCliente());

            dtoV.setDireccion(tramite.getCliente().getDireccionCliente());
            System.out.println("Dirección del cliente: " + dtoV.getDireccion());

            dtoV.setNombreApellidoConsultor(tramite.getConsultor().getNombreApellidoConsultor());
            System.out.println("Nombre del consultor: " + dtoV.getNombreApellidoConsultor());
            
            dtoV.setPrecioTramite(tramite.getPrecioTramite());
            System.out.println("Precio del trámite: " + dtoV.getPrecioTramite());

            dtoV.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            System.out.println("Nombre del estado del trámite: " + dtoV.getNombreEstadoTramite());
            
            List<TramiteDocumentacion> presentarDoc = tramite.getTramiteDocumentacionList();
            
            if(!presentarDoc.isEmpty()){
                for(TramiteDocumentacion t : presentarDoc){
                    DTODocumentacion dtoDoc = new DTODocumentacion();
                    Documentacion recuperada = t.getDocumentacion();
                    dtoDoc.setNombreDocumentacion(recuperada.getNombreDocumentacion());
                    System.out.println("nombre documentacion: " + dtoDoc.getNombreDocumentacion());
                    dtoDoc.setFechaHoraEntrega(t.getFechaHoraEntrega());
                        System.out.println("fecha de entrega: " + dtoDoc.getFechaHoraEntrega());
                    dtoV.addDTODocumentacion(dtoDoc);
                    }
                }   
            }
         
        }catch(Exception e){
                throw new Exception("El tramite no presenta ninguna documentacion asociada",e);
            }
         
         return dtoV;
  }
     
     
     
     
     
     

    public void anularTramite(int nroTramite) throws TramiteException {
    FachadaPersistencia.getInstance().iniciarTransaccion(); // Iniciar transacción
    boolean exito = false; // Bandera para verificar el éxito de la operación

    try {
        
        List<DTOCriterio> criterioList = new ArrayList<>();
        
        if (nroTramite > 0) {
            
            DTOCriterio dto = new DTOCriterio();
            dto.setAtributo("nroTramite");
            dto.setOperacion("=");
            dto.setValor(nroTramite);
            criterioList.add(dto);

            
            DTOCriterio dto2 = new DTOCriterio();
            dto2.setAtributo("fechaHoraBajaTramite");
            dto2.setOperacion("=");
            dto2.setValor(null);
            criterioList.add(dto2);

          
            List<Tramite> resultado = (List) FachadaPersistencia.getInstance().buscar("Tramite", criterioList);
            
            if (!resultado.isEmpty()) {
                Tramite tramiteEncontrado = (Tramite) resultado.get(0);
                String nombreEstado = tramiteEncontrado.getEstadoTramite().getNombreEstadoTramite();
                
                if (nombreEstado.equalsIgnoreCase("iniciado")) {
                    // Dar de baja el trámite
                    tramiteEncontrado.setFechaHoraBajaTramite(new Timestamp(System.currentTimeMillis()));
                    FachadaPersistencia.getInstance().guardar(tramiteEncontrado);
                    System.out.println(tramiteEncontrado.getFechaHoraBajaTramite());
                    exito = true; // Indica que la operación fue exitosa
                } else {
                    throw new TramiteException("No es posible anular el trámite n°:" + nroTramite + " ya que no está en estado 'iniciado'.");
                }
            } else {
                throw new TramiteException("El trámite n°:" + nroTramite + " ya fue dado de baja.");
            }
        } else {
            throw new TramiteException("El número de trámite debe ser mayor que 0.");
        }
    } catch (TramiteException e) {
        System.err.println("Error en la anulación del trámite: " + e.getMessage());
        throw e;
    } catch (Exception e) {
        System.err.println("Error inesperado: " + e.getMessage());
        throw new TramiteException("Ocurrió un error inesperado al anular el trámite.");
    } finally {
        if (exito) {
            FachadaPersistencia.getInstance().finalizarTransaccion(); 
            
        } else {
            FachadaPersistencia.getInstance().finalizarTransaccion(); 
            }
        }
    }

}
