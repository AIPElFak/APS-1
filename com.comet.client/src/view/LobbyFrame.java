package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controller.ControllerOnline;
import guicomponents.GUIFactory;
import utilities.DocumentList;
import utilities.DocumentRemote;
import view.View;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LobbyFrame extends JFrame implements View {

	private JPanel contentPane;
	private JTextField txtChatInput;
	private JTextArea textArea;
	private JTextField txtSearch;
	private JList<DocumentRemote> list;
	private DefaultListModel<DocumentRemote> dlm;
	
	private ControllerOnline controller;
	
	public LobbyFrame(ControllerOnline cntrl) {
		controller = cntrl;
		controller.setView(this);
		initialize();
	}
	
	private void initialize() {
		setTitle("Comet");
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null);
		
		MouseAdapter docBtnColorChanger = GUIFactory.createButtonColorChanger
		(
				new Color(1, 96, 181),
				new Color(1, 86, 171),
				new Color(1, 76, 161)
		);
			
		
		JPanel messagePane = new JPanel();
		messagePane.setBackground(new Color(60, 60, 60));
		messagePane.setBounds(758, 0, 236, 571);
		contentPane.add(messagePane);
		messagePane.setLayout(null);
		
		JSeparator botChatSeparator = new JSeparator();
		botChatSeparator.setBounds(10, 503, 216, 2);
		messagePane.add(botChatSeparator);
		
		textArea = new JTextArea();
		textArea.setColumns(10);
		textArea.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 2, 0, 2, new Color(180, 180, 180)),
				new EmptyBorder(5, 10, 5, 10)));
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		textArea.setBackground(new Color(60, 60, 60));
		textArea.setForeground(new Color(150, 150, 150));
		textArea.setBounds(10, 85, 216, 231);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFocusable(false);
		
		JScrollPane textAreaScroll = new JScrollPane(
			textArea,
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		textAreaScroll.setBounds(10, 75, 216, 357);
		textAreaScroll.setBorder(null);
		messagePane.add(textAreaScroll);
		
		JSeparator topChatUpperSeparator = new JSeparator();
		topChatUpperSeparator.setBounds(10, 11, 216, 2);
		messagePane.add(topChatUpperSeparator);
		
		JButton btnSend = GUIFactory.createCometFlatButton(
				"Send",
				new Color(32, 32, 32),
				new Color(244, 244, 255));
		btnSend.addMouseListener(GUIFactory.createButtonColorChanger(
			new Color(32, 32, 32),
			new Color(12, 12, 12),
			new Color(2, 2, 2))
		);
		btnSend.addActionListener(new ActionHandler());
		btnSend.addKeyListener(new KeyHandler());
		btnSend.setBounds(10, 516, 216, 46);
		btnSend.setForeground(new Color(244, 244, 255));
		btnSend.setBorderPainted(false);
		messagePane.add(btnSend);
		
		JLabel lblLobbyChat = new JLabel("Lobby chat");
		lblLobbyChat.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblLobbyChat.setBounds(63, 24, 122, 27);
		lblLobbyChat.setForeground(new Color(244, 244, 255));
		messagePane.add(lblLobbyChat);
		
		txtChatInput = new JTextField();
		txtChatInput.setText("Enter your message.");
		txtChatInput.setCaretColor(new Color(238, 238, 255));
		txtChatInput.setForeground(new Color(150, 150, 150));
		txtChatInput.setHorizontalAlignment(SwingConstants.CENTER);
		txtChatInput.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtChatInput.setBounds(10, 471, 216, 27);
		txtChatInput.setBackground(new Color(60, 60, 60));
		txtChatInput.setMargin(new Insets(5, 5, 5, 5));
		txtChatInput.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				txtChatInput.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtChatInput.getText().equals("")) 
					txtChatInput.setText("Enter your message.");
			}
		});
		txtChatInput.setBorder(new EmptyBorder(5, 10, 5, 10));
		messagePane.add(txtChatInput);
		txtChatInput.setColumns(10);
		
		JSeparator topChatLowerSeparator = new JSeparator();
		topChatLowerSeparator.setBounds(10, 62, 216, 2);
		messagePane.add(topChatLowerSeparator);
		
		JLabel lblMessageInput = new JLabel("Message input:");
		lblMessageInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessageInput.setForeground(new Color(244, 244, 255));
		lblMessageInput.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblMessageInput.setBounds(10, 443, 216, 35);
		messagePane.add(lblMessageInput);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 440, 216, 2);
		messagePane.add(separator_1);
		
		JPanel documentPane = new JPanel();
		documentPane.setBounds(0, 0, 758, 571);
		documentPane.setBackground(new Color(21, 126, 251));
		contentPane.add(documentPane);
		documentPane.setLayout(null);
		
		JLabel lblAlreadyOpenDocuments = new JLabel("Available documents");
		lblAlreadyOpenDocuments.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblAlreadyOpenDocuments.setBounds(298, 24, 219, 27);
		lblAlreadyOpenDocuments.setForeground(new Color(244, 244, 255));
		documentPane.add(lblAlreadyOpenDocuments);
		
		JSeparator topDocUpperSeparator = new JSeparator();
		topDocUpperSeparator.setBounds(10, 11, 738, 2);
		documentPane.add(topDocUpperSeparator);
		
		JSeparator topDocLowerSeparator = new JSeparator();
		topDocLowerSeparator.setBounds(10, 62, 738, 2);
		documentPane.add(topDocLowerSeparator);
		
		JPanel docButtonsHolder = new JPanel();
		docButtonsHolder.setBorder(null);
		docButtonsHolder.setBounds(10, 516, 738, 46);
		documentPane.add(docButtonsHolder);
		docButtonsHolder.setOpaque(false);
		docButtonsHolder.setLayout(new GridLayout(1, 3));
		
		JButton btnCreateDocument = GUIFactory.createCometFlatButton(
				"Create document",
				new Color(1, 91, 181),
				new Color(244, 244, 255));
		btnCreateDocument.addMouseListener(docBtnColorChanger);
		btnCreateDocument.setBorder(new LineBorder(new Color(21, 126, 251)));
		docButtonsHolder.add(btnCreateDocument);
		
		JButton btnOpenDocument = GUIFactory.createCometFlatButton(
				"Open document",
				new Color(1, 91, 181),
				new Color(244, 244, 181));
		btnOpenDocument.addMouseListener(docBtnColorChanger);
		btnOpenDocument.setBorder(new LineBorder(new Color(21, 126, 251)));
		docButtonsHolder.add(btnOpenDocument);
		
		JButton btnDeleteDocument = GUIFactory.createCometFlatButton(
				"Delete document",
				new Color(1, 91, 181),
				new Color(244, 244, 181));
		btnDeleteDocument.setBorder(new LineBorder(new Color(21, 126, 251)));
		docButtonsHolder.add(btnDeleteDocument);
		
		JSeparator botDocSeparator = new JSeparator();
		botDocSeparator.setBounds(10, 503, 738, 2);
		documentPane.add(botDocSeparator);
		
		list = new JList<DocumentRemote>();
		list.setCellRenderer(GUIFactory.createCometListRenderer());
		list.setBounds(10, 86, 539, 406);
		list.setBackground(new Color(175, 238, 238));
		
		JScrollPane listBoxScroll = new JScrollPane(
			list,
			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		listBoxScroll.setBorder(null);
		listBoxScroll.setBounds(10, 160, 738, 328);
		documentPane.add(listBoxScroll);
		
		txtSearch = new JTextField();
		txtSearch.setHorizontalAlignment(SwingConstants.CENTER);
		txtSearch.setForeground(new Color(1, 91, 181));
		txtSearch.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtSearch.setColumns(10);
		txtSearch.setBorder(null);
		txtSearch.setBackground(new Color(175, 238, 238));
		txtSearch.setBounds(10, 75, 455, 32);
		documentPane.add(txtSearch);
		
		JPanel searchButtonsHolder = new JPanel();
		searchButtonsHolder.setBackground(new Color(21, 126, 251));
		searchButtonsHolder.setBounds(476, 75, 272, 32);
		documentPane.add(searchButtonsHolder);
		searchButtonsHolder.setLayout(new GridLayout(1, 3));
		
		JButton btnSearch = GUIFactory.createCometFlatButton(
				"",
				new Color(1, 91, 181),
				new Color(1, 71, 161));
		btnSearch.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/searchGlyphWhite.png"))
				.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT)));
		btnSearch.setBorder(new LineBorder(new Color(21, 126, 251)));
		btnSearch.addMouseListener(docBtnColorChanger);
		searchButtonsHolder.add(btnSearch);
		
		JButton btnProfile = GUIFactory.createCometFlatButton(
				"",
				new Color(1, 91, 181),
				new Color(1, 71, 161));
		btnProfile.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/personGlyphWhite.png"))
				.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT)));
		btnProfile.setBorder(new LineBorder(new Color(21, 126, 251)));
		btnProfile.addMouseListener(docBtnColorChanger);
		searchButtonsHolder.add(btnProfile);
		
		JButton btnInfo = GUIFactory.createCometFlatButton(
				"",
				new Color(1, 91, 181),
				new Color(1, 71, 161));
		btnInfo.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/infoGlyphWhite.png"))
				.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT)));
		btnInfo.setBorder(new LineBorder(new Color(21, 126, 251)));
		btnInfo.addMouseListener(docBtnColorChanger);
		searchButtonsHolder.add(btnInfo);
		
		JPanel listHeader = new JPanel();
		listHeader.setBounds(10, 118, 738, 32);
		documentPane.add(listHeader);
		listHeader.setBackground(new Color(21, 126, 251));
		listHeader.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblDocumentName = new JLabel("Document name");
		lblDocumentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocumentName.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblDocumentName.setForeground(new Color(238, 238, 255));
		lblDocumentName.setBorder(new MatteBorder(1, 0, 1, 0, new Color(238, 238, 255)));
		listHeader.add(lblDocumentName);
		
		JLabel lblProgrammingLanguage = new JLabel("Programming language");
		lblProgrammingLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblProgrammingLanguage.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblProgrammingLanguage.setForeground(new Color(238, 238, 255));
		lblProgrammingLanguage.setBorder(new MatteBorder(1, 0, 1, 0, new Color(238, 238, 255)));
		listHeader.add(lblProgrammingLanguage);
		
		JLabel lblPasswordProtection = new JLabel("Password protection");
		lblPasswordProtection.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordProtection.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblPasswordProtection.setForeground(new Color(238, 238, 255));
		lblPasswordProtection.setBorder(new MatteBorder(1, 0, 1, 0, new Color(238, 238, 255)));
		listHeader.add(lblPasswordProtection);
		
		controller.displayAllAvailableDocuments();
		
	}

	public void appendMessage(String username, String text) {
		textArea.append(username + ": " + text + "\n\n");
	}
	
	private class KeyHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == e.VK_ENTER) {
				textArea.append("Me: " + txtChatInput.getText() + "\n\n");
				controller.sendLobbyMessage(txtChatInput.getText());
				txtChatInput.setText("");
			}
		}
	}
	
	
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			textArea.append("Me: " + txtChatInput.getText() + "\n\n");
			controller.sendLobbyMessage(txtChatInput.getText());
			txtChatInput.setText("");
		}
	}


	@Override
	public void appendDocumentMessage(String username, String message) {
		return;
	}

	@Override
	public void appendLobyMessage(String username, String message) {
		textArea.append(username + ": " + message + "\n\n");
	}

	@Override
	public void disposeView() {
		this.dispose();
	}

	@Override
	public void showRowColPosition(int row, int col) {
		return;
	}

	@Override
	public void setDocumentName(String name) {
		return;
	}

	@Override
	public void updateContent(String content) {
		return;
	}

	@Override
	public void setCaretLocation(int x, int y) {
		return;
	}

	@Override
	public void updateStatisics(String statistics) {
		return;
	}

	@Override
	public void find(String text) {
		return;
	}

	@Override
	public void replace(String text, String replace) {
		return;
	}

	@Override
	public String getDocumentContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showAvailableDocument(DocumentList docLst) {
		dlm = new DefaultListModel<DocumentRemote>();
		try {
			for(DocumentRemote d : docLst.getAllAvailableDocument()) {
				dlm.addElement(d);
			}
			list.setModel(dlm);
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
