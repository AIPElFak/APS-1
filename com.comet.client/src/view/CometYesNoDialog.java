package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import guicomponents.GUIFactory;

public class CometYesNoDialog extends JDialog {

	private JLabel lblImage, lblMessage;
	
	private Image WARNING_IMAGE;

	private Image INFO_IMAGE;
	
	private Image selectedImage;

	static int selectedButton;
	
	private CometYesNoDialog(String type, String message) {
		JDialog self = this;
		
		setTitle("Comet");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 342, 146);
		setUndecorated(true);
		setLocationRelativeTo(null);
		
		WARNING_IMAGE = new ImageIcon(getClass()
				.getResource("../resources/warningDialog.png")).getImage();
		
		INFO_IMAGE = new ImageIcon(getClass()
				.getResource("../resources/infoDialog.png")).getImage();
		
		if(type.toLowerCase().equals("warning")) {
			selectedImage = this.WARNING_IMAGE;
		} else {
			selectedImage = this.INFO_IMAGE;
		}
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.setBackground(new Color(1, 86, 191));
		panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(1, 66, 171)));
		panel.setLayout(null);
		
		lblImage = new JLabel();
		lblImage.setBounds(10, 11, 96, 96);
		lblImage.setIcon(new ImageIcon(selectedImage.getScaledInstance(96, 96, Image.SCALE_DEFAULT)));
		panel.add(lblImage);
		
		lblMessage = new JLabel("<html>" + message + "</html>");
		lblMessage.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(238, 238, 255));
		lblMessage.setBounds(116, 11, 177, 86);
		panel.add(lblMessage);
		
		MouseAdapter docBtnColorChanger = GUIFactory.createButtonColorChanger
		(
				new Color(1, 96, 181),
				new Color(1, 86, 171),
				new Color(1, 76, 161)
		);
		
		
		
		JButton btnYes = GUIFactory.createCometFlatButton
		(
				"Yes",
				new Color(1, 91, 181),
				new Color(244, 244, 181)
		);
		btnYes.addMouseListener(docBtnColorChanger);
		btnYes.setBounds(220, 110, 64, 32);
		btnYes.setBorder(new LineBorder(new Color(210, 0, 0)));
		btnYes.setFont(new Font("Courier New", Font.BOLD, 16));
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedButton = JOptionPane.YES_OPTION;
				self.dispose();
			}
		});
		panel.add(btnYes);
		
		
		
		JButton btnNo = GUIFactory.createCometFlatButton
		(
				"No",
				new Color(1, 91, 181),
				new Color(244, 244, 255)
		);
		btnNo.addMouseListener(docBtnColorChanger);
		btnNo.setBounds(120, 110, 64, 32);
		btnNo.setBorder(new LineBorder(new Color(210, 0, 0)));
		btnNo.setFont(new Font("Courier New", Font.BOLD, 16));
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedButton = JOptionPane.NO_OPTION;
				self.dispose();
			}
		});
		panel.add(btnNo);
		
		
		
		
		JButton btnX = GUIFactory.createCometFlatButton
		(
				"X",
				new Color(255, 0, 0),
				new Color(238, 238, 255)
		);
		btnX.setBounds(300, 11, 32, 32);
		btnX.setBorder(new LineBorder(new Color(210, 0, 0)));
		btnX.setFont(new Font("Courier New", Font.BOLD, 21));
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedButton = JOptionPane.CLOSED_OPTION;
				self.dispose();
			}
		});
		panel.add(btnX);
		
	}
	
	public static int showDialog(String type, String message) {
		CometYesNoDialog d = new CometYesNoDialog(type, message);
		d.setVisible(true);
		return selectedButton;
	}
	
	public static void main(String args[]) {
		int res = CometYesNoDialog.showDialog("warning", "Are you sure?");
		if(res == JOptionPane.YES_OPTION)
			System.out.println("YES");
		else if(res == JOptionPane.NO_OPTION)
			System.out.println("NO");
		else if(res == JOptionPane.CLOSED_OPTION)
			System.out.println("CLOSED");
	}
}
