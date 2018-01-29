package application;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import communication.Client;
import communication.Server;
import view.View;
import javax.swing.JList;
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

public class LobbyFrame extends JFrame implements View {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;

	private Client client;
	private Server server;
	
	public LobbyFrame() {
		initialize();
	}
	
	private void initialize() {
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
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 503, 216, 2);
		messagePane.add(separator_3);
		
		textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBackground(new Color(190, 190, 190));
		textArea.setForeground(new Color(230, 230, 250));
		textArea.setBounds(10, 85, 216, 231);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFocusable(false);
		textArea.setForeground(new Color(32, 32, 32));
		
		JScrollPane textAreaScroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		textAreaScroll.setBounds(10, 85, 216, 364);
		messagePane.add(textAreaScroll);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 11, 216, 2);
		messagePane.add(separator_1);
		
		JButton btnSend = new JButton("Send");
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
		textField.setBackground(new Color(160, 160, 160));
		messagePane.add(textField);
		textField.setColumns(10);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(10, 62, 216, 2);
		messagePane.add(separator_5);
		
		JPanel documentPane = new JPanel();
		documentPane.setBounds(0, 0, 559, 600);
		documentPane.setBackground(new Color(0, 171, 169));
		contentPane.add(documentPane);
		documentPane.setLayout(null);
		
		JLabel lblAlreadyOpenDocuments = new JLabel("Available documents");
		lblAlreadyOpenDocuments.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblAlreadyOpenDocuments.setBounds(164, 24, 219, 27);
		lblAlreadyOpenDocuments.setForeground(new Color(244, 244, 255));
		documentPane.add(lblAlreadyOpenDocuments);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 539, 2);
		documentPane.add(separator);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 62, 539, 2);
		documentPane.add(separator_4);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBounds(10, 516, 539, 46);
		documentPane.add(panel);
		panel.setOpaque(false);
		panel.setLayout(new GridLayout(1, 2));
		
		JButton btnCreateDocument = new JButton("Create Document");
		btnCreateDocument.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnCreateDocument.setBackground(new Color(0, 131, 129));
		btnCreateDocument.setBorderPainted(false);
		btnCreateDocument.setFocusPainted(false);
		btnCreateDocument.setContentAreaFilled(false);
		btnCreateDocument.setOpaque(true);
		btnCreateDocument.setForeground(new Color(244, 244, 255));
		panel.add(btnCreateDocument);
		
		JButton btnOpenDocument = new JButton("Open Document");
		btnOpenDocument.setFont(new Font("Courier New", Font.PLAIN, 18));
		btnOpenDocument.setBackground(new Color(0, 131, 129));
		btnOpenDocument.setBorderPainted(false);
		btnOpenDocument.setFocusPainted(false);
		btnOpenDocument.setContentAreaFilled(false);
		btnOpenDocument.setOpaque(true);
		btnOpenDocument.setForeground(new Color(244, 244, 255));
		panel.add(btnOpenDocument);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 503, 539, 2);
		documentPane.add(separator_2);
		
		JList list = new JList();
		list.setBounds(10, 86, 539, 406);
		list.setBackground(new Color(175, 238, 238));
		
		JScrollPane listBoxScroll = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
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
