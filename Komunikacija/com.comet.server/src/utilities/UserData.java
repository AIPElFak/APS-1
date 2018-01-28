package utilities;

import java.rmi.*;

public interface UserData extends Remote {
	
	void setUsername(String username) throws RemoteException;
	
	String getUsername() throws RemoteException;
	
	void setPassword(String password) throws RemoteException;
	
	String getPassword() throws RemoteException;
	
	void setPrivilegies(String privilegies) throws RemoteException;
	
	String getPrivilegies() throws RemoteException;
}
