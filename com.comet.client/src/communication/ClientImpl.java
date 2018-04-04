package communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import communication.Client;
import controller.ControllerOnline;
import utilities.DocumentRemote;
import utilities.UserRemote;

public class ClientImpl extends UnicastRemoteObject implements Client {

	private UserRemote userData;
	private DocumentRemote document;
	
	private ControllerOnline controller;
	
	public ClientImpl() throws RemoteException {
		super();
	}
	
	public ClientImpl(ControllerOnline cntrl) throws RemoteException {
		super();
		controller = cntrl;
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void lobbyRecv(Client cl, String message) throws RemoteException {
		controller.recvLobbyMessage(cl, message);
	}

	@Override
	public void documentRecv(Client cl, String message) throws RemoteException {
		controller.recvDocumentMessage(cl, message);
	}

	@Override
	public void setUserData(UserRemote userData) throws RemoteException {
		this.userData = userData;
	}

	@Override
	public UserRemote getUserData() throws RemoteException {
		return userData;
	}
	
	@Override
	public void finalize() {
		controller.removeClient(this);
	}

	@Override
	public void setWorkingDocument(DocumentRemote doc) throws RemoteException {
		this.document = doc;
	}

	@Override
	public void updateCollaboratorsList(List<UserRemote> collaborators) throws RemoteException {
		controller.updateCollaborators(collaborators);
	}

	@Override
	public DocumentRemote getDocumentData() throws RemoteException {
		return document;
	}

	@Override
	public void recvDocUpdate(String type, String text, int length, int location) throws RemoteException {
		controller.recvDocUpdate(type, text, length, location);
	}

	@Override
	public void recvAllDocuments() throws RemoteException {
		controller.displayAllAvailableDocuments();
	}

	@Override
	public void goBackToLobby() throws RemoteException {
		controller.goBackToLobby();
	}

}
