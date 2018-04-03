package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.MatteBorder;

import controller.ControllerOnline;
import controller.ControllerOnlineImpl;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import guicomponents.GUIFactory;
import languages.Language;
import languages.LanguageManager;

import javax.swing.border.LineBorder;

public class CreateDoucmentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtDocumentName;
	private JComboBox cbxDocumentType;
	private JTextField txtPassword;

	private ControllerOnline controller;

	public CreateDoucmentDialog(ControllerOnline cntrl) {
		
		controller = cntrl;
		
		JDialog self = this;
		
		setBounds(100, 100, 450, 315);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(11, 116, 241));
		setTitle("Comet");
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		MouseAdapter docBtnColorChanger = GUIFactory.createButtonColorChanger
		(
				new Color(1, 96, 181),
				new Color(1, 86, 171),
				new Color(1, 76, 161)
		);
		
		txtDocumentName = new JTextField();
		txtDocumentName.setHorizontalAlignment(SwingConstants.CENTER);
		txtDocumentName.setForeground(new Color(1, 91, 181));
		txtDocumentName.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtDocumentName.setColumns(10);
		txtDocumentName.setBorder(null);
		txtDocumentName.setBackground(new Color(175, 238, 238));
		txtDocumentName.setBounds(189, 75, 235, 32);
		contentPanel.add(txtDocumentName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 414, 2);
		contentPanel.add(separator);
		
		JLabel lblCreateDocument = new JLabel("Create document");
		lblCreateDocument.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateDocument.setForeground(new Color(244, 244, 255));
		lblCreateDocument.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblCreateDocument.setBounds(10, 24, 414, 27);
		contentPanel.add(lblCreateDocument);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 62, 414, 2);
		contentPanel.add(separator_1);
		
		cbxDocumentType = new JComboBox
		(
			LanguageManager.getInstance().getAllLanguages()
		);
		cbxDocumentType.setForeground(new Color(1, 91, 181));
		cbxDocumentType.setFont(new Font("Courier New", Font.PLAIN, 13));
		cbxDocumentType.setBorder(null);
		cbxDocumentType.setBackground(new Color(175, 238, 238));
		cbxDocumentType.setBounds(189, 118, 235, 32);
		contentPanel.add(cbxDocumentType);
		
		JLabel lblDocumentName = new JLabel("Document name:");
		lblDocumentName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocumentName.setForeground(new Color(238, 238, 255));
		lblDocumentName.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblDocumentName.setBorder(null);
		lblDocumentName.setBounds(10, 75, 169, 32);
		contentPanel.add(lblDocumentName);
		
		JLabel lblDocumentType = new JLabel("Document type:");
		lblDocumentType.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocumentType.setForeground(new Color(238, 238, 255));
		lblDocumentType.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblDocumentType.setBorder(null);
		lblDocumentType.setBounds(10, 117, 169, 32);
		contentPanel.add(lblDocumentType);
		
		txtPassword = new JTextField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setForeground(new Color(1, 91, 181));
		txtPassword.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtPassword.setColumns(10);
		txtPassword.setBorder(null);
		txtPassword.setBackground(new Color(175, 238, 238));
		txtPassword.setBounds(243, 161, 181, 32);
		contentPanel.add(txtPassword);
		
		JLabel lblPasswordOptional = new JLabel("Password (optional):");
		lblPasswordOptional.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordOptional.setForeground(new Color(238, 238, 255));
		lblPasswordOptional.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblPasswordOptional.setBorder(null);
		lblPasswordOptional.setBounds(10, 160, 226, 32);
		contentPanel.add(lblPasswordOptional);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 217, 414, 50);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		panel.setBackground(new Color(21, 126, 251));
		
		JButton btnCreateDocument = GUIFactory.createCometFlatButton
		(
				"Create document",
				new Color(1, 91, 181),
				new Color(244, 244, 255)
		);
		btnCreateDocument.addMouseListener(docBtnColorChanger);
		btnCreateDocument.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnCreateDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CometSplashScreen cs = new CometSplashScreen("Creating new document...");
				cs.setVisible(true);
				new Thread(cs).start();
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {}
						String name = txtDocumentName.getText();
						String type = ((Language)cbxDocumentType.getSelectedItem()).getName();
						String password = txtPassword.getText();
						cs.stopAnimation();
						CometDialog cd;
						if(controller.createDocument(name, type, password)) {
							cd = new CometDialog("info", "Document created.");
							cd.setVisible(true);
							controller.displayAllAvailableDocuments();
						}else {
							cd = new CometDialog("warning", "Document creation failed.");
							cd.setVisible(true);
						}
						try {
							Thread.sleep(700);
						} catch (InterruptedException e) {}
						cd.dispose();
						self.dispose();
					}
				}).start();
			}
		});
		panel.add(btnCreateDocument);
		
		JButton btnCancel = GUIFactory.createCometFlatButton
		(
				"Cancel",
				new Color(1, 91, 181),
				new Color(244, 244, 255)
		);
		btnCancel.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnCancel.addMouseListener(docBtnColorChanger);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.dispose();
			}
		});
		panel.add(btnCancel);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 204, 414, 2);
		contentPanel.add(separator_2);
	}
}
