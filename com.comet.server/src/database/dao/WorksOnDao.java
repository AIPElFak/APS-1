package database.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import database.dto.*;
import database.test.dto.HibernateUtil;

public class WorksOnDao extends Repository<WorksOn>{
	
	public WorksOnDao() {
		this.setClassType(WorksOn.class);
	}
	
	public boolean addWorksOnAllExisting(int docId, int userId, WorksOn.Privilege privilege) {
		Transaction trns = null;
		boolean success = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			Document d = s.load(Document.class, docId);
			User u = s.load(User.class, userId);
			WorksOn w = new WorksOn(d,u,privilege);
			s.save(w);
			
			s.getTransaction().commit();
			success = true;
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
		return success;
	}


	public boolean addWorksOnForNewDocument(Document doc, int userId) {
		Transaction trns = null;
		boolean success = false;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.save(doc);
			User u = s.load(User.class, userId);
			WorksOn w = new WorksOn(doc, u, WorksOn.Privilege.Owner);
			s.save(w);
			
			s.getTransaction().commit();
			success = true;
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
		return success;
	}
	
	
	public ArrayList<JoinRequest> getDocumentsByPrivilegeAndUser(User user, WorksOn.Privilege privilege) {
		Transaction trns = null;
		ArrayList<WorksOn> list = null;
		ArrayList<JoinRequest> requests = new ArrayList<JoinRequest>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "from WorksOn where privilege = :privilege and user = :user";
			list = (ArrayList<WorksOn>)s.createQuery(query, WorksOn.class)
					.setParameter("privilege", privilege.toString())
					.setParameter("user", user)
					.list();
			
			for(WorksOn w : list)
				requests.addAll(w.getDocument().getRequests());
			
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) 
				s.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return requests;
	}
	
	
	public ArrayList<JoinRequest> getRequestsForOwnerDocuments(User owner) {
		Transaction trns = null;
		ArrayList<WorksOn> list = null;
		ArrayList<JoinRequest> requests = new ArrayList<JoinRequest>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "from WorksOn where privilege = :privilege and user = :user";
			list = (ArrayList<WorksOn>)s.createQuery(query, WorksOn.class)
					.setParameter("privilege", WorksOn.Privilege.Owner.toString())
					.setParameter("user", owner)
					.list();
			
			for(WorksOn w : list)
				requests.addAll(w.getDocument().getRequests());
			
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) 
				s.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			s.close();
		}
		return requests;
	}
	
	//if new on document - creates new worksOn with ReadOnly privilege
	//if already worked on document - just returns privilege
	public String startWorkingOnDocument(int userId, int documentId) {
		Transaction trns = null;
		String privilege = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "SELECT * FROM WORKS_ON WHERE USER_ID = :userId AND DOCUMENT_ID = :docId";
			WorksOn w = (WorksOn)s.createNativeQuery(query, WorksOn.class)
					.setParameter("userId", userId)
					.setParameter("docId", documentId)
					.uniqueResult();
			if(w != null) {
				privilege = w.getPrivilege();
			}else {
				privilege = WorksOn.Privilege.ReadOnly.toString();
				Document d = s.load(Document.class, documentId);
				User u = s.load(User.class, userId);
				WorksOn wo = new WorksOn(d,u,WorksOn.Privilege.ReadOnly);
				s.save(wo);
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
		return privilege;
	}
	
	
	public void changePrivilege(int userId, int documentId, String privilege) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "UPDATE WORKS_ON SET PRIVILEGE = :priv WHERE USER_ID = :userId AND DOCUMENT_ID = :docId";
			s.createNativeQuery(query, WorksOn.class)
					.setParameter("priv", privilege)
					.setParameter("userId", userId)
					.setParameter("docId", documentId)
					.executeUpdate();
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
}
