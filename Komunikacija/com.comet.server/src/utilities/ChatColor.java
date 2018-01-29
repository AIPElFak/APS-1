package utilities;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatColor extends Remote {

	int getRed() throws RemoteException;
	
	int getBlue() throws RemoteException;
	
	int getGreen() throws RemoteException;
	
}
