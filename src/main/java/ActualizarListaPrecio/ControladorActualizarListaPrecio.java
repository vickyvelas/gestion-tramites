package ActualizarListaPrecio;

import ActualizarListaPrecio.dtos.DTOListaPrecio;
import ActualizarListaPrecio.dtos.DTOListaPrecioImportar;
import ActualizarListaPrecio.dtos.DTOListaPrecioExportar;
import ActualizarListaPrecio.exceptions.ListaPrecioException;
import java.sql.Timestamp;

import java.util.List;

public class ControladorActualizarListaPrecio {
    
    private ExpertoActualizarListaPrecio expertoActualizarListaPrecio = new ExpertoActualizarListaPrecio();
    
    public List<DTOListaPrecio> obtenerListaPrecio(Timestamp fechaActual)throws ListaPrecioException{
        return expertoActualizarListaPrecio.obtenerListaPrecio(fechaActual);
    }

    public void importarListaPrecio(DTOListaPrecioImportar dtoListaPrecioImportar) throws ListaPrecioException{
        expertoActualizarListaPrecio.importarListaPrecio(dtoListaPrecioImportar);
    }
    
    public void exportarListaPrecio (DTOListaPrecioExportar dtoListaPrecioExportar) throws ListaPrecioException{
        expertoActualizarListaPrecio.exportarListaPrecio(dtoListaPrecioExportar);
    }

    public void darDeBajaListaPrecio(int codigo) throws ListaPrecioException{
        expertoActualizarListaPrecio.darDeBajaListaPrecio (codigo);
    }
    
//    public List<DTOListaPrecio> obtenerListaPrecio()throws ListaPrecioException{
//        return expertoActualizarListaPrecio.obtenerListaPrecio();
//    }
}