/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPreciov2;
import ListaPreciov2.dtos.DTODetalleListaPrecioExportar;
import ListaPreciov2.dtos.DTOListaPrecioImportar;
import ListaPreciov2.dtos.DTODetalleListaPrecioImportar;
import ListaPreciov2.dtos.DTOListaPrecioExportar;
import entidades.DetallePrecioTipoTramite;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author marti
 */
public class ControladorLP {
    private ExpertoLP expertoActualizarListaPrecio = new ExpertoLP();

    public List<DTOListaPrecioExportar> obtenerListaPrecio(Timestamp fechaActual) {
        return expertoActualizarListaPrecio.obtenerListaPrecio(fechaActual);
    }

    public DTOListaPrecioImportar importarListaPrecio( DTOListaPrecioImportar listaPrecioImportarDTO, DTODetalleListaPrecioImportar detallesImportar) {
       return expertoActualizarListaPrecio.importarListaPrecio(listaPrecioImportarDTO, detallesImportar);
    }

   public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
         return expertoActualizarListaPrecio.exportarListaPrecio(codListaPrecio);
    }
}
