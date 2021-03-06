package aps.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="DOCUMENT")
public class Document {

//attributes
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="PROGRAM_LANGUAGE")
	private String programLaunguage;
	
	@Column(name="NAME")
	private String filename;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="document")
	private Collection<DocumentVersion> versions;
	
	@OneToMany(mappedBy="document", cascade = CascadeType.ALL)
	private Collection<WorksOn> users;
	
	@OneToMany(mappedBy="document", cascade = CascadeType.ALL)
	private Collection<JoinRequest> requests;
	
//constructors
	public Document() {
		this.versions = new ArrayList<DocumentVersion>();
		this.users = new ArrayList<WorksOn>();
		this.requests = new ArrayList<JoinRequest>();
	}
	
	public Document(String programLanguage,String filename) {
		this.versions = new ArrayList<DocumentVersion>();
		this.users = new ArrayList<WorksOn>();
		this.requests = new ArrayList<JoinRequest>();
		this.programLaunguage = programLanguage;
		this.filename = filename;
	}

//getters and setters
	public Collection<DocumentVersion> getVersions() {
		return versions;
	}
	public void setVersions(Collection<DocumentVersion> versions) {
		this.versions = versions;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getProgramLaunguage() {
		return programLaunguage;
	}
	public void setProgramLaunguage(String programLaunguage) {
		this.programLaunguage = programLaunguage;
	}
	
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
	public Collection<WorksOn> getUsers() {
		return users;
	}
	public void setUsers(Collection<WorksOn> users) {
		this.users = users;
	}
	
	
	public Collection<JoinRequest> getRequests() {
		return requests;
	}
	public void setRequests(Collection<JoinRequest> requests) {
		this.requests = requests;
	}
}
