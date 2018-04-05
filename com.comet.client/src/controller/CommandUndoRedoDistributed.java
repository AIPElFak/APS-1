package controller;

public class CommandUndoRedoDistributed implements Command {

	private ControllerOnline reciever;
	private String state;
	
	public CommandUndoRedoDistributed(ControllerOnline reciever, String state) {
		this.reciever = reciever;
		this.state = state;
	}
	
	public void execute() {
		reciever.sendDocUpdate("pull", state, state.length(), 0);
		reciever.updateDocumentContent(state);
	}

}
