package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import communication.Client;
import communication.ClientImpl;
import communication.Server;
import communication.ServerXMLConnector;
import controller.ControllerOffline;
import controller.ControllerOfflineImpl;
import controller.ControllerOnline;
import controller.ControllerOnlineImpl;
import guicomponents.GUIFactory;
import model.Model;
import model.ModelImpl;
import view.EditorFrameOffline;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LoginFrame extends JFrame {

	private JPanel contentPane, inputsHolder, signUpInputs;
	private JTextField txtUsername;
	private JPasswordField pfPassword;
	
	private JTextField txtUsernameSignUp, txtEmail;
	private JPasswordField pfPasswordSignUp;
	private JLabel lblImage;
	
	private Image userImage;
	private String userImageExtension;

	private JFrame self;

	public LoginFrame() {
		
		self = this;
		
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
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
		
		MouseAdapter buttonColorChanger = GUIFactory.createButtonColorChanger
		(
			new Color(52, 52, 52),
			new Color(32, 32, 32),
			new Color(12, 12, 12)
		);
		
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
		
		JButton btnSignIn = GUIFactory.createCometFlatButton("Sign in",
				new Color(52, 52, 52), Color.LIGHT_GRAY);
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inputsHolder.setVisible(true);
				signUpInputs.setVisible(false);
			}
		});
		btnSignIn.addMouseListener(buttonColorChanger);
		btnSignIn.setBorder(new LineBorder(Color.DARK_GRAY));
		singInOutHolder.add(btnSignIn);
		
		JButton btnSignUp = GUIFactory.createCometFlatButton("Sign up",
				new Color(52, 52, 52), Color.LIGHT_GRAY);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inputsHolder.setVisible(false);
				signUpInputs.setVisible(true);
			}
		});
		btnSignUp.addMouseListener(buttonColorChanger);
		btnSignUp.setBorder(new LineBorder(Color.DARK_GRAY));
		singInOutHolder.add(btnSignUp);
		
		JButton btnContinueOffline = GUIFactory.createCometFlatButton(
				"or continue offline...",
				new Color(52, 52, 52), Color.LIGHT_GRAY);
		btnContinueOffline.addMouseListener(buttonColorChanger);
		btnContinueOffline.setBounds(39, 423, 425, 47);
		btnContinueOffline.setBorderPainted(false);
		btnContinueOffline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CometSplashScreen cs = new CometSplashScreen("Starting offline mode...");
				Thread t1 = new Thread(cs);
				Thread t2 = new Thread(new Runnable() {
					public void run() {
						ControllerOffline cntrl = new ControllerOfflineImpl();
						try {
							Thread.sleep(1500);
						} 
						catch (InterruptedException e) {}
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								EditorFrameOffline frame = new EditorFrameOffline(cntrl);
								Model model = new ModelImpl();
								cntrl.setView(frame);
								cntrl.setModel(model);
								frame.setVisible(true);
								cs.stopAnimation();
							}
						});
						cs.stopAnimation();
						disposeFrame();
					}
				});
				t1.start();
				t2.start();
			}
		});
		loginInputs.add(btnContinueOffline);
		
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
				if(txtUsernameSignUp.getText().equals(""))
					txtUsernameSignUp.setText("Enter your username");
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
		signUpEmailSeparator.setBounds(31, 162, 172, 2);
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
				if(txtEmail.getText().equals(""))
					txtEmail.setText("password");
			}
		});
		txtEmail.setText("Enter your email");
		txtEmail.setForeground(Color.GRAY);
		txtEmail.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtEmail.setCaretColor(Color.LIGHT_GRAY);
		txtEmail.setBorder(null);
		txtEmail.setBackground(Color.DARK_GRAY);
		txtEmail.setBounds(31, 135, 172, 20);
		signUpInputs.add(txtEmail);
		
		JButton btnCreateAccount = GUIFactory.createCometFlatButton(
				"Create account",
				new Color(52, 52, 52),
				Color.LIGHT_GRAY);
		btnCreateAccount.addMouseListener(buttonColorChanger);
		btnCreateAccount.setBorderPainted(false);
		btnCreateAccount.setBounds(31, 212, 172, 47);
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CometSplashScreen cs = new CometSplashScreen("Creating account...");
				Thread t1 = new Thread(cs);
				Thread t2 = new Thread(new Runnable() {
					@SuppressWarnings("deprecation")
					public void run() {
						try {
							Server server = new ServerXMLConnector().getConnection();
							ControllerOnline controller = new ControllerOnlineImpl(server);
							Client client =  new ClientImpl(controller);
							controller.setClient(client);
							String username = txtUsernameSignUp.getText();
							String password = pfPasswordSignUp.getText();
							String email = txtEmail.getText();
							
							byte[] imageBytes = null;
							if(userImage != null) {
								//u userImage se cuva skalirana slika!!!
								//uzimaju se bajtovi iz takve skalirane slike na sledeci nacin:
								BufferedImage bImage = new BufferedImage(userImage.getWidth(null), userImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
								Graphics2D bImageGraphics = bImage.createGraphics();
								bImageGraphics.drawImage(userImage, null, null);
								RenderedImage rImage = (RenderedImage)bImage;
								
								ByteArrayOutputStream baos = new ByteArrayOutputStream();
								ImageIO.write(rImage, userImageExtension, baos);
								imageBytes = baos.toByteArray();
							}
							
							if(!controller.signIn(username, password, email, imageBytes)) {	
								cs.stopAnimation();
								CometDialog cd = new CometDialog("warning", "Account already exists!");
								cd.setVisible(true);
								return;
							}
							cs.stopAnimation();
							CometDialog cd = new CometDialog("info", "Acount created succesfully!");
							cd.setVisible(true);
							txtUsernameSignUp.setText("Enter your username");
							txtEmail.setText("Enter your email");
							pfPasswordSignUp.setText("markojelep");
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} finally {
							cs.stopAnimation();
						}
					}
				});
				t1.start();
				t2.start();
			}	
		});
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
				if(pfPasswordSignUp.getText().equals(""))
					pfPasswordSignUp.setText("password");
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
		
		lblImage = new JLabel("");
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(296, 81, 120, 120);
		lblImage.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(52, 52, 52)));
		lblImage.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/personImage.jpg"))
				.getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT)));
		signUpInputs.add(lblImage);
		
		JButton btnLoadImage = GUIFactory.createCometFlatButton(
				"Load image",
				new Color(52, 52, 52),
				Color.LIGHT_GRAY);
		btnLoadImage.setBounds(270, 212, 172, 47);
		btnLoadImage.setBorderPainted(false);
		btnLoadImage.addMouseListener(buttonColorChanger);
		
		btnLoadImage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg");
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(filter);
				fc.setAcceptAllFileFilterUsed(false);
				
				int retVal = fc.showOpenDialog(fc);
				if(retVal == JFileChooser.APPROVE_OPTION) {
					String filePath = fc.getSelectedFile().getAbsolutePath();
					userImageExtension = filePath.substring(filePath.length()-3, filePath.length());
					BufferedImage img = null;
					try {
						img = ImageIO.read(new File(filePath));
					}catch(Exception ex) {
						ex.printStackTrace();
					}
					Image image = img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
					userImage = img.getScaledInstance(187, 187, Image.SCALE_SMOOTH);	//velicina slike za CometUserDialog jer je veca tamo
					lblImage.setIcon(new ImageIcon(image));
				}
			}
		});
		
		signUpInputs.add(btnLoadImage);
		
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
				if(txtUsername.getText().equals("")) 
					txtUsername.setText("Enter your username");
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
		
		JLabel lblSignIn = new JLabel("Log in with:");
		lblSignIn.setForeground(Color.LIGHT_GRAY);
		lblSignIn.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblSignIn.setBounds(322, 180, 125, 21);
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
		
		JButton btnLogIn = GUIFactory.createCometFlatButton(
				"Log in",
				new Color(52, 52, 52),
				Color.LIGHT_GRAY);
		btnLogIn.addMouseListener(buttonColorChanger);
		btnLogIn.addActionListener(new LoginListener());
		btnLogIn.setBounds(31, 212, 253, 47);
		btnLogIn.setBorderPainted(false);
		inputsHolder.add(btnLogIn);
	
	}
	
	private void disposeFrame() {
		this.dispose();
	}
	
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CometSplashScreen cs = new CometSplashScreen("Loging in...");
			Thread t1 = new Thread(cs);
			Thread t2 = new Thread(new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					try {
						Server server = new ServerXMLConnector().getConnection();
						ControllerOnline controller = new ControllerOnlineImpl(server);
						Client client =  new ClientImpl(controller);
						controller.setClient(client);
						if(!controller.logIn(txtUsername.getText(), pfPassword.getText())) {	
							cs.stopAnimation();
							CometDialog cd = new CometDialog("warning", "Wrong username or password!");
							cd.setVisible(true);
							return;
						}
						Thread.sleep(1000);
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								LobbyFrame lf = new LobbyFrame(controller);
								cs.stopAnimation();
								lf.setVisible(true);
							}
						});
						disposeFrame();
					} 
					catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						cs.stopAnimation();
					}
				}
			});
			t1.start();
			t2.start();
		}
		
	}
}
