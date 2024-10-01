/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;


import RC_2.ControladorRC;
import RC_2.dto.DestinosPosiblesDTO;
import RC_2.dto.NuevoEstadoDTO;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.omnifaces.util.Messages;
import utils.BeansUtils;
@Named("uiNuevoEstado")
@ViewScoped
public class UInuevoEstado  implements Serializable{
    private List<String> nombreEstadoTramiteDestino;
    private List<EstadosPosiblesUI> estadosPosiblesUI=new ArrayList<>();
    private String nombreEstadoTramiteActual;
    private int codEstadoTramite;
    private String observacionTramiteEstado;
    private ControladorRC controladorRC = new ControladorRC();
   
    private int codEstadoSeleccionado;

    public List<EstadosPosiblesUI> getEstadosPosiblesUI() {
        return estadosPosiblesUI;
    }

    public void setEstadosPosiblesUI(List<EstadosPosiblesUI> estadosPosiblesUI) {
        this.estadosPosiblesUI = estadosPosiblesUI;
    }

    public int getCodEstadoSeleccionado() {
        return codEstadoSeleccionado;
    }

    public void setCodEstadoSeleccionado(int codEstadoSeleccionado) {
        this.codEstadoSeleccionado = codEstadoSeleccionado;
    }
    
    
    public UInuevoEstado() {
          NuevoEstadoDTO nuevoEstadoDTO=controladorRC.nuevoEstado(codEstadoTramite);
         nombreEstadoTramiteActual=nuevoEstadoDTO.getNombreEstadoTramiteActual();
         for (DestinosPosiblesDTO d : nuevoEstadoDTO.getDestinosPosiblesList())
         {
         EstadosPosiblesUI e = new EstadosPosiblesUI();
         e.setCodEstadoTramite(d.getCodEstadoTramite());
         e.setNombreEstadoTramiteDestino(d.getNombreEstadoTramiteDestino());
         estadosPosiblesUI.add(e);
        }
                  EstadosPosiblesUI e = new EstadosPosiblesUI();
         e.setCodEstadoTramite(1);
         e.setNombreEstadoTramiteDestino("Cristian");
         estadosPosiblesUI.add(e);
                           e = new EstadosPosiblesUI();
         e.setCodEstadoTramite(2);
         e.setNombreEstadoTramiteDestino("Martina");
         estadosPosiblesUI.add(e);
    }

   
    public String getNombreEstadoTramiteActual() {
        return nombreEstadoTramiteActual;
    }

    public void setNombreEstadoTramiteActual(String nombreEstadoTramiteActual) {
        this.nombreEstadoTramiteActual = nombreEstadoTramiteActual;
    }

    public int getCodEstadoTramite() {
        return codEstadoTramite;
    }

    public void setCodEstadoTramite(int codEstadoTramite) {
        this.codEstadoTramite = codEstadoTramite;
    }

    public String getObservacionTramiteEstado() {
        return observacionTramiteEstado;
    }

    public void setObservacionTramiteEstado(String observacionTramiteEstado) {
        this.observacionTramiteEstado = observacionTramiteEstado;
    }
    
    public String guardar(){
  
              System.out.println(codEstadoSeleccionado); 
              System.out.println(observacionTramiteEstado);
            //return BeansUtils.redirectToPreviousPage();
           return "";
    }

    
    
    
}
