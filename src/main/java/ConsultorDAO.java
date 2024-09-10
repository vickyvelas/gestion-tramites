import entidades.Consultor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultorDAO {

    // Método para agregar un nuevo consultor
    public void agregarConsultor(Consultor consultor) throws SQLException {
        String sql = "INSERT INTO consultor (legajoConsultor, nombreApellidoConsultor, consultorNroMaxTramite, fechaHoraBajaConsultor) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consultor.getLegajoConsultor());
            stmt.setString(2, consultor.getNombreApellidoConsultor());
            stmt.setInt(3, consultor.getConsultorNroMaxTramite());
            stmt.setTimestamp(4, consultor.getFechaHoraBajaConsultor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al agregar el consultor.");
        }
    }

    // Método para obtener todos los consultores
    public List<Consultor> obtenerConsultores() throws SQLException {
        List<Consultor> consultores = new ArrayList<>();
        String sql = "SELECT * FROM consultor";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Consultor consultor = new Consultor();
                consultor.setLegajoConsultor(rs.getInt("legajoConsultor"));
                consultor.setNombreApellidoConsultor(rs.getString("nombreApellidoConsultor"));
                consultor.setConsultorNroMaxTramite(rs.getInt("consultorNroMaxTramite"));
                consultor.setFechaHoraBajaConsultor(rs.getTimestamp("fechaHoraBajaConsultor"));

                consultores.add(consultor);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los consultores.");
        }
        return consultores;
    }

    // Método para obtener un consultor por su legajo
    public Consultor obtenerConsultorPorId(int legajoConsultor) throws SQLException {
        String sql = "SELECT * FROM consultor WHERE legajoConsultor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, legajoConsultor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Consultor consultor = new Consultor();
                    consultor.setLegajoConsultor(rs.getInt("legajoConsultor"));
                    consultor.setNombreApellidoConsultor(rs.getString("nombreApellidoConsultor"));
                    consultor.setConsultorNroMaxTramite(rs.getInt("consultorNroMaxTramite"));
                    consultor.setFechaHoraBajaConsultor(rs.getTimestamp("fechaHoraBajaConsultor"));

                    return consultor;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el consultor.");
        }
        return null; // Si no se encuentra el consultor
    }

    // Método para actualizar un consultor existente
    public void actualizarConsultor(Consultor consultor) throws SQLException {
        String sql = "UPDATE consultor SET nombreApellidoConsultor = ?, consultorNroMaxTramite = ?, fechaHoraBajaConsultor = ? WHERE legajoConsultor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, consultor.getNombreApellidoConsultor());
            stmt.setInt(2, consultor.getConsultorNroMaxTramite());
            stmt.setTimestamp(3, consultor.getFechaHoraBajaConsultor());
            stmt.setInt(4, consultor.getLegajoConsultor());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el consultor.");
        }
    }

    // Método para eliminar un consultor por su legajo
    public void eliminarConsultor(int legajoConsultor) throws SQLException {
        String sql = "DELETE FROM consultor WHERE legajoConsultor = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, legajoConsultor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el consultor.");
        }
    }
}
