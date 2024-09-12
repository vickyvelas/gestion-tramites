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
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(codigo>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("codigo");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codigo);
            lCriterio.add(unCriterio);
        }
        if(nombre.trim().length()>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("nombre");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombre);
            lCriterio.add(unCriterio);
        }
        List objetoList = FachadaPersistencia.getInstance().buscar("Estado", lCriterio);
        List<DTOVersionHistorial> versionesResultado = new ArrayList<>();
        for (Object x : objetoList) {
            Version version = (Version) x;
            DTOVersionHistorial versionDTO = new DTOVersionHistorial();
            versionDTO.setCodVersion(version.getCodVersion());
            versionDTO.setFechaBajaVersion(version.getFechaHoraBajaVersion());
            versionDTO.setFechaDesdeVersion(version.getFechaHoraDesdeVersion());
            versionDTO.setFechaHastaVersion(version.getFechaHoraHastaVersion());
            TipoTramite tipoTramiteDTO = new TipoTramite();
            tipoTramiteDTO = version.getTipoTramite();
            versionDTO.setCodTipoTramite(tipoTramiteDTO.getCodTipoTramite());
            versionDTO.setNombreTipoTramite(tipoTramiteDTO.getNombreTipoTramite());
            versionesResultado.add(versionDTO);
        }
        return versionesResultado;
    }

    public void agregarVersion(DTOVersionInput nuevaVersionDTO) throws VersionException{
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        //buscar('Version','codigo='+nuevaVersionDTO.getCodigo() and fechaBaja=null' ) 
        
        List<DTOCriterio> criterioList = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(nuevaVersionDTO.getCodVersion());
        criterioList.add(dto);
        
        DTOCriterio dto2  = new DTOCriterio();

        dto2.setAtributo("fechaHoraBajaVersion");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        
        List LVersion=FachadaPersistencia.getInstance().buscar("Version", criterioList);
        System.out.println("Tamaño:"+LVersion.size());   
        if(LVersion.size()> 0)
        {
                throw new VersionException("El código de estado ya existe");
        }
        else
        //acá temdía que ir la validacion de las fechas y de los estados. Es decir todas las validaciones del editar
        {
        Version version = new Version();
        version.setCodVersion(nuevaVersionDTO.getCodVersion());
        version.setFechaHoraDesdeVersion(nuevaVersionDTO.getFechaHoraDesdeVersion());
        version.setFechaHoraHastaVersion(nuevaVersionDTO.getFechaHoraHastaVersion());
        version.setFechaHoraBajaVersion(null);
        //Aca se guarda la que creemos antes para la verificación
        //version.setTransicionEstadoList(nuevaVersionDTO.getEstadosActuales()); 
        //busco si existe el tipo tramite, se debe hacer arriva
        List<DTOCriterio> criterioList2 = new ArrayList<>();
        
        DTOCriterio dto3 = new DTOCriterio();
        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(nuevaVersionDTO.getCodTipoTramite());
        criterioList.add(dto);
        
        List LTipoTramite=FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);
        System.out.println("Tamaño:"+LTipoTramite.size());   
        if(LTipoTramite.size() > 0)
        {
               throw new VersionException("El TipoTramite NO existe");
        }
        else{
        TipoTramite tipoTramite = new TipoTramite();
        tipoTramite = (TipoTramite) LTipoTramite.getFirst();
        version.setTipoTramite(tipoTramite);
        } 
        
        FachadaPersistencia.getInstance().guardar(version);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }

    
    public void anularVersion(int codVersion, int codTT) throws VersionException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(codigo);

        criterioList.add(dto);
        //Aca entraría la condición de la fecha desde. la de mayor fecha desde
        //DTOCriterio dto2 = new DTOCriterio();

        //dto2.setAtributo("fechaHoraDesdeVersion");
       // dto2.setOperacion("=");
        //dto2.setValor(null);

     // criterioList.add(dto2);


        Version versionEncontrado = (Version) FachadaPersistencia.getInstance().buscar("Version", criterioList).get(0);


        versionEncontrado.setFechaHoraBajaVersion(new Timestamp(System.currentTimeMillis()));

        
        //ACa tambien tenco q modificar la antenúltima
        FachadaPersistencia.getInstance().guardar(versionEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}
