package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import communication.Client;
import communication.ClientImpl;
import communication.Server;
import configuration.RmiConfiguration;

public class CometClient {
	
	private Server server;
	private Client client;
	
	private CometClient() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RmiConfiguration.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			URL url = getClass().getResource("../configuration/Configuration.xml");
			RmiConfiguration cfg = (RmiConfiguration) 
					jaxbUnmarshaller.unmarshal(new File(url.getPath()));
			server = (Server) 
				Naming.lookup("rmi://" + cfg.getRmiHost() + ":" + cfg.getRmiPort() + "/CometServer");
			client =  new ClientImpl();
			client.addServer(server);
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
	}
	
	public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException, JAXBException{
		CometClient cc = new CometClient();
	}
	
}