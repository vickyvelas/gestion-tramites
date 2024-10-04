package ActualizarListaPrecio;

import ActualizarListaPrecio.dtos.DTODetalleListaPrecioImportar;
import ActualizarListaPrecio.dtos.DTOListaPrecioImportar;
import ActualizarListaPrecio.dtos.DTOListaPrecioExportar;
import ActualizarListaPrecio.exceptions.ListaPrecioException;
import java.sql.Timestamp;

import java.util.List;

public class ControladorActualizarListaPrecio {

    private ExpertoActualizarListaPrecio expertoActualizarListaPrecio = new ExpertoActualizarListaPrecio();

    public List<DTOListaPrecioExportar> obtenerListaPrecio(Timestamp fechaActual) {
        return expertoActualizarListaPrecio.obtenerListaPrecio(fechaActual);
    }

    public DTOListaPrecioImportar importarListaPrecio( DTOListaPrecioImportar listaPrecioImportarDTO, DTODetalleListaPrecioImportar detallesImportar) {
       return expertoActualizarListaPrecio.importarListaPrecio(listaPrecioImportarDTO, detallesImportar);
    }

   public DTOListaPrecioExportar exportarListaPrecio(int codListaPrecio) {
         return expertoActualizarListaPrecio.exportarListaPrecio(codListaPrecio);
    }

    public void darDeBajaListaPrecio(int codigo) throws ListaPrecioException {
        expertoActualizarListaPrecio.darDeBajaListaPrecio(codigo);
    }

}
