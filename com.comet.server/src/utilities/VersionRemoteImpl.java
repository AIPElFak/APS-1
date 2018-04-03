package utilities;

import java.util.Date;

import database.dto.DocumentVersion;

public class VersionRemoteImpl implements VersionRemote{
	
	int id;
	String content;
	Date dateTime;
	
	int documentId;
	String documentName;
	
	int userId;
	String userName;
	
	
	
	public VersionRemoteImpl(int id, String content, Date dateTime, int documentId, String documentName, int userId, String userName) {
		this.id = id;
		this.content = content;
		this.dateTime = dateTime;
		this.documentId = documentId;
		this.documentName = documentName;
		this.userId = userId;
		this.userName = userName;
	}


	public VersionRemoteImpl(DocumentVersion v) {
		this.id = v.getId();
		this.content = v.getContent();
		this.dateTime = v.getDateTime();
		this.documentId = v.getDocument().getId();
		this.documentName = v.getDocument().getFilename();
		this.userId = v.getUser().getId();
		this.userName = v.getUser().getUsername();
	}
	
	
	@Override
	public int getId() {
		return this.id;
	}
	
	@Override
	public String getContent() {
		return this.content;
	}
	
	@Override
	public Date getDateTime() {
		return this.dateTime;
	}
	
	@Override
	public int getDocumentId() {
		return this.documentId;
	}
	
	@Override
	public String getDocumentName() {
		return this.documentName;
	}
	
	@Override
	public int getUserId() {
		return this.userId;
	}
	
	@Override
	public String getUserName() {
		return this.userName;
	}
}
