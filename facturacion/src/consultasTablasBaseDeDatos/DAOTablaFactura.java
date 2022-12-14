package consultasTablasBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.DAOConexionMySQL;

public class DAOTablaFactura {
	final String INSERT = "INSERT INTO Factura (idFactura, idCliente) VALUES(?, ?)";
	final String UPDATE = "UPDATE Factura SET idCliente=? WHERE idFactura=?";
	final String DELETE = "DELETE FROM Factura WHERE idFactura=?";
	final String SELECTALL = "SELECT f FROM Factura f";
//	String sql = "SELECT c.nombre FROM cliente c";
	final String SELECTID = "SELECT f FROM Factura WHERE idFactura=?";
	
	private static final DAOConexionMySQL conexion = DAOConexionMySQL.crearConexion();
	
	public DAOTablaFactura() {}

	
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
		List<String> facturas = new ArrayList<>();   //al no haber objetos, se me ocurrio asi
		try {
			ps = conn.prepareStatement(SELECTALL);
			rs = ps.executeQuery();  
			while(rs.next()) {                   
				facturas.add(rs.getString(1) + ", "+rs.getString(2));                     
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
		return facturas;
	}

	


	public void insertar(int idFactura, int idCliente) {
		conexion.abrirConexion();
		Connection conn = conexion.getConn();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT); 
			ps.setInt(1, idFactura); 
			ps.setInt(2, idCliente);
			ps.executeUpdate();
			ps.close();
	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
	}


	public void crearTabla() {
		conexion.abrirConexion();
		Connection conn = conexion.getConn();

		String tablaFactura = "CREATE TABLE Factura("
				+ "idFactura INT, " 
				+ "idCliente int," 
				+ "PRIMARY KEY(idFactura),"
				+ "FOREIGN KEY(idCliente) references Cliente(idCliente))";
		try {
			conn.prepareStatement(tablaFactura).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
		
	}
}
