package utilities;

import java.rmi.RemoteException;


import java.rmi.Remote;

public interface UserRemote extends Remote {
	
	int getId() throws RemoteException;

	String getUsername() throws RemoteException;
	
	void setUsername(String username) throws RemoteException;
	
	String getPassword() throws RemoteException;
	
	void setPassword(String password) throws RemoteException; 
	
	String getEmail() throws RemoteException;
	
	void setEmail(String email) throws RemoteException;
	
	byte[] getImageBytes() throws RemoteException;
	
	void setImage(byte[] image) throws RemoteException;
	
	void setPrivilege(String priv) throws RemoteException;
	
	String getPrivilege() throws RemoteException;

}