package database.dao;

import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import database.dto.*;
import database.test.dto.HibernateUtil;

public class DocumentVersionDao extends Repository<DocumentVersion> {

	public DocumentVersionDao() {
		this.setClassType(DocumentVersion.class);
	}
	

	public DocumentVersion getDocumentVersionByDate(Date dateTime, Document d) {
		Transaction trns = null;
		DocumentVersion v = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "from DocumentVersion where dateTime = :dt and document = :doc";
			v = (DocumentVersion)s.createQuery(query)
					.setParameter("dt", dateTime)
					.setParameter("doc", d)
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
		return v;
	}

	public ArrayList<DocumentVersion> getDocumentVersionsByUser(User u, Document d){
		Transaction trns = null;
		ArrayList<DocumentVersion> vers = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "from DocumentVersion where user = :user and document = :doc";
			vers = (ArrayList<DocumentVersion>) s.createQuery(query,DocumentVersion.class)
					.setParameter("user", u)
					.setParameter("doc", d)
					.list();
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
		return vers;
	}
	
	public DocumentVersion getDocumentLastVersion(Document doc){
		Transaction trns = null;
		DocumentVersion last = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "SELECT * FROM DOCUMENT_VERSION" + 
							" WHERE DOCUMENT_ID = :docId" + 
							" AND ID = (SELECT MAX(ID) FROM DOCUMENT_VERSION WHERE DOCUMENT_ID = :docId);";
			last = (DocumentVersion)s.createNativeQuery(query,DocumentVersion.class)
					.setParameter("docId", doc.getId())
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
		return last;
	}
	
	public ArrayList<DocumentVersion> getAllDocumentVersions(Document doc){
		Transaction trns = null;
		ArrayList<DocumentVersion> list = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "from DocumentVersion where document = :doc";
			list = (ArrayList<DocumentVersion>)s.createQuery(query,DocumentVersion.class)
					.setParameter("doc", doc)
					.list();
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
