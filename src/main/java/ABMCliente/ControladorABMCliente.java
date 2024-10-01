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
    
    private ExpertoABMCliente2 expertoABMCliente = new ExpertoABMCliente2();
    
    
    
     public void eliminarCliente(int cuitCliente) throws exceptionABMCliente{
        return expertoABMCliente2.eliminarCliente(cuitCliente);
    }
            
     
     
     
    public DetalleClienteDTO obtenerDetalleCliente (int cuitCliente){
        return expertoABMCliente2.obtenerDetalleCliente(cuitCliente);
    }
      public List<ListaClienteDTO> obtenerListaCliente( int cuitClienteFiltro,String nombreApellidoClienteFiltro){
          return expertoABMListaCliente2.obtenerListaCliente(cuitClienteFiltro, nombreApellidoClienteFiltro);
    }
    
    
}

