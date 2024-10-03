/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2;

import RC_2.dto.*;
import entidades.EstadoTramite;
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
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("fechaHoraCargaTramite");
            unCriterio.setOperacion("<");
            unCriterio.setValor(fechaFiltro);
            lCriterio.add(unCriterio);
            List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        }
        if(nombreTipoTramiteFiltro.trim().length()>0)
        {
            
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("tipoTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreTipoTramiteFiltro);
            lCriterio.add(unCriterio);
            //List objetoList2 = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
            
        }
        if(estadoFiltro.trim().length()>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("estadoTramite.nombreEstadoTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor(estadoFiltro);
            lCriterio.add(unCriterio);
            //List objetoList = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio);
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
//        
//        for (TramiteEstado tramiteEstado : tramiteEncontrado.getTramiteEstadoList()){
//           Timestamp fechaDesdeTE =  tramiteEstado.getFechaHoraDesdeTramiteEstado();
//           
//        }
        
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
         
               
        
        List<TramiteEstado> tramitesEst =tramiteEncontrado.getTramiteEstadoList();
        //List objetoList = FachadaPersistencia.getInstance().buscar("TramiteEstado", lCriterio);
        for (TramiteEstado tramiteEstado : tramitesEst) {
            //TramiteEstado tramiteEstado = (TramiteEstado) x;
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
