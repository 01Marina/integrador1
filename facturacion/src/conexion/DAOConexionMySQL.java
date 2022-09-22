package conexion;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAOConexionMySQL{
	
	public static DAOConexionMySQL instancia;
	
	private static final String HOST = "localhost";
	private static final String PUERTO = "3306";
	private static final String DB = "facturacion";
	
	private static String usuario = "root";
	private static String contraseña = "admin";
	
	private static String  uri = "jdbc:mysql://";
	private static Connection CONN = null;
	private static String driver = "\"com.mysql.cj.jdbc.Driver\"";
	
	private DAOConexionMySQL() {
		this.instanciarConexion();
	}
	
	private void instanciarConexion() {
		//activo el driver
			try {
				Class.forName(driver).getDeclaredConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//creo la conexión
			try {
				uri += HOST+":"+PUERTO+"/"+DB;
				CONN = DriverManager.getConnection(uri, usuario, contraseña);
				CONN.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
	}
	
	public static synchronized DAOConexionMySQL crearConexion() {
		if(instancia == null) {
			instancia = new DAOConexionMySQL();
		}
		return instancia;
	}
	
	public void cerrarConexion() {
		try {
			CONN.commit();
			CONN.close();
			instancia = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return CONN;
	}

	


}
