package database.test.dto;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	
	/*Ovo je static block koji postoji u samo Javi i izvrsava se samo jednom 
	 * tj. prvi put kad se pristupa ovoj klasi HibernateUtil. Ovim je ostvaren singleton.
	 * Dakle, samo jednom se kreira sessionFactory. Treba naglasiti i da se poziva i pre konstruktroa
	 * http://www.geeksforgeeks.org/g-fact-79/*/
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex) {
			System.err.println("Initial SessionFactory creation failed."+ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
