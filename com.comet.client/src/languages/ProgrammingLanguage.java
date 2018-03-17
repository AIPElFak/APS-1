package languages;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProgrammingLanguage {

	String extension;
    List<String> keyWords = new ArrayList<String>();
    List<String> commentSymbol = new ArrayList<String>();
    List<String> stringSymbol = new ArrayList<String>();
    String name;

    public List<String> getKeyWords() {
        return keyWords;
    }

    @XmlElement(name="keyWord")
    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public List<String> getCommentSymbol() {
        return commentSymbol;
    }
    
    @XmlElement
    public void setCommentSymbol(List<String> symbol) {
        commentSymbol = symbol;
    }

    public List<String> getStringSymbol() {
        return stringSymbol;
    }
    
    @XmlElement
    public void setStringSymbol(List<String> symbol) {
        stringSymbol = symbol;
    }
    
    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
}
