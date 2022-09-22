package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAOConexionJPAHibernate{
	
	public static DAOConexionJPAHibernate instancia;
	private static  EntityManagerFactory emf = null;
	private static EntityManager em = null;
	
	private DAOConexionJPAHibernate() {
		emf = Persistence.createEntityManagerFactory("Example");
		em = emf.createEntityManager();
	}

	public DAOConexionJPAHibernate crearConexion() {
		return crearConexion2();		
	}
	
	public static synchronized DAOConexionJPAHibernate crearConexion2() {
		if(instancia == null || !emf.isOpen()) {
			instancia = new DAOConexionJPAHibernate();
		}
		return instancia;
	}
	
	public void cerrarConexion() {
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	public EntityManager getEm() {
		em.getTransaction().begin();
		return em;
	}
}
