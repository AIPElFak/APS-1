package aps.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aps.dto.*;
import aps.test.dto.HibernateUtil;

public class DocumentVersionDao {

//	public void addDocumentVersion(DocumentVersion v) {
//		Transaction trns = null;
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		try {
//			trns = s.beginTransaction();
//			s.save(v);
//			s.getTransaction().commit();
//		}
//		catch(RuntimeException e) {
//			if(trns!=null) {
//				s.getTransaction().rollback();
//			}
//			e.printStackTrace();
//		}
//		finally {
//			s.close();
//		}		
//	}
//	
//	public void deleteDocumentVersion(int versionId) {
//		Transaction trns = null;
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		try {
//			trns = s.beginTransaction();
//			DocumentVersion v = s.load(DocumentVersion.class, versionId);
//			s.delete(v);
//			s.getTransaction().commit();
//		}
//		catch(RuntimeException e) {
//			if(trns!=null) {
//				s.getTransaction().rollback();
//			}
//			e.printStackTrace();
//		}
//		finally {
//			s.close();
//		}		
//	}
//	
//	public void updateDocumentVersion(DocumentVersion v) {
//		Transaction trns = null;
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		try {
//			trns = s.beginTransaction();
//			s.update(v);
//			s.getTransaction().commit();
//		}
//		catch(RuntimeException e) {
//			if(trns!=null) {
//				s.getTransaction().rollback();
//			}
//			e.printStackTrace();
//		}
//		finally {
//			s.close();
//		}		
//	}
//	
//	public DocumentVersion getDocumentVersionById(int versionId) {
//		Transaction trns = null;
//		DocumentVersion v = null;
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		try {
//			trns = s.beginTransaction();
//			//v = s.load(DocumentVersion.class, versionId);
//			String query = "from DocumentVersion where id = :vId";
//			v = (DocumentVersion)s.createQuery(query)
//					.setParameter("vId", versionId)
//					.uniqueResult();
//			s.getTransaction().commit();
//		}
//		catch(RuntimeException e) {
//			if(trns!=null) {
//				s.getTransaction().rollback();
//			}
//			e.printStackTrace();
//		}
//		finally {
//			s.close();
//		}
//		return v;
//	}
//	
//	//TODO: razmisliti da se ova f-ja prebaci u DocumentDao
//	public DocumentVersion getDocumentVersionByDate(Date dateTime,Document d) {
//		Transaction trns = null;
//		DocumentVersion v = null;
//		Session s = HibernateUtil.getSessionFactory().openSession();
//		try {
//			trns = s.beginTransaction();
//			String query = "from DocumentVersion where dateTime = :dt and document = :doc";
//			v = (DocumentVersion)s.createQuery(query)
//					.setParameter("dt", dateTime)
//					.setParameter("doc", d)
//					.uniqueResult();
//			s.getTransaction().commit();
//		}
//		catch(RuntimeException e) {
//			if(trns!=null) {
//				s.getTransaction().rollback();
//			}
//			e.printStackTrace();
//		}
//		finally {
//			s.close();
//		}
//		return v;
//	}
	
}
