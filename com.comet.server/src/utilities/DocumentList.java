package utilities;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DocumentList extends Remote {

	List<DocumentRemote> getAllAvailableDocument() throws RemoteException;
	
}
