package communication;

import java.rmi.RemoteException;

import components.AuthenticationService;
import components.DocumentService;
import components.MessageService;
import utilities.DocumentRemote;

public interface ServerFacade {
	
	AuthenticationService getAuthenticationService() throws RemoteException;
	
	MessageService getMessageService() throws RemoteException;
	
	DocumentService getDocumentService() throws RemoteException;
	
	void addClient (Client cl) throws RemoteException;
	
	void removeClient (Client cl) throws RemoteException;
}