package consultasTablasBaseDeDatos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import conexion.DAOConexionJPAHibernate;
import entidades.Cliente;



public class DAOTablaCliente implements DAOTabla<Cliente> {
//	final String INSERT = "INSERT INTO cliente (nombre, email) VALUES(?, ?)";
	final String UPDATE = "UPDATE cliente SET nombre=?, email=? WHERE idCliente=?";
	final String DELETE = "DELETE FROM cliente WHERE idCliente=?";
	final String SELECTALL = "SELECT c FROM cliente c";
//	String sql = "SELECT c.nombre FROM cliente c";
//	final String SELECTID = "SELECT c FROM cliente WHERE idCliente=?";
	
	private static final DAOConexionJPAHibernate conexion = DAOConexionJPAHibernate.crearConexion2();
	
	public DAOTablaCliente() {}

	@Override
	public Optional<Cliente> get(long id) {
		EntityManager em = conexion.getEm();
		Cliente cliente = em.find(Cliente.class, id);
		
//		em.createQuery(SELECTID).setParameter(1, id);
		
		conexion.cerrarConexion();
		return Optional.of(cliente);
	}

	@Override
	public List<Cliente> getAll() {
		conexion.crearConexion();
		EntityManager em = conexion.getEm(); 
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = em.createQuery(SELECTALL).getResultList();
		conexion.cerrarConexion();
		return clientes;
	}

	@Override
	public void save(Cliente c) {
		conexion.crearConexion();
		EntityManager em = conexion.getEm();
		
		
		em.persist(c);
		
//		em.createQuery(INSERT)
//			.setParameter(1, p.getId())
//			.setParameter(2, p.getNombre())
//			.setParameter(3, p.getEdad())
//			.executeUpdate();
		
		conexion.cerrarConexion();
		
	}

	@Override
	public void update(Cliente c, String nombre, String email) {
		EntityManager em = conexion.getEm();
			//actualizo los datos de la persona
			c.setNombre(nombre);
			c.setEmail(email);
			
			em.createQuery(UPDATE)
				.setParameter(1, c.getIdCliente())
				.setParameter(2, c.getNombre())
				.setParameter(3, c.getEmail())
				.executeUpdate();
			
			conexion.cerrarConexion();
			
	}

	@Override
	public void delete(Cliente c) {
		EntityManager em = conexion.getEm();
		
		em.createNativeQuery(DELETE)
			.setParameter(1, c.getIdCliente())
			.executeUpdate();
		
		conexion.cerrarConexion();
		
	}   
}
