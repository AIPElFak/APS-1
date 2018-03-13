package aps.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DOCUMENT_VERSION")
public class DocumentVersion {

//attributes
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="DOCUMENT_ID")
	private Document document;
	
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private User user;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="DATE_TIME")
	private Date dateTime;
	
	
//constructors
	public DocumentVersion() {
		
	}
	public DocumentVersion(Document doc,User u, String content,Date dateTime) {
		this.document = doc;
		this.user = u;
		this.content = content;
		this.dateTime = dateTime;
	}
	
	
//getters and setters	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
}
