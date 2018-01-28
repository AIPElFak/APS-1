package components;

import java.rmi.*;

import communication.Client;

public interface Authenticator extends Remote {
	
	boolean login (Client cl, String username, String password) throws RemoteException;
	
	boolean logout (Client cl) throws RemoteException;
	
	boolean signin (Client cl) throws RemoteException;
	
	boolean deleteAccount (Client cl) throws RemoteException;
	
	boolean modifyUserData (Client cl) throws RemoteException;
	
	boolean addClient (Client cl) throws RemoteException;
	
	boolean removeClient (Client cl) throws RemoteException;
}
