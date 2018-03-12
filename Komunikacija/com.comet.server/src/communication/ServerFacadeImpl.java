package communication;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import components.Authenticator;
import components.DocumentSynchronizer;
import components.MessageServer;
import utilities.Document;

public class ServerFacadeImpl implements ServerFacade {
	
	private Authenticator auth;
	private DocumentSynchronizer docsynch;
	private MessageServer msgsrv;
	
	public ServerFacadeImpl(String host, String port) throws MalformedURLException, RemoteException, NotBoundException {
		auth = (Authenticator) 
				Naming.lookup("rmi://" + host + ":" + port + "/Authenticator");
		docsynch = (DocumentSynchronizer)
				Naming.lookup("rmi://" + host + ":" + port + "/DocumentSynchronizer");
		msgsrv = (MessageServer)
				Naming.lookup("rmi://" + host + ":" + port + "/MessageServer");
	}
	
	public void addClient(Client cl) throws RemoteException {
		auth.addClient(cl);
		msgsrv.addClient(cl);
		docsynch.addClient(cl);
		logActivity("Client reference: " + cl + " added.");
	}
	
	public void removeClient(Client cl) throws RemoteException {
		auth.removeClient(cl);
		msgsrv.removeClient(cl);
		docsynch.removeClient(cl);
		logActivity("Client reference: " + cl + " removed.");
	}

	public void logActivity(String log) throws RemoteException {
		System.out.println(log);
	}

	@Override
	public void lobbyBroadcast(String message, Client cl) throws RemoteException {
		msgsrv.lobbyBroadcast(message, cl);
	}

	@Override
	public void documentBroadcast(Document doc, Client cl, String message) throws RemoteException {
		msgsrv.lobbyBroadcast(message, cl);
	}

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		return auth.login(cl, username, password);
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		return auth.logout(cl);
	}

	@Override
	public boolean signin(Client cl) throws RemoteException {
		return auth.signin(cl);
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		return auth.deleteAccount(cl);
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		return auth.modifyUserData(cl);
	}

}
