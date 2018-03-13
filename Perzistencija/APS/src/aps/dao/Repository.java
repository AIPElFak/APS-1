package aps.dao;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.Transaction;

import aps.test.dto.HibernateUtil;

public class Repository<TEntity> implements IRepository<TEntity>{

	private Class<TEntity> classType;
	
	public Class<TEntity> getClassType() {
		return classType;
	}
	public void setClassType(Class<TEntity> classType) {
		this.classType = classType;
	}

	@Override
	public void add(TEntity entity) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.save(entity);
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

	@Override
	public void update(TEntity entity) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.update(entity);
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

	@Override
	public void delete(TEntity entity) {
		Transaction trns = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			s.delete(entity);
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

	@Override
	public TEntity getById(int id) {
		Transaction trns = null;
		TEntity entity = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			entity = s.get(classType,id);
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
		return entity;
	}

	@Override
	public ArrayList<TEntity> getAll(int limit) {
		Transaction trns = null;
		ArrayList<TEntity> list = null;;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			String q = "from " + classType.getName();
			list = (ArrayList<TEntity>)s.createQuery(q, classType).setMaxResults(limit).list();
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

//	public DocumentVersion getDocumentVersionById(int versionId) {
//	Transaction trns = null;
//	DocumentVersion v = null;
//	Session s = HibernateUtil.getSessionFactory().openSession();
//	try {
//		trns = s.beginTransaction();
//		//v = s.load(DocumentVersion.class, versionId);
//		String query = "from DocumentVersion where id = :vId";
//		v = (DocumentVersion)s.createQuery(query)
//				.setParameter("vId", versionId)
//				.uniqueResult();
//		s.getTransaction().commit();
//	}
//	catch(RuntimeException e) {
//		if(trns!=null) {
//			s.getTransaction().rollback();
//		}
//		e.printStackTrace();
//	}
//	finally {
//		s.close();
//	}
//	return v;
//}
}
