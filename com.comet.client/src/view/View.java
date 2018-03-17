package view;

public interface View {
	
	void appendDocumentMessage(String username, String message);
	
	void appendLobyMessage(String username, String message);
	
	void disposeView();
	
	void setCaretLocation(int x, int y);
	
	void showRowColPosition(int row, int col);
	
	void setDocumentName(String name);
	
	void updateStatisics(String statistics);
	
	void updateContent(String content);
}