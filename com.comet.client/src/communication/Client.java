package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;

import controller.ControllerOnline;
import utilities.Document;
import utilities.UserData;

public interface Client extends Remote {
	
	void lobbyRecv(Client cl, String message) throws RemoteException;
	
	void documentRecv(Document doc, Client cl, String message) throws RemoteException;
	
	void directRecv(Client cl, String message) throws RemoteException;
	
	void setUserData(UserData userData) throws RemoteException;
	
	UserData getUserData() throws RemoteException;
	
}
