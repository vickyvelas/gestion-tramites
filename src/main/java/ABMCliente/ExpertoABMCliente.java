/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCliente;

import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.ListaClienteDTO;
import entidades.Cliente;
import entidades.Tramite;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author Dell
 */
public class ExpertoABMCliente {
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
    
    public List<ListaClienteDTO> obtenerListaCliente(){
        //lista que devuelve el metodo
        List<ListaClienteDTO> listaClienteDTOs = new ArrayList<>();
        
        //busqueda buscar("Cliente", "")
        List<DTOCriterio> lCriterio=new ArrayList<>();
        //DTOCriterio unCriterio=new DTOCriterio(); Esto se pone asi si necesito utilizar criterio para buscar
         //Obtengo la lista de OBJETOS(clientes) desde la fachada de persistencia    
        List<Object> objetoList = FachadaPersistencia.getInstance().buscar("Cliente", lCriterio);
         
         for(Object x : objetoList){
             Cliente cliente = (Cliente) x;
             ListaClienteDTO listaClienteDTO = new ListaClienteDTO();
                listaClienteDTO.setCuitCliente(cliente.getCuitCliente());
                listaClienteDTO.setNombreApellidoCliente(cliente.getNombreApellidoCliente());
                listaClienteDTO.setFechaHoraBajaCliente(cliente.getFechaHoraBajaCliente());
                listaClienteDTOs.add(listaClienteDTO);
         }
         
         
        return listaClienteDTOs;
        
        
    }
     public void modificarCliente(int cuitCliente)throws Exception {
         //Logica para modificar cliente
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
//    public List<ListaClienteDTO> obtenerListaCliente(int cuitClienteFiltro, String nombreApellidoClienteFiltro) {
//        // Lógica para obtener la lista de clientes
//        List<ListaClienteDTO> listaClientes = new ArrayList<>();
//        for (DetalleClienteDTO cliente : clientes) {
//            if ((cuitClienteFiltro == 0 || cliente.getCuitCliente() == cuitClienteFiltro) &&
//                (nombreApellidoClienteFiltro == null || cliente.getNombreApellidoCliente().contains(nombreApellidoClienteFiltro))) {
//                ListaClienteDTO listaCliente = new ListaClienteDTO();
//                listaCliente.setNombreApellidoCliente(cliente.getNombreApellidoCliente());
//                listaCliente.setCuitCliente(cliente.getCuitCliente());
//                listaCliente.setFechaHoraBajaCliente(cliente.getFechaHoraBajaCliente());
//                listaClientes.add(listaCliente);
//            }
//        }
//        return listaClientes;
//    }

   
}
