package components;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


import database.dao.BusinessLogic;
import database.dto.Document;
import database.dto.DocumentVersion;
import database.dto.WorksOn;
import utilities.DocumentRemote;
import utilities.DocumentRemoteImpl;
import utilities.UserRemote;
import utilities.VersionRemote;
import utilities.VersionRemoteImpl;
import communication.Client;

public class DocumentServiceImpl extends UnicastRemoteObject implements DocumentService {

	private ArrayList<Client> clients;
	private ArrayList<DocumentRemote> documents;
	
	public DocumentServiceImpl() throws RemoteException {
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
	public boolean createDocument(Client cl, String name, String type, String password) throws RemoteException {
		if(name.equals("") || type.equals("")) return false;
		Document doc = new Document(type, name, !password.equals(""), password);
		BusinessLogic logic = new BusinessLogic();
		if(!logic.createDocument(cl.getUserData().getId(), doc)) return false;
		DocumentRemote docRemote = new DocumentRemoteImpl(doc);
		documents.add(docRemote);
		System.out.println(doc.getId());
		for(Client c : clients)
			c.recvAllDocuments();
		return true;
	}

	@Override
	public String openDocument(Client cl, int documentId) throws RemoteException {
		DocumentRemote target = null;
		BusinessLogic logic = new BusinessLogic();
		for(DocumentRemote docRem : documents)
			if(docRem.getId() == documentId)
				target = docRem;
		
		target.addClientToThisDocument(cl);
		cl.setWorkingDocument(target);
		String priv = logic.startWorkingOnDocument(cl.getUserData().getId(), documentId);
		UserRemote ur = cl.getUserData();
		ur.setPrivilege(priv);
		cl.setUserData(ur);
		
		List<UserRemote> users = new ArrayList<UserRemote>();
		for(Client c : target.getCollaborators()) {
			try {
				users.add(c.getUserData());
			}
			catch(RemoteException e) {}
		}
		for(Client c : target.getCollaborators()) {
			try {
				c.updateCollaboratorsList(users);
			}
			catch(RemoteException e) {}
		}
		return target.getCurrentContent();
	}

	@Override
	public boolean addDocumentVersion(Client cl, int documentId, String content) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		return logic.addDocumentVersion(documentId, cl.getUserData().getId(), content);
	}

	@Override
	public boolean deleteDocument(Client cl, int documentId) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		boolean result = logic.deleteDocument(documentId, cl.getUserData().getId());
		if(!result) return false;
		for(DocumentRemote d : documents) {
			if(d.getId() == documentId) {
				documents.remove(d);
				break;
			}
		}
		for(Client c : clients) {
			c.recvAllDocuments();
		}
		return result;
	}

	@Override
	public void sendDocUpdate(Client cl, String type, String text, int length, int location) throws RemoteException {
		DocumentRemote docRemote = null;
		int i = 0;
		for(DocumentRemote d : documents)
			if(d.getId() == cl.getDocumentData().getId()) {
				docRemote = d;
				i++;
				break;
			}
		StringBuffer sb = null;
		sb = new StringBuffer(docRemote.getCurrentContent());
		if(type.toLowerCase().equals("insert")) 
			sb.insert(location, text);
		else if(type.toLowerCase().equals("remove"))
			sb.delete(location, location+length);
		else  if(type.toLowerCase().equals("pull"))
			sb = new StringBuffer(text);
			
		documents.get(i).setCurrentContent(sb.toString());
		
		for(Client c : docRemote.getCollaborators()) {
			try {
				if(c.getUserData().getId() != cl.getUserData().getId()) {
					c.recvDocUpdate(type, text, length, location);
				}
			}catch(RemoteException e) {}
		}
	}

	@Override
	public ArrayList<VersionRemote> getAllDocumentVersions(int documentId) {
		BusinessLogic logic = new BusinessLogic();
		ArrayList<VersionRemote> result = new ArrayList<VersionRemote>();
		ArrayList<DocumentVersion> vers =  logic.getAllDocumentVersions(documentId);
		for(DocumentVersion v : vers) {
			VersionRemote vr = null;
			try {
				vr = new VersionRemoteImpl(v);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result.add(vr);
		}
		return result;
	}

	@Override
	public String openDocumentVersion(int versionId) {
		BusinessLogic logic = new BusinessLogic();
		return logic.openDocumentVersion(versionId);
	}

}
