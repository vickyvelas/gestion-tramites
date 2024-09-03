package utils;


import entidades.*;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EjemplosPersistencia {

    /**
     * Esto es solo para que se use de ejemplo.
     *
     * Hay que tener en cuenta que la mayoria de los metodos se basan en un camino feliz en el que los objetos
     * ya se encuentran persistidos en la base de datos y no se hace una comprobacion previa.
     *
     * Se recomienda llamar a crearElementos() para que se creen todos los objetos necesarios para los siguientes
     * metodos.
     *
     * Cualquier cosa, consultar a: minellinh@gmail.com
     */
    public void Metodo() {
        // Reemplazar la siguiente linea con el metodo que se desea probar.
        crearElementos();
    }

    /**
     * Crea un estado, un articulo con dicho estado, una reposicion con dicho estado y un detalle para esa reposicion.
     */
    public void crearElementos() {
        FachadaPersistencia.getInstance().iniciarTransaccion();

        Agenda primera = new Agenda();
        primera.setAnio(2024);
        primera.setSemana(34);
        primera.setFechaDesdeAgenda(new Timestamp(System.currentTimeMillis()));
        primera.setFechaHastaAgenda(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(primera);

        Consultor consultor = new Consultor();
        consultor.setLegajoConsultor(44902);
        consultor.setNombreApellidoConsultor("Emilio Amin");
        consultor.setConsultorNroMaxTramite(10);
        consultor.setFechaHoraBajaConsultor(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(consultor);

        Consultor consultor2 = new Consultor();
        consultor2.setLegajoConsultor(44903);
        consultor2.setNombreApellidoConsultor("Marcos");
        consultor2.setConsultorNroMaxTramite(13);
        consultor2.setFechaHoraBajaConsultor(null);

        FachadaPersistencia.getInstance().guardar(consultor2);
        
        FachadaPersistencia.getInstance().finalizarTransaccion();
        
    }
}