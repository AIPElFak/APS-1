package controller;

import model.Model;
import view.View;

public class CommandUndoRedo implements Command {

	private Controller reciever;
	private String state;
	
	public CommandUndoRedo(Controller reciever, String state) {
		this.reciever = reciever;
		this.state = state;
	}
	
	public void execute() {
		reciever.updateDocumentContent(state);
		reciever.updateModelContent(state);
	}

}
