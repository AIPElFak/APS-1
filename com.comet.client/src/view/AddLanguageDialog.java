package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JList;
import utilities.VersionRemote;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import guicomponents.GUIFactory;
import languages.Language;
import languages.LanguageManager;
import languages.Languages;
import languages.ProgrammingLanguage;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class AddLanguageDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtName;
	private JTextField txtType;
	private JTextField txtKeyWord;
	
	private DefaultListModel<String> model;
	private JList<String> list;
	
	public AddLanguageDialog() {
		
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 335, 579);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setBackground(new Color(21, 126, 251));
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 11, 294, 2);
		contentPanel.add(separator);
		
		JLabel lblAddLanguage = new JLabel("Add Language");
		lblAddLanguage.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddLanguage.setForeground(new Color(244, 244, 255));
		lblAddLanguage.setFont(new Font("Courier New", Font.PLAIN, 18));
		lblAddLanguage.setBounds(10, 24, 294, 27);
		contentPanel.add(lblAddLanguage);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 62, 294, 2);
		contentPanel.add(separator_1);
		
		JLabel lblLanguageName = new JLabel("Language name:");
		lblLanguageName.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguageName.setForeground(new Color(238, 238, 255));
		lblLanguageName.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblLanguageName.setBorder(null);
		lblLanguageName.setBounds(10, 75, 147, 32);
		contentPanel.add(lblLanguageName);
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setForeground(new Color(1, 91, 181));
		txtName.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtName.setColumns(10);
		txtName.setBorder(null);
		txtName.setBackground(new Color(175, 238, 238));
		txtName.setBounds(167, 75, 137, 32);
		contentPanel.add(txtName);
		
		JLabel lblLanguageType = new JLabel("Extension:");
		lblLanguageType.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguageType.setForeground(new Color(238, 238, 255));
		lblLanguageType.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblLanguageType.setBorder(null);
		lblLanguageType.setBounds(10, 118, 147, 32);
		contentPanel.add(lblLanguageType);
		
		txtType = new JTextField();
		txtType.setHorizontalAlignment(SwingConstants.CENTER);
		txtType.setForeground(new Color(1, 91, 181));
		txtType.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtType.setColumns(10);
		txtType.setBorder(null);
		txtType.setBackground(new Color(175, 238, 238));
		txtType.setBounds(167, 118, 137, 32);
		contentPanel.add(txtType);
		
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setBackground(new Color(175, 238, 238));
		list.setBounds(10, 161, 397, 219);
		
		JScrollPane scroll = new JScrollPane(
				list,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(10, 201, 294, 179);
		contentPanel.add(scroll);
		
		JLabel lblAddKeyword = new JLabel("Input key word:");
		lblAddKeyword.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddKeyword.setForeground(new Color(238, 238, 255));
		lblAddKeyword.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblAddKeyword.setBorder(null);
		lblAddKeyword.setBounds(10, 391, 147, 32);
		contentPanel.add(lblAddKeyword);
		
		txtKeyWord = new JTextField();
		txtKeyWord.setHorizontalAlignment(SwingConstants.CENTER);
		txtKeyWord.setForeground(new Color(1, 91, 181));
		txtKeyWord.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtKeyWord.setColumns(10);
		txtKeyWord.setBorder(null);
		txtKeyWord.setBackground(new Color(175, 238, 238));
		txtKeyWord.setBounds(167, 391, 137, 32);
		contentPanel.add(txtKeyWord);
		
		JDialog self = this;
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(21, 126, 251));
		panel.setBounds(10, 479, 294, 50);
		contentPanel.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnAdd = GUIFactory.createCometFlatButton("Add", new Color(1, 91, 181), new Color(244, 244, 255));
		btnAdd.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgrammingLanguage lan = new ProgrammingLanguage();
				lan.setName(txtName.getText());
				List<String> keyWords = new ArrayList<String>();
				for(int i = 0; i < model.size(); i++) {
					keyWords.add(model.get(i));
				}
				lan.setKeyWords(keyWords);
				Language lng = new Language();
				lng.setName(txtName.getText());
				lng.setExtension(txtType.getText());
				Languages languages = new Languages();
				Language[] lngList = new Language[LanguageManager.getInstance().getAllLanguages().length + 1];
				for(int i = 0; i < LanguageManager.getInstance().getAllLanguages().length; i++) {
					lngList[i] = LanguageManager.getInstance().getAllLanguages()[i];
				}
				lngList[lngList.length - 1] = lng;
				languages.setLanguage(lngList);				

				try {
				
				JAXBContext jaxbContext = JAXBContext.newInstance(Languages.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(languages, new File("src/languages/languages.xml"));
				
				jaxbContext = JAXBContext.newInstance(ProgrammingLanguage.class);
				jaxbMarshaller = jaxbContext.createMarshaller();

				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				File f = new File("src/languages/", txtType.getText() + ".xml");
				if(!f.exists())
					jaxbMarshaller.marshal(lan, f);

				} catch (JAXBException ex) {System.out.println(ex);}
			}
		});
		panel.add(btnAdd);
		
		JButton btnCancel = GUIFactory.createCometFlatButton("Cancel", new Color(1, 91, 181), new Color(244, 244, 255));
		btnCancel.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				self.dispose();
			}
		});
		panel.add(btnCancel);
		
		JButton btnAddKeyword = GUIFactory.createCometFlatButton("Add key word", new Color(1, 61, 151), new Color(244, 244, 255));
		btnAddKeyword.setBorder(new LineBorder(new Color(11, 116, 241)));
		btnAddKeyword.setBounds(10, 436, 294, 32);
		btnAddKeyword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyWord = txtKeyWord.getText();
				model.addElement(keyWord);
				txtKeyWord.setText("");
			}
		});
		contentPanel.add(btnAddKeyword);
		
		JLabel lblKeyWords = new JLabel("Key words");
		lblKeyWords.setHorizontalAlignment(SwingConstants.CENTER);
		lblKeyWords.setForeground(new Color(238, 238, 255));
		lblKeyWords.setFont(new Font("Courier New", Font.PLAIN, 13));
		lblKeyWords.setBorder(new MatteBorder(0, 0, 1, 0, new Color(238, 238, 255)));
		lblKeyWords.setBounds(10, 161, 294, 32);
		contentPanel.add(lblKeyWords);
	}
}
