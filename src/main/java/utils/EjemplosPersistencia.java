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
/**
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
     
*/
/**
        ListaPrecio lista1 = new ListaPrecio();
        lista1.setFechaHoraDesdeListaPrecio(new Timestamp(System.currentTimeMillis()));
        lista1.setFechaHoraHastaListaPrecio(new Timestamp(System.currentTimeMillis()));
        lista1.setFechaHoraBajaListaPrecio(null);
        
        DetallePrecioTipoTramite detalleLista1 = new DetallePrecioTipoTramite();
        detalleLista1.setPrecioTipoTramite(1500);
        
        
        
        Cliente cliente1=new Cliente();
        cliente1.setCuitCliente(44567890);
        cliente1.setDireccionCliente("Alvear 35 B");
        cliente1.setFechaHoraAltaCliente(new Timestamp(System.currentTimeMillis()));
        cliente1.setMailCliente("carolinaMartin@gmail.com");
        cliente1.setFechaHoraBajaCliente(null);
        cliente1.setNombreApellidoCliente("Carolina Martin");
        cliente1.setTelefonoCliente(261568884);
        
        
        TramiteEstado tramEstado=new TramiteEstado();
        tramEstado.setFechaHoraBajaTramiteEstado(null);
        tramEstado.setFechaHoraDesdeTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramEstado.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramEstado.setNroLineaTramiteEstado(1);
        tramEstado.setObservacionTramiteEstado("Este es el primer estado");
        
        
        EstadoTramite estado = new EstadoTramite();
        estado.setCodEstadoTramite(1);
        estado.setFechaHoraBajaEstadoTramite(null);
        estado.setNombreEstadoTramite("Iniciado");
        */  

        
        
        
    }
}