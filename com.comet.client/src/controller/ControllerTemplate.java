package controller;

import model.Model;
import view.View;

public interface ControllerTemplate {

	public void setModel(Model model);
	
	public void setView(View view);
	
	Model getModel();
	
	View getView();
	
	public void exitApplication();
	
	public void updateDocumentName(String name);
	
	public void updateDocumentInfo(String extension, String progLanguage);
	
	public void updateDocumentContent(String content);
	
	void updateRowColStatus(int row, int col);
	
}
