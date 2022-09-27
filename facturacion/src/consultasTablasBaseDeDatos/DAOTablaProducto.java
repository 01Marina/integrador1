package consultasTablasBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.DAOConexionMySQL;
import entidades.Producto;

public class DAOTablaProducto  {
	
	private static final DAOConexionMySQL conexion = DAOConexionMySQL.crearConexion();
	
	public DAOTablaProducto() {}
	
	public Producto obtenerProductoQueMasRecaudo() {
		String consulta = "SELECT p.idProducto, SUM(f.cantidad)*p.valor as recaudacion"
				+ "FROM Producto p JOIN Factura_Producto f on p.idProducto = f.idProducto "
				+ "GROUP BY idProducto ORDER BY recaudacion DESC LIMIT 1";
		
		Connection conn = conexion.getConn();
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(consulta);
			
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("id: "+rs.getInt(1)+", Nombre: "+ rs.getString(2)+", Valor: "+ rs.getFloat(3));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conexion.cerrarConexion();
		
		return null;
	}

}
