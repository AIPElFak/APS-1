package view;

import java.util.ArrayList;
import java.util.List;

import communication.Client;
import utilities.DocumentRemote;
import utilities.UserRemote;

public interface View {
	
	void appendDocumentMessage(String username, String message);
	
	void appendLobyMessage(String username, String message);
	
	void disposeView();
	
	void setCaretLocation(int x, int y);
	
	void showRowColPosition(int row, int col);
	
	void setDocumentName(String name);
	
	void updateStatisics(String statistics);
	
	void updateContent(String content);
	
	void find(String text);
	
	void replace(String text, String replace);
	
	void showAvailableDocument(ArrayList<DocumentRemote> docLst);
	
	String getDocumentContent();

	void updateCollaborators(List<UserRemote> collabs);

	void recvDocUpdate(String type, String text, int length, int location);
	
}
