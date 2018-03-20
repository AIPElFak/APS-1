package components;

import java.rmi.*;

import communication.Client;
import utilities.DocumentList;
import utilities.DocumentRemote;

public interface DocumentSynchronizer extends Remote {
	
	boolean addClient (Client cl) throws RemoteException;
	
	boolean removeClient (Client cl) throws RemoteException;
	
	DocumentList getAllAvailableDocuments() throws RemoteException;
	
	void addClientToDocument(Client cl, int docId) throws RemoteException;
	
}
