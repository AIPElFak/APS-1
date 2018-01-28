package controller;

import view.View;

public interface Controller {

	public void continueOffline(View view);
	
	public void logIn(View view, String username, String password);
	
	public void signIn(View view, String username, String password, String email);
	
}
