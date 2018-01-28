package application;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import communication.Server;
import implementations.ServerImpl;

public class CometServer {
	
	private Server server;
	private String host, port, name;
	
	private CometServer(String host, String port, String name) {
		try {
			LocateRegistry.createRegistry(Integer.parseInt(port));
			server = new ServerImpl();
			Naming.rebind("rmi://" + host + ":" + port +"/" + name, server);
			this.host = host; this.port = port; this.name = name;
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws RemoteException, MalformedURLException, AlreadyBoundException, NotBoundException {
		CometServer cs = new CometServer("127.0.0.1", "1099", "CometServer");
		System.out.println("Server has started working...");
	}
	
	protected void finalize() throws Throwable {
		try {
			Naming.unbind("rmi://" + host + ":" + port +"/" + name);
			super.finalize();
			System.exit(0);
		} 
		catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}