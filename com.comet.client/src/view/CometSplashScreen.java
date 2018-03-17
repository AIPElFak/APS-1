package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;

public class CometSplashScreen extends JFrame implements Runnable {

	private JPanel contentPane;
	private Image images[];
	private JLabel lblAnimation;
	private JLabel lblMessage;
	private boolean animate;
	
	public CometSplashScreen(String message) {
		
		images = new Image[9];
		for(int i = 0; i < 9; i++) {
			images[i] = new ImageIcon(getClass()
					.getResource("../resources/ss" + i + ".png")).getImage();
		}
		
		animate = true;
		
		initialize(message);
	}

	@Override
	public void run() {
		int counter = 0;
		while(animate) {
			lblAnimation.setIcon(new ImageIcon(
					images[counter % 9].getScaledInstance(109, 117, Image.SCALE_DEFAULT)));
			counter++;
			try {
				Thread.sleep(130);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.dispose();
	}

	private void initialize(String message) {
		setBounds(100, 100, 342, 146);
		setAlwaysOnTop(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon(getClass()
				.getResource("../resources/cometIconMin.png")).getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(1, 66, 171)));
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(1, 86, 191));
		setContentPane(contentPane);
		
		lblAnimation = new JLabel();
		lblAnimation.setBounds(10, 11, 109, 117);
		lblAnimation.setIcon(new ImageIcon(
				images[0].getScaledInstance(109, 117, Image.SCALE_DEFAULT)));
		contentPane.add(lblAnimation);
		
		lblMessage = new JLabel("<html>" + message + "</html>");
		lblMessage.setFont(new Font("Courier New", Font.PLAIN, 21));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(new Color(238, 238, 255));
		lblMessage.setBounds(140, 11, 186, 117);
		contentPane.add(lblMessage);
		
		setUndecorated(true);
		setVisible(true);
	}
	
	public void startAnimation() {
		animate = true;
	}
	
	public void stopAnimation() {
		animate = false;
	}
}
