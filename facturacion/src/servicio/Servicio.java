package servicio;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import consultasTablasBaseDeDatos.DAOTablaCliente;
import consultasTablasBaseDeDatos.DAOTablaFactura;
import consultasTablasBaseDeDatos.DAOTablaFactura_Producto;
import consultasTablasBaseDeDatos.DAOTablaProducto;

public class Servicio {
	

	public static void main(String[] args) {
		
		DAOTablaCliente tablaCliente = new DAOTablaCliente();
		DAOTablaProducto tablaProducto = new DAOTablaProducto();
		DAOTablaFactura tablaFactura = new DAOTablaFactura();
		DAOTablaFactura_Producto tablaFacturaProducto = new DAOTablaFactura_Producto();
		
		// Crea las tablas
		tablaCliente.crearTabla();
		tablaProducto.crearTabla();
		tablaFactura.crearTabla();
		tablaFacturaProducto.crearTabla();
		
//		leemos los datos que nos dan desde la cátedra	
		
		String urlCliente = "src/csv/clientes.csv";
		String urlProducto = "src/csv/productos.csv";
		String urlFactura = "src/csv/facturas.csv";
		String urlFacturaProducto = "src/csv/facturasProductos.csv";
				
		cargarDatosCliente(tablaCliente, urlCliente);
		
		cargarDatosProducto(tablaProducto, urlProducto);
		
		cargarDatosFactura(tablaFactura, urlFactura);
		
		cargarDatosFacturaProducto(tablaFacturaProducto, urlFacturaProducto);

//		Resolución de consignas
//		consigna 3
		System.out.println("Producto que más recaudó:");
		tablaProducto.obtenerProductoQueMasRecaudo();
		
//		consigna 4
		System.out.println(System.lineSeparator()+"Lista de clientes con más facturación:");
		tablaCliente.imprimirListaClientesMasFacturoOrdenado();

	}

	private static void cargarDatosFactura(DAOTablaFactura tablaFactura, String string) {
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.parse(new FileReader(string));
			
			for(CSVRecord row: parser) {
				if(!row.get(0).equals("idFactura")) {
					tablaFactura.insertar(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)));
				}
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
				if(!row.get(0).equals("idProducto")) {
					tablaProducto.insertar(Integer.parseInt(row.get(0)),row.get(1), Float.parseFloat( row.get(2)));
				}
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
				if(!row.get(0).equals("idCliente")) {
					tablaCliente.insertar(Integer.parseInt(row.get(0)) , row.get(1), row.get(2));
				}
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
				if(!row.get(0).equals("idFactura")) {
					tablaFacturaProducto.insertar(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)), Integer.parseInt(row.get(2)));
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
