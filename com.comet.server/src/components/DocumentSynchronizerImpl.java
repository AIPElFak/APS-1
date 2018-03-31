package components;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import components.DocumentSynchronizer;
import database.dao.BusinessLogic;
import database.dto.Document;
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
		
		documents = this.populateDocuments(Integer.MAX_VALUE);
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
	public ArrayList<DocumentRemote> getAllAvailableDocuments() throws RemoteException {
		return documents;
	}

	@Override
	public void addClientToDocument(Client cl, int docId) throws RemoteException {
		for(DocumentRemote d : documents) {
			if(d.getId() == docId)
				cl.setWorkingDocument(d);
				d.addClientToThisDocument(cl);
		}
	}
	
	private ArrayList<DocumentRemote> populateDocuments(int limit) throws RemoteException{
		BusinessLogic logic = new BusinessLogic();
		ArrayList<Document> docs = new ArrayList<Document>();
		ArrayList<DocumentRemote> docsRemote = new ArrayList<DocumentRemote>();
		docs = logic.getAvailableDocuments(limit);
		for(Document d : docs) {
			docsRemote.add(new DocumentRemoteImpl(d));
		}
		return docsRemote;
	}

	@Override
	public ArrayList<DocumentRemote> searchDocuments(String criteria) throws RemoteException {
		ArrayList<DocumentRemote> resaults = new ArrayList<DocumentRemote>();
		for(DocumentRemote doc : documents)
			if(doc.getName().toLowerCase().contains(criteria.toLowerCase())
				|| doc.getType().toLowerCase().contains(criteria.toLowerCase()))
				resaults.add(doc);
		return resaults;
	}

	@Override
	public void createDocument(Client cl, DocumentRemote dr) throws RemoteException {
		Document doc = new Document(dr.getType(), dr.getName(), dr.isPasswordProtected(), dr.getPassword());
		BusinessLogic logic = new BusinessLogic();
		logic.createDocument(cl.getUserData().getId(), doc);
		
		dr.addClientToThisDocument(cl);
//		this.documents.add(dr);			//nece da ima id ako se odma doda u listu... jer je id generisan u bazi..
	}

	@Override
	public String openDocument(int documentId) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		return logic.getLastDocumentVersion(documentId).getContent();
	}

}
