package everyonesSurvey;

import java.util.*;  
import javax.mail.*;

import javax.mail.internet.*;  
import javax.activation.*;  
public class Mailman {
    
    protected static void sendMail(String from, String to, String title, String text){
	
		String host = "localhost";//or IP address  
		  
		//Get the session object  
		Properties properties = System.getProperties();  
		properties.setProperty("mail.smtp.host", host);  
		Session session = Session.getDefaultInstance(properties);  
		  
		//compose the message  
		try{  
		    MimeMessage message = new MimeMessage(session);  
		    message.setFrom(new InternetAddress(from));  
		    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		    message.setSubject(title);  
		    message.setText(text);  
		  
		         // Send message  
		    Transport.send(message);  
		    System.out.println("message sent");  
		  
		}catch (MessagingException e) {
		    e.printStackTrace();
		}  
    }
    
}
