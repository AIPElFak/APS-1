package guicomponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

class ButtonColorChanger extends MouseAdapter {
	
	private Color original, mouseEntered, mousePressed;
	
	public ButtonColorChanger(Color original, Color mouseEntered, Color mousePressed) {
		this.original = original;
		this.mouseEntered = mouseEntered;
		this.mousePressed = mousePressed;
	}
	
	public void mouseEntered(MouseEvent e) {
		((JButton) e.getSource()).setBackground(mouseEntered);
		((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	public void mouseExited(MouseEvent e) {
		((JButton) e.getSource()).setBackground(original);
		((JButton) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	public void mousePressed(MouseEvent e) {
		((JButton) e.getSource()).setBackground(mousePressed);
		((JButton) e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	public void mouseReleased(MouseEvent e) {
		((JButton) e.getSource()).setBackground(original);
		((JButton) e.getSource()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
}
