package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controller.Controller;
import guicomponents.GUIFactory;

import javax.swing.JTextField;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JSeparator;
import java.awt.GridLayout;

public class FindReplaceDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEnterTextHere;
	private JTextField textField;

	private Controller controller;
	private JFrame parent;
	
	public FindReplaceDialog(JFrame parent, Controller controller) {
		
		setBounds(100, 100, 300, 170);
		setUndecorated(true);
		setAlwaysOnTop(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		JDialog self = this;
		
		this.controller = controller;
		this.parent = parent;
		
		int x = parent.getX() + parent.getWidth() - this.getWidth() - 22;
		int y = parent.getY() + 74;
		
		setLocation(x, y);
		
		addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				System.out.println("Focus lost");
				self.dispose();
			}
		});
		
		
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			panel.setBackground(new Color(50, 50, 50));
			panel.setBorder(new MatteBorder(0, 0, 1, 0, new Color(30, 30, 30)));
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setBackground(new Color(190, 190, 190));
			panel.setBorder(new MatteBorder(0, 2, 0, 2, new Color(130, 130, 130)));
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setBackground(new Color(190, 190, 190));
			panel.setBorder(new MatteBorder(0, 2, 0, 2, new Color(130, 130, 130)));
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.SOUTH);
			panel.setBackground(new Color(50, 50, 50));
			panel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(30, 30, 30)));
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setBackground(new Color(90, 90, 90));
			panel.setLayout(null);
			
			JLabel lblFind = new JLabel("Find:");
			lblFind.setHorizontalAlignment(SwingConstants.CENTER);
			lblFind.setForeground(new Color(244, 244, 255));
			lblFind.setFont(new Font("Courier New", Font.PLAIN, 16));
			lblFind.setBounds(10, 0, 252, 26);
			panel.add(lblFind);
			
			txtEnterTextHere = new JTextField();
			txtEnterTextHere.setText("Enter text here.");
			txtEnterTextHere.setMargin(new Insets(5, 5, 5, 5));
			txtEnterTextHere.setHorizontalAlignment(SwingConstants.CENTER);
			txtEnterTextHere.setForeground(new Color(150, 150, 150));
			txtEnterTextHere.setFont(new Font("Courier New", Font.PLAIN, 11));
			txtEnterTextHere.setColumns(10);
			txtEnterTextHere.setCaretColor(new Color(238, 238, 255));
			txtEnterTextHere.setBorder(new EmptyBorder(5, 10, 5, 10));
			txtEnterTextHere.setBackground(new Color(90, 90, 90));
			txtEnterTextHere.setBounds(10, 20, 252, 24);
			txtEnterTextHere.addFocusListener(new FocusAdapter() {
				public void focusGained(FocusEvent e) {
					txtEnterTextHere.setText("");
				}
			});
			panel.add(txtEnterTextHere);
			{
				JSeparator separator = new JSeparator();
				separator.setBounds(30, 45, 216, 2);
				panel.add(separator);
			}
			{
				JLabel lblReplace = new JLabel("Replace:");
				lblReplace.setHorizontalAlignment(SwingConstants.CENTER);
				lblReplace.setForeground(new Color(244, 244, 255));
				lblReplace.setFont(new Font("Courier New", Font.PLAIN, 16));
				lblReplace.setBounds(10, 55, 252, 26);
				panel.add(lblReplace);
			}
			{
				textField = new JTextField();
				textField.setText("Enter text here.");
				textField.setMargin(new Insets(5, 5, 5, 5));
				textField.setHorizontalAlignment(SwingConstants.CENTER);
				textField.setForeground(new Color(150, 150, 150));
				textField.setFont(new Font("Courier New", Font.PLAIN, 11));
				textField.setColumns(10);
				textField.setCaretColor(new Color(238, 238, 255));
				textField.setBorder(new EmptyBorder(5, 10, 5, 10));
				textField.setBackground(new Color(90, 90, 90));
				textField.setBounds(10, 75, 252, 24);
				panel.add(textField);
				textField.addFocusListener(new FocusAdapter() {
					public void focusGained(FocusEvent e) {
						textField.setText("");
					}
				});
			}
			{
				JSeparator separator = new JSeparator();
				separator.setBounds(30, 100, 216, 2);
				panel.add(separator);
			}
			{
				JPanel pnlButtonHolder = new JPanel();
				pnlButtonHolder.setBounds(10, 110, 252, 26);
				pnlButtonHolder.setBackground(new Color(90, 90, 90));
				panel.add(pnlButtonHolder);
				pnlButtonHolder.setLayout(new GridLayout(1, 3));
				{
					JButton cmtfltbtnFind = GUIFactory.createCometFlatButton("Send", new Color(32, 32, 32), new Color(244, 244, 255));
					cmtfltbtnFind.setFont(new Font("Courier New", Font.PLAIN, 11));
					cmtfltbtnFind.setText("Find");
					cmtfltbtnFind.setForeground(new Color(244, 244, 255));
					cmtfltbtnFind.setBorder(new LineBorder(new Color(90, 90, 90)));
					cmtfltbtnFind.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							controller.find(txtEnterTextHere.getText());
						}
					});
					pnlButtonHolder.add(cmtfltbtnFind);
				}
				{
					JButton cmtfltbtnReplace = GUIFactory.createCometFlatButton("Send", new Color(32, 32, 32), new Color(244, 244, 255));
					cmtfltbtnReplace.setFont(new Font("Courier New", Font.PLAIN, 11));
					cmtfltbtnReplace.setText("Replace");
					cmtfltbtnReplace.setForeground(new Color(244, 244, 255));
					cmtfltbtnReplace.setBorder(new LineBorder(new Color(90, 90, 90)));
					pnlButtonHolder.add(cmtfltbtnReplace);
					cmtfltbtnReplace.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							controller.replace(
									txtEnterTextHere.getText(),
									textField.getText());
						}
					});
				}
				{
					JButton cometFlatButton = GUIFactory.createCometFlatButton("Send", new Color(32, 32, 32), new Color(244, 244, 255));
					cometFlatButton.setFont(new Font("Courier New", Font.PLAIN, 11));
					cometFlatButton.setText("Cancel");
					cometFlatButton.setForeground(new Color(244, 244, 255));
					cometFlatButton.setBorder(new LineBorder(new Color(90, 90, 90)));
					cometFlatButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							self.dispose();
						}
					});
					pnlButtonHolder.add(cometFlatButton);
				}
			}
		}
	}
}
