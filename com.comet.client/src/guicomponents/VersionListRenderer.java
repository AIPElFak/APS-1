package guicomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import utilities.VersionRemote;

class VersionListRenderer extends JPanel implements ListCellRenderer<VersionRemote> {

	private JLabel name, date, user;
	
	public VersionListRenderer() {
		
		setLayout(new GridLayout(1, 3));
		setBackground(new Color(175, 238, 238));
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(21, 126, 251)));
		
		name = new JLabel();
		name.setBorder(new EmptyBorder(0, 35, 0, 0));
		name.setFont(new Font("Courier New", Font.PLAIN, 11));
		add(name);
		
		date = new JLabel();
		date.setBorder(new EmptyBorder(0, 35, 0, 0));
		date.setFont(new Font("Courier New", Font.PLAIN, 11));
		add(date);
		
		user = new JLabel();
		user.setBorder(new EmptyBorder(0, 35, 0, 0));
		user.setFont(new Font("Courier New", Font.PLAIN, 11));
		add(user);
		
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends VersionRemote> list, VersionRemote value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		try {
			name.setText(value.getDocumentName());
			String[] tokens = value.getDateTime().toString().split("[ ]");
			date.setText(tokens[0]);
			user.setText(value.getUserName());
		} catch (RemoteException e) {}
		
		if(isSelected) {
			name.setForeground(new Color(234, 129, 4));
			date.setForeground(new Color(234, 129, 4));
			user.setForeground(new Color(234, 129, 4));
		}
		else {
			name.setForeground(new Color(1, 81, 191));
			date.setForeground(new Color(1, 81, 191));
			user.setForeground(new Color(1, 81, 191));
		}
		
		return this;
	}

}
