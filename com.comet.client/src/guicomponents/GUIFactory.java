package guicomponents;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.JTextComponent;

import languages.SymbolTable;

public class GUIFactory {

	public static MouseAdapter createButtonColorChanger(Color original, Color mouseEntered, Color mousePressed) {
		
		return new ButtonColorChanger(original, mouseEntered, mousePressed);
		
	}
	
	public static DefaultStyledDocument createCometEditorDocument(SymbolTable st) {
		
		return new CometEditorDocument(st);
		
	}
	
	public static JButton createCometFlatButton(String text, Color bgColor, Color fgColor) {
		
		return new CometFlatButton(text, bgColor, fgColor);
	
	}
	
	public static JPanel createTextLineNumber(JTextComponent component) {
		
		return new TextLineNumber(component);
		
	} 
	
	public static JPanel createTextLineNumber(JTextComponent component, int minimumDisplayDigits) {
		
		return new TextLineNumber(component, minimumDisplayDigits);
		
	}
	
}
