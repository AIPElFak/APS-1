package controller;

import java.rmi.RemoteException;

import communication.Client;
import communication.Server;
import utilities.DocumentRemote;

public class ControllerOnlineImpl extends ControllerImpl implements ControllerOnline {
	
	private DocumentRemote document;
	
	private Server server;
	private Client client;
	
	public ControllerOnlineImpl(Server server) {
		this.server = server;
	}
	
	public ControllerOnlineImpl() {
		setModel(null);
		setView(null);
		server = null;
	}
	
	@Override
	public boolean logIn(String username, String password) {
		try {
			return server.login(client, username, password);
		} 
		catch (RemoteException e) {}
		return false;
	}

	@Override
	public void signIn(String username, String password, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendLobbyMessage(String Message) {
		try {
			server.lobbyBroadcast(Message, client);
		} catch (RemoteException e) {}
	}

	@Override
	public void sendDocumentMessage(Client cl, String Message) {
		
	}

	@Override
	public void recvLobbyMessage(Client cl, String Message) {
		try {
			getView().appendLobyMessage(cl.getUserData().getUsername(), Message);
		} 
		catch (RemoteException e) {}
	}

	@Override
	public void recvDocumentMessage(Client cl, String Message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setClient(Client cl) {
		client = cl;
		try {
			server.addClient(cl);
		} 
		catch (RemoteException e) {}
		catch (NullPointerException e) {
			System.exit(0);
		}
	}

	@Override
	public boolean logOut(Client cl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createDocument(Client cl, String name, String lang, String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDocument(Client cl, DocumentRemote doc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openDocument(Client cl, DocumentRemote doc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeClient(Client cl) {
		try {
			server.removeClient(cl);
		}
		catch (RemoteException e) {}
	}

	@Override
	public void saveDocument(String path, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openDocument(String path, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNewDocument() {
		getView().setDocumentName("New document");
		getView().updateContent("");
		getModel().setContent("");
		getModel().setExtension("");
		getModel().setLanguage("");
		getModel().setName("New document");
	}

}
