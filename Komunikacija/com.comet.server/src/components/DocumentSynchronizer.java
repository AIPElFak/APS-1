package components;

import java.rmi.*;

import communication.Client;

public interface DocumentSynchronizer extends Remote {
	
	boolean addClient (Client cl) throws RemoteException;
	
	boolean removeClient (Client cl) throws RemoteException;
	
}
