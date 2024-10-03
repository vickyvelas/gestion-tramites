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
    
    public void eliminarCliente(int cuitCliente) throws ExceptionABMCliente {
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List <DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
            dto.setAtributo("cuitCliente");
            dto.setOperacion("=");
            dto.setValor(cuitCliente);
            
            lCriterio.add(dto);
            
        Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        Timestamp fechaBaja = clienteEncontrado.getFechaHoraBajaCliente();
        
        if(fechaBaja == null){
            clienteEncontrado.setFechaHoraBajaCliente(new Timestamp(System.currentTimeMillis()));
        }
        FachadaPersistencia.getInstance().guardar(clienteEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        
    }
    
    public DetalleClienteDTO obtenerDetalleCliente(int cuitCliente) {
        //muestra detalles
        
        
         List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if(cuitCliente>0)
        {
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("cuitCliente");
            unCriterio.setOperacion("=");
            unCriterio.setValor(cuitCliente);
            lCriterio.add(unCriterio);
        }
//        if(nombreApellidoCliente.trim().length()>0)
//        {
//            DTOCriterio unCriterio=new DTOCriterio();
//            unCriterio.setAtributo("nombreApellidoCliente");
//            unCriterio.setOperacion("like");
//            unCriterio.setValor(nombreApellidoCliente);
//            lCriterio.add(unCriterio);
//        }
        //falta criterio fecha
        
        //buscar un solo cliente 
        Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        
        DetalleClienteDTO detalleClienteDTO = new DetalleClienteDTO();
            detalleClienteDTO.setCuitCliente(cuitCliente);
            detalleClienteDTO.setNombreApellidoCliente(clienteEncontrado.getNombreApellidoCliente());
            detalleClienteDTO.setDireccion(clienteEncontrado.getDireccionCliente());
            detalleClienteDTO.setMailCliente(clienteEncontrado.getMailCliente());
            detalleClienteDTO.setTelefonoCliente(clienteEncontrado.getTelefonoCliente());
      
        return detalleClienteDTO;
    }
    
    public void modificarCliente(int cuitCliente, DetalleClienteDTO detalleClienteDTO)throws ExceptionABMCliente {
        //literalmente modifica la instancia cliente
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List <DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("cuitCliente");
        dto.setOperacion("=");
        dto.setValor(cuitCliente);
        lCriterio.add(dto);
        
         Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        
         clienteEncontrado.setDireccionCliente(detalleClienteDTO.getDireccion());
         clienteEncontrado.setMailCliente(detalleClienteDTO.getMailCliente());
         clienteEncontrado.setNombreApellidoCliente(detalleClienteDTO.getNombreApellidoCliente());
         clienteEncontrado.setTelefonoCliente(detalleClienteDTO.getTelefonoCliente());
         
         FachadaPersistencia.getInstance().guardar(clienteEncontrado);
         FachadaPersistencia.getInstance().finalizarTransaccion();
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
