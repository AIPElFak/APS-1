package database.dao;

import java.util.ArrayList;

import database.dto.Document;
import database.dto.DocumentVersion;
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
	
	public boolean deleteUserById(int id) {
		return userDao.deleteById(id);
	}

	public boolean updateUser(User user) {
		return userDao.update(user);
	}

	public ArrayList<Document> getAvailableDocuments(int limit) {
		return documentDao.getAll(limit);
	}
	
	public DocumentVersion getLastDocumentVersion(int documentId) {
		return documentVersionDao.getDocumentLastVersion(documentId);
	}
	
	public boolean createDocument(int id, Document doc) {
		return worksOnDao.addWorksOnForNewDocument(doc, id);
	}
		
	public boolean deleteDocument(int documentId, int userId) {
		return documentDao.deleteIfOwner(documentId, userId);
	}

	public boolean addDocumentVersion(int documentId, int userId, String content) {
		return documentVersionDao.add(documentId, userId, content);
	}

	public ArrayList<DocumentVersion> getAllDocumentVersions(int documentId) {
		return documentVersionDao.getAllDocumentVersions(documentId);
	}

	public String openDocumentVersion(int versionId) {
		return documentVersionDao.getById(versionId).getContent();
	}
}
