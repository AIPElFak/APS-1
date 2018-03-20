package utilities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import database.dto.User;
import utilities.UserRemote;
import utilities.UserRemoteImpl;

public class UserRemoteImpl extends UnicastRemoteObject implements UserRemote {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username, password, email, imageUrl;
	
	
	public UserRemoteImpl(String username, String password, String email, String imageUrl,int id) throws RemoteException {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.imageUrl = imageUrl;
	}

	public UserRemoteImpl(User user)  throws RemoteException  {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.imageUrl = user.getImage();
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
