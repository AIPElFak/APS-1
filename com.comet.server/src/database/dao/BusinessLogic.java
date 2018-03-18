package database.dao;

import java.util.ArrayList;

import database.dto.Document;
import database.dto.User;
import database.test.dto.Info;

public class BusinessLogic {

	UserDao userDao = new UserDao();
	DocumentDao documentDao = new DocumentDao();
	DocumentVersionDao documentVersionDao = new DocumentVersionDao();
	WorksOnDao worksOnDao = new WorksOnDao();
	JoinRequestDao joinRequestDao = new JoinRequestDao();
	
	public User login(String username, String password) {
		return userDao.login(username, password);
	}
	
	public Info register(User user) {
		return userDao.register(user);
	}
	
	public boolean deleteUser(User user) {
		return userDao.delete(user);
	}

	public boolean updateUser(User user) {
		return userDao.update(user);
	}

	public ArrayList<Document> getAvailableDocuments(int limit) {
		return documentDao.getAll(limit);
	}
}
