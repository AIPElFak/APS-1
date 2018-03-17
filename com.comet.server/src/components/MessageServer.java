package components;

import java.rmi.*;

import communication.Client;
import utilities.Document;

public interface MessageServer extends Remote {
	
	void lobbyBroadcast(String message, Client cl) throws RemoteException;
	
	void documentBroadcast(Document doc, Client cl, String message) throws RemoteException;
	
	boolean addClient (Client cl) throws RemoteException;
	
	boolean removeClient (Client cl) throws RemoteException;
}
