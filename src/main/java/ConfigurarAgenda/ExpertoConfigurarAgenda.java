package ConfigurarAgenda;

import ConfigurarAgenda.dtos.DTOAgendaSemana;
import ConfigurarAgenda.dtos.DTOConsultor;
import ConfigurarAgenda.dtos.DTOConsultorAgenda;
import ConfigurarAgenda.dtos.DTOPantallaAgenda;
import ConfigurarAgenda.exceptions.ConfigurarAgendaException;
import entidades.Agenda;
import entidades.Consultor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.DTOCriterio;
import utils.FachadaPersistencia;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.hibernate.Hibernate;

public class ExpertoConfigurarAgenda {

    public List<Integer> obtenerSemanasDelMes(int anio, int mes) {
        List<Integer> semanas = new ArrayList<>();
        // Obtener la fecha del primer día del mes
        LocalDate firstDayOfMonth = LocalDate.of(anio, mes, 1);
        // Obtener la fecha del primer lunes del mes
        LocalDate firstMonday = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        // Asegúrate de que las semanas comiencen desde la primera semana del mes
        if (firstMonday.getMonthValue() != mes) {
            firstMonday = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        }
        // Iterar a través de las semanas del mes
        LocalDate currentMonday = firstMonday;
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        while (currentMonday.getMonthValue() == mes) {
            int weekNumber = currentMonday.get(weekFields.weekOfYear());
            if (!semanas.contains(weekNumber)) { // Evitar duplicados
                semanas.add(weekNumber);
            }
            // Pasar al siguiente lunes
            currentMonday = currentMonday.plusWeeks(1);
        }
        return semanas;
    }

    public Map<String, Date> obtenerFechasDeSemana(int anio, int semana) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        // Obtener el primer día del año
        LocalDate firstDayOfYear = LocalDate.of(anio, 1, 1);

