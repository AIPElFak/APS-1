package components;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import components.MessageServer;
import communication.Client;
import utilities.DocumentRemote;

public class MessageServerImpl extends UnicastRemoteObject implements MessageServer {

	private ArrayList<Client> clients;
	
	public MessageServerImpl() throws RemoteException {
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
				/* ignoring unreachable endpoints */
			}
		}
	}

	public void documentBroadcast(DocumentRemote doc, Client cl, String message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public boolean addClient(Client cl) throws RemoteException {
		if(clients.contains(cl)) return false;
		clients.add(cl);
		return true;
	}

	public boolean removeClient(Client cl) throws RemoteException {
		if(!clients.contains(cl)) return false;
		clients.remove(cl);
		return true;
	}

	

}
