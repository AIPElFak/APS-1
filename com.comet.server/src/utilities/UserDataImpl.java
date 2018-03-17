package utilities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import utilities.UserData;

public class UserDataImpl extends UnicastRemoteObject implements UserData {

	private int id;
	private String username, password, email, imageUrl;
	
	public UserDataImpl(String username, String password, String email, String imageUrl,int id) throws RemoteException {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.imageUrl = imageUrl;
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
	public int getId() throws RemoteException {
		return id;
	}

	@Override
	public String getEmail() throws RemoteException {
		return email;
	}

	@Override
	public String getImage() throws RemoteException {
		return imageUrl;
	}


}
