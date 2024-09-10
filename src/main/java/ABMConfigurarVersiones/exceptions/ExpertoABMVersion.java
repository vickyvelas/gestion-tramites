/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.exceptions;

import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import entidades.TipoTramite;
import entidades.Version;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author julie
 */
public class ExpertoABMVersion {
     public List<DTOVersionHistorial> buscarVersionHistorial(int codigo,String nombre){
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

    
    public void darDeBajaVersion(int codigo) throws VersionException {
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
