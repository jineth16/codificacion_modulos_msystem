package usuarios;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class eliminar {
    public static void main(String[] args) {
        // ID del usuario a eliminar
        int id_usuario = 5;

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

                // Consulta SQL para eliminar el usuario
                String sqlDelete = "DELETE FROM usuarios WHERE id = " + id_usuario;

                // Ejecutar la consulta de eliminación
                int rowsDeleted = st.executeUpdate(sqlDelete);

                if (rowsDeleted > 0) {
                    System.out.println("Usuario eliminado correctamente.");
                } else {
                    System.out.println("No se encontró el usuario con ID: " + id_usuario);
                }

                // Mostrar los datos de la tabla usuarios después de la eliminación
                String sqlSelect = "SELECT * FROM usuarios";
                rs = st.executeQuery(sqlSelect);

                System.out.println("\nDatos de la tabla usuarios:");
                while (rs.next()) {
                    int idUsuario = rs.getInt("id");
                    String nombreUsuario = rs.getString("nombre_usuario");
                    String contrasena = rs.getString("contrasena");
                    boolean recordar = rs.getBoolean("recordar");

                    System.out.println("ID: " + idUsuario);
                    System.out.println("Nombre Usuario: " + nombreUsuario);
                    System.out.println("Contraseña: " + contrasena);
                    System.out.println("Recordar: " + recordar);
                    System.out.println("-------------------------");
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(eliminar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

