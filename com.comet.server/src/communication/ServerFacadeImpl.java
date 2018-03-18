package communication;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import components.Authenticator;
import components.DocumentSynchronizer;
import components.MessageServer;
import utilities.DocumentRemote;

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
	public Authenticator getAuthenticator() throws RemoteException {
		return auth;
	}

	@Override
	public MessageServer getMessageServer() throws RemoteException {
		return msgsrv;
	}

	@Override
	public DocumentSynchronizer getDocumentSynchronizer() throws RemoteException {
		return docsynch;
	}

}
