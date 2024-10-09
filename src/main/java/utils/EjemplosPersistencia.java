package utils;

import entidades.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EjemplosPersistencia {

    /**
     * Esto es solo para que se use de ejemplo.
     *
     * Hay que tener en cuenta que la mayoria de los metodos se basan en un
     * camino feliz en el que los objetos ya se encuentran persistidos en la
     * base de datos y no se hace una comprobacion previa.
     *
     * Se recomienda llamar a crearElementos() para que se creen todos los
     * objetos necesarios para los siguientes metodos.
     *
     * Cualquier cosa, consultar a: minellinh@gmail.com
     */
    public void Metodo() {
        // Reemplazar la siguiente linea con el metodo que se desea probar.
        crearElementos();
    }

    /**
     * Crea un estado, un articulo con dicho estado, una reposicion con dicho
     * estado y un detalle para esa reposicion.
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

        Cliente cliente2 = new Cliente();
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
        estado1.setFechaHoraBajaEstadoTramite(new Timestamp(System.currentTimeMillis()));

        FachadaPersistencia.getInstance().guardar(estado1);

        EstadoTramite estado2 = new EstadoTramite();
        estado2.setCodEstadoTramite(2);
        estado2.setNombreEstadoTramite("Etapa 1");
        estado2.setFechaHoraBajaEstadoTramite(new Timestamp(System.currentTimeMillis()));

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
        tipoTramite1.setDescripcionTipoTramite("Se expide un DNI nuevo");
        tipoTramite1.setDescripcionTipoTramiteWeb("Se expide un DNI nuevo WEB");
        tipoTramite1.setMaxDiaEntrega(20);
        tipoTramite1.setNombreTipoTramite("Nuevo DNI");
        tipoTramite1.setFechaHoraBajaTipoTramite(new Timestamp(System.currentTimeMillis()));
        tipoTramite1.setCategoriaTramite(categoriaTramite1);
        List lista1 = new ArrayList();
        lista1.add(documentacion1);
        tipoTramite1.setDocumentaciones(lista1);

        FachadaPersistencia.getInstance().guardar(tipoTramite1);

        TipoTramite tipoTramite2 = new TipoTramite();
        tipoTramite2.setCodTipoTramite(2);
        tipoTramite2.setDescripcionTipoTramite("Se expide la Ciudadania Italiana");
        tipoTramite2.setDescripcionTipoTramiteWeb("Se expide la Ciudadania Italiana WEB");
        tipoTramite2.setMaxDiaEntrega(25);
        tipoTramite2.setNombreTipoTramite("Ciudadania Italiana");
        tipoTramite2.setFechaHoraBajaTipoTramite(new Timestamp(System.currentTimeMillis()));
        tipoTramite2.setCategoriaTramite(categoriaTramite1);
        List lista2 = new ArrayList();
        lista2.add(documentacion1);
        tipoTramite2.setDocumentaciones(lista2);

        FachadaPersistencia.getInstance().guardar(tipoTramite2);

        TipoTramite tipoTramite3 = new TipoTramite();
        tipoTramite3.setCodTipoTramite(3);
        tipoTramite3.setDescripcionTipoTramite("Se expide el acta de Matrimonio");
        tipoTramite3.setDescripcionTipoTramiteWeb("Se expide el acta de Matrimonio WEB");
        tipoTramite3.setMaxDiaEntrega(30);
        tipoTramite3.setNombreTipoTramite("Matrimonio");
        tipoTramite3.setFechaHoraBajaTipoTramite(new Timestamp(System.currentTimeMillis()));
        tipoTramite3.setCategoriaTramite(categoriaTramite1);
        List lista3 = new ArrayList();
        lista3.add(documentacion1);
        tipoTramite3.setDocumentaciones(lista3);

        FachadaPersistencia.getInstance().guardar(tipoTramite3);

        // ListaPrecio --------------------------------------------------------------------------------
        ListaPrecio listaPrecio1 = new ListaPrecio();
        listaPrecio1.setCodListaPrecio(1);
        listaPrecio1.setFechaHoraDesdeListaPrecio(new Timestamp(System.currentTimeMillis()));
        listaPrecio1.setFechaHoraHastaListaPrecio(new Timestamp(System.currentTimeMillis()));
        listaPrecio1.setFechaHoraBajaListaPrecio(new Timestamp(System.currentTimeMillis()));

        ListaPrecio listaPrecio2 = new ListaPrecio();
        listaPrecio2.setCodListaPrecio(2);
        listaPrecio2.setFechaHoraDesdeListaPrecio(new Timestamp(System.currentTimeMillis()));
        listaPrecio2.setFechaHoraHastaListaPrecio(new Timestamp(System.currentTimeMillis()));
        listaPrecio2.setFechaHoraBajaListaPrecio(new Timestamp(System.currentTimeMillis()));

        ListaPrecio listaPrecio3 = new ListaPrecio();
        listaPrecio3.setCodListaPrecio(3);
        listaPrecio3.setFechaHoraDesdeListaPrecio(new Timestamp(System.currentTimeMillis()));
        listaPrecio3.setFechaHoraHastaListaPrecio(new Timestamp(System.currentTimeMillis()));
        listaPrecio3.setFechaHoraBajaListaPrecio(new Timestamp(System.currentTimeMillis()));

        // DetallePrecioTipoTramite
        DetallePrecioTipoTramite detallePrecio1 = new DetallePrecioTipoTramite();
        detallePrecio1.setPrecioTipoTramite(2000);
        detallePrecio1.setTipoTramite(tipoTramite1);

        DetallePrecioTipoTramite detallePrecio2 = new DetallePrecioTipoTramite();
        detallePrecio2.setPrecioTipoTramite(1600);
        detallePrecio2.setTipoTramite(tipoTramite2);

        DetallePrecioTipoTramite detallePrecio3 = new DetallePrecioTipoTramite();
        detallePrecio3.setPrecioTipoTramite(2500);
        detallePrecio3.setTipoTramite(tipoTramite3);

        DetallePrecioTipoTramite detallePrecio4 = new DetallePrecioTipoTramite();
        detallePrecio4.setPrecioTipoTramite(2300);
        detallePrecio4.setTipoTramite(tipoTramite1);

        DetallePrecioTipoTramite detallePrecio5 = new DetallePrecioTipoTramite();
        detallePrecio5.setPrecioTipoTramite(1900);
        detallePrecio5.setTipoTramite(tipoTramite2);

        DetallePrecioTipoTramite detallePrecio6 = new DetallePrecioTipoTramite();
        detallePrecio6.setPrecioTipoTramite(3000);
        detallePrecio6.setTipoTramite(tipoTramite3);

        DetallePrecioTipoTramite detallePrecio7 = new DetallePrecioTipoTramite();
        detallePrecio7.setPrecioTipoTramite(2700);
        detallePrecio7.setTipoTramite(tipoTramite1);

        DetallePrecioTipoTramite detallePrecio8 = new DetallePrecioTipoTramite();
        detallePrecio8.setPrecioTipoTramite(2100);
        detallePrecio8.setTipoTramite(tipoTramite2);

        DetallePrecioTipoTramite detallePrecio9 = new DetallePrecioTipoTramite();
        detallePrecio9.setPrecioTipoTramite(3300);
        detallePrecio9.setTipoTramite(tipoTramite3);

        // Guardar DetallePrecioTipoTramite antes de añadirlos a la lista
        FachadaPersistencia.getInstance().guardar(detallePrecio1);
        FachadaPersistencia.getInstance().guardar(detallePrecio2);
        FachadaPersistencia.getInstance().guardar(detallePrecio3);
        FachadaPersistencia.getInstance().guardar(detallePrecio4);
        FachadaPersistencia.getInstance().guardar(detallePrecio5);
        FachadaPersistencia.getInstance().guardar(detallePrecio6);
        FachadaPersistencia.getInstance().guardar(detallePrecio7);
        FachadaPersistencia.getInstance().guardar(detallePrecio8);
        FachadaPersistencia.getInstance().guardar(detallePrecio9);

        // Añadir los DetallePrecioTipoTramite a ListaPrecio
        listaPrecio1.addDetallePrecioTipoTramiteList(detallePrecio1);
        listaPrecio1.addDetallePrecioTipoTramiteList(detallePrecio2);
        listaPrecio1.addDetallePrecioTipoTramiteList(detallePrecio3);
        listaPrecio2.addDetallePrecioTipoTramiteList(detallePrecio4);
        listaPrecio2.addDetallePrecioTipoTramiteList(detallePrecio5);
        listaPrecio2.addDetallePrecioTipoTramiteList(detallePrecio6);
        listaPrecio3.addDetallePrecioTipoTramiteList(detallePrecio7);
        listaPrecio3.addDetallePrecioTipoTramiteList(detallePrecio8);
        listaPrecio3.addDetallePrecioTipoTramiteList(detallePrecio9);

        // Guardar ListaPrecio
        FachadaPersistencia.getInstance().guardar(listaPrecio1);
        FachadaPersistencia.getInstance().guardar(listaPrecio2);
        FachadaPersistencia.getInstance().guardar(listaPrecio3);

        // Version ------------------------------------------------------------------------------------
        Version version1 = new Version();
        version1.setCodVersion(25);
        version1.setFechaHoraDesdeVersion(new Timestamp(System.currentTimeMillis()));
        version1.setFechaHoraHastaVersion(new Timestamp(System.currentTimeMillis()));
        version1.setFechaHoraBajaVersion(new Timestamp(System.currentTimeMillis()));
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
        tramite1.setFechaHoraFinEntregaDocumentacion(new Timestamp(System.currentTimeMillis()));
        tramite1.setFechaHoraFinTramite(new Timestamp(System.currentTimeMillis()));
        tramite1.setFechaHoraVencimientoDocumentacion(new Timestamp(System.currentTimeMillis()));
        tramite1.setConsultor(consultor1);
        tramite1.setCliente(cliente1);
        tramite1.setEstadoTramite(estado1);
        tramite1.setTipoTramite(tipoTramite1);
        tramite1.setVersion(version1);
        // Guardo los cambios de Tramite
        FachadaPersistencia.getInstance().guardar(tramite1);

        // TramiteDocumentaciones 
        TramiteDocumentacion tramiteDocumentacion1 = new TramiteDocumentacion();
        tramiteDocumentacion1.setCodTramiteDocumentacion(12);
        tramiteDocumentacion1.setArchivo(null);
        tramiteDocumentacion1.setFechaHoraEntrega(null);
        tramiteDocumentacion1.setDocumentacion(documentacion1);

        // Guardo los TramiteDocumentacion en Tramite
        tramite1.addTramiteDocumentacionList(tramiteDocumentacion1);
        FachadaPersistencia.getInstance().guardar(tramiteDocumentacion1);

        // TramiteEstados 
        TramiteEstado tramiteEstado1 = new TramiteEstado();
        tramiteEstado1.setNroLineaTramiteEstado(1);
        tramiteEstado1.setObservacionTramiteEstado("Algo");
        tramiteEstado1.setFechaHoraDesdeTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramiteEstado1.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramiteEstado1.setFechaHoraBajaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramiteEstado1.setEstadoTramite(estado1);
        tramite1.addTramiteEstadoList(tramiteEstado1);

        TramiteEstado tramEstado = new TramiteEstado();
        tramEstado.setFechaHoraBajaTramiteEstado(null);
        tramEstado.setFechaHoraDesdeTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramEstado.setFechaHoraHastaTramiteEstado(new Timestamp(System.currentTimeMillis()));
        tramEstado.setNroLineaTramiteEstado(2);
        tramEstado.setEstadoTramite(estado2);
        tramEstado.setObservacionTramiteEstado("Este es el primer estado");
        //tramite1.addTramiteEstadoList(tramEstado);
        FachadaPersistencia.getInstance().guardar(tramEstado);

        // Guardo los TramiteEstado en Tramite
        FachadaPersistencia.getInstance().guardar(tramiteEstado1);

        FachadaPersistencia.getInstance().finalizarTransaccion();

    }
}
