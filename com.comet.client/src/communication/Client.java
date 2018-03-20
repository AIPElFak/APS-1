package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

import controller.ControllerOnline;
import utilities.DocumentRemote;
import utilities.UserRemote;

public interface Client extends Remote {
	
	void lobbyRecv(Client cl, String message) throws RemoteException;
	
	void documentRecv(DocumentRemote doc, Client cl, String message) throws RemoteException;
	
	void setUserData(UserRemote userData) throws RemoteException;
	
	void setWorkingDocument(DocumentRemote doc) throws RemoteException;
	
	UserRemote getUserData() throws RemoteException;
	
}