        // Obtener el primer día de la semana indicada (por defecto, lunes)
        LocalDate fechaDesde = firstDayOfYear
                .with(weekFields.weekOfYear(), semana)
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        // Obtener el último día de la semana indicada (por defecto, domingo)
        LocalDate fechaHasta = fechaDesde.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        // Convertir LocalDate a java.util.Date
        Map<String, Date> fechas = new HashMap<>();
        fechas.put("fechaDesdeAgenda", Date.from(fechaDesde.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        fechas.put("fechaHastaAgenda", Date.from(fechaHasta.atStartOfDay(ZoneId.systemDefault()).toInstant()));

        return fechas;
    }
    
    public DTOPantallaAgenda mostrarAgenda(int anio, int mes) {
        DTOPantallaAgenda dtoPantallaAgenda = new DTOPantallaAgenda();

        dtoPantallaAgenda.setAnio(anio);
        dtoPantallaAgenda.setMes(mes);

        // Obtener el primer lunes del mes
        LocalDate firstDayOfMonth = LocalDate.of(anio, mes, 1);
        LocalDate firstMonday = firstDayOfMonth.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        if (firstMonday.getMonthValue() != mes) {
            firstMonday = firstDayOfMonth.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        // Obtener las semanas del mes
        List<Integer> semanas = obtenerSemanasDelMes(anio, mes);
        
        // Iterar sobre las semanas y crear un DTOAgendaSemana para cada semana
        for (int i = 0; i < semanas.size(); i++) {
            DTOAgendaSemana dtoAgendaSemana = new DTOAgendaSemana();
            dtoAgendaSemana.setAnio(anio);
            dtoAgendaSemana.setSemana(semanas.get(i));

            // Calcular las fechas desde y hasta para la semana actual
            LocalDate fechaDesdeAgenda = firstMonday.plusWeeks(i); // Lunes de la semana i
            LocalDate fechaHastaAgenda = fechaDesdeAgenda.plusDays(6); // Domingo de la semana i

            // Asignar las fechas al DTO
            dtoAgendaSemana.setFechaDesdeAgenda(convertirLocalDateADate(fechaDesdeAgenda));
            dtoAgendaSemana.setFechaHastaAgenda(convertirLocalDateADate(fechaHastaAgenda));

            List<DTOCriterio> lcriterio = new ArrayList<>();
            DTOCriterio unCriterio = new DTOCriterio();
            unCriterio.setAtributo("anio");
            unCriterio.setOperacion("=");
            unCriterio.setValor(anio);
            lcriterio.add(unCriterio);

            DTOCriterio dosCriterio = new DTOCriterio();
            dosCriterio.setAtributo("semana");
            dosCriterio.setOperacion("=");
            dosCriterio.setValor(semanas.get(i));
            lcriterio.add(dosCriterio);

            // Verificar si se obtuvieron resultados de la búsqueda
            List<Object> agendas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);
            if (agendas != null && !agendas.isEmpty()) { // Verificación agregada
                Agenda agenda = (Agenda) agendas.get(0); // Solo accede si la lista no está vacía

                for (Consultor consultor : agenda.getConsultores()) {
                    DTOConsultorAgenda dtoConsultorAgenda = new DTOConsultorAgenda();
                    dtoConsultorAgenda.setLegajoConsultor(consultor.getLegajoConsultor());
                    dtoConsultorAgenda.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
                    // Agrega el consultor al dtoAgendaSemana
                    dtoAgendaSemana.addDtoConsultorAgenda(dtoConsultorAgenda);
                }
            } else {
                System.out.println("No se encontró ninguna agenda para los criterios proporcionados.");
            }

            // Agrega la agenda al dtoPantallaAgenda
            dtoPantallaAgenda.addDtoAgendaSemana(dtoAgendaSemana);
        }

        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("fechaHoraBajaConsultor");
        unCriterio.setOperacion("=");
        unCriterio.setValor(null);
        lcriterio.add(unCriterio);

        List lconsultor = FachadaPersistencia.getInstance().buscar("Consultor", lcriterio);

        for (Object x : lconsultor) {
            Consultor consultor = (Consultor) x;
            DTOConsultor dtoConsultor = new DTOConsultor();
            dtoConsultor.setLegajoConsultor(consultor.getLegajoConsultor());
            dtoConsultor.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
            dtoPantallaAgenda.addDtoConsultor(dtoConsultor);
        }

        return dtoPantallaAgenda;
    }
    
    private Date convertirLocalDateADate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public List<DTOConsultor> buscarConsultores() {
        System.out.println("Iniciando búsqueda de consultores...");

        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("fechaHoraBajaConsultor");
        unCriterio.setOperacion("=");
        unCriterio.setValor(null);
        lcriterio.add(unCriterio);

        System.out.println("Criterios de búsqueda: " + lcriterio);

        List lconsultor = FachadaPersistencia.getInstance().buscar("Consultor", lcriterio);
        System.out.println("Consultores encontrados: " + lconsultor.size());

        List<DTOConsultor> consultores = new ArrayList<>();
        for (Object x : lconsultor) {
            Consultor consultor = (Consultor) x;
            DTOConsultor dtoConsultor = new DTOConsultor();
            dtoConsultor.setLegajoConsultor(consultor.getLegajoConsultor());
            dtoConsultor.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
            consultores.add(dtoConsultor);
        }

        System.out.println("Cantidad de DTOConsultor creados: " + consultores.size());
        return consultores;
    }

    public List<DTOConsultorAgenda> buscarConsultoresAsignados(int anio, int semana) throws ConfigurarAgendaException {
        System.out.println("Iniciando la búsqueda de consultores asignados para la agenda...");

        // Criterios para buscar la agenda según el año y la semana
        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio criterioAnio = new DTOCriterio();
        criterioAnio.setAtributo("anio");
        criterioAnio.setOperacion("=");
        criterioAnio.setValor(anio);
        lcriterio.add(criterioAnio);

        DTOCriterio criterioSemana = new DTOCriterio();
        criterioSemana.setAtributo("semana");
        criterioSemana.setOperacion("=");
        criterioSemana.setValor(semana);
        lcriterio.add(criterioSemana);

        // Buscar la agenda en la base de datos
        List agendasEncontradas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);

        // Si no se encuentran agendas, retornar una lista vacía
        if (agendasEncontradas.isEmpty()) {
            System.out.println("No se encontró la agenda para la semana " + semana + " del año " + anio + ". Retornando lista vacía.");
            return new ArrayList<>(); // Retornar lista vacía
        }

        Agenda agenda = (Agenda) agendasEncontradas.get(0);
        System.out.println("Agenda encontrada: Año " + agenda.getAnio() + ", Semana: " + agenda.getSemana());

        // Inicializar los consultores para evitar LazyInitializationException
        Hibernate.initialize(agenda.getConsultores());

        // Lista para almacenar los DTOConsultorAgenda
        List<DTOConsultorAgenda> dtoConsultoresAgenda = new ArrayList<>();

        // Iterar sobre los consultores de la agenda
        for (Consultor consultor : agenda.getConsultores()) {
            DTOConsultorAgenda dtoConsultorAgenda = new DTOConsultorAgenda();
            dtoConsultorAgenda.setLegajoConsultor(consultor.getLegajoConsultor());
            dtoConsultorAgenda.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
            dtoConsultoresAgenda.add(dtoConsultorAgenda);
        }

        System.out.println("Consultores asignados encontrados: " + dtoConsultoresAgenda.size());
        return dtoConsultoresAgenda;
    }


