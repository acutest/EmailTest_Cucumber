package com.acutest.EmailTestPerfomance;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendMailUsingAuthentication {
	private static final String SMTP_HOST_NAME = "smtp.office365.com"; 
	private static final String SMTP_AUTH_USER = "username@domain.com";
	private static final String SMTP_AUTH_PWD = "password";
	
	public void sendEmail(String emailMsgTxt, String emailSubjectTxt, String[] recipients, String from) throws Exception {
		SendMailUsingAuthentication smtpMailSender = new SendMailUsingAuthentication();
		smtpMailSender.postMail(recipients, emailSubjectTxt, emailMsgTxt, from);
		System.out.println("Sucessfully Sent mail to All Users");
	}

	private void postMail(String[] recipients, String subject, String message, String from)
			throws MessagingException, AuthenticationFailedException {
		
		boolean debug = false;
		
		// Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.port", 587);
	    props.put("mail.smtp.host", SMTP_HOST_NAME);
	    props.put("mail.smtp.auth", "true");
	    Session session = Session.getInstance(props, new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(SMTP_AUTH_USER,SMTP_AUTH_PWD);
	        }
	    });
		
		session.setDebug(debug);

		// create a message
		Message msg = new MimeMessage(session);
		
		// set the from and to address
		InternetAddress addressFrom = new InternetAddress(from);
		msg.setFrom(addressFrom);
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// Setting the Subject and Content Type
		msg.setSubject(subject);
		msg.setContent(message, "text/plain");
		Transport.send(msg);
	}
}
