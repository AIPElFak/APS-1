package utilities;

import java.rmi.*;

public interface UserData extends Remote {
	
	String getUsername() throws RemoteException;
	
	String getPassword() throws RemoteException;
	
	String getPrivilegies() throws RemoteException;
	
}
