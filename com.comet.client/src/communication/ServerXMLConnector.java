package communication;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import configuration.RmiConfiguration;

public class ServerXMLConnector {
	
	public  Server getConnection() {
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(RmiConfiguration.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		URL url = getClass().getResource("../configuration/Configuration.xml");
		RmiConfiguration cfg = (RmiConfiguration) 
				jaxbUnmarshaller.unmarshal(new File(url.getPath()));
		Server server = (Server) 
			Naming.lookup("rmi://" + cfg.getRmiHost() + ":" + cfg.getRmiPort() + "/CometServer");
		
		return server;
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JAXBException e) {
			
		}
		return null;
	}
	
}
