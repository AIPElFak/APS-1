package controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import communication.Client;
import communication.Server;
import utilities.DocumentRemote;
import utilities.UserRemote;
import utilities.VersionRemote;

public class ControllerOnlineImpl extends ControllerImpl implements ControllerOnline {
	
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
			server.addClient(client);
			boolean result = server.login(client, username, password);
			if(!result) return false;
			return true;
		} 
		catch (RemoteException e) {}
		return false;
	}

	@Override
	public boolean signIn(String username, String password, String email, byte[] imageBytes) {
		try {
			return server.signin(client, username, password, email, imageBytes);
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
	public void sendDocumentMessage(String Message) {
		try {
			server.documentBroadcast(client, Message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		try {
			getView().appendDocumentMessage(cl.getUserData().getUsername(), Message);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public boolean deleteDocument(DocumentRemote doc) {
		try { 
		      return server.deleteDocument(client, doc.getId()); 
		} catch (RemoteException e) {} 
		return false;
	}

	@Override
	public boolean openDocument(DocumentRemote doc, String password) {
		try {
			String text = server.openDocument(client, doc.getId(), password);
			if(text == null)
				return false;
			getView().setDocumentName(doc.getName());
			getView().updateContent(text);
			getModel().setContent(text);
			return true;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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

	@Override
	public void updateCollaborators(List<UserRemote> collabs) {
		getView().updateCollaborators(collabs);
	}

	@Override
	public boolean editUserInformations(String username, String email, String password, byte[] image) {
		try {
			return server.editUserInformations(client, username, email, password, image);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void recvDocUpdate(String type, String text, int length, int location) {
		getView().recvDocUpdate(type, text, length, location);
	}

	@Override
	public void sendDocUpdate(String type, String text, int length, int location) {
		try {
			if(client.getUserData().getPrivilege().toLowerCase().equals("readonly")) {
				return;
			}
			server.sendDocUpdate(client, type, text, length, location);
		} catch (RemoteException e) {}
	}

	@Override
	public ArrayList<VersionRemote> getAllDocumentVersions(){
		try {
			return server.getAllDocumentVersions(client.getDocumentData().getId());
		} catch (RemoteException e) {}
		return null;
	}

	@Override
	public String openDocumentVersion(int id) {
		try {
			return server.openDocumentVersion(id);
		} catch (RemoteException e) {}
		return null;
	}

	@Override
	public boolean resetPassword(String email) {
		try {
			return server.resetPassword(email);
		} catch (RemoteException e) {}
		return false;
	}

	@Override
	public void removeClient() {
		try {
			server.removeClient(client);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean saveDocumentVersion(String content) {
		try {
			if(client.getUserData().getPrivilege().equals("ReadOnly"))
				return false;
			return server.addDocumentVersion(client, client.getDocumentData().getId(), content);
		}catch(RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void setPrivilegies(UserRemote value, String string) {
		try {
			server.setPrivilegies(client, value, string);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removeFromDocument(UserRemote value) {
		try {
			server.removeFromDocument(client, value, client.getDocumentData().getId());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		getView().disposeView();
	}

}
