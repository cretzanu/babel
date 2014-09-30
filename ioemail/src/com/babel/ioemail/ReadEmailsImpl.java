package com.babel.ioemail;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 * @author liviu.cretu
 * @version 1.0
 * @created 26-Sep-2014 10:57:11 AM
 */
public class ReadEmailsImpl implements ReadEmails {

	private static final String USERNAME_GMAIL_COM = "arhitecturi.informationale@gmail.com";
	private List<EmailMessageHandler> handlers = new LinkedList<EmailMessageHandler>();
	public ReadEmailsImpl(List<EmailMessageHandler> handlers){

		this.handlers.addAll(handlers);
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	public void readEmails() {
		//read new email messages  and send them  to the handlers
		Properties props=new Properties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getInstance(props, null);
		try{
		Store store = session.getStore();
		String pass=getPassword();
		store.connect("imap.gmail.com", USERNAME_GMAIL_COM, pass );
		Folder inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		System.out.println(inbox.getMessageCount()+ " messages in inbox");
		//we are interested only in unread messages
		Flags seen = new Flags(Flags.Flag.RECENT);
        FlagTerm unseenFlagTerm = new FlagTerm(seen, false);
        Message[] messages = inbox.search(unseenFlagTerm);
        //in this example we just process the latest unread message
		Message msg=messages[messages.length-1];
		//notify handlers (aka observers)
		for (EmailMessageHandler handler:this.handlers)
			handler.notify(msg,  USERNAME_GMAIL_COM, pass);
		inbox.close(false);
		store.close();
		}catch(Exception e){
			e.printStackTrace();
		} 
	}
	
	private String getPassword(){
		JPasswordField pf = new JPasswordField();
		int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		String password="";
		if (okCxl == JOptionPane.OK_OPTION) {
		   password = new String(pf.getPassword());
		  
		}
		return password;
	}
}//end ReadEmailsImpl