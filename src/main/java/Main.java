import SolicitarTramiteWeb.dtos.DTOCategoriaTramite;
import SolicitarTramiteWeb.exceptions.ExpertoSolicitarTramiteWeb;
import utils.EjemplosPersistencia;

public class Main {

    public static void main(String[] args) {
        EjemplosPersistencia ejemplos = new EjemplosPersistencia();
        ejemplos.crearElementos();
        /*
        // Crear una instancia de ExpertoSolicitarTramiteWeb
        ExpertoSolicitarTramiteWeb experto = new ExpertoSolicitarTramiteWeb();
        // Elegir una categoría de trámite
        String nombreCategoriaTramite = "Renovacion";
        DTOCategoriaTramite categoriaTramite = experto.elegirCategoriaTramite(nombreCategoriaTramite);
        System.out.println("Categoría elegida: " + categoriaTramite.getNombreCategoriaTramite());*/
    }


}


 
/*
    -public static void main(String[] args) {
        // Crear una instancia de ExpertoSolicitarTramiteWeb
        ExpertoSolicitarTramiteWeb experto = new ExpertoSolicitarTramiteWeb();

        // Elegir una categoría de trámite
        String nombreCategoria = "Financiero";
        DTOCategoriaTramite categoriaTramite = experto.elegirCategoriaTramite(nombreCategoria);
        System.out.println("Categoría elegida: " + categoriaTramite.getNombre());

        // Elegir un tipo de trámite
        String nombreTipoTramite = "Pago de impuestos";
        DTOTipoTramiteWeb tipoTramite = experto.elegirTipoTramite(nombreTipoTramite);
        System.out.println("Tipo de trámite elegido: " + tipoTramite.getNombre());

        // Obtener información del cliente por CUIT
        int CUITCliente = 123456789;
        DTOCliente cliente = experto.obtenerCliente(CUITCliente);
        System.out.println("Cliente obtenido con CUIT: " + cliente.getCUIT());

        // Solicitar un trámite
        DTOTramiteWeb tramite = experto.solicitarTramite();
        System.out.println("Estado del trámite solicitado: " + tramite.getEstado());
    

*/