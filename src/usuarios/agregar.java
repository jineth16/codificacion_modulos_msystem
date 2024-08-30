package analisis_reportes;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class agregar {
    public static void main(String[] args) {
        // Datos del nuevo usuario
        String nombre_usuario = "pedrorojas";
        String contrasena = "123456789";
        boolean recordar = false;

        // Conexión
        conexion con = new conexion();
        Connection cn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            cn = con.getConnection();

            if (cn != null) {
                st = cn.createStatement();

                // Consulta SQL para insertar un nuevo usuario
                String sqlInsert = "INSERT INTO usuarios (nombre_usuario, contrasena, recordar) "
                                 + "VALUES ('" + nombre_usuario + "', '" + contrasena + "', " + recordar + ")";

                // Ejecutar la consulta de inserción
                int rowsInserted = st.executeUpdate(sqlInsert, Statement.RETURN_GENERATED_KEYS);

                if (rowsInserted > 0) {
                    System.out.println("Nuevo usuario agregado correctamente.");

                    // Obtener el ID generado automáticamente
                    rs = st.getGeneratedKeys();
                    if (rs.next()) {
                        int id_usuario = rs.getInt(1);

                        // Consulta SQL para obtener todos los usuarios
                        String sqlSelect = "SELECT * FROM usuarios";

                        // Ejecutar la consulta de selección
                        rs = st.executeQuery(sqlSelect);

                        // Mostrar los resultados en la consola
                        System.out.println("Lista de Usuarios:");
                        while (rs.next()) {
                            System.out.println("ID Usuario: " + rs.getInt("id"));
                            System.out.println("Nombre Usuario: " + rs.getString("nombre_usuario"));
                            System.out.println("Contraseña: " + rs.getString("contrasena"));
                            System.out.println("Recordar: " + rs.getBoolean("recordar"));
                            System.out.println("---------------------------");
                        }
                    }
                } else {
                    System.out.println("No se pudo agregar el nuevo usuario.");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(agregar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

