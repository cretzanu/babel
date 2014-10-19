package com.babel.ioemail;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:57:11 AM
 */
public class SendEmailImpl implements SendEmail {

	public SendEmailImpl() {

	}

	/**
	 * 
	 * @param p
	 */
	public void sendEmail(EmailMessage p) {
		// TODO move the code from the handler here
		// check the "to"
		if (p.getTo() == null)
			throw new IllegalArgumentException(
					"the field -to- is mandatory and should contain one or more e-mail addresses");
		System.out.println("Sending email message...TODO!");
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
}// end SendEmailImpl