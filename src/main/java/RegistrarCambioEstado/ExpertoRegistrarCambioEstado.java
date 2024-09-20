/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrarCambioEstado;
import RegistrarCambioEstado.dtos.TramitesConsultorDTO;
import DTO.D.TransicionDTO;
import RegistrarCambioEstado.dtos.TramiteParticularDTO;
import RegistrarCambioEstado.exceptions.cambioEstadoException;
import entidades.EstadoTramite;
import entidades.ListaPrecio;
import entidades.Tramite;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author marti
 */
public class ExpertoRegistrarCambioEstado {
        public List<TramitesConsultorDTO> buscarTramitesConsultor(int nroTramiteFiltro,String nombreTipoTramiteFiltro, String estadoFiltro){
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(nroTramiteFiltro>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nroTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(nroTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        if(nombreTipoTramiteFiltro.trim().length()>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nombreTipoTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreTipoTramiteFiltro);
            lCriterio.add(unCriterio);
        }
        if(estadoFiltro.trim().length()>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("estado");
            unCriterio.setOperacion("like");
            unCriterio.setValor(estadoFiltro);
            lCriterio.add(unCriterio);
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
    
    public TramiteParticularDTO buscarTramiteParticular(int nroTramiteFiltro){
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
            tramiteParticularDTO.setPrecioTipoTramite(1500);
        
        
        return tramiteParticularDTO;
    }

    public void cambioEstado(TransicionDTO transicionDTO) throws cambioEstadoException{
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dtoc = new DTOCriterio();
        dtoc.setAtributo("codEstadoTramite");
        dtoc.setOperacion("=");
        dtoc.setValor(transicionDTO.getCodEstadoTramite());
        
        criterioList.add(dtoc);
        List lEstado=FachadaPersistencia.getInstance().buscar("EstadoTramite", criterioList);
        
        //hay un if si el cod ingresado existe larga excepcion. Sino
        
        if(lEstado.size()>0){
            throw new cambioEstadoException("El codigo ya existe");
        }else{
            EstadoTramite estadoTramite = new EstadoTramite();
            estadoTramite.setCodEstadoTramite(transicionDTO.getCodEstadoTramite());
            estadoTramite.setNombreEstadoTramite(transicionDTO.getNombreEstadoTramite());
            estadoTramite.setFechaHoraBajaEstadoTramite(null);
            
            FachadaPersistencia.getInstance().guardar(estadoTramite);
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }
        
     
}
