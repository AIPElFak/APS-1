package controller;

public interface ControllerOffline extends Controller {
	
	void updateRowColStatus(int row, int col);
	
	void saveDocument(String path, String name);
	
	void openDocument(String path, String name);
	
	void createNewDocument();
	
}