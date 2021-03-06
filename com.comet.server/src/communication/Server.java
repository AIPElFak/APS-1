package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import utilities.DocumentRemote;
import utilities.UserRemote;
import utilities.VersionRemote;

public interface Server extends Remote {
	
	void lobbyBroadcast(String message, Client cl) throws RemoteException;
	
	void documentBroadcast(Client cl, String message) throws RemoteException;
	
	boolean login (Client cl, String username, String password) throws RemoteException;
	
	boolean logout (Client cl) throws RemoteException;
	
	boolean signin (Client cl, String username, String password, String email, byte[] imageBytes) throws RemoteException;
	
	boolean deleteAccount (Client cl) throws RemoteException;
	
	boolean modifyUserData (Client cl) throws RemoteException;
	
	boolean resetPassword(String email) throws RemoteException;
	
	void logActivity(String message) throws RemoteException;
	
	ArrayList<DocumentRemote> getAllAvailableDocuments() throws RemoteException;
	
	ArrayList<DocumentRemote> searchDocuments(String criteria) throws RemoteException;
	
	void addClientToDocument(Client cl, int docId) throws RemoteException;
	
	void addClient (Client cl) throws RemoteException;
	
	void removeClient (Client cl) throws RemoteException;
	
	boolean createDocument(Client cl, String name, String type, String password) throws RemoteException;
	
	String openDocument(Client cl, int documentId, String passwordEntered) throws RemoteException;
	
	boolean editUserInformations(Client client, String username, String email, String password, byte[] image) throws RemoteException;
	
	boolean deleteDocument(Client cl, int documentId) throws RemoteException;
	
	boolean addDocumentVersion(Client cl, int documentId, String content) throws RemoteException;
	
	ArrayList<VersionRemote> getAllDocumentVersions(int documentId) throws RemoteException;	

	String openDocumentVersion(int versionId) throws RemoteException;
	
	void sendDocUpdate(Client cl, String type, String text, int length, int location) throws RemoteException;
	
	void setPrivileges(Client cl, int userId, int documentId, String privilege) throws RemoteException;

	void setPrivilegies(Client client, UserRemote value, String string) throws RemoteException;

	void removeFromDocument(Client client, UserRemote value, int id) throws RemoteException;
}
