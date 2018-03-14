package components;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import components.Authenticator;
import database.dao.BusinessLogic;
import database.dto.User;
import database.test.dto.Info;
import utilities.UserData;
import utilities.UserDataImpl;
import communication.Client;

public class AuthenticatorImpl extends UnicastRemoteObject implements Authenticator {

	private ArrayList<Client> clients;
	private ArrayList<UserData> users;
	
	public AuthenticatorImpl() throws RemoteException {
		super();
		clients = new ArrayList<Client>();
//		users = new ArrayList<UserData>();
//		
//		UserData udMarko = new UserDataImpl("Marko", "Sifra", "Owner");		
//		UserData udSentic = new UserDataImpl("Sentic", "Sifra", "Guest");
//
//		users.add(udMarko);
//		users.add(udSentic);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		User user = logic.login(username, password);
		if(user!=null) {
			cl.setUserData(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		if(cl.getUserData()!=null) {
			cl.setUserData(null);
			return true;
		}
		return false;
	}

	@Override
	public boolean signin(Client cl, String username, String password, String email, String imageUrl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		User user = new User(username, password, email, imageUrl);
		Info info = logic.register(user);
		if(info.isSuccessful()) {
			cl.setUserData(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		User user = (User)cl.getUserData();
		return logic.deleteUser(user);
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		User user = (User)cl.getUserData();
		return logic.updateUser(user);
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
