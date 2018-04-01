package components;

import java.rmi.*;
import java.util.ArrayList;

import communication.Client;
import utilities.DocumentRemote;

public interface DocumentService extends Remote {
	
	boolean addClient (Client cl) throws RemoteException;
	
	boolean removeClient (Client cl) throws RemoteException;
	
	ArrayList<DocumentRemote> getAllAvailableDocuments() throws RemoteException;
	
	ArrayList<DocumentRemote> searchDocuments(String criteria) throws RemoteException;
	
	void addClientToDocument(Client cl, int docId) throws RemoteException;
	
	boolean createDocument(Client cl, String name, String type, String password) throws RemoteException;
	
	String openDocument (Client cl, int documentId) throws RemoteException;
}
