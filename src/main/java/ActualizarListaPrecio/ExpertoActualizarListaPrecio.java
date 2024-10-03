package ActualizarListaPrecio;

import ActualizarListaPrecio.dtos.DTOListaPrecio;
import ActualizarListaPrecio.dtos.DTOListaPrecioExportar;
import ActualizarListaPrecio.dtos.DTOListaPrecioImportar;
import entidades.Cliente;
import entidades.ListaPrecio;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author arode
 */
public class ExpertoActualizarListaPrecio {

    List<DTOListaPrecio> obtenerListaPrecio(int codigo, String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void importarListaPrecio(DTOListaPrecioImportar dtoListaPrecioImportar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void exportarListaPrecio(DTOListaPrecioExportar dtoListaPrecioExportar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void darDeBajaListaPrecio(int codigo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

/*
public class ExpertoABMCliente {
     private List<DetalleClienteDTO> clientes = new ArrayList<>();
    
    public void eliminarCliente(int cuitCliente) throws Exception {
        // Lógica para eliminar un cliente
        for (DetalleClienteDTO cliente : clientes) {
            if (cliente.getCuitCliente() == cuitCliente) {
                clientes.remove(cliente);
                return;
            }
        }
        throw new Exception("Cliente no encontrado");
    }
    
    public DetalleClienteDTO obtenerDetalleCliente(int cuitCliente) {
        // Lógica para obtener el detalle del cliente
        for (DetalleClienteDTO cliente : clientes) {
            if (cliente.getCuitCliente() == cuitCliente) {
                return cliente;
            }
        }
        return null;
    }
    
    public List<ListaClienteDTO> obtenerListaCliente(){
        //lista que devuelve el metodo
        List<ListaClienteDTO> listaClienteDTOs = new ArrayList<>();
        
        //busqueda buscar("Cliente", "")
        List<DTOCriterio> lCriterio=new ArrayList<>();
        //DTOCriterio unCriterio=new DTOCriterio(); Esto se pone asi si necesito utilizar criterio para buscar
         //Obtengo la lista de OBJETOS(clientes) desde la fachada de persistencia    
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("Cliente", lCriterio);
         
         for(Object x : objetoList){
             Cliente cliente = (Cliente) x;
             ListaClienteDTO listaClienteDTO = new ListaClienteDTO();
                listaClienteDTO.setCuitCliente(cliente.getCuitCliente());
                listaClienteDTO.setNombreApellidoCliente(cliente.getNombreApellidoCliente());
                listaClienteDTO.setFechaHoraBajaCliente(cliente.getFechaHoraBajaCliente());
                listaClienteDTOs.add(listaClienteDTO);
         }
         
         
        return listaClienteDTOs;
        
        
    }
     public void modificarCliente(int cuitCliente)throws Exception {
         //Logica para modificar cliente
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
//    public List<ListaClienteDTO> obtenerListaCliente(int cuitClienteFiltro, String nombreApellidoClienteFiltro) {
//        // Lógica para obtener la lista de clientes
//        List<ListaClienteDTO> listaClientes = new ArrayList<>();
//        for (DetalleClienteDTO cliente : clientes) {
//            if ((cuitClienteFiltro == 0 || cliente.getCuitCliente() == cuitClienteFiltro) &&
//                (nombreApellidoClienteFiltro == null || cliente.getNombreApellidoCliente().contains(nombreApellidoClienteFiltro))) {
//                ListaClienteDTO listaCliente = new ListaClienteDTO();
//                listaCliente.setNombreApellidoCliente(cliente.getNombreApellidoCliente());
//                listaCliente.setCuitCliente(cliente.getCuitCliente());
//                listaCliente.setFechaHoraBajaCliente(cliente.getFechaHoraBajaCliente());
//                listaClientes.add(listaCliente);
//            }
//        }
//        return listaClientes;
//    }

   
}
-----------------------------------------------------------------------------------------------------------------------------------------------


public class ExpertoRC {
    
    
            
    public List<TramitesConsultorDTO> obtenerTramitesConsultor(Timestamp fechaFiltro,String nombreTipoTramiteFiltro, String estadoFiltro){
        
   
          //arreglarlo
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(fechaFiltro != null){
            DTOCriterio fechaCriterio=new DTOCriterio();
            fechaCriterio.setAtributo("fechaHoraCargaTramite");
            fechaCriterio.setOperacion("<");
            fechaCriterio.setValor(fechaFiltro);
            lCriterio.add(fechaCriterio);
            //List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        }
        if(nombreTipoTramiteFiltro.trim().length()>0)
        {
            
            
            DTOCriterio criterioTT = new DTOCriterio();

            criterioTT.setAtributo("nombreTipoTramite");
            criterioTT.setOperacion("like");
            criterioTT.setValor(nombreTipoTramiteFiltro);
            lCriterio.add(criterioTT);

            TipoTramite tipoTramiteEncontrado = null;

            List ltipoTramites = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
            if (!ltipoTramites.isEmpty()) {
                tipoTramiteEncontrado = (TipoTramite) ltipoTramites.get(0);
            }

            lCriterio.clear();
            criterioTT.setAtributo("tipoTramite"); 
            criterioTT.setOperacion("=");
            criterioTT.setValor(tipoTramiteEncontrado);

            lCriterio.add(criterioTT);
           
            } 
  
        
        if(estadoFiltro.trim().length()>0)
        {    
            DTOCriterio criterioET = new DTOCriterio();

            criterioET.setAtributo("nombreEstadoTramite");
            criterioET.setOperacion("like");
            criterioET.setValor(estadoFiltro);
            lCriterio.add(criterioET);

            EstadoTramite estadoTramiteEncontrado = null;

            List lestTramites = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio);
            if (!lestTramites.isEmpty()) {
                estadoTramiteEncontrado = (EstadoTramite) lestTramites.get(0);
            }

            lCriterio.clear();
            criterioET.setAtributo("estadoTramite"); 
            criterioET.setOperacion("=");
            criterioET.setValor(estadoTramiteEncontrado);

            lCriterio.add(criterioET);
            
        }

        List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        List<TramitesConsultorDTO> tramitesConsultorResultado = new ArrayList<>();
        for (Object x : objetoList) {
            Tramite tramite = (Tramite) x;
           
            TramitesConsultorDTO tramitesConsultorDTO = new TramitesConsultorDTO();
            tramitesConsultorDTO.setNroTramite(tramite.getNroTramite());
            tramitesConsultorDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            tramitesConsultorDTO.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            tramitesConsultorDTO.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            tramitesConsultorDTO.setCuitCliente(tramite.getCliente().getCuitCliente());
            tramitesConsultorDTO.setLegajoConsultor(tramite.getConsultor().getLegajoConsultor());
            tramitesConsultorDTO.setNombreConsultor(tramite.getConsultor().getNombreApellidoConsultor());
            tramitesConsultorResultado.add(tramitesConsultorDTO);
        }
        return tramitesConsultorResultado;
        
       
    
        
    }
    

    
    public TramiteParticularDTO obtenerTramiteParticular(int nroTramiteFiltro){
        
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        
        if(nroTramiteFiltro>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        Tramite tramiteEncontrado = (Tramite) FachadaPersistencia.getInstance().buscar("Tramite", lCriterio).get(0);

        
        TramiteParticularDTO tramiteParticularDTO = new TramiteParticularDTO();
            tramiteParticularDTO.setCodTipoTramite(tramiteEncontrado.getTipoTramite().getCodTipoTramite());
            tramiteParticularDTO.setFechaHoraFinEntregaDoc(tramiteEncontrado.getFechaHoraFinEntregaDocumentacion());
            tramiteParticularDTO.setNombreApellidoCliente(tramiteEncontrado.getCliente().getNombreApellidoCliente());
            tramiteParticularDTO.setPrecioTipoTramite(2500); //temporalmente
            List <TramiteEstado> tramEstados = tramiteEncontrado.getTramiteEstadoList();
            TramiteEstado tramiteEstadoMasReciente = tramEstados.stream()
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
            tramiteParticularDTO.setObservaciones(tramiteEstadoMasReciente.getObservacionTramiteEstado());
         
         List<DTOCriterio> lCriterio2=new ArrayList<DTOCriterio>();
        
        //List<TramiteEstado> tramitesEst =tramiteEncontrado.getTramiteEstadoList();
        List tramitesEst = FachadaPersistencia.getInstance().buscar("TramiteEstado", lCriterio2);
        for (Object x : tramitesEst) {
            TramiteEstado tramiteEstado = (TramiteEstado) x;
            HistoricoDTO historicoDTO = new HistoricoDTO();
            historicoDTO.setNroLineaTramiteEstado(tramiteEstado.getNroLineaTramiteEstado());
            historicoDTO.setCodEstadoTramite(tramiteEstado.getEstadoTramite().getCodEstadoTramite());
            historicoDTO.setFechaHoraBajaTramiteEstado(tramiteEstado.getFechaHoraBajaTramiteEstado());
            historicoDTO.setFechaHastaTramiteEstado(tramiteEstado.getFechaHoraHastaTramiteEstado());
            historicoDTO.setObservaciones(tramiteEstado.getObservacionTramiteEstado());
            historicoDTO.setNombreEstadoTramite(tramiteEstado.getEstadoTramite().getNombreEstadoTramite());
            
            tramiteParticularDTO.addHistoricoDTOList(historicoDTO);        
        }
        return tramiteParticularDTO;
    }
    
//    public List<HistoricoDTO> etapas(int nroTramite){
//
//        return historicoDTOResultado;
//    }
//    
    public void anularUltimo(int nroTramite) throws exceptionRC{
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List <DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("nroTramite");
        dto.setOperacion("=");
        dto.setValor(nroTramite);
        
        criterioList.add(dto);
        
        Tramite tramiteEncontrado =(Tramite) FachadaPersistencia.getInstance().buscar("Tramite", criterioList).get(0);
        List <TramiteEstado> tramitesEstados=tramiteEncontrado.getTramiteEstadoList();
        //busca el ultimo para anular
        TramiteEstado tramiteEstadoMasReciente = tramitesEstados.stream()
                .filter(tramiteEstado -> tramiteEstado.getFechaHoraBajaTramiteEstado() == null)
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
        //lo anula poniendole la fecha de baja y hasta
        tramiteEstadoMasReciente.setFechaHoraBajaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramiteEstadoMasReciente.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        //verificacion que no sea el primero
        int nroLineaTEA=tramiteEstadoMasReciente.getNroLineaTramiteEstado();
        if(nroLineaTEA==1){
            System.out.println("No se puede anular el estado Iniciado");
        }
        
        Timestamp fechaFinTram = tramiteEncontrado.getFechaHoraFinTramite();
        if(fechaFinTram != null){
           tramiteEncontrado.setFechaHoraFinTramite(null);
        }
        //busca el anterior al anulado
        tramiteEncontrado.getTramiteEstadoList();
        TramiteEstado tramiteEstadoAnterior = tramitesEstados.stream()
                .filter(tramiteEstado -> tramiteEstado.getFechaHoraBajaTramiteEstado() == null)
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
        
        tramiteEstadoAnterior.setFechaHoraHastaTramiteEstado(null);
        
        FachadaPersistencia.getInstance().guardar(tramiteEstadoMasReciente);
        FachadaPersistencia.getInstance().guardar(tramiteEstadoAnterior);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
            
    public TramiteParticularDTO cambiarEstadoTramite (TransicionDTO tdto, int nroTramite){
        List <DTOCriterio> criterioList = new ArrayList<>();
        //busco tramite x nro
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("nroTramite");
        dto.setOperacion("=");
        dto.setValor(nroTramite);
        
        criterioList.add(dto);
        
        Tramite tramiteEncontrado =(Tramite) FachadaPersistencia.getInstance().buscar("Tramite", criterioList).get(0);
        //le pido el estado tramite
        EstadoTramite estadoTramite=tramiteEncontrado.getEstadoTramite();
        int codET = estadoTramite.getCodEstadoTramite();
        
        List <TramiteEstado> tramitesEstados=tramiteEncontrado.getTramiteEstadoList();
        TramiteEstado tramiteEstadoMasReciente = tramitesEstados.stream()
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
        tramiteEstadoMasReciente.getFechaHoraHastaTramiteEstado();
        tramiteEstadoMasReciente.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        
        TramiteEstado tramiteEstadoNuevo = new TramiteEstado();
            tramiteEstadoNuevo.setFechaHoraDesdeTramiteEstado(tramiteEstadoMasReciente.getFechaHoraHastaTramiteEstado());
            tramiteEstadoNuevo.setFechaHoraHastaTramiteEstado(null);
            tramiteEstadoNuevo.setFechaHoraBajaTramiteEstado(null);
            tramiteEstadoNuevo.setObservacionTramiteEstado(tdto.getObservacion());
            tramiteEncontrado.addTramiteEstadoList(tramiteEstadoNuevo);
        //buscar el estado ingresado en el dto
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        
          DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codEstadoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(tdto.getCodEstadoTramite());
            lCriterio.add(unCriterio);
          
          DTOCriterio unCriterio2=new DTOCriterio();
            unCriterio2.setAtributo("nombreEstadoTramite");
            unCriterio2.setOperacion("=");
            unCriterio2.setValor(tdto.getNombreEstadoTramite());
            lCriterio.add(unCriterio2);
        
        EstadoTramite estadoTramiteEncontrado = (EstadoTramite) FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio).get(0);
        tramiteEstadoNuevo.setEstadoTramite(estadoTramiteEncontrado);
        tramiteEncontrado.setEstadoTramite(estadoTramiteEncontrado);
            
         
        
        return null;
    }
    
    
    
            
    public NuevoEstadoDTO nuevoEstado(int nroTramite){
        List<EstadoActualDTO> dtoEA=new ArrayList<>();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(nroTramite>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramite);
            lCriterio.add(unCriterio);
        }
        Tramite tramite = (Tramite) FachadaPersistencia.getInstance().buscar("Tramite", lCriterio).get(0);
        
        EstadoTramite estadoTramite=tramite.getEstadoTramite();
        int codEstadoTramite = estadoTramite.getCodEstadoTramite();
        
        Version version = tramite.getVersion();
        List<TransicionEstado> transicionesEstado = version.getTransicionEstadoList();
        
        NuevoEstadoDTO estadoActualDTO = new NuevoEstadoDTO();
        estadoActualDTO.setNombreApellidoCliente(tramite.getCliente().getNombreApellidoCliente());
        estadoActualDTO.setNroTramite(tramite.getNroTramite());
        estadoActualDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
        for(TransicionEstado transicionEstado : transicionesEstado){
            EstadoTramite estadoTramiteActual = transicionEstado.getEstadoActual();
            
            //verificacion de que los codigos sean iguales
            if (!estadoTramiteActual.equals(estadoTramite)) {
            throw new RuntimeException("La instancia de EstadoTramite no coincide");
            }
            
            String nombreEA= estadoTramiteActual.getNombreEstadoTramite();
            
            estadoActualDTO.setNombreEstadoTramiteActual(nombreEA);
           
            
            //destinosPosibles
            
            List<EstadoTramite> etDestinos = transicionEstado.getEstadosDestino();
            
            for (EstadoTramite etDestino : etDestinos) {
                    // Crear DTODestinosPosibles con nombreEstadoTramiteDestino y codEstadoTramite
                    DestinosPosiblesDTO destinosDTO = new DestinosPosiblesDTO();
                    destinosDTO.setNombreEstadoTramiteDestino(etDestino.getNombreEstadoTramite());
                    destinosDTO.setCodEstadoTramite(etDestino.getCodEstadoTramite());

                    // Añadir DTODestinosPosibles a DTOEstadoActual
                    
                    estadoActualDTO.addDestinosPosiblesList(destinosDTO);
            }
        }
        
        return estadoActualDTO;
    }
    
        public TramitesConsultorDTO buscarTramiteConsultor(int nroTramiteFiltro){
         List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(nroTramiteFiltro>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        
        //List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        Tramite tramite = (Tramite) FachadaPersistencia.getInstance().buscar("Tramite", lCriterio).get(0);
        
        TramitesConsultorDTO tramitesConsultorResultado = new TramitesConsultorDTO();
        
            tramitesConsultorResultado.setNroTramite(tramite.getNroTramite());
            tramitesConsultorResultado.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
            tramitesConsultorResultado.setFechaHoraCargaTramite(tramite.getFechaHoraCargaTramite());
            tramitesConsultorResultado.setNombreEstadoTramite(tramite.getEstadoTramite().getNombreEstadoTramite());
            tramitesConsultorResultado.setCuitCliente(tramite.getCliente().getCuitCliente());
    
            
        
        return tramitesConsultorResultado;
    }
            

    
}
