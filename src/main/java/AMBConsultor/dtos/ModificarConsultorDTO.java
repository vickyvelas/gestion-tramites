package AMBConsultor.dtos;

/**
 *
 * @author adrie
 */

public class ModificarConsultorDTO  {

    private int consultorNroMaxTramite;
    private int legajoConsultor;
    private String nombreApellidoConsultor;

    public int getConsultorNroMaxTramite() {
        return consultorNroMaxTramite;
    }

    public void setConsultorNroMaxTramite(int consultorNroMaxTramite) {
        this.consultorNroMaxTramite = consultorNroMaxTramite;
    }
    
     public int getLegajoConsultor() {
            return legajoConsultor;
    }
    public void setLegajoConsultor(int legajoConsultor) {
        this.legajoConsultor = legajoConsultor; 
    
    }
    
    public String getNombreApellidoConsultor(){
        return nombreApellidoConsultor;
    }
    
    public void setNombreApellidoConsultor(String nombreApellidoConsultor){
        this.nombreApellidoConsultor = nombreApellidoConsultor;
    }      
}