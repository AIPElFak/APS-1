package communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import view.LobbyFrame;
import view.LoginFrame;
import communication.Client;
import communication.Server;
import controller.ControllerOnline;
import utilities.Document;
import utilities.UserData;

public class ClientImpl extends UnicastRemoteObject implements Client {

	private UserData userData;
	
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
	public void documentRecv(Document doc, Client cl, String message) throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public void directRecv(Client cl, String message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUserData(UserData userData) throws RemoteException {
		this.userData = userData;
	}

	@Override
	public UserData getUserData() throws RemoteException {
		return userData;
	}
	
	@Override
	public void finalize() {
		controller.removeClient(this);
	}

}
