package communication;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import application.LobbyFrame;
import application.LoginFrame;
import communication.Client;
import communication.Server;
import utilities.Document;
import utilities.UserData;

public class ClientImpl extends UnicastRemoteObject implements Client {

	private Server server;
	private LoginFrame lg;
	private LobbyFrame lb;
	private UserData userData;
	
	public ClientImpl() throws RemoteException {
		super();
		this.lg = new LoginFrame();
		this.lb = new LobbyFrame();
		lg.addClient(this);
		lb.addClient(this);
		lg.setVisible(true);
	}

	public boolean addServer(Server serv) throws RemoteException {
		if(server != null)return false;
		server = serv;
		serv.addClient(this);
		lg.addServer(serv);
		lb.addServer(serv);
		return true;
	}
	
	public boolean removeServer(Server serv) throws RemoteException {
		if(server == null) return false;
		server = null;
		serv.removeClient(this);
		lg.addServer(null);
		lb.addServer(null);
		return false;
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void lobbyRecv(Client cl, String message) throws RemoteException {
		lb.appendMessage(cl.getUserData().getUsername(), message);
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
	public void showLobby() throws RemoteException {
		lb.setVisible(true);
	}

	@Override
	public void hideLobby() throws RemoteException {
		lb.setVisible(false);
	}

	@Override
	public void showLogin() throws RemoteException {
		lg.setVisible(true);
	}

	@Override
	public void hideLogin() throws RemoteException {
		lg.setVisible(false);
	}

}
