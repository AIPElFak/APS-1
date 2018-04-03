package utilities;

import java.util.Date;

public interface VersionRemote {

	public int getId() ;
	
	public String getContent();
	
	public Date getDateTime();
	
	public int getDocumentId();
	
	public String getDocumentName();
	
	public int getUserId() ;
	
	public String getUserName();
	
}
