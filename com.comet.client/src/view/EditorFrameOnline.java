package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import controller.ClipboardManager;
import controller.CommandUndoRedo;
import controller.CommandUndoRedoDistributed;
import controller.ControllerOnline;
import controller.UndoRedoManager;
import guicomponents.GUIFactory;
import languages.LanguageManager;
import languages.SymbolTable;
import utilities.DocumentRemote;
import utilities.UserRemote;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JToolBar;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;

public class EditorFrameOnline extends JFrame implements View {

	private JPanel contentPane;
	private JTextField txtEnterTextHere;
	private ControllerOnline controller;
	private JFrame self;
	private JTextPane textPane;
	private JList<UserRemote> collaboartorsList;
	private DefaultListModel<UserRemote> listModel;
	private JLabel lblDocumentName;
	private JTextArea textArea;
	
	private OnlineDocumentListener docListener;

	public EditorFrameOnline(ControllerOnline cntrl) {
		setTitle("Comet");
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		
		controller = cntrl;
		
		JFrame self = this;
		
		MouseAdapter toolBarColorChanger =  GUIFactory.createButtonColorChanger
		(
			new Color(60, 60, 60),
			new Color(60, 60, 60),
			new Color(60, 60, 60)
		);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1024, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LobbyFrame lf = new LobbyFrame(controller);
				controller.setView(lf);
				self.dispose();
				lf.setVisible(true);
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		mnFile.addSeparator();
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mntmUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UndoRedoManager.getInstance().undo();
			}
		});
		mnEdit.add(mntmUndo);
		
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mntmRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UndoRedoManager.getInstance().redo();
			}
		});
		mnEdit.add(mntmRedo);
		
		mnEdit.addSeparator();
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textPane.getSelectedText() == null) return;
				ClipboardManager.getInstance().setClipboardContents(textPane.getSelectedText());
				int i = textPane.getSelectionStart();
				int j = textPane.getSelectionEnd();
				try {
					textPane.getDocument().remove(i, j - i);
				} catch (BadLocationException e) {}
			}
		});
		mnEdit.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPane.getSelectedText() == null) return;
				ClipboardManager.getInstance().setClipboardContents(textPane.getSelectedText());
			}
		});
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = ClipboardManager.getInstance().getClipboardContents();
				int i = textPane.getCaretPosition();
				try {
					textPane.getDocument().insertString(i, text, null);
				} catch (BadLocationException e1) {}
			}
		});
		mnEdit.add(mntmPaste);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textPane.getSelectedText() == null) return;
				int i = textPane.getSelectionStart();
				int j = textPane.getSelectionEnd();
				try {
					textPane.getDocument().remove(i, j - i);
				} catch (BadLocationException ex) {}
				
			}
		});
		mnEdit.add(mntmDelete);
		
		mnEdit.addSeparator();
		
		JMenuItem mntmFindReplace = new JMenuItem("Find / Replace");
		mntmFindReplace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FindReplaceDialog frd = new FindReplaceDialog(self, controller);
				int x = frd.getX() - 170;
				int y = frd.getY();
				frd.setLocation(x, y);
				frd.setVisible(true);
			}
		});
		mnEdit.add(mntmFindReplace);
		
		JMenu menu = new JMenu("Settings");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("Add language definition");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddLanguageDialog ad = new AddLanguageDialog();
				ad.setVisible(true);
			}
		});
		menu.add(menuItem);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolbar = new JToolBar();
		toolbar.setBorder(null);
		toolbar.setOrientation(JToolBar.VERTICAL);
		toolbar.setBackground(new Color(60, 60, 60));
		toolbar.setForeground(new Color(230, 230, 250));
		toolbar.setFloatable(false);
		panel.add(toolbar, BorderLayout.WEST);
		
		JLabel topLabelSeparator = new JLabel(" ");
		toolbar.add(topLabelSeparator);
		
		JButton Open = GUIFactory.createCometFlatButton("", new Color(60,60,60), Color.WHITE);
		Open.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/open.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		Open.setToolTipText("Open a new doucment");
		Open.addMouseListener(toolBarColorChanger);
		Open.setBorderPainted(false);
		Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LobbyFrame lf = new LobbyFrame(controller);
				controller.setView(lf);
				self.dispose();
				lf.setVisible(true);
			}
		});
		toolbar.add(Open);
		
		toolbar.addSeparator();
		toolbar.addSeparator();
		
		JButton Paste = GUIFactory.createCometFlatButton("", new Color(60,60,60), Color.WHITE);
		Paste.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/paste.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		Paste.setToolTipText("Paste");
		Paste.setBorderPainted(false);
		Paste.addMouseListener(toolBarColorChanger);
		toolbar.add(Paste);
		
		JButton Find = GUIFactory.createCometFlatButton("", new Color(60,60,60), Color.WHITE);
		Find.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/search.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		Find.setToolTipText("Find");
		Find.setBorderPainted(false);
		Find.addMouseListener(toolBarColorChanger);
		toolbar.add(Find);
		
		JButton Copy = GUIFactory.createCometFlatButton("", new Color(60,60,60), Color.WHITE);
		Copy.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/copy.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		Copy.setToolTipText("Copy");
		Copy.setBorderPainted(false);
		Copy.addMouseListener(toolBarColorChanger);
		toolbar.add(Copy);
		
		toolbar.addSeparator();
		toolbar.addSeparator();
		toolbar.addSeparator();
		toolbar.addSeparator();
		toolbar.addSeparator();
		toolbar.addSeparator();
		
		JButton SaveVersion = GUIFactory.createCometFlatButton("", new Color(60,60,60), Color.WHITE);
		SaveVersion.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/cloudUpload.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		SaveVersion.setToolTipText("Save version of a document in a cloud");
		SaveVersion.setBorderPainted(false);
		SaveVersion.addMouseListener(toolBarColorChanger);
		toolbar.add(SaveVersion);
		
		JButton PullVersion = GUIFactory.createCometFlatButton("", new Color(60,60,60), Color.WHITE);
		PullVersion.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/cloudDownload.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		PullVersion.setToolTipText("Pull version of a document from cloud");
		PullVersion.addMouseListener(toolBarColorChanger);
		PullVersion.setBorderPainted(false);
		PullVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CometVersionDialog cv = new CometVersionDialog(controller);
				cv.setVisible(true);
			}
		});
		toolbar.add(PullVersion);
		
		JPanel leftSeparator = new JPanel();
		leftSeparator.setBorder(new MatteBorder(0, 2, 0, 2, new Color(130, 130, 130)));
		leftSeparator.setBackground(new Color(180, 180, 180));
		panel.add(leftSeparator, BorderLayout.EAST);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(81, 186, 251)));
		statusPanel.setBackground(new Color(21, 126, 251));
		contentPane.add(statusPanel, BorderLayout.SOUTH);
		statusPanel.setLayout(new GridLayout(1, 3));
		
		JLabel label = new JLabel("Cursor(0, 0)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("Courier New", Font.PLAIN, 13));
		label.setBorder(new EmptyBorder(5, 0, 5, 0));
		statusPanel.add(label);
		
		JLabel label_1 = new JLabel("Number of words: 0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setFont(new Font("Courier New", Font.PLAIN, 13));
		label_1.setBorder(new EmptyBorder(5, 0, 5, 0));
		statusPanel.add(label_1);
		
		JLabel label_2 = new JLabel("<html><body>Copyright &copy Sentic & Marko<body></html>");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.LIGHT_GRAY);
		label_2.setFont(new Font("Courier New", Font.PLAIN, 13));
		label_2.setBorder(new EmptyBorder(5, 0, 5, 0));
		statusPanel.add(label_2);
		
		JPanel editorHolder = new JPanel();
		editorHolder.setLayout(new BorderLayout(0, 0));
		
		textPane = new JTextPane();
		textPane.setForeground(Color.WHITE);
		textPane.setCaretColor(new Color(238,238,255));
		textPane.setDocument(
				GUIFactory.createCometEditorDocument(
						LanguageManager.getInstance().getSymbolTable()));
		textPane.setFont(new Font("Courier New", Font.PLAIN, 16));
		textPane.setSelectionColor(Color.WHITE);
		textPane.setSelectedTextColor(Color.BLACK);
		JScrollPane textScroll = new JScrollPane(textPane);
		JPanel tln = GUIFactory.createTextLineNumber(textPane);
		//textScroll.setRowHeaderView(tln);
		textScroll.setBorder(null);
		textPane.setMargin(new Insets(10, 10, 10, 10));
		textPane.setBackground(new Color(90, 90, 90));
		editorHolder.add(textScroll);
		contentPane.add(editorHolder, BorderLayout.CENTER);
		
		docListener = new OnlineDocumentListener();
		textPane.getDocument().addDocumentListener(docListener);
		
		JPanel documentTitleHolder = new JPanel();
		documentTitleHolder.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(35, 35, 35)));
		documentTitleHolder.setBackground(new Color(50, 50, 50));
		editorHolder.add(documentTitleHolder, BorderLayout.NORTH);
		
		lblDocumentName = new JLabel("Document name");
		lblDocumentName.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblDocumentName.setForeground(new Color(230, 230, 250));
		documentTitleHolder.add(lblDocumentName);
		
		JPanel colaborationHolder = new JPanel();
		contentPane.add(colaborationHolder, BorderLayout.EAST);
		colaborationHolder.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 2, 0, 2, new Color(130, 130, 130)));
		panel_1.setBackground(new Color(180, 180, 180));
		colaborationHolder.add(panel_1, BorderLayout.WEST);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_2.setBackground(new Color(60, 60, 60));
		colaborationHolder.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JLabel lblCollaborators = new JLabel("Collaborators");
		lblCollaborators.setForeground(new Color(230, 230, 250));
		lblCollaborators.setFont(new Font("Courier New", Font.PLAIN, 18));
		panel_2.add(lblCollaborators);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(60, 60, 60));
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(238, 238, 255));
		separator.setBounds(0, 0, 143, 2);
		panel_3.add(separator);
		
		collaboartorsList = new JList<UserRemote>();
		collaboartorsList.setCellRenderer(GUIFactory.createCollabRenderer(collaboartorsList));
		collaboartorsList.setBounds(0, 13, 143, 182);
		collaboartorsList.setCursor(new Cursor(Cursor.HAND_CURSOR));
		JScrollPane collaboratorsHolder = new JScrollPane(
				collaboartorsList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		collaboratorsHolder.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 2, new Color(180, 180, 180)));
		collaboartorsList.setBackground(new Color(60, 60, 60));
		collaboratorsHolder.setBounds(0, 13, 143, 182);
		panel_3.add(collaboratorsHolder);
		
		JLabel lblChat = new JLabel("Chat");
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setForeground(new Color(230, 230, 250));
		lblChat.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblChat.setBounds(0, 211, 143, 19);
		panel_3.add(lblChat);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(238, 238, 255));
		separator_1.setBounds(0, 235, 143, 2);
		panel_3.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 206, 143, 2);
		separator_2.setBackground(new Color(238, 238, 255));
		panel_3.add(separator_2);
		
		textArea = new JTextArea();
		textArea.setForeground(Color.LIGHT_GRAY);
		textArea.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createMatteBorder(0, 2, 0, 2, new Color(180, 180, 180)),
				new EmptyBorder(5, 10, 5, 10)));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font("Courier New", Font.PLAIN, 13));
		JScrollPane chatHolder = new JScrollPane(
			textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
		);
		chatHolder.setBounds(0, 248, 143, 202);
		chatHolder.setBorder(null);
		textArea.setBackground(new Color(60, 60, 60));
		panel_3.add(chatHolder);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(238, 238, 255));
		separator_3.setBounds(0, 461, 143, 2);
		panel_3.add(separator_3);
		
		txtEnterTextHere = new JTextField();
		txtEnterTextHere.setHorizontalAlignment(SwingConstants.CENTER);
		txtEnterTextHere.setFont(new Font("Courier New", Font.PLAIN, 11));
		txtEnterTextHere.setText("Enter text here");
		txtEnterTextHere.setBounds(0, 504, 143, 20);
		panel_3.add(txtEnterTextHere);
		txtEnterTextHere.setBackground(new Color(60, 60, 60));
		txtEnterTextHere.setForeground(Color.LIGHT_GRAY);
		txtEnterTextHere.setColumns(10);
		txtEnterTextHere.setBorder(null);
		txtEnterTextHere.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				txtEnterTextHere.setText("");
			}
			public void focusLost(FocusEvent e) {
				if(txtEnterTextHere.getText().equals("")) 
					txtEnterTextHere.setText("Enter your message.");
			}
		});
		
		JButton btnSend = GUIFactory.createCometFlatButton(
			"Send",
			new Color(92, 92, 92),
			new Color(230, 230, 250)
		);
		btnSend.setBounds(0, 539, 143, 34);
		btnSend.addMouseListener(GUIFactory.createButtonColorChanger(
			new Color(92, 92, 92),
			new Color(102, 102, 102),
			new Color(112, 112, 112))
		);
		btnSend.setBorderPainted(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("Me: " + txtEnterTextHere.getText() + "\n\n");
				controller.sendDocumentMessage(txtEnterTextHere.getText());
				txtEnterTextHere.setText("Enter message here.");
			}
		});
		panel_3.add(btnSend);
		
		JLabel lblMessageInput = new JLabel("Message input:");
		lblMessageInput.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessageInput.setFont(new Font("Courier New", Font.BOLD, 14));
		lblMessageInput.setBounds(0, 486, 143, 19);
		lblMessageInput.setForeground(new Color(238, 238, 255));
		panel_3.add(lblMessageInput);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(238, 238, 255));
		separator_4.setBounds(0, 526, 143, 2);
		panel_3.add(separator_4);
		
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						LobbyFrame lf = new LobbyFrame(controller);
						controller.setView(lf);
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
				UndoRedoManager.getInstance().clear();
			}});
		
	}

	@Override
	public void appendDocumentMessage(String username, String message) {
		textArea.append(username + ": " + message + "\n\n");
	}

	@Override
	public void appendLobyMessage(String username, String message) {
		return;
	}

	@Override
	public void disposeView() {
		this.dispose();
	}

	@Override
	public void setCaretLocation(int x, int y) {
		
	}

	@Override
	public void showRowColPosition(int row, int col) {
		
	}

	@Override
	public void setDocumentName(String name) {
		lblDocumentName.setText(name);
	}

	@Override
	public void updateStatisics(String statistics) {
		
	}

	@Override
	public void updateContent(String content) {
		Document doc = textPane.getDocument();
		doc.removeDocumentListener(docListener);
		try {
			doc.remove(0, doc.getLength());
			doc.insertString(0, content, null);
		} catch (BadLocationException e) {e.printStackTrace();}
		doc.addDocumentListener(docListener);
	}

	@Override
	public void find(String text) {
		
	}

	@Override
	public void replace(String text, String replace) {
		
	}

	@Override
	public void showAvailableDocument(ArrayList<DocumentRemote> docLst) {
		return;
	}

	@Override
	public String getDocumentContent() {
		return textPane.getText();
	}

	@Override
	public void updateCollaborators(List<UserRemote> collabs) {
		listModel = new DefaultListModel<UserRemote>();
		for(UserRemote user : collabs) {
			listModel.addElement(user);
		}
		collaboartorsList.setModel(listModel);
	}

	@Override
	public void recvDocUpdate(String type, String text, int length, int location) {
		Document document = textPane.getDocument();
		document.removeDocumentListener(docListener);
		String txt = textPane.getText().substring(0, location);
		UndoRedoManager.getInstance()
			.addUndoCommand(new CommandUndoRedoDistributed(controller, txt));
		if(type.toLowerCase().equals("insert")) {
			try {
				document.insertString(location, text, null);
			} catch (BadLocationException e) {e.printStackTrace();}
		} else if(type.toLowerCase().equals("remove")){
			try {
				document.remove(location, length);
			} catch (BadLocationException e) {e.printStackTrace();}
		} else  if(type.toLowerCase().equals("pull")){
			try {
				UndoRedoManager.getInstance()
				.addRedoCommand(
					new CommandUndoRedoDistributed(controller, text));
				document.remove(0, document.getLength());
				document.insertString(0, text, null);
			} catch (BadLocationException e) {e.printStackTrace();}
		}
		document.addDocumentListener(docListener);
	}
	
	private class OnlineDocumentListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			updateText(arg0);
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			synchronized(textPane) {
				updateText(arg0);
				int changeLength = arg0.getLength();
				int offset = arg0.getOffset();
				int insert = textPane.getCaret().getDot();
				try {
					String text = textPane.getText(offset, changeLength);
					controller.sendDocUpdate("insert", text, changeLength, insert);
				} catch (BadLocationException e) {e.printStackTrace();}
			}
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			synchronized(textPane) {
				updateText(arg0);
				int changeLength = arg0.getLength();
				int offset = arg0.getOffset();
				controller.sendDocUpdate("remove", "", changeLength, offset);
			}
		}
		
		private void updateText(DocumentEvent e) {
			String text = textPane.getText().substring(0, e.getOffset());
			UndoRedoManager.getInstance()
				.addUndoCommand(new CommandUndoRedoDistributed(controller, text));
			int x = textPane.getText().split("[\r]").length;
			int y = e.getOffset() + e.getLength();
			int numOfWords = textPane.getText().split("[  |(|)|]").length;
			controller.updateCaretLocation(x, y);
			controller.updateDocumentStatisics("Number of words: " + numOfWords);
		}
		
	}
}
