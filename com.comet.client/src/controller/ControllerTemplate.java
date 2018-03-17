package controller;

import model.Model;
import view.View;

public interface ControllerTemplate {

	public void setModel(Model model);
	
	public void setView(View view);
	
	Model getModel();
	
	View getView();
	
	void updateCaretLocation(int x, int y);
	
	void exitApplication();
	
	void updateDocumentName(String name);
	
	void updateDocumentInfo(String extension, String progLanguage);
	
	void updateDocumentStatisics(String statistics);
	
	void updateDocumentContent(String content);
	
	void updateRowColStatus(int row, int col);
	
}
