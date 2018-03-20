package view;

import utilities.DocumentList;

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
	
	void showAvailableDocument(DocumentList docLst);
	
	String getDocumentContent();
	
}
