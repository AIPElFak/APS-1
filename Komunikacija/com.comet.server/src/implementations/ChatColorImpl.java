package implementations;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import utilities.ChatColor;

public class ChatColorImpl extends UnicastRemoteObject implements ChatColor {
	
	private int red, green, blue;
	
	protected ChatColorImpl() throws RemoteException {
		super();
		red = (int) Math.random() * 169 + 1;
		green = (int) Math.random() * 169 + 1;
		blue = (int) Math.random() * 169 + 1;
	}

	private static final long serialVersionUID = 1L;

	@Override
	public int getRed() throws RemoteException {
		return red;
	}

	@Override
	public int getBlue() throws RemoteException {
		return green;
	}

	@Override
	public int getGreen() throws RemoteException {
		return blue;
	}

}
