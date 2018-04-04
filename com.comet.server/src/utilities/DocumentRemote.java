package utilities;

import java.rmi.*;
import java.util.List;

import communication.Client;

public interface DocumentRemote extends Remote {

	int getId() throws RemoteException;
	
	String getName() throws RemoteException;
	
	String getType() throws RemoteException;
	
	boolean isPasswordProtected() throws RemoteException;
	
	String getPassword() throws RemoteException;
	
	String getPrivilege() throws RemoteException;
	
	void addClientToThisDocument(Client cl) throws RemoteException;
	
	void removeCollaborator(Client cl) throws RemoteException;
	
	List<Client> getCollaborators() throws RemoteException;
	
	String getCurrentContent() throws RemoteException;
	
	void setCurrentContent(String content) throws RemoteException;
}
