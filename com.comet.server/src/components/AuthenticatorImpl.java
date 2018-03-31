package components;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import components.Authenticator;
import database.dao.BusinessLogic;
import database.dto.User;
import database.test.dto.Info;
import database.test.dto.PasswordReset;
import utilities.UserRemote;
import utilities.UserRemoteImpl;
import communication.Client;

public class AuthenticatorImpl extends UnicastRemoteObject implements Authenticator {

	private ArrayList<Client> clients;
	private ArrayList<UserRemote> users;
	
	public AuthenticatorImpl() throws RemoteException {
		super();
		clients = new ArrayList<Client>();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		User user = logic.login(username, password);
		if(user == null) return false; 
		UserRemoteImpl ur = new UserRemoteImpl(user);
		cl.setUserData(ur);
		return true;
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		if(cl.getUserData() == null) return false;
		cl.setUserData(null);
		return true;
	}

	@Override
	public boolean signin(Client cl, String username, String password, String email, String imageUrl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		User user = new User(username, password, email, imageUrl);
		Info info = logic.register(user);
		if(!info.isSuccessful()) return false;
		UserRemoteImpl ur = new UserRemoteImpl(user);
		cl.setUserData(ur);
		return true;
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		UserRemoteImpl ur = (UserRemoteImpl) cl.getUserData();
		return logic.deleteUserById(ur.getId());
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		UserRemoteImpl ur = (UserRemoteImpl)cl.getUserData();
		User user = new User(ur.getUsername(), ur.getPassword(), ur.getEmail(), ur.getImage());
		user.setId(ur.getId());
		return logic.updateUser(user);
	}

	@Override
	public boolean resetPassword(Client cl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		UserRemoteImpl ur = (UserRemoteImpl)cl.getUserData();
		PasswordReset reset = new PasswordReset(ur.getEmail(),ur.getUsername());
		
		User user = new User(ur.getUsername(), reset.getNewPassword(), ur.getEmail(), ur.getImage());
		user.setId(ur.getId());
		
		if(!logic.updateUser(user)) return false;
		reset.Send();
		return true;
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
