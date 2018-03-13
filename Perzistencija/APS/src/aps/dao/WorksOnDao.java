package aps.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aps.dto.*;
import aps.test.dto.HibernateUtil;

public class WorksOnDao extends Repository<WorksOn>{
	
	public WorksOnDao() {
		this.setClassType(WorksOn.class);
	}
	
	public void addWorksOnAllExisting(int docId, int userId, WorksOn.Privilege privilege) {
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
	
}
