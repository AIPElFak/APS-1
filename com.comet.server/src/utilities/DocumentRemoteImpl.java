package utilities;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import communication.Client;
import database.dao.BusinessLogic;
import database.dto.Document;
import database.dto.DocumentVersion;

public class DocumentRemoteImpl extends UnicastRemoteObject implements DocumentRemote {

	private static final long serialVersionUID = 1L;

	int id;
	String name, type, privilege, password, content;
	boolean passwordProtected;
	List<Client> collaborators;
	BusinessLogic logic;
	
	public DocumentRemoteImpl() throws RemoteException {
		super();
		collaborators = new ArrayList<Client>();
	}

	public DocumentRemoteImpl(int id,String name,String type,String privilege,boolean passwordProtected)  throws RemoteException{
		this.id = id;
		this.name = name;
		this.type = type;
		this.passwordProtected = passwordProtected;
		this.privilege = privilege;
		collaborators = new ArrayList<Client>();
		
		logic = new BusinessLogic();
		DocumentVersion dv = logic.getLastDocumentVersion(id);
		this.content = dv == null ? "" : dv.getContent();
	}
	
	public DocumentRemoteImpl(Document doc)  throws RemoteException{
		this.id = doc.getId();
		this.name = doc.getFilename();
		this.type = doc.getProgramLaunguage();
		this.passwordProtected = doc.isPassword_protected();
		this.privilege = "";
		collaborators = new ArrayList<Client>();
		
		logic = new BusinessLogic();
		DocumentVersion dv = logic.getLastDocumentVersion(id);
		this.content = dv == null ? "" : dv.getContent();
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int getId() throws RemoteException {
		return this.id;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getName() throws RemoteException {
		return this.name;
	}

	
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String getType() throws RemoteException {
		return this.type;
	}

	
	public void setPasswordProtected(boolean passwordProtected) {
		this.passwordProtected = passwordProtected;
	}
	@Override
	public boolean isPasswordProtected() throws RemoteException {
		return this.passwordProtected;
	}

	
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	@Override
	public String getPrivilege() throws RemoteException {
		return this.privilege;
	}
	
	@Override
	public String toString() {
		return name; 
	}

	@Override
	public void addClientToThisDocument(Client cl) throws RemoteException {
		collaborators.add(cl);
	}

	@Override
	public String getPassword() throws RemoteException {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void removeCollaborator(Client cl) throws RemoteException {
		for(Client c : collaborators) {
			if(c.getUserData().getId() == cl.getUserData().getId())
				collaborators.remove(c);
		}
	}

	@Override
	public List<Client> getCollaborators() throws RemoteException {
		return collaborators;
	}

	@Override
	public String getCurrentContent() throws RemoteException {
		return content;
	}

	@Override
	public void setCurrentContent(String content) throws RemoteException {
		this.content = content;
	}

}
