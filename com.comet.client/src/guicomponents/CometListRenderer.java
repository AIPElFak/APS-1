package guicomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import utilities.DocumentRemote;

class CometListRenderer extends JPanel implements ListCellRenderer<DocumentRemote> {

	private JLabel first, second, third;
	
	public CometListRenderer() {
        
        setBackground(new Color(175, 238, 238));
        setForeground(new Color(1, 91, 181));
        setLayout(new GridLayout(1, 3));
        setBorder(new MatteBorder(0, 0, 1, 0, new Color(115, 178, 178)));
       
			
		ImageIcon imageIcon = new ImageIcon(getClass()
				 .getResource("../resources/document.png"));
			
		first = new JLabel();
		first.setFont(new Font("Courier New", Font.PLAIN, 13));
		first.setIcon(new ImageIcon(imageIcon.getImage()
				.getScaledInstance(36, 36, Image.SCALE_DEFAULT)));
		first.setBorder(new EmptyBorder(3, 30, 3, 0));
	    add(first);
	        
	    second = new JLabel();
	    second.setFont(new Font("Courier New", Font.PLAIN, 13));
	    second.setBorder(new EmptyBorder(3, 30, 3, 0));
	    add(second);
	        
	    third = new JLabel();
	    third.setFont(new Font("Courier New", Font.PLAIN, 13));
	    third.setBorder(new EmptyBorder(3, 30, 3, 0));
	    add(third);
        
    }
	
	@Override
	public Component getListCellRendererComponent(JList<? extends DocumentRemote> list, DocumentRemote doc, int index,
			boolean isSelected, boolean hasFocus) {

			try {
				first.setText(doc.getName());
				second.setText("<html><body><b>&#128218</b>  " + doc.getType() + "</body></html>");
				third.setText("<html><body>  "  + (doc.isPasswordProtected() ? " &#128274 password" : " &#128275 free") + "</body></html>");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
       if (isSelected) {
            first.setForeground(new Color(229, 39, 39));
            second.setForeground(new Color(229, 39, 39));
            third.setForeground(new Color(229, 39, 39));
        } else {
            first.setForeground(new Color(1, 91, 181));
            second.setForeground(new Color(1, 91, 181));
            third.setForeground(new Color(1, 91, 181));
        }
		
		return this;
	}
	 
    
     
}
