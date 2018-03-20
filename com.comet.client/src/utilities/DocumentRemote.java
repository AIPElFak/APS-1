package utilities;

import java.rmi.*;

public interface DocumentRemote extends Remote {

	int getId() throws RemoteException;
	
	String getName() throws RemoteException;
	
	String getType() throws RemoteException;
	
	boolean isPasswordProtected() throws RemoteException;
	
	String getPrivilege() throws RemoteException;
	
}
