package ABMConfigurarVersiones.beans;

import ABMConfigurarVersiones.dtos.DTOEstadoActualInput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoInput;
import ABMConfigurarVersiones.dtos.DTOEstadoPosible;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import ABMConfigurarVersiones.dtos.DTOVersionyEstados;
import ABMConfigurarVersiones.exceptions.ControladorABMVersion;
import ABMConfigurarVersiones.exceptions.VersionException;
import utils.BeansUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;

import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omnifaces.util.Messages;

@Named("uiDraw")
@ViewScoped

public class UIDraw implements Serializable {

    private String guardarJSON = "";
    private ControladorABMVersion controlador = new ControladorABMVersion();
    private String cargarJSON = "";
    private String titulo = "";
    private boolean editable;
    private String nodosPosibles = "";
    private int nroVersion=0;
    private int codigoTipoTramite=0;
    public void setNroVersion(int nroVersion) {
        this.nroVersion = nroVersion;
    }

    public int getNroVersion() {
        return nroVersion;
    }
    public String getNodosPosibles() {
        return nodosPosibles;
    }

    public void setNodosPosibles(String nodosPosibles) {
        this.nodosPosibles = nodosPosibles;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UIDraw()  {
        //Diagrama
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int codigo = Integer.parseInt(request.getParameter("codigoTipoTramite"));
        System.out.println("El codigo");
        codigoTipoTramite=codigo;
        DTOVersionyEstados versionMostrar=null;
        try {
            versionMostrar = controlador.buscarVersionEditar(codigo);
        } catch (VersionException ex) {
            Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<NodoMenuIU> lestadosP = new ArrayList<NodoMenuIU>();    
        List<DTOEstadoPosible> estados = versionMostrar.getEstadosPosibles();
        for(DTOEstadoPosible x: estados){
             NodoMenuIU unEP = new NodoMenuIU();
             unEP.setCodigo(x.getCodEstadoTramite());
             unEP.setNombre(x.getNombreEstadoTramite());
             lestadosP.add(unEP);
        }
        
        Gson gson = new Gson();
        nodosPosibles = gson.toJson(lestadosP);
         List<NodoIU> lestados = new ArrayList<NodoIU>();
        if(versionMostrar.getVersion()==null || versionMostrar.getVersion().getDraw().trim().length()==0)
        {
            
           for(DTOEstadoPosible x: estados)
           {
               if(x.getCodEstadoTramite()==1)
               {
                    NodoIU unE = new NodoIU();
                    unE.setCodigo(x.getCodEstadoTramite());
                    unE.setNombre(x.getNombreEstadoTramite());
                    unE.setXpos(80);
                    unE.setYpos(80);
                    lestados.add(unE);  
               }

           }
           
            
        }
        else
        {
            cargarJSON=versionMostrar.getVersion().getDraw();
        }

        titulo = "Versión 1";
        editable = true;

        //Estados Posibles
        //Ejemplo

    }

    public String getCargarJSON() {
        return cargarJSON;
    }

    public void setCargarJSON(String cargarJSON) {
        this.cargarJSON = cargarJSON;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getGuardarJSON() {
        return guardarJSON;
    }

    public void setGuardarJSON(String guardarJSON) {
        this.guardarJSON = guardarJSON;
    }

    public void guardar() {
        
        
        DTOVersionInput vi=new DTOVersionInput();
        vi.setCodTipoTramite(codigoTipoTramite);
        vi.setCodVersion(2);
        vi.setFechaHoraDesdeVersion(new Timestamp(System.currentTimeMillis()));
        Timestamp fh = Timestamp.valueOf("2070-09-15 22:06:45.0");
        vi.setFechaHoraHastaVersion(fh);
       
        
        System.out.println(this.guardarJSON);
        Messages.create("nroVersion").detail(""+this.nroVersion).add();
        Messages.create("Guardar").detail(this.guardarJSON).add();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<NodoIU> listaNodo = new ArrayList();
        try {
            listaNodo = objectMapper.readValue(this.guardarJSON, typeFactory.constructCollectionType(List.class, NodoIU.class));
            System.out.println(listaNodo.toString());
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);
            Messages.create(ex.getMessage()).error().add();
        } catch (IOException ex) {
            Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(NodoIU n:listaNodo)
        {
            DTOEstadoActualInput ei=new DTOEstadoActualInput();
            ei.setCodEstadoActual(n.getCodigo());
            for(int cod : n.getDestinos())
            {
                DTOEstadoDestinoInput ed = new DTOEstadoDestinoInput();
                ed.setCodEstadoDestino(cod);
                ei.addEstadosDestino(ed);
            }
        }
        vi.setDraw(this.guardarJSON);
        try {
            controlador.nuevaVersion(vi);
        } catch (VersionException ex) {
            Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);
        }
        //listaNodo tiene los nodos
        //para comprobar
        String jsonArray = "";

        Gson gson = new Gson();
        cargarJSON = gson.toJson(listaNodo);
        System.out.println(jsonArray);
        Messages.create("Guardar 2").detail(this.guardarJSON).add();
    }

}
