package utilities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import database.dto.DocumentVersion;

public class VersionRemoteImpl extends UnicastRemoteObject implements VersionRemote{
	
	int id;
	String content;
	Date dateTime;
	
	int documentId;
	String documentName;
	
	int userId;
	String userName;
	
	
	
	public VersionRemoteImpl(int id, String content, Date dateTime, int documentId, String documentName, int userId, String userName) throws RemoteException {
		super();
		this.id = id;
		this.content = content;
		this.dateTime = dateTime;
		this.documentId = documentId;
		this.documentName = documentName;
		this.userId = userId;
		this.userName = userName;
	}


	public VersionRemoteImpl(DocumentVersion v) throws RemoteException {
		super();
		this.id = v.getId();
		this.content = v.getContent();
		this.dateTime = v.getDateTime();
		this.documentId = v.getDocument().getId();
		this.documentName = v.getDocument().getFilename();
		this.userId = v.getUser().getId();
		this.userName = v.getUser().getUsername();
	}
	
	
	@Override
	public int getId() throws RemoteException {
		return this.id;
	}
	
	@Override
	public String getContent() throws RemoteException {
		return this.content;
	}
	
	@Override
	public Date getDateTime() throws RemoteException {
		return this.dateTime;
	}
	
	@Override
	public int getDocumentId() throws RemoteException {
		return this.documentId;
	}
	
	@Override
	public String getDocumentName() throws RemoteException {
		return this.documentName;
	}
	
	@Override
	public int getUserId() throws RemoteException {
		return this.userId;
	}
	
	@Override
	public String getUserName() throws RemoteException {
		return this.userName;
	}
}
