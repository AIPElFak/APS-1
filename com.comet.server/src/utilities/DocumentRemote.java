package utilities;

import java.rmi.*;

import communication.Client;

public interface DocumentRemote extends Remote {

	int getId() throws RemoteException;
	
	String getName() throws RemoteException;
	
	String getType() throws RemoteException;
	
	boolean isPasswordProtected() throws RemoteException;
	
	String getPrivilege() throws RemoteException;
	
	void addClientToThisDocument(Client cl) throws RemoteException;
	
}
