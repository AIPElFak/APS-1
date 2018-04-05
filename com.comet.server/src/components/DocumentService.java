package components;

import java.rmi.*;
import java.util.ArrayList;

import communication.Client;
import utilities.DocumentRemote;
import utilities.UserRemote;
import utilities.VersionRemote;

public interface DocumentService extends Remote {
	
	boolean addClient (Client cl) throws RemoteException;
	
	boolean removeClient (Client cl) throws RemoteException;
	
	ArrayList<DocumentRemote> getAllAvailableDocuments() throws RemoteException;
	
	ArrayList<DocumentRemote> searchDocuments(String criteria) throws RemoteException;
	
	void addClientToDocument(Client cl, int docId) throws RemoteException;
	
	boolean createDocument(Client cl, String name, String type, String password) throws RemoteException;
	
	String openDocument (Client cl, int documentId, String passwordEntered) throws RemoteException;
	
	boolean deleteDocument(Client cl, int documentId) throws RemoteException;
	
	boolean addDocumentVersion(Client cl, int documentId, String content) throws RemoteException;
	
	String openDocumentVersion(int versionId) throws RemoteException;

	ArrayList<VersionRemote> getAllDocumentVersions(int documentId) throws RemoteException;

	void sendDocUpdate(Client cl, String type, String text, int length, int location) throws RemoteException;

	void setPrivileges(Client cl, int userId, int documentId, String privilege) throws RemoteException;

	void removeFromDocument(Client client, UserRemote value, int id) throws RemoteException;

}
