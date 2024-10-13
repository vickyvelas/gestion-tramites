package ABMDocumentacion;

import ABMDocumentacion.dtos.DocumentacionDTO;
import ABMDocumentacion.dtos.ModificarDocumentacionDTO;
import ABMDocumentacion.dtos.ModificarDocumentacionDTOIn;
import ABMDocumentacion.dtos.NuevaDocumentacionDTO;
import ABMDocumentacion.exceptions.ABMDocumentacionException;
import entidades.Documentacion;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;


public class ExpertoABMDocumentacion {

    // Método para buscar documentación por código o nombre
    public List<DocumentacionDTO> buscarDocumentacion(int codDocumentacion, String nombreDocumentacion) {
        List<DTOCriterio> lCriterio = new ArrayList<>();

        if (codDocumentacion > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("codDocumentacion");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codDocumentacion);
            lCriterio.add(unCriterio);
        }

        if(nombreDocumentacion != null && !nombreDocumentacion.trim().isEmpty()) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreDocumentacion");
            unCriterio.setOperacion("like");
            unCriterio.setValor("%" + nombreDocumentacion + "%");
            lCriterio.add(unCriterio);
        }

        List<Object> listaDocumentacion = FachadaPersistencia.getInstance().buscar("Documentacion", lCriterio);
        List<DocumentacionDTO> documentacionResultado = new ArrayList<>();

        for (Object obj : listaDocumentacion) {
            Documentacion documentacion = (Documentacion) obj;
            DocumentacionDTO documentacionDTO = new DocumentacionDTO();
            documentacionDTO.setCodDocumentacion(documentacion.getCodDocumentacion());
            documentacionDTO.setNombreDocumentacion(documentacion.getNombreDocumentacion());
            documentacionDTO.setFechaHoraBajaDocumentacion(documentacion.getFechaHoraBajaDocumentacion());
            documentacionResultado.add(documentacionDTO);
        }

        return documentacionResultado;
    }

    // Método para agregar una nueva documentación
    public void agregarDocumentacion(NuevaDocumentacionDTO nuevaDocumentacionDTO) throws ABMDocumentacionException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCod = new DTOCriterio();
        criterioCod.setAtributo("codDocumentacion");
        criterioCod.setOperacion("=");
        criterioCod.setValor(nuevaDocumentacionDTO.getCodDocumentacion());

        criterioList.add(criterioCod);
        
        List<Object> lDocumentacion = FachadaPersistencia.getInstance().buscar("Documentacion", criterioList);

        if (!lDocumentacion.isEmpty()) {
            throw new ABMDocumentacionException("El código de documentación ya existe.");
        } else {
            Documentacion documentacion = new Documentacion();
            documentacion.setCodDocumentacion(nuevaDocumentacionDTO.getCodDocumentacion());
            documentacion.setNombreDocumentacion(nuevaDocumentacionDTO.getNombreDocumentacion());

            FachadaPersistencia.getInstance().guardar(documentacion);
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }

    // Método para buscar una documentación por código para modificar
    public ModificarDocumentacionDTO buscarDocumentacionAModificar(int codDocumentacion) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCod = new DTOCriterio();
        criterioCod.setAtributo("codDocumentacion");
        criterioCod.setOperacion("=");
        criterioCod.setValor(codDocumentacion);

        Documentacion documentacion = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);

        ModificarDocumentacionDTO modificarDocumentacionDTO = new ModificarDocumentacionDTO();
        modificarDocumentacionDTO.setCodDocumentacion(documentacion.getCodDocumentacion());
        modificarDocumentacionDTO.setNombreDocumentacion(documentacion.getNombreDocumentacion());

        return modificarDocumentacionDTO;
    }

    
    // Método para modificar la documentación
    public void modificarDocumentacion(ModificarDocumentacionDTOIn modificarDocumentacionDTOIn) throws ABMDocumentacionException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        // Buscar la documentación actual
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCod = new DTOCriterio();
        criterioCod.setAtributo("codDocumentacion");
        criterioCod.setOperacion("=");
        criterioCod.setValor(modificarDocumentacionDTOIn.getCodDocumentacion());

        criterioList.add(criterioCod);

        Documentacion documentacion = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);

        // Verificar si el nuevo nombre ya está en uso por otra documentación
        List<DTOCriterio> criterioNombre = new ArrayList<>();
        DTOCriterio criterioNombreDoc = new DTOCriterio();
        criterioNombreDoc.setAtributo("nombreDocumentacion");
        criterioNombreDoc.setOperacion("=");
        criterioNombreDoc.setValor(modificarDocumentacionDTOIn.getNombreDocumentacion());
        criterioNombre.add(criterioNombreDoc);

        List<Object> lDocumentacionConMismoNombre = FachadaPersistencia.getInstance().buscar("Documentacion", criterioNombre);

        if (!lDocumentacionConMismoNombre.isEmpty()) {
            Documentacion otraDocumentacion = (Documentacion) lDocumentacionConMismoNombre.get(0);

            // Si el nombre ya existe y pertenece a otra documentación
            if (otraDocumentacion.getCodDocumentacion() != modificarDocumentacionDTOIn.getCodDocumentacion()) {
                throw new ABMDocumentacionException("El nombre de la documentación ya está en uso por otra documentación.");
            }
        }

        // Si el nombre no está en uso o pertenece a la misma documentación, proceder con la modificación
        documentacion.setNombreDocumentacion(modificarDocumentacionDTOIn.getNombreDocumentacion());

        FachadaPersistencia.getInstance().guardar(documentacion);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }


    // Método para dar de baja una documentación
    public void darDeBajaDocumentacion(int codDocumentacion) throws ABMDocumentacionException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio criterioCod = new DTOCriterio();
        criterioCod.setAtributo("codDocumentacion");
        criterioCod.setOperacion("=");
        criterioCod.setValor(codDocumentacion);
        criterioList.add(criterioCod);

        Documentacion documentacion = (Documentacion) FachadaPersistencia.getInstance().buscar("Documentacion", criterioList).get(0);

        // Marcar la documentación como dada de baja
        documentacion.setFechaHoraBajaDocumentacion(new Timestamp(System.currentTimeMillis()));
        
        FachadaPersistencia.getInstance().guardar(documentacion);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }
}
