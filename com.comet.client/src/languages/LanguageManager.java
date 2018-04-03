package languages;

import java.io.File;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LanguageManager {

	private Languages languages;
	
	private Language selectedLanguage;
	
	private static LanguageManager instance;
	
	private LanguageManager() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Languages.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			URL url = getClass().getResource("../languages/languages.xml");
			languages = (Languages) 
					jaxbUnmarshaller.unmarshal(new File(url.getPath()));
		} catch (JAXBException e) {System.out.println(e);}
		
	}
	
	public static LanguageManager getInstance() {
		if(instance == null) instance = new LanguageManager();
		return instance;
	}
	
	public  void setLanguageByType(String type) {
		for(Language l : languages.getLanguage())
			if(l.getName().toLowerCase().equals(type.toLowerCase())) {
				selectedLanguage = l;
				break;
			}
		selectedLanguage = null;
	}
	
	public Language[] getAllLanguages() {
		return languages.getLanguage();
	}
	
	public SymbolTable getSymbolTable() {
		if(selectedLanguage == null) return null;
		return new SymbolTable(selectedLanguage.getExtension().toLowerCase());
	}
	
}
