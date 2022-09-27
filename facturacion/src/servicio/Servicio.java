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
		
		
//		leemos los datos que nos dan desde la cátedra
		
		cargarDatosCliente(tablaCliente, "cliente.csv");
		
		cargarDatosProducto(tablaProducto, "producto.csv");
		
		cargarDatosFactura(tablaFactura, "factura.csv");
		
		cargarDatosFacturaProducto(tablaFacturaProducto, "factura producto.csv");
		
		//Resolución de consignas
		//consigna 3
		System.out.println(tablaProducto.obtenerProductoQueMasRecaudo());
		//consigna 4
		tablaCliente.imprimirListaClientesMasFacturoOrdenado();
	}
	
	private static void crearTablas() {
		conexion.cerrarConexion();
		Connection conn = conexion.getConn();

		
		String tablaCliente = "CREATE TABLE Cliente("
								+ "idCliente INT,"
								+ "nombre VARCHAR(500),"
								+ "email VARCHAR(150),"
								+ "PRIMARY KEY(idCliente))";

		
		String tablaProducto = "CREATE TABLE Producto("
								+ "idProducto INT, " 
								+ "nombre VARCHAR(45)," 
								+ "valor FLOAT,"
								+ "PRIMARY KEY(idProducto))";
		
		String tablaFacturaProducto = "CREATE TABLE Factura_Producto("
										+ "idFactura INT, " 
										+ "idProducto int," 
										+ "cantidad int," 
										+ "PRIMARY KEY(idFactura, idProducto),"
										+ "FOREIGN KEY(idFactura) references Factura(idFactura),"
										+ "FOREIGN KEY(idProducto) references Producto(idProducto))";
		
		String tablaFactura = "CREATE TABLE Factura("
								+ "idFactura INT, " 
								+ "idCliente int," 
								+ "PRIMARY KEY(idFactura),"
								+ "FOREIGN KEY(idCliente) references Cliente(idCliente))";
		
		
		try {
			conn.prepareStatement(tablaCliente).execute();
			conn.prepareStatement(tablaProducto).execute();
			conn.prepareStatement(tablaFacturaProducto).execute();
			conn.prepareStatement(tablaFactura).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		conexion.cerrarConexion();
		
	}

	
	private static void cargarDatosFacturaProducto(DAOTablaFactura_Producto tablaFacturaProducto, String string) {
		// TODO Auto-generated method stub
		
	}

	private static void cargarDatosFactura(DAOTablaFactura tablaFactura, String string) {
		// TODO Auto-generated method stub
		
	}

	private static void cargarDatosProducto(DAOTablaProducto tablaProducto, String string) {
		// TODO Auto-generated method stub
		
	}

	private static void cargarDatosCliente(DAOTablaCliente tablaCliente, String string) {
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.parse(new FileReader(string));
			
			for(CSVRecord row: parser) {
				System.out.println(row.get("idProducto"));
				System.out.println(row.get("nombre"));
				System.out.println(row.get("valor"));
				
				tablaCliente.insertar(row.get("idProducto"), row.get("nombre"), row.get("valor"));
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
