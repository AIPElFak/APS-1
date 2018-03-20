package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

import components.Authenticator;
import components.DocumentSynchronizer;
import components.MessageServer;
import utilities.DocumentList;
import utilities.DocumentRemote;

public interface Server extends Remote {
	
	void lobbyBroadcast(String message, Client cl) throws RemoteException;
	
	void documentBroadcast(DocumentRemote doc, Client cl, String message) throws RemoteException;
	
	boolean login (Client cl, String username, String password) throws RemoteException;
	
	boolean logout (Client cl) throws RemoteException;
	
	boolean signin (Client cl, String username, String password, String email, String imageUrl) throws RemoteException;
	
	boolean deleteAccount (Client cl) throws RemoteException;
	
	boolean modifyUserData (Client cl) throws RemoteException;
	
	void logActivity(String message) throws RemoteException;
	
	DocumentList getAllAvailableDocuments() throws RemoteException;
	
	void addClientToDocument(Client cl, int docId) throws RemoteException;
	
	void addClient (Client cl) throws RemoteException;
	
	void removeClient (Client cl) throws RemoteException;
	
}
