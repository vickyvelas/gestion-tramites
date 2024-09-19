/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMCategoriaTramite.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vickyvelasco
 */
public class DTOTablaCategoriaTramite {
    private List<DTOCategoriaTramite> categoriaTramitelist = new ArrayList<DTOCategoriaTramite>();
    

    public List<DTOCategoriaTramite> getCategoriaTramitelist() {
        return categoriaTramitelist;
    }

    public void setCategoriaTramitelist(List<DTOCategoriaTramite> categoriaTramitelist) {
        this.categoriaTramitelist = categoriaTramitelist;
    }
    
    public void addCategoriaTramite(DTOCategoriaTramite categoriaTramite){
        categoriaTramitelist.add(categoriaTramite);
    }

    
}
