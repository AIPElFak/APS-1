package database.dao;

import database.dto.JoinRequest;

public class JoinRequestDao extends Repository<JoinRequest>{

	public JoinRequestDao() {
		this.setClassType(JoinRequest.class);
	}
	
}
