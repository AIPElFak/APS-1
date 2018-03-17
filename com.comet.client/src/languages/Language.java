package languages;

import javax.xml.bind.annotation.XmlAttribute;

public class Language {
	
    private String extension;

    private String name;

    public String getExtension ()
    {
        return extension;
    }

    @XmlAttribute
    public void setExtension (String extension)
    {
        this.extension = extension;
    }

    public String getName ()
    {
        return name;
    }

    @XmlAttribute
    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [extension = "+extension+", name = "+name+"]";
    }
}