package com.babel.ioemail;

import javax.mail.Message;
import javax.mail.Session;


/**
 * @author liviu.cretu
 * @version 1.0
 * @created 30-Sep-2014 5:34:51 PM
 */
public abstract class EmailMessageHandler {

	public EmailMessageHandler(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param emailMessage
	 */
	public abstract void notify(Message emailMessage,  String user, String pass );
}//end EmailMessageHandler