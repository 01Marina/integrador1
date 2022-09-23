package consultasTablasBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.DAOConexionMySQL;

public class DAOTablaProducto  {
	final String INSERT = "INSERT INTO Producto (idProducto,nombre, valor) VALUES(?,?,?)";
	final String UPDATE = "UPDATE Producto SET nombre=?, valor=? WHERE idProducto=?";
	final String DELETE = "DELETE FROM Producto WHERE idProducto=?";
	final String SELECTALL = "SELECT p Producto producto p";
//	String sql = "SELECT c.nombre FROM cliente c";
	final String SELECTID = "SELECT p FROM Producto WHERE idProducto=?";
	
	private static final DAOConexionMySQL conexion = DAOConexionMySQL.crearConexion();
	
	public DAOTablaProducto() {}

	
	public void get(long id) {
		
		Connection conn = conexion.getConn();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(SELECTID);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
//				System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getInt(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
	}

	
	public List<String> getAll() {
		Connection conn = conexion.getConn();
		PreparedStatement ps;
		ResultSet rs = null;
		List<String> productos = new ArrayList<>();   //al no haber objetos, se me ocurrio asi
		try {
			ps = conn.prepareStatement(SELECTALL);
			rs = ps.executeQuery();  
			while(rs.next()) {                   
				productos.add(rs.getString(1) + ", "+rs.getString(2) + ", "+rs.getString(3));                       //agrego lista de ResultSet               
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
		return productos;
	}

	


	public void insertar(String idProducto, String nombre, String valor) {
		Connection conn = conexion.getConn();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT); 
			ps.setString(1, idProducto); 
			ps.setString(2, nombre);
			ps.setString(3, valor);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
	}

}
