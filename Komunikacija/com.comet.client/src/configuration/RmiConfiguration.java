package configuration;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RmiConfiguration {

    String rmihost;
    String rmiport;
    int id;

    public String getRmiHost() {
        return rmihost;
    }

    @XmlElement
    public void setRmiHost(String host) {
        rmihost = host;
    }

    public String getRmiPort() {
        return rmiport;
    }

    @XmlElement
    public void setRmiPort(String port) {
        rmiport = port;
    }

    public int getId() {
        return id;
    }

    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

}
