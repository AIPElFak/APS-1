package database.test.dto;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordSecurity {
	 
	 public static String SHA_256(String passwordToHash){
		 String passwordGenerated = null;
		 try {
			 MessageDigest digest = MessageDigest.getInstance("SHA-256");
			 byte[] hash = digest.digest(passwordToHash.getBytes("UTF-8"));
			 StringBuffer hexString = new StringBuffer();
			 
			 for(int i = 0; i< hash.length; i++) {
				 String hex = Integer.toHexString(hash[i] & 0xff);	//convert to positive
				 if(hex.length() == 1) hexString.append('0');
				 hexString.append(hex);
			 	}
			 passwordGenerated = hexString.toString();
		 }
		 catch(NoSuchAlgorithmException e) {
			 e.printStackTrace();
		 }
		 catch(UnsupportedEncodingException e) {
			 e.printStackTrace();
		 }
		 return passwordGenerated;
	 }
}
