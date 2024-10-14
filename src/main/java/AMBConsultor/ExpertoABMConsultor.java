package AMBConsultor;

import AMBConsultor.dtos.ConsultorDTO;
import AMBConsultor.dtos.ModificarConsultorDTO;
import AMBConsultor.dtos.ModificarConsultorDTOIn;
import AMBConsultor.dtos.NuevoConsultorDTO;
import AMBConsultor.exceptions.ConsultorException;
import entidades.Agenda;
import entidades.Consultor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;


/**
 *
 * @author adrie
 */
public class ExpertoABMConsultor  {
    
    // Método para buscar consultores por legajo o nombre
    public List<ConsultorDTO> buscarConsultores(int legajoConsultor, String nombreApellidoConsultor) {
        List<DTOCriterio> lCriterio=new ArrayList<DTOCriterio>();
        if (legajoConsultor > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("legajoConsultor");
            unCriterio.setOperacion("=");
            unCriterio.setValor(legajoConsultor);
            lCriterio.add(unCriterio);
        }

        if(nombreApellidoConsultor.trim().length()>0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreApellidoConsultor");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreApellidoConsultor);
            lCriterio.add(unCriterio);
        }

        List objetoList = FachadaPersistencia.getInstance().buscar("Consultor", lCriterio);
        List<ConsultorDTO> consultoresResultado = new ArrayList<>();

        for (Object x : objetoList) {
            Consultor consultor = (Consultor) x;
            ConsultorDTO consultorDTO = new ConsultorDTO();
            consultorDTO.setLegajoConsultor(consultor.getLegajoConsultor());
            consultorDTO.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
            consultorDTO.setConsultorNroMaxTramite(consultor.getConsultorNroMaxTramite());
            consultorDTO.setFechaHoraBajaConsultor(consultor.getFechaHoraBajaConsultor());
            consultoresResultado.add(consultorDTO);
        }

        return consultoresResultado;
    }
    // Método para agregar un nuevo consultor
    public void agregarConsultor(NuevoConsultorDTO nuevoConsultorDTO) throws ConsultorException {
    FachadaPersistencia.getInstance().iniciarTransaccion();

    // Validación de que el legajo del consultor sea mayor a 0
    if (nuevoConsultorDTO.getLegajoConsultor() <= 0) {
        throw new ConsultorException("El legajo del consultor debe ser mayor a cero");
    }

    // Validación de la cantidad máxima de trámites
    if (nuevoConsultorDTO.getConsultorNroMaxTramite() <= 0) {
        throw new ConsultorException("La cantidad máxima de trámites debe ser mayor a cero");
    }

    List<DTOCriterio> criterioList = new ArrayList<>();
    DTOCriterio dto = new DTOCriterio();
    dto.setAtributo("legajoConsultor");
    dto.setOperacion("=");
    dto.setValor(nuevoConsultorDTO.getLegajoConsultor());

    criterioList.add(dto);
    DTOCriterio dto2 = new DTOCriterio();
    dto2.setAtributo("fechaHoraBajaConsultor");
    dto2.setOperacion("=");
    dto2.setValor(null);
    criterioList.add(dto2);

    List lConsultor = FachadaPersistencia.getInstance().buscar("Consultor", criterioList);

    if (lConsultor.size() > 0) {
        throw new ConsultorException("El legajo del consultor ya existe");
    } else {
        Consultor consultor = new Consultor();
        consultor.setLegajoConsultor(nuevoConsultorDTO.getLegajoConsultor());
        consultor.setNombreApellidoConsultor(nuevoConsultorDTO.getNombreApellidoConsultor());
        consultor.setConsultorNroMaxTramite(nuevoConsultorDTO.getConsultorNroMaxTramite());

        FachadaPersistencia.getInstance().guardar(consultor);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}


    // Método para buscar un consultor por legajo y obtener el DTO para modificar
    public ModificarConsultorDTO buscarConsultorAModificar(int legajoConsultor) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("legajoConsultor");
        dto.setOperacion("=");
        dto.setValor(legajoConsultor);

        criterioList.add(dto);

        Consultor consultorEncontrado = (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", criterioList).get(0);

        ModificarConsultorDTO modificarConsultorDTO = new ModificarConsultorDTO();
        modificarConsultorDTO.setLegajoConsultor(consultorEncontrado.getLegajoConsultor());
        modificarConsultorDTO.setNombreApellidoConsultor(consultorEncontrado.getNombreApellidoConsultor());
        modificarConsultorDTO.setConsultorNroMaxTramite(consultorEncontrado.getConsultorNroMaxTramite());

        return modificarConsultorDTO;
    }

    // Método para modificar solo el nroMaxTramite del consultor
    public void modificarConsultor(ModificarConsultorDTOIn modificarConsultorDTOIn) {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();

        dto.setAtributo("legajoConsultor");
        dto.setOperacion("=");
        dto.setValor(modificarConsultorDTOIn.getLegajoConsultor());

        criterioList.add(dto);

        Consultor consultorEncontrado = (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", criterioList).get(0);

        // Solo se modifica el nroMaxTramite
        consultorEncontrado.setConsultorNroMaxTramite(modificarConsultorDTOIn.getConsultorNroMaxTramite());

        FachadaPersistencia.getInstance().guardar(consultorEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
    
    public void darDeBajaConsultor(int legajoConsultor) throws ConsultorException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Crear el criterio para buscar el consultor por legajo
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("legajoConsultor");
        dto.setOperacion("=");
        dto.setValor(legajoConsultor);
        criterioList.add(dto);

        // Buscar el consultor
        Consultor consultorEncontrado = (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", criterioList).get(0);

        // Verificar si el consultor está en alguna agenda activa
        criterioList.clear();
        DTOCriterio dtoFecha = new DTOCriterio();
        dtoFecha.setAtributo("fechaHastaAgenda");
        dtoFecha.setOperacion(">");
        dtoFecha.setValor(new Date()); // Fecha actual
        criterioList.add(dtoFecha);

        // Buscar agendas activas relacionadas con el consultor
        List<Object> agendasRelacionadas = FachadaPersistencia.getInstance().buscar("Agenda", criterioList);

        for (Object obj : agendasRelacionadas) {
            Agenda agenda = (Agenda) obj; // Realizar el cast a Agenda
            if (agenda.getConsultores().contains(consultorEncontrado)) {
                // Si el consultor está en alguna agenda activa, lanzar excepción
                throw new ConsultorException("El consultor no puede darse de baja porque está agregado a una agenda activa.");
            }
        }

        // Verificar si el consultor está asignado a algún trámite no finalizado
        criterioList.clear();
        dto = new DTOCriterio();
        dto.setAtributo("consultor");
        dto.setOperacion("=");
        dto.setValor(consultorEncontrado);
        criterioList.add(dto);

        DTOCriterio dto2 = new DTOCriterio();
        dto2.setAtributo("fechaHoraFinTramite");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);

        List<Object> tramitesRelacionados = FachadaPersistencia.getInstance().buscar("Tramite", criterioList);

        if (!tramitesRelacionados.isEmpty()) {
            // Si hay trámites no finalizados, lanzar excepción
            throw new ConsultorException("El consultor no puede darse de baja porque está asignado a trámites.");
        }

        // Dar de baja al consultor
        consultorEncontrado.setFechaHoraBajaConsultor(new Timestamp(System.currentTimeMillis()));
        FachadaPersistencia.getInstance().guardar(consultorEncontrado);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}

