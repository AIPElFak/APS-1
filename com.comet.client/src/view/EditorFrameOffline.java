package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.ControllerOffline;
import controller.ControllerOfflineImpl;
import guicomponents.ButtonColorChanger;
import guicomponents.CometEditor;
import guicomponents.CometEditorDocument;
import guicomponents.CometFlatButton;
import guicomponents.TextLineNumber;
import languages.SymbolTable;
import model.ModelImpl;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditorFrameOffline extends JFrame implements View {

	private JTextPane textPane;
	private JPanel contentPane;
	private JLabel lblRowColStatus, lblDocumentName, lblStatistics;
	
	private Style cometStyle;
	private DefaultCaret caret;
	
	private ControllerOffline controller;

	public EditorFrameOffline(ControllerOffline cntrl) {
		initialize();
		controller = cntrl;
	}
	
	private void initialize() {
		setTitle("Comet");
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		
		ButtonColorChanger toolBarColorChanger = new ButtonColorChanger(
			new Color(60, 60, 60),
			new Color(60, 60, 60),
			new Color(60, 60, 60)
		);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createNewDocument();
			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if(jfc.showOpenDialog(contentPane) != JFileChooser.APPROVE_OPTION) return;
				String name = jfc.getSelectedFile().getName();
				String path = jfc.getSelectedFile().getPath();
				controller.openDocument(path, name);
			}
		});
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if(jfc.showSaveDialog(contentPane) != JFileChooser.APPROVE_OPTION) return;
				String name = jfc.getSelectedFile().getName();
				String path = jfc.getSelectedFile().getPath();
				controller.saveDocument(path, name);
			}
		});
		mnFile.add(mntmSave);
		
		mnFile.addSeparator();
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.exitApplication();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmUndo = new JMenuItem("Undo");
		mnEdit.add(mntmUndo);
		
		JMenuItem mntmRedo = new JMenuItem("Redo");
		mnEdit.add(mntmRedo);
		
		mnEdit.addSeparator();
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mnEdit.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mnEdit.add(mntmDelete);
		
		mnEdit.addSeparator();
		
		JMenuItem mntmFindReplace = new JMenuItem("Find / Replace");
		mnEdit.add(mntmFindReplace);
		
		JMenu mnRun = new JMenu("Run");
		menuBar.add(mnRun);
		
		JMenuItem mntmOpenConsole = new JMenuItem("Open console");
		mnRun.add(mntmOpenConsole);
		
		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);
		
		JMenuItem mntmAddLanguageDefinition = new JMenuItem("Add language definition");
		mnSettings.add(mntmAddLanguageDefinition);
		
		JMenuItem mntmManageLanguageDefinitions = new JMenuItem("Manage language definitions");
		mnSettings.add(mntmManageLanguageDefinitions);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmStatistics = new JMenuItem("Statistics");
		mnHelp.add(mntmStatistics);
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
		
		JButton New = new CometFlatButton("", new Color(60,60,60), Color.WHITE);
		New.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/documentEdit.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		New.setToolTipText("Create a new doucment");
		New.setBorderPainted(false);
		New.addMouseListener(toolBarColorChanger);
		toolbar.add(New);
		
		JButton Open = new CometFlatButton("", new Color(60,60,60), Color.WHITE);
		Open.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/open.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		Open.setToolTipText("Open a new doucment");
		Open.addMouseListener(toolBarColorChanger);
		Open.setBorderPainted(false);
		Open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				if(jfc.showOpenDialog(contentPane) != JFileChooser.APPROVE_OPTION) return;
				String name = jfc.getSelectedFile().getName();
				String path = jfc.getSelectedFile().getPath();
				controller.openDocument(path, name);
			}
		});
		toolbar.add(Open);
		
		JLabel topLabelSeparator = new JLabel(" ");
		toolbar.add(topLabelSeparator);
		
		toolbar.addSeparator();
		toolbar.addSeparator();
		
		JButton Paste = new CometFlatButton("", new Color(60,60,60), Color.WHITE);
		Paste.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/paste.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		Paste.setToolTipText("Paste");
		Paste.setBorderPainted(false);
		Paste.addMouseListener(toolBarColorChanger);
		toolbar.add(Paste);
		
		JButton Find = new CometFlatButton("", new Color(60,60,60), Color.WHITE);
		Find.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/search.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		Find.setToolTipText("Find");
		Find.setBorderPainted(false);
		Find.addMouseListener(toolBarColorChanger);
		toolbar.add(Find);
		
		JButton Copy = new CometFlatButton("", new Color(60,60,60), Color.WHITE);
		Copy.setIcon(new ImageIcon(new ImageIcon(getClass()
				.getResource("../resources/copy.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		Copy.setToolTipText("Copy");
		Copy.setBorderPainted(false);
		Copy.addMouseListener(toolBarColorChanger);
		toolbar.add(Copy);
		
		JPanel leftSeparator = new JPanel();
		leftSeparator.setBorder(new MatteBorder(0, 2, 0, 2, new Color(130, 130, 130)));
		leftSeparator.setBackground(new Color(190, 190, 190));
		panel.add(leftSeparator, BorderLayout.EAST);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(81, 186, 251)));
		statusPanel.setBackground(new Color(21, 126, 251));
		statusPanel.setLayout(new FlowLayout());
		contentPane.add(statusPanel, BorderLayout.SOUTH);
		
		lblRowColStatus = new JLabel("Cursor(0, 0)");
		lblRowColStatus.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblRowColStatus.setForeground(Color.LIGHT_GRAY);
		lblRowColStatus.setBorder(new EmptyBorder(0, 15, 0, 15));
		statusPanel.add(lblRowColStatus);
		
		lblStatistics = new JLabel("Number of words: 0");
		lblStatistics.setForeground(Color.LIGHT_GRAY);
		lblStatistics.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblStatistics.setBorder(new EmptyBorder(0, 15, 0, 15));
		statusPanel.add(lblStatistics);
		
		JPanel editorHolder = new JPanel();
		editorHolder.setLayout(new BorderLayout(0, 0));
		
		caret = new DefaultCaret();
		
		textPane = new JTextPane(
				new CometEditorDocument(new SymbolTable("Java")));
		textPane.setCaret(caret);
		textPane.setForeground(Color.WHITE);
		textPane.setCaretColor(Color.WHITE);
		textPane.setFont(new Font("Courier New", Font.PLAIN, 16));
		textPane.setSelectionColor(Color.WHITE);
		textPane.setSelectedTextColor(Color.BLACK);
		cometStyle = textPane.addStyle("CometStyle", null);
		JScrollPane textScroll = new JScrollPane(textPane);
		TextLineNumber tln = new TextLineNumber(textPane);
		textScroll.setRowHeaderView(tln);
		textScroll.setBorder(null);
		textPane.setMargin(new Insets(10, 10, 10, 10));
		textPane.setBackground(new Color(90, 90, 90));
		textPane.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				updateText(e);
			}
			public void insertUpdate(DocumentEvent e) {
				updateText(e);
			}
			public void removeUpdate(DocumentEvent e) {
				updateText(e);
			}
			private void updateText(DocumentEvent e) {
				int x = textPane.getText().split("[\r]").length;
				int y = e.getOffset() + e.getLength();
				int numOfWords = textPane.getText().split("[  |(|)|]").length;
				controller.updateCaretLocation(x, y);
				controller.updateDocumentStatisics("Number of words: " + numOfWords);
			}
		});
		editorHolder.add(textScroll);
		contentPane.add(editorHolder, BorderLayout.CENTER);
		
		JPanel documentTitleHolder = new JPanel();
		documentTitleHolder.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(35, 35, 35)));
		documentTitleHolder.setBackground(new Color(50, 50, 50));
		editorHolder.add(documentTitleHolder, BorderLayout.NORTH);
		
		lblDocumentName = new JLabel("New document");
		lblDocumentName.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblDocumentName.setForeground(new Color(230, 230, 250));
		documentTitleHolder.add(lblDocumentName);
		
		JPanel colaborationHolder = new JPanel();
		contentPane.add(colaborationHolder, BorderLayout.EAST);
		colaborationHolder.setLayout(new BorderLayout(0, 0));
		
		JPanel rightSeparator = new JPanel();
		rightSeparator.setBorder(new MatteBorder(0, 2, 0, 2, new Color(130, 130, 130)));
		rightSeparator.setBackground(new Color(190, 190, 190));
		contentPane.add(rightSeparator, BorderLayout.EAST);
		
		setLocationRelativeTo(null);
	}

	@Override
	public void appendDocumentMessage(String username, String message) {
		return;
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
	public void showRowColPosition(int row, int col) {
		lblRowColStatus.setText("Row " + row + ", Coll " + col);
	}

	@Override
	public void setDocumentName(String name) {
		lblDocumentName.setText(name);
	}

	@Override
	public void updateContent(String content) {
		textPane.setText(content);
	}

	@Override
	public void setCaretLocation(int x, int y) {
		this.lblRowColStatus.setText("Cursor(" + x + ", " + y + ") ");
	}

	@Override
	public void updateStatisics(String statistics) {
		lblStatistics.setText(statistics);
	}
}