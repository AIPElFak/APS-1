package aps.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class WorksOnId implements Serializable{

	@ManyToOne(cascade = CascadeType.ALL)
	private Document document;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	public WorksOnId() {
		
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

	@Override
	public int hashCode() {
		return Objects.hash(this.getDocument().getId(),this.getUser().getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass())return false;
		WorksOnId recieved = (WorksOnId) obj;
		if((this.document.getId() == recieved.getDocument().getId()) &&
				this.user.getId() == recieved.getUser().getId()) {
			return true;
		}
		return false;
	}
	
}
