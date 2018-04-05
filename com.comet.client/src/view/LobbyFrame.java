package view;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import communication.Client;
import controller.ControllerOnline;
import guicomponents.GUIFactory;
import languages.LanguageManager;
import model.ModelImpl;
import utilities.DocumentRemote;
import utilities.UserRemote;
import view.View;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JSeparator;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LobbyFrame extends JFrame implements View {

	private JPanel contentPane;
	private JTextField txtChatInput;
	private JTextArea textArea;
	private JTextField txtSearch;
	private JList<DocumentRemote> list;
	private DefaultListModel<DocumentRemote> dlm;
	
	private boolean documentOpened;
	
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null);
		
		JFrame self = this;
		
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
		textArea.setForeground(new Color(238, 238, 255));
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
				new Color(92, 92, 92),
				new Color(244, 244, 255));
		btnSend.addMouseListener(GUIFactory.createButtonColorChanger(
				new Color(92, 92, 92),
				new Color(102, 102, 102),
				new Color(112, 112, 112))
		);
		btnSend.addActionListener(new ActionHandler());
		btnSend.addKeyListener(new KeyHandler());
		btnSend.setBounds(10, 516, 216, 46);
		btnSend.setForeground(new Color(244, 244, 255));
		btnSend.setBorderPainted(false);
		messagePane.add(btnSend);
		
		JLabel lblLobbyChat = new JLabel("Lobby chat");
		lblLobbyChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblLobbyChat.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblLobbyChat.setBounds(10, 24, 216, 27);
		lblLobbyChat.setForeground(new Color(244, 244, 255));
		messagePane.add(lblLobbyChat);
		
		txtChatInput = new JTextField();
		txtChatInput.setText("Enter your message");
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
		lblAlreadyOpenDocuments.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlreadyOpenDocuments.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblAlreadyOpenDocuments.setBounds(10, 24, 738, 27);
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
		btnCreateDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateDoucmentDialog cd = new CreateDoucmentDialog(controller);
				cd.setVisible(true);
			}
		});
		docButtonsHolder.add(btnCreateDocument);
		
		JButton btnOpenDocument = GUIFactory.createCometFlatButton(
				"Open document",
				new Color(1, 91, 181),
				new Color(244, 244, 181));
		btnOpenDocument.addMouseListener(docBtnColorChanger);
		btnOpenDocument.setBorder(new LineBorder(new Color(21, 126, 251)));
		btnOpenDocument.addActionListener(new ActionListener() {
			private String documentPassword = null;
			
			public void actionPerformed(ActionEvent arg0) {
				DocumentRemote doc = list.getSelectedValue();
				if(doc == null) return;
				
				try {
					if(doc.isPasswordProtected()) {
						documentPassword = JOptionPane.showInputDialog("Enter password:");
						if(documentPassword == null) return;
					}
				} catch (HeadlessException | RemoteException e1) {
					e1.printStackTrace();
				}
				CometSplashScreen cs = new CometSplashScreen("Opening document...");
				cs.setVisible(true);
				new Thread(cs).start();
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(1500);
							try {
								LanguageManager.getInstance().setLanguageByType(
									doc.getType().toLowerCase()
								);
							} catch (RemoteException e) {
								return;
							}
							EditorFrameOnline editor = new EditorFrameOnline(controller);
							controller.setView(editor);
							controller.setModel(new ModelImpl());
							CometDialog cd;
							if(!controller.openDocument(doc,documentPassword)) {
								cs.stopAnimation();
								cd = new CometDialog("warning", "Password incorect!");
								cd.setVisible(true);
								//return;
							}
							else {
								editor.setVisible(true);
								documentOpened = true;
								self.dispose();
								cs.stopAnimation();
							}
						} catch (InterruptedException e) {}
					}
				}).start();
			}
		});
		docButtonsHolder.add(btnOpenDocument);
		
		JButton btnDeleteDocument = GUIFactory.createCometFlatButton(
				"Delete document",
				new Color(1, 91, 181),
				new Color(244, 244, 181));
		btnDeleteDocument.setBorder(new LineBorder(new Color(21, 126, 251)));
		btnDeleteDocument.addMouseListener(docBtnColorChanger);
		btnDeleteDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DocumentRemote doc = list.getSelectedValue();
				if(doc == null) return;
				int selectedOption = JOptionPane.showConfirmDialog(null, 
			                        "Are you sure to delete this document?", 
			                        "Choose", 
			                        JOptionPane.YES_NO_OPTION);
				if(selectedOption != JOptionPane.YES_OPTION) return;
				CometSplashScreen cs = new CometSplashScreen("Deleting document...");
				cs.setVisible(true);
				new Thread(cs).start();
				new Thread(new Runnable() {
					public void run() {
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {}

							cs.stopAnimation();
							CometDialog cd;
							if(controller.deleteDocument(doc)) {
								cd = new CometDialog("info", "Document deleted.");
								cd.setVisible(true);
								
								try {
									Thread.sleep(700);
								} catch (InterruptedException e) {}
								cd.dispose();
							}else {
								cd = new CometDialog("warning", "You can not delete this document!");
								cd.setVisible(true);
								try {
									Thread.sleep(1500);
								} catch (InterruptedException e) {}
								cd.dispose();
							}
					}
				}).start();
			}
		});
		docButtonsHolder.add(btnDeleteDocument);
		
		JSeparator botDocSeparator = new JSeparator();
		botDocSeparator.setBounds(10, 503, 738, 2);
		documentPane.add(botDocSeparator);
		
		list = new JList<DocumentRemote>();
		list.setCellRenderer(GUIFactory.createCometListRenderer());
		list.setBounds(10, 86, 539, 406);
		list.setBackground(new Color(175, 238, 238));
		list.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
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
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				searchForDocuments(e);
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				searchForDocuments(e);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				searchForDocuments(e);
			}
			private void searchForDocuments(DocumentEvent e) {
				controller.searchDocuments(txtSearch.getText());
			}
		});
		documentPane.add(txtSearch);
		
		JPanel searchButtonsHolder = new JPanel();
		searchButtonsHolder.setBackground(new Color(21, 126, 251));
		searchButtonsHolder.setBounds(476, 75, 272, 32);
		documentPane.add(searchButtonsHolder);
		searchButtonsHolder.setLayout(new GridLayout(1, 3));
		
		JButton btnRefresh = GUIFactory.createCometFlatButton(
				"",
				new Color(1, 91, 181),
				new Color(1, 71, 161));
		btnRefresh.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/refresh.png"))
				.getImage().getScaledInstance(19, 19, Image.SCALE_DEFAULT)));
		btnRefresh.setBorder(new LineBorder(new Color(21, 126, 251)));
		btnRefresh.addMouseListener(docBtnColorChanger);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.displayAllAvailableDocuments();
			}
		});
		searchButtonsHolder.add(btnRefresh);
		
		JButton btnProfile = GUIFactory.createCometFlatButton(
				"",
				new Color(1, 91, 181),
				new Color(1, 71, 161));
		btnProfile.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/personGlyphWhite.png"))
				.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT)));
		btnProfile.setBorder(new LineBorder(new Color(21, 126, 251)));
		btnProfile.addMouseListener(docBtnColorChanger);
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CometUserDialog cud = new CometUserDialog(controller, self);
				cud.setVisible(true);
			}
		});
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
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CometDialog cd = new CometDialog("info", "Number of available"
						+ " documents: " + dlm.size());
				cd.setVisible(true);
			}
		});
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
		
		JLabel lblProgrammingLanguage = new JLabel("Document type");
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
		
		addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				if(documentOpened) return;
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						LoginFrame lf = new LoginFrame();
						lf.setVisible(true);
					}
				});
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

					@Override
					public void run() {
						CometSplashScreen cs = new CometSplashScreen("Loading available documents");
						new Thread(cs).start();
						controller.displayAllAvailableDocuments();
						try {
							Thread.sleep(800);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						cs.stopAnimation();
					}
					
				}).start();;
			}});
	}

	public void appendMessage(String username, String text) {
		textArea.append(username + ": " + text + "\n\n");
	}
	
	private class KeyHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == e.VK_ENTER) {
				textArea.append("Me: " + txtChatInput.getText() + "\n\n");
				controller.sendLobbyMessage(txtChatInput.getText());
				txtChatInput.setText("Enter your message");
			}
		}
	}
	
	
	private class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			textArea.append("Me: " + txtChatInput.getText() + "\n\n");
			controller.sendLobbyMessage(txtChatInput.getText());
			txtChatInput.setText("Enter your message");
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
	public void showAvailableDocument(ArrayList<DocumentRemote> docLst) {
		dlm = new DefaultListModel<DocumentRemote>();
		for(DocumentRemote d : docLst) {
			dlm.addElement(d);
		}
		list.setModel(dlm);
	}

	@Override
	public void updateCollaborators(List<UserRemote> collabs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recvDocUpdate(String type, String text, int length, int location) {
		return;
	}

	@Override
	public void disableEditor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableEditor() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear(int length, int location) {
		// TODO Auto-generated method stub
		
	}
}
