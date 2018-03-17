package languages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Languages {
    
	private Language[] language;

    public Language[] getLanguage ()
    {
        return language;
    }

    @XmlElement
    public void setLanguage (Language[] language)
    {
        this.language = language;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [language = "+language+"]";
    }
}