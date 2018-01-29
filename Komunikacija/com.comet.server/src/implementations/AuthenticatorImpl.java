package implementations;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import components.Authenticator;
import utilities.UserData;
import communication.Client;

public class AuthenticatorImpl extends UnicastRemoteObject implements Authenticator {

	private ArrayList<Client> clients;
	private ArrayList<UserData> users;
	
	public AuthenticatorImpl() throws RemoteException {
		super();
		clients = new ArrayList<Client>();
		users = new ArrayList<UserData>();
		
		UserData udMarko = new UserDataImpl("Marko", "Sifra", "Owner");		
		UserData udSentic = new UserDataImpl("Sentic", "Sifra", "Guest");

		users.add(udMarko);
		users.add(udSentic);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		if (!clients.contains(cl)) return false;
		for(UserData ud : users) {
			if(ud.getUsername().equals(username) && ud.getPassword().equals(password)) {
				cl.setUserData(ud);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean signin(Client cl) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addClient(Client cl) throws RemoteException {
		if(clients.contains(cl)) return false;
		clients.add(cl);
		return true;
	}

	@Override
	public boolean removeClient(Client cl) throws RemoteException {
		if(!clients.contains(cl)) return false;
		clients.remove(cl);
		return true;
	}

}
