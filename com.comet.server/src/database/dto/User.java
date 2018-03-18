package database.dto;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name="USER_DETAILS")
public class User{
	
//attributes
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASS")
	private String password;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="IMAGE")
	private String image;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private Collection<WorksOn> documents;
	
	@OneToMany(mappedBy="user")
	private Collection<DocumentVersion> versions;
	
	@OneToMany(mappedBy="requester", cascade = CascadeType.ALL)
	private Collection<JoinRequest> requests;
	
//constructors
	public User() throws RemoteException {
		super();
		this.documents = new ArrayList<WorksOn>();
		this.versions = new ArrayList<DocumentVersion>();
		this.requests = new ArrayList<JoinRequest>();
	}
	
	public User(String username,String password,String email,String image) throws RemoteException {
		super();
		this.documents = new ArrayList<WorksOn>();
		this.versions = new ArrayList<DocumentVersion>();
		this.requests = new ArrayList<JoinRequest>();
		this.username = username;
		this.password = password;
		this.email = email;
		this.image = image;
	}
//getters and setters	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	public Collection<WorksOn> getDocuments() {
		return documents;
	}
	public void setDocuments(Collection<WorksOn> documents) {
		this.documents = documents;
	}
	
	
	public Collection<DocumentVersion> getVersions() {
		return versions;
	}
	public void setVersions(Collection<DocumentVersion> versions) {
		this.versions = versions;
	}
	
	
	public Collection<JoinRequest> getRequests() {
		return requests;
	}
	public void setRequests(Collection<JoinRequest> requests) {
		this.requests = requests;
	}
}
