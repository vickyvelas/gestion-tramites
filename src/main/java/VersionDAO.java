import entidades.TipoTramite;
import entidades.Version;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VersionDAO {

    // Método para agregar una nueva versión
    public void agregarVersion(Version version) throws SQLException {
        String sql = "INSERT INTO version (codVersion, fechaHoraDesdeVersion, fechaHoraHastaVersion, fechaHoraBajaVersion, tipoTramite) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, version.getCodVersion());
            stmt.setTimestamp(2, version.getFechaHoraDesdeVersion());
            stmt.setTimestamp(3, version.getFechaHoraHastaVersion());
            stmt.setTimestamp(4, version.getFechaHoraBajaVersion());
            stmt.setInt(5, version.getTipoTramite().getId()); // Asegúrate de que `TipoTramite` tenga un método `getId()`

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al agregar la versión.");
        }
    }

    // Método para obtener todas las versiones
    public List<Version> obtenerVersiones() throws SQLException {
        List<Version> versiones = new ArrayList<>();
        String sql = "SELECT * FROM version";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Version version = new Version();
                version.setCodVersion(rs.getInt("codVersion"));
                version.setFechaHoraDesdeVersion(rs.getTimestamp("fechaHoraDesdeVersion"));
                version.setFechaHoraHastaVersion(rs.getTimestamp("fechaHoraHastaVersion"));
                version.setFechaHoraBajaVersion(rs.getTimestamp("fechaHoraBajaVersion"));

                // Aquí se debería obtener el `TipoTramite` desde la base de datos o de otra fuente
                int tipoTramiteId = rs.getInt("tipoTramite");
                TipoTramite tipoTramite = obtenerTipoTramitePorId(tipoTramiteId); // Implementa este método según tus necesidades
                version.setTipoTramite(tipoTramite);

                versiones.add(version);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener las versiones.");
        }
        return versiones;
    }

    // Método para obtener una versión por su código
    public Version obtenerVersionPorCodigo(int codVersion) throws SQLException {
        String sql = "SELECT * FROM version WHERE codVersion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codVersion);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Version version = new Version();
                    version.setCodVersion(rs.getInt("codVersion"));
                    version.setFechaHoraDesdeVersion(rs.getTimestamp("fechaHoraDesdeVersion"));
                    version.setFechaHoraHastaVersion(rs.getTimestamp("fechaHoraHastaVersion"));
                    version.setFechaHoraBajaVersion(rs.getTimestamp("fechaHoraBajaVersion"));

                    // Aquí se debería obtener el `TipoTramite` desde la base de datos o de otra fuente
                    int tipoTramiteId = rs.getInt("tipoTramite");
                    TipoTramite tipoTramite = obtenerTipoTramitePorId(tipoTramiteId); // Implementa este método según tus necesidades
                    version.setTipoTramite(tipoTramite);

                    return version;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener la versión.");
        }
        return null; // Si no se encuentra la versión
    }

    // Método para actualizar una versión existente
    public void actualizarVersion(Version version) throws SQLException {
        String sql = "UPDATE version SET fechaHoraDesdeVersion = ?, fechaHoraHastaVersion = ?, fechaHoraBajaVersion = ?, tipoTramite = ? WHERE codVersion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, version.getFechaHoraDesdeVersion());
            stmt.setTimestamp(2, version.getFechaHoraHastaVersion());
            stmt.setTimestamp(3, version.getFechaHoraBajaVersion());
            stmt.setInt(4, version.getTipoTramite().getId()); // Asegúrate de que `TipoTramite` tenga un método `getId()`
            stmt.setInt(5, version.getCodVersion());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar la versión.");
        }
    }

    // Método para eliminar una versión por su código
    public void eliminarVersion(int codVersion) throws SQLException {
        String sql = "DELETE FROM version WHERE codVersion = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codVersion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar la versión.");
        }
    }

    // Método auxiliar para obtener un TipoTramite por su ID (implementa este método según tus necesidades)
    private TipoTramite obtenerTipoTramitePorId(int id) throws SQLException {
        // Implementa la lógica para obtener un TipoTramite desde la base de datos
        // Por ejemplo, podrías usar un DAO similar al `TipoTramiteDAO`
        // Aquí se muestra un ejemplo básico que devuelve un objeto vacío para ilustrar
        TipoTramite tipoTramite = new TipoTramite();
        tipoTramite.setId(id);
        return tipoTramite;
    }

    Version obtenerVersionPorId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

