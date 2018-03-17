package controller;

import communication.Client;
import utilities.Document;

public interface ControllerOnline extends ControllerTemplate {
	
	boolean logIn(String username, String password);
	
	boolean logOut(Client cl);
	
	void signIn(String username, String password, String email);
	
	void sendLobbyMessage(String Message);
	
	void sendDocumentMessage(Client cl, String Message);
	
	void recvLobbyMessage(Client cl, String Message);
	
	void recvDocumentMessage(Client cl, String Message);
	
	void saveDocument(String path, String name);
	
	void createNewDocument();
	
	public void openDocument(String path, String name);
	
	public void createDocument(Client cl, String name, String lang, String password);
	
	public void deleteDocument(Client cl, Document doc);
	
	public void openDocument(Client cl, Document doc);
	
	public void setClient(Client cl);
	
	public void removeClient(Client cl);
	
}
