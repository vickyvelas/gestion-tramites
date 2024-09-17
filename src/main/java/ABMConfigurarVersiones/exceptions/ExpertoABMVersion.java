/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.exceptions;

import ABMConfigurarVersiones.dtos.DTOCodDestino;
import ABMConfigurarVersiones.dtos.DTOEstadoActualInput;
import ABMConfigurarVersiones.dtos.DTOEstadoActualOutput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoInput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoOutput;
import ABMConfigurarVersiones.dtos.DTOEstadoPosible;
import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.dtos.DTOVersionesDeTipo;
import ABMConfigurarVersiones.dtos.DTOVersionyEstados;
import entidades.EstadoTramite;
import entidades.TipoTramite;
import entidades.TransicionEstado;
import entidades.Version;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author julie
 */
public class ExpertoABMVersion {
    public List<DTOVersionesDeTipo> filtroTablaVersiones(int codtt, String nombrett){
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(codtt>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codtt);
            lCriterio.add(unCriterio);
        }
        if(nombrett.trim().length()>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nombreTipoTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombrett);
            lCriterio.add(unCriterio);
        }
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        List<DTOVersionesDeTipo> lisResultado = new ArrayList<>();
        for (Object x : objetoList) {
            TipoTramite tt = (TipoTramite)x;
            DTOVersionesDeTipo dtolist = new DTOVersionesDeTipo();
            dtolist.setCodTipoTramite(tt.getCodTipoTramite());
            dtolist.setNombreTipoTramite(tt.getNombreTipoTramite());
            dtolist.setFechaHoraBajaTipoTramite(tt.getFechaHoraBajaTipoTramite());
            
            List<DTOCriterio> lCriterio2 =new ArrayList<DTOCriterio>();
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("TipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(tt.toString());
            lCriterio.add(unCriterio);
            List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio2);
            
            //Selecciono la de mayor fecha desde
            List<Version> listversion = (List<Version>) objetoList2;
            Optional<Version> ultimaVersion = listversion.stream().max(Comparator.comparing(Version::getFechaHoraDesdeVersion)); 
            if (ultimaVersion.isPresent()){
            Version v1 = ultimaVersion.get();     
            dtolist.setCodVersion(v1.getCodVersion());
            dtolist.setFechaHoraDesdeVersion(v1.getFechaHoraDesdeVersion());
            }
            else{
                System.out.println("No esxisten Versiones asociadas");
            }
            lisResultado.add(dtolist);
        }
        return lisResultado;
    }
    
    public List<DTOVersionesDeTipo> listarTablaVersionesDeTipos(){
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);

        List<DTOVersionesDeTipo> dtoLista = new ArrayList();
        
        if (objetoList.size()>0){
            for (Object x : objetoList) {
            TipoTramite tt = (TipoTramite) x;
            DTOVersionesDeTipo tipotramiteyV = new DTOVersionesDeTipo();
            tipotramiteyV.setCodTipoTramite(tt.getCodTipoTramite());
            tipotramiteyV.setNombreTipoTramite(tt.getNombreTipoTramite());
            tipotramiteyV.setFechaHoraBajaTipoTramite(tt.getFechaHoraBajaTipoTramite());
            
            List<DTOCriterio> lCriterio2 =new ArrayList<DTOCriterio>();
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("TipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(tt.toString());
            lCriterio.add(unCriterio);
            List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio2);
            
            //Selecciono la de mayor fecha desde
            List<Version> listversion = (List<Version>) objetoList2;
            Optional<Version> ultimaVersion = listversion.stream().max(Comparator.comparing(Version::getFechaHoraDesdeVersion)); 
            if (ultimaVersion.isPresent()){
            Version v1 = ultimaVersion.get();     
            tipotramiteyV.setCodVersion(v1.getCodVersion());
            tipotramiteyV.setFechaHoraDesdeVersion(v1.getFechaHoraDesdeVersion());
            }
            else{
                System.out.println("No esxisten Versiones asociadas");
            }
            dtoLista.add(tipotramiteyV);
            }
        }
        else{
        System.out.println("No esxisten TiposTramites creados");
        }  
        return dtoLista;
    }
    
    public DTOVersionOutput verVersion (int codTipoTramite, int codVersion) throws VersionException{ 
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>(); 
        DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codTipoTramite);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio); 
        if(objetoList.size()<= 0)
        {
                throw new VersionException("El TipoTramite NO existe");
        }
        TipoTramite tt = (TipoTramite) objetoList;
        
        lCriterio.clear();
        DTOCriterio Criterio1=new DTOCriterio();
            Criterio1.setAtributo("TipoTramite");
            Criterio1.setOperacion("=");
            Criterio1.setValor(tt.toString());
            lCriterio.add(Criterio1);
        DTOCriterio Criterio2=new DTOCriterio();
            Criterio2.setAtributo("codVersion");
            Criterio2.setOperacion("=");
            Criterio2.setValor(codVersion);
            lCriterio.add(Criterio2);
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio);
         if(objetoList2.size()<= 0)
        {
                throw new VersionException("La Version NO existe");
        }
        Version v = (Version)objetoList2;
        
        DTOVersionOutput dtoVersion = new DTOVersionOutput ();
        
        dtoVersion.setCodTipoTramite(tt.getCodTipoTramite());
        dtoVersion.setCodVersion(v.getCodVersion());
        dtoVersion.setFechaHoraDesdeVersion(v.getFechaHoraDesdeVersion());
        dtoVersion.setFechaHoraHastaVersion(v.getFechaHoraHastaVersion());
        
        List<TransicionEstado> transiciones = v.getTransicionEstadoList();
        List<DTOEstadoActualOutput> listEA = new ArrayList();
        
        for(TransicionEstado x : transiciones){
        DTOEstadoActualOutput estadoActual = new DTOEstadoActualOutput();
        estadoActual.setNroTransicion(x.getNroTransicionEstado());
        EstadoTramite eA = x.getEstadoActual();
        estadoActual.setCodEstadoActual(eA.getCodEstadoTramite());
        estadoActual.setNombreEstadoTramite(eA.getNombreEstadoTramite());
        
        List<EstadoTramite> eDestinos = x.getEstadosDestino();
        for (EstadoTramite eD : eDestinos){
        DTOEstadoDestinoOutput estadoDestino = new DTOEstadoDestinoOutput();
        estadoDestino.setCodEstadoDestino(eD.getCodEstadoTramite());
        estadoDestino.setNombreEstadoTramite(eD.getNombreEstadoTramite());
        estadoActual.addEstadosDestino(estadoDestino);
        }
        listEA.add(estadoActual);
        }
        
        dtoVersion.setEstadosActuales(listEA);
        
        return dtoVersion;
    }
    
    public DTOVersionyEstados buscarVersionEditar (int codTipoTramite) throws VersionException {
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>(); 
        DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codTipoTramite);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio); 
        if(objetoList.size()<= 0)
        {
                throw new VersionException("El TipoTramite NO existe");
        }
        TipoTramite tt = (TipoTramite) objetoList;
        
        lCriterio.clear();
        DTOCriterio Criterio1=new DTOCriterio();
            Criterio1.setAtributo("TipoTramite");
            Criterio1.setOperacion("=");
            Criterio1.setValor(tt.toString());
            lCriterio.add(Criterio1);
        DTOCriterio Criterio2=new DTOCriterio();
            Criterio2.setAtributo("fechaHoraBajaVersion");
            Criterio2.setOperacion("=");
            Criterio2.setValor(null);
            lCriterio.add(Criterio2);
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio);
        
