package controller;

import model.Model;
import view.View;

public class ControllerTemplateImpl implements ControllerTemplate {

	private Model model;
	private View view;
	
	@Override
	public void setModel(Model model) {
		this.model = model;
	}

	@Override
	public void setView(View view) {
		this.view = view;
	}

	@Override
	public void exitApplication() {
		System.exit(0);
	}
	
	@Override
	public void updateRowColStatus(int row, int col) {
		view.showRowColPosition(row, col);
	}

	@Override
	public void updateDocumentName(String name) {
		model.setName(name);
		view.setDocumentName(name);
	}

	@Override
	public void updateDocumentInfo(String extension, String progLanguage) {
		model.setExtension(extension);
		model.setLanguage(progLanguage);
	}

	@Override
	public void updateDocumentContent(String content) {
		model.setContent(content);
	}

	@Override
	public Model getModel() {
		return model;
	}

	@Override
	public View getView() {
		return view;
	}

}
