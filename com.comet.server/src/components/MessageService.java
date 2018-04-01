package components;

import java.rmi.*;

import communication.Client;
import utilities.DocumentRemote;

public interface MessageService extends Remote {
	
	void lobbyBroadcast(String message, Client cl) throws RemoteException;
	
	void documentBroadcast(DocumentRemote doc, Client cl, String message) throws RemoteException;
	
	boolean addClient (Client cl) throws RemoteException;
	
	boolean removeClient (Client cl) throws RemoteException;
}
