package view;

public interface View {
	
	void appendDocumentMessage(String username, String message);
	
	void appendLobyMessage(String username, String message);
	
	void disposeView();
	
	void showRowColPosition(int row, int col);
	
	void setDocumentName(String name);
	
	void updateDocumentInfo(String extension, String progLanguage);
	
	void updateContent(String content);
}
