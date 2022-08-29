package org.vtop.CourseRegistration.Common.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;


@Service
public class MailUtility
{
	private static final Logger logger = LogManager.getLogger(MailUtility.class);
	
	public static String triggerMail(String subject, String body, String attachementFilePath, String to)
	{
		String FromEmailID = "xxxxx@vit.ac.in";
		String FromEmailPass = "xxxxxxxxxxxxxxxxx";			
 
		Properties props = new Properties();
		props.put("mail.smtp.host", "xxxxxxxxxxxxxxxx");
		props.put("mail.smtp.port", "xx"); 		  
		props.put("mail.smtp.auth", "true");		 
		props.put("mail.smtp.ssl.trust", "*");
		
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FromEmailID, FromEmailPass);
			}
		});
		 
		 
		try
		{		
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FromEmailID));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(body ,"text/html");		
 
			Transport.send(message);
			logger.trace("\n <<<<<< Email Sent Successfully >>>>>>>");
			
			return "SUCCESS";
		 }
		 catch(Exception e)
		 {
			 logger.trace(e);
			 return e.toString();
		 }
	}
}
