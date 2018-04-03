package utilities;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface VersionRemote extends Remote {

	public int getId() throws RemoteException;
	
	public String getContent() throws RemoteException;
	
	public Date getDateTime() throws RemoteException;
	
	public int getDocumentId() throws RemoteException;
	
	public String getDocumentName() throws RemoteException;
	
	public int getUserId()throws RemoteException;
	
	public String getUserName() throws RemoteException;
	
}
