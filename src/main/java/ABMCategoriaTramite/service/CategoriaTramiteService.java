/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCategoriaTramite.service;


/**
 *
 * @author vickyvelasco
 */
public class CategoriaTramiteService implements Serializable {
    private List<CategoriaTramite> allCategorias = new ArrayList<>();
    
    // Método para agregar una nueva categoría
    public void agregar(CategoriaTramite ct) {
        allCategorias.add(ct);
    }
    
    // Método para eliminar una categoría
    public void eliminar(CategoriaTramite ct) {
        allCategorias.remove(ct);
    }
    
    // Método para editar una categoría (en este caso reemplaza la existente)
    public void editar(CategoriaTramite ct) {
        eliminar(ct);  // Elimina la existente
        agregar(ct);   // Agrega la nueva versión editada
    }
    
    // Método para buscar una categoría por código
    public CategoriaTramite buscarPorCodigo(int codigo) {
        return allCategorias.stream()
                            .filter(ct -> ct.getCodCategoriaTramite() == codigo)
                            .findFirst()
                            .orElse(null);
    }

    // Método para ordenar las categorías por código
    public List<CategoriaTramite> ordenarPorCodigo() {
        allCategorias.sort(Comparator.comparingInt(CategoriaTramite::getCodCategoriaTramite));
        return allCategorias;
    }

    // Método para obtener todas las categorías
    public List<CategoriaTramite> obtenerTodas() {
        return allCategorias;
    }
    
}
