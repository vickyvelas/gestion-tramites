package ABMConfigurarVersiones.beans;

import ABMConfigurarVersiones.dtos.DTOEstadoActualInput;
import ABMConfigurarVersiones.dtos.DTOEstadoDestinoInput;
import ABMConfigurarVersiones.dtos.DTOEstadoPosible;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import ABMConfigurarVersiones.dtos.DTOVersionOutput;
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
    private boolean insert;
    private int nroVersion;
    private int codigoTipoTramite;
    private Timestamp fechaHoraDesdeVersion;
    private Timestamp fechaHoraHastaVersion;

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public int getCodigoTipoTramite() {
        return codigoTipoTramite;
    }

    public void setCodigoTipoTramite(int codigoTipoTramite) {
        this.codigoTipoTramite = codigoTipoTramite;
    }

    public Timestamp getFechaHoraDesdeVersion() {
        return fechaHoraDesdeVersion;
    }

    public void setFechaHoraDesdeVersion(Timestamp fechaHoraDesdeVersion) {
        this.fechaHoraDesdeVersion = fechaHoraDesdeVersion;
    }

    public Timestamp getFechaHoraHastaVersion() {
        return fechaHoraHastaVersion;
    }

    public void setFechaHoraHastaVersion(Timestamp fechaHoraHastaVersion) {
        this.fechaHoraHastaVersion = fechaHoraHastaVersion;
    }
    
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

    public UIDraw() throws VersionException  {
        //Diagrama
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        
        int codigo = Integer.parseInt(request.getParameter("codigoTipoTramite"));
        codigoTipoTramite=codigo;
        boolean editaOve = Boolean.parseBoolean(request.getParameter("editable"));
        editable = editaOve;
        insert=true;
        DTOVersionyEstados versionMostrar=null;
        DTOVersionOutput versionoutput =null;
        if(editable == true){
        try {
            versionMostrar = controlador.buscarVersionEditar(codigo);
            versionoutput = versionMostrar.getVersion();
        } catch (VersionException ex) {
            Logger.getLogger(UIDraw.class.getName()).log(Level.SEVERE, null, ex);   
        }
        versionoutput = versionMostrar.getVersion();
        setNroVersion(versionoutput.getCodVersion());
        setFechaHoraDesdeVersion(versionoutput.getFechaHoraDesdeVersion());
        setFechaHoraHastaVersion(versionoutput.getFechaHoraHastaVersion());
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
        
            if(versionoutput==null || versionoutput.getDraw().trim().length()==0)
            {
                for(DTOEstadoPosible x: estados){
                    if(x.getCodEstadoTramite()==1){
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
        editable = true;
        }
        else{
            int codver = Integer.parseInt(request.getParameter("codVersion"));
            nroVersion = codver;
            versionoutput = controlador.verVersion(codigoTipoTramite, nroVersion);
            
            List<NodoIU> lestados = new ArrayList<NodoIU>();
            if(versionoutput==null || versionoutput.getDraw().trim().length()==0)
            {
            //throw new VersionException("No hay version para visualizar");
                       NodoIU unE = new NodoIU();
                        unE.setCodigo(1);
                        unE.setNombre("nada");
                        unE.setXpos(80);
                        unE.setYpos(80);
                        lestados.add(unE);  
                     
            }
            else
            {
                setNroVersion(versionoutput.getCodVersion());
                setFechaHoraDesdeVersion(versionoutput.getFechaHoraDesdeVersion());
                setFechaHoraHastaVersion(versionoutput.getFechaHoraHastaVersion());
                cargarJSON=versionoutput.getDraw();
            }
            editable = false;
        }
       titulo = "Versión";

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
        
        DTOVersionInput vi = new DTOVersionInput();
        
                vi.setCodTipoTramite(codigoTipoTramite);
                vi.setCodVersion(getNroVersion());
                vi.setFechaHoraDesdeVersion(fechaHoraDesdeVersion);
                vi.setFechaHoraHastaVersion(fechaHoraHastaVersion);
            
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
        Messages.create("Guardar").detail(this.guardarJSON).add();
    }

}
