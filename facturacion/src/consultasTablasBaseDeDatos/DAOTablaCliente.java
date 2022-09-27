package consultasTablasBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DAOConexionMySQL;



public class DAOTablaCliente {
//	final String INSERT = "INSERT INTO cliente (nombre, email) VALUES(?, ?)";
	final String UPDATE = "UPDATE cliente SET nombre=?, email=? WHERE idCliente=?";
	final String DELETE = "DELETE FROM cliente WHERE idCliente=?";
	final String SELECTALL = "SELECT c FROM cliente c";
//	String sql = "SELECT c.nombre FROM cliente c";
	final String SELECTID = "SELECT c FROM cliente WHERE idCliente=?";
	
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

	
	public void getAll() {
//		conexion.crearConexion();
//
//		conexion.cerrarConexion();
//		return clientes;
	}


	public void insertar(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		
	}
	
	public void imprimirListaClientesMasFacturoOrdenado() {
		String consulta = "SELECT c.*, SUM(fp.cantidad)*p.valor as facturacion "
				+ "FROM Cliente c, Factura f, Factura_Producto fp, Producto p"
				+ "WHERE c.idCliente = f.idCliente AND f.idFactura=fp.idFactura AND fp.idProducto = p.idProducto"
				+ "GROUP BY idCliente ORDER BY facturacion DESC";
		
		Connection conn = conexion.getConn();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(consulta);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("Id: "+rs.getInt(1)+", Nombre: "+ rs.getString(2)+", Email: "+ rs.getString(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.cerrarConexion();
	}


}
