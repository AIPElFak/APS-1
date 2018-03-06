package application;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import communication.Client;
import communication.Server;
import guicomponents.ButtonColorChanger;
import view.View;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LobbyFrame extends JFrame implements View {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	private Client client;
	private Server server;
	
	public LobbyFrame() {
		setTitle("Comet");
		initialize();
	}
	
	private void initialize() {
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null);
		
		JPanel messagePane = new JPanel();
		messagePane.setBackground(Color.DARK_GRAY);
		messagePane.setBounds(558, 0, 236, 600);
		contentPane.add(messagePane);
		messagePane.setLayout(null);
		
		JSeparator botChatSeparator = new JSeparator();
		botChatSeparator.setBounds(10, 503, 216, 2);
		messagePane.add(botChatSeparator);
		
		textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBackground(new Color(100, 100, 100));
		textArea.setForeground(new Color(230, 230, 250));
		textArea.setBounds(10, 85, 216, 231);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFocusable(false);
		textArea.setForeground(new Color(32, 32, 32));
		
		JScrollPane textAreaScroll = new JScrollPane(
			textArea,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		textAreaScroll.setBounds(10, 85, 216, 364);
		textAreaScroll.setBorder(null);
		messagePane.add(textAreaScroll);
		
		JSeparator topChatUpperSeparator = new JSeparator();
		topChatUpperSeparator.setBounds(10, 11, 216, 2);
		messagePane.add(topChatUpperSeparator);
		
		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new ButtonColorChanger(
			new Color(92, 92, 92),
			new Color(102, 102, 102),
			new Color(112, 112, 112))
		);
		btnSend.addActionListener(new ActionHandler());
		btnSend.addKeyListener(new KeyHandler());
		btnSend.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnSend.setBackground(new Color(92, 92, 92));
		btnSend.setBounds(10, 516, 216, 46);
		btnSend.setBorderPainted(false);
		btnSend.setFocusPainted(false);
		btnSend.setContentAreaFilled(false);
		btnSend.setOpaque(true);
		btnSend.setForeground(new Color(244, 244, 255));
		messagePane.add(btnSend);
		
		JLabel lblLobbyChat = new JLabel("Lobby chat");
		lblLobbyChat.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblLobbyChat.setBounds(63, 24, 122, 27);
		lblLobbyChat.setForeground(new Color(244, 244, 255));
		messagePane.add(lblLobbyChat);
		
		textField = new JTextField();
		textField.setForeground(new Color(230, 230, 250));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Courier New", Font.PLAIN, 13));
		textField.setBounds(10, 460, 216, 32);
		textField.setBackground(new Color(110, 110, 110));
		messagePane.add(textField);
		textField.setColumns(10);
		
		JSeparator topChatLowerSeparator = new JSeparator();
		topChatLowerSeparator.setBounds(10, 62, 216, 2);
		messagePane.add(topChatLowerSeparator);
		
		JPanel documentPane = new JPanel();
		documentPane.setBounds(0, 0, 559, 600);
		documentPane.setBackground(new Color(21, 126, 251));
		contentPane.add(documentPane);
		documentPane.setLayout(null);
		
		JLabel lblAlreadyOpenDocuments = new JLabel("Available documents");
		lblAlreadyOpenDocuments.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblAlreadyOpenDocuments.setBounds(183, 24, 219, 27);
		lblAlreadyOpenDocuments.setForeground(new Color(244, 244, 255));
		documentPane.add(lblAlreadyOpenDocuments);
		
		JSeparator topDocUpperSeparator = new JSeparator();
		topDocUpperSeparator.setBounds(10, 11, 539, 2);
		documentPane.add(topDocUpperSeparator);
		
		JSeparator topDocLowerSeparator = new JSeparator();
		topDocLowerSeparator.setBounds(10, 62, 539, 2);
		documentPane.add(topDocLowerSeparator);
		
		JPanel docButtonsHolder = new JPanel();
		docButtonsHolder.setBorder(null);
		docButtonsHolder.setBounds(10, 516, 539, 46);
		documentPane.add(docButtonsHolder);
		docButtonsHolder.setOpaque(false);
		docButtonsHolder.setLayout(new GridLayout(1, 2));
		
		JButton btnCreateDocument = new JButton("Create Document");
		btnCreateDocument.addMouseListener(new ButtonColorChanger(
			new Color(1, 96, 171),
			new Color(1, 86, 171),
			new Color(1, 76, 171))
		);
		btnCreateDocument.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnCreateDocument.setBackground(new Color(1, 96, 181));
		btnCreateDocument.setBorderPainted(false);
		btnCreateDocument.setFocusPainted(false);
		btnCreateDocument.setContentAreaFilled(false);
		btnCreateDocument.setOpaque(true);
		btnCreateDocument.setForeground(new Color(244, 244, 255));
		docButtonsHolder.add(btnCreateDocument);
		
		JButton btnOpenDocument = new JButton("Open Document");
		btnOpenDocument.addMouseListener(new ButtonColorChanger(
			new Color(1, 96, 171),
			new Color(1, 86, 171),
			new Color(1, 76, 171))
		);
		btnOpenDocument.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnOpenDocument.setBackground(new Color(1, 96, 181));
		btnOpenDocument.setBorderPainted(false);
		btnOpenDocument.setFocusPainted(false);
		btnOpenDocument.setContentAreaFilled(false);
		btnOpenDocument.setOpaque(true);
		btnOpenDocument.setForeground(new Color(244, 244, 255));
		docButtonsHolder.add(btnOpenDocument);
		
		JSeparator botDocSeparator = new JSeparator();
		botDocSeparator.setBounds(10, 503, 539, 2);
		documentPane.add(botDocSeparator);
		
		JList list = new JList();
		list.setBounds(10, 86, 539, 406);
		list.setBackground(new Color(175, 238, 238));
		
		JScrollPane listBoxScroll = new JScrollPane(
			list,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		listBoxScroll.setBorder(null);
		listBoxScroll.setBounds(10, 85, 539, 403);
		documentPane.add(listBoxScroll);
	}

	public void appendMessage(String username, String text) {
		textArea.append(username + ": " + text + "\n");
	}
	
	public void addClient (Client client) {
		this.client = client;
	}
	
	public void addServer (Server server) {
		this.server = server;
	}
	
	private void sendLobbyMessage() {
		try {
			textArea.append("Me: " + textField.getText() + "\n");
			server.lobbyBroadcast(textField.getText(), client);
			server.logActivity(client.getUserData().getUsername() + " logged message!");
			textField.setText("");
		} 
		catch (RemoteException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	private class KeyHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == e.VK_ENTER) sendLobbyMessage();
		}
	}
	
	
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			sendLobbyMessage();
		}
	}
}
