package ConfigurarAgenda.beans;

import ConfigurarAgenda.ControladorConfigurarAgenda;
import ConfigurarAgenda.dtos.DTOConsultor;
import ConfigurarAgenda.dtos.DTOConsultorAgenda;
import ConfigurarAgenda.exceptions.ConfigurarAgendaException;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named("uiAgregarConsultorAgenda")
@ViewScoped
public class UIAgregarConsultorAgenda implements Serializable {

    private ControladorConfigurarAgenda controladorConfigurarAgenda = new ControladorConfigurarAgenda();
    private List<ConsultorGrillaUI> consultoresNoAsignados = new ArrayList<>();
    private List<ConsultorAgendaGrillaUI> consultoresAsignados = new ArrayList<>();
    private int semana;
    private int mes;
    private int anio;
    
    @PostConstruct
    public void init() {
        // Obtener los parámetros de anio, mes y semana desde la URL en un solo lugar
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        try {
            // Verificar y obtener los parámetros de la URL
            String anioParam = request.getParameter("anio");
            String mesParam = request.getParameter("mes");
            String semanaParam = request.getParameter("semana");

            // Si los parámetros existen, parsearlos y asignarlos
            if (anioParam != null) {
                this.anio = Integer.parseInt(anioParam);
            }

            if (mesParam != null) {
                this.mes = Integer.parseInt(mesParam);
            }

            if (semanaParam != null) {
                this.semana = Integer.parseInt(semanaParam);
            }
            
            // Llamar al método para cargar los consultores no asignados a la agenda
            this.consultoresNoAsignados = cargarConsultoresNoAsignados(anio, semana);
            // Llamar al método para cargar los consultores asignados a la agenda
            this.consultoresAsignados = cargarConsultoresAsignados(anio, semana);

        } catch (ConfigurarAgendaException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar los consultores", e.getMessage()));
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al parsear los parámetros de la URL", e.getMessage()));
        }
    }

    // Getters y setters para los atributos
    public ControladorConfigurarAgenda getControladorConfigurarAgenda() {
        return controladorConfigurarAgenda;
    }

    public void setControladorConfigurarAgenda(ControladorConfigurarAgenda controladorConfigurarAgenda) {
        this.controladorConfigurarAgenda = controladorConfigurarAgenda;
    }

    public List<ConsultorAgendaGrillaUI> getConsultoresAsignados() {
        return consultoresAsignados;
    }

    public void setConsultoresAsignados(List<ConsultorAgendaGrillaUI> consultoresAsignados) {
        this.consultoresAsignados = consultoresAsignados;
    }

    public List<ConsultorGrillaUI> getConsultoresNoAsignados() {
        return consultoresNoAsignados;
    }

    public void setConsultoresNoAsignados(List<ConsultorGrillaUI> consultoresNoAsignados) {
        this.consultoresNoAsignados = consultoresNoAsignados;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    
    // Método para buscar los consultores
    public List<ConsultorGrillaUI> cargarConsultoresNoAsignados(int anio, int semana) throws ConfigurarAgendaException{
        System.out.println("Buscando consultores...");
        
        List<DTOConsultor> dtoConsultores = controladorConfigurarAgenda.buscarConsultoresNoAsignados(anio, semana); // Método existente para buscar consultores
        System.out.println("Consultores encontrados: " + dtoConsultores.size());
        
        List<ConsultorGrillaUI> consultoresGrilla = new ArrayList<>();
        
        // Iterar sobre los DTOs de consultores y crear los UI correspondientes
        for (DTOConsultor dto : dtoConsultores) {
            ConsultorGrillaUI consultorNoAsignado = new ConsultorGrillaUI();
            consultorNoAsignado.setLegajoConsultor(dto.getLegajoConsultor());
            consultorNoAsignado.setNombreApellidoConsultor(dto.getNombreApellidoConsultor());
            consultoresGrilla.add(consultorNoAsignado); // Agregar a la lista de consultores de la UI
            
            // Imprimir información del consultor
            System.out.println("Consultor - Legajo: " + dto.getLegajoConsultor());
            System.out.println("Consultor - Nombre y Apellido: " + dto.getNombreApellidoConsultor());
        }
        
        consultoresNoAsignados = consultoresGrilla;
                
        return consultoresGrilla;
    }
    
    public List<ConsultorAgendaGrillaUI> cargarConsultoresAsignados(int anio, int semana) throws ConfigurarAgendaException {
        // Obtener la lista de consultores asignados de la agenda
        List<DTOConsultorAgenda> dtoConsultoresAsignados = controladorConfigurarAgenda.buscarConsultoresAsignados(anio, semana);

        List<ConsultorAgendaGrillaUI> consultoresAgendaGrilla = new ArrayList<>();

        // Recorrer la lista de DTOConsultorAgenda y convertir a ConsultorAgendaGrillaUI
        for (DTOConsultorAgenda dto : dtoConsultoresAsignados) {
            ConsultorAgendaGrillaUI consultorUI = new ConsultorAgendaGrillaUI();
            consultorUI.setLegajoConsultor(dto.getLegajoConsultor());
            consultorUI.setNombreApellidoConsultor(dto.getNombreApellidoConsultor());
            // Puedes agregar otros campos que sean necesarios
            consultoresAgendaGrilla.add(consultorUI);
        }

        // Asignar la lista convertida a consultoresAsignados
        consultoresAsignados = consultoresAgendaGrilla;

        // Retornar la lista de consultores convertidos
        return consultoresAgendaGrilla;
    }
    
    // Nuevo método para dar de baja un consultor de la agenda
    public void bajaConsultorDeAgenda(int semana, int anio, ConsultorAgendaGrillaUI consultor) {
        try {
            // Verificamos que el consultor no sea nulo
            if (consultor != null) {
                // Imprimir los valores de semana y año para depuración
                System.out.println("Semana recibida: " + semana);
                System.out.println("Año recibido: " + anio);
                System.out.println("Legajo del consultor: " + consultor.getLegajoConsultor());
                System.out.println("Nombre del consultor: " + consultor.getNombreApellidoConsultor());

                // Llama al controlador para eliminar la relación en la agenda
                controladorConfigurarAgenda.darDeBajaConsultorDeAgenda(semana, anio, consultor.getLegajoConsultor());

                // Agregamos un mensaje de éxito a la interfaz
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage("Consultor dado de baja de la agenda: " + consultor.getNombreApellidoConsultor()));

                // **NO** eliminamos el consultor de la lista de UI
            } else {
                System.out.println("Error: El consultor es nulo");
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El consultor es nulo", ""));
            }
        } catch (ConfigurarAgendaException e) {
            System.out.println("Error al dar de baja consultor: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al dar de baja consultor", e.getMessage()));
        }
    }
    
    // Acción para agregar un consultor a la agenda
    public void agregarAAgenda(int anio, int semana, ConsultorGrillaUI consultor) {
        System.out.println("Método agregarAAgenda llamado");
        try {
            controladorConfigurarAgenda.agregarConsultores(semana, anio, consultor.getLegajoConsultor());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Consultor agregado a la agenda: " + consultor.getNombreApellidoConsultor()));
        } catch (ConfigurarAgendaException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al agregar consultor", e.getMessage()));
        }
    }
    
    // Volver a la lista de agendas
    public String volverAgenda(int anio, int mes) {
        // Redirigir a la página ConfigurarAgenda con los parámetros de anio y mes
        return "configurarAgenda.xhtml?faces-redirect=true&anio=" + anio + "&mes=" + mes;
    }


}
