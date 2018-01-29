package application;

import javax.swing.JFrame;
import javax.swing.JPanel;
import communication.Client;
import communication.Server;
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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

public class LoginFrame extends JFrame {

	private JPanel contentPane, inputsHolder, signUpInputs;
	private JTextField txtUsername;
	private JPasswordField pfPassword;
	
	private Client client;
	private Server server;
	private JTextField txtUsernameSignUp, txtEmail;
	private JPasswordField pfPasswordSignUp;

	public LoginFrame() {
		setIconImage(new ImageIcon(getClass().getResource("../resources/cometIconMin.png")).getImage());
		setTitle("Comet");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 525);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(21, 126, 251));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JSeparator logoBotLowerSeparator = new JSeparator();
		logoBotLowerSeparator.setForeground(new Color(245, 245, 245));
		logoBotLowerSeparator.setBackground(new Color(248, 248, 255));
		logoBotLowerSeparator.setBounds(68, 463, 362, 2);
		panel.add(logoBotLowerSeparator);
		
		JSeparator logoBotSeparator = new JSeparator();
		logoBotSeparator.setForeground(new Color(245, 245, 245));
		logoBotSeparator.setBackground(new Color(248, 248, 255));
		logoBotSeparator.setBounds(25, 427, 448, 2);
		panel.add(logoBotSeparator);
		
		JLabel logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(getClass().getResource("../resources/logo.png")));
		logo.setBounds(10, 158, 477, 193);
		panel.add(logo);
		
		JLabel lblTogetherYouCan = new JLabel("Together we can do more! ");
		lblTogetherYouCan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTogetherYouCan.setFont(new Font("Courier New", Font.PLAIN, 20));
		lblTogetherYouCan.setForeground(new Color(248, 248, 255));
		lblTogetherYouCan.setBounds(96, 30, 300, 25);
		panel.add(lblTogetherYouCan);
		
		JSeparator logoTopSeparator = new JSeparator();
		logoTopSeparator.setForeground(new Color(245, 245, 245));
		logoTopSeparator.setBackground(new Color(248, 248, 255));
		logoTopSeparator.setBounds(25, 82, 448, 2);
		panel.add(logoTopSeparator);
		
		JPanel loginInputs = new JPanel();
		loginInputs.addMouseListener(new MouseAdapter() {
			
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
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inputsHolder.setVisible(true);
				signUpInputs.setVisible(false);
			}
		});
		btnSignIn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnSignIn.setBackground(new Color(32, 32, 32));
			}
			public void mouseExited(MouseEvent e) {
				btnSignIn.setBackground(new Color(52, 52, 52));
			}
			public void mousePressed(MouseEvent e) {
				btnSignIn.setBackground(new Color(12, 12, 12));
			}
			public void mouseReleased(MouseEvent e) {
				btnSignIn.setBackground(new Color(52, 52, 52));
			}
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnSignIn.setBackground(new Color(52, 52, 52));
		btnSignIn.setForeground(Color.LIGHT_GRAY);
		btnSignIn.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnSignIn.setFocusPainted(false);
		btnSignIn.setBorderPainted(false);
		btnSignIn.setContentAreaFilled(false);
		btnSignIn.setOpaque(true);
		btnSignIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		singInOutHolder.add(btnSignIn);
		
		JButton btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputsHolder.setVisible(false);
				signUpInputs.setVisible(true);
			}
		});
		btnSignUp.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnSignUp.setBackground(new Color(32, 32, 32));
			}
			public void mouseExited(MouseEvent e) {
				btnSignUp.setBackground(new Color(52, 52, 52));
			}
			public void mousePressed(MouseEvent e) {
				btnSignUp.setBackground(new Color(12, 12, 12));
			}
			public void mouseReleased(MouseEvent e) {
				btnSignUp.setBackground(new Color(52, 52, 52));
			}
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnSignUp.setBackground(new Color(52, 52, 52));
		btnSignUp.setForeground(Color.LIGHT_GRAY);
		btnSignUp.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnSignUp.setFocusPainted(false);
		btnSignUp.setBorderPainted(false);
		btnSignUp.setContentAreaFilled(false);
		btnSignUp.setOpaque(true);
		btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		singInOutHolder.add(btnSignUp);
		
		JButton btnContinueOffline = new JButton("or continue offline...");
		btnContinueOffline.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnContinueOffline.setBackground(new Color(32, 32, 32));
			}
			public void mouseExited(MouseEvent e) {
				btnContinueOffline.setBackground(new Color(52, 52, 52));
			}
			public void mousePressed(MouseEvent e) {
				btnContinueOffline.setBackground(new Color(12, 12, 12));
			}
			public void mouseReleased(MouseEvent e) {
				btnContinueOffline.setBackground(new Color(52, 52, 52));
			}
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnContinueOffline.setForeground(Color.LIGHT_GRAY);
		btnContinueOffline.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnContinueOffline.setFocusPainted(false);
		btnContinueOffline.setBorderPainted(false);
		btnContinueOffline.setContentAreaFilled(false);
		btnContinueOffline.setOpaque(true);
		btnContinueOffline.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContinueOffline.setBackground(new Color(52, 52, 52));
		btnContinueOffline.setBounds(39, 423, 425, 47);
		loginInputs.add(btnContinueOffline);
		
		inputsHolder = new JPanel();
		inputsHolder.setBackground(Color.DARK_GRAY);
		inputsHolder.setBounds(10, 110, 477, 296);
		inputsHolder.setLayout(null);
		loginInputs.add(inputsHolder);
		
		txtUsername = new JTextField();
		txtUsername.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				txtUsername.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtUsername.getText().equals("")) txtUsername.setText("Enter your username");
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
		
		JSeparator logInUsernameSeparator = new JSeparator();
		logInUsernameSeparator.setForeground(Color.LIGHT_GRAY);
		logInUsernameSeparator.setBackground(Color.LIGHT_GRAY);
		logInUsernameSeparator.setBounds(31, 70, 416, 2);
		inputsHolder.add(logInUsernameSeparator);
		
		JSeparator logInPasswordSeparator = new JSeparator();
		logInPasswordSeparator.setForeground(Color.LIGHT_GRAY);
		logInPasswordSeparator.setBackground(Color.LIGHT_GRAY);
		logInPasswordSeparator.setBounds(31, 162, 416, 2);
		inputsHolder.add(logInPasswordSeparator);
		
		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setForeground(Color.LIGHT_GRAY);
		lblUsername.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblUsername.setBounds(31, 22, 143, 21);
		inputsHolder.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setForeground(Color.LIGHT_GRAY);
		lblPassword.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblPassword.setBounds(31, 114, 143, 21);
		inputsHolder.add(lblPassword);
		
		JLabel lblSignIn = new JLabel("Sign in with:");
		lblSignIn.setForeground(Color.LIGHT_GRAY);
		lblSignIn.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblSignIn.setBounds(312, 175, 135, 21);
		inputsHolder.add(lblSignIn);
		
		pfPassword = new JPasswordField();
		pfPassword.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				pfPassword.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(pfPassword.getText().equals("")) pfPassword.setText("password");
			}
		});
		pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pfPassword.setForeground(Color.GRAY);
		pfPassword.setBackground(Color.DARK_GRAY);
		pfPassword.setText("password");
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
		btnLogIn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnLogIn.setBackground(new Color(32, 32, 32));
			}
			public void mouseExited(MouseEvent e) {
				btnLogIn.setBackground(new Color(52, 52, 52));
			}
			public void mousePressed(MouseEvent e) {
				btnLogIn.setBackground(new Color(12, 12, 12));
			}
			public void mouseReleased(MouseEvent e) {
				btnLogIn.setBackground(new Color(52, 52, 52));
			}
			public void mouseClicked(MouseEvent e) {
				try {
					if(server.login(client, txtUsername.getText(), pfPassword.getText())) {
						server.logActivity("Logged client: " + client.getUserData().getUsername());
						client.showLobby();
						client.hideLogin();
					}
				} 
				catch (RemoteException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		btnLogIn.setForeground(Color.LIGHT_GRAY);
		btnLogIn.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnLogIn.setFocusPainted(false);
		btnLogIn.setBorderPainted(false);
		btnLogIn.setContentAreaFilled(false);
		btnLogIn.setOpaque(true);
		btnLogIn.setBackground(new Color(52, 52, 52));
		btnLogIn.setBounds(31, 212, 253, 47);
		inputsHolder.add(btnLogIn);
		
		signUpInputs = new JPanel();
		signUpInputs.setBackground(Color.DARK_GRAY);
		signUpInputs.setBounds(10, 110, 477, 296);
		signUpInputs.setLayout(null);
		signUpInputs.setVisible(false);
		loginInputs.add(signUpInputs);
		
		txtUsernameSignUp = new JTextField();
		txtUsernameSignUp.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				txtUsernameSignUp.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtUsernameSignUp.getText().equals("")) txtUsernameSignUp.setText("Enter your username");
			}
		});
		txtUsernameSignUp.setText("Enter your username");
		txtUsernameSignUp.setForeground(Color.GRAY);
		txtUsernameSignUp.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtUsernameSignUp.setColumns(10);
		txtUsernameSignUp.setCaretColor(Color.LIGHT_GRAY);
		txtUsernameSignUp.setBorder(null);
		txtUsernameSignUp.setBackground(Color.DARK_GRAY);
		txtUsernameSignUp.setBounds(31, 45, 183, 14);
		signUpInputs.add(txtUsernameSignUp);
		
		JSeparator signUpUsernameSeparator = new JSeparator();
		signUpUsernameSeparator.setForeground(Color.LIGHT_GRAY);
		signUpUsernameSeparator.setBackground(Color.LIGHT_GRAY);
		signUpUsernameSeparator.setBounds(31, 70, 172, 2);
		signUpInputs.add(signUpUsernameSeparator);
		
		JSeparator signUpEmailSeparator = new JSeparator();
		signUpEmailSeparator.setForeground(Color.LIGHT_GRAY);
		signUpEmailSeparator.setBackground(Color.LIGHT_GRAY);
		signUpEmailSeparator.setBounds(31, 162, 416, 2);
		signUpInputs.add(signUpEmailSeparator);
		
		JLabel lblUsernameSignIn = new JLabel("USERNAME:");
		lblUsernameSignIn.setForeground(Color.LIGHT_GRAY);
		lblUsernameSignIn.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblUsernameSignIn.setBounds(31, 22, 183, 21);
		signUpInputs.add(lblUsernameSignIn);
		
		JLabel lblEmail = new JLabel("EMAIL:");
		lblEmail.setForeground(Color.LIGHT_GRAY);
		lblEmail.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblEmail.setBounds(31, 114, 99, 21);
		signUpInputs.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				txtEmail.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtEmail.getText().equals("")) txtEmail.setText("password");
			}
		});
		txtEmail.setText("Enter your email");
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtEmail.setCaretColor(Color.LIGHT_GRAY);
		txtEmail.setBorder(null);
		txtEmail.setBackground(Color.DARK_GRAY);
		txtEmail.setBounds(31, 135, 416, 20);
		signUpInputs.add(txtEmail);
		
		JButton btnCreateAccount = new JButton("Create account");
		btnCreateAccount.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent arg0) {
				btnCreateAccount.setBackground(new Color(32, 32, 32));
			}
			public void mouseExited(MouseEvent e) {
				btnCreateAccount.setBackground(new Color(52, 52, 52));
			}
			public void mousePressed(MouseEvent e) {
				btnCreateAccount.setBackground(new Color(12, 12, 12));
			}
			public void mouseReleased(MouseEvent e) {
				btnCreateAccount.setBackground(new Color(52, 52, 52));
			}
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnCreateAccount.setForeground(Color.LIGHT_GRAY);
		btnCreateAccount.setFont(new Font("Courier New", Font.PLAIN, 20));
		btnCreateAccount.setFocusPainted(false);
		btnCreateAccount.setBorderPainted(false);
		btnCreateAccount.setContentAreaFilled(false);
		btnCreateAccount.setOpaque(true);
		btnCreateAccount.setBackground(new Color(52, 52, 52));
		btnCreateAccount.setBounds(117, 212, 253, 47);
		signUpInputs.add(btnCreateAccount);
		
		JSeparator signUpPasswordSeparator = new JSeparator();
		signUpPasswordSeparator.setForeground(Color.LIGHT_GRAY);
		signUpPasswordSeparator.setBackground(Color.LIGHT_GRAY);
		signUpPasswordSeparator.setBounds(270, 70, 172, 2);
		signUpInputs.add(signUpPasswordSeparator);
		
		JLabel lblPasswordSignIn = new JLabel("PASSWORD:");
		lblPasswordSignIn.setForeground(Color.LIGHT_GRAY);
		lblPasswordSignIn.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblPasswordSignIn.setBounds(270, 22, 183, 21);
		signUpInputs.add(lblPasswordSignIn);
		
		pfPasswordSignUp = new JPasswordField();
		pfPasswordSignUp.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				pfPasswordSignUp.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(pfPasswordSignUp.getText().equals("")) pfPasswordSignUp.setText("password");
			}
		});
		pfPasswordSignUp.setText("password");
		pfPasswordSignUp.setForeground(Color.GRAY);
		pfPasswordSignUp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pfPasswordSignUp.setCaretColor(Color.LIGHT_GRAY);
		pfPasswordSignUp.setBorder(null);
		pfPasswordSignUp.setBackground(Color.DARK_GRAY);
		pfPasswordSignUp.setBounds(269, 45, 183, 20);
		signUpInputs.add(pfPasswordSignUp);
	
	}
	
	public void addClient (Client client) {
		this.client = client;
	}
	
	public void addServer (Server server) {
		this.server = server;
	}
}
