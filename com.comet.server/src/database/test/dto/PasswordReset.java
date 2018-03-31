package database.test.dto;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class PasswordReset {
	
	String host = "smtp.gmail.com" ;
    String user = "comettexteditor@gmail.com";
    String pass = "BojnikVranje123";
    String from = "comettexteditor@gmail.com";
    String subject = "Password Reset";
    
    String to = "";
    String newPass = "";
    String messageText = "Dear %s, you have asked for password reset.\nYour new password is %s."
    						+ "\nThis should be used as a temporary password so we suggest you to change it as soon as posible."
    						+ "\n\nHave a nice day, \nComet team.";
    
    
    public PasswordReset(String recipientEmail,String username) {
    	this.to = recipientEmail;
    	this.newPass = generateString();
    	this.messageText = String.format(messageText, username,newPass);
    }
    
    public void Send() {
    	try
    	{
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");		//Dodato.. from: https://stackoverflow.com/questions/16115453/javamail-could-not-convert-socket-to-tls-gmail

           // java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(false);							//Zamenjeno: sessionDebug->false
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from,"Comet Team"));	//Dodato "Comet Team" za from...
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }
    	catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	
    
    private String generateString() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-","");
		return uuid.substring(0, 16);
	}
    
    public String getNewPassword() {
    	return this.newPass;
    }
}
