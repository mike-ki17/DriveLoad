
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


public class VehiculoDAO {
    public void obtenerUsuarios() {
        String query = "SELECT * FROM vehiculos";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Usuario: " + rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener usuarios: " + e.getMessage());
        }
    }
    
    
    public boolean registrarVehiculo(Vehiculo vehiculo){
        String sql = "INSERT INTO vehiculos (placa, tipo, capacidad) VALUES ( ?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehiculo.getPlaca());
            stmt.setString(2, vehiculo.getTipo());
            stmt.setDouble(3, vehiculo.getCapacidad());

            stmt.executeUpdate();
            System.out.println("✅ Vehículo registrado con éxito");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar vehículo");
            e.printStackTrace();
            return false;
        }
    }
    
    
    public void obtenerVehiculosTabla(DefaultTableModel modelo) {
        String query = "SELECT * FROM vehiculos";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("placa"),
                    rs.getString("tipo"),
                    rs.getDouble("capacidad")
                };
                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener vehículos: " + e.getMessage());
        }
    }
    


    public void limpiarModelo(DefaultTableModel modelo) {
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
}


