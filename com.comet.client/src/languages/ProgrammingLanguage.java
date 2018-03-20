package languages;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProgrammingLanguage {

    List<String> keyWords = new ArrayList<String>();
    String name;

    public List<String> getKeyWords() {
        return keyWords;
    }

    @XmlElement(name="keyWord")
    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }
    
    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
}
