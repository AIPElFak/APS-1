package communication;

import java.rmi.RemoteException;

import components.Authenticator;
import components.DocumentSynchronizer;
import components.MessageServer;
import utilities.Document;

public interface ServerFacade {
	
	Authenticator getAuthenticator() throws RemoteException;
	
	MessageServer getMessageServer() throws RemoteException;
	
	DocumentSynchronizer getDocumentSynchronizer() throws RemoteException;
	
	void addClient (Client cl) throws RemoteException;
	
	void removeClient (Client cl) throws RemoteException;
}
