package application;

import javax.swing.SwingUtilities;

import view.LoginFrame;

public class CometClient {
	
	public static void main(String args[]) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				LoginFrame lf = new LoginFrame();
				lf.setVisible(true);
			}}
		);
		
	}
	
}