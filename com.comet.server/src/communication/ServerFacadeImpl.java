package communication;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import components.AuthenticationService;
import components.DocumentService;
import components.MessageService;
import utilities.DocumentRemote;

public class ServerFacadeImpl implements ServerFacade {
	
	private AuthenticationService auth;
	private DocumentService docsynch;
	private MessageService msgsrv;
	
	public ServerFacadeImpl(String host, String port) throws MalformedURLException, RemoteException, NotBoundException {
		auth = (AuthenticationService) 
				Naming.lookup("rmi://" + host + ":" + port + "/Authenticator");
		docsynch = (DocumentService)
				Naming.lookup("rmi://" + host + ":" + port + "/DocumentSynchronizer");
		msgsrv = (MessageService)
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
	public AuthenticationService getAuthenticationService() throws RemoteException {
		return auth;
	}

	@Override
	public MessageService getMessageService() throws RemoteException {
		return msgsrv;
	}

	@Override
	public DocumentService getDocumentService() throws RemoteException {
		return docsynch;
	}

}
