package implementations;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import components.Authenticator;
import implementations.AuthenticatorImpl;
import components.DocumentSynchronizer;
import implementations.DocumentSynchronizerImpl;
import components.MessageServer;
import implementations.MessageServerImpl;
import utilities.Document;
import communication.Client;
import communication.Server;

public class ServerImpl extends UnicastRemoteObject implements Server {
	
	private Authenticator auth;
	private DocumentSynchronizer docsynch;
	private MessageServer msgsrv;

	public ServerImpl() throws RemoteException {
		super();
		auth = new AuthenticatorImpl();
		docsynch = new DocumentSynchronizerImpl();
		msgsrv = new MessageServerImpl();
	}

	private static final long serialVersionUID = 1L;
	
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
	public void directMessage(Client cl, String message) throws RemoteException {
		msgsrv.directMessage(cl, message);
	}

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		if(auth.login(cl, username, password)) return true;
		return false;
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		if(auth.logout(cl)) return true;
		return false;
	}

	@Override
	public boolean signin(Client cl) throws RemoteException {
		if(auth.signin(cl)) return true;
		return false;
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		if(auth.deleteAccount(cl)) return true;
		return false;
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		if(auth.modifyUserData(cl)) return true;
		return false;
	}

}
