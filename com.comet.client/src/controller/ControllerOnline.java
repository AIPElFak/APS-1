package controller;

import communication.Client;
import utilities.DocumentRemote;
import utilities.UserRemote;

public interface ControllerOnline extends Controller {
	
	boolean logIn(String username, String password);
	
	boolean logOut(Client cl);
	
	boolean signIn(String username, String password, String email);
	
	void sendLobbyMessage(String Message);
	
	void sendDocumentMessage(Client cl, String Message);
	
	void recvLobbyMessage(Client cl, String Message);
	
	void recvDocumentMessage(Client cl, String Message);
	
	void saveDocument(String path, String name);
	
	void addClientToDocument(int docId);
	
	void displayAllAvailableDocuments();
	
	void searchDocuments(String criteria);
	
	void createNewDocument();
	
	public void openDocument(String path, String name);
	
	public void createDocument(Client cl, String name, String lang, String password);
	
	public void deleteDocument(Client cl, DocumentRemote doc);
	
	public void openDocument(Client cl, DocumentRemote doc);
	
	public void setClient(Client cl);
	
	public void removeClient(Client cl);
	
}
