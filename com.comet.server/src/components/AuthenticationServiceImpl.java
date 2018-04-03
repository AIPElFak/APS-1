package components;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import database.dao.BusinessLogic;
import database.dto.User;
import database.test.dto.Info;
import database.test.dto.PasswordReset;
import utilities.UserRemote;
import utilities.UserRemoteImpl;
import communication.Client;

public class AuthenticationServiceImpl extends UnicastRemoteObject implements AuthenticationService {

	private ArrayList<Client> clients;
	private ArrayList<UserRemote> users;
	
	public AuthenticationServiceImpl() throws RemoteException {
		super();
		clients = new ArrayList<Client>();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public boolean login(Client cl, String username, String password) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		User user = logic.login(username, password);
		if(user == null) return false;
		
		File f = new File("src/userImages/"+user.getId()+".jpg");
		byte[] imageBytes = null;
		try {
			imageBytes = Files.readAllBytes(f.toPath());
		} catch (IOException e) {
		//	e.printStackTrace();
		}
		
		UserRemoteImpl ur = new UserRemoteImpl(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), imageBytes);
		cl.setUserData(ur);
		return true;
	}

	@Override
	public boolean logout(Client cl) throws RemoteException {
		if(cl.getUserData() == null) return false;
		cl.setUserData(null);
		return true;
	}

	@Override
	public boolean signin(Client cl, String username, String password, String email, byte[] imageBytes) throws RemoteException {
		
		BusinessLogic logic = new BusinessLogic();
		User user = new User(username, password, email);
		Info info = logic.register(user);
		if(!info.isSuccessful()) return false;
		UserRemoteImpl ur = new UserRemoteImpl(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), imageBytes);
		cl.setUserData(ur);
		
		BufferedImage image;
		try {
			image = ImageIO.read(new ByteArrayInputStream(imageBytes));
			File outputFile = new File("src/userImages/"+user.getId()+".jpg");	//user ID umesto toga
			ImageIO.write(image, "jpg", outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		UserRemote ur = cl.getUserData();
		return logic.deleteUserById(ur.getId());
	}

	@Override
	public boolean modifyUserData(Client cl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		UserRemoteImpl ur = (UserRemoteImpl)cl.getUserData();
		User user = new User(ur.getUsername(), ur.getPassword(), ur.getEmail());
		user.setId(ur.getId());
		return logic.updateUser(user);
	}

	@Override
	public boolean resetPassword(Client cl) throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		UserRemoteImpl ur = (UserRemoteImpl)cl.getUserData();
		PasswordReset reset = new PasswordReset(ur.getEmail(),ur.getUsername());
		
		User user = new User(ur.getUsername(), reset.getNewPassword(), ur.getEmail());
		user.setId(ur.getId());
		
		if(!logic.updateUser(user)) return false;
		reset.Send();
		return true;
	}
	
	@Override
	public boolean addClient(Client cl) throws RemoteException {
		if(clients.contains(cl)) return false;
		clients.add(cl);
		return true;
	}

	@Override
	public boolean removeClient(Client cl) throws RemoteException {
		if(!clients.contains(cl)) return false;
		clients.remove(cl);
		return true;
	}

	@Override
	public boolean editUserInformations(Client client, String username, String email, String password)
			throws RemoteException {
		BusinessLogic logic = new BusinessLogic();
		User user = new User(client.getUserData().getId(),username, password, email);
		if(!logic.updateUser(user))return false;
		UserRemote newUserData = new UserRemoteImpl(user);
		client.setUserData(newUserData);
		return true;
	}

}
