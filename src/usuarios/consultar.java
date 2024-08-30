package usuarios;

import conexion.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class consultar {
    public static void main(String[] args) {
        // Nombre de usuario a buscar
        String nombre_usuario = "jinethbetancourt";

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

                // Consulta SQL para obtener el usuario por nombre de usuario
                String sqlSelect = "SELECT * FROM usuarios WHERE nombre_usuario = '" + nombre_usuario + "'";

                // Ejecutar la consulta de selección
                rs = st.executeQuery(sqlSelect);

                // Mostrar los resultados en la consola
                System.out.println("Resultados de la consulta:");
                if (rs.next()) {
                    System.out.println("ID Usuario: " + rs.getInt("id"));
                    System.out.println("Nombre Usuario: " + rs.getString("nombre_usuario"));
                    System.out.println("Contraseña: " + rs.getString("contrasena"));
                    System.out.println("Recordar: " + rs.getBoolean("recordar"));
                    System.out.println("---------------------------");
                } else {
                    System.out.println("No se encontraron usuarios con el nombre de usuario: " + nombre_usuario);
                }
            } else {
                System.out.println("No se pudo conectar a la base de datos.");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(consultar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
