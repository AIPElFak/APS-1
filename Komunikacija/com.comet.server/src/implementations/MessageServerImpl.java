package implementations;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import components.MessageServer;
import communication.Client;
import utilities.Document;

public class MessageServerImpl extends UnicastRemoteObject implements MessageServer {

	private ArrayList<Client> clients;
	
	public MessageServerImpl() throws RemoteException {
		super();
		clients = new ArrayList<Client>();
	}

	private static final long serialVersionUID = 1L;

	public void lobbyBroadcast(String message, Client cl) throws RemoteException {
		for(Client c : clients) {
			if(!c.getUserData().getUsername().equals(cl.getUserData().getUsername())) {
				c.lobbyRecv(cl, message);
			}
		}
	}

	public void documentBroadcast(Document doc, Client cl, String message) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void directMessage(Client cl, String message) throws RemoteException {
		
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
