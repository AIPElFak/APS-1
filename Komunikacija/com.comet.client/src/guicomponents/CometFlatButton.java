package guicomponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class CometFlatButton extends JButton {
	
	public CometFlatButton(String text, Color bgColor, Color fgColor) {
		
		setFont(new Font("Courier New", Font.PLAIN, 15));
		setText(text);
		setForeground(fgColor);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(true);
		setBackground(bgColor);
		
	}
	
}
