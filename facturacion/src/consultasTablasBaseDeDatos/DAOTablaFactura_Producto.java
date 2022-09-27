package consultasTablasBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexion.DAOConexionMySQL;

public class DAOTablaFactura_Producto {
	final String INSERT = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES(?,?,?)";
	//final String UPDATE = "UPDATE Factura_Producto SET nombre=?, valor=? WHERE idProducto=?";
	final String DELETE = "DELETE FROM Factura_Producto WHERE idFactura=?";
	final String SELECTALL = "SELECT fp FROM Factura_Producto fp LEFT JOIN idFactura = idProducto";
		
//	String sql = "SELECT c.nombre FROM cliente c";
	final String SELECTID = "SELECT fp FROM Factura_Producto WHERE idProducto=?";
	
	private static final DAOConexionMySQL conexion = DAOConexionMySQL.crearConexion();
	
	public DAOTablaFactura_Producto() {}

	
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
		List<String> facturas_productos = new ArrayList<>();   //al no haber objetos, se me ocurrio asi
		try {
			ps = conn.prepareStatement(SELECTALL);
			rs = ps.executeQuery();  
			while(rs.next()) {                   
				facturas_productos.add(rs.getString(1) + ", "+rs.getString(2) + ", "+rs.getString(3));                       //agrego lista de ResultSet               
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
		return facturas_productos;
	}

	


	public void insertar(int idFactura, int idProducto, int cantidad) {
		conexion.abrirConexion();
		Connection conn = conexion.getConn();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(INSERT); 
			ps.setInt(1, idFactura); 
			ps.setInt(2, idProducto);
			ps.setInt(3, cantidad);
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
	}

}
