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
  
                // Consultores
        Consultor consultor1 = new Consultor();
        consultor1.setLegajoConsultor(44902);
        consultor1.setNombreApellidoConsultor("Emilio Amin");
        consultor1.setConsultorNroMaxTramite(10);
        consultor1.setFechaHoraBajaConsultor(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(consultor1);

        Consultor consultor2 = new Consultor();
        consultor2.setLegajoConsultor(44903);
        consultor2.setNombreApellidoConsultor("Marcos");
        consultor2.setConsultorNroMaxTramite(13);
        consultor2.setFechaHoraBajaConsultor(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(consultor2);
        
        Consultor consultor3 = new Consultor();
        consultor3.setLegajoConsultor(44904);
        consultor3.setNombreApellidoConsultor("Esteban Quito");
        consultor3.setConsultorNroMaxTramite(5);
        consultor3.setFechaHoraBajaConsultor(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(consultor3);
        
        
        // Agendas ------------------------------------------------------------------------------------
        Agenda agenda = new Agenda();
        agenda.setAnio(2024);
        agenda.setSemana(29);
        agenda.setFechaDesdeAgenda(new Timestamp(System.currentTimeMillis()));
        agenda.setFechaHastaAgenda(new Timestamp(System.currentTimeMillis()));
        agenda.addConsultor(consultor1);
        agenda.addConsultor(consultor2);
        agenda.addConsultor(consultor3); 
        
        FachadaPersistencia.getInstance().guardar(agenda);

        
        // Clientes -----------------------------------------------------------------------------------
        Cliente cliente1 = new Cliente();
        cliente1.setCuitCliente(20202050);
        cliente1.setDireccionCliente("Patricias Mendocinas 275");
        cliente1.setNombreApellidoCliente("Lucas Perez");
        cliente1.setMailCliente("lucasperez@hotmail.com");
        cliente1.setTelefonoCliente(26199999);
        cliente1.setFechaHoraAltaCliente(new Timestamp(System.currentTimeMillis()));
        cliente1.setFechaHoraBajaCliente(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(cliente1);
        
        Cliente cliente2=new Cliente();
        cliente2.setCuitCliente(44567890);
        cliente2.setDireccionCliente("Alvear 35 B");
        cliente2.setFechaHoraAltaCliente(new Timestamp(System.currentTimeMillis()));
        cliente2.setMailCliente("carolinaMartin@gmail.com");
        cliente2.setFechaHoraBajaCliente(null);
        cliente2.setNombreApellidoCliente("Carolina Martin");
        cliente2.setTelefonoCliente(261568884);
        
        FachadaPersistencia.getInstance().guardar(cliente2);
                
        
        // EstadoTramites -----------------------------------------------------------------------------
        EstadoTramite estado1 = new EstadoTramite();
        estado1.setCodEstadoTramite(1);
        estado1.setNombreEstadoTramite("Iniciado");
        estado1.setFechaHoraBajaEstadoTramite(null);
        
        FachadaPersistencia.getInstance().guardar(estado1);
        
        EstadoTramite estado2 = new EstadoTramite();
        estado2.setCodEstadoTramite(2);
        estado2.setNombreEstadoTramite("Etapa 1");
        estado2.setFechaHoraBajaEstadoTramite(null);
        
        FachadaPersistencia.getInstance().guardar(estado2);
        
                
        EstadoTramite estado3 = new EstadoTramite();
        estado3.setCodEstadoTramite(3);
        estado3.setFechaHoraBajaEstadoTramite(null);
        estado3.setNombreEstadoTramite("Etapa 2");
        
        FachadaPersistencia.getInstance().guardar(estado3);
        
        
        // CategoriaTramites --------------------------------------------------------------------------
        CategoriaTramite categoriaTramite1 = new CategoriaTramite();
        categoriaTramite1.setCodCategoriaTramite(23);
        categoriaTramite1.setNombreCategoriaTramite("DNIs");
        categoriaTramite1.setFechaHoraBajaCategoriaTramite(new Timestamp(System.currentTimeMillis()));
        
        FachadaPersistencia.getInstance().guardar(categoriaTramite1);
        
        
        // Documentaciones ----------------------------------------------------------------------------
        Documentacion documentacion1 = new Documentacion();
        documentacion1.setCodDocumentacion(4);
        documentacion1.setNombreDocumentacion("Partida de Nacimiento");
        documentacion1.setFechaHoraBajaDocumentacion(null);
        
        FachadaPersistencia.getInstance().guardar(documentacion1);
        
        
        // TipoTramites -------------------------------------------------------------------------------
        TipoTramite tipoTramite1 = new TipoTramite();
        tipoTramite1.setCodTipoTramite(1);
        tipoTramite1.setDescripcionTipoTramite("nose");
        tipoTramite1.setDescripcionTipoTramiteWeb("noseWeb");
        tipoTramite1.setMaxDiaEntrega(20);
        tipoTramite1.setNombreTipoTramite("Nuevo DNI");
        tipoTramite1.setFechaHoraBajaTipoTramite(null);
        tipoTramite1.setCategoriaTramite(categoriaTramite1);
        List lista = new ArrayList();
        lista.add(documentacion1);
        tipoTramite1.setDocumentaciones(lista);
        
        FachadaPersistencia.getInstance().guardar(tipoTramite1);
        
        TipoTramite tipoTramite2 = new TipoTramite();
        tipoTramite2.setCodTipoTramite(2);
        tipoTramite2.setDescripcionTipoTramite("nose");
        tipoTramite2.setDescripcionTipoTramiteWeb("noseWeb");
        tipoTramite2.setMaxDiaEntrega(25);
        tipoTramite2.setNombreTipoTramite("Ciudadania Italiana");
        tipoTramite2.setFechaHoraBajaTipoTramite(new Timestamp(System.currentTimeMillis()));
        tipoTramite2.setCategoriaTramite(categoriaTramite1);
        List lista2 = new ArrayList();
        lista2.add(documentacion1);
        tipoTramite2.setDocumentaciones(lista2);
        
        FachadaPersistencia.getInstance().guardar(tipoTramite2);
        
        // ListaPrecio --------------------------------------------------------------------------------
        ListaPrecio listaPrecio1 = new ListaPrecio();
        listaPrecio1.setCodListaPrecio(2);
        listaPrecio1.setFechaHoraDesdeListaPrecio(new Timestamp(System.currentTimeMillis()));
        listaPrecio1.setFechaHoraHastaListaPrecio(new Timestamp(System.currentTimeMillis()));
        listaPrecio1.setFechaHoraBajaListaPrecio(new Timestamp(System.currentTimeMillis()));

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
        version1.setCodVersion(1);
        version1.setFechaHoraDesdeVersion(new Timestamp(System.currentTimeMillis()));
        Timestamp fh = Timestamp.valueOf("2070-09-15 22:06:45.0");
        version1.setFechaHoraHastaVersion(fh);
        version1.setFechaHoraBajaVersion(null);
        version1.setTipoTramite(tipoTramite1);
        
        // TransicionEstados
        TransicionEstado transicionEstado1 = new TransicionEstado();
        transicionEstado1.setNroTransicionEstado(1);
        transicionEstado1.setEstadoActual(estado1);
        List<EstadoTramite> destinos = new ArrayList();
        destinos.add(estado2);
        destinos.add(estado3);
        transicionEstado1.setEstadosDestino(destinos);
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
        tramite1.setFechaHoraFinEntregaDocumentacion(new Timestamp(System.currentTimeMillis()));
        tramite1.setFechaHoraFinTramite(new Timestamp(System.currentTimeMillis()));
        tramite1.setFechaHoraVencimientoDocumentacion(new Timestamp(System.currentTimeMillis()));
        tramite1.setConsultor(consultor1);
        tramite1.setCliente(cliente1);
        tramite1.setEstadoTramite(estado1);
        tramite1.setTipoTramite(tipoTramite1);
        tramite1.setVersion(version1);
        
                
        // TramiteDocumentaciones 
        TramiteDocumentacion tramiteDocumentacion1 = new TramiteDocumentacion();
        tramiteDocumentacion1.setCodTramiteDocumentacion(12);
        tramiteDocumentacion1.setArchivo(null);
        tramiteDocumentacion1.setFechaHoraEntrega(null);
        tramiteDocumentacion1.setDocumentacion(documentacion1);
        
        
        // TramiteEstados 
        TramiteEstado tramiteEstado1 = new TramiteEstado();
        tramiteEstado1.setNroLineaTramiteEstado(1);
        tramiteEstado1.setObservacionTramiteEstado("Algo");
        tramiteEstado1.setFechaHoraDesdeTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramiteEstado1.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramiteEstado1.setFechaHoraBajaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramiteEstado1.setEstadoTramite(estado1);
        
        
        TramiteEstado tramEstado=new TramiteEstado();
        tramEstado.setFechaHoraBajaTramiteEstado(null);
        tramEstado.setFechaHoraDesdeTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramEstado.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramEstado.setNroLineaTramiteEstado(1);
        tramEstado.setEstadoTramite(estado2);
        tramEstado.setObservacionTramiteEstado("Este es el primer estado");
        
        FachadaPersistencia.getInstance().guardar(tramEstado);
        
        // Guardo los TramiteDocumentacion en Tramite
        tramite1.addTramiteDocumentacionList(tramiteDocumentacion1);
        FachadaPersistencia.getInstance().guardar(tramiteDocumentacion1);
        
        // Guardo los TramiteEstado en Tramite
        tramite1.addTramiteEstadoList(tramiteEstado1);
        FachadaPersistencia.getInstance().guardar(tramiteEstado1);
        
        // Guardo los cambios de Tramite
        FachadaPersistencia.getInstance().guardar(tramite1);
        
        FachadaPersistencia.getInstance().finalizarTransaccion();
        
       
    }
}