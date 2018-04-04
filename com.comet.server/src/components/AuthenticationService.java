package components;

import java.rmi.*;

import communication.Client;

public interface AuthenticationService extends Remote {
	
	boolean login (Client cl, String username, String password) throws RemoteException;
	
	boolean logout (Client cl) throws RemoteException;
	
	boolean signin (Client cl, String username, String password, String email, byte[] imageBytes) throws RemoteException;
	
	boolean deleteAccount (Client cl) throws RemoteException;
	
	boolean modifyUserData (Client cl) throws RemoteException;
	
	boolean resetPassword(String email) throws RemoteException;

	boolean addClient (Client cl) throws RemoteException;
	
	boolean removeClient (Client cl) throws RemoteException;

	boolean editUserInformations(Client client, String username, String email, String password, byte[] image) throws RemoteException;

}