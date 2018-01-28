package application;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import communication.Client;
import communication.Server;
import implementations.ClientImpl;

public class CometClient {
	
	private Server server;
	private Client client;
	
	private CometClient(String host, String port, String name) {
		try {
			server = (Server) 
					Naming.lookup("rmi://" + host + ":" + port + "/" + name);
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
	}
	
	public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException{
		CometClient cc = new CometClient("127.0.0.1", "1099", "CometServer");
	}
	
	protected void finalize () throws Throwable {
		try {
			server.removeClient(client);
			super.finalize();
		} 
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}