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
import java.util.Comparator;
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

    public void importarListaPrecio(DTOListaPrecioImportar listaPrecioImportarDTO) {

        List<DTOCriterio> lCriterio = new ArrayList<>();

        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("ListaPrecio", lCriterio);

        ListaPrecio listaPrecioAnterior = objetoList.stream()
                .map(x -> (ListaPrecio) x)
                .max(Comparator.comparing(ListaPrecio::getFechaHoraDesdeListaPrecio))
                .orElse(null);

        if (listaPrecioAnterior != null) {

            // ver la fecha de la lista anterior por consola
            System.out.println("Fecha Hora Hasta de la Lista Anterior: " + listaPrecioAnterior.getFechaHoraHastaListaPrecio());
            System.out.println("Fecha Hora Desde del DTO (Nueva Lista): " + listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio());

            // comparar las fechas
            if (listaPrecioAnterior.getFechaHoraHastaListaPrecio().before(listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio())) {
                System.out.println("La nueva lista de precios es válida y posterior a la anterior.");
            } else {
                System.out.println("Error: La nueva lista de precios tiene una fecha anterior o igual a la lista anterior.");
                throw new IllegalArgumentException("La nueva lista tiene una fecha inválida con respecto a la lista anterior.");
            }

            // Modificar la lista de precios anterior para cerrar su periodo de vigencia
            listaPrecioAnterior.setFechaHoraHastaListaPrecio(listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio());

            // Guardar los cambios en la lista anterior
            FachadaPersistencia.getInstance().iniciarTransaccion();
            FachadaPersistencia.getInstance().guardar(listaPrecioAnterior);
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }

        //CREAR NUEVA LISTA PRECIO
        ListaPrecio nuevaListaPrecio = new ListaPrecio();
        nuevaListaPrecio.setFechaHoraDesdeListaPrecio(listaPrecioImportarDTO.getFechaHoraDesdeListaPrecio());
        nuevaListaPrecio.setFechaHoraHastaListaPrecio(listaPrecioImportarDTO.getFechaHoraHastaListaPrecio());

        //BUSCAR TIPOS TRAMITES
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("fechaHoraBajaTipoTramite");
        unCriterio.setOperacion("!=");
        unCriterio.setValor(null);
        lCriterio.add(unCriterio);
        List<Object> objetoListTT = FachadaPersistencia.getInstance().buscar("TipoTramite", lCriterio);

        for (Object tt : objetoListTT) {
            TipoTramite tipoTramite = (TipoTramite) tt;

            DetallePrecioTipoTramite nuevodetallePrecioTipoTramite = new DetallePrecioTipoTramite();
            nuevodetallePrecioTipoTramite.setTipoTramite(tipoTramite);
            nuevodetallePrecioTipoTramite.setPrecioTipoTramite(0); // Inicializar precio en 0

            //obtengo detalles
            List<DTODetalleListaPrecioImportar> listaDetalles = listaPrecioImportarDTO.getDetalleListaPrecioImportar();
            boolean isEncontrado = false;

            for (DTODetalleListaPrecioImportar detImpdto : listaDetalles) {
                int codTT = tipoTramite.getCodTipoTramite();
                int codTTdto = detImpdto.getCodTipotramite();

                //Verificar si los códigos de tipo trámite coinciden
                if (codTT == codTTdto) {
                    nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImpdto.getPrecioTipoTramite());
                    isEncontrado = true;
                    break;
                }
            }

            // Si no se encuentra en los detalles del DTO, buscar en los detalles de la lista anterior
            if (!isEncontrado && listaPrecioAnterior != null) {
                List<DetallePrecioTipoTramite> listaDetallesAnt = listaPrecioAnterior.getDetallePrecioTipoTramiteList();
                for (DetallePrecioTipoTramite detImp : listaDetallesAnt) {
                    TipoTramite ttAnterior = detImp.getTipoTramite();
                    //int codTTA = tipoTramite.getCodTipoTramite();

                    if (ttAnterior.getCodTipoTramite() == tipoTramite.getCodTipoTramite()) {
                        nuevodetallePrecioTipoTramite.setPrecioTipoTramite(detImp.getPrecioTipoTramite());
                        break;
                    }
                }
            }
            // Añadir el nuevo detalle a la lista de precios nueva
            nuevaListaPrecio.addDetallePrecioTipoTramiteList(nuevodetallePrecioTipoTramite);
        }
        // Guardar la nueva lista de precios
        FachadaPersistencia.getInstance().iniciarTransaccion();
        FachadaPersistencia.getInstance().guardar(nuevaListaPrecio);
        FachadaPersistencia.getInstance().finalizarTransaccion();
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

            TipoTramite tt = detlp.getTipoTramite();

            DTODetalleListaPrecioExportar dtoDetalleListaPrecioExportar = new DTODetalleListaPrecioExportar();
            dtoDetalleListaPrecioExportar.setCodTipoTramite(tt.getCodTipoTramite());
            dtoDetalleListaPrecioExportar.setNombreTipoTramite(tt.getNombreTipoTramite());
            dtoDetalleListaPrecioExportar.setPrecioTipoTramite(detlp.getPrecioTipoTramite());
            dtoListaPrecioExportar.addDetalleListaPrecioExportar(dtoDetalleListaPrecioExportar);
        }
        return dtoListaPrecioExportar;
    }
}
