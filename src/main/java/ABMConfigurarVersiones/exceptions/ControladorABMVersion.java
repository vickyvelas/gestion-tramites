/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ABMConfigurarVersiones.exceptions;

import ABMConfigurarVersiones.dtos.DTOVersionHistorial;
import ABMConfigurarVersiones.dtos.DTOVersionInput;
import java.util.List;

/**
 *
 * @author julie
 */
public class ControladorABMVersion {
    private ExpertoABMVersion expertoABMVersion = new ExpertoABMVersion();
    public List<DTOVersionHistorial> buscarVersionHistorial(int codigo, String nombre){
        return expertoABMVersion.buscarVersionHistorial(codigo,nombre);
    }

    public void agregarVersion(DTOVersionInput nuevaVersionDTO) throws VersionException{
        expertoABMVersion.agregarVersion(nuevaVersionDTO);
    }



    public void darDeBajaVersion(int codigo) throws VersionException {
        expertoABMVersion.darDeBajaVersion(codigo);
    }
    
}
