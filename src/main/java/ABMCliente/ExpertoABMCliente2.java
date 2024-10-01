package ABMCliente;

import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.ListaClienteDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ExpertoABMCliente2 {
    
    private List<DetalleClienteDTO> clientes = new ArrayList<>();
    
    public void eliminarCliente(int cuitCliente) throws Exception {
        // Lógica para eliminar un cliente
        for (DetalleClienteDTO cliente : clientes) {
            if (cliente.getCuitCliente() == cuitCliente) {
                clientes.remove(cliente);
                return;
            }
        }
        throw new Exception("Cliente no encontrado");
    }
    
    public DetalleClienteDTO obtenerDetalleCliente(int cuitCliente) {
        // Lógica para obtener el detalle del cliente
        for (DetalleClienteDTO cliente : clientes) {
            if (cliente.getCuitCliente() == cuitCliente) {
                return cliente;
            }
        }
        return null;
    }
    
    public List<ListaClienteDTO> obtenerListaCliente(int cuitClienteFiltro, String nombreApellidoClienteFiltro) {
        // Lógica para obtener la lista de clientes
        List<ListaClienteDTO> listaClientes = new ArrayList<>();
        for (DetalleClienteDTO cliente : clientes) {
            if ((cuitClienteFiltro == 0 || cliente.getCuitCliente() == cuitClienteFiltro) &&
                (nombreApellidoClienteFiltro == null || cliente.getNombreApellidoCliente().contains(nombreApellidoClienteFiltro))) {
                ListaClienteDTO listaCliente = new ListaClienteDTO();
                listaCliente.setNombreApellidoCliente(cliente.getNombreApellidoCliente());
                listaCliente.setCuitCliente(cliente.getCuitCliente());
                listaCliente.setFechaHoraBajaCliente(cliente.getFechaHoraBajaCliente());
                listaClientes.add(listaCliente);
            }
        }
        return listaClientes;
    }
}