    public List<DTOConsultor> buscarConsultoresNoAsignados(int anio, int semana) throws ConfigurarAgendaException {
        System.out.println("Iniciando la búsqueda de consultores no asignados...");

        // Criterios para buscar la agenda según el año y la semana
        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio criterioAnio = new DTOCriterio();
        criterioAnio.setAtributo("anio");
        criterioAnio.setOperacion("=");
        criterioAnio.setValor(anio);
        lcriterio.add(criterioAnio);

        DTOCriterio criterioSemana = new DTOCriterio();
        criterioSemana.setAtributo("semana");
        criterioSemana.setOperacion("=");
        criterioSemana.setValor(semana);
        lcriterio.add(criterioSemana);

        // Buscar la agenda en la base de datos
        List agendasEncontradas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);

        List<DTOConsultor> consultoresNoAsignados = new ArrayList<>();

        if (agendasEncontradas.isEmpty()) {
            // Si no se encontraron agendas, buscar todos los consultores activos
            System.out.println("No se encontraron agendas para el año " + anio + " y semana " + semana + ". Buscando todos los consultores activos...");

            // Criterio para buscar consultores activos
            List<DTOCriterio> lcriterioConsultores = new ArrayList<>();
            DTOCriterio criterioConsultores = new DTOCriterio();
            criterioConsultores.setAtributo("fechaHoraBajaConsultor"); // Se asume que buscamos consultores activos
            criterioConsultores.setOperacion("=");
            criterioConsultores.setValor(null); // Solo consultores activos
            lcriterioConsultores.add(criterioConsultores);

            List consultoresActivos = FachadaPersistencia.getInstance().buscar("Consultor", lcriterioConsultores);
            System.out.println("Consultores activos encontrados: " + consultoresActivos.size());

            // Agregar todos los consultores activos a la lista de no asignados
            for (Object objConsultor : consultoresActivos) {
                Consultor consultor = (Consultor) objConsultor;
                DTOConsultor dtoConsultor = new DTOConsultor();
                dtoConsultor.setLegajoConsultor(consultor.getLegajoConsultor());
                dtoConsultor.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
                consultoresNoAsignados.add(dtoConsultor);
            }
        } else {
            // Si se encontró al menos una agenda, procesar la primera agenda
            Agenda agenda = (Agenda) agendasEncontradas.get(0);
            System.out.println("Agenda encontrada: Año " + agenda.getAnio() + ", Semana: " + agenda.getSemana());

            // Inicializar los consultores para evitar LazyInitializationException
            Hibernate.initialize(agenda.getConsultores());

            // Obtener la lista de todos los consultores activos
            List<DTOCriterio> lcriterioConsultores = new ArrayList<>();
            DTOCriterio criterioConsultores = new DTOCriterio();
            criterioConsultores.setAtributo("fechaHoraBajaConsultor"); // Se asume que buscamos consultores activos
            criterioConsultores.setOperacion("=");
            criterioConsultores.setValor(null); // Solo consultores activos
            lcriterioConsultores.add(criterioConsultores);

            List consultoresActivos = FachadaPersistencia.getInstance().buscar("Consultor", lcriterioConsultores);
            System.out.println("Consultores activos encontrados: " + consultoresActivos.size());

            // Iterar sobre todos los consultores activos y verificar si están en la agenda
            for (Object objConsultor : consultoresActivos) {
                Consultor consultor = (Consultor) objConsultor;

                // Si el consultor no está en la agenda, lo agregamos a la lista de no asignados
                if (!agenda.getConsultores().contains(consultor)) {
                    DTOConsultor dtoConsultor = new DTOConsultor();
                    dtoConsultor.setLegajoConsultor(consultor.getLegajoConsultor());
                    dtoConsultor.setNombreApellidoConsultor(consultor.getNombreApellidoConsultor());
                    consultoresNoAsignados.add(dtoConsultor);
                }
            }
        }

