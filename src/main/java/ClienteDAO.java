import entidades.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // Método para agregar un nuevo cliente
    public void agregarCliente(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO cliente (cuitCliente, direccionCliente, fechaHoraAltaCliente, fechaHoraBajaCliente, mailCliente, nombreApellidoCliente, telefonoCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cliente.getCuitCliente());
            stmt.setString(2, cliente.getDireccionCliente());
            stmt.setTimestamp(3, cliente.getFechaHoraAltaCliente());
            stmt.setTimestamp(4, cliente.getFechaHoraBajaCliente());
            stmt.setString(5, cliente.getMailCliente());
            stmt.setString(6, cliente.getNombreApellidoCliente());
            stmt.setDouble(7, cliente.getTelefonoCliente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al agregar el cliente.");
        }
    }

    // Método para obtener todos los clientes
    public List<Cliente> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCuitCliente(rs.getInt("cuitCliente"));
                cliente.setDireccionCliente(rs.getString("direccionCliente"));
                cliente.setFechaHoraAltaCliente(rs.getTimestamp("fechaHoraAltaCliente"));
                cliente.setFechaHoraBajaCliente(rs.getTimestamp("fechaHoraBajaCliente"));
                cliente.setMailCliente(rs.getString("mailCliente"));
                cliente.setNombreApellidoCliente(rs.getString("nombreApellidoCliente"));
                cliente.setTelefonoCliente(rs.getDouble("telefonoCliente"));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener los clientes.");
        }
        return clientes;
    }

    // Método para obtener un cliente por su CUIT
    public Cliente obtenerClientePorCuit(int cuitCliente) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE cuitCliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cuitCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCuitCliente(rs.getInt("cuitCliente"));
                    cliente.setDireccionCliente(rs.getString("direccionCliente"));
                    cliente.setFechaHoraAltaCliente(rs.getTimestamp("fechaHoraAltaCliente"));
                    cliente.setFechaHoraBajaCliente(rs.getTimestamp("fechaHoraBajaCliente"));
                    cliente.setMailCliente(rs.getString("mailCliente"));
                    cliente.setNombreApellidoCliente(rs.getString("nombreApellidoCliente"));
                    cliente.setTelefonoCliente(rs.getDouble("telefonoCliente"));

                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el cliente.");
        }
        return null; // Si no se encuentra el cliente
    }

    // Método para actualizar un cliente existente
    public void actualizarCliente(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET direccionCliente = ?, fechaHoraAltaCliente = ?, fechaHoraBajaCliente = ?, mailCliente = ?, nombreApellidoCliente = ?, telefonoCliente = ? WHERE cuitCliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getDireccionCliente());
            stmt.setTimestamp(2, cliente.getFechaHoraAltaCliente());
            stmt.setTimestamp(3, cliente.getFechaHoraBajaCliente());
            stmt.setString(4, cliente.getMailCliente());
            stmt.setString(5, cliente.getNombreApellidoCliente());
            stmt.setDouble(6, cliente.getTelefonoCliente());
            stmt.setInt(7, cliente.getCuitCliente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar el cliente.");
        }
    }

    // Método para eliminar un cliente por su CUIT
    public void eliminarCliente(int cuitCliente) throws SQLException {
        String sql = "DELETE FROM cliente WHERE cuitCliente = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cuitCliente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar el cliente.");
        }
    }

    Cliente obtenerClientePorId(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
