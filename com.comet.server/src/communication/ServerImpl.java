package communication;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import utilities.DocumentRemote;
import utilities.VersionRemote;
import communication.Client;
import communication.Server;
import configuration.RmiConfiguration;

public class ServerImpl extends UnicastRemoteObject implements Server {
	
	ServerFacade facade;

	public ServerImpl() throws RemoteException, MalformedURLException, NotBoundException, JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(RmiConfiguration.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		URL url = getClass().getResource("../configuration/Configuration.xml");
		RmiConfiguration cfg = (RmiConfiguration) 
				jaxbUnmarshaller.unmarshal(new File(url.getPath()));
		facade = new ServerFacadeImpl(cfg.getRmiHost(), cfg.getRmiPort());
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
		facade.getMessageService().lobbyBroadcast(message, cl);
	}

	@Override
	public void documentBroadcast(Client cl, String message) throws RemoteException {
		facade.getMessageService().documentBroadcast(cl, message);
	}

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		return facade.getAuthenticationService().login(cl, username, password);
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		return facade.getAuthenticationService().logout(cl);
	}

	@Override
	public boolean signin(Client cl, String username, String password, String email, byte[] imageBytes) throws RemoteException {
		return facade.getAuthenticationService().signin( cl, username, password, email, imageBytes);
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		return facade.getAuthenticationService().deleteAccount(cl);
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		return facade.getAuthenticationService().modifyUserData(cl);
	}

	@Override
	public boolean resetPassword(String email) throws RemoteException {
		return facade.getAuthenticationService().resetPassword(email);
	}
	
	@Override
	public ArrayList<DocumentRemote> getAllAvailableDocuments() throws RemoteException {
		return facade.getDocumentService().getAllAvailableDocuments();
	}

	@Override
	public void addClientToDocument(Client cl, int docId) throws RemoteException {
		facade.getDocumentService().addClientToDocument(cl, docId);
	}

	@Override
	public ArrayList<DocumentRemote> searchDocuments(String criteria) throws RemoteException {
		return facade.getDocumentService().searchDocuments(criteria);
	}

	@Override
	public boolean createDocument(Client cl, String name, String type, String password) throws RemoteException {
		return facade.getDocumentService().createDocument(cl, name, type, password);
	}

	@Override
	public String openDocument(Client cl, int documentId) throws RemoteException {
		return facade.getDocumentService().openDocument(cl, documentId);
	}

	@Override
	public boolean addDocumentVersion(Client cl, int documentId, String content) throws RemoteException {
		return facade.getDocumentService().addDocumentVersion(cl, documentId, content);
	}

	@Override
	public boolean deleteDocument(Client cl, int documentId) throws RemoteException {
		return facade.getDocumentService().deleteDocument(cl, documentId);
	}

	@Override
	public boolean editUserInformations(Client client, String username, String email, String password) throws RemoteException {
		return facade.getAuthenticationService().editUserInformations(client, username, email, password);
	}

	@Override
	public void sendDocUpdate(Client cl, String type, String text, int length, int location) throws RemoteException {
		facade.getDocumentService().sendDocUpdate(cl, type, text, length, location);
	}

	@Override
	public ArrayList<VersionRemote> getAllDocumentVersions(int documentId) throws RemoteException {
		return facade.getDocumentService().getAllDocumentVersions(documentId);
	}

	@Override
	public String openDocumentVersion(int versionId) throws RemoteException {
		return facade.getDocumentService().openDocumentVersion(versionId);
	}

}
