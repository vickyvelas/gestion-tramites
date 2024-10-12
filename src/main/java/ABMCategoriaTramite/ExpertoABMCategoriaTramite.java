/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCategoriaTramite;

import ABMCategoriaTramite.dtos.DTOCategoriaTramite;
import ABMCategoriaTramite.dtos.DTOModificarCategoriaTramite;
import ABMCategoriaTramite.dtos.DTOModificarCategoriaTramiteIn;
import ABMCategoriaTramite.dtos.DTONuevaCategoriaTramite;
import ABMCategoriaTramite.exceptions.CategoriaTramiteException;
import entidades.CategoriaTramite;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;

/**
 *
 * @author vickyvelasco
 */
public class ExpertoABMCategoriaTramite {
    public List<DTOCategoriaTramite> buscarCategorias(int codCategoriaTramite, String nombreCategoriaTramite) {
        List<DTOCriterio> lCriterio = new ArrayList<>();

        if (codCategoriaTramite > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("codCategoriaTramite");
            unCriterio.setOperacion("=");
            unCriterio.setValor(codCategoriaTramite);
            lCriterio.add(unCriterio);
        }

        if (nombreCategoriaTramite.trim().length() > 0) {
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("nombreCategoriaTramite");
            unCriterio.setOperacion("like");
            unCriterio.setValor(nombreCategoriaTramite);
            lCriterio.add(unCriterio);
        }

        List objetoList = FachadaPersistencia.getInstance().buscar("CategoriaTramite", lCriterio);
        List<DTOCategoriaTramite> categoriasResultado = new ArrayList<>();

        for (Object x : objetoList) {
            CategoriaTramite categoria = (CategoriaTramite) x;
            DTOCategoriaTramite categoriaDTO = new DTOCategoriaTramite();
            categoriaDTO.setCodCategoriaTramite(categoria.getCodCategoriaTramite());
            categoriaDTO.setNombreCategoriaTramite(categoria.getNombreCategoriaTramite());
            categoriaDTO.setFechaHoraBajaCategoriaTramite(categoria.getFechaHoraBajaCategoriaTramite());
            categoriasResultado.add(categoriaDTO);
        }

        return categoriasResultado;
    }

    // Método para agregar una nueva categoría
    public void agregarCategoria(DTONuevaCategoriaTramite dtoNuevaCategoriaTramite) throws CategoriaTramiteException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(dtoNuevaCategoriaTramite.getCodCategoriaTramite());

        criterioList.add(dto);

        DTOCriterio dto2 = new DTOCriterio();
        dto2.setAtributo("fechaHoraBajaCategoriaTramite");
        dto2.setOperacion("=");
        dto2.setValor(null);
        criterioList.add(dto2);

        List lCategoria = FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList);

        if (lCategoria.size() > 0) {
            throw new CategoriaTramiteException("El código de la categoría ya existe");
        } else {
            CategoriaTramite categoria = new CategoriaTramite();
            categoria.setCodCategoriaTramite(dtoNuevaCategoriaTramite.getCodCategoriaTramite());
            categoria.setNombreCategoriaTramite(dtoNuevaCategoriaTramite.getNombreCategoriaTramite());

            FachadaPersistencia.getInstance().guardar(categoria);
            FachadaPersistencia.getInstance().finalizarTransaccion();
        }
    }

    // Método para buscar una categoría por código para modificar
    public DTOModificarCategoriaTramite buscarCategoriaAModificar(int codCategoriaTramite) {
        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(codCategoriaTramite);

        criterioList.add(dto);

        CategoriaTramite categoriaEncontrada = (CategoriaTramite) FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList).get(0);

        DTOModificarCategoriaTramite dtoModificarCategoria = new DTOModificarCategoriaTramite();
        dtoModificarCategoria.setCodCategoriaTramite(categoriaEncontrada.getCodCategoriaTramite());
        dtoModificarCategoria.setNombreCategoriaTramite(categoriaEncontrada.getNombreCategoriaTramite());

        return dtoModificarCategoria;
    }

    // Método para modificar una categoría
    public void modificarCategoria(DTOModificarCategoriaTramiteIn dtoModificarCategoriaIn) {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        dto.setAtributo("codCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(dtoModificarCategoriaIn.getCodCategoriaTramite());

        criterioList.add(dto);

        CategoriaTramite categoriaEncontrada = (CategoriaTramite) FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList).get(0);

        // Solo se modifica el nombre de la categoría
        categoriaEncontrada.setNombreCategoriaTramite(dtoModificarCategoriaIn.getNombreCategoriaTramite());

        FachadaPersistencia.getInstance().guardar(categoriaEncontrada);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

    // Método para dar de baja una categoría
    public void darDeBajaCategoria(int codCategoriaTramite) throws CategoriaTramiteException {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        List<DTOCriterio> criterioList = new ArrayList<>();
        DTOCriterio dto = new DTOCriterio();
        
        dto.setAtributo("codCategoriaTramite");
        dto.setOperacion("=");
        dto.setValor(codCategoriaTramite);

        criterioList.add(dto);

        CategoriaTramite categoriaEncontrada = (CategoriaTramite) FachadaPersistencia.getInstance().buscar("CategoriaTramite", criterioList).get(0);

        // Verificar si la categoría está en uso
        /*criterioList.clear();
        dto = new DTOCriterio();
        dto.setAtributo("categoriaTramite");
        dto.setOperacion("=");
        dto.setValor(categoriaEncontrada);

        criterioList.add(dto);

        List objetoList = FachadaPersistencia.getInstance().buscar("TipoTramite", criterioList);

        if (objetoList.size() != 0) {
            throw new CategoriaTramiteException("La categoría no puede darse de baja porque está asignada a tipos trámites.");
        }*/

        categoriaEncontrada.setFechaHoraBajaCategoriaTramite(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(categoriaEncontrada);
        FachadaPersistencia.getInstance().finalizarTransaccion();
    }

}
