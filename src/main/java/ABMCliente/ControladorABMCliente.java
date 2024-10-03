/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCliente;

import ABMCliente.dtos.DetalleClienteDTO;
import ABMCliente.dtos.ListaClienteDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ControladorABMCliente {
    
    private ExpertoABMCliente expertoABMCliente = new ExpertoABMCliente();
        
     public void eliminarCliente(int cuitCliente) throws Exception, ExceptionABMCliente{
         expertoABMCliente.eliminarCliente(cuitCliente);
     }
         
     public void modificarCliente(int cuitCliente, DetalleClienteDTO detalleClienteDTO) throws ExceptionABMCliente{
         expertoABMCliente.modificarCliente(cuitCliente, detalleClienteDTO);
     }
            
  public List<ListaClienteDTO> obtenerListaCliente(){
      return expertoABMCliente.obtenerListaCliente();
  }
     
     
    public DetalleClienteDTO obtenerDetalleCliente (int cuitCliente){
        return expertoABMCliente.obtenerDetalleCliente(cuitCliente);
    }
//      public List<ListaClienteDTO> obtenerListaCliente( int cuitClienteFiltro,String nombreApellidoClienteFiltro){
//          return expertoABMCliente.obtenerListaCliente(cuitClienteFiltro, nombreApellidoClienteFiltro);
//    }
    
    
}

