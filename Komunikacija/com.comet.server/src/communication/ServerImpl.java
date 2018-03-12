package communication;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import utilities.Document;
import communication.Client;
import communication.Server;

public class ServerImpl extends UnicastRemoteObject implements Server {
	
	ServerFacade facade;

	public ServerImpl() throws RemoteException, MalformedURLException, NotBoundException {
		facade = new ServerFacadeImpl("127.0.0.1", "1099");
	}

	private static final long serialVersionUID = 1L;
	
	public void addClient(Client cl) throws RemoteException {
		facade.addClient(cl);
	}
	
	public void removeClient(Client cl) throws RemoteException {
		facade.removeClient(cl);
	}

	public void logActivity(String log) throws RemoteException {
		System.out.println(log);
	}

	@Override
	public void lobbyBroadcast(String message, Client cl) throws RemoteException {
		facade.lobbyBroadcast(message, cl);
	}

	@Override
	public void documentBroadcast(Document doc, Client cl, String message) throws RemoteException {
		facade.documentBroadcast(doc, cl, message);
	}

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		return facade.login(cl, username, password);
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		return facade.logout(cl);
	}

	@Override
	public boolean signin(Client cl) throws RemoteException {
		return facade.signin(cl);
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		return facade.deleteAccount(cl);
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		return facade.modifyUserData(cl);
	}

}
