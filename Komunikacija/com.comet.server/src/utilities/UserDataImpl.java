package utilities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserDataImpl extends UnicastRemoteObject implements UserData{

	private String username, password, privilegies;
	
	public UserDataImpl() throws RemoteException {
		super();
		username = "";
		password = "";
		privilegies = "";
	}

	@Override
	public void setUsername(String username) throws RemoteException {
		this.username = username;
	}

	@Override
	public String getUsername() throws RemoteException {
		return username;
	}

	@Override
	public void setPassword(String password) throws RemoteException {
		this.password = password;
	}

	@Override
	public String getPassword() throws RemoteException {
		return password;
	}

	@Override
	public void setPrivilegies(String privilegies) throws RemoteException {
		this.privilegies = privilegies;
	}

	@Override
	public String getPrivilegies() throws RemoteException {
		return privilegies;
	}

}
