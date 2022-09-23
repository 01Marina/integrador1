package servicio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import conexion.DAOConexionMySQL;
import consultasTablasBaseDeDatos.DAOTablaCliente;
import consultasTablasBaseDeDatos.DAOTablaFactura;
import consultasTablasBaseDeDatos.DAOTablaFactura_Producto;
import consultasTablasBaseDeDatos.DAOTablaProducto;

public class Servicio {
	
	private static final DAOConexionMySQL conexion = DAOConexionMySQL.crearConexion();

	public static void main(String[] args) {
		
		crearTablas();
		
		DAOTablaCliente tablaCliente = new DAOTablaCliente();
		DAOTablaProducto tablaProducto = new DAOTablaProducto();
		DAOTablaFactura tablaFactura = new DAOTablaFactura();
		DAOTablaFactura_Producto tablaFacturaProducto = new DAOTablaFactura_Producto();
		
		
//		leemos los datos que nos dan desde la catedra
		
		cargarDatosCliente(tablaCliente, "cliente.csv");
		
		cargarDatosProducto(tablaProducto, "producto.csv");
		
		cargarDatosFactura(tablaFactura, "factura.csv");
		
		cargarDatosFacturaProducto(tablaFacturaProducto, "factura producto.csv");
	}



	private static void crearTablas() {
		conexion.cerrarConexion();
		Connection conn = conexion.getConn();

		String tablaCliente = "CREATE TABLE cliente(" + "id INT, " + "nombre VARCHAR(500)," + "edad INT,"
				+ "PRIMARY KEY(id))";
		
		String tablaFacturaProducto = "";
		
		try {
			conn.prepareStatement(tablaCliente).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
		
	}

	private static void cargarDatosFactura(DAOTablaFactura tablaFactura, String string) {
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.parse(new FileReader(string));
			
			for(CSVRecord row: parser) {
				System.out.println(row.get("idFactura"));
				System.out.println(row.get("idCliente"));
				
				tablaFactura.insertar(row.get("idFactura"), row.get("idCliente"));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void cargarDatosProducto(DAOTablaProducto tablaProducto, String string) {
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.parse(new FileReader(string));
			
			for(CSVRecord row: parser) {
				System.out.println(row.get("idProducto"));
				System.out.println(row.get("nombre"));
				System.out.println(row.get("valor"));
				
				tablaProducto.insertar(row.get("idProducto"), row.get("nombre"), row.get("valor"));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void cargarDatosCliente(DAOTablaCliente tablaCliente, String string) {
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.parse(new FileReader(string));
			
			for(CSVRecord row: parser) {
				System.out.println(row.get("idCliente"));
				System.out.println(row.get("nombre"));
				System.out.println(row.get("email"));
				
				tablaCliente.insertar(row.get("idCliente"), row.get("nombre"), row.get("email"));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void cargarDatosFacturaProducto(DAOTablaFactura_Producto tablaFacturaProducto, String string) {
	
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.parse(new FileReader(string));
			
			for(CSVRecord row: parser) {
				System.out.println(row.get("idFactura"));
				System.out.println(row.get("idProducto"));
				System.out.println(row.get("cantidad"));
				tablaFacturaProducto.insertar(row.get("idFactura"), row.get("idProducto"), row.get("cantidad"));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
