package controller;

import java.util.List;

import communication.Client;
import utilities.DocumentRemote;
import utilities.UserRemote;

public interface ControllerOnline extends Controller {
	
	boolean logIn(String username, String password);
	
	boolean logOut(Client cl);
	
	boolean signIn(String username, String password, String email);
	
	boolean modifyUserData(Client cl);
	
	boolean deleteAccount();
	
	void sendLobbyMessage(String Message);
	
	void sendDocumentMessage(String Message);
	
	void recvLobbyMessage(Client cl, String Message);
	
	void recvDocumentMessage(Client cl, String Message);
	
	void saveDocument(String path, String name);
	
	void addClientToDocument(int docId);
	
	void displayAllAvailableDocuments();
	
	void searchDocuments(String criteria);
	
	void createNewDocument();
	
	void openDocument(String path, String name);
	
	boolean createDocument(String name, String type, String password);
	
	void deleteDocument(Client cl, DocumentRemote doc);
	
	boolean openDocument(DocumentRemote doc);
	
	void setClient(Client cl);
	
	void removeClient(Client cl);
	
	void updateCollaborators(List<UserRemote> collabs);
	
	UserRemote getUserData();
	
}
