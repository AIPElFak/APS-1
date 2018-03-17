package languages;

import java.awt.Color;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import languages.ProgrammingLanguage;

public class SymbolTable {

	private HashMap<String, Color> words;
	private Color keyWordColor, commentColor, stringColor;
	
	public SymbolTable(String language) {
		
		words = new HashMap<String, Color>();
		keyWordColor = new Color(255, 0, 0);
		stringColor = new Color(0, 255, 0);
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ProgrammingLanguage.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			URL url = getClass().getResource("../languages/" + language.toLowerCase() + ".xml");
			ProgrammingLanguage pl = (ProgrammingLanguage) 
					jaxbUnmarshaller.unmarshal(new File(url.getPath()));
			for(String keyWord : pl.getKeyWords())
				words.put(keyWord, keyWordColor);
			for(String commentSymbol : pl.getCommentSymbol())
				words.put(commentSymbol, keyWordColor);
			for(String stringSymbol : pl.getStringSymbol())
				words.put(stringSymbol, stringColor);
		} 
		catch (JAXBException e) {}
	}
	
	public Color getColorForWord(String word) {
		return words.get(word);
	}
	
}
