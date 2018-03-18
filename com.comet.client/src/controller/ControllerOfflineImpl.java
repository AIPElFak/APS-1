package controller;

public class ControllerOfflineImpl extends ControllerImpl implements ControllerOffline{

	@Override
	public void saveDocument(String path, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openDocument(String path, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createNewDocument() {
		getView().setDocumentName("New document");
		getView().updateContent("");
		getModel().setName("New document");
		getModel().setContent("");
		getModel().setExtension("");
		getModel().setLanguage("");
	}

}
