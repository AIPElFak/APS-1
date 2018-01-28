package application;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

public class EditorFrame extends JFrame {

	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		panel.add(toolBar, BorderLayout.WEST);
		
		JPanel documentsPanel = new JPanel();
		panel.add(documentsPanel, BorderLayout.EAST);
		documentsPanel.setLayout(new BoxLayout(documentsPanel, BoxLayout.Y_AXIS));
		
		JLabel lblWorkingDocuments = new JLabel("Working documents");
		lblWorkingDocuments.setFont(new Font("Courier New", Font.PLAIN, 14));
		lblWorkingDocuments.setHorizontalAlignment(SwingConstants.CENTER);
		documentsPanel.add(lblWorkingDocuments);
		
		JSeparator documentsSeparator = new JSeparator();
		documentsPanel.add(documentsSeparator);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
	}

}