//Selecciono la de mayor fecha desde
        List<Version> listversion = (List<Version>) objetoList2;
        Optional<Version> ultimaVersion = listversion.stream().max(Comparator.comparing(Version::getFechaHoraDesdeVersion)); 
        if (!(ultimaVersion.isPresent())){
            throw new VersionException("NO existen Versiones Activas");
        }
        
        Version v = (Version)ultimaVersion.get();
        
        DTOVersionOutput dtoVersion = new DTOVersionOutput ();
        
        dtoVersion.setCodTipoTramite(tt.getCodTipoTramite());
        dtoVersion.setCodVersion(v.getCodVersion());
        dtoVersion.setFechaHoraDesdeVersion(v.getFechaHoraDesdeVersion());
        dtoVersion.setFechaHoraHastaVersion(v.getFechaHoraHastaVersion());
         
        List<TransicionEstado> transiciones = v.getTransicionEstadoList();
        List<DTOEstadoActualOutput> listEA = new ArrayList();
        
        for(TransicionEstado x : transiciones){
        DTOEstadoActualOutput estadoActual = new DTOEstadoActualOutput();
        estadoActual.setNroTransicion(x.getNroTransicionEstado());
        EstadoTramite eA = x.getEstadoActual();
        estadoActual.setCodEstadoActual(eA.getCodEstadoTramite());
        estadoActual.setNombreEstadoTramite(eA.getNombreEstadoTramite());
        
        List<EstadoTramite> eDestinos = x.getEstadosDestino();
        for (EstadoTramite eD : eDestinos){
        DTOEstadoDestinoOutput estadoDestino = new DTOEstadoDestinoOutput();
        estadoDestino.setCodEstadoDestino(eD.getCodEstadoTramite());
        estadoDestino.setNombreEstadoTramite(eD.getNombreEstadoTramite());
        estadoActual.addEstadosDestino(estadoDestino);
        }
        listEA.add(estadoActual);
        }
        
        dtoVersion.setEstadosActuales(listEA);
        
        DTOVersionyEstados dtofinal = new DTOVersionyEstados();
        dtofinal.setVersion(dtoVersion);
        
        lCriterio.clear();
        DTOCriterio Criterio=new DTOCriterio();
            Criterio.setAtributo("fechaHoraBajaEstadoTramite");
            Criterio.setOperacion("=");
            Criterio.setValor(null);
            lCriterio.add(Criterio);
        List objetoList3 = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio);
        if(objetoList3.size()<= 0)
        {
                throw new VersionException("No esxisten EstadosTramites vigentes");
        }
        
        for(Object x : objetoList3){
            EstadoTramite et = (EstadoTramite) x;
            DTOEstadoPosible ep = new DTOEstadoPosible();
            ep.setCodEstadoTramite(et.getCodEstadoTramite());
            ep.setNombreEstadoTramite(et.getNombreEstadoTramite());
            dtofinal.addEstadosPosibles(ep);
        }
        
        
        return dtofinal;
    }
    
    public void nuevaVersion (DTOVersionInput datosingresados)throws VersionException{
    
    FachadaPersistencia.getInstance().iniciarTransaccion();
        
    //Creo nueva version
        Version nuevaV = new Version();
        nuevaV.setCodVersion(datosingresados.getCodVersion());
        nuevaV.setFechaHoraDesdeVersion(datosingresados.getFechaHoraDesdeVersion());
        nuevaV.setFechaHoraHastaVersion(datosingresados.getFechaHoraHastaVersion());
        nuevaV.setFechaHoraBajaVersion(null);
        //busco el TT
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>(); 
        DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(datosingresados.getCodTipoTramite());
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio); 
        if(objetoList.size()<= 0)
        {
                throw new VersionException("El TipoTramite NO existe");
        }
        TipoTramite tt = (TipoTramite) objetoList;
        nuevaV.setTipoTramite(tt);
        
        List<DTOEstadoActualInput> EAingresados = datosingresados.getEstadosActuales();
        for (DTOEstadoActualInput x :EAingresados){
            TransicionEstado Transicion = new TransicionEstado();
            Transicion.setNroTransicionEstado(x.getNroTransicion());
            //busco es estado actual
            lCriterio.clear();
            DTOCriterio Criterio=new DTOCriterio();
            Criterio.setAtributo("codEstadoTramite");
            Criterio.setOperacion("=");
            Criterio.setValor(x.getCodEstadoActual());
            lCriterio.add(Criterio);
            List objetoList2 = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio); 
            if(objetoList2.size()<= 0)
            {
                throw new VersionException("El Estado Actual NO existe");
            }
            EstadoTramite eA = (EstadoTramite) objetoList2;
            Transicion.setEstadoActual(eA);
            //busco los estados destino
            List<EstadoTramite> eDestinos = new ArrayList();
            List<DTOEstadoDestinoInput> EDingresados = x.getEstadosDestino();
            for(DTOEstadoDestinoInput e : EDingresados){
                lCriterio.clear();
                DTOCriterio Criterio2=new DTOCriterio();
                Criterio2.setAtributo("codEstadoTramite");
                Criterio2.setOperacion("=");
                Criterio2.setValor(e.getCodEstadoDestino());
                lCriterio.add(Criterio2);
                List objetoList3 = FachadaPersistencia.getInstance().buscar("EstadoTramite", lCriterio); 
                if(objetoList3.size()<= 0)
                {
                    throw new VersionException("El Estado Actual NO existe");
                }
                EstadoTramite eD = (EstadoTramite) objetoList2;
                eDestinos.add(eD);
            }
            Transicion.setEstadosDestino(eDestinos);
            nuevaV.addTransicionEstadoList(Transicion);
        }
