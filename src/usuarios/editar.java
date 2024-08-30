package usuarios;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class editar {
    public static void main(String[] args) {
        // ID del usuario a editar
        int id_usuario = 3;
        // Nuevos valores para el usuario
        String nuevaContrasena = "aeiou";
        boolean nuevoRecordar = true;

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

                // Consulta SQL para actualizar el usuario
                String sqlUpdate = "UPDATE usuarios SET contrasena = '" + nuevaContrasena + "', recordar = " + nuevoRecordar + " WHERE id = " + id_usuario;

                // Ejecutar la consulta de actualización
                int rowsUpdated = st.executeUpdate(sqlUpdate);

                if (rowsUpdated > 0) {
                    System.out.println("Usuario actualizado correctamente.");

                    // Mostrar el usuario actualizado
                    String sqlSelect = "SELECT * FROM usuarios WHERE id = " + id_usuario;
                    rs = st.executeQuery(sqlSelect);
                    if (rs.next()) {
                        System.out.println("ID Usuario: " + rs.getInt("id"));
                        System.out.println("Nombre Usuario: " + rs.getString("nombre_usuario"));
                        System.out.println("Contraseña: " + rs.getString("contrasena"));
                        System.out.println("Recordar: " + rs.getBoolean("recordar"));
                        System.out.println("---------------------------");
                    }
                } else {
                    System.out.println("No se encontró el usuario con ID: " + id_usuario);
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}