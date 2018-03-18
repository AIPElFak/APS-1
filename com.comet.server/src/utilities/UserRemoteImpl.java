package utilities;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

import database.dto.Document;
import database.dto.User;
import database.dto.WorksOn;
import utilities.UserRemote;
import utilities.UserRemoteImpl;

public class UserRemoteImpl extends UnicastRemoteObject implements UserRemote {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String username, password, email, imageUrl;
	private ArrayList<DocumentRemoteImpl> currentDocuments;
	private ArrayList<DocumentRemoteImpl> availableDocuments;
	
	
	public UserRemoteImpl(String username, String password, String email, String imageUrl,int id) throws RemoteException {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.imageUrl = imageUrl;

		this.currentDocuments = new ArrayList<DocumentRemoteImpl>();
		this.availableDocuments = new ArrayList<DocumentRemoteImpl>();
	}

	public UserRemoteImpl(User user)  throws RemoteException  {
		super();
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.imageUrl = user.getImage();
		
		this.currentDocuments = getRemoteDocuments(user.getDocuments());
	}

	private ArrayList<DocumentRemoteImpl> getRemoteDocuments(Collection<WorksOn> documents) throws RemoteException {
		ArrayList<DocumentRemoteImpl> docs = new ArrayList<DocumentRemoteImpl>();
		for(WorksOn wo : documents) {
			DocumentRemoteImpl dr = new DocumentRemoteImpl(wo.getDocument());
			dr.setPrivilege(wo.getPrivilege());
			docs.add(dr);
		}
		return docs;
	}

	@Override
	public String getUsername() throws RemoteException {
		return username;
	}

	@Override
	public String getPassword() throws RemoteException {
		return password;
	}

	@Override
	public int getId() throws RemoteException {
		return id;
	}

	@Override
	public String getEmail() throws RemoteException {
		return email;
	}

	@Override
	public String getImage() throws RemoteException {
		return imageUrl;
	}

	public ArrayList<DocumentRemoteImpl> getCurrentDocuments() {
		return currentDocuments;
	}

	public void setCurrentDocuments(ArrayList<DocumentRemoteImpl> currentDocuments) {
		this.currentDocuments = currentDocuments;
	}

	public ArrayList<DocumentRemoteImpl> getAvailableDocuments() {
		return availableDocuments;
	}

	public void setAvailableDocuments(ArrayList<DocumentRemoteImpl> availableDocuments) {
		this.availableDocuments = availableDocuments;
	}

	//ne dozvoljava zbog template parametra da se zovu isto ovi set-eri
	public void setAvailableDocuments2(ArrayList<Document> documents) throws RemoteException {
		ArrayList<DocumentRemoteImpl> remoteDocs = new ArrayList<DocumentRemoteImpl>();
		for(Document doc : documents) {
			DocumentRemoteImpl dr = new DocumentRemoteImpl(doc);
			remoteDocs.add(dr);
		}
		this.availableDocuments = remoteDocs;
	}


}
