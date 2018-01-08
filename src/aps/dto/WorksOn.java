package aps.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="WORKS_ON")
public class WorksOn {

//attributes
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE} )   				//TODO: obavezno razmotriti!!!
	@JoinColumn(name="DOCUMENT_ID")
	private Document document;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})		//kad se cuva WorksOn cuvaj User i Document	
	@JoinColumn(name = "USER_ID")										//kad se brise WorksOn, User i Document ostaju
	private User user;
	
	@Column(name="PRIVILEGE")
	private String privilege;
	

//constructors
	public WorksOn() {
		
	}
	public WorksOn(Document doc,User u,String privilege) {
		this.document = doc;
		this.user = u;
		this.privilege = privilege;
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
	public String getPrivilege() {
		return privilege;
	}
	public void setPrivilege(String privilege) {
		this.privilege = privilege;
	}
	
	

}

