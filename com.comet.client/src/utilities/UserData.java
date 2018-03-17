package utilities;

import java.rmi.*;

public interface UserData extends Remote {
	
	int getId() throws RemoteException;

	String getUsername() throws RemoteException;
	
	String getPassword() throws RemoteException;
	
	String getEmail() throws RemoteException;
	
	String getImage() throws RemoteException;

}
