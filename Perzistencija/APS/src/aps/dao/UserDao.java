package aps.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aps.dto.Document;
import aps.dto.User;
import aps.dto.WorksOn;
import aps.test.dto.HibernateUtil;
import aps.test.dto.Info;

public class UserDao {

	/*public void addUser(User u) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.save(u);
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
	}*/
	
	//user registration
	public Info addUser(User u) {
		Transaction trns = null;
		Info info = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String uquery = "SELECT ID FROM USER_DETAILS WHERE BINARY USERNAME = :username";
			boolean usernameExists = s.createNativeQuery(uquery)
									.setParameter("username", u.getUsername())
									.uniqueResult() != null;
			
			String equery = "SELECT 0 FROM USER_DETAILS WHERE BINARY EMAIL = :email";
			boolean  emailExists = s.createNativeQuery(equery)
									.setParameter("email", u.getEmail())
									.uniqueResult() != null;
			
			if(usernameExists) 
				info = new Info("Username allredy exists.",false);
			else if(emailExists)
				info = new Info("Email allredy exists.",false);
			else {
				info = new Info();
				s.save(u);
			}
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
		return info;
	}
	
	
	public void deleteUser(int id) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			User u = s.load(User.class, id);
			s.delete(u);
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns != null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	public void updateUser(User user) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.update(user);
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns != null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
	}
	
	//Done with query because function load is "too much lazy"
	public User getUserById(int id) {
		Transaction trns = null;
		User u = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "from User u where id = :userId";
			u = (User)s.createQuery(query)
					.setParameter("userId", id)
					.uniqueResult();
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns != null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return u;
	}
	
	public ArrayList<Document> getAllDocuments (int userId){
		Transaction trns = null;
		ArrayList<Document> docs = new ArrayList<Document>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			User u = s.load(User.class, userId);
			for(WorksOn w : u.getDocuments()) {
				docs.add(w.getDocument());
			}
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns != null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return docs;
	}
	
	public ArrayList<WorksOn> getAllWorksOn(int userId){
		Transaction trns = null;
		ArrayList<WorksOn> workings = new ArrayList<WorksOn>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			User u = s.load(User.class, userId);
			for(WorksOn w : u.getDocuments()) {
				workings.add(w);
			}
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns != null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return workings;
	}
	
	//Returns user object if parameters are correct else returns null
	//Do check and write appropriate message on login place
	public User login (String username, String password) {
		Transaction trns = null;
		User user = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			//keyword BINARY makes comparison byte by byte and makes it case sensitive
			String query = "SELECT * FROM USER_DETAILS WHERE BINARY USERNAME = :usrnm AND BINARY PASS = :pass";
			user = (User)s.createNativeQuery(query,User.class)
					.setParameter("usrnm", username)
					.setParameter("pass", password)						//TODO: use some hashing method here
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
		return user;
	}
}
