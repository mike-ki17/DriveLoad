
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
}
