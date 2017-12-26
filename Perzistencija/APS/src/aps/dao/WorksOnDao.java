package aps.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aps.dto.*;
import aps.test.dto.HibernateUtil;

public class WorksOnDao {

	public void addWorksOnAllNew(WorksOn w) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
												//persist radi samo sa novim objektima, ako se proslede postojeci
												//user i document prijavljuje gresku
			s.persist(w);									//zbog CascadeType.PERSIST
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	public void addWorksOnAllExisting(int docId, int userId, String privilege) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			Document d = s.load(Document.class, docId);
			User u = s.load(User.class, userId);
			WorksOn w = new WorksOn(d,u,privilege);
			s.save(w);
			
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	public void addWorksOnExistingDocument(int docId, User u, String privilege) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			Document d = s.load(Document.class, docId);
			WorksOn w = new WorksOn(d,u,privilege);
			s.save(u);
			s.save(w);
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	public void addWorksOnExistingUser(int userId, Document d, String privilege) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			User u = s.load(User.class, userId);
			WorksOn w = new WorksOn(d,u,privilege);
			s.save(d);
			s.save(w);
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	public void deleteWorksOn(int id) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			WorksOn w = s.load(WorksOn.class, id);
			s.delete(w);
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	public void updateWorksOn(WorksOn w) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.update(w);
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	public WorksOn getWorksOnById(int id) {
		Transaction trns = null;
		WorksOn w = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "from WorksOn where id = :wId";
			w = (WorksOn)s.createQuery(query)
					.setParameter("wId", id)
					.uniqueResult();
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return w;
	}
}
