package aps.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name="JOIN_REQUEST")
public class JoinRequest {

//attributes
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	int Id;
	
	@ManyToOne
	@JoinColumn(name="REQUESTER_ID")
	User requester;
	
	@ManyToOne
	@JoinColumn(name="DOCUMENT_ID")
	Document document;
	
	@Column(name="REQUEST_TIME")
	Date requestTime;
	
//constructors
	public JoinRequest() {
		
	}
	
	public JoinRequest(User requester,Document document) {
		this.requester = requester;
		this.document = document;
		this.requestTime = new Date();
	}
	
//getters and setters
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
}
