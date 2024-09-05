package ActualizarListaPrecio;

import ActualizarListaPrecio.dtos.DTOListaPrecioImportar;
import ActualizarListaPrecio.dtos.DTOListaPrecioExportar;
import ActualizarListaPrecio.exceptions.ListaPrecioException;
import entidades.ListaPrecio;

import java.util.List;

public class ControladorActualizarListaPrecio {
    private ExpertoActualizarListaPrecio expertoActualizarListaPrecio = new ExpertoActualizarListaPrecio();
    public List<ListaPrecio> buscarEstados(int codigo, String nombre){
        return expertoActualizarListaPrecio.buscarListaPrecio(codigo,nombre);
    }

    public void importarListaPrecio(DTOListaPrecioImportar dtoListaPrecioImportar) throws ListaPrecioException{
        expertoActualizarListaPrecio.importarListaPrecio(dtoListaPrecioImportar);
    }
    
    public void exportarListaPrecio (DTOListaPrecioExportar dtoListaPrecioExportar) throws ListaPrecioException{
        expertoActualizarListaPrecio.exportarListaPrecio(dtoListaPrecioExportar);
    }
}