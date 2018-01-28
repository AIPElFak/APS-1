package application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import communication.Client;
import communication.Server;
import utilities.UserData;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane, inputsHolder;
	private JTextField txtUsername;
	private JPasswordField pfPassword;
	
	private Client client;
	private Server server;

	public LoginFrame() {
		setIconImage(new ImageIcon(getClass().getResource("../resources/cometIconMin.png")).getImage());
		setTitle("COMET");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 525);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 171, 169));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(245, 245, 245));
		separator_2.setBackground(new Color(248, 248, 255));
		separator_2.setBounds(68, 463, 362, 2);
		panel.add(separator_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(245, 245, 245));
		separator_4.setBackground(new Color(248, 248, 255));
		separator_4.setBounds(25, 427, 448, 2);
		panel.add(separator_4);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(getClass().getResource("../resources/logo.png")));
		label.setBounds(10, 158, 477, 193);
		panel.add(label);
		
		JLabel lblTogetherYouCan = new JLabel("Together we can do more! ");
		lblTogetherYouCan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTogetherYouCan.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblTogetherYouCan.setForeground(new Color(248, 248, 255));
		lblTogetherYouCan.setBounds(96, 30, 300, 25);
		panel.add(lblTogetherYouCan);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(245, 245, 245));
		separator_3.setBackground(new Color(248, 248, 255));
		separator_3.setBounds(25, 82, 448, 2);
		panel.add(separator_3);
		
		JPanel loginInputs = new JPanel();
		loginInputs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loginInputs.grabFocus();
			}
		});
		loginInputs.setBackground(Color.DARK_GRAY);
		contentPane.add(loginInputs);
		loginInputs.setLayout(null);
		
		JPanel singInOutHolder = new JPanel();
		singInOutHolder.setBackground(Color.DARK_GRAY);
		singInOutHolder.setBounds(39, 40, 425, 47);
		loginInputs.add(singInOutHolder);
		singInOutHolder.setLayout(new GridLayout(1, 2));
		
		JButton btnSignIn = new JButton("Sign in");
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnSignIn.setBackground(new Color(32, 32, 32));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSignIn.setBackground(new Color(52, 52, 52));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnSignIn.setBackground(new Color(32, 32, 32));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnSignIn.setBackground(new Color(52, 52, 52));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnSignIn.setBackground(new Color(52, 52, 52));
		btnSignIn.setForeground(Color.LIGHT_GRAY);
		btnSignIn.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnSignIn.setFocusPainted(false);
		btnSignIn.setBorderPainted(false);
		btnSignIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		singInOutHolder.add(btnSignIn);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSignUp.setBackground(new Color(32, 32, 32));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSignUp.setBackground(new Color(52, 52, 52));
			}
			@Override
			public void mousePressed(MouseEvent e) {
				btnSignUp.setBackground(new Color(32, 32, 32));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnSignUp.setBackground(new Color(52, 52, 52));
			}
		});
		btnSignUp.setBackground(new Color(52, 52, 52));
		btnSignUp.setForeground(Color.LIGHT_GRAY);
		btnSignUp.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnSignUp.setFocusPainted(false);
		btnSignUp.setBorderPainted(false);
		btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		singInOutHolder.add(btnSignUp);
		
		inputsHolder = new JPanel();
		inputsHolder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				inputsHolder.grabFocus();
			}
		});
		inputsHolder.setBackground(Color.DARK_GRAY);
		inputsHolder.setBounds(10, 110, 477, 296);
		loginInputs.add(inputsHolder);
		inputsHolder.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(txtUsername.getText().equals("")) {
					txtUsername.setText("Enter your username");
				}
			}
		});
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtUsername.setText("");
			}
		});
		txtUsername.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtUsername.setForeground(Color.GRAY);
		txtUsername.setBackground(Color.DARK_GRAY);
		txtUsername.setText("Enter your username");
		txtUsername.setBorder(null);
		txtUsername.setCaretColor(Color.LIGHT_GRAY);
		txtUsername.setBounds(31, 45, 416, 14);
		inputsHolder.add(txtUsername);
		txtUsername.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.LIGHT_GRAY);
		separator.setBackground(Color.LIGHT_GRAY);
		separator.setBounds(31, 70, 416, 2);
		inputsHolder.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(Color.LIGHT_GRAY);
		separator_1.setBounds(31, 162, 416, 2);
		inputsHolder.add(separator_1);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setForeground(Color.LIGHT_GRAY);
		lblUsername.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblUsername.setBounds(31, 22, 99, 21);
		inputsHolder.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblPassword.setBounds(31, 114, 99, 21);
		inputsHolder.add(lblPassword);
		
		JLabel lblSignIn = new JLabel("Sign in with:");
		lblSignIn.setForeground(Color.LIGHT_GRAY);
		lblSignIn.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblSignIn.setBounds(312, 175, 135, 21);
		inputsHolder.add(lblSignIn);
		
		pfPassword = new JPasswordField();
		pfPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pfPassword.setText("");
			}
		});
		pfPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(pfPassword.getText().equals("")) {
					pfPassword.setText("markojelep");
				}
			}
		});
		pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pfPassword.setForeground(Color.GRAY);
		pfPassword.setBackground(Color.DARK_GRAY);
		pfPassword.setText("markojelep");
		pfPassword.setBorder(null);
		pfPassword.setCaretColor(Color.LIGHT_GRAY);
		pfPassword.setBounds(31, 135, 416, 20);
		inputsHolder.add(pfPassword);
		
		JButton btnLoginG = new JButton("");
		btnLoginG.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLoginG.setIcon(new ImageIcon(getClass().getResource("../resources/loginGoogle.png")));
		btnLoginG.setBounds(388, 212, 46, 47);
		btnLoginG.setBorder(null);
		btnLoginG.setContentAreaFilled(false);
		inputsHolder.add(btnLoginG);
		
		JButton btnLoginFb = new JButton("");
		btnLoginFb.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLoginFb.setIcon(new ImageIcon(getClass().getResource("../resources/loginFb.png")));
		btnLoginFb.setSelectedIcon(null);
		btnLoginFb.setBorder(null);
		btnLoginFb.setContentAreaFilled(false);
		btnLoginFb.setBounds(322, 212, 46, 47);
		inputsHolder.add(btnLoginFb);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(server.login(client, txtUsername.getText(), pfPassword.getText())) {
						server.logActivity("Logged client: " + client.getUserData().getUsername());
						client.showLobby();
						client.hideLogin();
					}
				} 
				catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogIn.setForeground(Color.LIGHT_GRAY);
		btnLogIn.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnLogIn.setFocusPainted(false);
		btnLogIn.setBorderPainted(false);
		btnLogIn.setBackground(new Color(52, 52, 52));
		btnLogIn.setBounds(31, 212, 253, 47);
		inputsHolder.add(btnLogIn);
		
		JButton btnContinueOffline = new JButton("or continue offline...");
		btnContinueOffline.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnContinueOffline.setBackground(new Color(32, 32, 32));
			}
			public void mouseExited(MouseEvent e) {
				btnContinueOffline.setBackground(new Color(52, 52, 52));
			}
		});
		btnContinueOffline.setForeground(Color.LIGHT_GRAY);
		btnContinueOffline.setFont(new Font("Courier New", Font.PLAIN, 16));
		btnContinueOffline.setFocusPainted(false);
		btnContinueOffline.setBorderPainted(false);
		btnContinueOffline.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContinueOffline.setBackground(new Color(52, 52, 52));
		btnContinueOffline.setBounds(39, 423, 425, 47);
		loginInputs.add(btnContinueOffline);
	
	}
	
	public void addClient (Client client) {
		this.client = client;
	}
	
	public void addServer (Server server) {
		this.server = server;
	}
}
