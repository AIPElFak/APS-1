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

		byte[] imageBytes = null;
		File f = new File("src/userImages/"+user.getId()+".jpg");
		if(f.exists()) {
			try {
				imageBytes = Files.readAllBytes(f.toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		
		if(imageBytes != null) {
			BufferedImage image;
			try {
				image = ImageIO.read(new ByteArrayInputStream(imageBytes));
				File outputFile = new File("src/userImages/"+user.getId()+".jpg");
				ImageIO.write(image, "jpg", outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		UserRemoteImpl ur = new UserRemoteImpl(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), imageBytes);
		cl.setUserData(ur);
		return true;
	}

	@Override
	public boolean deleteAccount(Client cl) throws RemoteException {
		File file = new File("src/userImages/"+cl.getUserData().getId()+".jpg");
		if(file.exists()) file.delete();
		
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
	public boolean editUserInformations(Client client, String username, String email, String password, byte[] imageBytes, boolean changedImgFlag)
			throws RemoteException {

		if(imageBytes != null) {
			BufferedImage image;
			try {
				image = ImageIO.read(new ByteArrayInputStream(imageBytes));
				File outputFile = new File("src/userImages/"+client.getUserData().getId()+".jpg");
				ImageIO.write(image, "jpg", outputFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if(changedImgFlag) {	//imao sliku pa kliknuo na set default image i sacuvao
			File file = new File("src/userImages/"+client.getUserData().getId()+".jpg");
			file.delete();
		}
		
		BusinessLogic logic = new BusinessLogic();
		User user = new User(client.getUserData().getId(),username, password, email);
		if(!logic.updateUser(user))return false;
		UserRemoteImpl newUserData = new UserRemoteImpl(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(), imageBytes);
		client.setUserData(newUserData);
		return true;
	}

}
