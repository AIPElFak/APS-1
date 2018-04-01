package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;

import communication.Client;
import communication.Server;
import utilities.DocumentRemote;
import utilities.UserRemote;

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
			boolean result = server.login(client, username, password);
			if(!result) return false;
			server.addClient(client);
			return true;
		} 
		catch (RemoteException e) {}
		return false;
	}

	@Override
	public boolean signIn(String username, String password, String email) {
		try {
			return server.signin(client, username, password, email, "http://no-image");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
	}

	@Override
	public boolean logOut(Client cl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createDocument(String name, String type, String password) {
		try {
			return server.createDocument(client, name, type, password);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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

	@Override
	public void addClientToDocument(int docId) {
		try {
			server.addClientToDocument(client, docId);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void displayAllAvailableDocuments() {
		try {
			ArrayList<DocumentRemote> docLst = server.getAllAvailableDocuments();
			getView().showAvailableDocument(docLst);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void searchDocuments(String criteria) {
		try {
			ArrayList<DocumentRemote> docs = server.searchDocuments(criteria);
			getView().showAvailableDocument(docs);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public UserRemote getUserData() {
		try {
			return client.getUserData();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		return null;
	}

	@Override
	public boolean modifyUserData(Client cl) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount() {
		try {
			return server.deleteAccount(client);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
