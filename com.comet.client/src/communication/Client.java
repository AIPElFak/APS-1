package communication;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import controller.ControllerOnline;
import utilities.DocumentRemote;
import utilities.UserRemote;

public interface Client extends Remote {
	
	void lobbyRecv(Client cl, String message) throws RemoteException;
	
	void documentRecv(Client cl, String message) throws RemoteException;
	
	void setUserData(UserRemote userData) throws RemoteException;
	
	void setWorkingDocument(DocumentRemote doc) throws RemoteException;
	
	void updateCollaboratorsList(List<UserRemote> collaborators) throws RemoteException;
	
	UserRemote getUserData() throws RemoteException;
	
	DocumentRemote getDocumentData() throws RemoteException;
	
	void recvAllDocuments() throws RemoteException;
	
	void recvDocUpdate(String type, String text, int length, int location) throws RemoteException;
	
}
