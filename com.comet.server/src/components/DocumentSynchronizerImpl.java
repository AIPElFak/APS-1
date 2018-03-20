package components;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import components.DocumentSynchronizer;
import utilities.DocumentList;
import utilities.DocumentListImpl;
import utilities.DocumentRemote;
import utilities.DocumentRemoteImpl;
import communication.Client;

public class DocumentSynchronizerImpl extends UnicastRemoteObject implements DocumentSynchronizer {

	private ArrayList<Client> clients;
	private ArrayList<DocumentRemote> documents;
	
	public DocumentSynchronizerImpl() throws RemoteException {
		super();
		clients = new ArrayList<Client>();
		documents = new ArrayList<DocumentRemote>();
		
		DocumentRemote doc1 = new DocumentRemoteImpl(2, "Cica", "Python", "Sentic2", false);
		DocumentRemote doc2 = new DocumentRemoteImpl(1, "Sentic", "C#", "Sentic", true);
		DocumentRemote doc3 = new DocumentRemoteImpl(2, "Martin", "C++", "Sentic2", false);
		DocumentRemote doc4 = new DocumentRemoteImpl(1, "Samo jako", "C", "Sentic", true);
		DocumentRemote doc5 = new DocumentRemoteImpl(2, "Srbija", "JavaScrpit", "Sentic2", false);
		
		documents.add(doc1);
		documents.add(doc2);
		documents.add(doc3);
		documents.add(doc4);
		documents.add(doc5);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean addClient(Client cl) throws RemoteException {
		if(clients.contains(cl)) return false;
		clients.add(cl);
		return true;
	}

	@Override
	public boolean removeClient(Client cl) throws RemoteException {
		if(!clients.contains(cl)) return false;
		clients.remove(cl);
		return true;
	}

	@Override
	public DocumentList getAllAvailableDocuments() throws RemoteException {
		return new DocumentListImpl(documents);
	}

	@Override
	public void addClientToDocument(Client cl, int docId) throws RemoteException {
		for(DocumentRemote d : documents) {
			if(d.getId() == docId)
				cl.setWorkingDocument(d);
				d.addClientToThisDocument(cl);
		}
	}

}
