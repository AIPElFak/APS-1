package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import communication.Client;
import utilities.DocumentRemote;
import utilities.UserRemote;
import utilities.VersionRemote;

public interface ControllerOnline extends Controller {
	
	boolean logIn(String username, String password);
	
	boolean logOut(Client cl);
	
	boolean signIn(String username, String password, String email, byte[] imageBytes);
	
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
	
	boolean deleteDocument(DocumentRemote doc);
	
	boolean openDocument(DocumentRemote doc);
	
	void setClient(Client cl);
	
	void removeClient(Client cl);
	
	void updateCollaborators(List<UserRemote> collabs);
	
	UserRemote getUserData();

	boolean editUserInformations(String username, String email, String password, byte[] image); 	//treba i slika

	void recvDocUpdate(String type, String text, int length, int location);
	
	void sendDocUpdate(String type, String text, int length, int location);
	
	ArrayList<VersionRemote> getAllDocumentVersions();

	String openDocumentVersion(int id);

	boolean resetPassword(String email);

	void setPrivilegies(UserRemote value, String string);

	void removeUserFromDocument(UserRemote value);

	void goBackToLobby();
	
}
