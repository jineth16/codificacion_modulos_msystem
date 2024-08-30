package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    Connection con;

    public conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalsystem", "root", "1234");
            System.out.println("Conectado a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No conectado");
        }
    }

    public Connection getConnection() {
        return con;
    }
}

