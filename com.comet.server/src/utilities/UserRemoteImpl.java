package utilities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import database.dto.User;
import utilities.UserRemote;
import utilities.UserRemoteImpl;

public class UserRemoteImpl extends UnicastRemoteObject implements UserRemote {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username, password, email;
	private byte[] imageBytes;
	

	public UserRemoteImpl(User user)  throws RemoteException  {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
	}

	public UserRemoteImpl(int id, String username, String password, String email, byte[] imageBytes) throws RemoteException {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.imageBytes = imageBytes;
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
	public byte[] getImageBytes() throws RemoteException {
		return this.imageBytes;
	}

	@Override
	public void setUsername(String username) throws RemoteException {
		this.username = username;
	}

	@Override
	public void setPassword(String password) throws RemoteException {
		this.password = password;
	}

	@Override
	public void setEmail(String email) throws RemoteException {
		this.email = email;
	}

	@Override
	public void setImage(byte[] image) throws RemoteException {
		this.imageBytes = image;
	}

}
