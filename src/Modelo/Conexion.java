
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    private static final String URL = "jdbc:mysql://localhost:3306/sisTransport_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "m7K3$18AL9";
    private static Connection connection = null;
    
     // Método para obtener conexión (singleton)
    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Asegúrate de tener el conector MySQL
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Error al conectar: " + e.getMessage());
            }
        }
        return connection;
    }

    
}
