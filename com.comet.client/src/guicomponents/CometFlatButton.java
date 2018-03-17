package guicomponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

public class CometFlatButton extends JButton {
	
	public CometFlatButton(String text, Color bgColor, Color fgColor) {
		setFont(new Font("Courier New", Font.PLAIN, 15));
		setText(text);
		setForeground(fgColor);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(true);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBackground(bgColor);
	}
}
