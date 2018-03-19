package controller;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoManager {

	private List<Command> undoCommands;
	private List<Command> redoCommands;
	
	private static UndoRedoManager instance = null;
	
	private UndoRedoManager() {
		undoCommands = new ArrayList<Command>();
		redoCommands = new ArrayList<Command>();
	}
	
	public static UndoRedoManager getInstance() {
		if(instance == null) instance = new UndoRedoManager();
		return instance;
	}
	
	public void addUndoCommand(Command command) {
		undoCommands.add(command);
	}
	
	public void addRedoCommand(Command command) {
		redoCommands.add(command);
	}
	
	public void undo() {
		if(undoCommands.size() == 0) return;
		Command command = undoCommands.remove(undoCommands.size() - 1);
		command.execute();
	}
	
	public void redo() {
		if(redoCommands.size() == 0) return;
		Command command = redoCommands.remove(redoCommands.size() - 1);
		command.execute();
	}
	
	public void clear() {
		for(Command command : undoCommands)
			undoCommands.remove(command);
		for(Command command : redoCommands)
			redoCommands.remove(command);
	}
	
}
