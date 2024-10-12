
package CargarTramite.beans;

import CargarTramite.ControladorCargarTramite;
import CargarTramite.dtos.DTODocumentacion;
import CargarTramite.dtos.DTOVisualizarTramite;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Named("uivisualizarTramite")
@ViewScoped
public class UIVisualizarTramite implements Serializable{


    private ControladorCargarTramite controladorCargarTramite = new ControladorCargarTramite();
    private VisualizarTramiteGrilla grilla = new VisualizarTramiteGrilla();
    private int nroTramite; //atributo que almancena el valor del parametro
    
    

        public VisualizarTramiteGrilla visualizarTramite()throws Exception{
            
            System.out.println("VISUALIZAR TRAMITE");
            
            String nroRecuperado = FacesContext.getCurrentInstance()
                    .getExternalContext().getRequestParameterMap().get("nroTramite");
            
            System.out.println("numero recuperado" +nroRecuperado);
            
             if (nroRecuperado != null){ 
                try {
                    int nroTramite = Integer.parseInt(nroRecuperado);
                    DTOVisualizarTramite dto = controladorCargarTramite.visualizarTramite(nroTramite);
           
                    grilla.setNroTramite(dto.getNroTramite());
                    System.out.println("Número de trámite: " + grilla.getNroTramite());

                    grilla.setFechaHoraCargaTramite(dto.getFechaHoraCargaTramite());
                    System.out.println("Fecha de Carga: " + grilla.getFechaHoraCargaTramite());

                    grilla.setNombreTipoTramite(dto.getNombreTipoTramite());
                    System.out.println("Nombre de tipo de trámite: " + grilla.getNombreTipoTramite());

                    grilla.setNombreCliente(dto.getNombreCliente());
                    System.out.println("Nombre del cliente: " + grilla.getNombreCliente());

                    grilla.setCuitCliente(dto.getCuitCliente());
                    System.out.println("CUIT del cliente: " + grilla.getCuitCliente());

                    grilla.setDireccion(dto.getDireccion());
                    System.out.println("Dirección del cliente: " + grilla.getDireccion());

                    grilla.setNombreApellidoConsultor(dto.getNombreApellidoConsultor());
                    System.out.println("Nombre del consultor: " + grilla.getNombreApellidoConsultor());

                    grilla.setNombreEstadoTramite(dto.getNombreEstadoTramite());
                    System.out.println("Nombre del estado del trámite: " + grilla.getNombreEstadoTramite());

                    grilla.setPrecioTramite(dto.getPrecioTramite());
                    System.out.println("Precio del trámite: " + grilla.getPrecioTramite());

                       List<DTODocumentacion> docRecu = dto.getDtoDocumentaciones();
                       System.out.println("doc recu tamano = " +docRecu.size());
                        for(DTODocumentacion d : docRecu){
                            DTODocumentacion docDTO = new DTODocumentacion();
                            docDTO.setNombreDocumentacion(d.getNombreDocumentacion());
                             System.out.println("nombre documentacion: " + docDTO.getNombreDocumentacion());
                            if(d.getFechaHoraEntrega() != null){
                            docDTO.setFechaHoraEntrega(d.getFechaHoraEntrega());
                            }
                             System.out.println("fecha de entrega: " + docDTO.getFechaHoraEntrega());
                             
                             grilla.addDocumentacion(docDTO);
                        }
                   
                    return grilla;
                    
                    } catch (Exception e) {
                        // Manejo de errores si el número de trámite no es válido
                        throw new Exception("Error, número de trámite inválido.",e);
                    }
                }
             return null;
            }
        
    
        
        
        //getters and setters
    public ControladorCargarTramite getControladorCargarTramite() {
        return controladorCargarTramite;
    }

    public void setControladorCargarTramite(ControladorCargarTramite controladorCargarTramite) {
        this.controladorCargarTramite = controladorCargarTramite;
    }
    
    
    public VisualizarTramiteGrilla getGrilla() {
        return grilla;
    }

    public void setGrilla(VisualizarTramiteGrilla grilla) {
        this.grilla = grilla;
    }

    
    public int getNroTramite() {
        return nroTramite;
    }

    public void setNroTramite(int nroTramite) {
        this.nroTramite = nroTramite;
    }
    
}