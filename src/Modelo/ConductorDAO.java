package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConductorDAO {

    public void obtenerConductores() {
        String query = "SELECT * FROM conductores";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println("Conductor: " + rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener conductores: " + e.getMessage());
        }
    }

    public boolean registrarConductor(Conductor conductor) {
        String sql = "INSERT INTO conductores (nombre, licencia, experiencia) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conductor.getNombre());
            stmt.setString(2, conductor.getLicencia());
            stmt.setInt(3, conductor.getExperiencia());

            stmt.executeUpdate();
            System.out.println("✅ Conductor registrado con éxito");
            return true;

        } catch (SQLException e) {
            System.err.println("❌ Error al registrar conductor");
            e.printStackTrace();
            return false;
        }
    }
    
    
    public List<Conductor> obtenerListaConductores() {
        List<Conductor> lista = new ArrayList<>();
        String query = "SELECT * FROM conductores";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Conductor c = new Conductor();
                c.setNombre(rs.getString("nombre"));
                c.setLicencia(rs.getString("licencia"));
                c.setExperiencia(rs.getInt("experiencia"));
                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener conductores: " + e.getMessage());
        }

        return lista;
    }

    
    
    public boolean actualizarConductorPorNombre(Conductor conductor) {
        String sql = "UPDATE conductores SET licencia = ?, experiencia = ? WHERE nombre = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, conductor.getLicencia());
            stmt.setInt(2, conductor.getExperiencia());
            stmt.setString(3, conductor.getNombre());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean eliminarConductorPorNombre(String nombre) {
        String sql = "DELETE FROM conductores WHERE nombre = ?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
