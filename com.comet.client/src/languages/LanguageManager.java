package languages;

import java.io.File;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class LanguageManager {

	Languages languages;
	
	public LanguageManager() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Languages.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			URL url = getClass().getResource("../languages/languages.xml");
			languages = (Languages) 
					jaxbUnmarshaller.unmarshal(new File(url.getPath()));
		} catch (JAXBException e) {System.out.println(e);}
		
	}
	
	public static void main(String[] args) {
		LanguageManager t = new LanguageManager();
	}
	
}
