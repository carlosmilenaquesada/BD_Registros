package paquete;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {

    //Cuando se utiliza el patrón singleton, las instancias de la clase se crean
    //medinte un método que comprobará si ya existe una instancia de esa clase,
    //por eso, es necesario establecer como privado el constructor de la clase, 
    //para evitar que se puedan hacer instancias de la clase que no sea a través
    //del método del singleton.
    private Conexion() {

    }

    // Lo primer creamos una variable en el cual vamos a guardar el estado de 
    // la conexión a nuestra BD.
    private static Connection conexion;

    //Creamos una variable para crear una sola instancia.
    private static Conexion instancia;

    //Creamos las variables para poder conectarnos a la BD.
    private static final String URL = "jdbc:mysql://localhost/bd_registros";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    //Creamos el método para conectarnos a la base de datos
    public Connection conectar() {
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");//No es necesario desde JDBC ver 4
            conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return conexion;
    }

    //Creamos el método para cerrar la conexión
    public void cerrarConexion() throws SQLException {
        try {
            conexion.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            conexion.close();
        } finally {
            conexion.close();
        }
    }

    //Patrón singleton
    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}
