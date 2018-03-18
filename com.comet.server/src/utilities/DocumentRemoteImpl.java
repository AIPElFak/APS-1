package utilities;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

import database.dto.Document;

public class DocumentRemoteImpl extends UnicastRemoteObject implements DocumentRemote {

	int id;
	String name,type,privilege;
	boolean passwordProtected;
	
	public DocumentRemoteImpl() throws RemoteException {
		super();
	}

	public DocumentRemoteImpl(int id,String name,String type,String privilege,boolean passwordProtected)  throws RemoteException{
		this.id = id;
		this.name = name;
		this.type = type;
		this.passwordProtected = passwordProtected;
		this.privilege = privilege;
	}
	
	public DocumentRemoteImpl(Document doc)  throws RemoteException{
		this.id = doc.getId();
		this.name = doc.getFilename();
		this.type = doc.getProgramLaunguage();
		this.passwordProtected = doc.isPassword_protected();
		this.privilege = "";
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

}
