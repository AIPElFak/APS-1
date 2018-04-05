package guicomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.LineBorder;

import utilities.UserRemote;

class CollabRenderer extends JPanel implements ListCellRenderer<UserRemote> {

	private JLabel image, username;
	private JButton btnRead, btnWrite, btnOwner, btnRemove; 
	private JList parent;
	
	public CollabRenderer(JList prnt) {
		
		setBackground(new Color(60, 60, 60));
		setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(190, 190, 190)));
		
		image = new JLabel();
		image.setBorder(new LineBorder(new Color(190, 190, 190)));
		add(image, BorderLayout.WEST);
		
		JPanel userDataHolder = new JPanel();
		userDataHolder.setLayout(new GridLayout(2, 1));
		userDataHolder.setBackground(new Color(60, 60, 60));
		add(userDataHolder, BorderLayout.EAST);
		
		username = new JLabel();
		username.setFont(new Font("Courier New", Font.BOLD, 12));
		username.setForeground(new Color(238, 238, 255));
		username.setHorizontalAlignment(JLabel.CENTER);
		userDataHolder.add(username);
		
		JPanel privilegies = new JPanel();
		privilegies.setLayout(new GridLayout(1, 4));
		userDataHolder.add(privilegies);
		
		btnRead = GUIFactory.createCometFlatButton("R", new Color(120, 120, 120), new Color(238, 238, 255));
		btnRead.setBorder(BorderFactory.createMatteBorder(1, 7, 1, 7, new Color(120, 120, 120)));
		privilegies.add(btnRead);
		btnWrite = GUIFactory.createCometFlatButton("W", new Color(120, 120, 120), new Color(238, 238, 255));
		btnWrite.setBorder(BorderFactory.createMatteBorder(1, 7, 1, 7, new Color(120, 120, 120)));
		privilegies.add(btnWrite);
		btnOwner = GUIFactory.createCometFlatButton("O", new Color(120, 120, 120), new Color(238, 238, 255));
		btnOwner.setBorder(BorderFactory.createMatteBorder(1, 7, 1, 7, new Color(120, 120, 120)));
		privilegies.add(btnOwner);
		btnRemove = GUIFactory.createCometFlatButton("X", new Color(255, 0, 0), new Color(238, 238, 255));
		btnRemove.setBorder(BorderFactory.createMatteBorder(1, 7, 1, 7, new Color(255, 0, 0)));
		privilegies.add(btnRemove);
		
		parent = prnt;
		
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends UserRemote> list, UserRemote value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		try {
			username.setText(value.getUsername().toUpperCase());
			image.setIcon(new ImageIcon(new ImageIcon(getClass()
					.getResource("../resources/personImage.jpg")).getImage()
					.getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
			if(value.getImageBytes() != null) {
				BufferedImage img;
				try {
					img = ImageIO.read(new ByteArrayInputStream(value.getImageBytes()));
					image.setIcon(new ImageIcon(img.getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
				} catch (IOException e) {}
				
			}
			
			if(isSelected) {
				handleClick(index);
			}
			parent.clearSelection();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this;
	}

	private void handleClick(int index) {
		
		if(parent.getMousePosition() == null) return;
		
		int btnReadX = 42;
		int btnWriteX = 64;
		int btnOwnerX = 88;
		int btnRemoveX = 112;
		
		int btnY = 24;
		
		int localX = parent.getMousePosition().x;
		int localY = parent.getMousePosition().y - index * 52;
		
		//Read
		if(btnReadX <= localX && localX <= btnReadX + 24
			&& btnY <= localY && localY <= btnY + 20) {
			btnRead.setForeground(new Color(255, 230, 0));
			btnWrite.setForeground(new Color(238, 238, 255));
			btnOwner.setForeground(new Color(238, 238, 255));
		}
		//Write
		else if(btnWriteX <= localX && localX <= btnWriteX + 24
			&& btnY <= localY && localY <= btnY + 20) {
			btnRead.setForeground(new Color(238, 238, 255));
			btnWrite.setForeground(new Color(255, 230, 0));
			btnOwner.setForeground(new Color(238, 238, 255));
		}
		//Owner
		else if(btnOwnerX <= localX && localX <= btnOwnerX + 24
			&& btnY <= localY && localY <= btnY + 20) {
			btnRead.setForeground(new Color(238, 238, 255));
			btnWrite.setForeground(new Color(238, 238, 255));
			btnOwner.setForeground(new Color(255, 230, 0));
		}
		//Remove
		else if(btnRemoveX <= localX && localX <= btnRemoveX + 24
			&& btnY <= localY && localY <= btnY + 20) {
			
		}
		else {
			btnRead.setForeground(new Color(238, 238, 255));
			btnWrite.setForeground(new Color(238, 238, 255));
			btnOwner.setForeground(new Color(238, 238, 255));
		}
	}
	
}