//Epuezo con las validaciones de fechas
    
        //buscar ultima version de ese TipoTramite
        
        lCriterio.clear();
        DTOCriterio Criterio1=new DTOCriterio();
            Criterio1.setAtributo("TipoTramite");
            Criterio1.setOperacion("=");
            Criterio1.setValor(tt.toString());
            lCriterio.add(Criterio1);
        DTOCriterio Criterio2=new DTOCriterio();
            Criterio2.setAtributo("fechaHoraBajaVersion");
            Criterio2.setOperacion("=");
            Criterio2.setValor(null);
            lCriterio.add(Criterio2);
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio); 
        //Selecciono la de mayor fecha desde
        List<Version> listversion = (List<Version>) objetoList2;
        Optional<Version> ultimaVersion = listversion.stream().max(Comparator.comparing(Version::getFechaHoraDesdeVersion)); 
        if (!(ultimaVersion.isPresent())){
            throw new VersionException("NO existen Versiones Activas");
        }       
        Version vultima = (Version)ultimaVersion.get();
       
        //Voy comparardo y tiro error fatal
        Timestamp FDNv = nuevaV.getFechaHoraDesdeVersion();
        Timestamp FDUv =vultima.getFechaHoraDesdeVersion();
        if(FDNv.before(FDUv)){
            throw new VersionException("Erro la version nueva esta antes que la última version. Existen versiones futuras");
            //FachadaPersistencia.getInstance().finalizarTransaccion();            
        }
        Timestamp FHUv = vultima.getFechaHoraHastaVersion();
        if(FDNv.after(FHUv)){
            throw new VersionException("Erro la version nueva esta despues de la última version. No puede haber huecos entre versiones");
            //FachadaPersistencia.getInstance().finalizarTransaccion();            
        }
        //Significa que fechaHoraDesdeVersion(ÚltimaVersion)< fechaHoraDesdeVersion(NuevaVersion)<=fechaHoraHastaVersion(ÚltimaVersion)
        Timestamp FHNv = nuevaV.getFechaHoraHastaVersion();
        if(FHNv.equals(FDNv)){
            throw new VersionException("Erro la version nueva debe tener distinta fecha desde y fehca hasta.");
            //FachadaPersistencia.getInstance().finalizarTransaccion(); 
        }
        if(FHNv.before(FDNv)){
            throw new VersionException("Erro la version nueva no puede tener una fecha desde > fehca hasta.");
            //FachadaPersistencia.getInstance().finalizarTransaccion(); 
        }
