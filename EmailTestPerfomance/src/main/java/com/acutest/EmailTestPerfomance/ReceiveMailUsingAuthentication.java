package com.acutest.EmailTestPerfomance;

import javax.mail.*;
import java.util.*;

public class ReceiveMailUsingAuthentication {
	private static final String SMTP_HOST_NAME = "outlook.office365.com";
	private static final String SMTP_AUTH_PWD = "password";

	public static void receiveMail(String emailFromAddress, String emailSubjectTxt, String emailInboxUser)
			throws Exception {
		ReceiveMailUsingAuthentication imapMailReceiver = new ReceiveMailUsingAuthentication();
		imapMailReceiver.getMail(emailFromAddress, emailSubjectTxt, emailInboxUser);
		System.out.println("Sucessfully Read Email in INBOX folder");
	}

	public String getMail(String sender, String subject, String receiver)
			throws MessagingException, AuthenticationFailedException {

		String message = null;

		Properties props = new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect(SMTP_HOST_NAME, receiver, SMTP_AUTH_PWD);
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);

			int numberOfMessages = inbox.getMessageCount();
			System.out.println("Messages in inbox: " + numberOfMessages);

			for (int i = 1; i <= numberOfMessages; i++) {
				Message msg = inbox.getMessage(i);
				Address[] in = msg.getFrom();

				String subj = msg.getSubject();

				for (Address address : in) {
					if (address.toString().equals(sender) && subj.equals(subject)) {

						Object content = msg.getContent();

						if (content instanceof String) {
							String body = (String) msg.getContent();
							message = ("FROM: " + address.toString());
							message += "\n" + ("SENT DATE: " + msg.getSentDate());
							message += "\n" + ("SUBJECT: " + subj);
							message += "\n" + ("BODY:" + body);
						} else if (content instanceof Multipart) {
							//I am sending a very simple message at the moment
							//so it is not multipart
							Multipart mp = (Multipart) msg.getContent();
							BodyPart bp = mp.getBodyPart(0);
							message = ("FROM:" + address.toString());
							message += "\n" + ("SENT DATE:" + msg.getSentDate());
							message += "\n" + ("SUBJECT:" + msg.getSubject());
							message += "\n" + ("CONTENT:" + bp.getContent());
						}

						return message;
					}

				}

			}
			

		} catch (Exception mex) {
			mex.printStackTrace();
		}
		
		return message;

	}

}
