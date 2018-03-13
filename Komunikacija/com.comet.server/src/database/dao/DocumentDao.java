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
	
	public ArrayList<WorksOn> getUsersWorksOnDocument(int documentId){
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
