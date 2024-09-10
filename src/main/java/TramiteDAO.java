import entidades.Tramite;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TramiteDAO {
    
    // Método para agregar un nuevo trámite
    public void agregarTramite(Tramite tramite) throws SQLException {
        String sql = "INSERT INTO tramite (nroTramite, precioTramite, fechaHoraCargaTramite, " +
                     "fechaHoraVencimientoDocumentacion, fechaHoraFinEntregaDocumentacion, fechaHoraFinTramite, " +
                     "consultor_id, cliente_id, tipoTramite_id, version_id, estadoTramite_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tramite.getNroTramite());
            stmt.setFloat(2, tramite.getPrecioTramite());
            stmt.setTimestamp(3, tramite.getFechaHoraCargaTramite());
            stmt.setTimestamp(4, tramite.getFechaHoraVencimientoDocumentacion());
            stmt.setTimestamp(5, tramite.getFechaHoraFinEntregaDocumentacion());
            stmt.setTimestamp(6, tramite.getFechaHoraFinTramite());
            stmt.setInt(7, tramite.getConsultor().getId());  // Asegúrate de que Consultor tiene un método getId()
            stmt.setInt(8, tramite.getCliente().getId());    // Asegúrate de que Cliente tiene un método getId()
            stmt.setInt(9, tramite.getTipoTramite().getId()); // Lo mismo para TipoTramite
            stmt.setInt(10, tramite.getVersion().getId());    // Lo mismo para Version
            stmt.setInt(11, tramite.getEstadoTramite().getId()); // Lo mismo para EstadoTramite

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al agregar el trámite.");
        }
    }

    // Método para obtener todos los trámites
    public List<Tramite> obtenerTramites() throws SQLException {
        List<Tramite> tramites = new ArrayList<>();
        String sql = "SELECT * FROM tramite";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tramite tramite = new Tramite();
                tramite.setNroTramite(rs.getInt("nroTramite"));
                tramite.setPrecioTramite(rs.getFloat("precioTramite"));
                tramite.setFechaHoraCargaTramite(rs.getTimestamp("fechaHoraCargaTramite"));
                tramite.setFechaHoraVencimientoDocumentacion(rs.getTimestamp("fechaHoraVencimientoDocumentacion"));
                tramite.setFechaHoraFinEntregaDocumentacion(rs.getTimestamp("fechaHoraFinEntregaDocumentacion"));
                tramite.setFechaHoraFinTramite(rs.getTimestamp("fechaHoraFinTramite"));
                
                // Obtener relaciones de otras tablas
                tramite.setConsultor(new ConsultorDAO().obtenerConsultorPorId(rs.getInt("consultor_id")));
                tramite.setCliente(new ClienteDAO().obtenerClientePorId(rs.getInt("cliente_id")));
                tramite.setTipoTramite(new TipoTramiteDAO().obtenerTipoTramitePorId(rs.getInt("tipoTramite_id")));
                tramite.setVersion(new VersionDAO().obtenerVersionPorId(rs.getInt("version_id")));
                tramite.setEstadoTramite(new EstadoTramiteDAO().obtenerEstadoTramitePorId(rs.getInt("estadoTramite_id")));
                
                tramites.add(tramite);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los trámites.");
        }
        return tramites;
    }

    // Método para obtener un trámite por su ID
    public Tramite obtenerTramitePorId(int id) throws SQLException {
        String sql = "SELECT * FROM tramite WHERE nroTramite = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Tramite tramite = new Tramite();
                    tramite.setNroTramite(rs.getInt("nroTramite"));
                    tramite.setPrecioTramite(rs.getFloat("precioTramite"));
                    tramite.setFechaHoraCargaTramite(rs.getTimestamp("fechaHoraCargaTramite"));
                    tramite.setFechaHoraVencimientoDocumentacion(rs.getTimestamp("fechaHoraVencimientoDocumentacion"));
                    tramite.setFechaHoraFinEntregaDocumentacion(rs.getTimestamp("fechaHoraFinEntregaDocumentacion"));
                    tramite.setFechaHoraFinTramite(rs.getTimestamp("fechaHoraFinTramite"));
                    
                    // Obtener relaciones de otras tablas
                    tramite.setConsultor(new ConsultorDAO().obtenerConsultorPorId(rs.getInt("consultor_id")));
                    tramite.setCliente(new ClienteDAO().obtenerClientePorId(rs.getInt("cliente_id")));
                    tramite.setTipoTramite(new TipoTramiteDAO().obtenerTipoTramitePorId(rs.getInt("tipoTramite_id")));
                    tramite.setVersion(new VersionDAO().obtenerVersionPorId(rs.getInt("version_id")));
                    tramite.setEstadoTramite(new EstadoTramiteDAO().obtenerEstadoTramitePorId(rs.getInt("estadoTramite_id")));
                    
                    return tramite;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el trámite.");
        }
        return null; // Si no se encuentra el trámite
    }

    // Método para actualizar un trámite existente
    public void actualizarTramite(Tramite tramite) throws SQLException {
        String sql = "UPDATE tramite SET precioTramite = ?, fechaHoraCargaTramite = ?, fechaHoraVencimientoDocumentacion = ?, " +
                     "fechaHoraFinEntregaDocumentacion = ?, fechaHoraFinTramite = ?, consultor_id = ?, cliente_id = ?, " +
                     "tipoTramite_id = ?, version_id = ?, estadoTramite_id = ? WHERE nroTramite = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, tramite.getPrecioTramite());
            stmt.setTimestamp(2, tramite.getFechaHoraCargaTramite());
            stmt.setTimestamp(3, tramite.getFechaHoraVencimientoDocumentacion());
            stmt.setTimestamp(4, tramite.getFechaHoraFinEntregaDocumentacion());
            stmt.setTimestamp(5, tramite.getFechaHoraFinTramite());
            stmt.setInt(6, tramite.getConsultor().getId());
            stmt.setInt(7, tramite.getCliente().getId());
            stmt.setInt(8, tramite.getTipoTramite().getId());
            stmt.setInt(9, tramite.getVersion().getId());
            stmt.setInt(10, tramite.getEstadoTramite().getId());
            stmt.setInt(11, tramite.getNroTramite());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el trámite.");
        }
    }

    // Método para eliminar un trámite por su ID
    public void eliminarTramite(int id) throws SQLException {
        String sql = "DELETE FROM tramite WHERE nroTramite = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el trámite.");
        }
    }
}
