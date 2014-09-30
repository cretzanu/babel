package com.babel.ioemail.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.babel.ioemail.EmailMessageHandler;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 30-Sep-2014 5:34:51 PM
 */
public class CreateOrderHandler extends EmailMessageHandler {

	private static final String VALID_SUBJECT = "new order";

	public CreateOrderHandler() {

	}

	public void finalize() throws Throwable {
		super.finalize();
	}

	/**
	 * 
	 * @param emailMessage
	 */
	public void notify(Message emailMessage, String user, String pass) {
		// checking if this message is for me
		// careful with this! You don't want have an unintended reply to...
		try {
			if (!VALID_SUBJECT.equals(emailMessage.getSubject()))
				return; // not for me
			System.out.println(emailMessage.getSubject());
			// now we should check if the message comes from our sales force
			// TODO: implement here a filter for trusted email addresses only

			// expecting a message body structured according to the
			// CreateCSVOrder protocol
			Multipart mp = (Multipart) emailMessage.getContent();
			BodyPart bp = mp.getBodyPart(0);
			String response = this.sendCreateCSVOrderMessage(bp.getContent()
					.toString());
			// String response="ok";
			this.replyServerResponse(emailMessage, response, user, pass);

		} catch (Throwable e) {
			// log and quietly ignore
			e.printStackTrace();
		}

	}

	/**
	 * reply to that message to inform the sender about the result of the new
	 * order process
	 * 
	 * @throws MessagingException
	 */
	private void replyServerResponse(Message originalMessage, String replyText,
			final String user, final String pass) throws MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, pass);
					}
				});

		String replyTo = InternetAddress.toString(originalMessage.getReplyTo());
		if (replyTo != null) {
			System.out.println("Reply-to: " + replyTo);
		}
		String to = InternetAddress.toString(originalMessage
				.getRecipients(Message.RecipientType.TO));
		if (to != null) {
			System.out.println("To: " + to);
		}
		Message replyMessage = new MimeMessage(session);
		replyMessage = (MimeMessage) originalMessage.reply(false);
		replyMessage.setFrom(new InternetAddress(to));
		replyMessage.setText(replyText);
		replyMessage.setReplyTo(originalMessage.getReplyTo());

		// Send the message by authenticating the SMTP server
		// Create a Transport instance and call the sendMessage
		Transport t = session.getTransport("smtp");
		try {
			// connect and send

			t.connect(user, pass);
			t.sendMessage(replyMessage, replyMessage.getAllRecipients());
		} finally {
			t.close();
		}
		System.out.println("message replied successfully ....");

	}

	private String sendCreateCSVOrderMessage(String newOrderMsg)
			throws UnknownHostException, IOException {
		Socket clientSocket = null;
		try {
			clientSocket = new Socket("localhost", 4444);// check this port to
															// be the same as
															// your server
															// socket
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
					true);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			// Send the text message with the new Order
			out.println(newOrderMsg);
			out.print((char) 13);// signal the end of the message

			String response = in.readLine();
			System.out
					.println("server response (the new order's external ID): "
							+ response);
			clientSocket.close();
			return response;

		} finally {
			clientSocket.close();
		}

	}
}// end CreateOrderHandler