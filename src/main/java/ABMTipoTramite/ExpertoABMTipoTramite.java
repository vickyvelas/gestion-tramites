package ABMTipoTramite;

import ABMTipoTramite.dtos.DTOModificarTipoTramite;
import ABMTipoTramite.dtos.DTOModificarTipoTramiteIn;
import ABMTipoTramite.dtos.DTONuevoTipoTramite;
import ABMTipoTramite.dtos.DTOTipoTramite;
import ABMTipoTramite.exceptions.ABMTipoTramiteException;
import entidades.TipoTramite;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;





public class ExpertoABMTipoTramite {

    static void agregarTipoTramite(DTONuevoTipoTramite DTONuevoTipoTramite) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<DTOTipoTramite> buscarTipoTramites(int codigo,String nombre){
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
        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);
        List<DTOTipoTramite> tipotramitesResultado = new ArrayList<>();
        for (Object x : objetoList) {
            TipoTramite tipotramite = (TipoTramite) x;
            DTOTipoTramite DTOtipotramite = new DTOTipoTramite();
            DTOtipotramite.setCodTipoTramite(tipotramite.getCodTipoTramite());
            DTOtipotramite.setNombreTipoTramite(tipotramite.getNombreTipoTramite());
            DTOtipotramite.setNombreCategoriaTramite(tipotramite.getNombreCategoriaTramite());
            DTOtipotramite.setFechaHoraBajaTipoTramite(tipotramite.getFechaHoraBajaTipoTramite());

            tipotramitesResultado.add(DTOtipotramite);
        }
        return tipotramitesResultado;
    }
        public void agregarABMTipoTramite(DTONuevoTipoTramite DTONuevoTipoTramite) throws ABMTipoTramiteException{
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        //buscar('Estado','codigo='+nuevoEstadoDTO.getCodigo() and fechaBaja=null' ) 
        
        List<DTOCriterio> criterioList = new ArrayList<>();
        
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(DTONuevoTipoTramite.getCodTipoTramite());
        criterioList.add(dto);
        
        DTOCriterio dto2  = new DTOCriterio();

        dto2.setAtributo("fechaBaja");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        
        List lEstado=FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);
        System.out.println("Tamaño:"+lEstado.size());   
        if(lEstado.size()> 0)
        {
                throw new ABMTipoTramiteException("El código de estado ya existe");
        }
        else
        {
        TipoTramite tipotramite = new TipoTramite();
        tipotramite.setCodTipoTramite( DTONuevoTipoTramite.getCodTipoTramite());
        tipotramite.setNombreTipoTramite(DTONuevoTipoTramite.getNombreTipoTramite());

        FachadaPersistencia.getInstance().guardar(tipotramite);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }

    public DTOModificarTipoTramite buscarTipoTramiteAModificar(int codigo){
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(codigo);

        criterioList.add(dto);

        TipoTramite tipotramiteEncontrado = (TipoTramite) FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList).get(0);

        DTOModificarTipoTramite DTOmodificartipotramite = new DTOModificarTipoTramite();
        DTOmodificartipotramite.setNombreTipoTramite(tipotramiteEncontrado.getNombreTipoTramite());
        DTOmodificartipotramite.setCodTipoTramite(tipotramiteEncontrado.getCodTipoTramite());
        return DTOmodificartipotramite;
    }

    public void modificarTipoTramite(DTOModificarTipoTramiteIn DTOModificarTipoTramiteIn){
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(DTOModificarTipoTramiteIn.getCodTipoTramite());

        criterioList.add(dto);

        TipoTramite tipotramiteEncontrado = (TipoTramite) FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList).get(0);

        tipotramiteEncontrado.setCodTipoTramite(DTOModificarTipoTramiteIn.getCodTipoTramite());
        tipotramiteEncontrado.setNombreTipoTramite(DTOModificarTipoTramiteIn.getNombreTipoTramite());

        FachadaPersistencia.getInstance().guardar(tipotramiteEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

    public void darDeBajaTipoTramite(int codigo) throws ABMTipoTramiteException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("codigo");
        dto.setOperacion("=");
        dto.setValor(codigo);

        criterioList.add(dto);

        TipoTramite tipotramiteEncontrado = (TipoTramite) FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList).get(0);

        criterioList.clear();
        dto = new DTOCriterio();

        dto.setAtributo("tipotramite");
        dto.setOperacion("=");
        dto.setValor(tipotramiteEncontrado);

        criterioList.add(dto);

        DTOCriterio dto2 = new DTOCriterio();

        dto2.setAtributo("fechaBaja");
        dto2.setOperacion("=");
        dto2.setValor(null);

        criterioList.add(dto2);

        List objetoList = FachadaPersistencia.getInstance().buscar("Articulo", criterioList);

        if (objetoList.size() != 0) {
            throw new ABMTipoTramiteException("TipoTramite no puede darse de baja");
        }

        tipotramiteEncontrado.setFechaHoraBajaTipoTramite(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(tipotramiteEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}


