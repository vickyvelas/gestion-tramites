/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.beans;

import ABMConfigurarVersiones.dtos.DTOEstadoActualInput;
import ABMConfigurarVersiones.dtos.DTOEstadoActualOutput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoOutput;
import ABMConfigurarVersiones.dtos.DTOEstadoPosible;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
import ABMConfigurarVersiones.dtos.DTOVersionyEstados;
import ABMConfigurarVersiones.exceptions.ControladorABMVersion;
import ABMConfigurarVersiones.exceptions.VersionException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import entidades.Version;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.util.Messages;


@Named("uiDraw")
@ViewScoped
public class EditarVersionUI implements Serializable{
    
    private String guardarJSON = "";
    private String cargarJSON = "";
    private String titulo = "";
    private boolean editable;
    private String nodosPosibles = "";
    
    public String getNodosPosibles() {
        return nodosPosibles;
    }

    public void setNodosPosibles(String nodosPosibles) {
        this.nodosPosibles = nodosPosibles;
    }  
        
    public String getGuardarJSON() {
        return guardarJSON;
    }

    public void setGuardarJSON(String guardarJSON) {
        this.guardarJSON = guardarJSON;
    }

    public String getCargarJSON() {
        return cargarJSON;
    }

    public void setCargarJSON(String cargarJSON) {
        this.cargarJSON = cargarJSON;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    public void UIDranw(int codTipoTramite) throws VersionException{
        ControladorABMVersion controlador = new ControladorABMVersion();
        DTOVersionyEstados dtoVyE = controlador.buscarVersionEditar(codTipoTramite);
        DTOVersionOutput version = dtoVyE.getVersion();
       
        List<DTOEstadoActualOutput> estadosVer = version.getEstadosActuales();
     //nodos pantalla       
        List<NodoPantalla> listNodos = new ArrayList();
        Random random = new Random();  
        for(DTOEstadoActualOutput eA : estadosVer){
        NodoPantalla n = new NodoPantalla();
        n.setCodigo(eA.getCodEstadoActual());
        n.setNombre(eA.getNombreEstadoTramite());
        n.setXpos(random.nextDouble(100 - 30 + 1) + 30);
        n.setYpos(random.nextDouble(100 - 30 + 1) + 30);
        List<DTOEstadoDestinoOutput> listdest = eA.getEstadosDestino();
        for(DTOEstadoDestinoOutput eD : listdest){
            n.addDestino(eD.getCodEstadoDestino());
        }
        }
        
        Gson gson = new Gson();
        cargarJSON = gson.toJson(listNodos);
        titulo = "Versión 1";
        editable = true;

        // estados posbles
        List<DTOEstadoPosible> listEstados = dtoVyE.getEstadosPosibles();
        nodosPosibles = gson.toJson(listEstados); 
    }
    public void guardar(DTOVersionInput datosIngre) {
        
        System.out.println(this.guardarJSON);
        
        
        /*
        System.out.println(this.guardarJSON);
        Messages.create("Guardar").detail(this.guardarJSON).add();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<DTOEstadoActualInput> listaNodo = new ArrayList();
        try {
            listaNodo = objectMapper.readValue(this.guardarJSON, typeFactory.constructCollectionType(List.class, NodoPantalla.class));
            System.out.println(listaNodo.toString());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(EditarVersionUI.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
        }

        //listaNodo tiene los nodos
        //para comprobar
        String jsonArray = "";

        Gson gson = new Gson();
        cargarJSON = gson.toJson(listaNodo);
        System.out.println(jsonArray);
        Messages.create("Guardar 2").detail(this.guardarJSON).add();
*/
    }

    
    
}
