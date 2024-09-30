/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RC_2.beans;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named("uiDestinosPosibles")
@ViewScoped
public class UIdestinosPosibles implements Serializable {
    private int codEstadoTramite;
    private List<String> nombreEstadoTramiteDestino= new ArrayList<>();
    
    //Map<int, String> estadosDestinos = new HashMap<>();
    
    

    
    
}
