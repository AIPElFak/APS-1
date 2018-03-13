package aps.dao;

import java.util.ArrayList;

import org.hibernate.Transaction;
import org.hibernate.Session;

import aps.dto.JoinRequest;
import aps.test.dto.HibernateUtil;

public class JoinRequestDao extends Repository<JoinRequest>{

	
	public JoinRequestDao() {
		this.setClassType(JoinRequest.class);
	}
	
	public ArrayList<JoinRequest> getNotifications(int ownerId){
		Transaction trns = null;
		ArrayList<JoinRequest> list = null;
		Session s = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = s.beginTransaction();
			
			s.getTransaction().commit();
		}
		catch(RuntimeException e) {
			if(trns!=null) {
				s.getTransaction().rollback();
			}
			e.printStackTrace();
		}
		return list;
	}
	
}
