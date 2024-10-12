package ABMDocumentacion.beans;

import ABMDocumentacion.ControladorABMDocumentacion;
import ABMDocumentacion.dtos.ModificarDocumentacionDTO;
import ABMDocumentacion.dtos.ModificarDocumentacionDTOIn;
import ABMDocumentacion.dtos.NuevaDocumentacionDTO;
import ABMDocumentacion.exceptions.ABMDocumentacionException;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import jakarta.servlet.http.HttpServletRequest;

@Named("uiabmDocumentacion")
@ViewScoped
public class UIABMDocumentacion implements Serializable {

    private ControladorABMDocumentacion controladorABMDocumentacion = new ControladorABMDocumentacion();
    private boolean insert;
    private String nombreDocumentacion;
    private int codDocumentacion;

    // Constructor que inicializa el formulario dependiendo de si es agregar o modificar
    public UIABMDocumentacion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        insert = true;
        if (codigo > 0) {
            insert = false;
            ModificarDocumentacionDTO modificarDocumentacionDTO = controladorABMDocumentacion.buscarDocumentacionAModificar(codigo);
            setNombreDocumentacion(modificarDocumentacionDTO.getNombreDocumentacion());
            setCodDocumentacion(modificarDocumentacionDTO.getCodDocumentacion());
        }
    }

    // Método para agregar o modificar una documentación
    public String agregarModificarDocumentacion() throws ABMDocumentacionException {
        if (insert) {
            NuevaDocumentacionDTO nuevaDocumentacionDTO = new NuevaDocumentacionDTO();
            nuevaDocumentacionDTO.setCodDocumentacion(getCodDocumentacion());
            nuevaDocumentacionDTO.setNombreDocumentacion(getNombreDocumentacion());
            controladorABMDocumentacion.agregarDocumentacion(nuevaDocumentacionDTO);
        } else {
            ModificarDocumentacionDTOIn modificarDocumentacionDTOIn = new ModificarDocumentacionDTOIn();
            modificarDocumentacionDTOIn.setCodDocumentacion(getCodDocumentacion());
            modificarDocumentacionDTOIn.setNombreDocumentacion(getNombreDocumentacion());
            controladorABMDocumentacion.modificarDocumentacion(modificarDocumentacionDTOIn);
        }

        return "documentacionLista.xhtml?faces-redirect=true";
    }

    // Getters y Setters
    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public String getNombreDocumentacion() {
        return nombreDocumentacion;
    }

    public void setNombreDocumentacion(String nombreDocumentacion) {
        this.nombreDocumentacion = nombreDocumentacion;
    }

    public int getCodDocumentacion() {
        return codDocumentacion;
    }

    public void setCodDocumentacion(int codDocumentacion) {
        this.codDocumentacion = codDocumentacion;
    }
}
