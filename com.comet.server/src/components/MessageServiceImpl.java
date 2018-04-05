package components;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import communication.Client;
import utilities.DocumentRemote;

public class MessageServiceImpl extends UnicastRemoteObject implements MessageService {

	private ArrayList<Client> clients;
	
	public MessageServiceImpl() throws RemoteException {
		super();
		clients = new ArrayList<Client>();
	}

	private static final long serialVersionUID = 1L;

	public void lobbyBroadcast(String message, Client cl) {
		for(Client c : clients) {
			try {
				if(!c.getUserData().getUsername().equals(cl.getUserData().getUsername())) {
					c.lobbyRecv(cl, message);
				}
			}
			catch(RemoteException e) {
				clients.remove(c);
			}
		}
	}

	public void documentBroadcast(Client cl, String message) throws RemoteException {
		try {
			DocumentRemote doc = cl.getDocumentData();
			if(doc == null) return;
			for(Client c : clients) {
				try {
					if(c.getUserData().getId() != cl.getUserData().getId()
							&& c.getDocumentData().getId() == 
							cl.getDocumentData().getId()) {
						c.documentRecv(cl, message);
					}
				}catch(RemoteException e) {
					clients.remove(c);
				}
			}
		}catch(RemoteException e) {}
	}

	public boolean addClient(Client cl) throws RemoteException {
		if(clients.contains(cl)) return false;
		clients.add(cl);
		return true;
	}

	public boolean removeClient(Client cl) throws RemoteException {
		for(Client c : clients) {
			try {
				if(c.getUserData().getId() == cl.getUserData().getId()) {
					clients.remove(c);
					return true;
				}
			}catch(RemoteException e) {
				clients.remove(c);
			}
		}
		return false;
	}

	

}