//cambio la ultima version:
        vultima.setFechaHoraHastaVersion(FDNv);

//Epuezo con las validaciones de las Tranciciones
        //Creo el dto para validar los destinos
        List<DTOCodDestino> listDestVerif = new ArrayList();
        List<TransicionEstado> transiciones = nuevaV.getTransicionEstadoList();
        for(TransicionEstado trans : transiciones){
        List<EstadoTramite> EDs = trans.getEstadosDestino();
        for(EstadoTramite ed : EDs){
            DTOCodDestino ced = new DTOCodDestino();
            ced.setCodDestino(ed.getCodEstadoTramite());
            listDestVerif.add(ced);
        }   
        }
        
        //Valido la primer transicion
        for(TransicionEstado transicion : transiciones){
            if(transicion.getNroTransicionEstado() == 1){
                EstadoTramite eA = transicion.getEstadoActual();
                String estadoInicial = "Iniciado";
                if(eA.getNombreEstadoTramite() == estadoInicial){
                    throw new VersionException("Error, la vercion debe tener como primer estado actual el Iniciado");
                //FachadaPersistencia.getInstance().finalizarTransaccion(); 
                }
                List<EstadoTramite> eDs = transicion.getEstadosDestino();
                for(EstadoTramite ed : eDs){
                    if(eA.getCodEstadoTramite()==ed.getCodEstadoTramite()){
                        throw new VersionException("Error, la vercion no puede tener estados recursivos");
                    //FachadaPersistencia.getInstance().finalizarTransaccion();     
                    }
                }
            }
            else{
                EstadoTramite eA = transicion.getEstadoActual();
                DTOCodDestino codeA = new DTOCodDestino();
                codeA.setCodDestino(eA.getCodEstadoTramite());
                if(!listDestVerif.contains(codeA)){
                    throw new VersionException("Error, la vercion debe tener estados actuales posibles de alcanzar");
                //FachadaPersistencia.getInstance().finalizarTransaccion(); 
                }
                List<EstadoTramite> eDs = transicion.getEstadosDestino();
                for(EstadoTramite ed : eDs){
                    if(eA.getCodEstadoTramite()==ed.getCodEstadoTramite()){
                        throw new VersionException("Error, la vercion no puede tener estados recursivos");
                    //FachadaPersistencia.getInstance().finalizarTransaccion();     
                    }
                }
            }
        }
        for(TransicionEstado transicion : transiciones){
        FachadaPersistencia.getInstance().guardar(transicion);            
        }
        FachadaPersistencia.getInstance().guardar(vultima);        
        FachadaPersistencia.getInstance().guardar(nuevaV);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    
    }
   
    public List<DTOVersionHistorial> historialVersiones(int codigoTT){
        
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List<DTOVersionHistorial> versionesResultado = new ArrayList<>();
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codigoTT);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        for (Object y : objetoList){
        TipoTramite tt = (TipoTramite) y;            
        
        lCriterio.clear();
            DTOCriterio Criterio=new DTOCriterio();
            Criterio.setAtributo("TipoTramite");
            Criterio.setOperacion("=");
            Criterio.setValor(tt.toString());
            lCriterio.add(Criterio);
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio);
        
            for (Object x : objetoList2){
            Version version = (Version) x;
            DTOVersionHistorial versionDTO = new DTOVersionHistorial();
            versionDTO.setCodVersion(version.getCodVersion());
            versionDTO.setFechaBajaVersion(version.getFechaHoraBajaVersion());
            versionDTO.setFechaDesdeVersion(version.getFechaHoraDesdeVersion());
            versionDTO.setFechaHastaVersion(version.getFechaHoraHastaVersion());            
            TipoTramite tipoTramiteDTO =version.getTipoTramite();
            versionDTO.setCodTipoTramite(tipoTramiteDTO.getCodTipoTramite());
            versionDTO.setNombreTipoTramite(tipoTramiteDTO.getNombreTipoTramite());
            versionesResultado.add(versionDTO);
            }            
        }
        return versionesResultado;
    }
    
    public void anularVersion(int codVersion, int codTT) throws VersionException {
        
        FachadaPersistencia.getInstance().iniciarTransaccion();

       List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codTipoTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codTT);
            lCriterio.add(unCriterio);
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        TipoTramite tt = (TipoTramite)objetoList;
        if(objetoList.size()<= 0)
        {
                throw new VersionException("El TipoTramite NO existe");
        }
        
        lCriterio.clear();
         DTOCriterio Criterio1=new DTOCriterio();
            Criterio1.setAtributo("TipoTramite");
            Criterio1.setOperacion("=");
            Criterio1.setValor(tt.toString());
            lCriterio.add(Criterio1);
        DTOCriterio Criterio2=new DTOCriterio();
            Criterio2.setAtributo("fechaHoraBajaVersion");
            Criterio2.setOperacion("=");
            Criterio2.setValor(null);
            lCriterio.add(Criterio2);
        DTOCriterio Criterio3=new DTOCriterio();
            Criterio3.setAtributo("codVersion");
            Criterio3.setOperacion("=");
            Criterio3.setValor(codVersion);
            lCriterio.add(Criterio3);
        List objetoList2 = FachadaPersistencia.getInstance().buscar("Version", lCriterio); 
        Version versionanula = (Version) objetoList2;
        if(objetoList.size()<= 0)
        {
                throw new VersionException("La version no se puede anular");
        }        
       //buscar ultima version de ese TipoTramite
        
        lCriterio.clear();
        DTOCriterio Cri=new DTOCriterio();
            Cri.setAtributo("TipoTramite");
            Cri.setOperacion("=");
            Cri.setValor(tt.toString());
            lCriterio.add(Cri);
        DTOCriterio Cri2=new DTOCriterio();
            Cri2.setAtributo("fechaHoraBajaVersion");
            Cri2.setOperacion("=");
            Cri2.setValor(null);
            lCriterio.add(Cri2);
        List objetoList3 = FachadaPersistencia.getInstance().buscar("Version", lCriterio); 
        //Selecciono la de mayor fecha desde
        List<Version> listversion = (List<Version>) objetoList3;
        Optional<Version> ultimaVersion = listversion.stream().max(Comparator.comparing(Version::getFechaHoraDesdeVersion)); 
        if (!(ultimaVersion.isPresent())){
            throw new VersionException("NO se encontró ultima version");
        }       
        Version vultima = (Version)ultimaVersion.get();
        
        int codvu = vultima.getCodVersion();
        int codva = versionanula.getCodVersion();
        if(codvu != codva){
            throw new VersionException("Existen versiones futuras. Solo se puede anular la última futura.");
        } 
        vultima.setFechaHoraBajaVersion(new Timestamp(System.currentTimeMillis()));
        FachadaPersistencia.getInstance().guardar(vultima);
        // la guardo antes para poder despues buscar la penultima como ultima
        //Cambio la antepenúltima version
        
        lCriterio.clear();
        DTOCriterio Cri1=new DTOCriterio();
            Cri1.setAtributo("TipoTramite");
            Cri1.setOperacion("=");
            Cri1.setValor(tt.toString());
            lCriterio.add(Cri1);
        DTOCriterio Cri3=new DTOCriterio();
            Cri3.setAtributo("fechaHoraBajaVersion");
            Cri3.setOperacion("=");
            Cri3.setValor(null);
            lCriterio.add(Cri3);
        List objetoList4 = FachadaPersistencia.getInstance().buscar("Version", lCriterio); 
        //Selecciono la de mayor fecha desde
        List<Version> listversion1 = (List<Version>) objetoList4;
        Optional<Version> ultimaVersion2 = listversion1.stream().max(Comparator.comparing(Version::getFechaHoraDesdeVersion)); 
        if (!(ultimaVersion2.isPresent())){
            throw new VersionException("NO se encontró ultima version");
        } 
        Version penulVer = (Version)ultimaVersion2.get();
        Timestamp FHUV = vultima.getFechaHoraHastaVersion();
        penulVer.setFechaHoraHastaVersion(FHUV);
        
        FachadaPersistencia.getInstance().guardar(penulVer);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}
