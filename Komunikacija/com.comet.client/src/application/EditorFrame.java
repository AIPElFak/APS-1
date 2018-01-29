package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit;

import guicomponents.TextLineNumber;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EditorFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea lineNumbers;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditorFrame frame = new EditorFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditorFrame() {
		setTitle("Comet");setIconImage(new ImageIcon(getClass().getResource("../resources/cometIconMin.png")).getImage());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolbar = new JToolBar();
		toolbar.setBorder(new EmptyBorder(8, 8, 8, 8));
		toolbar.setOrientation(JToolBar.VERTICAL);
		toolbar.setBackground(new Color(60, 60, 60));
		toolbar.setForeground(new Color(230, 230, 250));
		toolbar.setFloatable(false);
		panel.add(toolbar, BorderLayout.WEST);
		
		JLabel New = new JLabel("");
		New.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../resources/documentEdit.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		toolbar.add(New);
		
		JLabel Open = new JLabel("");
		Open.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../resources/open.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		toolbar.add(Open);
		
		JLabel topLabelSeparator = new JLabel(" ");
		toolbar.add(topLabelSeparator);
		
		JLabel label_1 = new JLabel(" ");
		toolbar.add(label_1);
		
		JLabel label_3 = new JLabel(" ");
		toolbar.add(label_3);
		
		JLabel Paste = new JLabel("");
		Paste.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../resources/paste.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		toolbar.add(Paste);
		
		JLabel Find = new JLabel("");
		Find.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../resources/search.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		toolbar.add(Find);
		
		JLabel Copy = new JLabel("");
		Copy.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../resources/copy.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		toolbar.add(Copy);
		
		JLabel label = new JLabel(" ");
		toolbar.add(label);
		
		JLabel label_2 = new JLabel(" ");
		toolbar.add(label_2);
		
		JLabel label_4 = new JLabel(" ");
		toolbar.add(label_4);
		
		JLabel SaveVersion = new JLabel("");
		SaveVersion.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../resources/cloudUpload.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		toolbar.add(SaveVersion);
		
		JLabel PullVersion = new JLabel("");
		PullVersion.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("../resources/cloudDownload.png"))
				.getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		toolbar.add(PullVersion);
		
		JPanel leftSeparator = new JPanel();
		leftSeparator.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(170, 170, 170)));
		leftSeparator.setBackground(new Color(150, 150, 150));
		panel.add(leftSeparator, BorderLayout.EAST);
		
		JPanel statusPanel = new JPanel();
		statusPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(81, 186, 251)));
		statusPanel.setBackground(new Color(21, 126, 251));
		contentPane.add(statusPanel, BorderLayout.SOUTH);
		
		JLabel statusLabel = new JLabel("Status bar");
		statusLabel.setFont(new Font("Courier New", Font.PLAIN, 13));
		statusLabel.setForeground(Color.LIGHT_GRAY);
		statusPanel.add(statusLabel);
		
		JPanel editorHolder = new JPanel();
		editorHolder.setLayout(new BorderLayout(0, 0));
		
		JTextPane textPane = new JTextPane();
		JScrollPane textScroll = new JScrollPane(textPane);
		//TextLineNumber tln = new TextLineNumber(textPane);
		//textScroll.setRowHeaderView(tln);
		textScroll.setBorder(null);
		textPane.setMargin(new Insets(10, 10, 10, 10));
		textPane.setBackground(new Color(90, 90, 90));
		editorHolder.add(textScroll);
		contentPane.add(editorHolder, BorderLayout.CENTER);
		
		JPanel documentTitleHolder = new JPanel();
		documentTitleHolder.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(35, 35, 35)));
		documentTitleHolder.setBackground(new Color(50, 50, 50));
		editorHolder.add(documentTitleHolder, BorderLayout.NORTH);
		
		JLabel lblDocumentName = new JLabel("Document name");
		lblDocumentName.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblDocumentName.setForeground(new Color(230, 230, 250));
		documentTitleHolder.add(lblDocumentName);
		
		JPanel colaborationHolder = new JPanel();
		contentPane.add(colaborationHolder, BorderLayout.EAST);
		colaborationHolder.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 1, 0, 0, (Color) new Color(170, 170, 170)));
		panel_1.setBackground(new Color(150, 150, 150));
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
		separator.setBounds(0, 0, 143, 2);
		panel_3.add(separator);
		
		JPanel panel_4 = new JPanel();
		JScrollPane collaboratorsHolder = new JScrollPane(panel_4, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		collaboratorsHolder.setBorder(null);
		panel_4.setBackground(new Color(85, 85, 85));
		collaboratorsHolder.setBounds(0, 13, 143, 213);
		panel_3.add(collaboratorsHolder);
		
		JLabel lblChat = new JLabel("Chat");
		lblChat.setHorizontalAlignment(SwingConstants.CENTER);
		lblChat.setForeground(new Color(230, 230, 250));
		lblChat.setFont(new Font("Courier New", Font.PLAIN, 16));
		lblChat.setBounds(0, 242, 143, 19);
		panel_3.add(lblChat);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 266, 143, 2);
		panel_3.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 237, 143, 2);
		panel_3.add(separator_2);
		
		JTextArea textArea = new JTextArea();
		JScrollPane chatHolder = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatHolder.setBounds(0, 279, 143, 202);
		chatHolder.setBorder(null);
		textArea.setBackground(new Color(100, 100, 100));
		panel_3.add(chatHolder);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 492, 143, 2);
		panel_3.add(separator_3);
		
		textField = new JTextField();
		textField.setBounds(0, 505, 143, 20);
		panel_3.add(textField);
		textField.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Courier New", Font.PLAIN, 13));
		btnSend.setForeground(new Color(230, 230, 250));
		btnSend.setBorderPainted(false);
		btnSend.setContentAreaFilled(false);
		btnSend.setFocusPainted(false);
		btnSend.setOpaque(true);
		btnSend.setBounds(0, 539, 143, 34);
		btnSend.setBackground(new Color(40, 40, 40));
		panel_3.add(btnSend);
		
		setLocationRelativeTo(null);
		
	}
}
