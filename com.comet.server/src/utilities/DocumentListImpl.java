package utilities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DocumentListImpl extends UnicastRemoteObject implements DocumentList {

	private List<DocumentRemote> documents;
	
	public DocumentListImpl(List<DocumentRemote> docs) throws RemoteException {
		super();
		this.documents = docs;
	}
	
	public List<DocumentRemote> getAllAvailableDocument() throws RemoteException {
		return documents;
	}

	
	
}
