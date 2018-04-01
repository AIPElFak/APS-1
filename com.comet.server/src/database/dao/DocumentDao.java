package database.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import database.dto.*;
import database.test.dto.HibernateUtil;

public class DocumentDao extends Repository<Document>{

	public DocumentDao() {
		this.setClassType(Document.class);
	}
	
	public boolean deleteIfOwner(int documentId, int userId) {
		boolean success = false;
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "SELECT 0 FROM WORKS_ON WHERE USER_ID = :userId"
												+ " AND DOCUMENT_ID = :docId"
												+ " AND PRIVILEGE = :privilege";
			boolean isOwner = s.createNativeQuery(query)
									.setParameter("userId", userId)
									.setParameter("docId", documentId)
									.setParameter("privilege", WorksOn.Privilege.Owner.toString())
									.uniqueResult() != null;

			if(isOwner) {
				Document doc = s.load(Document.class, documentId);
				s.delete(doc);
				success = true;
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
		return success;
	}
	
	public ArrayList<WorksOn> getAllWorksOnDocument(int documentId){
		Transaction trns = null;
		ArrayList<WorksOn> list = new ArrayList<WorksOn>();
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			Document d = s.load(Document.class, documentId);
			for(WorksOn w : d.getUsers()) {
				list.add(w);
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
		return list;
	}
		
}
