package languages;

import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import languages.ProgrammingLanguage;

public class SymbolTable {

	private List<String> words;
	
	public SymbolTable(String language) {
		
		words = new ArrayList<String>();
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ProgrammingLanguage.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			URL url = getClass().getResource("../languages/" + language.toLowerCase() + ".xml");
			ProgrammingLanguage pl = (ProgrammingLanguage) 
					jaxbUnmarshaller.unmarshal(new File(url.getPath()));
			
			words = pl.getKeyWords();
		} 
		catch (JAXBException e) {}
	}
	
	public List<String> getKeyWords(){
		return words;
	}
	
}
