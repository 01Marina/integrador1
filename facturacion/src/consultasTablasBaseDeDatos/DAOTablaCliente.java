package consultasTablasBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.DAOConexionMySQL;




public class DAOTablaCliente {
	final String INSERT = "INSERT INTO Cliente (idCliente,nombre, email) VALUES(?,?,?)";
	final String UPDATE = "UPDATE Cliente SET nombre=?, email=? WHERE idCliente=?";
	final String DELETE = "DELETE FROM Cliente WHERE idCliente=?";
	final String SELECTALL = "SELECT c FROM Cliente c";
//	String sql = "SELECT c.nombre FROM cliente c";
	final String SELECTID = "SELECT c FROM Cliente WHERE idCliente=?";
	
	private static final DAOConexionMySQL conexion = DAOConexionMySQL.crearConexion();
	
	public DAOTablaCliente() {}

	
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
		List<String> clientes = new ArrayList<>();   //al no haber objetos, se me ocurrio asi
		try {
			ps = conn.prepareStatement(SELECTALL);
			rs = ps.executeQuery();  
			while(rs.next()) {                   
				clientes.add(rs.getString(1) + ", "+rs.getString(2) + ", "+rs.getString(3));                       //agrego lista de ResultSet               
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
		return clientes;
	}

	


	public void insertar(String idCliente, String nombre, String email) {
		Connection conn = conexion.getConn();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT); 
			ps.setString(1, idCliente); 
			ps.setString(2, nombre);
			ps.setString(3, email);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
	}


}
