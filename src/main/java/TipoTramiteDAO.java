
import entidades.CategoriaTramite;
import entidades.TipoTramite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TipoTramiteDAO {

    // Método para agregar un nuevo TipoTramite
    public void agregarTipoTramite(TipoTramite tipoTramite) throws SQLException {
        String sql = "INSERT INTO tipo_tramite (codTipoTramite, nombreTipoTramite, descripcionTipoTramite, descripcionTipoTramiteWeb, maxDiaEntrega, fechaHoraBajaTipoTramite, categoriaTramite) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, tipoTramite.getCodTipoTramite());
            stmt.setString(2, tipoTramite.getNombreTipoTramite());
            stmt.setString(3, tipoTramite.getDescripcionTipoTramite());
            stmt.setString(4, tipoTramite.getDescripcionTipoTramiteWeb());
            stmt.setInt(5, tipoTramite.getMaxDiaEntrega());
            stmt.setTimestamp(6, tipoTramite.getFechaHoraBajaTipoTramite());
            stmt.setInt(7, tipoTramite.getCategoriaTramite().getId()); // Asegúrate de que `CategoriaTramite` tenga un método `getId()`

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error al agregar el tipo de trámite.");
        }
    }

    // Método para obtener todos los TipoTramite
    public List<TipoTramite> obtenerTipoTramites() throws SQLException {
        List<TipoTramite> tipoTramites = new ArrayList<>();
        String sql = "SELECT * FROM tipo_tramite";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoTramite tipoTramite = new TipoTramite();
                tipoTramite.setCodTipoTramite(rs.getInt("codTipoTramite"));
                tipoTramite.setNombreTipoTramite(rs.getString("nombreTipoTramite"));
                tipoTramite.setDescripcionTipoTramite(rs.getString("descripcionTipoTramite"));
                tipoTramite.setDescripcionTipoTramiteWeb(rs.getString("descripcionTipoTramiteWeb"));
                tipoTramite.setMaxDiaEntrega(rs.getInt("maxDiaEntrega"));
                tipoTramite.setFechaHoraBajaTipoTramite(rs.getTimestamp("fechaHoraBajaTipoTramite"));

                // Aquí se debería obtener el `CategoriaTramite` desde la base de datos o de otra fuente
                int categoriaTramiteId = rs.getInt("categoriaTramite");
                CategoriaTramite categoriaTramite = obtenerCategoriaTramitePorId(categoriaTramiteId); // Implementa este método según tus necesidades
                tipoTramite.setCategoriaTramite(categoriaTramite);

                tipoTramites.add(tipoTramite);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los tipos de trámite.");
        }
        return tipoTramites;
    }

    // Método para obtener un TipoTramite por su código
    public TipoTramite obtenerTipoTramitePorCodigo(int codTipoTramite) throws SQLException {
        String sql = "SELECT * FROM tipo_tramite WHERE codTipoTramite = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codTipoTramite);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    TipoTramite tipoTramite = new TipoTramite();
                    tipoTramite.setCodTipoTramite(rs.getInt("codTipoTramite"));
                    tipoTramite.setNombreTipoTramite(rs.getString("nombreTipoTramite"));
                    tipoTramite.setDescripcionTipoTramite(rs.getString("descripcionTipoTramite"));
                    tipoTramite.setDescripcionTipoTramiteWeb(rs.getString("descripcionTipoTramiteWeb"));
                    tipoTramite.setMaxDiaEntrega(rs.getInt("maxDiaEntrega"));
                    tipoTramite.setFechaHoraBajaTipoTramite(rs.getTimestamp("fechaHoraBajaTipoTramite"));

                    // Aquí se debería obtener el `CategoriaTramite` desde la base de datos o de otra fuente
                    int categoriaTramiteId = rs.getInt("categoriaTramite");
                    CategoriaTramite categoriaTramite = obtenerCategoriaTramitePorId(categoriaTramiteId); // Implementa este método según tus necesidades
                    tipoTramite.setCategoriaTramite(categoriaTramite);

                    return tipoTramite;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el tipo de trámite.");
        }
        return null; // Si no se encuentra el tipo de trámite
    }

    // Método para actualizar un TipoTramite existente
    public void actualizarTipoTramite(TipoTramite tipoTramite) throws SQLException {
        String sql = "UPDATE tipo_tramite SET nombreTipoTramite = ?, descripcionTipoTramite = ?, descripcionTipoTramiteWeb = ?, maxDiaEntrega = ?, fechaHoraBajaTipoTramite = ?, categoriaTramite = ? WHERE codTipoTramite = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tipoTramite.getNombreTipoTramite());
            stmt.setString(2, tipoTramite.getDescripcionTipoTramite());
            stmt.setString(3, tipoTramite.getDescripcionTipoTramiteWeb());
            stmt.setInt(4, tipoTramite.getMaxDiaEntrega());
            stmt.setTimestamp(5, tipoTramite.getFechaHoraBajaTipoTramite());
            stmt.setInt(6, tipoTramite.getCategoriaTramite().getId()); // Asegúrate de que `CategoriaTramite` tenga un método `getId()`
            stmt.setInt(7, tipoTramite.getCodTipoTramite());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el tipo de trámite.");
        }
    }

    // Método para eliminar un TipoTramite por su código
    public void eliminarTipoTramite(int codTipoTramite) throws SQLException {
        String sql = "DELETE FROM tipo_tramite WHERE codTipoTramite = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codTipoTramite);
            stmt.executeUpdate();
        }    }

    // Método auxiliar para obtener un CategoriaTramite por su ID (implementa este método según tus necesidades)
    private CategoriaTramite obtenerCategoriaTramitePorId(int id) throws SQLException {
        // Implementa la lógica para obtener un CategoriaTramite desde la base de datos
        // Por ejemplo, podrías usar un DAO similar al `CategoriaTramiteDAO`
        // Aquí se muestra un ejemplo básico que devuelve un objeto vacío para ilustrar
        CategoriaTramite categoriaTramite = new CategoriaTramite();
        categoriaTramite.setId(id);
        return categoriaTramite;
    }

    TipoTramite obtenerTipoTramitePorId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

