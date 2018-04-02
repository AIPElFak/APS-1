package utilities;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.rmi.Remote;

public interface UserRemote extends Remote {
	
	int getId() throws RemoteException;

	String getUsername() throws RemoteException;
	
	String getPassword() throws RemoteException;
	
	String getEmail() throws RemoteException;
	
	byte[] getImageBytes() throws RemoteException;

	ArrayList<? extends DocumentRemote> getCurrentDocuments() throws RemoteException;
	
	ArrayList<? extends DocumentRemote> getAvailableDocuments() throws RemoteException;
	
}