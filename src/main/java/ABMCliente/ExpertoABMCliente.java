/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCliente;

import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.DetalleClienteDTOIn;
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
     //private List<DetalleClienteDTO> clientes = new ArrayList<>();
    
    //Metodo para buscar Clientes
    public List<ListaClienteDTO> obtenerListaCliente(int cuitCliente, String nombreApellidoCliente){
        //lista que devuelve el metodo
        //List<ListaClienteDTO> listaClienteDTOs = new ArrayList<>();
        
        //busqueda buscar("Cliente", "") trae todos los clientes
        List<DTOCriterio> lCriterio=new ArrayList<>();
        //DTOCriterio unCriterio=new DTOCriterio(); Esto se pone asi si necesito utilizar criterio para buscar
         //Obtengo la lista de OBJETOS(clientes) desde la fachada de persistencia    
        //List<Object> objetoList= FachadaPersistencia.getInstance().buscar("Cliente", lCriterio);
        
        if(cuitCliente>0) {
            DTOCriterio unCriterio= new DTOCriterio();
            unCriterio.setAtributo("cuitCliente");
            unCriterio.setOperacion("=");
            unCriterio.setValor(cuitCliente);
            lCriterio.add(unCriterio);
        }
        
        if (nombreApellidoCliente.trim().length()>0){
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreApellidoCliente");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreApellidoCliente);
            lCriterio.add(unCriterio);
        }
//        Me larga error esto, como que ya esta declarado un poco mas arriba
        List<Object> objetoList= FachadaPersistencia.getInstance().buscar("Cliente", lCriterio);
        //List<ListaClienteDTO> listaClienteDTOs = new ArrayList<>();

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

     //Metodo para eliminar cliente
    public void eliminarCliente(int cuitCliente) throws ExceptionABMCliente {
        FachadaPersistencia.getInstance().iniciarTransaccion();
        
        List <DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
            dto.setAtributo("cuitCliente");
            dto.setOperacion("=");
            dto.setValor(cuitCliente);
            
            lCriterio.add(dto);
            
        Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        //Traigo fechaBaja para ver si tiene o no
        Timestamp fechaBaja = clienteEncontrado.getFechaHoraBajaCliente();
        //Compruebo que cliente no este ocupado con tramites
        lCriterio.clear();
        dto = new DTOCriterio();
        dto.setAtributo("cliente");
        dto.setOperacion("=");
        dto.setValor(clienteEncontrado);

        lCriterio.add(dto);
        
        List objetoList = FachadaPersistencia.getInstance().buscar("Tramite", lCriterio);
        
        if (objetoList.size() != 0) {
            throw new ExceptionABMCliente("El cliente tiene trámites activos!!");
        }

        
        if(fechaBaja == null){
        clienteEncontrado.setFechaHoraBajaCliente(new Timestamp(System.currentTimeMillis()));
        }
        FachadaPersistencia.getInstance().guardar(clienteEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
        
    }
    
//    /**
//     *
//     * @param cuitCliente
//     * @param nombreApellidoCliente
//     * @return
//     */
    public DetalleClienteDTO obtenerDetalleCliente(int cuitCliente) {
        //muestra detalles
        List<DTOCriterio> lCriterio=new ArrayList<>();
        if(cuitCliente>0){
            DTOCriterio unCriterio=new DTOCriterio();
            unCriterio.setAtributo("cuitCliente");
            unCriterio.setOperacion("=");
            unCriterio.setValor(cuitCliente);
            lCriterio.add(unCriterio);
        }
//        if(nombreApellidoCliente.trim().length()>0){
//            DTOCriterio unCriterio=new DTOCriterio();
//            unCriterio.setAtributo("nombreApellidoCliente");
//            unCriterio.setOperacion("like");
//            unCriterio.setValor(nombreApellidoCliente);
//            lCriterio.add(unCriterio);
//        }
        //falta criterio fecha
        
        //Nos de el primer cliente según lo marcado arriba 
        Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        
        //Encontro cliente y ahora modifica
        DetalleClienteDTO detalleClienteDTO = new DetalleClienteDTO();
            detalleClienteDTO.setCuitCliente(cuitCliente);
            detalleClienteDTO.setNombreApellidoCliente(clienteEncontrado.getNombreApellidoCliente());
            detalleClienteDTO.setDireccion(clienteEncontrado.getDireccionCliente());
            detalleClienteDTO.setMailCliente(clienteEncontrado.getMailCliente());
            detalleClienteDTO.setTelefonoCliente(clienteEncontrado.getTelefonoCliente());
      
        return detalleClienteDTO;
    }
    //Metodo para modificar todo EXCEPTO CUIT
    public void modificarCliente(int cuitCliente, DetalleClienteDTOIn detalleClienteDTOIn)throws ExceptionABMCliente {
        //literalmente modifica la instancia cliente
        FachadaPersistencia.getInstance().iniciarTransaccion();
        List <DTOCriterio> lCriterio = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("cuitCliente");
        dto.setOperacion("=");
        dto.setValor(cuitCliente);
        lCriterio.add(dto);
        
         Cliente clienteEncontrado =(Cliente) FachadaPersistencia.getInstance().buscar("Cliente", lCriterio).get(0);
        
         clienteEncontrado.setDireccionCliente(detalleClienteDTOIn.getDireccion());
         clienteEncontrado.setMailCliente(detalleClienteDTOIn.getMailCliente());
         clienteEncontrado.setNombreApellidoCliente(detalleClienteDTOIn.getNombreApellidoCliente());
         clienteEncontrado.setTelefonoCliente(detalleClienteDTOIn.getTelefonoCliente());
         
         FachadaPersistencia.getInstance().guardar(clienteEncontrado);
         FachadaPersistencia.getInstance().finalizarTransaccion();
    }
    

    //Metodo para ageegar Cliente Nuevo
     public void agregarCliente (DetalleClienteDTO detalleClienteDTO) throws ExceptionABMCliente{
         FachadaPersistencia.getInstance().iniciarTransaccion();
        //Me quede aca en la logica agregar cliente 
         List<DTOCriterio> criterioList = new ArrayList<>();
         DTOCriterio dto = new DTOCriterio();
         dto.setAtributo("cuitCliente");
         dto.setOperacion("=");
         dto.setValor(detalleClienteDTO.getCuitCliente());
         criterioList.add(dto);
        
        DTOCriterio dto2  = new DTOCriterio();
        //Para comprobar que el cliente No este dado de baja
        dto2.setAtributo("fechaHoraBajaCliente");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);
        
         List lCliente = FachadaPersistencia.getInstance().buscar("Cliente", criterioList);

        if (lCliente.size() > 0) {
            throw new ExceptionABMCliente ("El cuit es de un cliente existente");
        } else {
            Cliente cliente = new Cliente();
            cliente.setCuitCliente(detalleClienteDTO.getCuitCliente());
            cliente.setNombreApellidoCliente(detalleClienteDTO.getNombreApellidoCliente());
            cliente.setDireccionCliente(detalleClienteDTO.getDireccion());
            cliente.setMailCliente(detalleClienteDTO.getMailCliente());
            cliente.setTelefonoCliente(detalleClienteDTO.getTelefonoCliente());
            
            FachadaPersistencia.getInstance().guardar(cliente);
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }
       } 
        
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

   

