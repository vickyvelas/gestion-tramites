package utils;


import entidades.*;


import java.sql.Timestamp;
import java.util.Date;

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
        
        
        // Consultores
        Consultor consultor1 = new Consultor();
        consultor1.setLegajoConsultor(44902);
        consultor1.setNombreApellidoConsultor("Emilio Amin");
        consultor1.setConsultorNroMaxTramite(10);

        FachadaPersistencia.getInstance().guardar(consultor1);

        Consultor consultor2 = new Consultor();
        consultor2.setLegajoConsultor(44903);
        consultor2.setNombreApellidoConsultor("Marcos");
        consultor2.setConsultorNroMaxTramite(1);

        FachadaPersistencia.getInstance().guardar(consultor2);
        
        Consultor consultor3 = new Consultor();
        consultor3.setLegajoConsultor(44904);
        consultor3.setNombreApellidoConsultor("Esteban Quito");
        consultor3.setConsultorNroMaxTramite(5);

        FachadaPersistencia.getInstance().guardar(consultor3);
        
        
        // Agendas ------------------------------------------------------------------------------------
        Agenda agenda = new Agenda();
        agenda.setAnio(2024);
        agenda.setSemana(29);
        agenda.setFechaDesdeAgenda(new Date(2024 - 1900, 6, 1));
        agenda.setFechaHastaAgenda(new Date(2024 - 1900, 6, 7));
        agenda.addConsultor(consultor1);
        agenda.addConsultor(consultor2);
         
        
        FachadaPersistencia.getInstance().guardar(agenda);
        
        Agenda agenda1 = new Agenda();
        agenda1.setAnio(2024);
        agenda1.setSemana(30);
        agenda.setFechaDesdeAgenda(new Date(2024 - 1900, 6, 8));
        agenda.setFechaHastaAgenda(new Date(2024 - 1900, 6, 14));
        agenda1.addConsultor(consultor3); 
        
        FachadaPersistencia.getInstance().guardar(agenda1);
        
        FachadaPersistencia.getInstance().guardar(agenda);
        
        Agenda agenda2 = new Agenda();
        agenda2.setAnio(2024);
        agenda2.setSemana(42);
        agenda2.setFechaDesdeAgenda(new Date(2024 - 1900, 9, 14));
        agenda2.setFechaHastaAgenda(new Date(2024 - 1900, 9, 20));
        agenda2.addConsultor(consultor2);
        agenda2.addConsultor(consultor3);   
        
        FachadaPersistencia.getInstance().guardar(agenda2);
        
        Agenda agenda3 = new Agenda();
        agenda3.setAnio(2024);
        agenda3.setSemana(41);
        agenda3.setFechaDesdeAgenda(new Date(2024 - 1900, 9, 7));
        agenda3.setFechaHastaAgenda(new Date(2024 - 1900, 9, 13));
        agenda3.addConsultor(consultor2);
        agenda3.addConsultor(consultor3);   
        
        FachadaPersistencia.getInstance().guardar(agenda3);

        
        // Clientes -----------------------------------------------------------------------------------
        Cliente cliente1 = new Cliente();
        cliente1.setCuitCliente(20202050);
        cliente1.setDireccionCliente("Patricias Mendocinas 275");
        cliente1.setNombreApellidoCliente("Lucas Perez");
        cliente1.setMailCliente("lucasperez@hotmail.com");
        cliente1.setTelefonoCliente(26199999);
        cliente1.setFechaHoraAltaCliente(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(cliente1);
        
        Cliente cliente2 = new Cliente();
        cliente2.setCuitCliente(20202051);
        cliente2.setDireccionCliente("España 575");
        cliente2.setNombreApellidoCliente("Pedro Sanchez");
        cliente2.setMailCliente("pedritosanchez@hotmail.com");
        cliente2.setTelefonoCliente(26200000);
        cliente2.setFechaHoraAltaCliente(new Timestamp(System.currentTimeMillis()));
        cliente2.setFechaHoraBajaCliente(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(cliente2);
        
        
        // EstadoTramites -----------------------------------------------------------------------------
        EstadoTramite estado1 = new EstadoTramite();
        estado1.setCodEstadoTramite(15);
        estado1.setNombreEstadoTramite("Iniciado");
        
        FachadaPersistencia.getInstance().guardar(estado1);
        
        EstadoTramite estado2 = new EstadoTramite();
        estado2.setCodEstadoTramite(16);
        estado2.setNombreEstadoTramite("Etapa 1");
        
        FachadaPersistencia.getInstance().guardar(estado2);
        
        
        // CategoriaTramites --------------------------------------------------------------------------
        CategoriaTramite categoriaTramite1 = new CategoriaTramite();
        categoriaTramite1.setCodCategoriaTramite(1);
        categoriaTramite1.setNombreCategoriaTramite("DNIs");
        
        FachadaPersistencia.getInstance().guardar(categoriaTramite1);
        
        CategoriaTramite categoriaTramite2 = new CategoriaTramite();
        categoriaTramite2.setCodCategoriaTramite(1);
        categoriaTramite2.setNombreCategoriaTramite("Ciudadanias");
        
        FachadaPersistencia.getInstance().guardar(categoriaTramite2);
        
        
        // Documentaciones ----------------------------------------------------------------------------
        Documentacion documentacion1 = new Documentacion();
        documentacion1.setCodDocumentacion(4);
        documentacion1.setNombreDocumentacion("Partida de Nacimiento");
        
        FachadaPersistencia.getInstance().guardar(documentacion1);
        
        Documentacion documentacion2 = new Documentacion();
        documentacion2.setCodDocumentacion(5);
        documentacion2.setNombreDocumentacion("Pasaporte");
        
        FachadaPersistencia.getInstance().guardar(documentacion2);
        
        Documentacion documentacion3 = new Documentacion();
        documentacion3.setCodDocumentacion(6);
        documentacion3.setNombreDocumentacion("DNI impreso");
        documentacion3.setFechaHoraBajaDocumentacion(new Timestamp(System.currentTimeMillis()));
        
        FachadaPersistencia.getInstance().guardar(documentacion2);
        
        // TipoTramites -------------------------------------------------------------------------------
        TipoTramite tipoTramite1 = new TipoTramite();
        tipoTramite1.setCodTipoTramite(1);
        tipoTramite1.setDescripcionTipoTramite("nose");
        tipoTramite1.setDescripcionTipoTramiteWeb("noseWeb");
        tipoTramite1.setMaxDiaEntrega(20);
        tipoTramite1.setNombreTipoTramite("Nuevo DNI");
        tipoTramite1.setCategoriaTramite(categoriaTramite1);
        tipoTramite1.addDocumentacion(documentacion1);
        
        FachadaPersistencia.getInstance().guardar(tipoTramite1);
        
        TipoTramite tipoTramite2 = new TipoTramite();
        tipoTramite2.setCodTipoTramite(2);
        tipoTramite2.setDescripcionTipoTramite("nose");
        tipoTramite2.setDescripcionTipoTramiteWeb("noseWeb");
        tipoTramite2.setMaxDiaEntrega(25);
        tipoTramite2.setNombreTipoTramite("Ciudadania Italiana");
        tipoTramite2.setCategoriaTramite(categoriaTramite1);
        tipoTramite2.addDocumentacion(documentacion1);
        tipoTramite2.addDocumentacion(documentacion2);
        
        FachadaPersistencia.getInstance().guardar(tipoTramite2);
        
        TipoTramite tipoTramite3 = new TipoTramite();
        tipoTramite3.setCodTipoTramite(3);
        tipoTramite3.setDescripcionTipoTramite("nose");
        tipoTramite3.setDescripcionTipoTramiteWeb("noseWeb");
        tipoTramite3.setMaxDiaEntrega(25);
        tipoTramite3.setNombreTipoTramite("EjemploBaja");
        tipoTramite3.setCategoriaTramite(categoriaTramite1);
        
        FachadaPersistencia.getInstance().guardar(tipoTramite2);
        
        // ListaPrecio --------------------------------------------------------------------------------
        ListaPrecio listaPrecio1 = new ListaPrecio();
        listaPrecio1.setCodListaPrecio(2);
        listaPrecio1.setFechaHoraDesdeListaPrecio(Timestamp.valueOf("2024-08-19 12:00:00"));
        listaPrecio1.setFechaHoraHastaListaPrecio(Timestamp.valueOf("2024-12-15 12:00:00"));

        // DetallePrecioTipoTramite
        DetallePrecioTipoTramite detallePrecio1 = new DetallePrecioTipoTramite();
        detallePrecio1.setPrecioTipoTramite(2000);
        detallePrecio1.setTipoTramite(tipoTramite1);

        DetallePrecioTipoTramite detallePrecio2 = new DetallePrecioTipoTramite();
        detallePrecio2.setPrecioTipoTramite(1600);
        detallePrecio2.setTipoTramite(tipoTramite2);

        // Guardar DetallePrecioTipoTramite antes de añadirlos a la lista
        FachadaPersistencia.getInstance().guardar(detallePrecio1);
        FachadaPersistencia.getInstance().guardar(detallePrecio2);

        // Añadir los DetallePrecioTipoTramite a ListaPrecio
        listaPrecio1.addDetallePrecioTipoTramiteList(detallePrecio1);
        listaPrecio1.addDetallePrecioTipoTramiteList(detallePrecio2);

        // Guardar ListaPrecio
        FachadaPersistencia.getInstance().guardar(listaPrecio1);
        
        
        // Version ------------------------------------------------------------------------------------
        Version version1 = new Version();
        version1.setCodVersion(25);
        version1.setFechaHoraDesdeVersion(Timestamp.valueOf("2024-09-15 12:00:00"));
        version1.setFechaHoraHastaVersion(Timestamp.valueOf("2024-11-15 12:00:00"));
        version1.setTipoTramite(tipoTramite1);
        
        // TransicionEstados
        TransicionEstado transicionEstado1 = new TransicionEstado();
        transicionEstado1.setNroTransicionEstado(1);
        transicionEstado1.setEstadoActual(estado1);
        
        // Guardo los TransicionEstado en Version
        version1.addTransicionEstadoList(transicionEstado1);
        FachadaPersistencia.getInstance().guardar(transicionEstado1);
        
        // Guardo los cambios de Version
        FachadaPersistencia.getInstance().guardar(version1);
        
        
        // Tramites -----------------------------------------------------------------------------------
        Tramite tramite1 = new Tramite();
        tramite1.setNroTramite(23);
        tramite1.setPrecioTramite(2500);
        tramite1.setFechaHoraCargaTramite(new Timestamp(System.currentTimeMillis()));
        tramite1.setFechaHoraVencimientoDocumentacion(Timestamp.valueOf("2024-11-11 12:00:00"));
        tramite1.setConsultor(consultor2);
        tramite1.setCliente(cliente1);
        tramite1.setEstadoTramite(estado1);
        tramite1.setTipoTramite(tipoTramite1);
        tramite1.setVersion(version1);
        
                
        // TramiteDocumentaciones 
        TramiteDocumentacion tramiteDocumentacion1 = new TramiteDocumentacion();
        tramiteDocumentacion1.setCodTramiteDocumentacion(12);
        tramiteDocumentacion1.setDocumentacion(documentacion1);
        
        TramiteDocumentacion tramiteDocumentacion2 = new TramiteDocumentacion();
        tramiteDocumentacion2.setCodTramiteDocumentacion(13);
        tramiteDocumentacion2.setDocumentacion(documentacion2);
        
        
        // TramiteEstados 
        TramiteEstado tramiteEstado1 = new TramiteEstado();
        tramiteEstado1.setNroLineaTramiteEstado(1);
        tramiteEstado1.setObservacionTramiteEstado("Algo");
        tramiteEstado1.setFechaHoraDesdeTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramiteEstado1.setEstadoTramite(estado1);
        
        
        // Guardo los TramiteDocumentacion en Tramite
        tramite1.addTramiteDocumentacionList(tramiteDocumentacion1);
        FachadaPersistencia.getInstance().guardar(tramiteDocumentacion1);
        
        tramite1.addTramiteDocumentacionList(tramiteDocumentacion2);
        FachadaPersistencia.getInstance().guardar(tramiteDocumentacion2);
        
        // Guardo los TramiteEstado en Tramite
        tramite1.addTramiteEstadoList(tramiteEstado1);
        FachadaPersistencia.getInstance().guardar(tramiteEstado1);
        
        
        // Guardo los cambios de Tramite
        FachadaPersistencia.getInstance().guardar(tramite1);
        
        FachadaPersistencia.getInstance().finalizarTransaccion();
        
    }
}