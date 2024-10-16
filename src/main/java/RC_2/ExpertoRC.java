/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2;

import RC_2.dto.*;
import entidades.DetallePrecioTipoTramite;
import entidades.EstadoTramite;
import entidades.ListaPrecio;
import entidades.TipoTramite;
import entidades.Tramite;
import entidades.TramiteEstado;
import entidades.TransicionEstado;
import entidades.Version;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author marti
 */
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
            
        }
        DTOCriterio fechaFinCriterio=new DTOCriterio();
            fechaFinCriterio.setAtributo("fechaHoraFinTramite");
            fechaFinCriterio.setOperacion( "=");
            fechaFinCriterio.setValor(null);
            lCriterio.add(fechaFinCriterio);
            
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
    

    
    public TramiteParticularDTO obtenerTramiteParticular(int nroTramiteFiltro) throws exceptionRC{
        
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
        
        if(tramiteEncontrado.getFechaHoraFinTramite()!= null){
            throw new exceptionRC("El trámite se encuentra finalizado");
            
        }
        
        TramiteParticularDTO tramiteParticularDTO = new TramiteParticularDTO();
            tramiteParticularDTO.setCodTipoTramite(tramiteEncontrado.getTipoTramite().getCodTipoTramite());
            tramiteParticularDTO.setFechaHoraFinEntregaDoc(tramiteEncontrado.getFechaHoraFinEntregaDocumentacion());
            tramiteParticularDTO.setNombreApellidoCliente(tramiteEncontrado.getCliente().getNombreApellidoCliente());
            //encontrar ultimo TE
            List <TramiteEstado> tramEstados = tramiteEncontrado.getTramiteEstadoList();
            TramiteEstado tramiteEstadoMasReciente = tramEstados.stream()
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
            
            tramiteParticularDTO.setObservaciones(tramiteEstadoMasReciente.getObservacionTramiteEstado());
            //setear precio
            List<DTOCriterio> lCriterioLP=new ArrayList<DTOCriterio>();
            List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterioLP);

            ListaPrecio listaPrecioActual = objetoList.stream()
                    .map(x -> (ListaPrecio) x) // Convertir cada objeto a ListaPrecio     
                    .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio)) // Obtener la lista con la fecha más reciente
                    .orElse(null); 
            List<DetallePrecioTipoTramite> detallesActuales = listaPrecioActual.getDetallePrecioTipoTramiteList();
            for(DetallePrecioTipoTramite det : detallesActuales){
                det.getTipoTramite();
                int codTTdet = det.getTipoTramite().getCodTipoTramite();
                int codTT = tramiteEncontrado.getTipoTramite().getCodTipoTramite();
                
                if(codTT == codTTdet){
                    tramiteParticularDTO.setPrecioTipoTramite(det.getPrecioTipoTramite());
                     
                }
                
            }
            
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
        //verifica q la fecha fin cel tramite
        Timestamp fechaFinTram = tramiteEncontrado.getFechaHoraFinTramite();
        if(fechaFinTram != null){
           tramiteEncontrado.setFechaHoraFinTramite(null);
        }
        //busca el anterior al anulado
        List<TramiteEstado> tramEstados = tramiteEncontrado.getTramiteEstadoList();
        
        TramiteEstado tramiteEstadoAnterior = tramEstados.stream()
                .filter(tramiteEstado -> tramiteEstado.getFechaHoraBajaTramiteEstado() == null)
                .max(Comparator.comparing(TramiteEstado::getFechaHoraDesdeTramiteEstado))
                .orElse(null);
        //EL PROBLEMA ES QUE DEVUELVE NULO
        tramiteEstadoAnterior.setFechaHoraHastaTramiteEstado(null);
        
        FachadaPersistencia.getInstance().guardar(tramiteEstadoMasReciente);
        FachadaPersistencia.getInstance().guardar(tramiteEstadoAnterior);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
            
    public void cambiarEstadoTramite (TransicionDTO tdto, int nroTramite){
        FachadaPersistencia.getInstance().iniciarTransaccion();
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
            tramiteEstadoNuevo.setNroLineaTramiteEstado((tramiteEstadoMasReciente.getNroLineaTramiteEstado())+1);
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
          
//          DTOCriterio unCriterio2=new DTOCriterio();
//            unCriterio2.setAtributo("nombreEstadoTramite");
//            unCriterio2.setOperacion("=");
//            unCriterio2.setValor(tdto.getNombreEstadoTramite());
//            lCriterio.add(unCriterio2);
        
        EstadoTramite estadoTramiteEncontrado = (EstadoTramite) FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio).get(0);
        tramiteEstadoNuevo.setEstadoTramite(estadoTramiteEncontrado);
        tramiteEncontrado.setEstadoTramite(estadoTramiteEncontrado);
            
         
        FachadaPersistencia.getInstance().guardar(tramiteEstadoNuevo);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
    
    
    
            
    public NuevoEstadoDTO nuevoEstado(int nroTramite) throws exceptionRC{
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
        
        EstadoTramite estadoTramiteActual=tramite.getEstadoTramite();
        int codEstadoTramite = estadoTramiteActual.getCodEstadoTramite();
        
        Version version = tramite.getVersion();
        List<TransicionEstado> transicionesEstado = version.getTransicionEstadoList();
        
        NuevoEstadoDTO estadoActualDTO = new NuevoEstadoDTO();
        estadoActualDTO.setNombreApellidoCliente(tramite.getCliente().getNombreApellidoCliente());
        estadoActualDTO.setNroTramite(tramite.getNroTramite());
        estadoActualDTO.setNombreTipoTramite(tramite.getTipoTramite().getNombreTipoTramite());
        //EstadoTramite estadoTramiteActual = tramite.getEstadoTramite();
        
        for(TransicionEstado transicionEstado : transicionesEstado){
            if(codEstadoTramite == transicionEstado.getEstadoActual().getCodEstadoTramite())
            {
            
                estadoActualDTO.setNombreEstadoTramiteActual(estadoTramiteActual.getNombreEstadoTramite());
                List<EstadoTramite> etDestinos = transicionEstado.getEstadosDestino();
                //CASO QUE LA LISTA ESTE VACIA
                if(etDestinos.size()<=0){
                    throw new exceptionRC("No hay probles transiciones");
                }
                for (EstadoTramite etDestino : etDestinos) {
                        // Crear DTODestinosPosibles con nombreEstadoTramiteDestino y codEstadoTramite
                        DestinosPosiblesDTO destinosDTO = new DestinosPosiblesDTO();
                        destinosDTO.setNombreEstadoTramiteDestino(etDestino.getNombreEstadoTramite());
                        destinosDTO.setCodEstadoTramite(etDestino.getCodEstadoTramite());

                        // Añadir DTODestinosPosibles a DTOEstadoActual

                        estadoActualDTO.addDestinosPosiblesList(destinosDTO);
                }
            }
//            SI DEVUELVE LISTA VACIA FIN AL TRAMITE, copiar lo de arriba antes dle bucle y el if de abajo
//            if(etDestinos==null){
//                tramite.setFechaHoraFinTramite(new Timestamp(System.currentTimeMillis()));
//                System.out.println("El tramite finalizo");
//            }
            //}
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
