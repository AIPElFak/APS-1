package aps.dao;


import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aps.dto.*;
import aps.test.dto.HibernateUtil;

public class DocumentDao {

	public void addDocument(Document doc) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.save(doc);
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
	
	public void deleteDocument(int id) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			Document d = s.load(Document.class, id);
			s.delete(d);
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
	
	public void updateDocument(Document doc) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.update(doc);
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
	
	public Document getDocumentById(int documentId) {
		Transaction trns = null;
		Document d = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String q = "from Document where id = :id";
			d = (Document)s.createQuery(q)
					.setParameter("id", documentId)
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
		return d;
	}

	public ArrayList<Document> getAllDocuments() {
		Transaction trns = null;
		ArrayList<Document> list = null;;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String q = "from Document";
			list = (ArrayList<Document>)s.createQuery(q,Document.class).list();
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
	
	
	
	public DocumentVersion getDocumentLastVersion(int documentId){
		Transaction trns = null;
		DocumentVersion last = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "SELECT * FROM document_version" + 
							" WHERE DOCUMENT_ID = :docId" + 
							" AND ID = (SELECT MAX(ID) FROM document_version WHERE DOCUMENT_ID = :docId);";
			last = (DocumentVersion)s.createNativeQuery(query,DocumentVersion.class)
					.setParameter("docId", documentId)
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
	
	public ArrayList<DocumentVersion> getAllDocumentVersions(int documentId){
		Transaction trns = null;
		ArrayList<DocumentVersion> list = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String query = "SELECT * FROM document_version" + 
							" WHERE DOCUMENT_ID = :docId";
			list = (ArrayList<DocumentVersion>)s.createNativeQuery(query,DocumentVersion.class)
					.setParameter("docId", documentId)
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
	
	public void addDocumentVersion(DocumentVersion v) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.save(v);
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
	
	public void deleteDocumentVersion(int versionId) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			DocumentVersion v = s.load(DocumentVersion.class, versionId);
			s.delete(v);
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
	
	public void updateDocumentVersion(DocumentVersion v) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.update(v);
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
	
	public DocumentVersion getDocumentVersionById(int versionId) {
		Transaction trns = null;
		DocumentVersion v = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			//v = s.load(DocumentVersion.class, versionId);
			String query = "from DocumentVersion where id = :vId";
			v = (DocumentVersion)s.createQuery(query)
					.setParameter("vId", versionId)
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

	public DocumentVersion getDocumentVersionByDate(Date dateTime,Document d) {
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
	
	
	
}
