package components;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


import database.dao.BusinessLogic;
import database.dto.Document;
import database.dto.DocumentVersion;
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
		for(Client c : clients) {
			try {
				if(c.getUserData().getId() == cl.getUserData().getId()) {
					clients.remove(c);
					return true;
				}
			}catch(RemoteException e) {
				clients.remove(c);
			}
		}
		return false;
	}

	@Override
	public ArrayList<DocumentRemote> getAllAvailableDocuments() throws RemoteException {
		return documents;
	}

	@Override
	public void addClientToDocument(Client cl, int docId) throws RemoteException {
		for(DocumentRemote d : documents) {
			try {
				if(d.getId() == docId)
					cl.setWorkingDocument(d);
					d.addClientToThisDocument(cl);
			}catch(RemoteException e) {
				documents.remove(d);
			}
		}
	}
	
	private ArrayList<DocumentRemote> populateDocuments(int limit) throws RemoteException{
		BusinessLogic logic = new BusinessLogic();
		ArrayList<Document> docs = new ArrayList<Document>();
		ArrayList<DocumentRemote> docsRemote = new ArrayList<DocumentRemote>();
		docs = logic.getAvailableDocuments(limit);
		for(Document d : docs) {
			try {
				docsRemote.add(new DocumentRemoteImpl(d));
			}catch(RemoteException e) {
				docs.remove(d);
			}
		}
		return docsRemote;
	}

	@Override
	public ArrayList<DocumentRemote> searchDocuments(String criteria) throws RemoteException {
		ArrayList<DocumentRemote> resaults = new ArrayList<DocumentRemote>();
		for(DocumentRemote doc : documents)
			try {
				if(doc.getName().toLowerCase().contains(criteria.toLowerCase())
						|| doc.getType().toLowerCase().contains(criteria.toLowerCase()))
						resaults.add(doc);
			}catch(RemoteException e) {
				documents.remove(doc);
			}
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
		/*for(Client c : clients)
			try {
				c.recvAllDocuments();
			}catch(RemoteException e) {
				clients.remove(c);
			}*/
		return true;
	}

	@Override
	public String openDocument(Client cl, int documentId) throws RemoteException {
		DocumentRemote target = null;
		BusinessLogic logic = new BusinessLogic();
		for(DocumentRemote docRem : documents){
			try {
				if(docRem.getId() == documentId)
					target = docRem;
			}catch(RemoteException e) {
				documents.remove(docRem);
			}
		}
		
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
			catch(RemoteException e) {
				target.getCollaborators().remove(c);
			}
		}
		for(Client c : target.getCollaborators()) {
			try {
				c.updateCollaboratorsList(users);
			}
			catch(RemoteException e) {
				target.getCollaborators().remove(c);
			}
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
			try {
				if(d.getId() == documentId) {
					documents.remove(d);
					break;
				}
			}catch(RemoteException e) {
				documents.remove(d);
			}
		}
		for(Client c : clients) {
			try {
				c.recvAllDocuments();
			}catch(RemoteException e) {
				clients.remove(c);
			}
		}
		return result;
	}

	@Override
	public void sendDocUpdate(Client cl, String type, String text, int length, int location) throws RemoteException {
		DocumentRemote docRemote = null;
		for(DocumentRemote d : documents)
			try {
				if(d.getId() == cl.getDocumentData().getId()) {
					docRemote = d;
					break;
				}
			}catch(RemoteException e) {
				documents.remove(d);
			}
		
		for(Client c : docRemote.getCollaborators()) {
			try {
				if(!c.getUserData().getUsername().equals(cl.getUserData().getUsername())) {
					c.recvDocUpdate(type, text, length, location);
				}
			}
			catch(RemoteException e) {
				docRemote.getCollaborators().remove(c);
			}
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
				vers.remove(vr);
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

	@Override
	public void setPrivileges(Client cl, int userId, int documentId, String privilege) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		logic.changePrivilege(userId,documentId,privilege);
	}

}
