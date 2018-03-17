package communication;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import utilities.Document;
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
		facade.getMessageServer().lobbyBroadcast(message, cl);
	}

	@Override
	public void documentBroadcast(Document doc, Client cl, String message) throws RemoteException {
		facade.getMessageServer().documentBroadcast(doc, cl, message);
	}

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		return facade.getAuthenticator().login(cl, username, password);
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		return facade.getAuthenticator().logout(cl);
	}

	@Override
	public boolean signin(Client cl, String username, String password, String email, String imageUrl) throws RemoteException {
		return facade.getAuthenticator().signin( cl, username, password, email, imageUrl);
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		return facade.getAuthenticator().deleteAccount(cl);
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		return facade.getAuthenticator().modifyUserData(cl);
	}

}
