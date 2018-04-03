package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;

import utilities.DocumentRemote;
import utilities.VersionRemote;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.ArrayList;

import guicomponents.GUIFactory;
import languages.LanguageManager;
import model.ModelImpl;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import controller.ControllerOnline;

public class CometVersionDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private DefaultListModel<VersionRemote> listModel;
	private JList<VersionRemote> list;
	
	private ControllerOnline controller;
	
	public CometVersionDialog(ControllerOnline cntrl) {
		
		setBounds(100, 100, 450, 462);
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.setBackground(new Color(21, 126, 251));
		setLocationRelativeTo(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 414, 2);
		contentPanel.add(separator);
		
		JDialog self = this;
		
		controller = cntrl;
		
		JLabel lblDocumentVersions = new JLabel("Document versions");
		lblDocumentVersions.setHorizontalAlignment(SwingConstants.CENTER);
		lblDocumentVersions.setForeground(new Color(244, 244, 255));
		lblDocumentVersions.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblDocumentVersions.setBounds(10, 24, 414, 27);
		contentPanel.add(lblDocumentVersions);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 62, 414, 2);
		contentPanel.add(separator_1);
		
		list = new JList<VersionRemote>();
		list.setBackground(new Color(175, 238, 238));
		list.setBounds(10, 122, 414, 219);
		JScrollPane listScrollBars = new JScrollPane(
				list,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScrollBars.setBorder(null);
		listScrollBars.setBounds(10, 122, 414, 219);
		contentPanel.add(listScrollBars);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 352, 414, 2);
		contentPanel.add(separator_2);
		
		JPanel buttonHolder = new JPanel();
		buttonHolder.setBackground(new Color(21, 126, 251));
		buttonHolder.setBounds(10, 362, 414, 50);
		contentPanel.add(buttonHolder);
		buttonHolder.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnLoadVersion = GUIFactory.createCometFlatButton("Load version", new Color(1, 91, 181), new Color(244, 244, 255));
		btnLoadVersion.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnLoadVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VersionRemote vers = list.getSelectedValue();
				if(vers == null) return;
				CometSplashScreen cs = new CometSplashScreen("Opening version...");
				cs.setVisible(true);
				new Thread(cs).start();
				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(1500);
							cs.stopAnimation();
							String content = vers.getContent();
							//String content = controller.openDocumentVersion(vers.getId());
							controller.updateDocumentContent(content);
							self.dispose();
						} 
						catch (InterruptedException e) 
						{} 
						catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});
		buttonHolder.add(btnLoadVersion);
		
		JButton btnCancel = GUIFactory.createCometFlatButton("Cancel", new Color(1, 91, 181), new Color(244, 244, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				self.dispose();
			}
		});
		btnCancel.setBorder(new LineBorder(new Color(11, 116, 241)));
		buttonHolder.add(btnCancel);
		
		JPanel listHeader = new JPanel();
		listHeader.setBackground(new Color(21, 126, 251));
		listHeader.setBounds(10, 75, 414, 32);
		contentPanel.add(listHeader);
		listHeader.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel label = new JLabel("Document name");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(238, 238, 255));
		label.setFont(new Font("Courier New", Font.PLAIN, 13));
		label.setBorder(new MatteBorder(0, 0, 1, 1, new Color(238, 238, 255)));
		listHeader.add(label);
		
		JLabel label_1 = new JLabel("Document type");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(238, 238, 255));
		label_1.setFont(new Font("Courier New", Font.PLAIN, 13));
		label_1.setBorder(new MatteBorder(0, 0, 1, 0, new Color(238, 238, 255)));
		listHeader.add(label_1);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setForeground(new Color(238, 238, 255));
		lblUser.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblUser.setBorder(new MatteBorder(0, 1, 1, 0, new Color(238, 238, 255)));
		listHeader.add(lblUser);
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
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
					public void run() {
						listModel = new DefaultListModel<VersionRemote>();
						ArrayList<VersionRemote> lst = controller.getAllDocumentVersions();
						if (lst == null) return;
						for(VersionRemote v : lst) {
							listModel.addElement(v);
						}
						list.setModel(listModel);
					}
				}).start();
			}
			
		});
	}
}
