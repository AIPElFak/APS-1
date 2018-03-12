package utilities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import utilities.UserData;

public class UserDataImpl extends UnicastRemoteObject implements UserData {

	private String username, password, privilegies;
	
	public UserDataImpl(String username, String password, String privilegies) throws RemoteException {
		super();
		this.username = username;
		this.password = password;
		this.privilegies = privilegies;
	}

	@Override
	public String getUsername() throws RemoteException {
		return username;
	}

	@Override
	public String getPassword() throws RemoteException {
		return password;
	}

	@Override
	public String getPrivilegies() throws RemoteException {
		return privilegies;
	}

}
