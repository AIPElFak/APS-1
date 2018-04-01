package application;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import communication.Server;
import communication.ServerImpl;
import components.AuthenticationServiceImpl;
import components.DocumentServiceImpl;
import components.MessageServiceImpl;
import configuration.RmiConfiguration;

public class CometServer {
	
	private Server server;
	private String host, port, name;
	
	private CometServer() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RmiConfiguration.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			URL url = getClass().getResource("../configuration/Configuration.xml");
			RmiConfiguration cfg = (RmiConfiguration) 
					jaxbUnmarshaller.unmarshal(new File(url.getPath()));
			LocateRegistry.createRegistry(Integer.parseInt(cfg.getRmiPort()));
			Naming.rebind("rmi://" + cfg.getRmiHost() + ":" + cfg.getRmiPort() +"/Authenticator", new AuthenticationServiceImpl());
			Naming.rebind("rmi://" + cfg.getRmiHost() + ":" + cfg.getRmiPort() +"/DocumentSynchronizer", new DocumentServiceImpl());
			Naming.rebind("rmi://" + cfg.getRmiHost() + ":" + cfg.getRmiPort() +"/MessageServer", new MessageServiceImpl());
			server = new ServerImpl();
			Naming.rebind("rmi://" + cfg.getRmiHost() + ":" + cfg.getRmiPort() +"/CometServer", server);
			this.host = cfg.getRmiHost();
			this.port = cfg.getRmiPort(); 
			this.name = "CometServer";
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
			
		}
		catch (JAXBException e) {
			
		}
	}
	
	public static void main(String args[]) throws RemoteException, MalformedURLException, AlreadyBoundException, NotBoundException {
		CometServer cs = new CometServer();
		System.out.println("Server has started working...");
	}
	
}