        System.out.println("Consultores no asignados encontrados: " + consultoresNoAsignados.size());
        return consultoresNoAsignados;
    }

    
    public void agregarConsultores(int semana, int anio, int legajoConsultor) throws ConfigurarAgendaException {
        System.out.println("Iniciando la adición de consultores a la agenda...");
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int semanaActual = fechaActual.get(weekFields.weekOfYear());
        int anioActual = fechaActual.getYear();

        // Validar si la agenda que se intenta modificar pertenece a una semana pasada o la semana actual
        if (anio < anioActual || (anio == anioActual && semana <= semanaActual)) {
        throw new ConfigurarAgendaException("No se pueden modificar agendas de semanas pasadas o la semana actual.");
        }
        // Iniciar la transacción
        FachadaPersistencia.getInstance().iniciarTransaccion();
        System.out.println("Transacción iniciada.");
        // Criterios para buscar la agenda según el año y la semana
        List<DTOCriterio> lcriterio = new ArrayList<>();
        DTOCriterio unCriterio = new DTOCriterio();
        unCriterio.setAtributo("anio");
        unCriterio.setOperacion("=");
        unCriterio.setValor(anio);
        lcriterio.add(unCriterio);

        DTOCriterio sdoCriterio = new DTOCriterio();
        sdoCriterio.setAtributo("semana");
        sdoCriterio.setOperacion("=");
        sdoCriterio.setValor(semana);
        lcriterio.add(sdoCriterio);

        // Mostrar los criterios de búsqueda antes de buscar la agenda
        System.out.println("Buscando agenda con año: " + anio + " y semana: " + semana);

        // Buscar la agenda en la base de datos
        List agendaEncontrada = FachadaPersistencia.getInstance().buscar("Agenda", lcriterio);
        System.out.println("Agenda encontrada: " + (agendaEncontrada != null ? agendaEncontrada.size() : 0));

        Agenda agenda;

        // Si no se encuentra una agenda, se crea una nueva
        if (agendaEncontrada == null || agendaEncontrada.isEmpty()) {
            Map<String, Date> fechas = obtenerFechasDeSemana(anio, semana);
            agenda = new Agenda();
            agenda.setAnio(anio);
            agenda.setSemana(semana);
            agenda.setFechaDesdeAgenda(fechas.get("fechaDesdeAgenda"));
            agenda.setFechaHastaAgenda(fechas.get("fechaHastaAgenda"));
            System.out.println("Nueva agenda creada para la semana " + semana + " del año " + anio);
        } else {
            // Usar la agenda existente
            agenda = (Agenda) agendaEncontrada.get(0);
            System.out.println("Usando la agenda existente para la semana " + semana + " del año " + anio);

            // Inicializar la colección "consultores" para evitar LazyInitializationException
            Hibernate.initialize(agenda.getConsultores());
        }
         // Validar si el consultor ya está en la agenda
    for (Consultor consultor : agenda.getConsultores()) {
        if (consultor.getLegajoConsultor() == legajoConsultor) {
            // Si el consultor ya está en la agenda, lanzamos una excepción
            throw new ConfigurarAgendaException("El consultor ya está asignado a esta agenda.");
        }
    }

        // Buscar el consultor elegido
        List<DTOCriterio> lcriterio1 = new ArrayList<>();
        DTOCriterio criterio1 = new DTOCriterio();
        criterio1.setAtributo("legajoConsultor");
        criterio1.setOperacion("=");
        criterio1.setValor(legajoConsultor);
        lcriterio1.add(criterio1);

        Consultor consultorElegido = (Consultor) FachadaPersistencia.getInstance().buscar("Consultor", lcriterio1).get(0);
        System.out.println("Consultor elegido: " + consultorElegido.getNombreApellidoConsultor());

        // Agregar el consultor a la agenda
        agenda.addConsultor(consultorElegido);

        // Guardar la agenda (nueva o modificada)
        FachadaPersistencia.getInstance().guardar(agenda);
        System.out.println("Agenda guardada.");

        // Finalizar la transacción
        FachadaPersistencia.getInstance().finalizarTransaccion();
        System.out.println("Transacción finalizada.");
    }
    
    // Eliminar Consultor de la Agenda
    public void darDeBajaConsultorDeAgenda(int semana, int anio, int legajoConsultor) throws ConfigurarAgendaException {
        FachadaPersistencia.getInstance().iniciarTransaccion();
        System.out.println("Iniciando la eliminación del consultor de la agenda...");
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int semanaActual = fechaActual.get(weekFields.weekOfYear());
            int anioActual = fechaActual.getYear();

            // Imprimir los valores de los parámetros y los calculados para depuración
            System.out.println("Semana recibida como parámetro: " + semana);
            System.out.println("Año recibido como parámetro: " + anio);
            System.out.println("Semana actual calculada: " + semanaActual);
            System.out.println("Año actual calculado: " + anioActual);

            // Validar si la agenda que se intenta modificar pertenece a una semana pasada o la semana actual
            if (anio < anioActual || (anio == anioActual && semana <= semanaActual)) {
                throw new ConfigurarAgendaException("No se pueden eliminar consultores de agendas pasadas o de la semana actual.");
            }
        // Buscar la agenda correspondiente al año y semana
        List<DTOCriterio> lcriterioAgenda = new ArrayList<>();
        DTOCriterio criterioAnio = new DTOCriterio();
        criterioAnio.setAtributo("anio");
        criterioAnio.setOperacion("=");
        criterioAnio.setValor(anio);
        lcriterioAgenda.add(criterioAnio);

        DTOCriterio criterioSemana = new DTOCriterio();
        criterioSemana.setAtributo("semana");
        criterioSemana.setOperacion("=");
        criterioSemana.setValor(semana);
        lcriterioAgenda.add(criterioSemana);

        List agendasEncontradas = FachadaPersistencia.getInstance().buscar("Agenda", lcriterioAgenda);
        if (agendasEncontradas.isEmpty()) {
            throw new ConfigurarAgendaException("No se encontró la agenda para la semana y año especificados.");
        }

        Agenda agenda = (Agenda) agendasEncontradas.get(0);
        System.out.println("Agenda encontrada: " + agenda.getAnio() + ", Semana: " + agenda.getSemana());

        // Cargar los consultores de la agenda
        Hibernate.initialize(agenda.getConsultores());
        System.out.println("Consultores en la agenda: ");
        for (Consultor c : agenda.getConsultores()) {
            System.out.println("- " + c.getNombreApellidoConsultor());
        }

        // Buscar el consultor por legajo
        List<DTOCriterio> lcriterioConsultor = new ArrayList<>();
        DTOCriterio criterioLegajo = new DTOCriterio();
        criterioLegajo.setAtributo("legajoConsultor");
        criterioLegajo.setOperacion("=");
        criterioLegajo.setValor(legajoConsultor);
        lcriterioConsultor.add(criterioLegajo);

        List<Object> consultoresEncontrados = FachadaPersistencia.getInstance().buscar("Consultor", lcriterioConsultor);
        if (consultoresEncontrados.isEmpty()) {
            throw new ConfigurarAgendaException("No se encontró el consultor con el legajo especificado.");
        }

        Consultor consultorElegido = (Consultor) consultoresEncontrados.get(0);
        System.out.println("Consultor encontrado: " + consultorElegido.getNombreApellidoConsultor());

        // Eliminar el consultor de la agenda
        
        if (agenda != null && consultorElegido != null) {
            // Eliminar la relación consultor-agenda
            System.out.println("Consultores en la agenda antes de la eliminación: " + agenda.getConsultores());
            agenda.getConsultores().remove(consultorElegido);
            System.out.println("Consultores en la agenda después de la eliminación: " + agenda.getConsultores());


            // Guardar la agenda
            FachadaPersistencia.getInstance().guardar(agenda);
            System.out.println("Consultor eliminado de la agenda.");
        } else {
            System.out.println("Agenda o consultor no encontrados.");
        }
        

        // Finalizar la transacción
        FachadaPersistencia.getInstance().finalizarTransaccion();
        System.out.println("Transacción finalizada.");
    }
    
}
