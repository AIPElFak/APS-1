package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import controller.ControllerOnline;
import controller.ControllerOnlineImpl;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import guicomponents.GUIFactory;
import javax.swing.border.LineBorder;

public class CreateDoucmentDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	private ControllerOnline controller;
	
	public static void main(String[] args) {
		try {
			ControllerOnline cntrl = new ControllerOnlineImpl();
			CreateDoucmentDialog dialog = new CreateDoucmentDialog(cntrl);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CreateDoucmentDialog(ControllerOnline cntrl) {
		
		controller = cntrl;
		
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
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(new Color(1, 91, 181));
		textField.setFont(new Font("Courier New", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(new Color(175, 238, 238));
		textField.setBounds(189, 75, 235, 32);
		contentPanel.add(textField);
		
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
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(new Color(1, 91, 181));
		textField_1.setFont(new Font("Courier New", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(new Color(175, 238, 238));
		textField_1.setBounds(189, 118, 235, 32);
		contentPanel.add(textField_1);
		
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
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(new Color(1, 91, 181));
		textField_2.setFont(new Font("Courier New", Font.PLAIN, 13));
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBackground(new Color(175, 238, 238));
		textField_2.setBounds(243, 161, 181, 32);
		contentPanel.add(textField_2);
		
		JLabel lblPasswordoptional = new JLabel("Password (optional):");
		lblPasswordoptional.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasswordoptional.setForeground(new Color(238, 238, 255));
		lblPasswordoptional.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblPasswordoptional.setBorder(null);
		lblPasswordoptional.setBounds(10, 160, 226, 32);
		contentPanel.add(lblPasswordoptional);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 217, 414, 50);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		panel.setBackground(new Color(21, 126, 251));
		
		JButton button = GUIFactory.createCometFlatButton("Create document", new Color(1, 91, 181), new Color(244, 244, 255));
		button.setBorder(new LineBorder(new Color(11, 116, 241)));
		panel.add(button);
		
		JDialog self = this;
		
		JButton btnCancel = GUIFactory.createCometFlatButton("Cancel", new Color(1, 91, 181), new Color(244, 244, 255));
		btnCancel.setBorder(new LineBorder(new Color(11, 116, 241)));
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
