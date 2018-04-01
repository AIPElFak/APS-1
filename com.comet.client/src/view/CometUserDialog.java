package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import guicomponents.GUIFactory;
import javax.swing.border.LineBorder;

import controller.ControllerOnline;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;

public class CometUserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtEmail;
	
	private ControllerOnline controller;
	private JFrame parentFrame;

	public CometUserDialog(ControllerOnline cntrl, JFrame parent) {
		setTitle("Comet");
		setBounds(100, 100, 450, 515);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(11, 116, 241));
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		controller = cntrl;
		parentFrame = parent;
		JDialog self = this;
		
		MouseAdapter docBtnColorChanger = GUIFactory.createButtonColorChanger
		(
				new Color(1, 96, 181),
				new Color(1, 86, 171),
				new Color(1, 76, 161)
		);
		
		MouseAdapter dialogBtnColorChanger = GUIFactory.createButtonColorChanger
		(
				new Color(1, 61, 151),
				new Color(1, 51, 141),
				new Color(1, 41, 131)
		);
		
		JLabel lblUserInformation = new JLabel("User information");
		lblUserInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserInformation.setForeground(new Color(244, 244, 255));
		lblUserInformation.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblUserInformation.setBounds(10, 24, 414, 27);
		contentPanel.add(lblUserInformation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 414, 2);
		contentPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 62, 414, 2);
		contentPanel.add(separator_1);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(new Color(238, 238, 255));
		lblUsername.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblUsername.setBorder(null);
		lblUsername.setBounds(10, 275, 189, 32);
		lblUsername.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(238, 238, 255)));
		contentPanel.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setForeground(new Color(1, 91, 181));
		txtUsername.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtUsername.setColumns(10);
		txtUsername.setBorder(null);
		txtUsername.setBackground(new Color(175, 238, 238));
		txtUsername.setBounds(217, 275, 207, 32);
		contentPanel.add(txtUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(new Color(238, 238, 255));
		lblPassword.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblPassword.setBorder(null);
		lblPassword.setBounds(10, 318, 189, 32);
		lblPassword.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(238, 238, 255)));
		contentPanel.add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setForeground(new Color(1, 91, 181));
		txtPassword.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtPassword.setColumns(10);
		txtPassword.setBorder(null);
		txtPassword.setBackground(new Color(175, 238, 238));
		txtPassword.setBounds(217, 318, 207, 32);
		contentPanel.add(txtPassword);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(new Color(238, 238, 255));
		lblEmail.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblEmail.setBorder(null);
		lblEmail.setBounds(10, 361, 189, 32);
		lblEmail.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(238, 238, 255)));
		contentPanel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setForeground(new Color(1, 91, 181));
		txtEmail.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtEmail.setColumns(10);
		txtEmail.setBorder(null);
		txtEmail.setBackground(new Color(175, 238, 238));
		txtEmail.setBounds(217, 361, 207, 32);
		contentPanel.add(txtEmail);
		
		JLabel label = new JLabel("");
		label.setBounds(10, 75, 189, 189);
		label.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/personImage.jpg"))
				.getImage().getScaledInstance(187, 187, Image.SCALE_DEFAULT)));
		label.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(238, 238, 255)));
		contentPanel.add(label);
		
		JButton btnLoadImage = GUIFactory.createCometFlatButton("Load image", new Color(1, 91, 181), new Color(244, 244, 255));
		btnLoadImage.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnLoadImage.setBounds(217, 88, 207, 39);
		btnLoadImage.addMouseListener(docBtnColorChanger);
		contentPanel.add(btnLoadImage);
		
		JButton btnSetDefault = GUIFactory.createCometFlatButton("Set default image", new Color(1, 91, 181), new Color(244, 244, 255));
		btnSetDefault.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnSetDefault.setBounds(217, 150, 207, 39);
		btnSetDefault.addMouseListener(docBtnColorChanger);
		contentPanel.add(btnSetDefault);
		
		JPanel dialogButtons = new JPanel();
		dialogButtons.setBounds(0, 420, 435, 56);
		contentPanel.add(dialogButtons);
		dialogButtons.setBackground(new Color(1, 91, 181));
		dialogButtons.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnSave = GUIFactory.createCometFlatButton("Save", new Color(1, 61, 151), new Color(244, 244, 255));
		btnSave.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnSave.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(21, 126, 251)));
		btnSave.addMouseListener(dialogBtnColorChanger);
		dialogButtons.add(btnSave);
		
		JButton btnCancel = GUIFactory.createCometFlatButton("Cancel", new Color(1, 61, 151), new Color(244, 244, 255));
		btnCancel.setBorder(new LineBorder(new Color(11, 116, 241)));
		dialogButtons.add(btnCancel);
		btnCancel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(21, 126, 251)));
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(217, 75, 207, 2);
		contentPanel.add(separator_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(217, 262, 207, 2);
		contentPanel.add(separator_4);
		
		JButton btnDeactivateAccount = GUIFactory.createCometFlatButton("Deactivate account", new Color(186, 20, 48), new Color(244, 244, 255));
		btnDeactivateAccount.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnDeactivateAccount.setBounds(217, 212, 207, 39);
		btnDeactivateAccount.addMouseListener(GUIFactory.createButtonColorChanger
		(
				new Color(186, 20, 48),
				new Color(176, 10, 38),
				new Color(166, 0, 28)
		));
		btnDeactivateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CometSplashScreen cs = new CometSplashScreen("Deactivating account...");
				new Thread(cs).start();
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e) {}
						cs.stopAnimation();
						CometDialog cd = new CometDialog("info", "Account deactivated.");
						if(controller.deleteAccount()) cd.setVisible(true);
						else {
							cd = new CometDialog("info", "Account deactivation failed.");
							cd.setVisible(true);
						}
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e) {}
						cd.dispose();
						self.dispose();
						parentFrame.dispose();
						controller.setClient(null);
					}
				}).start();
			}
		});
		contentPanel.add(btnDeactivateAccount);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 417, 414, 2);
		contentPanel.add(separator_3);
		
		btnCancel.addMouseListener(dialogBtnColorChanger);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				self.dispose();
			}
		});
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				new Thread(new Runnable() {
					public void run() {
						try {
							txtUsername.setText(controller.getUserData().getUsername());
							txtPassword.setText(controller.getUserData().getPassword());
							txtEmail.setText(controller.getUserData().getEmail());
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}).start();
			}
			
		});
	}
}
