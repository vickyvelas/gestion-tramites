/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaPrecio;

import ListaPrecio.dtos.DTODetalleListaPrecioImportar;
import ListaPrecio.dtos.DTOListaPrecioExportar;
import ListaPrecio.dtos.DTOListaPrecioImportar;
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

    public void importarListaPrecio( DTOListaPrecioImportar listaPrecioImportarDTO) {
        expertoActualizarListaPrecio.importarListaPrecio(listaPrecioImportarDTO);
    }

   public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
         return expertoActualizarListaPrecio.exportarListaPrecio(codListaPrecio);
    }
}
